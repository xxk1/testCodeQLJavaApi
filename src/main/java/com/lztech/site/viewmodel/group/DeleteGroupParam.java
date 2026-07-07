package com.lztech.site.viewmodel.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * DeleteGroupParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-01-07T10:25:05.857Z")


public class DeleteGroupParam   {
  @JsonProperty("operatorId")
  private String operatorId = null;

  @JsonProperty("operatorName")
  private String operatorName = null;

  @JsonProperty("deleteGroupIds")
  @Valid
  private List<DeleteGroupIdVo> deleteGroupIds = null;

  public DeleteGroupParam operatorId(String operatorId) {
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

  public DeleteGroupParam operatorName(String operatorName) {
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

  public DeleteGroupParam deleteGroupIds(List<DeleteGroupIdVo> deleteGroupIds) {
    this.deleteGroupIds = deleteGroupIds;
    return this;
  }

  public DeleteGroupParam addDeleteGroupIdsItem(DeleteGroupIdVo deleteGroupIdsItem) {
    if (this.deleteGroupIds == null) {
      this.deleteGroupIds = new ArrayList<DeleteGroupIdVo>();
    }
    this.deleteGroupIds.add(deleteGroupIdsItem);
    return this;
  }

  /**
   * 删除班级id列表
   * @return deleteGroupIds
  **/
  @ApiModelProperty(value = "删除班级id列表")

  @Valid

  public List<DeleteGroupIdVo> getDeleteGroupIds() {
    return deleteGroupIds;
  }

  public void setDeleteGroupIds(List<DeleteGroupIdVo> deleteGroupIds) {
    this.deleteGroupIds = deleteGroupIds;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DeleteGroupParam deleteGroupParam = (DeleteGroupParam) o;
    return Objects.equals(this.operatorId, deleteGroupParam.operatorId) &&
        Objects.equals(this.operatorName, deleteGroupParam.operatorName) &&
        Objects.equals(this.deleteGroupIds, deleteGroupParam.deleteGroupIds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(operatorId, operatorName, deleteGroupIds);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DeleteGroupParam {\n");
    
    sb.append("    operatorId: ").append(toIndentedString(operatorId)).append("\n");
    sb.append("    operatorName: ").append(toIndentedString(operatorName)).append("\n");
    sb.append("    deleteGroupIds: ").append(toIndentedString(deleteGroupIds)).append("\n");
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

