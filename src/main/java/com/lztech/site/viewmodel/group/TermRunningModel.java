package com.lztech.site.viewmodel.group;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * TermRunningModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-11-01T02:43:18.545Z")


public class TermRunningModel   {
  @JsonProperty("openClassNum")
  private String openClassNum = null;

  @JsonProperty("serviceTeacherNum")
  private String serviceTeacherNum = null;

  @JsonProperty("serviceStudentNum")
  private String serviceStudentNum = null;

  public TermRunningModel openClassNum(String openClassNum) {
    this.openClassNum = openClassNum;
    return this;
  }

  /**
   * 开班总数
   * @return openClassNum
  **/
  @ApiModelProperty(value = "开班总数")


  public String getOpenClassNum() {
    return openClassNum;
  }

  public void setOpenClassNum(String openClassNum) {
    this.openClassNum = openClassNum;
  }

  public TermRunningModel serviceTeacherNum(String serviceTeacherNum) {
    this.serviceTeacherNum = serviceTeacherNum;
    return this;
  }

  /**
   * 服务教师总数
   * @return serviceTeacherNum
  **/
  @ApiModelProperty(value = "服务教师总数")


  public String getServiceTeacherNum() {
    return serviceTeacherNum;
  }

  public void setServiceTeacherNum(String serviceTeacherNum) {
    this.serviceTeacherNum = serviceTeacherNum;
  }

  public TermRunningModel serviceStudentNum(String serviceStudentNum) {
    this.serviceStudentNum = serviceStudentNum;
    return this;
  }

  /**
   * 服务学生总数
   * @return serviceStudentNum
  **/
  @ApiModelProperty(value = "服务学生总数")


  public String getServiceStudentNum() {
    return serviceStudentNum;
  }

  public void setServicestudentNum(String serviceStudentNum) {
    this.serviceStudentNum = serviceStudentNum;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TermRunningModel termRunningModel = (TermRunningModel) o;
    return Objects.equals(this.openClassNum, termRunningModel.openClassNum) &&
        Objects.equals(this.serviceTeacherNum, termRunningModel.serviceTeacherNum) &&
        Objects.equals(this.serviceStudentNum, termRunningModel.serviceStudentNum);
  }

  @Override
  public int hashCode() {
    return Objects.hash(openClassNum, serviceTeacherNum, serviceStudentNum);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TermRunningModel {\n");

    sb.append("    openClassNum: ").append(toIndentedString(openClassNum)).append("\n");
    sb.append("    serviceTeacherNum: ").append(toIndentedString(serviceTeacherNum)).append("\n");
    sb.append("    serviceStudentNum: ").append(toIndentedString(serviceStudentNum)).append("\n");
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

