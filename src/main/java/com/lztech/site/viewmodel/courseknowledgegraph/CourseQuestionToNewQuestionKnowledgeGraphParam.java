package com.lztech.site.viewmodel.courseknowledgegraph;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseQuestionToNewQuestionKnowledgeGraphParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-11-25T09:46:31.094Z")


public class CourseQuestionToNewQuestionKnowledgeGraphParam {
    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("userId")
    private String userId = null;

    @JsonProperty("userName")
    private String userName = null;

    @JsonProperty("questionIdList")
    @Valid
    private List<QuestionMapVo> questionIdList = null;

    public CourseQuestionToNewQuestionKnowledgeGraphParam courseId(String courseId) {
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

    public CourseQuestionToNewQuestionKnowledgeGraphParam userId(String userId) {
        this.userId = userId;
        return this;
    }

    /**
     * 用户id
     *
     * @return userId
     **/
    @ApiModelProperty(value = "用户id")


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public CourseQuestionToNewQuestionKnowledgeGraphParam userName(String userName) {
        this.userName = userName;
        return this;
    }

    /**
     * 用户姓名
     *
     * @return userName
     **/
    @ApiModelProperty(value = "用户姓名")


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public CourseQuestionToNewQuestionKnowledgeGraphParam questionIdList(List<QuestionMapVo> questionIdList) {
        this.questionIdList = questionIdList;
        return this;
    }

    public CourseQuestionToNewQuestionKnowledgeGraphParam addQuestionIdListItem(QuestionMapVo questionIdListItem) {
        if (this.questionIdList == null) {
            this.questionIdList = new ArrayList<QuestionMapVo>();
        }
        this.questionIdList.add(questionIdListItem);
        return this;
    }

    /**
     * 题目对应列表
     *
     * @return questionIdList
     **/
    @ApiModelProperty(value = "题目对应列表")

    @Valid

    public List<QuestionMapVo> getQuestionIdList() {
        return questionIdList;
    }

    public void setQuestionIdList(List<QuestionMapVo> questionIdList) {
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
        CourseQuestionToNewQuestionKnowledgeGraphParam courseQuestionToNewQuestionKnowledgeGraphParam =
                (CourseQuestionToNewQuestionKnowledgeGraphParam) o;
        return Objects.equals(this.courseId, courseQuestionToNewQuestionKnowledgeGraphParam.courseId) &&
                Objects.equals(this.userId, courseQuestionToNewQuestionKnowledgeGraphParam.userId) &&
                Objects.equals(this.userName, courseQuestionToNewQuestionKnowledgeGraphParam.userName) &&
                Objects.equals(this.questionIdList, courseQuestionToNewQuestionKnowledgeGraphParam.questionIdList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, userId, userName, questionIdList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseQuestionToNewQuestionKnowledgeGraphParam {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
        sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
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

