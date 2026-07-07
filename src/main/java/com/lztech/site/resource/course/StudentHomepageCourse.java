package com.lztech.site.resource.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * StudentHomepageCourse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-17T06:55:18.036Z")

public class StudentHomepageCourse {
    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseCategoryId")
    private String courseCategoryId = null;

    @JsonProperty("courseCategory")
    private String courseCategory = null;

    @JsonProperty("courseCollege")
    private String courseCollege = null;

    @JsonProperty("teacherId")
    private String teacherId = null;

    @JsonProperty("teacherName")
    private String teacherName = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("teachingClassName")
    @Valid
    private List<StudentHomepageCourseTeachingClassName> teachingClassName = null;

    public StudentHomepageCourse courseId(String courseId) {
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

    public StudentHomepageCourse courseCategoryId(String courseCategoryId) {
        this.courseCategoryId = courseCategoryId;
        return this;
    }

    /**
     * 课程类别Id
     *
     * @return courseCategoryId
     **/
    @ApiModelProperty(value = "课程类别Id")


    public String getCourseCategoryId() {
        return courseCategoryId;
    }

    public void setCourseCategoryId(String courseCategoryId) {
        this.courseCategoryId = courseCategoryId;
    }

    public StudentHomepageCourse courseCategory(String courseCategory) {
        this.courseCategory = courseCategory;
        return this;
    }

    /**
     * 课程类别
     *
     * @return courseCategory
     **/
    @ApiModelProperty(value = "课程类别")


    public String getCourseCategory() {
        return courseCategory;
    }

    public void setCourseCategory(String courseCategory) {
        this.courseCategory = courseCategory;
    }

    public StudentHomepageCourse courseCollege(String courseCollege) {
        this.courseCollege = courseCollege;
        return this;
    }

    /**
     * 课程学院
     *
     * @return courseCollege
     **/
    @ApiModelProperty(value = "课程学院")


    public String getCourseCollege() {
        return courseCollege;
    }

    public void setCourseCollege(String courseCollege) {
        this.courseCollege = courseCollege;
    }

    public StudentHomepageCourse teacherId(String teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    /**
     * 上课老师id,多个老师使用“,” 隔开
     *
     * @return teacherId
     **/
    @ApiModelProperty(value = "上课老师id,多个老师使用“,” 隔开")


    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public StudentHomepageCourse teacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    /**
     * 上课老师名称,多个老师使用“,” 隔开
     *
     * @return teacherName
     **/
    @ApiModelProperty(value = "上课老师名称,多个老师使用“,” 隔开")


    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public StudentHomepageCourse courseName(String courseName) {
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

    public StudentHomepageCourse teachingClassName(List<StudentHomepageCourseTeachingClassName> teachingClassName) {
        this.teachingClassName = teachingClassName;
        return this;
    }

    public StudentHomepageCourse addTeachingClassNameItem(StudentHomepageCourseTeachingClassName teachingClassNameItem) {
        if (this.teachingClassName == null) {
            this.teachingClassName = new ArrayList<StudentHomepageCourseTeachingClassName>();
        }
        this.teachingClassName.add(teachingClassNameItem);
        return this;
    }

    /**
     * 上课班级列表
     *
     * @return teachingClassName
     **/
    @ApiModelProperty(value = "上课班级列表")

    @Valid

    public List<StudentHomepageCourseTeachingClassName> getTeachingClassName() {
        return teachingClassName;
    }

    public void setTeachingClassName(List<StudentHomepageCourseTeachingClassName> teachingClassName) {
        this.teachingClassName = teachingClassName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StudentHomepageCourse studentHomepageCourse = (StudentHomepageCourse) o;
        return Objects.equals(this.courseId, studentHomepageCourse.courseId) &&
                Objects.equals(this.courseCategoryId, studentHomepageCourse.courseCategoryId) &&
                Objects.equals(this.courseCategory, studentHomepageCourse.courseCategory) &&
                Objects.equals(this.courseCollege, studentHomepageCourse.courseCollege) &&
                Objects.equals(this.teacherId, studentHomepageCourse.teacherId) &&
                Objects.equals(this.teacherName, studentHomepageCourse.teacherName) &&
                Objects.equals(this.courseName, studentHomepageCourse.courseName) &&
                Objects.equals(this.teachingClassName, studentHomepageCourse.teachingClassName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseCategoryId, courseCategory, courseCollege, teacherId, teacherName, courseName, teachingClassName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class StudentHomepageCourse {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseCategoryId: ").append(toIndentedString(courseCategoryId)).append("\n");
        sb.append("    courseCategory: ").append(toIndentedString(courseCategory)).append("\n");
        sb.append("    courseCollege: ").append(toIndentedString(courseCollege)).append("\n");
        sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
        sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    teachingClassName: ").append(toIndentedString(teachingClassName)).append("\n");
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

