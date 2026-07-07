package com.lztech.site.viewmodel.experimentschedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ExperimentScheduleCourseTableDetailQueryVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-11-20T06:24:49.642Z")


public class ExperimentScheduleCourseTableDetailQueryVo {
    @JsonProperty("page")
    private Integer page = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

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

    @JsonProperty("classId")
    private String classId = null;

    @JsonProperty("teacherId")
    private String teacherId = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    public ExperimentScheduleCourseTableDetailQueryVo page(Integer page) {
        this.page = page;
        return this;
    }

    /**
     * 页码
     *
     * @return page
     **/
    @ApiModelProperty(value = "页码")


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public ExperimentScheduleCourseTableDetailQueryVo pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * 每页记录数
     *
     * @return pageSize
     **/
    @ApiModelProperty(value = "每页记录数")


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public ExperimentScheduleCourseTableDetailQueryVo startDate(String startDate) {
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

    public ExperimentScheduleCourseTableDetailQueryVo endDate(String endDate) {
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

    public ExperimentScheduleCourseTableDetailQueryVo scheduleStatus(String scheduleStatus) {
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

    public ExperimentScheduleCourseTableDetailQueryVo courseName(String courseName) {
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

    public ExperimentScheduleCourseTableDetailQueryVo className(String className) {
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

    public ExperimentScheduleCourseTableDetailQueryVo roomName(String roomName) {
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

    public ExperimentScheduleCourseTableDetailQueryVo classId(String classId) {
        this.classId = classId;
        return this;
    }

    /**
     * 上课班级Id
     *
     * @return classId
     **/
    @ApiModelProperty(value = "上课班级Id")


    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public ExperimentScheduleCourseTableDetailQueryVo teacherId(String teacherId) {
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

    public ExperimentScheduleCourseTableDetailQueryVo courseId(String courseId) {
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

    public ExperimentScheduleCourseTableDetailQueryVo collegeId(String collegeId) {
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
        ExperimentScheduleCourseTableDetailQueryVo experimentScheduleCourseTableDetailQueryVo = (ExperimentScheduleCourseTableDetailQueryVo) o;
        return Objects.equals(this.page, experimentScheduleCourseTableDetailQueryVo.page) &&
                Objects.equals(this.pageSize, experimentScheduleCourseTableDetailQueryVo.pageSize) &&
                Objects.equals(this.startDate, experimentScheduleCourseTableDetailQueryVo.startDate) &&
                Objects.equals(this.endDate, experimentScheduleCourseTableDetailQueryVo.endDate) &&
                Objects.equals(this.scheduleStatus, experimentScheduleCourseTableDetailQueryVo.scheduleStatus) &&
                Objects.equals(this.courseName, experimentScheduleCourseTableDetailQueryVo.courseName) &&
                Objects.equals(this.className, experimentScheduleCourseTableDetailQueryVo.className) &&
                Objects.equals(this.roomName, experimentScheduleCourseTableDetailQueryVo.roomName) &&
                Objects.equals(this.classId, experimentScheduleCourseTableDetailQueryVo.classId) &&
                Objects.equals(this.teacherId, experimentScheduleCourseTableDetailQueryVo.teacherId) &&
                Objects.equals(this.courseId, experimentScheduleCourseTableDetailQueryVo.courseId) &&
                Objects.equals(this.collegeId, experimentScheduleCourseTableDetailQueryVo.collegeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, pageSize, startDate, endDate, scheduleStatus, courseName,
                className, roomName, classId, teacherId, courseId, collegeId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExperimentScheduleCourseTableDetailQueryVo {\n");

        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
        sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
        sb.append("    scheduleStatus: ").append(toIndentedString(scheduleStatus)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    className: ").append(toIndentedString(className)).append("\n");
        sb.append("    roomName: ").append(toIndentedString(roomName)).append("\n");
        sb.append("    classId: ").append(toIndentedString(classId)).append("\n");
        sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
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

