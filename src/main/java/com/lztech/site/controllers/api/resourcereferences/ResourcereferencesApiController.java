package com.lztech.site.controllers.api.resourcereferences;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.domain.model.course.CourseResource;
import com.lztech.domain.model.course.enums.ResourceStatus;
import com.lztech.persistence.repositories.course.CourseResourceRepository;
import com.lztech.site.service.resourcereferences.ResourceReferencesService;
import com.lztech.site.until.RequestUtils;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.lztech.site.config.Access.signKey;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-03-05T08:18:49.495Z")

@Controller
public class ResourcereferencesApiController implements ResourcereferencesApi {

    private final Logger log = LoggerFactory.getLogger(ResourcereferencesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ResourcereferencesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Autowired
    private ResourceReferencesService resourceReferencesService;
    @Autowired
    private CourseResourceRepository resourceRepository;

    public ResponseEntity<Void> resourcereferencesPost(
            @NotNull @ApiParam(value = "资源Id,多个以‘,’分割", required = true)
            @Valid @RequestParam(value = "resourceIds", required = true) String resourceIds,
            @ApiParam(value = "引用人id")
            @Valid @RequestParam(value = "quotePeopleId", required = false) String quotePeopleId,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyBlank(validCode, resourceIds)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            List<String> ids = Arrays.asList(resourceIds.split(","));
            Map<String, Long> idMap = ids.stream()
                    .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
            List<CourseResource> courseResources = resourceRepository.findByIdInAndResourceStatus(ids, ResourceStatus.NORMAL);
            if (CollectionUtils.isEmpty(courseResources)) {
                return new ResponseEntity(ErrorResult.dataNotExistError("资源"), HttpStatus.NOT_FOUND);
            }
            resourceReferencesService.updateCourseResource(courseResources,quotePeopleId,idMap);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("resourcereferencesPost->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> updateReferenceNumByResourceDetailId(@NotNull @ApiParam(value = "资源详情Id", required = true)
                                                                     @Valid @RequestParam(value = "resourceDetailId", required = true)
                                                                             String resourceDetailId,
                                                                     @NotNull @ApiParam(value = "验证码：resourceDetailId={}&signKey=123123(md5加密)",
                                                                             required = true)
                                                                     @Valid @RequestParam(value = "validCode", required = true)
                                                                             String validCode) {
        if (StringUtils.isAnyBlank(validCode, resourceDetailId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("resourceDetailId", resourceDetailId, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            resourceReferencesService.updateReferenceNumByResourceDetailId(resourceDetailId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("updateReferenceNumByResourceDetailId->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
