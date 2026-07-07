package com.lztech.site.viewmodel.teachingpackage;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * PackageKnowledgePointResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-03-25T06:01:53.748Z")


public class PackageKnowledgePointResource   {
  @JsonProperty("pointId")
  private String pointId = null;

  @JsonProperty("pointName")
  private String pointName = null;

  public PackageKnowledgePointResource pointId(String pointId) {
    this.pointId = pointId;
    return this;
  }

  /**
   * 知识点id
   * @return pointId
  **/
  @ApiModelProperty(value = "知识点id")


  public String getPointId() {
    return pointId;
  }

  public void setPointId(String pointId) {
    this.pointId = pointId;
  }

  public PackageKnowledgePointResource pointName(String pointName) {
    this.pointName = pointName;
    return this;
  }

  /**
   * 知识点名称
   * @return pointName
  **/
  @ApiModelProperty(value = "知识点名称")


  public String getPointName() {
    return pointName;
  }

  public void setPointName(String pointName) {
    this.pointName = pointName;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PackageKnowledgePointResource packageKnowledgePointResource = (PackageKnowledgePointResource) o;
    return Objects.equals(this.pointId, packageKnowledgePointResource.pointId) &&
        Objects.equals(this.pointName, packageKnowledgePointResource.pointName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pointId, pointName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PackageKnowledgePointResource {\n");
    
    sb.append("    pointId: ").append(toIndentedString(pointId)).append("\n");
    sb.append("    pointName: ").append(toIndentedString(pointName)).append("\n");
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

