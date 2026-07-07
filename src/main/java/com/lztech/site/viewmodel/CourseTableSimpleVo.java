package com.lztech.site.viewmodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseTableSimpleVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-17T11:48:02.042Z")

public class CourseTableSimpleVo {
  @JsonProperty("courseTableId")
  private String courseTableId = null;

  @JsonProperty("courseTableDetailId")
  private String courseTableDetailId = null;

  @JsonProperty("groupId")
  private String groupId = null;

  @JsonProperty("collegeId")
  private String collegeId = null;

  @JsonProperty("collegeName")
  private String collegeName = null;

  @JsonProperty("roomIds")
  private String roomIds = null;


  public CourseTableSimpleVo roomIds(String roomIds) {
    this.roomIds = roomIds;
    return this;
  }

  /**
   * 课表对应教室Id
   *
   * @return roomIds
   **/
  @ApiModelProperty(value = "课表对应教室Id")


  public String getRoomIds() {
    return roomIds;
  }

  public void setRoomIds(String roomIds) {
    this.roomIds = roomIds;
  }



  public CourseTableSimpleVo courseTableId(String courseTableId) {
    this.courseTableId = courseTableId;
    return this;
  }

  /**
   * 课表id
   *
   * @return courseTableId
   **/
  @ApiModelProperty(value = "课表id")


  public String getCourseTableId() {
    return courseTableId;
  }

  public void setCourseTableId(String courseTableId) {
    this.courseTableId = courseTableId;
  }

  public CourseTableSimpleVo courseTableDetailId(String courseTableDetailId) {
    this.courseTableDetailId = courseTableDetailId;
    return this;
  }

  /**
   * 课表明细id
   *
   * @return courseTableDetailId
   **/
  @ApiModelProperty(value = "课表明细id")


  public String getCourseTableDetailId() {
    return courseTableDetailId;
  }

  public void setCourseTableDetailId(String courseTableDetailId) {
    this.courseTableDetailId = courseTableDetailId;
  }

  public CourseTableSimpleVo groupId(String groupId) {
    this.groupId = groupId;
    return this;
  }

  /**
   * 组id
   *
   * @return groupId
   **/
  @ApiModelProperty(value = "组id")


  public String getGroupId() {
    return groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }

  public CourseTableSimpleVo collegeId(String collegeId) {
    this.collegeId = collegeId;
    return this;
  }

  /**
   * 学院id
   *
   * @return collegeId
   **/
  @ApiModelProperty(value = "学院id")


  public String getCollegeId() {
    return collegeId;
  }

  public void setCollegeId(String collegeId) {
    this.collegeId = collegeId;
  }

  public CourseTableSimpleVo collegeName(String collegeName) {
    this.collegeName = collegeName;
    return this;
  }

  /**
   * 学院名称
   *
   * @return collegeName
   **/
  @ApiModelProperty(value = "学院名称")


  public String getCollegeName() {
    return collegeName;
  }

  public void setCollegeName(String collegeName) {
    this.collegeName = collegeName;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CourseTableSimpleVo courseTableSimpleVo = (CourseTableSimpleVo) o;
    return Objects.equals(this.courseTableId, courseTableSimpleVo.courseTableId) &&
            Objects.equals(this.courseTableDetailId, courseTableSimpleVo.courseTableDetailId) &&
            Objects.equals(this.groupId, courseTableSimpleVo.groupId) &&
            Objects.equals(this.collegeId, courseTableSimpleVo.collegeId) &&
            Objects.equals(this.roomIds, courseTableSimpleVo.roomIds) &&
            Objects.equals(this.collegeName, courseTableSimpleVo.collegeName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(courseTableId, courseTableDetailId, groupId, collegeId, collegeName,roomIds);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CourseTableSimpleVo {\n");

    sb.append("    courseTableId: ").append(toIndentedString(courseTableId)).append("\n");
    sb.append("    courseTableDetailId: ").append(toIndentedString(courseTableDetailId)).append("\n");
    sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
    sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
    sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
    sb.append("    roomIds: ").append(toIndentedString(roomIds)).append("\n");
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

