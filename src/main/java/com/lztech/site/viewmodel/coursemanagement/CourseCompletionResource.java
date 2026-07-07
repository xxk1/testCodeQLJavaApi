package com.lztech.site.viewmodel.coursemanagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * CourseCompletionResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-02-28T09:21:24.689Z")


public class CourseCompletionResource {
    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("completion")
    private BigDecimal completion = null;

    public CourseCompletionResource courseId(String courseId) {
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

    public CourseCompletionResource completion(BigDecimal completion) {
        this.completion = completion;
        return this;
    }

    /**
     * 完成度
     *
     * @return completion
     **/
    @ApiModelProperty(value = "完成度")

    @Valid

    public BigDecimal getCompletion() {
        return completion;
    }

    public void setCompletion(BigDecimal completion) {
        this.completion = completion;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseCompletionResource courseCompletionResource = (CourseCompletionResource) o;
        return Objects.equals(this.courseId, courseCompletionResource.courseId) &&
                Objects.equals(this.completion, courseCompletionResource.completion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, completion);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseCompletionResource {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    completion: ").append(toIndentedString(completion)).append("\n");
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

