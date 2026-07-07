package com.lztech.site.viewmodel.projectcard;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ProjectCardPageVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-24T03:19:17.736Z")


public class ProjectCardPageVo {
    @JsonProperty("total")
    private Integer total = null;

    @JsonProperty("page")
    private Integer page = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    @JsonProperty("ProjectCardModels")
    @Valid
    private List<ProjectCardModel> projectCardModels = null;

    public ProjectCardPageVo total(Integer total) {
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

    public ProjectCardPageVo page(Integer page) {
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

    public ProjectCardPageVo pageSize(Integer pageSize) {
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

    public ProjectCardPageVo projectCardModels(List<ProjectCardModel> projectCardModels) {
        this.projectCardModels = projectCardModels;
        return this;
    }

    public ProjectCardPageVo addProjectCardModelsItem(ProjectCardModel projectCardModelsItem) {
        if (this.projectCardModels == null) {
            this.projectCardModels = new ArrayList<ProjectCardModel>();
        }
        this.projectCardModels.add(projectCardModelsItem);
        return this;
    }

    /**
     * Get projectCardModels
     *
     * @return projectCardModels
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<ProjectCardModel> getProjectCardModels() {
        return projectCardModels;
    }

    public void setProjectCardModels(List<ProjectCardModel> projectCardModels) {
        this.projectCardModels = projectCardModels;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProjectCardPageVo projectCardPageVo = (ProjectCardPageVo) o;
        return Objects.equals(this.total, projectCardPageVo.total) &&
                Objects.equals(this.page, projectCardPageVo.page) &&
                Objects.equals(this.pageSize, projectCardPageVo.pageSize) &&
                Objects.equals(this.projectCardModels, projectCardPageVo.projectCardModels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, page, pageSize, projectCardModels);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ProjectCardPageVo {\n");

        sb.append("    total: ").append(toIndentedString(total)).append("\n");
        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    projectCardModels: ").append(toIndentedString(projectCardModels)).append("\n");
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

