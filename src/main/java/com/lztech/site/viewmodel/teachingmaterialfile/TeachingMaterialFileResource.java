package com.lztech.site.viewmodel.teachingmaterialfile;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * TeachingMaterialFileResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-06-16T02:29:30.517Z")

public class TeachingMaterialFileResource {

    @JsonProperty("teachingMaterialFileId")
    private String teachingMaterialFileId = null;

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


    @JsonProperty("teachingMaterialFileContentList")
    @Valid
    private List<TeachingMaterialFileContentResource> teachingMaterialFileContentList = null;

    public TeachingMaterialFileResource teachingMaterialFileId(String teachingMaterialFileId) {
        this.teachingMaterialFileId = teachingMaterialFileId;
        return this;
    }

    /**
     * 课程教材id
     *
     * @return teachingMaterialFileId
     **/
    @ApiModelProperty(value = "课程教材id")

    public String getTeachingMaterialFileId() {
        return teachingMaterialFileId;
    }

    public void setTeachingMaterialFileId(String teachingMaterialFileId) {
        this.teachingMaterialFileId = teachingMaterialFileId;
    }

    public TeachingMaterialFileResource fileDisplayName(String fileDisplayName) {
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


    public TeachingMaterialFileResource fileRealName(String fileRealName) {
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


    public TeachingMaterialFileResource fileType(String fileType) {
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

    public TeachingMaterialFileResource innerUrl(String innerUrl) {
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

    public TeachingMaterialFileResource outerUrl(String outerUrl) {
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

    public TeachingMaterialFileResource filePath(String filePath) {
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


    public TeachingMaterialFileResource teachingMaterialFileContentList(
            List<TeachingMaterialFileContentResource> teachingMaterialFileContentList) {
        this.teachingMaterialFileContentList = teachingMaterialFileContentList;
        return this;
    }

    /**
     * @return teachingMaterialFileContentList
     **/
    @ApiModelProperty(value = "")

    public List<TeachingMaterialFileContentResource> getTeachingMaterialFileContentList() {
        return teachingMaterialFileContentList;
    }

    public void setTeachingMaterialFileContentList(List<TeachingMaterialFileContentResource> teachingMaterialFileContentList) {
        this.teachingMaterialFileContentList = teachingMaterialFileContentList;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TeachingMaterialFileResource)) {
            return false;
        }
        TeachingMaterialFileResource that = (TeachingMaterialFileResource) o;
        return Objects.equals(teachingMaterialFileId, that.teachingMaterialFileId) &&
                Objects.equals(fileDisplayName, that.fileDisplayName) &&
                Objects.equals(fileRealName, that.fileRealName) &&
                Objects.equals(fileType, that.fileType) &&
                Objects.equals(innerUrl, that.innerUrl) &&
                Objects.equals(outerUrl, that.outerUrl) &&
                Objects.equals(filePath, that.filePath) &&
                Objects.equals(teachingMaterialFileContentList, that.teachingMaterialFileContentList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teachingMaterialFileId, fileDisplayName, fileRealName, fileType, innerUrl, outerUrl, filePath,
                teachingMaterialFileContentList);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TeachingMaterialFileResource{");
        sb.append("teachingMaterialFileId='").append(teachingMaterialFileId).append('\'');
        sb.append(", fileDisplayName='").append(fileDisplayName).append('\'');
        sb.append(", fileRealName='").append(fileRealName).append('\'');
        sb.append(", fileType='").append(fileType).append('\'');
        sb.append(", innerUrl='").append(innerUrl).append('\'');
        sb.append(", outerUrl='").append(outerUrl).append('\'');
        sb.append(", filePath='").append(filePath).append('\'');
        sb.append(", teachingMaterialFileContentList=").append(teachingMaterialFileContentList);
        sb.append('}');
        return sb.toString();
    }
}
