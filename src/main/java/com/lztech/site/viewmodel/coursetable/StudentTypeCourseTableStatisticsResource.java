package com.lztech.site.viewmodel.coursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * StudentTypeCourseTableStatisticsResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-10-26T02:57:25.963Z")


public class StudentTypeCourseTableStatisticsResource {
    @JsonProperty("totalNum")
    private Integer totalNum = null;

    @JsonProperty("detailList")
    @Valid
    private List<StatisticsResource> detailList = null;

    public StudentTypeCourseTableStatisticsResource totalNum(Integer totalNum) {
        this.totalNum = totalNum;
        return this;
    }

    /**
     * 开课总数量
     *
     * @return totalNum
     **/
    @ApiModelProperty(value = "开课总数量")


    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public StudentTypeCourseTableStatisticsResource detailList(List<StatisticsResource> detailList) {
        this.detailList = detailList;
        return this;
    }

    public StudentTypeCourseTableStatisticsResource addDetailListItem(StatisticsResource detailListItem) {
        if (this.detailList == null) {
            this.detailList = new ArrayList<StatisticsResource>();
        }
        this.detailList.add(detailListItem);
        return this;
    }

    /**
     * Get detailList
     *
     * @return detailList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<StatisticsResource> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<StatisticsResource> detailList) {
        this.detailList = detailList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StudentTypeCourseTableStatisticsResource studentTypeCourseTableStatisticsResource = (StudentTypeCourseTableStatisticsResource) o;
        return Objects.equals(this.totalNum, studentTypeCourseTableStatisticsResource.totalNum) &&
                Objects.equals(this.detailList, studentTypeCourseTableStatisticsResource.detailList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalNum, detailList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class StudentTypeCourseTableStatisticsResource {\n");

        sb.append("    totalNum: ").append(toIndentedString(totalNum)).append("\n");
        sb.append("    detailList: ").append(toIndentedString(detailList)).append("\n");
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

