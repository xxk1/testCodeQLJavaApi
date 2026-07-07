package com.lztech.site.viewmodel.teachingreport;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CollegeTeacherResourceStatisticsRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-01-03T03:12:14.631Z")


public class CollegeTeacherResourceStatisticsRequest {
    @JsonProperty("schoolYear")
    private String schoolYear = null;

    @JsonProperty("term")
    private Integer term = null;

    @JsonProperty("startDate")
    private String startDate = null;

    @JsonProperty("endDate")
    private String endDate = null;

    public CollegeTeacherResourceStatisticsRequest schoolYear(String schoolYear) {
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

    public CollegeTeacherResourceStatisticsRequest term(Integer term) {
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

    public CollegeTeacherResourceStatisticsRequest startDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * 开始日期（格式：yyyy-MM-dd）
     *
     * @return startDate
     **/
    @ApiModelProperty(value = "开始日期（格式：yyyy-MM-dd）")


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public CollegeTeacherResourceStatisticsRequest endDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * 结束日期（格式：yyyy-MM-dd）
     *
     * @return endDate
     **/
    @ApiModelProperty(value = "结束日期（格式：yyyy-MM-dd）")


    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CollegeTeacherResourceStatisticsRequest collegeTeacherResourceStatisticsRequest = (CollegeTeacherResourceStatisticsRequest) o;
        return Objects.equals(this.schoolYear, collegeTeacherResourceStatisticsRequest.schoolYear)
                && Objects.equals(this.term, collegeTeacherResourceStatisticsRequest.term)
                && Objects.equals(this.startDate, collegeTeacherResourceStatisticsRequest.startDate)
                && Objects.equals(this.endDate, collegeTeacherResourceStatisticsRequest.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schoolYear, term, startDate, endDate);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CollegeTeacherResourceStatisticsRequest {\n");

        sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
        sb.append("    term: ").append(toIndentedString(term)).append("\n");
        sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
        sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
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

