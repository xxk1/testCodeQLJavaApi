package com.lztech.site.viewmodel.teachingcalendarcoursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ProjectResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-08-20T12:04:45.217Z")

public class ProjectResource {
    @JsonProperty("projectId")
    private String projectId = null;

    @JsonProperty("projectCode")
    private String projectCode = null;

    @JsonProperty("projectName")
    private String projectName = null;

    @JsonProperty("projectStatus")
    private Boolean projectStatus = null;

    public ProjectResource projectId(String projectId) {
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

    public ProjectResource projectCode(String projectCode) {
        this.projectCode = projectCode;
        return this;
    }

    /**
     * 项目code
     *
     * @return projectCode
     **/
    @ApiModelProperty(value = "项目code")


    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public ProjectResource projectName(String projectName) {
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

    public ProjectResource projectStatus(Boolean projectStatus) {
        this.projectStatus = projectStatus;
        return this;
    }

    /**
     * 项目是否存在（true：存在；false：不存在）
     *
     * @return projectStatus
     **/
    @ApiModelProperty(value = "项目是否存在（true：存在；false：不存在）")


    public Boolean isProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(Boolean projectStatus) {
        this.projectStatus = projectStatus;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProjectResource projectResource = (ProjectResource) o;
        return Objects.equals(this.projectId, projectResource.projectId) &&
                Objects.equals(this.projectCode, projectResource.projectCode) &&
                Objects.equals(this.projectName, projectResource.projectName) &&
                Objects.equals(this.projectStatus, projectResource.projectStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, projectCode, projectName, projectStatus);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ProjectResource {\n");

        sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
        sb.append("    projectCode: ").append(toIndentedString(projectCode)).append("\n");
        sb.append("    projectName: ").append(toIndentedString(projectName)).append("\n");
        sb.append("    projectStatus: ").append(toIndentedString(projectStatus)).append("\n");
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

