package com.lztech.site.viewmodel.courseresourceintelligentrecommendation;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * CourseResourceIntelligentRecommendationQueryParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-06-19T16:00:34.725+08:00")

public class CourseResourceIntelligentRecommendationQueryParam   {

  @JsonProperty("courseId")
  private String courseId = null;
  public CourseResourceIntelligentRecommendationQueryParam courseId(String courseId) {
    this.courseId = courseId;
    return this;
  }
  /**
   * 课程id
   * @return courseId
   **/
  @ApiModelProperty(value = "课程id")
  public String getCourseId() {
    return courseId;
  }
  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }


  @JsonProperty("courseKnowledgeGraphNodeId")
  private String courseKnowledgeGraphNodeId = null;
  public CourseResourceIntelligentRecommendationQueryParam courseKnowledgeGraphNodeId(String courseKnowledgeGraphNodeId) {
    this.courseKnowledgeGraphNodeId = courseKnowledgeGraphNodeId;
    return this;
  }
  /**
   * 课程知识图谱知识点id
   * @return courseKnowledgeGraphNodeId
   **/
  @ApiModelProperty(value = "课程知识图谱知识点id")
  public String getCourseKnowledgeGraphNodeId() {
    return courseKnowledgeGraphNodeId;
  }
  public void setCourseKnowledgeGraphNodeId(String courseKnowledgeGraphNodeId) {
    this.courseKnowledgeGraphNodeId = courseKnowledgeGraphNodeId;
  }

  @JsonProperty("courseKnowledgeGraphId")
  private String courseKnowledgeGraphId = null;
  public CourseResourceIntelligentRecommendationQueryParam courseKnowledgeGraphId(String courseKnowledgeGraphId) {
    this.courseKnowledgeGraphId = courseKnowledgeGraphId;
    return this;
  }
  /**
   * 课程知识图谱id
   * @return courseKnowledgeGraphId
   **/
  @ApiModelProperty(value = "课程知识图谱id")
  public String getCourseKnowledgeGraphId() {
    return courseKnowledgeGraphId;
  }
  public void setCourseKnowledgeGraphId(String courseKnowledgeGraphId) {
    this.courseKnowledgeGraphId = courseKnowledgeGraphId;
  }

  @JsonProperty("schoolYear")
  private String schoolYear = null;
  public CourseResourceIntelligentRecommendationQueryParam schoolYear(String schoolYear) {
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
  public CourseResourceIntelligentRecommendationQueryParam term(Integer term) {
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

  @JsonProperty("teacherIds")
  private String teacherIds = null;
  public CourseResourceIntelligentRecommendationQueryParam teacherIds(String teacherIds) {
    this.teacherIds = teacherIds;
    return this;
  }
  /**
   * 教师id,多个用逗号分隔
   * @return teacherIds
   **/
  @ApiModelProperty(value = "教师id,多个用逗号分隔")
  public String getTeacherIds() {
    return teacherIds;
  }
  public void setTeacherIds(String teacherIds) {
    this.teacherIds = teacherIds;
  }

  @JsonProperty("similarityScore")
  private Double similarityScore = null;
  public CourseResourceIntelligentRecommendationQueryParam similarityScore(Double similarityScore) {
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
    CourseResourceIntelligentRecommendationQueryParam courseResourceIntelligentRecommendationQueryParam =
            (CourseResourceIntelligentRecommendationQueryParam) o;
    return Objects.equals(this.courseId, courseResourceIntelligentRecommendationQueryParam.courseId) &&
        Objects.equals(this.courseKnowledgeGraphNodeId, courseResourceIntelligentRecommendationQueryParam.courseKnowledgeGraphNodeId) &&
        Objects.equals(this.courseKnowledgeGraphId, courseResourceIntelligentRecommendationQueryParam.courseKnowledgeGraphId) &&
        Objects.equals(this.schoolYear, courseResourceIntelligentRecommendationQueryParam.schoolYear) &&
        Objects.equals(this.term, courseResourceIntelligentRecommendationQueryParam.term) &&
        Objects.equals(this.teacherIds, courseResourceIntelligentRecommendationQueryParam.teacherIds) &&
        Objects.equals(this.similarityScore, courseResourceIntelligentRecommendationQueryParam.similarityScore);
  }

  @Override
  public int hashCode() {
    return Objects.hash(courseId, courseKnowledgeGraphNodeId, courseKnowledgeGraphId, schoolYear, term, teacherIds,similarityScore);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CourseResourceIntelligentRecommendationQueryParam {\n");

    sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
    sb.append("    courseKnowledgeGraphNodeId: ").append(toIndentedString(courseKnowledgeGraphNodeId)).append("\n");
    sb.append("    courseKnowledgeGraphId: ").append(toIndentedString(courseKnowledgeGraphId)).append("\n");
    sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
    sb.append("    term: ").append(toIndentedString(term)).append("\n");
    sb.append("    teacherIds: ").append(toIndentedString(teacherIds)).append("\n");
    sb.append("    similarityScore: ").append(toIndentedString(similarityScore)).append("\n");
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

