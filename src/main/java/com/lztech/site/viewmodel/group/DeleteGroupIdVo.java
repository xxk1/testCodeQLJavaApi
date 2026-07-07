package com.lztech.site.viewmodel.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * DeleteGroupIdVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-01-07T10:25:05.857Z")


public class DeleteGroupIdVo   {
  @JsonProperty("groupId")
  private String groupId = null;

  public DeleteGroupIdVo groupId(String groupId) {
    this.groupId = groupId;
    return this;
  }

  /**
   * 班级id
   * @return groupId
  **/
  @ApiModelProperty(value = "班级id")


  public String getGroupId() {
    return groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DeleteGroupIdVo deleteGroupIdVo = (DeleteGroupIdVo) o;
    return Objects.equals(this.groupId, deleteGroupIdVo.groupId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(groupId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DeleteGroupIdVo {\n");
    
    sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
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

