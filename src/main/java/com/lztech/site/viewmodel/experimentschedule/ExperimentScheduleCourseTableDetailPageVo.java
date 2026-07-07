package com.lztech.site.viewmodel.experimentschedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ExperimentScheduleCourseTableDetailPageVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-09-12T10:03:02.836Z")


public class ExperimentScheduleCourseTableDetailPageVo   {
  @JsonProperty("page")
  private Integer page = null;

  @JsonProperty("pageSize")
  private Integer pageSize = null;

  @JsonProperty("total")
  private Integer total = null;

  @JsonProperty("totalPage")
  private Integer totalPage = null;

  @JsonProperty("courseTableDetails")
  @Valid
  private List<ExperimentScheduleCourseTableDetailVo> courseTableDetails = null;

  public ExperimentScheduleCourseTableDetailPageVo page(Integer page) {
    this.page = page;
    return this;
  }

  /**
   * 页码
   * @return page
  **/
  @ApiModelProperty(example = "10", value = "页码")


  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public ExperimentScheduleCourseTableDetailPageVo pageSize(Integer pageSize) {
    this.pageSize = pageSize;
    return this;
  }

  /**
   * 每页记录数
   * @return pageSize
  **/
  @ApiModelProperty(example = "10", value = "每页记录数")


  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public ExperimentScheduleCourseTableDetailPageVo total(Integer total) {
    this.total = total;
    return this;
  }

  /**
   * 总记录数
   * @return total
  **/
  @ApiModelProperty(example = "10", value = "总记录数")


  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public ExperimentScheduleCourseTableDetailPageVo totalPage(Integer totalPage) {
    this.totalPage = totalPage;
    return this;
  }

  /**
   * 总页数
   * @return totalPage
  **/
  @ApiModelProperty(example = "10", value = "总页数")


  public Integer getTotalPage() {
    return totalPage;
  }

  public void setTotalPage(Integer totalPage) {
    this.totalPage = totalPage;
  }

  public ExperimentScheduleCourseTableDetailPageVo courseTableDetails(List<ExperimentScheduleCourseTableDetailVo> courseTableDetails) {
    this.courseTableDetails = courseTableDetails;
    return this;
  }

  public ExperimentScheduleCourseTableDetailPageVo addCourseTableDetailsItem(ExperimentScheduleCourseTableDetailVo courseTableDetailsItem) {
    if (this.courseTableDetails == null) {
      this.courseTableDetails = new ArrayList<ExperimentScheduleCourseTableDetailVo>();
    }
    this.courseTableDetails.add(courseTableDetailsItem);
    return this;
  }

  /**
   * Get courseTableDetails
   * @return courseTableDetails
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<ExperimentScheduleCourseTableDetailVo> getCourseTableDetails() {
    return courseTableDetails;
  }

  public void setCourseTableDetails(List<ExperimentScheduleCourseTableDetailVo> courseTableDetails) {
    this.courseTableDetails = courseTableDetails;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExperimentScheduleCourseTableDetailPageVo experimentScheduleCourseTableDetailPageVo = (ExperimentScheduleCourseTableDetailPageVo) o;
    return Objects.equals(this.page, experimentScheduleCourseTableDetailPageVo.page) &&
        Objects.equals(this.pageSize, experimentScheduleCourseTableDetailPageVo.pageSize) &&
        Objects.equals(this.total, experimentScheduleCourseTableDetailPageVo.total) &&
        Objects.equals(this.totalPage, experimentScheduleCourseTableDetailPageVo.totalPage) &&
        Objects.equals(this.courseTableDetails, experimentScheduleCourseTableDetailPageVo.courseTableDetails);
  }

  @Override
  public int hashCode() {
    return Objects.hash(page, pageSize, total, totalPage, courseTableDetails);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExperimentScheduleCourseTableDetailPageVo {\n");
    
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
    sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    totalPage: ").append(toIndentedString(totalPage)).append("\n");
    sb.append("    courseTableDetails: ").append(toIndentedString(courseTableDetails)).append("\n");
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

