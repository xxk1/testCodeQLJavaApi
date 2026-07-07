package com.lztech.site.viewmodel.courseknowledgegraph;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseKnowledgeGraphNodeQuestionParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-03-19T02:58:49.024Z")


public class CourseKnowledgeGraphNodeQuestionParam {
    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("knowledgeGraphNodeId")
    private String knowledgeGraphNodeId = null;

    @JsonProperty("accessUserId")
    private String accessUserId = null;

    @JsonProperty("accessUserName")
    private String accessUserName = null;

    @JsonProperty("courseKnowledgeGraphId")
    private String courseKnowledgeGraphId = null;

    @JsonProperty("questionIdList")
    @Valid
    private List<String> questionIdList = null;

    public CourseKnowledgeGraphNodeQuestionParam courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    /**
     * 课程id
     *
     * @return courseId
     **/
    @ApiModelProperty(value = "课程id")


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public CourseKnowledgeGraphNodeQuestionParam knowledgeGraphNodeId(String knowledgeGraphNodeId) {
        this.knowledgeGraphNodeId = knowledgeGraphNodeId;
        return this;
    }

    /**
     * 课程知识图谱节点id
     *
     * @return knowledgeGraphNodeId
     **/
    @ApiModelProperty(value = "课程知识图谱节点id")


    public String getKnowledgeGraphNodeId() {
        return knowledgeGraphNodeId;
    }

    public void setKnowledgeGraphNodeId(String knowledgeGraphNodeId) {
        this.knowledgeGraphNodeId = knowledgeGraphNodeId;
    }

    public CourseKnowledgeGraphNodeQuestionParam accessUserId(String accessUserId) {
        this.accessUserId = accessUserId;
        return this;
    }

    /**
     * 访问用户id
     *
     * @return accessUserId
     **/
    @ApiModelProperty(value = "访问用户id")


    public String getAccessUserId() {
        return accessUserId;
    }

    public void setAccessUserId(String accessUserId) {
        this.accessUserId = accessUserId;
    }

    public CourseKnowledgeGraphNodeQuestionParam accessUserName(String accessUserName) {
        this.accessUserName = accessUserName;
        return this;
    }

    /**
     * 访问用户名称
     *
     * @return accessUserName
     **/
    @ApiModelProperty(value = "访问用户名称")


    public String getAccessUserName() {
        return accessUserName;
    }

    public void setAccessUserName(String accessUserName) {
        this.accessUserName = accessUserName;
    }


    public CourseKnowledgeGraphNodeQuestionParam courseKnowledgeGraphId(String courseKnowledgeGraphId) {
        this.courseKnowledgeGraphId = courseKnowledgeGraphId;
        return this;
    }

    /**
     * 课程知识图谱id
     *
     * @return courseKnowledgeGraphId
     **/
    @ApiModelProperty(value = "课程知识图谱id")


    public String getCourseKnowledgeGraphId() {
        return courseKnowledgeGraphId;
    }

    public void setCourseKnowledgeGraphId(String courseKnowledgeGraphId) {
        this.courseKnowledgeGraphId = courseKnowledgeGraphId;
    }

    public CourseKnowledgeGraphNodeQuestionParam questionIdList(List<String> questionIdList) {
        this.questionIdList = questionIdList;
        return this;
    }

    public CourseKnowledgeGraphNodeQuestionParam addQuestionIdListItem(String questionIdListItem) {
        if (this.questionIdList == null) {
            this.questionIdList = new ArrayList<String>();
        }
        this.questionIdList.add(questionIdListItem);
        return this;
    }

    /**
     * 题目id列表
     *
     * @return questionIdList
     **/
    @ApiModelProperty(value = "题目id列表")


    public List<String> getQuestionIdList() {
        return questionIdList;
    }

    public void setQuestionIdList(List<String> questionIdList) {
        this.questionIdList = questionIdList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseKnowledgeGraphNodeQuestionParam courseKnowledgeGraphNodeQuestionParam = (CourseKnowledgeGraphNodeQuestionParam) o;
        return Objects.equals(this.courseId, courseKnowledgeGraphNodeQuestionParam.courseId) &&
                Objects.equals(this.knowledgeGraphNodeId, courseKnowledgeGraphNodeQuestionParam.knowledgeGraphNodeId) &&
                Objects.equals(this.accessUserId, courseKnowledgeGraphNodeQuestionParam.accessUserId) &&
                Objects.equals(this.accessUserName, courseKnowledgeGraphNodeQuestionParam.accessUserName) &&
                Objects.equals(this.questionIdList, courseKnowledgeGraphNodeQuestionParam.questionIdList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, knowledgeGraphNodeId, accessUserId, accessUserName, questionIdList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseKnowledgeGraphNodeQuestionParam {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    knowledgeGraphNodeId: ").append(toIndentedString(knowledgeGraphNodeId)).append("\n");
        sb.append("    accessUserId: ").append(toIndentedString(accessUserId)).append("\n");
        sb.append("    accessUserName: ").append(toIndentedString(accessUserName)).append("\n");
        sb.append("    questionIdList: ").append(toIndentedString(questionIdList)).append("\n");
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

