package com.lztech.site.viewmodel.questionknowledgepointvo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * QuestionKnowledgePointVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-07-02T16:37:02.226+08:00")

public class QuestionKnowledgePointVo {
    @JsonProperty("operatorId")
    private String operatorId = null;

    @JsonProperty("operatorName")
    private String operatorName = null;

    @JsonProperty("questionId")
    private String questionId = null;

    @JsonProperty("courseKnowledgeGraphId")
    private String courseKnowledgeGraphId = null;

    @JsonProperty("pointIdList")
    @Valid
    private List<String> pointIdList = null;

    public String getCourseKnowledgeGraphId() {
        return courseKnowledgeGraphId;
    }

    public void setCourseKnowledgeGraphId(String courseKnowledgeGraphId) {
        this.courseKnowledgeGraphId = courseKnowledgeGraphId;
    }

    public QuestionKnowledgePointVo operatorId(String operatorId) {
        this.operatorId = operatorId;
        return this;
    }

    /**
     * 操作人id
     *
     * @return operatorId
     **/
    @ApiModelProperty(value = "操作人id")


    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public QuestionKnowledgePointVo operatorName(String operatorName) {
        this.operatorName = operatorName;
        return this;
    }

    /**
     * 操作人姓名
     *
     * @return operatorName
     **/
    @ApiModelProperty(value = "操作人姓名")


    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public QuestionKnowledgePointVo questionId(String questionId) {
        this.questionId = questionId;
        return this;
    }

    /**
     * 题目id
     *
     * @return questionId
     **/
    @ApiModelProperty(value = "题目id")


    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public QuestionKnowledgePointVo pointIdList(List<String> pointIdList) {
        this.pointIdList = pointIdList;
        return this;
    }

    public QuestionKnowledgePointVo addPointIdListItem(String pointIdListItem) {
        if (this.pointIdList == null) {
            this.pointIdList = new ArrayList<String>();
        }
        this.pointIdList.add(pointIdListItem);
        return this;
    }

    /**
     * Get pointIdList
     *
     * @return pointIdList
     **/
    @ApiModelProperty(value = "")


    public List<String> getPointIdList() {
        return pointIdList;
    }

    public void setPointIdList(List<String> pointIdList) {
        this.pointIdList = pointIdList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QuestionKnowledgePointVo questionKnowledgePointVo = (QuestionKnowledgePointVo) o;
        return Objects.equals(this.operatorId, questionKnowledgePointVo.operatorId) &&
                Objects.equals(this.operatorName, questionKnowledgePointVo.operatorName) &&
                Objects.equals(this.questionId, questionKnowledgePointVo.questionId) &&
                Objects.equals(this.pointIdList, questionKnowledgePointVo.pointIdList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operatorId, operatorName, questionId, pointIdList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class QuestionKnowledgePointVo {\n");

        sb.append("    operatorId: ").append(toIndentedString(operatorId)).append("\n");
        sb.append("    operatorName: ").append(toIndentedString(operatorName)).append("\n");
        sb.append("    questionId: ").append(toIndentedString(questionId)).append("\n");
        sb.append("    pointIdList: ").append(toIndentedString(pointIdList)).append("\n");
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

