package com.lztech.site.viewmodel.experimentschedulecoursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ExperimentScheduleCourseTableDetailRoomVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-09-14T06:34:55.092Z")


public class ExperimentScheduleCourseTableDetailRoomVo {
    @JsonProperty("roomId")
    private String roomId = null;

    @JsonProperty("roomCode")
    private String roomCode = null;

    @JsonProperty("roomName")
    private String roomName = null;

    @JsonProperty("labStudentCount")
    private String labStudentCount = null;

    @JsonProperty("itemTeacherId")
    private String itemTeacherId = null;

    @JsonProperty("itemTeacherNo")
    private String itemTeacherNo = null;

    @JsonProperty("itemTeacherName")
    private String itemTeacherName = null;

    public ExperimentScheduleCourseTableDetailRoomVo roomId(String roomId) {
        this.roomId = roomId;
        return this;
    }

    /**
     * 实验室id
     *
     * @return roomId
     **/
    @ApiModelProperty(value = "实验室id", position = 1, required = true)


    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public ExperimentScheduleCourseTableDetailRoomVo roomCode(String roomCode) {
        this.roomCode = roomCode;
        return this;
    }

    /**
     * 实验室编号
     *
     * @return roomCode
     **/
    @ApiModelProperty(value = "实验室编号", position = 2, required = true)


    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public ExperimentScheduleCourseTableDetailRoomVo roomName(String roomName) {
        this.roomName = roomName;
        return this;
    }

    /**
     * 实验室名称
     *
     * @return roomName
     **/
    @ApiModelProperty(value = "实验室名称", position = 3, required = true)


    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public ExperimentScheduleCourseTableDetailRoomVo labStudentCount(String labStudentCount) {
        this.labStudentCount = labStudentCount;
        return this;
    }

    /**
     * 实验室学生数量
     *
     * @return labStudentCount
     **/
    @ApiModelProperty(value = "实验室学生数量", position = 4, required = false)


    public String getLabStudentCount() {
        return labStudentCount;
    }

    public void setLabStudentCount(String labStudentCount) {
        this.labStudentCount = labStudentCount;
    }

    public ExperimentScheduleCourseTableDetailRoomVo itemTeacherId(String itemTeacherId) {
        this.itemTeacherId = itemTeacherId;
        return this;
    }

    /**
     * 物品准备老师id
     *
     * @return itemTeacherId
     **/
    @ApiModelProperty(value = "物品准备老师id", position = 5, required = false)


    public String getItemTeacherId() {
        return itemTeacherId;
    }

    public void setItemTeacherId(String itemTeacherId) {
        this.itemTeacherId = itemTeacherId;
    }

    public ExperimentScheduleCourseTableDetailRoomVo itemTeacherNo(String itemTeacherNo) {
        this.itemTeacherNo = itemTeacherNo;
        return this;
    }

    /**
     * 物品准备老师工号
     *
     * @return itemTeacherNo
     **/
    @ApiModelProperty(value = "物品准备老师工号", position = 6, required = false)


    public String getItemTeacherNo() {
        return itemTeacherNo;
    }

    public void setItemTeacherNo(String itemTeacherNo) {
        this.itemTeacherNo = itemTeacherNo;
    }

    public ExperimentScheduleCourseTableDetailRoomVo itemTeacherName(String itemTeacherName) {
        this.itemTeacherName = itemTeacherName;
        return this;
    }

    /**
     * 物品准备老师姓名
     *
     * @return itemTeacherName
     **/
    @ApiModelProperty(value = "物品准备老师姓名", position = 7, required = false)


    public String getItemTeacherName() {
        return itemTeacherName;
    }

    public void setItemTeacherName(String itemTeacherName) {
        this.itemTeacherName = itemTeacherName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExperimentScheduleCourseTableDetailRoomVo experimentScheduleCourseTableDetailRoomVo = (ExperimentScheduleCourseTableDetailRoomVo) o;
        return Objects.equals(this.roomId, experimentScheduleCourseTableDetailRoomVo.roomId) &&
                Objects.equals(this.roomCode, experimentScheduleCourseTableDetailRoomVo.roomCode) &&
                Objects.equals(this.roomName, experimentScheduleCourseTableDetailRoomVo.roomName) &&
                Objects.equals(this.labStudentCount, experimentScheduleCourseTableDetailRoomVo.labStudentCount) &&
                Objects.equals(this.itemTeacherId, experimentScheduleCourseTableDetailRoomVo.itemTeacherId) &&
                Objects.equals(this.itemTeacherNo, experimentScheduleCourseTableDetailRoomVo.itemTeacherNo) &&
                Objects.equals(this.itemTeacherName, experimentScheduleCourseTableDetailRoomVo.itemTeacherName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, roomCode, roomName, labStudentCount, itemTeacherId, itemTeacherNo, itemTeacherName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExperimentScheduleCourseTableDetailRoomVo {\n");

        sb.append("    roomId: ").append(toIndentedString(roomId)).append("\n");
        sb.append("    roomCode: ").append(toIndentedString(roomCode)).append("\n");
        sb.append("    roomName: ").append(toIndentedString(roomName)).append("\n");
        sb.append("    labStudentCount: ").append(toIndentedString(labStudentCount)).append("\n");
        sb.append("    itemTeacherId: ").append(toIndentedString(itemTeacherId)).append("\n");
        sb.append("    itemTeacherNo: ").append(toIndentedString(itemTeacherNo)).append("\n");
        sb.append("    itemTeacherName: ").append(toIndentedString(itemTeacherName)).append("\n");
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

