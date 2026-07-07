package com.lztech.site.viewmodel.coursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseInfoModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-05-27T02:32:38.088Z")


public class CourseInfoModel {
    @JsonProperty("courseInfoId")
    private String courseInfoId = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("courseCode")
    private String courseCode = null;

    @JsonProperty("teacherId")
    private String teacherId = null;

    @JsonProperty("teacherName")
    private String teacherName = null;

    @JsonProperty("teacherNo")
    private String teacherNo = null;

    @JsonProperty("schoolYear")
    private String schoolYear = null;

    @JsonProperty("term")
    private Integer term = null;

    @JsonProperty("teachingClassId")
    private String teachingClassId = null;

    @JsonProperty("teachingClassName")
    private String teachingClassName = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    @JsonProperty("weekType")
    private String weekType = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseType")
    private Integer courseType = null;

    @JsonProperty("courseInfoCode")
    private String courseInfoCode = null;

    @JsonProperty("freshmanSeminar")
    private Integer freshmanSeminar = null;

    @JsonProperty("noticeCategory")
    private String noticeCategory = null;

    @JsonProperty("auxiliaryName")
    private String auxiliaryName = null;

    public CourseInfoModel courseInfoId(String courseInfoId) {
        this.courseInfoId = courseInfoId;
        return this;
    }

    /**
     * 开课id
     *
     * @return courseInfoId
     **/
    @ApiModelProperty(value = "开课id")


    public String getCourseInfoId() {
        return courseInfoId;
    }

    public void setCourseInfoId(String courseInfoId) {
        this.courseInfoId = courseInfoId;
    }

    public CourseInfoModel courseName(String courseName) {
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

    public CourseInfoModel courseCode(String courseCode) {
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

    public CourseInfoModel teacherId(String teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    /**
     * 开课老师id
     *
     * @return teacherId
     **/
    @ApiModelProperty(value = "开课老师id")


    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public CourseInfoModel teacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    /**
     * 开课老师名称
     *
     * @return teacherName
     **/
    @ApiModelProperty(value = "开课老师名称")


    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public CourseInfoModel teacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
        return this;
    }

    /**
     * 开课老师工号
     *
     * @return teacherNo
     **/
    @ApiModelProperty(value = "开课老师工号")


    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    public CourseInfoModel schoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
        return this;
    }

    /**
     * 学年: yyyy-yyyy
     *
     * @return schoolYear
     **/
    @ApiModelProperty(value = "学年: yyyy-yyyy")


    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public CourseInfoModel term(Integer term) {
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

    public CourseInfoModel teachingClassId(String teachingClassId) {
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

    public CourseInfoModel teachingClassName(String teachingClassName) {
        this.teachingClassName = teachingClassName;
        return this;
    }

    /**
     * 教学班名称
     *
     * @return teachingClassName
     **/
    @ApiModelProperty(value = "教学班名称")


    public String getTeachingClassName() {
        return teachingClassName;
    }

    public void setTeachingClassName(String teachingClassName) {
        this.teachingClassName = teachingClassName;
    }

    public CourseInfoModel collegeId(String collegeId) {
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

    public CourseInfoModel collegeName(String collegeName) {
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

    public CourseInfoModel weekType(String weekType) {
        this.weekType = weekType;
        return this;
    }

    /**
     * 单/双周
     *
     * @return weekType
     **/
    @ApiModelProperty(value = "单/双周")


    public String getWeekType() {
        return weekType;
    }

    public void setWeekType(String weekType) {
        this.weekType = weekType;
    }

    public CourseInfoModel courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    /**
     * 大课id
     *
     * @return courseId
     **/
    @ApiModelProperty(value = "大课id")


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public CourseInfoModel courseType(Integer courseType) {
        this.courseType = courseType;
        return this;
    }

    /**
     * 课程类别0：理论课，1：实验课
     *
     * @return courseType
     **/
    @ApiModelProperty(value = "课程类别0：理论课，1：实验课")


    public Integer getCourseType() {
        return courseType;
    }

    public void setCourseType(Integer courseType) {
        this.courseType = courseType;
    }

    public CourseInfoModel courseInfoCode(String courseInfoCode) {
        this.courseInfoCode = courseInfoCode;
        return this;
    }

    /**
     * 开课通知单号
     *
     * @return courseInfoCode
     **/
    @ApiModelProperty(value = "开课通知单号")


    public String getCourseInfoCode() {
        return courseInfoCode;
    }

    public void setCourseInfoCode(String courseInfoCode) {
        this.courseInfoCode = courseInfoCode;
    }

    public CourseInfoModel freshmanSeminar(Integer freshmanSeminar) {
        this.freshmanSeminar = freshmanSeminar;
        return this;
    }

    /**
     * 新生研讨课判断
     *
     * @return freshmanSeminar
     **/
    @ApiModelProperty(value = "新生研讨课判断")


    public Integer getFreshmanSeminar() {
        return freshmanSeminar;
    }

    public void setFreshmanSeminar(Integer freshmanSeminar) {
        this.freshmanSeminar = freshmanSeminar;
    }

    public CourseInfoModel noticeCategory(String noticeCategory) {
        this.noticeCategory = noticeCategory;
        return this;
    }

    /**
     * 通知单类型
     *
     * @return noticeCategory
     **/
    @ApiModelProperty(value = "通知单类型")


    public String getNoticeCategory() {
        return noticeCategory;
    }

    public void setNoticeCategory(String noticeCategory) {
        this.noticeCategory = noticeCategory;
    }

    public CourseInfoModel auxiliaryName(String auxiliaryName) {
        this.auxiliaryName = auxiliaryName;
        return this;
    }

    /**
     * 辅助名称
     *
     * @return auxiliaryName
     **/
    @ApiModelProperty(value = "辅助名称")


    public String getAuxiliaryName() {
        return auxiliaryName;
    }

    public void setAuxiliaryName(String auxiliaryName) {
        this.auxiliaryName = auxiliaryName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseInfoModel courseInfoModel = (CourseInfoModel) o;
        return Objects.equals(this.courseInfoId, courseInfoModel.courseInfoId) &&
                Objects.equals(this.courseName, courseInfoModel.courseName) &&
                Objects.equals(this.courseCode, courseInfoModel.courseCode) &&
                Objects.equals(this.teacherId, courseInfoModel.teacherId) &&
                Objects.equals(this.teacherName, courseInfoModel.teacherName) &&
                Objects.equals(this.teacherNo, courseInfoModel.teacherNo) &&
                Objects.equals(this.schoolYear, courseInfoModel.schoolYear) &&
                Objects.equals(this.term, courseInfoModel.term) &&
                Objects.equals(this.teachingClassId, courseInfoModel.teachingClassId) &&
                Objects.equals(this.teachingClassName, courseInfoModel.teachingClassName) &&
                Objects.equals(this.collegeId, courseInfoModel.collegeId) &&
                Objects.equals(this.collegeName, courseInfoModel.collegeName) &&
                Objects.equals(this.weekType, courseInfoModel.weekType) &&
                Objects.equals(this.courseId, courseInfoModel.courseId) &&
                Objects.equals(this.courseType, courseInfoModel.courseType) &&
                Objects.equals(this.courseInfoCode, courseInfoModel.courseInfoCode) &&
                Objects.equals(this.freshmanSeminar, courseInfoModel.freshmanSeminar) &&
                Objects.equals(this.noticeCategory, courseInfoModel.noticeCategory) &&
                Objects.equals(this.auxiliaryName, courseInfoModel.auxiliaryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseInfoId, courseName, courseCode, teacherId, teacherName, teacherNo, schoolYear, term,
                teachingClassId, teachingClassName, collegeId, collegeName, weekType, courseId, courseType, courseInfoCode,
                freshmanSeminar, noticeCategory, auxiliaryName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseInfoModel {\n");

        sb.append("    courseInfoId: ").append(toIndentedString(courseInfoId)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
        sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
        sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
        sb.append("    teacherNo: ").append(toIndentedString(teacherNo)).append("\n");
        sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
        sb.append("    term: ").append(toIndentedString(term)).append("\n");
        sb.append("    teachingClassId: ").append(toIndentedString(teachingClassId)).append("\n");
        sb.append("    teachingClassName: ").append(toIndentedString(teachingClassName)).append("\n");
        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
        sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
        sb.append("    weekType: ").append(toIndentedString(weekType)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseType: ").append(toIndentedString(courseType)).append("\n");
        sb.append("    courseInfoCode: ").append(toIndentedString(courseInfoCode)).append("\n");
        sb.append("    freshmanSeminar: ").append(toIndentedString(freshmanSeminar)).append("\n");
        sb.append("    noticeCategory: ").append(toIndentedString(noticeCategory)).append("\n");
        sb.append("    auxiliaryName: ").append(toIndentedString(auxiliaryName)).append("\n");
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

