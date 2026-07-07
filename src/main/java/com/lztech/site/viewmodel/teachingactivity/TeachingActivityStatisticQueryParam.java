package com.lztech.site.viewmodel.teachingactivity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * StatisticQueryParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-03-28T08:23:44.244Z")


public class TeachingActivityStatisticQueryParam {
  @JsonProperty("pageNum")
  private Integer pageNum = null;

  @JsonProperty("pageSize")
  private Integer pageSize = null;

  @JsonProperty("sort")
  private String sort = null;

  @JsonProperty("sortType")
  private Integer sortType = null;

  @JsonProperty("collegeId")
  private String collegeId = null;

  @JsonProperty("courseNameOrNo")
  private String courseNameOrNo = null;

  public TeachingActivityStatisticQueryParam pageNum(Integer pageNum) {
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

  public TeachingActivityStatisticQueryParam pageSize(Integer pageSize) {
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

  public TeachingActivityStatisticQueryParam sort(String sort) {
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

  public TeachingActivityStatisticQueryParam sortType(Integer sortType) {
    this.sortType = sortType;
    return this;
  }

  /**
   * 排序方式 0:默认按照课程编号 1：按活动总数 2：考核测验 3：讨论主题
   * @return sortType
  **/
  @ApiModelProperty(value = "排序方式 0:默认按照课程编号 1：按活动总数 2：考核测验 3：讨论主题")


  public Integer getSortType() {
    return sortType;
  }

  public void setSortType(Integer sortType) {
    this.sortType = sortType;
  }

  public TeachingActivityStatisticQueryParam collegeId(String collegeId) {
    this.collegeId = collegeId;
    return this;
  }

  /**
   * 学院ID
   * @return collegeId
  **/
  @ApiModelProperty(value = "学院ID")


  public String getCollegeId() {
    return collegeId;
  }

  public void setCollegeId(String collegeId) {
    this.collegeId = collegeId;
  }

  public TeachingActivityStatisticQueryParam courseNameOrNo(String courseNameOrNo) {
    this.courseNameOrNo = courseNameOrNo;
    return this;
  }

  /**
   * 课程名称
   * @return courseNameOrNo
  **/
  @ApiModelProperty(value = "课程名称")


  public String getCourseNameOrNo() {
    return courseNameOrNo;
  }

  public void setCourseNameOrNo(String courseNameOrNo) {
    this.courseNameOrNo = courseNameOrNo;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TeachingActivityStatisticQueryParam teachingActivityStatisticQueryParam = (TeachingActivityStatisticQueryParam) o;
    return Objects.equals(this.pageNum, teachingActivityStatisticQueryParam.pageNum) &&
        Objects.equals(this.pageSize, teachingActivityStatisticQueryParam.pageSize) &&
        Objects.equals(this.sort, teachingActivityStatisticQueryParam.sort) &&
        Objects.equals(this.sortType, teachingActivityStatisticQueryParam.sortType) &&
        Objects.equals(this.collegeId, teachingActivityStatisticQueryParam.collegeId) &&
        Objects.equals(this.courseNameOrNo, teachingActivityStatisticQueryParam.courseNameOrNo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pageNum, pageSize, sort, sortType, collegeId, courseNameOrNo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StatisticQueryParam {\n");
    
    sb.append("    pageNum: ").append(toIndentedString(pageNum)).append("\n");
    sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
    sb.append("    sort: ").append(toIndentedString(sort)).append("\n");
    sb.append("    sortType: ").append(toIndentedString(sortType)).append("\n");
    sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
    sb.append("    courseNameOrNo: ").append(toIndentedString(courseNameOrNo)).append("\n");
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

