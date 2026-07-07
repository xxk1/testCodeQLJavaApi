package com.lztech.site.resource.term;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * TermModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-04-29T02:54:23.204Z")


public class TermModel {
    @JsonProperty("schoolYear")
    private String schoolYear = null;

    @JsonProperty("term")
    private String term = null;

    @JsonProperty("termName")
    private String termName = null;

    @JsonProperty("startDate")
    private String startDate = null;

    @JsonProperty("endDate")
    private String endDate = null;

    @JsonProperty("whetherCurrentTerm")
    private String whetherCurrentTerm = null;
    @JsonProperty("schoolYearTermNickName")
    private String schoolYearTermNickName = null;

    public TermModel schoolYearTermNickName(String schoolYearTermNickName) {
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



    public TermModel schoolYear(String schoolYear) {
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

    public TermModel term(String term) {
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

    public TermModel termName(String termName) {
        this.termName = termName;
        return this;
    }

    /**
     * 学期名称: yyyy-yyyy 第几学期
     *
     * @return schoolYear
     **/
    @ApiModelProperty(value = "学期名称: yyyy-yyyy 第几学期")


    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }


    public TermModel startDate(String startDate) {
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

    public TermModel endDate(String endDate) {
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

    public TermModel whetherCurrentTerm(String whetherCurrentTerm) {
        this.whetherCurrentTerm = whetherCurrentTerm;
        return this;
    }

    /**
     * 是否为本学期
     *
     * @return whetherCurrentTerm
     **/
    @ApiModelProperty(value = "是否为本学期")


    public String getWhetherCurrentTerm() {
        return whetherCurrentTerm;
    }

    public void setWhetherCurrentTerm(String whetherCurrentTerm) {
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
        TermModel termModel = (TermModel) o;
        return Objects.equals(this.schoolYear, termModel.schoolYear) &&
                Objects.equals(this.term, termModel.term) &&
                Objects.equals(this.schoolYearTermNickName, termModel.schoolYearTermNickName) &&
                Objects.equals(this.termName, termModel.termName) &&
                Objects.equals(this.startDate, termModel.startDate) &&
                Objects.equals(this.endDate, termModel.endDate) &&
                Objects.equals(this.whetherCurrentTerm, termModel.whetherCurrentTerm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schoolYear, term, schoolYearTermNickName, startDate, endDate, whetherCurrentTerm);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TermModel {\n");

        sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
        sb.append("    term: ").append(toIndentedString(term)).append("\n");
        sb.append("    termName: ").append(toIndentedString(termName)).append("\n");
        sb.append("    schoolYearTermNickName: ").append(toIndentedString(schoolYearTermNickName)).append("\n");
        sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
        sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
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

