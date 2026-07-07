package com.lztech.site.viewmodel.coursestatistics;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * CourseStatisticsKnowledgeTopVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-04-18T10:17:38.912Z")


public class CourseStatisticsKnowledgeTopVo   {
  @JsonProperty("knowledgeContent")
  private String knowledgeContent = null;

  @JsonProperty("konwledgeRelationNum")
  private String konwledgeRelationNum = null;

  public CourseStatisticsKnowledgeTopVo knowledgeContent(String knowledgeContent) {
    this.knowledgeContent = knowledgeContent;
    return this;
  }

  /**
   * 知识点文案
   * @return knowledgeContent
  **/
  @ApiModelProperty(value = "知识点文案")


  public String getKnowledgeContent() {
    return knowledgeContent;
  }

  public void setKnowledgeContent(String knowledgeContent) {
    this.knowledgeContent = knowledgeContent;
  }

  public CourseStatisticsKnowledgeTopVo konwledgeRelationNum(String konwledgeRelationNum) {
    this.konwledgeRelationNum = konwledgeRelationNum;
    return this;
  }

  /**
   * 知识点关联数量
   * @return konwledgeRelationNum
  **/
  @ApiModelProperty(value = "知识点关联数量")


  public String getKonwledgeRelationNum() {
    return konwledgeRelationNum;
  }

  public void setKonwledgeRelationNum(String konwledgeRelationNum) {
    this.konwledgeRelationNum = konwledgeRelationNum;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CourseStatisticsKnowledgeTopVo courseStatisticsKnowledgeTopVo = (CourseStatisticsKnowledgeTopVo) o;
    return Objects.equals(this.knowledgeContent, courseStatisticsKnowledgeTopVo.knowledgeContent) &&
        Objects.equals(this.konwledgeRelationNum, courseStatisticsKnowledgeTopVo.konwledgeRelationNum);
  }

  @Override
  public int hashCode() {
    return Objects.hash(knowledgeContent, konwledgeRelationNum);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CourseStatisticsKnowledgeTopVo {\n");
    
    sb.append("    knowledgeContent: ").append(toIndentedString(knowledgeContent)).append("\n");
    sb.append("    konwledgeRelationNum: ").append(toIndentedString(konwledgeRelationNum)).append("\n");
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

