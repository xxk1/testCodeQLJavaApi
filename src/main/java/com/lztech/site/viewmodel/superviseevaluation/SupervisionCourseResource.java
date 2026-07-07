package com.lztech.site.viewmodel.superviseevaluation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * SupervisionCourseResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-12-03T02:12:08.142Z")




public class SupervisionCourseResource   {
  @JsonProperty("teachers")
  private String teachers = null;

  @JsonProperty("live")
  private Boolean live = null;

  @JsonProperty("liveCourseTableDetailId")
  private String liveCourseTableDetailId = null;

  @JsonProperty("courseTableDetailSegmentStartTime")
  private String courseTableDetailSegmentStartTime = null;

  @JsonProperty("courseTableDetailSegmentEndTime")
  private String courseTableDetailSegmentEndTime = null;

  @JsonProperty("roomId")
  private String roomId = null;

  @JsonProperty("roomName")
  private String roomName = null;

  @JsonProperty("courseDate")
  private String courseDate = null;

  @JsonProperty("segment")
  private String segment = null;

  @JsonProperty("groupId")
  private String groupId = null;

  public SupervisionCourseResource teachers(String teachers) {
    this.teachers = teachers;
    return this;
  }

  /**
   * 老师信息（id+工号+姓名，多个逗号分割）
   * @return teachers
  **/
  @ApiModelProperty(value = "老师信息（id+工号+姓名，多个逗号分割）")


  public String getTeachers() {
    return teachers;
  }

  public void setTeachers(String teachers) {
    this.teachers = teachers;
  }

  public SupervisionCourseResource live(Boolean live) {
    this.live = live;
    return this;
  }

  /**
   * 是否直播中（0-不在直播中，1-直播中）
   * @return live
  **/
  @ApiModelProperty(value = "是否直播中（0-不在直播中，1-直播中）")


  public Boolean isLive() {
    return live;
  }

  public void setLive(Boolean live) {
    this.live = live;
  }

  public SupervisionCourseResource liveCourseTableDetailId(String liveCourseTableDetailId) {
    this.liveCourseTableDetailId = liveCourseTableDetailId;
    return this;
  }

  /**
   * 直播中的课表明细id
   * @return liveCourseTableDetailId
  **/
  @ApiModelProperty(value = "直播中的课表明细id")


  public String getLiveCourseTableDetailId() {
    return liveCourseTableDetailId;
  }

  public void setLiveCourseTableDetailId(String liveCourseTableDetailId) {
    this.liveCourseTableDetailId = liveCourseTableDetailId;
  }

  public SupervisionCourseResource roomId(String roomId) {
    this.roomId = roomId;
    return this;
  }

  /**
   * 上课教室id
   * @return roomId
  **/
  @ApiModelProperty(value = "上课教室id")


  public String getRoomId() {
    return roomId;
  }

  public void setRoomId(String roomId) {
    this.roomId = roomId;
  }

  public SupervisionCourseResource roomName(String roomName) {
    this.roomName = roomName;
    return this;
  }

  /**
   * 上课教室名称
   * @return roomName
  **/
  @ApiModelProperty(value = "上课教室名称")


  public String getRoomName() {
    return roomName;
  }

  public void setRoomName(String roomName) {
    this.roomName = roomName;
  }

  public SupervisionCourseResource courseDate(String courseDate) {
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

  public SupervisionCourseResource segment(String segment) {
    this.segment = segment;
    return this;
  }

  /**
   * 节次
   * @return segment
  **/
  @ApiModelProperty(value = "节次")


  public String getSegment() {
    return segment;
  }

  public void setSegment(String segment) {
    this.segment = segment;
  }

  public SupervisionCourseResource groupId(String groupId) {
    this.groupId = groupId;
    return this;
  }

  /**
   * 组ID
   * @return groupId
  **/
  @ApiModelProperty(value = "组ID")


  public String getGroupId() {
    return groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }

  public SupervisionCourseResource courseTableDetailSegmentStartTime(String courseTableDetailSegmentStartTime) {
    this.courseTableDetailSegmentStartTime = courseTableDetailSegmentStartTime;
    return this;
  }

  /**
   * 课表节次开始时间
   * @return courseTableDetailSegmentStartTime
   **/
  @ApiModelProperty(value = "课表节次开始时间")

  public String getCourseTableDetailSegmentStartTime() {
    return courseTableDetailSegmentStartTime;
  }

  public void setCourseTableDetailSegmentStartTime(String courseTableDetailSegmentStartTime) {
    this.courseTableDetailSegmentStartTime = courseTableDetailSegmentStartTime;
  }


  public SupervisionCourseResource courseTableDetailSegmentEndTime(String courseTableDetailSegmentEndTime) {
    this.courseTableDetailSegmentEndTime = courseTableDetailSegmentEndTime;
    return this;
  }

  /**
   * 课表节次结束时间
   * @return courseTableDetailSegmentEndTime
   **/
  @ApiModelProperty(value = "课表节次结束时间")

  public String getCourseTableDetailSegmentEndTime() {
    return courseTableDetailSegmentEndTime;
  }

  public void setCourseTableDetailSegmentEndTime(String courseTableDetailSegmentEndTime) {
    this.courseTableDetailSegmentEndTime = courseTableDetailSegmentEndTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SupervisionCourseResource supervisionCourseResource = (SupervisionCourseResource) o;
    return Objects.equals(this.teachers, supervisionCourseResource.teachers) &&
        Objects.equals(this.live, supervisionCourseResource.live) &&
        Objects.equals(this.liveCourseTableDetailId, supervisionCourseResource.liveCourseTableDetailId) &&
        Objects.equals(this.courseTableDetailSegmentStartTime, supervisionCourseResource.courseTableDetailSegmentStartTime) &&
        Objects.equals(this.courseTableDetailSegmentEndTime, supervisionCourseResource.courseTableDetailSegmentEndTime) &&
        Objects.equals(this.roomId, supervisionCourseResource.roomId) &&
        Objects.equals(this.roomName, supervisionCourseResource.roomName) &&
        Objects.equals(this.courseDate, supervisionCourseResource.courseDate) &&
        Objects.equals(this.segment, supervisionCourseResource.segment) &&
        Objects.equals(this.groupId, supervisionCourseResource.groupId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(teachers, live, liveCourseTableDetailId,courseTableDetailSegmentStartTime,courseTableDetailSegmentEndTime,
            roomId, roomName, courseDate, segment, groupId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SupervisionCourseResource {\n");
    
    sb.append("    teachers: ").append(toIndentedString(teachers)).append("\n");
    sb.append("    live: ").append(toIndentedString(live)).append("\n");
    sb.append("    liveCourseTableDetailId: ").append(toIndentedString(liveCourseTableDetailId)).append("\n");
    sb.append("    roomId: ").append(toIndentedString(roomId)).append("\n");
    sb.append("    roomName: ").append(toIndentedString(roomName)).append("\n");
    sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
    sb.append("    segment: ").append(toIndentedString(segment)).append("\n");
    sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
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

