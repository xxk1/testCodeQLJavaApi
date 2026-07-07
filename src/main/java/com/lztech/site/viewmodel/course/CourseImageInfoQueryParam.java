package com.lztech.site.viewmodel.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseCoverVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-01-12T07:46:17.139Z")


public class CourseImageInfoQueryParam {
    @JsonProperty("courseIds")
    private String courseIds = null;

    public CourseImageInfoQueryParam courseIds(String courseIds) {
        this.courseIds = courseIds;
        return this;
    }

    /**
     * 课程id(多个,拼接)
     *
     * @return courseIds
     **/
    @ApiModelProperty(value = "课程id(多个,拼接)")


    public String getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(String courseIds) {
        this.courseIds = courseIds;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseImageInfoQueryParam courseImageInfoQueryParam = (CourseImageInfoQueryParam) o;
        return Objects.equals(this.courseIds, courseImageInfoQueryParam.courseIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseIds);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class courseImageInfoQueryParam {\n");

        sb.append("    courseIds: ").append(toIndentedString(courseIds)).append("\n");
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

