package com.lztech.site.viewmodel.college;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CollegeIdsParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-09-06T08:36:43.003Z")


public class CollegeIdsParam   {
  @JsonProperty("courseInfoCollegeStatus")
  private Boolean courseInfoCollegeStatus = null;

  @JsonProperty("ids")
  private String ids = null;

  public CollegeIdsParam courseInfoCollegeStatus(Boolean courseInfoCollegeStatus) {
    this.courseInfoCollegeStatus = courseInfoCollegeStatus;
    return this;
  }

  /**
   * 是否开课学院（true：开课学院，false：所有学院）
   * @return courseInfoCollegeStatus
  **/
  @ApiModelProperty(value = "是否开课学院（true：开课学院，false：所有学院）")


  public Boolean isCourseInfoCollegeStatus() {
    return courseInfoCollegeStatus;
  }

  public void setCourseInfoCollegeStatus(Boolean courseInfoCollegeStatus) {
    this.courseInfoCollegeStatus = courseInfoCollegeStatus;
  }

  public CollegeIdsParam ids(String ids) {
    this.ids = ids;
    return this;
  }

  /**
   * 学院ids(逗号拼接)
   * @return ids
  **/
  @ApiModelProperty(value = "学院ids(逗号拼接)")


  public String getIds() {
    return ids;
  }

  public void setIds(String ids) {
    this.ids = ids;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CollegeIdsParam collegeIdsParam = (CollegeIdsParam) o;
    return Objects.equals(this.courseInfoCollegeStatus, collegeIdsParam.courseInfoCollegeStatus) &&
        Objects.equals(this.ids, collegeIdsParam.ids);
  }

  @Override
  public int hashCode() {
    return Objects.hash(courseInfoCollegeStatus, ids);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CollegeIdsParam {\n");
    
    sb.append("    courseInfoCollegeStatus: ").append(toIndentedString(courseInfoCollegeStatus)).append("\n");
    sb.append("    ids: ").append(toIndentedString(ids)).append("\n");
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

