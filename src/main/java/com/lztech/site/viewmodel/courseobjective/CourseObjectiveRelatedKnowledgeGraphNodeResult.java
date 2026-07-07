package com.lztech.site.viewmodel.courseobjective;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lztech.site.viewmodel.courseknowledgegraph.CourseKnowledgeGraphNodeTreeModel;
import com.lztech.site.viewmodel.courseknowledgegraph.CourseKnowledgeGraphRelationshipModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseObjectiveRelatedKnowledgeGraphNodeResult
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-12-01T10:31:59.126+08:00")

public class CourseObjectiveRelatedKnowledgeGraphNodeResult {
    @JsonProperty("courseKnowledgeGraphId")
    private String courseKnowledgeGraphId = null;

    @JsonProperty("courseObjectiveId")
    private String courseObjectiveId = null;

    @JsonProperty("relatedKnowledgePointNum")
    private Integer relatedKnowledgePointNum = null;

    @JsonProperty("parentChildKnowledgeGraphRelationshipList")
    private List<CourseKnowledgeGraphRelationshipModel> parentChildKnowledgeGraphRelationshipList = null;

    @JsonProperty("relatedKnowledgePointList")
    @Valid
    private List<CourseObjectiveRelatedKnowledgePoint> relatedKnowledgePointList = null;

    @JsonProperty("nodeList")
    @Valid
    private List<CourseKnowledgeGraphNodeTreeModel> nodeList = null;

    public List<CourseKnowledgeGraphRelationshipModel> getParentChildKnowledgeGraphRelationshipList() {
        return parentChildKnowledgeGraphRelationshipList;
    }

    public void setParentChildKnowledgeGraphRelationshipList(List<CourseKnowledgeGraphRelationshipModel> parentChildKnowledgeGraphRelationshipList) {
        this.parentChildKnowledgeGraphRelationshipList = parentChildKnowledgeGraphRelationshipList;
    }

    public CourseObjectiveRelatedKnowledgeGraphNodeResult courseKnowledgeGraphId(String courseKnowledgeGraphId) {
        this.courseKnowledgeGraphId = courseKnowledgeGraphId;
        return this;
    }

    /**
     * 课程图谱ID
     *
     * @return courseKnowledgeGraphId
     **/
    @ApiModelProperty(value = "课程图谱ID")


    public String getCourseKnowledgeGraphId() {
        return courseKnowledgeGraphId;
    }

    public void setCourseKnowledgeGraphId(String courseKnowledgeGraphId) {
        this.courseKnowledgeGraphId = courseKnowledgeGraphId;
    }

    public CourseObjectiveRelatedKnowledgeGraphNodeResult courseObjectiveId(String courseObjectiveId) {
        this.courseObjectiveId = courseObjectiveId;
        return this;
    }

    /**
     * 课程目标ID
     *
     * @return courseObjectiveId
     **/
    @ApiModelProperty(value = "课程目标ID")


    public String getCourseObjectiveId() {
        return courseObjectiveId;
    }

    public void setCourseObjectiveId(String courseObjectiveId) {
        this.courseObjectiveId = courseObjectiveId;
    }

    public CourseObjectiveRelatedKnowledgeGraphNodeResult relatedKnowledgePointNum(Integer relatedKnowledgePointNum) {
        this.relatedKnowledgePointNum = relatedKnowledgePointNum;
        return this;
    }

    /**
     * 关联知识点数量
     *
     * @return relatedKnowledgePointNum
     **/
    @ApiModelProperty(value = "关联知识点数量")


    public Integer getRelatedKnowledgePointNum() {
        return relatedKnowledgePointNum;
    }

    public void setRelatedKnowledgePointNum(Integer relatedKnowledgePointNum) {
        this.relatedKnowledgePointNum = relatedKnowledgePointNum;
    }

    public CourseObjectiveRelatedKnowledgeGraphNodeResult relatedKnowledgePointList(List<CourseObjectiveRelatedKnowledgePoint> relatedKnowledgePointList) {
        this.relatedKnowledgePointList = relatedKnowledgePointList;
        return this;
    }

    public CourseObjectiveRelatedKnowledgeGraphNodeResult addRelatedKnowledgePointListItem(CourseObjectiveRelatedKnowledgePoint relatedKnowledgePointListItem) {
        if (this.relatedKnowledgePointList == null) {
            this.relatedKnowledgePointList = new ArrayList<CourseObjectiveRelatedKnowledgePoint>();
        }
        this.relatedKnowledgePointList.add(relatedKnowledgePointListItem);
        return this;
    }

    /**
     * Get relatedKnowledgePointList
     *
     * @return relatedKnowledgePointList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<CourseObjectiveRelatedKnowledgePoint> getRelatedKnowledgePointList() {
        return relatedKnowledgePointList;
    }

    public void setRelatedKnowledgePointList(List<CourseObjectiveRelatedKnowledgePoint> relatedKnowledgePointList) {
        this.relatedKnowledgePointList = relatedKnowledgePointList;
    }

    public CourseObjectiveRelatedKnowledgeGraphNodeResult nodeList(List<CourseKnowledgeGraphNodeTreeModel> nodeList) {
        this.nodeList = nodeList;
        return this;
    }

    public CourseObjectiveRelatedKnowledgeGraphNodeResult addNodeListItem(CourseKnowledgeGraphNodeTreeModel nodeListItem) {
        if (this.nodeList == null) {
            this.nodeList = new ArrayList<CourseKnowledgeGraphNodeTreeModel>();
        }
        this.nodeList.add(nodeListItem);
        return this;
    }

    /**
     * Get nodeList
     *
     * @return nodeList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<CourseKnowledgeGraphNodeTreeModel> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<CourseKnowledgeGraphNodeTreeModel> nodeList) {
        this.nodeList = nodeList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseObjectiveRelatedKnowledgeGraphNodeResult courseObjectiveRelatedKnowledgeGraphNodeResult = (CourseObjectiveRelatedKnowledgeGraphNodeResult) o;
        return Objects.equals(this.courseKnowledgeGraphId, courseObjectiveRelatedKnowledgeGraphNodeResult.courseKnowledgeGraphId) &&
                Objects.equals(this.courseObjectiveId, courseObjectiveRelatedKnowledgeGraphNodeResult.courseObjectiveId) &&
                Objects.equals(this.relatedKnowledgePointNum, courseObjectiveRelatedKnowledgeGraphNodeResult.relatedKnowledgePointNum) &&
                Objects.equals(this.relatedKnowledgePointList, courseObjectiveRelatedKnowledgeGraphNodeResult.relatedKnowledgePointList) &&
                Objects.equals(this.nodeList, courseObjectiveRelatedKnowledgeGraphNodeResult.nodeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseKnowledgeGraphId, courseObjectiveId, relatedKnowledgePointNum, relatedKnowledgePointList, nodeList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseObjectiveRelatedKnowledgeGraphNodeResult {\n");

        sb.append("    courseKnowledgeGraphId: ").append(toIndentedString(courseKnowledgeGraphId)).append("\n");
        sb.append("    courseObjectiveId: ").append(toIndentedString(courseObjectiveId)).append("\n");
        sb.append("    relatedKnowledgePointNum: ").append(toIndentedString(relatedKnowledgePointNum)).append("\n");
        sb.append("    relatedKnowledgePointList: ").append(toIndentedString(relatedKnowledgePointList)).append("\n");
        sb.append("    nodeList: ").append(toIndentedString(nodeList)).append("\n");
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

