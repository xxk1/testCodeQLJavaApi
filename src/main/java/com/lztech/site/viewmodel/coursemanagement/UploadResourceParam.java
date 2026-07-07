package com.lztech.site.viewmodel.coursemanagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * UploadResourceParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-01-21T02:30:01.365Z")




public class UploadResourceParam   {
  @JsonProperty("courseStructureId")
  private String courseStructureId = null;

  @JsonProperty("userId")
  private String userId = null;

  @JsonProperty("userName")
  private String userName = null;

  @JsonProperty("courseResourceList")
  @Valid
  private List<ResourceVo> courseResourceList = null;

  public UploadResourceParam courseStructureId(String courseStructureId) {
    this.courseStructureId = courseStructureId;
    return this;
  }

  /**
   * 课程结构id
   * @return courseStructureId
  **/
  @ApiModelProperty(value = "课程结构id")


  public String getCourseStructureId() {
    return courseStructureId;
  }

  public void setCourseStructureId(String courseStructureId) {
    this.courseStructureId = courseStructureId;
  }

  public UploadResourceParam userId(String userId) {
    this.userId = userId;
    return this;
  }

  /**
   * 用户id
   * @return userId
  **/
  @ApiModelProperty(value = "用户id")


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public UploadResourceParam userName(String userName) {
    this.userName = userName;
    return this;
  }

  /**
   * 用户名称
   * @return userName
  **/
  @ApiModelProperty(value = "用户名称")


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public UploadResourceParam courseResourceList(List<ResourceVo> courseResourceList) {
    this.courseResourceList = courseResourceList;
    return this;
  }

  public UploadResourceParam addCourseResourceListItem(ResourceVo courseResourceListItem) {
    if (this.courseResourceList == null) {
      this.courseResourceList = new ArrayList<ResourceVo>();
    }
    this.courseResourceList.add(courseResourceListItem);
    return this;
  }

  /**
   * 资源集合
   * @return courseResourceList
  **/
  @ApiModelProperty(value = "资源集合")

  @Valid

  public List<ResourceVo> getCourseResourceList() {
    return courseResourceList;
  }

  public void setCourseResourceList(List<ResourceVo> courseResourceList) {
    this.courseResourceList = courseResourceList;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UploadResourceParam uploadResourceParam = (UploadResourceParam) o;
    return Objects.equals(this.courseStructureId, uploadResourceParam.courseStructureId) &&
        Objects.equals(this.userId, uploadResourceParam.userId) &&
        Objects.equals(this.userName, uploadResourceParam.userName) &&
        Objects.equals(this.courseResourceList, uploadResourceParam.courseResourceList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(courseStructureId, userId, userName, courseResourceList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UploadResourceParam {\n");
    
    sb.append("    courseStructureId: ").append(toIndentedString(courseStructureId)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
    sb.append("    courseResourceList: ").append(toIndentedString(courseResourceList)).append("\n");
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

