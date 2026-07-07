package com.lztech.site.viewmodel.term;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * TermWeekDayResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-11T08:21:30.309Z")

public class TermWeekDayResource {
  @JsonProperty("date")
  private String date = null;

  @JsonProperty("weekNum")
  private String weekNum = null;

  public TermWeekDayResource date(String date) {
    this.date = date;
    return this;
  }

  /**
   * 日期
   * @return date
   **/
  @ApiModelProperty(value = "日期")


  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public TermWeekDayResource weekNum(String weekNum) {
    this.weekNum = weekNum;
    return this;
  }

  /**
   * 星期几：1-7
   * @return weekNum
   **/
  @ApiModelProperty(value = "星期几：1-7")


  public String getWeekNum() {
    return weekNum;
  }

  public void setWeekNum(String weekNum) {
    this.weekNum = weekNum;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TermWeekDayResource termWeekDayResource = (TermWeekDayResource) o;
    return Objects.equals(this.date, termWeekDayResource.date) &&
            Objects.equals(this.weekNum, termWeekDayResource.weekNum);
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, weekNum);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TermWeekDayResource {\n");

    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    weekNum: ").append(toIndentedString(weekNum)).append("\n");
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

