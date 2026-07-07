package com.lztech.site.viewmodel.term;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * TermVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-11-04T12:12:35.211Z")

public class TermVo {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("schoolYear")
    private String schoolYear = null;

    @JsonProperty("term")
    private String term = null;

    @JsonProperty("startDate")
    private String startDate = null;

    @JsonProperty("endDate")
    private String endDate = null;

    @JsonProperty("description")
    private String description = null;

    @JsonProperty("termType")
    private Integer termType = null;

    @JsonProperty("schoolYearTermNickName")
    private String schoolYearTermNickName = null;

    public TermVo id(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     *
     * @return id
     **/
    @ApiModelProperty(value = "")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TermVo schoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
        return this;
    }

    /**
     * 学年: yyyy-yyyy
     *
     * @return schoolYear
     **/
    @ApiModelProperty(value = "学年: yyyy-yyyy")


    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public TermVo term(String term) {
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

    public TermVo startDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * 开始日期
     *
     * @return startDate
     **/
    @ApiModelProperty(value = "开始日期")


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public TermVo endDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * 结束日期
     *
     * @return endDate
     **/
    @ApiModelProperty(value = "结束日期")


    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public TermVo description(String description) {
        this.description = description;
        return this;
    }

    /**
     * 学期描述
     *
     * @return description
     **/
    @ApiModelProperty(value = "学期描述")


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TermVo termType(Integer termType) {
        this.termType = termType;
        return this;
    }

    /**
     * 学期类型 0：之前学期；1：当前学期；2：之后学期
     *
     * @return termType
     **/
    @ApiModelProperty(value = "学期类型 0：之前学期；1：当前学期；2：之后学期")


    public Integer getTermType() {
        return termType;
    }

    public void setTermType(Integer termType) {
        this.termType = termType;
    }

    public TermVo schoolYearTermNickName(String schoolYearTermNickName) {
        this.schoolYearTermNickName = schoolYearTermNickName;
        return this;
    }

    /**
     * 学年学期昵称
     *
     * @return schoolYearTermNickName
     **/
    @ApiModelProperty(value = "学年学期昵称")


    public String getSchoolYearTermNickName() {
        return schoolYearTermNickName;
    }

    public void setSchoolYearTermNickName(String schoolYearTermNickName) {
        this.schoolYearTermNickName = schoolYearTermNickName;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TermVo termVo = (TermVo) o;
        return Objects.equals(this.id, termVo.id) &&
                Objects.equals(this.schoolYear, termVo.schoolYear) &&
                Objects.equals(this.term, termVo.term) &&
                Objects.equals(this.startDate, termVo.startDate) &&
                Objects.equals(this.endDate, termVo.endDate) &&
                Objects.equals(this.description, termVo.description) &&
                Objects.equals(this.termType, termVo.termType) &&
                Objects.equals(this.schoolYearTermNickName, termVo.schoolYearTermNickName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, schoolYear, term, startDate, endDate, description, termType,schoolYearTermNickName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TermVo {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
        sb.append("    term: ").append(toIndentedString(term)).append("\n");
        sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
        sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    termType: ").append(toIndentedString(termType)).append("\n");
        sb.append("    schoolYearTermNickName: ").append(toIndentedString(schoolYearTermNickName)).append("\n");
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

