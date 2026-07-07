package com.lztech.site.viewmodel.major;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * MajorVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-20T10:40:15.812Z")

public class MajorVo   {
  @JsonProperty("pageCount")
  private Integer pageCount = null;

  @JsonProperty("total")
  private Integer total = null;

  @JsonProperty("page")
  private Integer page = null;

  @JsonProperty("pageSize")
  private Integer pageSize = null;

  @JsonProperty("majorResourcesList")
  @Valid
  private List<MajorResourceVo> majorResourcesList = null;

  public MajorVo pageCount(Integer pageCount) {
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

  public MajorVo total(Integer total) {
    this.total = total;
    return this;
  }

  /**
   * 总页数
   * @return total
   **/
  @ApiModelProperty(example = "10", value = "总页数")


  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public MajorVo page(Integer page) {
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

  public MajorVo pageSize(Integer pageSize) {
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

  public MajorVo majorResourcesList(List<MajorResourceVo> majorResourcesList) {
    this.majorResourcesList = majorResourcesList;
    return this;
  }

  public MajorVo addMajorResourcesListItem(MajorResourceVo majorResourcesListItem) {
    if (this.majorResourcesList == null) {
      this.majorResourcesList = new ArrayList<MajorResourceVo>();
    }
    this.majorResourcesList.add(majorResourcesListItem);
    return this;
  }

  /**
   * Get majorResourcesList
   * @return majorResourcesList
   **/
  @ApiModelProperty(value = "")

  @Valid

  public List<MajorResourceVo> getMajorResourcesList() {
    return majorResourcesList;
  }

  public void setMajorResourcesList(List<MajorResourceVo> majorResourcesList) {
    this.majorResourcesList = majorResourcesList;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MajorVo majorVo = (MajorVo) o;
    return Objects.equals(this.pageCount, majorVo.pageCount) &&
            Objects.equals(this.total, majorVo.total) &&
            Objects.equals(this.page, majorVo.page) &&
            Objects.equals(this.pageSize, majorVo.pageSize) &&
            Objects.equals(this.majorResourcesList, majorVo.majorResourcesList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pageCount, total, page, pageSize, majorResourcesList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MajorVo {\n");

    sb.append("    pageCount: ").append(toIndentedString(pageCount)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
    sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
    sb.append("    majorResourcesList: ").append(toIndentedString(majorResourcesList)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

