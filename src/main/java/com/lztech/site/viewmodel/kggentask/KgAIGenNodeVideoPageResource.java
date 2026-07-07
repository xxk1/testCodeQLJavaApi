package com.lztech.site.viewmodel.kggentask;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lztech.site.viewmodel.courseknowledgegraph.CourseKnowledgeGraphNodeVideoInfoTextModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * KgAIGenNodeVideoPageResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-07-31T14:50:52.722+08:00")

public class KgAIGenNodeVideoPageResource {
    @JsonProperty("page")
    private Integer page = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    @JsonProperty("totalCount")
    private Integer totalCount = null;

    @JsonProperty("pageCount")
    private Integer pageCount = null;

    @JsonProperty("videoTextList")
    @Valid
    private List<CourseKnowledgeGraphNodeVideoInfoTextModel> videoTextList = null;

    public KgAIGenNodeVideoPageResource page(Integer page) {
        this.page = page;
        return this;
    }

    /**
     * 页码
     *
     * @return page
     **/
    @ApiModelProperty(value = "页码")


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public KgAIGenNodeVideoPageResource pageSize(Integer pageSize) {
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

    public KgAIGenNodeVideoPageResource totalCount(Integer totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    /**
     * 总记录数
     *
     * @return totalCount
     **/
    @ApiModelProperty(value = "总记录数")


    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public KgAIGenNodeVideoPageResource pageCount(Integer pageCount) {
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

    public KgAIGenNodeVideoPageResource videoTextList(List<CourseKnowledgeGraphNodeVideoInfoTextModel> videoTextList) {
        this.videoTextList = videoTextList;
        return this;
    }

    public KgAIGenNodeVideoPageResource addVideoTextListItem(CourseKnowledgeGraphNodeVideoInfoTextModel videoTextListItem) {
        if (this.videoTextList == null) {
            this.videoTextList = new ArrayList<CourseKnowledgeGraphNodeVideoInfoTextModel>();
        }
        this.videoTextList.add(videoTextListItem);
        return this;
    }

    /**
     * Get videoTextList
     *
     * @return videoTextList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<CourseKnowledgeGraphNodeVideoInfoTextModel> getVideoTextList() {
        return videoTextList;
    }

    public void setVideoTextList(List<CourseKnowledgeGraphNodeVideoInfoTextModel> videoTextList) {
        this.videoTextList = videoTextList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        KgAIGenNodeVideoPageResource kgAIGenNodeVideoPageResource = (KgAIGenNodeVideoPageResource) o;
        return Objects.equals(this.page, kgAIGenNodeVideoPageResource.page) &&
                Objects.equals(this.pageSize, kgAIGenNodeVideoPageResource.pageSize) &&
                Objects.equals(this.totalCount, kgAIGenNodeVideoPageResource.totalCount) &&
                Objects.equals(this.pageCount, kgAIGenNodeVideoPageResource.pageCount) &&
                Objects.equals(this.videoTextList, kgAIGenNodeVideoPageResource.videoTextList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, pageSize, totalCount, pageCount, videoTextList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class KgAIGenNodeVideoPageResource {\n");

        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    totalCount: ").append(toIndentedString(totalCount)).append("\n");
        sb.append("    pageCount: ").append(toIndentedString(pageCount)).append("\n");
        sb.append("    videoTextList: ").append(toIndentedString(videoTextList)).append("\n");
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

