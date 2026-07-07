package com.lztech.site.resource.classresource;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ClassTeacher
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-12-06T01:46:14.456Z")


public class ClassTeacher   {
    @JsonProperty("classes")
    @Valid
    private List<ClassResource> classes = null;

    @JsonProperty("courseCategory")
    private String courseCategory = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("courseSegment")
    private Integer courseSegment = null;

    @JsonProperty("isRecommend")
    private Integer isRecommend = null;

    @JsonProperty("schoolYear")
    private String schoolYear = null;

    @JsonProperty("term")
    private Integer term = null;

    @JsonProperty("userId")
    private String userId = null;

    @JsonProperty("userName")
    private String userName = null;

    @JsonProperty("userNo")
    private String userNo = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("studentType")
    private String studentType = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    @JsonProperty("validCode")
    private String validCode = null;

    public ClassTeacher classes(List<ClassResource> classes) {
        this.classes = classes;
        return this;
    }

    public ClassTeacher addClassesItem(ClassResource classesItem) {
        if (this.classes == null) {
            this.classes = new ArrayList<ClassResource>();
        }
        this.classes.add(classesItem);
        return this;
    }

    /**
     * Get classes
     * @return classes
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<ClassResource> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassResource> classes) {
        this.classes = classes;
    }

    public ClassTeacher courseCategory(String courseCategory) {
        this.courseCategory = courseCategory;
        return this;
    }

    /**
     * 课程类别
     * @return courseCategory
     **/
    @ApiModelProperty(value = "课程类别")


    public String getCourseCategory() {
        return courseCategory;
    }

    public void setCourseCategory(String courseCategory) {
        this.courseCategory = courseCategory;
    }

    public ClassTeacher courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    /**
     * 课程Id
     * @return courseId
     **/
    @ApiModelProperty(value = "课程Id")


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public ClassTeacher courseName(String courseName) {
        this.courseName = courseName;
        return this;
    }

    /**
     * 课程名称
     * @return courseName
     **/
    @ApiModelProperty(value = "课程名称")


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public ClassTeacher courseSegment(Integer courseSegment) {
        this.courseSegment = courseSegment;
        return this;
    }

    /**
     * 课次
     * @return courseSegment
     **/
    @ApiModelProperty(value = "课次")


    public Integer getCourseSegment() {
        return courseSegment;
    }

    public void setCourseSegment(Integer courseSegment) {
        this.courseSegment = courseSegment;
    }

    public ClassTeacher isRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
        return this;
    }

    /**
     * 是否推荐 0：推荐 1：不推荐
     * @return isRecommend
     **/
    @ApiModelProperty(value = "是否推荐 0：推荐 1：不推荐")


    public Integer getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

    public ClassTeacher schoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
        return this;
    }

    /**
     * 学年
     * @return schoolYear
     **/
    @ApiModelProperty(value = "学年")


    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public ClassTeacher term(Integer term) {
        this.term = term;
        return this;
    }

    /**
     * 学期
     * @return term
     **/
    @ApiModelProperty(value = "学期")


    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public ClassTeacher userId(String userId) {
        this.userId = userId;
        return this;
    }

    /**
     * 老师Id
     * @return userId
     **/
    @ApiModelProperty(value = "老师Id")


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ClassTeacher userName(String userName) {
        this.userName = userName;
        return this;
    }

    /**
     * 老师姓名
     * @return userName
     **/
    @ApiModelProperty(value = "老师姓名")


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ClassTeacher userNo(String userNo) {
        this.userNo = userNo;
        return this;
    }

    /**
     * 老师编号
     * @return userNo
     **/
    @ApiModelProperty(value = "老师编号")


    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public ClassTeacher collegeId(String collegeId) {
        this.collegeId = collegeId;
        return this;
    }

    /**
     * 老师所属学院id
     * @return collegeId
     **/
    @ApiModelProperty(value = "老师所属学院id")


    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public ClassTeacher studentType(String studentType) {
        this.studentType = studentType;
        return this;
    }

    /**
     * 课程开课类型：0：本科生 1：研究生
     * @return studentType
     **/
    @ApiModelProperty(value = "课程开课类型：0：本科生 1：研究生")


    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    public ClassTeacher collegeName(String collegeName) {
        this.collegeName = collegeName;
        return this;
    }

    /**
     * 老师所属学院名称
     * @return collegeName
     **/
    @ApiModelProperty(value = "老师所属学院名称")


    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public ClassTeacher validCode(String validCode) {
        this.validCode = validCode;
        return this;
    }

    /**
     * 验证码（&signKey=123123）
     * @return validCode
     **/
    @ApiModelProperty(value = "验证码（&signKey=123123）")


    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClassTeacher classTeacher = (ClassTeacher) o;
        return Objects.equals(this.classes, classTeacher.classes) &&
                Objects.equals(this.courseCategory, classTeacher.courseCategory) &&
                Objects.equals(this.courseId, classTeacher.courseId) &&
                Objects.equals(this.courseName, classTeacher.courseName) &&
                Objects.equals(this.courseSegment, classTeacher.courseSegment) &&
                Objects.equals(this.isRecommend, classTeacher.isRecommend) &&
                Objects.equals(this.schoolYear, classTeacher.schoolYear) &&
                Objects.equals(this.term, classTeacher.term) &&
                Objects.equals(this.userId, classTeacher.userId) &&
                Objects.equals(this.userName, classTeacher.userName) &&
                Objects.equals(this.userNo, classTeacher.userNo) &&
                Objects.equals(this.collegeId, classTeacher.collegeId) &&
                Objects.equals(this.studentType, classTeacher.studentType) &&
                Objects.equals(this.collegeName, classTeacher.collegeName) &&
                Objects.equals(this.validCode, classTeacher.validCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classes, courseCategory, courseId, courseName, courseSegment, isRecommend,
                schoolYear, term, userId, userName, userNo, collegeId, studentType, collegeName, validCode);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ClassTeacher {\n");

        sb.append("    classes: ").append(toIndentedString(classes)).append("\n");
        sb.append("    courseCategory: ").append(toIndentedString(courseCategory)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    courseSegment: ").append(toIndentedString(courseSegment)).append("\n");
        sb.append("    isRecommend: ").append(toIndentedString(isRecommend)).append("\n");
        sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
        sb.append("    term: ").append(toIndentedString(term)).append("\n");
        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
        sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
        sb.append("    userNo: ").append(toIndentedString(userNo)).append("\n");
        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
        sb.append("    studentType: ").append(toIndentedString(studentType)).append("\n");
        sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
        sb.append("    validCode: ").append(toIndentedString(validCode)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

