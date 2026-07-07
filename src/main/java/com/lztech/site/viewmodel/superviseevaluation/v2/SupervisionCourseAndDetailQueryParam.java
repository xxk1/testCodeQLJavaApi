package com.lztech.site.viewmodel.superviseevaluation.v2;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * SupervisionCourseAndDetailQueryParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-07-11T08:58:14.057Z")


public class SupervisionCourseAndDetailQueryParam {
    @JsonProperty("schoolYear")
    private String schoolYear = null;

    @JsonProperty("term")
    private Integer term = null;

    @JsonProperty("collegeType")
    private Integer collegeType = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("collegeIds")
    private String collegeIds = null;

    @JsonProperty("nowTime")
    private String nowTime = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("teachers")
    private String teachers = null;

    @JsonProperty("studentType")
    private String studentType = null;

    @JsonProperty("isNeedFilterPoliticalCourse")
    private Boolean isNeedFilterPoliticalCourse = null;

    public SupervisionCourseAndDetailQueryParam isNeedFilterPoliticalCourse(Boolean isNeedFilterPoliticalCourse) {
        this.isNeedFilterPoliticalCourse = isNeedFilterPoliticalCourse;
        return this;
    }

    /**
     * 是否需要过思政课
     *
     * @return isNeedFilterPoliticalCourse
     **/
    @ApiModelProperty(value = "是否需要过思政课")


    public Boolean isIsNeedFilterPoliticalCourse() {
        return isNeedFilterPoliticalCourse;
    }

    public void setIsNeedFilterPoliticalCourse(Boolean isNeedFilterPoliticalCourse) {
        this.isNeedFilterPoliticalCourse = isNeedFilterPoliticalCourse;
    }

    public String getCollegeIds() {
        return collegeIds;
    }

    public void setCollegeIds(String collegeIds) {
        this.collegeIds = collegeIds;
    }

    public SupervisionCourseAndDetailQueryParam schoolYear(String schoolYear) {
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

    public SupervisionCourseAndDetailQueryParam term(Integer term) {
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

    public SupervisionCourseAndDetailQueryParam collegeType(Integer collegeType) {
        this.collegeType = collegeType;
        return this;
    }

    /**
     * 学院类型（0：授课教师学院；1：开课学院）
     *
     * @return collegeType
     **/
    @ApiModelProperty(value = "学院类型（0：授课教师学院；1：开课学院）")


    public Integer getCollegeType() {
        return collegeType;
    }

    public void setCollegeType(Integer collegeType) {
        this.collegeType = collegeType;
    }

    public SupervisionCourseAndDetailQueryParam collegeId(String collegeId) {
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

    public SupervisionCourseAndDetailQueryParam nowTime(String nowTime) {
        this.nowTime = nowTime;
        return this;
    }

    /**
     * 现在的时间（yyyy-MM-hh HH:mm:ss）
     *
     * @return nowTime
     **/
    @ApiModelProperty(value = "现在的时间（yyyy-MM-hh HH:mm:ss）")


    public String getNowTime() {
        return nowTime;
    }

    public void setNowTime(String nowTime) {
        this.nowTime = nowTime;
    }

    public SupervisionCourseAndDetailQueryParam courseId(String courseId) {
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

    public SupervisionCourseAndDetailQueryParam teachers(String teachers) {
        this.teachers = teachers;
        return this;
    }

    /**
     * 老师ids
     *
     * @return teachers
     **/
    @ApiModelProperty(value = "老师ids")


    public String getTeachers() {
        return teachers;
    }

    public void setTeachers(String teachers) {
        this.teachers = teachers;
    }

    public SupervisionCourseAndDetailQueryParam studentType(String studentType) {
        this.studentType = studentType;
        return this;
    }

    /**
     * 开课类型
     *
     * @return studentType
     **/
    @ApiModelProperty(value = "开课类型")


    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SupervisionCourseAndDetailQueryParam supervisionCourseAndDetailQueryParam = (SupervisionCourseAndDetailQueryParam) o;
        return Objects.equals(this.schoolYear, supervisionCourseAndDetailQueryParam.schoolYear) &&
                Objects.equals(this.term, supervisionCourseAndDetailQueryParam.term) &&
                Objects.equals(this.collegeType, supervisionCourseAndDetailQueryParam.collegeType) &&
                Objects.equals(this.collegeId, supervisionCourseAndDetailQueryParam.collegeId) &&
                Objects.equals(this.nowTime, supervisionCourseAndDetailQueryParam.nowTime) &&
                Objects.equals(this.courseId, supervisionCourseAndDetailQueryParam.courseId) &&
                Objects.equals(this.teachers, supervisionCourseAndDetailQueryParam.teachers) &&
                Objects.equals(this.studentType, supervisionCourseAndDetailQueryParam.studentType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schoolYear, term, collegeType, collegeId, nowTime, courseId, teachers, studentType);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SupervisionCourseAndDetailQueryParam {\n");

        sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
        sb.append("    term: ").append(toIndentedString(term)).append("\n");
        sb.append("    collegeType: ").append(toIndentedString(collegeType)).append("\n");
        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
        sb.append("    nowTime: ").append(toIndentedString(nowTime)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    teachers: ").append(toIndentedString(teachers)).append("\n");
        sb.append("    studentType: ").append(toIndentedString(studentType)).append("\n");
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

