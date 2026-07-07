package com.lztech.site.viewmodel.myuniversity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * MyUniversityHighestScoreCourseInfoInfoVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-09-02T08:51:14.631Z")


public class MyUniversityHighestScoreCourseInfoInfoVo   {
  @JsonProperty("courseCode")
  private String courseCode = null;

  @JsonProperty("courseName")
  private String courseName = null;

  @JsonProperty("score")
  private String score = null;

  public MyUniversityHighestScoreCourseInfoInfoVo courseCode(String courseCode) {
    this.courseCode = courseCode;
    return this;
  }

  /**
   * 课程编码
   * @return courseCode
  **/
  @ApiModelProperty(value = "课程编码")


  public String getCourseCode() {
    return courseCode;
  }

  public void setCourseCode(String courseCode) {
    this.courseCode = courseCode;
  }

  public MyUniversityHighestScoreCourseInfoInfoVo courseName(String courseName) {
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

  public MyUniversityHighestScoreCourseInfoInfoVo score(String score) {
    this.score = score;
    return this;
  }

  /**
   * 分数
   * @return score
  **/
  @ApiModelProperty(value = "分数")


  public String getScore() {
    return score;
  }

  public void setScore(String score) {
    this.score = score;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MyUniversityHighestScoreCourseInfoInfoVo myUniversityHighestScoreCourseInfoInfoVo = (MyUniversityHighestScoreCourseInfoInfoVo) o;
    return Objects.equals(this.courseCode, myUniversityHighestScoreCourseInfoInfoVo.courseCode) &&
        Objects.equals(this.courseName, myUniversityHighestScoreCourseInfoInfoVo.courseName) &&
        Objects.equals(this.score, myUniversityHighestScoreCourseInfoInfoVo.score);
  }

  @Override
  public int hashCode() {
    return Objects.hash(courseCode, courseName, score);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MyUniversityHighestScoreCourseInfoInfoVo {\n");
    
    sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
    sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
    sb.append("    score: ").append(toIndentedString(score)).append("\n");
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

