package com.lztech.site.viewmodel.group;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * GroupResourceVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-21T02:55:42.642Z")

public class GroupResourceVo   {
  @JsonProperty("groupId")
  private String groupId = null;

  @JsonProperty("groupNo")
  private String groupNo = null;

  @JsonProperty("groupName")
  private String groupName = null;

  @JsonProperty("createTime")
  private String createTime = null;

  @JsonProperty("createName")
  private String createName = null;

  @JsonProperty("modifierId")
  private String modifierId = null;

  @JsonProperty("modifierName")
  private String modifierName = null;

  @JsonProperty("modifierTime")
  private String modifierTime = null;

  @JsonProperty("source")
  private String source = null;

  public GroupResourceVo groupId(String groupId) {
    this.groupId = groupId;
    return this;
  }

  /**
   * 主键ID
   * @return groupId
  **/
  @ApiModelProperty(value = "主键ID")


  public String getGroupId() {
    return groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }

  public GroupResourceVo groupNo(String groupNo) {
    this.groupNo = groupNo;
    return this;
  }

  /**
   * 分组编号
   * @return groupNo
  **/
  @ApiModelProperty(value = "分组编号")


  public String getGroupNo() {
    return groupNo;
  }

  public void setGroupNo(String groupNo) {
    this.groupNo = groupNo;
  }

  public GroupResourceVo groupName(String groupName) {
    this.groupName = groupName;
    return this;
  }

  /**
   * 组名称
   * @return groupName
  **/
  @ApiModelProperty(value = "组名称")


  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public GroupResourceVo createTime(String createTime) {
    this.createTime = createTime;
    return this;
  }

  /**
   * 创建时间
   * @return createTime
  **/
  @ApiModelProperty(value = "创建时间")


  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public GroupResourceVo createName(String createName) {
    this.createName = createName;
    return this;
  }

  /**
   * 创建人
   * @return createName
  **/
  @ApiModelProperty(value = "创建人")


  public String getCreateName() {
    return createName;
  }

  public void setCreateName(String createName) {
    this.createName = createName;
  }

  public GroupResourceVo modifierId(String modifierId) {
    this.modifierId = modifierId;
    return this;
  }

  /**
   * 修改人ID
   * @return modifierId
  **/
  @ApiModelProperty(value = "修改人ID")


  public String getModifierId() {
    return modifierId;
  }

  public void setModifierId(String modifierId) {
    this.modifierId = modifierId;
  }

  public GroupResourceVo modifierName(String modifierName) {
    this.modifierName = modifierName;
    return this;
  }

  /**
   * 修改人姓名
   * @return modifierName
  **/
  @ApiModelProperty(value = "修改人姓名")


  public String getModifierName() {
    return modifierName;
  }

  public void setModifierName(String modifierName) {
    this.modifierName = modifierName;
  }

  public GroupResourceVo modifierTime(String modifierTime) {
    this.modifierTime = modifierTime;
    return this;
  }

  /**
   * 修改时间
   * @return modifierTime
  **/
  @ApiModelProperty(value = "修改时间")


  public String getModifierTime() {
    return modifierTime;
  }

  public void setModifierTime(String modifierTime) {
    this.modifierTime = modifierTime;
  }

  public GroupResourceVo source(String source) {
    this.source = source;
    return this;
  }

  /**
   * 数据来源
   * @return source
  **/
  @ApiModelProperty(value = "数据来源")


  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GroupResourceVo groupResourceVo = (GroupResourceVo) o;
    return Objects.equals(this.groupId, groupResourceVo.groupId) &&
        Objects.equals(this.groupNo, groupResourceVo.groupNo) &&
        Objects.equals(this.groupName, groupResourceVo.groupName) &&
        Objects.equals(this.createTime, groupResourceVo.createTime) &&
        Objects.equals(this.createName, groupResourceVo.createName) &&
        Objects.equals(this.modifierId, groupResourceVo.modifierId) &&
        Objects.equals(this.modifierName, groupResourceVo.modifierName) &&
        Objects.equals(this.modifierTime, groupResourceVo.modifierTime) &&
        Objects.equals(this.source, groupResourceVo.source);
  }

  @Override
  public int hashCode() {
    return Objects.hash(groupId, groupNo, groupName, createTime, createName, modifierId, modifierName, modifierTime, source);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GroupResourceVo {\n");
    
    sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
    sb.append("    groupNo: ").append(toIndentedString(groupNo)).append("\n");
    sb.append("    groupName: ").append(toIndentedString(groupName)).append("\n");
    sb.append("    createTime: ").append(toIndentedString(createTime)).append("\n");
    sb.append("    createName: ").append(toIndentedString(createName)).append("\n");
    sb.append("    modifierId: ").append(toIndentedString(modifierId)).append("\n");
    sb.append("    modifierName: ").append(toIndentedString(modifierName)).append("\n");
    sb.append("    modifierTime: ").append(toIndentedString(modifierTime)).append("\n");
    sb.append("    source: ").append(toIndentedString(source)).append("\n");
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

