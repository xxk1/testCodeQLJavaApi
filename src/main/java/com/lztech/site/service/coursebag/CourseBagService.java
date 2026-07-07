package com.lztech.site.service.coursebag;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.lztech.domain.model.college.College;
import com.lztech.domain.model.course.*;
import com.lztech.domain.model.course.enums.*;
import com.lztech.domain.model.coursecategory.CourseCategory;
import com.lztech.domain.model.downloadrecord.DownloadRecord;
import com.lztech.domain.model.downloadrecord.enums.DowmloadRecordStatus;
import com.lztech.persistence.repositories.college.CollegeRepository;
import com.lztech.persistence.repositories.course.*;
import com.lztech.persistence.repositories.coursecategory.CourseCategoryRepository;
import com.lztech.persistence.repositories.courseknowledgestructure.CourseKnowledgeStructureRepository;
import com.lztech.persistence.repositories.courseversion.CourseVersionRepository;
import com.lztech.persistence.repositories.downloadrecord.DownloadRecordRepository;
import com.lztech.site.constants.Result;
import com.lztech.site.constants.ResultStatus;
import com.lztech.site.resource.coursebag.CoursePackageListVo;
import com.lztech.site.resource.coursebag.FileInfoVo;
import com.lztech.site.resource.coursebag.UploadFileMessage;
import com.lztech.site.service.courseconstruction.CourseMaterialService;
import com.lztech.site.service.courseconstruction.CourseVersionService;
import com.lztech.site.until.Md5Utils;
import com.lztech.site.viewmodel.coursemanagement.CourseIntroductionResource;
import com.lztech.site.viewmodel.coursemanagement.CourseMaterialResource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static com.lztech.site.config.Access.signKey;
import static com.lztech.site.constants.ConstantWebApi.*;
import static com.lztech.site.constants.ConstantWebApi.POST_FILE_BY_PROJECTNAME;


@Service
public class CourseBagService {
    @Value("${uploadPath}")
    private  String uploadPath;
    @Autowired
    private CourseMaterialService courseMaterialService;
    @Autowired
    private CourseTeachingTeamRepository courseTeachingTeamRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseKnowledgeStructureRepository courseKnowledgeStructureRepository;
    @Autowired
    private CourseStructureRepository courseStructureRepository;
    @Autowired
    private CourseMaterialRepository courseMaterialRepository;
    @Autowired
    private CourseResourceFileRepository courseResourceFileRepository;
    @Autowired
    private CourseResourceRepository courseResourceRepository;
    @Autowired
    private CourseVersionRepository courseVersionRepository;
    @Autowired
    private CollegeRepository collegeRepository;
    @Autowired
    private CourseVersionService courseVersionService;
    @Autowired
    private CourseIntroductionRepository courseIntroductionRepository;
    @Autowired
    private DownloadRecordRepository downloadRecordRepository;
    @Autowired
    private CourseCategoryRepository courseCategoryRepository;
    @Value("${request.address.fileManagementApi}")
    private String fileManagementApi;

    private static final Logger  LOGGER = LoggerFactory.getLogger(CourseBagService.class);


    public Result ecordCourseBag(String userId,String userName,String courseId,String versionId){
        Date now = new Date();
        Optional<CourseVersion> optionalCourseVersion = courseVersionRepository.findById(versionId);
        CourseVersion courseVersion = optionalCourseVersion.get();
        Course course = courseRepository.findByIdAndUseState(courseId,true);
        List<DowmloadRecordStatus> dowmloadRecordStatuses = new ArrayList<>();
        dowmloadRecordStatuses.add(DowmloadRecordStatus.PACK_FAIL);
        dowmloadRecordStatuses.add(DowmloadRecordStatus.PACK_END);
        dowmloadRecordStatuses.add(DowmloadRecordStatus.PACK_ING);
        DownloadRecord  downloadRecord =
                downloadRecordRepository.findByCreatorIdAndPackTypeAndCourseIdAndVersionSerialNumberAndPackStatusIn(
                        userId,0,courseId,courseVersion.getVersionSerialNumber(),dowmloadRecordStatuses);
        long defoultSize = 0;
        if(ObjectUtils.isEmpty(downloadRecord)){
            downloadRecord = new DownloadRecord();
            downloadRecord.setPackType(0);
            downloadRecord.setPackStatus(DowmloadRecordStatus.PACK_ING);
            downloadRecord.setCourseId(courseId);
            downloadRecord.setCourseName(course.getCourseName());
            downloadRecord.setVersionId(versionId);
            downloadRecord.setVersionSerialNumber(courseVersion.getVersionSerialNumber());
            downloadRecord.setPackUserId(userId);
            downloadRecord.setCreateTime(now);
            downloadRecord.setCreatorId(userId);
            downloadRecord.setCreatorName(userName);
            downloadRecord.setModifyTime(now);
            downloadRecord.setModifierId(userId);
            downloadRecord.setModifierName(userName);
            downloadRecord.setFileSize(defoultSize);
        }else {
            downloadRecord.setFileSize(defoultSize);
            downloadRecord.setModifyTime(now);
            downloadRecord.setPackStatus(DowmloadRecordStatus.PACK_ING);
        }

        if(ObjectUtils.isEmpty(course)){
            LOGGER.error("不存在此课程");
            return Result.error("不存在此课程");
        }
        downloadRecordRepository.saveAndFlush(downloadRecord);
        return Result.success();
    }

    @Transactional
    @Async
    public synchronized Result exportCourseBag(String userId,String courseId,String versionId) throws IOException {
        Optional<CourseVersion> optionalCourseVersion = courseVersionRepository.findById(versionId);
        CourseVersion courseVersion = optionalCourseVersion.get();
        Course course = courseRepository.findByIdAndUseState(courseId,true);
        List<DowmloadRecordStatus> dowmloadRecordStatuses = new ArrayList<>();
        dowmloadRecordStatuses.add(DowmloadRecordStatus.PACK_FAIL);
        dowmloadRecordStatuses.add(DowmloadRecordStatus.PACK_END);
        dowmloadRecordStatuses.add(DowmloadRecordStatus.PACK_ING);
        DownloadRecord  downloadRecord =
                downloadRecordRepository.findByCreatorIdAndPackTypeAndCourseIdAndVersionSerialNumberAndPackStatusIn(
                        userId,0,courseId,courseVersion.getVersionSerialNumber(),dowmloadRecordStatuses);


        if(ObjectUtils.isEmpty(course)){
            downloadRecord.setPackStatus(DowmloadRecordStatus.PACK_FAIL);
            downloadRecordRepository.saveAndFlush(downloadRecord);
            LOGGER.error("不存在此课程");
            return Result.error("不存在此课程");
        }
        LOGGER.info("课程包打包开始->"+course.getCourseName()+"版本"+courseVersion.getVersionSerialNumber());
        String diskDir = uploadPath+"\\"+"teachingcenterteacher_web"+ "\\" +"courseBag";
        String filename = course.getCourseName()+
                "("+course.getCourseCode()+")"+"版本"+courseVersion.getVersionSerialNumber()+"-课程包";
        String filePath  = diskDir + "\\" +course.getCourseName()+
                "("+course.getCourseCode()+")"+"版本"+courseVersion.getVersionSerialNumber()+"-课程包.xls";
        File createFile = new File(diskDir);
        if (!createFile.exists()) {
            createFile.mkdirs();
        }
        //压缩输出流
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        try {
            HSSFWorkbook wb = new HSSFWorkbook();
            //课程信息
            createCourseInfo(wb,courseId,versionId);
            //课程简介
            createCourseBrief(wb,courseId,versionId);
            //课程团队
            createCourseTeam(wb,courseId,versionId);
            //知识结构
            createKonwlwdge(wb,courseId,versionId);
            //教学结构
            createStructure(wb,courseId,versionId);
            //考核方案
            createAssessment(wb,courseId,versionId);
            List<FileInfoVo> fileInfoVos = createResourceFiles(courseId,versionId);

            wb.write(fileOutputStream);
            fileOutputStream.flush();
            String fileInfo = "[]";
            if(CollectionUtils.isNotEmpty(fileInfoVos)){
                fileInfo = JSONObject.toJSONString(fileInfoVos);
            }
            UploadFileMessage uploadFileMessage = saveCourseBagFile(fileInfo,filename,filePath);
            LOGGER.info("打包文件大小"+uploadFileMessage.getFileSize());
            downloadRecord.setInnerIp(uploadFileMessage.getIntranetFilePath());
            downloadRecord.setOuterIp(uploadFileMessage.getOuterNetFilePath());
            downloadRecord.setFilePath(uploadFileMessage.getPhysicalPath());
            downloadRecord.setFileSize(Long.valueOf(uploadFileMessage.getFileSize()));
            downloadRecord.setFileType(uploadFileMessage.getFileType());
            downloadRecord.setFileRealName(uploadFileMessage.getFileName());
            downloadRecord.setFileSavedName(uploadFileMessage.getUploadedFileName());

        } catch (Exception e) {
            downloadRecord.setPackStatus(DowmloadRecordStatus.PACK_ING);
            downloadRecordRepository.saveAndFlush(downloadRecord);
            LOGGER.error("exportCourseBag->",e);
            return Result.error("打包失败");
        }finally {
            if(fileOutputStream!=null){
                fileOutputStream.close();
            }
        }
        downloadRecord.setPackStatus(DowmloadRecordStatus.PACK_END);
        downloadRecordRepository.saveAndFlush(downloadRecord);
        LOGGER.info("课程包打包结束->"+courseId+course.getCourseName());
        return Result.success();
    }

    private void createCourseInfo(HSSFWorkbook wb, String courseId,String versionId) {
        HSSFSheet sheet = wb.createSheet("课程信息");
        Course course = courseRepository.findByIdAndUseState(courseId,true);
        College college = course.getCollege();
        if(ObjectUtils.isNotEmpty(course)&&ObjectUtils.isNotEmpty(college)){
            Course courseCopy = new Course();
            BeanUtil.copyProperties(course,courseCopy,"college","courseStructures",
                    "courseTeachingTeams","courseIntroductionList","courseCompletionList");
            HSSFRow row = sheet.createRow(0);
            HSSFCell cellCourse = row.createCell(0);
            String jsonString = JSONObject.toJSONString(courseCopy);
            buildCellValue(jsonString,cellCourse);
            if(ObjectUtils.isNotEmpty(college)){
                College collegeCopy = new College();
                BeanUtil.copyProperties(college,collegeCopy,"administrativeClasses");
                HSSFRow rowCollege = sheet.createRow(1);
                HSSFCell cellCourseCollege = rowCollege.createCell(0);
                String jsonStringCollege = JSONObject.toJSONString(collegeCopy);
                buildCellValue(jsonStringCollege,cellCourseCollege);
            }

            Optional<CourseVersion> courseVersion = courseVersionRepository.findById(versionId);
            CourseVersion courseVersionCopy = new CourseVersion();
            BeanUtil.copyProperties(courseVersion.get(),courseVersionCopy,"course");
            String jsonVersionString = JSONObject.toJSONString(courseVersionCopy);
            final int two = 2;
            HSSFRow rowVersion= sheet.createRow(two);
            HSSFCell cellCourseVersion = rowVersion.createCell(0);
            buildCellValue(jsonVersionString,cellCourseVersion);
        }
    }

    private void createAssessment(HSSFWorkbook wb, String courseId, String versionId) {
        HSSFSheet sheet = wb.createSheet("考核方案" );
        CourseMaterial courseMaterial = courseMaterialRepository.findByCourseIdAndVersionId(courseId, versionId);
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        CourseMaterial courseMaterialCopy = new CourseMaterial();
        BeanUtil.copyProperties(courseMaterial,courseMaterialCopy,"course","courseVersion","courseCategory");
        String teamJsonString = JSONObject.toJSONString(courseMaterialCopy);
        buildCellValue(teamJsonString, cell);
        final int one = 1,two =2,three = 3,four = 4;
        HSSFCell cellCourseId = row.createCell(one);
        buildCellValue(courseId, cellCourseId);
        HSSFCell cellVersionId = row.createCell(two);
        buildCellValue(versionId, cellVersionId);
        HSSFCell cellCourseCategory = row.createCell(three);
        if(ObjectUtils.isNotEmpty(courseMaterial)){
            buildCellValue(courseMaterial.getCourseCategory()==null?""
                    :courseMaterial.getCourseCategory().getId().toString(), cellCourseCategory);
        }else{
            buildCellValue("", cellCourseCategory);
        }

    }

    private void createStructure(HSSFWorkbook wb, String courseId, String versionId) {
        HSSFSheet sheet = wb.createSheet("教学内容" );
        List<CourseStructure> courseStructures = courseStructureRepository.findByCourseIdAndCourseVersionIdAndStructureStatus
                (courseId, versionId, StructureStatus.NORMAL);
        List<CourseResource> courseResourceList = new ArrayList<>();
        courseStructures.stream().filter(item-> CollectionUtils.isNotEmpty(item.getCourseResources()))
                .forEach(item->{item.getCourseResources().stream().filter(courseResource -> courseResource.getIsPublic()&&
                            courseResource.getResourceStatus().equals(ResourceStatus.NORMAL)).forEach(courseResource -> {
                        courseResourceList.add(courseResource);
                    });
        });
        for (int i = 0;i<courseStructures.size();i++){
            HSSFRow row = sheet.createRow(i);
            HSSFCell cell = row.createCell(0);
            CourseStructure courseStructure = courseStructures.get(i);
            CourseStructure courseStructureCopy = new CourseStructure();
            BeanUtil.copyProperties(courseStructure,courseStructureCopy,"course","courseVersion","courseResources");
            String teamJsonString = JSONObject.toJSONString(courseStructureCopy);
            buildCellValue(teamJsonString, cell);
            final int one = 1,two =2;
            HSSFCell cellCourseId = row.createCell(one);
            buildCellValue(courseId, cellCourseId);
            HSSFCell cellVersionId = row.createCell(two);
            buildCellValue(versionId, cellVersionId);
        }
        buildResource(wb,courseResourceList);
    }

    public void buildResource(HSSFWorkbook wb, List<CourseResource> courseResourceList){
        HSSFSheet sheet = wb.createSheet("教学内容资源" );
        HSSFSheet sheetFile = wb.createSheet("教学内容资源文件" );
        for (int i = 0;i<courseResourceList.size();i++){
            HSSFRow row = sheet.createRow(i);
            HSSFCell cell = row.createCell(0);
            CourseResource courseResource = courseResourceList.get(i);
            CourseResource courseResourceCopy = new CourseResource();
            BeanUtil.copyProperties(courseResource,courseResourceCopy,"courseStructure");
            String jsonString = JSONObject.toJSONString(courseResourceCopy);
            buildCellValue(jsonString, cell);
            final int one = 1,two = 2;
            HSSFCell cellStructureId = row.createCell(one);
            buildCellValue(courseResourceList.get(i).getCourseStructure().getId(), cellStructureId);
            HSSFCell cellResourceDetailId = row.createCell(two);
            buildCellValue(courseResource.getResourceDetailId(), cellResourceDetailId);
            HSSFRow rowFile = sheetFile.createRow(i);
            HSSFCell cellFile = rowFile.createCell(0);
            String fileId = courseResource.getResourceDetailId();
            if(courseResource.getResourceType().equals(ResourceType.IMAGE)||
                    courseResource.getResourceType().equals(ResourceType.MICRO_VIDEO)||
                    courseResource.getResourceType().equals(ResourceType.TEACHING_COURSE_WARE)){
                CourseResourceFile courseResourceFile = courseResourceFileRepository.findById(fileId).orElse(null);
                buildCellValue(courseResource.getResourceDetailId()+"-"+courseResourceFile.getFileSavedName(), cellFile);
            }
        }
    }

    public List<FileInfoVo> createResourceFiles(String courseId,String versionId){
        List<FileInfoVo> fileInfoVos = new ArrayList<>();
        List<CourseStructure> courseStructures = courseStructureRepository.findByCourseIdAndCourseVersionIdAndStructureStatus
                (courseId, versionId, StructureStatus.NORMAL);
        List<CourseResource> courseResourceList = new ArrayList<>();
        courseStructures.stream().filter(item->
                CollectionUtils.isNotEmpty(item.getCourseResources())).forEach(item->{
            item.getCourseResources().stream().filter(courseResource -> courseResource.getIsPublic()&&
                    courseResource.getResourceStatus().equals(ResourceStatus.NORMAL)).forEach(courseResource -> {
                courseResourceList.add(courseResource);
            });
        });
        for (CourseResource courseResource:courseResourceList){
            CourseResourceFile courseResourceFile = courseResourceFileRepository
                    .findByIdAndResourceStatus(courseResource.getResourceDetailId(), ResourceStatus.NORMAL);
            if(ObjectUtils.isNotEmpty(courseResourceFile)) {
                try {
                    if(courseResourceFile.getIsPublic()){
                        FileInfoVo fileInfoVo = new FileInfoVo();
                        fileInfoVo.setFileType(courseResourceFile.getFileType());
                        fileInfoVo.setFilePath(courseResourceFile.getFilePath());
                        fileInfoVo.setFileSaveName(courseResourceFile.getFileSavedName());
                        fileInfoVos.add(fileInfoVo);
                    }
                }catch (Exception e){
                    LOGGER.error("createResourceFiles->",e);
                }
            }
        }
        return fileInfoVos;
    }

    private void createKonwlwdge(HSSFWorkbook wb, String courseId, String versionId) {
        HSSFSheet sheet = wb.createSheet("知识结构" );
        List<CourseKnowledgeStructure> courseKnowledgeStructureList =
                courseKnowledgeStructureRepository.findByCourseIdAndVersionId(courseId, versionId);
        for (int i = 0;i<courseKnowledgeStructureList.size();i++){
            HSSFRow row = sheet.createRow(i);
            HSSFCell cell = row.createCell(0);
            CourseKnowledgeStructure courseKnowledgeStructure = courseKnowledgeStructureList.get(i);
            CourseKnowledgeStructure courseKnowledgeStructureCopy = new CourseKnowledgeStructure();
            BeanUtil.copyProperties(courseKnowledgeStructure,courseKnowledgeStructureCopy,"course","courseVersion");
            String teamHsonString = JSONObject.toJSONString(courseKnowledgeStructureCopy);
            buildCellValue(teamHsonString, cell);
            final int one = 1,two =2;
            HSSFCell cellCourseId = row.createCell(one);
            buildCellValue(courseId, cellCourseId);
            HSSFCell cellVersionId = row.createCell(two);
            buildCellValue(versionId, cellVersionId);
        }
    }

    private void createCourseTeam(HSSFWorkbook wb, String courseId, String versionId) {
        HSSFSheet sheet = wb.createSheet("课程团队" );
        List<CourseTeachingTeam> courseTeachingTeamList = courseTeachingTeamRepository.findByCourseIdAndCourseVersionId(courseId, versionId);
        for (int i = 0;i<courseTeachingTeamList.size();i++){
            HSSFRow row = sheet.createRow(i);
            HSSFCell cell = row.createCell(0);
            CourseTeachingTeam courseTeachingTeam = courseTeachingTeamList.get(i);
            CourseTeachingTeam courseTeachingTeamCopy = new CourseTeachingTeam();
            BeanUtil.copyProperties(courseTeachingTeam,courseTeachingTeamCopy,"course","courseVersion");
            String teamHsonString = JSONObject.toJSONString(courseTeachingTeamCopy);
            buildCellValue(teamHsonString, cell);
            final int one = 1,two =2;
            HSSFCell cellCourseId = row.createCell(one);
            buildCellValue(courseId, cellCourseId);
            HSSFCell cellVersionId = row.createCell(two);
            buildCellValue(versionId, cellVersionId);
        }
    }

    public void createCourseBrief(HSSFWorkbook wb,String courseId,String versionId){
        Result result = courseMaterialService.getCourseIdMaterialModuleInfo(courseId, 0, versionId);
        if(result.getStatus().equals(ResultStatus.SUCCESS)){
            CourseMaterialResource courseMaterialResource = (CourseMaterialResource) result.getData();
            String[] rowName = {"课程名称","课程编号","授课对象","课程内容","教材","参考教材","课程类别","开课学院","课程学时"};
            HSSFSheet sheet = wb.createSheet("课程简介" );
            HSSFRow row = sheet.createRow(0);
            setSheetHeader(row,rowName);
            HSSFRow essentialInformation = sheet.createRow(1);
            buildCourseBriefData(essentialInformation,courseMaterialResource,rowName,courseId);
            buildCourseIntroduce(sheet,courseMaterialResource,courseId,versionId);
            buildCourseIntroduceInfo(courseId,versionId,wb);
        }
    }

    private void buildCourseIntroduceInfo(String courseId, String versionId, HSSFWorkbook wb) {
        HSSFSheet sheet = wb.createSheet("课程介绍" );
        List<CourseIntroduction> courseIntroductionList =
                courseIntroductionRepository.findByCourseIdAndCourseVersionId(courseId, versionId);
        for (int i = 0;i<courseIntroductionList.size();i++){
            HSSFRow row = sheet.createRow(i);
            CourseIntroduction courseIntroductionCopy = new CourseIntroduction();
            BeanUtil.copyProperties(courseIntroductionList.get(i),courseIntroductionCopy,"course","courseVersion");
            String jsonStringIntroduction = JSONObject.toJSONString(courseIntroductionCopy);
            HSSFCell cell = row.createCell(0);
            buildCellValue(jsonStringIntroduction, cell);
        }
    }

    private void buildCourseIntroduce(HSSFSheet sheet, CourseMaterialResource courseMaterialResource,
                                      String courseId,String versionId) {
        List<CourseIntroductionResource> courseIntroductionList = courseMaterialResource.getCourseIntroductionList();
        final int two = 2,three = 3;
        for (int i =0;i<courseIntroductionList.size();i++){
            CourseIntroductionResource courseIntroductionResource = courseIntroductionList.get(i);
            HSSFRow row = sheet.createRow(i+two);
            HSSFCell cell = row.createCell(0);
            buildCellValue(courseIntroductionResource.getClassificationName(),cell);
            HSSFCell cellContent = row.createCell(1);
            buildCellValue(courseIntroductionResource.getClassificationContent(),cellContent);
        }
        CourseMaterial courseMaterial = courseMaterialRepository.findByCourseIdAndVersionId(courseId, versionId);
        CourseCategory courseCategory = courseMaterial.getCourseCategory();
        CourseMaterial courseMaterialCopy = new CourseMaterial();
        BeanUtil.copyProperties(courseMaterial,courseMaterialCopy,"courseCategory","courseVersion","course");
        String jsonString = JSONObject.toJSONString(courseMaterialCopy);
        HSSFRow copy = sheet.createRow(courseIntroductionList.size()+two);
        HSSFCell cellCopy = copy.createCell(0);
        buildCellValue(jsonString, cellCopy);
        CourseCategory courseCategoryCopy = new CourseCategory();
        BeanUtil.copyProperties(courseCategory,courseCategoryCopy,"courseTableList");
        String jsonStringCategory = JSONObject.toJSONString(courseCategoryCopy);
        HSSFRow copyCategory = sheet.createRow(courseIntroductionList.size()+three);
        HSSFCell cellCopyCategory = copyCategory.createCell(0);
        buildCellValue(jsonStringCategory, cellCopyCategory);
    }

    public void setSheetHeader(HSSFRow row,String[] sheetHeader){
        for (int i = 0;i<sheetHeader.length;i++){
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(sheetHeader[i]);
        }
    }
    public void buildCourseBriefData(HSSFRow row,CourseMaterialResource courseMaterialResource,String[] rowName,
                                     String courseId){
        for (int i = 0;i<rowName.length;i++){
            String header = rowName[i];
            switch (header){
                case "课程名称":
                    HSSFCell cellCourseName = row.createCell(i);
                    buildCellValue(courseMaterialResource.getCourseName(), cellCourseName);
                    break;
                case "课程编号":
                    HSSFCell cellCourseNo = row.createCell(i);
                    buildCellValue(courseId, cellCourseNo);
                    break;
                case "授课对象":
                    HSSFCell cellCoursePrelect  = row.createCell(i);
                    buildCellValue(courseMaterialResource.getTeachingUserType(), cellCoursePrelect);
                    break;
                case "课程内容":
                    HSSFCell cellCourseContent  = row.createCell(i);
                    buildCellValue(courseMaterialResource.getCourseContent(), cellCourseContent);
                    break;
                case "教材":
                    HSSFCell cellCourseMaterial  = row.createCell(i);
                    buildCellValue(courseMaterialResource.getTeachingMaterial(), cellCourseMaterial);
                    break;
                case "参考教材":
                    HSSFCell cellCourseReferenceMaterial  = row.createCell(i);
                    buildCellValue(courseMaterialResource.getReferenceMaterials(), cellCourseReferenceMaterial);
                    break;
                case "课程类别":
                    HSSFCell cellCourseType  = row.createCell(i);
                    buildCellValue(courseMaterialResource.getCourseCategoryId()==null?""
                            :courseMaterialResource.getCourseCategoryId()+"-"
                            +courseMaterialResource.getCourseCategoryName(), cellCourseType);
                    break;
                case "开课学院":
                    HSSFCell cellCourseCollege  = row.createCell(i);
                    buildCellValue(courseMaterialResource.getCollegeId()==null?"":courseMaterialResource.getCollegeId()+"-"+
                            courseMaterialResource.getCollegeName(), cellCourseCollege);
                    break;
                case "课程学时":
                    HSSFCell cellCourseHours  = row.createCell(i);
                    buildCellValue(courseMaterialResource.getClassHours()==null?""
                            :courseMaterialResource.getClassHours().toString(), cellCourseHours);
                    break;
                default:
                    LOGGER.error("不符合的格式");
                    break;
            }
        }
    }

    private void buildCellValue(String content, HSSFCell cell) {
        if(StringUtils.isNotEmpty(content)){
            cell.setCellValue(content);
        }else{
            cell.setCellValue("");
        }
    }


    @Transactional
    public synchronized Result importCourseBag(String versionId,String userName,String userId,
                                  MultipartFile multipartFile, String uploadType) {
        File file = null;
        if(uploadType.equals("0")){
            try {
                String fileName = multipartFile.getOriginalFilename();
                if(checkFileName(fileName)){
                    return Result.error("导入失败");
                }
                StringBuilder stringBuilder = new StringBuilder(fileName);
                stringBuilder.replace(fileName.lastIndexOf("-")-1,fileName.lastIndexOf("-"),"1");
                fileName = stringBuilder.toString();
                if(!fileName.contains(".zip")){ return Result.error("不支持的文件格式"); }
                String filecatalogue = uploadPath + "\\"+"teachingcenterteacher_web"+ "\\" +"courseBag";
                String filePath = filecatalogue+"\\"+fileName;
                File createFile = new File(filecatalogue);
                if (!createFile.exists()) { createFile.mkdirs(); }
                file  = new File(filePath);
                if(!file.exists()){multipartFile.transferTo(new File(filecatalogue,fileName));}
                else{file.delete();multipartFile.transferTo(new File(filecatalogue,fileName));}
                LOGGER.info("文件大小"+file.length());
                ZipFile zipFile = new ZipFile(file);
                Enumeration<?> zipEnum = zipFile.entries();
                Map<String,Object> result = getResult(userName, userId, file, zipFile, zipEnum);
                filePath = filecatalogue+"\\"+ result.get("fileName");
                if(result.get("error")==null){
                    File copyFile  = new File(filePath);
                    FileInputStream fileInputStream = new FileInputStream(file);
                    buildCopyFile(fileInputStream, copyFile, filePath);
                    DownloadRecord downloadRecord = (DownloadRecord) result.get("downloadRecord");
                    downloadRecord.setPackStatus(DowmloadRecordStatus.UP_END);
                    downloadRecordRepository.saveAndFlush(downloadRecord);
                    LOGGER.info("文件解析完成2"+downloadRecord.getPackStatus().getName());
                    LOGGER.info("上传文件结束");
                }else{
                    return  Result.error(result.get("error"));
                }
                return Result.success();
            }catch (Exception e){ if(file!=null){file.delete();}
                LOGGER.error("importCourseBag->",e);
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); }
        }else{
            LOGGER.info("生成文件开始");
            Optional<CourseVersion> optionalCourseVersion = courseVersionRepository.findById(versionId);
            CourseVersion courseVersion = optionalCourseVersion.get();
            DownloadRecord downloadRecord = getDownLoadInfo(userId, courseVersion);
            try {
                Course course = courseVersion.getCourse();
                course.setUseState(true);
                courseRepository.save(course);
                String filecatalogue = uploadPath + "\\"+"teachingcenterteacher_web"+ "\\" +"courseBag";
                String fileName = course.getCourseName()+
                        "("+course.getCourseCode()+")"+"版本"+courseVersion.getVersionSerialNumber()+"-课程包.zip";
                String zipFilePath  = filecatalogue + "\\" +fileName;
                Result result = buildCourseData(userName, userId, courseVersion, downloadRecord, course, zipFilePath);
                if (result != null) {return result;}
            }catch (Exception e){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                downloadRecord.setModifyTime(new Date());
                downloadRecord.setPackStatus(DowmloadRecordStatus.GENERATE_FAIL);
                courseVersionRepository.saveAndFlush(courseVersion);
                LOGGER.error("importCourseBag->生成课程失败",e);
            }
        }
        return Result.success();
    }

    private boolean checkFileName(String fileName) {
        if(fileName.indexOf("-")<=0||fileName.indexOf("(") <=0){
            return true;
        }
        if(fileName.lastIndexOf("-")==(fileName.length()-1)){
            return true;
        }
        String coursePre = fileName.substring(fileName.lastIndexOf("-")+1);
        if(!coursePre.equals("课程包.zip")){
            return  true;
        }
        String courseNo = fileName.substring(fileName.indexOf("(")+1,fileName.lastIndexOf(")"));
        Pattern pattern = Pattern.compile("[0-9]*");
        return !pattern.matcher(courseNo).matches();
    }

    private Result buildCourseData(String userName, String userId, CourseVersion courseVersion,
                                   DownloadRecord downloadRecord, Course course, String zipFilePath) throws Exception {
        ZipEntry zipEntry;
        String zipFileName;
        ZipFile zipFile = new ZipFile(zipFilePath);
        Enumeration<?> zipEnum = zipFile.entries();
        while (zipEnum.hasMoreElements()){
            zipEntry = (ZipEntry) zipEnum.nextElement();
            zipFileName = zipEntry.getName();
            final int four = 4;
            String suffix = zipFileName.substring(zipFileName.length() - four);
            if(suffix!=null&&".xls".equals(suffix)) {
                long size = zipEntry.getSize();
                if (size > 0) {
                    Result result;
                    InputStream inZip =  zipFile.getInputStream(zipEntry);
                    HSSFWorkbook workBook = new HSSFWorkbook(inZip);
                    createCourseBriefData(userName, userId,workBook, course, courseVersion);
                    result = createCourseTeamData(userName, userId,workBook, course, courseVersion);
                    if(result.getStatus().equals(ResultStatus.ERROR)){
                        return result;
                    }
                    createKonwlwdgeData(userName, userId,workBook, course, courseVersion);
                    createTeachContent(userName, userId,workBook, course, courseVersion,zipFilePath);
                }
            }
        }
        downloadRecord.setModifyTime(new Date());
        downloadRecord.setPackStatus(DowmloadRecordStatus.GENERATE_END);
        courseVersionRepository.saveAndFlush(courseVersion);
        return null;
    }

    private DownloadRecord getDownLoadInfo(String userId, CourseVersion courseVersion) {
        List<DowmloadRecordStatus> dowmloadRecordStatuses = new ArrayList<>();
        dowmloadRecordStatuses.add(DowmloadRecordStatus.UP_FAIL);
        dowmloadRecordStatuses.add(DowmloadRecordStatus.UP_END);
        dowmloadRecordStatuses.add(DowmloadRecordStatus.UP_ING);
        DownloadRecord  downloadRecord =
                downloadRecordRepository.findByCreatorIdAndPackTypeAndCourseIdAndVersionSerialNumberAndPackStatusIn(
                        userId,0, courseVersion.getCourse().getId(), courseVersion.getVersionSerialNumber(),dowmloadRecordStatuses);
        downloadRecord.setModifyTime(new Date());
        downloadRecord.setPackStatus(DowmloadRecordStatus.GENERATE_ING);
        return downloadRecord;
    }

    private void buildFile(MultipartFile multipartFile, File file, String filePath) throws IOException {
        if(!file.exists()){
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            InputStream in2 = multipartFile.getInputStream();
            for(int c=0;(c=in2.read())!=-1;){
                fileOutputStream.write(c);
            }
            fileOutputStream.close();
            in2.close();
        }
    }

    private void buildCopyFile(FileInputStream fileInputStream, File file, String filePath) throws IOException {
        if(!file.exists()){
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            for(int c=0;(c=fileInputStream.read())!=-1;){
                fileOutputStream.write(c);
            }
            fileOutputStream.close();
            fileInputStream.close();
        }
    }

    private Map<String,Object> getResult(String userName, String userId, File file, ZipFile zipFile, Enumeration<?> zipEnum) throws IOException {
        Map<String,Object> result = new HashMap<>();
        String zipFileName;
        ZipEntry zipEntry;
        String fileName = null;
        while(zipEnum.hasMoreElements()){
            zipEntry = (ZipEntry) zipEnum.nextElement();
            zipFileName = zipEntry.getName();
            final int four = 4;
            String suffix = zipFileName.substring(zipFileName.length() - four);
            if(suffix!=null&&".xls".equals(suffix)){
                long size = zipEntry.getSize();
                if(size>0){
                    InputStream inZip =  zipFile.getInputStream(zipEntry);
                    HSSFWorkbook workBook = new HSSFWorkbook(inZip);
                    Map<String,Object> resultMap = buildCourseAndCollegeInfo(userName, userId,workBook);
                    if(resultMap.get("error")!=null){
                        file.delete();
                        result.put("error",resultMap.get("error"));
                        return result;
                    }
                    Course course = (Course) resultMap.get("course");
                    CourseVersion courseVersion = (CourseVersion) resultMap.get("courseVersion");
                    DownloadRecord downloadRecord = (DownloadRecord) resultMap.get("downloadRecord");
                    downloadRecord.setCourseId(course.getId());
                    downloadRecord.setFileSize(file.length());
                    downloadRecord.setPackStatus(DowmloadRecordStatus.UP_ING);

                    downloadRecord = downloadRecordRepository.saveAndFlush(downloadRecord);
                    LOGGER.info("文件解析完成"+downloadRecord.getPackStatus().getName());
                    result.put("downloadRecord",downloadRecord);
                    fileName = course.getCourseName()+"("+course.getCourseCode()+")"+"版本"+courseVersion.getVersionSerialNumber()
                            +"-课程包.zip";

                }
            }
        }
        result.put("fileName",fileName);
        return result;
    }

    private synchronized void createTeachContent(String userName, String userId, HSSFWorkbook workBook,
                                                 Course course, CourseVersion courseVersion, String zipFilePath) throws Exception {
        Date now = new Date();
        HSSFSheet sheet  = workBook.getSheet("教学内容");
        HSSFSheet sheetResourse  = workBook.getSheet("教学内容资源");
        HSSFSheet sheetResourseFile  = workBook.getSheet("教学内容资源文件");
        List<CourseStructure> courseStructureList = new ArrayList<>();
        List<CourseResource> courseResourceList = new ArrayList<>();
        Map<String,CourseResource> courseResourceMap = new HashMap<>();
        for (Row row:sheet){
            String jsonStructure = row.getCell(0).getStringCellValue();
            if(row.getCell(0)!=null&&StringUtils.isNotEmpty(row.getCell(0).getStringCellValue().trim())) {
                CourseStructure courseStructure = JSONObject.parseObject(jsonStructure,CourseStructure.class);
                courseStructure.setCourse(course);
                courseStructure.setCourseVersion(courseVersion);
                courseStructure.setCreateTime(now);
                courseStructure.setCreatorId(userId);
                courseStructure.setCreatorName(userName);
                courseStructureList.add(courseStructure);
            }}
        for (Row row:sheetResourse){
            if(row.getCell(1)!=null&&StringUtils.isNotEmpty(row.getCell(1).getStringCellValue().trim())) {
                String structureId = row.getCell(1).getStringCellValue().trim();
                String jsonResource = row.getCell(0).getStringCellValue().trim();
                CourseResource courseResource =
                        JSONObject.parseObject(jsonResource,CourseResource.class);
                courseResourceMap.put(structureId,courseResource);
            }}
        List<CourseStructure> topFloorCourseStructureList =  courseStructureList.stream().filter(
                item->StringUtils.isBlank(item.getParentId()))
                .collect(Collectors.toList());
        for (CourseStructure courseStructure:topFloorCourseStructureList){
            CourseStructure finalCourseStructure = courseStructure;
            CourseStructure courseStructureResult = courseStructureRepository.saveAndFlush(finalCourseStructure);
            List<CourseStructure> point = new ArrayList<>();
            courseStructureList.stream().filter(item->StringUtils.isNotBlank(item.getParentId())
                    &&item.getParentId().equals(finalCourseStructure.getId())&&
                    item.getStructureStatus().equals(StructureStatus.NORMAL)).forEach(item->{
                item.setCourse(course);
                item.setCourseVersion(courseVersion);
                item.setCreateTime(now);
                item.setCreatorId(userId);
                item.setCreatorName(userName);
                item.setParentId(courseStructureResult.getId());
                point.add(item);
            });
            for (Row row:sheetResourse){
                if(row.getCell(1)!=null&&StringUtils.isNotEmpty(row.getCell(1).getStringCellValue().trim())) {
                    String structureId = row.getCell(1).getStringCellValue().trim();
                    String jsonResource = row.getCell(0).getStringCellValue().trim();
                    CourseResource courseResource =
                            JSONObject.parseObject(jsonResource,CourseResource.class);
                    if(courseStructure.getId().equals(structureId)){
                        courseResource.setCourseStructure(courseStructureResult);
                        courseResource.setSourceType(ResourceSourceType.USER_ADDED);
                        courseResource.setCourseId(courseStructureResult.getCourse().getId());
                        courseResourceList.add(courseResource);
                    }}}
            for (CourseStructure courseStructure1:point){
                CourseStructure courseStructureLeftResult = courseStructureRepository.save(courseStructure1);
                if (courseResourceMap.get(courseStructure1.getId())!=null){
                    CourseResource courseResource = courseResourceMap.get(courseStructure1.getId());
                    courseResource.setCourseStructure(courseStructureLeftResult);
                    courseResource.setSourceType(ResourceSourceType.USER_ADDED);
                    courseResource.setCourseId(courseStructureResult.getCourse().getId());
                    courseResourceList.add(courseResource);
                }}}
        List<String> fileAndDetail = new ArrayList<>();
        for (Row rowFile:sheetResourseFile){
            if(rowFile.getCell(0)!=null&&StringUtils.isNotEmpty(rowFile.getCell(0).getStringCellValue().trim())){
                String fileInfo = rowFile.getCell(0).getStringCellValue().trim();
                fileAndDetail.add(fileInfo);
            }
        }
        courseResourceList.forEach(item->{
            item.setCourseId(course.getId());
            buildResources(userName, userId, courseVersion, zipFilePath, fileAndDetail, item);
        });
    }

    private void buildResources(String userName, String userId, CourseVersion courseVersion, String zipFilePath,
                                List<String> fileAndDetail, CourseResource item) {
        item.setCreateTime(new Date());
        item.setCreatorId(userId);
        item.setCreatorName(userName);
        AtomicReference<String> fileSaveName = new AtomicReference<>("");
        fileAndDetail.forEach(saveName ->{
            String detailId = saveName.substring(0,saveName.indexOf("-"));

            String save = saveName.substring(saveName.indexOf("-")+1,saveName.lastIndexOf("."));
            if(item.getResourceDetailId().equals(detailId)){
                fileSaveName.set(save);
            }
        });
        try {
            extracted(userName, userId, courseVersion, item, fileSaveName.get(), zipFilePath);
        }catch (Exception e){
            LOGGER.error("createTeachContent->",e);
        }
        LOGGER.info("资源版本信息"+ item.getCourseStructure().getCourseVersion().getId());
        courseResourceRepository.saveAndFlush(item);
    }

    private void extracted(String userName, String userId, CourseVersion courseVersion
            , CourseResource item, String saveName,String zipFilePath) throws IOException {
        Date now = new Date();
        ZipEntry zipEntryFile;
        ZipFile zipFile = new ZipFile(zipFilePath);
        Enumeration<?> zipEnum = zipFile.entries();
        while (zipEnum.hasMoreElements()){
            zipEntryFile = (ZipEntry) zipEnum.nextElement();
            String fileSaveName = UUID.randomUUID().toString();
            String zipFileName = zipEntryFile.getName();
            String fildId = zipFileName.substring(0,zipFileName.lastIndexOf("."));
            String fileType = zipFileName.substring(zipFileName.lastIndexOf(".")+1);
            String yearMouth = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMM"));
            String day = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd"));
            String path = uploadPath+"\\" + "teachingcenterteacher_web" + "\\" + yearMouth + "\\" + day;
            String filePath = path + "\\" + fileSaveName + "." + fileType;
            File fileResource = new File(path);
            if(!fileResource.exists()){
                fileResource.mkdirs();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            File file = new File(filePath);
            LOGGER.info("进行压缩"+zipEntryFile.getSize());
            if(fildId.equals(saveName)) {
                final int fileByte = 9000;
                byte[] buffer = new byte[fileByte];
                int len;
                InputStream inZip = zipFile.getInputStream(zipEntryFile);
                while ((len = inZip.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, len);
                }
                LOGGER.info("开始上传文件");
                UploadFileMessage uploadFileMessage = saveFile(file);
                LOGGER.info("文件保存成功并返回文件信息"+uploadFileMessage.getPhysicalPath());
                fileOutputStream.close();
                CourseResourceFile courseResourceFile = new CourseResourceFile();
                courseResourceFile.setFileSavedName(uploadFileMessage.getUploadedFileName());
                courseResourceFile.setFilePath(uploadFileMessage.getPhysicalPath());
                courseResourceFile.setFileSize(Long.valueOf(uploadFileMessage.getFileSize()));
                courseResourceFile.setFileRealName(uploadFileMessage.getFileName());
                courseResourceFile.setFileType(uploadFileMessage.getFileType());
                courseResourceFile.setInnerIp(uploadFileMessage.getIntranetFilePath());
                courseResourceFile.setOuterIp(uploadFileMessage.getOuterNetFilePath());
                courseResourceFile.setIsPublic(true);
                courseResourceFile.setResourceStatus(ResourceStatus.NORMAL);
                courseResourceFile.setVisionNumber(courseVersion.getVersionSerialNumber());
                courseResourceFile.setCreateTime(now);
                courseResourceFile.setCreatorId(userId);
                courseResourceFile.setCreatorName(userName);
                courseResourceFile.setModifierId(userId);
                courseResourceFile.setModifierName(userName);
                courseResourceFile.setModifyTime(now);
                courseResourceFile = courseResourceFileRepository.save(courseResourceFile);
                item.setResourceDetailId(courseResourceFile.getId());
                item.setResourceSize(courseResourceFile.getFileSize());
                item.setIsPublic(true);
                fileResource.delete();
            }
        }
    }

    private void createKonwlwdgeData(String userName, String userId, HSSFWorkbook workBook,
                                     Course course, CourseVersion courseVersion) {
        Date now = new Date();
        HSSFSheet sheet  = workBook.getSheet("知识结构");
        List<CourseKnowledgeStructure> courseKnowledgeStructures = new ArrayList<>();
        for (Row row:sheet){
            if(row.getCell(0)!=null&&StringUtils.isNotEmpty(row.getCell(0).getStringCellValue().trim())){
                String jsonKonwlwdge = row.getCell(0).getStringCellValue().trim();
                CourseKnowledgeStructure courseKnowledgeStructure =
                        JSONObject.parseObject(jsonKonwlwdge,CourseKnowledgeStructure.class);
                courseKnowledgeStructure.setCourse(course);
                courseKnowledgeStructure.setCourseVersion(courseVersion);
                courseKnowledgeStructures.add(courseKnowledgeStructure);
            }
        }
        List<CourseKnowledgeStructure> topFloorCourseKnowledgeStructureList =  courseKnowledgeStructures.stream().filter(
                item->StringUtils.isBlank(item.getParentId())&&item.getKnowledgeStructureType().equals(KnowledgeStructureType.CHAPTER))
                .sorted(Comparator.comparing(CourseKnowledgeStructure::getShowOrder))
                .collect(Collectors.toList());
        for (CourseKnowledgeStructure courseKnowledgeStructure:topFloorCourseKnowledgeStructureList){
            CourseKnowledgeStructure finalCourseKnowledgeStructure = courseKnowledgeStructure;
            CourseKnowledgeStructure courseKnowledgeStructureResult = courseKnowledgeStructureRepository.save(finalCourseKnowledgeStructure);
            List<CourseKnowledgeStructure> point = new ArrayList<>();
            List<CourseKnowledgeStructure> left = new ArrayList<>();
            courseKnowledgeStructures.stream().filter(item->StringUtils.isNotBlank(item.getParentId())
                    &&item.getParentId().equals(finalCourseKnowledgeStructure.getId())&&
                    item.getStructureStatus().equals(StructureStatus.NORMAL)&&
                    item.getKnowledgeStructureType().equals(KnowledgeStructureType.KNOWLEDGE_POINT)).forEach(item->{
                        item.setCourse(course);
                        item.setCourseVersion(courseVersion);
                        item.setCreateTime(now);
                        item.setCreatorId(userId);
                        item.setCreatorName(userName);
                        item.setParentId(courseKnowledgeStructureResult.getId());
                point.add(item);
            });
            if(CollectionUtils.isNotEmpty(point)){
                courseKnowledgeStructureRepository.saveAll(point);
            }
            courseKnowledgeStructures.stream().filter(item->StringUtils.isNotBlank(item.getParentId())
                    &&item.getParentId().equals(finalCourseKnowledgeStructure.getId())&&
                    item.getStructureStatus().equals(StructureStatus.NORMAL)&&
                    item.getKnowledgeStructureType().equals(KnowledgeStructureType.SECTION)).forEach(item->{
                item.setParentId(courseKnowledgeStructureResult.getId());
                item.setCourse(course);
                item.setCourseVersion(courseVersion);
                item.setCreateTime(now);
                item.setCreatorId(userId);
                item.setCreatorName(userName);
                left.add(item);
            });

            for (CourseKnowledgeStructure courseKnowledgeStructureLeft:left){
                CourseKnowledgeStructure courseKnowledgeStructureLeftResult = courseKnowledgeStructureRepository.save(courseKnowledgeStructureLeft);

                List<CourseKnowledgeStructure> courseKnowledgeStructureListLeft = new ArrayList<>();
                courseKnowledgeStructures.stream().filter(item->StringUtils.isNotBlank(item.getParentId())
                        &&item.getParentId().equals(courseKnowledgeStructureLeft.getId())&&
                        item.getStructureStatus().equals(StructureStatus.NORMAL)&&
                        item.getKnowledgeStructureType().equals(KnowledgeStructureType.KNOWLEDGE_POINT)).forEach(item->{
                    item.setParentId(courseKnowledgeStructureLeftResult.getId());
                    item.setCourse(course);
                    item.setCourseVersion(courseVersion);
                    item.setCreateTime(now);
                    item.setCreatorId(userId);
                    item.setCreatorName(userName);
                    courseKnowledgeStructureListLeft.add(item);
                });
                if(CollectionUtils.isNotEmpty(courseKnowledgeStructureListLeft)){
                    courseKnowledgeStructureRepository.saveAll(courseKnowledgeStructureListLeft);
                }
            }

        }

    }

    private Result createCourseBriefData(String userName, String userId, HSSFWorkbook workBook,
                                         Course course, CourseVersion courseVersion) {
        HSSFSheet sheet  = workBook.getSheet("课程简介");
        List<CourseMaterial> courseMaterials = new ArrayList<>();
        CourseCategory courseCategory = null;
        final int two = 1;
        for (Row row :sheet){
            if(row.getRowNum() == sheet.getLastRowNum()-two){
                if(row.getCell(0)!=null
                        &&StringUtils.isNotEmpty(row.getCell(0).getStringCellValue().trim())){
                    String jsonMaterial = row.getCell(0).getStringCellValue().trim();
                    CourseMaterial courseMaterial = JSONObject.parseObject(jsonMaterial,CourseMaterial.class);
                    courseMaterial.setId(null);
                    courseMaterial.setCourse(course);
                    courseMaterial.setCourseVersion(courseVersion);
                    courseMaterial.setCreateTime(new Date());
                    courseMaterial.setCreatorId(userId);
                    courseMaterial.setCreatorName(userName);
                    courseMaterials.add(courseMaterial);
                }
            }

            if(row.getRowNum() == sheet.getLastRowNum()){
                if(row.getCell(0)!=null
                        &&StringUtils.isNotEmpty(row.getCell(0).getStringCellValue().trim())){
                    String jsonMaterial = row.getCell(0).getStringCellValue().trim();
                    CourseCategory courseCategoryJson = JSONObject.parseObject(jsonMaterial,CourseCategory.class);
                    if(ObjectUtils.isNotEmpty(courseCategoryJson)){
                        courseCategory = courseCategoryJson;
                    }
                }
            }
        }
        if(ObjectUtils.isNotEmpty(courseCategory)){
            courseCategory = courseCategoryRepository.saveAndFlush(courseCategory);
            CourseCategory finalCourseCategory = courseCategory;
            courseMaterials.stream().forEach(item->{
                item.setCourseCategory(finalCourseCategory);
            });
        }
        if(CollectionUtils.isNotEmpty(courseMaterials)){
            courseMaterialRepository.saveAll(courseMaterials);
        }
        HSSFSheet sheetIntroduction  = workBook.getSheet("课程介绍");
        List<CourseIntroduction> courseIntroductionList = new ArrayList<>();
        if(sheetIntroduction!=null){
            for (Row row :sheetIntroduction){
                if(row.getCell(0)!=null
                        &&StringUtils.isNotEmpty(row.getCell(0).getStringCellValue().trim())){
                    String jsonMaterial = row.getCell(0).getStringCellValue().trim();
                    CourseIntroduction courseIntroduction = JSONObject.parseObject(jsonMaterial,CourseIntroduction.class);
                    courseIntroduction.setId(null);
                    courseIntroduction.setCourse(course);
                    courseIntroduction.setCourseVersion(courseVersion);
                    courseIntroduction.setCreateTime(new Date());
                    courseIntroduction.setCreatorId(userId);
                    courseIntroduction.setCreatorName(userName);
                    courseIntroductionList.add(courseIntroduction);
                }
            }
        }
        if(CollectionUtils.isNotEmpty(courseIntroductionList)){
            courseIntroductionRepository.saveAll(courseIntroductionList);
        }
        return Result.success();
    }

    private Map<String,Object> buildCourseAndCollegeInfo(String userName,String userId, HSSFWorkbook workBook) {
        Map<String,Object> resultMap = new HashMap<>();
        HSSFSheet sheet  = workBook.getSheet("课程信息");
        Row rowTwo = sheet.getRow(1);
        if(isRowEmpty(rowTwo)){
            resultMap.put("error","学院信息为空");
            return resultMap;
        }
        String jsonCollege = rowTwo.getCell(0).getStringCellValue().trim();
        College collegeInfo = JSONObject.parseObject(jsonCollege,College.class);
        College college = null;
        College optionalCollege = collegeRepository.findByCollegeCode(collegeInfo.getCollegeCode());
        if(ObjectUtils.isEmpty(optionalCollege)){
            college = collegeRepository.saveAndFlush(collegeInfo);
        }else{
            college = optionalCollege;
        }
        Row rowOne = sheet.getRow(0);
        if(isRowEmpty(rowOne)){
            resultMap.put("error","课程数据为空");
            return resultMap;
        }
        String jsonCourse = rowOne.getCell(0).getStringCellValue().trim();
        Course courseInfo = JSONObject.parseObject(jsonCourse,Course.class);
        Course courseDate = null;
        Course optionalCourse = courseRepository.findByCourseCode(courseInfo.getCourseCode());
        if(ObjectUtils.isNotEmpty(optionalCourse)){
            Course course = optionalCourse;
            course.setCollege(college);
            if(!course.isUseState()){
                course.setUseState(true);
                courseDate = course;
                List<CourseVersion> courseVersionList = courseVersionRepository.findByCourseId(course.getId());
                courseVersionList.forEach(courseVersion -> {
                    courseVersion.setCourseVersionStatus(CourseVersionStatus.DELETE);
                });
                courseVersionRepository.saveAll(courseVersionList);
            }else{
                resultMap.put("error","课程已存在，无法导入");
                return resultMap;
            }
        }
        courseInfo.setId(null);
        courseInfo.setCreatorId(userId);
        courseInfo.setCreateTime(new Date());
        courseInfo.setCreatorName(userName);
        courseInfo.setUseState(false);
        courseInfo.setCollege(college);
        courseInfo.setCollegeCode(college.getCollegeCode());
        courseInfo.setCollegeName(college.getCollegeName());

        CourseVersion courseVersion;
        if(ObjectUtils.isNotEmpty(courseDate)){
            courseInfo = courseDate;
            courseVersion = courseVersionService.generateAndUpdateCourseVersion(courseInfo);
        }else{
            courseInfo = courseRepository.saveAndFlush(courseInfo);
            courseVersion = courseVersionService.generateAndUpdateCourseVersion(courseInfo);
        }
        courseVersionRepository.saveAndFlush(courseVersion);

        DownloadRecord downloadRecord = buildDownloadInfo(userName, userId, courseInfo, courseDate, courseVersion);
        resultMap.put("course",courseInfo);
        resultMap.put("college",college);
        resultMap.put("courseVersion",courseVersion);
        resultMap.put("downloadRecord",downloadRecord);
        return  resultMap;
    }

    private DownloadRecord buildDownloadInfo(String userName, String userId, Course courseInfo, Course courseDate, CourseVersion courseVersion) {
        Date now = new Date();
        List<DowmloadRecordStatus> dowmloadRecordStatuses = new ArrayList<>();
        dowmloadRecordStatuses.add(DowmloadRecordStatus.UP_FAIL);
        dowmloadRecordStatuses.add(DowmloadRecordStatus.UP_END);
        dowmloadRecordStatuses.add(DowmloadRecordStatus.UP_ING);
        DownloadRecord  downloadRecord =
                downloadRecordRepository.findByCreatorIdAndPackTypeAndCourseIdAndVersionSerialNumberAndPackStatusIn(
                        userId,0, courseVersion.getCourse().getId(), courseVersion.getVersionSerialNumber(),dowmloadRecordStatuses);

        if(ObjectUtils.isNotEmpty(downloadRecord)){
            downloadRecord.setPackStatus(DowmloadRecordStatus.UP_ING);
            downloadRecord.setModifyTime(now);
            downloadRecord.setCourseId(courseInfo.getId());
            downloadRecord.setCourseName(courseInfo.getCourseName());
            downloadRecord.setVersionId(courseVersion.getId());
            downloadRecord.setVersionSerialNumber(courseVersion.getVersionSerialNumber());
        }else {
            downloadRecord = new DownloadRecord();
            downloadRecord.setPackType(0);
            downloadRecord.setPackStatus(DowmloadRecordStatus.UP_ING);
            downloadRecord.setCourseId(courseInfo.getId());
            downloadRecord.setCourseName(courseInfo.getCourseName());
            downloadRecord.setVersionId(courseVersion.getId());
            downloadRecord.setVersionSerialNumber(courseVersion.getVersionSerialNumber());
            downloadRecord.setPackUserId(userId);
            downloadRecord.setCreateTime(now);
            downloadRecord.setCreatorId(userId);
            downloadRecord.setCreatorName(userName);
            downloadRecord.setModifyTime(now);
            downloadRecord.setModifierId(userId);
            downloadRecord.setModifierName(userName);
        }
        downloadRecordRepository.saveAndFlush(downloadRecord);
        return downloadRecord;
    }

    private Result createCourseTeamData(String userName,String userId, HSSFWorkbook workBook,
                                        Course course,CourseVersion courseVersion)  {
        HSSFSheet sheet  = workBook.getSheet("课程团队");
        Row rowOne = sheet.getRow(0);
        if(isRowEmpty(rowOne)){
            return Result.error("课程团队数据为空");
        }
        List<CourseTeachingTeam> teachingTeams =
                courseTeachingTeamRepository.findByCourseIdAndCourseVersionId(course.getId(), courseVersion.getId());
        List<CourseTeachingTeam> courseTeachingTeams = new ArrayList<>();
        for (Row row:sheet){
            if(row.getCell(0)!=null&&StringUtils.isNotEmpty(row.getCell(0).getStringCellValue())){
                String jsonTeam = row.getCell(0).getStringCellValue();
                CourseTeachingTeam courseTeachingTeam = JSONObject.parseObject(jsonTeam,CourseTeachingTeam.class);
                if(teachingTeams.stream().filter(item->item.getTeacherId().equals(courseTeachingTeam.getTeacherId())).count()==0){
                    courseTeachingTeam.setId(null);
                    courseTeachingTeam.setCourse(course);
                    courseTeachingTeam.setCourseVersion(courseVersion);
                    courseTeachingTeam.setCreateTime(new Date());
                    courseTeachingTeam.setCreatorId(userId);
                    courseTeachingTeam.setCreatorName(userName);
                    courseTeachingTeams.add(courseTeachingTeam);
                }

            }
        }
        teachingTeams.forEach(item->{

        });
        if(CollectionUtils.isNotEmpty(courseTeachingTeams)){
            courseTeachingTeamRepository.saveAll(courseTeachingTeams);
        }
        return Result.success();

    }

    public static boolean isRowEmpty(Row row) {
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null&&StringUtils.isNotEmpty(row.getCell(c).getStringCellValue())||
                    !"".equals(row.getCell(c).getStringCellValue().trim()) ) {
                return false;
            }
        }
        return true;
    }


    public List<CoursePackageListVo> getPackCourseBag(String userId){
        List<CoursePackageListVo> coursePackageListVos = new ArrayList<>();
        List<DownloadRecord> downloadRecords = downloadRecordRepository.findByCreatorIdAndPackType(userId,0);
        for (DownloadRecord downloadRecord :downloadRecords){
            CoursePackageListVo coursePackageListVo  =new CoursePackageListVo();
            CourseVersion courseVersion = courseVersionRepository.findById(downloadRecord.getVersionId()).orElse(null);
            Course course = courseRepository.findById(downloadRecord.getCourseId()).orElse(null);
            List<CourseStructure> courseStructureList = courseStructureRepository.findByCourseIdAndCourseVersionIdAndStructureStatus(
                    course.getId(),courseVersion.getId(),StructureStatus.NORMAL);
            List<CourseResource> courseResourceList = new ArrayList<>();
            for (CourseStructure courseStructure:courseStructureList){
                if(CollectionUtils.isNotEmpty(courseStructure.getCourseResources())){
                    courseResourceList.addAll(courseStructure.getCourseResources());
                }
            }
            courseResourceList = courseResourceList.stream().filter(item -> item.getIsPublic() &&
                    item.getResourceStatus().equals(ResourceStatus.NORMAL)&&item.getResourceSize()!=null).collect(Collectors.toList());
            long fileSize = courseResourceList.stream()
                    .mapToLong(CourseResource::getResourceSize).sum();

            if(fileSize>0){
                coursePackageListVo.setFileSize(getPrintSize(fileSize));
            }else{
                coursePackageListVo.setFileSize(getPrintSize(downloadRecord.getFileSize()));
            }
            coursePackageListVo.setFileSize(getPrintSize(downloadRecord.getFileSize()));
            coursePackageListVo.setCourseName(course.getCourseName()+"("+course.getCourseCode()+")"
                    +"版本"+courseVersion.getVersionSerialNumber());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            coursePackageListVo.setStartTime(simpleDateFormat.format(downloadRecord.getModifyTime()));
            coursePackageListVo.setTaskStatus(String.valueOf(downloadRecord.getPackStatus().getValue()));
            if(downloadRecord.getPackStatus().equals(DowmloadRecordStatus.PACK_END)||
                    downloadRecord.getPackStatus().equals(DowmloadRecordStatus.PACK_ING)||
                    downloadRecord.getPackStatus().equals(DowmloadRecordStatus.PACK_FAIL)){
                coursePackageListVo.setTaskType("导出课程包");
            }else{
                coursePackageListVo.setTaskType("导入课程包");
            }
            if(downloadRecord.getPackStatus().equals(DowmloadRecordStatus.PACK_END)){
                coursePackageListVo.setPhysicalPath(downloadRecord.getFilePath());
                coursePackageListVo.setIntranetFilePath(downloadRecord.getInnerIp());
                coursePackageListVo.setOuterNetFilePath(downloadRecord.getOuterIp());
            }
            coursePackageListVo.setCourseId(course.getId());
            coursePackageListVo.setVersionId(courseVersion.getId());
            coursePackageListVos.add(coursePackageListVo);
        }
        coursePackageListVos = coursePackageListVos.stream()
                .sorted(Comparator.comparing(CoursePackageListVo::getStartTime).reversed()).collect(Collectors.toList());
        return coursePackageListVos;
    }

    public String getPrintSize(long size) {
        final int company = 1024 ,hundred = 100;
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


    private UploadFileMessage saveCourseBagFile(String fileInfoVo,String tempFileName, String zipFilePath) throws IOException {
        File tempFile = new File(zipFilePath);

        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.fileManagementApi + COURSE_FILE_TO_ZIP);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
            FileSystemResource resource = new FileSystemResource(tempFile);//把临时文件变成FileSystemResource

            parts.add("file", resource);
            parts.add("fileInfoVo ", fileInfoVo);
            parts.add("fileName",tempFileName);
            parts.add("validCode ", Md5Utils.md5(signKey));

            HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(parts, headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<UploadFileMessage> responseEntity =
                    restTemplate.postForEntity(stringBuilder.toString(), httpEntity, UploadFileMessage.class);
            return responseEntity.getBody();
        } catch (Exception e) {
            return null;
        } finally {
            this.deleteTempFile(tempFile);
        }
    }

    private boolean deleteTempFile(File tempFile) {
        try {
            return tempFile.delete();
        } catch (SecurityException e) {
            e.printStackTrace();
            return false;
        }
    }

    private UploadFileMessage saveFile( File file) throws IOException {

        try {
            String uploadFileName = UUID.randomUUID().toString().replace("-", "");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.fileManagementApi + POST_FILE_BY_PROJECTNAME);
            stringBuilder.append("teachingcenterteacher_web");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
            //MultipartFile 直接转 fileSystemResource 是不行的
            FileSystemResource resource = new FileSystemResource(file);//把临时文件变成FileSystemResource

            parts.add("file", resource);
            parts.add("uploadFileName ", uploadFileName);
            parts.add("validCode ", Md5Utils.md5("projectName=teachingcenterteacher_web" + signKey));

            HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(parts, headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<UploadFileMessage> responseEntity =
                    restTemplate.postForEntity(stringBuilder.toString(), httpEntity, UploadFileMessage.class);
            return responseEntity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
