package com.lztech.site.viewmodel.experimentschedulecoursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CancelExperimentScheduleCourseTableDetailModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-09-12T12:21:44.947Z")
public class CheckExperimentScheduleCourseTableDetailModel {

    @JsonProperty("checkStatus")
    private Integer checkStatus;

    @JsonProperty("checkRemark")
    private String checkRemark;

    @JsonProperty("roomsegmentTimeList")
    @Valid
    private List<RoomSegmentTimeVo> roomSegmentTimeList = new ArrayList<>();


    public CheckExperimentScheduleCourseTableDetailModel chackStatus(Integer chackStatus) {
        this.checkStatus = chackStatus;
        return this;
    }

    /**
     * 校验状态(0:没有通过;1:通过)
     *
     * @return checkStatus
     **/
    @ApiModelProperty(value = "校验状态(0:没有通过;1:通过)", position = 1, required = true)
    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public CheckExperimentScheduleCourseTableDetailModel chackRemark(String chackRemark) {
        this.checkRemark = chackRemark;
        return this;
    }

    /**
     * 校验备注
     *
     * @return checkRemark
     **/
    @ApiModelProperty(value = "校验备注", position = 2, required = true)
    public String getCheckRemark() {
        return checkRemark;
    }

    public void setCheckRemark(String checkRemark) {
        this.checkRemark = checkRemark;
    }

    public CheckExperimentScheduleCourseTableDetailModel roomSegmentTimeList(List<RoomSegmentTimeVo> roomSegmentTimeList) {
        this.roomSegmentTimeList = roomSegmentTimeList;
        return this;
    }

    /**
     * 教室节次列表
     *
     * @return roomSegmentTimeList
     **/
    @ApiModelProperty(value = "教室节次列表", position = 3, required = true)
    public List<RoomSegmentTimeVo> getRoomSegmentTimeList() {
        return roomSegmentTimeList;
    }

    public void setRoomSegmentTimeList(List<RoomSegmentTimeVo> roomSegmentTimeList) {
        this.roomSegmentTimeList = roomSegmentTimeList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CheckExperimentScheduleCourseTableDetailModel cancelExperimentScheduleCourseTableDetailModel =
                (CheckExperimentScheduleCourseTableDetailModel) o;
        return Objects.equals(this.checkStatus, cancelExperimentScheduleCourseTableDetailModel.checkStatus) &&
                Objects.equals(this.checkRemark, cancelExperimentScheduleCourseTableDetailModel.checkRemark) &&
                Objects.equals(this.roomSegmentTimeList, cancelExperimentScheduleCourseTableDetailModel.roomSegmentTimeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(checkStatus, checkRemark, roomSegmentTimeList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CheckExperimentScheduleCourseTableDetailModel {\n");
        sb.append("    checkStatus: ").append(toIndentedString(checkStatus)).append("\n");
        sb.append("    checkRemark: ").append(toIndentedString(checkRemark)).append("\n");
        sb.append("    roomsegmentTimeList: ").append(toIndentedString(roomSegmentTimeList)).append("\n");
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
