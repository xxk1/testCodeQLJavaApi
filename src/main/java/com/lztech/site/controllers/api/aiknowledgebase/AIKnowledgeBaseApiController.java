package com.lztech.site.controllers.api.aiknowledgebase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.course.CourseVersion;
import com.lztech.domain.model.course.enums.CourseVersionStatus;
import com.lztech.persistence.repositories.courseversion.CourseVersionRepository;
import com.lztech.site.service.aiknowledgebase.AIKnowledgeBaseService;
import com.lztech.site.service.course.CourseService;
import com.lztech.site.until.RequestUtils;
import com.lztech.site.viewmodel.aiknowledgebase.AIKnowledgeBaseParam;
import com.lztech.site.viewmodel.aiknowledgebase.AIKnowledgeBaseVo;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-20T08:30:00.298Z")

@Controller
public class AIKnowledgeBaseApiController implements AIKnowledgeBaseApi {

    private final Logger log = LoggerFactory.getLogger(AIKnowledgeBaseApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Value("${signKey}")
    private String signKey;

    @Autowired
    private CourseService courseService;

    @Autowired
    private AIKnowledgeBaseService aiKnowledgeBaseService;

    @Autowired
    private CourseVersionRepository courseVersionRepository;

    @Autowired
    public AIKnowledgeBaseApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<AIKnowledgeBaseVo>> getAIKnowledgeBaseList(
            @NotNull @ApiParam(value = "&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "知识库查询条件", required = true)
            @Valid @RequestBody AIKnowledgeBaseParam aiKnowledgeBaseParam
    ) {
        try {
            if (StringUtils.isAnyBlank(aiKnowledgeBaseParam.getCourseId(), aiKnowledgeBaseParam.getUserId())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            Course course = courseService.findById(aiKnowledgeBaseParam.getCourseId());
            if (ObjectUtils.isEmpty(course)) {
                return new ResponseEntity(ErrorResult.customError("课程【" + aiKnowledgeBaseParam.getCourseId() + "】没有找到"),
                        HttpStatus.CONFLICT);
            }
            CourseVersion courseVersion = courseVersionRepository.findByCourseIdAndVersionStatus(course.getId(),
                    CourseVersionStatus.IN_USE.getValue()).stream().findFirst().orElse(null);
            if (ObjectUtils.isEmpty(courseVersion)) {
                return new ResponseEntity(ErrorResult.customError(
                        "课程【" + aiKnowledgeBaseParam.getCourseId() + "】运行版本没有找到"), HttpStatus.CONFLICT);
            }
            List<AIKnowledgeBaseVo> aiKnowledgeBaseVoList =
                    aiKnowledgeBaseService.getAIKnowledgeBaseList(course, courseVersion, aiKnowledgeBaseParam);

            return new ResponseEntity<>(aiKnowledgeBaseVoList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getAIKnowledgeBaseList:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
