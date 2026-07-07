package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * SegmentCourseTableResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-24T03:19:23.567Z")




public class SegmentCourseTableResource   {
  @JsonProperty("roomName")
  private String roomName = null;

  @JsonProperty("roomId")
  private String roomId = null;

  @JsonProperty("courseDate")
  private String courseDate = null;

  @JsonProperty("week")
  private String week = null;

  @JsonProperty("weekNum")
  private Integer weekNum = null;

  @JsonProperty("segment")
  private String segment = null;

  public SegmentCourseTableResource roomName(String roomName) {
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

  public SegmentCourseTableResource roomId(String roomId) {
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

  public SegmentCourseTableResource courseDate(String courseDate) {
    this.courseDate = courseDate;
    return this;
  }

  /**
   * 上课日期
   * @return courseDate
  **/
  @ApiModelProperty(value = "上课日期")


  public String getCourseDate() {
    return courseDate;
  }

  public void setCourseDate(String courseDate) {
    this.courseDate = courseDate;
  }

  public SegmentCourseTableResource week(String week) {
    this.week = week;
    return this;
  }

  /**
   * 第几周
   * @return week
  **/
  @ApiModelProperty(value = "第几周")


  public String getWeek() {
    return week;
  }

  public void setWeek(String week) {
    this.week = week;
  }

  public SegmentCourseTableResource weekNum(Integer weekNum) {
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

  public SegmentCourseTableResource segment(String segment) {
    this.segment = segment;
    return this;
  }

  /**
   * 节次
   * @return segment
  **/
  @ApiModelProperty(value = "节次")


  public String getSegment() {
    return segment;
  }

  public void setSegment(String segment) {
    this.segment = segment;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SegmentCourseTableResource segmentCourseTableResource = (SegmentCourseTableResource) o;
    return Objects.equals(this.roomName, segmentCourseTableResource.roomName) &&
        Objects.equals(this.roomId, segmentCourseTableResource.roomId) &&
        Objects.equals(this.courseDate, segmentCourseTableResource.courseDate) &&
        Objects.equals(this.week, segmentCourseTableResource.week) &&
        Objects.equals(this.weekNum, segmentCourseTableResource.weekNum) &&
        Objects.equals(this.segment, segmentCourseTableResource.segment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(roomName, roomId, courseDate, week, weekNum, segment);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SegmentCourseTableResource {\n");
    
    sb.append("    roomName: ").append(toIndentedString(roomName)).append("\n");
    sb.append("    roomId: ").append(toIndentedString(roomId)).append("\n");
    sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
    sb.append("    week: ").append(toIndentedString(week)).append("\n");
    sb.append("    weekNum: ").append(toIndentedString(weekNum)).append("\n");
    sb.append("    segment: ").append(toIndentedString(segment)).append("\n");
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

