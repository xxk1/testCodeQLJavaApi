package com.lztech.site.viewmodel.courseknowledgegraph;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseKnowledgeGraphNodeQueryInfoParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-03-04T11:17:57.580Z")


public class CourseKnowledgeGraphNodeQueryInfoParam {

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseKnowledgeGraphId")
    private String courseKnowledgeGraphId = null;

    @JsonProperty("queryType")
    private Integer queryType = null;

    @JsonProperty("searchParams")
    private String searchParams = null;

    public String getCourseId() {
        return courseId;
    }
    public CourseKnowledgeGraphNodeQueryInfoParam courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    /**
     * 课程id
     *
     * @return courseId
     **/
    @ApiModelProperty(value = "课程id")

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
    public CourseKnowledgeGraphNodeQueryInfoParam queryType(Integer queryType) {
        this.queryType = queryType;
        return this;
    }

    public CourseKnowledgeGraphNodeQueryInfoParam courseKnowledgeGraphId(String courseKnowledgeGraphId) {
        this.courseKnowledgeGraphId = courseKnowledgeGraphId;
        return this;
    }

    /**
     * 课程知识图谱id
     *
     * @return courseKnowledgeGraphId
     **/
    @ApiModelProperty(value = "课程知识图谱id")

    public String getCourseKnowledgeGraphId() {
        return courseKnowledgeGraphId;
    }

    public void setCourseKnowledgeGraphId(String courseKnowledgeGraphId) {
        this.courseKnowledgeGraphId = courseKnowledgeGraphId;
    }

    /**
     * 搜索类型(0:精确搜索;1:模糊搜索)
     *
     * @return queryType
     **/
    @ApiModelProperty(value = "搜索类型(0:精确搜索;1:模糊搜索)")

    public Integer getQueryType() {
        return queryType;
    }

    public void setQueryType(Integer queryType) {
        this.queryType = queryType;
    }
    public CourseKnowledgeGraphNodeQueryInfoParam searchParams(String searchParams) {
        this.searchParams = searchParams;
        return this;
    }

    /**
     * 查询条件:知识节点名称
     *
     * @return searchParams
     **/
    @ApiModelProperty(value = "查询条件:知识节点名称")

    public String getSearchParams() {
        return searchParams;
    }

    public void setSearchParams(String searchParams) {
        this.searchParams = searchParams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseKnowledgeGraphNodeQueryInfoParam courseKnowledgeGraphNodeQueryInfoParam = (CourseKnowledgeGraphNodeQueryInfoParam) o;
        return Objects.equals(this.courseId, courseKnowledgeGraphNodeQueryInfoParam.getCourseId()) &&
                Objects.equals(this.queryType, courseKnowledgeGraphNodeQueryInfoParam.getQueryType()) &&
                Objects.equals(this.searchParams, courseKnowledgeGraphNodeQueryInfoParam.getSearchParams());
    }


    @Override
    public int hashCode() {
        return Objects.hash(courseId, queryType, searchParams );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseKnowledgeGraphNodeQueryInfoParam {\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    queryType: ").append(toIndentedString(queryType)).append("\n");
        sb.append("    searchParams: ").append(toIndentedString(searchParams)).append("\n");
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
