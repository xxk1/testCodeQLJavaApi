package com.lztech.site.viewmodel.coursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseInfoCode
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-11-25T07:29:03.161Z")


public class CourseInfoCode {
    @JsonProperty("courseInfoCode")
    private String courseInfoCode = null;

    public CourseInfoCode courseInfoCode(String courseInfoCode) {
        this.courseInfoCode = courseInfoCode;
        return this;
    }

    /**
     * 开课通知单号
     *
     * @return courseInfoCode
     **/
    @ApiModelProperty(value = "开课通知单号")


    public String getCourseInfoCode() {
        return courseInfoCode;
    }

    public void setCourseInfoCode(String courseInfoCode) {
        this.courseInfoCode = courseInfoCode;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseInfoCode courseInfoCode = (CourseInfoCode) o;
        return Objects.equals(this.courseInfoCode, courseInfoCode.courseInfoCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseInfoCode);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseInfoCode {\n");

        sb.append("    courseInfoCode: ").append(toIndentedString(courseInfoCode)).append("\n");
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

