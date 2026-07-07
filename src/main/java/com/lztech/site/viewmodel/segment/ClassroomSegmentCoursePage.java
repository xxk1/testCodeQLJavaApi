package com.lztech.site.viewmodel.segment;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ClassroomSegmentCoursePage
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-16T09:55:44.249Z")




public class ClassroomSegmentCoursePage   {
  @JsonProperty("page")
  private Integer page = null;

  @JsonProperty("pageSize")
  private Integer pageSize = null;

  @JsonProperty("pageCount")
  private Integer pageCount = null;

  @JsonProperty("totalCount")
  private Integer totalCount = null;

  @JsonProperty("weekNum")
  private String weekNum = null;

  @JsonProperty("week")
  private String week = null;

  @JsonProperty("ClassroomSegmentCourseList")
  @Valid
  private List<ClassroomSegmentCourseVo> classroomSegmentCourseList = null;

  public ClassroomSegmentCoursePage page(Integer page) {
    this.page = page;
    return this;
  }

  /**
   * 当前页码
   * @return page
  **/
  @ApiModelProperty(value = "当前页码")


  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public ClassroomSegmentCoursePage pageSize(Integer pageSize) {
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

  public ClassroomSegmentCoursePage pageCount(Integer pageCount) {
    this.pageCount = pageCount;
    return this;
  }

  /**
   * 总页数
   * @return pageCount
  **/
  @ApiModelProperty(value = "总页数")


  public Integer getPageCount() {
    return pageCount;
  }

  public void setPageCount(Integer pageCount) {
    this.pageCount = pageCount;
  }

  public ClassroomSegmentCoursePage totalCount(Integer totalCount) {
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

  public ClassroomSegmentCoursePage weekNum(String weekNum) {
    this.weekNum = weekNum;
    return this;
  }

  /**
   * 第几周
   * @return weekNum
  **/
  @ApiModelProperty(value = "第几周")


  public String getWeekNum() {
    return weekNum;
  }

  public void setWeekNum(String weekNum) {
    this.weekNum = weekNum;
  }

  public ClassroomSegmentCoursePage week(String week) {
    this.week = week;
    return this;
  }

  /**
   * 星期几
   * @return week
  **/
  @ApiModelProperty(value = "星期几")


  public String getWeek() {
    return week;
  }

  public void setWeek(String week) {
    this.week = week;
  }

  public ClassroomSegmentCoursePage classroomSegmentCourseList(List<ClassroomSegmentCourseVo> classroomSegmentCourseList) {
    this.classroomSegmentCourseList = classroomSegmentCourseList;
    return this;
  }

  public ClassroomSegmentCoursePage addClassroomSegmentCourseListItem(ClassroomSegmentCourseVo classroomSegmentCourseListItem) {
    if (this.classroomSegmentCourseList == null) {
      this.classroomSegmentCourseList = new ArrayList<ClassroomSegmentCourseVo>();
    }
    this.classroomSegmentCourseList.add(classroomSegmentCourseListItem);
    return this;
  }

  /**
   * Get classroomSegmentCourseList
   * @return classroomSegmentCourseList
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<ClassroomSegmentCourseVo> getClassroomSegmentCourseList() {
    return classroomSegmentCourseList;
  }

  public void setClassroomSegmentCourseList(List<ClassroomSegmentCourseVo> classroomSegmentCourseList) {
    this.classroomSegmentCourseList = classroomSegmentCourseList;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ClassroomSegmentCoursePage classroomSegmentCoursePage = (ClassroomSegmentCoursePage) o;
    return Objects.equals(this.page, classroomSegmentCoursePage.page) &&
        Objects.equals(this.pageSize, classroomSegmentCoursePage.pageSize) &&
        Objects.equals(this.pageCount, classroomSegmentCoursePage.pageCount) &&
        Objects.equals(this.totalCount, classroomSegmentCoursePage.totalCount) &&
        Objects.equals(this.weekNum, classroomSegmentCoursePage.weekNum) &&
        Objects.equals(this.week, classroomSegmentCoursePage.week) &&
        Objects.equals(this.classroomSegmentCourseList, classroomSegmentCoursePage.classroomSegmentCourseList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(page, pageSize, pageCount, totalCount, weekNum, week, classroomSegmentCourseList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ClassroomSegmentCoursePage {\n");
    
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
    sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
    sb.append("    pageCount: ").append(toIndentedString(pageCount)).append("\n");
    sb.append("    totalCount: ").append(toIndentedString(totalCount)).append("\n");
    sb.append("    weekNum: ").append(toIndentedString(weekNum)).append("\n");
    sb.append("    week: ").append(toIndentedString(week)).append("\n");
    sb.append("    classroomSegmentCourseList: ").append(toIndentedString(classroomSegmentCourseList)).append("\n");
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

