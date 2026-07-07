package com.lztech.site.resource.classresource;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * AdministrativeClassResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-18T08:53:47.425Z")

public class AdministrativeClassResource {
    @JsonProperty("administrativeClassName")
    private String administrativeClassName = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    @JsonProperty("majorId")
    private String majorId = null;

    @JsonProperty("majorName")
    private String majorName = null;

    @JsonProperty("studentCount")
    private Integer studentCount = null;

    @JsonProperty("checked")
    private Boolean checked = null;

    public AdministrativeClassResource administrativeClassName(String administrativeClassName) {
        this.administrativeClassName = administrativeClassName;
        return this;
    }

    /**
     * 行政班名称
     *
     * @return administrativeClassName
     **/
    @ApiModelProperty(value = "行政班名称")


    public String getAdministrativeClassName() {
        return administrativeClassName;
    }

    public void setAdministrativeClassName(String administrativeClassName) {
        this.administrativeClassName = administrativeClassName;
    }

    public AdministrativeClassResource collegeId(String collegeId) {
        this.collegeId = collegeId;
        return this;
    }

    /**
     * 所属学院id
     *
     * @return collegeId
     **/
    @ApiModelProperty(value = "所属学院id")


    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public AdministrativeClassResource collegeName(String collegeName) {
        this.collegeName = collegeName;
        return this;
    }

    /**
     * 所属学院名称
     *
     * @return collegeName
     **/
    @ApiModelProperty(value = "所属学院名称")


    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public AdministrativeClassResource majorId(String majorId) {
        this.majorId = majorId;
        return this;
    }

    /**
     * 专业id
     *
     * @return majorId
     **/
    @ApiModelProperty(value = "专业id")


    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public AdministrativeClassResource majorName(String majorName) {
        this.majorName = majorName;
        return this;
    }

    /**
     * 专业名称
     *
     * @return majorName
     **/
    @ApiModelProperty(value = "专业名称")


    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public AdministrativeClassResource studentCount(Integer studentCount) {
        this.studentCount = studentCount;
        return this;
    }

    /**
     * 班级人数
     *
     * @return studentCount
     **/
    @ApiModelProperty(value = "班级人数")


    public Integer getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }

    public AdministrativeClassResource checked(Boolean checked) {
        this.checked = checked;
        return this;
    }

    /**
     * 是否选择
     *
     * @return checked
     **/
    @ApiModelProperty(value = "是否选择")


    public Boolean isChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AdministrativeClassResource administrativeClassResource = (AdministrativeClassResource) o;
        return Objects.equals(this.administrativeClassName, administrativeClassResource.administrativeClassName) &&
                Objects.equals(this.collegeId, administrativeClassResource.collegeId) &&
                Objects.equals(this.collegeName, administrativeClassResource.collegeName) &&
                Objects.equals(this.majorId, administrativeClassResource.majorId) &&
                Objects.equals(this.majorName, administrativeClassResource.majorName) &&
                Objects.equals(this.studentCount, administrativeClassResource.studentCount) &&
                Objects.equals(this.checked, administrativeClassResource.checked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(administrativeClassName, collegeId, collegeName, majorId, majorName, studentCount, checked);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AdministrativeClassResource {\n");

        sb.append("    administrativeClassName: ").append(toIndentedString(administrativeClassName)).append("\n");
        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
        sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
        sb.append("    majorId: ").append(toIndentedString(majorId)).append("\n");
        sb.append("    majorName: ").append(toIndentedString(majorName)).append("\n");
        sb.append("    studentCount: ").append(toIndentedString(studentCount)).append("\n");
        sb.append("    checked: ").append(toIndentedString(checked)).append("\n");
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

