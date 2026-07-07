package com.lztech.site.viewmodel.coursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * RoomResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-08-18T06:51:28.762Z")


public class RoomResource {
  @JsonProperty("roomId")
  private String roomId = null;

  @JsonProperty("roomCode")
  private String roomCode = null;

  @JsonProperty("roomName")
  private String roomName = null;

  @JsonProperty("courseDate")
  private String courseDate = null;

  @JsonProperty("courseSegments")
  private String courseSegments = null;

  public RoomResource roomId(String roomId) {
    this.roomId = roomId;
    return this;
  }

  /**
   * 教室Id
   *
   * @return roomId
   **/
  @ApiModelProperty(value = "教室Id")


  public String getRoomId() {
    return roomId;
  }

  public void setRoomId(String roomId) {
    this.roomId = roomId;
  }

  public RoomResource roomCode(String roomCode) {
    this.roomCode = roomCode;
    return this;
  }

  /**
   * 教室编号
   *
   * @return roomCode
   **/
  @ApiModelProperty(value = "教室编号")


  public String getRoomCode() {
    return roomCode;
  }

  public void setRoomCode(String roomCode) {
    this.roomCode = roomCode;
  }

  public RoomResource roomName(String roomName) {
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

  public RoomResource courseDate(String courseDate) {
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

  public RoomResource courseSegments(String courseSegments) {
    this.courseSegments = courseSegments;
    return this;
  }

  /**
   * 课程节次（多个“,”拼接）
   *
   * @return courseSegments
   **/
  @ApiModelProperty(value = "课程节次（多个“,”拼接）")


  public String getCourseSegments() {
    return courseSegments;
  }

  public void setCourseSegments(String courseSegments) {
    this.courseSegments = courseSegments;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RoomResource roomResource = (RoomResource) o;
    return Objects.equals(this.roomId, roomResource.roomId) &&
            Objects.equals(this.roomCode, roomResource.roomCode) &&
            Objects.equals(this.roomName, roomResource.roomName) &&
            Objects.equals(this.courseDate, roomResource.courseDate) &&
            Objects.equals(this.courseSegments, roomResource.courseSegments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(roomId, roomCode, roomName, courseDate, courseSegments);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RoomResource {\n");

    sb.append("    roomId: ").append(toIndentedString(roomId)).append("\n");
    sb.append("    roomCode: ").append(toIndentedString(roomCode)).append("\n");
    sb.append("    roomName: ").append(toIndentedString(roomName)).append("\n");
    sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
    sb.append("    courseSegments: ").append(toIndentedString(courseSegments)).append("\n");
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

