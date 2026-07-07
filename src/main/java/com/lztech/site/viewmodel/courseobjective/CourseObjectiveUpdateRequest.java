package com.lztech.site.viewmodel.courseobjective;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseObjectiveUpdateRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-11-06T15:31:22.319+08:00")

public class CourseObjectiveUpdateRequest {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("content")
    private String content = null;

    @JsonProperty("operatorId")
    private String operatorId = null;

    @JsonProperty("operatorNo")
    private String operatorNo = null;

    @JsonProperty("operatorName")
    private String operatorName = null;

    public CourseObjectiveUpdateRequest id(String id) {
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

    public CourseObjectiveUpdateRequest name(String name) {
        this.name = name;
        return this;
    }

    /**
     * 新的课程目标名称
     *
     * @return name
     **/
    @ApiModelProperty(value = "新的课程目标名称")


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CourseObjectiveUpdateRequest content(String content) {
        this.content = content;
        return this;
    }

    /**
     * 新的课程目标简介
     *
     * @return content
     **/
    @ApiModelProperty(value = "新的课程目标简介")


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CourseObjectiveUpdateRequest operatorId(String operatorId) {
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

    public CourseObjectiveUpdateRequest operatorNo(String operatorNo) {
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

    public CourseObjectiveUpdateRequest operatorName(String operatorName) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseObjectiveUpdateRequest courseObjectiveUpdateRequest = (CourseObjectiveUpdateRequest) o;
        return Objects.equals(this.id, courseObjectiveUpdateRequest.id) &&
                Objects.equals(this.name, courseObjectiveUpdateRequest.name) &&
                Objects.equals(this.content, courseObjectiveUpdateRequest.content) &&
                Objects.equals(this.operatorId, courseObjectiveUpdateRequest.operatorId) &&
                Objects.equals(this.operatorNo, courseObjectiveUpdateRequest.operatorNo) &&
                Objects.equals(this.operatorName, courseObjectiveUpdateRequest.operatorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, content, operatorId, operatorNo, operatorName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseObjectiveUpdateRequest {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    content: ").append(toIndentedString(content)).append("\n");
        sb.append("    operatorId: ").append(toIndentedString(operatorId)).append("\n");
        sb.append("    operatorNo: ").append(toIndentedString(operatorNo)).append("\n");
        sb.append("    operatorName: ").append(toIndentedString(operatorName)).append("\n");
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

