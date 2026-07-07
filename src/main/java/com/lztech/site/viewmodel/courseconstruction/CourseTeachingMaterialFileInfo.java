package com.lztech.site.viewmodel.courseconstruction;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseTeachingMaterialFileInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-07-21T16:36:23.222+08:00")

public class CourseTeachingMaterialFileInfo {
    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("versionId")
    private String versionId = null;

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

    @JsonProperty("operatorId")
    private String operatorId;

    @JsonProperty("operatorName")
    private String operatorName;

    @JsonProperty("operatorNo")
    private String operatorNo = null;

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperatorNo() {
        return operatorNo;
    }

    public void setOperatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
    }

    public CourseTeachingMaterialFileInfo courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    /**
     * 课程ID
     *
     * @return courseId
     **/
    @ApiModelProperty(value = "课程ID")


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public CourseTeachingMaterialFileInfo versionId(String versionId) {
        this.versionId = versionId;
        return this;
    }

    /**
     * 课程版本ID
     *
     * @return versionId
     **/
    @ApiModelProperty(value = "课程版本ID")


    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public CourseTeachingMaterialFileInfo fileDisplayName(String fileDisplayName) {
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

    public CourseTeachingMaterialFileInfo fileRealName(String fileRealName) {
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

    public CourseTeachingMaterialFileInfo fileType(String fileType) {
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

    public CourseTeachingMaterialFileInfo innerUrl(String innerUrl) {
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

    public CourseTeachingMaterialFileInfo outerUrl(String outerUrl) {
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

    public CourseTeachingMaterialFileInfo filePath(String filePath) {
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

    public CourseTeachingMaterialFileInfo fileSize(String fileSize) {
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
        CourseTeachingMaterialFileInfo courseTeachingMaterialFileInfo = (CourseTeachingMaterialFileInfo) o;
        return Objects.equals(this.courseId, courseTeachingMaterialFileInfo.courseId) &&
                Objects.equals(this.versionId, courseTeachingMaterialFileInfo.versionId) &&
                Objects.equals(this.fileDisplayName, courseTeachingMaterialFileInfo.fileDisplayName) &&
                Objects.equals(this.fileRealName, courseTeachingMaterialFileInfo.fileRealName) &&
                Objects.equals(this.fileType, courseTeachingMaterialFileInfo.fileType) &&
                Objects.equals(this.innerUrl, courseTeachingMaterialFileInfo.innerUrl) &&
                Objects.equals(this.outerUrl, courseTeachingMaterialFileInfo.outerUrl) &&
                Objects.equals(this.filePath, courseTeachingMaterialFileInfo.filePath) &&
                Objects.equals(this.fileSize, courseTeachingMaterialFileInfo.fileSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, versionId, fileDisplayName, fileRealName, fileType, innerUrl, outerUrl, filePath, fileSize);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTeachingMaterialFileInfo {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    versionId: ").append(toIndentedString(versionId)).append("\n");
        sb.append("    fileDisplayName: ").append(toIndentedString(fileDisplayName)).append("\n");
        sb.append("    fileRealName: ").append(toIndentedString(fileRealName)).append("\n");
        sb.append("    fileType: ").append(toIndentedString(fileType)).append("\n");
        sb.append("    innerUrl: ").append(toIndentedString(innerUrl)).append("\n");
        sb.append("    outerUrl: ").append(toIndentedString(outerUrl)).append("\n");
        sb.append("    filePath: ").append(toIndentedString(filePath)).append("\n");
        sb.append("    fileSize: ").append(toIndentedString(fileSize)).append("\n");
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

