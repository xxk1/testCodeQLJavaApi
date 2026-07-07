package com.lztech.site.viewmodel.portal;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * TourCoursesInfoVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-07-11T02:16:04.365Z")


public class TourCoursesInfoVo {
  @JsonProperty("detailId")
  private String detailId = null;

  @JsonProperty("teacherName")
  private String teacherName = null;

  @JsonProperty("collegeName")
  private String collegeName = null;

  public TourCoursesInfoVo detailId(String detailId) {
    this.detailId = detailId;
    return this;
  }

  /**
   * 课表详情id
   * @return detailId
  **/
  @ApiModelProperty(value = "课表详情id")


  public String getDetailId() {
    return detailId;
  }

  public void setDetailId(String detailId) {
    this.detailId = detailId;
  }

  public TourCoursesInfoVo teacherName(String teacherName) {
    this.teacherName = teacherName;
    return this;
  }

  /**
   * 授课教师名称
   * @return teacherName
  **/
  @ApiModelProperty(value = "授课教师名称")


  public String getTeacherName() {
    return teacherName;
  }

  public void setTeacherName(String teacherName) {
    this.teacherName = teacherName;
  }

  public TourCoursesInfoVo collegeName(String collegeName) {
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TourCoursesInfoVo tourCoursesInfoVo = (TourCoursesInfoVo) o;
    return Objects.equals(this.detailId, tourCoursesInfoVo.detailId) &&
        Objects.equals(this.teacherName, tourCoursesInfoVo.teacherName) &&
        Objects.equals(this.collegeName, tourCoursesInfoVo.collegeName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(detailId, teacherName, collegeName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TourCoursesInfoVo {\n");
    
    sb.append("    detailId: ").append(toIndentedString(detailId)).append("\n");
    sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
    sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
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

