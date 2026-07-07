package com.lztech.site.viewmodel.superviseevaluation;

import java.math.BigInteger;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;


/**
 * PoliticalcourseCourseTable
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-02-01T06:19:36.742Z")


public class PoliticalcourseCourseTable {
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
    private BigInteger teachingClassStudentCount = null;

    @JsonProperty("roomName")
    private String roomName = null;

    @JsonProperty("roomId")
    private String roomId = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("collegeName")
    private String collegeName = null;



    public PoliticalcourseCourseTable studentType(Integer studentType) {
        this.studentType = studentType;
        return this;
    }

    /**
     * 开课类型
     * @return studentType
     **/
    @ApiModelProperty(value = "开课类型")


    public Integer getStudentType() {
        return studentType;
    }

    public void setStudentType(Integer studentType) {
        this.studentType = studentType;
    }

    public PoliticalcourseCourseTable segment(String segment) {
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

    public PoliticalcourseCourseTable courseName(String courseName) {
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

    public PoliticalcourseCourseTable courseId(String courseId) {
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

    public PoliticalcourseCourseTable courseTableDetailId(String courseTableDetailId) {
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

    public PoliticalcourseCourseTable teachingClassName(String teachingClassName) {
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

    public PoliticalcourseCourseTable teachingClassId(String teachingClassId) {
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


    public PoliticalcourseCourseTable teachingClassStudentCount(BigInteger teachingClassStudentCount) {
        this.teachingClassStudentCount = teachingClassStudentCount;
        return this;
    }

    /**
     * 班级学生人数
     *
     * @return teachingClassStudentCount
     **/
    @ApiModelProperty(value = "班级学生人数")


    public BigInteger getTeachingClassStudentCount() {
        return teachingClassStudentCount;
    }

    public void setTeachingClassStudentCount(BigInteger teachingClassStudentCount) {
        this.teachingClassStudentCount = teachingClassStudentCount;
    }


    public PoliticalcourseCourseTable roomName(String roomName) {
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

    public PoliticalcourseCourseTable roomId(String roomId) {
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

    public PoliticalcourseCourseTable collegeId(String collegeId) {
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

    public PoliticalcourseCourseTable collegeName(String collegeName) {
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
        PoliticalcourseCourseTable politicalcourseCourseTable = (PoliticalcourseCourseTable) o;
        return Objects.equals(this.studentType, politicalcourseCourseTable.studentType) &&
                Objects.equals(this.segment, politicalcourseCourseTable.segment) &&
                Objects.equals(this.courseName, politicalcourseCourseTable.courseName) &&
                Objects.equals(this.courseId, politicalcourseCourseTable.courseId) &&
                Objects.equals(this.courseTableDetailId, politicalcourseCourseTable.courseTableDetailId) &&
                Objects.equals(this.teachingClassName, politicalcourseCourseTable.teachingClassName) &&
                Objects.equals(this.teachingClassId, politicalcourseCourseTable.teachingClassId) &&
                Objects.equals(this.teachingClassStudentCount, politicalcourseCourseTable.teachingClassStudentCount) &&
                Objects.equals(this.roomName, politicalcourseCourseTable.roomName) &&
                Objects.equals(this.roomId, politicalcourseCourseTable.roomId) &&
                Objects.equals(this.collegeId, politicalcourseCourseTable.collegeId) &&
                Objects.equals(this.collegeName, politicalcourseCourseTable.collegeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentType,segment, courseName, courseId, courseTableDetailId, teachingClassName, teachingClassId,
                teachingClassStudentCount, roomName, roomId, collegeId, collegeName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PoliticalcourseCourseTable {\n");

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

