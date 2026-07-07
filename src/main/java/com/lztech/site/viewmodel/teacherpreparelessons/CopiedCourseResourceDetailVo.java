package com.lztech.site.viewmodel.teacherpreparelessons;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CopiedCourseResourceDetailVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-03-02T06:18:18.885Z")

public class CopiedCourseResourceDetailVo {
    @JsonProperty("resourceId")
    private String resourceId = null;

    @JsonProperty("resourceDetailId")
    private String resourceDetailId = null;

    @JsonProperty("resourceType")
    private Integer resourceType = null;

    @JsonProperty("copiedResourceDetailId")
    private String copiedResourceDetailId = null;

    public CopiedCourseResourceDetailVo resourceId(String resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    /**
     * 资源ID
     *
     * @return resourceId
     **/
    @ApiModelProperty(value = "资源ID")


    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public CopiedCourseResourceDetailVo resourceDetailId(String resourceDetailId) {
        this.resourceDetailId = resourceDetailId;
        return this;
    }

    /**
     * 资源详情ID
     *
     * @return resourceDetailId
     **/
    @ApiModelProperty(value = "资源详情ID")


    public String getResourceDetailId() {
        return resourceDetailId;
    }

    public void setResourceDetailId(String resourceDetailId) {
        this.resourceDetailId = resourceDetailId;
    }

    public CopiedCourseResourceDetailVo resourceType(Integer resourceType) {
        this.resourceType = resourceType;
        return this;
    }

    /**
     * 资源类型
     *
     * @return resourceType
     **/
    @ApiModelProperty(value = "资源类型")


    public Integer getResourceType() {
        return resourceType;
    }

    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    public CopiedCourseResourceDetailVo copiedResourceDetailId(String copiedResourceDetailId) {
        this.copiedResourceDetailId = copiedResourceDetailId;
        return this;
    }

    /**
     * 复制之后的资源详情ID
     *
     * @return copiedResourceDetailId
     **/
    @ApiModelProperty(value = "复制之后的资源详情ID")


    public String getCopiedResourceDetailId() {
        return copiedResourceDetailId;
    }

    public void setCopiedResourceDetailId(String copiedResourceDetailId) {
        this.copiedResourceDetailId = copiedResourceDetailId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CopiedCourseResourceDetailVo copiedCourseResourceDetailVo = (CopiedCourseResourceDetailVo) o;
        return Objects.equals(this.resourceId, copiedCourseResourceDetailVo.resourceId) &&
                Objects.equals(this.resourceDetailId, copiedCourseResourceDetailVo.resourceDetailId) &&
                Objects.equals(this.resourceType, copiedCourseResourceDetailVo.resourceType) &&
                Objects.equals(this.copiedResourceDetailId, copiedCourseResourceDetailVo.copiedResourceDetailId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resourceId, resourceDetailId, resourceType, copiedResourceDetailId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CopiedCourseResourceDetailVo {\n");

        sb.append("    resourceId: ").append(toIndentedString(resourceId)).append("\n");
        sb.append("    resourceDetailId: ").append(toIndentedString(resourceDetailId)).append("\n");
        sb.append("    resourceType: ").append(toIndentedString(resourceType)).append("\n");
        sb.append("    copiedResourceDetailId: ").append(toIndentedString(copiedResourceDetailId)).append("\n");
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

