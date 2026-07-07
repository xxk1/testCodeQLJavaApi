package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * RoomScheduleInformation
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-16T05:55:37.752Z")


public class RoomScheduleInformation {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("roomId")
  private String roomId = null;

  @JsonProperty("roomCode")
  private String roomCode = null;

  @JsonProperty("roomName")
  private String roomName = null;

  @JsonProperty("courseDate")
  private String courseDate = null;

  @JsonProperty("segmentStartTime")
  private String segmentStartTime = null;

  @JsonProperty("segmentEndTime")
  private String segmentEndTime = null;

  public RoomScheduleInformation id(String id) {
    this.id = id;
    return this;
  }

  /**
   * 课表明细id
   *
   * @return id
   **/
  @ApiModelProperty(value = "课表明细id")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public RoomScheduleInformation roomId(String roomId) {
    this.roomId = roomId;
    return this;
  }

  /**
   * 上课教室Id
   *
   * @return roomId
   **/
  @ApiModelProperty(value = "上课教室Id")


  public String getRoomId() {
    return roomId;
  }

  public void setRoomId(String roomId) {
    this.roomId = roomId;
  }

  public RoomScheduleInformation roomCode(String roomCode) {
    this.roomCode = roomCode;
    return this;
  }

  /**
   * 上课教室编号
   *
   * @return roomCode
   **/
  @ApiModelProperty(value = "上课教室编号")


  public String getRoomCode() {
    return roomCode;
  }

  public void setRoomCode(String roomCode) {
    this.roomCode = roomCode;
  }

  public RoomScheduleInformation roomName(String roomName) {
    this.roomName = roomName;
    return this;
  }

  /**
   * 上课教室名称
   *
   * @return roomName
   **/
  @ApiModelProperty(value = "上课教室名称")


  public String getRoomName() {
    return roomName;
  }

  public void setRoomName(String roomName) {
    this.roomName = roomName;
  }

  public RoomScheduleInformation courseDate(String courseDate) {
    this.courseDate = courseDate;
    return this;
  }

  /**
   * 课程日期
   *
   * @return courseDate
   **/
  @ApiModelProperty(value = "课程日期")


  public String getCourseDate() {
    return courseDate;
  }

  public void setCourseDate(String courseDate) {
    this.courseDate = courseDate;
  }

  public RoomScheduleInformation segmentStartTime(String segmentStartTime) {
    this.segmentStartTime = segmentStartTime;
    return this;
  }

  /**
   * 节次开始时间
   *
   * @return segmentStartTime
   **/
  @ApiModelProperty(value = "节次开始时间")


  public String getSegmentStartTime() {
    return segmentStartTime;
  }

  public void setSegmentStartTime(String segmentStartTime) {
    this.segmentStartTime = segmentStartTime;
  }

  public RoomScheduleInformation segmentEndTime(String segmentEndTime) {
    this.segmentEndTime = segmentEndTime;
    return this;
  }

  /**
   * 节次结束时间
   *
   * @return segmentEndTime
   **/
  @ApiModelProperty(value = "节次结束时间")


  public String getSegmentEndTime() {
    return segmentEndTime;
  }

  public void setSegmentEndTime(String segmentEndTime) {
    this.segmentEndTime = segmentEndTime;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RoomScheduleInformation roomScheduleInformation = (RoomScheduleInformation) o;
    return Objects.equals(this.id, roomScheduleInformation.id) &&
            Objects.equals(this.roomId, roomScheduleInformation.roomId) &&
            Objects.equals(this.roomCode, roomScheduleInformation.roomCode) &&
            Objects.equals(this.roomName, roomScheduleInformation.roomName) &&
            Objects.equals(this.courseDate, roomScheduleInformation.courseDate) &&
            Objects.equals(this.segmentStartTime, roomScheduleInformation.segmentStartTime) &&
            Objects.equals(this.segmentEndTime, roomScheduleInformation.segmentEndTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, roomId, roomCode, roomName, courseDate, segmentStartTime, segmentEndTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RoomScheduleInformation {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    roomId: ").append(toIndentedString(roomId)).append("\n");
    sb.append("    roomCode: ").append(toIndentedString(roomCode)).append("\n");
    sb.append("    roomName: ").append(toIndentedString(roomName)).append("\n");
    sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
    sb.append("    segmentStartTime: ").append(toIndentedString(segmentStartTime)).append("\n");
    sb.append("    segmentEndTime: ").append(toIndentedString(segmentEndTime)).append("\n");
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

