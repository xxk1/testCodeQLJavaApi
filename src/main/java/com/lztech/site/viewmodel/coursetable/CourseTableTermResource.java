package com.lztech.site.viewmodel.coursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseTableTermResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-09-03T02:06:48.455Z")


public class CourseTableTermResource {
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

    public CourseTableTermResource id(String id) {
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

    public CourseTableTermResource schoolYear(String schoolYear) {
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

    public CourseTableTermResource term(String term) {
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

    public CourseTableTermResource startDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * 开始日期 (格式:yyyy-MM-dd)
     *
     * @return startDate
     **/
    @ApiModelProperty(value = "开始日期 (格式:yyyy-MM-dd)")


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public CourseTableTermResource endDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * 结束日期 (格式:yyyy-MM-dd)
     *
     * @return endDate
     **/
    @ApiModelProperty(value = "结束日期 (格式:yyyy-MM-dd)")


    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public CourseTableTermResource description(String description) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseTableTermResource courseTableTermResource = (CourseTableTermResource) o;
        return Objects.equals(this.id, courseTableTermResource.id) &&
                Objects.equals(this.schoolYear, courseTableTermResource.schoolYear) &&
                Objects.equals(this.term, courseTableTermResource.term) &&
                Objects.equals(this.startDate, courseTableTermResource.startDate) &&
                Objects.equals(this.endDate, courseTableTermResource.endDate) &&
                Objects.equals(this.description, courseTableTermResource.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, schoolYear, term, startDate, endDate, description);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTableTermResource {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
        sb.append("    term: ").append(toIndentedString(term)).append("\n");
        sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
        sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
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

