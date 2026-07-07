package com.lztech.site.viewmodel.courseknowledgegraph;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * QuestionFileResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-03-19T02:58:49.024Z")


public class QuestionFileResource {
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

    @JsonProperty("source")
    private Integer source = null;

    @JsonProperty("order")
    private Integer order = null;

    public QuestionFileResource fileRealName(String fileRealName) {
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

    public QuestionFileResource fileSavedName(String fileSavedName) {
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

    public QuestionFileResource filepath(String filepath) {
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

    public QuestionFileResource innerServerIp(String innerServerIp) {
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

    public QuestionFileResource outerServerIp(String outerServerIp) {
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

    public QuestionFileResource fileType(String fileType) {
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

    public QuestionFileResource fileSize(BigDecimal fileSize) {
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

    public QuestionFileResource source(Integer source) {
        this.source = source;
        return this;
    }

    /**
     * 文件所属 0：题干；1：解析
     *
     * @return source
     **/
    @ApiModelProperty(value = "文件所属 0：题干；1：解析")


    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public QuestionFileResource order(Integer order) {
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
        QuestionFileResource questionFileResource = (QuestionFileResource) o;
        return Objects.equals(this.fileRealName, questionFileResource.fileRealName) &&
                Objects.equals(this.fileSavedName, questionFileResource.fileSavedName) &&
                Objects.equals(this.filepath, questionFileResource.filepath) &&
                Objects.equals(this.innerServerIp, questionFileResource.innerServerIp) &&
                Objects.equals(this.outerServerIp, questionFileResource.outerServerIp) &&
                Objects.equals(this.fileType, questionFileResource.fileType) &&
                Objects.equals(this.fileSize, questionFileResource.fileSize) &&
                Objects.equals(this.source, questionFileResource.source) &&
                Objects.equals(this.order, questionFileResource.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileRealName, fileSavedName, filepath, innerServerIp, outerServerIp, fileType, fileSize, source, order);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class QuestionFileResource {\n");

        sb.append("    fileRealName: ").append(toIndentedString(fileRealName)).append("\n");
        sb.append("    fileSavedName: ").append(toIndentedString(fileSavedName)).append("\n");
        sb.append("    filepath: ").append(toIndentedString(filepath)).append("\n");
        sb.append("    innerServerIp: ").append(toIndentedString(innerServerIp)).append("\n");
        sb.append("    outerServerIp: ").append(toIndentedString(outerServerIp)).append("\n");
        sb.append("    fileType: ").append(toIndentedString(fileType)).append("\n");
        sb.append("    fileSize: ").append(toIndentedString(fileSize)).append("\n");
        sb.append("    source: ").append(toIndentedString(source)).append("\n");
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

