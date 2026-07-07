package com.lztech.site.controllers.api.courseresources;

import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphDomain;
import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphNode;
import com.lztech.site.service.courseknowledgegraph.CourseKnowledgeGraphService;
import com.lztech.site.service.courseresources.CourseResourceIntelligentRecommendationService;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.courseknowledgegraph.CourseKnowledgeGraphNodeVideoInfoTextModel;
import com.lztech.site.viewmodel.courseresourceintelligentrecommendation.CourseResourceIntelligentRecommendationQueryParam;
import com.lztech.site.viewmodel.courseresourceintelligentrecommendation.CourseResourceIntelligentRecommendationTermResource;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-06-19T13:50:47.154+08:00")

@Controller
public class CourseResourceIntelligentRecommendationApiController implements CourseResourceIntelligentRecommendationApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseResourceIntelligentRecommendationApiController.class);

    @Autowired
    private CourseResourceIntelligentRecommendationService courseResourceIntelligentRecommendationService;
    @Autowired
    private CourseKnowledgeGraphService courseKnowledgeGraphService;

    public ResponseEntity<List<CourseResourceIntelligentRecommendationTermResource>>
    getCourseResourceIntelligentRecommendationTermList(
            @NotNull
            @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "课程id", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "课程知识图谱知识点id", required = true)
            @Valid @RequestParam(value = "courseKnowledgeGraphNodeId", required = true) String courseKnowledgeGraphNodeId,
            @NotNull @ApiParam(value = "课程知识图谱id", required = true)
            @Valid @RequestParam(value = "courseKnowledgeGraphId", required = true) String courseKnowledgeGraphId,
            @ApiParam(value = "课程版本id")
            @Valid @RequestParam(value = "courseVersionId", required = false) String courseVersionId,
            @ApiParam(value = "相似性分数")
            @Valid @RequestParam(value = "similarityScore", required = false) Double similarityScore
    ) {
        if (StringUtils.isAnyBlank(validCode, courseId, courseKnowledgeGraphNodeId, courseKnowledgeGraphId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }

        try {
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphDomainById(
                            courseKnowledgeGraphId);
            if (ObjectUtils.isEmpty(courseKnowledgeGraphDomain)) {
                return new ResponseEntity(ErrorResult.customError("id为【" +
                        courseKnowledgeGraphId
                        + "】的课程知识图谱不存在"), HttpStatus.CONFLICT);
            }

            if (!courseKnowledgeGraphDomain.getStatus()) {
                return new ResponseEntity(ErrorResult.customError("当前页面知识图谱已归档"), HttpStatus.CONFLICT);
            }

            String lastVersionLabel = courseKnowledgeGraphService.getLastVersionLabel(courseId);
            if (StringUtils.isNotBlank(lastVersionLabel) && !lastVersionLabel.equals(courseKnowledgeGraphDomain.getVersionLabel())) {
                courseKnowledgeGraphService.updateCourseKnowledgeGraphDomainStatus(courseKnowledgeGraphId, false);
                return new ResponseEntity(ErrorResult.customError("当前页面知识图谱已归档"), HttpStatus.CONFLICT);
            }
            CourseKnowledgeGraphNode courseKnowledgeGraphNode =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphNode(courseKnowledgeGraphDomain.getId(),
                            courseKnowledgeGraphNodeId);
            if (ObjectUtils.isEmpty(courseKnowledgeGraphNode)) {
                return new ResponseEntity(ErrorResult.customError(
                        courseKnowledgeGraphNodeId + "知识点不存在"), HttpStatus.CONFLICT);
            }
            List<CourseResourceIntelligentRecommendationTermResource> termResourceList =
                    courseResourceIntelligentRecommendationService.getTermList(courseId,
                            courseVersionId,courseKnowledgeGraphDomain, courseKnowledgeGraphNode,similarityScore);
            return new ResponseEntity<>(termResourceList, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getCourseResourceIntelligentRecommendationTermList", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    public ResponseEntity<List<CourseKnowledgeGraphNodeVideoInfoTextModel>> getCourseResourceIntelligentRecommendationNodeVideoInfoTexts(
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "查询参数" ,required=true )
            @Valid @RequestBody
            CourseResourceIntelligentRecommendationQueryParam queryParams
    ) {
        if (Objects.isNull(queryParams)||StringUtils.isAnyBlank(validCode,queryParams.getCourseId(),queryParams.getCourseKnowledgeGraphId(),
                queryParams.getCourseKnowledgeGraphNodeId(),queryParams.getTeacherIds())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)){
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<CourseKnowledgeGraphNodeVideoInfoTextModel> courseKnowledgeGraphNodeVideoInfoTexts =
                    courseResourceIntelligentRecommendationService.getCourseKnowledgeGraphNodeVideoInfoTexts(queryParams);
            return new ResponseEntity<>(courseKnowledgeGraphNodeVideoInfoTexts, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getCourseResourceIntelligentRecommendationNodeVideoInfoTexts", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



}
