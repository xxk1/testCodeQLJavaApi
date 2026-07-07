package com.lztech.site.viewmodel.coursemanagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * CourseKnowledgeStructureSortVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-02-23T06:35:26.728Z")


public class CourseKnowledgeStructureSortVo {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("childList")
    @Valid
    private List<CourseKnowledgeStructureSortVo> childList = null;

    public CourseKnowledgeStructureSortVo id(String id) {
        this.id = id;
        return this;
    }

    /**
     * 知识结构id
     *
     * @return id
     **/
    @ApiModelProperty(value = "知识结构id")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CourseKnowledgeStructureSortVo childList(List<CourseKnowledgeStructureSortVo> childList) {
        this.childList = childList;
        return this;
    }


    /**
     * Get childIdList
     *
     * @return childIdList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<CourseKnowledgeStructureSortVo> getChildList() {
        return childList;
    }

    public void setChildList(List<CourseKnowledgeStructureSortVo> childList) {
        this.childList = childList;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseKnowledgeStructureSortVo courseKnowledgeStructureSortVo = (CourseKnowledgeStructureSortVo) o;
        return Objects.equals(this.id, courseKnowledgeStructureSortVo.id) &&
                Objects.equals(this.childList, courseKnowledgeStructureSortVo.childList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, childList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseKnowledgeStructureSortVo {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    childList: ").append(toIndentedString(childList)).append("\n");
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

