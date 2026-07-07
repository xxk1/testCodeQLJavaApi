package com.lztech.site.resource.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ClassYear
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-16T06:43:20.140Z")

public class ClassYear {
    @JsonProperty("year")
    private String year = null;

    @JsonProperty("checked")
    private Boolean checked = null;

    public ClassYear year(String year) {
        this.year = year;
        return this;
    }

    /**
     * 年级名称
     *
     * @return year
     **/
    @ApiModelProperty(value = "年级名称")


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ClassYear checked(Boolean checked) {
        this.checked = checked;
        return this;
    }

    /**
     * 是否选择
     *
     * @return checked
     **/
    @ApiModelProperty(value = "是否选择")


    public Boolean isChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClassYear classYear = (ClassYear) o;
        return Objects.equals(this.year, classYear.year) &&
                Objects.equals(this.checked, classYear.checked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, checked);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ClassYear {\n");

        sb.append("    year: ").append(toIndentedString(year)).append("\n");
        sb.append("    checked: ").append(toIndentedString(checked)).append("\n");
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

