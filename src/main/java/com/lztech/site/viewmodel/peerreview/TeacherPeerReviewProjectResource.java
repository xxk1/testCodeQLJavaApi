package com.lztech.site.viewmodel.peerreview;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * TeacherPeerReviewProjectResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-12-22T02:31:37.314Z")


public class TeacherPeerReviewProjectResource {
    @JsonProperty("projectId")
    private String projectId = null;

    @JsonProperty("projectCode")
    private String projectCode = null;

    @JsonProperty("projectName")
    private String projectName = null;

    public TeacherPeerReviewProjectResource projectId(String projectId) {
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

    public TeacherPeerReviewProjectResource projectCode(String projectCode) {
        this.projectCode = projectCode;
        return this;
    }

    /**
     * 项目编码
     *
     * @return projectCode
     **/
    @ApiModelProperty(value = "项目编码")


    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public TeacherPeerReviewProjectResource projectName(String projectName) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeacherPeerReviewProjectResource teacherPeerReviewProjectResource = (TeacherPeerReviewProjectResource) o;
        return Objects.equals(this.projectId, teacherPeerReviewProjectResource.projectId) &&
                Objects.equals(this.projectCode, teacherPeerReviewProjectResource.projectCode) &&
                Objects.equals(this.projectName, teacherPeerReviewProjectResource.projectName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, projectCode, projectName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TeacherPeerReviewProjectResource {\n");

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

