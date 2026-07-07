package com.lztech.site.viewmodel.kggentask;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * KgAIGenTeachingMaterialAIGenTaskBaseResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-07-25T10:47:52.748+08:00")

public class KgAIGenTeachingMaterialAIGenTaskBaseResource {
    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("masterTaskId")
    private String masterTaskId = null;

    @JsonProperty("subTaskId")
    private String subTaskId = null;

    public KgAIGenTeachingMaterialAIGenTaskBaseResource courseId(String courseId) {
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

    public KgAIGenTeachingMaterialAIGenTaskBaseResource masterTaskId(String masterTaskId) {
        this.masterTaskId = masterTaskId;
        return this;
    }

    /**
     * 任务id
     *
     * @return masterTaskId
     **/
    @ApiModelProperty(value = "任务id")


    public String getMasterTaskId() {
        return masterTaskId;
    }

    public void setMasterTaskId(String masterTaskId) {
        this.masterTaskId = masterTaskId;
    }

    public KgAIGenTeachingMaterialAIGenTaskBaseResource subTaskId(String subTaskId) {
        this.subTaskId = subTaskId;
        return this;
    }

    /**
     * 子任务id
     *
     * @return subTaskId
     **/
    @ApiModelProperty(value = "子任务id")


    public String getSubTaskId() {
        return subTaskId;
    }

    public void setSubTaskId(String subTaskId) {
        this.subTaskId = subTaskId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        KgAIGenTeachingMaterialAIGenTaskBaseResource kgAIGenTeachingMaterialAIGenTaskBaseResource = (KgAIGenTeachingMaterialAIGenTaskBaseResource) o;
        return Objects.equals(this.courseId, kgAIGenTeachingMaterialAIGenTaskBaseResource.courseId) &&
                Objects.equals(this.masterTaskId, kgAIGenTeachingMaterialAIGenTaskBaseResource.masterTaskId) &&
                Objects.equals(this.subTaskId, kgAIGenTeachingMaterialAIGenTaskBaseResource.subTaskId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, masterTaskId, subTaskId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class KgAIGenTeachingMaterialAIGenTaskBaseResource {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    masterTaskId: ").append(toIndentedString(masterTaskId)).append("\n");
        sb.append("    subTaskId: ").append(toIndentedString(subTaskId)).append("\n");
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

