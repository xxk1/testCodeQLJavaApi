package com.lztech.site.resource.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * TeachingCalendarResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-10-19T11:46:31.033Z")


public class TeachingCalendarResource {
    @JsonProperty("courseDate")
    private String courseDate = null;

    @JsonProperty("hasCourseTable")
    private Boolean hasCourseTable = null;

    public TeachingCalendarResource courseDate(String courseDate) {
        this.courseDate = courseDate;
        return this;
    }

    /**
     * 上课日期
     *
     * @return courseDate
     **/
    @ApiModelProperty(value = "上课日期")


    public String getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(String courseDate) {
        this.courseDate = courseDate;
    }

    public TeachingCalendarResource hasCourseTable(Boolean hasCourseTable) {
        this.hasCourseTable = hasCourseTable;
        return this;
    }

    /**
     * 是否有课表
     *
     * @return hasCourseTable
     **/
    @ApiModelProperty(value = "是否有课表")


    public Boolean isHasCourseTable() {
        return hasCourseTable;
    }

    public void setHasCourseTable(Boolean hasCourseTable) {
        this.hasCourseTable = hasCourseTable;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeachingCalendarResource teachingCalendarResource = (TeachingCalendarResource) o;
        return Objects.equals(this.courseDate, teachingCalendarResource.courseDate) &&
                Objects.equals(this.hasCourseTable, teachingCalendarResource.hasCourseTable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseDate, hasCourseTable);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TeachingCalendarResource {\n");

        sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
        sb.append("    hasCourseTable: ").append(toIndentedString(hasCourseTable)).append("\n");
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

