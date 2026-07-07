package com.lztech.site.viewmodel.superviseevaluation.v2;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * SupervisionCourseAndCourseTableDetailVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-08-06T16:30:45.421+08:00")

public class SupervisionCourseAndCourseTableDetailVo {
    @JsonProperty("courseTableDetails")
    @Valid
    private List<SupervisionCourseTableDetailVo> courseTableDetails = null;

    @JsonProperty("studentType")
    private String studentType = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("teachers")
    private String teachers = null;

    @JsonProperty("live")
    private Boolean live = null;

    @JsonProperty("liveCourseTableDetailId")
    private String liveCourseTableDetailId = null;

    @JsonProperty("courseTableDetailSegmentStartTime")
    private String courseTableDetailSegmentStartTime = null;

    @JsonProperty("courseTableDetailSegmentEndTime")
    private String courseTableDetailSegmentEndTime = null;

    @JsonProperty("sourceDataSourceName")
    private String sourceDataSourceName = null;
    @JsonProperty("sourceDataSource")
    private String sourceDataSource = null;
    public SupervisionCourseAndCourseTableDetailVo courseTableDetails(List<SupervisionCourseTableDetailVo> courseTableDetails) {
        this.courseTableDetails = courseTableDetails;
        return this;
    }

    public SupervisionCourseAndCourseTableDetailVo addCourseTableDetailsItem(SupervisionCourseTableDetailVo courseTableDetailsItem) {
        if (this.courseTableDetails == null) {
            this.courseTableDetails = new ArrayList<SupervisionCourseTableDetailVo>();
        }
        this.courseTableDetails.add(courseTableDetailsItem);
        return this;
    }

    /**
     * 课表明细ids
     *
     * @return courseTableDetails
     **/
    @ApiModelProperty(value = "课表明细ids")

    @Valid

    public List<SupervisionCourseTableDetailVo> getCourseTableDetails() {
        return courseTableDetails;
    }

    public void setCourseTableDetails(List<SupervisionCourseTableDetailVo> courseTableDetails) {
        this.courseTableDetails = courseTableDetails;
    }

    public SupervisionCourseAndCourseTableDetailVo studentType(String studentType) {
        this.studentType = studentType;
        return this;
    }

    /**
     * 开课类型
     *
     * @return studentType
     **/
    @ApiModelProperty(value = "开课类型")


    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    public SupervisionCourseAndCourseTableDetailVo courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    /**
     * 课程Id
     *
     * @return courseId
     **/
    @ApiModelProperty(value = "课程Id")


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public SupervisionCourseAndCourseTableDetailVo courseName(String courseName) {
        this.courseName = courseName;
        return this;
    }

    /**
     * 课程名称
     *
     * @return courseName
     **/
    @ApiModelProperty(value = "课程名称")


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public SupervisionCourseAndCourseTableDetailVo teachers(String teachers) {
        this.teachers = teachers;
        return this;
    }

    /**
     * 老师信息（id,工号,姓名，多个;分割）
     *
     * @return teachers
     **/
    @ApiModelProperty(value = "老师信息（id,工号,姓名，多个;分割）")


    public String getTeachers() {
        return teachers;
    }

    public void setTeachers(String teachers) {
        this.teachers = teachers;
    }

    public SupervisionCourseAndCourseTableDetailVo live(Boolean live) {
        this.live = live;
        return this;
    }

    /**
     * 是否直播中（0-不在直播中，1-直播中）
     *
     * @return live
     **/
    @ApiModelProperty(value = "是否直播中（0-不在直播中，1-直播中）")


    public Boolean isLive() {
        return live;
    }

    public void setLive(Boolean live) {
        this.live = live;
    }

    public SupervisionCourseAndCourseTableDetailVo liveCourseTableDetailId(String liveCourseTableDetailId) {
        this.liveCourseTableDetailId = liveCourseTableDetailId;
        return this;
    }

    /**
     * 直播中的课表明细ids
     *
     * @return liveCourseTableDetailId
     **/
    @ApiModelProperty(value = "直播中的课表明细ids")


    public String getLiveCourseTableDetailId() {
        return liveCourseTableDetailId;
    }

    public void setLiveCourseTableDetailId(String liveCourseTableDetailId) {
        this.liveCourseTableDetailId = liveCourseTableDetailId;
    }

    public SupervisionCourseAndCourseTableDetailVo courseTableDetailSegmentStartTime(String courseTableDetailSegmentStartTime) {
        this.courseTableDetailSegmentStartTime = courseTableDetailSegmentStartTime;
        return this;
    }

    /**
     * 课表节次开始时间
     *
     * @return courseTableDetailSegmentStartTime
     **/
    @ApiModelProperty(value = "课表节次开始时间")


    public String getCourseTableDetailSegmentStartTime() {
        return courseTableDetailSegmentStartTime;
    }

    public void setCourseTableDetailSegmentStartTime(String courseTableDetailSegmentStartTime) {
        this.courseTableDetailSegmentStartTime = courseTableDetailSegmentStartTime;
    }

    public SupervisionCourseAndCourseTableDetailVo courseTableDetailSegmentEndTime(String courseTableDetailSegmentEndTime) {
        this.courseTableDetailSegmentEndTime = courseTableDetailSegmentEndTime;
        return this;
    }

    /**
     * 课表节次结束时间
     *
     * @return courseTableDetailSegmentEndTime
     **/
    @ApiModelProperty(value = "课表节次结束时间")


    public String getCourseTableDetailSegmentEndTime() {
        return courseTableDetailSegmentEndTime;
    }

    public void setCourseTableDetailSegmentEndTime(String courseTableDetailSegmentEndTime) {
        this.courseTableDetailSegmentEndTime = courseTableDetailSegmentEndTime;
    }

    public SupervisionCourseAndCourseTableDetailVo sourceDataSourceName(String sourceDataSourceName) {
        this.sourceDataSourceName = sourceDataSourceName;
        return this;
    }

    /**
     * 数据来源名称
     *
     * @return sourceDataSourceName
     **/
    @ApiModelProperty(value = "数据来源名称")


    public String getSourceDataSourceName() {
        return sourceDataSourceName;
    }

    public void setSourceDataSourceName(String sourceDataSourceName) {
        this.sourceDataSourceName = sourceDataSourceName;
    }

    public String getSourceDataSource() {
        return sourceDataSource;
    }

    public void setSourceDataSource(String sourceDataSource) {
        this.sourceDataSource = sourceDataSource;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SupervisionCourseAndCourseTableDetailVo supervisionCourseAndCourseTableDetailVo = (SupervisionCourseAndCourseTableDetailVo) o;
        return Objects.equals(this.courseTableDetails, supervisionCourseAndCourseTableDetailVo.courseTableDetails) &&
                Objects.equals(this.studentType, supervisionCourseAndCourseTableDetailVo.studentType) &&
                Objects.equals(this.courseId, supervisionCourseAndCourseTableDetailVo.courseId) &&
                Objects.equals(this.courseName, supervisionCourseAndCourseTableDetailVo.courseName) &&
                Objects.equals(this.teachers, supervisionCourseAndCourseTableDetailVo.teachers) &&
                Objects.equals(this.live, supervisionCourseAndCourseTableDetailVo.live) &&
                Objects.equals(this.liveCourseTableDetailId, supervisionCourseAndCourseTableDetailVo.liveCourseTableDetailId) &&
                Objects.equals(this.courseTableDetailSegmentStartTime, supervisionCourseAndCourseTableDetailVo.courseTableDetailSegmentStartTime) &&
                Objects.equals(this.courseTableDetailSegmentEndTime, supervisionCourseAndCourseTableDetailVo.courseTableDetailSegmentEndTime) &&
                Objects.equals(this.sourceDataSourceName, supervisionCourseAndCourseTableDetailVo.sourceDataSourceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseTableDetails, studentType, courseId, courseName, teachers, live, liveCourseTableDetailId,
                courseTableDetailSegmentStartTime, courseTableDetailSegmentEndTime, sourceDataSourceName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SupervisionCourseAndCourseTableDetailVo {\n");

        sb.append("    courseTableDetails: ").append(toIndentedString(courseTableDetails)).append("\n");
        sb.append("    studentType: ").append(toIndentedString(studentType)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    teachers: ").append(toIndentedString(teachers)).append("\n");
        sb.append("    live: ").append(toIndentedString(live)).append("\n");
        sb.append("    liveCourseTableDetailId: ").append(toIndentedString(liveCourseTableDetailId)).append("\n");
        sb.append("    courseTableDetailSegmentStartTime: ").append(toIndentedString(courseTableDetailSegmentStartTime)).append("\n");
        sb.append("    courseTableDetailSegmentEndTime: ").append(toIndentedString(courseTableDetailSegmentEndTime)).append("\n");
        sb.append("    sourceDataSource: ").append(toIndentedString(sourceDataSource)).append("\n");
        sb.append("    sourceDataSourceName: ").append(toIndentedString(sourceDataSourceName)).append("\n");
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

