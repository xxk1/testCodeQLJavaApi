package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseTableTerm
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-08-30T03:36:18.420Z")

public class CourseTableTerm {

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

    public CourseTableTerm id(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     * @return id
     **/
    @ApiModelProperty(value = "")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public CourseTableTerm schoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
        return this;
    }

    /**
     * Get schoolYear
     * @return 学年
     **/
    @ApiModelProperty(value = "学年")

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }
    public CourseTableTerm term(String term) {
        this.term = term;
        return this;
    }

    /**
     * Get term
     * @return 学期
     **/
    @ApiModelProperty(value = "学期")

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
    public CourseTableTerm startDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * Get startDate
     * @return 开始日期
     **/
    @ApiModelProperty(value = "开始日期")

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public CourseTableTerm endDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * Get endDate
     * @return 结束日期
     **/
    @ApiModelProperty(value = "结束日期")

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public CourseTableTerm description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get endDate
     * @return 学期描述
     **/
    @ApiModelProperty(value = "结束日期")

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CourseTableTerm schoolYearTermNickName(String schoolYearTermNickName) {
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
        CourseTableTerm courseTableTerm = (CourseTableTerm) o;
        return Objects.equals(this.id, courseTableTerm.id) &&
                Objects.equals(this.schoolYear, courseTableTerm.schoolYear) &&
                Objects.equals(this.term, courseTableTerm.term) &&
                Objects.equals(this.startDate, courseTableTerm.startDate) &&
                Objects.equals(this.endDate, courseTableTerm.endDate) &&
                Objects.equals(this.description, courseTableTerm.description) &&
                Objects.equals(this.schoolYearTermNickName, courseTableTerm.schoolYearTermNickName) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, schoolYear, term, startDate, endDate, description,schoolYearTermNickName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTableTerm {\n");

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
