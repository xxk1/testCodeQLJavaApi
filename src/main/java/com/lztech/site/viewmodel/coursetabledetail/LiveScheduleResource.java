package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.math.BigInteger;
import java.util.Objects;

/**
 * LiveScheduleResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-08-23T02:11:25.521Z")


public class LiveScheduleResource   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("courseTableDetailId")
  private String courseTableDetailId = null;

  @JsonProperty("courseCode")
  private String courseCode = null;

  @JsonProperty("teacherNameAndNo")
  private String teacherNameAndNo = null;

  @JsonProperty("schoolYear")
  private String schoolYear = null;

  @JsonProperty("term")
  private Integer term = null;

  @JsonProperty("courseDate")
  private String courseDate = null;

  @JsonProperty("courseName")
  private String courseName = null;

  @JsonProperty("segments")
  private String segments = null;

  @JsonProperty("segmentStartTime")
  private String segmentStartTime = null;

  @JsonProperty("segmentEndTime")
  private String segmentEndTime = null;

  @JsonProperty("startTime")
  private String startTime = null;

  @JsonProperty("endTime")
  private String endTime = null;

  @JsonProperty("courseTableId")
  private String courseTableId = null;

  @JsonProperty("roomId")
  private String roomId = null;

  @JsonProperty("roomName")
  private String roomName = null;

  @JsonProperty("groupName")
  private String groupName = null;

  @JsonProperty("classCompositionName")
  private String classCompositionName = null;

  @JsonProperty("hasLiveBroadcast")
  private BigInteger hasLiveBroadcast = null;

  public LiveScheduleResource id(String id) {
    this.id = id;
    return this;
  }

  /**
   * 课表自定义id
   * @return id
  **/
  @ApiModelProperty(value = "课表自定义id")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public LiveScheduleResource courseTableDetailId(String courseTableDetailId) {
    this.courseTableDetailId = courseTableDetailId;
    return this;
  }

  /**
   * 课表id
   * @return courseTableDetailId
  **/
  @ApiModelProperty(value = "课表id")


  public String getCourseTableDetailId() {
    return courseTableDetailId;
  }

  public void setCourseTableDetailId(String courseTableDetailId) {
    this.courseTableDetailId = courseTableDetailId;
  }

  public LiveScheduleResource courseCode(String courseCode) {
    this.courseCode = courseCode;
    return this;
  }

  /**
   * 课程编号
   * @return courseCode
  **/
  @ApiModelProperty(value = "课程编号")


  public String getCourseCode() {
    return courseCode;
  }

  public void setCourseCode(String courseCode) {
    this.courseCode = courseCode;
  }

  public LiveScheduleResource teacherNameAndNo(String teacherNameAndNo) {
    this.teacherNameAndNo = teacherNameAndNo;
    return this;
  }

  /**
   * 教师姓名和编号（格式：名称 编号）
   * @return teacherNameAndNo
  **/
  @ApiModelProperty(value = "教师姓名和编号（格式：名称 编号）")


  public String getTeacherNameAndNo() {
    return teacherNameAndNo;
  }

  public void setTeacherNameAndNo(String teacherNameAndNo) {
    this.teacherNameAndNo = teacherNameAndNo;
  }

  public LiveScheduleResource schoolYear(String schoolYear) {
    this.schoolYear = schoolYear;
    return this;
  }

  /**
   * 学年
   * @return schoolYear
  **/
  @ApiModelProperty(value = "学年")


  public String getSchoolYear() {
    return schoolYear;
  }

  public void setSchoolYear(String schoolYear) {
    this.schoolYear = schoolYear;
  }

  public LiveScheduleResource term(Integer term) {
    this.term = term;
    return this;
  }

  /**
   * 学期
   * @return term
  **/
  @ApiModelProperty(value = "学期")


  public Integer getTerm() {
    return term;
  }

  public void setTerm(Integer term) {
    this.term = term;
  }

  public LiveScheduleResource courseDate(String courseDate) {
    this.courseDate = courseDate;
    return this;
  }

  /**
   * 上课日期
   * @return courseDate
  **/
  @ApiModelProperty(value = "上课日期")


  public String getCourseDate() {
    return courseDate;
  }

  public void setCourseDate(String courseDate) {
    this.courseDate = courseDate;
  }

  public LiveScheduleResource courseName(String courseName) {
    this.courseName = courseName;
    return this;
  }

  /**
   * 课程名称
   * @return courseName
  **/
  @ApiModelProperty(value = "课程名称")


  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public LiveScheduleResource segments(String segments) {
    this.segments = segments;
    return this;
  }

  /**
   * 节次
   * @return segments
  **/
  @ApiModelProperty(value = "节次")


  public String getSegments() {
    return segments;
  }

  public void setSegments(String segments) {
    this.segments = segments;
  }

  public LiveScheduleResource segmentStartTime(String segmentStartTime) {
    this.segmentStartTime = segmentStartTime;
    return this;
  }

  /**
   * 节次开始时间
   * @return segmentStartTime
  **/
  @ApiModelProperty(value = "节次开始时间")


  public String getSegmentStartTime() {
    return segmentStartTime;
  }

  public void setSegmentStartTime(String segmentStartTime) {
    this.segmentStartTime = segmentStartTime;
  }

  public LiveScheduleResource segmentEndTime(String segmentEndTime) {
    this.segmentEndTime = segmentEndTime;
    return this;
  }

  /**
   * 节次结束时间
   * @return segmentEndTime
  **/
  @ApiModelProperty(value = "节次结束时间")


  public String getSegmentEndTime() {
    return segmentEndTime;
  }

  public void setSegmentEndTime(String segmentEndTime) {
    this.segmentEndTime = segmentEndTime;
  }

  public LiveScheduleResource startTime(String startTime) {
    this.startTime = startTime;
    return this;
  }

  /**
   * 大节次开始时间
   * @return startTime
  **/
  @ApiModelProperty(value = "大节次开始时间")


  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public LiveScheduleResource endTime(String endTime) {
    this.endTime = endTime;
    return this;
  }

  /**
   * 大节次结束时间
   * @return endTime
  **/
  @ApiModelProperty(value = "大节次结束时间")


  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public LiveScheduleResource courseTableId(String courseTableId) {
    this.courseTableId = courseTableId;
    return this;
  }

  /**
   * 开课id
   * @return courseTableId
  **/
  @ApiModelProperty(value = "开课id")


  public String getCourseTableId() {
    return courseTableId;
  }

  public void setCourseTableId(String courseTableId) {
    this.courseTableId = courseTableId;
  }

  public LiveScheduleResource roomId(String roomId) {
    this.roomId = roomId;
    return this;
  }

  /**
   * 教室id
   * @return roomId
  **/
  @ApiModelProperty(value = "教室id")


  public String getRoomId() {
    return roomId;
  }

  public void setRoomId(String roomId) {
    this.roomId = roomId;
  }

  public LiveScheduleResource roomName(String roomName) {
    this.roomName = roomName;
    return this;
  }

  /**
   * 教室名称
   * @return roomName
  **/
  @ApiModelProperty(value = "教室名称")


  public String getRoomName() {
    return roomName;
  }

  public void setRoomName(String roomName) {
    this.roomName = roomName;
  }

  public LiveScheduleResource groupName(String groupName) {
    this.groupName = groupName;
    return this;
  }

  /**
   * 班级名称
   * @return groupName
  **/
  @ApiModelProperty(value = "班级名称")


  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public LiveScheduleResource classCompositionName(String classCompositionName) {
    this.classCompositionName = classCompositionName;
    return this;
  }

  /**
   * 班级名称组成
   * @return classCompositionName
  **/
  @ApiModelProperty(value = "班级名称组成")


  public String getClassCompositionName() {
    return classCompositionName;
  }

  public void setClassCompositionName(String classCompositionName) {
    this.classCompositionName = classCompositionName;
  }



  /**
   * 是否在直播(0:没有;1:有)
   * @return hasLiveBroadcast
  **/
  @ApiModelProperty(value = "是否在直播(0:没有;1:有)")

  public LiveScheduleResource hasLiveBroadcast(BigInteger hasLiveBroadcast) {
    this.hasLiveBroadcast = hasLiveBroadcast;
    return this;
  }

  public BigInteger getHasLiveBroadcast() {
    return hasLiveBroadcast;
  }

  public void setHasLiveBroadcast(BigInteger hasLiveBroadcast) {
    this.hasLiveBroadcast = hasLiveBroadcast;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LiveScheduleResource liveScheduleResource = (LiveScheduleResource) o;
    return Objects.equals(this.id, liveScheduleResource.id) &&
        Objects.equals(this.courseTableDetailId, liveScheduleResource.courseTableDetailId) &&
        Objects.equals(this.courseCode, liveScheduleResource.courseCode) &&
        Objects.equals(this.teacherNameAndNo, liveScheduleResource.teacherNameAndNo) &&
        Objects.equals(this.schoolYear, liveScheduleResource.schoolYear) &&
        Objects.equals(this.term, liveScheduleResource.term) &&
        Objects.equals(this.courseDate, liveScheduleResource.courseDate) &&
        Objects.equals(this.courseName, liveScheduleResource.courseName) &&
        Objects.equals(this.segments, liveScheduleResource.segments) &&
        Objects.equals(this.segmentStartTime, liveScheduleResource.segmentStartTime) &&
        Objects.equals(this.segmentEndTime, liveScheduleResource.segmentEndTime) &&
        Objects.equals(this.startTime, liveScheduleResource.startTime) &&
        Objects.equals(this.endTime, liveScheduleResource.endTime) &&
        Objects.equals(this.courseTableId, liveScheduleResource.courseTableId) &&
        Objects.equals(this.roomId, liveScheduleResource.roomId) &&
        Objects.equals(this.roomName, liveScheduleResource.roomName) &&
        Objects.equals(this.groupName, liveScheduleResource.groupName) &&
        Objects.equals(this.classCompositionName, liveScheduleResource.classCompositionName) &&
        Objects.equals(this.hasLiveBroadcast, liveScheduleResource.hasLiveBroadcast);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, courseTableDetailId, courseCode, teacherNameAndNo, schoolYear, term, courseDate, courseName,
            segments, segmentStartTime, segmentEndTime, startTime, endTime, courseTableId, roomId, roomName,
            groupName, classCompositionName, hasLiveBroadcast);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LiveScheduleResource {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    courseTableDetailId: ").append(toIndentedString(courseTableDetailId)).append("\n");
    sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
    sb.append("    teacherNameAndNo: ").append(toIndentedString(teacherNameAndNo)).append("\n");
    sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
    sb.append("    term: ").append(toIndentedString(term)).append("\n");
    sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
    sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
    sb.append("    segments: ").append(toIndentedString(segments)).append("\n");
    sb.append("    segmentStartTime: ").append(toIndentedString(segmentStartTime)).append("\n");
    sb.append("    segmentEndTime: ").append(toIndentedString(segmentEndTime)).append("\n");
    sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
    sb.append("    endTime: ").append(toIndentedString(endTime)).append("\n");
    sb.append("    courseTableId: ").append(toIndentedString(courseTableId)).append("\n");
    sb.append("    roomId: ").append(toIndentedString(roomId)).append("\n");
    sb.append("    roomName: ").append(toIndentedString(roomName)).append("\n");
    sb.append("    groupName: ").append(toIndentedString(groupName)).append("\n");
    sb.append("    classCompositionName: ").append(toIndentedString(classCompositionName)).append("\n");
    sb.append("    hasLiveBroadcast: ").append(toIndentedString(hasLiveBroadcast)).append("\n");
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

