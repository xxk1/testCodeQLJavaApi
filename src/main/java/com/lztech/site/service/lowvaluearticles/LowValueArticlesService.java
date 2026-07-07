package com.lztech.site.service.lowvaluearticles;

import com.lztech.domain.model.projectcard.ProjectCard;
import com.lztech.domain.model.projectcardprojectsuite.ProjectCardProjectSuite;
import com.lztech.domain.model.projectsuite.ProjectSuite;
import com.lztech.persistence.repositories.projectcard.ProjectCardRepository;
import com.lztech.site.constants.ConstantWebApi;
import com.lztech.site.until.DateUtils;
import com.lztech.site.until.Md5Utils;
import com.lztech.site.viewmodel.lowvaluearticles.LowValueArticlesResource;
import com.lztech.site.viewmodel.lowvaluearticles.ProjectSuiteLowValueArticlesVo;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LowValueArticlesService {
    private final Logger log = LoggerFactory.getLogger(LowValueArticlesService.class);
    @Value("${request.address.classRoom}")
    private String classRoomApiUrl;
    @Value("${signKey}")
    private String signKey;
    @Autowired
    private ProjectCardRepository projectCardRepository;

    public ResponseEntity<List<LowValueArticlesResource>> getProjectByIds(List<String> projectIds, Integer appointmentPeopleNumber) {
        List<ProjectCard> projectCardList = projectCardRepository.findByProjectIdIn(projectIds);
        List<ProjectSuiteLowValueArticlesVo> lowValueArticlesVos = new ArrayList<>();
        projectCardList.forEach(projectCard -> {
            List<ProjectSuite> projectSuites = projectCard.getProjectCardProjectSuiteList()
                    .stream()
                    .map(ProjectCardProjectSuite::getProjectSuite)
                    .collect(Collectors.toList());
            projectSuites.forEach(projectSuite -> {
                projectSuite.getProjectSuiteLowValueArticles().forEach(projectSuiteLowValueArticles -> {
                    ProjectSuiteLowValueArticlesVo lowValueArticlesVo = new ProjectSuiteLowValueArticlesVo();
                    lowValueArticlesVo.setLowValueArticlesId(projectSuiteLowValueArticles.getLowValueArticlesId());
                    lowValueArticlesVo.setNumber(projectSuiteLowValueArticles.getNumber());
                    lowValueArticlesVos.add(lowValueArticlesVo);
                });
            });
        });
        List<LowValueArticlesResource> lowValueArticlesResources = getLowValueArticlesResourceList(getLowValueArticlesResources(lowValueArticlesVos));
        lowValueArticlesResources = lowValueArticlesResources.stream()
                .peek(lowValueArticlesResource -> {
                    lowValueArticlesResource.setUsage(lowValueArticlesResource.getUsage() * appointmentPeopleNumber);
                }).collect(Collectors.toList());
        return new ResponseEntity<>(lowValueArticlesResources, HttpStatus.OK);
    }

    private List<LowValueArticlesResource> getLowValueArticlesResourceList(List<LowValueArticlesResource> lowValueArticlesResources) {
        HashMap<String, LowValueArticlesResource> hashMap = new HashMap<>();
        //去掉重复的key
        for (LowValueArticlesResource lowValueArticlesResource : lowValueArticlesResources) {
            String lowValueArticlesId = lowValueArticlesResource.getLowValueArticlesId();
            if (hashMap.containsKey(lowValueArticlesId)) {
                LowValueArticlesResource newArticlesResource = new LowValueArticlesResource();
                newArticlesResource.setLowValueArticlesId(lowValueArticlesId);
                newArticlesResource.setLowValueArticlesName(lowValueArticlesResource.getLowValueArticlesName());
                newArticlesResource.setLowValueArticlesNo(lowValueArticlesResource.getLowValueArticlesNo());
                newArticlesResource.setLowValueArticlesTypeId(lowValueArticlesResource.getLowValueArticlesTypeId());
                newArticlesResource.setLowValueArticlesTypeName(lowValueArticlesResource.getLowValueArticlesTypeName());
                newArticlesResource.setSpecification(lowValueArticlesResource.getSpecification());
                newArticlesResource.setEmployDate(DateUtils.formatDate(DateUtils.DATE, new Date()));
                newArticlesResource.setPrice(lowValueArticlesResource.getPrice());
                newArticlesResource.setUnits(lowValueArticlesResource.getUnits());
                //合并相同key的value
                newArticlesResource.setUsage(hashMap.get(lowValueArticlesId).getUsage() + lowValueArticlesResource.getUsage());
                //HashMap不允许key重复，当有key重复时，前面key对应的value值会被覆盖
                hashMap.put(lowValueArticlesId, newArticlesResource);
            } else {
                hashMap.put(lowValueArticlesId, lowValueArticlesResource);
            }
        }
        //去除重复key的list
        List<LowValueArticlesResource> lowValueArticlesResourceList = new ArrayList<>();
        for (String temp : hashMap.keySet()) {
            lowValueArticlesResourceList.add(hashMap.get(temp));
        }
        return lowValueArticlesResourceList;
    }

    public List<LowValueArticlesResource> getLowValueArticlesResources(List<ProjectSuiteLowValueArticlesVo> lowValueArticlesVos) {
        RestTemplate restTemplate = new RestTemplate();
        List<LowValueArticlesResource> articlesResources = new ArrayList<>();
        String url = classRoomApiUrl + ConstantWebApi.POST_LOW_VALUE_ARTICLES + "?validCode=" + Md5Utils.md5(signKey);
        try {
            ResponseEntity<List> responseEntity = restTemplate.postForEntity(url, lowValueArticlesVos, List.class);
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                for (Object object : Objects.requireNonNull(responseEntity.getBody())) {
                    JSONObject jsonObject = JSONObject.fromObject(object);
                    LowValueArticlesResource lowValueArticlesResource = (LowValueArticlesResource)
                            JSONObject.toBean(jsonObject, LowValueArticlesResource.class);
                    articlesResources.add(lowValueArticlesResource);
                }
            }
            return articlesResources;
        } catch (Exception e) {
            log.error("getLowValueArticlesResources->{}", e.getMessage());
            e.printStackTrace();
            return articlesResources;
        }
    }
}
