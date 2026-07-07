package com.lztech.site.viewmodel.experimentschedulecoursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ExperimentScheduleCourseTableDetailProjectVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-09-14T06:34:55.092Z")


public class ExperimentScheduleCourseTableDetailProjectVo {
    @JsonProperty("projectId")
    private String projectId = null;

    @JsonProperty("projectCode")
    private String projectCode = null;

    @JsonProperty("projectName")
    private String projectName = null;

    public ExperimentScheduleCourseTableDetailProjectVo projectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    /**
     * 项目id
     *
     * @return projectId
     **/
    @ApiModelProperty(value = "项目id", position = 1, required = true)


    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public ExperimentScheduleCourseTableDetailProjectVo projectCode(String projectCode) {
        this.projectCode = projectCode;
        return this;
    }

    /**
     * 项目code
     *
     * @return projectCode
     **/
    @ApiModelProperty(value = "项目code", position = 2, required = true)


    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public ExperimentScheduleCourseTableDetailProjectVo projectName(String projectName) {
        this.projectName = projectName;
        return this;
    }

    /**
     * 项目名称
     *
     * @return projectName
     **/
    @ApiModelProperty(value = "项目名称", position = 3, required = true)


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExperimentScheduleCourseTableDetailProjectVo experimentScheduleCourseTableDetailProjectVo =
                (ExperimentScheduleCourseTableDetailProjectVo) o;
        return Objects.equals(this.projectId, experimentScheduleCourseTableDetailProjectVo.projectId) &&
                Objects.equals(this.projectCode, experimentScheduleCourseTableDetailProjectVo.projectCode) &&
                Objects.equals(this.projectName, experimentScheduleCourseTableDetailProjectVo.projectName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, projectCode, projectName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExperimentScheduleCourseTableDetailProjectVo {\n");

        sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
        sb.append("    projectCode: ").append(toIndentedString(projectCode)).append("\n");
        sb.append("    projectName: ").append(toIndentedString(projectName)).append("\n");
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

