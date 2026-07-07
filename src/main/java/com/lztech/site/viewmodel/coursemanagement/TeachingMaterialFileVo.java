package com.lztech.site.viewmodel.coursemanagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * TeachingMaterialFileVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2026-01-05T11:50:25.471+08:00")

public class TeachingMaterialFileVo {
    @JsonProperty("fileId")
    private String fileId = null;

    @JsonProperty("fileDisplayName")
    private String fileDisplayName = null;

    @JsonProperty("fileRealName")
    private String fileRealName = null;

    @JsonProperty("fileType")
    private String fileType = null;

    @JsonProperty("innerUrl")
    private String innerUrl = null;

    @JsonProperty("outerUrl")
    private String outerUrl = null;

    @JsonProperty("filePath")
    private String filePath = null;

    @JsonProperty("fileSize")
    private String fileSize = null;

    @JsonProperty("courseMaterialId")
    private String courseMaterialId = null;

    @JsonProperty("dataOperationType")
    private Integer dataOperationType = null;

    @JsonProperty("creatorId")
    private String creatorId = null;

    @JsonProperty("creatorName")
    private String creatorName = null;

    @JsonProperty("createTime")
    private String createTime = null;

    @JsonProperty("coverFileDisplayName")
    private String coverFileDisplayName = null;

    @JsonProperty("coverFileRealName")
    private String coverFileRealName = null;

    @JsonProperty("coverFileType")
    private String coverFileType = null;

    @JsonProperty("coverInnerUrl")
    private String coverInnerUrl = null;

    @JsonProperty("coverOuterUrl")
    private String coverOuterUrl = null;

    @JsonProperty("coverFilePath")
    private String coverFilePath = null;

    @JsonProperty("coverFileSize")
    private String coverFileSize = null;

    public TeachingMaterialFileVo fileId(String fileId) {
        this.fileId = fileId;
        return this;
    }

    /**
     * 文件id
     *
     * @return fileId
     **/
    @ApiModelProperty(value = "文件id")


    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public TeachingMaterialFileVo fileDisplayName(String fileDisplayName) {
        this.fileDisplayName = fileDisplayName;
        return this;
    }

    /**
     * 文件显示名称
     *
     * @return fileDisplayName
     **/
    @ApiModelProperty(value = "文件显示名称")


    public String getFileDisplayName() {
        return fileDisplayName;
    }

    public void setFileDisplayName(String fileDisplayName) {
        this.fileDisplayName = fileDisplayName;
    }

    public TeachingMaterialFileVo fileRealName(String fileRealName) {
        this.fileRealName = fileRealName;
        return this;
    }

    /**
     * 文件存储名称
     *
     * @return fileRealName
     **/
    @ApiModelProperty(value = "文件存储名称")


    public String getFileRealName() {
        return fileRealName;
    }

    public void setFileRealName(String fileRealName) {
        this.fileRealName = fileRealName;
    }

    public TeachingMaterialFileVo fileType(String fileType) {
        this.fileType = fileType;
        return this;
    }

    /**
     * 文件类型（即文件后缀）
     *
     * @return fileType
     **/
    @ApiModelProperty(value = "文件类型（即文件后缀）")


    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public TeachingMaterialFileVo innerUrl(String innerUrl) {
        this.innerUrl = innerUrl;
        return this;
    }

    /**
     * 内网地址
     *
     * @return innerUrl
     **/
    @ApiModelProperty(value = "内网地址")


    public String getInnerUrl() {
        return innerUrl;
    }

    public void setInnerUrl(String innerUrl) {
        this.innerUrl = innerUrl;
    }

    public TeachingMaterialFileVo outerUrl(String outerUrl) {
        this.outerUrl = outerUrl;
        return this;
    }

    /**
     * 外网地址
     *
     * @return outerUrl
     **/
    @ApiModelProperty(value = "外网地址")


    public String getOuterUrl() {
        return outerUrl;
    }

    public void setOuterUrl(String outerUrl) {
        this.outerUrl = outerUrl;
    }

    public TeachingMaterialFileVo filePath(String filePath) {
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

    public TeachingMaterialFileVo fileSize(String fileSize) {
        this.fileSize = fileSize;
        return this;
    }

    /**
     * 文件大小
     *
     * @return fileSize
     **/
    @ApiModelProperty(value = "文件大小")


    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public TeachingMaterialFileVo courseMaterialId(String courseMaterialId) {
        this.courseMaterialId = courseMaterialId;
        return this;
    }

    /**
     * 课程资料表Id
     *
     * @return courseMaterialId
     **/
    @ApiModelProperty(value = "课程资料表Id")


    public String getCourseMaterialId() {
        return courseMaterialId;
    }

    public void setCourseMaterialId(String courseMaterialId) {
        this.courseMaterialId = courseMaterialId;
    }

    public TeachingMaterialFileVo dataOperationType(Integer dataOperationType) {
        this.dataOperationType = dataOperationType;
        return this;
    }

    /**
     * 数据操作状态（0:删除、1：新增、2：已保存）
     *
     * @return dataOperationType
     **/
    @ApiModelProperty(value = "数据操作状态（0:删除、1：新增、2：已保存、3：更新）")


    public Integer getDataOperationType() {
        return dataOperationType;
    }

    public void setDataOperationType(Integer dataOperationType) {
        this.dataOperationType = dataOperationType;
    }

    public TeachingMaterialFileVo creatorId(String creatorId) {
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

    public TeachingMaterialFileVo creatorName(String creatorName) {
        this.creatorName = creatorName;
        return this;
    }

    /**
     * 创建人姓名
     *
     * @return creatorName
     **/
    @ApiModelProperty(value = "创建人姓名")


    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public TeachingMaterialFileVo createTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    /**
     * 创建时间（yyyy-MM-dd HH:mm:ss）
     *
     * @return createTime
     **/
    @ApiModelProperty(value = "创建时间（yyyy-MM-dd HH:mm:ss）")


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public TeachingMaterialFileVo coverFileDisplayName(String coverFileDisplayName) {
        this.coverFileDisplayName = coverFileDisplayName;
        return this;
    }

    /**
     * 封面文件显示名称
     *
     * @return coverFileDisplayName
     **/
    @ApiModelProperty(value = "封面文件显示名称")


    public String getCoverFileDisplayName() {
        return coverFileDisplayName;
    }

    public void setCoverFileDisplayName(String coverFileDisplayName) {
        this.coverFileDisplayName = coverFileDisplayName;
    }

    public TeachingMaterialFileVo coverFileRealName(String coverFileRealName) {
        this.coverFileRealName = coverFileRealName;
        return this;
    }

    /**
     * 封面文件存储名称
     *
     * @return coverFileRealName
     **/
    @ApiModelProperty(value = "封面文件存储名称")


    public String getCoverFileRealName() {
        return coverFileRealName;
    }

    public void setCoverFileRealName(String coverFileRealName) {
        this.coverFileRealName = coverFileRealName;
    }

    public TeachingMaterialFileVo coverFileType(String coverFileType) {
        this.coverFileType = coverFileType;
        return this;
    }

    /**
     * 封面文件类型（即文件后缀）
     *
     * @return coverFileType
     **/
    @ApiModelProperty(value = "封面文件类型（即文件后缀）")


    public String getCoverFileType() {
        return coverFileType;
    }

    public void setCoverFileType(String coverFileType) {
        this.coverFileType = coverFileType;
    }

    public TeachingMaterialFileVo coverInnerUrl(String coverInnerUrl) {
        this.coverInnerUrl = coverInnerUrl;
        return this;
    }

    /**
     * 封面内网地址
     *
     * @return coverInnerUrl
     **/
    @ApiModelProperty(value = "封面内网地址")


    public String getCoverInnerUrl() {
        return coverInnerUrl;
    }

    public void setCoverInnerUrl(String coverInnerUrl) {
        this.coverInnerUrl = coverInnerUrl;
    }

    public TeachingMaterialFileVo coverOuterUrl(String coverOuterUrl) {
        this.coverOuterUrl = coverOuterUrl;
        return this;
    }

    /**
     * 封面外网地址
     *
     * @return coverOuterUrl
     **/
    @ApiModelProperty(value = "封面外网地址")


    public String getCoverOuterUrl() {
        return coverOuterUrl;
    }

    public void setCoverOuterUrl(String coverOuterUrl) {
        this.coverOuterUrl = coverOuterUrl;
    }

    public TeachingMaterialFileVo coverFilePath(String coverFilePath) {
        this.coverFilePath = coverFilePath;
        return this;
    }

    /**
     * 封面文件路径
     *
     * @return coverFilePath
     **/
    @ApiModelProperty(value = "封面文件路径")


    public String getCoverFilePath() {
        return coverFilePath;
    }

    public void setCoverFilePath(String coverFilePath) {
        this.coverFilePath = coverFilePath;
    }

    public TeachingMaterialFileVo coverFileSize(String coverFileSize) {
        this.coverFileSize = coverFileSize;
        return this;
    }

    /**
     * 封面文件大小
     *
     * @return coverFileSize
     **/
    @ApiModelProperty(value = "封面文件大小")


    public String getCoverFileSize() {
        return coverFileSize;
    }

    public void setCoverFileSize(String coverFileSize) {
        this.coverFileSize = coverFileSize;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeachingMaterialFileVo teachingMaterialFileVo = (TeachingMaterialFileVo) o;
        return Objects.equals(this.fileId, teachingMaterialFileVo.fileId) &&
                Objects.equals(this.fileDisplayName, teachingMaterialFileVo.fileDisplayName) &&
                Objects.equals(this.fileRealName, teachingMaterialFileVo.fileRealName) &&
                Objects.equals(this.fileType, teachingMaterialFileVo.fileType) &&
                Objects.equals(this.innerUrl, teachingMaterialFileVo.innerUrl) &&
                Objects.equals(this.outerUrl, teachingMaterialFileVo.outerUrl) &&
                Objects.equals(this.filePath, teachingMaterialFileVo.filePath) &&
                Objects.equals(this.fileSize, teachingMaterialFileVo.fileSize) &&
                Objects.equals(this.courseMaterialId, teachingMaterialFileVo.courseMaterialId) &&
                Objects.equals(this.dataOperationType, teachingMaterialFileVo.dataOperationType) &&
                Objects.equals(this.creatorId, teachingMaterialFileVo.creatorId) &&
                Objects.equals(this.creatorName, teachingMaterialFileVo.creatorName) &&
                Objects.equals(this.createTime, teachingMaterialFileVo.createTime) &&
                Objects.equals(this.coverFileDisplayName, teachingMaterialFileVo.coverFileDisplayName) &&
                Objects.equals(this.coverFileRealName, teachingMaterialFileVo.coverFileRealName) &&
                Objects.equals(this.coverFileType, teachingMaterialFileVo.coverFileType) &&
                Objects.equals(this.coverInnerUrl, teachingMaterialFileVo.coverInnerUrl) &&
                Objects.equals(this.coverOuterUrl, teachingMaterialFileVo.coverOuterUrl) &&
                Objects.equals(this.coverFilePath, teachingMaterialFileVo.coverFilePath) &&
                Objects.equals(this.coverFileSize, teachingMaterialFileVo.coverFileSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileId, fileDisplayName, fileRealName, fileType, innerUrl, outerUrl, filePath, fileSize,
                courseMaterialId, dataOperationType, creatorId, creatorName, createTime, coverFileDisplayName,
                coverFileRealName, coverFileType, coverInnerUrl, coverOuterUrl, coverFilePath, coverFileSize);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TeachingMaterialFileVo {\n");

        sb.append("    fileId: ").append(toIndentedString(fileId)).append("\n");
        sb.append("    fileDisplayName: ").append(toIndentedString(fileDisplayName)).append("\n");
        sb.append("    fileRealName: ").append(toIndentedString(fileRealName)).append("\n");
        sb.append("    fileType: ").append(toIndentedString(fileType)).append("\n");
        sb.append("    innerUrl: ").append(toIndentedString(innerUrl)).append("\n");
        sb.append("    outerUrl: ").append(toIndentedString(outerUrl)).append("\n");
        sb.append("    filePath: ").append(toIndentedString(filePath)).append("\n");
        sb.append("    fileSize: ").append(toIndentedString(fileSize)).append("\n");
        sb.append("    courseMaterialId: ").append(toIndentedString(courseMaterialId)).append("\n");
        sb.append("    dataOperationType: ").append(toIndentedString(dataOperationType)).append("\n");
        sb.append("    creatorId: ").append(toIndentedString(creatorId)).append("\n");
        sb.append("    creatorName: ").append(toIndentedString(creatorName)).append("\n");
        sb.append("    createTime: ").append(toIndentedString(createTime)).append("\n");
        sb.append("    coverFileDisplayName: ").append(toIndentedString(coverFileDisplayName)).append("\n");
        sb.append("    coverFileRealName: ").append(toIndentedString(coverFileRealName)).append("\n");
        sb.append("    coverFileType: ").append(toIndentedString(coverFileType)).append("\n");
        sb.append("    coverInnerUrl: ").append(toIndentedString(coverInnerUrl)).append("\n");
        sb.append("    coverOuterUrl: ").append(toIndentedString(coverOuterUrl)).append("\n");
        sb.append("    coverFilePath: ").append(toIndentedString(coverFilePath)).append("\n");
        sb.append("    coverFileSize: ").append(toIndentedString(coverFileSize)).append("\n");
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

