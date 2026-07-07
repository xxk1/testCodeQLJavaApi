package com.lztech.site.service.courseconstruction;

import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.course.CourseCompletion;
import com.lztech.domain.model.course.CourseVersion;
import com.lztech.domain.model.course.enums.CourseContentTypeEnum;
import com.lztech.domain.model.course.enums.CourseVersionStatus;
import com.lztech.persistence.repositories.course.CourseCompletionRepository;
import com.lztech.persistence.repositories.courseversion.CourseVersionRepository;
import com.lztech.site.service.event.EventService;
import com.lztech.site.until.DateUtils;
import com.lztech.site.viewmodel.coursemanagement.CourseCompletionDetailResource;
import com.lztech.site.viewmodel.coursemanagement.CourseCompletionResource;
import com.lztech.site.viewmodel.event.CourseVersionEvent;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.lztech.site.constants.ConstantDataVisual.DATA_VISUAL_SOURCE_CLOUD_CLASSROOM;


@Service
public class CourseCompletionService {

    @Autowired
    private CourseCompletionRepository courseCompletionRepository;

    @Value("${dataVisual.enable}")
    private Boolean dataVisualEnable;
    @Autowired
    private EventService eventService;

    @Autowired
    private CourseVersionRepository courseVersionRepository;

    public synchronized CourseCompletion saveCourseCompletion(Course course,
                                                              CourseContentTypeEnum type,
                                                              boolean isCompleted,
                                                              String userId,
                                                              String userName) {
        CourseCompletion courseCompletion = courseCompletionRepository.findByCourseAndCourseContentEnum(course, type).orElse(null);
        CourseVersion courseVersion = courseVersionRepository.findByCourseIdAndVersionStatus(course.getId(),
                CourseVersionStatus.IN_USE.getValue()).stream().findFirst().orElse(null);
        Date now = new Date();
        if (Objects.isNull(courseCompletion)) {
            courseCompletion = new CourseCompletion();
            courseCompletion.setCourseVersion(courseVersion);
            courseCompletion.setCreatorId(userId);
            courseCompletion.setCreatorName(userName);
            courseCompletion.setCreateTime(now);

        } else {
            if (isCompleted == courseCompletion.isCompleted()) {
                return courseCompletion;
            }
        }
        courseCompletion.setCourseVersion(courseVersion);
        courseCompletion.setCourseContentEnum(type);
        courseCompletion.setCompleted(isCompleted);
        courseCompletion.setCourse(course);
        courseCompletion.setModifierId(userId);
        courseCompletion.setModifierName(userName);
        courseCompletion.setModifyTime(now);
        CourseCompletion result = courseCompletionRepository.save(courseCompletion);
        result.getCourseVersion().setModifierId(userId);
        result.getCourseVersion().setModifierName(userName);
        result.getCourseVersion().setModifyTime(now);
        if (dataVisualEnable) {
            createCourseVersionEvent(result.getCourseVersion());
        }

        return result;
    }

    public List<CourseCompletionResource> getCourseCompletion(String courseIds) {
        List<String> courseIdList = Arrays.asList(courseIds.split(","));
        List<CourseCompletion> completionList = courseCompletionRepository.findByCourseIdList(courseIdList);
        List<CourseCompletionResource> resourceList = new ArrayList<>();
        courseIdList.forEach(c -> {
            CourseCompletionResource courseCompletionResource = new CourseCompletionResource();
            List<CourseCompletion> courseCompletionList =
                    completionList.stream().filter(completion -> c.equals(completion.getCourse().getId())).collect(Collectors.toList());
            double completion = 0.0;
            if (CollectionUtils.isNotEmpty(courseCompletionList)) {
                completion = courseCompletionList.stream().mapToDouble(com -> com.getCourseContentEnum().getCompletionPercent()).sum();
            }
            courseCompletionResource.setCourseId(c);
            courseCompletionResource.setCompletion(BigDecimal.valueOf(completion));
            resourceList.add(courseCompletionResource);
        });
        return resourceList;
    }

    public List<CourseCompletionDetailResource> getCourseCompletionDetail(String courseId) {
        List<CourseCompletion> completionList = courseCompletionRepository.findByCourseId(courseId);
        CourseContentTypeEnum[] enumList = CourseContentTypeEnum.values();
        List<CourseCompletionDetailResource> returnList = new ArrayList<>();
        for (CourseContentTypeEnum contentTypeEnum : enumList) {
            CourseCompletionDetailResource resource = new CourseCompletionDetailResource();
            resource.setContentTypeValue(String.valueOf(contentTypeEnum.getValue()));
            resource.setContentTypeName(contentTypeEnum.getName());
            resource.setShowOrder(contentTypeEnum.getShowOrder());
            resource.setTypeParentName(contentTypeEnum.getParentName());
            resource.setIsCompleted(false);

            CourseCompletion courseCompletion = completionList
                    .stream()
                    .filter(m -> contentTypeEnum.equals(m.getCourseContentEnum()))
                    .findFirst()
                    .orElse(null);

            if (Objects.nonNull(courseCompletion)) {
                resource.setIsCompleted(courseCompletion.isCompleted());
            }
            returnList.add(resource);
        }
        return returnList;
    }


    @Async
    public void createCourseVersionEvent(CourseVersion courseVersion) {
        CourseVersionEvent courseVersionEvent = new CourseVersionEvent();
        courseVersionEvent.setId(courseVersion.getId());
        courseVersionEvent.setSource(DATA_VISUAL_SOURCE_CLOUD_CLASSROOM);
        courseVersionEvent.setVersionNumber(courseVersion.getVersionSerialNumber());
        List<CourseCompletion> completionList = courseCompletionRepository
                .findByCourseIdList(Collections.singletonList(courseVersion.getCourse().getId()));
        double completion = 0.0;
        if (CollectionUtils.isNotEmpty(completionList)) {
            completion = completionList.stream().mapToDouble(com -> com.getCourseContentEnum().getCompletionPercent()).sum();
        }
        courseVersionEvent.setCourseId(courseVersion.getCourse().getId());
        courseVersionEvent.setCourseCode(courseVersion.getCourse().getCourseCode());
        courseVersionEvent.setCourseName(courseVersion.getCourse().getCourseName());
        courseVersionEvent.setCompleteRate(completion);
        courseVersionEvent.setFilingUserId(courseVersion.getFilingUserId());
        courseVersionEvent.setFilingUserNo(courseVersion.getFilingUserNo());
        courseVersionEvent.setFilingUserName(courseVersion.getFilingUserName());
        courseVersionEvent.setModifierId(courseVersion.getModifierId());
        courseVersionEvent.setModifierName(courseVersion.getModifierName());
        courseVersionEvent.setModifyTime(DateUtils.formatDate("yyyy-MM-dd HH:mm:ss", courseVersion.getModifyTime()));
        eventService.sendCourseVersionEvent(courseVersionEvent);
    }

    @Async
    public void createCourseVersionEvent(CourseVersion courseVersion, List<CourseCompletion> courseCompletionList) {
        CourseVersionEvent courseVersionEvent = new CourseVersionEvent();
        courseVersionEvent.setId(courseVersion.getId());
        courseVersionEvent.setSource(DATA_VISUAL_SOURCE_CLOUD_CLASSROOM);
        courseVersionEvent.setCourseId(courseVersion.getCourse().getId());
        courseVersionEvent.setCourseCode(courseVersion.getCourse().getCourseCode());
        courseVersionEvent.setCourseName(courseVersion.getCourse().getCourseName());
        courseVersionEvent.setVersionNumber(courseVersion.getVersionSerialNumber());
        double completion = 0.0;
        if (CollectionUtils.isNotEmpty(courseCompletionList)) {
            completion = courseCompletionList.stream().mapToDouble(com -> com.getCourseContentEnum().getCompletionPercent()).sum();
        }
        courseVersionEvent.setCompleteRate(completion);
        courseVersionEvent.setFilingUserId(courseVersion.getFilingUserId());
        courseVersionEvent.setFilingUserNo(courseVersion.getFilingUserNo());
        courseVersionEvent.setFilingUserName(courseVersion.getFilingUserName());
        courseVersionEvent.setModifierId(courseVersion.getModifierId());
        courseVersionEvent.setModifierName(courseVersion.getModifierName());
        courseVersionEvent.setModifyTime(DateUtils.formatDate("yyyy-MM-dd HH:mm:ss", courseVersion.getModifyTime()));
        eventService.sendCourseVersionEvent(courseVersionEvent);
    }

    public List<CourseCompletionResource> getLatestCourseCompletion(List<String> courseIds, List<String> courseVersionIds) {
        List<CourseCompletion> completionList = courseCompletionRepository.findByCourseIdListAndCourseVersionIdList(courseIds, courseVersionIds);
        List<CourseCompletionResource> resourceList = new ArrayList<>();
        courseIds.forEach(c -> {
            CourseCompletionResource courseCompletionResource = new CourseCompletionResource();
            List<CourseCompletion> courseCompletionList =
                    completionList.stream().filter(completion -> c.equals(completion.getCourse().getId())).collect(Collectors.toList());
            double completion = 0.0;
            if (CollectionUtils.isNotEmpty(courseCompletionList)) {
                completion = courseCompletionList.stream().mapToDouble(com -> com.getCourseContentEnum().getCompletionPercent()).sum();
            }
            courseCompletionResource.setCourseId(c);
            courseCompletionResource.setCompletion(BigDecimal.valueOf(completion));
            resourceList.add(courseCompletionResource);
        });
        return resourceList;
    }
}
