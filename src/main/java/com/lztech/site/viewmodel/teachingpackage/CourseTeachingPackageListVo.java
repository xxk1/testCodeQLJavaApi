package com.lztech.site.viewmodel.teachingpackage;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * CourseTeachingPackageListVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-03-26T07:36:24.014Z")


public class CourseTeachingPackageListVo   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("teachingPackageName")
  private String teachingPackageName = null;

  @JsonProperty("courseVersionId")
  private String courseVersionId = null;

  @JsonProperty("courseId")
  private String courseId = null;

  @JsonProperty("courseName")
  private String courseName = null;

  @JsonProperty("visionNumber")
  private Integer visionNumber = null;

  @JsonProperty("visionTime")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date visionTime = null;

  @JsonProperty("quoteNum")
  private Integer quoteNum = null;

  @JsonProperty("filePath")
  private String filePath = null;

  @JsonProperty("innerIp")
  private String innerIp = null;

  @JsonProperty("outerIp")
  private String outerIp = null;

  public CourseTeachingPackageListVo id(String id) {
    this.id = id;
    return this;
  }

  /**
   * 教学包Id
   * @return id
  **/
  @ApiModelProperty(value = "教学包Id")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public CourseTeachingPackageListVo teachingPackageName(String teachingPackageName) {
    this.teachingPackageName = teachingPackageName;
    return this;
  }

  /**
   * 课程包名称
   * @return teachingPackageName
  **/
  @ApiModelProperty(value = "课程包名称")


  public String getTeachingPackageName() {
    return teachingPackageName;
  }

  public void setTeachingPackageName(String teachingPackageName) {
    this.teachingPackageName = teachingPackageName;
  }

  public CourseTeachingPackageListVo courseVersionId(String courseVersionId) {
    this.courseVersionId = courseVersionId;
    return this;
  }

  /**
   * 版本ID
   * @return courseVersionId
  **/
  @ApiModelProperty(value = "版本ID")


  public String getCourseVersionId() {
    return courseVersionId;
  }

  public void setCourseVersionId(String courseVersionId) {
    this.courseVersionId = courseVersionId;
  }

  public CourseTeachingPackageListVo courseId(String courseId) {
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

  public CourseTeachingPackageListVo courseName(String courseName) {
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

  public CourseTeachingPackageListVo visionNumber(Integer visionNumber) {
    this.visionNumber = visionNumber;
    return this;
  }

  /**
   * 版本号
   * @return visionNumber
  **/
  @ApiModelProperty(value = "版本号")


  public Integer getVisionNumber() {
    return visionNumber;
  }

  public void setVisionNumber(Integer visionNumber) {
    this.visionNumber = visionNumber;
  }

  public CourseTeachingPackageListVo visionTime(Date visionTime) {
    this.visionTime = visionTime;
    return this;
  }

  /**
   * 版本时间
   * @return visionTime
  **/
  @ApiModelProperty(value = "版本时间")


  public Date getVisionTime() {
    return visionTime;
  }

  public void setVisionTime(Date visionTime) {
    this.visionTime = visionTime;
  }

  public CourseTeachingPackageListVo quoteNum(Integer quoteNum) {
    this.quoteNum = quoteNum;
    return this;
  }

  /**
   * 引用次数
   * @return quoteNum
  **/
  @ApiModelProperty(value = "引用次数")


  public Integer getQuoteNum() {
    return quoteNum;
  }

  public void setQuoteNum(Integer quoteNum) {
    this.quoteNum = quoteNum;
  }

  public CourseTeachingPackageListVo filePath(String filePath) {
    this.filePath = filePath;
    return this;
  }

  /**
   * 文件路径
   * @return filePath
  **/
  @ApiModelProperty(value = "文件路径")


  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public CourseTeachingPackageListVo innerIp(String innerIp) {
    this.innerIp = innerIp;
    return this;
  }

  /**
   * 内网ip
   * @return innerIp
  **/
  @ApiModelProperty(value = "内网ip")


  public String getInnerIp() {
    return innerIp;
  }

  public void setInnerIp(String innerIp) {
    this.innerIp = innerIp;
  }

  public CourseTeachingPackageListVo outerIp(String outerIp) {
    this.outerIp = outerIp;
    return this;
  }

  /**
   * 外网IP
   * @return outerIp
  **/
  @ApiModelProperty(value = "外网IP")


  public String getOuterIp() {
    return outerIp;
  }

  public void setOuterIp(String outerIp) {
    this.outerIp = outerIp;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CourseTeachingPackageListVo courseTeachingPackageListVo = (CourseTeachingPackageListVo) o;
    return Objects.equals(this.id, courseTeachingPackageListVo.id) &&
        Objects.equals(this.teachingPackageName, courseTeachingPackageListVo.teachingPackageName) &&
        Objects.equals(this.courseVersionId, courseTeachingPackageListVo.courseVersionId) &&
        Objects.equals(this.courseId, courseTeachingPackageListVo.courseId) &&
        Objects.equals(this.courseName, courseTeachingPackageListVo.courseName) &&
        Objects.equals(this.visionNumber, courseTeachingPackageListVo.visionNumber) &&
        Objects.equals(this.visionTime, courseTeachingPackageListVo.visionTime) &&
        Objects.equals(this.quoteNum, courseTeachingPackageListVo.quoteNum) &&
        Objects.equals(this.filePath, courseTeachingPackageListVo.filePath) &&
        Objects.equals(this.innerIp, courseTeachingPackageListVo.innerIp) &&
        Objects.equals(this.outerIp, courseTeachingPackageListVo.outerIp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, teachingPackageName, courseVersionId, courseId,
            courseName, visionNumber, visionTime, quoteNum, filePath, innerIp, outerIp);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CourseTeachingPackageListVo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    teachingPackageName: ").append(toIndentedString(teachingPackageName)).append("\n");
    sb.append("    courseVersionId: ").append(toIndentedString(courseVersionId)).append("\n");
    sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
    sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
    sb.append("    visionNumber: ").append(toIndentedString(visionNumber)).append("\n");
    sb.append("    visionTime: ").append(toIndentedString(visionTime)).append("\n");
    sb.append("    quoteNum: ").append(toIndentedString(quoteNum)).append("\n");
    sb.append("    filePath: ").append(toIndentedString(filePath)).append("\n");
    sb.append("    innerIp: ").append(toIndentedString(innerIp)).append("\n");
    sb.append("    outerIp: ").append(toIndentedString(outerIp)).append("\n");
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

