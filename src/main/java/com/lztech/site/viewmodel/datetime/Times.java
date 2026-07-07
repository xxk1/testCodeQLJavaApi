package com.lztech.site.viewmodel.datetime;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

@Validated

public class Times {
  @JsonProperty("nowDateTime")
  private String nowDateTime = null;

  @JsonProperty("timeStamp")
  private String timeStamp = null;

  @JsonProperty("nowYear")
  private String nowYear = null;

  @JsonProperty("nowMonth")
  private String nowMonth = null;

  @JsonProperty("nowDay")
  private String nowDay = null;

  @JsonProperty("nowHour")
  private String nowHour = null;

  @JsonProperty("nowMinute")
  private String nowMinute = null;

  @JsonProperty("nowSecond")
  private String nowSecond = null;

  public Times nowDateTime(String nowDateTime) {
    this.nowDateTime = nowDateTime;
    return this;
  }

  /**
   * 当前时间
   * @return nowDateTime
  **/
  @ApiModelProperty(value = "当前时间")


  public String getNowDateTime() {
    return nowDateTime;
  }

  public void setNowDateTime(String nowDateTime) {
    this.nowDateTime = nowDateTime;
  }

  public Times timeStamp(String timeStamp) {
    this.timeStamp = timeStamp;
    return this;
  }

  /**
   * 时间戳
   * @return timeStamp
  **/
  @ApiModelProperty(value = "时间戳")


  public String getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(String timeStamp) {
    this.timeStamp = timeStamp;
  }

  public Times nowYear(String nowYear) {
    this.nowYear = nowYear;
    return this;
  }

  /**
   * 年
   * @return nowYear
  **/
  @ApiModelProperty(value = "年")


  public String getNowYear() {
    return nowYear;
  }

  public void setNowYear(String nowYear) {
    this.nowYear = nowYear;
  }

  public Times nowMonth(String nowMonth) {
    this.nowMonth = nowMonth;
    return this;
  }

  /**
   * 月
   * @return nowMonth
  **/
  @ApiModelProperty(value = "月")


  public String getNowMonth() {
    return nowMonth;
  }

  public void setNowMonth(String nowMonth) {
    this.nowMonth = nowMonth;
  }

  public Times nowDay(String nowDay) {
    this.nowDay = nowDay;
    return this;
  }

  /**
   * 日
   * @return nowDay
  **/
  @ApiModelProperty(value = "日")


  public String getNowDay() {
    return nowDay;
  }

  public void setNowDay(String nowDay) {
    this.nowDay = nowDay;
  }

  public Times nowHour(String nowHour) {
    this.nowHour = nowHour;
    return this;
  }

  /**
   * 时
   * @return nowHour
  **/
  @ApiModelProperty(value = "时")


  public String getNowHour() {
    return nowHour;
  }

  public void setNowHour(String nowHour) {
    this.nowHour = nowHour;
  }

  public Times nowMinute(String nowMinute) {
    this.nowMinute = nowMinute;
    return this;
  }

  /**
   * 分
   * @return nowMinute
  **/
  @ApiModelProperty(value = "分")


  public String getNowMinute() {
    return nowMinute;
  }

  public void setNowMinute(String nowMinute) {
    this.nowMinute = nowMinute;
  }

  public Times nowSecond(String nowSecond) {
    this.nowSecond = nowSecond;
    return this;
  }

  /**
   * 秒
   * @return nowSecond
  **/
  @ApiModelProperty(value = "秒")


  public String getNowSecond() {
    return nowSecond;
  }

  public void setNowSecond(String nowSecond) {
    this.nowSecond = nowSecond;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Times times = (Times) o;
    return Objects.equals(this.nowDateTime, times.nowDateTime) &&
        Objects.equals(this.timeStamp, times.timeStamp) &&
        Objects.equals(this.nowYear, times.nowYear) &&
        Objects.equals(this.nowMonth, times.nowMonth) &&
        Objects.equals(this.nowDay, times.nowDay) &&
        Objects.equals(this.nowHour, times.nowHour) &&
        Objects.equals(this.nowMinute, times.nowMinute) &&
        Objects.equals(this.nowSecond, times.nowSecond);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nowDateTime, timeStamp, nowYear, nowMonth, nowDay, nowHour, nowMinute, nowSecond);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Times {\n");
    
    sb.append("    nowDateTime: ").append(toIndentedString(nowDateTime)).append("\n");
    sb.append("    timeStamp: ").append(toIndentedString(timeStamp)).append("\n");
    sb.append("    nowYear: ").append(toIndentedString(nowYear)).append("\n");
    sb.append("    nowMonth: ").append(toIndentedString(nowMonth)).append("\n");
    sb.append("    nowDay: ").append(toIndentedString(nowDay)).append("\n");
    sb.append("    nowHour: ").append(toIndentedString(nowHour)).append("\n");
    sb.append("    nowMinute: ").append(toIndentedString(nowMinute)).append("\n");
    sb.append("    nowSecond: ").append(toIndentedString(nowSecond)).append("\n");
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

