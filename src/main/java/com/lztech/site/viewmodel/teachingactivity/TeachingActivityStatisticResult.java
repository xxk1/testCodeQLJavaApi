package com.lztech.site.viewmodel.teachingactivity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * StatisticResult
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-03-28T08:23:44.244Z")


public class TeachingActivityStatisticResult {
  @JsonProperty("pageNum")
  private Integer pageNum = null;

  @JsonProperty("pageSize")
  private Integer pageSize = null;

  @JsonProperty("totalCount")
  private Integer totalCount = null;

  @JsonProperty("courseResourceStatisticList")
  @Valid
  private List<TeachingActivityStatisticVo> teachingActivityStatisticList = null;

  public TeachingActivityStatisticResult pageNum(Integer pageNum) {
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

  public TeachingActivityStatisticResult pageSize(Integer pageSize) {
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

  public TeachingActivityStatisticResult totalCount(Integer totalCount) {
    this.totalCount = totalCount;
    return this;
  }

  /**
   * 总条数
   * @return totalCount
  **/
  @ApiModelProperty(value = "总条数")


  public Integer getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(Integer totalCount) {
    this.totalCount = totalCount;
  }

  public TeachingActivityStatisticResult courseResourceStatisticList(List<TeachingActivityStatisticVo> courseResourceStatisticList) {
    this.teachingActivityStatisticList = courseResourceStatisticList;
    return this;
  }

  public TeachingActivityStatisticResult addCourseResourceStatisticListItem(TeachingActivityStatisticVo courseResourceStatisticListItem) {
    if (this.teachingActivityStatisticList == null) {
      this.teachingActivityStatisticList = new ArrayList<TeachingActivityStatisticVo>();
    }
    this.teachingActivityStatisticList.add(courseResourceStatisticListItem);
    return this;
  }

  /**
   * Get courseResourceStatisticList
   * @return courseResourceStatisticList
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<TeachingActivityStatisticVo> getTeachingActivityStatisticList() {
    return teachingActivityStatisticList;
  }

  public void setTeachingActivityStatisticList(List<TeachingActivityStatisticVo> teachingActivityStatisticList) {
    this.teachingActivityStatisticList = teachingActivityStatisticList;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TeachingActivityStatisticResult teachingActivityStatisticResult = (TeachingActivityStatisticResult) o;
    return Objects.equals(this.pageNum, teachingActivityStatisticResult.pageNum) &&
        Objects.equals(this.pageSize, teachingActivityStatisticResult.pageSize) &&
        Objects.equals(this.totalCount, teachingActivityStatisticResult.totalCount) &&
        Objects.equals(this.teachingActivityStatisticList, teachingActivityStatisticResult.teachingActivityStatisticList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pageNum, pageSize, totalCount, teachingActivityStatisticList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StatisticResult {\n");
    
    sb.append("    pageNum: ").append(toIndentedString(pageNum)).append("\n");
    sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
    sb.append("    totalCount: ").append(toIndentedString(totalCount)).append("\n");
    sb.append("    courseResourceStatisticList: ").append(toIndentedString(teachingActivityStatisticList)).append("\n");
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

