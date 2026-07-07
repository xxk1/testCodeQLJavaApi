package com.lztech.site.viewmodel.courseknowledgegraphstatistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseKnowledgeGraphStatisticsVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2026-01-13T14:28:39.409+08:00")

public class CourseKnowledgeGraphStatisticsVo {
    @JsonProperty("totalLevelCount")
    private Integer totalLevelCount = null;

    @JsonProperty("firstLevelCount")
    private Integer firstLevelCount = null;

    @JsonProperty("secondLevelCount")
    private Integer secondLevelCount = null;

    @JsonProperty("thirdLevelCount")
    private Integer thirdLevelCount = null;

    @JsonProperty("otherLevelCount")
    private Integer otherLevelCount = null;

    @JsonProperty("videoFragmentCount")
    private Integer videoFragmentCount = null;

    @JsonProperty("imageResourceCount")
    private Integer imageResourceCount = null;

    @JsonProperty("learnVideoResourceCount")
    private Integer learnVideoResourceCount = null;

    @JsonProperty("documentResourceCount")
    private Integer documentResourceCount = null;

    @JsonProperty("classTestCount")
    private Integer classTestCount = null;

    @JsonProperty("uploadQuestionCount")
    private Integer uploadQuestionCount = null;

    public CourseKnowledgeGraphStatisticsVo totalLevelCount(Integer totalLevelCount) {
        this.totalLevelCount = totalLevelCount;
        return this;
    }

    /**
     * 总知识点数量
     *
     * @return totalLevelCount
     **/
    @ApiModelProperty(value = "总知识点数量")


    public Integer getTotalLevelCount() {
        return totalLevelCount;
    }

    public void setTotalLevelCount(Integer totalLevelCount) {
        this.totalLevelCount = totalLevelCount;
    }

    public CourseKnowledgeGraphStatisticsVo firstLevelCount(Integer firstLevelCount) {
        this.firstLevelCount = firstLevelCount;
        return this;
    }

    /**
     * 一级知识点数量
     *
     * @return firstLevelCount
     **/
    @ApiModelProperty(value = "一级知识点数量")


    public Integer getFirstLevelCount() {
        return firstLevelCount;
    }

    public void setFirstLevelCount(Integer firstLevelCount) {
        this.firstLevelCount = firstLevelCount;
    }

    public CourseKnowledgeGraphStatisticsVo secondLevelCount(Integer secondLevelCount) {
        this.secondLevelCount = secondLevelCount;
        return this;
    }

    /**
     * 二级知识点数量
     *
     * @return secondLevelCount
     **/
    @ApiModelProperty(value = "二级知识点数量")


    public Integer getSecondLevelCount() {
        return secondLevelCount;
    }

    public void setSecondLevelCount(Integer secondLevelCount) {
        this.secondLevelCount = secondLevelCount;
    }

    public CourseKnowledgeGraphStatisticsVo thirdLevelCount(Integer thirdLevelCount) {
        this.thirdLevelCount = thirdLevelCount;
        return this;
    }

    /**
     * 三级知识点数量
     *
     * @return thirdLevelCount
     **/
    @ApiModelProperty(value = "三级知识点数量")


    public Integer getThirdLevelCount() {
        return thirdLevelCount;
    }

    public void setThirdLevelCount(Integer thirdLevelCount) {
        this.thirdLevelCount = thirdLevelCount;
    }

    public CourseKnowledgeGraphStatisticsVo otherLevelCount(Integer otherLevelCount) {
        this.otherLevelCount = otherLevelCount;
        return this;
    }

    /**
     * 其他级知识点数量
     *
     * @return otherLevelCount
     **/
    @ApiModelProperty(value = "其他级知识点数量")


    public Integer getOtherLevelCount() {
        return otherLevelCount;
    }

    public void setOtherLevelCount(Integer otherLevelCount) {
        this.otherLevelCount = otherLevelCount;
    }

    public CourseKnowledgeGraphStatisticsVo videoFragmentCount(Integer videoFragmentCount) {
        this.videoFragmentCount = videoFragmentCount;
        return this;
    }

    /**
     * 智能推荐课堂实录的AI课堂切片总数
     *
     * @return videoFragmentCount
     **/
    @ApiModelProperty(value = "智能推荐课堂实录的AI课堂切片总数")


    public Integer getVideoFragmentCount() {
        return videoFragmentCount;
    }

    public void setVideoFragmentCount(Integer videoFragmentCount) {
        this.videoFragmentCount = videoFragmentCount;
    }

    public CourseKnowledgeGraphStatisticsVo imageResourceCount(Integer imageResourceCount) {
        this.imageResourceCount = imageResourceCount;
        return this;
    }

    /**
     * 图片资源总数
     *
     * @return imageResourceCount
     **/
    @ApiModelProperty(value = "图片资源总数")


    public Integer getImageResourceCount() {
        return imageResourceCount;
    }

    public void setImageResourceCount(Integer imageResourceCount) {
        this.imageResourceCount = imageResourceCount;
    }

    public CourseKnowledgeGraphStatisticsVo learnVideoResourceCount(Integer learnVideoResourceCount) {
        this.learnVideoResourceCount = learnVideoResourceCount;
        return this;
    }

    /**
     * 学习视频资源总数
     *
     * @return learnVideoResourceCount
     **/
    @ApiModelProperty(value = "学习视频资源总数")


    public Integer getLearnVideoResourceCount() {
        return learnVideoResourceCount;
    }

    public void setLearnVideoResourceCount(Integer learnVideoResourceCount) {
        this.learnVideoResourceCount = learnVideoResourceCount;
    }

    public CourseKnowledgeGraphStatisticsVo documentResourceCount(Integer documentResourceCount) {
        this.documentResourceCount = documentResourceCount;
        return this;
    }

    /**
     * 文档资源总数
     *
     * @return documentResourceCount
     **/
    @ApiModelProperty(value = "文档资源总数")


    public Integer getDocumentResourceCount() {
        return documentResourceCount;
    }

    public void setDocumentResourceCount(Integer documentResourceCount) {
        this.documentResourceCount = documentResourceCount;
    }

    public CourseKnowledgeGraphStatisticsVo classTestCount(Integer classTestCount) {
        this.classTestCount = classTestCount;
        return this;
    }

    /**
     * 考核测验数总数
     *
     * @return classTestCount
     **/
    @ApiModelProperty(value = "考核测验数总数")


    public Integer getClassTestCount() {
        return classTestCount;
    }

    public void setClassTestCount(Integer classTestCount) {
        this.classTestCount = classTestCount;
    }

    public CourseKnowledgeGraphStatisticsVo uploadQuestionCount(Integer uploadQuestionCount) {
        this.uploadQuestionCount = uploadQuestionCount;
        return this;
    }

    /**
     * 教师上传题目总数
     *
     * @return uploadQuestionCount
     **/
    @ApiModelProperty(value = "教师上传题目总数")


    public Integer getUploadQuestionCount() {
        return uploadQuestionCount;
    }

    public void setUploadQuestionCount(Integer uploadQuestionCount) {
        this.uploadQuestionCount = uploadQuestionCount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseKnowledgeGraphStatisticsVo courseKnowledgeGraphStatisticsVo = (CourseKnowledgeGraphStatisticsVo) o;
        return Objects.equals(this.totalLevelCount, courseKnowledgeGraphStatisticsVo.totalLevelCount) &&
                Objects.equals(this.firstLevelCount, courseKnowledgeGraphStatisticsVo.firstLevelCount) &&
                Objects.equals(this.secondLevelCount, courseKnowledgeGraphStatisticsVo.secondLevelCount) &&
                Objects.equals(this.thirdLevelCount, courseKnowledgeGraphStatisticsVo.thirdLevelCount) &&
                Objects.equals(this.otherLevelCount, courseKnowledgeGraphStatisticsVo.otherLevelCount) &&
                Objects.equals(this.videoFragmentCount, courseKnowledgeGraphStatisticsVo.videoFragmentCount) &&
                Objects.equals(this.imageResourceCount, courseKnowledgeGraphStatisticsVo.imageResourceCount) &&
                Objects.equals(this.learnVideoResourceCount, courseKnowledgeGraphStatisticsVo.learnVideoResourceCount) &&
                Objects.equals(this.documentResourceCount, courseKnowledgeGraphStatisticsVo.documentResourceCount) &&
                Objects.equals(this.classTestCount, courseKnowledgeGraphStatisticsVo.classTestCount) &&
                Objects.equals(this.uploadQuestionCount, courseKnowledgeGraphStatisticsVo.uploadQuestionCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalLevelCount, firstLevelCount, secondLevelCount, thirdLevelCount, otherLevelCount, videoFragmentCount, imageResourceCount, learnVideoResourceCount, documentResourceCount, classTestCount, uploadQuestionCount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseKnowledgeGraphStatisticsVo {\n");

        sb.append("    totalLevelCount: ").append(toIndentedString(totalLevelCount)).append("\n");
        sb.append("    firstLevelCount: ").append(toIndentedString(firstLevelCount)).append("\n");
        sb.append("    secondLevelCount: ").append(toIndentedString(secondLevelCount)).append("\n");
        sb.append("    thirdLevelCount: ").append(toIndentedString(thirdLevelCount)).append("\n");
        sb.append("    otherLevelCount: ").append(toIndentedString(otherLevelCount)).append("\n");
        sb.append("    videoFragmentCount: ").append(toIndentedString(videoFragmentCount)).append("\n");
        sb.append("    imageResourceCount: ").append(toIndentedString(imageResourceCount)).append("\n");
        sb.append("    learnVideoResourceCount: ").append(toIndentedString(learnVideoResourceCount)).append("\n");
        sb.append("    documentResourceCount: ").append(toIndentedString(documentResourceCount)).append("\n");
        sb.append("    classTestCount: ").append(toIndentedString(classTestCount)).append("\n");
        sb.append("    uploadQuestionCount: ").append(toIndentedString(uploadQuestionCount)).append("\n");
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

