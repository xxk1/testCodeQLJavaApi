package com.lztech.site.viewmodel.superviseevaluation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * SuperviseCourseTable
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-12-12T02:57:37.182Z")


public class SuperviseCourseTable {
    @JsonProperty("studentType")
    private Integer studentType = null;

    @JsonProperty("segment")
    private String segment = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseTableDetailId")
    private String courseTableDetailId = null;

    @JsonProperty("teachingClassName")
    private String teachingClassName = null;

    @JsonProperty("teachingClassId")
    private String teachingClassId = null;

    @JsonProperty("teachingClassStudentCount")
    private Integer teachingClassStudentCount = null;

    @JsonProperty("roomName")
    private String roomName = null;

    @JsonProperty("roomId")
    private String roomId = null;

    @JsonProperty("teacherInfo")
    @Valid
    private List<TeacherInfo> teacherInfo = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    @JsonProperty("courseAttrName")
    private String courseAttrName = null;

    @JsonProperty("schoolYear")
    private String schoolYear = null;

    @JsonProperty("term")
    private Integer term = null;

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public String getCourseAttrName() {
        return courseAttrName;
    }

    public void setCourseAttrName(String courseAttrName) {
        this.courseAttrName = courseAttrName;
    }

    public SuperviseCourseTable studentType(Integer studentType) {
        this.studentType = studentType;
        return this;
    }

    /**
     * 开课类型
     *
     * @return studentType
     **/
    @ApiModelProperty(value = "开课类型")


    public Integer getStudentType() {
        return studentType;
    }

    public void setStudentType(Integer studentType) {
        this.studentType = studentType;
    }

    public SuperviseCourseTable segment(String segment) {
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

    public SuperviseCourseTable courseName(String courseName) {
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

    public SuperviseCourseTable courseId(String courseId) {
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

    public SuperviseCourseTable courseTableDetailId(String courseTableDetailId) {
        this.courseTableDetailId = courseTableDetailId;
        return this;
    }

    /**
     * 课表id
     *
     * @return courseTableDetailId
     **/
    @ApiModelProperty(value = "课表id")


    public String getCourseTableDetailId() {
        return courseTableDetailId;
    }

    public void setCourseTableDetailId(String courseTableDetailId) {
        this.courseTableDetailId = courseTableDetailId;
    }

    public SuperviseCourseTable teachingClassName(String teachingClassName) {
        this.teachingClassName = teachingClassName;
        return this;
    }

    /**
     * 上课班级
     *
     * @return teachingClassName
     **/
    @ApiModelProperty(value = "上课班级")


    public String getTeachingClassName() {
        return teachingClassName;
    }

    public void setTeachingClassName(String teachingClassName) {
        this.teachingClassName = teachingClassName;
    }

    public SuperviseCourseTable teachingClassId(String teachingClassId) {
        this.teachingClassId = teachingClassId;
        return this;
    }

    /**
     * 班级id
     *
     * @return teachingClassId
     **/
    @ApiModelProperty(value = "班级id")


    public String getTeachingClassId() {
        return teachingClassId;
    }

    public void setTeachingClassId(String teachingClassId) {
        this.teachingClassId = teachingClassId;
    }

    public SuperviseCourseTable teachingClassStudentCount(Integer teachingClassStudentCount) {
        this.teachingClassStudentCount = teachingClassStudentCount;
        return this;
    }

    /**
     * 班级学生人数
     *
     * @return teachingClassStudentCount
     **/
    @ApiModelProperty(value = "班级学生人数")


    public Integer getTeachingClassStudentCount() {
        return teachingClassStudentCount;
    }

    public void setTeachingClassStudentCount(Integer teachingClassStudentCount) {
        this.teachingClassStudentCount = teachingClassStudentCount;
    }

    public SuperviseCourseTable roomName(String roomName) {
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

    public SuperviseCourseTable roomId(String roomId) {
        this.roomId = roomId;
        return this;
    }

    /**
     * 上课教室Id
     *
     * @return roomId
     **/
    @ApiModelProperty(value = "上课教室Id")


    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public SuperviseCourseTable teacherInfo(List<TeacherInfo> teacherInfo) {
        this.teacherInfo = teacherInfo;
        return this;
    }

    public SuperviseCourseTable addTeacherInfoItem(TeacherInfo teacherInfoItem) {
        if (this.teacherInfo == null) {
            this.teacherInfo = new ArrayList<TeacherInfo>();
        }
        this.teacherInfo.add(teacherInfoItem);
        return this;
    }

    /**
     * Get teacherInfo
     *
     * @return teacherInfo
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<TeacherInfo> getTeacherInfo() {
        return teacherInfo;
    }

    public void setTeacherInfo(List<TeacherInfo> teacherInfo) {
        this.teacherInfo = teacherInfo;
    }

    public SuperviseCourseTable collegeId(String collegeId) {
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

    public SuperviseCourseTable collegeName(String collegeName) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SuperviseCourseTable superviseCourseTable = (SuperviseCourseTable) o;
        return Objects.equals(this.studentType, superviseCourseTable.studentType) &&
                Objects.equals(this.segment, superviseCourseTable.segment) &&
                Objects.equals(this.courseName, superviseCourseTable.courseName) &&
                Objects.equals(this.courseId, superviseCourseTable.courseId) &&
                Objects.equals(this.courseTableDetailId, superviseCourseTable.courseTableDetailId) &&
                Objects.equals(this.teachingClassName, superviseCourseTable.teachingClassName) &&
                Objects.equals(this.teachingClassId, superviseCourseTable.teachingClassId) &&
                Objects.equals(this.teachingClassStudentCount, superviseCourseTable.teachingClassStudentCount) &&
                Objects.equals(this.roomName, superviseCourseTable.roomName) &&
                Objects.equals(this.roomId, superviseCourseTable.roomId) &&
                Objects.equals(this.teacherInfo, superviseCourseTable.teacherInfo) &&
                Objects.equals(this.collegeId, superviseCourseTable.collegeId) &&
                Objects.equals(this.collegeName, superviseCourseTable.collegeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentType, segment, courseName, courseId, courseTableDetailId, teachingClassName, teachingClassId,
                teachingClassStudentCount, roomName, roomId, teacherInfo, collegeId, collegeName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SuperviseCourseTable {\n");

        sb.append("    studentType: ").append(toIndentedString(studentType)).append("\n");
        sb.append("    segment: ").append(toIndentedString(segment)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseTableDetailId: ").append(toIndentedString(courseTableDetailId)).append("\n");
        sb.append("    teachingClassName: ").append(toIndentedString(teachingClassName)).append("\n");
        sb.append("    teachingClassId: ").append(toIndentedString(teachingClassId)).append("\n");
        sb.append("    teachingClassStudentCount: ").append(toIndentedString(teachingClassStudentCount)).append("\n");
        sb.append("    roomName: ").append(toIndentedString(roomName)).append("\n");
        sb.append("    roomId: ").append(toIndentedString(roomId)).append("\n");
        sb.append("    teacherInfo: ").append(toIndentedString(teacherInfo)).append("\n");
        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
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

