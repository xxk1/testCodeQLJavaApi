package com.lztech.site.viewmodel.coursemanagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * KnowledgePointResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-02-26T08:27:32.565Z")


public class KnowledgePointResource {
    @JsonProperty("pointId")
    private String pointId = null;

    @JsonProperty("pointName")
    private String pointName = null;

    public KnowledgePointResource pointId(String pointId) {
        this.pointId = pointId;
        return this;
    }

    /**
     * 知识点id
     *
     * @return pointId
     **/
    @ApiModelProperty(value = "知识点id")


    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
    }

    public KnowledgePointResource pointName(String pointName) {
        this.pointName = pointName;
        return this;
    }

    /**
     * 知识点名称
     *
     * @return pointName
     **/
    @ApiModelProperty(value = "知识点名称")


    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        KnowledgePointResource knowledgePointResource = (KnowledgePointResource) o;
        return Objects.equals(this.pointId, knowledgePointResource.pointId) &&
                Objects.equals(this.pointName, knowledgePointResource.pointName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pointId, pointName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class KnowledgePointResource {\n");

        sb.append("    pointId: ").append(toIndentedString(pointId)).append("\n");
        sb.append("    pointName: ").append(toIndentedString(pointName)).append("\n");
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

