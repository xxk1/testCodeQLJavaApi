package com.lztech.site.viewmodel.courseobjective;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * CourseObjectiveCreateRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-11-06T15:31:22.319+08:00")

public class CourseObjectiveCreateRequest {
    @JsonProperty("courseId")
    private String courseId = null;

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

    public CourseObjectiveCreateRequest courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    /**
     * 课程ID
     *
     * @return courseId
     **/
    @ApiModelProperty(required = true, value = "课程ID")
    @NotNull


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public CourseObjectiveCreateRequest name(String name) {
        this.name = name;
        return this;
    }

    /**
     * 课程目标名称（必填）
     *
     * @return name
     **/
    @ApiModelProperty(required = true, value = "课程目标名称（必填）")
    @NotNull


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CourseObjectiveCreateRequest content(String content) {
        this.content = content;
        return this;
    }

    /**
     * 课程目标简介（选填）
     *
     * @return content
     **/
    @ApiModelProperty(value = "课程目标简介（选填）")


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CourseObjectiveCreateRequest operatorId(String operatorId) {
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

    public CourseObjectiveCreateRequest operatorNo(String operatorNo) {
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

    public CourseObjectiveCreateRequest operatorName(String operatorName) {
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
        CourseObjectiveCreateRequest courseObjectiveCreateRequest = (CourseObjectiveCreateRequest) o;
        return Objects.equals(this.courseId, courseObjectiveCreateRequest.courseId) &&
                Objects.equals(this.name, courseObjectiveCreateRequest.name) &&
                Objects.equals(this.content, courseObjectiveCreateRequest.content) &&
                Objects.equals(this.operatorId, courseObjectiveCreateRequest.operatorId) &&
                Objects.equals(this.operatorNo, courseObjectiveCreateRequest.operatorNo) &&
                Objects.equals(this.operatorName, courseObjectiveCreateRequest.operatorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, name, content, operatorId, operatorNo, operatorName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseObjectiveCreateRequest {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
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

