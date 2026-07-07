package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseSegmentInfoVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-08-31T12:13:28.330Z")


public class CourseSegmentInfoVo {
  @JsonProperty("courseTableDetaiId")
  private String courseTableDetaiId = null;

  @JsonProperty("courseDate")
  private String courseDate = null;

  @JsonProperty("segment")
  private String segment = null;

  @JsonProperty("roomId")
  private String roomId = null;

  @JsonProperty("roomName")
  private String roomName = null;

  @JsonProperty("term")
  private Integer term = null;


  @JsonProperty("schoolYear")
  private String schoolYear = null;

  @JsonProperty("collegeId")
  private String collegeId = null;

  @JsonProperty("collegeName")
  private String collegeName = null;


  public CourseSegmentInfoVo courseTableDetaiId(String courseTableDetaiId) {
    this.courseTableDetaiId = courseTableDetaiId;
    return this;
  }

  /**
   * 课表ID
   * @return courseTableDetaiId
  **/
  @ApiModelProperty(value = "课表ID")


  public String getCourseTableDetaiId() {
    return courseTableDetaiId;
  }

  public void setCourseTableDetaiId(String courseTableDetaiId) {
    this.courseTableDetaiId = courseTableDetaiId;
  }

  public CourseSegmentInfoVo courseDate(String courseDate) {
    this.courseDate = courseDate;
    return this;
  }

  /**
   * 日期(2021-08-20)
   * @return courseDate
  **/
  @ApiModelProperty(value = "日期(2021-08-20)")


  public String getCourseDate() {
    return courseDate;
  }

  public void setCourseDate(String courseDate) {
    this.courseDate = courseDate;
  }

  public CourseSegmentInfoVo segment(String segment) {
    this.segment = segment;
    return this;
  }

  /**
   * 节次(9,10,11,12)
   * @return segment
  **/
  @ApiModelProperty(value = "节次(9,10,11,12)")


  public String getSegment() {
    return segment;
  }

  public void setSegment(String segment) {
    this.segment = segment;
  }

  public CourseSegmentInfoVo roomId(String roomId) {
    this.roomId = roomId;
    return this;
  }

  /**
   * 教室ID
   * @return roomId
  **/
  @ApiModelProperty(value = "教室ID")


  public String getRoomId() {
    return roomId;
  }

  public void setRoomId(String roomId) {
    this.roomId = roomId;
  }

  public CourseSegmentInfoVo roomName(String roomName) {
    this.roomName = roomName;
    return this;
  }

  /**
   * 教室名称
   * @return roomName
  **/
  @ApiModelProperty(value = "教室名称")


  public String getRoomName() {
    return roomName;
  }

  public void setRoomName(String roomName) {
    this.roomName = roomName;
  }

  public CourseSegmentInfoVo term(Integer term) {
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

  public CourseSegmentInfoVo schoolYear(String schoolYear) {
    this.schoolYear = schoolYear;
    return this;
  }

  /**
   * 学年
   * @return term
   **/
  @ApiModelProperty(value = "学年")


  public String getSchoolYear() {
    return schoolYear;
  }

  public void setSchoolYear(String schoolYear) {
    this.schoolYear = schoolYear;
  }

  public CourseSegmentInfoVo collegeId(String collegeId) {
    this.collegeId = collegeId;
    return this;
  }

  /**
   * 学院ID
   * @return term
   **/
  @ApiModelProperty(value = "学院ID")


  public String getCollegeId() {
    return collegeId;
  }

  public void setCollegeId(String collegeId) {
    this.collegeId = collegeId;
  }

  public CourseSegmentInfoVo collegeName(String collegeName) {
    this.collegeName = collegeName;
    return this;
  }

  /**
   * 学院名称
   * @return term
   **/
  @ApiModelProperty(value = "学院名臣")


  public String getCollegeName() {
    return collegeName;
  }

  public void setCollegeName(String collegeName) {
    this.collegeName = collegeName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CourseSegmentInfoVo courseSegmentInfoVo = (CourseSegmentInfoVo) o;
    return Objects.equals(this.courseTableDetaiId, courseSegmentInfoVo.courseTableDetaiId) &&
        Objects.equals(this.courseDate, courseSegmentInfoVo.courseDate) &&
        Objects.equals(this.segment, courseSegmentInfoVo.segment) &&
        Objects.equals(this.roomId, courseSegmentInfoVo.roomId) &&
        Objects.equals(this.roomName, courseSegmentInfoVo.roomName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(courseTableDetaiId, courseDate, segment, roomId, roomName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CourseSegmentInfoVo {\n");
    
    sb.append("    courseTableDetaiId: ").append(toIndentedString(courseTableDetaiId)).append("\n");
    sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
    sb.append("    segment: ").append(toIndentedString(segment)).append("\n");
    sb.append("    roomId: ").append(toIndentedString(roomId)).append("\n");
    sb.append("    roomName: ").append(toIndentedString(roomName)).append("\n");
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

