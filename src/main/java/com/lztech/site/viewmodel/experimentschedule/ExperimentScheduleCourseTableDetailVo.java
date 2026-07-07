package com.lztech.site.viewmodel.experimentschedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ExperimentScheduleCourseTableDetailVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-11-22T05:58:58.019Z")


public class ExperimentScheduleCourseTableDetailVo {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("week")
    private String week = null;

    @JsonProperty("dayOfWeek")
    private String dayOfWeek = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseCode")
    private String courseCode = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("projectNames")
    private String projectNames = null;

    @JsonProperty("projectIds")
    private String projectIds = null;

    @JsonProperty("classId")
    private String classId = null;

    @JsonProperty("className")
    private String className = null;

    @JsonProperty("classStudentNumber")
    private Integer classStudentNumber = null;

    @JsonProperty("teacherIds")
    private String teacherIds = null;

    @JsonProperty("teacherNames")
    private String teacherNames = null;

    @JsonProperty("courseDate")
    private String courseDate = null;

    @JsonProperty("segment")
    private String segment = null;

    @JsonProperty("roomIds")
    private String roomIds = null;

    @JsonProperty("roomNames")
    private String roomNames = null;

    @JsonProperty("scheduleSource")
    private Integer scheduleSource = null;

    @JsonProperty("scheduleSourceName")
    private String scheduleSourceName = null;

    @JsonProperty("scheduleStatus")
    private Integer scheduleStatus = null;

    @JsonProperty("scheduleStatusName")
    private String scheduleStatusName = null;

    @JsonProperty("operatorName")
    private String operatorName = null;

    @JsonProperty("expire")
    private Integer expire = null;

    @JsonProperty("modifyTime")
    private String modifyTime = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    public ExperimentScheduleCourseTableDetailVo id(String id) {
        this.id = id;
        return this;
    }

    /**
     * id
     *
     * @return id
     **/
    @ApiModelProperty(value = "id")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ExperimentScheduleCourseTableDetailVo week(String week) {
        this.week = week;
        return this;
    }

    /**
     * 周次
     *
     * @return week
     **/
    @ApiModelProperty(value = "周次")


    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public ExperimentScheduleCourseTableDetailVo dayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        return this;
    }

    /**
     * 星期几
     *
     * @return dayOfWeek
     **/
    @ApiModelProperty(value = "星期几")


    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public ExperimentScheduleCourseTableDetailVo courseId(String courseId) {
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

    public ExperimentScheduleCourseTableDetailVo courseCode(String courseCode) {
        this.courseCode = courseCode;
        return this;
    }

    /**
     * 课程编码
     *
     * @return courseCode
     **/
    @ApiModelProperty(value = "课程编码")


    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public ExperimentScheduleCourseTableDetailVo courseName(String courseName) {
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

    public ExperimentScheduleCourseTableDetailVo projectNames(String projectNames) {
        this.projectNames = projectNames;
        return this;
    }

    /**
     * 实验项目（逗号分隔）
     *
     * @return projectNames
     **/
    @ApiModelProperty(value = "实验项目（逗号分隔）")


    public String getProjectNames() {
        return projectNames;
    }

    public void setProjectNames(String projectNames) {
        this.projectNames = projectNames;
    }

    public ExperimentScheduleCourseTableDetailVo projectIds(String projectIds) {
        this.projectIds = projectIds;
        return this;
    }

    /**
     * 实验项目ids（逗号分隔）
     *
     * @return projectIds
     **/
    @ApiModelProperty(value = "实验项目ids（逗号分隔）")


    public String getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(String projectIds) {
        this.projectIds = projectIds;
    }

    public ExperimentScheduleCourseTableDetailVo classId(String classId) {
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

    public ExperimentScheduleCourseTableDetailVo className(String className) {
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

    public ExperimentScheduleCourseTableDetailVo classStudentNumber(Integer classStudentNumber) {
        this.classStudentNumber = classStudentNumber;
        return this;
    }

    /**
     * 班级人数
     *
     * @return classStudentNumber
     **/
    @ApiModelProperty(value = "班级人数")


    public Integer getClassStudentNumber() {
        return classStudentNumber;
    }

    public void setClassStudentNumber(Integer classStudentNumber) {
        this.classStudentNumber = classStudentNumber;
    }

    public ExperimentScheduleCourseTableDetailVo teacherIds(String teacherIds) {
        this.teacherIds = teacherIds;
        return this;
    }

    /**
     * 教师ids（逗号分隔）
     *
     * @return teacherIds
     **/
    @ApiModelProperty(value = "教师ids（逗号分隔）")


    public String getTeacherIds() {
        return teacherIds;
    }

    public void setTeacherIds(String teacherIds) {
        this.teacherIds = teacherIds;
    }

    public ExperimentScheduleCourseTableDetailVo teacherNames(String teacherNames) {
        this.teacherNames = teacherNames;
        return this;
    }

    /**
     * 教师姓名（逗号分隔）
     *
     * @return teacherNames
     **/
    @ApiModelProperty(value = "教师姓名（逗号分隔）")


    public String getTeacherNames() {
        return teacherNames;
    }

    public void setTeacherNames(String teacherNames) {
        this.teacherNames = teacherNames;
    }

    public ExperimentScheduleCourseTableDetailVo courseDate(String courseDate) {
        this.courseDate = courseDate;
        return this;
    }

    /**
     * 上课日期
     *
     * @return courseDate
     **/
    @ApiModelProperty(value = "上课日期")


    public String getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(String courseDate) {
        this.courseDate = courseDate;
    }

    public ExperimentScheduleCourseTableDetailVo segment(String segment) {
        this.segment = segment;
        return this;
    }

    /**
     * 节次
     *
     * @return segment
     **/
    @ApiModelProperty(value = "节次")


    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public ExperimentScheduleCourseTableDetailVo roomIds(String roomIds) {
        this.roomIds = roomIds;
        return this;
    }

    /**
     * 实验室ids（逗号分隔）
     *
     * @return roomIds
     **/
    @ApiModelProperty(value = "实验室ids（逗号分隔）")


    public String getRoomIds() {
        return roomIds;
    }

    public void setRoomIds(String roomIds) {
        this.roomIds = roomIds;
    }

    public ExperimentScheduleCourseTableDetailVo roomNames(String roomNames) {
        this.roomNames = roomNames;
        return this;
    }

    /**
     * 实验室（逗号分隔）
     *
     * @return roomNames
     **/
    @ApiModelProperty(value = "实验室（逗号分隔）")


    public String getRoomNames() {
        return roomNames;
    }

    public void setRoomNames(String roomNames) {
        this.roomNames = roomNames;
    }

    public ExperimentScheduleCourseTableDetailVo scheduleSource(Integer scheduleSource) {
        this.scheduleSource = scheduleSource;
        return this;
    }

    /**
     * 来源值
     *
     * @return scheduleSource
     **/
    @ApiModelProperty(value = "来源值")


    public Integer getScheduleSource() {
        return scheduleSource;
    }

    public void setScheduleSource(Integer scheduleSource) {
        this.scheduleSource = scheduleSource;
    }

    public ExperimentScheduleCourseTableDetailVo scheduleSourceName(String scheduleSourceName) {
        this.scheduleSourceName = scheduleSourceName;
        return this;
    }

    /**
     * 来源名称
     *
     * @return scheduleSourceName
     **/
    @ApiModelProperty(value = "来源名称")


    public String getScheduleSourceName() {
        return scheduleSourceName;
    }

    public void setScheduleSourceName(String scheduleSourceName) {
        this.scheduleSourceName = scheduleSourceName;
    }

    public ExperimentScheduleCourseTableDetailVo scheduleStatus(Integer scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
        return this;
    }

    /**
     * 排课状态值
     *
     * @return scheduleStatus
     **/
    @ApiModelProperty(value = "排课状态值")


    public Integer getScheduleStatus() {
        return scheduleStatus;
    }

    public void setScheduleStatus(Integer scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }

    public ExperimentScheduleCourseTableDetailVo scheduleStatusName(String scheduleStatusName) {
        this.scheduleStatusName = scheduleStatusName;
        return this;
    }

    /**
     * 排课状态名称
     *
     * @return scheduleStatusName
     **/
    @ApiModelProperty(value = "排课状态名称")


    public String getScheduleStatusName() {
        return scheduleStatusName;
    }

    public void setScheduleStatusName(String scheduleStatusName) {
        this.scheduleStatusName = scheduleStatusName;
    }

    public ExperimentScheduleCourseTableDetailVo operatorName(String operatorName) {
        this.operatorName = operatorName;
        return this;
    }

    /**
     * 操作人姓名
     *
     * @return operatorName
     **/
    @ApiModelProperty(value = "操作人姓名")


    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public ExperimentScheduleCourseTableDetailVo expire(Integer expire) {
        this.expire = expire;
        return this;
    }

    /**
     * 是否过期，0-未过期，1-过期
     *
     * @return expire
     **/
    @ApiModelProperty(value = "是否过期，0-未过期，1-过期")


    public Integer getExpire() {
        return expire;
    }

    public void setExpire(Integer expire) {
        this.expire = expire;
    }

    public ExperimentScheduleCourseTableDetailVo modifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

    /**
     * 更新时间（yyyy-MM-dd HH:mm:ss）
     *
     * @return modifyTime
     **/
    @ApiModelProperty(value = "更新时间（yyyy-MM-dd HH:mm:ss）")


    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public ExperimentScheduleCourseTableDetailVo collegeName(String collegeName) {
        this.collegeName = collegeName;
        return this;
    }

    /**
     * 开课学院
     *
     * @return collegeName
     **/
    @ApiModelProperty(value = "开课学院")


    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExperimentScheduleCourseTableDetailVo experimentScheduleCourseTableDetailVo = (ExperimentScheduleCourseTableDetailVo) o;
        return Objects.equals(this.id, experimentScheduleCourseTableDetailVo.id) &&
                Objects.equals(this.week, experimentScheduleCourseTableDetailVo.week) &&
                Objects.equals(this.dayOfWeek, experimentScheduleCourseTableDetailVo.dayOfWeek) &&
                Objects.equals(this.courseId, experimentScheduleCourseTableDetailVo.courseId) &&
                Objects.equals(this.courseCode, experimentScheduleCourseTableDetailVo.courseCode) &&
                Objects.equals(this.courseName, experimentScheduleCourseTableDetailVo.courseName) &&
                Objects.equals(this.projectNames, experimentScheduleCourseTableDetailVo.projectNames) &&
                Objects.equals(this.projectIds, experimentScheduleCourseTableDetailVo.projectIds) &&
                Objects.equals(this.classId, experimentScheduleCourseTableDetailVo.classId) &&
                Objects.equals(this.className, experimentScheduleCourseTableDetailVo.className) &&
                Objects.equals(this.classStudentNumber, experimentScheduleCourseTableDetailVo.classStudentNumber) &&
                Objects.equals(this.teacherIds, experimentScheduleCourseTableDetailVo.teacherIds) &&
                Objects.equals(this.teacherNames, experimentScheduleCourseTableDetailVo.teacherNames) &&
                Objects.equals(this.courseDate, experimentScheduleCourseTableDetailVo.courseDate) &&
                Objects.equals(this.segment, experimentScheduleCourseTableDetailVo.segment) &&
                Objects.equals(this.roomIds, experimentScheduleCourseTableDetailVo.roomIds) &&
                Objects.equals(this.roomNames, experimentScheduleCourseTableDetailVo.roomNames) &&
                Objects.equals(this.scheduleSource, experimentScheduleCourseTableDetailVo.scheduleSource) &&
                Objects.equals(this.scheduleSourceName, experimentScheduleCourseTableDetailVo.scheduleSourceName) &&
                Objects.equals(this.scheduleStatus, experimentScheduleCourseTableDetailVo.scheduleStatus) &&
                Objects.equals(this.scheduleStatusName, experimentScheduleCourseTableDetailVo.scheduleStatusName) &&
                Objects.equals(this.operatorName, experimentScheduleCourseTableDetailVo.operatorName) &&
                Objects.equals(this.expire, experimentScheduleCourseTableDetailVo.expire) &&
                Objects.equals(this.modifyTime, experimentScheduleCourseTableDetailVo.modifyTime) &&
                Objects.equals(this.collegeName, experimentScheduleCourseTableDetailVo.collegeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, week, dayOfWeek, courseId, courseCode, courseName, projectNames, projectIds,
                classId, className, classStudentNumber, teacherIds, teacherNames, courseDate, segment, roomIds,
                roomNames, scheduleSource, scheduleSourceName, scheduleStatus, scheduleStatusName, operatorName,
                expire, modifyTime, collegeName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExperimentScheduleCourseTableDetailVo {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    week: ").append(toIndentedString(week)).append("\n");
        sb.append("    dayOfWeek: ").append(toIndentedString(dayOfWeek)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    projectNames: ").append(toIndentedString(projectNames)).append("\n");
        sb.append("    projectIds: ").append(toIndentedString(projectIds)).append("\n");
        sb.append("    classId: ").append(toIndentedString(classId)).append("\n");
        sb.append("    className: ").append(toIndentedString(className)).append("\n");
        sb.append("    classStudentNumber: ").append(toIndentedString(classStudentNumber)).append("\n");
        sb.append("    teacherIds: ").append(toIndentedString(teacherIds)).append("\n");
        sb.append("    teacherNames: ").append(toIndentedString(teacherNames)).append("\n");
        sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
        sb.append("    segment: ").append(toIndentedString(segment)).append("\n");
        sb.append("    roomIds: ").append(toIndentedString(roomIds)).append("\n");
        sb.append("    roomNames: ").append(toIndentedString(roomNames)).append("\n");
        sb.append("    scheduleSource: ").append(toIndentedString(scheduleSource)).append("\n");
        sb.append("    scheduleSourceName: ").append(toIndentedString(scheduleSourceName)).append("\n");
        sb.append("    scheduleStatus: ").append(toIndentedString(scheduleStatus)).append("\n");
        sb.append("    scheduleStatusName: ").append(toIndentedString(scheduleStatusName)).append("\n");
        sb.append("    operatorName: ").append(toIndentedString(operatorName)).append("\n");
        sb.append("    expire: ").append(toIndentedString(expire)).append("\n");
        sb.append("    modifyTime: ").append(toIndentedString(modifyTime)).append("\n");
        sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
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

