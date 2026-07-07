package com.lztech.site.viewmodel.projectsuite;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ProjectSuiteModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-21T09:22:03.543Z")


public class ProjectSuiteModel {
    @JsonProperty("projectSuiteId")
    private Object projectSuiteId = null;

    @JsonProperty("projectSuiteName")
    private String projectSuiteName = null;

    @JsonProperty("projectSuiteTypeId")
    private String projectSuiteTypeId = null;

    @JsonProperty("projectSuiteTypeName")
    private String projectSuiteTypeName = null;

    @JsonProperty("projectSuiteDetail")
    private String projectSuiteDetail = null;

    public ProjectSuiteModel projectSuiteId(Object projectSuiteId) {
        this.projectSuiteId = projectSuiteId;
        return this;
    }

    /**
     * 套件Id
     *
     * @return projectSuiteId
     **/
    @ApiModelProperty(value = "套件Id")


    public Object getProjectSuiteId() {
        return projectSuiteId;
    }

    public void setProjectSuiteId(Object projectSuiteId) {
        this.projectSuiteId = projectSuiteId;
    }

    public ProjectSuiteModel projectSuiteName(String projectSuiteName) {
        this.projectSuiteName = projectSuiteName;
        return this;
    }

    /**
     * 套件名称
     *
     * @return projectSuiteName
     **/
    @ApiModelProperty(value = "套件名称")


    public String getProjectSuiteName() {
        return projectSuiteName;
    }

    public void setProjectSuiteName(String projectSuiteName) {
        this.projectSuiteName = projectSuiteName;
    }

    public ProjectSuiteModel projectSuiteTypeId(String projectSuiteTypeId) {
        this.projectSuiteTypeId = projectSuiteTypeId;
        return this;
    }

    /**
     * 套件类型编号
     *
     * @return projectSuiteTypeId
     **/
    @ApiModelProperty(value = "套件类型编号")


    public String getProjectSuiteTypeId() {
        return projectSuiteTypeId;
    }

    public void setProjectSuiteTypeId(String projectSuiteTypeId) {
        this.projectSuiteTypeId = projectSuiteTypeId;
    }

    public ProjectSuiteModel projectSuiteTypeName(String projectSuiteTypeName) {
        this.projectSuiteTypeName = projectSuiteTypeName;
        return this;
    }

    /**
     * 套件类型名称
     *
     * @return projectSuiteTypeName
     **/
    @ApiModelProperty(value = "套件类型名称")


    public String getProjectSuiteTypeName() {
        return projectSuiteTypeName;
    }

    public void setProjectSuiteTypeName(String projectSuiteTypeName) {
        this.projectSuiteTypeName = projectSuiteTypeName;
    }

    public ProjectSuiteModel projectSuiteDetail(String projectSuiteDetail) {
        this.projectSuiteDetail = projectSuiteDetail;
        return this;
    }

    /**
     * 项目套件明细
     *
     * @return projectSuiteDetail
     **/
    @ApiModelProperty(value = "项目套件明细")


    public String getProjectSuiteDetail() {
        return projectSuiteDetail;
    }

    public void setProjectSuiteDetail(String projectSuiteDetail) {
        this.projectSuiteDetail = projectSuiteDetail;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProjectSuiteModel projectSuiteModel = (ProjectSuiteModel) o;
        return Objects.equals(this.projectSuiteId, projectSuiteModel.projectSuiteId) &&
                Objects.equals(this.projectSuiteName, projectSuiteModel.projectSuiteName) &&
                Objects.equals(this.projectSuiteTypeId, projectSuiteModel.projectSuiteTypeId) &&
                Objects.equals(this.projectSuiteTypeName, projectSuiteModel.projectSuiteTypeName) &&
                Objects.equals(this.projectSuiteDetail, projectSuiteModel.projectSuiteDetail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectSuiteId, projectSuiteName, projectSuiteTypeId, projectSuiteTypeName, projectSuiteDetail);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ProjectSuiteModel {\n");

        sb.append("    projectSuiteId: ").append(toIndentedString(projectSuiteId)).append("\n");
        sb.append("    projectSuiteName: ").append(toIndentedString(projectSuiteName)).append("\n");
        sb.append("    projectSuiteTypeId: ").append(toIndentedString(projectSuiteTypeId)).append("\n");
        sb.append("    projectSuiteTypeName: ").append(toIndentedString(projectSuiteTypeName)).append("\n");
        sb.append("    projectSuiteDetail: ").append(toIndentedString(projectSuiteDetail)).append("\n");
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

