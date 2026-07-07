package com.lztech.site.viewmodel.teachingcalendarcoursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * LabResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-08-20T12:04:45.217Z")

public class LabResource {
    @JsonProperty("roomId")
    private String roomId = null;

    @JsonProperty("roomCode")
    private String roomCode = null;

    @JsonProperty("roomName")
    private String roomName = null;

    @JsonProperty("labStudentCount")
    private Integer labStudentCount = null;

    @JsonProperty("itemTeacherId")
    private String itemTeacherId = null;

    @JsonProperty("itemTeacherNo")
    private String itemTeacherNo = null;

    @JsonProperty("itemTeacherName")
    private String itemTeacherName = null;

    @JsonProperty("studentList")
    @Valid
    private List<TeachingCalendarStudentResource> studentList = null;

    public LabResource roomId(String roomId) {
        this.roomId = roomId;
        return this;
    }

    /**
     * 实验室id
     *
     * @return roomId
     **/
    @ApiModelProperty(value = "实验室id")


    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public LabResource roomCode(String roomCode) {
        this.roomCode = roomCode;
        return this;
    }

    /**
     * 实验室编号
     *
     * @return roomCode
     **/
    @ApiModelProperty(value = "实验室编号")


    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public LabResource roomName(String roomName) {
        this.roomName = roomName;
        return this;
    }

    /**
     * 实验室名称
     *
     * @return roomName
     **/
    @ApiModelProperty(value = "实验室名称")


    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public LabResource labStudentCount(Integer labStudentCount) {
        this.labStudentCount = labStudentCount;
        return this;
    }

    /**
     * 实验室学生数量
     *
     * @return labStudentCount
     **/
    @ApiModelProperty(value = "实验室学生数量")


    public Integer getLabStudentCount() {
        return labStudentCount;
    }

    public void setLabStudentCount(Integer labStudentCount) {
        this.labStudentCount = labStudentCount;
    }

    public LabResource itemTeacherId(String itemTeacherId) {
        this.itemTeacherId = itemTeacherId;
        return this;
    }

    /**
     * 物品准备老师id
     *
     * @return itemTeacherId
     **/
    @ApiModelProperty(value = "物品准备老师id")


    public String getItemTeacherId() {
        return itemTeacherId;
    }

    public void setItemTeacherId(String itemTeacherId) {
        this.itemTeacherId = itemTeacherId;
    }

    public LabResource itemTeacherNo(String itemTeacherNo) {
        this.itemTeacherNo = itemTeacherNo;
        return this;
    }

    /**
     * 物品准备老师工号
     *
     * @return itemTeacherNo
     **/
    @ApiModelProperty(value = "物品准备老师工号")


    public String getItemTeacherNo() {
        return itemTeacherNo;
    }

    public void setItemTeacherNo(String itemTeacherNo) {
        this.itemTeacherNo = itemTeacherNo;
    }

    public LabResource itemTeacherName(String itemTeacherName) {
        this.itemTeacherName = itemTeacherName;
        return this;
    }

    /**
     * 物品准备老师姓名
     *
     * @return itemTeacherName
     **/
    @ApiModelProperty(value = "物品准备老师姓名")


    public String getItemTeacherName() {
        return itemTeacherName;
    }

    public void setItemTeacherName(String itemTeacherName) {
        this.itemTeacherName = itemTeacherName;
    }

    public LabResource studentList(List<TeachingCalendarStudentResource> studentList) {
        this.studentList = studentList;
        return this;
    }

    public LabResource addStudentListItem(TeachingCalendarStudentResource studentListItem) {
        if (this.studentList == null) {
            this.studentList = new ArrayList<TeachingCalendarStudentResource>();
        }
        this.studentList.add(studentListItem);
        return this;
    }

    /**
     * Get studentList
     *
     * @return studentList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<TeachingCalendarStudentResource> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<TeachingCalendarStudentResource> studentList) {
        this.studentList = studentList;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LabResource labResource = (LabResource) o;
        return Objects.equals(this.roomId, labResource.roomId) &&
                Objects.equals(this.roomCode, labResource.roomCode) &&
                Objects.equals(this.roomName, labResource.roomName) &&
                Objects.equals(this.labStudentCount, labResource.labStudentCount) &&
                Objects.equals(this.itemTeacherId, labResource.itemTeacherId) &&
                Objects.equals(this.itemTeacherNo, labResource.itemTeacherNo) &&
                Objects.equals(this.itemTeacherName, labResource.itemTeacherName) &&
                Objects.equals(this.studentList, labResource.studentList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, roomCode, roomName, labStudentCount, itemTeacherId, itemTeacherNo, itemTeacherName, studentList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class LabResource {\n");

        sb.append("    roomId: ").append(toIndentedString(roomId)).append("\n");
        sb.append("    roomCode: ").append(toIndentedString(roomCode)).append("\n");
        sb.append("    roomName: ").append(toIndentedString(roomName)).append("\n");
        sb.append("    labStudentCount: ").append(toIndentedString(labStudentCount)).append("\n");
        sb.append("    itemTeacherId: ").append(toIndentedString(itemTeacherId)).append("\n");
        sb.append("    itemTeacherNo: ").append(toIndentedString(itemTeacherNo)).append("\n");
        sb.append("    itemTeacherName: ").append(toIndentedString(itemTeacherName)).append("\n");
        sb.append("    studentList: ").append(toIndentedString(studentList)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}

