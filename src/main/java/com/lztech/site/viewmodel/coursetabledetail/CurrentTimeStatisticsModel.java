package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CurrentTimeStatisticsModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-08-02T08:01:01.136Z")


public class CurrentTimeStatisticsModel {
    @JsonProperty("courseDate")
    private String courseDate = null;

    @JsonProperty("isInClassCourseTable")
    private Integer isInClassCourseTable = null;

    @JsonProperty("inClassCourseTableSegment")
    private Integer inClassCourseTableSegment = null;

    @JsonProperty("inClassCourseTableSegmentStartTime")
    private String inClassCourseTableSegmentStartTime = null;

    @JsonProperty("inClassCourseTableSegmentEndTime")
    private String inClassCourseTableSegmentEndTime = null;

    @JsonProperty("inClassCourseTableDetailCount")
    private Integer inClassCourseTableDetailCount = null;

    @JsonProperty("historyCourseTableDetailCount")
    private Integer historyCourseTableDetailCount = null;

    @JsonProperty("courseTableDetailIdList")
    @Valid
    List<String> courseTableDetailIdList = new ArrayList<>();



    public CurrentTimeStatisticsModel courseDate(String courseDate) {
        this.courseDate = courseDate;
        return this;
    }

    /**
     * 上课日期
     *
     * @return courseDate
     **/
    @ApiModelProperty(value = "上课日期")


    public String getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(String courseDate) {
        this.courseDate = courseDate;
    }

    public CurrentTimeStatisticsModel isInclassCourseTable(Integer isInclassCourseTable) {
        this.isInClassCourseTable = isInclassCourseTable;
        return this;
    }

    /**
     * 是否存在正在上课课表 0:否；1：是
     *
     * @return isInclassCourseTable
     **/
    @ApiModelProperty(value = "是否存在正在上课课表 0:否；1：是")


    public Integer getIsInClassCourseTable() {
        return isInClassCourseTable;
    }

    public void setIsInClassCourseTable(Integer isInClassCourseTable) {
        this.isInClassCourseTable = isInClassCourseTable;
    }

    public CurrentTimeStatisticsModel inclassCourseTableSegment(Integer inclassCourseTableSegment) {
        this.inClassCourseTableSegment = inclassCourseTableSegment;
        return this;
    }

    /**
     * 正在上课课表节次
     *
     * @return inclassCourseTableSegment
     **/
    @ApiModelProperty(value = "正在上课课表节次")


    public Integer getInClassCourseTableSegment() {
        return inClassCourseTableSegment;
    }

    public void setInClassCourseTableSegment(Integer inClassCourseTableSegment) {
        this.inClassCourseTableSegment = inClassCourseTableSegment;
    }

    public CurrentTimeStatisticsModel inclassCourseTableSegmentStartTime(String inclassCourseTableSegmentStartTime) {
        this.inClassCourseTableSegmentStartTime = inclassCourseTableSegmentStartTime;
        return this;
    }

    /**
     * 正在上课课表节次开始时间
     *
     * @return inclassCourseTableSegmentStartTime
     **/
    @ApiModelProperty(value = "正在上课课表节次开始时间")


    public String getInClassCourseTableSegmentStartTime() {
        return inClassCourseTableSegmentStartTime;
    }

    public void setInClassCourseTableSegmentStartTime(String inClassCourseTableSegmentStartTime) {
        this.inClassCourseTableSegmentStartTime = inClassCourseTableSegmentStartTime;
    }

    public CurrentTimeStatisticsModel inclassCourseTableSegmentEndTime(String inclassCourseTableSegmentEndTime) {
        this.inClassCourseTableSegmentEndTime = inclassCourseTableSegmentEndTime;
        return this;
    }

    /**
     * 正在上课课表节次结束时间
     *
     * @return inclassCourseTableSegmentEndTime
     **/
    @ApiModelProperty(value = "正在上课课表节次结束时间")


    public String getInClassCourseTableSegmentEndTime() {
        return inClassCourseTableSegmentEndTime;
    }

    public void setInClassCourseTableSegmentEndTime(String inClassCourseTableSegmentEndTime) {
        this.inClassCourseTableSegmentEndTime = inClassCourseTableSegmentEndTime;
    }

    public CurrentTimeStatisticsModel inClassCourseTableDetailCount(Integer inClassCourseTableDetailCount) {
        this.inClassCourseTableDetailCount = inClassCourseTableDetailCount;
        return this;
    }

    /**
     * 正在上课课表数
     *
     * @return inClassCourseTableDetailCount
     **/
    @ApiModelProperty(value = "正在上课课表数")


    public Integer getInClassCourseTableDetailCount() {
        return inClassCourseTableDetailCount;
    }

    public void setInClassCourseTableDetailCount(Integer inClassCourseTableDetailCount) {
        this.inClassCourseTableDetailCount = inClassCourseTableDetailCount;
    }

    public CurrentTimeStatisticsModel historyCourseTableDetailCount(Integer historyCourseTableDetailCount) {
        this.historyCourseTableDetailCount = historyCourseTableDetailCount;
        return this;
    }

    /**
     * 当前时间之前今天课表数
     *
     * @return historyCourseTableDetailCount
     **/
    @ApiModelProperty(value = "当前时间之前今天课表数")


    public Integer getHistoryCourseTableDetailCount() {
        return historyCourseTableDetailCount;
    }

    public void setHistoryCourseTableDetailCount(Integer historyCourseTableDetailCount) {
        this.historyCourseTableDetailCount = historyCourseTableDetailCount;
    }
    public CurrentTimeStatisticsModel courseTableDetailIdList(List<String> courseTableDetailIdList) {
        this.courseTableDetailIdList = courseTableDetailIdList;
        return this;
    }

    /**
     * 课表id列表
     *
     * @return courseTableDetailIdList
     **/
    @ApiModelProperty(value = "课表id列表")

    public List<String> getCourseTableDetailIdList() {
        return courseTableDetailIdList;
    }

    public void setCourseTableDetailIdList(List<String> courseTableDetailIdList) {
        this.courseTableDetailIdList = courseTableDetailIdList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CurrentTimeStatisticsModel currentTimeStatisticsModel = (CurrentTimeStatisticsModel) o;
        return Objects.equals(this.courseDate, currentTimeStatisticsModel.courseDate) &&
                Objects.equals(this.isInClassCourseTable, currentTimeStatisticsModel.isInClassCourseTable) &&
                Objects.equals(this.inClassCourseTableSegment, currentTimeStatisticsModel.inClassCourseTableSegment) &&
                Objects.equals(this.inClassCourseTableSegmentStartTime, currentTimeStatisticsModel.inClassCourseTableSegmentStartTime) &&
                Objects.equals(this.inClassCourseTableSegmentEndTime, currentTimeStatisticsModel.inClassCourseTableSegmentEndTime) &&
                Objects.equals(this.inClassCourseTableDetailCount, currentTimeStatisticsModel.inClassCourseTableDetailCount) &&
                Objects.equals(this.historyCourseTableDetailCount, currentTimeStatisticsModel.historyCourseTableDetailCount) &&
                Objects.equals(this.courseTableDetailIdList, currentTimeStatisticsModel.courseTableDetailIdList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseDate, isInClassCourseTable, inClassCourseTableSegment,
                inClassCourseTableSegmentStartTime, inClassCourseTableSegmentEndTime,
                inClassCourseTableDetailCount, historyCourseTableDetailCount,courseTableDetailIdList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CurrentTimeStatisticsModel {\n");
        sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
        sb.append("    isInClassCourseTable: ").append(toIndentedString(isInClassCourseTable)).append("\n");
        sb.append("    inClassCourseTableSegment: ").append(toIndentedString(inClassCourseTableSegment)).append("\n");
        sb.append("    inClassCourseTableSegmentStartTime: ").append(toIndentedString(inClassCourseTableSegmentStartTime)).append("\n");
        sb.append("    inClassCourseTableSegmentEndTime: ").append(toIndentedString(inClassCourseTableSegmentEndTime)).append("\n");
        sb.append("    inClassCourseTableDetailCount: ").append(toIndentedString(inClassCourseTableDetailCount)).append("\n");
        sb.append("    historyCourseTableDetailCount: ").append(toIndentedString(historyCourseTableDetailCount)).append("\n");
        sb.append("    courseTableDetailIdList: ").append(toIndentedString(courseTableDetailIdList)).append("\n");
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

