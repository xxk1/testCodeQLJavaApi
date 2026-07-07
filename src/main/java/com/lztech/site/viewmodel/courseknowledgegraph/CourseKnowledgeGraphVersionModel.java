package com.lztech.site.viewmodel.courseknowledgegraph;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseKnowledgeGraphVersionModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-01-26T11:20:06.069Z")

public class CourseKnowledgeGraphVersionModel {

    @JsonProperty("courseKnowledgeGraphDomainId")
    private String courseKnowledgeGraphDomainId = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseCode")
    private String courseCode = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("nodeCount")
    private Integer nodeCount = null;

    @JsonProperty("shipCount")
    private Integer shipCount = null;

    @JsonProperty("showOrder")
    private Integer showOrder = null;

    @JsonProperty("versionLabel")
    private String versionLabel = null;

    @JsonProperty("modifierId")
    private String modifierId = null;

    @JsonProperty("modifierName")
    private String modifierName = null;


    @JsonProperty("modifyTime")
    private String modifyTime = null;

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public CourseKnowledgeGraphVersionModel courseKnowledgeGraphDomainId(String courseKnowledgeGraphDomainId) {
        this.courseKnowledgeGraphDomainId = courseKnowledgeGraphDomainId;
        return this;
    }

    /**
     * 课程知识图谱id
     *
     * @return courseKnowledgeGraphDomainId
     **/
    @ApiModelProperty(value = "课程知识图谱id")


    public String getCourseKnowledgeGraphDomainId() {
        return courseKnowledgeGraphDomainId;
    }

    public void setCourseKnowledgeGraphDomainId(String courseKnowledgeGraphDomainId) {
        this.courseKnowledgeGraphDomainId = courseKnowledgeGraphDomainId;
    }

    public CourseKnowledgeGraphVersionModel courseId(String courseId) {
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


    public CourseKnowledgeGraphVersionModel courseCode(String courseCode) {
        this.courseCode = courseCode;
        return this;
    }

    /**
     * 课程编号
     *
     * @return courseCode
     **/
    @ApiModelProperty(value = "课程编号")


    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public CourseKnowledgeGraphVersionModel courseName(String courseName) {
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

    public CourseKnowledgeGraphVersionModel nodeCount(Integer nodeCount) {
        this.nodeCount = nodeCount;
        return this;
    }

    /**
     * 知识点个数
     *
     * @return nodeCount
     **/
    @ApiModelProperty(value = "知识点个数")


    public Integer getNodeCount() {
        return nodeCount;
    }

    public void setNodeCount(Integer nodeCount) {
        this.nodeCount = nodeCount;
    }

    public CourseKnowledgeGraphVersionModel shipCount(Integer shipCount) {
        this.nodeCount = nodeCount;
        return this;
    }

    /**
     * 关系个数
     *
     * @return shipCount
     **/
    @ApiModelProperty(value = "关系个数")


    public Integer getShipCount() {
        return shipCount;
    }

    public void setShipCount(Integer shipCount) {
        this.shipCount = shipCount;
    }

    public CourseKnowledgeGraphVersionModel showOrder(Integer showOrder) {
        this.showOrder = showOrder;
        return this;
    }

    /**
     * 排序
     *
     * @return showOrder
     **/
    @ApiModelProperty(value = "排序")


    public Integer getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
    }

    public CourseKnowledgeGraphVersionModel versionLabel(String versionLabel) {
        this.versionLabel = versionLabel;
        return this;
    }

    /**
     * 版本标签
     *
     * @return versionLabel
     **/
    @ApiModelProperty(value = "版本标签")

    public String getVersionLabel() {
        return versionLabel;
    }

    public void setVersionLabel(String versionLabel) {
        this.versionLabel = versionLabel;
    }


    public CourseKnowledgeGraphVersionModel modifierId(String modifierId) {
        this.modifierId = modifierId;
        return this;
    }

    /**
     * 更新人id
     *
     * @return modifierId
     **/
    @ApiModelProperty(value = "更新人id")

    public String getModifierId() {
        return modifierId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }


    public CourseKnowledgeGraphVersionModel modifierName(String modifierName) {
        this.modifierName = modifierName;
        return this;
    }

    /**
     * 更新人名称
     *
     * @return modifierName
     **/
    @ApiModelProperty(value = "更新人名称")

    public String getModifierName() {
        return modifierName;
    }

    public void setModifierName(String modifierName) {
        this.modifierName = modifierName;
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CourseKnowledgeGraphVersionModel)) {
            return false;
        }
        CourseKnowledgeGraphVersionModel that = (CourseKnowledgeGraphVersionModel) o;
        return Objects.equals(courseKnowledgeGraphDomainId, that.courseKnowledgeGraphDomainId) &&
                Objects.equals(courseId, that.courseId) &&
                Objects.equals(courseCode, that.courseCode) &&
                Objects.equals(courseName, that.courseName) &&
                Objects.equals(nodeCount, that.nodeCount) &&
                Objects.equals(shipCount, that.shipCount) &&
                Objects.equals(showOrder, that.showOrder) &&
                Objects.equals(versionLabel, that.versionLabel) &&
                Objects.equals(modifierId, that.modifierId) &&
                Objects.equals(modifierName, that.modifierName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseKnowledgeGraphDomainId, courseId, courseCode,
                courseName, nodeCount, shipCount, showOrder, versionLabel, modifierId, modifierName);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CourseKnowledgeGraphVersionModel{");
        sb.append("courseKnowledgeGraphDomainId='").append(courseKnowledgeGraphDomainId).append('\'');
        sb.append(", courseId='").append(courseId).append('\'');
        sb.append(", courseCode='").append(courseCode).append('\'');
        sb.append(", courseName='").append(courseName).append('\'');
        sb.append(", nodeCount=").append(nodeCount);
        sb.append(", shipCount=").append(shipCount);
        sb.append(", showOrder=").append(showOrder);
        sb.append(", versionLabel=").append(versionLabel);
        sb.append(", modifierId=").append(modifierId);
        sb.append(", modifierName='").append(modifierName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
