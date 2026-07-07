package com.lztech.site.viewmodel.courseobjective;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseObjectiveResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-11-06T16:04:50.234+08:00")

public class CourseObjectiveResponse {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("content")
    private String content = null;

    @JsonProperty("showOrder")
    private Integer showOrder = null;

    @JsonProperty("relatedKnowledgePointNum")
    private Integer relatedKnowledgePointNum = null;

    public CourseObjectiveResponse id(String id) {
        this.id = id;
        return this;
    }

    /**
     * 课程目标ID
     *
     * @return id
     **/
    @ApiModelProperty(value = "课程目标ID")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CourseObjectiveResponse name(String name) {
        this.name = name;
        return this;
    }

    /**
     * 课程目标名称
     *
     * @return name
     **/
    @ApiModelProperty(value = "课程目标名称")


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CourseObjectiveResponse content(String content) {
        this.content = content;
        return this;
    }

    /**
     * 课程目标简介
     *
     * @return content
     **/
    @ApiModelProperty(value = "课程目标简介")


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CourseObjectiveResponse showOrder(Integer showOrder) {
        this.showOrder = showOrder;
        return this;
    }

    /**
     * 显示顺序
     *
     * @return showOrder
     **/
    @ApiModelProperty(value = "显示顺序")


    public Integer getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
    }

    public CourseObjectiveResponse relatedKnowledgePointNum(Integer relatedKnowledgePointNum) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseObjectiveResponse courseObjectiveResponse = (CourseObjectiveResponse) o;
        return Objects.equals(this.id, courseObjectiveResponse.id) &&
                Objects.equals(this.name, courseObjectiveResponse.name) &&
                Objects.equals(this.content, courseObjectiveResponse.content) &&
                Objects.equals(this.showOrder, courseObjectiveResponse.showOrder) &&
                Objects.equals(this.relatedKnowledgePointNum, courseObjectiveResponse.relatedKnowledgePointNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, content, showOrder, relatedKnowledgePointNum);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseObjectiveResponse {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    content: ").append(toIndentedString(content)).append("\n");
        sb.append("    showOrder: ").append(toIndentedString(showOrder)).append("\n");
        sb.append("    relatedKnowledgePointNum: ").append(toIndentedString(relatedKnowledgePointNum)).append("\n");
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

