package com.lztech.site.viewmodel.coursemanagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * MaterialModule
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-01-13T06:00:37.467Z")




public class MaterialModule {
  @JsonProperty("moduleName")
  private String moduleName = null;

  @JsonProperty("moduleStates")
  private Integer moduleStates = null;

  @JsonProperty("moduleOrder")
  private Integer moduleOrder = null;

  public MaterialModule moduleName(String moduleName) {
    this.moduleName = moduleName;
    return this;
  }

  /**
   * 模块名称
   * @return moduleName
  **/
  @ApiModelProperty(value = "模块名称")


  public String getModuleName() {
    return moduleName;
  }

  public void setModuleName(String moduleName) {
    this.moduleName = moduleName;
  }

  public MaterialModule moduleStates(Integer moduleStates) {
    this.moduleStates = moduleStates;
    return this;
  }

  /**
   * 模块状态：0 未完成；1 完成
   * @return moduleStates
  **/
  @ApiModelProperty(value = "模块状态：0 未完成；1 完成")


  public Integer getModuleStates() {
    return moduleStates;
  }

  public void setModuleStates(Integer moduleStates) {
    this.moduleStates = moduleStates;
  }

  public MaterialModule moduleOrder(Integer moduleOrder) {
    this.moduleOrder = moduleOrder;
    return this;
  }

  /**
   * 顺序
   * @return moduleOrder
  **/
  @ApiModelProperty(value = "顺序")


  public Integer getModuleOrder() {
    return moduleOrder;
  }

  public void setModuleOrder(Integer moduleOrder) {
    this.moduleOrder = moduleOrder;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MaterialModule materialModule = (MaterialModule) o;
    return Objects.equals(this.moduleName, materialModule.moduleName) &&
        Objects.equals(this.moduleStates, materialModule.moduleStates) &&
        Objects.equals(this.moduleOrder, materialModule.moduleOrder);
  }

  @Override
  public int hashCode() {
    return Objects.hash(moduleName, moduleStates, moduleOrder);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MaterialModule {\n");
    
    sb.append("    moduleName: ").append(toIndentedString(moduleName)).append("\n");
    sb.append("    moduleStates: ").append(toIndentedString(moduleStates)).append("\n");
    sb.append("    moduleOrder: ").append(toIndentedString(moduleOrder)).append("\n");
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

