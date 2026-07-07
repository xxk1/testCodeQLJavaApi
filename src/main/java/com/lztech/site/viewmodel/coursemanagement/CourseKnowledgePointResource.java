package com.lztech.site.viewmodel.coursemanagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseKnowledgePointResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-02-28T02:58:39.406Z")


public class CourseKnowledgePointResource {
    @JsonProperty("pointId")
    private String pointId = null;

    @JsonProperty("pointContent")
    private String pointContent = null;

    @JsonProperty("resourceList")
    @Valid
    private List<CourseResourceVo> resourceList = null;

    public CourseKnowledgePointResource pointId(String pointId) {
        this.pointId = pointId;
        return this;
    }

    /**
     * 知识点id
     *
     * @return pointId
     **/
    @ApiModelProperty(value = "知识点id")


    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
    }

    public CourseKnowledgePointResource pointContent(String pointContent) {
        this.pointContent = pointContent;
        return this;
    }

    /**
     * 知识点内容
     *
     * @return pointContent
     **/
    @ApiModelProperty(value = "知识点内容")


    public String getPointContent() {
        return pointContent;
    }

    public void setPointContent(String pointContent) {
        this.pointContent = pointContent;
    }

    public CourseKnowledgePointResource resourceList(List<CourseResourceVo> resourceList) {
        this.resourceList = resourceList;
        return this;
    }

    public CourseKnowledgePointResource addResourceListItem(CourseResourceVo resourceListItem) {
        if (this.resourceList == null) {
            this.resourceList = new ArrayList<CourseResourceVo>();
        }
        this.resourceList.add(resourceListItem);
        return this;
    }

    /**
     * Get resourceList
     *
     * @return resourceList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<CourseResourceVo> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<CourseResourceVo> resourceList) {
        this.resourceList = resourceList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseKnowledgePointResource courseKnowledgePointResource = (CourseKnowledgePointResource) o;
        return Objects.equals(this.pointId, courseKnowledgePointResource.pointId) &&
                Objects.equals(this.pointContent, courseKnowledgePointResource.pointContent) &&
                Objects.equals(this.resourceList, courseKnowledgePointResource.resourceList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pointId, pointContent, resourceList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseKnowledgePointResource {\n");

        sb.append("    pointId: ").append(toIndentedString(pointId)).append("\n");
        sb.append("    pointContent: ").append(toIndentedString(pointContent)).append("\n");
        sb.append("    resourceList: ").append(toIndentedString(resourceList)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

