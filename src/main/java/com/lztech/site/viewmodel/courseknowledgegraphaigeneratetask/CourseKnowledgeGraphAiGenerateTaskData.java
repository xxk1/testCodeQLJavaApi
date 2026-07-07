package com.lztech.site.viewmodel.courseknowledgegraphaigeneratetask;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseKnowledgeGraphAiGenerateTaskData
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-01-31T03:06:00.478Z")

public class CourseKnowledgeGraphAiGenerateTaskData {

    @JsonProperty("id")
    private String id = null;

    @JsonProperty("graphContent")
    private String graphContent = null;

    public CourseKnowledgeGraphAiGenerateTaskData id(String id) {
        this.id = id;
        return this;
    }

    /**
     * 任务id
     *
     * @return id
     **/
    @ApiModelProperty(value = "任务id")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public CourseKnowledgeGraphAiGenerateTaskData graphContent(String graphContent) {
        this.graphContent = graphContent;
        return this;
    }

    /**
     * 知识图谱内容
     *
     * @return graphContent
     **/
    @ApiModelProperty(value = "知识图谱内容")

    public String getGraphContent() {
        return graphContent;
    }

    public void setGraphContent(String graphContent) {
        this.graphContent = graphContent;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseKnowledgeGraphAiGenerateTaskData that = (CourseKnowledgeGraphAiGenerateTaskData) o;
        return Objects.equals(id, that.id) && Objects.equals(graphContent, that.graphContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, graphContent);
    }

    @Override
    public String toString() {
        return "CourseKnowledgeGraphAiGenerateTaskData{" +
                "id='" + id + '\'' +
                ", graphContent='" + graphContent + '\'' +
                '}';
    }
}
