package com.lztech.site.viewmodel.courseresourceintelligentrecommendation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseResourceIntelligentRecommendationTermResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-06-19T13:50:47.154+08:00")

public class CourseResourceIntelligentRecommendationTermResource {
    @JsonProperty("schoolYear")
    private String schoolYear = null;

    @JsonProperty("term")
    private Integer term = null;

    @JsonProperty("termName")
    private String termName = null;

    @JsonProperty("teacherList")
    @Valid
    private List<CourseResourceIntelligentRecommendationTermTeacherResource> teacherList = null;

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public CourseResourceIntelligentRecommendationTermResource schoolYear(String schoolYear) {
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

    public CourseResourceIntelligentRecommendationTermResource term(Integer term) {
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

    public CourseResourceIntelligentRecommendationTermResource teacherList(
            List<CourseResourceIntelligentRecommendationTermTeacherResource> teacherList) {
        this.teacherList = teacherList;
        return this;
    }

    public CourseResourceIntelligentRecommendationTermResource addTeacherListItem(
            CourseResourceIntelligentRecommendationTermTeacherResource teacherListItem) {
        if (this.teacherList == null) {
            this.teacherList = new ArrayList<CourseResourceIntelligentRecommendationTermTeacherResource>();
        }
        this.teacherList.add(teacherListItem);
        return this;
    }

    /**
     * Get teacherList
     *
     * @return teacherList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<CourseResourceIntelligentRecommendationTermTeacherResource> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<CourseResourceIntelligentRecommendationTermTeacherResource> teacherList) {
        this.teacherList = teacherList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseResourceIntelligentRecommendationTermResource courseResourceIntelligentRecommendationTermResource =
                (CourseResourceIntelligentRecommendationTermResource) o;
        return Objects.equals(this.schoolYear, courseResourceIntelligentRecommendationTermResource.schoolYear) &&
                Objects.equals(this.term, courseResourceIntelligentRecommendationTermResource.term) &&
                Objects.equals(this.teacherList, courseResourceIntelligentRecommendationTermResource.teacherList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schoolYear, term, teacherList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseResourceIntelligentRecommendationTermResource {\n");

        sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
        sb.append("    term: ").append(toIndentedString(term)).append("\n");
        sb.append("    teacherList: ").append(toIndentedString(teacherList)).append("\n");
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

