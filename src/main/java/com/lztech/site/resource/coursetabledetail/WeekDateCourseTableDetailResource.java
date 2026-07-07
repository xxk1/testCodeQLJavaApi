package com.lztech.site.resource.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * WeekDateCourseTableDetailResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-10-20T06:57:00.078Z")


public class WeekDateCourseTableDetailResource {
    @JsonProperty("weekNum")
    private String weekNum = null;

    @JsonProperty("courseDate")
    private String courseDate = null;

    @JsonProperty("hasToday")
    private Boolean hasToday = null;

    @JsonProperty("courseTableDetailList")
    @Valid
    private List<CourseTableDetailInfoModel> courseTableDetailList = null;

    public WeekDateCourseTableDetailResource weekNum(String weekNum) {
        this.weekNum = weekNum;
        return this;
    }

    /**
     * 星期几
     *
     * @return weekNum
     **/
    @ApiModelProperty(value = "周几")


    public String getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(String weekNum) {
        this.weekNum = weekNum;
    }

    public WeekDateCourseTableDetailResource courseDate(String courseDate) {
        this.courseDate = courseDate;
        return this;
    }

    /**
     * 日期
     *
     * @return courseDate
     **/
    @ApiModelProperty(value = "日期")


    public String getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(String courseDate) {
        this.courseDate = courseDate;
    }

    public WeekDateCourseTableDetailResource hasToday(Boolean hasToday) {
        this.hasToday = hasToday;
        return this;
    }

    /**
     * 是否是今天
     *
     * @return hasToday
     **/
    @ApiModelProperty(value = "是否是今天")


    public Boolean isHasToday() {
        return hasToday;
    }

    public void setHasToday(Boolean hasToday) {
        this.hasToday = hasToday;
    }

    public WeekDateCourseTableDetailResource courseTableDetailList(List<CourseTableDetailInfoModel> courseTableDetailList) {
        this.courseTableDetailList = courseTableDetailList;
        return this;
    }

    public WeekDateCourseTableDetailResource addCourseTableDetailListItem(CourseTableDetailInfoModel courseTableDetailListItem) {
        if (this.courseTableDetailList == null) {
            this.courseTableDetailList = new ArrayList<CourseTableDetailInfoModel>();
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

    public List<CourseTableDetailInfoModel> getCourseTableDetailList() {
        return courseTableDetailList;
    }

    public void setCourseTableDetailList(List<CourseTableDetailInfoModel> courseTableDetailList) {
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
        WeekDateCourseTableDetailResource weekDateCourseTableDetailResource = (WeekDateCourseTableDetailResource) o;
        return Objects.equals(this.weekNum, weekDateCourseTableDetailResource.weekNum) &&
                Objects.equals(this.courseDate, weekDateCourseTableDetailResource.courseDate) &&
                Objects.equals(this.hasToday, weekDateCourseTableDetailResource.hasToday) &&
                Objects.equals(this.courseTableDetailList, weekDateCourseTableDetailResource.courseTableDetailList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weekNum, courseDate, hasToday, courseTableDetailList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class WeekDateCourseTableDetailResource {\n");

        sb.append("    weekNum: ").append(toIndentedString(weekNum)).append("\n");
        sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
        sb.append("    hasToday: ").append(toIndentedString(hasToday)).append("\n");
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

