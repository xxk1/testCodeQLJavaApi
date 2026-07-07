package com.lztech.site.viewmodel.coursestatistics;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * CourseStatisticsResourceVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-04-18T11:04:12.465Z")


public class CourseStatisticsResourceVo   {
  @JsonProperty("assessmentTest")
  private String assessmentTest = null;

  @JsonProperty("discussTheme")
  private String discussTheme = null;

  @JsonProperty("coursewareResource")
  private String coursewareResource = null;

  @JsonProperty("videoResource")
  private String videoResource = null;

  @JsonProperty("imgResource")
  private String imgResource = null;

  @JsonProperty("totalResource")
  private String totalResource = null;

  @JsonProperty("topTen")
  @Valid
  private List<CourseStatisticsResourceTopVo> topTen = null;

  public CourseStatisticsResourceVo assessmentTest(String assessmentTest) {
    this.assessmentTest = assessmentTest;
    return this;
  }

  /**
   * 考核测验数量
   * @return assessmentTest
  **/
  @ApiModelProperty(value = "考核测验数量")


  public String getAssessmentTest() {
    return assessmentTest;
  }

  public void setAssessmentTest(String assessmentTest) {
    this.assessmentTest = assessmentTest;
  }

  public CourseStatisticsResourceVo discussTheme(String discussTheme) {
    this.discussTheme = discussTheme;
    return this;
  }

  /**
   * 讨论主题数量
   * @return discussTheme
  **/
  @ApiModelProperty(value = "讨论主题数量")


  public String getDiscussTheme() {
    return discussTheme;
  }

  public void setDiscussTheme(String discussTheme) {
    this.discussTheme = discussTheme;
  }

  public CourseStatisticsResourceVo coursewareResource(String coursewareResource) {
    this.coursewareResource = coursewareResource;
    return this;
  }

  /**
   * 课件资源数量
   * @return coursewareResource
  **/
  @ApiModelProperty(value = "课件资源数量")


  public String getCoursewareResource() {
    return coursewareResource;
  }

  public void setCoursewareResource(String coursewareResource) {
    this.coursewareResource = coursewareResource;
  }

  public CourseStatisticsResourceVo videoResource(String videoResource) {
    this.videoResource = videoResource;
    return this;
  }

  /**
   * 视频资源数量
   * @return videoResource
  **/
  @ApiModelProperty(value = "视频资源数量")


  public String getVideoResource() {
    return videoResource;
  }

  public void setVideoResource(String videoResource) {
    this.videoResource = videoResource;
  }

  public CourseStatisticsResourceVo imgResource(String imgResource) {
    this.imgResource = imgResource;
    return this;
  }

  /**
   * 图片资源数量
   * @return imgResource
  **/
  @ApiModelProperty(value = "图片资源数量")


  public String getImgResource() {
    return imgResource;
  }

  public void setImgResource(String imgResource) {
    this.imgResource = imgResource;
  }

  public CourseStatisticsResourceVo totalResource(String totalResource) {
    this.totalResource = totalResource;
    return this;
  }

  /**
   * 资源总数
   * @return totalResource
  **/
  @ApiModelProperty(value = "资源总数")


  public String getTotalResource() {
    return totalResource;
  }

  public void setTotalResource(String totalResource) {
    this.totalResource = totalResource;
  }

  public CourseStatisticsResourceVo topTen(List<CourseStatisticsResourceTopVo> topTen) {
    this.topTen = topTen;
    return this;
  }

  public CourseStatisticsResourceVo addTopTenItem(CourseStatisticsResourceTopVo topTenItem) {
    if (this.topTen == null) {
      this.topTen = new ArrayList<CourseStatisticsResourceTopVo>();
    }
    this.topTen.add(topTenItem);
    return this;
  }

  /**
   * Get topTen
   * @return topTen
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<CourseStatisticsResourceTopVo> getTopTen() {
    return topTen;
  }

  public void setTopTen(List<CourseStatisticsResourceTopVo> topTen) {
    this.topTen = topTen;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CourseStatisticsResourceVo courseStatisticsResourceVo = (CourseStatisticsResourceVo) o;
    return Objects.equals(this.assessmentTest, courseStatisticsResourceVo.assessmentTest) &&
        Objects.equals(this.discussTheme, courseStatisticsResourceVo.discussTheme) &&
        Objects.equals(this.coursewareResource, courseStatisticsResourceVo.coursewareResource) &&
        Objects.equals(this.videoResource, courseStatisticsResourceVo.videoResource) &&
        Objects.equals(this.imgResource, courseStatisticsResourceVo.imgResource) &&
        Objects.equals(this.totalResource, courseStatisticsResourceVo.totalResource) &&
        Objects.equals(this.topTen, courseStatisticsResourceVo.topTen);
  }

  @Override
  public int hashCode() {
    return Objects.hash(assessmentTest, discussTheme, coursewareResource, videoResource, imgResource, totalResource, topTen);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CourseStatisticsResourceVo {\n");
    
    sb.append("    assessmentTest: ").append(toIndentedString(assessmentTest)).append("\n");
    sb.append("    discussTheme: ").append(toIndentedString(discussTheme)).append("\n");
    sb.append("    coursewareResource: ").append(toIndentedString(coursewareResource)).append("\n");
    sb.append("    videoResource: ").append(toIndentedString(videoResource)).append("\n");
    sb.append("    imgResource: ").append(toIndentedString(imgResource)).append("\n");
    sb.append("    totalResource: ").append(toIndentedString(totalResource)).append("\n");
    sb.append("    topTen: ").append(toIndentedString(topTen)).append("\n");
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

