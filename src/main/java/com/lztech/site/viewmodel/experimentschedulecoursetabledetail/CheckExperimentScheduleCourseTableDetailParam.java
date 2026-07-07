package com.lztech.site.viewmodel.experimentschedulecoursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
/**
 * CheckExperimentScheduleCourseTableDetailParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-09-12T12:21:44.947Z")
public class CheckExperimentScheduleCourseTableDetailParam {

    @JsonProperty("courseDate")
    private String courseDate = null;

    @JsonProperty("startSegment")
    private Integer startSegment = null;

    @JsonProperty("endSegment")
    private Integer endSegment = null;

    @JsonProperty("roomIdList")
    @Valid
    private List<String> roomIdList = null;


    public CheckExperimentScheduleCourseTableDetailParam courseDate(String courseDate) {
        this.courseDate = courseDate;
        return this;
    }

    /**
     * 课表日期
     *
     * @return courseDate
     **/
    @ApiModelProperty(value = "课表日期", position = 6, required = true)

    public String getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(String courseDate) {
        this.courseDate = courseDate;
    }

    public CheckExperimentScheduleCourseTableDetailParam startSegment(Integer startSegment) {
        this.startSegment = startSegment;
        return this;
    }

    /**
     * 开始节次
     *
     * @return id
     **/
    @ApiModelProperty(value = "开始节次", position = 2, required = true)

    public Integer getStartSegment() {
        return startSegment;
    }

    public void setStartSegment(Integer startSegment) {
        this.startSegment = startSegment;
    }

    public CheckExperimentScheduleCourseTableDetailParam endSegment(Integer endSegment) {
        this.endSegment = endSegment;
        return this;
    }

    /**
     * 结束节次
     *
     * @return id
     **/
    @ApiModelProperty(value = "结束节次", position = 3, required = true)

    public Integer getEndSegment() {
        return endSegment;
    }

    public void setEndSegment(Integer endSegment) {
        this.endSegment = endSegment;
    }

    public CheckExperimentScheduleCourseTableDetailParam roomIdList(List<String> roomIdList) {
        this.roomIdList = roomIdList;
        return this;
    }

    /**
     * 教室列表
     *
     * @return roomIdList
     **/
    @ApiModelProperty(value = "教室列表", position = 8, required = true)
    public List<String> getRoomIdList() {
        return roomIdList;
    }

    public void setRoomIdList(List<String> roomIdList) {
        this.roomIdList = roomIdList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CheckExperimentScheduleCourseTableDetailParam checkExperimentScheduleCourseTableDetailParam =
                (CheckExperimentScheduleCourseTableDetailParam) o;
        return Objects.equals(this.courseDate, checkExperimentScheduleCourseTableDetailParam.courseDate) &&
                Objects.equals(this.startSegment, checkExperimentScheduleCourseTableDetailParam.startSegment) &&
                Objects.equals(this.endSegment, checkExperimentScheduleCourseTableDetailParam.endSegment) &&
                Objects.equals(this.roomIdList, checkExperimentScheduleCourseTableDetailParam.roomIdList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseDate, startSegment, endSegment,roomIdList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CheckExperimentScheduleCourseTableDetailParam {\n");
        sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
        sb.append("    startSegment: ").append(toIndentedString(startSegment)).append("\n");
        sb.append("    endSegment: ").append(toIndentedString(endSegment)).append("\n");
        sb.append("    experimentScheduleCourseTableDetailRoomList: ").append(
                toIndentedString(roomIdList)).append("\n");
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
