package com.lztech.site.viewmodel.segment;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * SegmentQueryParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-16T02:30:54.876Z")




public class SegmentQueryParam   {
  @JsonProperty("buildingId")
  private String buildingId = null;

  @JsonProperty("classRoomId")
  private String classRoomId = null;

  @JsonProperty("segmentList")
  @Valid
  private List<Segments> segmentList = null;

  @JsonProperty("searchDate")
  private String searchDate = null;

  @JsonProperty("validecode")
  private String validecode = null;

  public SegmentQueryParam buildingId(String buildingId) {
    this.buildingId = buildingId;
    return this;
  }

  /**
   * 楼栋id
   * @return buildingId
  **/
  @ApiModelProperty(value = "楼栋id")


  public String getBuildingId() {
    return buildingId;
  }

  public void setBuildingId(String buildingId) {
    this.buildingId = buildingId;
  }

  public SegmentQueryParam classRoomId(String classRoomId) {
    this.classRoomId = classRoomId;
    return this;
  }

  /**
   * 教室id
   * @return classRoomId
  **/
  @ApiModelProperty(value = "教室id")


  public String getClassRoomId() {
    return classRoomId;
  }

  public void setClassRoomId(String classRoomId) {
    this.classRoomId = classRoomId;
  }

  public SegmentQueryParam segmentList(List<Segments> segmentList) {
    this.segmentList = segmentList;
    return this;
  }

  public SegmentQueryParam addSegmentListItem(Segments segmentListItem) {
    if (this.segmentList == null) {
      this.segmentList = new ArrayList<Segments>();
    }
    this.segmentList.add(segmentListItem);
    return this;
  }

  /**
   * Get segmentList
   * @return segmentList
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Segments> getSegmentList() {
    return segmentList;
  }

  public void setSegmentList(List<Segments> segmentList) {
    this.segmentList = segmentList;
  }

  public SegmentQueryParam searchDate(String searchDate) {
    this.searchDate = searchDate;
    return this;
  }

  /**
   * 查询日期
   * @return searchDate
  **/
  @ApiModelProperty(value = "查询日期")


  public String getSearchDate() {
    return searchDate;
  }

  public void setSearchDate(String searchDate) {
    this.searchDate = searchDate;
  }

  public SegmentQueryParam validecode(String validecode) {
    this.validecode = validecode;
    return this;
  }

  /**
   * 验证码md5加密
   * @return validecode
  **/
  @ApiModelProperty(value = "验证码md5加密")


  public String getValidecode() {
    return validecode;
  }

  public void setValidecode(String validecode) {
    this.validecode = validecode;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SegmentQueryParam segmentQueryParam = (SegmentQueryParam) o;
    return Objects.equals(this.buildingId, segmentQueryParam.buildingId) &&
        Objects.equals(this.classRoomId, segmentQueryParam.classRoomId) &&
        Objects.equals(this.segmentList, segmentQueryParam.segmentList) &&
        Objects.equals(this.searchDate, segmentQueryParam.searchDate) &&
        Objects.equals(this.validecode, segmentQueryParam.validecode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(buildingId, classRoomId, segmentList, searchDate, validecode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SegmentQueryParam {\n");
    
    sb.append("    buildingId: ").append(toIndentedString(buildingId)).append("\n");
    sb.append("    classRoomId: ").append(toIndentedString(classRoomId)).append("\n");
    sb.append("    segmentList: ").append(toIndentedString(segmentList)).append("\n");
    sb.append("    searchDate: ").append(toIndentedString(searchDate)).append("\n");
    sb.append("    validecode: ").append(toIndentedString(validecode)).append("\n");
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

