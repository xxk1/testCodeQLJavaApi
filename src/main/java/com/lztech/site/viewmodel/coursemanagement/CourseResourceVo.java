package com.lztech.site.viewmodel.coursemanagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseResourceVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-06-27T13:56:31.631+08:00")

public class CourseResourceVo {
    @JsonProperty("resourceId")
    private String resourceId = null;

    @JsonProperty("resourceName")
    private String resourceName = null;

    @JsonProperty("resourceType")
    private Integer resourceType = null;

    @JsonProperty("resourceSize")
    private String resourceSize = null;

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

    @JsonProperty("resourceOtherReferences")
    private Integer resourceOtherReferences = null;

    @JsonProperty("resourceNum")
    private Integer resourceNum = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("creatorId")
    private String creatorId = null;

    @JsonProperty("sourceType")
    private Integer sourceType = null;

    @JsonProperty("transcodeStatus")
    private Integer transcodeStatus = null;

    @JsonProperty("knowledgePointList")
    @Valid
    private List<KnowledgePointResource> knowledgePointList = null;

    @JsonProperty("initialCreatorId")
    private String initialCreatorId = null;

    @JsonProperty("initialCreatorName")
    private String initialCreatorName = null;

    @JsonProperty("aiIdentifyStatus")
    private Integer aiIdentifyStatus = null;



    public CourseResourceVo resourceId(String resourceId) {
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

    public CourseResourceVo resourceName(String resourceName) {
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

    public CourseResourceVo resourceType(Integer resourceType) {
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

    public CourseResourceVo resourceSize(String resourceSize) {
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

    public CourseResourceVo modifyTime(String modifyTime) {
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

    public CourseResourceVo modifierName(String modifierName) {
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

    public CourseResourceVo modifierId(String modifierId) {
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

    public CourseResourceVo innerIp(String innerIp) {
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

    public CourseResourceVo outerIp(String outerIp) {
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

    public CourseResourceVo filePath(String filePath) {
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

    public CourseResourceVo fileType(String fileType) {
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

    public CourseResourceVo isPublic(Integer isPublic) {
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

    public CourseResourceVo questionNum(Integer questionNum) {
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

    public CourseResourceVo resourceDetailId(String resourceDetailId) {
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

    public CourseResourceVo fileSavedName(String fileSavedName) {
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

    public CourseResourceVo fileSize(String fileSize) {
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

    public CourseResourceVo resourceReferences(Integer resourceReferences) {
        this.resourceReferences = resourceReferences;
        return this;
    }

    /**
     * 资源引用次数(个人引用次数)
     *
     * @return resourceReferences
     **/
    @ApiModelProperty(value = "资源引用次数(个人引用次数)")


    public Integer getResourceReferences() {
        return resourceReferences;
    }

    public void setResourceReferences(Integer resourceReferences) {
        this.resourceReferences = resourceReferences;
    }

    public CourseResourceVo resourceOtherReferences(Integer resourceOtherReferences) {
        this.resourceOtherReferences = resourceOtherReferences;
        return this;
    }

    /**
     * 资源引用次数(他人引用次数)
     *
     * @return resourceOtherReferences
     **/
    @ApiModelProperty(value = "资源引用次数(他人引用次数)")


    public Integer getResourceOtherReferences() {
        return resourceOtherReferences;
    }

    public void setResourceOtherReferences(Integer resourceOtherReferences) {
        this.resourceOtherReferences = resourceOtherReferences;
    }

    public CourseResourceVo resourceNum(Integer resourceNum) {
        this.resourceNum = resourceNum;
        return this;
    }

    /**
     * 资源内容数量
     *
     * @return resourceNum
     **/
    @ApiModelProperty(value = "资源内容数量")


    public Integer getResourceNum() {
        return resourceNum;
    }

    public void setResourceNum(Integer resourceNum) {
        this.resourceNum = resourceNum;
    }

    public CourseResourceVo courseId(String courseId) {
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

    public CourseResourceVo creatorId(String creatorId) {
        this.creatorId = creatorId;
        return this;
    }

    /**
     * 创建人id
     *
     * @return creatorId
     **/
    @ApiModelProperty(value = "创建人id")


    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public CourseResourceVo sourceType(Integer sourceType) {
        this.sourceType = sourceType;
        return this;
    }

    /**
     * 资源来源类型
     *
     * @return sourceType
     **/
    @ApiModelProperty(value = "资源来源类型")


    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public CourseResourceVo transcodeStatus(Integer transcodeStatus) {
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

    public CourseResourceVo knowledgePointList(List<KnowledgePointResource> knowledgePointList) {
        this.knowledgePointList = knowledgePointList;
        return this;
    }

    public CourseResourceVo addKnowledgePointListItem(KnowledgePointResource knowledgePointListItem) {
        if (this.knowledgePointList == null) {
            this.knowledgePointList = new ArrayList<KnowledgePointResource>();
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

    public List<KnowledgePointResource> getKnowledgePointList() {
        return knowledgePointList;
    }

    public void setKnowledgePointList(List<KnowledgePointResource> knowledgePointList) {
        this.knowledgePointList = knowledgePointList;
    }

    public CourseResourceVo initialCreatorId(String initialCreatorId) {
        this.initialCreatorId = initialCreatorId;
        return this;
    }

    /**
     * 初始创建人id
     *
     * @return initialCreatorId
     **/
    @ApiModelProperty(value = "初始创建人id")


    public String getInitialCreatorId() {
        return initialCreatorId;
    }

    public void setInitialCreatorId(String initialCreatorId) {
        this.initialCreatorId = initialCreatorId;
    }

    public CourseResourceVo initialCreatorName(String initialCreatorName) {
        this.initialCreatorName = initialCreatorName;
        return this;
    }

    /**
     * 初始创建人姓名
     *
     * @return initialCreatorName
     **/
    @ApiModelProperty(value = "初始创建人姓名")


    public String getInitialCreatorName() {
        return initialCreatorName;
    }

    public void setInitialCreatorName(String initialCreatorName) {
        this.initialCreatorName = initialCreatorName;
    }

    public CourseResourceVo aiIdentifyStatus(Integer aiIdentifyStatus) {
        this.aiIdentifyStatus = aiIdentifyStatus;
        return this;
    }
    /**
     * AI识别状态（0:未识别,1:识别中,2:识别成功,3:识别失败)
     *
     * @return aiIdentifyStatus
     **/
    @ApiModelProperty(value = "AI识别状态（0:未识别,1:识别中,2:识别成功,3:识别失败)")

    public Integer getAiIdentifyStatus() {
        return aiIdentifyStatus;
    }

    public void setAiIdentifyStatus(Integer aiIdentifyStatus) {
        this.aiIdentifyStatus = aiIdentifyStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseResourceVo courseResourceVo = (CourseResourceVo) o;
        return Objects.equals(this.resourceId, courseResourceVo.resourceId) &&
                Objects.equals(this.resourceName, courseResourceVo.resourceName) &&
                Objects.equals(this.resourceType, courseResourceVo.resourceType) &&
                Objects.equals(this.resourceSize, courseResourceVo.resourceSize) &&
                Objects.equals(this.modifyTime, courseResourceVo.modifyTime) &&
                Objects.equals(this.modifierName, courseResourceVo.modifierName) &&
                Objects.equals(this.modifierId, courseResourceVo.modifierId) &&
                Objects.equals(this.innerIp, courseResourceVo.innerIp) &&
                Objects.equals(this.outerIp, courseResourceVo.outerIp) &&
                Objects.equals(this.filePath, courseResourceVo.filePath) &&
                Objects.equals(this.fileType, courseResourceVo.fileType) &&
                Objects.equals(this.isPublic, courseResourceVo.isPublic) &&
                Objects.equals(this.questionNum, courseResourceVo.questionNum) &&
                Objects.equals(this.resourceDetailId, courseResourceVo.resourceDetailId) &&
                Objects.equals(this.fileSavedName, courseResourceVo.fileSavedName) &&
                Objects.equals(this.fileSize, courseResourceVo.fileSize) &&
                Objects.equals(this.resourceReferences, courseResourceVo.resourceReferences) &&
                Objects.equals(this.resourceOtherReferences, courseResourceVo.resourceOtherReferences) &&
                Objects.equals(this.resourceNum, courseResourceVo.resourceNum) &&
                Objects.equals(this.courseId, courseResourceVo.courseId) &&
                Objects.equals(this.creatorId, courseResourceVo.creatorId) &&
                Objects.equals(this.sourceType, courseResourceVo.sourceType) &&
                Objects.equals(this.transcodeStatus, courseResourceVo.transcodeStatus) &&
                Objects.equals(this.knowledgePointList, courseResourceVo.knowledgePointList) &&
                Objects.equals(this.initialCreatorId, courseResourceVo.initialCreatorId) &&
                Objects.equals(this.initialCreatorName, courseResourceVo.initialCreatorName) &&
                Objects.equals(this.aiIdentifyStatus, courseResourceVo.aiIdentifyStatus) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(resourceId, resourceName, resourceType, resourceSize, modifyTime, modifierName, modifierId,
                innerIp, outerIp, filePath, fileType, isPublic, questionNum, resourceDetailId, fileSavedName, fileSize,
                resourceReferences, resourceOtherReferences, resourceNum, courseId, creatorId, sourceType, transcodeStatus,
                knowledgePointList, initialCreatorId, initialCreatorName,aiIdentifyStatus);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseResourceVo {\n");

        sb.append("    resourceId: ").append(toIndentedString(resourceId)).append("\n");
        sb.append("    resourceName: ").append(toIndentedString(resourceName)).append("\n");
        sb.append("    resourceType: ").append(toIndentedString(resourceType)).append("\n");
        sb.append("    resourceSize: ").append(toIndentedString(resourceSize)).append("\n");
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
        sb.append("    resourceOtherReferences: ").append(toIndentedString(resourceOtherReferences)).append("\n");
        sb.append("    resourceNum: ").append(toIndentedString(resourceNum)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    creatorId: ").append(toIndentedString(creatorId)).append("\n");
        sb.append("    sourceType: ").append(toIndentedString(sourceType)).append("\n");
        sb.append("    transcodeStatus: ").append(toIndentedString(transcodeStatus)).append("\n");
        sb.append("    knowledgePointList: ").append(toIndentedString(knowledgePointList)).append("\n");
        sb.append("    initialCreatorId: ").append(toIndentedString(initialCreatorId)).append("\n");
        sb.append("    initialCreatorName: ").append(toIndentedString(initialCreatorName)).append("\n");
        sb.append("    aiIdentifyStatus: ").append(toIndentedString(aiIdentifyStatus)).append("\n");
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

