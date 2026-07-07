package com.lztech.site.viewmodel.segment;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseQueryParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-16T09:37:21.481Z")




public class CourseQueryParam   {
  @JsonProperty("buildingId")
  private String buildingId = null;

  @JsonProperty("roomId")
  private String roomId = null;

  @JsonProperty("collegeId")
  private String collegeId = null;

  @JsonProperty("courseOrTeacherName")
  private String courseOrTeacherName = null;

  @JsonProperty("segment")
  private Integer segment = null;

  @JsonProperty("searchDate")
  private String searchDate = null;

  @JsonProperty("validecode")
  private String validecode = null;

  @JsonProperty("page")
  private Integer page = null;

  @JsonProperty("pageSize")
  private Integer pageSize = null;

  public CourseQueryParam buildingId(String buildingId) {
    this.buildingId = buildingId;
    return this;
  }

  /**
   * 楼栋id
   * @return buildingId
  **/
  @ApiModelProperty(value = "楼栋id")


  public String getBuildingId() {
    return buildingId;
  }

  public void setBuildingId(String buildingId) {
    this.buildingId = buildingId;
  }

  public CourseQueryParam roomId(String roomId) {
    this.roomId = roomId;
    return this;
  }

  /**
   * 教室id
   * @return roomId
  **/
  @ApiModelProperty(value = "教室id")


  public String getRoomId() {
    return roomId;
  }

  public void setRoomId(String roomId) {
    this.roomId = roomId;
  }

  public CourseQueryParam collegeId(String collegeId) {
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

  public CourseQueryParam courseOrTeacherName(String courseOrTeacherName) {
    this.courseOrTeacherName = courseOrTeacherName;
    return this;
  }

  /**
   * 课程/教师名称
   * @return courseOrTeacherName
  **/
  @ApiModelProperty(value = "课程/教师名称")


  public String getCourseOrTeacherName() {
    return courseOrTeacherName;
  }

  public void setCourseOrTeacherName(String courseOrTeacherName) {
    this.courseOrTeacherName = courseOrTeacherName;
  }

  public CourseQueryParam segment(Integer segment) {
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

  public CourseQueryParam searchDate(String searchDate) {
    this.searchDate = searchDate;
    return this;
  }

  /**
   * 查询日期
   * @return searchDate
  **/
  @ApiModelProperty(value = "查询日期")


  public String getSearchDate() {
    return searchDate;
  }

  public void setSearchDate(String searchDate) {
    this.searchDate = searchDate;
  }

  public CourseQueryParam validecode(String validecode) {
    this.validecode = validecode;
    return this;
  }

  /**
   * 验证码md5加密
   * @return validecode
  **/
  @ApiModelProperty(value = "验证码md5加密")


  public String getValidecode() {
    return validecode;
  }

  public void setValidecode(String validecode) {
    this.validecode = validecode;
  }

  public CourseQueryParam page(Integer page) {
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

  public CourseQueryParam pageSize(Integer pageSize) {
    this.pageSize = pageSize;
    return this;
  }

  /**
   * 页大小
   * @return pageSize
  **/
  @ApiModelProperty(value = "页大小")


  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CourseQueryParam courseQueryParam = (CourseQueryParam) o;
    return Objects.equals(this.buildingId, courseQueryParam.buildingId) &&
        Objects.equals(this.roomId, courseQueryParam.roomId) &&
        Objects.equals(this.collegeId, courseQueryParam.collegeId) &&
        Objects.equals(this.courseOrTeacherName, courseQueryParam.courseOrTeacherName) &&
        Objects.equals(this.segment, courseQueryParam.segment) &&
        Objects.equals(this.searchDate, courseQueryParam.searchDate) &&
        Objects.equals(this.validecode, courseQueryParam.validecode) &&
        Objects.equals(this.page, courseQueryParam.page) &&
        Objects.equals(this.pageSize, courseQueryParam.pageSize);
  }

  @Override
  public int hashCode() {
    return Objects.hash(buildingId, roomId, collegeId, courseOrTeacherName, segment, searchDate, validecode, page, pageSize);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CourseQueryParam {\n");
    
    sb.append("    buildingId: ").append(toIndentedString(buildingId)).append("\n");
    sb.append("    roomId: ").append(toIndentedString(roomId)).append("\n");
    sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
    sb.append("    courseOrTeacherName: ").append(toIndentedString(courseOrTeacherName)).append("\n");
    sb.append("    segment: ").append(toIndentedString(segment)).append("\n");
    sb.append("    searchDate: ").append(toIndentedString(searchDate)).append("\n");
    sb.append("    validecode: ").append(toIndentedString(validecode)).append("\n");
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
    sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
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

