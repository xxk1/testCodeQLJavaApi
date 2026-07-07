package com.lztech.site.viewmodel.experimentschedulecoursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ExperimentScheduleObtainDataParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-01-07T07:50:59.313Z")


public class ExperimentScheduleObtainDataParam {
    @JsonProperty("pageNum")
    private Integer pageNum = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    @JsonProperty("startDate")
    private String startDate = null;

    @JsonProperty("endDate")
    private String endDate = null;

    @JsonProperty("roomNameSearchParams")
    private String roomNameSearchParams = null;

    @JsonProperty("creatorId")
    private String creatorId = null;

    @JsonProperty("creatorName")
    private String creatorName = null;

    @JsonProperty("teacherName")
    private String teacherName = null;

    @JsonProperty("courseName")
    private String courseName = null;

    public ExperimentScheduleObtainDataParam pageNum(Integer pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    /**
     * 当前页码
     *
     * @return pageNum
     **/
    @ApiModelProperty(value = "当前页码")


    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public ExperimentScheduleObtainDataParam pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * 每页记录数
     *
     * @return pageSize
     **/
    @ApiModelProperty(value = "每页记录数")


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public ExperimentScheduleObtainDataParam startDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * 开始日期
     *
     * @return startDate
     **/
    @ApiModelProperty(value = "开始日期")


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public ExperimentScheduleObtainDataParam endDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * 结束日期
     *
     * @return endDate
     **/
    @ApiModelProperty(value = "结束日期")


    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public ExperimentScheduleObtainDataParam roomNameSearchParams(String roomNameSearchParams) {
        this.roomNameSearchParams = roomNameSearchParams;
        return this;
    }

    /**
     * 教室名称搜索参数
     *
     * @return roomNameSearchParams
     **/
    @ApiModelProperty(value = "教室名称搜索参数")


    public String getRoomNameSearchParams() {
        return roomNameSearchParams;
    }

    public void setRoomNameSearchParams(String roomNameSearchParams) {
        this.roomNameSearchParams = roomNameSearchParams;
    }

    public ExperimentScheduleObtainDataParam creatorId(String creatorId) {
        this.creatorId = creatorId;
        return this;
    }

    /**
     * 创建人id
     *
     * @return creatorId
     **/
    @ApiModelProperty(value = "创建人id")


    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public ExperimentScheduleObtainDataParam creatorName(String creatorName) {
        this.creatorName = creatorName;
        return this;
    }

    /**
     * 创建人名称
     *
     * @return creatorName
     **/
    @ApiModelProperty(value = "创建人名称")


    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public ExperimentScheduleObtainDataParam teacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    /**
     * 任课教师名称
     *
     * @return teacherName
     **/
    @ApiModelProperty(value = "任课教师名称")


    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public ExperimentScheduleObtainDataParam courseName(String courseName) {
        this.courseName = courseName;
        return this;
    }

    /**
     * 课程名称
     *
     * @return courseName
     **/
    @ApiModelProperty(value = "课程名称")


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExperimentScheduleObtainDataParam experimentScheduleObtainDataParam = (ExperimentScheduleObtainDataParam) o;
        return Objects.equals(this.pageNum, experimentScheduleObtainDataParam.pageNum) &&
                Objects.equals(this.pageSize, experimentScheduleObtainDataParam.pageSize) &&
                Objects.equals(this.startDate, experimentScheduleObtainDataParam.startDate) &&
                Objects.equals(this.endDate, experimentScheduleObtainDataParam.endDate) &&
                Objects.equals(this.roomNameSearchParams, experimentScheduleObtainDataParam.roomNameSearchParams) &&
                Objects.equals(this.creatorId, experimentScheduleObtainDataParam.creatorId) &&
                Objects.equals(this.creatorName, experimentScheduleObtainDataParam.creatorName) &&
                Objects.equals(this.teacherName, experimentScheduleObtainDataParam.teacherName) &&
                Objects.equals(this.courseName, experimentScheduleObtainDataParam.courseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageNum, pageSize, startDate, endDate, roomNameSearchParams, creatorId, creatorName, teacherName, courseName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExperimentScheduleObtainDataParam {\n");

        sb.append("    pageNum: ").append(toIndentedString(pageNum)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
        sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
        sb.append("    roomNameSearchParams: ").append(toIndentedString(roomNameSearchParams)).append("\n");
        sb.append("    creatorId: ").append(toIndentedString(creatorId)).append("\n");
        sb.append("    creatorName: ").append(toIndentedString(creatorName)).append("\n");
        sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
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

