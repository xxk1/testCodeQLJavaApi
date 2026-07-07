package com.lztech.site.service.courseknowledgegraph;
import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.course.CourseResourceKnowledgePoint;
import com.lztech.domain.model.course.CourseVersion;
import com.lztech.domain.model.course.enums.CourseContentTypeEnum;
import com.lztech.domain.model.course.enums.CourseVersionStatus;
import com.lztech.domain.model.knowledgegraph.*;
import com.lztech.domain.model.knowledgegraph.enums.BusinessType;
import com.lztech.domain.model.knowledgegraph.enums.KnowledgeNodeLevel;
import com.lztech.domain.model.knowledgegraph.enums.LevelType;
import com.lztech.persistence.repositories.course.CourseResourceKnowledgePointRepository;
import com.lztech.persistence.repositories.courseknowledgegraph.*;
import com.lztech.persistence.repositories.courseversion.CourseVersionRepository;
import com.lztech.site.constants.Result;
import com.lztech.site.constants.ResultStatus;
import com.lztech.site.service.authorityapi.AuthorityApiService;
import com.lztech.site.service.courseconstruction.CourseCompletionService;
import com.lztech.site.service.courseknowledgegraphaigeneratetask.CourseKnowledgeGraphAiGenerateTaskService;
import com.lztech.site.service.event.EventService;
import com.lztech.site.until.DateUtils;
import com.lztech.site.until.IpUtil;
import com.lztech.site.until.Neo4jUtil;
import com.lztech.site.viewmodel.authorityapi.UserResource;
import com.lztech.site.viewmodel.courseknowledgegraph.CourseKnowledgeGraphNodeTreeModel;
import com.lztech.site.viewmodel.courseknowledgegraph.ImportCourseKnowledgeGraphNode;
import com.lztech.site.viewmodel.courseknowledgegraph.ImportCourseKnowledgeGraphNodeParam;
import com.lztech.site.viewmodel.courseknowledgegraph.ImportCourseKnowledgeGraphNodeResource;
import com.lztech.site.viewmodel.event.KnowledgeGraphDomainTopicVo;
import com.lztech.site.viewmodel.event.KnowledgeStructureTopicVo;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;
import org.neo4j.driver.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.stream.Collectors;
import static com.lztech.site.constants.Constant.*;
import static com.lztech.site.constants.ConstantDataVisual.DATA_VISUAL_SOURCE_CLOUD_CLASSROOM;
import static org.neo4j.driver.Values.parameters;
@Service
public class CourseKnowledgeGraphExportService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseKnowledgeGraphExportService.class);
    @Autowired
    private CourseKnowledgeGraphDomainRepository courseKnowledgeGraphDomainRepository;
    @Autowired
    private Neo4jUtil neo4jUtil;
    @Autowired
    private CourseKnowledgeGraphService courseKnowledgeGraphService;
    @Autowired
    private AuthorityApiService authorityApiService;
    @Autowired
    private CourseKnowledgeGraphActionLogRepository courseKnowledgeGraphActionLogRepository;
    @Autowired
    private CourseCompletionService courseCompletionService;
    @org.springframework.beans.factory.annotation.Value("${dataVisual.enable}")
    private Boolean dataVisualEnable;
    @Autowired
    private EventService eventService;
    @Autowired
    private CourseVersionRepository courseVersionRepository;
    @Autowired
    private CourseKnowledgeGraphAiGenerateTaskService aiGenerateTaskService;
    @Autowired
    private CourseKnowledgeGraphNodeQuestionRepository courseKnowledgeGraphNodeQuestionRepository;
    @Autowired
    private CourseKnowledgeGraphNodeResourceRepository courseKnowledgeGraphNodeResourceRepository;
    @Autowired
    private CourseKnowledgeGraphNodeResourceFileRepository courseKnowledgeGraphNodeResourceFileRepository;
    @Autowired
    private CourseKnowledgeGraphNodeVideoInfoTextRepository courseKnowledgeGraphNodeVideoInfoTextRepository;
    @Autowired
    private CourseResourceKnowledgePointRepository courseResourceKnowledgePointRepository;

    public ImportCourseKnowledgeGraphNodeResource importCourseKnowledgeGraphNode(HttpServletRequest request, String userId, String userName,
                                                                                 MultipartFile file, Course course) {
        List<ImportCourseKnowledgeGraphNode> importCourseKnowledgeGraphNodes = new ArrayList<>();
        ImportCourseKnowledgeGraphNodeResource importCourseKnowledgeGraphNodeResource = new ImportCourseKnowledgeGraphNodeResource();
        try {
            if (file != null) {
                String fileName = file.getOriginalFilename();
                String uploadUrl = request.getSession().getServletContext().getRealPath("\\upload\\");
                String stringUrl = uploadUrl + "\\" + UUID.randomUUID() + fileName;
                File createFile = new File(uploadUrl);
                File fileUrl = new File(stringUrl);
                String ip = IpUtil.getIpAddr(request);
                if (!createFile.exists()) {
                    createFile.mkdir();
                }
                file.transferTo(fileUrl);
                importCourseKnowledgeGraphNodeResource = importExcel(stringUrl, importCourseKnowledgeGraphNodes, userId, userName, course, ip);
                if (importCourseKnowledgeGraphNodeResource.getMessage().contains("error-")) {
                    return importCourseKnowledgeGraphNodeResource;
                }
                fileUrl.delete();
            }
        } catch (Exception e) {
            LOGGER.error("importCourseKnowledgeGraphNode->{}", e);
            return null;
        }
        /*推送新知识点数据*/

        return importCourseKnowledgeGraphNodeResource;
    }

    public ImportCourseKnowledgeGraphNodeResource importExcel(String xlsPath,
                                                              List<ImportCourseKnowledgeGraphNode> indexDetails,
                                                              String userId,
                                                              String userName, Course course, String ip) {
        ImportCourseKnowledgeGraphNodeResource importCourseKnowledgeGraphNodeResource = new ImportCourseKnowledgeGraphNodeResource();
        try {
            String[] titleArr = new String[]{"一级知识点", "二级知识点", "三级知识点", "四级知识点", "五级知识点", "六级知识点",
                    "七级知识点", "八级知识点", "九级知识点", "十级知识点", "前置知识点", "后置知识点", "关联知识点", "重要层级", "知识点内容详情"};
            if (xlsPath != null) {
                XSSFWorkbook workBook = null;
                FileInputStream fileIn = new FileInputStream(xlsPath);
                workBook = XSSFWorkbookFactory.createWorkbook(fileIn);
                XSSFSheet sheet = workBook.getSheetAt(0);
                XSSFRow row = sheet.getRow(1);
                if (verifyModel(titleArr, row)) {
                    importCourseKnowledgeGraphNodeResource.setMessage("error-请使用导入模版导入");
                    return importCourseKnowledgeGraphNodeResource;
                }
                importCourseKnowledgeGraphNodeResource = getSheet(titleArr, sheet, indexDetails, userId, userName, course, ip);
                fileIn.close();
            }
        } catch (Exception e) {
            LOGGER.error("importExcel->{}", e);
            e.printStackTrace();
        }

        return importCourseKnowledgeGraphNodeResource;
    }

    private boolean verifyModel(String[] titleArr, XSSFRow row) {
        for (short i = 0; i < titleArr.length; i++) {
            if (row == null) {
                return true;
            }
            if (row.getCell(i) != null) {
                row.getCell(i).setCellType(CellType.STRING);
                if (!row.getCell(i).getStringCellValue().trim().equals(titleArr[i])) {
                    return true;
                }
            } else {
                return true;
            }
        }
        return false;
    }

    private ImportCourseKnowledgeGraphNodeResource getSheet(String[] titleArr, XSSFSheet sheet,
                                                            List<ImportCourseKnowledgeGraphNode> importCourseKnowledgeGraphNodeList,
                                                            String userId, String userName, Course course, String ip) {
        ImportCourseKnowledgeGraphNodeResource importCourseKnowledgeGraphNodeResource = new ImportCourseKnowledgeGraphNodeResource();
        boolean isNewCourseGenerated = false;
        CourseKnowledgeGraphDomain existCourseKnowledgeGraphDomain =
                courseKnowledgeGraphDomainRepository.getEffectiveCourseKnowledgeGraphDomainByCourseId(course.getId());
        if (existCourseKnowledgeGraphDomain == null) {
            isNewCourseGenerated = true;
        }

        CourseKnowledgeGraphDomain courseKnowledgeGraphDomain =
                courseKnowledgeGraphDomainRepository.getEffectiveCourseKnowledgeGraphDomainByCourseId(course.getId());
        if (!isNewCourseGenerated && courseKnowledgeGraphDomain.getNodeCount() > 1) {
            List<CourseKnowledgeGraphNode> oldCourseKnowledgeGraphNodeList =
                    neo4jUtil.getCourseKnowledgeGraphDomainAllNodeList(courseKnowledgeGraphDomain.getId());
            List<CourseKnowledgeGraphNodeRelationship> oldCourseKnowledgeGraphNodeRelationshipList =
                    neo4jUtil.getCourseKnowledgeGraphDomainAllRelationshipList(courseKnowledgeGraphDomain.getId());
            List<CourseKnowledgeGraphNodeQuestion> oldCourseKnowledgeGraphNodeQuestionList =
                    courseKnowledgeGraphNodeQuestionRepository.findByCourseKnowledgeGraphDomain(courseKnowledgeGraphDomain);
            List<CourseKnowledgeGraphNodeResource> oldCourseKnowledgeGraphNodeResourceList =
                    courseKnowledgeGraphNodeResourceRepository.findByCourseKnowledgeGraphDomain(courseKnowledgeGraphDomain);
            List<CourseKnowledgeGraphNodeVideoInfoText> oldCourseKnowledgeGraphNodeVideoInfoTextList =
                    courseKnowledgeGraphNodeVideoInfoTextRepository.findByCourseKnowledgeGraphDomain(courseKnowledgeGraphDomain);
            List<String> nodeIdList = oldCourseKnowledgeGraphNodeList.stream().map(CourseKnowledgeGraphNode::getId).collect(Collectors.toList());
            List<CourseResourceKnowledgePoint> oldCourseResourceKnowledgePointList =
                    courseResourceKnowledgePointRepository.findByKnowledgePointIdIn(nodeIdList);

            List<CourseVersion> courseVersions = courseVersionRepository.findByCourseInAndCourseVersionStatus(
                    Collections.singletonList(course), CourseVersionStatus.IN_USE);
            CourseVersion courseVersion = courseVersions.get(0);
            courseKnowledgeGraphDomain = aiGenerateTaskService.createCourseKnowledgeGraphDomain(course, courseVersion, userId, userName);
            courseKnowledgeGraphDomain = courseKnowledgeGraphDomainRepository.saveAndFlush(courseKnowledgeGraphDomain);
            if (dataVisualEnable) {
                KnowledgeGraphDomainTopicVo knowledgeGraphDomainTopicVo = getKnowledgeGraphDomainTopicVo(courseKnowledgeGraphDomain);
                eventService.sendKnowledgeGraphDomainTopicVo(knowledgeGraphDomainTopicVo);
            }
            Map<String, CourseKnowledgeGraphNode> newCourseKnowledgeGraphNodeMap = new HashMap<>();
            List<CourseKnowledgeGraphNode> newCourseKnowledgeGraphNodeList = new ArrayList<>();
            for (CourseKnowledgeGraphNode courseKnowledgeGraphNode : oldCourseKnowledgeGraphNodeList) {
                CourseKnowledgeGraphNode newCourseKnowledgeGraphNode = getCourseKnowledgeGraphNode(courseKnowledgeGraphNode);
                newCourseKnowledgeGraphNodeMap.put(courseKnowledgeGraphNode.getId(), newCourseKnowledgeGraphNode);
                newCourseKnowledgeGraphNodeList.add(newCourseKnowledgeGraphNode);
            }
            List<CourseKnowledgeGraphNodeRelationship> newCourseKnowledgeGraphNodeRelationshipList = new ArrayList<>();
            createNewCourseKnowledgeGraphNodeRelationshipList(
                    oldCourseKnowledgeGraphNodeRelationshipList, newCourseKnowledgeGraphNodeMap, newCourseKnowledgeGraphNodeRelationshipList);
            CourseKnowledgeGraphNode rootCourseKnowledgeGraphNode = newCourseKnowledgeGraphNodeList.stream().filter(courseKnowledgeGraphNode ->
                    courseKnowledgeGraphNode.getKnowledgeNodeLevel() == KnowledgeNodeLevel.ZERO_LEVEL.getIndex()).findFirst().get();
            List<CourseKnowledgeGraphNode> childCourseKnowledgeGraphNodeList =
                    newCourseKnowledgeGraphNodeList.stream().filter(courseKnowledgeGraphNode ->
                    !courseKnowledgeGraphNode.getId().equals(rootCourseKnowledgeGraphNode.getId())).collect(Collectors.toList());
            neo4jUtil.createRootCourseKnowledgeGraphNode(rootCourseKnowledgeGraphNode, courseKnowledgeGraphDomain.getId());
            List<CourseKnowledgeGraphNodeRelationship> parentChildCourseKnowledgeGraphNodeRelationshipList = new ArrayList<>();
            List<CourseKnowledgeGraphNodeRelationship> frontRearKnowledgeGraphRelationshipList = new ArrayList<>();
            List<CourseKnowledgeGraphNodeRelationship> relevanceKnowledgeGraphRelationshipList = new ArrayList<>();
            for (CourseKnowledgeGraphNodeRelationship courseKnowledgeGraphNodeRelationship : newCourseKnowledgeGraphNodeRelationshipList) {
                if (courseKnowledgeGraphNodeRelationship.getRelationshipType().equals("PARENT_CHILD")) {
                    parentChildCourseKnowledgeGraphNodeRelationshipList.add(courseKnowledgeGraphNodeRelationship);
                } else if (courseKnowledgeGraphNodeRelationship.getRelationshipType().equals("FRONT_REAR")) {
                    frontRearKnowledgeGraphRelationshipList.add(courseKnowledgeGraphNodeRelationship);
                } else if (courseKnowledgeGraphNodeRelationship.getRelationshipType().equals("RELEVANCE")) {
                    relevanceKnowledgeGraphRelationshipList.add(courseKnowledgeGraphNodeRelationship);
                }
            }
            childCourseKnowledgeGraphNodeList =
                    childCourseKnowledgeGraphNodeList.stream().sorted(Comparator.comparingInt(CourseKnowledgeGraphNode::getKnowledgeNodeLevel)
                            .thenComparingInt(CourseKnowledgeGraphNode::getSort)).collect(Collectors.toList());
            saveChildCourseKnowledgeGraphNodeList(childCourseKnowledgeGraphNodeList,
                    parentChildCourseKnowledgeGraphNodeRelationshipList, courseKnowledgeGraphDomain);
            LOGGER.info(">>>courseId->【{}】新知识图谱知识点保存结束", courseKnowledgeGraphDomain.getCourse().getId());
            LOGGER.info(">>>courseId->【{}】新知识图谱相关关系开始保存", courseKnowledgeGraphDomain.getCourse().getId());
            saveNewCourseKnowledgeGraphNodeRelationshipList(
                    courseKnowledgeGraphDomain, frontRearKnowledgeGraphRelationshipList, relevanceKnowledgeGraphRelationshipList);
            updateCourseKnowledgeGraphDomainData(userId, userName, courseKnowledgeGraphDomain);
            LOGGER.info(">>>courseId->【{}】新知识图谱相关关系保存结束", courseKnowledgeGraphDomain.getCourse().getId());
            LOGGER.info(">>>courseId->【{}】新知识图谱知识点相关题目关系开始保存", courseKnowledgeGraphDomain.getCourse().getId());
            saveNewCourseKnowledgeGraphNodeQuestionList(
                    oldCourseKnowledgeGraphNodeQuestionList, newCourseKnowledgeGraphNodeMap, courseKnowledgeGraphDomain);
            LOGGER.info(">>>courseId->【{}】新知识图谱知识点相关题目关系保存结束", courseKnowledgeGraphDomain.getCourse().getId());
            LOGGER.info(">>>courseId->【{}】新知识图谱知识点相关资源关系开始保存", courseKnowledgeGraphDomain.getCourse().getId());
            saveNewCourseKnowledgeGraphNodeResourceList(
                    oldCourseKnowledgeGraphNodeResourceList, newCourseKnowledgeGraphNodeMap, courseKnowledgeGraphDomain);
            LOGGER.info(">>>courseId->【{}】新知识图谱知识点相关资源关系保存结束", courseKnowledgeGraphDomain.getCourse().getId());
            LOGGER.info(">>>courseId->【{}】新知识图谱知识点相关视频关联信息开始保存", courseKnowledgeGraphDomain.getCourse().getId());
            saveNewCourseKnowledgeGraphNodeVideoInfoTextList(
                    oldCourseKnowledgeGraphNodeVideoInfoTextList, newCourseKnowledgeGraphNodeMap, courseKnowledgeGraphDomain);
            LOGGER.info(">>>courseId->【{}】新知识图谱知识点相关视频关联信息保存结束", courseKnowledgeGraphDomain.getCourse().getId());

            LOGGER.info(">>>courseId->【{}】新知识图谱知识点相关关联资源开始保存", courseKnowledgeGraphDomain.getCourse().getId());
            saveNewCourseResourceKnowledgePointList(
                    oldCourseResourceKnowledgePointList, newCourseKnowledgeGraphNodeMap, courseKnowledgeGraphDomain, userId, userName);
            LOGGER.info(">>>courseId->【{}】新知识图谱知识点相关相关关联资源保存结束", courseKnowledgeGraphDomain.getCourse().getId());

        }
        //树状数据
        CourseKnowledgeGraphNodeTreeModel courseKnowledgeGraphNodeTreeModel =
                courseKnowledgeGraphService.getCourseKnowledgeGraphTree(course, userId, userName);
        ImportCourseKnowledgeGraphNodeParam importCourseKnowledgeGraphNodeParam = new ImportCourseKnowledgeGraphNodeParam();
        importCourseKnowledgeGraphNodeParam.setUserId(userId);
        importCourseKnowledgeGraphNodeParam.setUserName(userName);
        importCourseKnowledgeGraphNodeParam.setCourseKnowledgeGraphDomain(courseKnowledgeGraphDomain);
        //所有知识点
        List<CourseKnowledgeGraphNode> courseKnowledgeGraphNodes = neo4jUtil
                .getCourseKnowledgeGraphDomainAllNodeList(courseKnowledgeGraphDomain.getId());
        String[] knowledgeGraphNodeArr = new String[]{"一级知识点", "二级知识点", "三级知识点", "四级知识点", "五级知识点", "六级知识点",
                "七级知识点", "八级知识点", "九级知识点", "十级知识点"};
        CourseKnowledgeGraphNodeTreeModel oldModel = null;
        for (Row rows : sheet) {
            final int fileSecondModel = 2, modelLength = 9;
            if (rows.getRowNum() < fileSecondModel) {
                continue;
            }
            int isNull = 0;
            isNull = getAlIsNullNum(titleArr, (XSSFRow) rows, isNull);
            if (isNull == IMPORT_KNOWLEDGE_NODE_COLUMN_COUNT) {
                continue;
            }
            int isNullNum = 0;
            isNullNum = getAlIsNullNum(knowledgeGraphNodeArr, (XSSFRow) rows, isNullNum);
            if (isNullNum == IMPORT_KNOWLEDGE_NODE_MAX_LEVEL) {
                importCourseKnowledgeGraphNodeResource.setMessage("error-第" + (rows.getRowNum() + 1) + "行未填写知识点");
                return importCourseKnowledgeGraphNodeResource;
            } else if (isNullNum < modelLength) {
                importCourseKnowledgeGraphNodeResource.setMessage("error-第" + (rows.getRowNum() + 1) + "行填写超过一个知识点");
                return importCourseKnowledgeGraphNodeResource;
            }
            importCourseKnowledgeGraphNodeParam.setImportCourseKnowledgeGraphNode(new ImportCourseKnowledgeGraphNode());
            importCourseKnowledgeGraphNodeParam.setOldModel(new CourseKnowledgeGraphNodeTreeModel());
            ImportCourseKnowledgeGraphNodeResource importCourseKnowledgeGraphNodeResource2 = getImportCourseKnowledgeGraphNodeResource(
                    titleArr, importCourseKnowledgeGraphNodeList, rows, courseKnowledgeGraphNodeTreeModel,
                    importCourseKnowledgeGraphNodeParam, importCourseKnowledgeGraphNodeResource);
            if (importCourseKnowledgeGraphNodeResource2 != null) {
                return importCourseKnowledgeGraphNodeResource2;
            }
            extractRelevanceKnowledgeGraphNode(importCourseKnowledgeGraphNodeParam.getImportCourseKnowledgeGraphNode().getIsSave(),
                    importCourseKnowledgeGraphNodeParam.getImportCourseKnowledgeGraphNode(), courseKnowledgeGraphNodes);
            if (importCourseKnowledgeGraphNodeParam.getImportCourseKnowledgeGraphNode().getIsUpdate() && ObjectUtils.isNotEmpty(oldModel)) {
                importCourseKnowledgeGraphNodeParam.getImportCourseKnowledgeGraphNode().setIsUpdate(true);
            }
            importCourseKnowledgeGraphNodeParam.getImportCourseKnowledgeGraphNode().setRow(rows.getRowNum() + 1);
            importCourseKnowledgeGraphNodeList.add(importCourseKnowledgeGraphNodeParam.getImportCourseKnowledgeGraphNode());
        }
        if (CollectionUtils.isEmpty(importCourseKnowledgeGraphNodeList)) {
            importCourseKnowledgeGraphNodeResource.setMessage("error-空数据不能导入");
            return importCourseKnowledgeGraphNodeResource;
        }
        List<ImportCourseKnowledgeGraphNode> importCourseKnowledgeGraphNodes = importCourseKnowledgeGraphNodeList.stream().filter(node ->
                !CollectionUtils.isEmpty(node.getPreKnowledgeGraphNodeIdList())
                        || !CollectionUtils.isEmpty(node.getPostKnowledgeGraphNodeIdList())
                        || !CollectionUtils.isEmpty(node.getRelevanceKnowledgeGraphNodeIdList())).collect(Collectors.toList());
        ImportCourseKnowledgeGraphNodeResource importCourseKnowledgeGraphNodeResource2 = getImportCourseKnowledgeGraphNodeResource(
                importCourseKnowledgeGraphNodes, courseKnowledgeGraphNodes, importCourseKnowledgeGraphNodeResource);
        if (importCourseKnowledgeGraphNodeResource2 != null) {
            return importCourseKnowledgeGraphNodeResource2;
        }
        ImportCourseKnowledgeGraphNodeResource importCourseKnowledgeGraphNodeResource1 =
                getImportCourseKnowledgeGraphNodeResource(importCourseKnowledgeGraphNodes, importCourseKnowledgeGraphNodeResource);
        if (importCourseKnowledgeGraphNodeResource1 != null) {
            return importCourseKnowledgeGraphNodeResource1;
        }
        return getImportCourseKnowledgeGraphNodeResource(
                importCourseKnowledgeGraphNodeList, userId, userName, courseKnowledgeGraphDomain, importCourseKnowledgeGraphNodes,
                importCourseKnowledgeGraphNodeResource, ip);
    }

    private void saveNewCourseResourceKnowledgePointList(List<CourseResourceKnowledgePoint> oldCourseResourceKnowledgePointList,
                                                         Map<String, CourseKnowledgeGraphNode> newCourseKnowledgeGraphNodeMap,
                                                         CourseKnowledgeGraphDomain newCourseKnowledgeGraphDomain,
                                                         String accessUserId, String accessUserName) {

        List<CourseResourceKnowledgePoint> newCourseResourceKnowledgePointList = new ArrayList<>();
        for (CourseResourceKnowledgePoint courseResourceKnowledgePoint : oldCourseResourceKnowledgePointList) {
            CourseKnowledgeGraphNode nowCourseKnowledgeGraphNode =
                    newCourseKnowledgeGraphNodeMap.get(courseResourceKnowledgePoint.getKnowledgePointId());
            CourseResourceKnowledgePoint newCourseKnowledgeGraphNodeVideoInfoText =
                    getCourseResourceKnowledgePoint(courseResourceKnowledgePoint,
                            newCourseKnowledgeGraphDomain, nowCourseKnowledgeGraphNode, accessUserId, accessUserName);
            newCourseResourceKnowledgePointList.add(newCourseKnowledgeGraphNodeVideoInfoText);
        }
        courseResourceKnowledgePointRepository.saveAllAndFlush(newCourseResourceKnowledgePointList);

    }

    private CourseResourceKnowledgePoint getCourseResourceKnowledgePoint(CourseResourceKnowledgePoint oldCourseResourceKnowledgePoint,
                                                                         CourseKnowledgeGraphDomain newCourseKnowledgeGraphDomain,
                                                                         CourseKnowledgeGraphNode nowCourseKnowledgeGraphNode,
                                                                         String accessUserId,
                                                                         String accessUserName) {
        CourseResourceKnowledgePoint newCourseResourceKnowledgePoint = new CourseResourceKnowledgePoint();
        newCourseResourceKnowledgePoint.setKnowledgePointId(nowCourseKnowledgeGraphNode.getId());
        newCourseResourceKnowledgePoint.setResourceId(oldCourseResourceKnowledgePoint.getResourceId());
        newCourseResourceKnowledgePoint.setCourseKnowledgeGraphDomainId(newCourseKnowledgeGraphDomain.getId());
        newCourseResourceKnowledgePoint.setModifierId(accessUserId);
        newCourseResourceKnowledgePoint.setModifierName(accessUserName);
        newCourseResourceKnowledgePoint.setModifyTime(new Date());
        newCourseResourceKnowledgePoint.setCreatorId(accessUserId);
        newCourseResourceKnowledgePoint.setCreatorName(accessUserName);
        newCourseResourceKnowledgePoint.setCreateTime(new Date());
        return newCourseResourceKnowledgePoint;
    }


    private ImportCourseKnowledgeGraphNodeResource getImportCourseKnowledgeGraphNodeResource(
            String[] titleArr, List<ImportCourseKnowledgeGraphNode> importCourseKnowledgeGraphNodeList,
            Row rows, CourseKnowledgeGraphNodeTreeModel courseKnowledgeGraphNodeTreeModel,
            ImportCourseKnowledgeGraphNodeParam param,
            ImportCourseKnowledgeGraphNodeResource importCourseKnowledgeGraphNodeResource) {
        for (int i = 0; i < titleArr.length; i++) {
            if (titleArr[i].equals("一级知识点")) {
                Result result = handleFirstLevel(rows, i, importCourseKnowledgeGraphNodeList,
                        courseKnowledgeGraphNodeTreeModel, param);
                if (ResultStatus.ERROR.equals(result.getStatus())) {
                    importCourseKnowledgeGraphNodeResource.setMessage(result.getMsg().toString());
                    return importCourseKnowledgeGraphNodeResource;
                }
            } else if (titleArr[i].equals("二级知识点")) {
                Result result = handleSecondLevel(rows, i, importCourseKnowledgeGraphNodeList, param);
                if (ResultStatus.ERROR.equals(result.getStatus())) {
                    importCourseKnowledgeGraphNodeResource.setMessage(result.getMsg().toString());
                    return importCourseKnowledgeGraphNodeResource;
                }
            } else if (titleArr[i].equals("三级知识点")) {
                Result result = handleThirdLevel(rows, i, importCourseKnowledgeGraphNodeList, param);
                if (ResultStatus.ERROR.equals(result.getStatus())) {
                    importCourseKnowledgeGraphNodeResource.setMessage(result.getMsg().toString());
                    return importCourseKnowledgeGraphNodeResource;
                }
            } else if (titleArr[i].equals("四级知识点")) {
                Result result = handleFourthLevel(rows, i, importCourseKnowledgeGraphNodeList, param);
                if (ResultStatus.ERROR.equals(result.getStatus())) {
                    importCourseKnowledgeGraphNodeResource.setMessage(result.getMsg().toString());
                    return importCourseKnowledgeGraphNodeResource;
                }
            } else if (titleArr[i].equals("五级知识点")) {
                Result result = handleFifthLevel(rows, i, importCourseKnowledgeGraphNodeList, param);
                if (ResultStatus.ERROR.equals(result.getStatus())) {
                    importCourseKnowledgeGraphNodeResource.setMessage(result.getMsg().toString());
                    return importCourseKnowledgeGraphNodeResource;
                }
            } else if (titleArr[i].equals("六级知识点")) {
                Result result = handleSixthLevel(rows, i, importCourseKnowledgeGraphNodeList, param);
                if (ResultStatus.ERROR.equals(result.getStatus())) {
                    importCourseKnowledgeGraphNodeResource.setMessage(result.getMsg().toString());
                    return importCourseKnowledgeGraphNodeResource;
                }
            } else if (titleArr[i].equals("七级知识点")) {
                Result result = handleSeventhLevel(rows, i, importCourseKnowledgeGraphNodeList, param);
                if (ResultStatus.ERROR.equals(result.getStatus())) {
                    importCourseKnowledgeGraphNodeResource.setMessage(result.getMsg().toString());
                    return importCourseKnowledgeGraphNodeResource;
                }
            } else if (titleArr[i].equals("八级知识点")) {
                Result result = handleEighthLevel(rows, i, importCourseKnowledgeGraphNodeList, param);
                if (ResultStatus.ERROR.equals(result.getStatus())) {
                    importCourseKnowledgeGraphNodeResource.setMessage(result.getMsg().toString());
                    return importCourseKnowledgeGraphNodeResource;
                }
            } else if (titleArr[i].equals("九级知识点")) {
                Result result = handleNinthLevel(rows, i, importCourseKnowledgeGraphNodeList, param);
                if (ResultStatus.ERROR.equals(result.getStatus())) {
                    importCourseKnowledgeGraphNodeResource.setMessage(result.getMsg().toString());
                    return importCourseKnowledgeGraphNodeResource;
                }
            } else if (titleArr[i].equals("十级知识点")) {
                Result result = handleTenthLevel(rows, i, importCourseKnowledgeGraphNodeList, param);
                if (ResultStatus.ERROR.equals(result.getStatus())) {
                    importCourseKnowledgeGraphNodeResource.setMessage(result.getMsg().toString());
                    return importCourseKnowledgeGraphNodeResource;
                }
            } else if (titleArr[i].equals("前置知识点")) {
                if (ObjectUtils.isNotEmpty(rows.getCell(i)) && StringUtils.isNotEmpty(rows.getCell(i).getStringCellValue().trim())) {
                    extractPreKnowledgeGraphNode(rows, i, param.getImportCourseKnowledgeGraphNode());
                }
            } else if (titleArr[i].equals("后置知识点")) {
                if (ObjectUtils.isNotEmpty(rows.getCell(i)) && StringUtils.isNotEmpty(rows.getCell(i).getStringCellValue().trim())) {
                    extractPostKnowledgeGraphNode(rows, i, param.getImportCourseKnowledgeGraphNode());
                }
            } else if (titleArr[i].equals("关联知识点")) {
                if (ObjectUtils.isNotEmpty(rows.getCell(i)) && StringUtils.isNotEmpty(rows.getCell(i).getStringCellValue().trim())) {
                    extractRelevanceKnowledgeGraphNode(rows, i, param.getImportCourseKnowledgeGraphNode());
                }
            } else if (titleArr[i].equals("重要层级")) {
                ImportCourseKnowledgeGraphNodeResource importCourseKnowledgeGraphNodeResource1 = getImportCourseKnowledgeGraphNodeResource(
                        rows, i, param, importCourseKnowledgeGraphNodeResource);
                if (importCourseKnowledgeGraphNodeResource1 != null) {
                    return importCourseKnowledgeGraphNodeResource1;
                }
            } else if (titleArr[i].equals("知识点内容详情")) {
                ImportCourseKnowledgeGraphNodeResource importCourseKnowledgeGraphNodeResource1 = getImportCourseKnowledgeGraphNodeResource(
                        rows, i, importCourseKnowledgeGraphNodeResource, param);
                if (importCourseKnowledgeGraphNodeResource1 != null) {
                    return importCourseKnowledgeGraphNodeResource1;
                }
            }
        }
        return null;
    }

    private static ImportCourseKnowledgeGraphNodeResource getImportCourseKnowledgeGraphNodeResource(
            Row rows, int i, ImportCourseKnowledgeGraphNodeResource importCourseKnowledgeGraphNodeResource,
            ImportCourseKnowledgeGraphNodeParam param) {
        if (ObjectUtils.isNotEmpty(rows.getCell(i)) && StringUtils.isNotEmpty(rows.getCell(i).getStringCellValue().trim())) {
            String cellValue = rows.getCell(i).getStringCellValue().trim();
            if (cellValue.length() > SIX_HUNDRED_WORDS) {
                importCourseKnowledgeGraphNodeResource.setMessage("error-第" + (rows.getRowNum() + 1) + "行知识点内容超过600个字");
                return importCourseKnowledgeGraphNodeResource;
            }
            param.getImportCourseKnowledgeGraphNode().setContentDetail(cellValue);
            if (!param.getImportCourseKnowledgeGraphNode().getIsSave()) {
                param.getImportCourseKnowledgeGraphNode().setIsUpdate(true);
            }
        }
        return null;
    }

    private static ImportCourseKnowledgeGraphNodeResource getImportCourseKnowledgeGraphNodeResource(
            Row rows, int i, ImportCourseKnowledgeGraphNodeParam importCourseKnowledgeGraphNodeParam,
            ImportCourseKnowledgeGraphNodeResource importCourseKnowledgeGraphNodeResource) {
        if (ObjectUtils.isNotEmpty(rows.getCell(i)) && StringUtils.isNotEmpty(rows.getCell(i).getStringCellValue().trim())) {
            ImportCourseKnowledgeGraphNodeResource importCourseKnowledgeGraphNodeResource1 =
                    getImportCourseKnowledgeGraphNodeResource(rows, i, importCourseKnowledgeGraphNodeParam.getImportCourseKnowledgeGraphNode(),
                            importCourseKnowledgeGraphNodeResource);
            if (importCourseKnowledgeGraphNodeResource1 != null) {
                return importCourseKnowledgeGraphNodeResource1;
            }
            if (!importCourseKnowledgeGraphNodeParam.getImportCourseKnowledgeGraphNode().getIsSave()) {
                importCourseKnowledgeGraphNodeParam.getImportCourseKnowledgeGraphNode().setIsUpdate(true);
            }
        }
        return null;
    }

    private Result handleFirstLevel(Row rows, Integer i,
                                    List<ImportCourseKnowledgeGraphNode> importCourseKnowledgeGraphNodeList,
                                    CourseKnowledgeGraphNodeTreeModel courseKnowledgeGraphNodeTreeModel,
                                    ImportCourseKnowledgeGraphNodeParam param) {
        if (ObjectUtils.isNotEmpty(rows.getCell(i)) && StringUtils.isNotEmpty(rows.getCell(i).getStringCellValue().trim())) {
            CourseKnowledgeGraphNodeTreeModel firstLevelNode;
            if (param.getLastLevel() < ONE_LEVEL && (ONE_LEVEL - param.getLastLevel()) > 1) {
                return Result.error("error-第" + (rows.getRowNum() + 1) + "行知识点未有上级知识点");
            }
            param.setLastLevel(ONE_LEVEL);
            String cellValue = rows.getCell(i).getStringCellValue().trim();
            if (cellValue.length() > ONE_HUNDRED) {
                return Result.error("error-第" + (rows.getRowNum() + 1) + "行知识点名称超过100个字");
            }
            ImportCourseKnowledgeGraphNode knowledgeGraphNode = importCourseKnowledgeGraphNodeList.stream().filter(node ->
                    node.getParentCourseKnowledgeGraphNode().equals(courseKnowledgeGraphNodeTreeModel.getId())
                            && node.getKnowledgeNodeName().equals(cellValue)).findFirst().orElse(null);
            if (ObjectUtils.isNotEmpty(knowledgeGraphNode)) {
                return Result.error("error-第" + (rows.getRowNum() + 1) + "行知识点与第" + knowledgeGraphNode.getRow() + "行知识点名称重复");

            }
            CourseKnowledgeGraphNodeTreeModel courseKnowledgeGraphNodeTreeModel1 = courseKnowledgeGraphNodeTreeModel
                    .getSubsetCourseKnowledgeGraphNodeTreeList().stream().filter(model ->
                            model.getKnowledgeNodeName().equals(cellValue)).findFirst().orElse(null);
            if (ObjectUtils.isEmpty(courseKnowledgeGraphNodeTreeModel1)) {
                firstLevelNode = new CourseKnowledgeGraphNodeTreeModel();
                param.setImportCourseKnowledgeGraphNode(getImportCourseKnowledgeGraphNode(
                        param.getCourseKnowledgeGraphDomain(),
                        courseKnowledgeGraphNodeTreeModel, param.getUserId(),
                        param.getUserName(), ONE_LEVEL, cellValue));
                param.getImportCourseKnowledgeGraphNode().setIsSave(true);
                firstLevelNode.setId(param.getImportCourseKnowledgeGraphNode().getId());
            } else {
                firstLevelNode = courseKnowledgeGraphNodeTreeModel1;
                param.setOldModel(courseKnowledgeGraphNodeTreeModel1);
                param.setImportCourseKnowledgeGraphNode(getImportCourseKnowledgeGraphNode(
                        param.getImportCourseKnowledgeGraphNode(), courseKnowledgeGraphNodeTreeModel1,
                        courseKnowledgeGraphNodeTreeModel));
            }
            param.setFirstLevelNode(firstLevelNode);
        }
        return Result.success();
    }

    private Result handleSecondLevel(Row rows, Integer i,
                                     List<ImportCourseKnowledgeGraphNode> importCourseKnowledgeGraphNodeList,
                                     ImportCourseKnowledgeGraphNodeParam param) {

        if (ObjectUtils.isNotEmpty(rows.getCell(i)) && StringUtils.isNotEmpty(rows.getCell(i).getStringCellValue().trim())) {
            CourseKnowledgeGraphNodeTreeModel secondLevelNode;

            if (param.getLastLevel() < TWO_LEVEL && (TWO_LEVEL - param.getLastLevel()) > 1) {
                return Result.error("error-第" + (rows.getRowNum() + 1) + "行知识点未有上级知识点");
            }
            param.setLastLevel(TWO_LEVEL);
            String cellValue = rows.getCell(i).getStringCellValue().trim();
            if (cellValue.length() > ONE_HUNDRED) {
                return Result.error("error-第" + (rows.getRowNum() + 1) + "行知识点名称超过100个字");
            }
            CourseKnowledgeGraphNodeTreeModel finalFirstLevelNode = param.getFirstLevelNode();
            ImportCourseKnowledgeGraphNode knowledgeGraphNode = importCourseKnowledgeGraphNodeList.stream().filter(node ->
                    node.getParentCourseKnowledgeGraphNode().equals(finalFirstLevelNode.getId())
                            && node.getKnowledgeNodeName().equals(cellValue)).findFirst().orElse(null);
            if (ObjectUtils.isNotEmpty(knowledgeGraphNode)) {
                return Result.error("error-第" + (rows.getRowNum() + 1) + "行知识点与第" + knowledgeGraphNode.getRow() + "行知识点名称重复");
            }
            if (CollectionUtils.isEmpty(param.getFirstLevelNode().getSubsetCourseKnowledgeGraphNodeTreeList())) {
                secondLevelNode = new CourseKnowledgeGraphNodeTreeModel();
                param.setImportCourseKnowledgeGraphNode(getImportCourseKnowledgeGraphNode(
                        param.getCourseKnowledgeGraphDomain(),
                        param.getFirstLevelNode(), param.getUserId(),
                        param.getUserName(), TWO_LEVEL, cellValue));
                param.getImportCourseKnowledgeGraphNode().setIsSave(true);
                secondLevelNode.setId(param.getImportCourseKnowledgeGraphNode().getId());
            } else {
                CourseKnowledgeGraphNodeTreeModel courseKnowledgeGraphNodeTreeModel1 = param.getFirstLevelNode()
                        .getSubsetCourseKnowledgeGraphNodeTreeList().stream().filter(model ->
                                model.getKnowledgeNodeName().equals(cellValue)).findFirst().orElse(null);
                if (ObjectUtils.isEmpty(courseKnowledgeGraphNodeTreeModel1)) {
                    secondLevelNode = new CourseKnowledgeGraphNodeTreeModel();
                    param.setImportCourseKnowledgeGraphNode(getImportCourseKnowledgeGraphNode(
                            param.getCourseKnowledgeGraphDomain(), param.getFirstLevelNode(),
                            param.getUserId(), param.getUserName(), TWO_LEVEL, cellValue));
                    param.getImportCourseKnowledgeGraphNode().setIsSave(true);
                    secondLevelNode.setId(param.getImportCourseKnowledgeGraphNode().getId());
                } else {
                    param.setImportCourseKnowledgeGraphNode(
                            getImportCourseKnowledgeGraphNode(param.getImportCourseKnowledgeGraphNode(),
                                    courseKnowledgeGraphNodeTreeModel1, param.getFirstLevelNode()));
                    secondLevelNode = courseKnowledgeGraphNodeTreeModel1;
                    param.setOldModel(courseKnowledgeGraphNodeTreeModel1);
                }
            }
            param.setSecondLevelNode(secondLevelNode);
        }
        return Result.success();
    }

    private Result handleThirdLevel(Row rows, Integer i,
                                    List<ImportCourseKnowledgeGraphNode> importCourseKnowledgeGraphNodeList,
                                    ImportCourseKnowledgeGraphNodeParam param) {
        if (ObjectUtils.isNotEmpty(rows.getCell(i)) && StringUtils.isNotEmpty(rows.getCell(i).getStringCellValue().trim())) {
            CourseKnowledgeGraphNodeTreeModel thirdLevelNode;

            if (param.getLastLevel() < THREE_LEVEL && (THREE_LEVEL - param.getLastLevel()) > 1) {
                return Result.error("error-第" + (rows.getRowNum() + 1) + "行知识点未有上级知识点");
            }
            param.setLastLevel(THREE_LEVEL);
            String cellValue = rows.getCell(i).getStringCellValue().trim();
            if (cellValue.length() > ONE_HUNDRED) {
                return Result.error("error-第" + (rows.getRowNum() + 1) + "行知识点名称超过100个字");
            }
            CourseKnowledgeGraphNodeTreeModel finalSecondLevelNode = param.getSecondLevelNode();
            ImportCourseKnowledgeGraphNode knowledgeGraphNode = importCourseKnowledgeGraphNodeList.stream().filter(node ->
                    node.getParentCourseKnowledgeGraphNode().equals(finalSecondLevelNode.getId())
                            && node.getKnowledgeNodeName().equals(cellValue)).findFirst().orElse(null);
            if (ObjectUtils.isNotEmpty(knowledgeGraphNode)) {
                return Result.error("error-第" + (rows.getRowNum() + 1) + "行知识点与第" + knowledgeGraphNode.getRow() + "行知识点名称重复");
            }
            if (CollectionUtils.isEmpty(param.getSecondLevelNode().getSubsetCourseKnowledgeGraphNodeTreeList())) {
                thirdLevelNode = new CourseKnowledgeGraphNodeTreeModel();
                param.setImportCourseKnowledgeGraphNode(getImportCourseKnowledgeGraphNode(
                        param.getCourseKnowledgeGraphDomain(), param.getSecondLevelNode(),
                        param.getUserId(), param.getUserName(), THREE_LEVEL, cellValue));
                param.getImportCourseKnowledgeGraphNode().setIsSave(true);
                thirdLevelNode.setId(param.getImportCourseKnowledgeGraphNode().getId());
            } else {
                CourseKnowledgeGraphNodeTreeModel courseKnowledgeGraphNodeTreeModel1 = param.getSecondLevelNode()
                        .getSubsetCourseKnowledgeGraphNodeTreeList().stream().filter(model ->
                                model.getKnowledgeNodeName().equals(cellValue)).findFirst().orElse(null);
                if (ObjectUtils.isEmpty(courseKnowledgeGraphNodeTreeModel1)) {
                    thirdLevelNode = new CourseKnowledgeGraphNodeTreeModel();
                    param.setImportCourseKnowledgeGraphNode(getImportCourseKnowledgeGraphNode(
                            param.getCourseKnowledgeGraphDomain(), param.getSecondLevelNode(),
                            param.getUserId(), param.getUserName(), THREE_LEVEL, cellValue));
                    param.getImportCourseKnowledgeGraphNode().setIsSave(true);
                    thirdLevelNode.setId(param.getImportCourseKnowledgeGraphNode().getId());
                } else {
                    param.setImportCourseKnowledgeGraphNode(
                            getImportCourseKnowledgeGraphNode(param.getImportCourseKnowledgeGraphNode(),
                                    courseKnowledgeGraphNodeTreeModel1, param.getSecondLevelNode()));
                    thirdLevelNode = courseKnowledgeGraphNodeTreeModel1;
                    param.setOldModel(courseKnowledgeGraphNodeTreeModel1);
                }
            }
            param.setThirdLevelNode(thirdLevelNode);

        }
        return Result.success();
    }

    private Result handleFourthLevel(Row rows, Integer i,
                                     List<ImportCourseKnowledgeGraphNode> importCourseKnowledgeGraphNodeList,
                                     ImportCourseKnowledgeGraphNodeParam param) {
        if (ObjectUtils.isNotEmpty(rows.getCell(i)) && StringUtils.isNotEmpty(rows.getCell(i).getStringCellValue().trim())) {
            CourseKnowledgeGraphNodeTreeModel fourthLevelNode;

            if (param.getLastLevel() < FOUR_LEVEL && (FOUR_LEVEL - param.getLastLevel()) > 1) {
                return Result.error("error-第" + (rows.getRowNum() + 1) + "行知识点未有上级知识点");

            }
            param.setLastLevel(FOUR_LEVEL);
            String cellValue = rows.getCell(i).getStringCellValue().trim();
            if (cellValue.length() > ONE_HUNDRED) {
                return Result.error("error-第" + (rows.getRowNum() + 1) + "行知识点名称超过100个字");

            }
            CourseKnowledgeGraphNodeTreeModel finalThirdLevelNode = param.getThirdLevelNode();
            ImportCourseKnowledgeGraphNode knowledgeGraphNode = importCourseKnowledgeGraphNodeList.stream().filter(node ->
                    node.getParentCourseKnowledgeGraphNode().equals(finalThirdLevelNode.getId())
                            && node.getKnowledgeNodeName().equals(cellValue)).findFirst().orElse(null);
            if (ObjectUtils.isNotEmpty(knowledgeGraphNode)) {
                return Result.error("error-第" + (rows.getRowNum() + 1) + "行知识点与第" + knowledgeGraphNode.getRow() + "行知识点名称重复");
            }
            if (CollectionUtils.isEmpty(param.getThirdLevelNode().getSubsetCourseKnowledgeGraphNodeTreeList())) {
                fourthLevelNode = new CourseKnowledgeGraphNodeTreeModel();
                param.setImportCourseKnowledgeGraphNode(getImportCourseKnowledgeGraphNode(
                        param.getCourseKnowledgeGraphDomain(), param.getThirdLevelNode(),
                        param.getUserId(), param.getUserName(), FOUR_LEVEL, cellValue));
                param.getImportCourseKnowledgeGraphNode().setIsSave(true);
                fourthLevelNode.setId(param.getImportCourseKnowledgeGraphNode().getId());
            } else {
                CourseKnowledgeGraphNodeTreeModel courseKnowledgeGraphNodeTreeModel1 = param.getThirdLevelNode()
                        .getSubsetCourseKnowledgeGraphNodeTreeList().stream().filter(model ->
                                model.getKnowledgeNodeName().equals(cellValue)).findFirst().orElse(null);
                if (ObjectUtils.isEmpty(courseKnowledgeGraphNodeTreeModel1)) {
                    fourthLevelNode = new CourseKnowledgeGraphNodeTreeModel();
                    param.setImportCourseKnowledgeGraphNode(getImportCourseKnowledgeGraphNode(
                            param.getCourseKnowledgeGraphDomain(), param.getThirdLevelNode(),
                            param.getUserId(), param.getUserName(), FOUR_LEVEL, cellValue));
                    param.getImportCourseKnowledgeGraphNode().setIsSave(true);
                    fourthLevelNode.setId(param.getImportCourseKnowledgeGraphNode().getId());
                } else {
                    param.setImportCourseKnowledgeGraphNode(
                            getImportCourseKnowledgeGraphNode(param.getImportCourseKnowledgeGraphNode(),
                                    courseKnowledgeGraphNodeTreeModel1, param.getThirdLevelNode()));
                    fourthLevelNode = courseKnowledgeGraphNodeTreeModel1;
                    param.setOldModel(courseKnowledgeGraphNodeTreeModel1);
                }
            }
            param.setFourthLevelNode(fourthLevelNode);
        }
        return Result.success();
    }

    private Result handleFifthLevel(Row rows, Integer i,
                                    List<ImportCourseKnowledgeGraphNode> importCourseKnowledgeGraphNodeList,
                                    ImportCourseKnowledgeGraphNodeParam param) {

        if (ObjectUtils.isNotEmpty(rows.getCell(i)) && StringUtils.isNotEmpty(rows.getCell(i).getStringCellValue().trim())) {
            CourseKnowledgeGraphNodeTreeModel fifthLevelNode;
            CourseKnowledgeGraphNodeTreeModel fourthLevelNode = param.getFourthLevelNode();
            if (param.getLastLevel() < FIVE_LEVEL && (FIVE_LEVEL - param.getLastLevel()) > 1) {
                return Result.error("error-第" + (rows.getRowNum() + 1) + "行知识点未有上级知识点");
            }
            param.setLastLevel(FIVE_LEVEL);
            String cellValue = rows.getCell(i).getStringCellValue().trim();
            if (cellValue.length() > ONE_HUNDRED) {
                return Result.error("error-第" + (rows.getRowNum() + 1) + "行知识点名称超过100个字");
            }
            CourseKnowledgeGraphNodeTreeModel finalFourthLevelNode = fourthLevelNode;
            ImportCourseKnowledgeGraphNode knowledgeGraphNode = importCourseKnowledgeGraphNodeList.stream().filter(node ->
                    node.getParentCourseKnowledgeGraphNode().equals(finalFourthLevelNode.getId())
                            && node.getKnowledgeNodeName().equals(cellValue)).findFirst().orElse(null);
            if (ObjectUtils.isNotEmpty(knowledgeGraphNode)) {
                return Result.error("error-第" + (rows.getRowNum() + 1) + "行知识点与第" + knowledgeGraphNode.getRow() + "行知识点名称重复");
            }
            if (CollectionUtils.isEmpty(param.getFourthLevelNode().getSubsetCourseKnowledgeGraphNodeTreeList())) {
                fifthLevelNode = new CourseKnowledgeGraphNodeTreeModel();
                param.setImportCourseKnowledgeGraphNode(getImportCourseKnowledgeGraphNode(
                        param.getCourseKnowledgeGraphDomain(), fourthLevelNode,
                        param.getUserId(), param.getUserName(), FIVE_LEVEL, cellValue));
                param.getImportCourseKnowledgeGraphNode().setIsSave(true);
                fifthLevelNode.setId(param.getImportCourseKnowledgeGraphNode().getId());
            } else {
                CourseKnowledgeGraphNodeTreeModel courseKnowledgeGraphNodeTreeModel1 = fourthLevelNode
                        .getSubsetCourseKnowledgeGraphNodeTreeList().stream().filter(model ->
                                model.getKnowledgeNodeName().equals(cellValue)).findFirst().orElse(null);
                if (ObjectUtils.isEmpty(courseKnowledgeGraphNodeTreeModel1)) {
                    fifthLevelNode = new CourseKnowledgeGraphNodeTreeModel();
                    param.setImportCourseKnowledgeGraphNode(getImportCourseKnowledgeGraphNode(
                            param.getCourseKnowledgeGraphDomain(), fourthLevelNode,
                            param.getUserId(), param.getUserName(), FIVE_LEVEL, cellValue));
                    param.getImportCourseKnowledgeGraphNode().setIsSave(true);
                    fifthLevelNode.setId(param.getImportCourseKnowledgeGraphNode().getId());
                } else {
                    param.setImportCourseKnowledgeGraphNode(getImportCourseKnowledgeGraphNode(
                            param.getImportCourseKnowledgeGraphNode(), courseKnowledgeGraphNodeTreeModel1, fourthLevelNode));
                    fifthLevelNode = courseKnowledgeGraphNodeTreeModel1;
                    param.setOldModel(courseKnowledgeGraphNodeTreeModel1);
                }
            }
            param.setFifthLevelNode(fifthLevelNode);
        }
        return Result.success();

    }

    private Result handleSixthLevel(Row rows, Integer i,
                                    List<ImportCourseKnowledgeGraphNode> importCourseKnowledgeGraphNodeList,
                                    ImportCourseKnowledgeGraphNodeParam param) {

        if (ObjectUtils.isNotEmpty(rows.getCell(i)) && StringUtils.isNotEmpty(rows.getCell(i).getStringCellValue().trim())) {
            CourseKnowledgeGraphNodeTreeModel sixthLevelNode;
            CourseKnowledgeGraphNodeTreeModel fifthLevelNode = param.getFifthLevelNode();
            if (param.getLastLevel() < SIX_LEVEL && (SIX_LEVEL - param.getLastLevel()) > 1) {
                return Result.error("error-第" + (rows.getRowNum() + 1) + "行知识点未有上级知识点");
            }
            param.setLastLevel(SIX_LEVEL);
            String cellValue = rows.getCell(i).getStringCellValue().trim();
            if (cellValue.length() > ONE_HUNDRED) {
                return Result.error("error-第" + (rows.getRowNum() + 1) + "行知识点名称超过100个字");
            }
            CourseKnowledgeGraphNodeTreeModel finalFifthLevelNode = fifthLevelNode;
            ImportCourseKnowledgeGraphNode knowledgeGraphNode = importCourseKnowledgeGraphNodeList.stream().filter(node ->
                    node.getParentCourseKnowledgeGraphNode().equals(finalFifthLevelNode.getId())
                            && node.getKnowledgeNodeName().equals(cellValue)).findFirst().orElse(null);
            if (ObjectUtils.isNotEmpty(knowledgeGraphNode)) {
                return Result.error("error-第" + (rows.getRowNum() + 1) + "行知识点与第" + knowledgeGraphNode.getRow() + "行知识点名称重复");
            }
            if (CollectionUtils.isEmpty(fifthLevelNode.getSubsetCourseKnowledgeGraphNodeTreeList())) {
                sixthLevelNode = new CourseKnowledgeGraphNodeTreeModel();
                param.setImportCourseKnowledgeGraphNode(getImportCourseKnowledgeGraphNode(
                        param.getCourseKnowledgeGraphDomain(), fifthLevelNode,
                        param.getUserId(), param.getUserName(), SIX_LEVEL, cellValue));
                param.getImportCourseKnowledgeGraphNode().setIsSave(true);
                sixthLevelNode.setId(param.getImportCourseKnowledgeGraphNode().getId());
            } else {
                CourseKnowledgeGraphNodeTreeModel courseKnowledgeGraphNodeTreeModel1 = fifthLevelNode
                        .getSubsetCourseKnowledgeGraphNodeTreeList().stream().filter(model ->
                                model.getKnowledgeNodeName().equals(cellValue)).findFirst().orElse(null);
                if (ObjectUtils.isEmpty(courseKnowledgeGraphNodeTreeModel1)) {
                    sixthLevelNode = new CourseKnowledgeGraphNodeTreeModel();
                    param.setImportCourseKnowledgeGraphNode(getImportCourseKnowledgeGraphNode(
                            param.getCourseKnowledgeGraphDomain(), fifthLevelNode,
                            param.getUserId(), param.getUserName(), SIX_LEVEL, cellValue));
                    param.getImportCourseKnowledgeGraphNode().setIsSave(true);
                    sixthLevelNode.setId(param.getImportCourseKnowledgeGraphNode().getId());
                } else {
                    param.setImportCourseKnowledgeGraphNode(getImportCourseKnowledgeGraphNode(
                            param.getImportCourseKnowledgeGraphNode(), courseKnowledgeGraphNodeTreeModel1, fifthLevelNode));
                    sixthLevelNode = courseKnowledgeGraphNodeTreeModel1;
                    param.setOldModel(courseKnowledgeGraphNodeTreeModel1);
                }
            }
            param.setSixthLevelNode(sixthLevelNode);
        }
        return Result.success();
    }

    private Result handleSeventhLevel(Row rows, Integer i,
                                      List<ImportCourseKnowledgeGraphNode> importCourseKnowledgeGraphNodeList,
                                      ImportCourseKnowledgeGraphNodeParam param) {

        if (ObjectUtils.isNotEmpty(rows.getCell(i)) && StringUtils.isNotEmpty(rows.getCell(i).getStringCellValue().trim())) {
            CourseKnowledgeGraphNodeTreeModel seventhLevelNode;
            CourseKnowledgeGraphNodeTreeModel sixthLevelNode = param.getSixthLevelNode();
            if (param.getLastLevel() < SEVEN_LEVEL && (SEVEN_LEVEL - param.getLastLevel()) > 1) {
                return Result.error("error-第" + (rows.getRowNum() + 1) + "行知识点未有上级知识点");
            }
            param.setLastLevel(SEVEN_LEVEL);
            String cellValue = rows.getCell(i).getStringCellValue().trim();
            if (cellValue.length() > ONE_HUNDRED) {
                return Result.error("error-第" + (rows.getRowNum() + 1) + "行知识点名称超过100个字");
            }
            CourseKnowledgeGraphNodeTreeModel finalSixthLevelNode = sixthLevelNode;
            ImportCourseKnowledgeGraphNode knowledgeGraphNode = importCourseKnowledgeGraphNodeList.stream().filter(node ->
                    node.getParentCourseKnowledgeGraphNode().equals(finalSixthLevelNode.getId())
                            && node.getKnowledgeNodeName().equals(cellValue)).findFirst().orElse(null);
            if (ObjectUtils.isNotEmpty(knowledgeGraphNode)) {
                return Result.error("error-第" + (rows.getRowNum() + 1) + "行知识点与第" + knowledgeGraphNode.getRow() + "行知识点名称重复");
            }
            if (CollectionUtils.isEmpty(sixthLevelNode.getSubsetCourseKnowledgeGraphNodeTreeList())) {
                seventhLevelNode = new CourseKnowledgeGraphNodeTreeModel();
                param.setImportCourseKnowledgeGraphNode(getImportCourseKnowledgeGraphNode(
                        param.getCourseKnowledgeGraphDomain(), sixthLevelNode,
                        param.getUserId(), param.getUserName(), SEVEN_LEVEL, cellValue));
                param.getImportCourseKnowledgeGraphNode().setIsSave(true);
                seventhLevelNode.setId(param.getImportCourseKnowledgeGraphNode().getId());
            } else {
                CourseKnowledgeGraphNodeTreeModel courseKnowledgeGraphNodeTreeModel1 = sixthLevelNode
                        .getSubsetCourseKnowledgeGraphNodeTreeList().stream().filter(model ->
                                model.getKnowledgeNodeName().equals(cellValue)).findFirst().orElse(null);
                if (ObjectUtils.isEmpty(courseKnowledgeGraphNodeTreeModel1)) {
                    seventhLevelNode = new CourseKnowledgeGraphNodeTreeModel();
                    param.setImportCourseKnowledgeGraphNode(getImportCourseKnowledgeGraphNode(
                            param.getCourseKnowledgeGraphDomain(), sixthLevelNode,
                            param.getUserId(), param.getUserName(), SEVEN_LEVEL, cellValue));
                    param.getImportCourseKnowledgeGraphNode().setIsSave(true);
                    seventhLevelNode.setId(param.getImportCourseKnowledgeGraphNode().getId());
                } else {
                    param.setImportCourseKnowledgeGraphNode(getImportCourseKnowledgeGraphNode(
                            param.getImportCourseKnowledgeGraphNode(), courseKnowledgeGraphNodeTreeModel1, sixthLevelNode));
                    seventhLevelNode = courseKnowledgeGraphNodeTreeModel1;
                    param.setOldModel(courseKnowledgeGraphNodeTreeModel1);
                }
            }
            param.setSeventhLevelNode(seventhLevelNode);
        }
        return Result.success();
    }

    private Result handleEighthLevel(Row rows, Integer i,
                                     List<ImportCourseKnowledgeGraphNode> importCourseKnowledgeGraphNodeList,
                                     ImportCourseKnowledgeGraphNodeParam param) {

        if (ObjectUtils.isNotEmpty(rows.getCell(i)) && StringUtils.isNotEmpty(rows.getCell(i).getStringCellValue().trim())) {
            CourseKnowledgeGraphNodeTreeModel eighthLevelNode;
            CourseKnowledgeGraphNodeTreeModel seventhLevelNode = param.getSeventhLevelNode();
            if (param.getLastLevel() < EIGHT_LEVEL && (EIGHT_LEVEL - param.getLastLevel()) > 1) {
                return Result.error("error-第" + (rows.getRowNum() + 1) + "行知识点未有上级知识点");
            }
            param.setLastLevel(EIGHT_LEVEL);
            String cellValue = rows.getCell(i).getStringCellValue().trim();
            if (cellValue.length() > ONE_HUNDRED) {
                return Result.error("error-第" + (rows.getRowNum() + 1) + "行知识点名称超过100个字");
            }
            CourseKnowledgeGraphNodeTreeModel finalSeventhLevelNode = seventhLevelNode;
            ImportCourseKnowledgeGraphNode knowledgeGraphNode = importCourseKnowledgeGraphNodeList.stream().filter(node ->
                    node.getParentCourseKnowledgeGraphNode().equals(finalSeventhLevelNode.getId())
                            && node.getKnowledgeNodeName().equals(cellValue)).findFirst().orElse(null);
            if (ObjectUtils.isNotEmpty(knowledgeGraphNode)) {
                return Result.error("error-第" + (rows.getRowNum() + 1) + "行知识点与第" + knowledgeGraphNode.getRow() + "行知识点名称重复");
            }
            if (CollectionUtils.isEmpty(seventhLevelNode.getSubsetCourseKnowledgeGraphNodeTreeList())) {
                eighthLevelNode = new CourseKnowledgeGraphNodeTreeModel();
                param.setImportCourseKnowledgeGraphNode(getImportCourseKnowledgeGraphNode(
                        param.getCourseKnowledgeGraphDomain(), seventhLevelNode,
                        param.getUserId(), param.getUserName(), EIGHT_LEVEL, cellValue));
                param.getImportCourseKnowledgeGraphNode().setIsSave(true);
                eighthLevelNode.setId(param.getImportCourseKnowledgeGraphNode().getId());
            } else {
                CourseKnowledgeGraphNodeTreeModel courseKnowledgeGraphNodeTreeModel1 = seventhLevelNode
                        .getSubsetCourseKnowledgeGraphNodeTreeList().stream().filter(model ->
                                model.getKnowledgeNodeName().equals(cellValue)).findFirst().orElse(null);
                if (ObjectUtils.isEmpty(courseKnowledgeGraphNodeTreeModel1)) {
                    eighthLevelNode = new CourseKnowledgeGraphNodeTreeModel();
                    param.setImportCourseKnowledgeGraphNode(getImportCourseKnowledgeGraphNode(
                            param.getCourseKnowledgeGraphDomain(), seventhLevelNode,
                            param.getUserId(), param.getUserName(), EIGHT_LEVEL, cellValue));
                    param.getImportCourseKnowledgeGraphNode().setIsSave(true);
                    eighthLevelNode.setId(param.getImportCourseKnowledgeGraphNode().getId());
                } else {
                    param.setImportCourseKnowledgeGraphNode(getImportCourseKnowledgeGraphNode(
                            param.getImportCourseKnowledgeGraphNode(), courseKnowledgeGraphNodeTreeModel1, seventhLevelNode));
                    eighthLevelNode = courseKnowledgeGraphNodeTreeModel1;
                    param.setOldModel(courseKnowledgeGraphNodeTreeModel1);
                }
            }
            param.setEighthLevelNode(eighthLevelNode);
        }
        return Result.success();
    }

    private Result handleNinthLevel(Row rows, Integer i,
                                    List<ImportCourseKnowledgeGraphNode> importCourseKnowledgeGraphNodeList,
                                    ImportCourseKnowledgeGraphNodeParam param) {

        if (ObjectUtils.isNotEmpty(rows.getCell(i)) && StringUtils.isNotEmpty(rows.getCell(i).getStringCellValue().trim())) {
            CourseKnowledgeGraphNodeTreeModel ninthLevelNode;
            CourseKnowledgeGraphNodeTreeModel eightthLevelNode = param.getEighthLevelNode();
            if (param.getLastLevel() < NINE_LEVEL && (NINE_LEVEL - param.getLastLevel()) > 1) {
                return Result.error("error-第" + (rows.getRowNum() + 1) + "行知识点未有上级知识点");
            }
            param.setLastLevel(NINE_LEVEL);
            String cellValue = rows.getCell(i).getStringCellValue().trim();
            if (cellValue.length() > ONE_HUNDRED) {
                return Result.error("error-第" + (rows.getRowNum() + 1) + "行知识点名称超过100个字");
            }
            CourseKnowledgeGraphNodeTreeModel finalEighthLevelNode = eightthLevelNode;
            ImportCourseKnowledgeGraphNode knowledgeGraphNode = importCourseKnowledgeGraphNodeList.stream().filter(node ->
                    node.getParentCourseKnowledgeGraphNode().equals(finalEighthLevelNode.getId())
                            && node.getKnowledgeNodeName().equals(cellValue)).findFirst().orElse(null);
            if (ObjectUtils.isNotEmpty(knowledgeGraphNode)) {
                return Result.error("error-第" + (rows.getRowNum() + 1) + "行知识点与第" + knowledgeGraphNode.getRow() + "行知识点名称重复");
            }
            if (CollectionUtils.isEmpty(eightthLevelNode.getSubsetCourseKnowledgeGraphNodeTreeList())) {
                ninthLevelNode = new CourseKnowledgeGraphNodeTreeModel();
                param.setImportCourseKnowledgeGraphNode(getImportCourseKnowledgeGraphNode(
                        param.getCourseKnowledgeGraphDomain(), eightthLevelNode,
                        param.getUserId(), param.getUserName(), EIGHT_LEVEL, cellValue));
                param.getImportCourseKnowledgeGraphNode().setIsSave(true);
                ninthLevelNode.setId(param.getImportCourseKnowledgeGraphNode().getId());
            } else {
                CourseKnowledgeGraphNodeTreeModel courseKnowledgeGraphNodeTreeModel1 = eightthLevelNode
                        .getSubsetCourseKnowledgeGraphNodeTreeList().stream().filter(model ->
                                model.getKnowledgeNodeName().equals(cellValue)).findFirst().orElse(null);
                if (ObjectUtils.isEmpty(courseKnowledgeGraphNodeTreeModel1)) {
                    ninthLevelNode = new CourseKnowledgeGraphNodeTreeModel();
                    param.setImportCourseKnowledgeGraphNode(getImportCourseKnowledgeGraphNode(
                            param.getCourseKnowledgeGraphDomain(), eightthLevelNode,
                            param.getUserId(), param.getUserName(), EIGHT_LEVEL, cellValue));
                    param.getImportCourseKnowledgeGraphNode().setIsSave(true);
                    ninthLevelNode.setId(param.getImportCourseKnowledgeGraphNode().getId());
                } else {
                    param.setImportCourseKnowledgeGraphNode(getImportCourseKnowledgeGraphNode(
                            param.getImportCourseKnowledgeGraphNode(), courseKnowledgeGraphNodeTreeModel1, eightthLevelNode));
                    ninthLevelNode = courseKnowledgeGraphNodeTreeModel1;
                    param.setOldModel(courseKnowledgeGraphNodeTreeModel1);
                }
            }
            param.setNinthLevelNode(ninthLevelNode);
        }
        return Result.success();
    }


    private Result handleTenthLevel(Row rows, Integer i,
                                    List<ImportCourseKnowledgeGraphNode> importCourseKnowledgeGraphNodeList,
                                    ImportCourseKnowledgeGraphNodeParam param) {

        if (ObjectUtils.isNotEmpty(rows.getCell(i)) && StringUtils.isNotEmpty(rows.getCell(i).getStringCellValue().trim())) {
            CourseKnowledgeGraphNodeTreeModel ninthLevelNode = param.getNinthLevelNode();
            if (param.getLastLevel() < TEN_LEVEL && (TEN_LEVEL - param.getLastLevel()) > 1) {
                return Result.error("error-第" + (rows.getRowNum() + 1) + "行知识点未有上级知识点");
            }
            param.setLastLevel(TEN_LEVEL);
            String cellValue = rows.getCell(i).getStringCellValue().trim();
            if (cellValue.length() > ONE_HUNDRED) {
                return Result.error("error-第" + (rows.getRowNum() + 1) + "行知识点名称超过100个字");
            }
            CourseKnowledgeGraphNodeTreeModel finalNinthLevelNode = ninthLevelNode;
            ImportCourseKnowledgeGraphNode knowledgeGraphNode = importCourseKnowledgeGraphNodeList.stream().filter(node ->
                    node.getParentCourseKnowledgeGraphNode().equals(finalNinthLevelNode.getId())
                            && node.getKnowledgeNodeName().equals(cellValue)).findFirst().orElse(null);
            if (ObjectUtils.isNotEmpty(knowledgeGraphNode)) {
                return Result.error("error-第" + (rows.getRowNum() + 1) + "行知识点与第" + knowledgeGraphNode.getRow() + "行知识点名称重复");
            }
            if (CollectionUtils.isEmpty(ninthLevelNode.getSubsetCourseKnowledgeGraphNodeTreeList())) {
                param.setImportCourseKnowledgeGraphNode(getImportCourseKnowledgeGraphNode(
                        param.getCourseKnowledgeGraphDomain(), ninthLevelNode, param.getUserId()
                        , param.getUserName(), TEN_LEVEL, cellValue));
                param.getImportCourseKnowledgeGraphNode().setIsSave(true);
            } else {
                CourseKnowledgeGraphNodeTreeModel courseKnowledgeGraphNodeTreeModel1 = ninthLevelNode
                        .getSubsetCourseKnowledgeGraphNodeTreeList().stream().filter(model ->
                                model.getKnowledgeNodeName().equals(cellValue)).findFirst().orElse(null);
                if (ObjectUtils.isEmpty(courseKnowledgeGraphNodeTreeModel1)) {
                    param.setImportCourseKnowledgeGraphNode(getImportCourseKnowledgeGraphNode(
                            param.getCourseKnowledgeGraphDomain(), ninthLevelNode,
                            param.getUserId(), param.getUserName(), TEN_LEVEL, cellValue));
                    param.getImportCourseKnowledgeGraphNode().setIsSave(true);
                } else {
                    param.setImportCourseKnowledgeGraphNode(getImportCourseKnowledgeGraphNode(
                            param.getImportCourseKnowledgeGraphNode(), courseKnowledgeGraphNodeTreeModel1, ninthLevelNode));
                    param.setOldModel(courseKnowledgeGraphNodeTreeModel1);
                }
            }
        }
        return Result.success();
    }


    private static ImportCourseKnowledgeGraphNode getImportCourseKnowledgeGraphNode(
            ImportCourseKnowledgeGraphNode importCourseKnowledgeGraphNode,
            CourseKnowledgeGraphNodeTreeModel courseKnowledgeGraphNodeTreeModel1, CourseKnowledgeGraphNodeTreeModel nodeTreeModel) {
        importCourseKnowledgeGraphNode.setId(courseKnowledgeGraphNodeTreeModel1.getId());
        importCourseKnowledgeGraphNode.setKnowledgeNodeName(courseKnowledgeGraphNodeTreeModel1.getKnowledgeNodeName());
        importCourseKnowledgeGraphNode.setParentCourseKnowledgeGraphNode(nodeTreeModel.getId());
        return importCourseKnowledgeGraphNode;
    }

    private static ImportCourseKnowledgeGraphNodeResource getImportCourseKnowledgeGraphNodeResource(
            Row rows, int i, ImportCourseKnowledgeGraphNode importCourseKnowledgeGraphNode,
            ImportCourseKnowledgeGraphNodeResource importCourseKnowledgeGraphNodeResource) {
        String cellValue = rows.getCell(i).getStringCellValue().trim();
        List<LevelType> levelTypes = Arrays.asList(LevelType.values());
        LevelType levelType = levelTypes.stream().filter(type -> type.getName().equals(cellValue)).findFirst().orElse(null);
        if (ObjectUtils.isNotEmpty(levelType)) {
            importCourseKnowledgeGraphNode.setLevelType(levelType.getIndex());
        } else {
            importCourseKnowledgeGraphNodeResource.setMessage("error-第" + (rows.getRowNum() + 1) + "行知识点重要层级填写错误");
            return importCourseKnowledgeGraphNodeResource;
        }
        return null;
    }

    private static void extractPreKnowledgeGraphNode(Row rows, int i, ImportCourseKnowledgeGraphNode importCourseKnowledgeGraphNode) {
        String cellValue = rows.getCell(i).getStringCellValue().trim();
        List<String> names = Arrays.asList(cellValue.split(";"));
        importCourseKnowledgeGraphNode.setPreKnowledgeGraphNodeIdList(names);
    }

    private static void extractPostKnowledgeGraphNode(Row rows, int i, ImportCourseKnowledgeGraphNode importCourseKnowledgeGraphNode) {
        String cellValue = rows.getCell(i).getStringCellValue().trim();
        List<String> names = Arrays.asList(cellValue.split(";"));
        importCourseKnowledgeGraphNode.setPostKnowledgeGraphNodeIdList(names);
    }

    private static void extractRelevanceKnowledgeGraphNode(Row rows, int i, ImportCourseKnowledgeGraphNode importCourseKnowledgeGraphNode) {
        String cellValue = rows.getCell(i).getStringCellValue().trim();
        List<String> names = Arrays.asList(cellValue.split(";"));
        importCourseKnowledgeGraphNode.setRelevanceKnowledgeGraphNodeIdList(names);
    }

    private static ImportCourseKnowledgeGraphNodeResource getImportCourseKnowledgeGraphNodeResource(
            List<ImportCourseKnowledgeGraphNode> importCourseKnowledgeGraphNodes,
            List<CourseKnowledgeGraphNode> courseKnowledgeGraphNodes, ImportCourseKnowledgeGraphNodeResource importCourseKnowledgeGraphNodeResource) {
        for (ImportCourseKnowledgeGraphNode node : importCourseKnowledgeGraphNodes) {
            List<String> preKnowledgeGraphNodeIdList = new ArrayList<>();
            List<String> postKnowledgeGraphNodeIdList = new ArrayList<>();
            List<String> relevanceKnowledgeGraphNodeIdList = new ArrayList<>();
            for (String postKnowledgeGraphNodeId : node.getPostKnowledgeGraphNodeIdList()) {
                List<CourseKnowledgeGraphNode> courseKnowledgeGraphNodeList = courseKnowledgeGraphNodes.stream().filter(knowledgeGraphNode ->
                        knowledgeGraphNode.getKnowledgeNodeName().equals(postKnowledgeGraphNodeId)
                                && !knowledgeGraphNode.getId().equals(node.getId())).collect(Collectors.toList());
                if (CollectionUtils.isEmpty(courseKnowledgeGraphNodeList)) {
                    importCourseKnowledgeGraphNodeResource.setMessage("error-第" + (node.getRow()) + "行后置知识点不存在");
                    return importCourseKnowledgeGraphNodeResource;
                }
                postKnowledgeGraphNodeIdList.addAll(
                        courseKnowledgeGraphNodeList.stream().map(CourseKnowledgeGraphNode::getId).collect(Collectors.toList()));
            }
            node.setPostKnowledgeGraphNodeIdList(postKnowledgeGraphNodeIdList);
            for (String preKnowledgeGraphNodeId : node.getPreKnowledgeGraphNodeIdList()) {
                List<CourseKnowledgeGraphNode> courseKnowledgeGraphNodeList = courseKnowledgeGraphNodes.stream().filter(knowledgeGraphNode ->
                        knowledgeGraphNode.getKnowledgeNodeName().equals(preKnowledgeGraphNodeId)
                                && !knowledgeGraphNode.getId().equals(node.getId())).collect(Collectors.toList());
                if (CollectionUtils.isEmpty(courseKnowledgeGraphNodeList)) {
                    importCourseKnowledgeGraphNodeResource.setMessage("error-第" + (node.getRow()) + "行前置知识点不存在");
                    return importCourseKnowledgeGraphNodeResource;
                }
                preKnowledgeGraphNodeIdList.addAll(
                        courseKnowledgeGraphNodeList.stream().map(CourseKnowledgeGraphNode::getId).collect(Collectors.toList()));
            }
            node.setPreKnowledgeGraphNodeIdList(preKnowledgeGraphNodeIdList);
            for (String relevanceKnowledgeGraphNodeId : node.getRelevanceKnowledgeGraphNodeIdList()) {
                List<CourseKnowledgeGraphNode> courseKnowledgeGraphNodeList = courseKnowledgeGraphNodes.stream().filter(knowledgeGraphNode ->
                        knowledgeGraphNode.getKnowledgeNodeName().equals(relevanceKnowledgeGraphNodeId)
                                && !knowledgeGraphNode.getId().equals(node.getId())).collect(Collectors.toList());
                if (CollectionUtils.isEmpty(courseKnowledgeGraphNodeList)) {
                    importCourseKnowledgeGraphNodeResource.setMessage("error-第" + (node.getRow()) + "行关联知识点不存在");
                    return importCourseKnowledgeGraphNodeResource;
                }
                relevanceKnowledgeGraphNodeIdList.addAll(
                        courseKnowledgeGraphNodeList.stream().map(CourseKnowledgeGraphNode::getId).collect(Collectors.toList()));
            }
            node.setRelevanceKnowledgeGraphNodeIdList(relevanceKnowledgeGraphNodeIdList);

            if (!Collections.disjoint(node.getPostKnowledgeGraphNodeIdList(), node.getPreKnowledgeGraphNodeIdList())
                    || !Collections.disjoint(node.getPostKnowledgeGraphNodeIdList(), node.getRelevanceKnowledgeGraphNodeIdList())
                    || !Collections.disjoint(node.getPreKnowledgeGraphNodeIdList(), node.getRelevanceKnowledgeGraphNodeIdList())) {
                importCourseKnowledgeGraphNodeResource.setMessage("error-第" + (node.getRow()) + "行知识点关系重复");
                return importCourseKnowledgeGraphNodeResource;
            }
        }
        return null;
    }

    private static void extractRelevanceKnowledgeGraphNode(Boolean isSave, ImportCourseKnowledgeGraphNode importCourseKnowledgeGraphNode,
                                                           List<CourseKnowledgeGraphNode> courseKnowledgeGraphNodes) {
        if (isSave) {
            importCourseKnowledgeGraphNode.setIsSave(true);
            CourseKnowledgeGraphNode courseKnowledgeGraphNode = new CourseKnowledgeGraphNode();
            courseKnowledgeGraphNode.setId(importCourseKnowledgeGraphNode.getId());
            courseKnowledgeGraphNode.setKnowledgeNodeName(importCourseKnowledgeGraphNode.getKnowledgeNodeName());
            courseKnowledgeGraphNode.setLevelType(importCourseKnowledgeGraphNode.getLevelType());
            courseKnowledgeGraphNode.setContentDetail(importCourseKnowledgeGraphNode.getContentDetail());
            courseKnowledgeGraphNode.setKnowledgeNodeLevel(importCourseKnowledgeGraphNode.getKnowledgeNodeLevel());
            courseKnowledgeGraphNode.setSort(importCourseKnowledgeGraphNode.getSort());
            courseKnowledgeGraphNode.setCreateTime(importCourseKnowledgeGraphNode.getCreateTime());
            courseKnowledgeGraphNode.setCreatorId(importCourseKnowledgeGraphNode.getCreatorId());
            courseKnowledgeGraphNode.setCreatorName(importCourseKnowledgeGraphNode.getCreatorName());
            courseKnowledgeGraphNode.setModifyTime(importCourseKnowledgeGraphNode.getModifyTime());
            courseKnowledgeGraphNode.setModifierId(importCourseKnowledgeGraphNode.getModifierId());
            courseKnowledgeGraphNode.setModifierName(importCourseKnowledgeGraphNode.getModifierName());
            courseKnowledgeGraphNode.setName(importCourseKnowledgeGraphNode.getName());
            courseKnowledgeGraphNodes.add(courseKnowledgeGraphNode);
        }
    }

    private static ImportCourseKnowledgeGraphNodeResource getImportCourseKnowledgeGraphNodeResource(
            List<ImportCourseKnowledgeGraphNode> importCourseKnowledgeGraphNodes,
            ImportCourseKnowledgeGraphNodeResource importCourseKnowledgeGraphNodeResource) {
        for (ImportCourseKnowledgeGraphNode node : importCourseKnowledgeGraphNodes) {
            for (String preKnowledgeGraphNodeId : node.getPreKnowledgeGraphNodeIdList()) {
                ImportCourseKnowledgeGraphNode matchImportCourseKnowledgeGraphNode = importCourseKnowledgeGraphNodes
                        .stream().filter(matchNode -> matchNode.getId().equals(preKnowledgeGraphNodeId)).findFirst().orElse(null);
                if (ObjectUtils.isNotEmpty(matchImportCourseKnowledgeGraphNode)) {
                    if (matchImportCourseKnowledgeGraphNode.getPreKnowledgeGraphNodeIdList().contains(node.getId())
                            || matchImportCourseKnowledgeGraphNode.getRelevanceKnowledgeGraphNodeIdList().contains(node.getId())) {
                        importCourseKnowledgeGraphNodeResource.setMessage(
                                "error-第" + (matchImportCourseKnowledgeGraphNode.getRow()) + "行知识点与第" + node.getRow() + "行知识点关系冲突");
                        return importCourseKnowledgeGraphNodeResource;
                    }
                }
            }
            for (String postKnowledgeGraphNodeId : node.getPostKnowledgeGraphNodeIdList()) {
                ImportCourseKnowledgeGraphNode matchImportCourseKnowledgeGraphNode = importCourseKnowledgeGraphNodes
                        .stream().filter(matchNode -> matchNode.getId().equals(postKnowledgeGraphNodeId)).findFirst().orElse(null);
                if (ObjectUtils.isNotEmpty(matchImportCourseKnowledgeGraphNode)) {
                    if (matchImportCourseKnowledgeGraphNode.getPostKnowledgeGraphNodeIdList().contains(node.getId())
                            || matchImportCourseKnowledgeGraphNode.getRelevanceKnowledgeGraphNodeIdList().contains(node.getId())) {
                        importCourseKnowledgeGraphNodeResource.setMessage(
                                "error-第" + (matchImportCourseKnowledgeGraphNode.getRow()) + "行知识点与第" + node.getRow() + "行知识点关系冲突");
                        return importCourseKnowledgeGraphNodeResource;
                    }
                }
            }
        }
        return null;
    }

    private ImportCourseKnowledgeGraphNodeResource getImportCourseKnowledgeGraphNodeResource(
            List<ImportCourseKnowledgeGraphNode> importCourseKnowledgeGraphNodeList, String userId, String userName,
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain, List<ImportCourseKnowledgeGraphNode> importCourseKnowledgeGraphNodes,
            ImportCourseKnowledgeGraphNodeResource importCourseKnowledgeGraphNodeResource, String ip) {
        //新增知识点
        List<ImportCourseKnowledgeGraphNode> needSaveImportCourseKnowledgeGraphNodes =
                importCourseKnowledgeGraphNodeList.stream().filter(item -> item.getIsSave()).collect(Collectors.toList());
        List<UserResource> userInfoList = authorityApiService.getAllUser();
        List<CourseKnowledgeGraphActionLog> logList = new ArrayList<>();
        List<String> waitPushCourseKnowledgeGraphNodeIdList = new ArrayList<>();
        needSaveImportCourseKnowledgeGraphNodes.forEach(item -> {
            neo4jUtil.createCourseKnowledgeGraphNode(courseKnowledgeGraphDomain.getId(), item.getParentCourseKnowledgeGraphNode(), item);
            createAddCourseKnowledgeGraphNodeActionLog(
                    item, courseKnowledgeGraphDomain, ip, userInfoList, logList);
            waitPushCourseKnowledgeGraphNodeIdList.add(item.getId());
        });
        courseKnowledgeGraphActionLogRepository.saveAll(logList);
        if (dataVisualEnable) {
            if (!CollectionUtils.isEmpty(waitPushCourseKnowledgeGraphNodeIdList)) {
                sendKnowledgeStructureTopicVoList(courseKnowledgeGraphDomain, waitPushCourseKnowledgeGraphNodeIdList);
            }
        }
        //更新知识点内容
        importCourseKnowledgeGraphNodeList.stream().filter(item -> item.getIsUpdate()).forEach(item -> {
            String modifyTime = DateUtils.formatDate("yyyy-MM-dd HH:mm:ss", new Date());
            String cypherSql = String.format("MATCH (n:`%s`{id:'%s'}) set n.modifierId=$modifierId,n.modifierName=$modifierName," +
                    "n.modifyTime=$modifyTime ", courseKnowledgeGraphDomain.getId(), item.getId());
            if (StringUtils.isNotEmpty(item.getContentDetail())) {
                cypherSql += ",n.contentDetail=$contentDetail ";
            }
            if (ObjectUtils.isNotEmpty(item.getLevelType())) {
                cypherSql += ",n.levelType=$levelType ";
            }
            cypherSql += "return n ";
            Value value = parameters("contentDetail", item.getContentDetail(), "levelType", item.getLevelType(),
                    "modifierId", userId, "modifierName", userName, "modifyTime", modifyTime);
            neo4jUtil.updateCourseKnowledgeGraphNode(cypherSql, value);
        });
        //删除关系
        importCourseKnowledgeGraphNodes.stream().forEach(node -> {
            node.getPreKnowledgeGraphNodeIdList().forEach(id -> {
                neo4jUtil.deleteNodeFrontRelationshipList(courseKnowledgeGraphDomain.getId(), node.getId());
            });
            node.getPostKnowledgeGraphNodeIdList().forEach(id -> {
                neo4jUtil.deleteNodeBehindRelationshipList(courseKnowledgeGraphDomain.getId(), node.getId());
            });
            node.getRelevanceKnowledgeGraphNodeIdList().forEach(id -> {
                neo4jUtil.deleteNodeRelevanceRelationshipList(courseKnowledgeGraphDomain.getId(), node.getId());
            });
        });
        //新增关系
        importCourseKnowledgeGraphNodes.stream().forEach(node -> {
            node.getPreKnowledgeGraphNodeIdList().forEach(id -> {
                neo4jUtil.checkRelationshipAndDelete(courseKnowledgeGraphDomain.getId(), id, node.getId());
                neo4jUtil.createFrontRearRelationship(courseKnowledgeGraphDomain.getId(), id, node.getId());
            });
            node.getPostKnowledgeGraphNodeIdList().forEach(id -> {
                neo4jUtil.checkRelationshipAndDelete(courseKnowledgeGraphDomain.getId(), id, node.getId());
                neo4jUtil.createFrontRearRelationship(courseKnowledgeGraphDomain.getId(), node.getId(), id);
            });
            node.getRelevanceKnowledgeGraphNodeIdList().forEach(id -> {
                neo4jUtil.checkRelationshipAndDelete(courseKnowledgeGraphDomain.getId(), id, node.getId());
                neo4jUtil.createRelevanceRelationship(courseKnowledgeGraphDomain.getId(), node.getId(), id);

            });
        });
        //更新节点数量和关系数量
        int nodeCounts = neo4jUtil.getDomainNodeCount(courseKnowledgeGraphDomain.getId());
        courseKnowledgeGraphDomain.setNodeCount(nodeCounts);
        if (nodeCounts > 1) {
            courseCompletionService.saveCourseCompletion(courseKnowledgeGraphDomain.getCourse(),
                    CourseContentTypeEnum.KNOWLEDGE_STRUCTURE, true, userId, userName);
        }
        courseKnowledgeGraphService.updateCourseKnowledgeGraphDomain(courseKnowledgeGraphDomain, userId, userName);
        importCourseKnowledgeGraphNodeResource.setAllNumber(importCourseKnowledgeGraphNodeList.size());
        importCourseKnowledgeGraphNodeResource.setRepeatNumber(
                importCourseKnowledgeGraphNodeList.size() - needSaveImportCourseKnowledgeGraphNodes.size());
        importCourseKnowledgeGraphNodeResource.setSuccessNumber(needSaveImportCourseKnowledgeGraphNodes.size());
        importCourseKnowledgeGraphNodeResource.setMessage("导入成功");
        return importCourseKnowledgeGraphNodeResource;
    }

    public void createAddCourseKnowledgeGraphNodeActionLog(ImportCourseKnowledgeGraphNode courseKnowledgeGraphNode,
                                                           CourseKnowledgeGraphDomain courseKnowledgeGraphDomain,
                                                           String ip, List<UserResource> userInfoList, List<CourseKnowledgeGraphActionLog> logList) {

        CourseKnowledgeGraphActionLog courseKnowledgeGraphActionLog = new CourseKnowledgeGraphActionLog();
        courseKnowledgeGraphActionLog.setCourseId(courseKnowledgeGraphDomain.getCourse().getId());
        courseKnowledgeGraphActionLog.setCourseName(courseKnowledgeGraphDomain.getCourseName());
        courseKnowledgeGraphActionLog.setCourseKnowledgeGraphDomainId(courseKnowledgeGraphDomain.getId());
        courseKnowledgeGraphActionLog.setTitle(BusinessType.IMPORT_COURSE_KNOWLEDGE_GRAPH_NODE.getName());
        courseKnowledgeGraphActionLog.setBusinessType(BusinessType.IMPORT_COURSE_KNOWLEDGE_GRAPH_NODE);
        courseKnowledgeGraphActionLog.setNowKnowledgeNodeId(courseKnowledgeGraphNode.getId());
        courseKnowledgeGraphActionLog.setNowKnowledgeNodeName(courseKnowledgeGraphNode.getKnowledgeNodeName());
        courseKnowledgeGraphActionLog.setNowKnowledgeNodeLevelIndex(courseKnowledgeGraphNode.getKnowledgeNodeLevel());
        courseKnowledgeGraphActionLog.setNowKnowledgeNodeLevelName(
                KnowledgeNodeLevel.getKnowledgeNodeLevel(courseKnowledgeGraphNode.getKnowledgeNodeLevel()).getName());
        courseKnowledgeGraphActionLog.setOperatorId(courseKnowledgeGraphNode.getCreatorId());
        UserResource userResource = userInfoList.stream()
                .filter(user -> user.getUserId().equals(courseKnowledgeGraphNode.getCreatorId())).findFirst().orElse(null);
        if (ObjectUtils.isNotEmpty(userResource)) {
            courseKnowledgeGraphActionLog.setOperatorNo(userResource.getUserNo());
        }
        courseKnowledgeGraphActionLog.setOperatorName(courseKnowledgeGraphNode.getCreatorName());
        courseKnowledgeGraphActionLog.setOperatorIp(ip);
        Date date = new Date();
        courseKnowledgeGraphActionLog.setOperatorTime(date);
        String operatorTime = DateUtils.formatDate("yyyy-MM-dd HH:mm:ss", date);
        courseKnowledgeGraphActionLog.setDetailContent(operatorTime + " " + courseKnowledgeGraphActionLog.getOperatorName()
                + "老师导入" + courseKnowledgeGraphActionLog.getNowKnowledgeNodeLevelName() + "【"
                + courseKnowledgeGraphActionLog.getNowKnowledgeNodeName() + "】");
        logList.add(courseKnowledgeGraphActionLog);
    }

    private ImportCourseKnowledgeGraphNode getImportCourseKnowledgeGraphNode(
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain, CourseKnowledgeGraphNodeTreeModel parentCourseKnowledgeGraphNode,
            String userId, String userName, Integer knowledgeNodeLevel, String knowledgeNodeName) {
        ImportCourseKnowledgeGraphNode importCourseKnowledgeGraphNode = new ImportCourseKnowledgeGraphNode();
        importCourseKnowledgeGraphNode.setId(UUID.randomUUID().toString().replace("-", ""));
        importCourseKnowledgeGraphNode.setCreateTime(DateUtils.formatDate("yyyy-MM-dd HH:mm:ss", new Date()));
        int childNodeCounts = neo4jUtil.getDomainChildNodeCount(courseKnowledgeGraphDomain.getId(), parentCourseKnowledgeGraphNode.getId());
        importCourseKnowledgeGraphNode.setSort(childNodeCounts + 1);
        importCourseKnowledgeGraphNode.setParentCourseKnowledgeGraphNode(parentCourseKnowledgeGraphNode.getId());
        importCourseKnowledgeGraphNode.setCreatorId(userId);
        importCourseKnowledgeGraphNode.setCreatorName(userName);
        importCourseKnowledgeGraphNode.setName(knowledgeNodeName);
        importCourseKnowledgeGraphNode.setKnowledgeNodeName(knowledgeNodeName);
        importCourseKnowledgeGraphNode.setKnowledgeNodeLevel(knowledgeNodeLevel);
        //源数据添加新增知识点数据
        CourseKnowledgeGraphNodeTreeModel courseKnowledgeGraphNodeTreeModel = new CourseKnowledgeGraphNodeTreeModel();
        courseKnowledgeGraphNodeTreeModel.setId(importCourseKnowledgeGraphNode.getId());
        courseKnowledgeGraphNodeTreeModel.setKnowledgeNodeName(importCourseKnowledgeGraphNode.getKnowledgeNodeName());
        courseKnowledgeGraphNodeTreeModel.setKnowledgeNodeLevelIndex(importCourseKnowledgeGraphNode.getKnowledgeNodeLevel());
        courseKnowledgeGraphNodeTreeModel.setSort(importCourseKnowledgeGraphNode.getSort());
        List<CourseKnowledgeGraphNodeTreeModel> subsetCourseKnowledgeGraphNodeTreeList =
                parentCourseKnowledgeGraphNode.getSubsetCourseKnowledgeGraphNodeTreeList();
        subsetCourseKnowledgeGraphNodeTreeList.add(courseKnowledgeGraphNodeTreeModel);
        parentCourseKnowledgeGraphNode.setSubsetCourseKnowledgeGraphNodeTreeList(subsetCourseKnowledgeGraphNodeTreeList);
        return importCourseKnowledgeGraphNode;
    }

    private int getAlIsNullNum(String[] titleArr, XSSFRow rows, int isNullNum) {
        for (short i = 0; i < titleArr.length; i++) {
            if (rows.getCell(i) == null) {
                isNullNum++;
            } else {
                rows.getCell(i).setCellType(CellType.STRING);
                if (StringUtils.isEmpty(rows.getCell(i).getStringCellValue()) || StringUtils.isEmpty(rows.getCell(i).getStringCellValue().trim())) {
                    isNullNum++;
                }
            }
        }
        return isNullNum;
    }

    @Async
    public void sendKnowledgeStructureTopicVoList(
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain,
            List<String> waitPushCourseKnowledgeGraphNodeIdList) {
        for (String waitPushCourseKnowledgeGraphNodeId : waitPushCourseKnowledgeGraphNodeIdList) {
            CourseKnowledgeGraphNode courseKnowledgeGraphNode =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphNode(courseKnowledgeGraphDomain.getId(),
                            waitPushCourseKnowledgeGraphNodeId);
            /*根据子节点id获取父节点*/
            CourseKnowledgeGraphNode parentCourseKnowledgeGraphNode =
                    courseKnowledgeGraphService.getParentCourseKnowledgeGraphNode(
                            courseKnowledgeGraphDomain.getId(), courseKnowledgeGraphNode.getId());
            KnowledgeStructureTopicVo knowledgeStructureTopicVo = new KnowledgeStructureTopicVo();
            knowledgeStructureTopicVo.setId(courseKnowledgeGraphNode.getId());
            knowledgeStructureTopicVo.setSource(DATA_VISUAL_SOURCE_CLOUD_CLASSROOM);
            knowledgeStructureTopicVo.setKnowledgeStructureType(TWO);
            knowledgeStructureTopicVo.setCourseCode(courseKnowledgeGraphDomain.getCourse().getCourseCode());
            knowledgeStructureTopicVo.setCourseId(courseKnowledgeGraphDomain.getCourse().getId());
            knowledgeStructureTopicVo.setCourseName(courseKnowledgeGraphDomain.getCourse().getCourseName());
            knowledgeStructureTopicVo.setParentId(parentCourseKnowledgeGraphNode.getId());
            knowledgeStructureTopicVo.setCourseVersionId(courseKnowledgeGraphDomain.getCourseVersion().getId());
            knowledgeStructureTopicVo.setContent(courseKnowledgeGraphNode.getKnowledgeNodeName());
            knowledgeStructureTopicVo.setModifierId(courseKnowledgeGraphNode.getCreatorId());
            knowledgeStructureTopicVo.setModifierName(courseKnowledgeGraphNode.getCreatorName());
            knowledgeStructureTopicVo.setModifyTime(DateUtils.formatDate("yyyy-MM-dd HH:mm:ss", new Date()));
            knowledgeStructureTopicVo.setParentName(parentCourseKnowledgeGraphNode.getKnowledgeNodeName());
            knowledgeStructureTopicVo.setKnowledgeGraphDomainId(courseKnowledgeGraphDomain.getId());
            eventService.sendAddKnowledgeStructure(knowledgeStructureTopicVo);
        }

    }

    private KnowledgeGraphDomainTopicVo getKnowledgeGraphDomainTopicVo(CourseKnowledgeGraphDomain courseKnowledgeGraphDomain) {
        KnowledgeGraphDomainTopicVo knowledgeGraphDomainTopicVo = new KnowledgeGraphDomainTopicVo();
        knowledgeGraphDomainTopicVo.setId(courseKnowledgeGraphDomain.getId());
        knowledgeGraphDomainTopicVo.setCourseId(courseKnowledgeGraphDomain.getCourse().getId());
        knowledgeGraphDomainTopicVo.setCourseCode(courseKnowledgeGraphDomain.getCourse().getCourseCode());
        knowledgeGraphDomainTopicVo.setCourseName(courseKnowledgeGraphDomain.getCourse().getCourseName());
        knowledgeGraphDomainTopicVo.setShowOrder(courseKnowledgeGraphDomain.getShowOrder());
        knowledgeGraphDomainTopicVo.setVersionLabel(courseKnowledgeGraphDomain.getVersionLabel());
        knowledgeGraphDomainTopicVo.setStatus(courseKnowledgeGraphDomain.getStatus());
        return knowledgeGraphDomainTopicVo;
    }

    private CourseKnowledgeGraphNode getCourseKnowledgeGraphNode(CourseKnowledgeGraphNode courseKnowledgeGraphNode) {
        CourseKnowledgeGraphNode newCourseKnowledgeGraphNode = new CourseKnowledgeGraphNode();
        newCourseKnowledgeGraphNode.setId(UUID.randomUUID().toString().replace("-", ""));
        newCourseKnowledgeGraphNode.setKnowledgeNodeName(courseKnowledgeGraphNode.getKnowledgeNodeName());
        newCourseKnowledgeGraphNode.setLevelType(courseKnowledgeGraphNode.getLevelType());
        newCourseKnowledgeGraphNode.setContentDetail(courseKnowledgeGraphNode.getContentDetail());
        newCourseKnowledgeGraphNode.setKnowledgeNodeLevel(courseKnowledgeGraphNode.getKnowledgeNodeLevel());
        newCourseKnowledgeGraphNode.setSort(courseKnowledgeGraphNode.getSort());
        newCourseKnowledgeGraphNode.setCreateTime(courseKnowledgeGraphNode.getCreateTime());
        newCourseKnowledgeGraphNode.setCreatorId(courseKnowledgeGraphNode.getCreatorId());
        newCourseKnowledgeGraphNode.setCreatorName(courseKnowledgeGraphNode.getCreatorName());
        newCourseKnowledgeGraphNode.setModifyTime(courseKnowledgeGraphNode.getModifyTime());
        newCourseKnowledgeGraphNode.setModifierId(courseKnowledgeGraphNode.getModifierId());
        newCourseKnowledgeGraphNode.setModifierName(courseKnowledgeGraphNode.getModifierName());
        newCourseKnowledgeGraphNode.setName(courseKnowledgeGraphNode.getName());
        return newCourseKnowledgeGraphNode;
    }

    private void createNewCourseKnowledgeGraphNodeRelationshipList(
            List<CourseKnowledgeGraphNodeRelationship> oldCourseKnowledgeGraphNodeRelationshipList,
            Map<String, CourseKnowledgeGraphNode> newCourseKnowledgeGraphNodeMap,
            List<CourseKnowledgeGraphNodeRelationship> newCourseKnowledgeGraphNodeRelationshipList) {
        for (CourseKnowledgeGraphNodeRelationship courseKnowledgeGraphNodeRelationship : oldCourseKnowledgeGraphNodeRelationshipList) {
            CourseKnowledgeGraphNode startCourseKnowledgeGraphNode =
                    newCourseKnowledgeGraphNodeMap.get(courseKnowledgeGraphNodeRelationship.getStartNodeId());
            CourseKnowledgeGraphNode endCourseKnowledgeGraphNode =
                    newCourseKnowledgeGraphNodeMap.get(courseKnowledgeGraphNodeRelationship.getEndNodeId());
            CourseKnowledgeGraphNodeRelationship newCourseKnowledgeGraphNodeRelationship =
                    getCourseKnowledgeGraphNodeRelationship(courseKnowledgeGraphNodeRelationship,
                            startCourseKnowledgeGraphNode, endCourseKnowledgeGraphNode);
            newCourseKnowledgeGraphNodeRelationshipList.add(newCourseKnowledgeGraphNodeRelationship);
        }
    }

    private CourseKnowledgeGraphNodeRelationship getCourseKnowledgeGraphNodeRelationship(
            CourseKnowledgeGraphNodeRelationship courseKnowledgeGraphNodeRelationship,
            CourseKnowledgeGraphNode startCourseKnowledgeGraphNode,
            CourseKnowledgeGraphNode endCourseKnowledgeGraphNode) {
        CourseKnowledgeGraphNodeRelationship newCourseKnowledgeGraphNodeRelationship = new CourseKnowledgeGraphNodeRelationship();
        newCourseKnowledgeGraphNodeRelationship.setStartNodeId(startCourseKnowledgeGraphNode.getId());
        newCourseKnowledgeGraphNodeRelationship.setStartNodeName(startCourseKnowledgeGraphNode.getKnowledgeNodeName());
        newCourseKnowledgeGraphNodeRelationship.setStartKnowledgeNodeLevel(courseKnowledgeGraphNodeRelationship.getStartKnowledgeNodeLevel());
        newCourseKnowledgeGraphNodeRelationship.setEndNodeId(endCourseKnowledgeGraphNode.getId());
        newCourseKnowledgeGraphNodeRelationship.setEndNodeName(endCourseKnowledgeGraphNode.getKnowledgeNodeName());
        newCourseKnowledgeGraphNodeRelationship.setEndKnowledgeNodeLevel(courseKnowledgeGraphNodeRelationship.getEndKnowledgeNodeLevel());
        newCourseKnowledgeGraphNodeRelationship.setRelationshipId(courseKnowledgeGraphNodeRelationship.getRelationshipId());
        newCourseKnowledgeGraphNodeRelationship.setRelationshipType(courseKnowledgeGraphNodeRelationship.getRelationshipType());
        newCourseKnowledgeGraphNodeRelationship.setRelationshipName(courseKnowledgeGraphNodeRelationship.getRelationshipName());
        newCourseKnowledgeGraphNodeRelationship.setRelationshipCreateTimestamp(courseKnowledgeGraphNodeRelationship.getRelationshipCreateTimestamp());
        return newCourseKnowledgeGraphNodeRelationship;
    }

    private void saveChildCourseKnowledgeGraphNodeList(
            List<CourseKnowledgeGraphNode> childCourseKnowledgeGraphNodeList,
            List<CourseKnowledgeGraphNodeRelationship> parentChildCourseKnowledgeGraphNodeRelationshipList,
            CourseKnowledgeGraphDomain newCourseKnowledgeGraphDomain) {
        for (CourseKnowledgeGraphNode childCourseKnowledgeGraphNode : childCourseKnowledgeGraphNodeList) {
            //查询父子关系
            CourseKnowledgeGraphNodeRelationship parentCourseKnowledgeGraphNodeRelationship =
                    parentChildCourseKnowledgeGraphNodeRelationshipList.stream().filter(filterCourseKnowledgeGraphNodeRelationship ->
                            filterCourseKnowledgeGraphNodeRelationship.getEndNodeId()
                                    .equals(childCourseKnowledgeGraphNode.getId())).findFirst().get();
            neo4jUtil.createChildCourseKnowledgeGraphNode(newCourseKnowledgeGraphDomain.getId(),
                    parentCourseKnowledgeGraphNodeRelationship.getStartNodeId(), childCourseKnowledgeGraphNode);
            if (dataVisualEnable) {
                sendKnowledgeStructureTopicVo(newCourseKnowledgeGraphDomain, childCourseKnowledgeGraphNode,
                        parentCourseKnowledgeGraphNodeRelationship);
            }
        }
    }

    private void sendKnowledgeStructureTopicVo(
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain, CourseKnowledgeGraphNode childCourseKnowledgeGraphNode,
            CourseKnowledgeGraphNodeRelationship parentCourseKnowledgeGraphNodeRelationship) {
        KnowledgeStructureTopicVo knowledgeStructureTopicVo = new KnowledgeStructureTopicVo();
        knowledgeStructureTopicVo.setId(childCourseKnowledgeGraphNode.getId());
        knowledgeStructureTopicVo.setSource(DATA_VISUAL_SOURCE_CLOUD_CLASSROOM);
        knowledgeStructureTopicVo.setKnowledgeStructureType(TWO);
        knowledgeStructureTopicVo.setCourseCode(courseKnowledgeGraphDomain.getCourse().getCourseCode());
        knowledgeStructureTopicVo.setCourseId(courseKnowledgeGraphDomain.getCourse().getId());
        knowledgeStructureTopicVo.setCourseName(courseKnowledgeGraphDomain.getCourse().getCourseName());
        knowledgeStructureTopicVo.setParentId(parentCourseKnowledgeGraphNodeRelationship.getStartNodeId());
        knowledgeStructureTopicVo.setCourseVersionId(courseKnowledgeGraphDomain.getCourseVersion().getId());
        knowledgeStructureTopicVo.setContent(childCourseKnowledgeGraphNode.getKnowledgeNodeName());
        knowledgeStructureTopicVo.setModifierId(childCourseKnowledgeGraphNode.getCreatorId());
        knowledgeStructureTopicVo.setModifierName(childCourseKnowledgeGraphNode.getCreatorName());
        knowledgeStructureTopicVo.setModifyTime(childCourseKnowledgeGraphNode.getCreateTime());
        knowledgeStructureTopicVo.setParentName(parentCourseKnowledgeGraphNodeRelationship.getStartNodeName());
        knowledgeStructureTopicVo.setKnowledgeGraphDomainId(courseKnowledgeGraphDomain.getId());
        eventService.sendAddKnowledgeStructure(knowledgeStructureTopicVo);
    }


    private void saveNewCourseKnowledgeGraphNodeRelationshipList(
            CourseKnowledgeGraphDomain newCourseKnowledgeGraphDomain,
            List<CourseKnowledgeGraphNodeRelationship> frontRearKnowledgeGraphRelationshipList,
            List<CourseKnowledgeGraphNodeRelationship> relevanceKnowledgeGraphRelationshipList) {
        for (CourseKnowledgeGraphNodeRelationship frontRearKnowledgeGraphRelationship : frontRearKnowledgeGraphRelationshipList) {
            neo4jUtil.createFrontRearRelationship(newCourseKnowledgeGraphDomain.getId(),
                    frontRearKnowledgeGraphRelationship.getStartNodeId(),
                    frontRearKnowledgeGraphRelationship.getEndNodeId());
        }
        for (CourseKnowledgeGraphNodeRelationship relevanceKnowledgeGraphRelationship : relevanceKnowledgeGraphRelationshipList) {
            neo4jUtil.createRelevanceRelationship(newCourseKnowledgeGraphDomain.getId(),
                    relevanceKnowledgeGraphRelationship.getStartNodeId(),
                    relevanceKnowledgeGraphRelationship.getEndNodeId());
        }
    }

    private void updateCourseKnowledgeGraphDomainData(
            String accessUserId, String accessUserName, CourseKnowledgeGraphDomain newCourseKnowledgeGraphDomain) {
        int nodeCounts = neo4jUtil.getDomainNodeCount(newCourseKnowledgeGraphDomain.getId());
        newCourseKnowledgeGraphDomain.setNodeCount(nodeCounts);
        int relationShipCounts = neo4jUtil.getDomainRelationShipCount(newCourseKnowledgeGraphDomain.getId());
        newCourseKnowledgeGraphDomain.setShipCount(relationShipCounts);
        newCourseKnowledgeGraphDomain.setModifyTime(new Date());
        newCourseKnowledgeGraphDomain.setModifierId(accessUserId);
        newCourseKnowledgeGraphDomain.setModifierName(accessUserName);
        courseKnowledgeGraphDomainRepository.saveAndFlush(newCourseKnowledgeGraphDomain);
    }

    private void saveNewCourseKnowledgeGraphNodeQuestionList(
            List<CourseKnowledgeGraphNodeQuestion> oldCourseKnowledgeGraphNodeQuestionList,
            Map<String, CourseKnowledgeGraphNode> newCourseKnowledgeGraphNodeMap, CourseKnowledgeGraphDomain newCourseKnowledgeGraphDomain) {
        List<CourseKnowledgeGraphNodeQuestion> newCourseKnowledgeGraphNodeQuestionList = new ArrayList<>();
        for (CourseKnowledgeGraphNodeQuestion courseKnowledgeGraphNodeQuestion : oldCourseKnowledgeGraphNodeQuestionList) {
            CourseKnowledgeGraphNode nowCourseKnowledgeGraphNode =
                    newCourseKnowledgeGraphNodeMap.get(courseKnowledgeGraphNodeQuestion.getKnowledgeNodeId());
            CourseKnowledgeGraphNodeQuestion newCourseKnowledgeGraphNodeQuestion =
                    getCourseKnowledgeGraphNodeQuestion(courseKnowledgeGraphNodeQuestion,
                            newCourseKnowledgeGraphDomain, nowCourseKnowledgeGraphNode);
            newCourseKnowledgeGraphNodeQuestionList.add(newCourseKnowledgeGraphNodeQuestion);
        }
        courseKnowledgeGraphNodeQuestionRepository.saveAllAndFlush(newCourseKnowledgeGraphNodeQuestionList);
    }

    private CourseKnowledgeGraphNodeQuestion getCourseKnowledgeGraphNodeQuestion(
            CourseKnowledgeGraphNodeQuestion courseKnowledgeGraphNodeQuestion,
            CourseKnowledgeGraphDomain newCourseKnowledgeGraphDomain,
            CourseKnowledgeGraphNode nowCourseKnowledgeGraphNode) {
        CourseKnowledgeGraphNodeQuestion newCourseKnowledgeGraphNodeQuestion = new CourseKnowledgeGraphNodeQuestion();
        newCourseKnowledgeGraphNodeQuestion.setCourseKnowledgeGraphDomain(newCourseKnowledgeGraphDomain);
        newCourseKnowledgeGraphNodeQuestion.setKnowledgeNodeId(nowCourseKnowledgeGraphNode.getId());
        newCourseKnowledgeGraphNodeQuestion.setQuestionId(courseKnowledgeGraphNodeQuestion.getQuestionId());
        newCourseKnowledgeGraphNodeQuestion.setSort(courseKnowledgeGraphNodeQuestion.getSort());
        newCourseKnowledgeGraphNodeQuestion.setCreatorId(courseKnowledgeGraphNodeQuestion.getCreatorId());
        newCourseKnowledgeGraphNodeQuestion.setCreatorName(courseKnowledgeGraphNodeQuestion.getCreatorName());
        newCourseKnowledgeGraphNodeQuestion.setCreateTime(courseKnowledgeGraphNodeQuestion.getCreateTime());
        return newCourseKnowledgeGraphNodeQuestion;
    }

    private void saveNewCourseKnowledgeGraphNodeResourceList(
            List<CourseKnowledgeGraphNodeResource> oldCourseKnowledgeGraphNodeResourceList,
            Map<String, CourseKnowledgeGraphNode> newCourseKnowledgeGraphNodeMap, CourseKnowledgeGraphDomain newCourseKnowledgeGraphDomain) {
        List<CourseKnowledgeGraphNodeResource> newCourseKnowledgeGraphNodeResourceList = new ArrayList<>();
        for (CourseKnowledgeGraphNodeResource courseKnowledgeGraphNodeResource : oldCourseKnowledgeGraphNodeResourceList) {
            CourseKnowledgeGraphNode nowCourseKnowledgeGraphNode =
                    newCourseKnowledgeGraphNodeMap.get(courseKnowledgeGraphNodeResource.getKnowledgeNodeId());
            CourseKnowledgeGraphNodeResourceFile oldCourseKnowledgeGraphNodeResourceFile =
                    courseKnowledgeGraphNodeResourceFileRepository.getCourseKnowledgeGraphNodeResourceFileById(
                            courseKnowledgeGraphNodeResource.getResourceDetailId());
            CourseKnowledgeGraphNodeResourceFile newCourseKnowledgeGraphNodeResourceFile =
                    getCourseKnowledgeGraphNodeResourceFile(oldCourseKnowledgeGraphNodeResourceFile);
            newCourseKnowledgeGraphNodeResourceFile =
                    courseKnowledgeGraphNodeResourceFileRepository.saveAndFlush(newCourseKnowledgeGraphNodeResourceFile);

            CourseKnowledgeGraphNodeResource newCourseKnowledgeGraphNodeResource =
                    getCourseKnowledgeGraphNodeResource(courseKnowledgeGraphNodeResource, newCourseKnowledgeGraphNodeResourceFile,
                            newCourseKnowledgeGraphDomain, nowCourseKnowledgeGraphNode);
            newCourseKnowledgeGraphNodeResourceList.add(newCourseKnowledgeGraphNodeResource);
        }
        courseKnowledgeGraphNodeResourceRepository.saveAllAndFlush(newCourseKnowledgeGraphNodeResourceList);
    }

    private CourseKnowledgeGraphNodeResourceFile getCourseKnowledgeGraphNodeResourceFile(
            CourseKnowledgeGraphNodeResourceFile oldCourseKnowledgeGraphNodeResourceFile) {
        CourseKnowledgeGraphNodeResourceFile newCourseKnowledgeGraphNodeResourceFile = new CourseKnowledgeGraphNodeResourceFile();
        newCourseKnowledgeGraphNodeResourceFile.setFileDisplayName(oldCourseKnowledgeGraphNodeResourceFile.getFileDisplayName());
        newCourseKnowledgeGraphNodeResourceFile.setFileRealName(oldCourseKnowledgeGraphNodeResourceFile.getFileRealName());
        newCourseKnowledgeGraphNodeResourceFile.setFileType(oldCourseKnowledgeGraphNodeResourceFile.getFileType());
        newCourseKnowledgeGraphNodeResourceFile.setInnerUrl(oldCourseKnowledgeGraphNodeResourceFile.getInnerUrl());
        newCourseKnowledgeGraphNodeResourceFile.setOuterUrl(oldCourseKnowledgeGraphNodeResourceFile.getOuterUrl());
        newCourseKnowledgeGraphNodeResourceFile.setFilePath(oldCourseKnowledgeGraphNodeResourceFile.getFilePath());
        newCourseKnowledgeGraphNodeResourceFile.setFileSize(oldCourseKnowledgeGraphNodeResourceFile.getFileSize());
        newCourseKnowledgeGraphNodeResourceFile.setTranscodeStatus(oldCourseKnowledgeGraphNodeResourceFile.getTranscodeStatus());
        newCourseKnowledgeGraphNodeResourceFile.setTranscodeMessage(oldCourseKnowledgeGraphNodeResourceFile.getTranscodeMessage());
        newCourseKnowledgeGraphNodeResourceFile.setTranscodeInnerIp(oldCourseKnowledgeGraphNodeResourceFile.getTranscodeInnerIp());
        newCourseKnowledgeGraphNodeResourceFile.setTranscodeOuterIp(oldCourseKnowledgeGraphNodeResourceFile.getTranscodeOuterIp());
        newCourseKnowledgeGraphNodeResourceFile.setTranscodeFileType(oldCourseKnowledgeGraphNodeResourceFile.getTranscodeFileType());
        newCourseKnowledgeGraphNodeResourceFile.setTranscodeFilePath(oldCourseKnowledgeGraphNodeResourceFile.getTranscodeFilePath());
        newCourseKnowledgeGraphNodeResourceFile.setTranscodeFileSavedName(oldCourseKnowledgeGraphNodeResourceFile.getTranscodeFileSavedName());
        newCourseKnowledgeGraphNodeResourceFile.setTranscodeFileSize(oldCourseKnowledgeGraphNodeResourceFile.getTranscodeFileSize());
        newCourseKnowledgeGraphNodeResourceFile.setModifierId(oldCourseKnowledgeGraphNodeResourceFile.getModifierId());
        newCourseKnowledgeGraphNodeResourceFile.setModifierName(oldCourseKnowledgeGraphNodeResourceFile.getModifierName());
        newCourseKnowledgeGraphNodeResourceFile.setModifyTime(oldCourseKnowledgeGraphNodeResourceFile.getModifyTime());
        newCourseKnowledgeGraphNodeResourceFile.setCreatorId(oldCourseKnowledgeGraphNodeResourceFile.getCreatorId());
        newCourseKnowledgeGraphNodeResourceFile.setCreatorName(oldCourseKnowledgeGraphNodeResourceFile.getCreatorName());
        newCourseKnowledgeGraphNodeResourceFile.setCreateTime(oldCourseKnowledgeGraphNodeResourceFile.getCreateTime());
        return newCourseKnowledgeGraphNodeResourceFile;
    }

    private CourseKnowledgeGraphNodeResource getCourseKnowledgeGraphNodeResource(
            CourseKnowledgeGraphNodeResource courseKnowledgeGraphNodeResource,
            CourseKnowledgeGraphNodeResourceFile newCourseKnowledgeGraphNodeResourceFile,
            CourseKnowledgeGraphDomain newCourseKnowledgeGraphDomain,
            CourseKnowledgeGraphNode nowCourseKnowledgeGraphNode) {
        CourseKnowledgeGraphNodeResource newCourseKnowledgeGraphNodeResource = new CourseKnowledgeGraphNodeResource();
        newCourseKnowledgeGraphNodeResource.setGraphNodeResourceStatus(courseKnowledgeGraphNodeResource.getGraphNodeResourceStatus());
        newCourseKnowledgeGraphNodeResource.setResourceType(courseKnowledgeGraphNodeResource.getResourceType());
        newCourseKnowledgeGraphNodeResource.setResourceDetailId(newCourseKnowledgeGraphNodeResourceFile.getId());
        newCourseKnowledgeGraphNodeResource.setResourceName(courseKnowledgeGraphNodeResource.getResourceName());
        newCourseKnowledgeGraphNodeResource.setResourceSize(courseKnowledgeGraphNodeResource.getResourceSize());
        newCourseKnowledgeGraphNodeResource.setResourceContentNum(courseKnowledgeGraphNodeResource.getResourceContentNum());
        newCourseKnowledgeGraphNodeResource.setCourseKnowledgeGraphDomain(newCourseKnowledgeGraphDomain);
        newCourseKnowledgeGraphNodeResource.setKnowledgeNodeId(nowCourseKnowledgeGraphNode.getId());
        newCourseKnowledgeGraphNodeResource.setSort(courseKnowledgeGraphNodeResource.getSort());
        newCourseKnowledgeGraphNodeResource.setModifierId(courseKnowledgeGraphNodeResource.getModifierId());
        newCourseKnowledgeGraphNodeResource.setModifierName(courseKnowledgeGraphNodeResource.getModifierName());
        newCourseKnowledgeGraphNodeResource.setModifyTime(courseKnowledgeGraphNodeResource.getModifyTime());
        newCourseKnowledgeGraphNodeResource.setCreatorId(courseKnowledgeGraphNodeResource.getCreatorId());
        newCourseKnowledgeGraphNodeResource.setCreatorName(courseKnowledgeGraphNodeResource.getCreatorName());
        newCourseKnowledgeGraphNodeResource.setCreateTime(courseKnowledgeGraphNodeResource.getCreateTime());
        return newCourseKnowledgeGraphNodeResource;
    }

    private void saveNewCourseKnowledgeGraphNodeVideoInfoTextList(
            List<CourseKnowledgeGraphNodeVideoInfoText> oldCourseKnowledgeGraphNodeVideoInfoTextList,
            Map<String, CourseKnowledgeGraphNode> newCourseKnowledgeGraphNodeMap,
            CourseKnowledgeGraphDomain newCourseKnowledgeGraphDomain) {
        List<CourseKnowledgeGraphNodeVideoInfoText> newCourseKnowledgeGraphNodeVideoInfoTextList = new ArrayList<>();
        for (CourseKnowledgeGraphNodeVideoInfoText oldCourseKnowledgeGraphNodeVideoInfoText : oldCourseKnowledgeGraphNodeVideoInfoTextList) {
            CourseKnowledgeGraphNode nowCourseKnowledgeGraphNode =
                    newCourseKnowledgeGraphNodeMap.get(oldCourseKnowledgeGraphNodeVideoInfoText.getKnowledgeNodeId());
            CourseKnowledgeGraphNodeVideoInfoText newCourseKnowledgeGraphNodeVideoInfoText =
                    getCourseKnowledgeGraphNodeVideoInfoText(oldCourseKnowledgeGraphNodeVideoInfoText,
                            newCourseKnowledgeGraphDomain, nowCourseKnowledgeGraphNode);
            newCourseKnowledgeGraphNodeVideoInfoTextList.add(newCourseKnowledgeGraphNodeVideoInfoText);
        }
        courseKnowledgeGraphNodeVideoInfoTextRepository.saveAllAndFlush(newCourseKnowledgeGraphNodeVideoInfoTextList);
    }

    private CourseKnowledgeGraphNodeVideoInfoText getCourseKnowledgeGraphNodeVideoInfoText(
            CourseKnowledgeGraphNodeVideoInfoText oldCourseKnowledgeGraphNodeVideoInfoText,
            CourseKnowledgeGraphDomain newCourseKnowledgeGraphDomain, CourseKnowledgeGraphNode nowCourseKnowledgeGraphNode) {
        CourseKnowledgeGraphNodeVideoInfoText newCourseKnowledgeGraphNodeVideoInfoText = new CourseKnowledgeGraphNodeVideoInfoText();
        newCourseKnowledgeGraphNodeVideoInfoText.setCourseKnowledgeGraphDomain(newCourseKnowledgeGraphDomain);
        newCourseKnowledgeGraphNodeVideoInfoText.setTextDataSource(oldCourseKnowledgeGraphNodeVideoInfoText.getTextDataSource());
        newCourseKnowledgeGraphNodeVideoInfoText.setVideoInfoId(oldCourseKnowledgeGraphNodeVideoInfoText.getVideoInfoId());
        newCourseKnowledgeGraphNodeVideoInfoText.setSchoolYear(oldCourseKnowledgeGraphNodeVideoInfoText.getSchoolYear());
        newCourseKnowledgeGraphNodeVideoInfoText.setTerm(oldCourseKnowledgeGraphNodeVideoInfoText.getTerm());
        newCourseKnowledgeGraphNodeVideoInfoText.setTeacherIds(oldCourseKnowledgeGraphNodeVideoInfoText.getTeacherIds());
        newCourseKnowledgeGraphNodeVideoInfoText.setTeacherNos(oldCourseKnowledgeGraphNodeVideoInfoText.getTeacherNos());
        newCourseKnowledgeGraphNodeVideoInfoText.setTeacherNames(oldCourseKnowledgeGraphNodeVideoInfoText.getTeacherNames());
        newCourseKnowledgeGraphNodeVideoInfoText.setVideoDate(oldCourseKnowledgeGraphNodeVideoInfoText.getVideoDate());
        newCourseKnowledgeGraphNodeVideoInfoText.setCourseTableDetailId(oldCourseKnowledgeGraphNodeVideoInfoText.getCourseTableDetailId());
        newCourseKnowledgeGraphNodeVideoInfoText.setSegment(oldCourseKnowledgeGraphNodeVideoInfoText.getSegment());
        newCourseKnowledgeGraphNodeVideoInfoText.setCourseTableId(oldCourseKnowledgeGraphNodeVideoInfoText.getCourseTableId());
        newCourseKnowledgeGraphNodeVideoInfoText.setGroupId(oldCourseKnowledgeGraphNodeVideoInfoText.getGroupId());
        newCourseKnowledgeGraphNodeVideoInfoText.setCourseId(oldCourseKnowledgeGraphNodeVideoInfoText.getCourseId());
        newCourseKnowledgeGraphNodeVideoInfoText.setInnerIp(oldCourseKnowledgeGraphNodeVideoInfoText.getInnerIp());
        newCourseKnowledgeGraphNodeVideoInfoText.setOuterIp(oldCourseKnowledgeGraphNodeVideoInfoText.getOuterIp());
        newCourseKnowledgeGraphNodeVideoInfoText.setThumbnailPath(oldCourseKnowledgeGraphNodeVideoInfoText.getThumbnailPath());
        newCourseKnowledgeGraphNodeVideoInfoText.setKnowledgeNodeId(nowCourseKnowledgeGraphNode.getId());
        newCourseKnowledgeGraphNodeVideoInfoText.setKnowledgeNodeName(nowCourseKnowledgeGraphNode.getKnowledgeNodeName());
        newCourseKnowledgeGraphNodeVideoInfoText.setTextContent(oldCourseKnowledgeGraphNodeVideoInfoText.getTextContent());
        newCourseKnowledgeGraphNodeVideoInfoText.setHighLightTextContent(oldCourseKnowledgeGraphNodeVideoInfoText.getHighLightTextContent());
        newCourseKnowledgeGraphNodeVideoInfoText.setStartTimestamp(oldCourseKnowledgeGraphNodeVideoInfoText.getStartTimestamp());
        newCourseKnowledgeGraphNodeVideoInfoText.setEndTimestamp(oldCourseKnowledgeGraphNodeVideoInfoText.getEndTimestamp());
        newCourseKnowledgeGraphNodeVideoInfoText.setCreatorId(oldCourseKnowledgeGraphNodeVideoInfoText.getCreatorId());
        newCourseKnowledgeGraphNodeVideoInfoText.setCreatorName(oldCourseKnowledgeGraphNodeVideoInfoText.getCreatorName());
        newCourseKnowledgeGraphNodeVideoInfoText.setCreateTime(oldCourseKnowledgeGraphNodeVideoInfoText.getCreateTime());
        newCourseKnowledgeGraphNodeVideoInfoText.setSimilarityScore(oldCourseKnowledgeGraphNodeVideoInfoText.getStartTimestamp());
        return newCourseKnowledgeGraphNodeVideoInfoText;
    }

}
