package com.lztech.site.viewmodel.wisdomcourse;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * WisdomCourseResult
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-08-13T15:53:10.736+08:00")

public class WisdomCourseResult {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("courseNo")
    private String courseNo = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseTeacherTeamNames")
    private String courseTeacherTeamNames = null;

    @JsonProperty("createTime")
    private String createTime = null;

    @JsonProperty("completedTaskNum")
    private Integer completedTaskNum = null;

    @JsonProperty("totalTaskNum")
    private Integer totalTaskNum = null;

    public WisdomCourseResult id(String id) {
        this.id = id;
        return this;
    }

    /**
     * 智慧课程id
     *
     * @return id
     **/
    @ApiModelProperty(value = "智慧课程id")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public WisdomCourseResult courseNo(String courseNo) {
        this.courseNo = courseNo;
        return this;
    }

    /**
     * 课程编号
     *
     * @return courseNo
     **/
    @ApiModelProperty(value = "课程编号")


    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    public WisdomCourseResult courseName(String courseName) {
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

    public WisdomCourseResult courseId(String courseId) {
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

    public WisdomCourseResult courseTeacherTeamNames(String courseTeacherTeamNames) {
        this.courseTeacherTeamNames = courseTeacherTeamNames;
        return this;
    }

    /**
     * 课程教师团队名称
     *
     * @return courseTeacherTeamNames
     **/
    @ApiModelProperty(value = "课程教师团队名称")


    public String getCourseTeacherTeamNames() {
        return courseTeacherTeamNames;
    }

    public void setCourseTeacherTeamNames(String courseTeacherTeamNames) {
        this.courseTeacherTeamNames = courseTeacherTeamNames;
    }

    public WisdomCourseResult createTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    /**
     * 创建时间
     *
     * @return createTime
     **/
    @ApiModelProperty(value = "创建时间")


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public WisdomCourseResult completedTaskNum(Integer completedTaskNum) {
        this.completedTaskNum = completedTaskNum;
        return this;
    }

    /**
     * 已完成任务数
     *
     * @return completedTaskNum
     **/
    @ApiModelProperty(value = "已完成任务数")


    public Integer getCompletedTaskNum() {
        return completedTaskNum;
    }

    public void setCompletedTaskNum(Integer completedTaskNum) {
        this.completedTaskNum = completedTaskNum;
    }

    public WisdomCourseResult totalTaskNum(Integer totalTaskNum) {
        this.totalTaskNum = totalTaskNum;
        return this;
    }

    /**
     * 任务总数
     *
     * @return totalTaskNum
     **/
    @ApiModelProperty(value = "任务总数")


    public Integer getTotalTaskNum() {
        return totalTaskNum;
    }

    public void setTotalTaskNum(Integer totalTaskNum) {
        this.totalTaskNum = totalTaskNum;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WisdomCourseResult wisdomCourseResult = (WisdomCourseResult) o;
        return Objects.equals(this.id, wisdomCourseResult.id) &&
                Objects.equals(this.courseNo, wisdomCourseResult.courseNo) &&
                Objects.equals(this.courseName, wisdomCourseResult.courseName) &&
                Objects.equals(this.courseId, wisdomCourseResult.courseId) &&
                Objects.equals(this.courseTeacherTeamNames, wisdomCourseResult.courseTeacherTeamNames) &&
                Objects.equals(this.createTime, wisdomCourseResult.createTime) &&
                Objects.equals(this.completedTaskNum, wisdomCourseResult.completedTaskNum) &&
                Objects.equals(this.totalTaskNum, wisdomCourseResult.totalTaskNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseNo, courseName, courseId, courseTeacherTeamNames, createTime, completedTaskNum, totalTaskNum);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class WisdomCourseResult {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    courseNo: ").append(toIndentedString(courseNo)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseTeacherTeamNames: ").append(toIndentedString(courseTeacherTeamNames)).append("\n");
        sb.append("    createTime: ").append(toIndentedString(createTime)).append("\n");
        sb.append("    completedTaskNum: ").append(toIndentedString(completedTaskNum)).append("\n");
        sb.append("    totalTaskNum: ").append(toIndentedString(totalTaskNum)).append("\n");
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

