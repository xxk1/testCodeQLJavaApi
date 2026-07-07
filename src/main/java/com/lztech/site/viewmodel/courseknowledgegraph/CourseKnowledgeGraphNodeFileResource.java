package com.lztech.site.viewmodel.courseknowledgegraph;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class CourseKnowledgeGraphNodeFileResource {
    @JsonProperty("resourceName")
    @NotBlank(message = "文件显示名称不允许为空")
    private String resourceName = null;

    @JsonProperty("fileRealName")
    @NotBlank(message = "文件存储名称不允许为空")
    private String fileRealName = null;

    @JsonProperty("fileType")
    @NotBlank(message = "文件类型不允许为空")
    private String fileType = null;

    @JsonProperty("innerUrl")
    @NotBlank(message = "内网地址不允许为空")
    private String innerUrl = null;

    @JsonProperty("outerUrl")
    @NotBlank(message = "外网地址不允许为空")
    private String outerUrl = null;

    @JsonProperty("filePath")
    @NotBlank(message = "文件路径不允许为空")
    private String filePath = null;

    @JsonProperty("fileSize")
    @NotBlank(message = "文件大小不允许为空")
    private String fileSize = null;


    public CourseKnowledgeGraphNodeFileResource resourceName(String resourceName) {
        this.resourceName = resourceName;
        return this;
    }

    /**
     * 文件显示名称
     *
     * @return resourceName
     **/
    @ApiModelProperty(value = "文件显示名称")


    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public CourseKnowledgeGraphNodeFileResource fileRealName(String fileRealName) {
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

    public CourseKnowledgeGraphNodeFileResource fileType(String fileType) {
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

    public CourseKnowledgeGraphNodeFileResource innerUrl(String innerUrl) {
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

    public CourseKnowledgeGraphNodeFileResource outerUrl(String outerUrl) {
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

    public CourseKnowledgeGraphNodeFileResource filePath(String filePath) {
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

    public CourseKnowledgeGraphNodeFileResource fileSize(String fileSize) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseKnowledgeGraphNodeFileResource that = (CourseKnowledgeGraphNodeFileResource) o;
        return Objects.equals(resourceName, that.resourceName) &&
                Objects.equals(fileRealName, that.fileRealName) &&
                Objects.equals(fileType, that.fileType) &&
                Objects.equals(innerUrl, that.innerUrl) &&
                Objects.equals(outerUrl, that.outerUrl) &&
                Objects.equals(filePath, that.filePath) &&
                Objects.equals(fileSize, that.fileSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resourceName, fileRealName, fileType, innerUrl,outerUrl,filePath,fileSize);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseKnowledgeGraphNodeFileResource {\n");
        sb.append("    resourceName:").append(toIndentedString(resourceName)).append("\n");
        sb.append("    fileRealName:").append(toIndentedString(fileRealName)).append("\n");
        sb.append("    fileType:").append(toIndentedString(fileType)).append("\n");
        sb.append("    innerUrl:").append(toIndentedString(innerUrl)).append("\n");
        sb.append("    outerUrl:").append(toIndentedString(outerUrl)).append("\n");
        sb.append("    filePath:").append(toIndentedString(filePath)).append("\n");
        sb.append("    fileSize:").append(toIndentedString(fileSize)).append("\n");
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
