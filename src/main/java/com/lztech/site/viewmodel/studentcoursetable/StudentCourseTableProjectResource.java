package com.lztech.site.viewmodel.studentcoursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * StudentCourseTableProjectResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-11-15T08:46:21.704Z")


public class StudentCourseTableProjectResource {
    @JsonProperty("projectId")
    private String projectId = null;

    @JsonProperty("projectCode")
    private String projectCode = null;

    @JsonProperty("projectName")
    private String projectName = null;

    @JsonProperty("isInClass")
    private Integer isInClass = null;

    public StudentCourseTableProjectResource projectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    /**
     * 项目id
     *
     * @return projectId
     **/
    @ApiModelProperty(value = "项目id")


    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public StudentCourseTableProjectResource projectCode(String projectCode) {
        this.projectCode = projectCode;
        return this;
    }

    /**
     * 项目编号
     *
     * @return projectCode
     **/
    @ApiModelProperty(value = "项目编号")


    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public StudentCourseTableProjectResource projectName(String projectName) {
        this.projectName = projectName;
        return this;
    }

    /**
     * 项目名称
     *
     * @return projectName
     **/
    @ApiModelProperty(value = "项目名称")


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public StudentCourseTableProjectResource isInClass(Integer isInClass) {
        this.isInClass = isInClass;
        return this;
    }

    /**
     * 是否为上课项目 0:否；1：是
     *
     * @return isInclass
     **/
    @ApiModelProperty(value = "是否为上课项目 0:否；1：是")


    public Integer getIsInClass() {
        return isInClass;
    }

    public void setIsInclass(Integer isInClass) {
        this.isInClass = isInClass;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StudentCourseTableProjectResource studentCourseTableProjectResource = (StudentCourseTableProjectResource) o;
        return Objects.equals(this.projectId, studentCourseTableProjectResource.projectId) &&
                Objects.equals(this.projectCode, studentCourseTableProjectResource.projectCode) &&
                Objects.equals(this.projectName, studentCourseTableProjectResource.projectName) &&
                Objects.equals(this.isInClass, studentCourseTableProjectResource.isInClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, projectCode, projectName, isInClass);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class StudentCourseTableProjectResource {\n");

        sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
        sb.append("    projectCode: ").append(toIndentedString(projectCode)).append("\n");
        sb.append("    projectName: ").append(toIndentedString(projectName)).append("\n");
        sb.append("    isInClass: ").append(toIndentedString(isInClass)).append("\n");
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

