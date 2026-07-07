package com.lztech.site.viewmodel.experimentschedulecoursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * DataDockingCourseTableDetailPageResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-09-12T12:21:44.947Z")


public class DataDockingCourseTableDetailPageResource {
    @JsonProperty("total")
    private Integer total = null;

    @JsonProperty("pageNum")
    private Integer pageNum = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    @JsonProperty("dataDockingCourseTableDetailList")
    @Valid
    private List<ExperimentScheduleUseCourseTableDetailVo> dataDockingCourseTableDetailList = null;

    public DataDockingCourseTableDetailPageResource total(Integer total) {
        this.total = total;
        return this;
    }

    /**
     * 总页数
     *
     * @return total
     **/
    @ApiModelProperty(value = "总页数",position = 1, required = true)


    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public DataDockingCourseTableDetailPageResource pageNum(Integer pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    /**
     * 当前页码
     *
     * @return pageNum
     **/
    @ApiModelProperty(value = "当前页码",position = 2, required = true)


    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public DataDockingCourseTableDetailPageResource pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * 每页记录数
     *
     * @return pageSize
     **/
    @ApiModelProperty(value = "每页记录数",position = 3, required = true)


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public DataDockingCourseTableDetailPageResource dataDockingCourseTableDetailList(
            List<ExperimentScheduleUseCourseTableDetailVo> dataDockingCourseTableDetailList) {
        this.dataDockingCourseTableDetailList = dataDockingCourseTableDetailList;
        return this;
    }

    public DataDockingCourseTableDetailPageResource addDataDockingCourseTableDetailListItem(
            ExperimentScheduleUseCourseTableDetailVo dataDockingCourseTableDetailListItem) {
        if (this.dataDockingCourseTableDetailList == null) {
            this.dataDockingCourseTableDetailList = new ArrayList<ExperimentScheduleUseCourseTableDetailVo>();
        }
        this.dataDockingCourseTableDetailList.add(dataDockingCourseTableDetailListItem);
        return this;
    }

    /**
     * Get dataDockingCourseTableDetailList
     *
     * @return dataDockingCourseTableDetailList
     **/
    @ApiModelProperty(value = "数据对接课表信息列表",position = 4, required = true)

    @Valid

    public List<ExperimentScheduleUseCourseTableDetailVo> getDataDockingCourseTableDetailList() {
        return dataDockingCourseTableDetailList;
    }

    public void setDataDockingCourseTableDetailList(List<ExperimentScheduleUseCourseTableDetailVo> dataDockingCourseTableDetailList) {
        this.dataDockingCourseTableDetailList = dataDockingCourseTableDetailList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DataDockingCourseTableDetailPageResource dataDockingCourseTableDetailPageResource = (DataDockingCourseTableDetailPageResource) o;
        return Objects.equals(this.total, dataDockingCourseTableDetailPageResource.total) &&
                Objects.equals(this.pageNum, dataDockingCourseTableDetailPageResource.pageNum) &&
                Objects.equals(this.pageSize, dataDockingCourseTableDetailPageResource.pageSize) &&
                Objects.equals(this.dataDockingCourseTableDetailList,
                        dataDockingCourseTableDetailPageResource.dataDockingCourseTableDetailList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, pageNum, pageSize, dataDockingCourseTableDetailList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DataDockingCourseTableDetailPageResource {\n");

        sb.append("    total: ").append(toIndentedString(total)).append("\n");
        sb.append("    pageNum: ").append(toIndentedString(pageNum)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    dataDockingCourseTableDetailList: ").append(toIndentedString(dataDockingCourseTableDetailList)).append("\n");
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

