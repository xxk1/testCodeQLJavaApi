package com.lztech.site.resource.administratorclass;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * AdministrativeClassVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-05-05T02:40:27.880Z")


public class AdministrativeClassInfoVo {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("counsellorId")
    private String counsellorId = null;

    @JsonProperty("counsellorNo")
    private String counsellorNo = null;

    @JsonProperty("counsellorName")
    private String counsellorName = null;

    @JsonProperty("className")
    private String className = null;

    @JsonProperty("year")
    private String year = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    @JsonProperty("majorId")
    private String majorId = null;

    @JsonProperty("majorCode")
    private String majorCode = null;

    @JsonProperty("majorName")
    private String majorName = null;

    public AdministrativeClassInfoVo id(String id) {
        this.id = id;
        return this;
    }

    /**
     * id
     * @return id
     **/
    @ApiModelProperty(value = "id")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AdministrativeClassInfoVo counsellorId(String counsellorId) {
        this.counsellorId = counsellorId;
        return this;
    }

    /**
     * 辅导员Id
     * @return counsellorId
     **/
    @ApiModelProperty(value = "辅导员Id")


    public String getCounsellorId() {
        return counsellorId;
    }

    public void setCounsellorId(String counsellorId) {
        this.counsellorId = counsellorId;
    }

    public AdministrativeClassInfoVo counsellorNo(String counsellorNo) {
        this.counsellorNo = counsellorNo;
        return this;
    }

    /**
     * 辅导员工号
     * @return counsellorNo
     **/
    @ApiModelProperty(value = "辅导员工号")


    public String getCounsellorNo() {
        return counsellorNo;
    }

    public void setCounsellorNo(String counsellorNo) {
        this.counsellorNo = counsellorNo;
    }


    public AdministrativeClassInfoVo counsellorName(String counsellorName) {
        this.counsellorName = counsellorName;
        return this;
    }

    /**
     * 辅导员姓名
     * @return counsellorName
     **/
    @ApiModelProperty(value = "辅导员姓名")


    public String getCounsellorName() {
        return counsellorName;
    }

    public void setCounsellorName(String counsellorName) {
        this.counsellorName = counsellorName;
    }



    public AdministrativeClassInfoVo className(String className) {
        this.className = className;
        return this;
    }

    /**
     * 班级名称
     * @return className
     **/
    @ApiModelProperty(value = "班级名称")


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public AdministrativeClassInfoVo year(String year) {
        this.year = year;
        return this;
    }

    /**
     * 年级
     * @return year
     **/
    @ApiModelProperty(value = "年级")


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public AdministrativeClassInfoVo collegeId(String collegeId) {
        this.collegeId = collegeId;
        return this;
    }

    /**
     * 学院id
     * @return collegeId
     **/
    @ApiModelProperty(value = "学院id")


    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public AdministrativeClassInfoVo collegeName(String collegeName) {
        this.collegeName = collegeName;
        return this;
    }

    /**
     * 学院名称
     * @return collegeName
     **/
    @ApiModelProperty(value = "学院名称")


    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public AdministrativeClassInfoVo majorId(String majorId) {
        this.majorId = majorId;
        return this;
    }

    /**
     * 专业id
     * @return majorId
     **/
    @ApiModelProperty(value = "专业id")


    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public AdministrativeClassInfoVo majorCode(String majorCode) {
        this.majorCode = majorCode;
        return this;
    }

    /**
     * 专业代码
     * @return majorCode
     **/
    @ApiModelProperty(value = "专业代码")


    public String getMajorCode() {
        return majorCode;
    }

    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode;
    }

    public AdministrativeClassInfoVo majorName(String majorName) {
        this.majorName = majorName;
        return this;
    }

    /**
     * 专业名称
     * @return majorName
     **/
    @ApiModelProperty(value = "专业名称")


    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AdministrativeClassInfoVo administrativeClassVo = (AdministrativeClassInfoVo) o;
        return Objects.equals(this.id, administrativeClassVo.id) &&
                Objects.equals(this.counsellorId, administrativeClassVo.counsellorId) &&
                Objects.equals(this.counsellorNo, administrativeClassVo.counsellorNo) &&
                Objects.equals(this.counsellorName, administrativeClassVo.counsellorName) &&
                Objects.equals(this.className, administrativeClassVo.className) &&
                Objects.equals(this.year, administrativeClassVo.year) &&
                Objects.equals(this.collegeId, administrativeClassVo.collegeId) &&
                Objects.equals(this.collegeName, administrativeClassVo.collegeName) &&
                Objects.equals(this.majorId, administrativeClassVo.majorId) &&
                Objects.equals(this.majorCode, administrativeClassVo.majorCode) &&
                Objects.equals(this.majorName, administrativeClassVo.majorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, counsellorId, counsellorNo, counsellorName, className, year, collegeId, collegeName, majorId, majorCode, majorName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AdministrativeClassInfoVo {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    counsellorId: ").append(toIndentedString(counsellorId)).append("\n");
        sb.append("    counsellorNo: ").append(toIndentedString(counsellorNo)).append("\n");
        sb.append("    counsellorName: ").append(toIndentedString(counsellorName)).append("\n");
        sb.append("    className: ").append(toIndentedString(className)).append("\n");
        sb.append("    year: ").append(toIndentedString(year)).append("\n");
        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
        sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
        sb.append("    majorId: ").append(toIndentedString(majorId)).append("\n");
        sb.append("    majorCode: ").append(toIndentedString(majorCode)).append("\n");
        sb.append("    majorName: ").append(toIndentedString(majorName)).append("\n");
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

