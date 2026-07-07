package com.lztech.site.viewmodel.projectsuite;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ProjectSuiteVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-21T05:33:12.625Z")


public class ProjectSuiteVo {
    @JsonProperty("projectSuiteId")
    private String projectSuiteId = null;

    @JsonProperty("projectSuiteName")
    private String projectSuiteName = null;

    @JsonProperty("projectSuiteType")
    private Integer projectSuiteType = null;

    @JsonProperty("articlesList")
    @Valid
    private List<ArticlesVo> articlesList = null;

    public ProjectSuiteVo projectSuiteId(String projectSuiteId) {
        this.projectSuiteId = projectSuiteId;
        return this;
    }

    /**
     * 项目套件Id
     *
     * @return projectSuiteId
     **/
    @ApiModelProperty(value = "项目套件Id")


    public String getProjectSuiteId() {
        return projectSuiteId;
    }

    public void setProjectSuiteId(String projectSuiteId) {
        this.projectSuiteId = projectSuiteId;
    }

    public ProjectSuiteVo projectSuiteName(String projectSuiteName) {
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

    public ProjectSuiteVo projectSuiteType(Integer projectSuiteType) {
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

    public ProjectSuiteVo articlesList(List<ArticlesVo> articlesList) {
        this.articlesList = articlesList;
        return this;
    }

    public ProjectSuiteVo addArticlesListItem(ArticlesVo articlesListItem) {
        if (this.articlesList == null) {
            this.articlesList = new ArrayList<ArticlesVo>();
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

    public List<ArticlesVo> getArticlesList() {
        return articlesList;
    }

    public void setArticlesList(List<ArticlesVo> articlesList) {
        this.articlesList = articlesList;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProjectSuiteVo projectSuiteVo = (ProjectSuiteVo) o;
        return Objects.equals(this.projectSuiteId, projectSuiteVo.projectSuiteId) &&
                Objects.equals(this.projectSuiteName, projectSuiteVo.projectSuiteName) &&
                Objects.equals(this.projectSuiteType, projectSuiteVo.projectSuiteType) &&
                Objects.equals(this.articlesList, projectSuiteVo.articlesList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectSuiteId, projectSuiteName, projectSuiteType, articlesList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ProjectSuiteVo {\n");

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
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
