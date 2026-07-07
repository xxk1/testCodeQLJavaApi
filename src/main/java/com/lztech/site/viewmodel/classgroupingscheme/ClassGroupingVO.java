package com.lztech.site.viewmodel.classgroupingscheme;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * ClassGroupingVO
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-03-16T07:21:49.467Z")


public class ClassGroupingVO   {
  @JsonProperty("classGroupId")
  private String classGroupId = null;

  @JsonProperty("classGroupName")
  private String classGroupName = null;

  public ClassGroupingVO classGroupId(String classGroupId) {
    this.classGroupId = classGroupId;
    return this;
  }

  /**
   * 小组Id
   * @return classGroupId
  **/
  @ApiModelProperty(value = "小组Id")


  public String getClassGroupId() {
    return classGroupId;
  }

  public void setClassGroupId(String classGroupId) {
    this.classGroupId = classGroupId;
  }

  public ClassGroupingVO classGroupName(String classGroupName) {
    this.classGroupName = classGroupName;
    return this;
  }

  /**
   * 小组名称
   * @return classGroupName
  **/
  @ApiModelProperty(value = "小组名称")


  public String getClassGroupName() {
    return classGroupName;
  }

  public void setClassGroupName(String classGroupName) {
    this.classGroupName = classGroupName;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ClassGroupingVO classGroupingVO = (ClassGroupingVO) o;
    return Objects.equals(this.classGroupId, classGroupingVO.classGroupId) &&
        Objects.equals(this.classGroupName, classGroupingVO.classGroupName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(classGroupId, classGroupName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ClassGroupingVO {\n");
    
    sb.append("    classGroupId: ").append(toIndentedString(classGroupId)).append("\n");
    sb.append("    classGroupName: ").append(toIndentedString(classGroupName)).append("\n");
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

