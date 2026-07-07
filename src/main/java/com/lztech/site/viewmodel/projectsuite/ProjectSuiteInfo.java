package com.lztech.site.viewmodel.projectsuite;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ProjectSuiteInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-23T08:11:59.643Z")


public class ProjectSuiteInfo {
    @JsonProperty("projectSuiteId")
    private String projectSuiteId = null;

    @JsonProperty("projectSuiteName")
    private String projectSuiteName = null;

    @JsonProperty("ProjectSuiteType")
    private Integer projectSuiteType = null;

    @JsonProperty("articlesList")
    @Valid
    private List<ArticlesModel> articlesList = null;

    public ProjectSuiteInfo projectSuiteId(String projectSuiteId) {
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

    public ProjectSuiteInfo projectSuiteName(String projectSuiteName) {
        this.projectSuiteName = projectSuiteName;
        return this;
    }

    /**
     * 项目套件名称
     *
     * @return projectSuiteName
     **/
    @ApiModelProperty(value = "项目套件名称")


    public String getProjectSuiteName() {
        return projectSuiteName;
    }

    public void setProjectSuiteName(String projectSuiteName) {
        this.projectSuiteName = projectSuiteName;
    }

    public ProjectSuiteInfo projectSuiteType(Integer projectSuiteType) {
        this.projectSuiteType = projectSuiteType;
        return this;
    }

    /**
     * 0:设备仪器;1:低值易耗
     *
     * @return projectSuiteType
     **/
    @ApiModelProperty(value = "0:设备仪器;1:低值易耗")


    public Integer getProjectSuiteType() {
        return projectSuiteType;
    }

    public void setProjectSuiteType(Integer projectSuiteType) {
        this.projectSuiteType = projectSuiteType;
    }

    public ProjectSuiteInfo articlesList(List<ArticlesModel> articlesList) {
        this.articlesList = articlesList;
        return this;
    }

    public ProjectSuiteInfo addArticlesListItem(ArticlesModel articlesListItem) {
        if (this.articlesList == null) {
            this.articlesList = new ArrayList<ArticlesModel>();
        }
        this.articlesList.add(articlesListItem);
        return this;
    }

    /**
     * Get articlesList
     *
     * @return articlesList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<ArticlesModel> getArticlesList() {
        return articlesList;
    }

    public void setArticlesList(List<ArticlesModel> articlesList) {
        this.articlesList = articlesList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProjectSuiteInfo projectSuiteInfo = (ProjectSuiteInfo) o;
        return Objects.equals(this.projectSuiteId, projectSuiteInfo.projectSuiteId) &&
                Objects.equals(this.projectSuiteName, projectSuiteInfo.projectSuiteName) &&
                Objects.equals(this.projectSuiteType, projectSuiteInfo.projectSuiteType) &&
                Objects.equals(this.articlesList, projectSuiteInfo.articlesList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectSuiteId, projectSuiteName, projectSuiteType, articlesList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ProjectSuiteInfo {\n");

        sb.append("    projectSuiteId: ").append(toIndentedString(projectSuiteId)).append("\n");
        sb.append("    projectSuiteName: ").append(toIndentedString(projectSuiteName)).append("\n");
        sb.append("    projectSuiteType: ").append(toIndentedString(projectSuiteType)).append("\n");
        sb.append("    articlesList: ").append(toIndentedString(articlesList)).append("\n");
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

