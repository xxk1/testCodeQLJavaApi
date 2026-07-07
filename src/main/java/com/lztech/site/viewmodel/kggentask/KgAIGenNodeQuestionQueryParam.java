package com.lztech.site.viewmodel.kggentask;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * KgAIGenNodeQuestionQueryParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-07-31T14:50:52.722+08:00")

public class KgAIGenNodeQuestionQueryParam {
    @JsonProperty("page")
    private Integer page = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    @JsonProperty("kgNodeId")
    private String kgNodeId = null;

    @JsonProperty("teacherId")
    private String teacherId = null;

    public KgAIGenNodeQuestionQueryParam page(Integer page) {
        this.page = page;
        return this;
    }

    /**
     * 页码
     *
     * @return page
     **/
    @ApiModelProperty(value = "页码")


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public KgAIGenNodeQuestionQueryParam pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * 每页记录数
     *
     * @return pageSize
     **/
    @ApiModelProperty(value = "每页记录数")


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public KgAIGenNodeQuestionQueryParam kgNodeId(String kgNodeId) {
        this.kgNodeId = kgNodeId;
        return this;
    }

    /**
     * 节点id
     *
     * @return kgNodeId
     **/
    @ApiModelProperty(value = "节点id")


    public String getKgNodeId() {
        return kgNodeId;
    }

    public void setKgNodeId(String kgNodeId) {
        this.kgNodeId = kgNodeId;
    }

    public KgAIGenNodeQuestionQueryParam teacherId(String teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    /**
     * 老师id
     *
     * @return teacherId
     **/
    @ApiModelProperty(value = "老师id")


    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        KgAIGenNodeQuestionQueryParam kgAIGenNodeQuestionQueryParam = (KgAIGenNodeQuestionQueryParam) o;
        return Objects.equals(this.page, kgAIGenNodeQuestionQueryParam.page) &&
                Objects.equals(this.pageSize, kgAIGenNodeQuestionQueryParam.pageSize) &&
                Objects.equals(this.kgNodeId, kgAIGenNodeQuestionQueryParam.kgNodeId) &&
                Objects.equals(this.teacherId, kgAIGenNodeQuestionQueryParam.teacherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, pageSize, kgNodeId, teacherId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class KgAIGenNodeQuestionQueryParam {\n");

        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    kgNodeId: ").append(toIndentedString(kgNodeId)).append("\n");
        sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
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

