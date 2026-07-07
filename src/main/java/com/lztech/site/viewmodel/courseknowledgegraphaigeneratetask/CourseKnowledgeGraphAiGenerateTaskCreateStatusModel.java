package com.lztech.site.viewmodel.courseknowledgegraphaigeneratetask;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseKnowledgeGraphAiGenerateTaskCreateStatusModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-01-31T03:06:00.478Z")

public class CourseKnowledgeGraphAiGenerateTaskCreateStatusModel {

    @JsonProperty("status")
    private Integer status = null;

    @JsonProperty("remark")
    private String remark = null;

    public CourseKnowledgeGraphAiGenerateTaskCreateStatusModel status(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * 任务状态：0:无法生成;1:允许生成
     *
     * @return status
     **/
    @ApiModelProperty(value = "任务状态：0:无法生成;1:允许生成")


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public CourseKnowledgeGraphAiGenerateTaskCreateStatusModel remark(String remark) {
        this.remark = remark;
        return this;
    }

    /**
     * 备注
     *
     * @return remark
     **/
    @ApiModelProperty(value = "备注")


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseKnowledgeGraphAiGenerateTaskCreateStatusModel that = (CourseKnowledgeGraphAiGenerateTaskCreateStatusModel) o;
        return Objects.equals(status, that.status) && Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, remark);
    }

    @Override
    public String toString() {
        return "CourseKnowledgeGraphAiGenerateTaskCreateStatusModel{" +
                "status=" + status +
                ", remark='" + remark + '\'' +
                '}';
    }
}
