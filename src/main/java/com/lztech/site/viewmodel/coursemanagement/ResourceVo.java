package com.lztech.site.viewmodel.coursemanagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;


/**
 * ResourceVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-03-16T12:59:42.939Z")


public class ResourceVo   {
    @JsonProperty("id")
    private String id = null;

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
    private Long fileSize = null;

    public ResourceVo id(String id) {
        this.id = id;
        return this;
    }

    /**
     * UUID做标识
     * @return id
     **/
    @ApiModelProperty(value = "UUID做标识")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ResourceVo fileRealName(String fileRealName) {
        this.fileRealName = fileRealName;
        return this;
    }

    /**
     * 文件实际名
     * @return fileRealName
     **/
    @ApiModelProperty(value = "文件实际名")


    public String getFileRealName() {
        return fileRealName;
    }

    public void setFileRealName(String fileRealName) {
        this.fileRealName = fileRealName;
    }

    public ResourceVo fileSavedName(String fileSavedName) {
        this.fileSavedName = fileSavedName;
        return this;
    }

    /**
     * 文件保存名
     * @return fileSavedName
     **/
    @ApiModelProperty(value = "文件保存名")


    public String getFileSavedName() {
        return fileSavedName;
    }

    public void setFileSavedName(String fileSavedName) {
        this.fileSavedName = fileSavedName;
    }

    public ResourceVo filepath(String filepath) {
        this.filepath = filepath;
        return this;
    }

    /**
     * 文件路径
     * @return filepath
     **/
    @ApiModelProperty(value = "文件路径")


    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public ResourceVo innerServerIp(String innerServerIp) {
        this.innerServerIp = innerServerIp;
        return this;
    }

    /**
     * 内网IP
     * @return innerServerIp
     **/
    @ApiModelProperty(value = "内网IP")


    public String getInnerServerIp() {
        return innerServerIp;
    }

    public void setInnerServerIp(String innerServerIp) {
        this.innerServerIp = innerServerIp;
    }

    public ResourceVo outerServerIp(String outerServerIp) {
        this.outerServerIp = outerServerIp;
        return this;
    }

    /**
     * 外网IP
     * @return outerServerIp
     **/
    @ApiModelProperty(value = "外网IP")


    public String getOuterServerIp() {
        return outerServerIp;
    }

    public void setOuterServerIp(String outerServerIp) {
        this.outerServerIp = outerServerIp;
    }

    public ResourceVo fileType(String fileType) {
        this.fileType = fileType;
        return this;
    }

    /**
     * 文件类型
     * @return fileType
     **/
    @ApiModelProperty(value = "文件类型")


    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public ResourceVo fileSize(Long fileSize) {
        this.fileSize = fileSize;
        return this;
    }

    /**
     * 文件大小
     * @return fileSize
     **/
    @ApiModelProperty(value = "文件大小")


    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ResourceVo resourceVo = (ResourceVo) o;
        return Objects.equals(this.id, resourceVo.id) &&
                Objects.equals(this.fileRealName, resourceVo.fileRealName) &&
                Objects.equals(this.fileSavedName, resourceVo.fileSavedName) &&
                Objects.equals(this.filepath, resourceVo.filepath) &&
                Objects.equals(this.innerServerIp, resourceVo.innerServerIp) &&
                Objects.equals(this.outerServerIp, resourceVo.outerServerIp) &&
                Objects.equals(this.fileType, resourceVo.fileType) &&
                Objects.equals(this.fileSize, resourceVo.fileSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fileRealName, fileSavedName, filepath, innerServerIp, outerServerIp, fileType, fileSize);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ResourceVo {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
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
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
