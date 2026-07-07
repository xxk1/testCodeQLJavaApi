package com.lztech.site.viewmodel.courseknowledgegraph;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ImportCourseKnowledgeGraphNodeResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-02-22T12:32:45.867Z")


public class ImportCourseKnowledgeGraphNodeResource {
    @JsonProperty("message")
    private String message = null;

    @JsonProperty("allNumber")
    private Integer allNumber = null;

    @JsonProperty("repeatNumber")
    private Integer repeatNumber = null;

    @JsonProperty("successNumber")
    private Integer successNumber = null;

    public ImportCourseKnowledgeGraphNodeResource message(String message) {
        this.message = message;
        return this;
    }

    /**
     * 返回消息
     *
     * @return message
     **/
    @ApiModelProperty(value = "返回消息")


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ImportCourseKnowledgeGraphNodeResource allNumber(Integer allNumber) {
        this.allNumber = allNumber;
        return this;
    }

    /**
     * 所有数量
     *
     * @return allNumber
     **/
    @ApiModelProperty(value = "所有数量")


    public Integer getAllNumber() {
        return allNumber;
    }

    public void setAllNumber(Integer allNumber) {
        this.allNumber = allNumber;
    }

    public ImportCourseKnowledgeGraphNodeResource repeatNumber(Integer repeatNumber) {
        this.repeatNumber = repeatNumber;
        return this;
    }

    /**
     * 重复数量
     *
     * @return repeatNumber
     **/
    @ApiModelProperty(value = "重复数量")


    public Integer getRepeatNumber() {
        return repeatNumber;
    }

    public void setRepeatNumber(Integer repeatNumber) {
        this.repeatNumber = repeatNumber;
    }

    public ImportCourseKnowledgeGraphNodeResource successNumber(Integer successNumber) {
        this.successNumber = successNumber;
        return this;
    }

    /**
     * 成功数量
     *
     * @return successNumber
     **/
    @ApiModelProperty(value = "成功数量")


    public Integer getSuccessNumber() {
        return successNumber;
    }

    public void setSuccessNumber(Integer successNumber) {
        this.successNumber = successNumber;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ImportCourseKnowledgeGraphNodeResource importCourseKnowledgeGraphNodeResource = (ImportCourseKnowledgeGraphNodeResource) o;
        return Objects.equals(this.message, importCourseKnowledgeGraphNodeResource.message) &&
                Objects.equals(this.allNumber, importCourseKnowledgeGraphNodeResource.allNumber) &&
                Objects.equals(this.repeatNumber, importCourseKnowledgeGraphNodeResource.repeatNumber) &&
                Objects.equals(this.successNumber, importCourseKnowledgeGraphNodeResource.successNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, allNumber, repeatNumber, successNumber);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ImportCourseKnowledgeGraphNodeResource {\n");

        sb.append("    message: ").append(toIndentedString(message)).append("\n");
        sb.append("    allNumber: ").append(toIndentedString(allNumber)).append("\n");
        sb.append("    repeatNumber: ").append(toIndentedString(repeatNumber)).append("\n");
        sb.append("    successNumber: ").append(toIndentedString(successNumber)).append("\n");
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

