package com.lztech.site.viewmodel.courseexpansion;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseExpansionCreateResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-12-08T06:03:14.171Z")

public class CourseExpansionCreateResource {
    @JsonProperty("expansionKey")
    private String expansionKey = null;

    @JsonProperty("expansionKeyName")
    private String expansionKeyName = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseCode")
    private String courseCode = null;

    @JsonProperty("creatorId")
    private String creatorId = null;

    @JsonProperty("creatorName")
    private String creatorName = null;

    public String getExpansionKeyName() {
        return expansionKeyName;
    }

    public void setExpansionKeyName(String expansionKeyName) {
        this.expansionKeyName = expansionKeyName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public CourseExpansionCreateResource expansionKey(String expansionKey) {
        this.expansionKey = expansionKey;
        return this;
    }

    /**
     * 扩展属性key
     *
     * @return expansionKey
     **/
    @ApiModelProperty(value = "扩展属性key")


    public String getExpansionKey() {
        return expansionKey;
    }

    public void setExpansionKey(String expansionKey) {
        this.expansionKey = expansionKey;
    }

    public CourseExpansionCreateResource courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    /**
     * 课程id
     *
     * @return courseId
     **/
    @ApiModelProperty(value = "课程id")


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public CourseExpansionCreateResource creatorId(String creatorId) {
        this.creatorId = creatorId;
        return this;
    }

    /**
     * 创建人id
     *
     * @return creatorId
     **/
    @ApiModelProperty(value = "创建人id")


    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public CourseExpansionCreateResource creatorName(String creatorName) {
        this.creatorName = creatorName;
        return this;
    }

    /**
     * 创建人姓名
     *
     * @return creatorName
     **/
    @ApiModelProperty(value = "创建人姓名")


    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseExpansionCreateResource courseExpansionCreateResource = (CourseExpansionCreateResource) o;
        return Objects.equals(this.expansionKey, courseExpansionCreateResource.expansionKey) &&
                Objects.equals(this.courseId, courseExpansionCreateResource.courseId) &&
                Objects.equals(this.creatorId, courseExpansionCreateResource.creatorId) &&
                Objects.equals(this.creatorName, courseExpansionCreateResource.creatorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expansionKey, courseId, creatorId, creatorName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseExpansionCreateResource {\n");

        sb.append("    expansionKey: ").append(toIndentedString(expansionKey)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    creatorId: ").append(toIndentedString(creatorId)).append("\n");
        sb.append("    creatorName: ").append(toIndentedString(creatorName)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

