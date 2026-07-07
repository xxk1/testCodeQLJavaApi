package com.lztech.site.viewmodel.coursemanagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ReorderParams
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-01-05T03:09:15.987Z")


public class ReorderParams {
    @JsonProperty("teacherId")
    private String teacherId = null;

    @JsonProperty("courseStructureId")
    private String courseStructureId = null;

    @JsonProperty("needToBeMovedResourceId")
    private String needToBeMovedResourceId = null;

    @JsonProperty("targetLocationBeforeResourceId")
    private String targetLocationBeforeResourceId = null;

    @JsonProperty("targetLocationAfterResourceId")
    private String targetLocationAfterResourceId = null;

    public ReorderParams teacherId(String teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    /**
     * 教师id
     *
     * @return teacherId
     **/
    @ApiModelProperty(value = "教师id")


    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public ReorderParams courseStructureId(String courseStructureId) {
        this.courseStructureId = courseStructureId;
        return this;
    }

    /**
     * 课程结构id
     *
     * @return courseStructureId
     **/
    @ApiModelProperty(value = "课程结构id")


    public String getCourseStructureId() {
        return courseStructureId;
    }

    public void setCourseStructureId(String courseStructureId) {
        this.courseStructureId = courseStructureId;
    }

    public ReorderParams needToBeMovedResourceId(String needToBeMovedResourceId) {
        this.needToBeMovedResourceId = needToBeMovedResourceId;
        return this;
    }

    /**
     * 被移动的资源id
     *
     * @return needToBeMovedResourceId
     **/
    @ApiModelProperty(value = "被移动的资源id")


    public String getNeedToBeMovedResourceId() {
        return needToBeMovedResourceId;
    }

    public void setNeedToBeMovedResourceId(String needToBeMovedResourceId) {
        this.needToBeMovedResourceId = needToBeMovedResourceId;
    }

    public ReorderParams targetLocationBeforeResourceId(String targetLocationBeforeResourceId) {
        this.targetLocationBeforeResourceId = targetLocationBeforeResourceId;
        return this;
    }

    /**
     * 目标位置前资源id
     *
     * @return targetLocationBeforeResourceId
     **/
    @ApiModelProperty(value = "目标位置前资源id")


    public String getTargetLocationBeforeResourceId() {
        return targetLocationBeforeResourceId;
    }

    public void setTargetLocationBeforeResourceId(String targetLocationBeforeResourceId) {
        this.targetLocationBeforeResourceId = targetLocationBeforeResourceId;
    }

    public ReorderParams targetLocationAfterResourceId(String targetLocationAfterResourceId) {
        this.targetLocationAfterResourceId = targetLocationAfterResourceId;
        return this;
    }

    /**
     * 目标位置后资源id
     *
     * @return targetLocationAfterResourceId
     **/
    @ApiModelProperty(value = "目标位置后资源id")


    public String getTargetLocationAfterResourceId() {
        return targetLocationAfterResourceId;
    }

    public void setTargetLocationAfterResourceId(String targetLocationAfterResourceId) {
        this.targetLocationAfterResourceId = targetLocationAfterResourceId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReorderParams reorderParams = (ReorderParams) o;
        return Objects.equals(this.teacherId, reorderParams.teacherId) &&
                Objects.equals(this.courseStructureId, reorderParams.courseStructureId) &&
                Objects.equals(this.needToBeMovedResourceId, reorderParams.needToBeMovedResourceId) &&
                Objects.equals(this.targetLocationBeforeResourceId, reorderParams.targetLocationBeforeResourceId) &&
                Objects.equals(this.targetLocationAfterResourceId, reorderParams.targetLocationAfterResourceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, courseStructureId, needToBeMovedResourceId, targetLocationBeforeResourceId, targetLocationAfterResourceId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ReorderParams {\n");

        sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
        sb.append("    courseStructureId: ").append(toIndentedString(courseStructureId)).append("\n");
        sb.append("    needToBeMovedResourceId: ").append(toIndentedString(needToBeMovedResourceId)).append("\n");
        sb.append("    targetLocationBeforeResourceId: ").append(toIndentedString(targetLocationBeforeResourceId)).append("\n");
        sb.append("    targetLocationAfterResourceId: ").append(toIndentedString(targetLocationAfterResourceId)).append("\n");
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

