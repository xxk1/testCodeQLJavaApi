package com.lztech.site.viewmodel.group;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * GroupVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-21T02:55:42.642Z")

public class GroupVo   {
  @JsonProperty("pageCount")
  private Integer pageCount = null;

  @JsonProperty("total")
  private Integer total = null;

  @JsonProperty("page")
  private Integer page = null;

  @JsonProperty("pageSize")
  private Integer pageSize = null;

  @JsonProperty("groupResourcesList")
  @Valid
  private List<GroupResourceVo> groupResourcesList = null;

  public GroupVo pageCount(Integer pageCount) {
    this.pageCount = pageCount;
    return this;
  }

  /**
   * 总页数
   * @return pageCount
  **/
  @ApiModelProperty(value = "总页数")


  public Integer getPageCount() {
    return pageCount;
  }

  public void setPageCount(Integer pageCount) {
    this.pageCount = pageCount;
  }

  public GroupVo total(Integer total) {
    this.total = total;
    return this;
  }

  /**
   * 总条数
   * @return total
  **/
  @ApiModelProperty(example = "10", value = "总条数")


  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public GroupVo page(Integer page) {
    this.page = page;
    return this;
  }

  /**
   * 当前页
   * @return page
  **/
  @ApiModelProperty(example = "10", value = "当前页")


  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public GroupVo pageSize(Integer pageSize) {
    this.pageSize = pageSize;
    return this;
  }

  /**
   * 每页个数
   * @return pageSize
  **/
  @ApiModelProperty(example = "10", value = "每页个数")


  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public GroupVo groupResourcesList(List<GroupResourceVo> groupResourcesList) {
    this.groupResourcesList = groupResourcesList;
    return this;
  }

  public GroupVo addGroupResourcesListItem(GroupResourceVo groupResourcesListItem) {
    if (this.groupResourcesList == null) {
      this.groupResourcesList = new ArrayList<GroupResourceVo>();
    }
    this.groupResourcesList.add(groupResourcesListItem);
    return this;
  }

  /**
   * Get groupResourcesList
   * @return groupResourcesList
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<GroupResourceVo> getGroupResourcesList() {
    return groupResourcesList;
  }

  public void setGroupResourcesList(List<GroupResourceVo> groupResourcesList) {
    this.groupResourcesList = groupResourcesList;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GroupVo groupVo = (GroupVo) o;
    return Objects.equals(this.pageCount, groupVo.pageCount) &&
        Objects.equals(this.total, groupVo.total) &&
        Objects.equals(this.page, groupVo.page) &&
        Objects.equals(this.pageSize, groupVo.pageSize) &&
        Objects.equals(this.groupResourcesList, groupVo.groupResourcesList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pageCount, total, page, pageSize, groupResourcesList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GroupVo {\n");
    
    sb.append("    pageCount: ").append(toIndentedString(pageCount)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
    sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
    sb.append("    groupResourcesList: ").append(toIndentedString(groupResourcesList)).append("\n");
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

