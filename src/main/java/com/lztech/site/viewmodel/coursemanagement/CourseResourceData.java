package com.lztech.site.viewmodel.coursemanagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseResourceData
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-01-11T11:17:07.854Z")


public class CourseResourceData {
    @JsonProperty("fileName")
    private String fileName = null;

    @JsonProperty("resourceType")
    private Integer resourceType = null;

    @JsonProperty("resourceDetailId")
    private String resourceDetailId = null;

    public CourseResourceData fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    /**
     * 资源名称
     *
     * @return fileName
     **/
    @ApiModelProperty(value = "资源名称")


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public CourseResourceData resourceType(Integer resourceType) {
        this.resourceType = resourceType;
        return this;
    }

    /**
     * 资源类型 2:课堂测验
     *
     * @return resourceType
     **/
    @ApiModelProperty(value = "资源类型 2:课堂测验")


    public Integer getResourceType() {
        return resourceType;
    }

    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    public CourseResourceData resourceDetailId(String resourceDetailId) {
        this.resourceDetailId = resourceDetailId;
        return this;
    }

    /**
     * 资源详情Id
     *
     * @return resourceDetailId
     **/
    @ApiModelProperty(value = "资源详情Id")


    public String getResourceDetailId() {
        return resourceDetailId;
    }

    public void setResourceDetailId(String resourceDetailId) {
        this.resourceDetailId = resourceDetailId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseResourceData courseResourceData = (CourseResourceData) o;
        return Objects.equals(this.fileName, courseResourceData.fileName) &&
                Objects.equals(this.resourceType, courseResourceData.resourceType) &&
                Objects.equals(this.resourceDetailId, courseResourceData.resourceDetailId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, resourceType, resourceDetailId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseResourceData {\n");

        sb.append("    fileName: ").append(toIndentedString(fileName)).append("\n");
        sb.append("    resourceType: ").append(toIndentedString(resourceType)).append("\n");
        sb.append("    resourceDetailId: ").append(toIndentedString(resourceDetailId)).append("\n");
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

