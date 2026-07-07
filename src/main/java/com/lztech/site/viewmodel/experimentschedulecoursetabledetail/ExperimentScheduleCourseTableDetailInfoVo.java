package com.lztech.site.viewmodel.experimentschedulecoursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ExperimentScheduleCourseTableDetailInfoVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-09-21T12:01:04.237Z")


public class ExperimentScheduleCourseTableDetailInfoVo   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("courseId")
  private String courseId = null;

  @JsonProperty("courseCode")
  private String courseCode = null;

  @JsonProperty("courseName")
  private String courseName = null;

  @JsonProperty("groupId")
  private String groupId = null;

  @JsonProperty("groupNo")
  private String groupNo = null;

  @JsonProperty("groupName")
  private String groupName = null;

  @JsonProperty("groupStudentCount")
  private Integer groupStudentCount = null;

  @JsonProperty("startSegment")
  private Integer startSegment = null;

  @JsonProperty("endSegment")
  private Integer endSegment = null;

  @JsonProperty("courseDate")
  private String courseDate = null;

  @JsonProperty("segmentStartTime")
  private String segmentStartTime = null;

  @JsonProperty("segmentEndTime")
  private String segmentEndTime = null;

  @JsonProperty("experimentScheduleCourseTableDetailTeacherList")
  @Valid
  private List<ExperimentScheduleCourseTableDetailTeacherVo> experimentScheduleCourseTableDetailTeacherList = null;

  @JsonProperty("experimentScheduleCourseTableDetailRoomList")
  @Valid
  private List<ExperimentScheduleCourseTableDetailRoomVo> experimentScheduleCourseTableDetailRoomList = null;

  @JsonProperty("experimentScheduleCourseTableDetailProjectList")
  @Valid
  private List<ExperimentScheduleCourseTableDetailProjectVo> experimentScheduleCourseTableDetailProjectList = null;

  public ExperimentScheduleCourseTableDetailInfoVo id(String id) {
    this.id = id;
    return this;
  }

  /**
   * 实验原始课表明细Id
   * @return id
  **/
  @ApiModelProperty(value = "实验原始课表明细Id", position = 1, required = true)


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ExperimentScheduleCourseTableDetailInfoVo courseId(String courseId) {
    this.courseId = courseId;
    return this;
  }

  /**
   * 课程id
   * @return courseId
  **/
  @ApiModelProperty(value = "课程id", position = 2, required = true)


  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }

  public ExperimentScheduleCourseTableDetailInfoVo courseCode(String courseCode) {
    this.courseCode = courseCode;
    return this;
  }

  /**
   * 课程编号
   * @return courseCode
  **/
  @ApiModelProperty(value = "课程编号", position = 3, required = true)


  public String getCourseCode() {
    return courseCode;
  }

  public void setCourseCode(String courseCode) {
    this.courseCode = courseCode;
  }

  public ExperimentScheduleCourseTableDetailInfoVo courseName(String courseName) {
    this.courseName = courseName;
    return this;
  }

  /**
   * 课程名称
   * @return courseName
  **/
  @ApiModelProperty(value = "课程名称", position = 4, required = true)


  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public ExperimentScheduleCourseTableDetailInfoVo groupId(String groupId) {
    this.groupId = groupId;
    return this;
  }

  /**
   * 班级id
   * @return groupId
  **/
  @ApiModelProperty(value = "班级id", position = 5, required = true)


  public String getGroupId() {
    return groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }

  public ExperimentScheduleCourseTableDetailInfoVo groupNo(String groupNo) {
    this.groupNo = groupNo;
    return this;
  }

  /**
   * 班级编号
   * @return groupNo
  **/
  @ApiModelProperty(value = "班级编号", position = 6, required = true)


  public String getGroupNo() {
    return groupNo;
  }

  public void setGroupNo(String groupNo) {
    this.groupNo = groupNo;
  }

  public ExperimentScheduleCourseTableDetailInfoVo groupName(String groupName) {
    this.groupName = groupName;
    return this;
  }

  /**
   * 班级名称
   * @return groupName
  **/
  @ApiModelProperty(value = "班级名称", position = 7, required = true)


  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }


  public ExperimentScheduleCourseTableDetailInfoVo groupStudentCount(Integer groupStudentCount) {
    this.groupStudentCount = groupStudentCount;
    return this;
  }

  /**
   * 班级学生人数
   * @return groupStudentCount
   **/
  @ApiModelProperty(value = "班级学生人数", position = 8, required = true)

  public Integer getGroupStudentCount() {
    return groupStudentCount;
  }

  public void setGroupStudentCount(Integer groupStudentCount) {
    this.groupStudentCount = groupStudentCount;
  }

  public ExperimentScheduleCourseTableDetailInfoVo startSegment(Integer startSegment) {
    this.startSegment = startSegment;
    return this;
  }

  /**
   * 开始节次
   * @return startSegment
  **/
  @ApiModelProperty(value = "开始节次", position = 9, required = true)


  public Integer getStartSegment() {
    return startSegment;
  }

  public void setStartSegment(Integer startSegment) {
    this.startSegment = startSegment;
  }

  public ExperimentScheduleCourseTableDetailInfoVo endSegment(Integer endSegment) {
    this.endSegment = endSegment;
    return this;
  }

  /**
   * 结束节次
   * @return endSegment
  **/
  @ApiModelProperty(value = "结束节次", position = 10, required = true)


  public Integer getEndSegment() {
    return endSegment;
  }

  public void setEndSegment(Integer endSegment) {
    this.endSegment = endSegment;
  }

  public ExperimentScheduleCourseTableDetailInfoVo courseDate(String courseDate) {
    this.courseDate = courseDate;
    return this;
  }

  /**
   * 上课日期
   * @return courseDate
  **/
  @ApiModelProperty(value = "上课日期", position = 11, required = true)


  public String getCourseDate() {
    return courseDate;
  }

  public void setCourseDate(String courseDate) {
    this.courseDate = courseDate;
  }

  public ExperimentScheduleCourseTableDetailInfoVo segmentStartTime(String segmentStartTime) {
    this.segmentStartTime = segmentStartTime;
    return this;
  }

  /**
   * 节次开始时间
   * @return segmentStartTime
  **/
  @ApiModelProperty(value = "节次开始时间", position = 12, required = true)


  public String getSegmentStartTime() {
    return segmentStartTime;
  }

  public void setSegmentStartTime(String segmentStartTime) {
    this.segmentStartTime = segmentStartTime;
  }

  public ExperimentScheduleCourseTableDetailInfoVo segmentEndTime(String segmentEndTime) {
    this.segmentEndTime = segmentEndTime;
    return this;
  }

  /**
   * 节次结束时间
   * @return segmentEndTime
  **/
  @ApiModelProperty(value = "节次结束时间", position = 13, required = true)


  public String getSegmentEndTime() {
    return segmentEndTime;
  }

  public void setSegmentEndTime(String segmentEndTime) {
    this.segmentEndTime = segmentEndTime;
  }

  public ExperimentScheduleCourseTableDetailInfoVo experimentScheduleCourseTableDetailTeacherList(
          List<ExperimentScheduleCourseTableDetailTeacherVo> experimentScheduleCourseTableDetailTeacherList) {
    this.experimentScheduleCourseTableDetailTeacherList = experimentScheduleCourseTableDetailTeacherList;
    return this;
  }

  public ExperimentScheduleCourseTableDetailInfoVo addExperimentScheduleCourseTableDetailTeacherListItem(
          ExperimentScheduleCourseTableDetailTeacherVo experimentScheduleCourseTableDetailTeacherListItem) {
    if (this.experimentScheduleCourseTableDetailTeacherList == null) {
      this.experimentScheduleCourseTableDetailTeacherList = new ArrayList<ExperimentScheduleCourseTableDetailTeacherVo>();
    }
    this.experimentScheduleCourseTableDetailTeacherList.add(experimentScheduleCourseTableDetailTeacherListItem);
    return this;
  }

  /**
   * 老师列表
   * @return experimentScheduleCourseTableDetailTeacherList
  **/
  @ApiModelProperty(value = "老师列表", position = 14, required = true)

  @Valid

  public List<ExperimentScheduleCourseTableDetailTeacherVo> getExperimentScheduleCourseTableDetailTeacherList() {
    return experimentScheduleCourseTableDetailTeacherList;
  }

  public void setExperimentScheduleCourseTableDetailTeacherList(
          List<ExperimentScheduleCourseTableDetailTeacherVo> experimentScheduleCourseTableDetailTeacherList) {
    this.experimentScheduleCourseTableDetailTeacherList = experimentScheduleCourseTableDetailTeacherList;
  }

  public ExperimentScheduleCourseTableDetailInfoVo experimentScheduleCourseTableDetailRoomList(
          List<ExperimentScheduleCourseTableDetailRoomVo> experimentScheduleCourseTableDetailRoomList) {
    this.experimentScheduleCourseTableDetailRoomList = experimentScheduleCourseTableDetailRoomList;
    return this;
  }

  public ExperimentScheduleCourseTableDetailInfoVo addExperimentScheduleCourseTableDetailRoomListItem(
          ExperimentScheduleCourseTableDetailRoomVo experimentScheduleCourseTableDetailRoomListItem) {
    if (this.experimentScheduleCourseTableDetailRoomList == null) {
      this.experimentScheduleCourseTableDetailRoomList = new ArrayList<ExperimentScheduleCourseTableDetailRoomVo>();
    }
    this.experimentScheduleCourseTableDetailRoomList.add(experimentScheduleCourseTableDetailRoomListItem);
    return this;
  }

  /**
   * 教室列表
   * @return experimentScheduleCourseTableDetailRoomList
  **/
  @ApiModelProperty(value = "教室列表", position = 15, required = true)

  @Valid

  public List<ExperimentScheduleCourseTableDetailRoomVo> getExperimentScheduleCourseTableDetailRoomList() {
    return experimentScheduleCourseTableDetailRoomList;
  }

  public void setExperimentScheduleCourseTableDetailRoomList(
          List<ExperimentScheduleCourseTableDetailRoomVo> experimentScheduleCourseTableDetailRoomList) {
    this.experimentScheduleCourseTableDetailRoomList = experimentScheduleCourseTableDetailRoomList;
  }

  public ExperimentScheduleCourseTableDetailInfoVo experimentScheduleCourseTableDetailProjectList(
          List<ExperimentScheduleCourseTableDetailProjectVo> experimentScheduleCourseTableDetailProjectList) {
    this.experimentScheduleCourseTableDetailProjectList = experimentScheduleCourseTableDetailProjectList;
    return this;
  }

  public ExperimentScheduleCourseTableDetailInfoVo addExperimentScheduleCourseTableDetailProjectListItem(
          ExperimentScheduleCourseTableDetailProjectVo experimentScheduleCourseTableDetailProjectListItem) {
    if (this.experimentScheduleCourseTableDetailProjectList == null) {
      this.experimentScheduleCourseTableDetailProjectList = new ArrayList<ExperimentScheduleCourseTableDetailProjectVo>();
    }
    this.experimentScheduleCourseTableDetailProjectList.add(experimentScheduleCourseTableDetailProjectListItem);
    return this;
  }

  /**
   * Get experimentScheduleCourseTableDetailProjectList
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
    ExperimentScheduleCourseTableDetailInfoVo experimentScheduleCourseTableDetailInfoVo = (ExperimentScheduleCourseTableDetailInfoVo) o;
    return Objects.equals(this.id, experimentScheduleCourseTableDetailInfoVo.id) &&
        Objects.equals(this.courseId, experimentScheduleCourseTableDetailInfoVo.courseId) &&
        Objects.equals(this.courseCode, experimentScheduleCourseTableDetailInfoVo.courseCode) &&
        Objects.equals(this.courseName, experimentScheduleCourseTableDetailInfoVo.courseName) &&
        Objects.equals(this.groupId, experimentScheduleCourseTableDetailInfoVo.groupId) &&
        Objects.equals(this.groupNo, experimentScheduleCourseTableDetailInfoVo.groupNo) &&
        Objects.equals(this.groupName, experimentScheduleCourseTableDetailInfoVo.groupName) &&
        Objects.equals(this.groupStudentCount, experimentScheduleCourseTableDetailInfoVo.groupStudentCount) &&
        Objects.equals(this.startSegment, experimentScheduleCourseTableDetailInfoVo.startSegment) &&
        Objects.equals(this.endSegment, experimentScheduleCourseTableDetailInfoVo.endSegment) &&
        Objects.equals(this.courseDate, experimentScheduleCourseTableDetailInfoVo.courseDate) &&
        Objects.equals(this.segmentStartTime, experimentScheduleCourseTableDetailInfoVo.segmentStartTime) &&
        Objects.equals(this.segmentEndTime, experimentScheduleCourseTableDetailInfoVo.segmentEndTime) &&
        Objects.equals(this.experimentScheduleCourseTableDetailTeacherList,
                experimentScheduleCourseTableDetailInfoVo.experimentScheduleCourseTableDetailTeacherList) &&
        Objects.equals(this.experimentScheduleCourseTableDetailRoomList,
                experimentScheduleCourseTableDetailInfoVo.experimentScheduleCourseTableDetailRoomList) &&
        Objects.equals(this.experimentScheduleCourseTableDetailProjectList,
                experimentScheduleCourseTableDetailInfoVo.experimentScheduleCourseTableDetailProjectList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, courseId, courseCode, courseName, groupId, groupNo, groupName,groupStudentCount,
            startSegment, endSegment, courseDate, segmentStartTime, segmentEndTime,
            experimentScheduleCourseTableDetailTeacherList, experimentScheduleCourseTableDetailRoomList,
            experimentScheduleCourseTableDetailProjectList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExperimentScheduleCourseTableDetailInfoVo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
    sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
    sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
    sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
    sb.append("    groupNo: ").append(toIndentedString(groupNo)).append("\n");
    sb.append("    groupName: ").append(toIndentedString(groupName)).append("\n");
    sb.append("    groupStudentCount: ").append(toIndentedString(groupStudentCount)).append("\n");
    sb.append("    startSegment: ").append(toIndentedString(startSegment)).append("\n");
    sb.append("    endSegment: ").append(toIndentedString(endSegment)).append("\n");
    sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
    sb.append("    segmentStartTime: ").append(toIndentedString(segmentStartTime)).append("\n");
    sb.append("    segmentEndTime: ").append(toIndentedString(segmentEndTime)).append("\n");
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

