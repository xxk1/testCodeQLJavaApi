package com.lztech.site.controllers.api.courseknowledgegraph;

import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphDomain;
import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphNode;
import com.lztech.persistence.repositories.courseknowledgegraph.CourseKnowledgeGraphDomainRepository;
import com.lztech.site.service.course.CourseService;
import com.lztech.site.service.courseknowledgegraph.CourseKnowledgeGraphExportExcelUseTemplateService;
import com.lztech.site.until.Neo4jUtil;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.courseknowledgegraph.CourseKnowledgeGraphNodeDetailTreeModel;
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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-01-24T07:25:45.794Z")
@Controller
public class CourseKnowledgeGraphExportApiController implements CourseKnowledgeGraphExportApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseKnowledgeGraphExportApiController.class);

    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseKnowledgeGraphDomainRepository courseKnowledgeGraphDomainRepository;
    @Autowired
    private Neo4jUtil neo4jUtil;
    @Autowired
    private CourseKnowledgeGraphExportExcelUseTemplateService courseKnowledgeGraphExportExcelService;

    public ResponseEntity<Void> exportCourseKnowledgeGraphToExcel(
            @NotNull @ApiParam(value = "课程id", required = true) @Valid
            @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "验证码：courseId=xxx&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode, HttpServletResponse response) {
        if (StringUtils.isAnyBlank(validCode, courseId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("courseId", courseId, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            Course course = courseService.findById(courseId);
            if (ObjectUtils.isEmpty(course)) {
                return new ResponseEntity(ErrorResult.customError(courseId + "课程不存在"), HttpStatus.CONFLICT);
            }
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain =
                    courseKnowledgeGraphDomainRepository.getEffectiveCourseKnowledgeGraphDomainByCourseId(course.getId());
            if (ObjectUtils.isEmpty(courseKnowledgeGraphDomain)) {
                return new ResponseEntity(ErrorResult.customError(courseId + "课程知识点图不存在"), HttpStatus.CONFLICT);
            }
            CourseKnowledgeGraphNode courseKnowledgeGraphNode = neo4jUtil.getRootCourseKnowledgeGraphNode(courseKnowledgeGraphDomain.getId());
            if (neo4jUtil.getDomainNodeCount(courseKnowledgeGraphDomain.getId()) <= 1) {
                return new ResponseEntity(ErrorResult.customError(courseId + "课程知识点图不存在"), HttpStatus.CONFLICT);
            }
            CourseKnowledgeGraphNodeDetailTreeModel detailTreeModel =
                    neo4jUtil.getNodeDetailTree(courseKnowledgeGraphDomain.getId(), courseKnowledgeGraphNode.getId());
            if (Objects.isNull(detailTreeModel)) {
                return new ResponseEntity(ErrorResult.customError(courseId + "课程知识点图不存在"), HttpStatus.CONFLICT);
            }

            courseKnowledgeGraphExportExcelService.exportData(response, detailTreeModel, courseKnowledgeGraphDomain.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("exportCourseKnowledgeGraphToExcel:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
