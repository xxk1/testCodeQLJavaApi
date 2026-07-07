package com.lztech.site.viewmodel.superviseevaluation.v2;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * SupervisionCourseListPageV2
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-07-08T08:33:31.689Z")


public class SupervisionCourseListPageV2   {
  @JsonProperty("page")
  private Integer page = null;

  @JsonProperty("pageCount")
  private Integer pageCount = null;

  @JsonProperty("totalCount")
  private Integer totalCount = null;

  @JsonProperty("supervisionCourseList")
  @Valid
  private List<SupervisionCourseAndCourseTableDetailVo> supervisionCourseList = null;

  public SupervisionCourseListPageV2 page(Integer page) {
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

  public SupervisionCourseListPageV2 pageCount(Integer pageCount) {
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

  public SupervisionCourseListPageV2 totalCount(Integer totalCount) {
    this.totalCount = totalCount;
    return this;
  }

  /**
   * 总条数
   * @return totalCount
  **/
  @ApiModelProperty(value = "总条数")


  public Integer getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(Integer totalCount) {
    this.totalCount = totalCount;
  }

  public SupervisionCourseListPageV2 supervisionCourseList(List<SupervisionCourseAndCourseTableDetailVo> supervisionCourseList) {
    this.supervisionCourseList = supervisionCourseList;
    return this;
  }

  public SupervisionCourseListPageV2 addSupervisionCourseListItem(SupervisionCourseAndCourseTableDetailVo supervisionCourseListItem) {
    if (this.supervisionCourseList == null) {
      this.supervisionCourseList = new ArrayList<SupervisionCourseAndCourseTableDetailVo>();
    }
    this.supervisionCourseList.add(supervisionCourseListItem);
    return this;
  }

  /**
   * 老师课程列表
   * @return supervisionCourseList
  **/
  @ApiModelProperty(value = "老师课程列表")

  @Valid

  public List<SupervisionCourseAndCourseTableDetailVo> getSupervisionCourseList() {
    return supervisionCourseList;
  }

  public void setSupervisionCourseList(List<SupervisionCourseAndCourseTableDetailVo> supervisionCourseList) {
    this.supervisionCourseList = supervisionCourseList;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SupervisionCourseListPageV2 supervisionCourseListPageV2 = (SupervisionCourseListPageV2) o;
    return Objects.equals(this.page, supervisionCourseListPageV2.page) &&
        Objects.equals(this.pageCount, supervisionCourseListPageV2.pageCount) &&
        Objects.equals(this.totalCount, supervisionCourseListPageV2.totalCount) &&
        Objects.equals(this.supervisionCourseList, supervisionCourseListPageV2.supervisionCourseList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(page, pageCount, totalCount, supervisionCourseList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SupervisionCourseListPageV2 {\n");
    
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
    sb.append("    pageCount: ").append(toIndentedString(pageCount)).append("\n");
    sb.append("    totalCount: ").append(toIndentedString(totalCount)).append("\n");
    sb.append("    supervisionCourseList: ").append(toIndentedString(supervisionCourseList)).append("\n");
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

