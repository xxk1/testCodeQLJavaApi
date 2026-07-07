package com.lztech.site.viewmodel.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 批量导入班级学生信息自定义异常返回值
 */
@ApiModel(description = "批量导入班级学生信息自定义异常返回值")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-01-10T02:40:56.379Z")


public class ImportStudentsVo {
  @JsonProperty("successRows")
  private Integer successRows = null;

  @JsonProperty("errorRows")
  private Integer errorRows = null;

  @JsonProperty("errorInfo")
  @Valid
  private List<String> errorInfo = null;

  public ImportStudentsVo successRows(Integer successRows) {
    this.successRows = successRows;
    return this;
  }

  /**
   * 成功条数
   *
   * @return successRows
   **/
  @ApiModelProperty(value = "成功条数")


  public Integer getSuccessRows() {
    return successRows;
  }

  public void setSuccessRows(Integer successRows) {
    this.successRows = successRows;
  }

  public ImportStudentsVo errorRows(Integer errorRows) {
    this.errorRows = errorRows;
    return this;
  }

  /**
   * 异常条数
   *
   * @return errorRows
   **/
  @ApiModelProperty(value = "异常条数")


  public Integer getErrorRows() {
    return errorRows;
  }

  public void setErrorRows(Integer errorRows) {
    this.errorRows = errorRows;
  }

  public ImportStudentsVo errorInfo(List<String> errorInfo) {
    this.errorInfo = errorInfo;
    return this;
  }

  public ImportStudentsVo addErrorInfoItem(String errorInfoItem) {
    if (this.errorInfo == null) {
      this.errorInfo = new ArrayList<String>();
    }
    this.errorInfo.add(errorInfoItem);
    return this;
  }

  /**
   * 异常信息详情
   *
   * @return errorInfo
   **/
  @ApiModelProperty(value = "异常信息详情")


  public List<String> getErrorInfo() {
    return errorInfo;
  }

  public void setErrorInfo(List<String> errorInfo) {
    this.errorInfo = errorInfo;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ImportStudentsVo importStudentsVo = (ImportStudentsVo) o;
    return Objects.equals(this.successRows, importStudentsVo.successRows) &&
            Objects.equals(this.errorRows, importStudentsVo.errorRows) &&
            Objects.equals(this.errorInfo, importStudentsVo.errorInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(successRows, errorRows, errorInfo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ImportStudentsVo {\n");

    sb.append("    successRows: ").append(toIndentedString(successRows)).append("\n");
    sb.append("    errorRows: ").append(toIndentedString(errorRows)).append("\n");
    sb.append("    errorInfo: ").append(toIndentedString(errorInfo)).append("\n");
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

