package com.lztech.site.resource.course;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * CourseResourcesPageVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-10T10:07:17.884Z")

public class CourseResourcesPageVo   {
  @JsonProperty("total")
  private Integer total = null;

  @JsonProperty("page")
  private Integer page = null;

  @JsonProperty("pageSize")
  private Integer pageSize = null;

  @JsonProperty("CourseResourcesList")
  @Valid
  private List<CourseInfoResource> courseResourcesList = null;

  public CourseResourcesPageVo total(Integer total) {
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

  public CourseResourcesPageVo page(Integer page) {
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

  public CourseResourcesPageVo pageSize(Integer pageSize) {
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

  public CourseResourcesPageVo courseResourcesList(List<CourseInfoResource> courseResourcesList) {
    this.courseResourcesList = courseResourcesList;
    return this;
  }

  public CourseResourcesPageVo addCourseResourcesListItem(CourseInfoResource courseResourcesListItem) {
    if (this.courseResourcesList == null) {
      this.courseResourcesList = new ArrayList<CourseInfoResource>();
    }
    this.courseResourcesList.add(courseResourcesListItem);
    return this;
  }

  /**
   * Get courseResourcesList
   * @return courseResourcesList
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<CourseInfoResource> getCourseResourcesList() {
    return courseResourcesList;
  }

  public void setCourseResourcesList(List<CourseInfoResource> courseResourcesList) {
    this.courseResourcesList = courseResourcesList;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CourseResourcesPageVo courseResourcesPageVo = (CourseResourcesPageVo) o;
    return Objects.equals(this.total, courseResourcesPageVo.total) &&
        Objects.equals(this.page, courseResourcesPageVo.page) &&
        Objects.equals(this.pageSize, courseResourcesPageVo.pageSize) &&
        Objects.equals(this.courseResourcesList, courseResourcesPageVo.courseResourcesList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(total, page, pageSize, courseResourcesList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CourseResourcesPageVo {\n");
    
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
    sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
    sb.append("    courseResourcesList: ").append(toIndentedString(courseResourcesList)).append("\n");
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

