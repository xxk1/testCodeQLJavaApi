package com.lztech.site.viewmodel.coursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ExperimentalCourseProject
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-06-16T06:45:52.365Z")


public class ExperimentalCourseProject {
    @JsonProperty("projectName")
    private String projectName = null;

    @JsonProperty("projectCode")
    private String projectCode = null;

    @JsonProperty("projectId")
    private String projectId = null;

    public ExperimentalCourseProject projectName(String projectName) {
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

    public ExperimentalCourseProject projectCode(String projectCode) {
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

    public ExperimentalCourseProject projectId(String projectId) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExperimentalCourseProject experimentalCourseProject = (ExperimentalCourseProject) o;
        return Objects.equals(this.projectName, experimentalCourseProject.projectName) &&
                Objects.equals(this.projectCode, experimentalCourseProject.projectCode) &&
                Objects.equals(this.projectId, experimentalCourseProject.projectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectName, projectCode, projectId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExperimentalCourseProject {\n");

        sb.append("    projectName: ").append(toIndentedString(projectName)).append("\n");
        sb.append("    projectCode: ").append(toIndentedString(projectCode)).append("\n");
        sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
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

