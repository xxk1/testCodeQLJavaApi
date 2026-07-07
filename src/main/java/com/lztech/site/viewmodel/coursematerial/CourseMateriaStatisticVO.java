package com.lztech.site.viewmodel.coursematerial;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseMateriaStatisticVO
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-02-16T12:06:19.110Z")


public class CourseMateriaStatisticVO   {
  @JsonProperty("value")
  private String value = null;

  @JsonProperty("name")
  private String name = null;

  public CourseMateriaStatisticVO value(String value) {
    this.value = value;
    return this;
  }

  /**
   * 素材数量
   * @return value
  **/
  @ApiModelProperty(value = "素材数量")


  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public CourseMateriaStatisticVO name(String name) {
    this.name = name;
    return this;
  }

  /**
   * 素材类型
   * @return name
  **/
  @ApiModelProperty(value = "素材类型")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CourseMateriaStatisticVO courseMateriaStatisticVO = (CourseMateriaStatisticVO) o;
    return Objects.equals(this.value, courseMateriaStatisticVO.value) &&
        Objects.equals(this.name, courseMateriaStatisticVO.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CourseMateriaStatisticVO {\n");
    
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

