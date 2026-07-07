package com.lztech.site.viewmodel.courseresource;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * CourseInfoResourceVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-07-05T06:18:00.205Z")


public class CourseInfoResourceVo   {
  @JsonProperty("courseId")
  private String courseId = null;

  @JsonProperty("courseCode")
  private String courseCode = null;

  @JsonProperty("courseName")
  private String courseName = null;

  @JsonProperty("collegeId")
  private String collegeId = null;

  @JsonProperty("collegeName")
  private String collegeName = null;

  @JsonProperty("collegeCode")
  private String collegeCode = null;

  @JsonProperty("teachingUserType")
  private String teachingUserType = null;

  @JsonProperty("classHours")
  private String classHours = null;

  @JsonProperty("courseContent")
  private String courseContent = null;

  @JsonProperty("teachingMaterial")
  private String teachingMaterial = null;

  @JsonProperty("referenceMaterials")
  private String referenceMaterials = null;

  public CourseInfoResourceVo courseId(String courseId) {
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

  public CourseInfoResourceVo courseCode(String courseCode) {
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

  public CourseInfoResourceVo courseName(String courseName) {
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

  public CourseInfoResourceVo collegeId(String collegeId) {
    this.collegeId = collegeId;
    return this;
  }

  /**
   * 学院Id
   * @return collegeId
   **/
  @ApiModelProperty(value = "学院Id")


  public String getCollegeId() {
    return collegeId;
  }

  public void setCollegeId(String collegeId) {
    this.collegeId = collegeId;
  }

  public CourseInfoResourceVo collegeName(String collegeName) {
    this.collegeName = collegeName;
    return this;
  }

  /**
   * 学院名称
   * @return collegeName
   **/
  @ApiModelProperty(value = "学院名称")


  public String getCollegeName() {
    return collegeName;
  }

  public void setCollegeName(String collegeName) {
    this.collegeName = collegeName;
  }

  public CourseInfoResourceVo collegeCode(String collegeCode) {
    this.collegeCode = collegeCode;
    return this;
  }

  /**
   * 学院编号
   * @return collegeCode
   **/
  @ApiModelProperty(value = "学院编号")


  public String getCollegeCode() {
    return collegeCode;
  }

  public void setCollegeCode(String collegeCode) {
    this.collegeCode = collegeCode;
  }

  public CourseInfoResourceVo teachingUserType(String teachingUserType) {
    this.teachingUserType = teachingUserType;
    return this;
  }

  /**
   * 授课对象
   * @return teachingUserType
   **/
  @ApiModelProperty(value = "授课对象")


  public String getTeachingUserType() {
    return teachingUserType;
  }

  public void setTeachingUserType(String teachingUserType) {
    this.teachingUserType = teachingUserType;
  }

  public CourseInfoResourceVo classHours(String classHours) {
    this.classHours = classHours;
    return this;
  }

  /**
   * 学时
   * @return classHours
   **/
  @ApiModelProperty(value = "学时")


  public String getClassHours() {
    return classHours;
  }

  public void setClassHours(String classHours) {
    this.classHours = classHours;
  }

  public CourseInfoResourceVo courseContent(String courseContent) {
    this.courseContent = courseContent;
    return this;
  }

  /**
   * 课程内容
   * @return courseContent
   **/
  @ApiModelProperty(value = "课程内容")


  public String getCourseContent() {
    return courseContent;
  }

  public void setCourseContent(String courseContent) {
    this.courseContent = courseContent;
  }

  public CourseInfoResourceVo teachingMaterial(String teachingMaterial) {
    this.teachingMaterial = teachingMaterial;
    return this;
  }

  /**
   * 教材
   * @return teachingMaterial
   **/
  @ApiModelProperty(value = "教材")


  public String getTeachingMaterial() {
    return teachingMaterial;
  }

  public void setTeachingMaterial(String teachingMaterial) {
    this.teachingMaterial = teachingMaterial;
  }

  public CourseInfoResourceVo referenceMaterials(String referenceMaterials) {
    this.referenceMaterials = referenceMaterials;
    return this;
  }

  /**
   * 参考教材
   * @return referenceMaterials
   **/
  @ApiModelProperty(value = "参考教材")


  public String getReferenceMaterials() {
    return referenceMaterials;
  }

  public void setReferenceMaterials(String referenceMaterials) {
    this.referenceMaterials = referenceMaterials;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CourseInfoResourceVo courseInfoResourceVo = (CourseInfoResourceVo) o;
    return Objects.equals(this.courseId, courseInfoResourceVo.courseId) &&
            Objects.equals(this.courseCode, courseInfoResourceVo.courseCode) &&
            Objects.equals(this.courseName, courseInfoResourceVo.courseName) &&
            Objects.equals(this.collegeId, courseInfoResourceVo.collegeId) &&
            Objects.equals(this.collegeName, courseInfoResourceVo.collegeName) &&
            Objects.equals(this.collegeCode, courseInfoResourceVo.collegeCode) &&
            Objects.equals(this.teachingUserType, courseInfoResourceVo.teachingUserType) &&
            Objects.equals(this.classHours, courseInfoResourceVo.classHours) &&
            Objects.equals(this.courseContent, courseInfoResourceVo.courseContent) &&
            Objects.equals(this.teachingMaterial, courseInfoResourceVo.teachingMaterial) &&
            Objects.equals(this.referenceMaterials, courseInfoResourceVo.referenceMaterials);
  }

  @Override
  public int hashCode() {
    return Objects.hash(courseId, courseCode, courseName, collegeId, collegeName, collegeCode, teachingUserType,
            classHours, courseContent, teachingMaterial, referenceMaterials);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CourseInfoResourceVo {\n");

    sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
    sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
    sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
    sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
    sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
    sb.append("    collegeCode: ").append(toIndentedString(collegeCode)).append("\n");
    sb.append("    teachingUserType: ").append(toIndentedString(teachingUserType)).append("\n");
    sb.append("    classHours: ").append(toIndentedString(classHours)).append("\n");
    sb.append("    courseContent: ").append(toIndentedString(courseContent)).append("\n");
    sb.append("    teachingMaterial: ").append(toIndentedString(teachingMaterial)).append("\n");
    sb.append("    referenceMaterials: ").append(toIndentedString(referenceMaterials)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

