package com.lztech.site.controllers.api.courseknowledgegraph;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.domain.model.course.Course;
import com.lztech.site.service.course.CourseService;
import com.lztech.site.service.courseknowledgegraph.CourseKnowledgeGraphStatisticsService;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.courseknowledgegraphstatistics.CourseKnowledgeGraphStatisticsVo;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2026-01-09T13:59:29.970+08:00")

@Controller
public class CourseKnowledgeGraphStatisticsController implements CourseKnowledgeGraphStatisticsApi {

    private static final Logger LOG = LoggerFactory.getLogger(CourseKnowledgeGraphStatisticsController.class);

    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseKnowledgeGraphStatisticsService courseKnowledgeGraphStatisticsService;

    @org.springframework.beans.factory.annotation.Autowired

    public CourseKnowledgeGraphStatisticsController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<CourseKnowledgeGraphStatisticsVo> getCourseKnowledgeGraphStatistics(
            @ApiParam(value = "课程id",required=true) @PathVariable("courseId") String courseId,
            @NotNull @ApiParam(value = "验证码：courseId=xxx&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "最小图谱语音相似性分数", required = true)
            @Valid @RequestParam(value = "minSimilarityScore", required = true) Double minSimilarityScore) {
        try {
            if (StringUtils.isAnyBlank(validCode, courseId)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (ObjectUtils.isEmpty(minSimilarityScore)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!ValidUtils.checkValidCode("courseId", courseId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            Course course = courseService.findById(courseId);
            if (ObjectUtils.isEmpty(course)) {
                return new ResponseEntity(ErrorResult.customError(courseId + "课程不存在"), HttpStatus.CONFLICT);
            }

            CourseKnowledgeGraphStatisticsVo statisticsVo =
                    courseKnowledgeGraphStatisticsService.getCourseKnowledgeGraphLevelPointStatistics(course,minSimilarityScore);

            return new ResponseEntity<>(statisticsVo, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("getCourseKnowledgeGraphStatistics:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
