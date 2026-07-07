package com.lztech.site.viewmodel.experimentschedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ExperimentScheduleCourseTableDetailExportQueryVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-11-20T07:09:39.241Z")


public class ExperimentScheduleCourseTableDetailExportQueryVo {
    @JsonProperty("term")
    private String term = null;

    @JsonProperty("startDate")
    private String startDate = null;

    @JsonProperty("endDate")
    private String endDate = null;

    @JsonProperty("scheduleStatus")
    private String scheduleStatus = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("className")
    private String className = null;

    @JsonProperty("roomName")
    private String roomName = null;

    @JsonProperty("teacherId")
    private String teacherId = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    public ExperimentScheduleCourseTableDetailExportQueryVo term(String term) {
        this.term = term;
        return this;
    }

    /**
     * 学期信息（例如：2024-2025 第一学期）
     *
     * @return term
     **/
    @ApiModelProperty(value = "学期信息（例如：2024-2025 第一学期）")


    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public ExperimentScheduleCourseTableDetailExportQueryVo startDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * 开始上课日期（yyyy-MM-dd）
     *
     * @return startDate
     **/
    @ApiModelProperty(value = "开始上课日期（yyyy-MM-dd）")


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public ExperimentScheduleCourseTableDetailExportQueryVo endDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * 结束上课日期（yyyy-MM-dd）
     *
     * @return endDate
     **/
    @ApiModelProperty(value = "结束上课日期（yyyy-MM-dd）")


    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public ExperimentScheduleCourseTableDetailExportQueryVo scheduleStatus(String scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
        return this;
    }

    /**
     * 排课状态：\"\"-全部，0-待排课,1-已取消,2-已过期,3-已排课,4-教务删除
     *
     * @return scheduleStatus
     **/
    @ApiModelProperty(value = "排课状态：\"\"-全部，0-待排课,1-已取消,2-已过期,3-已排课,4-教务删除")


    public String getScheduleStatus() {
        return scheduleStatus;
    }

    public void setScheduleStatus(String scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }

    public ExperimentScheduleCourseTableDetailExportQueryVo courseName(String courseName) {
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

    public ExperimentScheduleCourseTableDetailExportQueryVo className(String className) {
        this.className = className;
        return this;
    }

    /**
     * 上课班级
     *
     * @return className
     **/
    @ApiModelProperty(value = "上课班级")


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public ExperimentScheduleCourseTableDetailExportQueryVo roomName(String roomName) {
        this.roomName = roomName;
        return this;
    }

    /**
     * 实验室
     *
     * @return roomName
     **/
    @ApiModelProperty(value = "实验室")


    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public ExperimentScheduleCourseTableDetailExportQueryVo teacherId(String teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    /**
     * 老师Id
     *
     * @return teacherId
     **/
    @ApiModelProperty(value = "老师Id")


    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public ExperimentScheduleCourseTableDetailExportQueryVo collegeId(String collegeId) {
        this.collegeId = collegeId;
        return this;
    }

    /**
     * 学院Id
     *
     * @return collegeId
     **/
    @ApiModelProperty(value = "学院Id")


    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExperimentScheduleCourseTableDetailExportQueryVo experimentScheduleCourseTableDetailExportQueryVo =
                (ExperimentScheduleCourseTableDetailExportQueryVo) o;
        return Objects.equals(this.term, experimentScheduleCourseTableDetailExportQueryVo.term) &&
                Objects.equals(this.startDate, experimentScheduleCourseTableDetailExportQueryVo.startDate) &&
                Objects.equals(this.endDate, experimentScheduleCourseTableDetailExportQueryVo.endDate) &&
                Objects.equals(this.scheduleStatus, experimentScheduleCourseTableDetailExportQueryVo.scheduleStatus) &&
                Objects.equals(this.courseName, experimentScheduleCourseTableDetailExportQueryVo.courseName) &&
                Objects.equals(this.className, experimentScheduleCourseTableDetailExportQueryVo.className) &&
                Objects.equals(this.roomName, experimentScheduleCourseTableDetailExportQueryVo.roomName) &&
                Objects.equals(this.teacherId, experimentScheduleCourseTableDetailExportQueryVo.teacherId) &&
                Objects.equals(this.collegeId, experimentScheduleCourseTableDetailExportQueryVo.collegeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(term, startDate, endDate, scheduleStatus, courseName, className, roomName, teacherId, collegeId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExperimentScheduleCourseTableDetailExportQueryVo {\n");

        sb.append("    term: ").append(toIndentedString(term)).append("\n");
        sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
        sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
        sb.append("    scheduleStatus: ").append(toIndentedString(scheduleStatus)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    className: ").append(toIndentedString(className)).append("\n");
        sb.append("    roomName: ").append(toIndentedString(roomName)).append("\n");
        sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
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

