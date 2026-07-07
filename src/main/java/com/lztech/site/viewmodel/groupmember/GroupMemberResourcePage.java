package com.lztech.site.viewmodel.groupmember;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * GroupMemberResourcePage
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-01-06T02:45:37.693Z")


public class GroupMemberResourcePage   {
  @JsonProperty("total")
  private Integer total = null;

  @JsonProperty("totalPage")
  private Integer totalPage = null;

  @JsonProperty("page")
  private Integer page = null;

  @JsonProperty("pageSize")
  private Integer pageSize = null;

  @JsonProperty("groupMemberResourceList")
  @Valid
  private List<GroupMemberResourceVo> groupMemberResourceList = null;

  public GroupMemberResourcePage total(Integer total) {
    this.total = total;
    return this;
  }

  /**
   * 总记录数
   * @return total
  **/
  @ApiModelProperty(value = "总记录数")


  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public GroupMemberResourcePage totalPage(Integer totalPage) {
    this.totalPage = totalPage;
    return this;
  }

  /**
   * 总页数
   * @return totalPage
  **/
  @ApiModelProperty(value = "总页数")


  public Integer getTotalPage() {
    return totalPage;
  }

  public void setTotalPage(Integer totalPage) {
    this.totalPage = totalPage;
  }

  public GroupMemberResourcePage page(Integer page) {
    this.page = page;
    return this;
  }

  /**
   * 当前页码
   * @return page
  **/
  @ApiModelProperty(value = "当前页码")


  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public GroupMemberResourcePage pageSize(Integer pageSize) {
    this.pageSize = pageSize;
    return this;
  }

  /**
   * 每页记录数
   * @return pageSize
  **/
  @ApiModelProperty(value = "每页记录数")


  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public GroupMemberResourcePage groupMemberResourceList(List<GroupMemberResourceVo> groupMemberResourceList) {
    this.groupMemberResourceList = groupMemberResourceList;
    return this;
  }

  public GroupMemberResourcePage addGroupMemberResourceListItem(GroupMemberResourceVo groupMemberResourceListItem) {
    if (this.groupMemberResourceList == null) {
      this.groupMemberResourceList = new ArrayList<GroupMemberResourceVo>();
    }
    this.groupMemberResourceList.add(groupMemberResourceListItem);
    return this;
  }

  /**
   * 当前页列表数据
   * @return groupMemberResourceList
  **/
  @ApiModelProperty(value = "当前页列表数据")

  @Valid

  public List<GroupMemberResourceVo> getGroupMemberResourceList() {
    return groupMemberResourceList;
  }

  public void setGroupMemberResourceList(List<GroupMemberResourceVo> groupMemberResourceList) {
    this.groupMemberResourceList = groupMemberResourceList;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GroupMemberResourcePage groupMemberResourcePage = (GroupMemberResourcePage) o;
    return Objects.equals(this.total, groupMemberResourcePage.total) &&
        Objects.equals(this.totalPage, groupMemberResourcePage.totalPage) &&
        Objects.equals(this.page, groupMemberResourcePage.page) &&
        Objects.equals(this.pageSize, groupMemberResourcePage.pageSize) &&
        Objects.equals(this.groupMemberResourceList, groupMemberResourcePage.groupMemberResourceList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(total, totalPage, page, pageSize, groupMemberResourceList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GroupMemberResourcePage {\n");
    
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    totalPage: ").append(toIndentedString(totalPage)).append("\n");
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
    sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
    sb.append("    groupMemberResourceList: ").append(toIndentedString(groupMemberResourceList)).append("\n");
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

