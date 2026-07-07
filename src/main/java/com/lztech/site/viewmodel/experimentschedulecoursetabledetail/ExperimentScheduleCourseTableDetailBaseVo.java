package com.lztech.site.viewmodel.experimentschedulecoursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lztech.site.viewmodel.group.StudentVo;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * ExperimentScheduleCourseTableDetailBaseVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-09-14T06:34:55.092Z")

public class ExperimentScheduleCourseTableDetailBaseVo {

    @JsonProperty("id")
    private String id = null;

    @JsonProperty("startSegment")
    private Integer startSegment = null;

    @JsonProperty("endSegment")
    private Integer endSegment = null;

    @JsonProperty("segmentStartTime")
    private String segmentStartTime = null;

    @JsonProperty("segmentEndTime")
    private String segmentEndTime = null;

    @JsonProperty("courseDate")
    private String courseDate = null;

    @JsonProperty("experimentScheduleCourseTableDetailTeacherList")
    @Valid
    private List<ExperimentScheduleCourseTableDetailTeacherVo> experimentScheduleCourseTableDetailTeacherList = null;

    @JsonProperty("experimentScheduleCourseTableDetailRoomList")
    @Valid
    private List<ExperimentScheduleCourseTableDetailRoomVo> experimentScheduleCourseTableDetailRoomList = null;

    @JsonProperty("experimentScheduleCourseTableDetailProjectList")
    @Valid
    private List<ExperimentScheduleCourseTableDetailProjectVo> experimentScheduleCourseTableDetailProjectList = null;

    @JsonProperty("studentVoList")
    @Valid
    private List<StudentVo> studentVoList = null;

    public ExperimentScheduleCourseTableDetailBaseVo id(String id) {
        this.id = id;
        return this;
    }

    /**
     * 实验课表明细Id
     *
     * @return id
     **/
    @ApiModelProperty(value = "实验原始课表明细Id", position = 1, required = true)

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ExperimentScheduleCourseTableDetailBaseVo startSegment(Integer startSegment) {
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

    public ExperimentScheduleCourseTableDetailBaseVo endSegment(Integer endSegment) {
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

    public ExperimentScheduleCourseTableDetailBaseVo segmentStartTime(String segmentStartTime) {
        this.segmentStartTime = segmentStartTime;
        return this;
    }

    /**
     * 节次开始时间
     *
     * @return segmentStartTime
     **/
    @ApiModelProperty(value = "节次开始时间", position = 4, required = true)

    public String getSegmentStartTime() {
        return segmentStartTime;
    }

    public void setSegmentStartTime(String segmentStartTime) {
        this.segmentStartTime = segmentStartTime;
    }

    public ExperimentScheduleCourseTableDetailBaseVo segmentEndTime(String segmentEndTime) {
        this.segmentEndTime = segmentEndTime;
        return this;
    }

    /**
     * 节次结束时间
     *
     * @return segmentEndTime
     **/
    @ApiModelProperty(value = "节次结束时间", position = 5, required = true)

    public String getSegmentEndTime() {
        return segmentEndTime;
    }

    public void setSegmentEndTime(String segmentEndTime) {
        this.segmentEndTime = segmentEndTime;
    }

    public ExperimentScheduleCourseTableDetailBaseVo courseDate(String courseDate) {
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
    public ExperimentScheduleCourseTableDetailBaseVo experimentScheduleCourseTableDetailTeacherList(
            List<ExperimentScheduleCourseTableDetailTeacherVo> experimentScheduleCourseTableDetailTeacherList) {
        this.experimentScheduleCourseTableDetailTeacherList = experimentScheduleCourseTableDetailTeacherList;
        return this;
    }

    /**
     * 老师列表
     *
     * @return courseDate
     **/
    @ApiModelProperty(value = "老师列表", position = 7, required = true)
    public List<ExperimentScheduleCourseTableDetailTeacherVo> getExperimentScheduleCourseTableDetailTeacherList() {
        return experimentScheduleCourseTableDetailTeacherList;
    }

    public void setExperimentScheduleCourseTableDetailTeacherList(
            List<ExperimentScheduleCourseTableDetailTeacherVo> experimentScheduleCourseTableDetailTeacherList) {
        this.experimentScheduleCourseTableDetailTeacherList = experimentScheduleCourseTableDetailTeacherList;
    }
    public ExperimentScheduleCourseTableDetailBaseVo experimentScheduleCourseTableDetailRoomList(
            List<ExperimentScheduleCourseTableDetailRoomVo> experimentScheduleCourseTableDetailRoomList) {
        this.experimentScheduleCourseTableDetailRoomList = experimentScheduleCourseTableDetailRoomList;
        return this;
    }

    /**
     * 教室列表
     *
     * @return experimentScheduleCourseTableDetailRoomList
     **/
    @ApiModelProperty(value = "教室列表", position = 8, required = true)
    public List<ExperimentScheduleCourseTableDetailRoomVo> getExperimentScheduleCourseTableDetailRoomList() {
        return experimentScheduleCourseTableDetailRoomList;
    }

    public void setExperimentScheduleCourseTableDetailRoomList(
            List<ExperimentScheduleCourseTableDetailRoomVo> experimentScheduleCourseTableDetailRoomList) {
        this.experimentScheduleCourseTableDetailRoomList = experimentScheduleCourseTableDetailRoomList;
    }
    public ExperimentScheduleCourseTableDetailBaseVo experimentScheduleCourseTableDetailProjectList(
            List<ExperimentScheduleCourseTableDetailProjectVo> experimentScheduleCourseTableDetailProjectList) {
        this.experimentScheduleCourseTableDetailProjectList = experimentScheduleCourseTableDetailProjectList;
        return this;
    }

    /**
     * 项目列表
     *
     * @return experimentScheduleCourseTableDetailProjectList
     **/
    @ApiModelProperty(value = "项目列表", position = 9, required = true)
    public List<ExperimentScheduleCourseTableDetailProjectVo> getExperimentScheduleCourseTableDetailProjectList() {
        return experimentScheduleCourseTableDetailProjectList;
    }

    public void setExperimentScheduleCourseTableDetailProjectList(
            List<ExperimentScheduleCourseTableDetailProjectVo> experimentScheduleCourseTableDetailProjectList) {
        this.experimentScheduleCourseTableDetailProjectList = experimentScheduleCourseTableDetailProjectList;
    }
    public ExperimentScheduleCourseTableDetailBaseVo studentVoList(List<StudentVo> studentVoList) {
        this.studentVoList = studentVoList;
        return this;
    }

    /**
     * 班级学生列表
     *
     * @return studentVoList
     **/
    @ApiModelProperty(value = "班级学生列表", position = 10, required = true)
    public List<StudentVo> getStudentVoList() {
        return studentVoList;
    }

    public void setStudentVoList(List<StudentVo> studentVoList) {
        this.studentVoList = studentVoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExperimentScheduleCourseTableDetailBaseVo experimentScheduleCourseTableDetailBaseVo =
                (ExperimentScheduleCourseTableDetailBaseVo) o;
        return Objects.equals(this.id, experimentScheduleCourseTableDetailBaseVo.id) &&
                Objects.equals(this.startSegment, experimentScheduleCourseTableDetailBaseVo.startSegment) &&
                Objects.equals(this.endSegment, experimentScheduleCourseTableDetailBaseVo.endSegment) &&
                Objects.equals(this.courseDate, experimentScheduleCourseTableDetailBaseVo.courseDate) &&
                Objects.equals(this.experimentScheduleCourseTableDetailTeacherList,
                        experimentScheduleCourseTableDetailBaseVo.experimentScheduleCourseTableDetailTeacherList) &&
                Objects.equals(this.experimentScheduleCourseTableDetailRoomList,
                        experimentScheduleCourseTableDetailBaseVo.experimentScheduleCourseTableDetailRoomList) &&
                Objects.equals(this.experimentScheduleCourseTableDetailProjectList,
                        experimentScheduleCourseTableDetailBaseVo.experimentScheduleCourseTableDetailProjectList) &&
                Objects.equals(this.studentVoList, experimentScheduleCourseTableDetailBaseVo.studentVoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startSegment, endSegment, courseDate,segmentStartTime,segmentEndTime,
                experimentScheduleCourseTableDetailTeacherList,
                experimentScheduleCourseTableDetailRoomList,
                experimentScheduleCourseTableDetailProjectList,studentVoList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExperimentScheduleCourseTableDetailPushParam {\n");
        sb.append("    experimentOriginalCourseTableDetailId: ").append(toIndentedString(id)).append("\n");
        sb.append("    startSegment: ").append(toIndentedString(startSegment)).append("\n");
        sb.append("    endSegment: ").append(toIndentedString(endSegment)).append("\n");
        sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
        sb.append("    segmentStartTime: ").append(toIndentedString(segmentStartTime)).append("\n");
        sb.append("    segmentEndTime: ").append(toIndentedString(segmentEndTime)).append("\n");
        sb.append("    experimentScheduleCourseTableDetailTeacherList: ").append(
                toIndentedString(experimentScheduleCourseTableDetailTeacherList)).append("\n");
        sb.append("    experimentScheduleCourseTableDetailRoomList: ").append(
                toIndentedString(experimentScheduleCourseTableDetailRoomList)).append("\n");
        sb.append("    experimentScheduleCourseTableDetailProjectList: ").append(
                toIndentedString(experimentScheduleCourseTableDetailProjectList)).append("\n");
        sb.append("    studentVoList: ").append(toIndentedString(studentVoList)).append("\n");
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
