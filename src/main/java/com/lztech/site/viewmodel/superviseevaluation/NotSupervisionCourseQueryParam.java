package com.lztech.site.viewmodel.superviseevaluation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * NotSupervisionCourseQueryParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-04-11T01:53:42.349Z")


public class NotSupervisionCourseQueryParam {
    @JsonProperty("supervisionCourseIdList")
    @Valid
    private List<String> supervisionCourseIdList = null;

    @JsonProperty("schoolYear")
    private String schoolYear = null;

    @JsonProperty("term")
    private String term = null;

    @JsonProperty("isDistinguishCourseStudentType")
    private String isDistinguishCourseStudentType = null;

    @JsonProperty("courseStudentTypes")
    private String courseStudentTypes = null;

    @JsonProperty("isNeedFilterPoliticalCourse")
    private Boolean isNeedFilterPoliticalCourse = null;

    @JsonProperty("superviseCollegeType")
    private Integer superviseCollegeType = null;

    @JsonProperty("queryNum")
    private Integer queryNum = null;

    @JsonProperty("collegeIds")
    private String collegeIds;

    public String getCollegeIds() {
        return collegeIds;
    }

    public void setCollegeIds(String collegeIds) {
        this.collegeIds = collegeIds;
    }

    public Integer getQueryNum() {
        return queryNum;
    }

    public void setQueryNum(Integer queryNum) {
        this.queryNum = queryNum;
    }

    public NotSupervisionCourseQueryParam supervisionCourseIdList(List<String> supervisionCourseIdList) {
        this.supervisionCourseIdList = supervisionCourseIdList;
        return this;
    }

    public NotSupervisionCourseQueryParam addSupervisionCourseIdListItem(String supervisionCourseIdListItem) {
        if (this.supervisionCourseIdList == null) {
            this.supervisionCourseIdList = new ArrayList<String>();
        }
        this.supervisionCourseIdList.add(supervisionCourseIdListItem);
        return this;
    }

    /**
     * Get supervisionCourseIdList
     *
     * @return supervisionCourseIdList
     **/
    @ApiModelProperty(value = "")


    public List<String> getSupervisionCourseIdList() {
        return supervisionCourseIdList;
    }

    public void setSupervisionCourseIdList(List<String> supervisionCourseIdList) {
        this.supervisionCourseIdList = supervisionCourseIdList;
    }

    public NotSupervisionCourseQueryParam schoolYear(String schoolYear) {
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

    public NotSupervisionCourseQueryParam term(String term) {
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

    public NotSupervisionCourseQueryParam isDistinguishCourseStudentType(String isDistinguishCourseStudentType) {
        this.isDistinguishCourseStudentType = isDistinguishCourseStudentType;
        return this;
    }

    /**
     * 是否需要区分授课学生类型
     *
     * @return isDistinguishCourseStudentType
     **/
    @ApiModelProperty(value = "是否需要区分授课学生类型")


    public String getIsDistinguishCourseStudentType() {
        return isDistinguishCourseStudentType;
    }

    public void setIsDistinguishCourseStudentType(String isDistinguishCourseStudentType) {
        this.isDistinguishCourseStudentType = isDistinguishCourseStudentType;
    }

    public NotSupervisionCourseQueryParam courseStudentTypes(String courseStudentTypes) {
        this.courseStudentTypes = courseStudentTypes;
        return this;
    }

    /**
     * 授课学生类型，多个使用逗号隔开
     *
     * @return courseStudentTypes
     **/
    @ApiModelProperty(value = "授课学生类型，多个使用逗号隔开")


    public String getCourseStudentTypes() {
        return courseStudentTypes;
    }

    public void setCourseStudentTypes(String courseStudentTypes) {
        this.courseStudentTypes = courseStudentTypes;
    }

    public NotSupervisionCourseQueryParam isNeedFilterPoliticalCourse(Boolean isNeedFilterPoliticalCourse) {
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

    public NotSupervisionCourseQueryParam superviseCollegeType(Integer superviseCollegeType) {
        this.superviseCollegeType = superviseCollegeType;
        return this;
    }

    /**
     * 督导听课查询学类型 0：授课教师学院；1：开课学院
     *
     * @return superviseCollegeType
     **/
    @ApiModelProperty(value = "督导听课查询学类型 0：授课教师学院；1：开课学院")


    public Integer getSuperviseCollegeType() {
        return superviseCollegeType;
    }

    public void setSuperviseCollegeType(Integer superviseCollegeType) {
        this.superviseCollegeType = superviseCollegeType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NotSupervisionCourseQueryParam notSupervisionCourseQueryParam = (NotSupervisionCourseQueryParam) o;
        return Objects.equals(this.supervisionCourseIdList, notSupervisionCourseQueryParam.supervisionCourseIdList) &&
                Objects.equals(this.schoolYear, notSupervisionCourseQueryParam.schoolYear) &&
                Objects.equals(this.term, notSupervisionCourseQueryParam.term) &&
                Objects.equals(this.isDistinguishCourseStudentType, notSupervisionCourseQueryParam.isDistinguishCourseStudentType) &&
                Objects.equals(this.courseStudentTypes, notSupervisionCourseQueryParam.courseStudentTypes) &&
                Objects.equals(this.isNeedFilterPoliticalCourse, notSupervisionCourseQueryParam.isNeedFilterPoliticalCourse) &&
                Objects.equals(this.superviseCollegeType, notSupervisionCourseQueryParam.superviseCollegeType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supervisionCourseIdList, schoolYear, term, isDistinguishCourseStudentType, courseStudentTypes,
                isNeedFilterPoliticalCourse, superviseCollegeType);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class NotSupervisionCourseQueryParam {\n");

        sb.append("    supervisionCourseIdList: ").append(toIndentedString(supervisionCourseIdList)).append("\n");
        sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
        sb.append("    term: ").append(toIndentedString(term)).append("\n");
        sb.append("    isDistinguishCourseStudentType: ").append(toIndentedString(isDistinguishCourseStudentType)).append("\n");
        sb.append("    courseStudentTypes: ").append(toIndentedString(courseStudentTypes)).append("\n");
        sb.append("    isNeedFilterPoliticalCourse: ").append(toIndentedString(isNeedFilterPoliticalCourse)).append("\n");
        sb.append("    superviseCollegeType: ").append(toIndentedString(superviseCollegeType)).append("\n");
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

