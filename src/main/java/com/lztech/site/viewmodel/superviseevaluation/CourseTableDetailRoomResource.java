package com.lztech.site.viewmodel.superviseevaluation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseTableDetailRoomResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-12-10T08:57:08.369Z")


public class CourseTableDetailRoomResource   {
  @JsonProperty("roomId")
  private String roomId = null;

  @JsonProperty("roomName")
  private String roomName = null;

  public CourseTableDetailRoomResource roomId(String roomId) {
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

  public CourseTableDetailRoomResource roomName(String roomName) {
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CourseTableDetailRoomResource courseTableDetailRoomResource = (CourseTableDetailRoomResource) o;
    return Objects.equals(this.roomId, courseTableDetailRoomResource.roomId) &&
        Objects.equals(this.roomName, courseTableDetailRoomResource.roomName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(roomId, roomName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CourseTableDetailRoomResource {\n");
    
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

