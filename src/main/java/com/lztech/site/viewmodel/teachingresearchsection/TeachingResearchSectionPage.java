package com.lztech.site.viewmodel.teachingresearchsection;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * TeachingResearchSectionPage
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-12-29T03:26:59.577Z")


public class TeachingResearchSectionPage {
    @JsonProperty("total")
    private Integer total = null;

    @JsonProperty("page")
    private Integer page = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    @JsonProperty("teachingResearchSectionResourceList")
    @Valid
    private List<TeachingResearchSectionResource> teachingResearchSectionResourceList = null;

    public TeachingResearchSectionPage total(Integer total) {
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

    public TeachingResearchSectionPage page(Integer page) {
        this.page = page;
        return this;
    }

    /**
     * 当前页
     *
     * @return page
     **/
    @ApiModelProperty(example = "10", value = "当前页")


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public TeachingResearchSectionPage pageSize(Integer pageSize) {
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

    public TeachingResearchSectionPage teachingResearchSectionResourceList(List<TeachingResearchSectionResource>
                                                                                   teachingResearchSectionResourceList) {
        this.teachingResearchSectionResourceList = teachingResearchSectionResourceList;
        return this;
    }

    public TeachingResearchSectionPage addTeachingResearchSectionResourceListItem(TeachingResearchSectionResource
                                                                                          teachingResearchSectionResourceListItem) {
        if (this.teachingResearchSectionResourceList == null) {
            this.teachingResearchSectionResourceList = new ArrayList<TeachingResearchSectionResource>();
        }
        this.teachingResearchSectionResourceList.add(teachingResearchSectionResourceListItem);
        return this;
    }

    /**
     * Get teachingResearchSectionResourceList
     *
     * @return teachingResearchSectionResourceList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<TeachingResearchSectionResource> getTeachingResearchSectionResourceList() {
        return teachingResearchSectionResourceList;
    }

    public void setTeachingResearchSectionResourceList(List<TeachingResearchSectionResource> teachingResearchSectionResourceList) {
        this.teachingResearchSectionResourceList = teachingResearchSectionResourceList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeachingResearchSectionPage teachingResearchSectionPage = (TeachingResearchSectionPage) o;
        return Objects.equals(this.total, teachingResearchSectionPage.total) &&
                Objects.equals(this.page, teachingResearchSectionPage.page) &&
                Objects.equals(this.pageSize, teachingResearchSectionPage.pageSize) &&
                Objects.equals(this.teachingResearchSectionResourceList, teachingResearchSectionPage.teachingResearchSectionResourceList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, page, pageSize, teachingResearchSectionResourceList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TeachingResearchSectionPage {\n");

        sb.append("    total: ").append(toIndentedString(total)).append("\n");
        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    teachingResearchSectionResourceList: ").append(toIndentedString(teachingResearchSectionResourceList)).append("\n");
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

