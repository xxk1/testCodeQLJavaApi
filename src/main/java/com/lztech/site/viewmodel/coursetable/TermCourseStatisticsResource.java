package com.lztech.site.viewmodel.coursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * TermCourseStatisticsResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-03-24T07:43:07.695Z")


public class TermCourseStatisticsResource {
    @JsonProperty("termId")
    private String termId = null;

    @JsonProperty("term")
    private Integer term = null;

    @JsonProperty("schoolYear")
    private String schoolYear = null;

    @JsonProperty("description")
    private String description = null;

    @JsonProperty("statisticsNumber")
    private Integer statisticsNumber = null;

    @JsonProperty("whetherCurrentTerm")
    private Integer whetherCurrentTerm = null;

    public TermCourseStatisticsResource termId(String termId) {
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

    public TermCourseStatisticsResource term(Integer term) {
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

    public TermCourseStatisticsResource schoolYear(String schoolYear) {
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

    public TermCourseStatisticsResource description(String description) {
        this.description = description;
        return this;
    }

    /**
     * 学期介绍
     *
     * @return description
     **/
    @ApiModelProperty(value = "学期介绍")


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TermCourseStatisticsResource statisticsNumber(Integer statisticsNumber) {
        this.statisticsNumber = statisticsNumber;
        return this;
    }

    /**
     * 数量
     *
     * @return statisticsNumber
     **/
    @ApiModelProperty(value = "数量")


    public Integer getStatisticsNumber() {
        return statisticsNumber;
    }

    public void setStatisticsNumber(Integer statisticsNumber) {
        this.statisticsNumber = statisticsNumber;
    }

    public TermCourseStatisticsResource whetherCurrentTerm(Integer whetherCurrentTerm) {
        this.whetherCurrentTerm = whetherCurrentTerm;
        return this;
    }

    /**
     * 是否为本学期
     *
     * @return whetherCurrentTerm
     **/
    @ApiModelProperty(value = "是否为本学期")


    public Integer getWhetherCurrentTerm() {
        return whetherCurrentTerm;
    }

    public void setWhetherCurrentTerm(Integer whetherCurrentTerm) {
        this.whetherCurrentTerm = whetherCurrentTerm;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TermCourseStatisticsResource termCourseStatisticsResource = (TermCourseStatisticsResource) o;
        return Objects.equals(this.termId, termCourseStatisticsResource.termId) &&
                Objects.equals(this.term, termCourseStatisticsResource.term) &&
                Objects.equals(this.schoolYear, termCourseStatisticsResource.schoolYear) &&
                Objects.equals(this.description, termCourseStatisticsResource.description) &&
                Objects.equals(this.statisticsNumber, termCourseStatisticsResource.statisticsNumber) &&
                Objects.equals(this.whetherCurrentTerm, termCourseStatisticsResource.whetherCurrentTerm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(termId, term, schoolYear,description, statisticsNumber, whetherCurrentTerm);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TermCourseStatisticsResource {\n");

        sb.append("    termId: ").append(toIndentedString(termId)).append("\n");
        sb.append("    term: ").append(toIndentedString(term)).append("\n");
        sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    statisticsNumber: ").append(toIndentedString(statisticsNumber)).append("\n");
        sb.append("    whetherCurrentTerm: ").append(toIndentedString(whetherCurrentTerm)).append("\n");
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

