package com.lztech.site.viewmodel.expertinformation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ExpertListenCurriculumPageVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-04-15T08:04:04.064Z")


public class ExpertListenCurriculumPageVo {
    @JsonProperty("page")
    private Integer page = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    @JsonProperty("pageCount")
    private Integer pageCount = null;

    @JsonProperty("totalCount")
    private Integer totalCount = null;

    @JsonProperty("curriculumList")
    @Valid
    private List<ExpertListenCurriculumVo> curriculumList = null;

    public ExpertListenCurriculumPageVo page(Integer page) {
        this.page = page;
        return this;
    }

    /**
     * 当前页码
     *
     * @return page
     **/
    @ApiModelProperty(value = "当前页码")


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public ExpertListenCurriculumPageVo pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * 每页记录数
     *
     * @return pageSize
     **/
    @ApiModelProperty(value = "每页记录数")


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public ExpertListenCurriculumPageVo pageCount(Integer pageCount) {
        this.pageCount = pageCount;
        return this;
    }

    /**
     * 总页数
     *
     * @return pageCount
     **/
    @ApiModelProperty(value = "总页数")


    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public ExpertListenCurriculumPageVo totalCount(Integer totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    /**
     * 总条数
     *
     * @return totalCount
     **/
    @ApiModelProperty(value = "总条数")


    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public ExpertListenCurriculumPageVo curriculumList(List<ExpertListenCurriculumVo> curriculumList) {
        this.curriculumList = curriculumList;
        return this;
    }

    public ExpertListenCurriculumPageVo addCurriculumListItem(ExpertListenCurriculumVo curriculumListItem) {
        if (this.curriculumList == null) {
            this.curriculumList = new ArrayList<ExpertListenCurriculumVo>();
        }
        this.curriculumList.add(curriculumListItem);
        return this;
    }

    /**
     * 课程集合
     *
     * @return curriculumList
     **/
    @ApiModelProperty(value = "课程集合")

    @Valid

    public List<ExpertListenCurriculumVo> getCurriculumList() {
        return curriculumList;
    }

    public void setCurriculumList(List<ExpertListenCurriculumVo> curriculumList) {
        this.curriculumList = curriculumList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExpertListenCurriculumPageVo expertListenCurriculumPageVo = (ExpertListenCurriculumPageVo) o;
        return Objects.equals(this.page, expertListenCurriculumPageVo.page) &&
                Objects.equals(this.pageSize, expertListenCurriculumPageVo.pageSize) &&
                Objects.equals(this.pageCount, expertListenCurriculumPageVo.pageCount) &&
                Objects.equals(this.totalCount, expertListenCurriculumPageVo.totalCount) &&
                Objects.equals(this.curriculumList, expertListenCurriculumPageVo.curriculumList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, pageSize, pageCount, totalCount, curriculumList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExpertListenCurriculumPageVo {\n");

        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    pageCount: ").append(toIndentedString(pageCount)).append("\n");
        sb.append("    totalCount: ").append(toIndentedString(totalCount)).append("\n");
        sb.append("    curriculumList: ").append(toIndentedString(curriculumList)).append("\n");
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

