package com.lztech.site.service.preparationcoursebag;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.lztech.domain.model.college.College;
import com.lztech.domain.model.course.*;
import com.lztech.domain.model.course.enums.ResourceStatus;
import com.lztech.domain.model.course.enums.ResourceType;
import com.lztech.domain.model.course.enums.StructureStatus;
import com.lztech.domain.model.course.enums.TeacherDataSource;
import com.lztech.domain.model.downloadrecord.DownloadRecord;
import com.lztech.domain.model.downloadrecord.enums.DowmloadRecordStatus;
import com.lztech.domain.model.term.Term;
import com.lztech.persistence.repositories.college.CollegeRepository;
import com.lztech.persistence.repositories.course.*;
import com.lztech.persistence.repositories.course.specification.CourseSpecification;
import com.lztech.persistence.repositories.downloadrecord.DownloadRecordRepository;
import com.lztech.site.constants.Constant;
import com.lztech.site.constants.Result;
import com.lztech.site.resource.coursebag.FileInfoVo;
import com.lztech.site.resource.coursebag.UploadFileMessage;
import com.lztech.site.resource.courseteacher.CourseTeacherNewVo;
import com.lztech.site.service.term.TermService;
import com.lztech.site.until.DateUtils;
import com.lztech.site.until.Md5Utils;
import com.lztech.site.until.Util;
import com.lztech.site.until.easyexcel.CustomCellWriteHeightHandler;
import com.lztech.site.until.easyexcel.SheetHeadHandler;
import com.lztech.site.viewmodel.coursemanagement.CourseResourceVo;
import com.lztech.site.viewmodel.coursematerial.*;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.lztech.site.config.Access.signKey;
import static com.lztech.site.constants.ConstantWebApi.SAVE_FILE_TO_ZIP;
import static java.util.stream.Collectors.*;

/**
 * @author 智教云项目组
 * @version 1.0
 * @date 2022/3/25 14:35
 * @content:
 */
@Service
@Log4j2
public class ExtendPreparationCourseBagService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExtendPreparationCourseBagService.class);

    @Autowired
    private CollegeRepository collegeRepository;
    @Autowired
    private DownloadRecordRepository downloadRecordRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TermService termService;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CourseTeachingTeamRepository courseTeachingTeamRepository;
    @Autowired
    private CourseStructureRepository courseStructureRepository;
    @Autowired
    private CourseResourceRepository courseResourceRepository;
    @Autowired
    private CourseResourceFileRepository courseResourceFileRepository;
    @Value("${request.address.fileManagementApi}")
    private String fileManagementApi;
    @Value("${studentType}")
    private String studentType;

    private static double trillion = 1048576.0;
    private static double zeroPointOne = 0.1;

    public CourseMateriaList courseMateriaList(String courseStructureId, MateriaListQueryParam queryParam) {
        CourseMateriaList courseMateriaList = new CourseMateriaList();
        List<CourseResourceVo> courseResourceVos = new ArrayList<>();
        CourseStructure courseStructure = courseStructureRepository.findById(courseStructureId).orElse(null);
        if (!ObjectUtils.isEmpty(courseStructure)) {
            String courseId = courseStructure.getCourse().getId();
            List<CourseResource> courseResources = courseResourceRepository.findAllByCourseStructureIdInAndResourceStatus(
                            courseStructureRepository.findByCourseIdAndStructureStatus(courseId, StructureStatus.NORMAL)
                                    .stream()
                                    .map(CourseStructure::getId)
                                    .collect(toList()),
                            ResourceStatus.NORMAL)
                    .stream().filter(
                            courseResource -> courseResource.getResourceType().equals(ResourceType.TEACHING_COURSE_WARE)
                                    || courseResource.getResourceType().equals(ResourceType.MICRO_VIDEO)
                                    || courseResource.getResourceType().equals(ResourceType.IMAGE)
                    ).collect(toList());
            if (ObjectUtils.allNotNull(queryParam.getResourceType())) {
                courseResources = courseResources
                        .stream()
                        .filter(courseResource -> Objects.equals(courseResource.getResourceType().getIndex(), queryParam.getResourceType()))
                        .collect(toList());
            }
            if (StringUtils.isNotEmpty(queryParam.getResourceName())) {
                courseResources = courseResources
                        .stream()
                        .filter(courseResource -> courseResource.getResourceName().contains(queryParam.getResourceName()))
                        .collect(toList());
            }
            if (StringUtils.isNotEmpty(queryParam.getModifierName())) {
                courseResources = courseResources
                        .stream()
                        .filter(courseResource -> courseResource.getModifierName().contains(queryParam.getModifierName()))
                        .collect(toList());
            }
            List<CourseResourceFile> courseResourceFiles = courseResourceFileRepository.findByIdIn(courseResources
                    .stream()
                    .map(CourseResource::getResourceDetailId)
                    .collect(toList()));
            courseResourceVos = getCourseResourceList(courseResources, courseResourceFiles)
                    .stream()
                    .sorted(Comparator.comparing(CourseResourceVo::getModifyTime))
                    .collect(toList());
            int allSize = courseResourceVos.size();
            if (ObjectUtils.allNotNull(queryParam.getPageNum(), queryParam.getPageSize())) {
                courseResourceVos = courseResourceVos.stream()
                        .skip((long) (queryParam.getPageNum() - 1) * queryParam.getPageSize())
                        .limit(queryParam.getPageSize()).collect(toList());
            }
            courseResourceVos.forEach(courseResourceVo -> courseResourceVo.setCourseId(courseId));
            courseMateriaList.setCourseResourceList(courseResourceVos);
            courseMateriaList.setTotalCount(allSize);
            courseMateriaList.setPageNum(queryParam.getPageNum());
            courseMateriaList.setPageSize(queryParam.getPageSize());
        }
        return courseMateriaList;
    }

    private List<CourseResourceVo> getCourseResourceList(List<CourseResource> courseResources,
                                                         List<CourseResourceFile> courseResourceFiles) {
        List<CourseResourceVo> courseResourceVos = courseResources.stream()
                .map(courseResource -> new CourseResourceVo() {{
                    this.setResourceId(courseResource.getId());
                    this.setResourceName(courseResource.getResourceName());
                    this.setResourceType(courseResource.getResourceType().getIndex());
                    this.setModifierId(courseResource.getModifierId());
                    this.setModifyTime(DateUtils.formatDate(DateUtils.DATE_TIME, courseResource.getModifyTime()));
                    this.setModifierName(courseResource.getModifierName());
                    this.setIsPublic(courseResource.getIsPublic() ? 1 : 0);
                    this.setResourceDetailId(courseResource.getResourceDetailId());
                    this.creatorId(courseResource.getCreatorId());
                    if (courseResource.getResourceType().equals(ResourceType.CLASS_TEST)) {
                        this.setQuestionNum(courseResource.getResourceContentNum());
                    }
                    this.setResourceNum(Objects.isNull(courseResource.getResourceContentNum()) ?
                            0 : courseResource.getResourceContentNum());
                    this.setResourceReferences(courseResource.getResourceReferences() == null ? 0 :
                            (courseResource.getResourceReferences()));
                    this.setResourceOtherReferences(courseResource.getResourceOtherReferences() == null ? 0 :
                            (courseResource.getResourceOtherReferences()));
                    CourseResourceFile file = courseResourceFiles
                            .stream()
                            .filter(courseResourceFile -> courseResourceFile.getId().equals(courseResource.getResourceDetailId()))
                            .findFirst()
                            .orElse(null);
                    if (ObjectUtils.isNotEmpty(file)) {
                        this.setInnerIp(file.getInnerIp());
                        this.setOuterIp(file.getOuterIp());
                        this.setFilePath(file.getFilePath());
                        this.setFileType(file.getFileType());
                        this.setFileSavedName(file.getFileSavedName());
                        this.setFileSize(file.getFileSize() + "");
                        String sizeStr = "";
                        if (file.getFileSize() != null) {
                            double size = file.getFileSize() / trillion;
                            if (size <= zeroPointOne) {
                                sizeStr += "0.1M";
                            } else {
                                sizeStr += Util.initDecimalFormat(size) + "M";
                            }
                        }
                        this.setResourceSize(sizeStr);
                    } else {
                        this.setInnerIp("");
                        this.setOuterIp("");
                        this.setFilePath("");
                        this.setResourceSize("");
                        this.setFileType("");
                        this.setFileSavedName("");
                        this.setFileSize("");
                    }
                }}).sorted(Comparator.comparing(CourseResourceVo::getModifyTime))
                .collect(Collectors.toList());
        return courseResourceVos;
    }

    public CourseMateriaStatistic courseMateriaStatistic(String courseStructureId) {
        CourseMateriaStatistic courseMateriaStatistic = new CourseMateriaStatistic();
        CourseStructure courseStructure = courseStructureRepository.findById(courseStructureId).orElse(null);
        if (ObjectUtils.isNotEmpty(courseStructure)) {
            String courseId = courseStructure.getCourse().getId();
            Map<ResourceType, List<CourseResource>> resourceTypeListMap = courseResourceRepository.findAllByCourseStructureIdInAndResourceStatus(
                            courseStructureRepository.findByCourseIdAndStructureStatus(courseId, StructureStatus.NORMAL)
                                    .stream()
                                    .map(CourseStructure::getId)
                                    .collect(toList()),
                            ResourceStatus.NORMAL)
                    .stream().filter(
                            courseResource -> courseResource.getResourceType().equals(ResourceType.TEACHING_COURSE_WARE)
                                    || courseResource.getResourceType().equals(ResourceType.MICRO_VIDEO)
                                    || courseResource.getResourceType().equals(ResourceType.IMAGE)
                    ).collect(groupingBy(CourseResource::getResourceType));
            resourceTypeListMap.forEach((key, value) -> {
                CourseMateriaStatisticVO courseMateriaStatisticVO = new CourseMateriaStatisticVO();
                courseMateriaStatisticVO.setName(key.name().equals(ResourceType.TEACHING_COURSE_WARE.name())
                        ? "课件" : key.name().equals(ResourceType.MICRO_VIDEO.name()) ? "视频"
                        : "图片");
                courseMateriaStatisticVO.setValue(String.valueOf(value.size()));
                courseMateriaStatistic.add(courseMateriaStatisticVO);
            });
            List<CourseMateriaStatisticVO> collect = courseMateriaStatistic
                    .stream()
                    .sorted(Comparator.comparing(CourseMateriaStatisticVO::getName).reversed())
                    .collect(toList());
            for (int i = 0; i < collect.size(); i++) {
                courseMateriaStatistic.set(i, collect.get(i));
            }
        }
        return courseMateriaStatistic;
    }

    public TeacherMateriaStatistic teacherMateriaStatistic(String courseStructureId) {
        TeacherMateriaStatistic teacherMateriaStatistic = new TeacherMateriaStatistic();
        List<CourseStatisticEntity> courseStatisticEntities = new ArrayList<>();
        CourseStructure courseStructure = courseStructureRepository.findById(courseStructureId).orElse(null);
        assert courseStructure != null;
        String courseId = courseStructure.getCourse().getId();
        List<CourseTeachingTeam> courseTeachingTeamList = courseTeachingTeamRepository
                .findByCourseIdAndTeacherDataSource(courseId, TeacherDataSource.CAMPUS_USER);
        List<String> teacherIds = courseTeachingTeamList.stream().map(CourseTeachingTeam::getTeacherId).collect(toList());
        if (courseTeachingTeamList.size() != 0) {
            if (ObjectUtils.isNotEmpty(courseStructure)) {
                Map<String, List<CourseResource>> teacherIdGroup = courseResourceRepository
                        .findAllByCourseStructureIdInAndResourceStatus(courseStructureRepository
                                .findByCourseIdAndStructureStatus(courseId, StructureStatus.NORMAL)
                                .stream()
                                .map(CourseStructure::getId)
                                .collect(toList()), ResourceStatus.NORMAL)
                        .stream()
                        .filter(courseResource -> courseResource.getResourceType().equals(ResourceType.TEACHING_COURSE_WARE)
                                || courseResource.getResourceType().equals(ResourceType.MICRO_VIDEO)
                                || courseResource.getResourceType().equals(ResourceType.IMAGE))
                        .collect(groupingBy(CourseResource::getCreatorId));
                teacherIdGroup.forEach((key, value) -> {
                    CourseStatisticEntity courseStatisticEntity = new CourseStatisticEntity();
                    if (!teacherIds.contains(key)) {
                        return;
                    }
                    courseTeachingTeamList
                            .stream()
                            .filter(courseTeachingTeam -> courseTeachingTeam.getTeacherId().equals(key))
                            .findFirst()
                            .ifPresent(teachingTeam -> {
                                        courseStatisticEntity.setTeacherName(teachingTeam.getTeacherName());
                                        courseStatisticEntity.setTeacherNo(teachingTeam.getTeacherNo());
                                        courseStatisticEntity.setMateriaNum(value.size());
                                    }
                            );
                    courseStatisticEntities.add(courseStatisticEntity);
                });
                List<String> sortedNameList = new LinkedList<>();
                List<String> sortedNumList = new LinkedList<>();
                courseStatisticEntities.stream()
                        .filter(courseStatistic -> StringUtils.isNotEmpty(courseStatistic.getTeacherName()))
                        .sorted(
                                Comparator.comparing(CourseStatisticEntity::getMateriaNum).reversed()
                                        .thenComparing(CourseStatisticEntity::getTeacherNo))
                        .forEach(courseStatistic -> {
                            sortedNameList.add(courseStatistic.getTeacherName());
                            sortedNumList.add(String.valueOf(courseStatistic.getMateriaNum()));
                        });
                teacherMateriaStatistic.setTeacherNameList(sortedNameList);
                teacherMateriaStatistic.setResourceNumList(sortedNumList);
            }
        }
        return teacherMateriaStatistic;
    }

    public boolean exportStatistic(StatisticQueryParam queryParam, HttpServletResponse response) {
        boolean flag = false;
        Map<String, Object> paramMap = new HashMap<>();
        String countSql = buildMaterialStatisticCountSql(queryParam, paramMap);
        Query countQuery = entityManager.createNativeQuery(countSql);
        paramMap.forEach(countQuery::setParameter);
        int count = ((int) countQuery.unwrap(NativeQueryImpl.class).stream().count());
        queryParam.setPageNum(1);
        queryParam.setPageSize(count);
        List<CourseResourceStatisticVo> courseResourceStatisticList = materialStatistic(queryParam).getCourseResourceStatisticList();
        if (courseResourceStatisticList.size() > 0) {
            try {
                //表头策略
                WriteCellStyle headWriteCellStyle = new WriteCellStyle();
                //设置头居中
                headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
                headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
                WriteFont headWriteFont = new WriteFont();
                headWriteFont.setFontName("宋体");
                headWriteFont.setFontHeightInPoints(Short.parseShort("12"));
                headWriteCellStyle.setWriteFont(headWriteFont);


                //内容策略
                WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
                //设置 水平居中
                contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.LEFT);
                contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);
                contentWriteCellStyle.setBorderRight(BorderStyle.THIN);
                contentWriteCellStyle.setBorderTop(BorderStyle.THIN);
                contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);
                contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                contentWriteCellStyle.setWrapped(true);
                WriteFont contentWriteFont = new WriteFont();
                contentWriteFont.setFontName("宋体");
                contentWriteFont.setFontHeightInPoints(Short.parseShort("11"));
                contentWriteCellStyle.setWriteFont(contentWriteFont);
                HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

                response.setContentType("application/vnd.ms-excel;charset=utf-8");
                //response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                response.setCharacterEncoding("utf-8");
                // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
                String fileName = URLEncoder.encode("课程素材统计表", "UTF-8");
                response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
                // 这里需要设置不关闭流
                EasyExcel.write(
                                response.getOutputStream(),
                                CourseResourceStatisticVo.class
                        ).autoCloseStream(Boolean.FALSE)
                        .registerWriteHandler(horizontalCellStyleStrategy)
                        .registerWriteHandler(new SheetHeadHandler())
                        .registerWriteHandler(new CustomCellWriteHeightHandler())
                        .sheet("课程素材统计表")
                        .relativeHeadRowIndex(1)
                        .doWrite(courseResourceStatisticList);
                flag = true;
            } catch (Exception exception) {
                log.error("exportStatistic->", exception);
            }
        }
        return flag;
    }

    public StatisticResult materialStatistic(StatisticQueryParam queryParam) {
        StatisticResult statisticResult = new StatisticResult();
        List<CourseResourceStatisticVo> courseResourceList = new ArrayList<>();
        Map<String, Object> paramMap = new HashMap<>();
        String countSql = buildMaterialStatisticCountSql(queryParam, paramMap);
        Query countQuery = entityManager.createNativeQuery(countSql);
        paramMap.forEach(countQuery::setParameter);
        long count = countQuery.unwrap(NativeQueryImpl.class).stream().count();
        Map<String, Object> queryParamMap = new HashMap<>();
        String querySql = buildMaterialStatisticQuerySql(queryParam, queryParamMap);
        Query querySqlQuery = entityManager.createNativeQuery(querySql);
        paramMap.forEach(querySqlQuery::setParameter);
        querySqlQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseResourceStatisticNativeVo.class));
        List<CourseResourceStatisticNativeVo> queryResultList = (List<CourseResourceStatisticNativeVo>) querySqlQuery.getResultList();
        queryResultList.forEach(courseResourceStatisticNativeVo -> {
            CourseResourceStatisticVo courseResourceStatisticVo = new CourseResourceStatisticVo();
            BeanUtils.copyProperties(courseResourceStatisticNativeVo, courseResourceStatisticVo);
            courseResourceStatisticVo.setMaterialNum(courseResourceStatisticNativeVo.getMaterialNum().intValue());
            courseResourceStatisticVo.setPictureNum(courseResourceStatisticNativeVo.getPictureNum().intValue());
            courseResourceStatisticVo.setCoursewareNum(courseResourceStatisticNativeVo.getCoursewareNum().intValue());
            courseResourceStatisticVo.setVideoNum(courseResourceStatisticNativeVo.getVideoNum().intValue());
            courseResourceStatisticVo.setUseState(courseResourceStatisticNativeVo.getUseState().toString());
            courseResourceList.add(courseResourceStatisticVo);
        });
        statisticResult.setCourseResourceStatisticList(courseResourceList);
        statisticResult.setPageNum(queryParam.getPageNum());
        statisticResult.setPageSize(queryParam.getPageSize());
        statisticResult.setTotalCount(((int) count));
        return statisticResult;
    }

    private String buildMaterialStatisticCountSql(StatisticQueryParam queryParam,
                                                  Map<String, Object> paramMap) {
        String collegeCode = queryParam.getCollegeCode();
        String courseName = queryParam.getCourseName();
        String querySql = "SELECT count(1) " +
                " FROM tb_course_resource tcr " +
                " LEFT JOIN tb_course_structure tcs ON tcr.course_structure_id = tcs.id " +
                " LEFT JOIN tb_course tc ON tc.id = tcs.course_id  " +
                " WHERE  tc.id = ( SELECT tcs.course_id FROM tb_course_structure tcs WHERE id = tcr.course_structure_id )  " +
                " AND tc.use_state=1 AND tcr.resource_status=0 AND tcs.structure_status = 0 ";
        if (StringUtils.isNotBlank(collegeCode)) {
            querySql += " AND tc.college_code= :collegeCode ";
            paramMap.put("collegeCode", collegeCode);
        }
        if (StringUtils.isNotBlank(courseName)) {
            querySql += " AND (tc.course_code like :courseName or tc.course_name like :courseName) ";
            paramMap.put("courseName", "%" + courseName + "%");
        }
        querySql += " GROUP BY course_code ";
        return querySql;
    }


    private String buildMaterialStatisticQuerySql(StatisticQueryParam queryParam, Map<String, Object> paramMap) {
        Integer pageNum = queryParam.getPageNum();
        Integer pageSize = queryParam.getPageSize();
        String sort = queryParam.getSort();
        Integer sortType = queryParam.getSortType();
        String collegeCode = queryParam.getCollegeCode();
        String courseName = queryParam.getCourseName();
        String querySql = "SELECT tcr.course_structure_id 'courseStructureId', " +
                " tcs.course_id 'courseId', tc.college_name 'courseCollege', " +
                " tc.college_code 'collegeCode', tc.use_state 'useState', " +
                " tc.course_code 'courseCode', tc.course_name 'courseName', " +
                " CONVERT((SELECT  count( id ) FROM  tb_course_resource tcrs  WHERE tcrs.resource_type = 3  AND tcrs.resource_status = 0  " +
                "  AND tcrs.course_structure_id IN ( SELECT id FROM tb_course_structure WHERE course_id = tcs.course_id ) ), UNSIGNED) " +
                "  'pictureNum', " +
                " CONVERT(( SELECT count( id ) FROM tb_course_resource tcrs  " +
                " WHERE  tcrs.resource_type = 1 AND tcrs.resource_status = 0  " +
                "  AND tcrs.course_structure_id IN ( SELECT id FROM tb_course_structure WHERE course_id = tcs.course_id )  " +
                " ), UNSIGNED) 'videoNum', " +
                " CONVERT(( SELECT count( id ) FROM tb_course_resource tcrs WHERE " +
                "  tcrs.resource_type = 0 AND tcrs.resource_status = 0  " +
                "  AND tcrs.course_structure_id IN ( SELECT id FROM tb_course_structure WHERE course_id = tcs.course_id )  " +
                " ) , UNSIGNED) 'coursewareNum', " +
                " CONVERT((SELECT(pictureNum + videoNum + coursewareNum)) , UNSIGNED) 'materialNum'  " +
                " FROM tb_course_resource tcr " +
                " LEFT JOIN tb_course_structure tcs ON tcr.course_structure_id = tcs.id " +
                " LEFT JOIN tb_course tc ON tc.id = tcs.course_id  " +
                " WHERE  tc.id = ( SELECT tcs.course_id FROM tb_course_structure tcs WHERE id = tcr.course_structure_id )  " +
                " AND tc.use_state=1  AND tcr.resource_status=0  AND tcs.structure_status=0 ";
        if (StringUtils.isNotBlank(collegeCode)) {
            querySql += " AND tc.college_code= :collegeCode ";
            paramMap.put("collegeCode", collegeCode);
        }
        if (StringUtils.isNotBlank(courseName)) {
            querySql += " AND (tc.course_code like :courseName or tc.course_name like :courseName) ";
            paramMap.put("courseName", "%" + courseName + "%");
        }
        querySql += " GROUP BY course_code ORDER BY " +
                (sortType == 1 ? "materialNum" : sortType == Constant.TWO ? "coursewareNum" :
                        sortType == Constant.THREE ? "videoNum" : sortType == Constant.FOUR ? "pictureNum" : "courseCode") + " " + sort +
                " LIMIT " + (pageNum - 1) * pageSize + "," + pageSize;
        return querySql;
    }

    public List<CourseTeacherNewVo> getTeacherCoursesByTeacherId(String teacherId) {
        Term term = termService.getNowTerm();
        /** 老师当前学期所上课程 **/
        List<CourseTeacherNewVo> courseTeachers = getCourseTeacherResource(teacherId, term);
        /** 老师所加入教师团队课程 **/
        List<CourseTeachingTeam> courseTeachingTeams = courseTeachingTeamRepository.findByTeacherIdOrderByCourseId(teacherId);
        /**加入教师团队课程筛选开课**/
        List<String> termCourseIds = courseTeachers
                .stream()
                .map(CourseTeacherNewVo::getCourseId)
                .collect(Collectors.toList());

        List<CourseTeacherNewVo> preparationCourseTeachers = courseTeachingTeams
                .stream()
                .filter(courseTeachingTeam
                        -> !termCourseIds.contains(courseTeachingTeam.getCourse().getId()) && courseTeachingTeam.getCourse().isUseState())
                .map(courseTeachingTeam
                        -> new CourseTeacherNewVo() {{
                    this.setCourseId(courseTeachingTeam.getCourse().getId());
                    this.setCourseCode(courseTeachingTeam.getCourse().getCourseCode());
                    this.setCourseName(courseTeachingTeam.getCourse().getCourseName());
                }})
                .collect(Collectors.toList());

        courseTeachers.addAll(preparationCourseTeachers);
        return courseTeachers;
    }

    public List<CourseTeacherNewVo> getCourseTeacherResource(String teacherId, Term term) {
        Map<String, Object> paramMap = new HashMap<>();
        String querySql = "SELECT " +
                " tc.id AS courseId, " +
                " tc.course_code as courseCode, " +
                " tc.course_name AS courseName , " +
                " tct.course_category_id AS courseCategoryId ," +
                " tcc.course_category_name AS courseCategoryName, " +
                " tctd.course_type_id AS courseType," +
                " tct.source AS courseSource " +
                " FROM tb_course tc " +
                " INNER JOIN tb_course_table tct ON tct.course_id = tc.id " +
                " INNER JOIN tb_course_table_detail tctd ON tctd.course_table_id = tct.id " +
                " INNER JOIN tb_course_table_detail_teacher tctdt ON tctdt.course_table_detail_id = tctd.id " +
                " INNER JOIN tb_course_category tcc on tct.course_category_id =tcc.id " +
                " WHERE tc.use_state=1 and teacher_id ='" + teacherId + "'" +
                " and tct.term = :term" +
                " and tct.school_year = :schoolYear " +
                " and tc.use_state = 1 ";
        paramMap.put("term", term.getTerm().getIndex());
        paramMap.put("schoolYear", term.getSchoolYear());
        querySql += " GROUP BY  tctdt.teacher_id, tc.id ";
        Query queryData = entityManager.createNativeQuery(querySql);
        paramMap.forEach(queryData::setParameter);
        queryData.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseTeacherNewVo.class));
        return (List<CourseTeacherNewVo>) queryData.getResultList();
    }

    public ResponseEntity<Void> getUpdateClassTestPaperNums(List<String> classTestPaperIds) {
        courseResourceRepository.updateQuestionNum(classTestPaperIds);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }


    @Async
    public Result exportPreparationCourseBag(String collegeId, String courseCodeOrName, String userId, String userName) {
        List<College> collegeList = new ArrayList<>();
        if (!collegeId.equals("0")) {
            collegeList.add(collegeRepository.findById(collegeId).orElse(null));
        } else {
            collegeList = collegeRepository.findAll();
        }
        for (College college : collegeList) {
            List<Course> courseList = courseRepository.findAll(
                    CourseSpecification.courseNameOrCodeAndCollegeSpecification(courseCodeOrName, Arrays.asList(college.getId())));
            String courseIds = courseList.stream().map(Course::getId).collect(joining(","));
            String courseNames = courseList.stream().map(Course::getCourseName).collect(joining(","));
            Date now = new Date();
            DownloadRecord downloadRecord = new DownloadRecord();
            downloadRecord.setPackType(1);
            downloadRecord.setPackUserId(userId);
            downloadRecord.setCreateTime(now);
            downloadRecord.setCreatorId(userId);
            downloadRecord.setCreatorName(userName);
            downloadRecord.setModifyTime(now);
            downloadRecord.setModifierId(userId);
            downloadRecord.setModifierName(userName);
            downloadRecord.setCollegeId(college.getId());
            downloadRecord.setCollegeName(college.getCollegeName());
            downloadRecord.setCourseId(courseIds);
            downloadRecord.setCourseName(courseNames);
            downloadRecord.setPackStatus(DowmloadRecordStatus.PACK_ING);
            downloadRecord.setPackUserId(userId);
            downloadRecordRepository.saveAndFlush(downloadRecord);
        }
        for (College college : collegeList) {
            List<Course> courseList = courseRepository.findAll(
                    CourseSpecification.courseNameOrCodeAndCollegeSpecification(courseCodeOrName, Arrays.asList(college.getId())));
            String courseIds = courseList.stream().map(Course::getId).collect(joining(","));
            List<DownloadRecord> downloadRecordList = downloadRecordRepository
                    .findByCreatorIdAndPackTypeAndCourseIdAndCollegeIdOrderByCreateTimeDesc(userId, 1, courseIds, college.getId());
            DownloadRecord downloadRecord = downloadRecordList.get(0);
            List<CourseStructure> courseStructureList = courseStructureRepository.findAll(
                    CourseSpecification.courseNameOrCodeSpecification(courseCodeOrName, college.getId()));
            List<CourseResource> courseResourceList = new ArrayList<>();
            courseStructureList.stream().filter(item -> item.getStructureStatus().equals(StructureStatus.NORMAL))
                    .forEach(courseStructure -> {
                        if (CollectionUtils.isNotEmpty(courseStructure.getCourseResources())) {
                            courseResourceList.addAll(courseStructure.getCourseResources());
                        }
                    });
            Set<FileInfoVo> fileInfoVos = new HashSet<>();
            courseResourceList.stream().filter(courseResource ->
                            courseResource.getResourceStatus().equals(ResourceStatus.NORMAL) && (
                                    courseResource.getResourceType().equals(ResourceType.IMAGE)
                                            || courseResource.getResourceType().equals(ResourceType.TEACHING_COURSE_WARE)
                                            || courseResource.getResourceType().equals(ResourceType.MICRO_VIDEO)))
                    .forEach(courseResource -> {
                        CourseResourceFile courseResourceFile = null;
                        if (StringUtils.isNotEmpty(courseResource.getResourceDetailId())) {
                            courseResourceFile =
                                    courseResourceFileRepository.findById(courseResource.getResourceDetailId()).orElse(null);
                        }
                        if (ObjectUtils.isNotEmpty(courseResourceFile)) {
                            FileInfoVo fileInfoVo = new FileInfoVo();
                            fileInfoVo.setFileSaveName(courseResourceFile.getFileSavedName());
                            fileInfoVo.setFilePath(courseResourceFile.getFilePath());
                            fileInfoVo.setFileType(courseResourceFile.getFileType());
                            fileInfoVos.add(fileInfoVo);
                        }
                    });
            Calendar nowDate = Calendar.getInstance();
            String fileName = college.getCollegeName() + "(" + college.getCollegeCode() + ")" + "-" + "备课包素材" + nowDate.get(Calendar.YEAR)
                    + (nowDate.get(Calendar.MONTH) + 1) + nowDate.get(Calendar.DAY_OF_MONTH) + nowDate.get(Calendar.HOUR_OF_DAY)
                    + nowDate.get(Calendar.MINUTE)
                    + nowDate.get(Calendar.SECOND) + nowDate.getTimeInMillis();

            buildFileInfo(downloadRecord, new ArrayList<>(fileInfoVos), fileName);
        }
        return Result.success();
    }

    private void buildFileInfo(DownloadRecord downloadRecord, List<FileInfoVo> fileInfoVos, String fileName) {
        if (CollectionUtils.isNotEmpty(fileInfoVos)) {
            downloadRecord = downloadRecordRepository.saveAndFlush(downloadRecord);
            UploadFileMessage uploadFileMessage = saveFile(fileInfoVos, fileName);
            if (ObjectUtils.isNotEmpty(uploadFileMessage)) {
                downloadRecord.setFilePath(uploadFileMessage.getPhysicalPath());
                downloadRecord.setFileSavedName(uploadFileMessage.getUploadedFileName());
                downloadRecord.setFileRealName(uploadFileMessage.getFileName());
                downloadRecord.setFileSize(Long.valueOf(uploadFileMessage.getFileSize()));
                downloadRecord.setPackStatus(DowmloadRecordStatus.PACK_END);
                downloadRecord.setOuterIp(uploadFileMessage.getOuterNetFilePath());
                downloadRecord.setInnerIp(uploadFileMessage.getIntranetFilePath());
                downloadRecord.setFileType(uploadFileMessage.getFileType());
                downloadRecordRepository.saveAndFlush(downloadRecord);
            } else {
                downloadRecord.setPackStatus(DowmloadRecordStatus.PACK_FAIL);
                LOGGER.info("文件保存返回为空");
                downloadRecordRepository.saveAndFlush(downloadRecord);
            }
        } else {
            downloadRecordRepository.delete(downloadRecord);
        }
    }

    public UploadFileMessage saveFile(List<FileInfoVo> fileInfoVo, String fileName) {
        RestTemplate restTemplate = new RestTemplate();
        String url = fileManagementApi + SAVE_FILE_TO_ZIP + "?validCode=" + Md5Utils.md5(signKey) + "&fileName=" + fileName;
        try {

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            HttpEntity<List<FileInfoVo>> httpEntity = new HttpEntity<>(fileInfoVo, headers);
            ResponseEntity<UploadFileMessage> responseEntity = restTemplate.exchange(url, HttpMethod.POST,
                    httpEntity, new ParameterizedTypeReference<UploadFileMessage>() {
                    });
            return responseEntity.getBody();
        } catch (Exception e) {
            log.error("saveFile->{}", e);
            return null;
        }
    }


    public List<PreparationPackageListVo> getPreparationList(String userId, String userName) {
        List<DownloadRecord> downloadRecords = downloadRecordRepository.findByCreatorIdAndPackType(userId, 1);
        List<PreparationPackageListVo> preparationPackageListVos = new ArrayList<>();
        List<PreparationPackageListVo> finalPreparationPackageListVos = preparationPackageListVos;
        downloadRecords.forEach(downloadRecord -> {
            PreparationPackageListVo preparationPackageListVo = new PreparationPackageListVo();
            preparationPackageListVo.setTaskStatus(String.valueOf(downloadRecord.getPackStatus().getValue()));
            preparationPackageListVo.setPhysicalPath(downloadRecord.getFilePath());
            preparationPackageListVo.setIntranetFilePath(downloadRecord.getInnerIp());
            preparationPackageListVo.setOuterNetFilePath(downloadRecord.getOuterIp());
            if (downloadRecord.getFileSize() != null) {
                preparationPackageListVo.setFileSize(getPrintSize(downloadRecord.getFileSize()));
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            preparationPackageListVo.setStartTime(simpleDateFormat.format(downloadRecord.getModifyTime()));
            preparationPackageListVo.setCollegeId(downloadRecord.getCollegeId());
            preparationPackageListVo.setCollegeName(downloadRecord.getCollegeName());
            preparationPackageListVo.setTaskType("导出素材");
            finalPreparationPackageListVos.add(preparationPackageListVo);
        });
        preparationPackageListVos = finalPreparationPackageListVos;
        preparationPackageListVos = preparationPackageListVos.stream().sorted(
                Comparator.comparing(PreparationPackageListVo::getStartTime).reversed()).collect(toList());
        return preparationPackageListVos;
    }

    public String getPrintSize(long size) {
        final int company = 1024, hundred = 100;
        if (size < company) {
            return String.valueOf(size) + "B";
        } else {
            size = size / company;
        }
        if (size < company) {
            return String.valueOf(size) + "KB";
        } else {
            size = size / company;
        }
        if (size < company) {
            size = size * hundred;
            return String.valueOf((size / hundred)) + "."
                    + String.valueOf((size % hundred)) + "MB";
        } else {
            size = size * hundred / company;
            return String.valueOf((size / hundred)) + "."
                    + String.valueOf((size % hundred)) + "GB";
        }
    }

    public void batchUpdateClassTestPaperNum(List<ClassTestPaperResult> classTestPaperResultList) {
        if (CollectionUtils.isNotEmpty(classTestPaperResultList)) {
            classTestPaperResultList.forEach(classTestPaperResult ->
                    courseResourceRepository.updateQuestionNum(classTestPaperResult.getClassTestPaperId(), new Date(),
                            classTestPaperResult.getQuestionNum()));
        }
    }
}
