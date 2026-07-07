package com.lztech.site.viewmodel.experimentschedulecoursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ExperimentScheduleCourseTableDetailPushParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-07-23T14:58:11.637+08:00")

public class ExperimentScheduleCourseTableDetailPushParam {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("startSegment")
    private Integer startSegment = null;

    @JsonProperty("endSegment")
    private Integer endSegment = null;

    @JsonProperty("courseDate")
    private String courseDate = null;

    @JsonProperty("creatorId")
    private String creatorId = null;

    @JsonProperty("creatorName")
    private String creatorName = null;

    @JsonProperty("pushType")
    private Integer pushType = null;

    @JsonProperty("experimentScheduleCourseTableDetailTeacherList")
    @Valid
    private List<ExperimentScheduleCourseTableDetailTeacherVo> experimentScheduleCourseTableDetailTeacherList = null;

    @JsonProperty("experimentScheduleCourseTableDetailRoomList")
    @Valid
    private List<ExperimentScheduleCourseTableDetailRoomVo> experimentScheduleCourseTableDetailRoomList = null;

    @JsonProperty("experimentScheduleCourseTableDetailProjectList")
    @Valid
    private List<ExperimentScheduleCourseTableDetailProjectVo> experimentScheduleCourseTableDetailProjectList = null;

    public ExperimentScheduleCourseTableDetailPushParam id(String id) {
        this.id = id;
        return this;
    }

    /**
     * 实验原始课表明细Id
     *
     * @return id
     **/
    @ApiModelProperty(value = "实验原始课表明细Id")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ExperimentScheduleCourseTableDetailPushParam startSegment(Integer startSegment) {
        this.startSegment = startSegment;
        return this;
    }

    /**
     * 开始节次
     *
     * @return startSegment
     **/
    @ApiModelProperty(value = "开始节次")


    public Integer getStartSegment() {
        return startSegment;
    }

    public void setStartSegment(Integer startSegment) {
        this.startSegment = startSegment;
    }

    public ExperimentScheduleCourseTableDetailPushParam endSegment(Integer endSegment) {
        this.endSegment = endSegment;
        return this;
    }

    /**
     * 结束节次
     *
     * @return endSegment
     **/
    @ApiModelProperty(value = "结束节次")


    public Integer getEndSegment() {
        return endSegment;
    }

    public void setEndSegment(Integer endSegment) {
        this.endSegment = endSegment;
    }

    public ExperimentScheduleCourseTableDetailPushParam courseDate(String courseDate) {
        this.courseDate = courseDate;
        return this;
    }

    /**
     * 上课日期
     *
     * @return courseDate
     **/
    @ApiModelProperty(value = "上课日期")


    public String getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(String courseDate) {
        this.courseDate = courseDate;
    }

    public ExperimentScheduleCourseTableDetailPushParam creatorId(String creatorId) {
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

    public ExperimentScheduleCourseTableDetailPushParam creatorName(String creatorName) {
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

    public ExperimentScheduleCourseTableDetailPushParam pushType(Integer pushType) {
        this.pushType = pushType;
        return this;
    }

    /**
     * 推送类型（0：新增排课、1：修改排课）
     *
     * @return pushType
     **/
    @ApiModelProperty(value = "推送类型（0：新增排课、1：修改排课）")


    public Integer getPushType() {
        return pushType;
    }

    public void setPushType(Integer pushType) {
        this.pushType = pushType;
    }

    public ExperimentScheduleCourseTableDetailPushParam experimentScheduleCourseTableDetailTeacherList(
            List<ExperimentScheduleCourseTableDetailTeacherVo> experimentScheduleCourseTableDetailTeacherList) {
        this.experimentScheduleCourseTableDetailTeacherList = experimentScheduleCourseTableDetailTeacherList;
        return this;
    }

    public ExperimentScheduleCourseTableDetailPushParam addExperimentScheduleCourseTableDetailTeacherListItem(
            ExperimentScheduleCourseTableDetailTeacherVo experimentScheduleCourseTableDetailTeacherListItem) {
        if (this.experimentScheduleCourseTableDetailTeacherList == null) {
            this.experimentScheduleCourseTableDetailTeacherList = new ArrayList<ExperimentScheduleCourseTableDetailTeacherVo>();
        }
        this.experimentScheduleCourseTableDetailTeacherList.add(experimentScheduleCourseTableDetailTeacherListItem);
        return this;
    }

    /**
     * 老师列表
     *
     * @return experimentScheduleCourseTableDetailTeacherList
     **/
    @ApiModelProperty(value = "老师列表")

    @Valid

    public List<ExperimentScheduleCourseTableDetailTeacherVo> getExperimentScheduleCourseTableDetailTeacherList() {
        return experimentScheduleCourseTableDetailTeacherList;
    }

    public void setExperimentScheduleCourseTableDetailTeacherList(
            List<ExperimentScheduleCourseTableDetailTeacherVo> experimentScheduleCourseTableDetailTeacherList) {
        this.experimentScheduleCourseTableDetailTeacherList = experimentScheduleCourseTableDetailTeacherList;
    }

    public ExperimentScheduleCourseTableDetailPushParam experimentScheduleCourseTableDetailRoomList(
            List<ExperimentScheduleCourseTableDetailRoomVo> experimentScheduleCourseTableDetailRoomList) {
        this.experimentScheduleCourseTableDetailRoomList = experimentScheduleCourseTableDetailRoomList;
        return this;
    }

    public ExperimentScheduleCourseTableDetailPushParam addExperimentScheduleCourseTableDetailRoomListItem(
            ExperimentScheduleCourseTableDetailRoomVo experimentScheduleCourseTableDetailRoomListItem) {
        if (this.experimentScheduleCourseTableDetailRoomList == null) {
            this.experimentScheduleCourseTableDetailRoomList = new ArrayList<ExperimentScheduleCourseTableDetailRoomVo>();
        }
        this.experimentScheduleCourseTableDetailRoomList.add(experimentScheduleCourseTableDetailRoomListItem);
        return this;
    }

    /**
     * 教室列表
     *
     * @return experimentScheduleCourseTableDetailRoomList
     **/
    @ApiModelProperty(value = "教室列表")

    @Valid

    public List<ExperimentScheduleCourseTableDetailRoomVo> getExperimentScheduleCourseTableDetailRoomList() {
        return experimentScheduleCourseTableDetailRoomList;
    }

    public void setExperimentScheduleCourseTableDetailRoomList(
            List<ExperimentScheduleCourseTableDetailRoomVo> experimentScheduleCourseTableDetailRoomList) {
        this.experimentScheduleCourseTableDetailRoomList = experimentScheduleCourseTableDetailRoomList;
    }

    public ExperimentScheduleCourseTableDetailPushParam experimentScheduleCourseTableDetailProjectList(
            List<ExperimentScheduleCourseTableDetailProjectVo> experimentScheduleCourseTableDetailProjectList) {
        this.experimentScheduleCourseTableDetailProjectList = experimentScheduleCourseTableDetailProjectList;
        return this;
    }

    public ExperimentScheduleCourseTableDetailPushParam addExperimentScheduleCourseTableDetailProjectListItem(
            ExperimentScheduleCourseTableDetailProjectVo experimentScheduleCourseTableDetailProjectListItem) {
        if (this.experimentScheduleCourseTableDetailProjectList == null) {
            this.experimentScheduleCourseTableDetailProjectList = new ArrayList<ExperimentScheduleCourseTableDetailProjectVo>();
        }
        this.experimentScheduleCourseTableDetailProjectList.add(experimentScheduleCourseTableDetailProjectListItem);
        return this;
    }

    /**
     * 项目列表
     *
     * @return experimentScheduleCourseTableDetailProjectList
     **/
    @ApiModelProperty(value = "项目列表")

    @Valid

    public List<ExperimentScheduleCourseTableDetailProjectVo> getExperimentScheduleCourseTableDetailProjectList() {
        return experimentScheduleCourseTableDetailProjectList;
    }

    public void setExperimentScheduleCourseTableDetailProjectList(
            List<ExperimentScheduleCourseTableDetailProjectVo> experimentScheduleCourseTableDetailProjectList) {
        this.experimentScheduleCourseTableDetailProjectList = experimentScheduleCourseTableDetailProjectList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExperimentScheduleCourseTableDetailPushParam experimentScheduleCourseTableDetailPushParam =
                (ExperimentScheduleCourseTableDetailPushParam) o;
        return Objects.equals(this.id, experimentScheduleCourseTableDetailPushParam.id) &&
                Objects.equals(this.startSegment, experimentScheduleCourseTableDetailPushParam.startSegment) &&
                Objects.equals(this.endSegment, experimentScheduleCourseTableDetailPushParam.endSegment) &&
                Objects.equals(this.courseDate, experimentScheduleCourseTableDetailPushParam.courseDate) &&
                Objects.equals(this.creatorId, experimentScheduleCourseTableDetailPushParam.creatorId) &&
                Objects.equals(this.creatorName, experimentScheduleCourseTableDetailPushParam.creatorName) &&
                Objects.equals(this.pushType, experimentScheduleCourseTableDetailPushParam.pushType) &&
                Objects.equals(this.experimentScheduleCourseTableDetailTeacherList,
                        experimentScheduleCourseTableDetailPushParam.experimentScheduleCourseTableDetailTeacherList) &&
                Objects.equals(this.experimentScheduleCourseTableDetailRoomList,
                        experimentScheduleCourseTableDetailPushParam.experimentScheduleCourseTableDetailRoomList) &&
                Objects.equals(this.experimentScheduleCourseTableDetailProjectList,
                        experimentScheduleCourseTableDetailPushParam.experimentScheduleCourseTableDetailProjectList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startSegment, endSegment, courseDate, creatorId, creatorName, pushType,
                experimentScheduleCourseTableDetailTeacherList, experimentScheduleCourseTableDetailRoomList,
                experimentScheduleCourseTableDetailProjectList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExperimentScheduleCourseTableDetailPushParam {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    startSegment: ").append(toIndentedString(startSegment)).append("\n");
        sb.append("    endSegment: ").append(toIndentedString(endSegment)).append("\n");
        sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
        sb.append("    creatorId: ").append(toIndentedString(creatorId)).append("\n");
        sb.append("    creatorName: ").append(toIndentedString(creatorName)).append("\n");
        sb.append("    pushType: ").append(toIndentedString(pushType)).append("\n");
        sb.append("    experimentScheduleCourseTableDetailTeacherList: ")
                .append(toIndentedString(experimentScheduleCourseTableDetailTeacherList)).append("\n");
        sb.append("    experimentScheduleCourseTableDetailRoomList: ")
                .append(toIndentedString(experimentScheduleCourseTableDetailRoomList)).append("\n");
        sb.append("    experimentScheduleCourseTableDetailProjectList: ")
                .append(toIndentedString(experimentScheduleCourseTableDetailProjectList)).append("\n");
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

