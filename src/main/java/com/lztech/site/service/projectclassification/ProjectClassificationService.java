package com.lztech.site.service.projectclassification;

import com.lztech.domain.model.project.Project;
import com.lztech.domain.model.projectclassification.ProjectClassification;
import com.lztech.persistence.repositories.project.ProjectRepository;
import com.lztech.persistence.repositories.projectclassification.ProjectClassificationRepository;
import com.lztech.site.constants.Constant;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.projectclassification.ClassificationResource;
import com.lztech.site.viewmodel.projectclassification.ProjectClassificationVo;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectClassificationService {

    @Autowired
    private ProjectClassificationRepository projectClassificationRepository;
    @Autowired
    private ProjectRepository projectRepository;

    //region 接口-获取所有项目分类
    public ResponseEntity<List<ProjectClassificationVo>> getProjectClassifications() {
        List<ProjectClassificationVo> projectClassificationVos = new ArrayList<>();
        List<ProjectClassification> projectClassifications = projectClassificationRepository.findAll();
        List<ProjectClassification> classifications = projectClassifications.stream()
                .sorted(Comparator.comparing(ProjectClassification::getCreateTime).reversed())
                .collect(Collectors.toList());
        classifications.forEach(projectClassification -> {
            ProjectClassificationVo projectClassificationVo = new ProjectClassificationVo();
            projectClassificationVo.setId(projectClassification.getId());
            projectClassificationVo.setClassificationName(projectClassification.getClassificationName());
            projectClassificationVos.add(projectClassificationVo);
        });
        return new ResponseEntity<>(projectClassificationVos, HttpStatus.OK);
    }
    //endregion


    //region 接口-新增项目分类
    @Transactional
    public ResponseEntity<Void> createProjectClassification(ClassificationResource classificationResource) {
        Date now = new Date();
        ProjectClassification byClassificationName = projectClassificationRepository
                .findByClassificationName(classificationResource.getClassificationName());
        if (classificationResource.getClassificationName().length() > Constant.EIGHT) {
            return new ResponseEntity(ErrorResult.customError("分类名称最大不能超过8个字"), HttpStatus.CONFLICT);

        }
        if (ObjectUtils.isNotEmpty(byClassificationName)) {
            return new ResponseEntity(ErrorResult.customError("分类名称：" + classificationResource.getClassificationName() + " 已存在")
                    , HttpStatus.CONFLICT);
        }
        ProjectClassification projectClassification = new ProjectClassification();
        projectClassification.setClassificationName(classificationResource.getClassificationName());
        projectClassification.setCreateTime(now);
        if (StringUtils.isNotBlank(classificationResource.getUserId())) {
            projectClassification.setCreatorId(classificationResource.getUserId());
        }
        if (StringUtils.isNotBlank(classificationResource.getUserName())) {
            projectClassification.setCreatorName(classificationResource.getUserName());
        }
        projectClassificationRepository.save(projectClassification);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //endregion

    //region 接口-删除项目分类
    @Transactional
    public ResponseEntity<Void> deleteProjectClassification(String id) {
        ProjectClassification projectClassification = projectClassificationRepository.findById(id).orElse(null);
        if (ObjectUtils.isEmpty(projectClassification)) {
            return new ResponseEntity(ErrorResult.dataNotExistError("该项目分类"), HttpStatus.NOT_FOUND);
        }
        List<Project> projects = projectRepository.findByProjectClassificationId(id);
        if (projects.size() > 0) {
            projects.forEach(project -> {
                project.setProjectClassificationId(null);
                project.setProjectClassificationName(null);
            });
            projectRepository.saveAll(projects);
        }
        projectClassificationRepository.delete(projectClassification);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //endregion
}
