package com.lztech.site.viewmodel.myuniversity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * MyUniversityAttendClassStatisticsInfoVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-09-04T01:39:03.491Z")


public class MyUniversityAttendClassStatisticsInfoVo   {
  @JsonProperty("firstClassYear")
  private String firstClassYear = null;

  @JsonProperty("firstClassMonth")
  private String firstClassMonth = null;

  @JsonProperty("firstClassDay")
  private String firstClassDay = null;

  @JsonProperty("firstClassHour")
  private String firstClassHour = null;

  @JsonProperty("firstClassMinute")
  private String firstClassMinute = null;

  @JsonProperty("firstClassCourseName")
  private String firstClassCourseName = null;

  @JsonProperty("totalCourseNumber")
  private String totalCourseNumber = null;

  @JsonProperty("totalSegmentNumber")
  private String totalSegmentNumber = null;

  public MyUniversityAttendClassStatisticsInfoVo firstClassYear(String firstClassYear) {
    this.firstClassYear = firstClassYear;
    return this;
  }

  /**
   * 第一堂课年份
   * @return firstClassYear
  **/
  @ApiModelProperty(value = "第一堂课年份")


  public String getFirstClassYear() {
    return firstClassYear;
  }

  public void setFirstClassYear(String firstClassYear) {
    this.firstClassYear = firstClassYear;
  }

  public MyUniversityAttendClassStatisticsInfoVo firstClassMonth(String firstClassMonth) {
    this.firstClassMonth = firstClassMonth;
    return this;
  }

  /**
   * 第一堂课月份
   * @return firstClassMonth
  **/
  @ApiModelProperty(value = "第一堂课月份")


  public String getFirstClassMonth() {
    return firstClassMonth;
  }

  public void setFirstClassMonth(String firstClassMonth) {
    this.firstClassMonth = firstClassMonth;
  }

  public MyUniversityAttendClassStatisticsInfoVo firstClassDay(String firstClassDay) {
    this.firstClassDay = firstClassDay;
    return this;
  }

  /**
   * 第一堂课日
   * @return firstClassDay
  **/
  @ApiModelProperty(value = "第一堂课日")


  public String getFirstClassDay() {
    return firstClassDay;
  }

  public void setFirstClassDay(String firstClassDay) {
    this.firstClassDay = firstClassDay;
  }

  public MyUniversityAttendClassStatisticsInfoVo firstClassHour(String firstClassHour) {
    this.firstClassHour = firstClassHour;
    return this;
  }

  /**
   * 第一堂课时
   * @return firstClassHour
  **/
  @ApiModelProperty(value = "第一堂课时")


  public String getFirstClassHour() {
    return firstClassHour;
  }

  public void setFirstClassHour(String firstClassHour) {
    this.firstClassHour = firstClassHour;
  }

  public MyUniversityAttendClassStatisticsInfoVo firstClassMinute(String firstClassMinute) {
    this.firstClassMinute = firstClassMinute;
    return this;
  }

  /**
   * 第一堂课分
   * @return firstClassMinute
  **/
  @ApiModelProperty(value = "第一堂课分")


  public String getFirstClassMinute() {
    return firstClassMinute;
  }

  public void setFirstClassMinute(String firstClassMinute) {
    this.firstClassMinute = firstClassMinute;
  }

  public MyUniversityAttendClassStatisticsInfoVo firstClassCourseName(String firstClassCourseName) {
    this.firstClassCourseName = firstClassCourseName;
    return this;
  }

  /**
   * 第一堂课课程名称
   * @return firstClassCourseName
  **/
  @ApiModelProperty(value = "第一堂课课程名称")


  public String getFirstClassCourseName() {
    return firstClassCourseName;
  }

  public void setFirstClassCourseName(String firstClassCourseName) {
    this.firstClassCourseName = firstClassCourseName;
  }

  public MyUniversityAttendClassStatisticsInfoVo totalCourseNumber(String totalCourseNumber) {
    this.totalCourseNumber = totalCourseNumber;
    return this;
  }

  /**
   * 总课程门数
   * @return totalCourseNumber
  **/
  @ApiModelProperty(value = "总课程门数")


  public String getTotalCourseNumber() {
    return totalCourseNumber;
  }

  public void setTotalCourseNumber(String totalCourseNumber) {
    this.totalCourseNumber = totalCourseNumber;
  }

  public MyUniversityAttendClassStatisticsInfoVo totalSegmentNumber(String totalSegmentNumber) {
    this.totalSegmentNumber = totalSegmentNumber;
    return this;
  }

  /**
   * 总课时数
   * @return totalSegmentNumber
  **/
  @ApiModelProperty(value = "总课时数")


  public String getTotalSegmentNumber() {
    return totalSegmentNumber;
  }

  public void setTotalSegmentNumber(String totalSegmentNumber) {
    this.totalSegmentNumber = totalSegmentNumber;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MyUniversityAttendClassStatisticsInfoVo myUniversityAttendClassStatisticsInfoVo = (MyUniversityAttendClassStatisticsInfoVo) o;
    return Objects.equals(this.firstClassYear, myUniversityAttendClassStatisticsInfoVo.firstClassYear) &&
        Objects.equals(this.firstClassMonth, myUniversityAttendClassStatisticsInfoVo.firstClassMonth) &&
        Objects.equals(this.firstClassDay, myUniversityAttendClassStatisticsInfoVo.firstClassDay) &&
        Objects.equals(this.firstClassHour, myUniversityAttendClassStatisticsInfoVo.firstClassHour) &&
        Objects.equals(this.firstClassMinute, myUniversityAttendClassStatisticsInfoVo.firstClassMinute) &&
        Objects.equals(this.firstClassCourseName, myUniversityAttendClassStatisticsInfoVo.firstClassCourseName) &&
        Objects.equals(this.totalCourseNumber, myUniversityAttendClassStatisticsInfoVo.totalCourseNumber) &&
        Objects.equals(this.totalSegmentNumber, myUniversityAttendClassStatisticsInfoVo.totalSegmentNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstClassYear, firstClassMonth, firstClassDay, firstClassHour, firstClassMinute,
            firstClassCourseName, totalCourseNumber, totalSegmentNumber);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MyUniversityAttendClassStatisticsInfoVo {\n");
    
    sb.append("    firstClassYear: ").append(toIndentedString(firstClassYear)).append("\n");
    sb.append("    firstClassMonth: ").append(toIndentedString(firstClassMonth)).append("\n");
    sb.append("    firstClassDay: ").append(toIndentedString(firstClassDay)).append("\n");
    sb.append("    firstClassHour: ").append(toIndentedString(firstClassHour)).append("\n");
    sb.append("    firstClassMinute: ").append(toIndentedString(firstClassMinute)).append("\n");
    sb.append("    firstClassCourseName: ").append(toIndentedString(firstClassCourseName)).append("\n");
    sb.append("    totalCourseNumber: ").append(toIndentedString(totalCourseNumber)).append("\n");
    sb.append("    totalSegmentNumber: ").append(toIndentedString(totalSegmentNumber)).append("\n");
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

