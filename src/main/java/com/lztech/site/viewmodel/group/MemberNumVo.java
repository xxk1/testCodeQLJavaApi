package com.lztech.site.viewmodel.group;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * MemberNumVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-04-13T02:17:40.947Z")


public class MemberNumVo   {
  @JsonProperty("memberNum")
  private Integer memberNum = null;

  public MemberNumVo memberNum(Integer memberNum) {
    this.memberNum = memberNum;
    return this;
  }

  /**
   * 去重后学生人数
   * @return memberNum
  **/
  @ApiModelProperty(value = "去重后学生人数")


  public Integer getMemberNum() {
    return memberNum;
  }

  public void setMemberNum(Integer memberNum) {
    this.memberNum = memberNum;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MemberNumVo memberNumVo = (MemberNumVo) o;
    return Objects.equals(this.memberNum, memberNumVo.memberNum);
  }

  @Override
  public int hashCode() {
    return Objects.hash(memberNum);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MemberNumVo {\n");

    sb.append("    memberNum: ").append(toIndentedString(memberNum)).append("\n");
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

