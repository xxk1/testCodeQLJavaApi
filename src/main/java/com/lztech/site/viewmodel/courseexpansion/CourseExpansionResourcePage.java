package com.lztech.site.viewmodel.courseexpansion;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseExpansionResourcePage
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2026-01-29T16:18:20.693+08:00")

public class CourseExpansionResourcePage {
    @JsonProperty("total")
    private Integer total = null;

    @JsonProperty("page")
    private Integer page = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    @JsonProperty("totalPage")
    private Integer totalPage = null;

    @JsonProperty("resourceList")
    @Valid
    private List<CourseExpansionResource> resourceList = null;

    public CourseExpansionResourcePage total(Integer total) {
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

    public CourseExpansionResourcePage page(Integer page) {
        this.page = page;
        return this;
    }

    /**
     * 当前页码
     *
     * @return page
     **/
    @ApiModelProperty(value = "当前页码")


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public CourseExpansionResourcePage pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * 每页数量
     *
     * @return pageSize
     **/
    @ApiModelProperty(value = "每页数量")


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public CourseExpansionResourcePage totalPage(Integer totalPage) {
        this.totalPage = totalPage;
        return this;
    }

    /**
     * 总页数
     *
     * @return totalPage
     **/
    @ApiModelProperty(value = "总页数")


    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public CourseExpansionResourcePage resourceList(List<CourseExpansionResource> resourceList) {
        this.resourceList = resourceList;
        return this;
    }

    public CourseExpansionResourcePage addResourceListItem(CourseExpansionResource resourceListItem) {
        if (this.resourceList == null) {
            this.resourceList = new ArrayList<CourseExpansionResource>();
        }
        this.resourceList.add(resourceListItem);
        return this;
    }

    /**
     * Get resourceList
     *
     * @return resourceList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<CourseExpansionResource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<CourseExpansionResource> resourceList) {
        this.resourceList = resourceList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseExpansionResourcePage courseExpansionResourcePage = (CourseExpansionResourcePage) o;
        return Objects.equals(this.total, courseExpansionResourcePage.total) &&
                Objects.equals(this.page, courseExpansionResourcePage.page) &&
                Objects.equals(this.pageSize, courseExpansionResourcePage.pageSize) &&
                Objects.equals(this.totalPage, courseExpansionResourcePage.totalPage) &&
                Objects.equals(this.resourceList, courseExpansionResourcePage.resourceList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, page, pageSize, totalPage, resourceList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseExpansionResourcePage {\n");

        sb.append("    total: ").append(toIndentedString(total)).append("\n");
        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    totalPage: ").append(toIndentedString(totalPage)).append("\n");
        sb.append("    resourceList: ").append(toIndentedString(resourceList)).append("\n");
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

