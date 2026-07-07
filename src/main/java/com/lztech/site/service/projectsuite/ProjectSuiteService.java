package com.lztech.site.service.projectsuite;

import com.lztech.domain.model.projectsuite.ProjectSuite;
import com.lztech.domain.model.projectsuite.ProjectSuiteArticles;
import com.lztech.domain.model.projectsuite.ProjectSuiteLowValueArticles;
import com.lztech.domain.model.projectsuite.enums.ProjectSuiteStatus;
import com.lztech.domain.model.projectsuite.enums.ProjectSuiteType;
import com.lztech.persistence.repositories.projectsuite.ProjectSuiteArticlesRepository;
import com.lztech.persistence.repositories.projectsuite.ProjectSuiteLowValueArticlesRepository;
import com.lztech.persistence.repositories.projectsuite.ProjectSuiteRepository;
import com.lztech.site.constants.ConstantWebApi;
import com.lztech.site.until.Md5Utils;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.projectsuite.*;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

import static com.lztech.persistence.repositories.projectsuite.specification.ProjectSuiteSpecification.specification;

@Service
public class ProjectSuiteService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectSuiteService.class);

    @Value("${signKey}")
    private String signKey;
    @Value("${request.address.classRoom}")
    private String classRoomAddress;
    @Autowired
    private ProjectSuiteRepository projectSuiteRepository;
    @Autowired
    private ProjectSuiteArticlesRepository projectSuiteArticlesRepository;
    @Autowired
    private ProjectSuiteLowValueArticlesRepository projectSuiteLowValueArticlesRepository;

    @Transactional
    public ResponseEntity<Void> operationProjectSuite(ProjectSuiteVo projectSuiteVo) {
        ProjectSuite projectSuite = new ProjectSuite();
        List<ProjectSuite> projectSuiteList;
        if (StringUtils.isEmpty(projectSuiteVo.getProjectSuiteId())){
            projectSuite.setProjectSuiteName(projectSuiteVo.getProjectSuiteName());
            projectSuite.setProjectSuiteNo(UUID.randomUUID().toString());
            projectSuite.setSuiteType(ProjectSuiteType.getProjectSuiteType(projectSuiteVo.getProjectSuiteType()));
            projectSuite.setSuiteStatus(ProjectSuiteStatus.NORMAL);
            projectSuite.setCreateTime(new Date());
            projectSuite.setModifyTime(new Date());
            projectSuiteList = projectSuiteRepository.findByProjectSuiteNameAndSuiteStatusAndSuiteType(
                    projectSuite.getProjectSuiteName(),ProjectSuiteStatus.NORMAL,projectSuite.getSuiteType());
        }else {
            projectSuite = projectSuiteRepository.findByIdAndSuiteStatus(projectSuiteVo.getProjectSuiteId(),ProjectSuiteStatus.NORMAL);
            if (ObjectUtils.isEmpty(projectSuite)){
                return new ResponseEntity(ErrorResult.dataNotExistError("项目卡套件"), HttpStatus.NOT_FOUND);
            }
             projectSuiteList = projectSuiteRepository.findByProjectSuiteNameAndSuiteStatusAndSuiteType(
                    projectSuiteVo.getProjectSuiteName(),ProjectSuiteStatus.NORMAL,
                     ProjectSuiteType.getProjectSuiteType(projectSuiteVo.getProjectSuiteType()));
            ProjectSuite finalProjectSuite = projectSuite;
            projectSuiteList = projectSuiteList.stream().filter(projectSuite1 ->
                    !projectSuite1.getId().equals(finalProjectSuite.getId())).collect(Collectors.toList());
            projectSuite.setProjectSuiteName(projectSuiteVo.getProjectSuiteName());
            projectSuite.setSuiteType(ProjectSuiteType.getProjectSuiteType(projectSuiteVo.getProjectSuiteType()));
            projectSuite.setModifyTime(new Date());
            /**清空项目套件中的物品信息**/
            List<ProjectSuiteArticles> projectSuiteArticlesList = projectSuite.getProjectSuiteArticles();
            projectSuiteArticlesRepository.deleteAll(projectSuiteArticlesList);
            List<ProjectSuiteLowValueArticles> projectSuiteLowValueArticlesList = projectSuite.getProjectSuiteLowValueArticles();
            projectSuiteLowValueArticlesRepository.deleteAll(projectSuiteLowValueArticlesList);
        }
        if (projectSuiteList!= null && projectSuiteList.size() > 0) {
            return new ResponseEntity(ErrorResult.customError("当前套件名称已存在"), HttpStatus.CONFLICT);
        }
        projectSuiteRepository.save(projectSuite);
        if (ProjectSuiteType.getProjectSuiteType(projectSuiteVo.getProjectSuiteType()) == ProjectSuiteType.CHEAP) {
            List<ProjectSuiteLowValueArticles> projectSuiteLowValueArticles = new ArrayList<>();
            projectSuiteLowValueArticles.addAll(getProjectSuiteLowValueArticles(projectSuiteVo.getArticlesList(), projectSuite));
            projectSuiteLowValueArticlesRepository.saveAll(projectSuiteLowValueArticles);
        }
        if (ProjectSuiteType.getProjectSuiteType(projectSuiteVo.getProjectSuiteType()) == ProjectSuiteType.EQUIPMENT) {
            List<ProjectSuiteArticles> projectSuiteArticles = new ArrayList<>();
            projectSuiteArticles.addAll(getProjectSuiteArticles(projectSuiteVo.getArticlesList(), projectSuite));
            projectSuiteArticlesRepository.saveAll(projectSuiteArticles);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public List<ProjectSuiteArticles> getProjectSuiteArticles(List<ArticlesVo> articlesVos, ProjectSuite projectSuite) {
        List<ProjectSuiteArticles> projectSuiteArticlesList = new ArrayList<>();
        articlesVos.stream().forEach(articlesVo -> {
            ProjectSuiteArticles projectSuiteArticles = new ProjectSuiteArticles();
            projectSuiteArticles.setArticlesId(articlesVo.getArticlesId());
            projectSuiteArticles.setArticlesNo(articlesVo.getArticlesNo());
            projectSuiteArticles.setNumber(articlesVo.getNumber());
            projectSuiteArticles.setProjectSuite(projectSuite);
            projectSuiteArticlesList.add(projectSuiteArticles);
        });
        return projectSuiteArticlesList;
    }

    public List<ProjectSuiteLowValueArticles> getProjectSuiteLowValueArticles(List<ArticlesVo> articlesVos, ProjectSuite projectSuite) {
        List<ProjectSuiteLowValueArticles> projectSuiteLowValueArticlesList = new ArrayList<>();
        articlesVos.stream().forEach(articlesVo -> {
            ProjectSuiteLowValueArticles projectSuiteLowValueArticles = new ProjectSuiteLowValueArticles();
            projectSuiteLowValueArticles.setLowValueArticlesId(articlesVo.getArticlesId());
            projectSuiteLowValueArticles.setLowValueArticlesNo(articlesVo.getArticlesNo());
            projectSuiteLowValueArticles.setNumber(articlesVo.getNumber());
            projectSuiteLowValueArticles.setProjectSuite(projectSuite);
            projectSuiteLowValueArticlesList.add(projectSuiteLowValueArticles);
        });
        return projectSuiteLowValueArticlesList;
    }

    public ResponseEntity<ProjectSuitePageVo> getProjectSuites(Integer page, Integer pageSize, String projectSuiteName, String projectSuiteType) {
        List<ArticlesModel> articlesModelList = findArticlesModelList(ProjectSuiteType.EQUIPMENT.getIndex());
        List<ArticlesModel> lowValueArticlesModeList = findArticlesModelList(ProjectSuiteType.CHEAP.getIndex());
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<ProjectSuite> projectSuitePages = projectSuiteRepository.findAll(specification(projectSuiteName, projectSuiteType), pageable);
        List<ProjectSuite> projectSuiteList = projectSuitePages.getContent();
        List<ProjectSuiteModel> projectSuiteModels = new ArrayList<>();
        projectSuiteList.stream().forEach(projectSuite -> {
            ProjectSuiteModel projectSuiteModel = new ProjectSuiteModel();
            projectSuiteModel.setProjectSuiteId(projectSuite.getId());
            projectSuiteModel.setProjectSuiteName(projectSuite.getProjectSuiteName());
            projectSuiteModel.setProjectSuiteTypeId(String.valueOf(projectSuite.getSuiteType().getIndex()));
            projectSuiteModel.setProjectSuiteTypeName(projectSuite.getSuiteType().getName());
            StringBuffer projectSuiteDetail = new StringBuffer();
            List<ProjectSuiteArticles> projectSuiteArticlesList = projectSuite.getProjectSuiteArticles();
            if (!CollectionUtils.isEmpty(projectSuiteArticlesList)){
                List<ArticlesModel> articlesModels = articlesModelList.stream()
                        .map(articlesModel -> projectSuiteArticlesList.stream()
                                .filter(projectSuiteArticles ->
                                        articlesModel.getArticlesId().equals(projectSuiteArticles.getArticlesId()))
                                .findFirst().map(projectSuiteArticles -> {
                                    articlesModel.setNumber(projectSuiteArticles.getNumber());
                                    return articlesModel;
                                }).orElse(null))
                        .filter(Objects::nonNull).collect(Collectors.toList());

                getProjectSuiteDetail(projectSuiteDetail,articlesModels);
            }
            List<ProjectSuiteLowValueArticles> projectSuiteLowValueArticlesList = projectSuite.getProjectSuiteLowValueArticles();
            if (!CollectionUtils.isEmpty(projectSuiteLowValueArticlesList)){
                List<ArticlesModel> articlesModels = lowValueArticlesModeList.stream()
                        .map(articlesModel -> projectSuiteLowValueArticlesList.stream()
                                .filter(projectSuiteLowValueArticles ->
                                        articlesModel.getArticlesId().equals(projectSuiteLowValueArticles.getLowValueArticlesId()))
                                .findFirst().map(projectSuiteLowValueArticles -> {
                                    articlesModel.setNumber(projectSuiteLowValueArticles.getNumber());
                                    return articlesModel;
                                }).orElse(null))
                        .filter(Objects::nonNull).collect(Collectors.toList());
                getProjectSuiteDetail(projectSuiteDetail,articlesModels);
            }
            /**明细**/
            projectSuiteModel.setProjectSuiteDetail(projectSuiteDetail.toString());
            projectSuiteModels.add(projectSuiteModel);
        });
        int total = (int) projectSuitePages.getTotalElements();
        return new ResponseEntity<>(getProjectSuitePageVo(total, page, pageSize, projectSuiteModels), HttpStatus.OK);
    }

    private ProjectSuitePageVo getProjectSuitePageVo(int total, Integer page, Integer pageSize,
                                                     List<ProjectSuiteModel> lowValueArticlesVoList) {
        ProjectSuitePageVo projectSuitePageVo = new ProjectSuitePageVo();
        projectSuitePageVo.setTotal(total);
        projectSuitePageVo.setPage(page);
        projectSuitePageVo.setPageSize(pageSize);
        projectSuitePageVo.setProjectSuiteModels(lowValueArticlesVoList);
        return projectSuitePageVo;
    }
    public List<ArticlesModel> findArticlesModelList(Integer articlesType) {
        RestTemplate restTemplate = new RestTemplate();
        String key = Md5Utils.md5(signKey);
        String url = "";
        List<ArticlesModel> articlesModels = new ArrayList<>();
        url = classRoomAddress + ConstantWebApi.GET_TYPE_ARTICLES + "?validCode=" + key + "&articlesType=" + articlesType;
        ResponseEntity<List<ArticlesModel>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<ArticlesModel>>() {
                });
        try {
            articlesModels = responseEntity.getBody();
            return articlesModels;
        } catch (Exception e) {
            e.printStackTrace();
            return articlesModels;
        }

    }
    public StringBuffer getProjectSuiteDetail(StringBuffer projectSuiteDetail,List<ArticlesModel> articlesModels){
        for (int i = 0 ; i< articlesModels.size(); i++){
            ArticlesModel articlesModel = articlesModels.get(i);
            if (i == 0 && StringUtils.isEmpty( projectSuiteDetail.toString())){
                projectSuiteDetail.append(articlesModel.getArticlesName()+
                        "("+articlesModel.getArticlesNorms()+")x"+articlesModel.getNumber());
            } else {
                projectSuiteDetail.append("," + articlesModel.getArticlesName()+
                        "("+articlesModel.getArticlesNorms()+")x"+articlesModel.getNumber());
            }
        }
        return projectSuiteDetail;
    }

    public ResponseEntity<ProjectSuiteInfo> getProjectSuiteInfo(String projectSuiteId) {
        List<ArticlesModel> articlesVoList = findArticlesModelList(ProjectSuiteType.EQUIPMENT.getIndex());
        List<ArticlesModel> lowValueArticlesVoList = findArticlesModelList(ProjectSuiteType.CHEAP.getIndex());
        ProjectSuite projectSuite = projectSuiteRepository.findByIdAndSuiteStatus(projectSuiteId,ProjectSuiteStatus.NORMAL);
        if (ObjectUtils.isEmpty(projectSuite)) {
            return new ResponseEntity(ErrorResult.dataNotExistError("项目卡套件"), HttpStatus.NOT_FOUND);
        }
        ProjectSuiteInfo projectSuiteInfo = new ProjectSuiteInfo();
        projectSuiteInfo.setProjectSuiteId(projectSuite.getId());
        projectSuiteInfo.setProjectSuiteName(projectSuite.getProjectSuiteName());
        projectSuiteInfo.setProjectSuiteType(projectSuite.getSuiteType().getIndex());
        List<ArticlesModel> articlesModelList = new ArrayList<>();
        List<ProjectSuiteArticles> projectSuiteArticlesList = projectSuite.getProjectSuiteArticles();
        if (!CollectionUtils.isEmpty(projectSuiteArticlesList)) {
            articlesModelList.addAll(articlesVoList.stream()
                    .map(articlesVo -> projectSuiteArticlesList.stream()
                            .filter(projectSuiteArticles ->
                                    projectSuiteArticles.getArticlesId().equals(articlesVo.getArticlesId()))
                            .findFirst().map(projectSuiteArticles -> {
                                articlesVo.setNumber(projectSuiteArticles.getNumber());
                                return articlesVo;
                            }).orElse(null))
                    .filter(Objects::nonNull).collect(Collectors.toList()));
        }
        List<ProjectSuiteLowValueArticles> projectSuiteLowValueArticlesList = projectSuite.getProjectSuiteLowValueArticles();
        if (!CollectionUtils.isEmpty(projectSuiteLowValueArticlesList)) {
            articlesModelList.addAll(lowValueArticlesVoList.stream()
                    .map(articlesVo -> projectSuiteLowValueArticlesList.stream()
                            .filter(projectSuiteLowValueArticles ->
                                    projectSuiteLowValueArticles.getLowValueArticlesId().equals(articlesVo.getArticlesId()))
                            .findFirst().map(projectSuiteLowValueArticles -> {
                                articlesVo.setNumber(projectSuiteLowValueArticles.getNumber());
                                return articlesVo;
                            }).orElse(null))
                    .filter(Objects::nonNull).collect(Collectors.toList()));
        }
        projectSuiteInfo.setArticlesList(articlesModelList);
        return new ResponseEntity<>(projectSuiteInfo, HttpStatus.OK);
    }
    public ResponseEntity<Void> deleteProjectSuite( String projectSuiteIds ){
        String[] projectSuiteIdList = projectSuiteIds.split(",");
        List<ProjectSuite> projectSuiteList = new ArrayList<>();
        for (String projectSuiteId: projectSuiteIdList) {
            ProjectSuite projectSuite = projectSuiteRepository.findByIdAndSuiteStatus(projectSuiteId,ProjectSuiteStatus.NORMAL);
            if (ObjectUtils.isEmpty(projectSuite)) {
                return new ResponseEntity(ErrorResult.dataNotExistError("项目卡套件"), HttpStatus.NOT_FOUND);
            }
            if (!CollectionUtils.isEmpty(projectSuite.getProjectCardProjectSuiteList())){
                return new ResponseEntity(ErrorResult.customError(projectSuite.getProjectSuiteName()+"套件正在" +
                        "使用中，不可删除"), HttpStatus.CONFLICT);
            }
            projectSuite.setSuiteStatus(ProjectSuiteStatus.REMOVE);
            projectSuiteList.add(projectSuite);
        }
        projectSuiteRepository.saveAll(projectSuiteList);
        return new ResponseEntity<>(HttpStatus.OK);

    }
    public  ResponseEntity<List<ArticlesModel>> getSuiteProjectSuiteIdLowValueArticles(
            List<ProjectSuiteResourceVo> projectSuiteResourceVos){
        List<ArticlesModel> lowValueArticlesModeList = findArticlesModelList(ProjectSuiteType.CHEAP.getIndex());
        List<ArticlesModel> articlesModels = new ArrayList<>();
        projectSuiteResourceVos.stream().forEach(projectSuiteResourceVo -> {
            int num = projectSuiteResourceVo.getNumber();
            ProjectSuite projectSuite = projectSuiteRepository.findById(projectSuiteResourceVo.getProjectSuiteId()).orElse(null);
            List<ProjectSuiteLowValueArticles> projectSuiteLowValueArticles = projectSuite.getProjectSuiteLowValueArticles();
            for (ProjectSuiteLowValueArticles projectSuiteLowValueArticle : projectSuiteLowValueArticles) {
                ArticlesModel articlesModel = new ArticlesModel();
                articlesModel = lowValueArticlesModeList.stream().filter(articlesModel1 ->
                        projectSuiteLowValueArticle.getLowValueArticlesId().equals(articlesModel1.getArticlesId())).findFirst()
                        .map(articlesModel1 ->new ArticlesModel(){{
                            this.articlesId(articlesModel1.getArticlesId());
                            this.articlesNo(articlesModel1.getArticlesNo());
                            this.articlesName(articlesModel1.getArticlesName());
                            this.articlesNorms(articlesModel1.getArticlesNorms());
                            this.articlesUnits(articlesModel1.getArticlesUnits());
                            this.articlesTypeId(articlesModel1.getArticlesTypeId());
                            this.articlesTypeNo(articlesModel1.getArticlesTypeNo());
                            this.articlesTypeName(articlesModel1.getArticlesTypeName());
                            this.price(articlesModel1.getPrice());
                            this.number(projectSuiteLowValueArticle.getNumber() * num);
                        }}).orElse(null);
                LOGGER.info("num ->{}",num);
                if (articlesModel!= null){
                    articlesModels.add(articlesModel);
                }
            }

        });
        List<ArticlesModel> articlesModelList = new ArrayList<>();
        articlesModels.parallelStream().collect(Collectors.groupingBy(ArticlesModel::getArticlesId, Collectors.toList()))
                .forEach((id, transfer) -> { transfer.stream().reduce((a, b) -> { a.setNumber(a.getNumber()+b.getNumber());
                        return a; }).ifPresent(articlesModelList::add); });
        return new ResponseEntity<>(articlesModelList,HttpStatus.OK);

    }
}
