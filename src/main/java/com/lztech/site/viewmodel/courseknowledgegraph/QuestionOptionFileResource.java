package com.lztech.site.viewmodel.courseknowledgegraph;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * QuestionOptionFileResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-03-19T02:58:49.024Z")


public class QuestionOptionFileResource {
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
    private BigDecimal fileSize = null;

    @JsonProperty("order")
    private Integer order = null;

    public QuestionOptionFileResource fileRealName(String fileRealName) {
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

    public QuestionOptionFileResource fileSavedName(String fileSavedName) {
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

    public QuestionOptionFileResource filepath(String filepath) {
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

    public QuestionOptionFileResource innerServerIp(String innerServerIp) {
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

    public QuestionOptionFileResource outerServerIp(String outerServerIp) {
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

    public QuestionOptionFileResource fileType(String fileType) {
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

    public QuestionOptionFileResource fileSize(BigDecimal fileSize) {
        this.fileSize = fileSize;
        return this;
    }

    /**
     * 文件大小
     *
     * @return fileSize
     **/
    @ApiModelProperty(value = "文件大小")

    @Valid

    public BigDecimal getFileSize() {
        return fileSize;
    }

    public void setFileSize(BigDecimal fileSize) {
        this.fileSize = fileSize;
    }

    public QuestionOptionFileResource order(Integer order) {
        this.order = order;
        return this;
    }

    /**
     * 文件在内容中的排序
     *
     * @return order
     **/
    @ApiModelProperty(value = "文件在内容中的排序")


    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QuestionOptionFileResource questionOptionFileResource = (QuestionOptionFileResource) o;
        return Objects.equals(this.fileRealName, questionOptionFileResource.fileRealName) &&
                Objects.equals(this.fileSavedName, questionOptionFileResource.fileSavedName) &&
                Objects.equals(this.filepath, questionOptionFileResource.filepath) &&
                Objects.equals(this.innerServerIp, questionOptionFileResource.innerServerIp) &&
                Objects.equals(this.outerServerIp, questionOptionFileResource.outerServerIp) &&
                Objects.equals(this.fileType, questionOptionFileResource.fileType) &&
                Objects.equals(this.fileSize, questionOptionFileResource.fileSize) &&
                Objects.equals(this.order, questionOptionFileResource.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileRealName, fileSavedName, filepath, innerServerIp, outerServerIp, fileType, fileSize, order);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class QuestionOptionFileResource {\n");

        sb.append("    fileRealName: ").append(toIndentedString(fileRealName)).append("\n");
        sb.append("    fileSavedName: ").append(toIndentedString(fileSavedName)).append("\n");
        sb.append("    filepath: ").append(toIndentedString(filepath)).append("\n");
        sb.append("    innerServerIp: ").append(toIndentedString(innerServerIp)).append("\n");
        sb.append("    outerServerIp: ").append(toIndentedString(outerServerIp)).append("\n");
        sb.append("    fileType: ").append(toIndentedString(fileType)).append("\n");
        sb.append("    fileSize: ").append(toIndentedString(fileSize)).append("\n");
        sb.append("    order: ").append(toIndentedString(order)).append("\n");
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

