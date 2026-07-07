package com.lztech.site.viewmodel.studentcoursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * StudentCourseTableDetailParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-04-29T14:36:17.223+08:00")

public class StudentCourseTableDetailParam {
    @JsonProperty("schoolYear")
    private String schoolYear = null;

    @JsonProperty("term")
    private Integer term = null;

    @JsonProperty("type")
    private Integer type = null;

    public StudentCourseTableDetailParam schoolYear(String schoolYear) {
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

    public StudentCourseTableDetailParam term(Integer term) {
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

    public StudentCourseTableDetailParam type(Integer type) {
        this.type = type;
        return this;
    }

    /**
     * 类型 0:全部；1：未上课的
     *
     * @return type
     **/
    @ApiModelProperty(value = "类型 0:全部；1：未上课的")


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StudentCourseTableDetailParam studentCourseTableDetailParam = (StudentCourseTableDetailParam) o;
        return Objects.equals(this.schoolYear, studentCourseTableDetailParam.schoolYear) &&
                Objects.equals(this.term, studentCourseTableDetailParam.term) &&
                Objects.equals(this.type, studentCourseTableDetailParam.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schoolYear, term, type);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class StudentCourseTableDetailParam {\n");

        sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
        sb.append("    term: ").append(toIndentedString(term)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

