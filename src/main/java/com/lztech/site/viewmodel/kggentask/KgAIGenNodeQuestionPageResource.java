package com.lztech.site.viewmodel.kggentask;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lztech.site.viewmodel.questionlibrary.QuestionLibraryResource;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * KgAIGenNodeQuestionPageResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-07-31T14:50:52.722+08:00")

public class KgAIGenNodeQuestionPageResource {
    @JsonProperty("page")
    private Integer page = null;

    @JsonProperty("pageCount")
    private Integer pageCount = null;

    @JsonProperty("totalCount")
    private Integer totalCount = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    @JsonProperty("questionList")
    @Valid
    private List<QuestionLibraryResource> questionList = null;

    public KgAIGenNodeQuestionPageResource page(Integer page) {
        this.page = page;
        return this;
    }

    /**
     * 页码
     *
     * @return page
     **/
    @ApiModelProperty(value = "页码")


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public KgAIGenNodeQuestionPageResource pageCount(Integer pageCount) {
        this.pageCount = pageCount;
        return this;
    }

    /**
     * 总页数
     *
     * @return pageCount
     **/
    @ApiModelProperty(value = "总页数")


    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public KgAIGenNodeQuestionPageResource totalCount(Integer totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    /**
     * 总记录数
     *
     * @return totalCount
     **/
    @ApiModelProperty(value = "总记录数")


    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public KgAIGenNodeQuestionPageResource pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * 每页记录数
     *
     * @return pageSize
     **/
    @ApiModelProperty(value = "每页记录数")


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public KgAIGenNodeQuestionPageResource questionList(List<QuestionLibraryResource> questionList) {
        this.questionList = questionList;
        return this;
    }

    public KgAIGenNodeQuestionPageResource addQuestionListItem(QuestionLibraryResource questionListItem) {
        if (this.questionList == null) {
            this.questionList = new ArrayList<QuestionLibraryResource>();
        }
        this.questionList.add(questionListItem);
        return this;
    }

    /**
     * Get questionList
     *
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
        KgAIGenNodeQuestionPageResource kgAIGenNodeQuestionPageResource = (KgAIGenNodeQuestionPageResource) o;
        return Objects.equals(this.page, kgAIGenNodeQuestionPageResource.page) &&
                Objects.equals(this.pageCount, kgAIGenNodeQuestionPageResource.pageCount) &&
                Objects.equals(this.totalCount, kgAIGenNodeQuestionPageResource.totalCount) &&
                Objects.equals(this.pageSize, kgAIGenNodeQuestionPageResource.pageSize) &&
                Objects.equals(this.questionList, kgAIGenNodeQuestionPageResource.questionList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, pageCount, totalCount, pageSize, questionList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class KgAIGenNodeQuestionPageResource {\n");

        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    pageCount: ").append(toIndentedString(pageCount)).append("\n");
        sb.append("    totalCount: ").append(toIndentedString(totalCount)).append("\n");
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

