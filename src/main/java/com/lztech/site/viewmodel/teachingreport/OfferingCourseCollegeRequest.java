package com.lztech.site.viewmodel.teachingreport;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * OfferingCourseCollegeRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-11-29T09:41:22.351Z")


public class OfferingCourseCollegeRequest   {
  @JsonProperty("collegeId")
  private String collegeId = null;

  public OfferingCourseCollegeRequest collegeId(String collegeId) {
    this.collegeId = collegeId;
    return this;
  }

  /**
   * 学院id
   * @return collegeId
  **/
  @ApiModelProperty(value = "学院id")


  public String getCollegeId() {
    return collegeId;
  }

  public void setCollegeId(String collegeId) {
    this.collegeId = collegeId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OfferingCourseCollegeRequest offeringCourseCollegeRequest = (OfferingCourseCollegeRequest) o;
    return Objects.equals(this.collegeId, offeringCourseCollegeRequest.collegeId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(collegeId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OfferingCourseCollegeRequest {\n");
    
    sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
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

