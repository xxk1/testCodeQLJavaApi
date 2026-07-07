package com.lztech.site.viewmodel.courseknowledgegraph;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseKnowledgeGraphNodeVideoInfoTextModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-01-29T12:07:56.861Z")

public class CourseKnowledgeGraphNodeVideoInfoTextModel {

    @JsonProperty("id")
    private String id = null;
    public CourseKnowledgeGraphNodeVideoInfoTextModel id(String id) {
        this.id = id;
        return this;
    }
    /**
     * 唯一id
     * @return id
     **/
    @ApiModelProperty(value = "唯一id")
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("videoInfoId")
    private String videoInfoId = null;
    public CourseKnowledgeGraphNodeVideoInfoTextModel videoInfoId(String videoInfoId) {
        this.videoInfoId = videoInfoId;
        return this;
    }
    /**
     * 视频信息id
     * @return videoInfoId
     **/
    @ApiModelProperty(value = "视频信息id")
    public String getVideoInfoId() {
        return videoInfoId;
    }
    public void setVideoInfoId(String videoInfoId) {
        this.videoInfoId = videoInfoId;
    }

    private Integer textDataSourceIndex = null;
    public CourseKnowledgeGraphNodeVideoInfoTextModel textDataSourceIndex(Integer textDataSourceIndex) {
        this.textDataSourceIndex = textDataSourceIndex;
        return this;
    }
    /**
     * 文本数据来源编号(0:ppt识别;1:语言转写识别)
     * @return textDataSourceIndex
     **/
    @ApiModelProperty(value = "文本数据来源编号(0:ppt识别;1:语言转写识别)")
    public Integer getTextDataSourceIndex() {
        return textDataSourceIndex;
    }
    public void setTextDataSourceIndex(Integer textDataSourceIndex) {
        this.textDataSourceIndex = textDataSourceIndex;
    }

    private String textDataSourceName = null;
    public CourseKnowledgeGraphNodeVideoInfoTextModel textDataSourceName(String textDataSourceName) {
        this.textDataSourceName = textDataSourceName;
        return this;
    }
    /**
     * 文本数据来源名称(0:ppt识别;1:语言转写识别)
     * @return textDataSourceName
     **/
    @ApiModelProperty(value = "文本数据来源名称(0:ppt识别;1:语言转写识别)")
    public String getTextDataSourceName() {
        return textDataSourceName;
    }
    public void setTextDataSourceName(String textDataSourceName) {
        this.textDataSourceName = textDataSourceName;
    }

    @JsonProperty("schoolYear")
    private String schoolYear = null;
    public CourseKnowledgeGraphNodeVideoInfoTextModel schoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
        return this;
    }
    /**
     * 学年
     * @return schoolYear
     **/
    @ApiModelProperty(value = "学年")
    public String getSchoolYear() {
        return schoolYear;
    }
    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    @JsonProperty("term")
    private Integer term = null;
    public CourseKnowledgeGraphNodeVideoInfoTextModel term(Integer term) {
        this.term = term;
        return this;
    }
    /**
     * 学期
     * @return term
     **/
    @ApiModelProperty(value = "学期")
    public Integer getTerm() {
        return term;
    }
    public void setTerm(Integer schoolYear) {
        this.term = term;
    }

    @JsonProperty("teacherIds")
    private  String teacherIds = null;
    public CourseKnowledgeGraphNodeVideoInfoTextModel teacherIds(String teacherIds) {
        this.teacherIds = teacherIds;
        return this;
    }
    /**
     * 课表明细老师id(多个时","分割)
     * @return teacherIds
     **/
    @ApiModelProperty(value = "课表明细老师id(多个时\",\"分割)")
    public String getTeacherIds() {
        return teacherIds;
    }
    public void setTeacherIds(String teacherIds) {
        this.teacherIds = teacherIds;
    }

    @JsonProperty("teacherNos")
    private String teacherNos = null;
    public CourseKnowledgeGraphNodeVideoInfoTextModel teacherNos(String teacherNos) {
        this.teacherNos = teacherNos;
        return this;
    }
    /**
     * 课表明细老师工号(多个时","分割)
     * @return teacherNos
     **/
    @ApiModelProperty(value = "课表明细老师工号(多个时\",\"分割)")
    public String getTeacherNos() {
        return teacherNos;
    }
    public void setTeacherNos(String teacherNos) {
        this.teacherNos = teacherNos;
    }

    @JsonProperty("teacherNames")
    private String teacherNames = null;
    public CourseKnowledgeGraphNodeVideoInfoTextModel teacherNames(String teacherNames) {
        this.teacherNames = teacherNames;
        return this;
    }
    /**
     * 课表明细老师名称(多个时","分割)
     * @return teacherNames
     **/
    @ApiModelProperty(value = "课表明细老师名称(多个时\",\"分割)")
    public String getTeacherNames() {
        return teacherNames;
    }
    public void setTeacherNames(String teacherNames) {
        this.teacherNames = teacherNames;
    }

    @JsonProperty("videoDate")
    private String videoDate = null;
    public CourseKnowledgeGraphNodeVideoInfoTextModel videoDate(String videoDate) {
        this.videoDate = videoDate;
        return this;
    }
    /**
     * 视频日期
     * @return videoDate
     **/
    @ApiModelProperty(value = "视频日期")
    public String getVideoDate() {
        return videoDate;
    }
    public void setVideoDate(String videoDate) {
        this.videoDate = videoDate;
    }

    @JsonProperty("segment")
    private Integer segment = null;
    public CourseKnowledgeGraphNodeVideoInfoTextModel segment(Integer segment) {
        this.segment = segment;
        return this;
    }
    /**
     * 节次(第几节次)
     * @return segment
     **/
    @ApiModelProperty(value = "节次(第几节次)")
    public Integer getSegment() {
        return segment;
    }
    public void setSegment(Integer segment) {
        this.segment = segment;
    }

    @JsonProperty("innerIp")
    private String innerIp = null;
    public CourseKnowledgeGraphNodeVideoInfoTextModel innerIp(String innerIp) {
        this.innerIp = innerIp;
        return this;
    }
    /**
     * 内网ip
     * @return innerIp
     **/
    @ApiModelProperty(value = "内网ip")
    public String getInnerIp() {
        return innerIp;
    }
    public void setInnerIp(String innerIp) {
        this.innerIp = innerIp;
    }

    @JsonProperty("outerIp")
    private String outerIp = null;
    public CourseKnowledgeGraphNodeVideoInfoTextModel outerIp(String outerIp) {
        this.outerIp = outerIp;
        return this;
    }
    /**
     * 外网ip
     * @return outerIp
     **/
    @ApiModelProperty(value = "外网ip")
    public String getOuterIp() {
        return outerIp;
    }
    public void setOuterIp(String outerIp) {
        this.outerIp = outerIp;
    }

    @JsonProperty("thumbnailPath")
    private String thumbnailPath = null;
    public CourseKnowledgeGraphNodeVideoInfoTextModel thumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
        return this;
    }
    /**
     * 缩略图路径
     * @return thumbnailPath
     **/
    @ApiModelProperty(value = "缩略图路径")
    public String getThumbnailPath() {
        return thumbnailPath;
    }
    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    @JsonProperty("knowledgeNodeId")
    private String knowledgeNodeId = null;
    public CourseKnowledgeGraphNodeVideoInfoTextModel knowledgeNodeId(String knowledgeNodeId) {
        this.knowledgeNodeId = knowledgeNodeId;
        return this;
    }
    /**
     * 知识图谱知识点id
     * @return knowledgeNodeId
     **/
    @ApiModelProperty(value = "知识图谱知识点id")
    public String getKnowledgeNodeId() {
        return knowledgeNodeId;
    }
    public void setKnowledgeNodeId(String knowledgeNodeId) {
        this.knowledgeNodeId = knowledgeNodeId;
    }

    @JsonProperty("knowledgeNodeName")
    private String knowledgeNodeName = null;
    public CourseKnowledgeGraphNodeVideoInfoTextModel knowledgeNodeName(String knowledgeNodeName) {
        this.knowledgeNodeName = knowledgeNodeName;
        return this;
    }
    /**
     * 知识图谱知识点名称
     * @return knowledgeNodeName
     **/
    @ApiModelProperty(value = "知识图谱知识点名称")
    public String getKnowledgeNodeName() {
        return knowledgeNodeName;
    }
    public void setKnowledgeNodeName(String knowledgeNodeName) {
        this.knowledgeNodeName = knowledgeNodeName;
    }

    @JsonProperty("textContent")
    private String textContent = null;
    public CourseKnowledgeGraphNodeVideoInfoTextModel textContent(String textContent) {
        this.textContent = textContent;
        return this;
    }
    /**
     * 文本内容
     * @return textContent
     **/
    @ApiModelProperty(value = "文本内容")
    public String getTextContent() {
        return textContent;
    }
    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    @JsonProperty("highLightTextContent")
    private String highLightTextContent = null;
    public CourseKnowledgeGraphNodeVideoInfoTextModel highLightTextContent(String highLightTextContent) {
        this.highLightTextContent = highLightTextContent;
        return this;
    }
    /**
     * 高亮文本内容
     * @return highLightTextContent
     **/
    @ApiModelProperty(value = "高亮文本内容")
    public String getHighLightTextContent() {
        return highLightTextContent;
    }
    public void setHighLightTextContent(String highLightTextContent) {
        this.highLightTextContent = highLightTextContent;
    }



    @JsonProperty("startTimestamp")
    private Double startTimestamp = null;
    public CourseKnowledgeGraphNodeVideoInfoTextModel startTimestamp(Double startTimestamp) {
        this.startTimestamp = startTimestamp;
        return this;
    }
    /**
     * 开始时间戳(离视频开始相隔多少秒)
     * @return startTimestamp
     **/
    @ApiModelProperty(value = "开始时间戳(离视频开始相隔多少秒)")
    public Double getStartTimestamp() {
        return startTimestamp;
    }
    public void setStartTimestamp(Double startTimestamp) {
        this.startTimestamp = startTimestamp;
    }
    public Integer getIntegerStartTimestamp() {
        return startTimestamp.intValue();
    }
    @JsonProperty("endTimestamp")
    private Double endTimestamp = null;
    public CourseKnowledgeGraphNodeVideoInfoTextModel endTimestamp(Double endTimestamp) {
        this.endTimestamp = endTimestamp;
        return this;
    }
    /**
     * 结束时间戳(离视频开始相隔多少秒)
     * @return endTimestamp
     **/
    @ApiModelProperty(value = "结束时间戳(离视频开始相隔多少秒)")
    public Double getEndTimestamp() {
        return endTimestamp;
    }
    public void setEndTimestamp(Double endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    @JsonProperty("similarityScore")
    private Double similarityScore = null;
    public CourseKnowledgeGraphNodeVideoInfoTextModel similarityScore(Double similarityScore) {
        this.similarityScore = similarityScore;
        return this;
    }
    /**
     * 相似性分数
     * @return similarityScore
     **/
    @ApiModelProperty(value = "相似性分数")
    public Double getSimilarityScore() {
        return similarityScore;
    }
    public void setSimilarityScore(Double similarityScore) {
        this.similarityScore = similarityScore;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseKnowledgeGraphNodeVideoInfoTextModel courseKnowledgeGraphNodeVideoInfoTextModel =
                (CourseKnowledgeGraphNodeVideoInfoTextModel) o;
        return Objects.equals(id, courseKnowledgeGraphNodeVideoInfoTextModel.id) &&
                Objects.equals(videoInfoId, courseKnowledgeGraphNodeVideoInfoTextModel.videoInfoId) &&
                Objects.equals(textDataSourceIndex, courseKnowledgeGraphNodeVideoInfoTextModel.textDataSourceIndex) &&
                Objects.equals(textDataSourceName, courseKnowledgeGraphNodeVideoInfoTextModel.textDataSourceName) &&
                Objects.equals(schoolYear, courseKnowledgeGraphNodeVideoInfoTextModel.schoolYear) &&
                Objects.equals(term, courseKnowledgeGraphNodeVideoInfoTextModel.term) &&
                Objects.equals(teacherIds, courseKnowledgeGraphNodeVideoInfoTextModel.teacherIds) &&
                Objects.equals(teacherNos, courseKnowledgeGraphNodeVideoInfoTextModel.teacherNos) &&
                Objects.equals(teacherNames, courseKnowledgeGraphNodeVideoInfoTextModel.teacherNames) &&
                Objects.equals(videoDate, courseKnowledgeGraphNodeVideoInfoTextModel.videoDate) &&
                Objects.equals(segment, courseKnowledgeGraphNodeVideoInfoTextModel.segment) &&
                Objects.equals(innerIp, courseKnowledgeGraphNodeVideoInfoTextModel.innerIp) &&
                Objects.equals(outerIp, courseKnowledgeGraphNodeVideoInfoTextModel.outerIp) &&
                Objects.equals(thumbnailPath, courseKnowledgeGraphNodeVideoInfoTextModel.thumbnailPath) &&
                Objects.equals(knowledgeNodeId, courseKnowledgeGraphNodeVideoInfoTextModel.knowledgeNodeId) &&
                Objects.equals(knowledgeNodeName, courseKnowledgeGraphNodeVideoInfoTextModel.knowledgeNodeName) &&
                Objects.equals(textContent, courseKnowledgeGraphNodeVideoInfoTextModel.textContent) &&
                Objects.equals(highLightTextContent, courseKnowledgeGraphNodeVideoInfoTextModel.highLightTextContent) &&
                Objects.equals(startTimestamp, courseKnowledgeGraphNodeVideoInfoTextModel.startTimestamp) &&
                Objects.equals(endTimestamp, courseKnowledgeGraphNodeVideoInfoTextModel.endTimestamp) &&
                Objects.equals(similarityScore, courseKnowledgeGraphNodeVideoInfoTextModel.similarityScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,videoInfoId,textDataSourceIndex,textDataSourceName,
                schoolYear, term, teacherIds, teacherNos, teacherNames,
                videoDate, segment, innerIp, outerIp, thumbnailPath, knowledgeNodeId, knowledgeNodeName,
                textContent, highLightTextContent, startTimestamp, endTimestamp, similarityScore);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseKnowledgeGraphNodeVideoInfoTextModel {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    videoInfoId: ").append(toIndentedString(videoInfoId)).append("\n");
        sb.append("    textDataSourceIndex: ").append(toIndentedString(textDataSourceIndex)).append("\n");
        sb.append("    textDataSourceName: ").append(toIndentedString(textDataSourceName)).append("\n");
        sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
        sb.append("    term: ").append(toIndentedString(term)).append("\n");
        sb.append("    teacherIds: ").append(toIndentedString(teacherIds)).append("\n");
        sb.append("    teacherNos: ").append(toIndentedString(teacherNos)).append("\n");
        sb.append("    teacherNames: ").append(toIndentedString(teacherNames)).append("\n");
        sb.append("    videoDate: ").append(toIndentedString(videoDate)).append("\n");
        sb.append("    segment: ").append(toIndentedString(segment)).append("\n");
        sb.append("    innerIp: ").append(toIndentedString(innerIp)).append("\n");
        sb.append("    outerIp: ").append(toIndentedString(outerIp)).append("\n");
        sb.append("    thumbnailPath: ").append(toIndentedString(thumbnailPath)).append("\n");
        sb.append("    knowledgeNodeId: ").append(toIndentedString(knowledgeNodeId)).append("\n");
        sb.append("    knowledgeNodeName: ").append(toIndentedString(knowledgeNodeName)).append("\n");
        sb.append("    textContent: ").append(toIndentedString(textContent)).append("\n");
        sb.append("    highLightTextContent: ").append(toIndentedString(highLightTextContent)).append("\n");
        sb.append("    startTimestamp: ").append(toIndentedString(startTimestamp)).append("\n");
        sb.append("    endTimestamp: ").append(toIndentedString(endTimestamp)).append("\n");
        sb.append("    similarityScore: ").append(toIndentedString(similarityScore)).append("\n");
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
