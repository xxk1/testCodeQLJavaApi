package com.lztech.site.viewmodel.coursemanagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * IsJoinTeachingTeam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-01-25T12:26:25.142Z")




public class IsJoinTeachingTeam   {
  @JsonProperty("status")
  private Boolean status = null;

  public IsJoinTeachingTeam status(Boolean status) {
    this.status = status;
    return this;
  }

  /**
   * true-加入 false-未加入
   * @return status
  **/
  @ApiModelProperty(value = "true-加入 false-未加入")


  public Boolean isStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IsJoinTeachingTeam isJoinTeachingTeam = (IsJoinTeachingTeam) o;
    return Objects.equals(this.status, isJoinTeachingTeam.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IsJoinTeachingTeam {\n");
    
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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

