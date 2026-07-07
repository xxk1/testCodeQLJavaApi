package com.lztech.site.viewmodel.teachingpackage;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * QuoteTeachingPackageVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-03-27T11:35:08.340Z")


public class QuoteTeachingPackageVo   {
  @JsonProperty("teachingPackageId")
  private String teachingPackageId = null;

  @JsonProperty("courseId")
  private String courseId = null;

  @JsonProperty("versionId")
  private String versionId = null;

  @JsonProperty("userId")
  private String userId = null;

  @JsonProperty("userName")
  private String userName = null;

  @JsonProperty("teachingPackageQuote")
  @Valid
  private List<TeachingPackageCourseStructureVo> teachingPackageQuote = null;

  public QuoteTeachingPackageVo teachingPackageId(String teachingPackageId) {
    this.teachingPackageId = teachingPackageId;
    return this;
  }

  /**
   * 教学包ID
   * @return teachingPackageId
  **/
  @ApiModelProperty(value = "教学包ID")


  public String getTeachingPackageId() {
    return teachingPackageId;
  }

  public void setTeachingPackageId(String teachingPackageId) {
    this.teachingPackageId = teachingPackageId;
  }

  public QuoteTeachingPackageVo courseId(String courseId) {
    this.courseId = courseId;
    return this;
  }

  /**
   * 课程ID
   * @return courseId
  **/
  @ApiModelProperty(value = "课程ID")


  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }

  public QuoteTeachingPackageVo versionId(String versionId) {
    this.versionId = versionId;
    return this;
  }

  /**
   * 版本ID
   * @return versionId
  **/
  @ApiModelProperty(value = "版本ID")


  public String getVersionId() {
    return versionId;
  }

  public void setVersionId(String versionId) {
    this.versionId = versionId;
  }

  public QuoteTeachingPackageVo userId(String userId) {
    this.userId = userId;
    return this;
  }

  /**
   * 引用人ID
   * @return userId
  **/
  @ApiModelProperty(value = "引用人ID")


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public QuoteTeachingPackageVo userName(String userName) {
    this.userName = userName;
    return this;
  }

  /**
   * 引用人名称
   * @return userName
  **/
  @ApiModelProperty(value = "引用人名称")


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public QuoteTeachingPackageVo teachingPackageQuote(List<TeachingPackageCourseStructureVo> teachingPackageQuote) {
    this.teachingPackageQuote = teachingPackageQuote;
    return this;
  }

  public QuoteTeachingPackageVo addTeachingPackageQuoteItem(TeachingPackageCourseStructureVo teachingPackageQuoteItem) {
    if (this.teachingPackageQuote == null) {
      this.teachingPackageQuote = new ArrayList<TeachingPackageCourseStructureVo>();
    }
    this.teachingPackageQuote.add(teachingPackageQuoteItem);
    return this;
  }

  /**
   * 引用课程结构
   * @return teachingPackageQuote
  **/
  @ApiModelProperty(value = "引用课程结构")

  @Valid

  public List<TeachingPackageCourseStructureVo> getTeachingPackageQuote() {
    return teachingPackageQuote;
  }

  public void setTeachingPackageQuote(List<TeachingPackageCourseStructureVo> teachingPackageQuote) {
    this.teachingPackageQuote = teachingPackageQuote;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QuoteTeachingPackageVo quoteTeachingPackageVo = (QuoteTeachingPackageVo) o;
    return Objects.equals(this.teachingPackageId, quoteTeachingPackageVo.teachingPackageId) &&
        Objects.equals(this.courseId, quoteTeachingPackageVo.courseId) &&
        Objects.equals(this.versionId, quoteTeachingPackageVo.versionId) &&
        Objects.equals(this.userId, quoteTeachingPackageVo.userId) &&
        Objects.equals(this.userName, quoteTeachingPackageVo.userName) &&
        Objects.equals(this.teachingPackageQuote, quoteTeachingPackageVo.teachingPackageQuote);
  }

  @Override
  public int hashCode() {
    return Objects.hash(teachingPackageId, courseId, versionId, userId, userName, teachingPackageQuote);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QuoteTeachingPackageVo {\n");
    
    sb.append("    teachingPackageId: ").append(toIndentedString(teachingPackageId)).append("\n");
    sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
    sb.append("    versionId: ").append(toIndentedString(versionId)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
    sb.append("    teachingPackageQuote: ").append(toIndentedString(teachingPackageQuote)).append("\n");
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

