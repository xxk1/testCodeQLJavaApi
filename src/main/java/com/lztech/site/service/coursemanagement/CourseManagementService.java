package com.lztech.site.service.coursemanagement;

import com.lztech.domain.model.course.*;
import com.lztech.domain.model.course.enums.*;
import com.lztech.persistence.repositories.course.*;
import com.lztech.persistence.repositories.course.specification.CourseResourceSpecification;
import com.lztech.persistence.repositories.coursetable.CourseTableRepository;
import com.lztech.site.constants.Constant;
import com.lztech.site.resource.course.CourseInfoResource;
import com.lztech.site.service.course.CourseService;
import com.lztech.site.service.courseconstruction.CourseCompletionService;
import com.lztech.site.service.courseconstruction.CourseVersionService;
import com.lztech.site.service.event.EventService;
import com.lztech.site.viewmodel.coursemanagement.*;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static com.lztech.persistence.repositories.course.specification.CourseSpecification.courseNameOrCodeSpecification;
import static com.lztech.site.constants.Constant.*;
import static java.util.stream.Collectors.toList;

@Service
public class CourseManagementService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseManagementService.class);

    @Autowired
    private CourseStructureRepository courseStructureRepository;
    @Autowired
    private CourseTeachingTeamRepository courseTeachingTeamRepository;
    @Autowired
    private CourseResourceFileRepository courseResourceFileRepository;
    @Autowired
    private CourseResourceRepository courseResourceRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseVersionService courseVersionService;

    @Autowired
    private CourseCompletionService courseCompletionService;
    @Autowired
    private EventService eventService;
    @Autowired
    private EntityManager entityManager;
    private static double trillion = 1048576.0;
    private static double zeroPointOne = 0.1;
    @Autowired
    private CourseTableRepository courseTableRepository;
    @Value("${dataVisual.enable}")
    private Boolean dataVisualEnable;
    @Autowired
    private ExtendCourseManagementService extendCourseManagementService;
    @Autowired
    private TranscodeService transcodeService;
    @Autowired
    private TeachingTeamTeacherRepository teachingTeamTeacherRepository;

    //endregion
    //region 接口-老师查看加入的课程
    public List<CourseVo> getCourseList(String teacherId) {
        List<CourseVo> courseVos = new ArrayList<>();
        List<CourseTeachingTeam> courseTeachingTeams = courseTeachingTeamRepository.findByTeacherIdOrderByCourseId(teacherId);
        System.currentTimeMillis();
        List<String> courseIdList = courseTeachingTeams
                .stream()
                .map(courseTeachingTeam
                        -> courseTeachingTeam.getCourse().getId())
                .collect(Collectors.toList());
        String ids = courseIdList.stream().collect(Collectors.joining(","));
        List<CourseVo> courseVoList = getCourseTableByCourseIds(ids);
        if (CollectionUtils.isNotEmpty(courseTeachingTeams)) {
            courseVos.addAll(courseTeachingTeams
                    .stream()
                    .filter(courseTeachingTeam -> courseTeachingTeam.getCourse().isUseState())
                    .map(courseTeachingTeam
                            -> new CourseVo() {{
                        this.courseId(courseTeachingTeam.getCourse().getId());
                        this.courseCode(courseTeachingTeam.getCourse().getCourseCode());
                        this.courseName(courseTeachingTeam.getCourse().getCourseName());
                        this.courseTeachingTeamId(courseTeachingTeam.getId());
                        this.teacherType(courseTeachingTeam.getTeacherType().getIndex());
                        this.courseSource(courseTeachingTeam.getCourse().getCourseSource().getIndex());
                        this.existCourseClass(courseVoList
                                .stream()
                                .anyMatch(courseTable
                                        -> courseTable.getCourseId().equals(courseTeachingTeam.getCourse().getId())));
                        CourseCover courseCover = courseTeachingTeam.getCourse().getCourseCover();
                        if (ObjectUtils.isNotEmpty(courseCover)) {
                            this.setCoverInnerIp(courseCover.getInnerIp());
                            this.setCoverOuterIp(courseCover.getOuterIp());
                            this.setCoverFilepath(courseCover.getFilePath());
                        }
                    }}).collect(Collectors.toList()));
            //去重
            courseVos = courseVos.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(()
                    -> new TreeSet<>(Comparator.comparing(CourseVo::getCourseId))), ArrayList::new));
        }
        return courseVos;
    }

    public CourseVo getCourseManagementByCourseIdAndTeacherId(String teacherId, String courseId) {
        List<CourseCompletionResource> completionResourceList = courseCompletionService.getCourseCompletion(courseId);
        List<CourseTeachingTeam> courseTeachingTeams =
                courseTeachingTeamRepository.findByTeacherIdAndCourseIdOrderByCourseId(teacherId, courseId);
        List<CourseVo> courseVoList = getCourseTableByCourseIds(courseId);
        if (CollectionUtils.isNotEmpty(courseTeachingTeams)) {
            CourseTeachingTeam courseTeachingTeam = courseTeachingTeams.get(0);
            CourseVo courseVo = getCourseVo(courseTeachingTeam);
            courseVo.existCourseClass(courseVoList
                    .stream()
                    .anyMatch(courseTable
                            -> courseTable.getCourseId().equals(courseTeachingTeam.getCourse().getId())));
            CourseCover courseCover = courseTeachingTeam.getCourse().getCourseCover();
            if (ObjectUtils.isNotEmpty(courseCover)) {
                courseVo.setCoverInnerIp(courseCover.getInnerIp());
                courseVo.setCoverOuterIp(courseCover.getOuterIp());
                courseVo.setCoverFilepath(courseCover.getFilePath());
            }
            CourseCompletionResource filterCourseCompletionResource =
                    completionResourceList.stream().filter(courseCompletionResource ->
                            courseCompletionResource.getCourseId().equals(courseId)).findFirst().orElse(null);
            if (ObjectUtils.isNotEmpty(filterCourseCompletionResource)) {
                courseVo.setCompleteRate(filterCourseCompletionResource.getCompletion().toString());
            }
            return courseVo;
        }
        return null;
    }

    private CourseVo getCourseVo(CourseTeachingTeam courseTeachingTeam) {
        CourseVo courseVo = new CourseVo();
        courseVo.courseId(courseTeachingTeam.getCourse().getId());
        courseVo.courseCode(courseTeachingTeam.getCourse().getCourseCode());
        courseVo.courseName(courseTeachingTeam.getCourse().getCourseName());
        courseVo.courseTeachingTeamId(courseTeachingTeam.getId());
        courseVo.teacherType(courseTeachingTeam.getTeacherType().getIndex());
        courseVo.courseSource(courseTeachingTeam.getCourse().getCourseSource().getIndex());
        return courseVo;
    }

    public List<CourseVo> getCourseTableByCourseIds(String courseIds) {
        String sql = " SELECT " +
                " c.id courseId,c.course_name courseName  " +
                "FROM tb_course_table ct inner join tb_course c on c.id =ct.course_id where course_id in ( '" + courseIds + "')";
        Query projectQuery = entityManager.createNativeQuery(sql);
        projectQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseVo.class));
        List<CourseVo> courseTables = (List<CourseVo>) projectQuery.getResultList();
        return courseTables;
    }

    //endregion
    @Transactional
    public ResponseEntity<Void> createTeachingContentResource(TeachingContentResource teachingContentResource) {
        CourseStructure courseStructure = courseStructureRepository
                .findByIdAndStructureStatus(teachingContentResource.getCourseStructureId(), StructureStatus.NORMAL);
        if (ObjectUtils.isEmpty(courseStructure)) {
            return new ResponseEntity(ErrorResult.customError("保存失败，课程结构已被删除"), HttpStatus.NOT_FOUND);
        }
        CourseVersion courseVersion = courseStructure.getCourseVersion();
        if (ObjectUtils.isEmpty(courseVersion) || !CourseVersionStatus.IN_USE
                .equals(courseStructure.getCourseVersion().getCourseVersionStatus())) {
            return new ResponseEntity(ErrorResult.customError("无法上传，当前版本已归档，请刷新页面"), HttpStatus.CONFLICT);
        }
        Date now = new Date();
        List<String> resourceNames = extendCourseManagementService.getAllResourceNames(teachingContentResource.getUserId(), courseStructure,
                null);
        List<CourseResource> courseResourceList = new ArrayList<>();
        List<CourseResourceFile> transcodeCourseResourceFiles = new ArrayList<>();
        for (int i = 0; i < teachingContentResource.getFileList().size(); i++) {
            TeachingContentFileResource file = teachingContentResource.getFileList().get(i);
            String newName = buildResourceNewName(resourceNames, teachingContentResource, file);
            CourseResourceFile courseResourceFile = new CourseResourceFile();
            buildCourseResourceFile(courseResourceFile, teachingContentResource, now, file);
            courseResourceFile.setFileRealName(newName);
            Boolean isTransCodeVideoType = transcodeService.isTransCodeVideoType(file.getFileType());
            if (isTransCodeVideoType) {
                courseResourceFile.setTranscodeStatus(TranscodeStatus.TRANS_CODING);
            } else {
                courseResourceFile.setTranscodeStatus(TranscodeStatus.NOT_TRANSCODE);
            }
            courseResourceFile = courseResourceFileRepository.saveAndFlush(courseResourceFile);
            if (isTransCodeVideoType) {
                transcodeCourseResourceFiles.add(courseResourceFile);
            }

            CourseResource courseResource = new CourseResource();
            buildCourseResource(courseResource, teachingContentResource, now, courseResourceFile.getId(), courseStructure, file, i);
            courseResource.setResourceName(newName);
            courseResourceList.add(courseResource);
        }
        List<CourseResource> saveCourseResourceList = courseResourceRepository.saveAll(courseResourceList);
        courseCompletionService.saveCourseCompletion(courseStructure.getCourse(), CourseContentTypeEnum.TEACHING_RESOURCES, true,
                teachingContentResource.getUserId(), teachingContentResource.getUserName());
        if (transcodeCourseResourceFiles.size() > 0) {
            transcodeService.sendTranscodeMqRequestMessage(transcodeCourseResourceFiles);
        }

        if (dataVisualEnable) {
            saveCourseResourceList.forEach(saveCourseResource -> {
                courseService.sendCourseResourceEvent(saveCourseResource);
            });
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public String buildResourceFileNewName(List<String> resourceNames, CourseResourceFile courseResourceFile) {
        String finalName = courseResourceFile.getFileRealName();
        if (courseResourceFile.getFileRealName().indexOf("(") > 0) {
            String suffix = courseResourceFile.getFileRealName().substring(courseResourceFile.getFileRealName().indexOf("("));
            String originallyName = courseResourceFile.getFileRealName().replace(suffix, "");
            finalName = originallyName + "." + courseResourceFile.getFileType() + suffix;
        } else {
            finalName = courseResourceFile.getFileRealName() + "." + courseResourceFile.getFileType();
        }
        AtomicReference<String> newName = new AtomicReference<>(checkZipName(resourceNames, finalName, 1));
        String finalName1 = finalName;
        resourceNames.stream().filter(item -> item.equals(finalName1)).forEach(item -> {
            if (resourceNames.contains(finalName1 + "(1)")) {
                newName.set(checkZipName(resourceNames, finalName1 + "(1)", 1));
            } else {
                newName.set(finalName1 + "(1)");
            }
        });
        finalName = newName.get();
        resourceNames.add(newName.get());
        if (finalName.lastIndexOf(".") > 0 && finalName.indexOf("(") > 0) {
            String suffixType = finalName.substring(finalName.lastIndexOf("."), finalName.indexOf("("));
            finalName = finalName.replace(suffixType, "");
        } else if (finalName.lastIndexOf(".") > 0 && finalName.indexOf("(") < 0) {
            String suffixType = finalName.substring(finalName.lastIndexOf("."));
            finalName = finalName.replace(suffixType, "");
        }
        return finalName;
    }

    public String buildResourceNewName(List<String> resourceNames, TeachingContentResource teachingContentResource,
                                       TeachingContentFileResource file) {
        // 1. 获取原始文件名和后缀
        String originalName = file.getFileRealName();
        String fileType = file.getFileType();

        // 【关键修复步骤】
        // 检查 originalName 是否已经包含了后缀？如果是，先把它切掉！
        // 比如：传入 "Ne2(1).txt"，我们要处理成 "Ne2(1)"，否则后面拼接会变成 "Ne2(1).txt.txt"
        String suffix = "." + fileType;
        if (originalName.toLowerCase().endsWith(suffix.toLowerCase())) {
            originalName = originalName.substring(0, originalName.length() - suffix.length());
        }

        // 此时 originalName 应该是纯文件名，例如 "Ne2(1)"

        // 2. 定义最终返回的名字（不带后缀）和用于查重的全名（带后缀）
        String finalRealName = originalName;
        String fullFileName = finalRealName + "." + fileType;

        // 3. 第一次检查：列表里有没有 "Ne2(1).txt"？
        if (checkIfExist(resourceNames, fullFileName)) {
            // 如果有，说明重名了，开始单纯地追加 (1), (2), (3)...
            int version = 1;
            while (true) {
                // 拼接新名字：原名 + (序号)
                // Ne2(1) -> Ne2(1)(1)
                String tryRealName = originalName + "(" + version + ")";
                String tryFullName = tryRealName + "." + fileType;

                // 再次检查这个新名字是否被占用
                if (!checkIfExist(resourceNames, tryFullName)) {
                    // 没被占用，找到目标了！
                    finalRealName = tryRealName;
                    fullFileName = tryFullName;
                    break;
                }
                // 被占用了，序号+1，试下一个
                version++;
            }
        }

        // 4. 将最终确定的全名加入列表（防止同批次文件冲突）
        resourceNames.add(fullFileName);

        // 5. 返回不带后缀的名字（系统会在外层再拼一次后缀）
        return finalRealName;
    }

    // 辅助方法：忽略大小写检查是否存在
    private boolean checkIfExist(List<String> list, String target) {
        // 遍历列表，忽略大小写比较 (防止 .txt 和 .TXT 造成的误判)
        for (String name : list) {
            if (name.equalsIgnoreCase(target)) {
                return true;
            }
        }
        return false;
    }


    private CourseResourceFile buildCourseResourceFile(CourseResourceFile courseResourceFile, TeachingContentResource teachingContentResource,
                                                       Date now, TeachingContentFileResource file) {
        courseResourceFile.setCreateTime(now);
        courseResourceFile.setModifyTime(now);
        courseResourceFile.setCreatorId(teachingContentResource.getUserId());
        courseResourceFile.setCreatorName(teachingContentResource.getUserName());
        courseResourceFile.setModifierId(teachingContentResource.getUserId());
        courseResourceFile.setModifierName(teachingContentResource.getUserName());
        courseResourceFile.setResourceStatus(ResourceStatus.NORMAL);
        courseResourceFile.setFilePath(file.getFilepath());
        courseResourceFile.setFileSavedName(file.getFileSavedName());
        courseResourceFile.setFileSize(file.getFileSize().longValue());
        courseResourceFile.setFileType(file.getFileType());
        courseResourceFile.setInnerIp(file.getInnerServerIp());
        courseResourceFile.setOuterIp(file.getOuterServerIp());
        courseResourceFile.setIsPublic(teachingContentResource.isIsPublic());
        return courseResourceFile;
    }

    private CourseResource buildCourseResource(CourseResource courseResource, TeachingContentResource teachingContentResource, Date now,
                                               String detailId, CourseStructure courseStructure, TeachingContentFileResource file, Integer i) {
        courseResource.setCourseStructure(courseStructure);
        courseResource.setCreateTime(now);
        courseResource.setModifyTime(now);
        courseResource.setCreatorId(teachingContentResource.getUserId());
        courseResource.setCreatorName(teachingContentResource.getUserName());
        courseResource.setModifierId(teachingContentResource.getUserId());
        courseResource.setModifierName(teachingContentResource.getUserName());
        courseResource.setResourceStatus(ResourceStatus.NORMAL);
        courseResource.setResourceReferences(0);
        courseResource.setResourceOtherReferences(0);
        courseResource.setResourceContentNum(1);
        courseResource.setResourceDetailId(detailId);
        courseResource.setResourceSize(file.getFileSize().longValue());
        courseResource.setVisionNumber(0);
        courseResource.setIsPublic(teachingContentResource.isIsPublic());
        courseResource.setResourceType(ResourceType.getResourceType(teachingContentResource.getResourceType()));
        courseResource.setCourseId(courseStructure.getCourse().getId());
        courseResource.setSourceType(ResourceSourceType.USER_ADDED);
        if ((ObjectUtils.isNotEmpty(teachingContentResource.getSource()) && 1 == teachingContentResource.getSource())
                || teachingContentResource.isIsPublic()) {
            CourseResource resource = courseResourceRepository.findTop1ByCourseStructureIdAndIsPublicAndResourceStatusOrderByAuxiliarySortDesc(
                    courseStructure.getId(), true, ResourceStatus.NORMAL);
            if (ObjectUtils.isNotEmpty(resource)) {
                courseResource.setAuxiliarySort(resource.getAuxiliarySort() + i + 1);
            } else {
                courseResource.setAuxiliarySort(i);
            }
        }
        CourseResource resource = courseResourceRepository.findTop1ByCreatorIdAndCourseStructureIdAndResourceStatusOrderBySortDesc(
                teachingContentResource.getUserId(),
                courseStructure.getId(),
                ResourceStatus.NORMAL);
        if (ObjectUtils.isNotEmpty(resource)) {
            courseResource.setSort(resource.getSort() + i + 1);
        } else {
            courseResource.setSort(i);
        }
        return courseResource;
    }

    @Transactional
    public ResponseEntity<CourseResourceData> updateCourseResourceFile(String userId, String fileId, String fileName) {
        Date nowDate = new Date();
        CourseResource resource = courseResourceRepository.findById(fileId).orElse(null);
        if (ObjectUtils.isEmpty(resource)) {
            return new ResponseEntity(ErrorResult.dataNotExistError("课程资源"), HttpStatus.NOT_FOUND);
        }
        if (!userId.equals(resource.getCreatorId())) {
            return new ResponseEntity(ErrorResult.customError("您不是该资源的创建者,无法进行重命名操作"), HttpStatus.METHOD_NOT_ALLOWED);
        }
        Date now = new Date();
        CourseStructure courseStructure = resource.getCourseStructure();
        if (!resource.getResourceName().equals(fileName)) {
            if (ResourceType.CLASS_TEST.equals(resource.getResourceType())) {
                resource = this.checkSameNamePaperUpdateClassTest(resource, fileName);
            } else {
                CourseResourceFile courseResourceFile = courseResourceFileRepository.findById(resource.getResourceDetailId()).orElse(null);
                List<CourseResource> courseResources = checkSameNamePaper(courseResourceFile.getCreatorId(), resource.getCourseStructure(),
                        fileName, courseResourceFile.getIsPublic(), courseResourceFile.getFileType(), "");
                if (CollectionUtils.isEmpty(courseResources)) {
                    courseResources = checkSameNamePaperLike(resource.getCreatorId(), resource.getCourseStructure(), fileName,
                            resource.getIsPublic(), courseResourceFile.getFileType(), "");
                    courseResourceFile = updateCourseResourceFileLike(courseResourceFile, fileName, now, courseResources, courseStructure);
                } else {
                    courseResourceFile = updateCourseResourceFileSave(courseResourceFile, fileName, now, courseResources, courseStructure);
                }
                resource.setResourceName(courseResourceFile.getFileRealName());
                resource.setVisionNumber(courseResourceFile.getVisionNumber());
                resource.setModifyTime(now);
                courseResourceRepository.save(resource);
                if ((ResourceType.TEACHING_COURSE_WARE.equals(resource.getResourceType()) ||
                        ResourceType.MICRO_VIDEO.equals(resource.getResourceType())
                        || ResourceType.AUDIO.equals(resource.getResourceType())
                        || ResourceType.IMAGE.equals(resource.getResourceType())
                ) && StringUtils.isNotBlank(resource.getResourceDetailId())) {
                    courseResourceFileRepository.save(courseResourceFile);
                }
            }
            if (dataVisualEnable) {
                resource.setResourceName(resource.getResourceName());
                resource.setModifyTime(nowDate);
                courseService.sendCourseResourceEvent(resource);
            }
        }
        CourseResourceData courseResourceData = new CourseResourceData();
        courseResourceData.setFileName(fileName);
        courseResourceData.setResourceType(resource.getResourceType().getIndex());
        courseResourceData.setResourceDetailId(resource.getResourceDetailId());
        return new ResponseEntity<>(courseResourceData, HttpStatus.OK);
    }

    private CourseResourceFile updateCourseResourceFileSave(CourseResourceFile courseResourceFile, String fileName, Date now,
                                                            List<CourseResource> courseResources, CourseStructure courseStructure) {
        List<String> detailIds = courseResources
                .stream()
                .map(CourseResource::getResourceDetailId)
                .collect(Collectors.toList());
        List<CourseResourceFile> exitFile = new ArrayList<>();
        List<CourseResourceFile> courseResourceFileList = courseResourceFileRepository.findByIdIn(detailIds);
        for (CourseResourceFile file : courseResourceFileList) {
            if (file.getFileType().equals(courseResourceFile.getFileType()) &&
                    !file.getId().equals(courseResourceFile.getId())) {
                exitFile.add(file);
            }
        }

        if (CollectionUtils.isEmpty(exitFile)) {
            courseResourceFile.setVisionNumber(0);
            courseResourceFile.setParentId(null);
            courseResourceFile.setFileRealName(fileName);
            courseResourceFile.setModifyTime(now);
            List<CourseResource> resourceList = checkSameNamePaperLike(courseResourceFile.getCreatorId(), courseStructure, fileName,
                    courseResourceFile.getIsPublic(), courseResourceFile.getFileType(), "");
            List<String> resourceDetailIds = resourceList.stream().map(CourseResource::getResourceDetailId).collect(Collectors.toList());
            List<CourseResourceFile> exitSaveFile = new ArrayList<>();
            List<CourseResourceFile> resourceFileList = courseResourceFileRepository.findByIdIn(resourceDetailIds);
            for (CourseResourceFile resourceFile : resourceFileList) {
                if (resourceFile.getFileType().equals(courseResourceFile.getFileType()) &&
                        !resourceFile.getId().equals(courseResourceFile.getId())) {
                    exitSaveFile.add(courseResourceFile);
                }
            }
            CourseResourceFile saveCourseResourceFile = courseResourceFileRepository.save(courseResourceFile);
            exitSaveFile.forEach(file -> {
                file.setParentId(saveCourseResourceFile.getId());
            });
            courseResourceFileRepository.saveAll(exitSaveFile);
            return saveCourseResourceFile;

        } else {
            return updateCourseResourceFileForNoteEmpty(courseResourceFile, fileName, now, exitFile, courseStructure);
        }
    }

    private CourseResourceFile updateCourseResourceFileForNoteEmpty(CourseResourceFile courseResourceFile,
                                                                    String fileName, Date now,
                                                                    List<CourseResourceFile> exitFile,
                                                                    CourseStructure courseStructure) {
        CourseResourceFile resourceFile = exitFile.get(0);
        List<CourseResourceFile> resourceFilesByParentId =
                courseResourceFileRepository.findByParentIdAndResourceStatus(resourceFile.getId(), ResourceStatus.NORMAL);
        List<CourseResourceFile> excludeIdList = new ArrayList<>();
        for (CourseResourceFile file : resourceFilesByParentId) {
            if (!file.getId().equals(courseResourceFile.getId())) {
                excludeIdList.add(file);
            }
        }
        List<String> ids = excludeIdList.stream().map(CourseResourceFile::getId).collect(Collectors.toList());
        List<CourseResource> courseResourceList = courseResourceRepository.findByResourceDetailIdIsInAndCourseStructure(ids, courseStructure);
        List<CourseResourceFile> judgeFiles = new ArrayList<>();
        resourceFilesByParentId.forEach(file -> {
            courseResourceList.forEach(resource -> {
                if (file.getId().equals(resource.getResourceDetailId()) && resource.getResourceStatus() == ResourceStatus.NORMAL) {
                    judgeFiles.add(file);
                }
            });
        });
        judgeFiles.sort(Comparator.comparingInt(CourseResourceFile::getVisionNumber));
        List<Integer> nowVisionNumberList = judgeFiles
                .stream()
                .map(CourseResourceFile::getVisionNumber)
                .distinct()
                .collect(Collectors.toList());
        int maxNowVisionNumber = nowVisionNumberList.stream().max(Integer::compareTo).orElse(0);
        courseResourceFile.setVisionNumber(maxNowVisionNumber + 1);
        courseResourceFile.setParentId(resourceFile.getId());
        courseResourceFile.setFileRealName(fileName + "(" + (maxNowVisionNumber + 1) + ")");
        courseResourceFile.setModifyTime(now);
        return courseResourceFile;
    }

    private CourseResourceFile updateCourseResourceFileLike(CourseResourceFile courseResourceFile, String filename, Date now,
                                                            List<CourseResource> courseResources, CourseStructure courseStructure) {
        String fileRealName = filename;
        fileRealName = StringUtils.substringAfterLast(fileRealName, "(");
        fileRealName = StringUtils.substringBeforeLast(fileRealName, ")");
        if (CollectionUtils.isEmpty(courseResources)) {
            if (filename.contains(")") && StringUtils.isNumeric(fileRealName)) {
                int index = Integer.parseInt(fileRealName);
                courseResourceFile.setFileRealName(filename);
                courseResourceFile.setVisionNumber(index);
                courseResourceFile.setModifyTime(now);
                List<CourseResourceFile> resourceStatus = courseResourceFileRepository.findByParentIdAndResourceStatus(courseResourceFile.getId(),
                        ResourceStatus.NORMAL);
                resourceStatus.forEach(file -> {
                    file.setParentId(null);
                });
                courseResourceFileRepository.save(courseResourceFile);
                courseResourceFileRepository.saveAll(resourceStatus);
                return courseResourceFile;

            } else {
                courseResourceFile.setFileRealName(filename);
                courseResourceFile.setVisionNumber(0);
                courseResourceFile.setModifyTime(now);
                List<CourseResourceFile> resourceStatus = courseResourceFileRepository.findByParentIdAndResourceStatus(courseResourceFile.getId(),
                        ResourceStatus.NORMAL);
                resourceStatus.forEach(file -> {
                    file.setParentId(null);
                });
                courseResourceFileRepository.save(courseResourceFile);
                courseResourceFileRepository.saveAll(resourceStatus);
                return courseResourceFile;
            }
        } else {
            return updateCourseResourceFileLikeListIsNoteEmpty(courseResourceFile, filename, now, courseResources, courseStructure, fileRealName);
        }
    }

    private CourseResourceFile updateCourseResourceFileLikeListIsNoteEmpty(CourseResourceFile courseResourceFile, String filename, Date now,
                                                                           List<CourseResource> courseResources, CourseStructure courseStructure,
                                                                           String fileRealName) {
        if (filename.contains(")") && StringUtils.isNumeric(fileRealName)) {
            String name = filename;
            name = StringUtils.substringBeforeLast(name, "(");
            List<CourseResource> resources = checkSameNamePaper(courseResourceFile.getCreatorId(), courseStructure, name,
                    courseResourceFile.getIsPublic(), courseResourceFile.getFileType(), "");
            if (CollectionUtils.isNotEmpty(resources)) {
                CourseResource courseResource = resources.get(0);
                Optional<CourseResourceFile> optionalCourseResourceFile =
                        courseResourceFileRepository.findById(courseResource.getResourceDetailId());
                if (optionalCourseResourceFile.isPresent()) {
                    courseResourceFile.setParentId(optionalCourseResourceFile.get().getId());
                }
            }
            int index = Integer.parseInt(fileRealName);
            courseResourceFile.setFileRealName(filename);
            courseResourceFile.setVisionNumber(index);
            courseResourceFile.setModifyTime(now);
            List<CourseResourceFile> resourceStatus = courseResourceFileRepository.findByParentIdAndResourceStatus(courseResourceFile.getId(),
                    ResourceStatus.NORMAL);
            resourceStatus.forEach(file -> {
                file.setParentId(null);
            });
            courseResourceFileRepository.save(courseResourceFile);
            courseResourceFileRepository.saveAll(resourceStatus);
            return courseResourceFile;
        } else {
            return updateCourseResourceFileLiseListIsNoteEmptyNoteContain(courseResourceFile, filename, now, courseResources);
        }
    }

    private CourseResourceFile updateCourseResourceFileLiseListIsNoteEmptyNoteContain(CourseResourceFile courseResourceFile, String filename,
                                                                                      Date now, List<CourseResource> courseResources) {
        List<String> detailIds = courseResources.stream().map(CourseResource::getResourceDetailId).collect(Collectors.toList());
        List<CourseResourceFile> exitFiles = new ArrayList<>();
        List<CourseResourceFile> courseResourceFileList = courseResourceFileRepository.findByIdIn(detailIds);
        List<CourseResourceFile> files =
                courseResourceFileList.stream().filter(file -> !file.getId().equals(courseResourceFile.getId())).collect(toList());
        List<String> pids = new ArrayList<>();
        files.forEach(file -> {
            if (StringUtils.isAnyEmpty(file.getParentId())) {
                pids.add(file.getParentId());
            }
        });
        files.forEach(file -> {
            if (StringUtils.isAnyEmpty(file.getParentId()) || !pids.contains(file.getId())) {
                exitFiles.add(file);
            }
        });
        List<String> screenLeft = Arrays.stream(filename.split("\\(")).collect(toList());
        List<String> saveIndex = new ArrayList<>();
        for (String left : screenLeft) {
            List<String> collect = Arrays.stream(left.split("\\)")).collect(Collectors.toList());
            List<String> collect1 = collect.stream().filter(s -> StringUtils.isNumeric(s)).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(collect1)) {
                saveIndex.add(collect1.get(0));
            }
        }
        List<CourseResourceFile> loopFiles = new ArrayList<>();
        for (CourseResourceFile file : exitFiles) {
            List<String> indexNum = Arrays.stream(file.getFileRealName().split("\\(")).collect(Collectors.toList());
            List<String> index = new ArrayList<>();
            for (String string : indexNum) {
                List<String> collect = Arrays.stream(string.split("\\)")).collect(Collectors.toList());
                List<String> collect1 = collect.stream().filter(s -> StringUtils.isNumeric(s)).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(collect1)) {
                    index.add(collect1.get(0));
                }
            }
            if (index.size() == saveIndex.size() + 1) {
                loopFiles.add(file);
            }
        }
        courseResourceFile.setFileRealName(filename);
        courseResourceFile.setVisionNumber(0);
        courseResourceFile.setModifyTime(now);
        CourseResourceFile saveCourseResourceFile = courseResourceFileRepository.save(courseResourceFile);
        loopFiles.forEach(file -> {
            file.setParentId(saveCourseResourceFile.getId());
        });
        List<CourseResourceFile> resourceStatus = courseResourceFileRepository.findByParentIdAndResourceStatus(courseResourceFile.getId(),
                ResourceStatus.NORMAL);
        resourceStatus.forEach(file -> {
            file.setParentId(null);
        });
        courseResourceFileRepository.saveAll(resourceStatus);
        courseResourceFileRepository.saveAll(loopFiles);
        return saveCourseResourceFile;
    }


    /**
     * @param
     * @param name
     * @return 修改考核测验 精确查询
     */
    private CourseResource checkSameNamePaperUpdateClassTest(CourseResource courseResource, String name) {
        List<CourseResource> courseResourceList1 = checkSameNamePaper2(courseResource.getCreatorId(), courseResource.getCourseStructure(), name,
                courseResource.getIsPublic());
        List<CourseResource> resourceList =
                courseResourceList1.stream().filter(resource -> courseResource.getResourceType() == ResourceType.CLASS_TEST).collect(toList());
        if (CollectionUtils.isEmpty(resourceList)) {
            List<CourseResource> courseResourcesLike = updateCheckSameNamePaperLike(courseResource.getCreatorId(),
                    courseResource.getCourseStructure(), name, courseResource.getIsPublic());
            List<CourseResource> resourceListLike = courseResourcesLike.stream()
                    .filter(resource -> resource.getResourceType() == ResourceType.CLASS_TEST &&
                            !resource.getId().equals(courseResource.getId()))
                    .collect(toList());
            String realName = name;
            realName = StringUtils.substringAfterLast(realName, "(");
            realName = StringUtils.substringBeforeLast(realName, ")");
            if (CollectionUtils.isEmpty(resourceListLike)) {
                if (name.contains(")") && StringUtils.isNumeric(realName)) {
                    int index = Integer.parseInt(realName);
                    courseResource.setVisionNumber(index);
                    courseResource.setResourceName(name);
                    courseResource.setModifyTime(new Date());
                    courseResourceRepository.save(courseResource);
                    List<CourseResource> courseResources = courseResourceRepository.findByParentIdAndResourceStatus(courseResource.getId(),
                            ResourceStatus.NORMAL);
                    courseResources.forEach(courseResource1 -> {
                        courseResource1.setParentId(null);
                    });
                    courseResourceRepository.saveAll(courseResources);
                    return courseResource;
                } else {
                    courseResource.setVisionNumber(0);
                    courseResource.setResourceName(name);
                    courseResource.setModifyTime(new Date());
                    courseResourceRepository.save(courseResource);
                    List<CourseResource> courseResources = courseResourceRepository.findByParentIdAndResourceStatus(courseResource.getId(),
                            ResourceStatus.NORMAL);
                    courseResources.forEach(courseResource1 -> {
                        courseResource1.setParentId(null);
                    });
                    courseResourceRepository.saveAll(courseResources);
                    return courseResource;
                }
            } else {
                return isNoteEmptyToCourseResource(courseResource, name, resourceListLike, realName);
            }
        } else {
            this.setCourseResourceName(resourceList, courseResource, name);
            courseResource.setModifyTime(new Date());
            courseResourceRepository.save(courseResource);
            List<CourseResource> courseResources = courseResourceRepository.findByParentIdAndResourceStatus(courseResource.getId(),
                    ResourceStatus.NORMAL);
            courseResources.forEach(courseResource1 -> {
                courseResource1.setParentId(null);
            });
            courseResourceRepository.saveAll(courseResources);
            return courseResource;
        }
    }

    private CourseResource isNoteEmptyToCourseResource(CourseResource courseResource, String name, List<CourseResource> resourceListLike,
                                                       String realName) {
        List<CourseResource> exitCourseResources = new ArrayList<>();
        List<String> pIdS = new ArrayList<>();
        resourceListLike.forEach(resource -> {
            if (StringUtils.isAnyEmpty(resource.getParentId())) {
                pIdS.add(resource.getParentId());
            }
        });
        resourceListLike.forEach(resource -> {
            if (StringUtils.isAnyEmpty(resource.getParentId()) || !pIdS.contains(resource.getId())) {
                exitCourseResources.add(resource);
            }
        });
        if (name.contains(")") && StringUtils.isNumeric(realName)) {
            String nameLike = name;
            nameLike = StringUtils.substringBeforeLast(nameLike, "(");
            List<CourseResource> courseResourceList = updateCheckSameNamePaperLike(courseResource.getCreatorId(),
                    courseResource.getCourseStructure(), nameLike, courseResource.getIsPublic());
            if (CollectionUtils.isNotEmpty(courseResourceList)) {
                CourseResource resource = courseResourceList.get(0);
                courseResource.setParentId(resource.getId());
            }
            courseResource.setResourceName(name);
            int index = Integer.parseInt(realName);
            courseResource.setVisionNumber(index);
            courseResource.setModifyTime(new Date());
            courseResourceRepository.save(courseResource);
            List<CourseResource> courseResources = courseResourceRepository.findByParentIdAndResourceStatus(courseResource.getId(),
                    ResourceStatus.NORMAL);
            courseResources.forEach(courseResource1 -> {
                courseResource1.setParentId(null);
            });
            courseResourceRepository.saveAll(courseResources);
            return courseResource;
        } else {
            courseResource.setResourceName(name);
            courseResource.setVisionNumber(0);
            courseResource.setModifyTime(new Date());
            CourseResource save = courseResourceRepository.save(courseResource);
            List<String> screenLeft = Arrays.stream(name.split("\\(")).collect(toList());
            List<String> saveIndex = new ArrayList<>();
            for (String left : screenLeft) {
                List<String> collect = Arrays.stream(left.split("\\)")).collect(Collectors.toList());
                List<String> collect1 = collect.stream().filter(s -> StringUtils.isNumeric(s)).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(collect1)) {
                    saveIndex.add(collect1.get(0));
                }
            }
            List<CourseResource> loopCourseResources = new ArrayList<>();
            for (CourseResource resource : exitCourseResources) {
                List<String> indexNum = Arrays.stream(resource.getResourceName().split("\\(")).collect(Collectors.toList());
                List<String> index = new ArrayList<>();
                for (String string : indexNum) {
                    List<String> collect = Arrays.stream(string.split("\\)")).collect(Collectors.toList());
                    List<String> collect1 = collect.stream().filter(s -> StringUtils.isNumeric(s)).collect(Collectors.toList());
                    if (CollectionUtils.isNotEmpty(collect1)) {
                        index.add(collect1.get(0));
                    }
                }
                if (index.size() == saveIndex.size() + 1) {
                    loopCourseResources.add(resource);
                }
            }
            loopCourseResources.forEach(resource -> resource.setParentId(save.getId()));
            courseResourceRepository.saveAll(loopCourseResources);
            List<CourseResource> courseResources =
                    courseResourceRepository.findByParentIdAndResourceStatus(courseResource.getId(), ResourceStatus.NORMAL);
            courseResources.forEach(courseResource1 -> {
                courseResource1.setParentId(null);
            });
            courseResourceRepository.saveAll(courseResources);
            return courseResource;
        }
    }


    private CourseResource setCourseResourceName(List<CourseResource> courseResources, CourseResource courseResource, String name) {
        List<CourseResource> judgeCourseResources = new ArrayList<>();
        courseResources.forEach(resource -> {
            if (resource.getResourceName().equals(name) && !resource.getId().equals(courseResource.getId())) {
                judgeCourseResources.add(resource);
            }
        });
        CourseResource existCourseResource = judgeCourseResources.get(0);
        List<CourseResource> existCourseResources = courseResourceRepository.findByParentIdAndResourceStatus(existCourseResource.getId(),
                ResourceStatus.NORMAL);
        List<CourseResource> courseResourceList =
                existCourseResources.stream().filter(resource -> !resource.getId().equals(courseResource.getId())).collect(toList());
        courseResourceList.sort(Comparator.comparingInt(CourseResource::getVisionNumber));
        List<Integer> nowVisionNumberList = courseResourceList
                .stream()
                .map(CourseResource::getVisionNumber)
                .distinct()
                .collect(Collectors.toList());
        int maxNowVisionNumber = nowVisionNumberList.stream().max(Integer::compareTo).orElse(0);
        courseResource.setVisionNumber(maxNowVisionNumber + 1);
        courseResource.setParentId(existCourseResource.getId());
        courseResource.setResourceName(name + "(" + (maxNowVisionNumber + 1) + ")");
        return courseResource;
    }


    private List<CourseResource> updateCheckSameNamePaperLike(String creatorId, CourseStructure courseStructure, String name, Boolean isPublic) {
        if (name.contains("(")) {
            name = StringUtils.substringBeforeLast(name, "(");
        }
        List<CourseResource> courseResources = courseResourceRepository.findAll(CourseResourceSpecification.testPaperSpecificationlike(creatorId,
                courseStructure.getCourse(), name, isPublic, courseStructure.getCourseVersion()));
        return courseResources;
    }


    /**
     * 新增模糊判断重复
     **/
    private List<CourseResource> checkSameNamePaperLike(String creatorId, CourseStructure courseStructure,
                                                        String name, boolean isIsPublic, String fileType, String resourceId) {
        if (name.contains("(")) {
            name = StringUtils.substringBeforeLast(name, "(");
        }
        List<CourseResource> courseResources = courseResourceRepository.findAll(
                CourseResourceSpecification.specificationlike(creatorId, courseStructure.getCourse().getId(), name, isIsPublic,
                        courseStructure.getCourseVersion(), courseStructure));
        courseResources = courseResources.stream().filter(c -> !resourceId.equals(c.getId())).collect(Collectors.toList());
        return courseResources;
    }

    /**
     * 新增判断重复
     **/
    private List<CourseResource> checkSameNamePaper(String creatorId, CourseStructure courseStructure,
                                                    String name, boolean isIsPublic, String fileType, String resourceId) {
        List<CourseResource> courseResources = courseResourceRepository.findAll(
                CourseResourceSpecification.specification(creatorId, courseStructure.getCourse().getId(), name, isIsPublic,
                        courseStructure.getCourseVersion(), courseStructure));
        courseResources = courseResources.stream().filter(c -> !resourceId.equals(c.getId())).collect(Collectors.toList());
        return courseResources;
    }

    private List<CourseResource> checkSameNamePaper2(String creatorId, CourseStructure courseStructure, String name, Boolean isPublic) {
        List<CourseResource> courseResources = courseResourceRepository.findAll(
                CourseResourceSpecification.testPaperSpecification(creatorId, courseStructure.getCourse(), name, isPublic,
                        courseStructure.getCourseVersion()));
        return courseResources;
    }

    //region 接口-老师退出教学团队
    @Transactional
    public ResponseEntity<Void> deleteCourseTeachingTeam(String courseTeachingTeamId, String userId, String userName) {
        Boolean flag = false;
        CourseTeachingTeam courseTeachingTeam = courseTeachingTeamRepository.findById(courseTeachingTeamId).orElse(null);
        if (ObjectUtils.isEmpty(courseTeachingTeam)) {
            return new ResponseEntity(ErrorResult.dataNotExistError("教师团队"), HttpStatus.NOT_FOUND);
        }
        CourseVersion courseVersion = courseTeachingTeam.getCourseVersion();
        if (ObjectUtils.isEmpty(courseVersion) || !CourseVersionStatus.IN_USE
                .equals(courseVersion.getCourseVersionStatus())) {
            return new ResponseEntity(ErrorResult.customError("无法退出，当前版本已归档，请刷新页面"), HttpStatus.CONFLICT);
        }
        if (!Objects.equals(userId, courseTeachingTeam.getCourse().getCourseLeaderId())) {
            if (!Objects.equals(userId, courseTeachingTeam.getTeacherId())) {
                return new ResponseEntity(ErrorResult.customError("没有权限使该老师退出教学团队"), HttpStatus.CONFLICT);
            }
        }
        courseTeachingTeamRepository.delete(courseTeachingTeam);

        if (courseTeachingTeam.getTeacherDataSource().getValue() == TeacherDataSource.OFF_CAMPUS_USER.getValue()) {
            teachingTeamTeacherRepository.deleteByTeacherId(courseTeachingTeam.getId());
        }

        if (dataVisualEnable) {
            eventService.sendDeleteCourseTeachingTeamEvent(courseTeachingTeam.getId());
        }
        List<CourseTeachingTeam> courseTeachingTeams =
                courseTeachingTeamRepository.findByTeacherTypeAndCourseAndCourseVersion(TeacherType.TEACHER,
                        courseTeachingTeam.getCourse(), courseVersion);
        if (CollectionUtils.isEmpty(courseTeachingTeams)) {
            courseCompletionService.saveCourseCompletion(courseTeachingTeam.getCourse(), CourseContentTypeEnum.COURSE_TEAM_USER, false, userId,
                    userName);
            flag = true;
        }
        if (flag && dataVisualEnable) {
            //TODO 教师退出团队后没人时推送完成度事件变更
//
//            Course course = courseTeachingTeam.getCourse();
//            courseService.createCourseEvent(course);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //endregion

    //region 接口-查询课程列表
    public ResponseEntity<List<CourseInfoResource>> getCourseInfoResources(String teacherId, String courseNameOrCode) {
        List<CourseTeachingTeam> courseTeachingTeams = courseTeachingTeamRepository.findByTeacherIdOrderByCourseId(teacherId);
        List<String> courseIds = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(courseTeachingTeams)) {
            courseIds.addAll(courseTeachingTeams.stream().map(courseTeachingTeam ->
                    courseTeachingTeam.getCourse().getId()).collect(Collectors.toList()));
            courseIds = courseIds.stream().distinct().collect(Collectors.toList());
        }
        List<Course> courses = courseRepository.findAll(courseNameOrCodeSpecification(courseNameOrCode, courseIds));
        List<CourseInfoResource> courseInfoResources = new ArrayList<>();
        courseInfoResources.addAll(courses.stream().map(course -> new CourseInfoResource() {{
            this.courseId(course.getId());
            this.courseCode(course.getCourseCode());
            this.courseName(course.getCourseName());
            this.collegeCode(course.getCollegeCode());
            this.collegeName(course.getCollegeName());
        }}).collect(Collectors.toList()));
        return new ResponseEntity<>(courseInfoResources, HttpStatus.OK);
    }
    //endregion

    //region 接口-资源移动
    public ResponseEntity<Void> moveToCourseStructure(String courseResourceIds, String courseStructureId, String userId) {
        List<CourseResource> courseResources = getCourseResources(courseResourceIds);
        if (CollectionUtils.isEmpty(courseResources)) {
            return new ResponseEntity(ErrorResult.customError("课程资源未找到"), HttpStatus.NOT_FOUND);
        }
        List<CourseResource> resources = getCourseResourceList(courseResources, userId);
        if (resources.size() > 0) {
            String resourceNames = resources.stream()
                    .map(CourseResource::getResourceName)
                    .collect(Collectors.joining(","));
            return new ResponseEntity(ErrorResult.customError("名称为" + resourceNames + "的课程资源不属于您的资源，无法移动"), HttpStatus.CONFLICT);
        }
        CourseStructure courseStructure = courseStructureRepository.findByIdAndStructureStatus(courseStructureId, StructureStatus.NORMAL);
        if (ObjectUtils.isEmpty(courseStructure)) {
            return new ResponseEntity(ErrorResult.customError("移动失败，课程结构已被删除"), HttpStatus.CONFLICT);
        }
        List<CourseResource> courseResourceList = new ArrayList<>();

        CourseResource resource = courseResourceRepository.findTop1ByCreatorIdAndCourseStructureIdAndResourceStatusOrderBySortDesc(
                userId,
                courseStructure.getId(),
                ResourceStatus.NORMAL);
        CourseResource auxiliarySortResource =
                courseResourceRepository.findTop1ByCourseStructureIdAndIsPublicAndResourceStatusOrderByAuxiliarySortDesc(
                        courseStructure.getId(), true, ResourceStatus.NORMAL);
        for (int i = 0; i < courseResources.size(); i++) {
            CourseResource courseResource = courseResources.get(i);
            courseResource.setCourseStructure(courseStructure);
            courseResource.setModifyTime(new Date());
            if (ObjectUtils.isNotEmpty(resource)) {
                courseResource.setSort(resource.getSort() + i + 1);
            } else {
                courseResource.setSort(1 + i);
            }
            if (courseResource.getIsPublic()) {
                if (ObjectUtils.isNotEmpty(auxiliarySortResource)) {
                    courseResource.setAuxiliarySort(auxiliarySortResource.getAuxiliarySort() + i + 1);
                } else {
                    courseResource.setAuxiliarySort(i);
                }
            }
            courseResourceList.add(courseResource);
        }
        courseResourceRepository.saveAll(courseResourceList);
        if (dataVisualEnable) {
            sendCourseResourceList(courseResourceList);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Async
    public void sendCourseResourceList(List<CourseResource> courseResourceList) {
        courseResourceList.forEach(courseResource -> {
            courseService.sendCourseResourceEvent(courseResource);
        });
    }

    private List<CourseResource> getCourseResources(String courseResourceIds) {
        List<String> ids = Arrays.asList(courseResourceIds.split(","));
        return courseResourceRepository.findByIdInAndResourceStatus(ids, ResourceStatus.NORMAL);
    }

    private List<CourseResource> getCourseResourceList(List<CourseResource> courseResources, String userId) {
        return courseResources.stream()
                .filter(courseResource -> !userId.equals(courseResource.getCreatorId()))
                .collect(Collectors.toList());
    }

    //endregion
    //region 接口-获取课程下结构及资源
    public ResponseEntity<List<CourseStructureResource>> getCourseResources(String courseId, String userId) {
        Course course = courseRepository.findByIdAndUseStateIsTrue(courseId).orElse(null);
        if (ObjectUtils.isEmpty(course)) {
            return new ResponseEntity(ErrorResult.customError("未找到课程信息"), HttpStatus.NOT_FOUND);
        }
        List<CourseTeachingTeam> courseTeachingTeams = course.getCourseTeachingTeams();
        CourseTeachingTeam teacher = courseTeachingTeams
                .stream()
                .filter(courseTeachingTeam -> courseTeachingTeam.getTeacherId().equals(userId))
                .findFirst()
                .orElse(null);
        if (ObjectUtils.isEmpty(teacher)) {
            return new ResponseEntity(ErrorResult.customError("请先加入教学团队"), HttpStatus.CONFLICT);
        }
        List<CourseStructureResource> courseStructureResources = new ArrayList<>();
        List<CourseStructure> courseStructures = course.getCourseStructures()
                .stream()
                .filter(courseStructure -> courseStructure.getStructureStatus().equals(StructureStatus.NORMAL))
                .collect(Collectors.toList());
        if (courseStructures.size() == 0) {
            return new ResponseEntity(ErrorResult.customError("当前课程无课程结构"), HttpStatus.CONFLICT);
        }
        List<CourseResource> courseResources = new ArrayList<>();
        //获取最上层课程结构
        List<CourseStructure> topStructures = courseStructures
                .stream()
                .filter(courseStructure -> courseStructure.getParentId() == null)
                .sorted(Comparator.comparing(CourseStructure::getShowOrder))
                .collect(Collectors.toList());
        courseStructures.forEach(structure -> {
            courseResources.addAll(structure.getCourseResources()
                    .stream()
                    .filter(courseResource -> (courseResource.getIsPublic()
                            && courseResource.getResourceStatus().equals(ResourceStatus.NORMAL)
                            && !courseResource.getResourceType().equals(ResourceType.CLASS_TEST)) ||
                            (!courseResource.getIsPublic() && userId.equals(courseResource.getCreatorId())
                                    && courseResource.getResourceStatus().equals(ResourceStatus.NORMAL))
                                    && !courseResource.getResourceType().equals(ResourceType.CLASS_TEST))
                    .collect(Collectors.toList()));
        });
        List<String> detailIds = courseResources
                .stream()
                .map(CourseResource::getResourceDetailId)
                .collect(Collectors.toList());
        List<CourseResourceFile> courseResourceFiles = courseResourceFileRepository.findByIdIn(detailIds);
        courseStructureResources = buildCourseStructureResourceList(topStructures, courseStructures, courseResources, courseResourceFiles);
        return new ResponseEntity<>(courseStructureResources, HttpStatus.OK);
    }

    private List<CourseStructureResource> buildCourseStructureResourceList(List<CourseStructure> topStructures,
                                                                           List<CourseStructure> courseStructures,
                                                                           List<CourseResource> courseResources,
                                                                           List<CourseResourceFile> courseResourceFiles) {
        List<CourseStructureResource> courseStructureResources = new ArrayList<>();
        topStructures.forEach(courseStructure -> {
            CourseStructureResource courseStructureResource = new CourseStructureResource();
            courseStructureResource.setStructureId(courseStructure.getId());
            courseStructureResource.setParentId(courseStructure.getParentId() == null ? "" : courseStructure.getParentId());
            courseStructureResource.setStructureName(courseStructure.getCourseStructureName());
            courseStructureResource.setShowOrder(courseStructure.getShowOrder());
            List<CourseStructure> structures = courseStructures
                    .stream()
                    .filter(structure -> courseStructure.getId().equals(structure.getParentId()))
                    .sorted(Comparator.comparing(CourseStructure::getShowOrder))
                    .collect(Collectors.toList());
            courseStructureResource.setChildStructureList
                    (buildCourseStructureResourceList(structures, courseStructures, courseResources, courseResourceFiles));
            List<CourseResource> resources = courseResources
                    .stream()
                    .filter(courseResource -> courseResource.getCourseStructure().getId().equals(courseStructure.getId()))
                    .sorted(Comparator.comparing(CourseResource::getModifyTime))
                    .collect(Collectors.toList());
            List<CourseResourceFile> resourceFiles = new ArrayList<>();
            resources.forEach(courseResource -> {
                CourseResourceFile file = courseResourceFiles
                        .stream()
                        .filter(courseResourceFile -> courseResource.getResourceDetailId().equals(courseResourceFile.getId()))
                        .findFirst()
                        .orElse(null);
                if (ObjectUtils.isNotEmpty(file)) {
                    resourceFiles.add(file);
                }
            });
            courseStructureResource.setCourseResourceList(getCourseResourceInfos(resources, resourceFiles));
            courseStructureResources.add(courseStructureResource);
        });
        return courseStructureResources;
    }

    private List<CourseResourceInfo> getCourseResourceInfos(List<CourseResource> resources, List<CourseResourceFile> resourceFiles) {
        return resources.stream().map(courseResource -> new CourseResourceInfo() {{
            this.setResourceId(courseResource.getId());
            this.setResourceName(courseResource.getResourceName());
            CourseResourceFile file = resourceFiles
                    .stream()
                    .filter(courseResourceFile -> courseResourceFile.getId().equals(courseResource.getResourceDetailId()))
                    .findFirst()
                    .orElse(null);
            if (ObjectUtils.isNotEmpty(file)) {
                this.setFileSavedName(file.getFileSavedName());
                this.setInnerIp(file.getInnerIp());
                this.setOuterIp(file.getOuterIp());
                this.setFilePath(file.getFilePath());
                this.setFileType(file.getFileType());
                this.setFileSize(String.valueOf(file.getFileSize()));
            } else {
                this.setFileSavedName("");
                this.setInnerIp("");
                this.setOuterIp("");
                this.setFilePath("");
                this.setFileSize("");
                this.setFileType("");
            }
        }}).collect(Collectors.toList());
    }

    //region 接口-批量上传资源到备课包
    @Transactional
    public ResponseEntity uploadResourceList(UploadResourceParam param) {
        Date now = new Date();
        CourseStructure courseStructure = courseStructureRepository
                .findById(param.getCourseStructureId()).orElse(null);
        if (ObjectUtils.isEmpty(courseStructure)) {
            return new ResponseEntity(ErrorResult.customError("未找到课程结构信息"), HttpStatus.NOT_FOUND);
        }
        List<CourseResourceFile> saveCourseResourceFileList = new ArrayList<>();
        param.getCourseResourceList().forEach(item -> {
            CourseResourceFile courseResourceFile = new CourseResourceFile();
            courseResourceFile.setFilePath(item.getFilepath());
            courseResourceFile.setResourceStatus(ResourceStatus.NORMAL);
            courseResourceFile.setFileSize(item.getFileSize());
            courseResourceFile.setFileType(item.getFileType());
            courseResourceFile.setInnerIp(item.getInnerServerIp());
            courseResourceFile.setOuterIp(item.getOuterServerIp());
            courseResourceFile.setIsPublic(Boolean.FALSE);
            courseResourceFile.setFileRealName(item.getFileRealName());
            courseResourceFile.setFileSavedName(item.getFileSavedName());
            courseResourceFile.setCreatorId(param.getUserId());
            courseResourceFile.setCreateTime(now);
            courseResourceFile.setCreatorName(param.getUserName());
            courseResourceFile.setModifierId(param.getUserId());
            courseResourceFile.setModifierName(param.getUserName());
            courseResourceFile.setModifyTime(now);
            courseResourceFile.setVisionNumber(courseStructure.getCourseVersion().getVersionSerialNumber());
            saveCourseResourceFileList.add(courseResourceFile);
        });
        List<String> resourceNames = extendCourseManagementService.getAllResourceNames(param.getUserId(), courseStructure, false);
        this.buildResourceVo(saveCourseResourceFileList, resourceNames);
        saveCourseResourceFileList.forEach(file -> {
            if (transcodeService.isTransCodeVideoType(file.getFileType())) {
                file.setTranscodeStatus(TranscodeStatus.TRANS_CODING);
            } else {
                file.setTranscodeStatus(TranscodeStatus.NOT_TRANSCODE);
            }

            file.setId(null);
        });

        List<CourseResourceFile> filesList = courseResourceFileRepository.saveAll(saveCourseResourceFileList);
        List<CourseResource> courseResourceList = courseStructure.getCourseResources();
        saveCourseResourceList(param, courseStructure, filesList, now, courseResourceList);

        List<CourseResourceFile> transcodeFiles = new ArrayList<>();
        filesList.forEach(file -> {
            if (transcodeService.isTransCodeVideoType(file.getFileType())) {
                transcodeFiles.add(file);
            }
        });

        if (transcodeFiles.size() > 0) {
            transcodeService.sendTranscodeMqRequestMessage(transcodeFiles);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    private List<ResourceVo> buildResourceVo(List<CourseResourceFile> files, List<String> fileNames) {
        List<ResourceVo> resourceVos = new ArrayList<>();
        if (CollectionUtils.isEmpty(files)) {
            return resourceVos;
        }
        files.forEach(file -> {
            ResourceVo resourceVo = new ResourceVo();
            resourceVo.setId(file.getId());
            resourceVo.setFilepath(file.getFilePath());
            String newName = buildResourceFileNewName(fileNames, file);
            file.setFileRealName(newName);
            resourceVo.setFileRealName(newName);
            resourceVo.setFileSavedName(file.getFileSavedName());
            resourceVo.setFileType(file.getFileType());
            resourceVo.setInnerServerIp(file.getInnerIp());
            resourceVo.setOuterServerIp(file.getOuterIp());
            resourceVo.setFileSize(file.getFileSize());
            resourceVos.add(resourceVo);
        });


        return resourceVos;
    }

    public String checkZipName(List<String> structureNames, String fileName, int j) {
        if (!structureNames.contains(fileName)) {
            return fileName;
        }
        if (decide(fileName)) {//文件名字没有(2)的形式
            return fileName = checkZipName(structureNames, fileName + "(" + j + ")", j++); //递归生成文件名字
        }
        String numCountString = fileName.substring(fileName.lastIndexOf("(") + 1, fileName.lastIndexOf(")"));
        int numCount = -1;
        if (Integer.valueOf(numCountString) != null) {
            numCount = Integer.parseInt(numCountString);
        }
        if (numCount >= 1) { //是数字，不是字符串，截取(x)之前的字符  在numCount这个数字的基础上实现自增
            String realName = fileName.substring(0, fileName.lastIndexOf("("));
            final int two = 2;
            return fileName = checkZipName(structureNames, realName + "(" + ++numCount + ")", two); //递归生成文件名字
        }
        return fileName = checkZipName(structureNames, fileName + "(" + j + ")", j++); //递归生成文件名字
    }

    public boolean decide(String fileName) {
        if (fileName.lastIndexOf("(") < 0) {
            return true;
        }
        if (fileName.lastIndexOf(")") < 0) {
            return true;
        }
        if ((fileName.length() - 1) > fileName.lastIndexOf(")")) {
            return true;
        }
        if ((fileName.lastIndexOf(")") - fileName.lastIndexOf("(")) <= 1) {
            return true;
        }
        return false;
    }


    //endregion
    @Transactional
    public void saveCourseResourceList(UploadResourceParam param, CourseStructure courseStructure, List<CourseResourceFile> courseResourceFiles,
                                       Date now, List<CourseResource> courseResourceList) {
        List<String> resourceNames = new ArrayList<>();
        List<String> resourceIds = courseResourceList.stream().filter(courseResource ->
                        courseResource.getResourceStatus().equals(ResourceStatus.NORMAL))
                .map(CourseResource::getResourceDetailId).collect(toList());
        List<CourseResourceFile> courseResourceFileList = courseResourceFileRepository.findByIdIn(resourceIds);
        courseResourceList.forEach(courseResource -> {
            courseResourceFileList.stream().filter(courseResourceFile -> courseResourceFile.getId().equals(courseResource.getResourceDetailId()))
                    .forEach(courseResourceFile -> {
                        if (courseResource.getResourceType().equals(ResourceType.IMAGE) ||
                                courseResource.getResourceType().equals(ResourceType.MICRO_VIDEO) ||
                                courseResource.getResourceType().equals(ResourceType.TEACHING_COURSE_WARE)) {
                            String fileName = courseResource.getResourceName();
                            if (fileName.indexOf("(") > 0 && fileName.lastIndexOf(")") > 0) {
                                String suffix = fileName.substring(fileName.indexOf("("));
                                String originallyName = fileName.replace(suffix, "");
                                resourceNames.add(originallyName + "." + courseResourceFile.getFileType() + suffix);
                            } else {
                                resourceNames.add(courseResource.getResourceName() + "." + courseResourceFile.getFileType());
                            }
                        } else {
                            resourceNames.add(courseResource.getResourceName());
                        }
                    });
        });
        List<CourseResource> courseResources = new ArrayList<>();
        CourseResource resource = courseResourceRepository.findTop1ByCreatorIdAndCourseStructureIdAndResourceStatusOrderBySortDesc(
                param.getUserId(),
                courseStructure.getId(),
                ResourceStatus.NORMAL);
        Integer sort;
        if (ObjectUtils.isNotEmpty(resource)) {
            sort = resource.getSort() + 1;
        } else {
            sort = 0;
        }
        for (int i = 0; i < courseResourceFiles.size(); i++) {
            CourseResource courseResource = new CourseResource();
            courseResource.setCreateTime(now);
            courseResource.setCreatorId(param.getUserId());
            courseResource.setCreatorName(param.getUserName());
            courseResource.setModifyTime(now);
            courseResource.setModifierId(param.getUserId());
            courseResource.setModifierName(param.getUserName());
            courseResource.setVisionNumber(courseResourceFiles.get(i).getVisionNumber());
            courseResource.setIsPublic(false);
            courseResource.setResourceStatus(ResourceStatus.NORMAL);
            courseResource.setCourseStructure(courseStructure);

            String fileType = courseResourceFiles.get(i).getFileType();
            if (ALLOWED_VIDEO_TYPE.contains(fileType.toLowerCase())) {
                courseResource.setResourceType(ResourceType.MICRO_VIDEO);
            } else if (ALLOWED_PIC_TYPE.contains(fileType.toLowerCase())) {
                courseResource.setResourceType(ResourceType.IMAGE);
            } else if (Arrays.asList(ALLOWED_AUDIO_TYPE.split(",")).stream()
                    .filter(s -> s.equals(fileType.toLowerCase())).count() > 0) {
                courseResource.setResourceType(ResourceType.AUDIO);
            } else {
                courseResource.setResourceType(ResourceType.TEACHING_COURSE_WARE);
            }
            courseResource.setResourceDetailId(courseResourceFiles.get(i).getId());
            courseResource.setResourceSize(courseResourceFiles.get(i).getFileSize());
            courseResource.setResourceName(courseResourceFiles.get(i).getFileRealName());
            courseResource.setResourceReferences(1);
            courseResource.setResourceOtherReferences(0);
            courseResource.setResourceName(courseResourceFiles.get(i).getFileRealName());
            courseResource.setSourceType(ResourceSourceType.USER_ADDED);
            courseResource.setCourseId(courseStructure.getCourse().getId());
            courseResource.setResourceContentNum(1);
            courseResource.setSort(sort + i);
            courseResources.add(courseResource);
        }
        courseResourceRepository.saveAll(courseResources);
    }

    public String getRepeatResourceName(List<String> resourceNames, CourseResourceFile courseResourceFile, CourseResource courseResource) {
        String finalName = "";
        if (courseResource.getResourceType().equals(ResourceType.IMAGE) ||
                courseResource.getResourceType().equals(ResourceType.MICRO_VIDEO) ||
                courseResource.getResourceType().equals(ResourceType.TEACHING_COURSE_WARE)) {
            String fileName = courseResource.getResourceName();
            if (fileName.indexOf("(") > 0 && fileName.lastIndexOf(")") > 0) {
                String suffix = fileName.substring(fileName.indexOf("("));
                String originallyName = fileName.replace(suffix, "");
                finalName = originallyName + "." + courseResourceFile.getFileType() + suffix;
            } else {
                finalName = courseResource.getResourceName() + "." + courseResourceFile.getFileType();
            }
        } else {
            finalName = courseResource.getResourceName();
        }
        AtomicReference<String> newName = new AtomicReference<>(checkZipName(resourceNames, finalName, 1));
        String copyFinalName = finalName;
        resourceNames.stream().filter(item -> item.equals(copyFinalName)).forEach(item -> {
            if (resourceNames.contains(copyFinalName + "(1)")) {
                newName.set(checkZipName(resourceNames, copyFinalName + "(1)", 1));
            } else {
                newName.set(copyFinalName + "(1)");
            }
        });
        finalName = newName.get();
        resourceNames.add(finalName);
        if (finalName.indexOf(".") > 0 && finalName.indexOf("(") > 0) {
            String suffixType = finalName.substring(finalName.indexOf("."), finalName.indexOf("("));
            finalName = finalName.replace(suffixType, "");
        } else if (finalName.indexOf(".") > 0 && finalName.indexOf("(") < 0) {
            String suffixType = finalName.substring(finalName.indexOf("."));
            finalName = finalName.replace(suffixType, "");
        }

        return finalName;
    }

    private List<CourseResourceFile> saveCourseResourceFileList(UploadResourceParam param, Date now) {
        List<CourseResourceFile> courseResourceFiles = new ArrayList<>();
        param.getCourseResourceList().forEach(resourceVo -> {
            CourseResourceFile courseResourceFile = new CourseResourceFile();
            courseResourceFile.setId(resourceVo.getId());
            courseResourceFile.setCreateTime(now);
            courseResourceFile.setCreatorId(param.getUserId());
            courseResourceFile.setCreatorName(param.getUserName());
            courseResourceFile.setModifyTime(now);
            courseResourceFile.setModifierId(param.getUserId());
            courseResourceFile.setModifierName(param.getUserName());
            courseResourceFile.setResourceStatus(ResourceStatus.NORMAL);
            courseResourceFile.setFilePath(resourceVo.getFilepath());
            courseResourceFile.setFileRealName(resourceVo.getFileRealName());
            courseResourceFile.setFileSavedName(resourceVo.getFileSavedName());
            courseResourceFile.setFileSize(resourceVo.getFileSize());
            courseResourceFile.setFileType(resourceVo.getFileType());
            courseResourceFile.setInnerIp(resourceVo.getInnerServerIp());
            courseResourceFile.setOuterIp(resourceVo.getOuterServerIp());
            courseResourceFile.setIsPublic(false);
            courseResourceFiles.add(courseResourceFile);
        });
        return courseResourceFiles;
    }

    private List<CourseResourceFile> judgeRepeatResource(List<CourseResourceFile> courseResourceFiles, String courseId, String creatorId) {
        List<CourseResourceFile> repeatCourseResourceFileList = new ArrayList<>();
        List<CourseResource> courseResourceList = courseResourceRepository.findAll(CourseResourceSpecification.getSpecification(creatorId, courseId));
        List<String> detailIds = courseResourceList.stream().map(CourseResource::getResourceDetailId).collect(Collectors.toList());
        List<CourseResourceFile> courseResourceFileList = courseResourceFileRepository.findByIdIn(detailIds);
        if (CollectionUtils.isEmpty(courseResourceFileList)) {//获取备课包数据是否存在
            return new ArrayList<>();
        }
        courseResourceFiles.forEach(courseResourceFile -> {
            CourseResourceFile courseResourceFile1 =
                    courseResourceFileList.stream().filter(courseResourceFile2 ->
                                    courseResourceFile2.getFileRealName().equals(courseResourceFile.getFileRealName())
                                            && courseResourceFile2.getFileType().equals(courseResourceFile.getFileType()))
                            .findFirst().orElse(null);
            if (ObjectUtils.isNotEmpty(courseResourceFile1)) {
                repeatCourseResourceFileList.add(courseResourceFile1);
            }
        });
        return courseResourceFileList;
    }

    public void reorderCourseResource(ReorderParams reorderParams) {
        Date now = new Date();
        // 拿到需要移动的题和目标位置之前的题的ID
        String needToBeMovedResourceId = reorderParams.getNeedToBeMovedResourceId();
        Optional<CourseResource> needToBeMovedResource = courseResourceRepository.findById(needToBeMovedResourceId);
        CourseResource currentResource = needToBeMovedResource.get();
        Integer currentResourceSort = currentResource.getAuxiliarySort();
        // 判断之前位置的ID是否为空
        if (ObjectUtils.isEmpty(reorderParams.getTargetLocationBeforeResourceId())) {
            // 为空置顶
            CourseResource courseResource = courseResourceRepository
                    .findTop1ByCourseStructureIdAndIsPublicAndResourceStatusOrderByAuxiliarySortDesc(
                            reorderParams.getCourseStructureId(), true,
                            ResourceStatus.NORMAL
                    );
            courseResourceRepository.forwardPublicByCourseIdWithIndexBetween(
                    reorderParams.getCourseStructureId(),
                    currentResourceSort, courseResource.getAuxiliarySort() + 1
            );
            currentResource.setAuxiliarySort(courseResource.getAuxiliarySort());
        } else {
            // 不为空
            // 拿到目标题和目标前一个题的索引，判断是前移还是后移
            CourseResource targetLocationBeforeQuestion = courseResourceRepository
                    .findById(reorderParams.getTargetLocationBeforeResourceId())
                    .get();
            Integer targetLocationBeforeSort = targetLocationBeforeQuestion.getAuxiliarySort();
            int directionOfMovement = targetLocationBeforeSort - currentResourceSort;
            if (directionOfMovement < 0) {
                // 后移一位
                if (directionOfMovement == -1) {
                    int tmpLocation = targetLocationBeforeSort;
                    targetLocationBeforeQuestion.setAuxiliarySort(currentResourceSort);
                    courseResourceRepository.save(targetLocationBeforeQuestion);
                    currentResource.setAuxiliarySort(tmpLocation);
                } else {
                    // 后移
                    courseResourceRepository.backwardPublicByCourseIdWithIndexBetween(
                            reorderParams.getCourseStructureId(),
                            targetLocationBeforeSort - 1, currentResourceSort);
                    currentResource.setAuxiliarySort(targetLocationBeforeSort);
                }
            } else {
                // 前移一位
                if (directionOfMovement == Constant.TWO) {
                    CourseResource nextResource = courseResourceRepository
                            .findById(reorderParams.getTargetLocationAfterResourceId())
                            .get();
                    int tmpLocation = nextResource.getAuxiliarySort();
                    nextResource.setAuxiliarySort(currentResourceSort);
                    courseResourceRepository.save(nextResource);
                    currentResource.setAuxiliarySort(tmpLocation);
                } else {
                    // 前移
                    CourseResource nextResource = courseResourceRepository
                            .findById(reorderParams.getTargetLocationAfterResourceId())
                            .get();
                    int targetLocationAfterSort = nextResource.getAuxiliarySort();

                    courseResourceRepository.forwardPublicByCourseIdWithIndexBetween(
                            reorderParams.getCourseStructureId(),
                            currentResourceSort, targetLocationAfterSort + 1
                    );
                    currentResource.setAuxiliarySort(targetLocationAfterSort);
                }
            }
        }
        currentResource.setModifyTime(now);
        courseResourceRepository.save(currentResource);
    }

}
