package com.lztech.site.viewmodel.groupmember;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * GroupMemberQueryParamVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-01-06T09:01:32.299Z")


public class GroupMemberQueryParamVo   {
  @JsonProperty("page")
  private Integer page = null;

  @JsonProperty("pageSize")
  private Integer pageSize = null;

  @JsonProperty("schoolYear")
  private String schoolYear = null;

  @JsonProperty("term")
  private Integer term = null;

  @JsonProperty("courseName")
  private String courseName = null;

  @JsonProperty("className")
  private String className = null;

  @JsonProperty("studentNoOrName")
  private String studentNoOrName = null;

  public GroupMemberQueryParamVo page(Integer page) {
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

  public GroupMemberQueryParamVo pageSize(Integer pageSize) {
    this.pageSize = pageSize;
    return this;
  }

  /**
   * 每页个数
   * @return pageSize
  **/
  @ApiModelProperty(value = "每页个数")


  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public GroupMemberQueryParamVo schoolYear(String schoolYear) {
    this.schoolYear = schoolYear;
    return this;
  }

  /**
   * 学年
   * @return schoolYear
  **/
  @ApiModelProperty(value = "学年")


  public String getSchoolYear() {
    return schoolYear;
  }

  public void setSchoolYear(String schoolYear) {
    this.schoolYear = schoolYear;
  }

  public GroupMemberQueryParamVo term(Integer term) {
    this.term = term;
    return this;
  }

  /**
   * 学期
   * @return term
  **/
  @ApiModelProperty(value = "学期")


  public Integer getTerm() {
    return term;
  }

  public void setTerm(Integer term) {
    this.term = term;
  }

  public GroupMemberQueryParamVo courseName(String courseName) {
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

  public GroupMemberQueryParamVo className(String className) {
    this.className = className;
    return this;
  }

  /**
   * 班级名称
   * @return className
  **/
  @ApiModelProperty(value = "班级名称")


  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public GroupMemberQueryParamVo studentNoOrName(String studentNoOrName) {
    this.studentNoOrName = studentNoOrName;
    return this;
  }

  /**
   * 学号或姓名
   * @return studentNoOrName
  **/
  @ApiModelProperty(value = "学号或姓名")


  public String getStudentNoOrName() {
    return studentNoOrName;
  }

  public void setStudentNoOrName(String studentNoOrName) {
    this.studentNoOrName = studentNoOrName;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GroupMemberQueryParamVo groupMemberQueryParamVo = (GroupMemberQueryParamVo) o;
    return Objects.equals(this.page, groupMemberQueryParamVo.page) &&
        Objects.equals(this.pageSize, groupMemberQueryParamVo.pageSize) &&
        Objects.equals(this.schoolYear, groupMemberQueryParamVo.schoolYear) &&
        Objects.equals(this.term, groupMemberQueryParamVo.term) &&
        Objects.equals(this.courseName, groupMemberQueryParamVo.courseName) &&
        Objects.equals(this.className, groupMemberQueryParamVo.className) &&
        Objects.equals(this.studentNoOrName, groupMemberQueryParamVo.studentNoOrName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(page, pageSize, schoolYear, term, courseName, className, studentNoOrName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GroupMemberQueryParamVo {\n");
    
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
    sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
    sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
    sb.append("    term: ").append(toIndentedString(term)).append("\n");
    sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
    sb.append("    className: ").append(toIndentedString(className)).append("\n");
    sb.append("    studentNoOrName: ").append(toIndentedString(studentNoOrName)).append("\n");
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

