package com.lztech.site.viewmodel.preparationcoursebag;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * TestPaperParmVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-04-07T02:38:58.077Z")


public class TestPaperParmVo {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("source")
    private Integer source = null;

    @JsonProperty("isPublic")
    private Boolean isPublic = null;

    @JsonProperty("paperId")
    private String paperId = null;

    @JsonProperty("paperName")
    private String paperName = null;

    @JsonProperty("userId")
    private String userId = null;

    @JsonProperty("userName")
    private String userName = null;

    @JsonProperty("courseStructureId")
    private String courseStructureId = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("questionNum")
    private Integer questionNum = null;

    public TestPaperParmVo id(String id) {
        this.id = id;
        return this;
    }

    /**
     * 测验id
     *
     * @return id
     **/
    @ApiModelProperty(value = "测验id")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TestPaperParmVo source(Integer source) {
        this.source = source;
        return this;
    }

    /**
     * 来源：0:教师备课，1:教学内容
     *
     * @return source
     **/
    @ApiModelProperty(value = "来源：0:教师备课，1:教学内容")


    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public TestPaperParmVo isPublic(Boolean isPublic) {
        this.isPublic = isPublic;
        return this;
    }

    /**
     * 是否公开
     *
     * @return isPublic
     **/
    @ApiModelProperty(value = "是否公开")


    public Boolean isIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public TestPaperParmVo paperId(String paperId) {
        this.paperId = paperId;
        return this;
    }

    /**
     * 试卷Id
     *
     * @return paperId
     **/
    @ApiModelProperty(value = "试卷Id")


    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public TestPaperParmVo paperName(String paperName) {
        this.paperName = paperName;
        return this;
    }

    /**
     * 试卷名称
     *
     * @return paperName
     **/
    @ApiModelProperty(value = "试卷名称")


    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public TestPaperParmVo userId(String userId) {
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

    public TestPaperParmVo userName(String userName) {
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

    public TestPaperParmVo courseStructureId(String courseStructureId) {
        this.courseStructureId = courseStructureId;
        return this;
    }

    /**
     * 课程结构Id
     *
     * @return courseStructureId
     **/
    @ApiModelProperty(value = "课程结构Id")


    public String getCourseStructureId() {
        return courseStructureId;
    }

    public void setCourseStructureId(String courseStructureId) {
        this.courseStructureId = courseStructureId;
    }

    public TestPaperParmVo courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    /**
     * 课程Id
     *
     * @return courseId
     **/
    @ApiModelProperty(value = "课程Id")


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public TestPaperParmVo questionNum(Integer questionNum) {
        this.questionNum = questionNum;
        return this;
    }

    /**
     * 题数
     *
     * @return questionNum
     **/
    @ApiModelProperty(value = "题数")


    public Integer getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(Integer questionNum) {
        this.questionNum = questionNum;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TestPaperParmVo testPaperParmVo = (TestPaperParmVo) o;
        return Objects.equals(this.id, testPaperParmVo.id) &&
                Objects.equals(this.source, testPaperParmVo.source) &&
                Objects.equals(this.isPublic, testPaperParmVo.isPublic) &&
                Objects.equals(this.paperId, testPaperParmVo.paperId) &&
                Objects.equals(this.paperName, testPaperParmVo.paperName) &&
                Objects.equals(this.userId, testPaperParmVo.userId) &&
                Objects.equals(this.userName, testPaperParmVo.userName) &&
                Objects.equals(this.courseStructureId, testPaperParmVo.courseStructureId) &&
                Objects.equals(this.courseId, testPaperParmVo.courseId) &&
                Objects.equals(this.questionNum, testPaperParmVo.questionNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, source, isPublic, paperId, paperName, userId, userName, courseStructureId, courseId, questionNum);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TestPaperParmVo {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    source: ").append(toIndentedString(source)).append("\n");
        sb.append("    isPublic: ").append(toIndentedString(isPublic)).append("\n");
        sb.append("    paperId: ").append(toIndentedString(paperId)).append("\n");
        sb.append("    paperName: ").append(toIndentedString(paperName)).append("\n");
        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
        sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
        sb.append("    courseStructureId: ").append(toIndentedString(courseStructureId)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    questionNum: ").append(toIndentedString(questionNum)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

