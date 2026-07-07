package com.lztech.site.viewmodel.group;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * GroupMemberByStudentNoOrNameVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-08-30T06:24:31.345Z")


public class GroupMemberByStudentNoOrNameVo   {
  @JsonProperty("studentNoOrName")
  private String studentNoOrName = null;

  @JsonProperty("groupIdList")
  @Valid
  private List<String> groupIdList = null;

  public GroupMemberByStudentNoOrNameVo studentNoOrName(String studentNoOrName) {
    this.studentNoOrName = studentNoOrName;
    return this;
  }

  /**
   * 根据学生编号或名称模糊查询字段
   * @return studentNoOrName
  **/
  @ApiModelProperty(value = "根据学生编号或名称模糊查询字段")


  public String getStudentNoOrName() {
    return studentNoOrName;
  }

  public void setStudentNoOrName(String studentNoOrName) {
    this.studentNoOrName = studentNoOrName;
  }

  public GroupMemberByStudentNoOrNameVo groupIdList(List<String> groupIdList) {
    this.groupIdList = groupIdList;
    return this;
  }

  public GroupMemberByStudentNoOrNameVo addGroupIdListItem(String groupIdListItem) {
    if (this.groupIdList == null) {
      this.groupIdList = new ArrayList<String>();
    }
    this.groupIdList.add(groupIdListItem);
    return this;
  }

  /**
   * 查询班级Id集合
   * @return groupIdList
  **/
  @ApiModelProperty(value = "查询班级Id集合")


  public List<String> getGroupIdList() {
    return groupIdList;
  }

  public void setGroupIdList(List<String> groupIdList) {
    this.groupIdList = groupIdList;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GroupMemberByStudentNoOrNameVo groupMemberByStudentNoOrNameVo = (GroupMemberByStudentNoOrNameVo) o;
    return Objects.equals(this.studentNoOrName, groupMemberByStudentNoOrNameVo.studentNoOrName) &&
        Objects.equals(this.groupIdList, groupMemberByStudentNoOrNameVo.groupIdList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(studentNoOrName, groupIdList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GroupMemberByStudentNoOrNameVo {\n");
    
    sb.append("    studentNoOrName: ").append(toIndentedString(studentNoOrName)).append("\n");
    sb.append("    groupIdList: ").append(toIndentedString(groupIdList)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

