package com.lztech.site.viewmodel.preparationcoursebag;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseResourceModule
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-01-21T09:33:54.868Z")


public class CourseResourceModule {
    @JsonProperty("resourceId")
    private String resourceId = null;

    @JsonProperty("fileName")
    private String fileName = null;

    @JsonProperty("fileSize")
    private String fileSize = null;

    @JsonProperty("intranetFilePath")
    private String intranetFilePath = null;

    @JsonProperty("outtranetFilePath")
    private String outtranetFilePath = null;

    @JsonProperty("filePath")
    private String filePath = null;

    @JsonProperty("uploadedFileName")
    private String uploadedFileName = null;

    @JsonProperty("fileType")
    private String fileType = null;

    @JsonProperty("courseStructureId")
    private String courseStructureId = null;

    @JsonProperty("courseStructureName")
    private String courseStructureName = null;

    public CourseResourceModule resourceId(String resourceId) {
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

    public CourseResourceModule fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    /**
     * 资源名称
     *
     * @return fileName
     **/
    @ApiModelProperty(value = "资源名称")


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public CourseResourceModule fileSize(String fileSize) {
        this.fileSize = fileSize;
        return this;
    }

    /**
     * 资源大小
     *
     * @return fileSize
     **/
    @ApiModelProperty(value = "资源大小")


    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public CourseResourceModule intranetFilePath(String intranetFilePath) {
        this.intranetFilePath = intranetFilePath;
        return this;
    }

    /**
     * 内网ip
     *
     * @return intranetFilePath
     **/
    @ApiModelProperty(value = "内网ip")


    public String getIntranetFilePath() {
        return intranetFilePath;
    }

    public void setIntranetFilePath(String intranetFilePath) {
        this.intranetFilePath = intranetFilePath;
    }

    public CourseResourceModule outtranetFilePath(String outtranetFilePath) {
        this.outtranetFilePath = outtranetFilePath;
        return this;
    }

    /**
     * 外网ip
     *
     * @return outtranetFilePath
     **/
    @ApiModelProperty(value = "外网ip")


    public String getOuttranetFilePath() {
        return outtranetFilePath;
    }

    public void setOuttranetFilePath(String outtranetFilePath) {
        this.outtranetFilePath = outtranetFilePath;
    }

    public CourseResourceModule filePath(String filePath) {
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

    public CourseResourceModule uploadedFileName(String uploadedFileName) {
        this.uploadedFileName = uploadedFileName;
        return this;
    }

    /**
     * 文件服务器存储的名称
     *
     * @return uploadedFileName
     **/
    @ApiModelProperty(value = "文件服务器存储的名称")


    public String getUploadedFileName() {
        return uploadedFileName;
    }

    public void setUploadedFileName(String uploadedFileName) {
        this.uploadedFileName = uploadedFileName;
    }

    public CourseResourceModule fileType(String fileType) {
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

    public CourseResourceModule courseStructureId(String courseStructureId) {
        this.courseStructureId = courseStructureId;
        return this;
    }

    /**
     * 课程结构id
     *
     * @return courseStructureId
     **/
    @ApiModelProperty(value = "课程结构id")


    public String getCourseStructureId() {
        return courseStructureId;
    }

    public void setCourseStructureId(String courseStructureId) {
        this.courseStructureId = courseStructureId;
    }

    public CourseResourceModule courseStructureName(String courseStructureName) {
        this.courseStructureName = courseStructureName;
        return this;
    }

    /**
     * 课程结构名称
     *
     * @return courseStructureName
     **/
    @ApiModelProperty(value = "课程结构名称")


    public String getCourseStructureName() {
        return courseStructureName;
    }

    public void setCourseStructureName(String courseStructureName) {
        this.courseStructureName = courseStructureName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseResourceModule courseResourceModule = (CourseResourceModule) o;
        return Objects.equals(this.resourceId, courseResourceModule.resourceId) &&
                Objects.equals(this.fileName, courseResourceModule.fileName) &&
                Objects.equals(this.fileSize, courseResourceModule.fileSize) &&
                Objects.equals(this.intranetFilePath, courseResourceModule.intranetFilePath) &&
                Objects.equals(this.outtranetFilePath, courseResourceModule.outtranetFilePath) &&
                Objects.equals(this.filePath, courseResourceModule.filePath) &&
                Objects.equals(this.uploadedFileName, courseResourceModule.uploadedFileName) &&
                Objects.equals(this.fileType, courseResourceModule.fileType) &&
                Objects.equals(this.courseStructureId, courseResourceModule.courseStructureId) &&
                Objects.equals(this.courseStructureName, courseResourceModule.courseStructureName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resourceId, fileName, fileSize, intranetFilePath, outtranetFilePath, filePath,
                uploadedFileName, fileType, courseStructureId, courseStructureName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseResourceModule {\n");

        sb.append("    resourceId: ").append(toIndentedString(resourceId)).append("\n");
        sb.append("    fileName: ").append(toIndentedString(fileName)).append("\n");
        sb.append("    fileSize: ").append(toIndentedString(fileSize)).append("\n");
        sb.append("    intranetFilePath: ").append(toIndentedString(intranetFilePath)).append("\n");
        sb.append("    outtranetFilePath: ").append(toIndentedString(outtranetFilePath)).append("\n");
        sb.append("    filePath: ").append(toIndentedString(filePath)).append("\n");
        sb.append("    uploadedFileName: ").append(toIndentedString(uploadedFileName)).append("\n");
        sb.append("    fileType: ").append(toIndentedString(fileType)).append("\n");
        sb.append("    courseStructureId: ").append(toIndentedString(courseStructureId)).append("\n");
        sb.append("    courseStructureName: ").append(toIndentedString(courseStructureName)).append("\n");
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

