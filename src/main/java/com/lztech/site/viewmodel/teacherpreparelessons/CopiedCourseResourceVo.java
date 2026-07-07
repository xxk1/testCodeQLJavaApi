package com.lztech.site.viewmodel.teacherpreparelessons;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CopiedCourseResourceVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-03-02T06:18:18.885Z")

public class CopiedCourseResourceVo {
    @JsonProperty("operatorId")
    private String operatorId = null;

    @JsonProperty("operatorName")
    private String operatorName = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseVersionId")
    private String courseVersionId = null;

    @JsonProperty("resourceList")
    @Valid
    private List<CopiedCourseResourceDetailVo> resourceList = null;

    public CopiedCourseResourceVo operatorId(String operatorId) {
        this.operatorId = operatorId;
        return this;
    }

    /**
     * 操作人ID
     *
     * @return operatorId
     **/
    @ApiModelProperty(value = "操作人ID")


    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public CopiedCourseResourceVo operatorName(String operatorName) {
        this.operatorName = operatorName;
        return this;
    }

    /**
     * 操作人姓名
     *
     * @return operatorName
     **/
    @ApiModelProperty(value = "操作人姓名")


    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public CopiedCourseResourceVo courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    /**
     * 课程ID
     *
     * @return courseId
     **/
    @ApiModelProperty(value = "课程ID")


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public CopiedCourseResourceVo courseVersionId(String courseVersionId) {
        this.courseVersionId = courseVersionId;
        return this;
    }

    /**
     * 课程版本ID
     *
     * @return courseVersionId
     **/
    @ApiModelProperty(value = "课程版本ID")


    public String getCourseVersionId() {
        return courseVersionId;
    }

    public void setCourseVersionId(String courseVersionId) {
        this.courseVersionId = courseVersionId;
    }

    public CopiedCourseResourceVo resourceList(List<CopiedCourseResourceDetailVo> resourceList) {
        this.resourceList = resourceList;
        return this;
    }

    public CopiedCourseResourceVo addResourceListItem(CopiedCourseResourceDetailVo resourceListItem) {
        if (this.resourceList == null) {
            this.resourceList = new ArrayList<CopiedCourseResourceDetailVo>();
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

    public List<CopiedCourseResourceDetailVo> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<CopiedCourseResourceDetailVo> resourceList) {
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
        CopiedCourseResourceVo copiedCourseResourceVo = (CopiedCourseResourceVo) o;
        return Objects.equals(this.operatorId, copiedCourseResourceVo.operatorId) &&
                Objects.equals(this.operatorName, copiedCourseResourceVo.operatorName) &&
                Objects.equals(this.courseId, copiedCourseResourceVo.courseId) &&
                Objects.equals(this.courseVersionId, copiedCourseResourceVo.courseVersionId) &&
                Objects.equals(this.resourceList, copiedCourseResourceVo.resourceList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operatorId, operatorName, courseId, courseVersionId, resourceList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CopiedCourseResourceVo {\n");

        sb.append("    operatorId: ").append(toIndentedString(operatorId)).append("\n");
        sb.append("    operatorName: ").append(toIndentedString(operatorName)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseVersionId: ").append(toIndentedString(courseVersionId)).append("\n");
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

