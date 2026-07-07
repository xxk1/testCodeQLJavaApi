package com.lztech.site.viewmodel.courseknowledgegraph;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseKnowledgeGraphNodeFileParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-01-30T03:26:27.862Z")


public class CourseKnowledgeGraphNodeFileParam {
    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("knowledgeGraphNodeId")
    private String knowledgeGraphNodeId = null;

    @JsonProperty("courseKnowledgeGraphId")
    private String courseKnowledgeGraphId = null;

    @JsonProperty("accessUserId")
    private String accessUserId = null;

    @JsonProperty("accessUserName")
    private String accessUserName = null;

    @JsonProperty("courseKnowledgeGraphNodeFileResourceList")
    private List<CourseKnowledgeGraphNodeFileResource> courseKnowledgeGraphNodeFileResourceList = new ArrayList<>();

    public CourseKnowledgeGraphNodeFileParam courseId(String courseId) {
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

    public CourseKnowledgeGraphNodeFileParam knowledgeGraphNodeId(String knowledgeGraphNodeId) {
        this.knowledgeGraphNodeId = knowledgeGraphNodeId;
        return this;
    }

    /**
     * 课程知识图谱节点id
     *
     * @return knowledgeGraphNodeId
     **/
    @ApiModelProperty(value = "课程知识图谱节点id")


    public String getKnowledgeGraphNodeId() {
        return knowledgeGraphNodeId;
    }

    public void setKnowledgeGraphNodeId(String knowledgeGraphNodeId) {
        this.knowledgeGraphNodeId = knowledgeGraphNodeId;
    }
    public CourseKnowledgeGraphNodeFileParam courseKnowledgeGraphId(String courseKnowledgeGraphId) {
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
     * 访问用户id
     *
     * @return accessUserId
     **/
    @ApiModelProperty(value = "访问用户id")


    public String getAccessUserId() {
        return accessUserId;
    }

    public void setAccessUserId(String accessUserId) {
        this.accessUserId = accessUserId;
    }

    public CourseKnowledgeGraphNodeFileParam accessUserName(String accessUserName) {
        this.accessUserName = accessUserName;
        return this;
    }

    /**
     * 访问用户名称
     *
     * @return accessUserName
     **/
    @ApiModelProperty(value = "访问用户名称")


    public String getAccessUserName() {
        return accessUserName;
    }

    public void setAccessUserName(String accessUserName) {
        this.accessUserName = accessUserName;
    }


    public CourseKnowledgeGraphNodeFileParam courseKnowledgeGraphNodeFileResourceList(
            List<CourseKnowledgeGraphNodeFileResource> courseKnowledgeGraphNodeFileResourceList) {
        this.courseKnowledgeGraphNodeFileResourceList = courseKnowledgeGraphNodeFileResourceList;
        return this;
    }

    public CourseKnowledgeGraphNodeFileParam addRelevanceKnowledgeGraphNodeListItem(
            CourseKnowledgeGraphNodeFileResource courseKnowledgeGraphNodeFileResource) {
        if (this.courseKnowledgeGraphNodeFileResourceList == null) {
            this.courseKnowledgeGraphNodeFileResourceList = new ArrayList<CourseKnowledgeGraphNodeFileResource>();
        }
        this.courseKnowledgeGraphNodeFileResourceList.add(courseKnowledgeGraphNodeFileResource);
        return this;
    }

    /**
     * 上传课程知识图谱节点学习材料文件列表
     *
     * @return courseKnowledgeGraphNodeFileResourceList
     **/
    @ApiModelProperty(value = "上传课程知识图谱节点学习材料文件列表")

    @Valid

    public List<CourseKnowledgeGraphNodeFileResource> getCourseKnowledgeGraphNodeFileResourceList() {
        return courseKnowledgeGraphNodeFileResourceList;
    }

    public void setCourseKnowledgeGraphNodeFileResourceList(
            List<CourseKnowledgeGraphNodeFileResource> courseKnowledgeGraphNodeFileResourceList) {
        this.courseKnowledgeGraphNodeFileResourceList = courseKnowledgeGraphNodeFileResourceList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseKnowledgeGraphNodeFileParam courseKnowledgeGraphNodeFileParam = (CourseKnowledgeGraphNodeFileParam) o;
        return Objects.equals(this.courseId, courseKnowledgeGraphNodeFileParam.courseId) &&
                Objects.equals(this.knowledgeGraphNodeId, courseKnowledgeGraphNodeFileParam.knowledgeGraphNodeId) &&
                Objects.equals(this.courseKnowledgeGraphId, courseKnowledgeGraphNodeFileParam.courseKnowledgeGraphId) &&
                Objects.equals(this.accessUserId, courseKnowledgeGraphNodeFileParam.accessUserId) &&
                Objects.equals(this.accessUserName, courseKnowledgeGraphNodeFileParam.accessUserName) &&
                Objects.equals(this.courseKnowledgeGraphNodeFileResourceList,
                        courseKnowledgeGraphNodeFileParam.courseKnowledgeGraphNodeFileResourceList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, knowledgeGraphNodeId,courseKnowledgeGraphId,
                accessUserId, accessUserName, courseKnowledgeGraphNodeFileResourceList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseKnowledgeGraphNodeFileParam {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    knowledgeGraphNodeId: ").append(toIndentedString(knowledgeGraphNodeId)).append("\n");
        sb.append("    courseKnowledgeGraphId: ").append(toIndentedString(courseKnowledgeGraphId)).append("\n");
        sb.append("    accessUserId: ").append(toIndentedString(accessUserId)).append("\n");
        sb.append("    accessUserName: ").append(toIndentedString(accessUserName)).append("\n");
        sb.append("    courseKnowledgeGraphNodeFileResourceList: ")
                .append(toIndentedString(courseKnowledgeGraphNodeFileResourceList)).append("\n");
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

