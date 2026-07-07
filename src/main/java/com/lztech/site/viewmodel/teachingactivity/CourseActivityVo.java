package com.lztech.site.viewmodel.teachingactivity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.Objects;

/**
 * CourseActivityVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-03-29T07:09:20.693Z")


public class CourseActivityVo {
    @JsonProperty("resourceId")
    private String resourceId = null;

    @JsonProperty("resourceName")
    private String resourceName = null;

    @JsonProperty("resourceType")
    private Integer resourceType = null;

    @JsonProperty("modifyTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifyTime = null;

    @JsonProperty("modifierName")
    private String modifierName = null;

    @JsonProperty("isPublic")
    private Integer isPublic = null;

    @JsonProperty("resourceReferences")
    private Integer resourceReferences = null;

    @JsonProperty("resourceDetailId")
    private String resourceDetailId = null;

    public CourseActivityVo resourceId(String resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    /**
     * 资源id
     *
     * @return resourceId
     **/
    @ApiModelProperty(value = "资源id")


    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public CourseActivityVo resourceName(String resourceName) {
        this.resourceName = resourceName;
        return this;
    }

    /**
     * 资源名称
     *
     * @return resourceName
     **/
    @ApiModelProperty(value = "资源名称")


    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public CourseActivityVo resourceType(Integer resourceType) {
        this.resourceType = resourceType;
        return this;
    }

    /**
     * 资源类型(2-测验 4-主题)
     *
     * @return resourceType
     **/
    @ApiModelProperty(value = "资源类型(2-测验 4-主题)")


    public Integer getResourceType() {
        return resourceType;
    }

    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    public CourseActivityVo modifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

    /**
     * 更新时间
     *
     * @return modifyTime
     **/
    @ApiModelProperty(value = "更新时间")


    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public CourseActivityVo modifierName(String modifierName) {
        this.modifierName = modifierName;
        return this;
    }

    /**
     * 更新人名称
     *
     * @return modifierName
     **/
    @ApiModelProperty(value = "更新人名称")


    public String getModifierName() {
        return modifierName;
    }

    public void setModifierName(String modifierName) {
        this.modifierName = modifierName;
    }

    public CourseActivityVo isPublic(Integer isPublic) {
        this.isPublic = isPublic;
        return this;
    }

    /**
     * 是否公开 0 不公开 1公开
     *
     * @return isPublic
     **/
    @ApiModelProperty(value = "是否公开 0 不公开 1公开")


    public Integer getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }

    public CourseActivityVo resourceReferences(Integer resourceReferences) {
        this.resourceReferences = resourceReferences;
        return this;
    }

    /**
     * 资源引用次数
     *
     * @return resourceReferences
     **/
    @ApiModelProperty(value = "资源引用次数")


    public Integer getResourceReferences() {
        return resourceReferences;
    }

    public void setResourceReferences(Integer resourceReferences) {
        this.resourceReferences = resourceReferences;
    }

    public CourseActivityVo resourceDetailId(String resourceDetailId) {
        this.resourceDetailId = resourceDetailId;
        return this;
    }

    /**
     * 资源详情id
     *
     * @return resourceDetailId
     **/
    @ApiModelProperty(value = "资源详情id")


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
        CourseActivityVo courseActivityVo = (CourseActivityVo) o;
        return Objects.equals(this.resourceId, courseActivityVo.resourceId) &&
                Objects.equals(this.resourceName, courseActivityVo.resourceName) &&
                Objects.equals(this.resourceType, courseActivityVo.resourceType) &&
                Objects.equals(this.modifyTime, courseActivityVo.modifyTime) &&
                Objects.equals(this.modifierName, courseActivityVo.modifierName) &&
                Objects.equals(this.isPublic, courseActivityVo.isPublic) &&
                Objects.equals(this.resourceReferences, courseActivityVo.resourceReferences) &&
                Objects.equals(this.resourceDetailId, courseActivityVo.resourceDetailId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resourceId, resourceName, resourceType, modifyTime, modifierName, isPublic, resourceReferences, resourceDetailId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseActivityVo {\n");

        sb.append("    resourceId: ").append(toIndentedString(resourceId)).append("\n");
        sb.append("    resourceName: ").append(toIndentedString(resourceName)).append("\n");
        sb.append("    resourceType: ").append(toIndentedString(resourceType)).append("\n");
        sb.append("    modifyTime: ").append(toIndentedString(modifyTime)).append("\n");
        sb.append("    modifierName: ").append(toIndentedString(modifierName)).append("\n");
        sb.append("    isPublic: ").append(toIndentedString(isPublic)).append("\n");
        sb.append("    resourceReferences: ").append(toIndentedString(resourceReferences)).append("\n");
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

