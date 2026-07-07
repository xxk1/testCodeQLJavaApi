package com.lztech.site.resource.administratorclass;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * AdministrativeClassVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-05-05T02:40:27.880Z")


public class AdministrativeClassVo   {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("teacherId")
    private String teacherId = null;

    @JsonProperty("className")
    private String className = null;

    @JsonProperty("year")
    private String year = null;

    @JsonProperty("collegeCode")
    private String collegeCode = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    @JsonProperty("majorId")
    private String majorId = null;

    @JsonProperty("majorCode")
    private String majorCode = null;

    @JsonProperty("majorName")
    private String majorName = null;

    public AdministrativeClassVo id(String id) {
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

    public AdministrativeClassVo teacherId(String teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    /**
     * 老师id
     * @return teacherId
     **/
    @ApiModelProperty(value = "老师id")


    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }


    public AdministrativeClassVo className(String className) {
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

    public AdministrativeClassVo year(String year) {
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

    public AdministrativeClassVo collegeCode(String collegeCode) {
        this.collegeCode = collegeCode;
        return this;
    }

    /**
     * 学院编号
     * @return collegeCode
     **/
    @ApiModelProperty(value = "学院编号")


    public String getCollegeCode() {
        return collegeCode;
    }

    public void setCollegeCode(String collegeCode) {
        this.collegeCode = collegeCode;
    }

    public AdministrativeClassVo collegeName(String collegeName) {
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

    public AdministrativeClassVo majorId(String majorId) {
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

    public AdministrativeClassVo majorCode(String majorCode) {
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

    public AdministrativeClassVo majorName(String majorName) {
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
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AdministrativeClassVo administrativeClassVo = (AdministrativeClassVo) o;
        return Objects.equals(this.id, administrativeClassVo.id) &&
                Objects.equals(this.teacherId, administrativeClassVo.teacherId) &&
                Objects.equals(this.className, administrativeClassVo.className) &&
                Objects.equals(this.year, administrativeClassVo.year) &&
                Objects.equals(this.collegeCode, administrativeClassVo.collegeCode) &&
                Objects.equals(this.collegeName, administrativeClassVo.collegeName) &&
                Objects.equals(this.majorId, administrativeClassVo.majorId) &&
                Objects.equals(this.majorCode, administrativeClassVo.majorCode) &&
                Objects.equals(this.majorName, administrativeClassVo.majorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teacherId, className, year, collegeCode, collegeName, majorId, majorCode, majorName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AdministrativeClassVo {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
        sb.append("    className: ").append(toIndentedString(className)).append("\n");
        sb.append("    year: ").append(toIndentedString(year)).append("\n");
        sb.append("    collegeCode: ").append(toIndentedString(collegeCode)).append("\n");
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
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

