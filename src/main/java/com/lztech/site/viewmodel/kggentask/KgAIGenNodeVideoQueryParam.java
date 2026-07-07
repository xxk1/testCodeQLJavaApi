package com.lztech.site.viewmodel.kggentask;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * KgAIGenNodeVideoQueryParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-07-31T14:50:52.722+08:00")

public class KgAIGenNodeVideoQueryParam {
    @JsonProperty("page")
    private Integer page = null;
    public KgAIGenNodeVideoQueryParam page(Integer page) {
        this.page = page;
        return this;
    }
    /**
     * 页码
     * @return page
     **/
    @ApiModelProperty(value = "页码")
    public Integer getPage() {
        return page;
    }
    public void setPage(Integer page) {
        this.page = page;
    }


    @JsonProperty("pageSize")
    private Integer pageSize = null;
    public KgAIGenNodeVideoQueryParam pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }
    /**
     * 每页记录数
     * @return pageSize
     **/
    @ApiModelProperty(value = "每页记录数")
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @JsonProperty("kgNodeId")
    private String kgNodeId = null;
    public KgAIGenNodeVideoQueryParam kgNodeId(String kgNodeId) {
        this.kgNodeId = kgNodeId;
        return this;
    }
    /**
     * 节点id
     * @return kgNodeId
     **/
    @ApiModelProperty(value = "节点id")
    public String getKgNodeId() {
        return kgNodeId;
    }
    public void setKgNodeId(String kgNodeId) {
        this.kgNodeId = kgNodeId;
    }

    @JsonProperty("teacherId")
    private String teacherId = null;
    public KgAIGenNodeVideoQueryParam teacherId(String teacherId) {
        this.teacherId = teacherId;
        return this;
    }
    /**
     * 老师id
     * @return teacherId
     **/
    @ApiModelProperty(value = "老师id")
    public String getTeacherId() {
        return teacherId;
    }
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @JsonProperty("schoolYear")
    private String schoolYear = null;
    public KgAIGenNodeVideoQueryParam schoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
        return this;
    }
    /**
     * 学年
     * @return schoolYear
     **/
    @ApiModelProperty(value = "学年")
    public String getSchoolYear() {
        return schoolYear;
    }
    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    @JsonProperty("term")
    private Integer term = null;
    public KgAIGenNodeVideoQueryParam term(Integer term) {
        this.term = term;
        return this;
    }
    /**
     * 学期
     * @return term
     **/
    @ApiModelProperty(value = "学期")
    public Integer getTerm() {
        return term;
    }
    public void setTerm(Integer term) {
        this.term = term;
    }

    @JsonProperty("similarityScore")
    private Double similarityScore = null;
    public KgAIGenNodeVideoQueryParam similarityScore(Double similarityScore) {
        this.similarityScore = similarityScore;
        return this;
    }
    /**
     * 相似性分数
     * @return similarityScore
     **/
    @ApiModelProperty(value = "相似性分数")
    public Double getSimilarityScore() {
        return similarityScore;
    }
    public void setSimilarityScore(Double similarityScore) {
        this.similarityScore = similarityScore;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        KgAIGenNodeVideoQueryParam kgAIGenNodeVideoQueryParam = (KgAIGenNodeVideoQueryParam) o;
        return Objects.equals(this.page, kgAIGenNodeVideoQueryParam.page) &&
                Objects.equals(this.pageSize, kgAIGenNodeVideoQueryParam.pageSize) &&
                Objects.equals(this.kgNodeId, kgAIGenNodeVideoQueryParam.kgNodeId) &&
                Objects.equals(this.teacherId, kgAIGenNodeVideoQueryParam.teacherId) &&
                Objects.equals(this.schoolYear, kgAIGenNodeVideoQueryParam.schoolYear) &&
                Objects.equals(this.term, kgAIGenNodeVideoQueryParam.term) &&
                Objects.equals(this.similarityScore, kgAIGenNodeVideoQueryParam.similarityScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, pageSize, kgNodeId, teacherId, schoolYear, term,similarityScore);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class KgAIGenNodeVideoQueryParam {\n");
        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    kgNodeId: ").append(toIndentedString(kgNodeId)).append("\n");
        sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
        sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
        sb.append("    term: ").append(toIndentedString(term)).append("\n");
        sb.append("    similarityScore: ").append(toIndentedString(similarityScore)).append("\n");
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

