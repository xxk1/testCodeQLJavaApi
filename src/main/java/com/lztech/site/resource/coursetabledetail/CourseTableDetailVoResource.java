package com.lztech.site.resource.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseTableDetailVoResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-08-21T12:16:42.291Z")


public class CourseTableDetailVoResource {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("dateTime")
    private String dateTime = null;

    @JsonProperty("segment")
    private String segment = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("project")
    private String project = null;

    @JsonProperty("courseKind")
    private Integer courseKind = null;

    @JsonProperty("teacherName")
    private String teacherName = null;

    @JsonProperty("roomName")
    private String roomName = null;

    @JsonProperty("className")
    private String className = null;

    @JsonProperty("classMember")
    private Integer classMember = null;

    @JsonProperty("itemTeacherName")
    private String itemTeacherName = null;


    @JsonProperty("courseTableCollegeId")
    private String courseTableCollegeId = null;

    @JsonProperty("courseTableCollegeName")
    private String courseTableCollegeName = null;

    public String getCourseTableCollegeId() {
        return courseTableCollegeId;
    }

    public void setCourseTableCollegeId(String courseTableCollegeId) {
        this.courseTableCollegeId = courseTableCollegeId;
    }

    public String getCourseTableCollegeName() {
        return courseTableCollegeName;
    }

    public void setCourseTableCollegeName(String courseTableCollegeName) {
        this.courseTableCollegeName = courseTableCollegeName;
    }

    public CourseTableDetailVoResource id(String id) {
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

    public CourseTableDetailVoResource dateTime(String dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    /**
     * 日期
     *
     * @return dateTime
     **/
    @ApiModelProperty(value = "日期")


    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public CourseTableDetailVoResource segment(String segment) {
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

    public CourseTableDetailVoResource courseName(String courseName) {
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

    public CourseTableDetailVoResource project(String project) {
        this.project = project;
        return this;
    }

    /**
     * 主要内容（项目）
     *
     * @return project
     **/
    @ApiModelProperty(value = "主要内容（项目）")


    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public CourseTableDetailVoResource courseKind(Integer courseKind) {
        this.courseKind = courseKind;
        return this;
    }

    /**
     * 课表类别 0:理论课 1:实验课
     *
     * @return courseKind
     **/
    @ApiModelProperty(value = "课表类别 0:理论课 1:实验课")


    public Integer getCourseKind() {
        return courseKind;
    }

    public void setCourseKind(Integer courseKind) {
        this.courseKind = courseKind;
    }

    public CourseTableDetailVoResource teacherName(String teacherName) {
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

    public CourseTableDetailVoResource roomName(String roomName) {
        this.roomName = roomName;
        return this;
    }

    /**
     * 上课教室
     *
     * @return roomName
     **/
    @ApiModelProperty(value = "上课教室")


    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public CourseTableDetailVoResource className(String className) {
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

    public CourseTableDetailVoResource classMember(Integer classMember) {
        this.classMember = classMember;
        return this;
    }

    /**
     * 班级人数
     *
     * @return classMember
     **/
    @ApiModelProperty(value = "班级人数")


    public Integer getClassMember() {
        return classMember;
    }

    public void setClassMember(Integer classMember) {
        this.classMember = classMember;
    }

    public CourseTableDetailVoResource itemTeacherName(String itemTeacherName) {
        this.itemTeacherName = itemTeacherName;
        return this;
    }

    /**
     * 物品准备老师
     *
     * @return itemTeacherName
     **/
    @ApiModelProperty(value = "物品准备老师")


    public String getItemTeacherName() {
        return itemTeacherName;
    }

    public void setItemTeacherName(String itemTeacherName) {
        this.itemTeacherName = itemTeacherName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseTableDetailVoResource courseTableDetailVoResource = (CourseTableDetailVoResource) o;
        return Objects.equals(this.id, courseTableDetailVoResource.id) &&
                Objects.equals(this.dateTime, courseTableDetailVoResource.dateTime) &&
                Objects.equals(this.segment, courseTableDetailVoResource.segment) &&
                Objects.equals(this.courseName, courseTableDetailVoResource.courseName) &&
                Objects.equals(this.project, courseTableDetailVoResource.project) &&
                Objects.equals(this.courseKind, courseTableDetailVoResource.courseKind) &&
                Objects.equals(this.teacherName, courseTableDetailVoResource.teacherName) &&
                Objects.equals(this.roomName, courseTableDetailVoResource.roomName) &&
                Objects.equals(this.className, courseTableDetailVoResource.className) &&
                Objects.equals(this.classMember, courseTableDetailVoResource.classMember) &&
                Objects.equals(this.itemTeacherName, courseTableDetailVoResource.itemTeacherName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateTime, segment, courseName, project, courseKind, teacherName, roomName, className, classMember, itemTeacherName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTableDetailVoResource {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    dateTime: ").append(toIndentedString(dateTime)).append("\n");
        sb.append("    segment: ").append(toIndentedString(segment)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    project: ").append(toIndentedString(project)).append("\n");
        sb.append("    courseKind: ").append(toIndentedString(courseKind)).append("\n");
        sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
        sb.append("    roomName: ").append(toIndentedString(roomName)).append("\n");
        sb.append("    className: ").append(toIndentedString(className)).append("\n");
        sb.append("    classMember: ").append(toIndentedString(classMember)).append("\n");
        sb.append("    itemTeacherName: ").append(toIndentedString(itemTeacherName)).append("\n");
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

