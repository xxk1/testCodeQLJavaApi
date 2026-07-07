package com.lztech.site.viewmodel.courseresource;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseResourceDetailVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-03-22T09:43:51.375Z")


public class CourseResourceDetailVo {
    @JsonProperty("resourceId")
    private String resourceId = null;

    @JsonProperty("resourceDetailId")
    private String resourceDetailId = null;

    @JsonProperty("isPublic")
    private Boolean isPublic = null;

    public CourseResourceDetailVo resourceId(String resourceId) {
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

    public CourseResourceDetailVo resourceDetailId(String resourceDetailId) {
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

    public CourseResourceDetailVo isPublic(Boolean isPublic) {
        this.isPublic = isPublic;
        return this;
    }

    /**
     * 是否公开
     *
     * @return isPublic
     **/
    @ApiModelProperty(value = "是否公开")


    public Boolean isIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseResourceDetailVo courseResourceDetailVo = (CourseResourceDetailVo) o;
        return Objects.equals(this.resourceId, courseResourceDetailVo.resourceId) &&
                Objects.equals(this.resourceDetailId, courseResourceDetailVo.resourceDetailId) &&
                Objects.equals(this.isPublic, courseResourceDetailVo.isPublic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resourceId, resourceDetailId, isPublic);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseResourceDetailVo {\n");

        sb.append("    resourceId: ").append(toIndentedString(resourceId)).append("\n");
        sb.append("    resourceDetailId: ").append(toIndentedString(resourceDetailId)).append("\n");
        sb.append("    isPublic: ").append(toIndentedString(isPublic)).append("\n");
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

