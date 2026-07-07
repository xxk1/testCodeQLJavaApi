package com.lztech.site.viewmodel.superviseevaluation.v2;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * SupervisionCourseTableDetailVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-07-05T03:44:28.658Z")


public class SupervisionCourseTableDetailVo   {
  @JsonProperty("courseTableDetailId")
  private String courseTableDetailId = null;

  public SupervisionCourseTableDetailVo courseTableDetailId(String courseTableDetailId) {
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SupervisionCourseTableDetailVo supervisionCourseTableDetailVo = (SupervisionCourseTableDetailVo) o;
    return Objects.equals(this.courseTableDetailId, supervisionCourseTableDetailVo.courseTableDetailId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(courseTableDetailId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SupervisionCourseTableDetailVo {\n");
    
    sb.append("    courseTableDetailId: ").append(toIndentedString(courseTableDetailId)).append("\n");
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

