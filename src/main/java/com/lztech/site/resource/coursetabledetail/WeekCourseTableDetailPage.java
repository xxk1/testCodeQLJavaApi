package com.lztech.site.resource.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * WeekCourseTableDetailPage
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-10-20T06:57:00.078Z")


public class WeekCourseTableDetailPage {
    @JsonProperty("week")
    private String week = null;

    @JsonProperty("weekDateCourseTableDetailList")
    @Valid
    private List<WeekDateCourseTableDetailResource> weekDateCourseTableDetailList = null;

    public WeekCourseTableDetailPage week(String week) {
        this.week = week;
        return this;
    }

    /**
     * 课次
     *
     * @return week
     **/
    @ApiModelProperty(value = "课次")


    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public WeekCourseTableDetailPage weekDateCourseTableDetailList(List<WeekDateCourseTableDetailResource> weekDateCourseTableDetailList) {
        this.weekDateCourseTableDetailList = weekDateCourseTableDetailList;
        return this;
    }

    public WeekCourseTableDetailPage addWeekDateCourseTableDetailListItem(WeekDateCourseTableDetailResource weekDateCourseTableDetailListItem) {
        if (this.weekDateCourseTableDetailList == null) {
            this.weekDateCourseTableDetailList = new ArrayList<WeekDateCourseTableDetailResource>();
        }
        this.weekDateCourseTableDetailList.add(weekDateCourseTableDetailListItem);
        return this;
    }

    /**
     * Get weekDateCourseTableDetailList
     *
     * @return weekDateCourseTableDetailList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<WeekDateCourseTableDetailResource> getWeekDateCourseTableDetailList() {
        return weekDateCourseTableDetailList;
    }

    public void setWeekDateCourseTableDetailList(List<WeekDateCourseTableDetailResource> weekDateCourseTableDetailList) {
        this.weekDateCourseTableDetailList = weekDateCourseTableDetailList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WeekCourseTableDetailPage weekCourseTableDetailPage = (WeekCourseTableDetailPage) o;
        return Objects.equals(this.week, weekCourseTableDetailPage.week) &&
                Objects.equals(this.weekDateCourseTableDetailList, weekCourseTableDetailPage.weekDateCourseTableDetailList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(week, weekDateCourseTableDetailList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class WeekCourseTableDetailPage {\n");

        sb.append("    week: ").append(toIndentedString(week)).append("\n");
        sb.append("    weekDateCourseTableDetailList: ").append(toIndentedString(weekDateCourseTableDetailList)).append("\n");
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

