package com.lztech.site.viewmodel.courseobjective;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseObjectiveRelatedKnowledgeGraphNodeRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-12-01T10:31:59.126+08:00")

public class CourseObjectiveRelatedKnowledgeGraphNodeRequest {
    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseKnowledgeGraphId")
    private String courseKnowledgeGraphId = null;

    @JsonProperty("operatorId")
    private String operatorId = null;

    @JsonProperty("operatorNo")
    private String operatorNo = null;

    @JsonProperty("operatorName")
    private String operatorName = null;

    @JsonProperty("courseObjectiveId")
    private String courseObjectiveId = null;

    @JsonProperty("relatedKnowledgePointList")
    @Valid
    private List<CourseObjectiveRelatedKnowledgePoint> relatedKnowledgePointList = null;

    public CourseObjectiveRelatedKnowledgeGraphNodeRequest courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    /**
     * 课程ID
     *
     * @return courseId
     **/
    @ApiModelProperty(value = "课程ID")


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public CourseObjectiveRelatedKnowledgeGraphNodeRequest courseKnowledgeGraphId(String courseKnowledgeGraphId) {
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

    public CourseObjectiveRelatedKnowledgeGraphNodeRequest operatorId(String operatorId) {
        this.operatorId = operatorId;
        return this;
    }

    /**
     * 创建人ID
     *
     * @return operatorId
     **/
    @ApiModelProperty(value = "创建人ID")


    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public CourseObjectiveRelatedKnowledgeGraphNodeRequest operatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
        return this;
    }

    /**
     * 创建人编号
     *
     * @return operatorNo
     **/
    @ApiModelProperty(value = "创建人编号")


    public String getOperatorNo() {
        return operatorNo;
    }

    public void setOperatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
    }

    public CourseObjectiveRelatedKnowledgeGraphNodeRequest operatorName(String operatorName) {
        this.operatorName = operatorName;
        return this;
    }

    /**
     * 创建人名称
     *
     * @return operatorName
     **/
    @ApiModelProperty(value = "创建人名称")


    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public CourseObjectiveRelatedKnowledgeGraphNodeRequest courseObjectiveId(String courseObjectiveId) {
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

    public CourseObjectiveRelatedKnowledgeGraphNodeRequest relatedKnowledgePointList(List<CourseObjectiveRelatedKnowledgePoint> relatedKnowledgePointList) {
        this.relatedKnowledgePointList = relatedKnowledgePointList;
        return this;
    }

    public CourseObjectiveRelatedKnowledgeGraphNodeRequest addRelatedKnowledgePointListItem(CourseObjectiveRelatedKnowledgePoint relatedKnowledgePointListItem) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseObjectiveRelatedKnowledgeGraphNodeRequest courseObjectiveRelatedKnowledgeGraphNodeRequest = (CourseObjectiveRelatedKnowledgeGraphNodeRequest) o;
        return Objects.equals(this.courseId, courseObjectiveRelatedKnowledgeGraphNodeRequest.courseId) &&
                Objects.equals(this.courseKnowledgeGraphId, courseObjectiveRelatedKnowledgeGraphNodeRequest.courseKnowledgeGraphId) &&
                Objects.equals(this.operatorId, courseObjectiveRelatedKnowledgeGraphNodeRequest.operatorId) &&
                Objects.equals(this.operatorNo, courseObjectiveRelatedKnowledgeGraphNodeRequest.operatorNo) &&
                Objects.equals(this.operatorName, courseObjectiveRelatedKnowledgeGraphNodeRequest.operatorName) &&
                Objects.equals(this.courseObjectiveId, courseObjectiveRelatedKnowledgeGraphNodeRequest.courseObjectiveId) &&
                Objects.equals(this.relatedKnowledgePointList, courseObjectiveRelatedKnowledgeGraphNodeRequest.relatedKnowledgePointList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseKnowledgeGraphId, operatorId, operatorNo, operatorName, courseObjectiveId, relatedKnowledgePointList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseObjectiveRelatedKnowledgeGraphNodeRequest {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseKnowledgeGraphId: ").append(toIndentedString(courseKnowledgeGraphId)).append("\n");
        sb.append("    operatorId: ").append(toIndentedString(operatorId)).append("\n");
        sb.append("    operatorNo: ").append(toIndentedString(operatorNo)).append("\n");
        sb.append("    operatorName: ").append(toIndentedString(operatorName)).append("\n");
        sb.append("    courseObjectiveId: ").append(toIndentedString(courseObjectiveId)).append("\n");
        sb.append("    relatedKnowledgePointList: ").append(toIndentedString(relatedKnowledgePointList)).append("\n");
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

