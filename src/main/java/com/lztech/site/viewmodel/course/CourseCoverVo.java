package com.lztech.site.viewmodel.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseCoverVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-01-12T07:46:17.139Z")


public class CourseCoverVo {
    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("modifierName")
    private String modifierName = null;

    @JsonProperty("modifierId")
    private String modifierId = null;

    @JsonProperty("fileRealName")
    private String fileRealName = null;

    @JsonProperty("fileSavedName")
    private String fileSavedName = null;

    @JsonProperty("filepath")
    private String filepath = null;

    @JsonProperty("innerServerIp")
    private String innerServerIp = null;

    @JsonProperty("outerServerIp")
    private String outerServerIp = null;

    @JsonProperty("fileType")
    private String fileType = null;

    @JsonProperty("fileSize")
    private String fileSize = null;

    public CourseCoverVo courseId(String courseId) {
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

    public CourseCoverVo modifierName(String modifierName) {
        this.modifierName = modifierName;
        return this;
    }

    /**
     * 更新人姓名
     *
     * @return modifierName
     **/
    @ApiModelProperty(value = "更新人姓名")


    public String getModifierName() {
        return modifierName;
    }

    public void setModifierName(String modifierName) {
        this.modifierName = modifierName;
    }

    public CourseCoverVo modifierId(String modifierId) {
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

    public CourseCoverVo fileRealName(String fileRealName) {
        this.fileRealName = fileRealName;
        return this;
    }

    /**
     * 文件实际名
     *
     * @return fileRealName
     **/
    @ApiModelProperty(value = "文件实际名")


    public String getFileRealName() {
        return fileRealName;
    }

    public void setFileRealName(String fileRealName) {
        this.fileRealName = fileRealName;
    }

    public CourseCoverVo fileSavedName(String fileSavedName) {
        this.fileSavedName = fileSavedName;
        return this;
    }

    /**
     * 文件保存名
     *
     * @return fileSavedName
     **/
    @ApiModelProperty(value = "文件保存名")


    public String getFileSavedName() {
        return fileSavedName;
    }

    public void setFileSavedName(String fileSavedName) {
        this.fileSavedName = fileSavedName;
    }

    public CourseCoverVo filepath(String filepath) {
        this.filepath = filepath;
        return this;
    }

    /**
     * 文件路径
     *
     * @return filepath
     **/
    @ApiModelProperty(value = "文件路径")


    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public CourseCoverVo innerServerIp(String innerServerIp) {
        this.innerServerIp = innerServerIp;
        return this;
    }

    /**
     * 内网IP
     *
     * @return innerServerIp
     **/
    @ApiModelProperty(value = "内网IP")


    public String getInnerServerIp() {
        return innerServerIp;
    }

    public void setInnerServerIp(String innerServerIp) {
        this.innerServerIp = innerServerIp;
    }

    public CourseCoverVo outerServerIp(String outerServerIp) {
        this.outerServerIp = outerServerIp;
        return this;
    }

    /**
     * 外网IP
     *
     * @return outerServerIp
     **/
    @ApiModelProperty(value = "外网IP")


    public String getOuterServerIp() {
        return outerServerIp;
    }

    public void setOuterServerIp(String outerServerIp) {
        this.outerServerIp = outerServerIp;
    }

    public CourseCoverVo fileType(String fileType) {
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

    public CourseCoverVo fileSize(String fileSize) {
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
        CourseCoverVo courseCoverVo = (CourseCoverVo) o;
        return Objects.equals(this.courseId, courseCoverVo.courseId) &&
                Objects.equals(this.modifierName, courseCoverVo.modifierName) &&
                Objects.equals(this.modifierId, courseCoverVo.modifierId) &&
                Objects.equals(this.fileRealName, courseCoverVo.fileRealName) &&
                Objects.equals(this.fileSavedName, courseCoverVo.fileSavedName) &&
                Objects.equals(this.filepath, courseCoverVo.filepath) &&
                Objects.equals(this.innerServerIp, courseCoverVo.innerServerIp) &&
                Objects.equals(this.outerServerIp, courseCoverVo.outerServerIp) &&
                Objects.equals(this.fileType, courseCoverVo.fileType) &&
                Objects.equals(this.fileSize, courseCoverVo.fileSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, modifierName, modifierId, fileRealName, fileSavedName, filepath,
                innerServerIp, outerServerIp, fileType, fileSize);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseCoverVo {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    modifierName: ").append(toIndentedString(modifierName)).append("\n");
        sb.append("    modifierId: ").append(toIndentedString(modifierId)).append("\n");
        sb.append("    fileRealName: ").append(toIndentedString(fileRealName)).append("\n");
        sb.append("    fileSavedName: ").append(toIndentedString(fileSavedName)).append("\n");
        sb.append("    filepath: ").append(toIndentedString(filepath)).append("\n");
        sb.append("    innerServerIp: ").append(toIndentedString(innerServerIp)).append("\n");
        sb.append("    outerServerIp: ").append(toIndentedString(outerServerIp)).append("\n");
        sb.append("    fileType: ").append(toIndentedString(fileType)).append("\n");
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

