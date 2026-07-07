package com.lztech.site.viewmodel.coursematerial;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ClassTestPaperResult
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-03-07T02:09:36.180Z")


public class ClassTestPaperResult {
    @JsonProperty("classTestPaperId")
    private String classTestPaperId = null;

    @JsonProperty("questionNum")
    private Integer questionNum = null;

    public ClassTestPaperResult classTestPaperId(String classTestPaperId) {
        this.classTestPaperId = classTestPaperId;
        return this;
    }

    /**
     * 测验id
     *
     * @return classTestPaperId
     **/
    @ApiModelProperty(value = "测验id")


    public String getClassTestPaperId() {
        return classTestPaperId;
    }

    public void setClassTestPaperId(String classTestPaperId) {
        this.classTestPaperId = classTestPaperId;
    }

    public ClassTestPaperResult questionNum(Integer questionNum) {
        this.questionNum = questionNum;
        return this;
    }

    /**
     * 题目总数
     *
     * @return questionNum
     **/
    @ApiModelProperty(value = "题目总数")


    public Integer getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(Integer questionNum) {
        this.questionNum = questionNum;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClassTestPaperResult classTestPaperResult = (ClassTestPaperResult) o;
        return Objects.equals(this.classTestPaperId, classTestPaperResult.classTestPaperId)
                && Objects.equals(this.questionNum, classTestPaperResult.questionNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classTestPaperId, questionNum);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ClassTestPaperResult {\n");

        sb.append("    classTestPaperId: ").append(toIndentedString(classTestPaperId)).append("\n");
        sb.append("    questionNum: ").append(toIndentedString(questionNum)).append("\n");
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

