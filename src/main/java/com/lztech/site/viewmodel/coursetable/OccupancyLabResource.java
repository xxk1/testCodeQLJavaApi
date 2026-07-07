package com.lztech.site.viewmodel.coursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * OccupancyLabResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-09-20T07:52:04.558Z")


public class OccupancyLabResource   {
  @JsonProperty("roomId")
  private String roomId = null;

  public OccupancyLabResource roomId(String roomId) {
    this.roomId = roomId;
    return this;
  }

  /**
   * 教室Id
   * @return roomId
  **/
  @ApiModelProperty(value = "教室Id")


  public String getRoomId() {
    return roomId;
  }

  public void setRoomId(String roomId) {
    this.roomId = roomId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OccupancyLabResource occupancyLabResource = (OccupancyLabResource) o;
    return Objects.equals(this.roomId, occupancyLabResource.roomId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(roomId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OccupancyLabResource {\n");
    
    sb.append("    roomId: ").append(toIndentedString(roomId)).append("\n");
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

