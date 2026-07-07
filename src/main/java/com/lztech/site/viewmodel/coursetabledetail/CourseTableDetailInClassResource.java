package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseTableDetailInClassResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-08-09T08:34:29.257Z")


public class CourseTableDetailInClassResource {
    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("studentCount")
    private String studentCount = null;

    @JsonProperty("teacherName")
    private String teacherName = null;

    @JsonProperty("classroomId")
    private String classroomId = null;

    @JsonProperty("classroomName")
    private String classroomName = null;

    @JsonProperty("courseTableDetailId")
    private String courseTableDetailId = null;

    public CourseTableDetailInClassResource courseId(String courseId) {
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

    public CourseTableDetailInClassResource courseName(String courseName) {
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

    public CourseTableDetailInClassResource studentCount(String studentCount) {
        this.studentCount = studentCount;
        return this;
    }

    /**
     * 上课学生数量
     *
     * @return studentCount
     **/
    @ApiModelProperty(value = "上课学生数量")


    public String getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(String studentCount) {
        this.studentCount = studentCount;
    }

    public CourseTableDetailInClassResource teacherName(String teacherName) {
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

    public CourseTableDetailInClassResource classroomId(String classroomId) {
        this.classroomId = classroomId;
        return this;
    }

    /**
     * 教室Id
     *
     * @return classroomId
     **/
    @ApiModelProperty(value = "教室Id")


    public String getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(String classroomId) {
        this.classroomId = classroomId;
    }

    public CourseTableDetailInClassResource classroomName(String classroomName) {
        this.classroomName = classroomName;
        return this;
    }

    /**
     * 教室名称
     *
     * @return classroomName
     **/
    @ApiModelProperty(value = "教室名称")


    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public CourseTableDetailInClassResource courseTableDetailId(String courseTableDetailId) {
        this.courseTableDetailId = courseTableDetailId;
        return this;
    }

    /**
     * 课表详情Id
     *
     * @return courseTableDetailId
     **/
    @ApiModelProperty(value = "课表详情Id")


    public String getCourseTableDetailId() {
        return courseTableDetailId;
    }

    public void setCourseTableDetailId(String courseTableDetailId) {
        this.courseTableDetailId = courseTableDetailId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseTableDetailInClassResource courseTableDetailInClassResource = (CourseTableDetailInClassResource) o;
        return Objects.equals(this.courseId, courseTableDetailInClassResource.courseId) &&
                Objects.equals(this.courseName, courseTableDetailInClassResource.courseName) &&
                Objects.equals(this.studentCount, courseTableDetailInClassResource.studentCount) &&
                Objects.equals(this.teacherName, courseTableDetailInClassResource.teacherName) &&
                Objects.equals(this.classroomId, courseTableDetailInClassResource.classroomId) &&
                Objects.equals(this.classroomName, courseTableDetailInClassResource.classroomName) &&
                Objects.equals(this.courseTableDetailId, courseTableDetailInClassResource.courseTableDetailId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseName, studentCount, teacherName, classroomId, classroomName, courseTableDetailId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTableDetailInClassResource {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    studentCount: ").append(toIndentedString(studentCount)).append("\n");
        sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
        sb.append("    classroomId: ").append(toIndentedString(classroomId)).append("\n");
        sb.append("    classroomName: ").append(toIndentedString(classroomName)).append("\n");
        sb.append("    courseTableDetailId: ").append(toIndentedString(courseTableDetailId)).append("\n");
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

