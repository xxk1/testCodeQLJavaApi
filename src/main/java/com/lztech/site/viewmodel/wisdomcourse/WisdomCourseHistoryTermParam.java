package com.lztech.site.viewmodel.wisdomcourse;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * WisdomCourseHistoryTermParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-08-13T15:53:10.736+08:00")

public class WisdomCourseHistoryTermParam {
    @JsonProperty("termId")
    private String termId = null;

    @JsonProperty("schoolYear")
    private String schoolYear = null;

    @JsonProperty("term")
    private Integer term = null;

    public WisdomCourseHistoryTermParam termId(String termId) {
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

    public WisdomCourseHistoryTermParam schoolYear(String schoolYear) {
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

    public WisdomCourseHistoryTermParam term(Integer term) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WisdomCourseHistoryTermParam wisdomCourseHistoryTermParam = (WisdomCourseHistoryTermParam) o;
        return Objects.equals(this.termId, wisdomCourseHistoryTermParam.termId) &&
                Objects.equals(this.schoolYear, wisdomCourseHistoryTermParam.schoolYear) &&
                Objects.equals(this.term, wisdomCourseHistoryTermParam.term);
    }

    @Override
    public int hashCode() {
        return Objects.hash(termId, schoolYear, term);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class WisdomCourseHistoryTermParam {\n");

        sb.append("    termId: ").append(toIndentedString(termId)).append("\n");
        sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
        sb.append("    term: ").append(toIndentedString(term)).append("\n");
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

