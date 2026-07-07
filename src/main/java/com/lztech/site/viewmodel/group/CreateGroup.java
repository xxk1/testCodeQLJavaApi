package com.lztech.site.viewmodel.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CreateGroup
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-12-06T02:56:09.132Z")


public class CreateGroup   {
    @JsonProperty("teacherId")
    private String teacherId = null;

    @JsonProperty("teacherName")
    private String teacherName = null;

    @JsonProperty("teacherNo")
    private String teacherNo = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("studentType")
    private String studentType = null;

    @JsonProperty("className")
    private String className = null;

    @JsonProperty("schoolYear")
    private String schoolYear = null;

    @JsonProperty("term")
    private String term = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    @JsonProperty("groupAttribute")
    private Integer groupAttribute = null;

    public CreateGroup teacherId(String teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    /**
     * 老师Id
     * @return teacherId
     **/
    @ApiModelProperty(value = "老师Id")


    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public CreateGroup teacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    /**
     * 老师Name
     * @return teacherName
     **/
    @ApiModelProperty(value = "老师Name")


    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public CreateGroup teacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
        return this;
    }

    /**
     * 老师No
     * @return teacherNo
     **/
    @ApiModelProperty(value = "老师No")


    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    public CreateGroup courseId(String courseId) {
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

    public CreateGroup studentType(String studentType) {
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

    public CreateGroup className(String className) {
        this.className = className;
        return this;
    }

    /**
     * 班级名称
     * @return className
     **/
    @ApiModelProperty(value = "班级名称")


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public CreateGroup schoolYear(String schoolYear) {
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

    public CreateGroup term(String term) {
        this.term = term;
        return this;
    }

    /**
     * 学期
     * @return term
     **/
    @ApiModelProperty(value = "学期")


    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public CreateGroup collegeId(String collegeId) {
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

    public CreateGroup collegeName(String collegeName) {
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

    public CreateGroup groupAttribute(Integer groupAttribute) {
        this.groupAttribute = groupAttribute;
        return this;
    }

    /**
     * 班级属性 0:理论班；1：实践班；2：理实混合班
     * @return groupAttribute
     **/
    @ApiModelProperty(value = "班级属性 0:理论班；1：实践班；2：理实混合班")


    public Integer getGroupAttribute() {
        return groupAttribute;
    }

    public void setGroupAttribute(Integer groupAttribute) {
        this.groupAttribute = groupAttribute;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateGroup createGroup = (CreateGroup) o;
        return Objects.equals(this.teacherId, createGroup.teacherId) &&
                Objects.equals(this.teacherName, createGroup.teacherName) &&
                Objects.equals(this.teacherNo, createGroup.teacherNo) &&
                Objects.equals(this.courseId, createGroup.courseId) &&
                Objects.equals(this.studentType, createGroup.studentType) &&
                Objects.equals(this.className, createGroup.className) &&
                Objects.equals(this.schoolYear, createGroup.schoolYear) &&
                Objects.equals(this.term, createGroup.term) &&
                Objects.equals(this.collegeId, createGroup.collegeId) &&
                Objects.equals(this.collegeName, createGroup.collegeName) &&
                Objects.equals(this.groupAttribute, createGroup.groupAttribute);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, teacherName, teacherNo, courseId, studentType, className,
                schoolYear, term, collegeId, collegeName, groupAttribute);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreateGroup {\n");

        sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
        sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
        sb.append("    teacherNo: ").append(toIndentedString(teacherNo)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    studentType: ").append(toIndentedString(studentType)).append("\n");
        sb.append("    className: ").append(toIndentedString(className)).append("\n");
        sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
        sb.append("    term: ").append(toIndentedString(term)).append("\n");
        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
        sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
        sb.append("    groupAttribute: ").append(toIndentedString(groupAttribute)).append("\n");
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

