package com.lztech.site.viewmodel.experimentschedulecoursetabledetail;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * RoomSegmentTimeVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-09-12T12:21:44.947Z")
public class RoomSegmentTimeVo {

    @JsonProperty("roomId")
    private String roomId = null;

    @JsonProperty("roomName")
    private String roomName = null;

    @JsonProperty("startSegment")
    private Integer startSegment = null;

    @JsonProperty("endSegment")
    private Integer endSegment = null;

    @JsonProperty("segmentStartTime")
    private String segmentStartTime = null ;

    @JsonProperty("segmentEndTime")
    private String segmentEndTime = null;

    public RoomSegmentTimeVo roomId(String roomId) {
        this.roomId = roomId;
        return this;
    }

    /**
     * 教室id
     *
     * @return roomId
     **/
    @ApiModelProperty(value = "教室id", position = 1, required = true)

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public RoomSegmentTimeVo roomName(String roomName) {
        this.roomName = roomName;
        return this;
    }

    /**
     * 教室名称
     *
     * @return roomName
     **/
    @ApiModelProperty(value = "教室名称", position = 2, required = true)

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public RoomSegmentTimeVo startSegment(Integer startSegment) {
        this.startSegment = startSegment;
        return this;
    }

    /**
     * 开始节次
     *
     * @return startSegment
     **/
    @ApiModelProperty(value = "开始节次", position = 3, required = true)

    public Integer getStartSegment() {
        return startSegment;
    }

    public void setStartSegment(Integer startSegment) {
        this.startSegment = startSegment;
    }

    public RoomSegmentTimeVo endSegment(Integer endSegment) {
        this.endSegment = endSegment;
        return this;
    }

    /**
     * 结束节次
     *
     * @return endSegment
     **/
    @ApiModelProperty(value = "结束节次", position = 4, required = true)

    public Integer getEndSegment() {
        return endSegment;
    }

    public void setEndSegment(Integer endSegment) {
        this.endSegment = endSegment;
    }

    public RoomSegmentTimeVo segmentStartTime(String segmentStartTime) {
        this.segmentStartTime = segmentStartTime;
        return this;
    }

    /**
     * 开始节次时间
     *
     * @return segmentStartTime
     **/
    @ApiModelProperty(value = "开始节次时间", position = 5, required = true)

    public String getSegmentStartTime() {
        return segmentStartTime;
    }

    public void setSegmentStartTime(String segmentStartTime) {
        this.segmentStartTime = segmentStartTime;
    }

    public RoomSegmentTimeVo segmentEndTime(String segmentEndTime) {
        this.segmentEndTime = segmentEndTime;
        return this;
    }

    /**
     * 结束节次时间
     *
     * @return segmentEndTime
     **/
    @ApiModelProperty(value = "结束节次时间", position = 6, required = true)

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
        RoomSegmentTimeVo roomSegmentTimeVo = (RoomSegmentTimeVo) o;
        return Objects.equals(this.roomId, roomSegmentTimeVo.roomId) &&
                Objects.equals(this.roomName, roomSegmentTimeVo.roomName) &&
                Objects.equals(this.startSegment, roomSegmentTimeVo.startSegment) &&
                Objects.equals(this.endSegment, roomSegmentTimeVo.endSegment) &&
                Objects.equals(this.segmentStartTime, roomSegmentTimeVo.segmentStartTime) &&
                Objects.equals(this.segmentEndTime, roomSegmentTimeVo.segmentEndTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, roomName, startSegment, endSegment, segmentStartTime, segmentEndTime);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RoomSegmentTimeVo {\n");
        sb.append("    roomId: ").append(toIndentedString(roomId)).append("\n");
        sb.append("    roomName: ").append(toIndentedString(roomName)).append("\n");
        sb.append("    startSegment: ").append(toIndentedString(startSegment)).append("\n");
        sb.append("    endSegment: ").append(toIndentedString(endSegment)).append("\n");
        sb.append("    segmentStartTime: ").append(toIndentedString(segmentStartTime)).append("\n");
        sb.append("    segmentEndTime: ").append(toIndentedString(segmentEndTime)).append("\n");
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
