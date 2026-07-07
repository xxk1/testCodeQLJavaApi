package com.lztech.site.viewmodel.term;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * WeeksResources
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-17T06:59:50.178Z")

public class WeeksResources   {
  @JsonProperty("nameList")
  @Valid
  private List<List<String>> nameList = null;

  @JsonProperty("indexList")
  @Valid
  private List<List<Integer>> indexList = null;

  public WeeksResources nameList(List<List<String>> nameList) {
    this.nameList = nameList;
    return this;
  }

  public WeeksResources addNameListItem(List<String> nameListItem) {
    if (this.nameList == null) {
      this.nameList = new ArrayList<List<String>>();
    }
    this.nameList.add(nameListItem);
    return this;
  }

  /**
   * 节次名称集合
   * @return nameList
  **/
  @ApiModelProperty(value = "节次名称集合")

  @Valid

  public List<List<String>> getNameList() {
    return nameList;
  }

  public void setNameList(List<List<String>> nameList) {
    this.nameList = nameList;
  }

  public WeeksResources indexList(List<List<Integer>> indexList) {
    this.indexList = indexList;
    return this;
  }

  public WeeksResources addIndexListItem(List<Integer> indexListItem) {
    if (this.indexList == null) {
      this.indexList = new ArrayList<List<Integer>>();
    }
    this.indexList.add(indexListItem);
    return this;
  }

  /**
   * 总节次数量集合
   * @return indexList
  **/
  @ApiModelProperty(value = "总节次数量集合")

  @Valid

  public List<List<Integer>> getIndexList() {
    return indexList;
  }

  public void setIndexList(List<List<Integer>> indexList) {
    this.indexList = indexList;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WeeksResources weeksResources = (WeeksResources) o;
    return Objects.equals(this.nameList, weeksResources.nameList) &&
        Objects.equals(this.indexList, weeksResources.indexList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nameList, indexList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WeeksResources {\n");
    
    sb.append("    nameList: ").append(toIndentedString(nameList)).append("\n");
    sb.append("    indexList: ").append(toIndentedString(indexList)).append("\n");
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

