package com.lztech.site.viewmodel.groupmember;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * DeleteGroupMemberIdVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-01-07T02:52:22.230Z")


public class DeleteGroupMemberIdVo   {
  @JsonProperty("groupMemberId")
  private String groupMemberId = null;

  public DeleteGroupMemberIdVo groupMemberId(String groupMemberId) {
    this.groupMemberId = groupMemberId;
    return this;
  }

  /**
   * 班级人员id
   * @return groupMemberId
  **/
  @ApiModelProperty(value = "班级人员id")


  public String getGroupMemberId() {
    return groupMemberId;
  }

  public void setGroupMemberId(String groupMemberId) {
    this.groupMemberId = groupMemberId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DeleteGroupMemberIdVo deleteGroupMemberIdVo = (DeleteGroupMemberIdVo) o;
    return Objects.equals(this.groupMemberId, deleteGroupMemberIdVo.groupMemberId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(groupMemberId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DeleteGroupMemberIdVo {\n");
    
    sb.append("    groupMemberId: ").append(toIndentedString(groupMemberId)).append("\n");
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

