package com.lztech.site.viewmodel.kggentask;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * KgAIGenRelatedStatisticsResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-07-31T14:50:52.722+08:00")

public class KgAIGenRelatedStatisticsResource {
    @JsonProperty("totalVideoNum")
    private Integer totalVideoNum = null;

    @JsonProperty("supportVoiceTranVideoNum")
    private Integer supportVoiceTranVideoNum = null;

    @JsonProperty("supportVoiceTranVideoTeacherNames")
    private String supportVoiceTranVideoTeacherNames = null;

    @JsonProperty("relatedVideoClipNum")
    private Integer relatedVideoClipNum = null;

    @JsonProperty("courseQuestionNum")
    private Integer courseQuestionNum = null;

    @JsonProperty("courseQuestionTeacherNames")
    private String courseQuestionTeacherNames = null;

    @JsonProperty("relatedQuestionNum")
    private Integer relatedQuestionNum = null;

    public KgAIGenRelatedStatisticsResource totalVideoNum(Integer totalVideoNum) {
        this.totalVideoNum = totalVideoNum;
        return this;
    }

    /**
     * 视频数量
     *
     * @return totalVideoNum
     **/
    @ApiModelProperty(value = "视频数量")


    public Integer getTotalVideoNum() {
        return totalVideoNum;
    }

    public void setTotalVideoNum(Integer totalVideoNum) {
        this.totalVideoNum = totalVideoNum;
    }

    public KgAIGenRelatedStatisticsResource supportVoiceTranVideoNum(Integer supportVoiceTranVideoNum) {
        this.supportVoiceTranVideoNum = supportVoiceTranVideoNum;
        return this;
    }

    /**
     * 支持语音转换的视频数量
     *
     * @return supportVoiceTranVideoNum
     **/
    @ApiModelProperty(value = "支持语音转换的视频数量")


    public Integer getSupportVoiceTranVideoNum() {
        return supportVoiceTranVideoNum;
    }

    public void setSupportVoiceTranVideoNum(Integer supportVoiceTranVideoNum) {
        this.supportVoiceTranVideoNum = supportVoiceTranVideoNum;
    }

    public KgAIGenRelatedStatisticsResource supportVoiceTranVideoTeacherNames(String supportVoiceTranVideoTeacherNames) {
        this.supportVoiceTranVideoTeacherNames = supportVoiceTranVideoTeacherNames;
        return this;
    }

    /**
     * 支持语音转换的视频老师姓名
     *
     * @return supportVoiceTranVideoTeacherNames
     **/
    @ApiModelProperty(value = "支持语音转换的视频老师姓名")


    public String getSupportVoiceTranVideoTeacherNames() {
        return supportVoiceTranVideoTeacherNames;
    }

    public void setSupportVoiceTranVideoTeacherNames(String supportVoiceTranVideoTeacherNames) {
        this.supportVoiceTranVideoTeacherNames = supportVoiceTranVideoTeacherNames;
    }

    public KgAIGenRelatedStatisticsResource relatedVideoClipNum(Integer relatedVideoClipNum) {
        this.relatedVideoClipNum = relatedVideoClipNum;
        return this;
    }

    /**
     * 关联视频片段数量
     *
     * @return relatedVideoClipNum
     **/
    @ApiModelProperty(value = "关联视频片段数量")


    public Integer getRelatedVideoClipNum() {
        return relatedVideoClipNum;
    }

    public void setRelatedVideoClipNum(Integer relatedVideoClipNum) {
        this.relatedVideoClipNum = relatedVideoClipNum;
    }

    public KgAIGenRelatedStatisticsResource courseQuestionNum(Integer courseQuestionNum) {
        this.courseQuestionNum = courseQuestionNum;
        return this;
    }

    /**
     * 课程题目数量
     *
     * @return courseQuestionNum
     **/
    @ApiModelProperty(value = "课程题目数量")


    public Integer getCourseQuestionNum() {
        return courseQuestionNum;
    }

    public void setCourseQuestionNum(Integer courseQuestionNum) {
        this.courseQuestionNum = courseQuestionNum;
    }

    public KgAIGenRelatedStatisticsResource courseQuestionTeacherNames(String courseQuestionTeacherNames) {
        this.courseQuestionTeacherNames = courseQuestionTeacherNames;
        return this;
    }

    /**
     * 课程题目老师姓名
     *
     * @return courseQuestionTeacherNames
     **/
    @ApiModelProperty(value = "课程题目老师姓名")


    public String getCourseQuestionTeacherNames() {
        return courseQuestionTeacherNames;
    }

    public void setCourseQuestionTeacherNames(String courseQuestionTeacherNames) {
        this.courseQuestionTeacherNames = courseQuestionTeacherNames;
    }

    public KgAIGenRelatedStatisticsResource relatedQuestionNum(Integer relatedQuestionNum) {
        this.relatedQuestionNum = relatedQuestionNum;
        return this;
    }

    /**
     * 关联题目数量
     *
     * @return relatedQuestionNum
     **/
    @ApiModelProperty(value = "关联题目数量")


    public Integer getRelatedQuestionNum() {
        return relatedQuestionNum;
    }

    public void setRelatedQuestionNum(Integer relatedQuestionNum) {
        this.relatedQuestionNum = relatedQuestionNum;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        KgAIGenRelatedStatisticsResource kgAIGenRelatedStatisticsResource = (KgAIGenRelatedStatisticsResource) o;
        return Objects.equals(this.totalVideoNum, kgAIGenRelatedStatisticsResource.totalVideoNum) &&
                Objects.equals(this.supportVoiceTranVideoNum, kgAIGenRelatedStatisticsResource.supportVoiceTranVideoNum) &&
                Objects.equals(this.supportVoiceTranVideoTeacherNames, kgAIGenRelatedStatisticsResource.supportVoiceTranVideoTeacherNames) &&
                Objects.equals(this.relatedVideoClipNum, kgAIGenRelatedStatisticsResource.relatedVideoClipNum) &&
                Objects.equals(this.courseQuestionNum, kgAIGenRelatedStatisticsResource.courseQuestionNum) &&
                Objects.equals(this.courseQuestionTeacherNames, kgAIGenRelatedStatisticsResource.courseQuestionTeacherNames) &&
                Objects.equals(this.relatedQuestionNum, kgAIGenRelatedStatisticsResource.relatedQuestionNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalVideoNum, supportVoiceTranVideoNum, supportVoiceTranVideoTeacherNames, relatedVideoClipNum, courseQuestionNum,
                courseQuestionTeacherNames, relatedQuestionNum);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class KgAIGenRelatedStatisticsResource {\n");

        sb.append("    totalVideoNum: ").append(toIndentedString(totalVideoNum)).append("\n");
        sb.append("    supportVoiceTranVideoNum: ").append(toIndentedString(supportVoiceTranVideoNum)).append("\n");
        sb.append("    supportVoiceTranVideoTeacherNames: ").append(toIndentedString(supportVoiceTranVideoTeacherNames)).append("\n");
        sb.append("    relatedVideoClipNum: ").append(toIndentedString(relatedVideoClipNum)).append("\n");
        sb.append("    courseQuestionNum: ").append(toIndentedString(courseQuestionNum)).append("\n");
        sb.append("    courseQuestionTeacherNames: ").append(toIndentedString(courseQuestionTeacherNames)).append("\n");
        sb.append("    relatedQuestionNum: ").append(toIndentedString(relatedQuestionNum)).append("\n");
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

