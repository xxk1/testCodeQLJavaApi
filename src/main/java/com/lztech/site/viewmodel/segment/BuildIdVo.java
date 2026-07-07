package com.lztech.site.viewmodel.segment;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * BuildIdVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-09-21T06:00:06.486Z")


public class BuildIdVo   {
  @JsonProperty("buildId")
  private String buildId = null;

  public BuildIdVo buildId(String buildId) {
    this.buildId = buildId;
    return this;
  }

  /**
   * 楼栋Id
   * @return buildId
  **/
  @ApiModelProperty(value = "楼栋Id")


  public String getBuildId() {
    return buildId;
  }

  public void setBuildId(String buildId) {
    this.buildId = buildId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BuildIdVo buildIdVo = (BuildIdVo) o;
    return Objects.equals(this.buildId, buildIdVo.buildId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(buildId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BuildIdVo {\n");
    
    sb.append("    buildId: ").append(toIndentedString(buildId)).append("\n");
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

