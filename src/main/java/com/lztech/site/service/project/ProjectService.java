package com.lztech.site.service.project;

import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.project.Project;
import com.lztech.domain.model.project.enums.*;
import com.lztech.domain.model.projectattribute.ProjectAttribute;
import com.lztech.domain.model.projectattribute.enums.ProjectAttributeKind;
import com.lztech.domain.model.projectclassification.ProjectClassification;
import com.lztech.persistence.repositories.course.CourseRepository;
import com.lztech.persistence.repositories.project.ProjectAttributeRepository;
import com.lztech.persistence.repositories.project.ProjectRepository;
import com.lztech.persistence.repositories.projectclassification.ProjectClassificationRepository;
import com.lztech.site.constants.Constant;
import com.lztech.site.service.classroom.ClassRoomService;
import com.lztech.site.until.CharactersReplace;
import com.lztech.site.until.DateUtils;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.project.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.Collator;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.lztech.domain.model.project.enums.WhetherAssociateCourses.getWhetherAssociateCourses;
import static com.lztech.persistence.repositories.project.specification.ProjectSpecification.specification;
import static com.lztech.site.constants.Constant.MIN_THREE_DIGIT_INTEGER;
import static com.lztech.site.constants.Constant.PROJECT_CODE_RANDOM_NUMBER_LENGTH;
import static org.apache.commons.lang3.StringUtils.substringAfter;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    private ProjectAttributeRepository projectAttributeRepository;
    @Autowired
    private ProjectClassificationRepository projectClassificationRepository;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private ClassRoomService classRoomService;
    @Autowired
    private CourseRepository courseRepository;


    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectVo> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream()
                .map(project -> {
                    ProjectVo projectVo = new ProjectVo();
                    projectVo.setProjectId(project.getId());
                    projectVo.setProjectCode(project.getProjectCode());
                    projectVo.setProjectName(project.getProjectName());
                    if (project.getProjectAttributeList().size() > 0
                            && project.getProjectAttributeList().get(0).getAttributeKind() == ProjectAttributeKind.CURRICULUM) {
                        projectVo.setProjectCourseId(project.getProjectAttributeList().get(0).getDataId());
                        projectVo.setProjectCourseName(project.getProjectAttributeList().get(0).getDataName());
                    }
                    return projectVo;
                }).collect(Collectors.toList());
    }

    public List<ProjectVo> getInitialSortProjects() {
        List<Project> projects = projectRepository.findAll();
        List<ProjectVo> projectVoList = projects.stream().map(project -> {
            ProjectVo projectVo = new ProjectVo();
            projectVo.setProjectId(project.getId());
            projectVo.setProjectCode(project.getProjectCode());
            projectVo.setProjectName(project.getProjectName());
            if (project.getProjectAttributeList().size() > 0
                    && project.getProjectAttributeList().get(0).getAttributeKind() == ProjectAttributeKind.CURRICULUM) {
                projectVo.setProjectCourseId(project.getProjectAttributeList().get(0).getDataId());
                projectVo.setProjectCourseName(project.getProjectAttributeList().get(0).getDataName());
            }
            return projectVo;
        }).collect(Collectors.toList());
        return projectVoList.stream().sorted(Comparator
                .comparing(ProjectVo::getProjectName, (x, y) -> {
                    Collator clt = Collator.getInstance(java.util.Locale.CHINA);
                    return clt.compare(x, y);
                })).collect(Collectors.toList());
    }

    public List<ProjectVo> getProjectByIds(List<String> ids) {
        List<Project> projectList = projectRepository.findAllByIdIn(ids);
        return projectList.stream().map(project -> {
            ProjectVo projectVo = new ProjectVo();
            projectVo.setProjectId(project.getId());
            projectVo.setProjectCode(project.getProjectCode());
            projectVo.setProjectName(project.getProjectName());
            if (project.getProjectAttributeList().size() > 0
                    && project.getProjectAttributeList().get(0).getAttributeKind() == ProjectAttributeKind.CURRICULUM) {
                projectVo.setProjectCourseId(project.getProjectAttributeList().get(0).getDataId());
                projectVo.setProjectCourseName(project.getProjectAttributeList().get(0).getDataName());
            }
            return projectVo;
        }).collect(Collectors.toList());
    }

    //region 根据课程id获取项目信息
    public List<ProjectVo> getProjectByCourseId(String courseId) {
        if (StringUtils.isNotEmpty(courseId)) {
            List<ProjectAttribute> projectAttributeList
                    = this.projectAttributeRepository.findByDataIdAndAttributeKind(courseId, ProjectAttributeKind.CURRICULUM);
            return projectAttributeList.stream().map(projectAttribute -> new ProjectVo() {
                {
                    this.setProjectId(projectAttribute.getProject().getId());
                    this.setProjectCode(projectAttribute.getProject().getProjectCode());
                    this.setProjectName(projectAttribute.getProject().getProjectName());
                    this.setProjectCourseId(projectAttribute.getDataId());
                    this.setProjectCourseName(projectAttribute.getDataName());
                }
            }).collect(Collectors.toList());
        } else {
            List<Project> projectList = projectRepository.findByWhetherAssociateCourses(WhetherAssociateCourses.NO);
            return projectList.stream().map(project -> new ProjectVo() {
                {
                    this.setProjectId(project.getId());
                    this.setProjectCode(project.getProjectCode());
                    this.setProjectName(project.getProjectName());
                }
            }).collect(Collectors.toList());
        }

    }
    //endregion

    //region 接口-获取项目种类
    public ResponseEntity<List<ProjectTypeVo>> getProjectTypes() {
        List<ProjectTypeVo> projectTypeVos = new ArrayList<>();
        List<ProjectType> projectTypes = Arrays.asList(ProjectType.values());
        projectTypes.forEach(projectType -> {
            ProjectTypeVo projectTypeVo = new ProjectTypeVo();
            projectTypeVo.setProjectTypeName(projectType.getName());
            projectTypeVo.setValue(projectType.getValue());
            projectTypeVos.add(projectTypeVo);
        });
        return new ResponseEntity<>(projectTypeVos, HttpStatus.OK);
    }
    //endregion

    //region 接口-获取项目类别
    public ResponseEntity<List<ProjectCategoryVo>> getProjectCategories() {
        List<ProjectCategoryVo> projectCategoryVos = new ArrayList<>();
        List<ProjectCategory> projectCategories = Arrays.asList(ProjectCategory.values());
        projectCategories.forEach(projectCategory -> {
            ProjectCategoryVo projectCategoryVo = new ProjectCategoryVo();
            projectCategoryVo.setValue(projectCategory.getValue());
            projectCategoryVo.setProjectCategoryName(projectCategory.getName());
            projectCategoryVos.add(projectCategoryVo);
        });
        return new ResponseEntity<>(projectCategoryVos, HttpStatus.OK);
    }
    //endregion

    //region 接口-获取项目要求
    public ResponseEntity<List<ProjectClaimVo>> getProjectClaims() {
        List<ProjectClaimVo> projectClaimVos = new ArrayList<>();
        List<ProjectClaim> projectClaims = Arrays.asList(ProjectClaim.values());
        projectClaims.forEach(projectClaim -> {
            ProjectClaimVo projectClaimVo = new ProjectClaimVo();
            projectClaimVo.setValue(projectClaim.getValue());
            projectClaimVo.setProjectClaimName(projectClaim.getName());
            projectClaimVos.add(projectClaimVo);
        });
        return new ResponseEntity<>(projectClaimVos, HttpStatus.OK);
    }
    //endregion

    //region 接口-获取项目类型
    public ResponseEntity<List<ProjectGenreVo>> getProjectGenres() {
        List<ProjectGenreVo> projectGenreVos = new ArrayList<>();
        List<ProjectGenre> projectGenres = Arrays.asList(ProjectGenre.values());
        projectGenres.forEach(projectGenre -> {
            ProjectGenreVo projectGenreVo = new ProjectGenreVo();
            projectGenreVo.setValue(projectGenre.getValue());
            projectGenreVo.setProjectGenreName(projectGenre.getName());
            projectGenreVos.add(projectGenreVo);
        });
        return new ResponseEntity<>(projectGenreVos, HttpStatus.OK);

    }
    //endregion

    //region 接口-项目信息列表
    public ResponseEntity<ProjectPageResource> getProjectsPageList(ProjectQueryParam param) {
        Pageable pageable = PageRequest.of(param.getPage() - 1, param.getPageSize());
        Page<Project> projectPage = projectRepository.findAll(specification(param), pageable);
        ProjectPageResource projectPageResource = new ProjectPageResource();
        projectPageResource.setPage(param.getPage());
        projectPageResource.setPageSize(param.getPageSize());
        projectPageResource.setTotalCount((int) projectPage.getTotalElements());
        projectPageResource.setPageCount(projectPage.getTotalPages());
        projectPageResource.setProjectList(getProjectResourceList(projectPage.getContent()));
        return new ResponseEntity<>(projectPageResource, HttpStatus.OK);
    }

    private List<ProjectResource> getProjectResourceList(List<Project> projects) {
        List<ProjectResource> projectResources = new ArrayList<>();
        projects.forEach(project -> {
            ProjectResource projectResource = new ProjectResource();
            projectResource.setId(project.getId());
            projectResource.setProjectName(project.getProjectName());
            projectResource.setWhetherAssociateCoursesValue(project.getWhetherAssociateCourses().getValue());
            projectResource.setWhetherAssociateCoursesName(project.getWhetherAssociateCourses().getName());
            List<ProjectAttribute> projectAttributes = project.getProjectAttributeList()
                    .stream()
                    .filter(projectAttribute -> projectAttribute.getAttributeKind().equals(ProjectAttributeKind.CURRICULUM))
                    .collect(Collectors.toList());
            if (projectAttributes.size() > 0) {
                ProjectAttribute attribute = projectAttributes.get(0);
                projectResource.setCourseId(attribute.getDataId());
                projectResource.setCourseName(attribute.getDataName());
                projectResource.setCourseCode(attribute.getDataCode());
            }
            projectResource.setClassificationId(project.getProjectClassificationId());
            projectResource.setClassificationName(project.getProjectClassificationName());
            projectResource.setProjectCategoryValue(project.getProjectCategory().getValue());
            projectResource.setProjectCategoryName(project.getProjectCategory().getName());
            projectResource.setProjectClaimValue(project.getProjectClaim().getValue());
            projectResource.setProjectClaimName(project.getProjectClaim().getName());
            projectResource.setProjectGenreValue(project.getProjectGenre().getValue());
            projectResource.setProjectGenreName(project.getProjectGenre().getName());
            projectResource.setProjectTypeValue(project.getProjectType().getValue());
            projectResource.setProjectTypeName(project.getProjectType().getName());
            projectResource.setProjectCode(project.getProjectCode());
            projectResource.setProjectDescription(project.getProjectDescription());
            projectResources.add(projectResource);
        });
        return projectResources;
    }
    //endregion

    //region 接口-新增项目
    @Transactional
    public ProjectResource createProject(ProjectInfoResource projectInfoResource) {
        Date now = new Date();

        Project project = new Project();
        String projectCode;
        if (StringUtils.isNotBlank(projectInfoResource.getProjectCode())) {
            projectCode = projectInfoResource.getProjectCode();
        } else {
            projectCode = generateProjectCode(projectInfoResource.getCourseCode());
        }
        project.setCreatorId(projectInfoResource.getUserId());
        project.setCreateTime(now);
        project.setCreatorName(projectInfoResource.getUserName());
        project.setModifierId(projectInfoResource.getUserId());
        project.setModifyTime(now);
        project.setModifierName(projectInfoResource.getUserName());

        project.setProjectName(projectInfoResource.getProjectName());
        project.setProjectCode(projectCode);
        project.setProjectDescription(projectInfoResource.getProjectDescription());
        project.setWhetherAssociateCourses(getWhetherAssociateCourses(projectInfoResource.getWhetherAssociateCourses()));

        if (StringUtils.isNotEmpty(projectInfoResource.getClassificationId())
                && StringUtils.isNotEmpty(projectInfoResource.getClassificationName())) {
            project.setProjectClassificationId(projectInfoResource.getClassificationId());
            project.setProjectClassificationName(projectInfoResource.getClassificationName());
        }
        project.setProjectCategory(Objects.isNull(projectInfoResource.getProjectCategoryValue()) ? ProjectCategory.PROFESSIONAL :
                ProjectCategory.getProjectCategory(projectInfoResource.getProjectCategoryValue()));
        project.setProjectClaim(Objects.isNull(projectInfoResource.getProjectClaimValue()) ? ProjectClaim.OBLIGATORY :
                ProjectClaim.getProjectClaim(projectInfoResource.getProjectClaimValue()));
        project.setProjectGenre(Objects.isNull(projectInfoResource.getProjectGenreValue()) ? ProjectGenre.INTEGRATEDDESIGN :
                ProjectGenre.getProjectGenre(projectInfoResource.getProjectGenreValue()));
        project.setProjectType(Objects.isNull(projectInfoResource.getProjectTypeValue()) ? ProjectType.EXPERIMENT :
                ProjectType.getProjectType(projectInfoResource.getProjectTypeValue()));
        Project savedProject = projectRepository.save(project);

        if (projectInfoResource.getWhetherAssociateCourses().equals(WhetherAssociateCourses.YES.getValue())) {
            saveProjectAttribute(projectInfoResource, savedProject);
            ProjectResource projectResource = new ProjectResource();
            projectResource.setId(project.getId());
            projectResource.setProjectName(project.getProjectName());
            projectResource.setProjectCode(project.getProjectCode());
            return projectResource;
        } else {
            return transformProject(savedProject, projectInfoResource);
        }
    }

    public int checkProjectName(String projectName, String courseId) {
        String sql = "SELECT count(1) AS count FROM tb_project tp LEFT JOIN tb_project_attribute tpa ON tp.id = tpa.project_id" +
                " WHERE tpa.attribute_kind = 2 AND tpa.data_id = :courseId and tp.project_name= :projectName ";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("projectName", projectName);
        query.setParameter("courseId", courseId);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        Map<String, Object> resMap = (Map<String, Object>) query.getResultList().get(0);
        return ((BigInteger) resMap.get("count")).intValue();
    }
    //endregion

    //region 接口-修改项目
    @Transactional
    public ResponseEntity<Void> updateProject(ProjectInfoResource projectInfoResource, String id) {
        Date now = new Date();
        Project project = projectRepository.findById(id).orElse(null);

        if (ObjectUtils.isEmpty(project)) {
            return new ResponseEntity(ErrorResult.dataNotExistError("项目"), HttpStatus.NOT_FOUND);
        }

        ProjectAttribute attribute = new ProjectAttribute();

        if (project.getProjectAttributeList().size() > 0) {
            attribute = project.getProjectAttributeList()
                    .stream()
                    .filter(projectAttribute -> projectAttribute.getAttributeKind().equals(ProjectAttributeKind.CURRICULUM))
                    .collect(Collectors.toList()).get(0);
        }

        if (projectInfoResource.getWhetherAssociateCourses().equals(WhetherAssociateCourses.YES.getValue())) {
            if (attribute.getDataId() == null || !projectInfoResource.getCourseId().equals(attribute.getDataId())) {
                if (projectInfoResource.getWhetherAssociateCourses().equals(WhetherAssociateCourses.YES.getValue())) {
                    attribute.setCreatorId(projectInfoResource.getUserId());
                    attribute.setCreateTime(now);
                    attribute.setCreatorName(projectInfoResource.getUserName());
                    attribute.setDataId(projectInfoResource.getCourseId());
                    attribute.setDataName(projectInfoResource.getCourseName());
                    attribute.setDataCode(projectInfoResource.getCourseCode());
                    attribute.setAttributeKind(ProjectAttributeKind.CURRICULUM);
                    attribute.setProject(project);
                    projectAttributeRepository.save(attribute);
                }
            }
            if (attribute.getDataId() != null && !(project.getProjectName().equals(projectInfoResource.getProjectName())
                    && projectInfoResource.getCourseId().equals(attribute.getDataId()))) {
                int count = checkProjectName(projectInfoResource.getProjectName(), projectInfoResource.getCourseId());
                if (count > 0) {
                    return new ResponseEntity(ErrorResult.customError("所属课程下该项目名称已存在"), HttpStatus.CONFLICT);
                }
                project.setProjectName(projectInfoResource.getProjectName());
            }
        } else {
            if (attribute.getDataId() != null) {
                projectAttributeRepository.delete(attribute);
            }
            if (!(project.getProjectName().equals(projectInfoResource.getProjectName()))) {
                project.setProjectName(projectInfoResource.getProjectName());
            }
        }

        project.setModifierId(projectInfoResource.getUserId());
        project.setModifyTime(now);
        project.setModifierName(projectInfoResource.getUserName());
        project.setWhetherAssociateCourses(getWhetherAssociateCourses(projectInfoResource.getWhetherAssociateCourses()));
        project.setProjectDescription(projectInfoResource.getProjectDescription());
        project.setProjectGenre(ProjectGenre.getProjectGenre(projectInfoResource.getProjectGenreValue()));
        project.setProjectCategory(ProjectCategory.getProjectCategory(projectInfoResource.getProjectCategoryValue()));
        project.setProjectClaim(ProjectClaim.getProjectClaim(projectInfoResource.getProjectClaimValue()));
        project.setProjectType(ProjectType.getProjectType(projectInfoResource.getProjectTypeValue()));
        if (StringUtils.isAllBlank(projectInfoResource.getClassificationId(), projectInfoResource.getClassificationName())) {
            project.setProjectClassificationId(null);
            project.setProjectClassificationName(null);
        }
        project.setProjectClassificationId(projectInfoResource.getClassificationId());
        project.setProjectClassificationName(projectInfoResource.getClassificationName());
        project.setProjectName(projectInfoResource.getProjectName());
        Project saveProject = projectRepository.save(project);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //endregion

    private ProjectResource transformProject(Project project, ProjectInfoResource projectInfoResource) {
        ProjectResource projectResource = new ProjectResource();
        projectResource.setId(project.getId());
        projectResource.setProjectName(project.getProjectName());
        projectResource.setProjectCode(project.getProjectCode());
        projectResource.setCourseId(projectInfoResource.getCourseId());
        projectResource.setCourseCode(projectInfoResource.getCourseCode());
        projectResource.setCourseName(projectInfoResource.getCourseName());
        return projectResource;
    }

    private void saveProjectAttribute(ProjectInfoResource projectInfoResource, Project project) {
        ProjectAttribute projectAttribute = new ProjectAttribute();
        projectAttribute.setAttributeKind(ProjectAttributeKind.CURRICULUM);
        projectAttribute.setProject(project);
        projectAttribute.setDataId(projectInfoResource.getCourseId());
        projectAttribute.setDataName(projectInfoResource.getCourseName());
        projectAttribute.setDataCode(projectInfoResource.getCourseCode());
        projectAttribute.setCreatorId(project.getCreatorId());
        projectAttribute.setCreatorName(project.getCreatorName());
        projectAttribute.setCreateTime(project.getCreateTime());
        projectAttributeRepository.save(projectAttribute);
    }

    private synchronized String generateProjectCode(String courseCode) {
        String projectCode;
        while (true) {
            projectCode = generateProjectCodeByCourse(courseCode);
            Project sameProjectCodeProject = projectRepository.findByProjectCodeLimitOne(projectCode);
            if (Objects.isNull(sameProjectCodeProject)) {
                break;
            }
        }
        return projectCode;
    }

    private String generateProjectCodeByCourse(String courseCode) {
        int projectCodeLength = courseCode.length() + PROJECT_CODE_RANDOM_NUMBER_LENGTH;
        List<Project> projectList = projectRepository.findByProjectCodeLike(courseCode, projectCodeLength);
        List<Integer> projectCodeSuffixList = new ArrayList<>();
        projectList.forEach(project -> {
            if (substringAfter(project.getProjectCode(), courseCode).matches("[0-9]+")) {
                projectCodeSuffixList.add(Integer.valueOf(substringAfter(project.getProjectCode(), courseCode)));
            }
        });
        int maxValue = CollectionUtils.isEmpty(projectCodeSuffixList) ? 1 : Collections.max(projectCodeSuffixList);
        int resultValue = Integer.MAX_VALUE;
        String finalCode;
        boolean generateFlag = false;
        for (int i = 1; i <= maxValue; i++) {
            if (!projectCodeSuffixList.contains(i)) {
                generateFlag = true;
                resultValue = i;
                break;
            }
        }
        if (!generateFlag) {
            resultValue = Collections.max(projectCodeSuffixList) + 1;
        }
        if (resultValue < MIN_THREE_DIGIT_INTEGER) {
            finalCode = String.format("%03d", resultValue);
        } else {
            finalCode = String.valueOf(resultValue);
        }

        return courseCode + finalCode;

    }

    public int countSameProjectCode(String projectCode) {
        return projectRepository.countByProjectCode(projectCode);
    }

    public List<ProjectResource> getCourseProjectList(String courseIds, String projectName) {
        Map<String, Object> paramMap = new HashMap<>();
        String querySql = getQueryCourseProjectSql(courseIds, projectName, paramMap);
        Query query = entityManager.createNativeQuery(querySql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(ProjectResource.class));
        return query.getResultList();
    }

    private String getQueryCourseProjectSql(String courseIds, String projectName, Map<String, Object> paramMap) {
        String sql = "select project_id id, project_code projectCode, project_name projectName,p.standard_duration standardDuration, " +
                " data_id as courseId,data_code as courseCode,data_name as courseName\n" +
                " from tb_project p inner join tb_project_attribute pa on p.id = pa.project_id\n" +
                "where attribute_kind = 2  ";
        if (StringUtils.isNotBlank(courseIds)) {
            List<String> resultCourseIds = Arrays.stream(courseIds.split(",")).map(String::trim).collect(Collectors.toList());
            sql += " and pa.data_id in ( :courseIdList ) ";
            paramMap.put("courseIdList", resultCourseIds);
        }
        if (StringUtils.isNotBlank(projectName)) {
            projectName = CharactersReplace.replaceCharacters(projectName);
            sql += " and p.project_name like :projectName ";
            paramMap.put("projectName", "%" + projectName + "%");
        }
        sql += " order by pa.data_code,p.project_code";
        return sql;
    }

    public void updateProjectStandardDuration(String projectId,
                                              BigDecimal standardDuration,
                                              String modifierId,
                                              String modifierName) {
        double resultStandardDuration = standardDuration.doubleValue();
        projectRepository.updateProjectStandardDuration(projectId, resultStandardDuration, modifierId, modifierName);

    }


    public List<ProjectVo> getBookableProjects(String startTime, String endTime) {
        List<ProjectVo> projectVoList = new ArrayList<>();
        ProjectIdVo projectIdVo = classRoomService.getBookableProjectIds(startTime, endTime);
        if (ObjectUtils.isEmpty(projectIdVo)) {
            return projectVoList;
        } else {
            List<Project> projects = new ArrayList<>();
            if (projectIdVo.getIsAllProjects()) {
                projects = projectRepository.findAll();
            } else {
                projects = projectRepository.findAllByIdIn(projectIdVo.getProjectIdList());
            }


            projectVoList = projects.stream()
                    .map(project -> {
                        ProjectVo projectVo = new ProjectVo();
                        projectVo.setProjectId(project.getId());
                        projectVo.setProjectCode(project.getProjectCode());
                        projectVo.setProjectName(project.getProjectName());
                        if (project.getProjectAttributeList().size() > 0
                                && project.getProjectAttributeList().get(0).getAttributeKind() == ProjectAttributeKind.CURRICULUM) {
                            projectVo.setProjectCourseId(project.getProjectAttributeList().get(0).getDataId());
                            projectVo.setProjectCourseName(project.getProjectAttributeList().get(0).getDataName());
                        }
                        return projectVo;
                    }).collect(Collectors.toList());

            return projectVoList;
        }
    }

    public void deleteProjectList(ProjectDeleteParam projectDeleteParam) {
        List<String> projectIdList = projectDeleteParam.getProjectIdList();
        List<Project> projectList = projectRepository.findAllByIdIn(projectIdList);
        List<ProjectAttribute> allProjectAttributeList = new ArrayList<>();
        for (Project project : projectList) {
            List<ProjectAttribute> projectAttributeList = project.getProjectAttributeList();
            allProjectAttributeList.addAll(projectAttributeList);
        }
        projectAttributeRepository.deleteAll(allProjectAttributeList);
        projectRepository.deleteAll(projectList);
    }

    public ProjectValidateResource importProjectList(String userId, String userName, List<ProjectFileModel> projectFileModelList){
        ProjectValidateResource projectValidateResource = new ProjectValidateResource();
        Date nowDate = new Date();
        String projectCodeLike =  "XM" + DateUtils.formatDate("yyMMdd",nowDate);
        String minProjectCode = projectCodeLike+"0001";
        String maxProjectCode = projectCodeLike+"9999";

        int serialNumber = projectRepository.countByProjectCodeData(minProjectCode,maxProjectCode);

        if (serialNumber+projectFileModelList.size()> Constant.MAX_SERIAL_NUMBER){
            projectValidateResource.setStatus(0);
            projectValidateResource.setMessage("今日项目编号已用完，请明日再试");
        }else{
            List<String> courseCodeList = projectFileModelList.stream()
                    .map(ProjectFileModel::getCourseCode)
                    .filter(ObjectUtils::isNotEmpty).distinct().collect(Collectors.toList());

            List<Course> courseList = courseRepository.findByCourseCodeIn(courseCodeList);

            List<String> classificationNameList = projectFileModelList.stream()
                    .map(ProjectFileModel::getClassificationName)
                    .filter(ObjectUtils::isNotEmpty).distinct().collect(Collectors.toList());

            List<ProjectClassification> projectClassificationList =
                    projectClassificationRepository.findByClassificationNameIn(classificationNameList);

            List<Project> effectiveProjectList = new ArrayList<>();
            for (ProjectFileModel projectFileModel : projectFileModelList) {
                serialNumber = serialNumber + 1;
                String projectCode = projectCodeLike + formatSerialNumber(serialNumber);
                projectFileModel.setProjectCode(projectCode);
                Project project = new Project();
                project.setProjectCode(projectCode);
                project.setProjectName(projectFileModel.getProjectName());
                project.setProjectDescription(projectFileModel.getProjectDescription());
                if (ObjectUtils.isNotEmpty(projectFileModel.getClassificationName())){
                    ProjectClassification projectClassification = projectClassificationList.stream()
                            .filter(filterProjectClassification ->
                                    filterProjectClassification.getClassificationName()
                                            .equals(projectFileModel.getClassificationName().trim()))
                            .findFirst().orElse(null);
                    if (ObjectUtils.isNotEmpty(projectClassification)){
                        project.setProjectClassificationId(projectClassification.getId());
                        project.setProjectClassificationName(projectClassification.getClassificationName());
                    }
                }
                ProjectType projectType = ProjectType.getProjectType(projectFileModel.getProjectTypeName().trim());
                if (ObjectUtils.isNotEmpty(projectType)){
                    project.setProjectType(projectType);
                }
                ProjectCategory projectCategory = ProjectCategory.getProjectCategory(projectFileModel.getProjectCategoryName().trim());
                if (ObjectUtils.isNotEmpty(projectCategory)){
                    project.setProjectCategory(projectCategory);
                }
                ProjectClaim projectClaim = ProjectClaim.getProjectClaim(projectFileModel.getProjectClaimName().trim());
                if (ObjectUtils.isNotEmpty(projectClaim)){
                    project.setProjectClaim(projectClaim);
                }
                ProjectGenre projectGenre = ProjectGenre.getProjectGenre(projectFileModel.getProjectGenreName().trim());
                if (ObjectUtils.isNotEmpty(projectGenre)){
                    project.setProjectGenre(projectGenre);
                }
                WhetherAssociateCourses whetherAssociateCourses =
                        WhetherAssociateCourses.getWhetherAssociateCourses(projectFileModel.getWhetherAssociateCoursesName().trim());
                if (ObjectUtils.isNotEmpty(whetherAssociateCourses)){
                    project.setWhetherAssociateCourses(whetherAssociateCourses);
                }
                project.setModifierId(userId);
                project.setModifierName(userName);
                project.setModifyTime(nowDate);
                project.setCreatorId(userId);
                project.setCreatorName(userName);
                project.setCreateTime(nowDate);
                effectiveProjectList.add(project);
            }
            effectiveProjectList = projectRepository.saveAll(effectiveProjectList);
            List<ProjectAttribute> projectAttributeList = new ArrayList<>();
            for (ProjectFileModel projectFileModel : projectFileModelList) {
                Project project = effectiveProjectList.stream()
                        .filter(filterProject -> filterProject.getProjectCode().equals(projectFileModel.getProjectCode()))
                        .findFirst().orElse(null);
                if (ObjectUtils.isNotEmpty(project)
                        && ObjectUtils.isNotEmpty(project.getWhetherAssociateCourses())
                        && project.getWhetherAssociateCourses().equals(WhetherAssociateCourses.YES)){
                    ProjectAttribute projectAttribute = new ProjectAttribute();
                    projectAttribute.setAttributeKind(ProjectAttributeKind.CURRICULUM);
                    projectAttribute.setProject(project);
                    Course course = courseList.stream().filter(filterCourse ->
                                    filterCourse.getCourseCode().equals(projectFileModel.getCourseCode()))
                            .findFirst().orElse(null);
                    if (ObjectUtils.isNotEmpty(course)){
                        projectAttribute.setDataId(course.getId());
                        projectAttribute.setDataCode(course.getCourseCode());
                        projectAttribute.setDataName(course.getCourseName());

                    }
                    projectAttribute.setCreatorId(userId);
                    projectAttribute.setCreatorName(userName);
                    projectAttribute.setCreateTime(nowDate);
                    projectAttributeList.add(projectAttribute);
                }
            }
            projectAttributeRepository.saveAll(projectAttributeList);
            projectValidateResource.setStatus(1);
        }
        return projectValidateResource;
    }
    public static String formatSerialNumber(int serialNumber) {
        DecimalFormat formatter = new DecimalFormat("0000");
        return formatter.format(serialNumber);
    }
}
