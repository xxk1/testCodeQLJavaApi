package com.lztech.site.viewmodel.coursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseTableBasicInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-10-19T05:42:15.865Z")




public class CourseTableBasicInfo   {
  @JsonProperty("courseId")
  private String courseId = null;

  @JsonProperty("courseName")
  private String courseName = null;

  public CourseTableBasicInfo courseId(String courseId) {
    this.courseId = courseId;
    return this;
  }

  /**
   * 课程id
   * @return courseId
  **/
  @ApiModelProperty(value = "课程id")


  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }

  public CourseTableBasicInfo courseName(String courseName) {
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CourseTableBasicInfo courseTableBasicInfo = (CourseTableBasicInfo) o;
    return Objects.equals(this.courseId, courseTableBasicInfo.courseId) &&
        Objects.equals(this.courseName, courseTableBasicInfo.courseName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(courseId, courseName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CourseTableBasicInfo {\n");
    
    sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
    sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
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

