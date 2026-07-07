package com.lztech.site.resource.term;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * TermResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-09T01:56:02.230Z")

public class TermResource {
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

    @JsonProperty("schoolYearTermNickName")
    private String schoolYearTermNickName = null;



    public TermResource id(String id) {
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

    public TermResource schoolYear(String schoolYear) {
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

    public TermResource term(String term) {
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

    public TermResource startDate(String startDate) {
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

    public TermResource endDate(String endDate) {
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

    public TermResource description(String description) {
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

    public TermResource schoolYearTermNickName(String schoolYearTermNickName) {
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
        TermResource termResource = (TermResource) o;
        return Objects.equals(this.id, termResource.id) &&
                Objects.equals(this.schoolYear, termResource.schoolYear) &&
                Objects.equals(this.term, termResource.term) &&
                Objects.equals(this.startDate, termResource.startDate) &&
                Objects.equals(this.endDate, termResource.endDate) &&
                Objects.equals(this.description, termResource.description) &&
                Objects.equals(this.schoolYearTermNickName, termResource.schoolYearTermNickName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, schoolYear, term, startDate, endDate, description,schoolYearTermNickName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TermResource {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
        sb.append("    term: ").append(toIndentedString(term)).append("\n");
        sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
        sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
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

