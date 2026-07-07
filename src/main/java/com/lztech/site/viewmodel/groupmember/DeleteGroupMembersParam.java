package com.lztech.site.viewmodel.groupmember;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * DeleteGroupMembersParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-01-07T02:52:22.230Z")


public class DeleteGroupMembersParam   {
  @JsonProperty("operatorId")
  private String operatorId = null;

  @JsonProperty("operatorName")
  private String operatorName = null;

  @JsonProperty("deleteGroupMembers")
  @Valid
  private List<DeleteGroupMemberIdVo> deleteGroupMembers = null;

  public DeleteGroupMembersParam operatorId(String operatorId) {
    this.operatorId = operatorId;
    return this;
  }

  /**
   * 操作人员id
   * @return operatorId
  **/
  @ApiModelProperty(value = "操作人员id")


  public String getOperatorId() {
    return operatorId;
  }

  public void setOperatorId(String operatorId) {
    this.operatorId = operatorId;
  }

  public DeleteGroupMembersParam operatorName(String operatorName) {
    this.operatorName = operatorName;
    return this;
  }

  /**
   * 操作人员姓名
   * @return operatorName
  **/
  @ApiModelProperty(value = "操作人员姓名")


  public String getOperatorName() {
    return operatorName;
  }

  public void setOperatorName(String operatorName) {
    this.operatorName = operatorName;
  }

  public DeleteGroupMembersParam deleteGroupMembers(List<DeleteGroupMemberIdVo> deleteGroupMembers) {
    this.deleteGroupMembers = deleteGroupMembers;
    return this;
  }

  public DeleteGroupMembersParam addDeleteGroupMembersItem(DeleteGroupMemberIdVo deleteGroupMembersItem) {
    if (this.deleteGroupMembers == null) {
      this.deleteGroupMembers = new ArrayList<DeleteGroupMemberIdVo>();
    }
    this.deleteGroupMembers.add(deleteGroupMembersItem);
    return this;
  }

  /**
   * 删除班级人员id列表
   * @return deleteGroupMembers
  **/
  @ApiModelProperty(value = "删除班级人员id列表")

  @Valid

  public List<DeleteGroupMemberIdVo> getDeleteGroupMembers() {
    return deleteGroupMembers;
  }

  public void setDeleteGroupMembers(List<DeleteGroupMemberIdVo> deleteGroupMembers) {
    this.deleteGroupMembers = deleteGroupMembers;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DeleteGroupMembersParam deleteGroupMembersParam = (DeleteGroupMembersParam) o;
    return Objects.equals(this.operatorId, deleteGroupMembersParam.operatorId) &&
        Objects.equals(this.operatorName, deleteGroupMembersParam.operatorName) &&
        Objects.equals(this.deleteGroupMembers, deleteGroupMembersParam.deleteGroupMembers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(operatorId, operatorName, deleteGroupMembers);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DeleteGroupMembersParam {\n");
    
    sb.append("    operatorId: ").append(toIndentedString(operatorId)).append("\n");
    sb.append("    operatorName: ").append(toIndentedString(operatorName)).append("\n");
    sb.append("    deleteGroupMembers: ").append(toIndentedString(deleteGroupMembers)).append("\n");
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

