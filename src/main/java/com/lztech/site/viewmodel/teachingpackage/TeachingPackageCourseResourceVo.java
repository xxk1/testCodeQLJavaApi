package com.lztech.site.viewmodel.teachingpackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * TeachingPackageCourseResourceVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-04-06T06:17:59.145Z")


public class TeachingPackageCourseResourceVo {
    @JsonProperty("isQuote")
    private Boolean isQuote = null;

    @JsonProperty("resourceId")
    private String resourceId = null;

    @JsonProperty("resourceName")
    private String resourceName = null;

    @JsonProperty("resourceType")
    private Integer resourceType = null;

    @JsonProperty("resourceSize")
    private String resourceSize = null;

    @JsonProperty("resourceNum")
    private String resourceNum = null;

    @JsonProperty("modifyTime")
    private String modifyTime = null;

    @JsonProperty("modifierName")
    private String modifierName = null;

    @JsonProperty("modifierId")
    private String modifierId = null;

    @JsonProperty("innerIp")
    private String innerIp = null;

    @JsonProperty("outerIp")
    private String outerIp = null;

    @JsonProperty("filePath")
    private String filePath = null;

    @JsonProperty("fileType")
    private String fileType = null;

    @JsonProperty("isPublic")
    private Integer isPublic = null;

    @JsonProperty("questionNum")
    private Integer questionNum = null;

    @JsonProperty("resourceDetailId")
    private String resourceDetailId = null;

    @JsonProperty("fileSavedName")
    private String fileSavedName = null;

    @JsonProperty("fileSize")
    private String fileSize = null;

    @JsonProperty("resourceReferences")
    private Integer resourceReferences = null;

    @JsonProperty("transcodeStatus")
    private Integer transcodeStatus = null;

    @JsonProperty("knowledgePointList")
    @Valid
    private List<PackageKnowledgePointResource> knowledgePointList = null;

    public TeachingPackageCourseResourceVo isQuote(Boolean isQuote) {
        this.isQuote = isQuote;
        return this;
    }

    /**
     * 资源是否被引用
     *
     * @return isQuote
     **/
    @ApiModelProperty(value = "资源是否被引用")


    public Boolean isIsQuote() {
        return isQuote;
    }

    public void setIsQuote(Boolean isQuote) {
        this.isQuote = isQuote;
    }

    public TeachingPackageCourseResourceVo resourceId(String resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    /**
     * 资源id
     *
     * @return resourceId
     **/
    @ApiModelProperty(value = "资源id")


    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public TeachingPackageCourseResourceVo resourceName(String resourceName) {
        this.resourceName = resourceName;
        return this;
    }

    /**
     * 资源名称
     *
     * @return resourceName
     **/
    @ApiModelProperty(value = "资源名称")


    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public TeachingPackageCourseResourceVo resourceType(Integer resourceType) {
        this.resourceType = resourceType;
        return this;
    }

    /**
     * 资源类型(0-课件 1-视频 2-测验)
     *
     * @return resourceType
     **/
    @ApiModelProperty(value = "资源类型(0-课件 1-视频 2-测验)")


    public Integer getResourceType() {
        return resourceType;
    }

    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    public TeachingPackageCourseResourceVo resourceSize(String resourceSize) {
        this.resourceSize = resourceSize;
        return this;
    }

    /**
     * 资源大小
     *
     * @return resourceSize
     **/
    @ApiModelProperty(value = "资源大小")


    public String getResourceSize() {
        return resourceSize;
    }

    public void setResourceSize(String resourceSize) {
        this.resourceSize = resourceSize;
    }

    public TeachingPackageCourseResourceVo resourceNum(String resourceNum) {
        this.resourceNum = resourceNum;
        return this;
    }

    /**
     * 问题数量
     *
     * @return resourceNum
     **/
    @ApiModelProperty(value = "问题数量")


    public String getResourceNum() {
        return resourceNum;
    }

    public void setResourceNum(String resourceNum) {
        this.resourceNum = resourceNum;
    }

    public TeachingPackageCourseResourceVo modifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

    /**
     * 更新时间
     *
     * @return modifyTime
     **/
    @ApiModelProperty(value = "更新时间")


    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public TeachingPackageCourseResourceVo modifierName(String modifierName) {
        this.modifierName = modifierName;
        return this;
    }

    /**
     * 更新人名称
     *
     * @return modifierName
     **/
    @ApiModelProperty(value = "更新人名称")


    public String getModifierName() {
        return modifierName;
    }

    public void setModifierName(String modifierName) {
        this.modifierName = modifierName;
    }

    public TeachingPackageCourseResourceVo modifierId(String modifierId) {
        this.modifierId = modifierId;
        return this;
    }

    /**
     * 更新人id
     *
     * @return modifierId
     **/
    @ApiModelProperty(value = "更新人id")


    public String getModifierId() {
        return modifierId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }

    public TeachingPackageCourseResourceVo innerIp(String innerIp) {
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

    public TeachingPackageCourseResourceVo outerIp(String outerIp) {
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

    public TeachingPackageCourseResourceVo filePath(String filePath) {
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

    public TeachingPackageCourseResourceVo fileType(String fileType) {
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

    public TeachingPackageCourseResourceVo isPublic(Integer isPublic) {
        this.isPublic = isPublic;
        return this;
    }

    /**
     * 是否公开 0 不公开 1公开
     *
     * @return isPublic
     **/
    @ApiModelProperty(value = "是否公开 0 不公开 1公开")


    public Integer getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }

    public TeachingPackageCourseResourceVo questionNum(Integer questionNum) {
        this.questionNum = questionNum;
        return this;
    }

    /**
     * 题目数量
     *
     * @return questionNum
     **/
    @ApiModelProperty(value = "题目数量")


    public Integer getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(Integer questionNum) {
        this.questionNum = questionNum;
    }

    public TeachingPackageCourseResourceVo resourceDetailId(String resourceDetailId) {
        this.resourceDetailId = resourceDetailId;
        return this;
    }

    /**
     * 资源详情id
     *
     * @return resourceDetailId
     **/
    @ApiModelProperty(value = "资源详情id")


    public String getResourceDetailId() {
        return resourceDetailId;
    }

    public void setResourceDetailId(String resourceDetailId) {
        this.resourceDetailId = resourceDetailId;
    }

    public TeachingPackageCourseResourceVo fileSavedName(String fileSavedName) {
        this.fileSavedName = fileSavedName;
        return this;
    }

    /**
     * 资源名称(上传名称)
     *
     * @return fileSavedName
     **/
    @ApiModelProperty(value = "资源名称(上传名称)")


    public String getFileSavedName() {
        return fileSavedName;
    }

    public void setFileSavedName(String fileSavedName) {
        this.fileSavedName = fileSavedName;
    }

    public TeachingPackageCourseResourceVo fileSize(String fileSize) {
        this.fileSize = fileSize;
        return this;
    }

    /**
     * 资源大小(上传)
     *
     * @return fileSize
     **/
    @ApiModelProperty(value = "资源大小(上传)")


    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public TeachingPackageCourseResourceVo resourceReferences(Integer resourceReferences) {
        this.resourceReferences = resourceReferences;
        return this;
    }

    /**
     * 资源引用次数
     *
     * @return resourceReferences
     **/
    @ApiModelProperty(value = "资源引用次数")


    public Integer getResourceReferences() {
        return resourceReferences;
    }

    public void setResourceReferences(Integer resourceReferences) {
        this.resourceReferences = resourceReferences;
    }

    public TeachingPackageCourseResourceVo transcodeStatus(Integer transcodeStatus) {
        this.transcodeStatus = transcodeStatus;
        return this;
    }

    /**
     * 转码状态：0:不转码;1:转码中;2:转码成功;3:转码失败
     *
     * @return transcodeStatus
     **/
    @ApiModelProperty(value = "转码状态：0:不转码;1:转码中;2:转码成功;3:转码失败")


    public Integer getTranscodeStatus() {
        return transcodeStatus;
    }

    public void setTranscodeStatus(Integer transcodeStatus) {
        this.transcodeStatus = transcodeStatus;
    }

    public TeachingPackageCourseResourceVo knowledgePointList(List<PackageKnowledgePointResource> knowledgePointList) {
        this.knowledgePointList = knowledgePointList;
        return this;
    }

    public TeachingPackageCourseResourceVo addKnowledgePointListItem(PackageKnowledgePointResource knowledgePointListItem) {
        if (this.knowledgePointList == null) {
            this.knowledgePointList = new ArrayList<PackageKnowledgePointResource>();
        }
        this.knowledgePointList.add(knowledgePointListItem);
        return this;
    }

    /**
     * 知识点集合
     *
     * @return knowledgePointList
     **/
    @ApiModelProperty(value = "知识点集合")

    @Valid

    public List<PackageKnowledgePointResource> getKnowledgePointList() {
        return knowledgePointList;
    }

    public void setKnowledgePointList(List<PackageKnowledgePointResource> knowledgePointList) {
        this.knowledgePointList = knowledgePointList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeachingPackageCourseResourceVo teachingPackageCourseResourceVo = (TeachingPackageCourseResourceVo) o;
        return Objects.equals(this.isQuote, teachingPackageCourseResourceVo.isQuote) &&
                Objects.equals(this.resourceId, teachingPackageCourseResourceVo.resourceId) &&
                Objects.equals(this.resourceName, teachingPackageCourseResourceVo.resourceName) &&
                Objects.equals(this.resourceType, teachingPackageCourseResourceVo.resourceType) &&
                Objects.equals(this.resourceSize, teachingPackageCourseResourceVo.resourceSize) &&
                Objects.equals(this.resourceNum, teachingPackageCourseResourceVo.resourceNum) &&
                Objects.equals(this.modifyTime, teachingPackageCourseResourceVo.modifyTime) &&
                Objects.equals(this.modifierName, teachingPackageCourseResourceVo.modifierName) &&
                Objects.equals(this.modifierId, teachingPackageCourseResourceVo.modifierId) &&
                Objects.equals(this.innerIp, teachingPackageCourseResourceVo.innerIp) &&
                Objects.equals(this.outerIp, teachingPackageCourseResourceVo.outerIp) &&
                Objects.equals(this.filePath, teachingPackageCourseResourceVo.filePath) &&
                Objects.equals(this.fileType, teachingPackageCourseResourceVo.fileType) &&
                Objects.equals(this.isPublic, teachingPackageCourseResourceVo.isPublic) &&
                Objects.equals(this.questionNum, teachingPackageCourseResourceVo.questionNum) &&
                Objects.equals(this.resourceDetailId, teachingPackageCourseResourceVo.resourceDetailId) &&
                Objects.equals(this.fileSavedName, teachingPackageCourseResourceVo.fileSavedName) &&
                Objects.equals(this.fileSize, teachingPackageCourseResourceVo.fileSize) &&
                Objects.equals(this.resourceReferences, teachingPackageCourseResourceVo.resourceReferences) &&
                Objects.equals(this.transcodeStatus, teachingPackageCourseResourceVo.transcodeStatus) &&
                Objects.equals(this.knowledgePointList, teachingPackageCourseResourceVo.knowledgePointList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isQuote, resourceId, resourceName, resourceType, resourceSize, resourceNum, modifyTime, modifierName,
                modifierId, innerIp, outerIp, filePath, fileType, isPublic, questionNum, resourceDetailId, fileSavedName,
                fileSize, resourceReferences, transcodeStatus, knowledgePointList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TeachingPackageCourseResourceVo {\n");

        sb.append("    isQuote: ").append(toIndentedString(isQuote)).append("\n");
        sb.append("    resourceId: ").append(toIndentedString(resourceId)).append("\n");
        sb.append("    resourceName: ").append(toIndentedString(resourceName)).append("\n");
        sb.append("    resourceType: ").append(toIndentedString(resourceType)).append("\n");
        sb.append("    resourceSize: ").append(toIndentedString(resourceSize)).append("\n");
        sb.append("    resourceNum: ").append(toIndentedString(resourceNum)).append("\n");
        sb.append("    modifyTime: ").append(toIndentedString(modifyTime)).append("\n");
        sb.append("    modifierName: ").append(toIndentedString(modifierName)).append("\n");
        sb.append("    modifierId: ").append(toIndentedString(modifierId)).append("\n");
        sb.append("    innerIp: ").append(toIndentedString(innerIp)).append("\n");
        sb.append("    outerIp: ").append(toIndentedString(outerIp)).append("\n");
        sb.append("    filePath: ").append(toIndentedString(filePath)).append("\n");
        sb.append("    fileType: ").append(toIndentedString(fileType)).append("\n");
        sb.append("    isPublic: ").append(toIndentedString(isPublic)).append("\n");
        sb.append("    questionNum: ").append(toIndentedString(questionNum)).append("\n");
        sb.append("    resourceDetailId: ").append(toIndentedString(resourceDetailId)).append("\n");
        sb.append("    fileSavedName: ").append(toIndentedString(fileSavedName)).append("\n");
        sb.append("    fileSize: ").append(toIndentedString(fileSize)).append("\n");
        sb.append("    resourceReferences: ").append(toIndentedString(resourceReferences)).append("\n");
        sb.append("    transcodeStatus: ").append(toIndentedString(transcodeStatus)).append("\n");
        sb.append("    knowledgePointList: ").append(toIndentedString(knowledgePointList)).append("\n");
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

