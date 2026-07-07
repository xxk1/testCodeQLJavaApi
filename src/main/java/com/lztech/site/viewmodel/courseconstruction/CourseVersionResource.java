package com.lztech.site.viewmodel.courseconstruction;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * CourseVersionResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-02-18T07:05:46.115Z")


public class CourseVersionResource {
    @JsonProperty("versionId")
    private String versionId = null;

    @JsonProperty("versionNumber")
    private String versionNumber = null;

    @JsonProperty("filingTime")
    private String filingTime = null;

    @JsonProperty("filingUserId")
    private String filingUserId = null;

    @JsonProperty("filingUserNo")
    private String filingUserNo = null;

    @JsonProperty("filingUserName")
    private String filingUserName = null;

    @JsonProperty("versionCompletion")
    private BigDecimal versionCompletion = null;

    @JsonProperty("sortTime")
    private String sortTime = null;

    public String getSortTime() {
        return sortTime;
    }

    public void setSortTime(String sortTime) {
        this.sortTime = sortTime;
    }

    public CourseVersionResource versionId(String versionId) {
        this.versionId = versionId;
        return this;
    }

    /**
     * 版本id
     *
     * @return versionId
     **/
    @ApiModelProperty(value = "版本id")


    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public CourseVersionResource versionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
        return this;
    }

    /**
     * 版本号
     *
     * @return versionNumber
     **/
    @ApiModelProperty(value = "版本号")


    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public CourseVersionResource filingTime(String filingTime) {
        this.filingTime = filingTime;
        return this;
    }

    /**
     * 归档时间
     *
     * @return filingTime
     **/
    @ApiModelProperty(value = "归档时间")


    public String getFilingTime() {
        return filingTime;
    }

    public void setFilingTime(String filingTime) {
        this.filingTime = filingTime;
    }

    public CourseVersionResource filingUserId(String filingUserId) {
        this.filingUserId = filingUserId;
        return this;
    }

    /**
     * 归档人id
     *
     * @return filingUserId
     **/
    @ApiModelProperty(value = "归档人id")


    public String getFilingUserId() {
        return filingUserId;
    }

    public void setFilingUserId(String filingUserId) {
        this.filingUserId = filingUserId;
    }

    public CourseVersionResource filingUserNo(String filingUserNo) {
        this.filingUserNo = filingUserNo;
        return this;
    }

    /**
     * 归档人工号
     *
     * @return filingUserNo
     **/
    @ApiModelProperty(value = "归档人工号")


    public String getFilingUserNo() {
        return filingUserNo;
    }

    public void setFilingUserNo(String filingUserNo) {
        this.filingUserNo = filingUserNo;
    }

    public CourseVersionResource filingUserName(String filingUserName) {
        this.filingUserName = filingUserName;
        return this;
    }

    /**
     * 归档人姓名
     *
     * @return filingUserName
     **/
    @ApiModelProperty(value = "归档人姓名")


    public String getFilingUserName() {
        return filingUserName;
    }

    public void setFilingUserName(String filingUserName) {
        this.filingUserName = filingUserName;
    }

    public CourseVersionResource versionCompletion(BigDecimal versionCompletion) {
        this.versionCompletion = versionCompletion;
        return this;
    }

    /**
     * 版本完成度
     *
     * @return versionCompletion
     **/
    @ApiModelProperty(value = "版本完成度")

    @Valid

    public BigDecimal getVersionCompletion() {
        return versionCompletion;
    }

    public void setVersionCompletion(BigDecimal versionCompletion) {
        this.versionCompletion = versionCompletion;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseVersionResource courseVersionResource = (CourseVersionResource) o;
        return Objects.equals(this.versionId, courseVersionResource.versionId) &&
                Objects.equals(this.versionNumber, courseVersionResource.versionNumber) &&
                Objects.equals(this.filingTime, courseVersionResource.filingTime) &&
                Objects.equals(this.filingUserId, courseVersionResource.filingUserId) &&
                Objects.equals(this.filingUserNo, courseVersionResource.filingUserNo) &&
                Objects.equals(this.filingUserName, courseVersionResource.filingUserName) &&
                Objects.equals(this.versionCompletion, courseVersionResource.versionCompletion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(versionId, versionNumber, filingTime, filingUserId, filingUserNo, filingUserName, versionCompletion);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseVersionResource {\n");

        sb.append("    versionId: ").append(toIndentedString(versionId)).append("\n");
        sb.append("    versionNumber: ").append(toIndentedString(versionNumber)).append("\n");
        sb.append("    filingTime: ").append(toIndentedString(filingTime)).append("\n");
        sb.append("    filingUserId: ").append(toIndentedString(filingUserId)).append("\n");
        sb.append("    filingUserNo: ").append(toIndentedString(filingUserNo)).append("\n");
        sb.append("    filingUserName: ").append(toIndentedString(filingUserName)).append("\n");
        sb.append("    versionCompletion: ").append(toIndentedString(versionCompletion)).append("\n");
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

