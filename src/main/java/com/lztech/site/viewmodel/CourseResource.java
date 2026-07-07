package com.lztech.site.viewmodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseResource
 */
@Validated

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-12-12T08:10:44.996Z")


public class CourseResource {
    @JsonProperty("roleId")
    private String roleId = null;

    @JsonProperty("studentType")
    private Integer studentType = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseSource")
    private Integer courseSource = null;

    @JsonProperty("courseCollegeId")
    private String courseCollegeId = null;
    
    @JsonProperty("courseCode")
    private String courseCode = null;

    @JsonProperty("courseCollege")
    private String courseCollege = null;

    @JsonProperty("courseCollegeCode")
    private String courseCollegeCode = null;

    @JsonProperty("courseCategoryId")
    private String courseCategoryId = null;

    @JsonProperty("courseCategory")
    private String courseCategory = null;

    @JsonProperty("teacherNo")
    private String teacherNo = null;

    @JsonProperty("courseName")
    private String courseName = null;
    @JsonProperty("teacherId")
    private String teacherId = null;

    @JsonProperty("teacherName")
    private String teacherName = null;

    @JsonProperty("schoolYear")
    private String schoolYear = null;

    @JsonProperty("term")
    private Integer term = null;


    @JsonProperty("teachingClassName")
    @Valid
    private List<CourseResourceTeachingClassName> teachingClassName = null;

    public CourseResource roleId(String roleId) {
        this.roleId = roleId;
        return this;
    }

    /**
     * 角色id
     *
     * @return roleId
     **/
    @ApiModelProperty(value = "角色id")


    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }




    public CourseResource studentType(Integer studentType) {
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

    public CourseResource courseId(String courseId) {
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

    public CourseResource courseCode(String courseCode) {
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

    public CourseResource courseSource(Integer courseSource) {
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

    public CourseResource courseCollegeId(String courseCollegeId) {
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

    public CourseResource courseCollege(String courseCollege) {
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

    public CourseResource courseCollegeCode(String courseCollegeCode) {
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

    public CourseResource courseCategoryId(String courseCategoryId) {
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

    public CourseResource courseCategory(String courseCategory) {
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

    public CourseResource teacherId(String teacherId) {
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

    public CourseResource teacherName(String teacherName) {
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

    public CourseResource courseName(String courseName) {
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

    public CourseResource schoolYear(String schoolYear) {
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

    public CourseResource term(Integer term) {
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

    public CourseResource teachingClassName(List<CourseResourceTeachingClassName> teachingClassName) {
        this.teachingClassName = teachingClassName;
        return this;
    }

    public CourseResource addTeachingClassNameItem(CourseResourceTeachingClassName teachingClassNameItem) {
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


    public CourseResource teacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
        return this;
    }

    /**
     * 教师工号
     *
     * @return teacherNo
     **/
    @ApiModelProperty(value = "教师工号")


    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(roleId).append(studentType).append(courseId)
                .append(courseSource).append(courseCollegeId).append(courseCode).append(courseCollege).append(courseCollegeCode)
                .append(courseCategoryId).append(courseCategory).append(teacherNo).append(courseName).append(teacherId)
                .append(teacherName).append(schoolYear).append(term).append(teachingClassName).toHashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseResource courseResource = (CourseResource) o;
        return Objects.equals(this.roleId, courseResource.roleId) &&
                Objects.equals(this.courseId, courseResource.courseId) &&
                Objects.equals(this.courseCode, courseResource.courseCode) &&
                Objects.equals(this.courseSource, courseResource.courseSource) &&
                Objects.equals(this.courseCollegeId, courseResource.courseCollegeId) &&
                Objects.equals(this.courseCollegeCode, courseResource.courseCollegeCode) &&
                Objects.equals(this.courseCollege, courseResource.courseCollege) &&
                Objects.equals(this.courseCategoryId, courseResource.courseCategoryId) &&
                Objects.equals(this.courseCategory, courseResource.courseCategory) &&
                Objects.equals(this.teacherId, courseResource.teacherId) &&
                Objects.equals(this.teacherName, courseResource.teacherName) &&
                Objects.equals(this.teacherNo, courseResource.teacherNo) &&
                Objects.equals(this.courseName, courseResource.courseName) &&
                Objects.equals(this.schoolYear, courseResource.schoolYear) &&
                Objects.equals(this.term, courseResource.term) &&
                Objects.equals(this.teachingClassName, courseResource.teachingClassName);
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

