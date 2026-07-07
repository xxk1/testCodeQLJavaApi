package com.lztech.site.resource.coursetable;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lztech.site.resource.term.TermResource;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * CourseTableRunningTerm
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-04-12T01:33:22.676Z")


public class CourseTableRunningTerm   {
  @JsonProperty("nowTerm")
  private Object nowTerm = null;

  @JsonProperty("runningTerm")
  @Valid
  private List<TermResource> runningTerm = null;

  public CourseTableRunningTerm nowTerm(Object nowTerm) {
    this.nowTerm = nowTerm;
    return this;
  }

  /**
   * Get nowTerm
   * @return nowTerm
  **/
  @ApiModelProperty(value = "")


  public Object getNowTerm() {
    return nowTerm;
  }

  public void setNowTerm(Object nowTerm) {
    this.nowTerm = nowTerm;
  }

  public CourseTableRunningTerm runningTerm(List<TermResource> runningTerm) {
    this.runningTerm = runningTerm;
    return this;
  }

  public CourseTableRunningTerm addRunningTermItem(TermResource runningTermItem) {
    if (this.runningTerm == null) {
      this.runningTerm = new ArrayList<TermResource>();
    }
    this.runningTerm.add(runningTermItem);
    return this;
  }

  /**
   * Get runningTerm
   * @return runningTerm
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<TermResource> getRunningTerm() {
    return runningTerm;
  }

  public void setRunningTerm(List<TermResource> runningTerm) {
    this.runningTerm = runningTerm;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CourseTableRunningTerm courseTableRunningTerm = (CourseTableRunningTerm) o;
    return Objects.equals(this.nowTerm, courseTableRunningTerm.nowTerm) &&
        Objects.equals(this.runningTerm, courseTableRunningTerm.runningTerm);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nowTerm, runningTerm);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CourseTableRunningTerm {\n");
    
    sb.append("    nowTerm: ").append(toIndentedString(nowTerm)).append("\n");
    sb.append("    runningTerm: ").append(toIndentedString(runningTerm)).append("\n");
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

