package com.lztech.site.viewmodel.courseresourceintelligentrecommendation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseResourceIntelligentRecommendationTermTeacherResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-06-19T13:50:47.154+08:00")

public class CourseResourceIntelligentRecommendationTermTeacherResource {
    @JsonProperty("teacherId")
    private String teacherId = null;

    @JsonProperty("teacherName")
    private String teacherName = null;

    @JsonProperty("teacherNo")
    private String teacherNo = null;

    public CourseResourceIntelligentRecommendationTermTeacherResource teacherId(String teacherId) {
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

    public CourseResourceIntelligentRecommendationTermTeacherResource teacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    /**
     * 教师名称
     *
     * @return teacherName
     **/
    @ApiModelProperty(value = "教师名称")


    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public CourseResourceIntelligentRecommendationTermTeacherResource teacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
        return this;
    }

    /**
     * 教师工号
     *
     * @return teacherNo
     **/
    @ApiModelProperty(value = "教师工号")


    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseResourceIntelligentRecommendationTermTeacherResource courseResourceIntelligentRecommendationTermTeacherResource =
                (CourseResourceIntelligentRecommendationTermTeacherResource) o;
        return Objects.equals(this.teacherId, courseResourceIntelligentRecommendationTermTeacherResource.teacherId) &&
                Objects.equals(this.teacherName, courseResourceIntelligentRecommendationTermTeacherResource.teacherName) &&
                Objects.equals(this.teacherNo, courseResourceIntelligentRecommendationTermTeacherResource.teacherNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, teacherName, teacherNo);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseResourceIntelligentRecommendationTermTeacherResource {\n");

        sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
        sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
        sb.append("    teacherNo: ").append(toIndentedString(teacherNo)).append("\n");
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

