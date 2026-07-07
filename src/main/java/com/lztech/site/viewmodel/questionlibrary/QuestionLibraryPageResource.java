package com.lztech.site.viewmodel.questionlibrary;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * QuestionLibraryPageResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-10-16T07:44:01.984Z")




public class QuestionLibraryPageResource {
  @JsonProperty("total")
  private Integer total = null;

  @JsonProperty("pageCount")
  private Integer pageCount = null;

  @JsonProperty("page")
  private Integer page = null;

  @JsonProperty("pageSize")
  private Integer pageSize = null;

  @JsonProperty("questionList")
  @Valid
  private List<QuestionLibraryResource> questionList = null;

  public QuestionLibraryPageResource total(Integer total) {
    this.total = total;
    return this;
  }

  /**
   * 总数量
   * @return total
  **/
  @ApiModelProperty(example = "10", value = "总数量")


  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public QuestionLibraryPageResource pageCount(Integer pageCount) {
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

  public QuestionLibraryPageResource page(Integer page) {
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

  public QuestionLibraryPageResource pageSize(Integer pageSize) {
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

  public QuestionLibraryPageResource questionList(List<QuestionLibraryResource> questionList) {
    this.questionList = questionList;
    return this;
  }

  public QuestionLibraryPageResource addQuestionListItem(QuestionLibraryResource questionListItem) {
    if (this.questionList == null) {
      this.questionList = new ArrayList<QuestionLibraryResource>();
    }
    this.questionList.add(questionListItem);
    return this;
  }

  /**
   * Get questionList
   * @return questionList
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<QuestionLibraryResource> getQuestionList() {
    return questionList;
  }

  public void setQuestionList(List<QuestionLibraryResource> questionList) {
    this.questionList = questionList;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QuestionLibraryPageResource questionLibraryPageResource = (QuestionLibraryPageResource) o;
    return Objects.equals(this.total, questionLibraryPageResource.total) &&
        Objects.equals(this.pageCount, questionLibraryPageResource.pageCount) &&
        Objects.equals(this.page, questionLibraryPageResource.page) &&
        Objects.equals(this.pageSize, questionLibraryPageResource.pageSize) &&
        Objects.equals(this.questionList, questionLibraryPageResource.questionList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(total, pageCount, page, pageSize, questionList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QuestionLibraryPageResource {\n");
    
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    pageCount: ").append(toIndentedString(pageCount)).append("\n");
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
    sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
    sb.append("    questionList: ").append(toIndentedString(questionList)).append("\n");
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

