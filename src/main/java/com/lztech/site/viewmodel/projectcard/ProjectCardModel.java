package com.lztech.site.viewmodel.projectcard;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ProjectCardModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-24T03:19:17.736Z")


public class ProjectCardModel {
    @JsonProperty("projectCardId")
    private String projectCardId = null;

    @JsonProperty("projectId")
    private String projectId = null;

    @JsonProperty("projectName")
    private String projectName = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseCode")
    private String courseCode = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("projectCardPeriod")
    private String projectCardPeriod = null;

    @JsonProperty("projectSuiteName")
    private String projectSuiteName = null;

    @JsonProperty("articlesDetail")
    private String articlesDetail = null;

    @JsonProperty("lowValueArticlesDetail")
    private String lowValueArticlesDetail = null;

    public ProjectCardModel projectCardId(String projectCardId) {
        this.projectCardId = projectCardId;
        return this;
    }

    /**
     * 项目卡Id
     *
     * @return projectCardId
     **/
    @ApiModelProperty(value = "项目卡Id")


    public String getProjectCardId() {
        return projectCardId;
    }

    public void setProjectCardId(String projectCardId) {
        this.projectCardId = projectCardId;
    }

    public ProjectCardModel projectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    /**
     * 项目Id
     *
     * @return projectId
     **/
    @ApiModelProperty(value = "项目Id")


    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public ProjectCardModel projectName(String projectName) {
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



    public ProjectCardModel courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    /**
     * 课程id
     *
     * @return courseId
     **/
    @ApiModelProperty(value = "课程id")


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public ProjectCardModel courseCode(String courseCode) {
        this.courseCode = courseCode;
        return this;
    }

    /**
     * 课程编号
     *
     * @return courseCode
     **/
    @ApiModelProperty(value = "课程编号")


    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }


    public ProjectCardModel courseName(String courseName) {
        this.courseName = courseName;
        return this;
    }

    /**
     * 课程名称
     *
     * @return courseName
     **/
    @ApiModelProperty(value = "课程名称")


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public ProjectCardModel projectCardPeriod(String projectCardPeriod) {
        this.projectCardPeriod = projectCardPeriod;
        return this;
    }

    /**
     * 项目学时(小时)
     *
     * @return projectCardPeriod
     **/
    @ApiModelProperty(value = "项目学时(小时)")


    public String getProjectCardPeriod() {
        return projectCardPeriod;
    }

    public void setProjectCardPeriod(String projectCardPeriod) {
        this.projectCardPeriod = projectCardPeriod;
    }

    public ProjectCardModel projectSuiteName(String projectSuiteName) {
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

    public ProjectCardModel articlesDetail(String articlesDetail) {
        this.articlesDetail = articlesDetail;
        return this;
    }

    /**
     * 项目仪器明细
     *
     * @return articlesDetail
     **/
    @ApiModelProperty(value = "项目仪器明细")


    public String getArticlesDetail() {
        return articlesDetail;
    }

    public void setArticlesDetail(String articlesDetail) {
        this.articlesDetail = articlesDetail;
    }

    public ProjectCardModel lowValueArticlesDetail(String lowValueArticlesDetail) {
        this.lowValueArticlesDetail = lowValueArticlesDetail;
        return this;
    }

    /**
     * 低值易耗品明细
     *
     * @return lowValueArticlesDetail
     **/
    @ApiModelProperty(value = "低值易耗品明细")


    public String getLowValueArticlesDetail() {
        return lowValueArticlesDetail;
    }

    public void setLowValueArticlesDetail(String lowValueArticlesDetail) {
        this.lowValueArticlesDetail = lowValueArticlesDetail;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProjectCardModel projectCardModel = (ProjectCardModel) o;
        return Objects.equals(this.projectCardId, projectCardModel.projectCardId) &&
                Objects.equals(this.projectId, projectCardModel.projectId) &&
                Objects.equals(this.projectName, projectCardModel.projectName) &&
                Objects.equals(this.courseId, projectCardModel.courseId) &&
                Objects.equals(this.courseCode, projectCardModel.courseCode) &&
                Objects.equals(this.courseName, projectCardModel.courseName) &&
                Objects.equals(this.projectCardPeriod, projectCardModel.projectCardPeriod) &&
                Objects.equals(this.projectSuiteName, projectCardModel.projectSuiteName) &&
                Objects.equals(this.articlesDetail, projectCardModel.articlesDetail) &&
                Objects.equals(this.lowValueArticlesDetail, projectCardModel.lowValueArticlesDetail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectCardId, projectId,projectName, courseId, courseCode,courseName, projectCardPeriod,
                projectSuiteName, articlesDetail, lowValueArticlesDetail);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ProjectCardModel {\n");

        sb.append("    projectCardId: ").append(toIndentedString(projectCardId)).append("\n");
        sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
        sb.append("    projectName: ").append(toIndentedString(projectName)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    projectCardPeriod: ").append(toIndentedString(projectCardPeriod)).append("\n");
        sb.append("    projectSuiteName: ").append(toIndentedString(projectSuiteName)).append("\n");
        sb.append("    articlesDetail: ").append(toIndentedString(articlesDetail)).append("\n");
        sb.append("    lowValueArticlesDetail: ").append(toIndentedString(lowValueArticlesDetail)).append("\n");
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

