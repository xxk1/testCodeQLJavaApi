package com.lztech.site.viewmodel.coursetabledetail;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * ExpertAuthorizationChildListThree
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-12-28T08:15:33.133Z")


public class ExpertAuthorizationChildListThree   {
  @JsonProperty("segments")
  private String segments = null;

  @JsonProperty("nodeId")
  private String nodeId = null;

  @JsonProperty("nodeLevel")
  private Integer nodeLevel = null;

  @JsonProperty("parentId")
  private String parentId = null;

  public ExpertAuthorizationChildListThree segments(String segments) {
    this.segments = segments;
    return this;
  }

  /**
   * 节次
   * @return segments
  **/
  @ApiModelProperty(value = "节次")


  public String getSegments() {
    return segments;
  }

  public void setSegments(String segments) {
    this.segments = segments;
  }

  public ExpertAuthorizationChildListThree nodeId(String nodeId) {
    this.nodeId = nodeId;
    return this;
  }

  /**
   * 节点ID
   * @return nodeId
  **/
  @ApiModelProperty(value = "节点ID")


  public String getNodeId() {
    return nodeId;
  }

  public void setNodeId(String nodeId) {
    this.nodeId = nodeId;
  }

  public ExpertAuthorizationChildListThree nodeLevel(Integer nodeLevel) {
    this.nodeLevel = nodeLevel;
    return this;
  }

  /**
   * 节点层级
   * @return nodeLevel
  **/
  @ApiModelProperty(value = "节点层级")


  public Integer getNodeLevel() {
    return nodeLevel;
  }

  public void setNodeLevel(Integer nodeLevel) {
    this.nodeLevel = nodeLevel;
  }

  public ExpertAuthorizationChildListThree parentId(String parentId) {
    this.parentId = parentId;
    return this;
  }

  /**
   * 节点父级ID
   * @return parentId
  **/
  @ApiModelProperty(value = "节点父级ID")


  public String getParentId() {
    return parentId;
  }

  public void setParentId(String parentId) {
    this.parentId = parentId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExpertAuthorizationChildListThree expertAuthorizationChildListThree = (ExpertAuthorizationChildListThree) o;
    return Objects.equals(this.segments, expertAuthorizationChildListThree.segments) &&
        Objects.equals(this.nodeId, expertAuthorizationChildListThree.nodeId) &&
        Objects.equals(this.nodeLevel, expertAuthorizationChildListThree.nodeLevel) &&
        Objects.equals(this.parentId, expertAuthorizationChildListThree.parentId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(segments, nodeId, nodeLevel, parentId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExpertAuthorizationChildListThree {\n");
    
    sb.append("    segments: ").append(toIndentedString(segments)).append("\n");
    sb.append("    nodeId: ").append(toIndentedString(nodeId)).append("\n");
    sb.append("    nodeLevel: ").append(toIndentedString(nodeLevel)).append("\n");
    sb.append("    parentId: ").append(toIndentedString(parentId)).append("\n");
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

