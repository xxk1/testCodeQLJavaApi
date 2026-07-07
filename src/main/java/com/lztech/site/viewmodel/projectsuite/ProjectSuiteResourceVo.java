package com.lztech.site.viewmodel.projectsuite;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ProjectSuiteResourceVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-27T09:07:48.063Z")


public class ProjectSuiteResourceVo {
    @JsonProperty("projectSuiteId")
    private String projectSuiteId = null;

    @JsonProperty("number")
    private Integer number = null;

    public ProjectSuiteResourceVo projectSuiteId(String projectSuiteId) {
        this.projectSuiteId = projectSuiteId;
        return this;
    }

    /**
     * 套件id
     *
     * @return projectSuiteId
     **/
    @ApiModelProperty(value = "套件id")


    public String getProjectSuiteId() {
        return projectSuiteId;
    }

    public void setProjectSuiteId(String projectSuiteId) {
        this.projectSuiteId = projectSuiteId;
    }

    public ProjectSuiteResourceVo number(Integer number) {
        this.number = number;
        return this;
    }

    /**
     * 数量
     *
     * @return number
     **/
    @ApiModelProperty(value = "数量")


    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProjectSuiteResourceVo projectSuiteResourceVo = (ProjectSuiteResourceVo) o;
        return Objects.equals(this.projectSuiteId, projectSuiteResourceVo.projectSuiteId) &&
                Objects.equals(this.number, projectSuiteResourceVo.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectSuiteId, number);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ProjectSuiteResourceVo {\n");

        sb.append("    projectSuiteId: ").append(toIndentedString(projectSuiteId)).append("\n");
        sb.append("    number: ").append(toIndentedString(number)).append("\n");
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

