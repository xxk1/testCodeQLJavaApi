package com.lztech.site.viewmodel.segment;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * Segments
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-16T02:30:54.876Z")




public class Segments   {
  @JsonProperty("segment")
  private Integer segment = null;

  @JsonProperty("occupyTypeIndex")
  private Integer occupyTypeIndex = null;

  public Segments segment(Integer segment) {
    this.segment = segment;
    return this;
  }

  /**
   * 节次
   * @return segment
  **/
  @ApiModelProperty(value = "节次")


  public Integer getSegment() {
    return segment;
  }

  public void setSegment(Integer segment) {
    this.segment = segment;
  }

  public Segments occupyTypeIndex(Integer occupyTypeIndex) {
    this.occupyTypeIndex = occupyTypeIndex;
    return this;
  }

  /**
   * 占用类型值
   * @return occupyTypeIndex
  **/
  @ApiModelProperty(value = "占用类型值")


  public Integer getOccupyTypeIndex() {
    return occupyTypeIndex;
  }

  public void setOccupyTypeIndex(Integer occupyTypeIndex) {
    this.occupyTypeIndex = occupyTypeIndex;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Segments segments = (Segments) o;
    return Objects.equals(this.segment, segments.segment) &&
        Objects.equals(this.occupyTypeIndex, segments.occupyTypeIndex);
  }

  @Override
  public int hashCode() {
    return Objects.hash(segment, occupyTypeIndex);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Segments {\n");
    
    sb.append("    segment: ").append(toIndentedString(segment)).append("\n");
    sb.append("    occupyTypeIndex: ").append(toIndentedString(occupyTypeIndex)).append("\n");
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

