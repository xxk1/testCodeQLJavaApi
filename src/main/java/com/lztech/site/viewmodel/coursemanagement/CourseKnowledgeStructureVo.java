package com.lztech.site.viewmodel.coursemanagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseKnowledgeStructureVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-02-22T07:41:01.018Z")


public class CourseKnowledgeStructureVo {
    @JsonProperty("content")
    private String content = null;

    @JsonProperty("structureType")
    private Integer structureType = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("versionId")
    private String versionId = null;

    @JsonProperty("operatorId")
    private String operatorId = null;

    @JsonProperty("operatorName")
    private String operatorName = null;

    @JsonProperty("parentId")
    private String parentId = null;

    @JsonProperty("knowledgePointList")
    @Valid
    private List<String> knowledgePointList = null;

    public CourseKnowledgeStructureVo content(String content) {
        this.content = content;
        return this;
    }

    /**
     * 知识结构内容
     * @return content
     **/
    @ApiModelProperty(value = "知识结构内容")


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CourseKnowledgeStructureVo structureType(Integer structureType) {
        this.structureType = structureType;
        return this;
    }

    /**
     * 知识结构类型
     * @return structureType
     **/
    @ApiModelProperty(value = "知识结构类型")


    public Integer getStructureType() {
        return structureType;
    }

    public void setStructureType(Integer structureType) {
        this.structureType = structureType;
    }

    public CourseKnowledgeStructureVo courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    /**
     * 课程id
     * @return courseId
     **/
    @ApiModelProperty(value = "课程id")


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public CourseKnowledgeStructureVo versionId(String versionId) {
        this.versionId = versionId;
        return this;
    }

    /**
     * 版本id
     * @return versionId
     **/
    @ApiModelProperty(value = "版本id")


    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public CourseKnowledgeStructureVo operatorId(String operatorId) {
        this.operatorId = operatorId;
        return this;
    }

    /**
     * 操作人id
     * @return operatorId
     **/
    @ApiModelProperty(value = "操作人id")


    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public CourseKnowledgeStructureVo operatorName(String operatorName) {
        this.operatorName = operatorName;
        return this;
    }

    /**
     * 操作人姓名
     * @return operatorName
     **/
    @ApiModelProperty(value = "操作人姓名")


    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public CourseKnowledgeStructureVo parentId(String parentId) {
        this.parentId = parentId;
        return this;
    }

    /**
     * 父级id
     * @return parentId
     **/
    @ApiModelProperty(value = "父级id")


    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public CourseKnowledgeStructureVo knowledgePointList(List<String> knowledgePointList) {
        this.knowledgePointList = knowledgePointList;
        return this;
    }

    public CourseKnowledgeStructureVo addKnowledgePointListItem(String knowledgePointListItem) {
        if (this.knowledgePointList == null) {
            this.knowledgePointList = new ArrayList<String>();
        }
        this.knowledgePointList.add(knowledgePointListItem);
        return this;
    }

    /**
     * Get knowledgePointList
     * @return knowledgePointList
     **/
    @ApiModelProperty(value = "")


    public List<String> getKnowledgePointList() {
        return knowledgePointList;
    }

    public void setKnowledgePointList(List<String> knowledgePointList) {
        this.knowledgePointList = knowledgePointList;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseKnowledgeStructureVo courseKnowledgeStructureVo = (CourseKnowledgeStructureVo) o;
        return Objects.equals(this.content, courseKnowledgeStructureVo.content) &&
                Objects.equals(this.structureType, courseKnowledgeStructureVo.structureType) &&
                Objects.equals(this.courseId, courseKnowledgeStructureVo.courseId) &&
                Objects.equals(this.versionId, courseKnowledgeStructureVo.versionId) &&
                Objects.equals(this.operatorId, courseKnowledgeStructureVo.operatorId) &&
                Objects.equals(this.operatorName, courseKnowledgeStructureVo.operatorName) &&
                Objects.equals(this.parentId, courseKnowledgeStructureVo.parentId) &&
                Objects.equals(this.knowledgePointList, courseKnowledgeStructureVo.knowledgePointList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, structureType, courseId, versionId, operatorId, operatorName, parentId, knowledgePointList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseKnowledgeStructureVo {\n");

        sb.append("    content: ").append(toIndentedString(content)).append("\n");
        sb.append("    structureType: ").append(toIndentedString(structureType)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    versionId: ").append(toIndentedString(versionId)).append("\n");
        sb.append("    operatorId: ").append(toIndentedString(operatorId)).append("\n");
        sb.append("    operatorName: ").append(toIndentedString(operatorName)).append("\n");
        sb.append("    parentId: ").append(toIndentedString(parentId)).append("\n");
        sb.append("    knowledgePointList: ").append(toIndentedString(knowledgePointList)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

