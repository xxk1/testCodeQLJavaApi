package com.lztech.site.resource.coursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseSegmentResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-18T07:59:30.969Z")

public class CourseSegmentResource {

    @JsonProperty("pageCount")
    private Integer pageCount = null;

    @JsonProperty("noData")
    private Boolean noData = null;

    @JsonProperty("courseTables")
    @Valid
    private List<CourseTableDetailsResource> courseTables = null;

    public CourseSegmentResource pageCount(Integer pageCount) {
        this.pageCount = pageCount;
        return this;
    }

    /**
     * pageCount
     *
     * @return pageCount
     **/
    @ApiModelProperty(value = "pageCount")


    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public CourseSegmentResource noData(Boolean noData) {
        this.noData = noData;
        return this;
    }

    /**
     * true false
     *
     * @return noData
     **/
    @ApiModelProperty(value = "true false")


    public Boolean isNoData() {
        return noData;
    }

    public void setNoData(Boolean noData) {
        this.noData = noData;
    }

    public CourseSegmentResource courseTables(List<CourseTableDetailsResource> courseTables) {
        this.courseTables = courseTables;
        return this;
    }

    public CourseSegmentResource addCourseTablesItem(CourseTableDetailsResource courseTablesItem) {
        if (this.courseTables == null) {
            this.courseTables = new ArrayList<CourseTableDetailsResource>();
        }
        this.courseTables.add(courseTablesItem);
        return this;
    }

    /**
     * Get courseTables
     *
     * @return courseTables
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<CourseTableDetailsResource> getCourseTables() {
        return courseTables;
    }

    public void setCourseTables(List<CourseTableDetailsResource> courseTables) {
        this.courseTables = courseTables;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseSegmentResource courseSegmentResource = (CourseSegmentResource) o;
        return Objects.equals(this.pageCount, courseSegmentResource.pageCount) &&
                Objects.equals(this.noData, courseSegmentResource.noData) &&
                Objects.equals(this.courseTables, courseSegmentResource.courseTables);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageCount, noData, courseTables);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseSegmentResource {\n");

        sb.append("    pageCount: ").append(toIndentedString(pageCount)).append("\n");
        sb.append("    noData: ").append(toIndentedString(noData)).append("\n");
        sb.append("    courseTables: ").append(toIndentedString(courseTables)).append("\n");
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

