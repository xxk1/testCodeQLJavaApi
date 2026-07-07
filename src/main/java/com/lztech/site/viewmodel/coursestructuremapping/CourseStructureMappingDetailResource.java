package com.lztech.site.viewmodel.coursestructuremapping;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseStructureMappingDetailResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-07-01T15:05:53.522+08:00")

public class CourseStructureMappingDetailResource {
    @JsonProperty("ocrStructureId")
    private String ocrStructureId = null;

    @JsonProperty("courseStructureId")
    private String courseStructureId = null;

    public CourseStructureMappingDetailResource ocrStructureId(String ocrStructureId) {
        this.ocrStructureId = ocrStructureId;
        return this;
    }

    /**
     * OCR识别的章节id
     *
     * @return ocrStructureId
     **/
    @ApiModelProperty(value = "OCR识别的章节id")


    public String getOcrStructureId() {
        return ocrStructureId;
    }

    public void setOcrStructureId(String ocrStructureId) {
        this.ocrStructureId = ocrStructureId;
    }

    public CourseStructureMappingDetailResource courseStructureId(String courseStructureId) {
        this.courseStructureId = courseStructureId;
        return this;
    }

    /**
     * 课程章节id
     *
     * @return courseStructureId
     **/
    @ApiModelProperty(value = "课程章节id")


    public String getCourseStructureId() {
        return courseStructureId;
    }

    public void setCourseStructureId(String courseStructureId) {
        this.courseStructureId = courseStructureId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseStructureMappingDetailResource courseStructureMappingDetailResource = (CourseStructureMappingDetailResource) o;
        return Objects.equals(this.ocrStructureId, courseStructureMappingDetailResource.ocrStructureId) &&
                Objects.equals(this.courseStructureId, courseStructureMappingDetailResource.courseStructureId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ocrStructureId, courseStructureId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseStructureMappingDetailResource {\n");

        sb.append("    ocrStructureId: ").append(toIndentedString(ocrStructureId)).append("\n");
        sb.append("    courseStructureId: ").append(toIndentedString(courseStructureId)).append("\n");
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

