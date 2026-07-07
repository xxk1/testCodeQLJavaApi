package com.lztech.site.viewmodel.coursestatistics;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * CourseStatisticsKnowledgeVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-04-18T10:17:38.912Z")


public class CourseStatisticsKnowledgeVo   {
  @JsonProperty("knowledgeNum")
  private String knowledgeNum = null;

  @JsonProperty("konwledgeTotal")
  private String konwledgeTotal = null;

  @JsonProperty("topThree")
  @Valid
  private List<CourseStatisticsKnowledgeTopVo> topThree = null;

  public CourseStatisticsKnowledgeVo knowledgeNum(String knowledgeNum) {
    this.knowledgeNum = knowledgeNum;
    return this;
  }

  /**
   * 知识点已使用数量
   * @return knowledgeNum
  **/
  @ApiModelProperty(value = "知识点已使用数量")


  public String getKnowledgeNum() {
    return knowledgeNum;
  }

  public void setKnowledgeNum(String knowledgeNum) {
    this.knowledgeNum = knowledgeNum;
  }

  public CourseStatisticsKnowledgeVo konwledgeTotal(String konwledgeTotal) {
    this.konwledgeTotal = konwledgeTotal;
    return this;
  }

  /**
   * 知识点总数
   * @return konwledgeTotal
  **/
  @ApiModelProperty(value = "知识点总数")


  public String getKonwledgeTotal() {
    return konwledgeTotal;
  }

  public void setKonwledgeTotal(String konwledgeTotal) {
    this.konwledgeTotal = konwledgeTotal;
  }

  public CourseStatisticsKnowledgeVo topThree(List<CourseStatisticsKnowledgeTopVo> topThree) {
    this.topThree = topThree;
    return this;
  }

  public CourseStatisticsKnowledgeVo addTopThreeItem(CourseStatisticsKnowledgeTopVo topThreeItem) {
    if (this.topThree == null) {
      this.topThree = new ArrayList<CourseStatisticsKnowledgeTopVo>();
    }
    this.topThree.add(topThreeItem);
    return this;
  }

  /**
   * Get topThree
   * @return topThree
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<CourseStatisticsKnowledgeTopVo> getTopThree() {
    return topThree;
  }

  public void setTopThree(List<CourseStatisticsKnowledgeTopVo> topThree) {
    this.topThree = topThree;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CourseStatisticsKnowledgeVo courseStatisticsKnowledgeVo = (CourseStatisticsKnowledgeVo) o;
    return Objects.equals(this.knowledgeNum, courseStatisticsKnowledgeVo.knowledgeNum) &&
        Objects.equals(this.konwledgeTotal, courseStatisticsKnowledgeVo.konwledgeTotal) &&
        Objects.equals(this.topThree, courseStatisticsKnowledgeVo.topThree);
  }

  @Override
  public int hashCode() {
    return Objects.hash(knowledgeNum, konwledgeTotal, topThree);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CourseStatisticsKnowledgeVo {\n");
    
    sb.append("    knowledgeNum: ").append(toIndentedString(knowledgeNum)).append("\n");
    sb.append("    konwledgeTotal: ").append(toIndentedString(konwledgeTotal)).append("\n");
    sb.append("    topThree: ").append(toIndentedString(topThree)).append("\n");
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

