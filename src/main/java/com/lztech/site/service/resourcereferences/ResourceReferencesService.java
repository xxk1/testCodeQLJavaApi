package com.lztech.site.service.resourcereferences;

import com.lztech.domain.model.course.CourseResource;
import com.lztech.persistence.repositories.course.CourseResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ResourceReferencesService {

    @Autowired
    private CourseResourceRepository resourceRepository;

    public void updateCourseResource(List<CourseResource> courseResources, String quotePeopleId, Map<String, Long> idMap) {
        List<CourseResource> courseResourceList = new ArrayList<>();
        courseResources.forEach(courseResource -> {
            Integer count = Math.toIntExact(idMap.get(courseResource.getId()));
            if (StringUtils.isEmpty(quotePeopleId)){
                courseResource.setResourceReferences(courseResource.getResourceReferences() == null ? count
                        : (courseResource.getResourceReferences() + count));
            }else{
                if (courseResource.getCreatorId().equals(quotePeopleId)){
                    courseResource.setResourceReferences(courseResource.getResourceReferences() == null ? count
                            : (courseResource.getResourceReferences() + count));
                }else{
                    courseResource.setResourceOtherReferences(courseResource.getResourceOtherReferences() == null ? count
                            : (courseResource.getResourceOtherReferences() + count));
                }
            }
            courseResourceList.add(courseResource);
        });
        resourceRepository.saveAll(courseResourceList);
    }

    public void updateReferenceNumByResourceDetailId(String resourceDetailId) {
        resourceRepository.updateReferenceNumByResourceDetailId(resourceDetailId);
    }
}
