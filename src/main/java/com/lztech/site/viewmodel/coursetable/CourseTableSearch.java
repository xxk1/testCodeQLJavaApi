package com.lztech.site.viewmodel.coursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseTableSearch
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-12-16T02:51:56.289Z")


public class CourseTableSearch {
    @JsonProperty("studentType")
    private Integer studentType = null;

    @JsonProperty("groupSource")
    private Integer groupSource = null;

    @JsonProperty("schoolYear")
    private String schoolYear = null;

    @JsonProperty("term")
    private String term = null;

    @JsonProperty("className")
    private String className = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("courseNameOrNo")
    private String courseNameOrNo = null;

    @JsonProperty("isDistinguishCourseStudentType")
    private String isDistinguishCourseStudentType = null;

    @JsonProperty("courseStudentTypeIds")
    private String courseStudentTypeIds = null;

    public String getIsDistinguishCourseStudentType() {
        return isDistinguishCourseStudentType;
    }

    public void setIsDistinguishCourseStudentType(String isDistinguishCourseStudentType) {
        this.isDistinguishCourseStudentType = isDistinguishCourseStudentType;
    }

    public String getCourseStudentTypeIds() {
        return courseStudentTypeIds;
    }

    public void setCourseStudentTypeIds(String courseStudentTypeIds) {
        this.courseStudentTypeIds = courseStudentTypeIds;
    }

    public CourseTableSearch studentType(Integer studentType) {
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

    public CourseTableSearch groupSource(Integer groupSource) {
        this.groupSource = groupSource;
        return this;
    }

    /**
     * 班级来源；0：数据对接；1：手动添加
     *
     * @return groupSource
     **/
    @ApiModelProperty(value = "班级来源；0：数据对接；1：手动添加")


    public Integer getGroupSource() {
        return groupSource;
    }

    public void setGroupSource(Integer groupSource) {
        this.groupSource = groupSource;
    }

    public CourseTableSearch schoolYear(String schoolYear) {
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

    public CourseTableSearch term(String term) {
        this.term = term;
        return this;
    }

    /**
     * 学期
     *
     * @return term
     **/
    @ApiModelProperty(value = "学期")


    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public CourseTableSearch className(String className) {
        this.className = className;
        return this;
    }

    /**
     * 班级名称
     *
     * @return className
     **/
    @ApiModelProperty(value = "班级名称")


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public CourseTableSearch collegeId(String collegeId) {
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

    public CourseTableSearch courseNameOrNo(String courseNameOrNo) {
        this.courseNameOrNo = courseNameOrNo;
        return this;
    }

    /**
     * 课程名称或编号
     *
     * @return courseNameOrNo
     **/
    @ApiModelProperty(value = "课程名称或编号")


    public String getCourseNameOrNo() {
        return courseNameOrNo;
    }

    public void setCourseNameOrNo(String courseNameOrNo) {
        this.courseNameOrNo = courseNameOrNo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseTableSearch courseTableSearch = (CourseTableSearch) o;
        return Objects.equals(this.studentType, courseTableSearch.studentType) &&
                Objects.equals(this.groupSource, courseTableSearch.groupSource) &&
                Objects.equals(this.schoolYear, courseTableSearch.schoolYear) &&
                Objects.equals(this.term, courseTableSearch.term) &&
                Objects.equals(this.className, courseTableSearch.className) &&
                Objects.equals(this.collegeId, courseTableSearch.collegeId) &&
                Objects.equals(this.courseNameOrNo, courseTableSearch.courseNameOrNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentType, groupSource, schoolYear, term, className, collegeId, courseNameOrNo);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTableSearch {\n");

        sb.append("    studentType: ").append(toIndentedString(studentType)).append("\n");
        sb.append("    groupSource: ").append(toIndentedString(groupSource)).append("\n");
        sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
        sb.append("    term: ").append(toIndentedString(term)).append("\n");
        sb.append("    className: ").append(toIndentedString(className)).append("\n");
        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
        sb.append("    courseNameOrNo: ").append(toIndentedString(courseNameOrNo)).append("\n");
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

