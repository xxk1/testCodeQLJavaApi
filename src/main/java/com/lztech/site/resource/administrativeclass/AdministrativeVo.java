package com.lztech.site.resource.administrativeclass;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * AdministrativeVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-12-15T10:03:56.724Z")


public class AdministrativeVo   {
  @JsonProperty("studentType")
  private Integer studentType = null;

  @JsonProperty("year")
  private String year = null;

  @JsonProperty("collegeId")
  private String collegeId = null;

  @JsonProperty("page")
  private Integer page = null;

  @JsonProperty("rows")
  private Integer rows = null;

  @JsonProperty("majorId")
  private String majorId = null;

  @JsonProperty("administrativeClass")
  private String administrativeClass = null;

  @JsonProperty("segment")
  private Integer segment = null;

  @JsonProperty("week")
  private Integer week = null;

  @JsonProperty("weekNum")
  private Integer weekNum = null;

  public AdministrativeVo studentType(Integer studentType) {
    this.studentType = studentType;
    return this;
  }

  /**
   * 开课类型
   * @return studentType
  **/
  @ApiModelProperty(value = "开课类型")


  public Integer getStudentType() {
    return studentType;
  }

  public void setStudentType(Integer studentType) {
    this.studentType = studentType;
  }

  public AdministrativeVo year(String year) {
    this.year = year;
    return this;
  }

  /**
   * 年级
   * @return year
  **/
  @ApiModelProperty(value = "年级")


  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public AdministrativeVo collegeId(String collegeId) {
    this.collegeId = collegeId;
    return this;
  }

  /**
   * 学院id
   * @return collegeId
  **/
  @ApiModelProperty(value = "学院id")


  public String getCollegeId() {
    return collegeId;
  }

  public void setCollegeId(String collegeId) {
    this.collegeId = collegeId;
  }

  public AdministrativeVo page(Integer page) {
    this.page = page;
    return this;
  }

  /**
   * 当前页
   * @return page
  **/
  @ApiModelProperty(value = "当前页")


  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public AdministrativeVo rows(Integer rows) {
    this.rows = rows;
    return this;
  }

  /**
   * 每页数量
   * @return rows
  **/
  @ApiModelProperty(value = "每页数量")


  public Integer getRows() {
    return rows;
  }

  public void setRows(Integer rows) {
    this.rows = rows;
  }

  public AdministrativeVo majorId(String majorId) {
    this.majorId = majorId;
    return this;
  }

  /**
   * 专业id
   * @return majorId
  **/
  @ApiModelProperty(value = "专业id")


  public String getMajorId() {
    return majorId;
  }

  public void setMajorId(String majorId) {
    this.majorId = majorId;
  }

  public AdministrativeVo administrativeClass(String administrativeClass) {
    this.administrativeClass = administrativeClass;
    return this;
  }

  /**
   * 班级名称
   * @return administrativeClass
  **/
  @ApiModelProperty(value = "班级名称")


  public String getAdministrativeClass() {
    return administrativeClass;
  }

  public void setAdministrativeClass(String administrativeClass) {
    this.administrativeClass = administrativeClass;
  }

  public AdministrativeVo segment(Integer segment) {
    this.segment = segment;
    return this;
  }

  /**
   * 节次
   * @return segment
  **/
  @ApiModelProperty(value = "节次")


  public Integer getSegment() {
    return segment;
  }

  public void setSegment(Integer segment) {
    this.segment = segment;
  }

  public AdministrativeVo week(Integer week) {
    this.week = week;
    return this;
  }

  /**
   * 周次
   * @return week
  **/
  @ApiModelProperty(value = "周次")


  public Integer getWeek() {
    return week;
  }

  public void setWeek(Integer week) {
    this.week = week;
  }

  public AdministrativeVo weekNum(Integer weekNum) {
    this.weekNum = weekNum;
    return this;
  }

  /**
   * 周几
   * @return weekNum
  **/
  @ApiModelProperty(value = "周几")


  public Integer getWeekNum() {
    return weekNum;
  }

  public void setWeekNum(Integer weekNum) {
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
    AdministrativeVo administrativeVo = (AdministrativeVo) o;
    return Objects.equals(this.studentType, administrativeVo.studentType) &&
        Objects.equals(this.year, administrativeVo.year) &&
        Objects.equals(this.collegeId, administrativeVo.collegeId) &&
        Objects.equals(this.page, administrativeVo.page) &&
        Objects.equals(this.rows, administrativeVo.rows) &&
        Objects.equals(this.majorId, administrativeVo.majorId) &&
        Objects.equals(this.administrativeClass, administrativeVo.administrativeClass) &&
        Objects.equals(this.segment, administrativeVo.segment) &&
        Objects.equals(this.week, administrativeVo.week) &&
        Objects.equals(this.weekNum, administrativeVo.weekNum);
  }

  @Override
  public int hashCode() {
    return Objects.hash(studentType, year, collegeId, page, rows, majorId, administrativeClass, segment, week, weekNum);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdministrativeVo {\n");
    
    sb.append("    studentType: ").append(toIndentedString(studentType)).append("\n");
    sb.append("    year: ").append(toIndentedString(year)).append("\n");
    sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
    sb.append("    rows: ").append(toIndentedString(rows)).append("\n");
    sb.append("    majorId: ").append(toIndentedString(majorId)).append("\n");
    sb.append("    administrativeClass: ").append(toIndentedString(administrativeClass)).append("\n");
    sb.append("    segment: ").append(toIndentedString(segment)).append("\n");
    sb.append("    week: ").append(toIndentedString(week)).append("\n");
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

