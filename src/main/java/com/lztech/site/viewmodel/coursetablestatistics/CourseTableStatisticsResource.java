package com.lztech.site.viewmodel.coursetablestatistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseTableStatisticsResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-08-18T04:29:54.572Z")


public class CourseTableStatisticsResource {
    @JsonProperty("inClassRoomNum")
    private Integer inClassRoomNum = null;

    @JsonProperty("inClassStudentNum")
    private Integer inClassStudentNum = null;

    @JsonProperty("courseInfoCollegeNum")
    private Integer courseInfoCollegeNum = null;

    @JsonProperty("courseInfoCourseNum")
    private Integer courseInfoCourseNum = null;

    public CourseTableStatisticsResource inClassRoomNum(Integer inClassRoomNum) {
        this.inClassRoomNum = inClassRoomNum;
        return this;
    }

    /**
     * 正在上课的教室数量
     *
     * @return inClassRoomNum
     **/
    @ApiModelProperty(value = "正在上课的教室数量")


    public Integer getInClassRoomNum() {
        return inClassRoomNum;
    }

    public void setInClassRoomNum(Integer inClassRoomNum) {
        this.inClassRoomNum = inClassRoomNum;
    }

    public CourseTableStatisticsResource inClassStudentNum(Integer inClassStudentNum) {
        this.inClassStudentNum = inClassStudentNum;
        return this;
    }

    /**
     * 正在上课的学生数量
     *
     * @return inClassStudentNum
     **/
    @ApiModelProperty(value = "正在上课的学生数量")


    public Integer getInClassStudentNum() {
        return inClassStudentNum;
    }

    public void setInClassStudentNum(Integer inClassStudentNum) {
        this.inClassStudentNum = inClassStudentNum;
    }

    public CourseTableStatisticsResource courseInfoCollegeNum(Integer courseInfoCollegeNum) {
        this.courseInfoCollegeNum = courseInfoCollegeNum;
        return this;
    }

    /**
     * 开课学院数量
     *
     * @return courseInfoCollegeNum
     **/
    @ApiModelProperty(value = "开课学院数量")


    public Integer getCourseInfoCollegeNum() {
        return courseInfoCollegeNum;
    }

    public void setCourseInfoCollegeNum(Integer courseInfoCollegeNum) {
        this.courseInfoCollegeNum = courseInfoCollegeNum;
    }

    public CourseTableStatisticsResource courseInfoCourseNum(Integer courseInfoCourseNum) {
        this.courseInfoCourseNum = courseInfoCourseNum;
        return this;
    }

    /**
     * 开课课程数量
     *
     * @return courseInfoCourseNum
     **/
    @ApiModelProperty(value = "开课课程数量")


    public Integer getCourseInfoCourseNum() {
        return courseInfoCourseNum;
    }

    public void setCourseInfoCourseNum(Integer courseInfoCourseNum) {
        this.courseInfoCourseNum = courseInfoCourseNum;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseTableStatisticsResource courseTableStatisticsResource = (CourseTableStatisticsResource) o;
        return Objects.equals(this.inClassRoomNum, courseTableStatisticsResource.inClassRoomNum) &&
                Objects.equals(this.inClassStudentNum, courseTableStatisticsResource.inClassStudentNum) &&
                Objects.equals(this.courseInfoCollegeNum, courseTableStatisticsResource.courseInfoCollegeNum) &&
                Objects.equals(this.courseInfoCourseNum, courseTableStatisticsResource.courseInfoCourseNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inClassRoomNum, inClassStudentNum, courseInfoCollegeNum, courseInfoCourseNum);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTableStatisticsResource {\n");

        sb.append("    inClassRoomNum: ").append(toIndentedString(inClassRoomNum)).append("\n");
        sb.append("    inClassStudentNum: ").append(toIndentedString(inClassStudentNum)).append("\n");
        sb.append("    courseInfoCollegeNum: ").append(toIndentedString(courseInfoCollegeNum)).append("\n");
        sb.append("    courseInfoCourseNum: ").append(toIndentedString(courseInfoCourseNum)).append("\n");
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

