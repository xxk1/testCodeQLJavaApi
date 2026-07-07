package com.lztech.site.viewmodel.teachingpackage;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * CourseTeachingPackagePageResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-03-26T07:36:24.014Z")


public class CourseTeachingPackagePageResource   {
  @JsonProperty("pageCount")
  private Integer pageCount = null;

  @JsonProperty("total")
  private Integer total = null;

  @JsonProperty("page")
  private Integer page = null;

  @JsonProperty("pageSize")
  private Integer pageSize = null;

  @JsonProperty("CourseTeachingPackageListVo")
  @Valid
  private List<CourseTeachingPackageListVo> courseTeachingPackageListVo = null;

  public CourseTeachingPackagePageResource pageCount(Integer pageCount) {
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

  public CourseTeachingPackagePageResource total(Integer total) {
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

  public CourseTeachingPackagePageResource page(Integer page) {
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

  public CourseTeachingPackagePageResource pageSize(Integer pageSize) {
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

  public CourseTeachingPackagePageResource courseTeachingPackageListVo(List<CourseTeachingPackageListVo> courseTeachingPackageListVo) {
    this.courseTeachingPackageListVo = courseTeachingPackageListVo;
    return this;
  }

  public CourseTeachingPackagePageResource addCourseTeachingPackageListVoItem(CourseTeachingPackageListVo courseTeachingPackageListVoItem) {
    if (this.courseTeachingPackageListVo == null) {
      this.courseTeachingPackageListVo = new ArrayList<CourseTeachingPackageListVo>();
    }
    this.courseTeachingPackageListVo.add(courseTeachingPackageListVoItem);
    return this;
  }

  /**
   * Get courseTeachingPackageListVo
   * @return courseTeachingPackageListVo
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<CourseTeachingPackageListVo> getCourseTeachingPackageListVo() {
    return courseTeachingPackageListVo;
  }

  public void setCourseTeachingPackageListVo(List<CourseTeachingPackageListVo> courseTeachingPackageListVo) {
    this.courseTeachingPackageListVo = courseTeachingPackageListVo;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CourseTeachingPackagePageResource courseTeachingPackagePageResource = (CourseTeachingPackagePageResource) o;
    return Objects.equals(this.pageCount, courseTeachingPackagePageResource.pageCount) &&
        Objects.equals(this.total, courseTeachingPackagePageResource.total) &&
        Objects.equals(this.page, courseTeachingPackagePageResource.page) &&
        Objects.equals(this.pageSize, courseTeachingPackagePageResource.pageSize) &&
        Objects.equals(this.courseTeachingPackageListVo, courseTeachingPackagePageResource.courseTeachingPackageListVo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pageCount, total, page, pageSize, courseTeachingPackageListVo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CourseTeachingPackagePageResource {\n");
    
    sb.append("    pageCount: ").append(toIndentedString(pageCount)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
    sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
    sb.append("    courseTeachingPackageListVo: ").append(toIndentedString(courseTeachingPackageListVo)).append("\n");
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

