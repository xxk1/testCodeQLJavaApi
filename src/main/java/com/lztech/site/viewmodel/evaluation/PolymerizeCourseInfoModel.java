package com.lztech.site.viewmodel.evaluation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * PolymerizeCourseInfoModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-07-09T11:13:57.420Z")


public class PolymerizeCourseInfoModel {

    @JsonProperty("termId")
    private String termId = null;

    @JsonProperty("schoolYear")
    private String schoolYear = null;

    @JsonProperty("term")
    private Integer term = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseCode")
    private String courseCode = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("collegeCode")
    private String collegeCode = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    public PolymerizeCourseInfoModel termId(String termId) {
        this.termId = termId;
        return this;
    }

    /**
     * 学期id
     *
     * @return termId
     **/
    @ApiModelProperty(value = "学期id")


    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }


    public PolymerizeCourseInfoModel schoolYear(String schoolYear) {
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

    public PolymerizeCourseInfoModel term(Integer term) {
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

    public PolymerizeCourseInfoModel courseId(String courseId) {
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

    public PolymerizeCourseInfoModel courseCode(String courseCode) {
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

    public PolymerizeCourseInfoModel courseName(String courseName) {
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

    public PolymerizeCourseInfoModel collegeId(String collegeId) {
        this.collegeId = collegeId;
        return this;
    }

    /**
     * 开课学院id
     *
     * @return collegeId
     **/
    @ApiModelProperty(value = "开课学院id")


    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public PolymerizeCourseInfoModel collegeCode(String collegeCode) {
        this.collegeCode = collegeCode;
        return this;
    }

    /**
     * 开课学院编号
     *
     * @return collegeCode
     **/
    @ApiModelProperty(value = "开课学院编号")


    public String getCollegeCode() {
        return collegeCode;
    }

    public void setCollegeCode(String collegeCode) {
        this.collegeCode = collegeCode;
    }

    public PolymerizeCourseInfoModel collegeName(String collegeName) {
        this.collegeName = collegeName;
        return this;
    }

    /**
     * 开课学院名称
     *
     * @return collegeName
     **/
    @ApiModelProperty(value = "开课学院名称")


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
        PolymerizeCourseInfoModel polymerizeCourseInfoModel = (PolymerizeCourseInfoModel) o;
        return Objects.equals(this.termId, polymerizeCourseInfoModel.termId) &&
                Objects.equals(this.schoolYear, polymerizeCourseInfoModel.schoolYear) &&
                Objects.equals(this.term, polymerizeCourseInfoModel.term) &&
                Objects.equals(this.courseId, polymerizeCourseInfoModel.courseId) &&
                Objects.equals(this.courseCode, polymerizeCourseInfoModel.courseCode) &&
                Objects.equals(this.courseName, polymerizeCourseInfoModel.courseName) &&
                Objects.equals(this.collegeId, polymerizeCourseInfoModel.collegeId) &&
                Objects.equals(this.collegeCode, polymerizeCourseInfoModel.collegeCode) &&
                Objects.equals(this.collegeName, polymerizeCourseInfoModel.collegeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(termId,schoolYear, term, courseId, courseCode, courseName, collegeId, collegeCode, collegeName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PolymerizeCourseInfoModel {\n");
        sb.append("    termId: ").append(toIndentedString(termId)).append("\n");
        sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
        sb.append("    term: ").append(toIndentedString(term)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
        sb.append("    collegeCode: ").append(toIndentedString(collegeCode)).append("\n");
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

