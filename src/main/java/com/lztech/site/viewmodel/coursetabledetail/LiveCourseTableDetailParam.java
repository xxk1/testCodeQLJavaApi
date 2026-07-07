package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * LiveCourseTableDetailParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-08-19T07:53:44.212Z")


public class LiveCourseTableDetailParam   {
  @JsonProperty("courseTableCustomIdList")
  @Valid
  private List<String> courseTableCustomIdList = null;

  @JsonProperty("studentType")
  private String studentType = null;

  @JsonProperty("courseCode")
  private String courseCode = null;

  @JsonProperty("authorizationScopeType")
  private Integer authorizationScopeType = null;

  @JsonProperty("courseDate")
  private String courseDate = null;

  public LiveCourseTableDetailParam courseTableCustomIdList(List<String> courseTableCustomIdList) {
    this.courseTableCustomIdList = courseTableCustomIdList;
    return this;
  }

  public LiveCourseTableDetailParam addCourseTableCustomIdListItem(String courseTableCustomIdListItem) {
    if (this.courseTableCustomIdList == null) {
      this.courseTableCustomIdList = new ArrayList<String>();
    }
    this.courseTableCustomIdList.add(courseTableCustomIdListItem);
    return this;
  }

  /**
   * 课表自定义id集合（为空则获取全部开课类型为本科的正在直播课表信息）
   * @return courseTableCustomIdList
  **/
  @ApiModelProperty(value = "课表自定义id集合（为空则获取全部开课类型为本科的正在直播课表信息）")


  public List<String> getCourseTableCustomIdList() {
    return courseTableCustomIdList;
  }

  public void setCourseTableCustomIdList(List<String> courseTableCustomIdList) {
    this.courseTableCustomIdList = courseTableCustomIdList;
  }

  public LiveCourseTableDetailParam studentType(String studentType) {
    this.studentType = studentType;
    return this;
  }

  /**
   * 课程开课类型（为空则获取全部）：0：本科生 1：研究生
   * @return studentType
  **/
  @ApiModelProperty(value = "课程开课类型（为空则获取全部）：0：本科生 1：研究生")


  public String getStudentType() {
    return studentType;
  }

  public void setStudentType(String studentType) {
    this.studentType = studentType;
  }

  public LiveCourseTableDetailParam courseCode(String courseCode) {
    this.courseCode = courseCode;
    return this;
  }

  /**
   * 课程编号
   * @return courseCode
  **/
  @ApiModelProperty(value = "课程编号")


  public String getCourseCode() {
    return courseCode;
  }

  public void setCourseCode(String courseCode) {
    this.courseCode = courseCode;
  }

  public LiveCourseTableDetailParam authorizationScopeType(Integer authorizationScopeType) {
    this.authorizationScopeType = authorizationScopeType;
    return this;
  }

  /**
   * 是否授权全部课程范围,0：授权全部课程范围，1：授权直播课程范围，2：授权点播课程范围
   * @return authorizationScopeType
  **/
  @ApiModelProperty(value = "是否授权全部课程范围,0：授权全部课程范围，1：授权直播课程范围，2：授权点播课程范围")


  public Integer getAuthorizationScopeType() {
    return authorizationScopeType;
  }

  public void setAuthorizationScopeType(Integer authorizationScopeType) {
    this.authorizationScopeType = authorizationScopeType;
  }

  public LiveCourseTableDetailParam courseDate(String courseDate) {
    this.courseDate = courseDate;
    return this;
  }

  /**
   * 课程日期（格式：yyyy-MM-dd）
   * @return courseDate
  **/
  @ApiModelProperty(value = "课程日期（格式：yyyy-MM-dd）")


  public String getCourseDate() {
    return courseDate;
  }

  public void setCourseDate(String courseDate) {
    this.courseDate = courseDate;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LiveCourseTableDetailParam liveCourseTableDetailParam = (LiveCourseTableDetailParam) o;
    return Objects.equals(this.courseTableCustomIdList, liveCourseTableDetailParam.courseTableCustomIdList) &&
        Objects.equals(this.studentType, liveCourseTableDetailParam.studentType) &&
        Objects.equals(this.courseCode, liveCourseTableDetailParam.courseCode) &&
        Objects.equals(this.authorizationScopeType, liveCourseTableDetailParam.authorizationScopeType) &&
        Objects.equals(this.courseDate, liveCourseTableDetailParam.courseDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(courseTableCustomIdList, studentType, courseCode, authorizationScopeType, courseDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LiveCourseTableDetailParam {\n");
    
    sb.append("    courseTableCustomIdList: ").append(toIndentedString(courseTableCustomIdList)).append("\n");
    sb.append("    studentType: ").append(toIndentedString(studentType)).append("\n");
    sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
    sb.append("    authorizationScopeType: ").append(toIndentedString(authorizationScopeType)).append("\n");
    sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
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

