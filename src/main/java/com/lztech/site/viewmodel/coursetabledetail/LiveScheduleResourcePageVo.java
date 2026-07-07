package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * LiveScheduleResourcePageVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-07-26T12:32:07.191Z")


public class LiveScheduleResourcePageVo {
    @JsonProperty("page")
    private Integer page = null;

    @JsonProperty("pageCount")
    private Integer pageCount = null;

    @JsonProperty("totalCount")
    private Integer totalCount = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    @JsonProperty("liveScheduleResourceList")
    @Valid
    private List<LiveCourseResource> liveScheduleResourceList = null;

    public LiveScheduleResourcePageVo page(Integer page) {
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

    public LiveScheduleResourcePageVo pageCount(Integer pageCount) {
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

    public LiveScheduleResourcePageVo totalCount(Integer totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    /**
     * 总条数
     *
     * @return totalCount
     **/
    @ApiModelProperty(value = "总条数")


    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public LiveScheduleResourcePageVo pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * 每页个数
     *
     * @return pageSize
     **/
    @ApiModelProperty(value = "每页个数")


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public LiveScheduleResourcePageVo liveScheduleResourceList(List<LiveCourseResource> liveScheduleResourceList) {
        this.liveScheduleResourceList = liveScheduleResourceList;
        return this;
    }

    public LiveScheduleResourcePageVo addLiveScheduleResourceListItem(LiveCourseResource liveScheduleResourceListItem) {
        if (this.liveScheduleResourceList == null) {
            this.liveScheduleResourceList = new ArrayList<LiveCourseResource>();
        }
        this.liveScheduleResourceList.add(liveScheduleResourceListItem);
        return this;
    }

    /**
     * 正在直播的课表信息集合
     *
     * @return liveScheduleResourceList
     **/
    @ApiModelProperty(value = "正在直播的课表信息集合")

    @Valid

    public List<LiveCourseResource> getLiveScheduleResourceList() {
        return liveScheduleResourceList;
    }

    public void setLiveScheduleResourceList(List<LiveCourseResource> liveScheduleResourceList) {
        this.liveScheduleResourceList = liveScheduleResourceList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LiveScheduleResourcePageVo liveScheduleResourcePageVo = (LiveScheduleResourcePageVo) o;
        return Objects.equals(this.page, liveScheduleResourcePageVo.page) &&
                Objects.equals(this.pageCount, liveScheduleResourcePageVo.pageCount) &&
                Objects.equals(this.totalCount, liveScheduleResourcePageVo.totalCount) &&
                Objects.equals(this.pageSize, liveScheduleResourcePageVo.pageSize) &&
                Objects.equals(this.liveScheduleResourceList, liveScheduleResourcePageVo.liveScheduleResourceList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, pageCount, totalCount, pageSize, liveScheduleResourceList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class LiveScheduleResourcePageVo {\n");

        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    pageCount: ").append(toIndentedString(pageCount)).append("\n");
        sb.append("    totalCount: ").append(toIndentedString(totalCount)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    liveScheduleResourceList: ").append(toIndentedString(liveScheduleResourceList)).append("\n");
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

