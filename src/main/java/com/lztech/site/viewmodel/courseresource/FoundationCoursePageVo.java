package com.lztech.site.viewmodel.courseresource;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * FoundationCoursePageVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-04-27T06:28:53.472Z")


public class FoundationCoursePageVo {
    @JsonProperty("total")
    private Integer total = null;

    @JsonProperty("page")
    private Integer page = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    @JsonProperty("FoundationCourseList")
    @Valid
    private List<FoundationCourseVo> foundationCourseList = null;

    public FoundationCoursePageVo total(Integer total) {
        this.total = total;
        return this;
    }

    /**
     * 总页数
     *
     * @return total
     **/
    @ApiModelProperty(example = "10", value = "总页数")


    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public FoundationCoursePageVo page(Integer page) {
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

    public FoundationCoursePageVo pageSize(Integer pageSize) {
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

    public FoundationCoursePageVo foundationCourseList(List<FoundationCourseVo> foundationCourseList) {
        this.foundationCourseList = foundationCourseList;
        return this;
    }

    public FoundationCoursePageVo addFoundationCourseListItem(FoundationCourseVo foundationCourseListItem) {
        if (this.foundationCourseList == null) {
            this.foundationCourseList = new ArrayList<FoundationCourseVo>();
        }
        this.foundationCourseList.add(foundationCourseListItem);
        return this;
    }

    /**
     * Get foundationCourseList
     *
     * @return foundationCourseList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<FoundationCourseVo> getFoundationCourseList() {
        return foundationCourseList;
    }

    public void setFoundationCourseList(List<FoundationCourseVo> foundationCourseList) {
        this.foundationCourseList = foundationCourseList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FoundationCoursePageVo foundationCoursePageVo = (FoundationCoursePageVo) o;
        return Objects.equals(this.total, foundationCoursePageVo.total) &&
                Objects.equals(this.page, foundationCoursePageVo.page) &&
                Objects.equals(this.pageSize, foundationCoursePageVo.pageSize) &&
                Objects.equals(this.foundationCourseList, foundationCoursePageVo.foundationCourseList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, page, pageSize, foundationCourseList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class FoundationCoursePageVo {\n");

        sb.append("    total: ").append(toIndentedString(total)).append("\n");
        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    foundationCourseList: ").append(toIndentedString(foundationCourseList)).append("\n");
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

