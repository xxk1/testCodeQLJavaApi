package com.lztech.site.viewmodel.aiknowledgebase;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * AIKnowledgeBaseVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-04-28T03:29:48.199Z")

public class AIKnowledgeBaseVo {

    @JsonProperty("id")
    private String id = null;

    @JsonProperty("resourceName")
    private String resourceName = null;

    @JsonProperty("aiKnowledgeBaseTypeIndex")
    private Integer aiKnowledgeBaseTypeIndex = null;

    @JsonProperty("aiKnowledgeBaseTypeName")
    private String aiKnowledgeBaseTypeName = null;

    @JsonProperty("innerIp")
    private String innerIp = null;

    @JsonProperty("outerIp")
    private String outerIp = null;

    @JsonProperty("filePath")
    private String filePath = null;

    @JsonProperty("fileType")
    private String fileType = null;

    @JsonProperty("sourceType")
    private Integer sourceType = null;

    @JsonProperty("creatorId")
    private String creatorId = null;

    @JsonProperty("creatorName")
    private String creatorName = null;

    @JsonProperty("createTime")
    private String createTime = null;

    public AIKnowledgeBaseVo id(String id) {
        this.id = id;
        return this;
    }

    /**
     * id
     *
     * @return id
     **/
    @ApiModelProperty(value = "id")

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AIKnowledgeBaseVo resourceName(String resourceName) {
        this.resourceName = resourceName;
        return this;
    }

    /**
     * 文件名称
     *
     * @return resourceName
     **/
    @ApiModelProperty(value = "文件名称")

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public AIKnowledgeBaseVo aiKnowledgeBaseTypeIndex(Integer aiKnowledgeBaseTypeIndex) {
        this.aiKnowledgeBaseTypeIndex = aiKnowledgeBaseTypeIndex;
        return this;
    }

    /**
     * AI知识库类型编号(1:课程教材;2:课件资源;)
     *
     * @return aiKnowledgeBaseTypeIndex
     **/
    @ApiModelProperty(value = "AI知识库类型编号(0:课程教材;1:课件资源;)")

    public Integer getAiKnowledgeBaseTypeIndex() {
        return aiKnowledgeBaseTypeIndex;
    }

    public void setAiKnowledgeBaseTypeIndex(Integer aiKnowledgeBaseTypeIndex) {
        this.aiKnowledgeBaseTypeIndex = aiKnowledgeBaseTypeIndex;
    }

    public AIKnowledgeBaseVo aiKnowledgeBaseTypeIndex(String aiKnowledgeBaseTypeName) {
        this.aiKnowledgeBaseTypeName = aiKnowledgeBaseTypeName;
        return this;
    }

    /**
     * AI知识库类型名称(0:课程教材;1:课件资源;)
     *
     * @return aiKnowledgeBaseTypeName
     **/
    @ApiModelProperty(value = "AI知识库类型名称(0:课程教材;1:课件资源;)")

    public String getAiKnowledgeBaseTypeName() {
        return aiKnowledgeBaseTypeName;
    }

    public void setAiKnowledgeBaseTypeName(String aiKnowledgeBaseTypeName) {
        this.aiKnowledgeBaseTypeName = aiKnowledgeBaseTypeName;
    }

    public AIKnowledgeBaseVo innerIp(String innerIp) {
        this.innerIp = innerIp;
        return this;
    }

    /**
     * 内网ip
     *
     * @return innerIp
     **/
    @ApiModelProperty(value = "内网ip")

    public String getInnerIp() {
        return innerIp;
    }

    public void setInnerIp(String innerIp) {
        this.innerIp = innerIp;
    }

    public AIKnowledgeBaseVo outerIp(String outerIp) {
        this.outerIp = outerIp;
        return this;
    }

    /**
     * 外网ip
     *
     * @return outerIp
     **/
    @ApiModelProperty(value = "外网ip")

    public String getOuterIp() {
        return outerIp;
    }

    public void setOuterIp(String outerIp) {
        this.outerIp = outerIp;
    }

    public AIKnowledgeBaseVo filePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    /**
     * 文件路径
     *
     * @return filePath
     **/
    @ApiModelProperty(value = "文件路径")

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public AIKnowledgeBaseVo fileType(String fileType) {
        this.fileType = fileType;
        return this;
    }

    /**
     * 文件类型
     *
     * @return fileType
     **/
    @ApiModelProperty(value = "文件类型")

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public AIKnowledgeBaseVo sourceType(Integer sourceType) {
        this.sourceType = sourceType;
        return this;
    }

    /**
     * 课程来源类型 (0:个人;1:公共)
     *
     * @return sourceType
     **/
    @ApiModelProperty(value = "课程来源类型 (0:个人;1:公共)")

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public AIKnowledgeBaseVo creatorId(String creatorId) {
        this.creatorId = creatorId;
        return this;
    }

    /**
     * 操作人Id
     *
     * @return creatorId
     **/
    @ApiModelProperty(value = "操作人Id")

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public AIKnowledgeBaseVo creatorName(String creatorName) {
        this.creatorName = creatorName;
        return this;
    }

    /**
     * 操作人姓名
     *
     * @return creatorName
     **/
    @ApiModelProperty(value = "操作人姓名")

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public AIKnowledgeBaseVo createTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    /**
     * 创建时间
     *
     * @return createTime
     **/
    @ApiModelProperty(value = "操作人姓名")

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AIKnowledgeBaseVo)) {
            return false;
        }
        AIKnowledgeBaseVo that = (AIKnowledgeBaseVo) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(resourceName, that.resourceName) &&
                Objects.equals(aiKnowledgeBaseTypeIndex, that.aiKnowledgeBaseTypeIndex) &&
                Objects.equals(aiKnowledgeBaseTypeName, that.aiKnowledgeBaseTypeName) &&
                Objects.equals(innerIp, that.innerIp) &&
                Objects.equals(outerIp, that.outerIp) &&
                Objects.equals(filePath, that.filePath) &&
                Objects.equals(fileType, that.fileType) &&
                Objects.equals(sourceType, that.sourceType) &&
                Objects.equals(creatorId, that.creatorId) &&
                Objects.equals(creatorName, that.creatorName) &&
                Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, resourceName, aiKnowledgeBaseTypeIndex, aiKnowledgeBaseTypeName,
                innerIp, outerIp, filePath, fileType, sourceType, creatorId, creatorName, createTime);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AIKnowledgeBaseVo{");
        sb.append("id='").append(id).append('\'');
        sb.append(", resourceName='").append(resourceName).append('\'');
        sb.append(", aiKnowledgeBaseTypeIndex=").append(aiKnowledgeBaseTypeIndex);
        sb.append(", aiKnowledgeBaseTypeName='").append(aiKnowledgeBaseTypeName).append('\'');
        sb.append(", innerIp='").append(innerIp).append('\'');
        sb.append(", outerIp='").append(outerIp).append('\'');
        sb.append(", filePath='").append(filePath).append('\'');
        sb.append(", fileType='").append(fileType).append('\'');
        sb.append(", sourceType=").append(sourceType);
        sb.append(", creatorId='").append(creatorId).append('\'');
        sb.append(", creatorName='").append(creatorName).append('\'');
        sb.append(", createTime='").append(createTime).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
