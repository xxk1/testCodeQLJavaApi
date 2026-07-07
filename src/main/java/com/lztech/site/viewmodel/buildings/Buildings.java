package com.lztech.site.viewmodel.buildings;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * Buildings
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-08T07:23:58.366Z")

public class Buildings   {
  @JsonProperty("buildId")
  private String buildId = null;

  @JsonProperty("buildName")
  private String buildName = null;

  public Buildings buildId(String buildId) {
    this.buildId = buildId;
    return this;
  }

  /**
   * 楼栋id
   * @return buildId
  **/
  @ApiModelProperty(value = "楼栋id")


  public String getBuildId() {
    return buildId;
  }

  public void setBuildId(String buildId) {
    this.buildId = buildId;
  }

  public Buildings buildName(String buildName) {
    this.buildName = buildName;
    return this;
  }

  /**
   * 楼栋名称
   * @return buildName
  **/
  @ApiModelProperty(value = "楼栋名称")


  public String getBuildName() {
    return buildName;
  }

  public void setBuildName(String buildName) {
    this.buildName = buildName;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Buildings buildings = (Buildings) o;
    return Objects.equals(this.buildId, buildings.buildId) &&
        Objects.equals(this.buildName, buildings.buildName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(buildId, buildName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Buildings {\n");
    
    sb.append("    buildId: ").append(toIndentedString(buildId)).append("\n");
    sb.append("    buildName: ").append(toIndentedString(buildName)).append("\n");
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

