package com.lztech.site.viewmodel.wisdomcourse;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * WisdomCourseTaskPageResult
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-08-13T15:53:10.736+08:00")

public class WisdomCourseTaskPageResult {
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

    @JsonProperty("taskList")
    @Valid
    private List<WisdomCourseTaskResult> taskList = null;

    public WisdomCourseTaskPageResult total(Integer total) {
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

    public WisdomCourseTaskPageResult page(Integer page) {
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

    public WisdomCourseTaskPageResult pageCount(Integer pageCount) {
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

    public WisdomCourseTaskPageResult totalCount(Integer totalCount) {
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

    public WisdomCourseTaskPageResult pageSize(Integer pageSize) {
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

    public WisdomCourseTaskPageResult taskList(List<WisdomCourseTaskResult> taskList) {
        this.taskList = taskList;
        return this;
    }

    public WisdomCourseTaskPageResult addTaskListItem(WisdomCourseTaskResult taskListItem) {
        if (this.taskList == null) {
            this.taskList = new ArrayList<WisdomCourseTaskResult>();
        }
        this.taskList.add(taskListItem);
        return this;
    }

    /**
     * Get taskList
     *
     * @return taskList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<WisdomCourseTaskResult> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<WisdomCourseTaskResult> taskList) {
        this.taskList = taskList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WisdomCourseTaskPageResult wisdomCourseTaskPageResult = (WisdomCourseTaskPageResult) o;
        return Objects.equals(this.total, wisdomCourseTaskPageResult.total) &&
                Objects.equals(this.page, wisdomCourseTaskPageResult.page) &&
                Objects.equals(this.pageCount, wisdomCourseTaskPageResult.pageCount) &&
                Objects.equals(this.totalCount, wisdomCourseTaskPageResult.totalCount) &&
                Objects.equals(this.pageSize, wisdomCourseTaskPageResult.pageSize) &&
                Objects.equals(this.taskList, wisdomCourseTaskPageResult.taskList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, page, pageCount, totalCount, pageSize, taskList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class WisdomCourseTaskPageResult {\n");

        sb.append("    total: ").append(toIndentedString(total)).append("\n");
        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    pageCount: ").append(toIndentedString(pageCount)).append("\n");
        sb.append("    totalCount: ").append(toIndentedString(totalCount)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    taskList: ").append(toIndentedString(taskList)).append("\n");
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

