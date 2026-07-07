package com.lztech.site.viewmodel.kggentask;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * KgAIGenTeachingMaterialNodeResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-07-24T15:17:22.858+08:00")

public class KgAIGenTeachingMaterialNodeResource {
    @JsonProperty("page")
    private Integer page = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    @JsonProperty("pageCount")
    private Integer pageCount = null;

    @JsonProperty("totalCount")
    private Integer totalCount = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("nodeCount")
    private Integer nodeCount = null;

    @JsonProperty("relatedQuestionNum")
    private Integer relatedQuestionNum = null;

    @JsonProperty("relatedVideoClipNum")
    private Integer relatedVideoClipNum = null;

    @JsonProperty("kgNode")
    private KgAIGenTeachingMaterialNodeResourceItem kgNode = null;

    @Valid
    @JsonProperty("kgNodeList")
    private List<KgAIGenTeachingMaterialNodeResourceItem> kgNodeList = null;

    public Integer getRelatedQuestionNum() {
        return relatedQuestionNum;
    }

    public void setRelatedQuestionNum(Integer relatedQuestionNum) {
        this.relatedQuestionNum = relatedQuestionNum;
    }

    public Integer getRelatedVideoClipNum() {
        return relatedVideoClipNum;
    }

    public void setRelatedVideoClipNum(Integer relatedVideoClipNum) {
        this.relatedVideoClipNum = relatedVideoClipNum;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<KgAIGenTeachingMaterialNodeResourceItem> getKgNodeList() {
        return kgNodeList;
    }

    public void setKgNodeList(List<KgAIGenTeachingMaterialNodeResourceItem> kgNodeList) {
        this.kgNodeList = kgNodeList;
    }

    public KgAIGenTeachingMaterialNodeResource courseName(String courseName) {
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

    public KgAIGenTeachingMaterialNodeResource courseId(String courseId) {
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

    public KgAIGenTeachingMaterialNodeResource nodeCount(Integer nodeCount) {
        this.nodeCount = nodeCount;
        return this;
    }

    /**
     * 节点数量
     *
     * @return nodeCount
     **/
    @ApiModelProperty(value = "节点数量")


    public Integer getNodeCount() {
        return nodeCount;
    }

    public void setNodeCount(Integer nodeCount) {
        this.nodeCount = nodeCount;
    }

    public KgAIGenTeachingMaterialNodeResourceItem getKgNode() {
        return kgNode;
    }

    public void setKgNode(KgAIGenTeachingMaterialNodeResourceItem kgNode) {
        this.kgNode = kgNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        KgAIGenTeachingMaterialNodeResource kgAIGenTeachingMaterialNodeResource = (KgAIGenTeachingMaterialNodeResource) o;
        return Objects.equals(this.courseName, kgAIGenTeachingMaterialNodeResource.courseName) &&
                Objects.equals(this.courseId, kgAIGenTeachingMaterialNodeResource.courseId) &&
                Objects.equals(this.nodeCount, kgAIGenTeachingMaterialNodeResource.nodeCount) &&
                Objects.equals(this.kgNode, kgAIGenTeachingMaterialNodeResource.kgNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName, courseId, nodeCount, kgNode);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class KgAIGenTeachingMaterialNodeResource {\n");

        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    nodeCount: ").append(toIndentedString(nodeCount)).append("\n");
        sb.append("    kgNode: ").append(toIndentedString(kgNode)).append("\n");
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

