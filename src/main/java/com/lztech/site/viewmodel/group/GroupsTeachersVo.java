package com.lztech.site.viewmodel.group;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * GroupsTeachersVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-04-12T06:47:03.479Z")


public class GroupsTeachersVo   {
  @JsonProperty("courseId")
  private String courseId = null;

  @JsonProperty("groupIds")
  @Valid
  private List<String> groupIds = null;

  public GroupsTeachersVo courseId(String courseId) {
    this.courseId = courseId;
    return this;
  }

  /**
   * 课程Id
   * @return courseId
  **/
  @ApiModelProperty(value = "课程Id")


  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }

  public GroupsTeachersVo groupIds(List<String> groupIds) {
    this.groupIds = groupIds;
    return this;
  }

  public GroupsTeachersVo addGroupIdsItem(String groupIdsItem) {
    if (this.groupIds == null) {
      this.groupIds = new ArrayList<String>();
    }
    this.groupIds.add(groupIdsItem);
    return this;
  }

  /**
   * 班级ID
   * @return groupIds
  **/
  @ApiModelProperty(value = "班级ID")


  public List<String> getGroupIds() {
    return groupIds;
  }

  public void setGroupIds(List<String> groupIds) {
    this.groupIds = groupIds;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GroupsTeachersVo groupsTeachersVo = (GroupsTeachersVo) o;
    return Objects.equals(this.courseId, groupsTeachersVo.courseId) &&
        Objects.equals(this.groupIds, groupsTeachersVo.groupIds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(courseId, groupIds);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GroupsTeachersVo {\n");
    
    sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
    sb.append("    groupIds: ").append(toIndentedString(groupIds)).append("\n");
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

