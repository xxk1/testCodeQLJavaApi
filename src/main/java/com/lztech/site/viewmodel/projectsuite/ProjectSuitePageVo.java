package com.lztech.site.viewmodel.projectsuite;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ProjectSuitePageVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-21T08:49:32.572Z")


public class ProjectSuitePageVo {
    @JsonProperty("total")
    private Integer total = null;

    @JsonProperty("page")
    private Integer page = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    @JsonProperty("projectSuiteModels")
    @Valid
    private List<ProjectSuiteModel> projectSuiteModels = null;

    public ProjectSuitePageVo total(Integer total) {
        this.total = total;
        return this;
    }

    /**
     * 总页数
     *
     * @return total
     **/
    @ApiModelProperty(example = "10", value = "总页数")


    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public ProjectSuitePageVo page(Integer page) {
        this.page = page;
        return this;
    }

    /**
     * 当前页
     *
     * @return page
     **/
    @ApiModelProperty(example = "1", value = "当前页")


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public ProjectSuitePageVo pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * 每页个数
     *
     * @return pageSize
     **/
    @ApiModelProperty(example = "10", value = "每页个数")


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public ProjectSuitePageVo projectSuiteModels(List<ProjectSuiteModel> projectSuiteModels) {
        this.projectSuiteModels = projectSuiteModels;
        return this;
    }

    public ProjectSuitePageVo addProjectSuiteModelsItem(ProjectSuiteModel projectSuiteModelsItem) {
        if (this.projectSuiteModels == null) {
            this.projectSuiteModels = new ArrayList<ProjectSuiteModel>();
        }
        this.projectSuiteModels.add(projectSuiteModelsItem);
        return this;
    }

    /**
     * Get projectSuiteModels
     *
     * @return projectSuiteModels
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<ProjectSuiteModel> getProjectSuiteModels() {
        return projectSuiteModels;
    }

    public void setProjectSuiteModels(List<ProjectSuiteModel> projectSuiteModels) {
        this.projectSuiteModels = projectSuiteModels;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProjectSuitePageVo projectSuitePageVo = (ProjectSuitePageVo) o;
        return Objects.equals(this.total, projectSuitePageVo.total) &&
                Objects.equals(this.page, projectSuitePageVo.page) &&
                Objects.equals(this.pageSize, projectSuitePageVo.pageSize) &&
                Objects.equals(this.projectSuiteModels, projectSuitePageVo.projectSuiteModels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, page, pageSize, projectSuiteModels);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ProjectSuitePageVo {\n");

        sb.append("    total: ").append(toIndentedString(total)).append("\n");
        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    projectSuiteModels: ").append(toIndentedString(projectSuiteModels)).append("\n");
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

