package com.lztech.site.viewmodel.classroom;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ClassroomBaseInfoVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-07-19T03:34:26.264Z")

public class ClassroomBaseInfoVo {
    @JsonProperty("roomId")
    private String roomId = null;

    @JsonProperty("roomName")
    private String roomName = null;

    @JsonProperty("seatNum")
    private Integer seatNum = null;

    @JsonProperty("roomIng")
    private String roomIng = null;

    @JsonProperty("roomLat")
    private String roomLat = null;

    public ClassroomBaseInfoVo roomId(String roomId) {
        this.roomId = roomId;
        return this;
    }

    /**
     * 教室id
     *
     * @return roomId
     **/
    @ApiModelProperty(value = "教室id")


    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public ClassroomBaseInfoVo roomName(String roomName) {
        this.roomName = roomName;
        return this;
    }

    /**
     * 教室名称
     *
     * @return roomName
     **/
    @ApiModelProperty(value = "教室名称")


    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public ClassroomBaseInfoVo seatNum(Integer seatNum) {
        this.seatNum = seatNum;
        return this;
    }

    /**
     * 座位数
     *
     * @return seatNum
     **/
    @ApiModelProperty(value = "座位数")


    public Integer getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(Integer seatNum) {
        this.seatNum = seatNum;
    }

    public ClassroomBaseInfoVo roomIng(String roomIng) {
        this.roomIng = roomIng;
        return this;
    }

    /**
     * 教室经度
     *
     * @return roomIng
     **/
    @ApiModelProperty(value = "教室经度")


    public String getRoomIng() {
        return roomIng;
    }

    public void setRoomIng(String roomIng) {
        this.roomIng = roomIng;
    }

    public ClassroomBaseInfoVo roomLat(String roomLat) {
        this.roomLat = roomLat;
        return this;
    }

    /**
     * 教室纬度
     *
     * @return roomLat
     **/
    @ApiModelProperty(value = "教室纬度")


    public String getRoomLat() {
        return roomLat;
    }

    public void setRoomLat(String roomLat) {
        this.roomLat = roomLat;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClassroomBaseInfoVo classroomBaseInfoVo = (ClassroomBaseInfoVo) o;
        return Objects.equals(this.roomId, classroomBaseInfoVo.roomId) &&
                Objects.equals(this.roomName, classroomBaseInfoVo.roomName) &&
                Objects.equals(this.seatNum, classroomBaseInfoVo.seatNum) &&
                Objects.equals(this.roomIng, classroomBaseInfoVo.roomIng) &&
                Objects.equals(this.roomLat, classroomBaseInfoVo.roomLat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, roomName, seatNum, roomIng, roomLat);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ClassroomBaseInfoVo {\n");

        sb.append("    roomId: ").append(toIndentedString(roomId)).append("\n");
        sb.append("    roomName: ").append(toIndentedString(roomName)).append("\n");
        sb.append("    seatNum: ").append(toIndentedString(seatNum)).append("\n");
        sb.append("    roomIng: ").append(toIndentedString(roomIng)).append("\n");
        sb.append("    roomLat: ").append(toIndentedString(roomLat)).append("\n");
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

