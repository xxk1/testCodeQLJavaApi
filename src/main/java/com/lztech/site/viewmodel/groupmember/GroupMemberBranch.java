package com.lztech.site.viewmodel.groupmember;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * GroupMemberBranch
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-08-01T15:57:31.127+08:00")

public class GroupMemberBranch   {
  @JsonProperty("studentNum")
  private String studentNum = null;

  @JsonProperty("learningMethodName")
  private String learningMethodName = null;

  public GroupMemberBranch studentNum(String studentNum) {
    this.studentNum = studentNum;
    return this;
  }

  /**
   * 学生人数
   * @return studentNum
  **/
  @ApiModelProperty(value = "学生人数")


  public String getStudentNum() {
    return studentNum;
  }

  public void setStudentNum(String studentNum) {
    this.studentNum = studentNum;
  }

  public GroupMemberBranch learningMethodName(String learningMethodName) {
    this.learningMethodName = learningMethodName;
    return this;
  }

  /**
   * 上课方式名称
   * @return learningMethodName
  **/
  @ApiModelProperty(value = "上课方式名称")


  public String getLearningMethodName() {
    return learningMethodName;
  }

  public void setLearningMethodName(String learningMethodName) {
    this.learningMethodName = learningMethodName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GroupMemberBranch groupMemberBranch = (GroupMemberBranch) o;
    return Objects.equals(this.studentNum, groupMemberBranch.studentNum) &&
        Objects.equals(this.learningMethodName, groupMemberBranch.learningMethodName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(studentNum, learningMethodName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GroupMemberBranch {\n");
    
    sb.append("    studentNum: ").append(toIndentedString(studentNum)).append("\n");
    sb.append("    learningMethodName: ").append(toIndentedString(learningMethodName)).append("\n");
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

