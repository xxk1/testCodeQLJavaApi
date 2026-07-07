package com.lztech.site.resource.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseTableDetailVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-08-21T09:50:33.716Z")


public class CourseTableDetailVo {
    @JsonProperty("pageCount")
    private Integer pageCount = null;

    @JsonProperty("total")
    private Integer total = null;

    @JsonProperty("page")
    private Integer page = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    @JsonProperty("courseTableDetails")
    @Valid
    private List<CourseTableDetailVoResource> courseTableDetails = null;

    public CourseTableDetailVo pageCount(Integer pageCount) {
        this.pageCount = pageCount;
        return this;
    }

    /**
     * 总页数
     *
     * @return pageCount
     **/
    @ApiModelProperty(value = "总页数")


    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public CourseTableDetailVo total(Integer total) {
        this.total = total;
        return this;
    }

    /**
     * 总条数
     *
     * @return total
     **/
    @ApiModelProperty(example = "10", value = "总条数")


    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public CourseTableDetailVo page(Integer page) {
        this.page = page;
        return this;
    }

    /**
     * 当前页
     *
     * @return page
     **/
    @ApiModelProperty(example = "10", value = "当前页")


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public CourseTableDetailVo pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * 每页个数
     *
     * @return pageSize
     **/
    @ApiModelProperty(example = "10", value = "每页个数")


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public CourseTableDetailVo courseTableDetails(List<CourseTableDetailVoResource> courseTableDetails) {
        this.courseTableDetails = courseTableDetails;
        return this;
    }

    public CourseTableDetailVo addCourseTableDetailsItem(CourseTableDetailVoResource courseTableDetailsItem) {
        if (this.courseTableDetails == null) {
            this.courseTableDetails = new ArrayList<CourseTableDetailVoResource>();
        }
        this.courseTableDetails.add(courseTableDetailsItem);
        return this;
    }

    /**
     * Get courseTableDetails
     *
     * @return courseTableDetails
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<CourseTableDetailVoResource> getCourseTableDetails() {
        return courseTableDetails;
    }

    public void setCourseTableDetails(List<CourseTableDetailVoResource> courseTableDetails) {
        this.courseTableDetails = courseTableDetails;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseTableDetailVo courseTableDetailVo = (CourseTableDetailVo) o;
        return Objects.equals(this.pageCount, courseTableDetailVo.pageCount) &&
                Objects.equals(this.total, courseTableDetailVo.total) &&
                Objects.equals(this.page, courseTableDetailVo.page) &&
                Objects.equals(this.pageSize, courseTableDetailVo.pageSize) &&
                Objects.equals(this.courseTableDetails, courseTableDetailVo.courseTableDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageCount, total, page, pageSize, courseTableDetails);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTableDetailVo {\n");

        sb.append("    pageCount: ").append(toIndentedString(pageCount)).append("\n");
        sb.append("    total: ").append(toIndentedString(total)).append("\n");
        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    courseTableDetails: ").append(toIndentedString(courseTableDetails)).append("\n");
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

