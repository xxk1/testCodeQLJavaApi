package com.lztech.site.viewmodel.teachingmaterialfile;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * TeachingMaterialFileContentResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-06-16T02:29:30.517Z")

public class TeachingMaterialFileContentResource {

    @JsonProperty("teachingMaterialFileContentId")
    private String teachingMaterialFileContentId = null;


    @JsonProperty("chapterName")
    private String chapterName = null;


    @JsonProperty("chapterIndex")
    private Integer chapterIndex = null;


    @JsonProperty("fileType")
    private String fileType = null;


    @JsonProperty("contentInnerUrl")
    private String contentInnerUrl = null;


    @JsonProperty("contentOuterUrl")
    private String contentOuterUrl = null;


    @JsonProperty("contentPath")
    private String contentPath = null;

    @JsonProperty("mappingStructureId")
    private String mappingStructureId = null;

    public String getMappingStructureId() {
        return mappingStructureId;
    }

    public void setMappingStructureId(String mappingStructureId) {
        this.mappingStructureId = mappingStructureId;
    }

    public TeachingMaterialFileContentResource teachingMaterialFileContentId(String teachingMaterialFileContentId) {
        this.teachingMaterialFileContentId = teachingMaterialFileContentId;
        return this;
    }

    /**
     * 课程建设教材内容id
     *
     * @return teachingMaterialFileContentId
     **/

    @ApiModelProperty(value = "课程建设教材内容id")

    public String getTeachingMaterialFileContentId() {
        return teachingMaterialFileContentId;
    }

    public void setTeachingMaterialFileContentId(String teachingMaterialFileContentId) {
        this.teachingMaterialFileContentId = teachingMaterialFileContentId;
    }
    public TeachingMaterialFileContentResource chapterName(String chapterName) {
        this.chapterName = chapterName;
        return this;
    }

    /**
     * 章节
     *
     * @return chapterName
     **/

    @ApiModelProperty(value = "章节")

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }
    public TeachingMaterialFileContentResource chapterIndex(Integer chapterIndex) {
        this.chapterIndex = chapterIndex;
        return this;
    }

    /**
     * 章序号
     *
     * @return chapterIndex
     **/

    @ApiModelProperty(value = "章序号")

    public Integer getChapterIndex() {
        return chapterIndex;
    }

    public void setChapterIndex(Integer chapterIndex) {
        this.chapterIndex = chapterIndex;
    }
    public TeachingMaterialFileContentResource fileType(String fileType) {
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
    public TeachingMaterialFileContentResource contentInnerUrl(String contentInnerUrl) {
        this.contentInnerUrl = contentInnerUrl;
        return this;
    }

    /**
     * 内网地址
     *
     * @return contentInnerUrl
     **/

    @ApiModelProperty(value = "内网地址")

    public String getContentInnerUrl() {
        return contentInnerUrl;
    }

    public void setContentInnerUrl(String contentInnerUrl) {
        this.contentInnerUrl = contentInnerUrl;
    }
    public TeachingMaterialFileContentResource contentOuterUrl(String contentOuterUrl) {
        this.contentOuterUrl = contentOuterUrl;
        return this;
    }

    /**
     * 外网地址
     *
     * @return contentOuterUrl
     **/

    @ApiModelProperty(value = "外网地址")

    public String getContentOuterUrl() {
        return contentOuterUrl;
    }

    public void setContentOuterUrl(String contentOuterUrl) {
        this.contentOuterUrl = contentOuterUrl;
    }
    public TeachingMaterialFileContentResource contentPath(String contentPath) {
        this.contentPath = contentPath;
        return this;
    }

    /**
     * 文件路径
     *
     * @return contentPath
     **/

    @ApiModelProperty(value = "文件路径")

    public String getContentPath() {
        return contentPath;
    }

    public void setContentPath(String contentPath) {
        this.contentPath = contentPath;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TeachingMaterialFileContentResource)) {
            return false;
        }
        TeachingMaterialFileContentResource that = (TeachingMaterialFileContentResource) o;
        return Objects.equals(teachingMaterialFileContentId, that.teachingMaterialFileContentId) &&
                Objects.equals(chapterName, that.chapterName) &&
                Objects.equals(chapterIndex, that.chapterIndex) &&
                Objects.equals(fileType, that.fileType) &&
                Objects.equals(contentInnerUrl, that.contentInnerUrl) &&
                Objects.equals(contentOuterUrl, that.contentOuterUrl) &&
                Objects.equals(contentPath, that.contentPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teachingMaterialFileContentId, chapterName, chapterIndex, fileType,
                contentInnerUrl, contentOuterUrl, contentPath);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TeachingMaterialFileContentResource{");
        sb.append("teachingMaterialFileContentId='").append(teachingMaterialFileContentId).append('\'');
        sb.append(", chapterName='").append(chapterName).append('\'');
        sb.append(", chapterIndex=").append(chapterIndex);
        sb.append(", fileType='").append(fileType).append('\'');
        sb.append(", contentInnerUrl='").append(contentInnerUrl).append('\'');
        sb.append(", contentOuterUrl='").append(contentOuterUrl).append('\'');
        sb.append(", contentPath='").append(contentPath).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
