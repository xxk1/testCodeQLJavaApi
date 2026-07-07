package com.lztech.site.viewmodel.superviseevaluation;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CanListenCourseTableDetailResourceVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-12-10T08:57:08.369Z")


public class CanListenCourseTableDetailResourceVo   {
  @JsonProperty("courseTableId")
  private String courseTableId = null;

  @JsonProperty("courseTableDetailId")
  private String courseTableDetailId = null;

  @JsonProperty("groupId")
  private String groupId = null;

  @JsonProperty("className")
  private String className = null;

  @JsonProperty("classNickName")
  private String classNickName = null;

  @JsonProperty("classCompositionName")
  private String classCompositionName = null;

  @JsonProperty("courseAttrName")
  private String courseAttrName = null;

  @JsonProperty("courseTableDetailSegmentStartTime")
  private String courseTableDetailSegmentStartTime = null;

  @JsonProperty("segment")
  private String segment = null;

  @JsonProperty("courseTableDetailSegmentEndTime")
  private String courseTableDetailSegmentEndTime = null;

  @JsonProperty("courseDate")
  private String courseDate = null;

  @JsonProperty("courseDateOrder")
  private String courseDateOrder = null;

  @JsonProperty("live")
  private Boolean live = null;

  @JsonProperty("studentCount")
  private String studentCount = null;

  @JsonProperty("teachers")
  @Valid
  private List<CourseTableDetailTeacherResource> teachers = null;

  @JsonProperty("rooms")
  @Valid
  private List<CourseTableDetailRoomResource> rooms = null;

  @JsonProperty("studentTypeIndex")
  private Integer studentTypeIndex = null;
  public CanListenCourseTableDetailResourceVo studentTypeIndex(Integer studentTypeIndex) {
    this.studentTypeIndex = studentTypeIndex;
    return this;
  }
  /**
   * 学生类型编号
   * @return studentTypeIndex
   **/
  @ApiModelProperty(value = "学生类型编号")
  public Integer getStudentTypeIndex() {
    return studentTypeIndex;
  }
  public void setStudentTypeIndex(Integer studentTypeIndex) {
    this.studentTypeIndex = studentTypeIndex;
  }

  @JsonProperty("studentTypeName")
  private String  studentTypeName = null;
  public CanListenCourseTableDetailResourceVo studentTypeName(String studentTypeName) {
    this.studentTypeName = studentTypeName;
    return this;
  }
  /**
   * 学生类型名称
   * @return studentTypeName
   **/
  @ApiModelProperty(value = "学生类型名称")
  public String getStudentTypeName() {
    return studentTypeName;
  }
  public void setStudentTypeName(String studentTypeName) {
    this.studentTypeName = studentTypeName;
  }


  public CanListenCourseTableDetailResourceVo courseTableId(String courseTableId) {
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

  public CanListenCourseTableDetailResourceVo courseTableDetailId(String courseTableDetailId) {
    this.courseTableDetailId = courseTableDetailId;
    return this;
  }

  /**
   * 课表明细id
   * @return courseTableDetailId
  **/
  @ApiModelProperty(value = "课表明细id")


  public String getCourseTableDetailId() {
    return courseTableDetailId;
  }

  public void setCourseTableDetailId(String courseTableDetailId) {
    this.courseTableDetailId = courseTableDetailId;
  }

  public CanListenCourseTableDetailResourceVo groupId(String groupId) {
    this.groupId = groupId;
    return this;
  }

  /**
   * 班级id
   * @return groupId
  **/
  @ApiModelProperty(value = "班级id")


  public String getGroupId() {
    return groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }

  public CanListenCourseTableDetailResourceVo className(String className) {
    this.className = className;
    return this;
  }

  /**
   * 班级名称
   * @return className
  **/
  @ApiModelProperty(value = "班级名称")


  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public CanListenCourseTableDetailResourceVo classNickName(String classNickName) {
    this.classNickName = classNickName;
    return this;
  }

  /**
   * 班级昵称
   * @return classNickName
  **/
  @ApiModelProperty(value = "班级昵称")


  public String getClassNickName() {
    return classNickName;
  }

  public void setClassNickName(String classNickName) {
    this.classNickName = classNickName;
  }

  public CanListenCourseTableDetailResourceVo classCompositionName(String classCompositionName) {
    this.classCompositionName = classCompositionName;
    return this;
  }

  /**
   * 班级组成
   * @return classCompositionName
  **/
  @ApiModelProperty(value = "班级组成")


  public String getClassCompositionName() {
    return classCompositionName;
  }

  public void setClassCompositionName(String classCompositionName) {
    this.classCompositionName = classCompositionName;
  }

  public CanListenCourseTableDetailResourceVo courseAttrName(String courseAttrName) {
    this.courseAttrName = courseAttrName;
    return this;
  }

  /**
   * 课程属性名称
   * @return courseAttrName
  **/
  @ApiModelProperty(value = "课程属性名称")


  public String getCourseAttrName() {
    return courseAttrName;
  }

  public void setCourseAttrName(String courseAttrName) {
    this.courseAttrName = courseAttrName;
  }

  public CanListenCourseTableDetailResourceVo courseTableDetailSegmentStartTime(String courseTableDetailSegmentStartTime) {
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

  public CanListenCourseTableDetailResourceVo segment(String segment) {
    this.segment = segment;
    return this;
  }

  /**
   * 上课节次
   * @return segment
  **/
  @ApiModelProperty(value = "上课节次")


  public String getSegment() {
    return segment;
  }

  public void setSegment(String segment) {
    this.segment = segment;
  }

  public CanListenCourseTableDetailResourceVo courseTableDetailSegmentEndTime(String courseTableDetailSegmentEndTime) {
    this.courseTableDetailSegmentEndTime = courseTableDetailSegmentEndTime;
    return this;
  }

  /**
   * 课表节次开始时间
   * @return courseTableDetailSegmentEndTime
  **/
  @ApiModelProperty(value = "课表节次开始时间")


  public String getCourseTableDetailSegmentEndTime() {
    return courseTableDetailSegmentEndTime;
  }

  public void setCourseTableDetailSegmentEndTime(String courseTableDetailSegmentEndTime) {
    this.courseTableDetailSegmentEndTime = courseTableDetailSegmentEndTime;
  }

  public CanListenCourseTableDetailResourceVo courseDate(String courseDate) {
    this.courseDate = courseDate;
    return this;
  }

  /**
   * 上课日期（yyyy-MM-dd）
   * @return courseDate
  **/
  @ApiModelProperty(value = "上课日期（yyyy-MM-dd）")


  public String getCourseDate() {
    return courseDate;
  }

  public void setCourseDate(String courseDate) {
    this.courseDate = courseDate;
  }

  public CanListenCourseTableDetailResourceVo courseDateOrder(String courseDateOrder) {
    this.courseDateOrder = courseDateOrder;
    return this;
  }

  /**
   * 上课日期(排序)
   * @return courseDateOrder
  **/
  @ApiModelProperty(value = "上课日期(排序)")


  public String getCourseDateOrder() {
    return courseDateOrder;
  }

  public void setCourseDateOrder(String courseDateOrder) {
    this.courseDateOrder = courseDateOrder;
  }

  public CanListenCourseTableDetailResourceVo live(Boolean live) {
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

  public CanListenCourseTableDetailResourceVo studentCount(String studentCount) {
    this.studentCount = studentCount;
    return this;
  }

  /**
   * 班级学生人数
   * @return studentCount
  **/
  @ApiModelProperty(value = "班级学生人数")


  public String getStudentCount() {
    return studentCount;
  }

  public void setStudentCount(String studentCount) {
    this.studentCount = studentCount;
  }

  public CanListenCourseTableDetailResourceVo teachers(List<CourseTableDetailTeacherResource> teachers) {
    this.teachers = teachers;
    return this;
  }

  public CanListenCourseTableDetailResourceVo addTeachersItem(CourseTableDetailTeacherResource teachersItem) {
    if (this.teachers == null) {
      this.teachers = new ArrayList<CourseTableDetailTeacherResource>();
    }
    this.teachers.add(teachersItem);
    return this;
  }

  /**
   * 老师列表
   * @return teachers
  **/
  @ApiModelProperty(value = "老师列表")

  @Valid

  public List<CourseTableDetailTeacherResource> getTeachers() {
    return teachers;
  }

  public void setTeachers(List<CourseTableDetailTeacherResource> teachers) {
    this.teachers = teachers;
  }

  public CanListenCourseTableDetailResourceVo rooms(List<CourseTableDetailRoomResource> rooms) {
    this.rooms = rooms;
    return this;
  }

  public CanListenCourseTableDetailResourceVo addRoomsItem(CourseTableDetailRoomResource roomsItem) {
    if (this.rooms == null) {
      this.rooms = new ArrayList<CourseTableDetailRoomResource>();
    }
    this.rooms.add(roomsItem);
    return this;
  }

  /**
   * 教室列表
   * @return rooms
  **/
  @ApiModelProperty(value = "教室列表")

  @Valid

  public List<CourseTableDetailRoomResource> getRooms() {
    return rooms;
  }

  public void setRooms(List<CourseTableDetailRoomResource> rooms) {
    this.rooms = rooms;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CanListenCourseTableDetailResourceVo canListenCourseTableDetailResourceVo = (CanListenCourseTableDetailResourceVo) o;
    return Objects.equals(this.courseTableId, canListenCourseTableDetailResourceVo.courseTableId) &&
        Objects.equals(this.courseTableDetailId, canListenCourseTableDetailResourceVo.courseTableDetailId) &&
        Objects.equals(this.groupId, canListenCourseTableDetailResourceVo.groupId) &&
        Objects.equals(this.className, canListenCourseTableDetailResourceVo.className) &&
        Objects.equals(this.classNickName, canListenCourseTableDetailResourceVo.classNickName) &&
        Objects.equals(this.classCompositionName, canListenCourseTableDetailResourceVo.classCompositionName) &&
        Objects.equals(this.courseAttrName, canListenCourseTableDetailResourceVo.courseAttrName) &&
        Objects.equals(this.courseTableDetailSegmentStartTime, canListenCourseTableDetailResourceVo.courseTableDetailSegmentStartTime) &&
        Objects.equals(this.segment, canListenCourseTableDetailResourceVo.segment) &&
        Objects.equals(this.courseTableDetailSegmentEndTime, canListenCourseTableDetailResourceVo.courseTableDetailSegmentEndTime) &&
        Objects.equals(this.courseDate, canListenCourseTableDetailResourceVo.courseDate) &&
        Objects.equals(this.courseDateOrder, canListenCourseTableDetailResourceVo.courseDateOrder) &&
        Objects.equals(this.live, canListenCourseTableDetailResourceVo.live) &&
        Objects.equals(this.studentCount, canListenCourseTableDetailResourceVo.studentCount) &&
        Objects.equals(this.teachers, canListenCourseTableDetailResourceVo.teachers) &&
        Objects.equals(this.rooms, canListenCourseTableDetailResourceVo.rooms) &&
        Objects.equals(this.studentTypeIndex,canListenCourseTableDetailResourceVo.studentTypeIndex) &&
        Objects.equals(this.studentTypeName,canListenCourseTableDetailResourceVo.studentTypeName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(courseTableId, courseTableDetailId, groupId, className, classNickName, classCompositionName,
            courseAttrName, courseTableDetailSegmentStartTime, segment, courseTableDetailSegmentEndTime, courseDate,
            courseDateOrder, live, studentCount, teachers, rooms,studentTypeIndex,studentTypeName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CanListenCourseTableDetailResourceVo {\n");
    
    sb.append("    courseTableId: ").append(toIndentedString(courseTableId)).append("\n");
    sb.append("    courseTableDetailId: ").append(toIndentedString(courseTableDetailId)).append("\n");
    sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
    sb.append("    className: ").append(toIndentedString(className)).append("\n");
    sb.append("    classNickName: ").append(toIndentedString(classNickName)).append("\n");
    sb.append("    classCompositionName: ").append(toIndentedString(classCompositionName)).append("\n");
    sb.append("    courseAttrName: ").append(toIndentedString(courseAttrName)).append("\n");
    sb.append("    courseTableDetailSegmentStartTime: ").append(toIndentedString(courseTableDetailSegmentStartTime)).append("\n");
    sb.append("    segment: ").append(toIndentedString(segment)).append("\n");
    sb.append("    courseTableDetailSegmentEndTime: ").append(toIndentedString(courseTableDetailSegmentEndTime)).append("\n");
    sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
    sb.append("    courseDateOrder: ").append(toIndentedString(courseDateOrder)).append("\n");
    sb.append("    live: ").append(toIndentedString(live)).append("\n");
    sb.append("    studentCount: ").append(toIndentedString(studentCount)).append("\n");
    sb.append("    teachers: ").append(toIndentedString(teachers)).append("\n");
    sb.append("    rooms: ").append(toIndentedString(rooms)).append("\n");
    sb.append("    studentTypeName: ").append(toIndentedString(studentTypeName)).append("\n");
    sb.append("    studentTypeName: ").append(toIndentedString(studentTypeName)).append("\n");
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

