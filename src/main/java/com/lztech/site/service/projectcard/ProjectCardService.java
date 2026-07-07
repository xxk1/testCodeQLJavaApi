package com.lztech.site.service.projectcard;

import com.lztech.domain.model.projectcard.ProjectCard;
import com.lztech.domain.model.projectcardprojectsuite.ProjectCardProjectSuite;
import com.lztech.domain.model.projectsuite.ProjectSuite;
import com.lztech.domain.model.projectsuite.ProjectSuiteArticles;
import com.lztech.domain.model.projectsuite.ProjectSuiteLowValueArticles;
import com.lztech.domain.model.projectsuite.enums.ProjectSuiteType;
import com.lztech.persistence.repositories.project.ProjectRepository;
import com.lztech.persistence.repositories.projectcard.ProjectCardRepository;
import com.lztech.persistence.repositories.projectcardprojectsuite.ProjectCardProjectSuiteRepository;
import com.lztech.persistence.repositories.projectsuite.ProjectSuiteRepository;
import com.lztech.site.service.projectsuite.ProjectSuiteService;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.projectcard.ProjectCardModel;
import com.lztech.site.viewmodel.projectcard.ProjectCardPageVo;
import com.lztech.site.viewmodel.projectcard.ProjectCardVo;
import com.lztech.site.viewmodel.projectsuite.ArticlesModel;
import com.lztech.site.viewmodel.projectsuite.ProjectSuiteResource;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

import static com.lztech.persistence.repositories.projectcard.specification.ProjectCardSpecification.specification;

@Service
public class ProjectCardService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProjectSuiteService projectSuiteService;
    @Autowired
    private ProjectSuiteRepository projectSuiteRepository;
    @Autowired
    private ProjectCardRepository projectCardRepository;
    @Autowired
    private ProjectCardProjectSuiteRepository projectCardProjectSuiteRepository;

    public ResponseEntity<List<ProjectSuiteResource>> getProjectSuiteByProjectIds(String courseId, String projectIds) {
        List<String> projectIdList = Arrays.asList(projectIds.split(","));
        List<ProjectCard> projectCardList = projectCardRepository.findByProjectIdInAndCourseId(projectIdList, courseId);

        List<ProjectSuiteResource> projectSuiteResourceList = new ArrayList<>();
        projectCardList.forEach(projectCard -> {
            List<ProjectSuiteResource> suiteResources = projectCard.getProjectCardProjectSuiteList()
                    .stream().map(projectCardProjectSuite -> new ProjectSuiteResource() {{
                        this.setCourseId(projectCard.getCourseId());
                        this.setProjectId(projectCard.getProjectId());
                        this.setProjectSuiteId(projectCardProjectSuite.getProjectSuite().getId());
                        this.setProjectSuiteNo(projectCardProjectSuite.getProjectSuite().getProjectSuiteNo());
                        this.setProjectSuiteName(projectCardProjectSuite.getProjectSuite().getProjectSuiteName());
                    }}).collect(Collectors.toList());
            projectSuiteResourceList.addAll(suiteResources);
        });
        if (projectSuiteResourceList.size() > 0) {
            return new ResponseEntity<>(projectSuiteResourceList, HttpStatus.OK);
        }
        return new ResponseEntity(ErrorResult.dataNotExistError("项目套件"), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<ProjectCardPageVo> getProjectCards(Integer page, Integer pageSize, String projectName, String courseId) {
        List<ArticlesModel> articlesModelList = projectSuiteService.findArticlesModelList(ProjectSuiteType.EQUIPMENT.getIndex());
        List<ArticlesModel> lowValueArticlesModeList = projectSuiteService.findArticlesModelList(ProjectSuiteType.CHEAP.getIndex());
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<ProjectCard> projectCardPage = projectCardRepository.findAll(specification(projectName, courseId), pageable);
        List<ProjectCard> projectCardList = projectCardPage.getContent();
        List<ProjectCardModel> projectCardModels = new ArrayList<>();
        projectCardList.stream().forEach(projectCard -> {
            ProjectCardModel projectCardModel = new ProjectCardModel();
            projectCardModel.setProjectCardId(projectCard.getId());
            projectCardModel.setProjectId(projectCard.getProjectId());
            projectCardModel.setProjectName(projectCard.getProjectName());
            projectCardModel.setCourseId(projectCard.getCourseId());
            projectCardModel.setCourseCode(projectCard.getCourseCode());
            projectCardModel.setCourseName(projectCard.getCourseName());
            projectCardModel.setProjectCardPeriod(projectCard.getProjectCardPeriod());
            /**赋予对应套件名称和物品信息**/
            projectCard.getProjectCardProjectSuiteList().stream().forEach(projectCardProjectSuite -> {
                ProjectSuite projectSuite = projectCardProjectSuite.getProjectSuite();
                getProjectSuite(projectCardModel, projectSuite, articlesModelList, lowValueArticlesModeList);
            });
            if (!ObjectUtils.isEmpty(projectCardModel)) {
                projectCardModels.add(projectCardModel);
            }
        });
        int total = (int) projectCardPage.getTotalElements();
        return new ResponseEntity<>(getProjectCardPageVo(total, page, pageSize, projectCardModels), HttpStatus.OK);
    }

    private ProjectCardPageVo getProjectCardPageVo(int total, Integer page, Integer pageSize,
                                                   List<ProjectCardModel> projectCardModels) {
        ProjectCardPageVo projectCardPageVo = new ProjectCardPageVo();
        projectCardPageVo.setTotal(total);
        projectCardPageVo.setPage(page);
        projectCardPageVo.setPageSize(pageSize);
        projectCardPageVo.setProjectCardModels(projectCardModels);
        return projectCardPageVo;
    }

    private void getProjectSuite(ProjectCardModel projectCardModel, ProjectSuite projectSuite, List<ArticlesModel> articlesModelList,
                                 List<ArticlesModel> lowValueArticlesModeList) {
        List<ProjectSuiteArticles> projectSuiteArticlesList = projectSuite.getProjectSuiteArticles();
        if (!CollectionUtils.isEmpty(projectSuiteArticlesList)) {
            List<ArticlesModel> articlesModels = articlesModelList.stream()
                    .map(articlesModel -> projectSuiteArticlesList.stream()
                            .filter(projectSuiteArticles ->
                                    articlesModel.getArticlesId().equals(projectSuiteArticles.getArticlesId()))
                            .findFirst().map(projectSuiteArticles -> {
                                articlesModel.setNumber(projectSuiteArticles.getNumber());
                                return articlesModel;
                            }).orElse(null))
                    .filter(Objects::nonNull).collect(Collectors.toList());
            StringBuffer projectSuiteDetail = StringUtils.isEmpty(projectCardModel.getArticlesDetail()) ?
                    new StringBuffer() : new StringBuffer(projectCardModel.getArticlesDetail());
            getProjectSuiteDetail(projectSuiteDetail, articlesModels);
            projectCardModel.setArticlesDetail(projectSuiteDetail.toString());
        }
        List<ProjectSuiteLowValueArticles> projectSuiteLowValueArticlesList = projectSuite.getProjectSuiteLowValueArticles();
        if (!CollectionUtils.isEmpty(projectSuiteLowValueArticlesList)) {
            List<ArticlesModel> articlesModels = lowValueArticlesModeList.stream()
                    .map(articlesModel -> projectSuiteLowValueArticlesList.stream()
                            .filter(projectSuiteLowValueArticles ->
                                    articlesModel.getArticlesId().equals(projectSuiteLowValueArticles.getLowValueArticlesId()))
                            .findFirst().map(projectSuiteLowValueArticles -> {
                                articlesModel.setNumber(projectSuiteLowValueArticles.getNumber());
                                return articlesModel;
                            }).orElse(null))
                    .filter(Objects::nonNull).collect(Collectors.toList());
            StringBuffer projectSuiteDetail = StringUtils.isEmpty(projectCardModel.getLowValueArticlesDetail()) ?
                    new StringBuffer() : new StringBuffer(projectCardModel.getLowValueArticlesDetail());
            getProjectSuiteDetail(projectSuiteDetail, articlesModels);
            projectCardModel.setLowValueArticlesDetail(projectSuiteDetail.toString());
        }
        String projectSuiteName = StringUtils.isEmpty(projectCardModel.getProjectSuiteName()) ?
                projectSuite.getProjectSuiteName() :
                projectCardModel.getProjectSuiteName() + "," + projectSuite.getProjectSuiteName();
        projectCardModel.setProjectSuiteName(projectSuiteName);
    }

    public StringBuffer getProjectSuiteDetail(StringBuffer projectSuiteDetail, List<ArticlesModel> articlesModels) {
        for (int i = 0; i < articlesModels.size(); i++) {
            ArticlesModel articlesModel = articlesModels.get(i);
            if (i == 0 && StringUtils.isEmpty(projectSuiteDetail.toString())) {
                projectSuiteDetail.append(articlesModel.getArticlesName() +
                        "(" + articlesModel.getArticlesNorms() + ")x" + articlesModel.getNumber());
            } else {
                projectSuiteDetail.append("," + articlesModel.getArticlesName() +
                        "(" + articlesModel.getArticlesNorms() + ")x" + articlesModel.getNumber());
            }
        }
        return projectSuiteDetail;
    }

    @Transactional
    public ResponseEntity<Void> saveProjectCard(ProjectCardVo projectCardVo) {
        ProjectCard projectCard = new ProjectCard();
        projectCard.setCourseId(projectCardVo.getCourseId());
        projectCard.setCourseCode(projectCardVo.getCourseCode());
        projectCard.setCourseName(projectCardVo.getCourseName());
        projectCard.setProjectId(projectCardVo.getProjectId());
        projectCard.setProjectName(projectCardVo.getProjectName());
        projectCard.setProjectCardPeriod("0");
        projectCard.setCreateTime(new Date());
        projectCard.setModifyTime(new Date());
        if (!CollectionUtils.isEmpty(projectCardRepository.findByProjectId(projectCard.getProjectId()))) {
            return new ResponseEntity(ErrorResult.customError("当前项目已被使用,不可再次新增"), HttpStatus.CONFLICT);
        }
        projectCardRepository.save(projectCard);
        List<ProjectCardProjectSuite> projectCardProjectSuiteList = new ArrayList<>();
        List<ProjectSuite> projectSuiteList = projectSuiteRepository.findByIdIsIn(projectCardVo.getProjectSuiteIds());
        projectSuiteList.stream().forEach(projectSuite -> {
            ProjectCardProjectSuite projectCardProjectSuite = new ProjectCardProjectSuite();
            projectCardProjectSuite.setProjectCard(projectCard);
            projectCardProjectSuite.setProjectSuite(projectSuite);
            projectCardProjectSuiteList.add(projectCardProjectSuite);
        });
        projectCardProjectSuiteRepository.saveAll(projectCardProjectSuiteList);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @Transactional
    public ResponseEntity<Void> deleteProjectCard(String projectCardIds) {

        String[] projectCardIdList = projectCardIds.split(",");
        List<ProjectCard> projectCards = new ArrayList<>();
        for (String projectCardId : projectCardIdList) {
            ProjectCard projectCard = projectCardRepository.findById(projectCardId).orElse(null);
            if (!ObjectUtils.isEmpty(projectCard)) {
                List<ProjectCardProjectSuite> projectCardProjectSuites = projectCard.getProjectCardProjectSuiteList();
                projectCardProjectSuiteRepository.deleteAll(projectCardProjectSuites);
            }
            if (ObjectUtils.isEmpty(projectCard)) {
                return new ResponseEntity(ErrorResult.dataNotExistError("项目卡"), HttpStatus.NOT_FOUND);
            }
            projectCards.add(projectCard);
        }
        projectCardRepository.deleteAll(projectCards);
        return new ResponseEntity<>(HttpStatus.OK);

    }


}
