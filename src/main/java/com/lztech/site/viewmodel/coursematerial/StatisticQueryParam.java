package com.lztech.site.viewmodel.coursematerial;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * StatisticQueryParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-02-14T08:38:49.404Z")


public class StatisticQueryParam   {
  @JsonProperty("pageNum")
  private Integer pageNum = null;

  @JsonProperty("pageSize")
  private Integer pageSize = null;

  @JsonProperty("sort")
  private String sort = null;

  @JsonProperty("sortType")
  private Integer sortType = null;

  @JsonProperty("collegeCode")
  private String collegeCode = null;

  @JsonProperty("courseName")
  private String courseName = null;

  public StatisticQueryParam pageNum(Integer pageNum) {
    this.pageNum = pageNum;
    return this;
  }

  /**
   * 当前页码
   * @return pageNum
  **/
  @ApiModelProperty(value = "当前页码")


  public Integer getPageNum() {
    return pageNum;
  }

  public void setPageNum(Integer pageNum) {
    this.pageNum = pageNum;
  }

  public StatisticQueryParam pageSize(Integer pageSize) {
    this.pageSize = pageSize;
    return this;
  }

  /**
   * 每页记录数
   * @return pageSize
  **/
  @ApiModelProperty(value = "每页记录数")


  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public StatisticQueryParam sort(String sort) {
    this.sort = sort;
    return this;
  }

  /**
   * ASC:升序排列 DESC:降序排列
   * @return sort
  **/
  @ApiModelProperty(value = "ASC:升序排列 DESC:降序排列")


  public String getSort() {
    return sort;
  }

  public void setSort(String sort) {
    this.sort = sort;
  }

  public StatisticQueryParam sortType(Integer sortType) {
    this.sortType = sortType;
    return this;
  }

  /**
   * 排序方式 0:默认按照课程编号 1：按素材总数 2：课件 3：视频 4:图片
   * @return sortType
  **/
  @ApiModelProperty(value = "排序方式 0:默认按照课程编号 1：按素材总数 2：课件 3：视频 4:图片")


  public Integer getSortType() {
    return sortType;
  }

  public void setSortType(Integer sortType) {
    this.sortType = sortType;
  }

  public StatisticQueryParam collegeCode(String collegeCode) {
    this.collegeCode = collegeCode;
    return this;
  }

  /**
   * 学院编号
   * @return collegeCode
  **/
  @ApiModelProperty(value = "学院编号")


  public String getCollegeCode() {
    return collegeCode;
  }

  public void setCollegeCode(String collegeCode) {
    this.collegeCode = collegeCode;
  }

  public StatisticQueryParam courseName(String courseName) {
    this.courseName = courseName;
    return this;
  }

  /**
   * 课程名称
   * @return courseName
  **/
  @ApiModelProperty(value = "课程名称")


  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StatisticQueryParam statisticQueryParam = (StatisticQueryParam) o;
    return Objects.equals(this.pageNum, statisticQueryParam.pageNum) &&
        Objects.equals(this.pageSize, statisticQueryParam.pageSize) &&
        Objects.equals(this.sort, statisticQueryParam.sort) &&
        Objects.equals(this.sortType, statisticQueryParam.sortType) &&
        Objects.equals(this.collegeCode, statisticQueryParam.collegeCode) &&
        Objects.equals(this.courseName, statisticQueryParam.courseName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pageNum, pageSize, sort, sortType, collegeCode, courseName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StatisticQueryParam {\n");
    
    sb.append("    pageNum: ").append(toIndentedString(pageNum)).append("\n");
    sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
    sb.append("    sort: ").append(toIndentedString(sort)).append("\n");
    sb.append("    sortType: ").append(toIndentedString(sortType)).append("\n");
    sb.append("    collegeCode: ").append(toIndentedString(collegeCode)).append("\n");
    sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
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

