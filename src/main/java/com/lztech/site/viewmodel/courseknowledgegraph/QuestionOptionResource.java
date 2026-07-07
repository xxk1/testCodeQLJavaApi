package com.lztech.site.viewmodel.courseknowledgegraph;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * QuestionOptionResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-03-19T02:58:49.024Z")


public class QuestionOptionResource {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("content")
    private String content = null;

    @JsonProperty("order")
    private Integer order = null;

    @JsonProperty("rightAnswer")
    private Boolean rightAnswer = null;

    @JsonProperty("optionFileList")
    @Valid
    private List<QuestionOptionFileResource> optionFileList = null;

    public QuestionOptionResource id(String id) {
        this.id = id;
        return this;
    }

    /**
     * 选项id
     *
     * @return id
     **/
    @ApiModelProperty(value = "选项id")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public QuestionOptionResource content(String content) {
        this.content = content;
        return this;
    }

    /**
     * 选项内容(类型为填空题时，选项内容传入数组格式字符串：'[{“答案一”},{“答案二”}]')
     *
     * @return content
     **/
    @ApiModelProperty(value = "选项内容(类型为填空题时，选项内容传入数组格式字符串：'[{“答案一”},{“答案二”}]')")


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public QuestionOptionResource order(Integer order) {
        this.order = order;
        return this;
    }

    /**
     * 排序
     *
     * @return order
     **/
    @ApiModelProperty(value = "排序")


    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public QuestionOptionResource rightAnswer(Boolean rightAnswer) {
        this.rightAnswer = rightAnswer;
        return this;
    }

    /**
     * 是否正确答案
     *
     * @return rightAnswer
     **/
    @ApiModelProperty(value = "是否正确答案")


    public Boolean isRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(Boolean rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public QuestionOptionResource optionFileList(List<QuestionOptionFileResource> optionFileList) {
        this.optionFileList = optionFileList;
        return this;
    }

    public QuestionOptionResource addOptionFileListItem(QuestionOptionFileResource optionFileListItem) {
        if (this.optionFileList == null) {
            this.optionFileList = new ArrayList<QuestionOptionFileResource>();
        }
        this.optionFileList.add(optionFileListItem);
        return this;
    }

    /**
     * Get optionFileList
     *
     * @return optionFileList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<QuestionOptionFileResource> getOptionFileList() {
        return optionFileList;
    }

    public void setOptionFileList(List<QuestionOptionFileResource> optionFileList) {
        this.optionFileList = optionFileList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QuestionOptionResource questionOptionResource = (QuestionOptionResource) o;
        return Objects.equals(this.id, questionOptionResource.id) &&
                Objects.equals(this.content, questionOptionResource.content) &&
                Objects.equals(this.order, questionOptionResource.order) &&
                Objects.equals(this.rightAnswer, questionOptionResource.rightAnswer) &&
                Objects.equals(this.optionFileList, questionOptionResource.optionFileList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, order, rightAnswer, optionFileList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class QuestionOptionResource {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    content: ").append(toIndentedString(content)).append("\n");
        sb.append("    order: ").append(toIndentedString(order)).append("\n");
        sb.append("    rightAnswer: ").append(toIndentedString(rightAnswer)).append("\n");
        sb.append("    optionFileList: ").append(toIndentedString(optionFileList)).append("\n");
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

