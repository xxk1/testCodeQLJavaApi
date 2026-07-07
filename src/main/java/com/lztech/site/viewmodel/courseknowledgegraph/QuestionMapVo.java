package com.lztech.site.viewmodel.courseknowledgegraph;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * QuestionMapVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-11-25T09:46:31.094Z")


public class QuestionMapVo {
    @JsonProperty("sourceQuestionId")
    private String sourceQuestionId = null;

    @JsonProperty("newQuestionId")
    private String newQuestionId = null;

    public QuestionMapVo sourceQuestionId(String sourceQuestionId) {
        this.sourceQuestionId = sourceQuestionId;
        return this;
    }

    /**
     * 原题库题目id
     *
     * @return sourceQuestionId
     **/
    @ApiModelProperty(value = "原题库题目id")


    public String getSourceQuestionId() {
        return sourceQuestionId;
    }

    public void setSourceQuestionId(String sourceQuestionId) {
        this.sourceQuestionId = sourceQuestionId;
    }

    public QuestionMapVo newQuestionId(String newQuestionId) {
        this.newQuestionId = newQuestionId;
        return this;
    }

    /**
     * 新题库题目id
     *
     * @return newQuestionId
     **/
    @ApiModelProperty(value = "新题库题目id")


    public String getNewQuestionId() {
        return newQuestionId;
    }

    public void setNewQuestionId(String newQuestionId) {
        this.newQuestionId = newQuestionId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QuestionMapVo questionMapVo = (QuestionMapVo) o;
        return Objects.equals(this.sourceQuestionId, questionMapVo.sourceQuestionId) &&
                Objects.equals(this.newQuestionId, questionMapVo.newQuestionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceQuestionId, newQuestionId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class QuestionMapVo {\n");

        sb.append("    sourceQuestionId: ").append(toIndentedString(sourceQuestionId)).append("\n");
        sb.append("    newQuestionId: ").append(toIndentedString(newQuestionId)).append("\n");
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

