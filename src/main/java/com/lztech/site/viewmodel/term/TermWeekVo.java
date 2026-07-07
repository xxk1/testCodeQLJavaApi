package com.lztech.site.viewmodel.term;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * TermWeekResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-11T08:21:30.309Z")

public class TermWeekVo {
  @JsonProperty("week")
  private String week = null;

  @JsonProperty("nowWeek")
  private String nowWeek = null;

  @JsonProperty("nowDate")
  private String nowDate = null;

  @JsonProperty("days")
  @Valid
  private List<TermWeekDayResource> days = null;

  public TermWeekVo week(String week) {
    this.week = week;
    return this;
  }

  /**
   * 第几周
   * @return week
   **/
  @ApiModelProperty(value = "第几周")


  public String getWeek() {
    return week;
  }

  public void setWeek(String week) {
    this.week = week;
  }

  public TermWeekVo nowWeek(String nowWeek) {
    this.nowWeek = nowWeek;
    return this;
  }

  /**
   * 现在是第几周
   * @return nowWeek
   **/
  @ApiModelProperty(value = "现在是第几周")


  public String getNowWeek() {
    return nowWeek;
  }

  public void setNowWeek(String nowWeek) {
    this.nowWeek = nowWeek;
  }

  public TermWeekVo nowDate(String nowDate) {
    this.nowDate = nowDate;
    return this;
  }

  /**
   * 今天的日期
   * @return nowDate
   **/
  @ApiModelProperty(value = "今天的日期")


  public String getNowDate() {
    return nowDate;
  }

  public void setNowDate(String nowDate) {
    this.nowDate = nowDate;
  }

  public TermWeekVo days(List<TermWeekDayResource> days) {
    this.days = days;
    return this;
  }

  public TermWeekVo addDaysItem(TermWeekDayResource daysItem) {
    if (this.days == null) {
      this.days = new ArrayList<TermWeekDayResource>();
    }
    this.days.add(daysItem);
    return this;
  }

  /**
   * 日期
   * @return days
   **/
  @ApiModelProperty(value = "日期")

  @Valid

  public List<TermWeekDayResource> getDays() {
    return days;
  }

  public void setDays(List<TermWeekDayResource> days) {
    this.days = days;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TermWeekVo termWeekResource = (TermWeekVo) o;
    return Objects.equals(this.week, termWeekResource.week) &&
            Objects.equals(this.nowWeek, termWeekResource.nowWeek) &&
            Objects.equals(this.nowDate, termWeekResource.nowDate) &&
            Objects.equals(this.days, termWeekResource.days);
  }

  @Override
  public int hashCode() {
    return Objects.hash(week, nowWeek, nowDate, days);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TermWeekResource {\n");

    sb.append("    week: ").append(toIndentedString(week)).append("\n");
    sb.append("    nowWeek: ").append(toIndentedString(nowWeek)).append("\n");
    sb.append("    nowDate: ").append(toIndentedString(nowDate)).append("\n");
    sb.append("    days: ").append(toIndentedString(days)).append("\n");
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

