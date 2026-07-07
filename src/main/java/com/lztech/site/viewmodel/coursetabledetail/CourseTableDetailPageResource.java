package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lztech.site.resource.coursetabledetail.CourseTableDetailResource;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseTableDetailPageResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-08-14T12:18:53.979Z")

public class CourseTableDetailPageResource {
    @JsonProperty("pageCount")
    private Integer pageCount = null;

    @JsonProperty("total")
    private Integer total = null;

    @JsonProperty("page")
    private Integer page = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    @JsonProperty("courseTableDetailList")
    @Valid
    private List<CourseTableDetailResource> courseTableDetailList = null;

    public CourseTableDetailPageResource pageCount(Integer pageCount) {
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

    public CourseTableDetailPageResource total(Integer total) {
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

    public CourseTableDetailPageResource page(Integer page) {
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

    public CourseTableDetailPageResource pageSize(Integer pageSize) {
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

    public CourseTableDetailPageResource courseTableDetailList(List<CourseTableDetailResource> courseTableDetailList) {
        this.courseTableDetailList = courseTableDetailList;
        return this;
    }

    public CourseTableDetailPageResource addCourseTableDetailListItem(CourseTableDetailResource courseTableDetailListItem) {
        if (this.courseTableDetailList == null) {
            this.courseTableDetailList = new ArrayList<CourseTableDetailResource>();
        }
        this.courseTableDetailList.add(courseTableDetailListItem);
        return this;
    }

    /**
     * Get courseTableDetailList
     *
     * @return courseTableDetailList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<CourseTableDetailResource> getCourseTableDetailList() {
        return courseTableDetailList;
    }

    public void setCourseTableDetailList(List<CourseTableDetailResource> courseTableDetailList) {
        this.courseTableDetailList = courseTableDetailList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseTableDetailPageResource courseTableDetailPageResource = (CourseTableDetailPageResource) o;
        return Objects.equals(this.pageCount, courseTableDetailPageResource.pageCount) &&
                Objects.equals(this.total, courseTableDetailPageResource.total) &&
                Objects.equals(this.page, courseTableDetailPageResource.page) &&
                Objects.equals(this.pageSize, courseTableDetailPageResource.pageSize) &&
                Objects.equals(this.courseTableDetailList, courseTableDetailPageResource.courseTableDetailList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageCount, total, page, pageSize, courseTableDetailList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTableDetailPageResource {\n");

        sb.append("    pageCount: ").append(toIndentedString(pageCount)).append("\n");
        sb.append("    total: ").append(toIndentedString(total)).append("\n");
        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    courseTableDetailList: ").append(toIndentedString(courseTableDetailList)).append("\n");
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

