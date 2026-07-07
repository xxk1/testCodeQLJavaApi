package com.lztech.site.viewmodel.teachingreport;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseResourceCollegeStatisticsModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-01-03T03:12:14.631Z")


public class CourseResourceCollegeStatisticsModel {
    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    @JsonProperty("courseResourceTeacherStatisticsList")
    @Valid
    private List<CourseResourceTeacherStatisticsModel> courseResourceTeacherStatisticsList = null;

    public CourseResourceCollegeStatisticsModel collegeId(String collegeId) {
        this.collegeId = collegeId;
        return this;
    }

    /**
     * 学院id
     *
     * @return collegeId
     **/
    @ApiModelProperty(value = "学院id")


    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public CourseResourceCollegeStatisticsModel collegeName(String collegeName) {
        this.collegeName = collegeName;
        return this;
    }

    /**
     * 学院名称
     *
     * @return collegeName
     **/
    @ApiModelProperty(value = "学院名称")


    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public CourseResourceCollegeStatisticsModel courseResourceTeacherStatisticsList(
            List<CourseResourceTeacherStatisticsModel> courseResourceTeacherStatisticsList) {
        this.courseResourceTeacherStatisticsList = courseResourceTeacherStatisticsList;
        return this;
    }

    public CourseResourceCollegeStatisticsModel addCourseResourceTeacherStatisticsListItem(
            CourseResourceTeacherStatisticsModel courseResourceTeacherStatisticsListItem) {
        if (this.courseResourceTeacherStatisticsList == null) {
            this.courseResourceTeacherStatisticsList = new ArrayList<CourseResourceTeacherStatisticsModel>();
        }
        this.courseResourceTeacherStatisticsList.add(courseResourceTeacherStatisticsListItem);
        return this;
    }

    /**
     * 课程资源教师统计列表
     *
     * @return courseResourceTeacherStatisticsList
     **/
    @ApiModelProperty(value = "课程资源教师统计列表")

    @Valid

    public List<CourseResourceTeacherStatisticsModel> getCourseResourceTeacherStatisticsList() {
        return courseResourceTeacherStatisticsList;
    }

    public void setCourseResourceTeacherStatisticsList(List<CourseResourceTeacherStatisticsModel> courseResourceTeacherStatisticsList) {
        this.courseResourceTeacherStatisticsList = courseResourceTeacherStatisticsList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseResourceCollegeStatisticsModel courseResourceCollegeStatisticsModel = (CourseResourceCollegeStatisticsModel) o;
        return Objects.equals(this.collegeId, courseResourceCollegeStatisticsModel.collegeId) &&
                Objects.equals(this.collegeName, courseResourceCollegeStatisticsModel.collegeName) &&
                Objects.equals(this.courseResourceTeacherStatisticsList, courseResourceCollegeStatisticsModel.courseResourceTeacherStatisticsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(collegeId, collegeName, courseResourceTeacherStatisticsList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseResourceCollegeStatisticsModel {\n");

        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
        sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
        sb.append("    courseResourceTeacherStatisticsList: ").append(toIndentedString(courseResourceTeacherStatisticsList)).append("\n");
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

