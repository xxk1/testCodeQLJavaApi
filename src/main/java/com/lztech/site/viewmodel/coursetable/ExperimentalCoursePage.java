package com.lztech.site.viewmodel.coursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ExperimentalCoursePage
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-06-15T12:40:38.633Z")


public class ExperimentalCoursePage {
    @JsonProperty("pageCount")
    private Integer pageCount = null;

    @JsonProperty("total")
    private Integer total = null;

    @JsonProperty("page")
    private Integer page = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    @JsonProperty("experimentalCourseList")
    @Valid
    private List<ExperimentalCourseVo> experimentalCourseList = null;

    public ExperimentalCoursePage pageCount(Integer pageCount) {
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

    public ExperimentalCoursePage total(Integer total) {
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

    public ExperimentalCoursePage page(Integer page) {
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

    public ExperimentalCoursePage pageSize(Integer pageSize) {
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

    public ExperimentalCoursePage experimentalCourseList(List<ExperimentalCourseVo> experimentalCourseList) {
        this.experimentalCourseList = experimentalCourseList;
        return this;
    }

    public ExperimentalCoursePage addExperimentalCourseListItem(ExperimentalCourseVo experimentalCourseListItem) {
        if (this.experimentalCourseList == null) {
            this.experimentalCourseList = new ArrayList<ExperimentalCourseVo>();
        }
        this.experimentalCourseList.add(experimentalCourseListItem);
        return this;
    }

    /**
     * Get experimentalCourseList
     *
     * @return experimentalCourseList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<ExperimentalCourseVo> getExperimentalCourseList() {
        return experimentalCourseList;
    }

    public void setExperimentalCourseList(List<ExperimentalCourseVo> experimentalCourseList) {
        this.experimentalCourseList = experimentalCourseList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExperimentalCoursePage experimentalCoursePage = (ExperimentalCoursePage) o;
        return Objects.equals(this.pageCount, experimentalCoursePage.pageCount) &&
                Objects.equals(this.total, experimentalCoursePage.total) &&
                Objects.equals(this.page, experimentalCoursePage.page) &&
                Objects.equals(this.pageSize, experimentalCoursePage.pageSize) &&
                Objects.equals(this.experimentalCourseList, experimentalCoursePage.experimentalCourseList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageCount, total, page, pageSize, experimentalCourseList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExperimentalCoursePage {\n");

        sb.append("    pageCount: ").append(toIndentedString(pageCount)).append("\n");
        sb.append("    total: ").append(toIndentedString(total)).append("\n");
        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    experimentalCourseList: ").append(toIndentedString(experimentalCourseList)).append("\n");
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

