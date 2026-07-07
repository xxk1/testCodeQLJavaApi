package com.lztech.site.viewmodel.superviseevaluation.v2;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * SupervisionCourseTableDetialQueryParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-08-06T16:30:45.421+08:00")

public class SupervisionCourseTableDetialQueryParam {
    @JsonProperty("schoolYear")
    private String schoolYear = null;

    @JsonProperty("term")
    private Integer term = null;

    @JsonProperty("collegeType")
    private Integer collegeType = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("studentType")
    private String studentType = null;

    @JsonProperty("nowTime")
    private String nowTime = null;

    @JsonProperty("collegeIds")
    private String collegeIds = null;

    @JsonProperty("sourceDataSource")
    private String sourceDataSource = null;

    @JsonProperty("courseTableDetails")
    @Valid
    private List<SupervisionCourseTableDetailVo> courseTableDetails = null;

    @JsonProperty("evaluationStatusType")
    private Integer evaluationStatusType;

    @JsonProperty("live")
    private Integer live = null;

    @JsonProperty("isDistinguishCourseStudentType")
    private String isDistinguishCourseStudentType = null;

    @JsonProperty("courseStudentTypes")
    private String courseStudentTypes = null;

    @JsonProperty("courseNameOrTeacherName")
    private String courseNameOrTeacherName;

    @JsonProperty("isNeedFilterPoliticalCourse")
    private Boolean isNeedFilterPoliticalCourse = null;

    public SupervisionCourseTableDetialQueryParam isNeedFilterPoliticalCourse(Boolean isNeedFilterPoliticalCourse) {
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


    public String getCourseNameOrTeacherName() {
        return courseNameOrTeacherName;
    }

    public void setCourseNameOrTeacherName(String courseNameOrTeacherName) {
        this.courseNameOrTeacherName = courseNameOrTeacherName;
    }

    public Integer getEvaluationStatusType() {
        return evaluationStatusType;
    }

    public void setEvaluationStatusType(Integer evaluationStatusType) {
        this.evaluationStatusType = evaluationStatusType;
    }

    public Integer getLive() {
        return live;
    }

    public void setLive(Integer live) {
        this.live = live;
    }

    public String getIsDistinguishCourseStudentType() {
        return isDistinguishCourseStudentType;
    }

    public void setIsDistinguishCourseStudentType(String isDistinguishCourseStudentType) {
        this.isDistinguishCourseStudentType = isDistinguishCourseStudentType;
    }

    public String getCourseStudentTypes() {
        return courseStudentTypes;
    }

    public void setCourseStudentTypes(String courseStudentTypes) {
        this.courseStudentTypes = courseStudentTypes;
    }

    public SupervisionCourseTableDetialQueryParam schoolYear(String schoolYear) {
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

    public SupervisionCourseTableDetialQueryParam term(Integer term) {
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

    public SupervisionCourseTableDetialQueryParam collegeType(Integer collegeType) {
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

    public SupervisionCourseTableDetialQueryParam collegeId(String collegeId) {
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

    public SupervisionCourseTableDetialQueryParam studentType(String studentType) {
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

    public SupervisionCourseTableDetialQueryParam nowTime(String nowTime) {
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

    public SupervisionCourseTableDetialQueryParam collegeIds(String collegeIds) {
        this.collegeIds = collegeIds;
        return this;
    }

    /**
     * 学院集合
     *
     * @return collegeIds
     **/
    @ApiModelProperty(value = "学院集合")


    public String getCollegeIds() {
        return collegeIds;
    }

    public void setCollegeIds(String collegeIds) {
        this.collegeIds = collegeIds;
    }

    public SupervisionCourseTableDetialQueryParam sourceDataSource(String sourceDataSource) {
        this.sourceDataSource = sourceDataSource;
        return this;
    }

    /**
     * 数据来源
     *
     * @return sourceDataSource
     **/
    @ApiModelProperty(value = "数据来源")


    public String getSourceDataSource() {
        return sourceDataSource;
    }

    public void setSourceDataSource(String sourceDataSource) {
        this.sourceDataSource = sourceDataSource;
    }

    public SupervisionCourseTableDetialQueryParam courseTableDetails(List<SupervisionCourseTableDetailVo> courseTableDetails) {
        this.courseTableDetails = courseTableDetails;
        return this;
    }

    public SupervisionCourseTableDetialQueryParam addCourseTableDetailsItem(SupervisionCourseTableDetailVo courseTableDetailsItem) {
        if (this.courseTableDetails == null) {
            this.courseTableDetails = new ArrayList<SupervisionCourseTableDetailVo>();
        }
        this.courseTableDetails.add(courseTableDetailsItem);
        return this;
    }

    /**
     * 课表明细ids
     *
     * @return courseTableDetails
     **/
    @ApiModelProperty(value = "课表明细ids")

    @Valid

    public List<SupervisionCourseTableDetailVo> getCourseTableDetails() {
        return courseTableDetails;
    }

    public void setCourseTableDetails(List<SupervisionCourseTableDetailVo> courseTableDetails) {
        this.courseTableDetails = courseTableDetails;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SupervisionCourseTableDetialQueryParam supervisionCourseTableDetialQueryParam = (SupervisionCourseTableDetialQueryParam) o;
        return Objects.equals(this.schoolYear, supervisionCourseTableDetialQueryParam.schoolYear) &&
                Objects.equals(this.term, supervisionCourseTableDetialQueryParam.term) &&
                Objects.equals(this.collegeType, supervisionCourseTableDetialQueryParam.collegeType) &&
                Objects.equals(this.collegeId, supervisionCourseTableDetialQueryParam.collegeId) &&
                Objects.equals(this.studentType, supervisionCourseTableDetialQueryParam.studentType) &&
                Objects.equals(this.nowTime, supervisionCourseTableDetialQueryParam.nowTime) &&
                Objects.equals(this.collegeIds, supervisionCourseTableDetialQueryParam.collegeIds) &&
                Objects.equals(this.sourceDataSource, supervisionCourseTableDetialQueryParam.sourceDataSource) &&
                Objects.equals(this.courseTableDetails, supervisionCourseTableDetialQueryParam.courseTableDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schoolYear, term, collegeType, collegeId, studentType, nowTime, collegeIds, sourceDataSource, courseTableDetails);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SupervisionCourseTableDetialQueryParam {\n");

        sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
        sb.append("    term: ").append(toIndentedString(term)).append("\n");
        sb.append("    collegeType: ").append(toIndentedString(collegeType)).append("\n");
        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
        sb.append("    studentType: ").append(toIndentedString(studentType)).append("\n");
        sb.append("    nowTime: ").append(toIndentedString(nowTime)).append("\n");
        sb.append("    collegeIds: ").append(toIndentedString(collegeIds)).append("\n");
        sb.append("    sourceDataSource: ").append(toIndentedString(sourceDataSource)).append("\n");
        sb.append("    courseTableDetails: ").append(toIndentedString(courseTableDetails)).append("\n");
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

