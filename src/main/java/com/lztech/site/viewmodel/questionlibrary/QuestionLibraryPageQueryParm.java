package com.lztech.site.viewmodel.questionlibrary;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * QuestionLibraryQueryParm
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-12-27T11:18:58.830Z")


public class QuestionLibraryPageQueryParm {
    @JsonProperty("teacherId")
    private String teacherId = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("questionStem")
    private String questionStem = null;

    @JsonProperty("questionTypeIndex")
    private Integer questionTypeIndex = null;

    @JsonProperty("questionComplexityIndex")
    private Integer questionComplexityIndex = null;

    @JsonProperty("callingParty")
    private Integer callingParty = null;

    @JsonProperty("courseStructureId")
    private String courseStructureId = null;

    @JsonProperty("page")
    private Integer page = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    public QuestionLibraryPageQueryParm teacherId(String teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    /**
     * 教师id
     * @return teacherId
     **/
    @ApiModelProperty(value = "教师id")


    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public QuestionLibraryPageQueryParm courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    /**
     * 课程id
     * @return courseId
     **/
    @ApiModelProperty(value = "课程id")


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public QuestionLibraryPageQueryParm questionStem(String questionStem) {
        this.questionStem = questionStem;
        return this;
    }

    /**
     * 题干
     * @return questionStem
     **/
    @ApiModelProperty(value = "题干")


    public String getQuestionStem() {
        return questionStem;
    }

    public void setQuestionStem(String questionStem) {
        this.questionStem = questionStem;
    }

    public QuestionLibraryPageQueryParm questionTypeIndex(Integer questionTypeIndex) {
        this.questionTypeIndex = questionTypeIndex;
        return this;
    }

    /**
     * 题目类型 0：单选；1：多选；2：判断；3：填空；4：简答
     * @return questionTypeIndex
     **/
    @ApiModelProperty(value = "题目类型 0：单选；1：多选；2：判断；3：填空；4：简答")


    public Integer getQuestionTypeIndex() {
        return questionTypeIndex;
    }

    public void setQuestionTypeIndex(Integer questionTypeIndex) {
        this.questionTypeIndex = questionTypeIndex;
    }

    public QuestionLibraryPageQueryParm questionComplexityIndex(Integer questionComplexityIndex) {
        this.questionComplexityIndex = questionComplexityIndex;
        return this;
    }

    /**
     * 题目难易度 0：简单；1：一般；2：困难
     * @return questionComplexityIndex
     **/
    @ApiModelProperty(value = "题目难易度 0：简单；1：一般；2：困难")


    public Integer getQuestionComplexityIndex() {
        return questionComplexityIndex;
    }

    public void setQuestionComplexityIndex(Integer questionComplexityIndex) {
        this.questionComplexityIndex = questionComplexityIndex;
    }

    public QuestionLibraryPageQueryParm callingParty(Integer callingParty) {
        this.callingParty = callingParty;
        return this;
    }

    /**
     * 0或null或'':题库；1：备课包
     * @return callingParty
     **/
    @ApiModelProperty(value = "0或null或'':题库；1：备课包")


    public Integer getCallingParty() {
        return callingParty;
    }

    public void setCallingParty(Integer callingParty) {
        this.callingParty = callingParty;
    }

    public QuestionLibraryPageQueryParm courseStructureId(String courseStructureId) {
        this.courseStructureId = courseStructureId;
        return this;
    }

    /**
     * 章节id
     * @return courseStructureId
     **/
    @ApiModelProperty(value = "章节id")


    public String getCourseStructureId() {
        return courseStructureId;
    }

    public void setCourseStructureId(String courseStructureId) {
        this.courseStructureId = courseStructureId;
    }

    public QuestionLibraryPageQueryParm page(Integer page) {
        this.page = page;
        return this;
    }

    /**
     * 第几页
     * @return page
     **/
    @ApiModelProperty(value = "第几页")


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public QuestionLibraryPageQueryParm pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * 每页数量
     * @return pageSize
     **/
    @ApiModelProperty(value = "每页数量")


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QuestionLibraryPageQueryParm questionLibraryQueryParm = (QuestionLibraryPageQueryParm) o;
        return Objects.equals(this.teacherId, questionLibraryQueryParm.teacherId) &&
                Objects.equals(this.courseId, questionLibraryQueryParm.courseId) &&
                Objects.equals(this.questionStem, questionLibraryQueryParm.questionStem) &&
                Objects.equals(this.questionTypeIndex, questionLibraryQueryParm.questionTypeIndex) &&
                Objects.equals(this.questionComplexityIndex, questionLibraryQueryParm.questionComplexityIndex) &&
                Objects.equals(this.callingParty, questionLibraryQueryParm.callingParty) &&
                Objects.equals(this.courseStructureId, questionLibraryQueryParm.courseStructureId) &&
                Objects.equals(this.page, questionLibraryQueryParm.page) &&
                Objects.equals(this.pageSize, questionLibraryQueryParm.pageSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, courseId, questionStem, questionTypeIndex, questionComplexityIndex,
                callingParty, courseStructureId, page, pageSize);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class QuestionLibraryQueryParm {\n");

        sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    questionStem: ").append(toIndentedString(questionStem)).append("\n");
        sb.append("    questionTypeIndex: ").append(toIndentedString(questionTypeIndex)).append("\n");
        sb.append("    questionComplexityIndex: ").append(toIndentedString(questionComplexityIndex)).append("\n");
        sb.append("    callingParty: ").append(toIndentedString(callingParty)).append("\n");
        sb.append("    courseStructureId: ").append(toIndentedString(courseStructureId)).append("\n");
        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
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

