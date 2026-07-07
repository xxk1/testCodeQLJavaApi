package com.lztech.site.viewmodel.wisdomcourse;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * WisdomCoursePageResult
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-08-13T15:53:10.736+08:00")

public class WisdomCoursePageResult {
    @JsonProperty("total")
    private Integer total = null;

    @JsonProperty("page")
    private Integer page = null;

    @JsonProperty("pageCount")
    private Integer pageCount = null;

    @JsonProperty("totalCount")
    private Integer totalCount = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    @JsonProperty("courseList")
    @Valid
    private List<WisdomCourseResult> courseList = null;

    public WisdomCoursePageResult total(Integer total) {
        this.total = total;
        return this;
    }

    /**
     * 总记录数
     *
     * @return total
     **/
    @ApiModelProperty(value = "总记录数")


    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public WisdomCoursePageResult page(Integer page) {
        this.page = page;
        return this;
    }

    /**
     * 页码
     *
     * @return page
     **/
    @ApiModelProperty(value = "页码")


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public WisdomCoursePageResult pageCount(Integer pageCount) {
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

    public WisdomCoursePageResult totalCount(Integer totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    /**
     * 总记录数
     *
     * @return totalCount
     **/
    @ApiModelProperty(value = "总记录数")


    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public WisdomCoursePageResult pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * 每页记录数
     *
     * @return pageSize
     **/
    @ApiModelProperty(value = "每页记录数")


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public WisdomCoursePageResult courseList(List<WisdomCourseResult> courseList) {
        this.courseList = courseList;
        return this;
    }

    public WisdomCoursePageResult addCourseListItem(WisdomCourseResult courseListItem) {
        if (this.courseList == null) {
            this.courseList = new ArrayList<WisdomCourseResult>();
        }
        this.courseList.add(courseListItem);
        return this;
    }

    /**
     * Get courseList
     *
     * @return courseList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<WisdomCourseResult> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<WisdomCourseResult> courseList) {
        this.courseList = courseList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WisdomCoursePageResult wisdomCoursePageResult = (WisdomCoursePageResult) o;
        return Objects.equals(this.total, wisdomCoursePageResult.total) &&
                Objects.equals(this.page, wisdomCoursePageResult.page) &&
                Objects.equals(this.pageCount, wisdomCoursePageResult.pageCount) &&
                Objects.equals(this.totalCount, wisdomCoursePageResult.totalCount) &&
                Objects.equals(this.pageSize, wisdomCoursePageResult.pageSize) &&
                Objects.equals(this.courseList, wisdomCoursePageResult.courseList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, page, pageCount, totalCount, pageSize, courseList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class WisdomCoursePageResult {\n");

        sb.append("    total: ").append(toIndentedString(total)).append("\n");
        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    pageCount: ").append(toIndentedString(pageCount)).append("\n");
        sb.append("    totalCount: ").append(toIndentedString(totalCount)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    courseList: ").append(toIndentedString(courseList)).append("\n");
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

