package com.lztech.site.viewmodel.coursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lztech.site.viewmodel.CourseResourceTeachingClassName;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * TeacherCourseResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-10-17T08:06:38.410Z")


public class TeacherCourseResource {

    @JsonProperty("studentType")
    private Integer studentType = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseCode")
    private String courseCode = null;

    @JsonProperty("courseSource")
    private Integer courseSource = null;

    @JsonProperty("courseCollegeId")
    private String courseCollegeId = null;

    @JsonProperty("courseCollege")
    private String courseCollege = null;

    @JsonProperty("courseCollegeCode")
    private String courseCollegeCode = null;

    @JsonProperty("courseCategoryId")
    private String courseCategoryId = null;

    @JsonProperty("courseCategory")
    private String courseCategory = null;

    @JsonProperty("teacherId")
    private String teacherId = null;

    @JsonProperty("teacherName")
    private String teacherName = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("schoolYear")
    private String schoolYear = null;

    @JsonProperty("term")
    private Integer term = null;

    @JsonProperty("teachingClassCount")
    private Integer teachingClassCount = null;

    @JsonProperty("teachingClassStudentCount")
    private Integer teachingClassStudentCount = null;

    @JsonProperty("totalClassHour")
    private Integer totalClassHour = null;

    @JsonProperty("completeClassHour")
    private Integer completeClassHour = null;

    @JsonProperty("teachingClassName")
    @Valid
    private List<CourseResourceTeachingClassName> teachingClassName = null;


    public TeacherCourseResource studentType(Integer studentType) {
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

    public TeacherCourseResource courseId(String courseId) {
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

    public TeacherCourseResource courseCode(String courseCode) {
        this.courseCode = courseCode;
        return this;
    }

    /**
     * 课程编号
     *
     * @return courseCode
     **/
    @ApiModelProperty(value = "课程编号")


    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public TeacherCourseResource courseSource(Integer courseSource) {
        this.courseSource = courseSource;
        return this;
    }

    /**
     * 课程来源 0：对接；1：新增
     *
     * @return courseSource
     **/
    @ApiModelProperty(value = "课程来源 0：对接；1：新增")


    public Integer getCourseSource() {
        return courseSource;
    }

    public void setCourseSource(Integer courseSource) {
        this.courseSource = courseSource;
    }

    public TeacherCourseResource courseCollegeId(String courseCollegeId) {
        this.courseCollegeId = courseCollegeId;
        return this;
    }

    /**
     * 课程学院id
     *
     * @return courseCollegeId
     **/
    @ApiModelProperty(value = "课程学院id")


    public String getCourseCollegeId() {
        return courseCollegeId;
    }

    public void setCourseCollegeId(String courseCollegeId) {
        this.courseCollegeId = courseCollegeId;
    }

    public TeacherCourseResource courseCollege(String courseCollege) {
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

    public TeacherCourseResource courseCollegeCode(String courseCollegeCode) {
        this.courseCollegeCode = courseCollegeCode;
        return this;
    }

    /**
     * 课程学院编号
     *
     * @return courseCollegeCode
     **/
    @ApiModelProperty(value = "课程学院编号")


    public String getCourseCollegeCode() {
        return courseCollegeCode;
    }

    public void setCourseCollegeCode(String courseCollegeCode) {
        this.courseCollegeCode = courseCollegeCode;
    }

    public TeacherCourseResource courseCategoryId(String courseCategoryId) {
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

    public TeacherCourseResource courseCategory(String courseCategory) {
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

    public TeacherCourseResource teacherId(String teacherId) {
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

    public TeacherCourseResource teacherName(String teacherName) {
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

    public TeacherCourseResource courseName(String courseName) {
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

    public TeacherCourseResource schoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
        return this;
    }

    /**
     * 学年
     *
     * @return schoolYear
     **/
    @ApiModelProperty(value = "学年")


    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public TeacherCourseResource term(Integer term) {
        this.term = term;
        return this;
    }

    /**
     * 学期
     *
     * @return term
     **/
    @ApiModelProperty(value = "学期")


    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public TeacherCourseResource teachingClassCount(Integer teachingClassCount) {
        this.teachingClassCount = teachingClassCount;
        return this;
    }

    /**
     * 教学班个数
     *
     * @return teachingClassCount
     **/
    @ApiModelProperty(value = "教学班个数")


    public Integer getTeachingClassCount() {
        return teachingClassCount;
    }

    public void setTeachingClassCount(Integer teachingClassCount) {
        this.teachingClassCount = teachingClassCount;
    }

    public TeacherCourseResource teachingClassStudentCount(Integer teachingClassStudentCount) {
        this.teachingClassStudentCount = teachingClassStudentCount;
        return this;
    }

    /**
     * 教学班学生个数
     *
     * @return teachingClassStudentCount
     **/
    @ApiModelProperty(value = "教学班学生个数")


    public Integer getTeachingClassStudentCount() {
        return teachingClassStudentCount;
    }

    public void setTeachingClassStudentCount(Integer teachingClassStudentCount) {
        this.teachingClassStudentCount = teachingClassStudentCount;
    }

    public TeacherCourseResource totalClassHour(Integer totalClassHour) {
        this.totalClassHour = totalClassHour;
        return this;
    }

    /**
     * 总课时
     *
     * @return totalClassHour
     **/
    @ApiModelProperty(value = "总课时")


    public Integer getTotalClassHour() {
        return totalClassHour;
    }

    public void setTotalClassHour(Integer totalClassHour) {
        this.totalClassHour = totalClassHour;
    }

    public TeacherCourseResource completeClassHour(Integer completeClassHour) {
        this.completeClassHour = completeClassHour;
        return this;
    }

    /**
     * 完成课时
     *
     * @return completeClassHour
     **/
    @ApiModelProperty(value = "完成课时")


    public Integer getCompleteClassHour() {
        return completeClassHour;
    }

    public void setCompleteClassHour(Integer completeClassHour) {
        this.completeClassHour = completeClassHour;
    }


    public TeacherCourseResource teachingClassName(List<CourseResourceTeachingClassName> teachingClassName) {
        this.teachingClassName = teachingClassName;
        return this;
    }

    public TeacherCourseResource addTeachingClassNameItem(CourseResourceTeachingClassName teachingClassNameItem) {
        if (this.teachingClassName == null) {
            this.teachingClassName = new ArrayList<CourseResourceTeachingClassName>();
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

    public List<CourseResourceTeachingClassName> getTeachingClassName() {
        return teachingClassName;
    }

    public void setTeachingClassName(List<CourseResourceTeachingClassName> teachingClassName) {
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
        TeacherCourseResource teacherCourseResource = (TeacherCourseResource) o;
        return Objects.equals(this.studentType, teacherCourseResource.studentType) &&
                Objects.equals(this.courseId, teacherCourseResource.courseId) &&
                Objects.equals(this.courseCode, teacherCourseResource.courseCode) &&
                Objects.equals(this.courseSource, teacherCourseResource.courseSource) &&
                Objects.equals(this.courseCollegeId, teacherCourseResource.courseCollegeId) &&
                Objects.equals(this.courseCollege, teacherCourseResource.courseCollege) &&
                Objects.equals(this.courseCollegeCode, teacherCourseResource.courseCollegeCode) &&
                Objects.equals(this.courseCategoryId, teacherCourseResource.courseCategoryId) &&
                Objects.equals(this.courseCategory, teacherCourseResource.courseCategory) &&
                Objects.equals(this.teacherId, teacherCourseResource.teacherId) &&
                Objects.equals(this.teacherName, teacherCourseResource.teacherName) &&
                Objects.equals(this.courseName, teacherCourseResource.courseName) &&
                Objects.equals(this.schoolYear, teacherCourseResource.schoolYear) &&
                Objects.equals(this.term, teacherCourseResource.term) &&
                Objects.equals(this.teachingClassCount, teacherCourseResource.teachingClassCount) &&
                Objects.equals(this.teachingClassStudentCount, teacherCourseResource.teachingClassStudentCount) &&
                Objects.equals(this.totalClassHour, teacherCourseResource.totalClassHour) &&
                Objects.equals(this.completeClassHour, teacherCourseResource.completeClassHour) &&
                Objects.equals(this.teachingClassName, teacherCourseResource.teachingClassName) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentType, courseId, courseCode, courseSource, courseCollegeId, courseCollege,
                courseCollegeCode, courseCategoryId, courseCategory, teacherId, teacherName, courseName,
                schoolYear, term, teachingClassCount, teachingClassStudentCount, totalClassHour, completeClassHour,
                teachingClassName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TeacherCourseResource {\n");

        sb.append("    studentType: ").append(toIndentedString(studentType)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
        sb.append("    courseSource: ").append(toIndentedString(courseSource)).append("\n");
        sb.append("    courseCollegeId: ").append(toIndentedString(courseCollegeId)).append("\n");
        sb.append("    courseCollege: ").append(toIndentedString(courseCollege)).append("\n");
        sb.append("    courseCollegeCode: ").append(toIndentedString(courseCollegeCode)).append("\n");
        sb.append("    courseCategoryId: ").append(toIndentedString(courseCategoryId)).append("\n");
        sb.append("    courseCategory: ").append(toIndentedString(courseCategory)).append("\n");
        sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
        sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
        sb.append("    term: ").append(toIndentedString(term)).append("\n");
        sb.append("    teachingClassCount: ").append(toIndentedString(teachingClassCount)).append("\n");
        sb.append("    teachingClassStudentCount: ").append(toIndentedString(teachingClassStudentCount)).append("\n");
        sb.append("    totalClassHour: ").append(toIndentedString(totalClassHour)).append("\n");
        sb.append("    completeClassHour: ").append(toIndentedString(completeClassHour)).append("\n");
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

