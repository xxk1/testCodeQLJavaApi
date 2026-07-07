package com.lztech.site.viewmodel.coursestatistics;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * CourseStatisticsVersionVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-04-14T06:35:21.472Z")


public class CourseStatisticsVersionVo   {
  @JsonProperty("versionNum")
  private String versionNum = null;

  @JsonProperty("resourceNum")
  private BigDecimal resourceNum = null;

  @JsonProperty("activityNum")
  private BigDecimal activityNum = null;

  @JsonProperty("teamMemberNum")
  private Integer teamMemberNum = null;

  public CourseStatisticsVersionVo versionNum(String versionNum) {
    this.versionNum = versionNum;
    return this;
  }

  /**
   * 版本号
   * @return versionNum
  **/
  @ApiModelProperty(value = "版本号")


  public String getVersionNum() {
    return versionNum;
  }

  public void setVersionNum(String versionNum) {
    this.versionNum = versionNum;
  }

  public CourseStatisticsVersionVo resourceNum(BigDecimal resourceNum) {
    this.resourceNum = resourceNum;
    return this;
  }

  /**
   * 教学资源数
   * @return resourceNum
  **/
  @ApiModelProperty(value = "教学资源数")

  @Valid

  public BigDecimal getResourceNum() {
    return resourceNum;
  }

  public void setResourceNum(BigDecimal resourceNum) {
    this.resourceNum = resourceNum;
  }

  public CourseStatisticsVersionVo activityNum(BigDecimal activityNum) {
    this.activityNum = activityNum;
    return this;
  }

  /**
   * 货送资源数
   * @return activityNum
  **/
  @ApiModelProperty(value = "货送资源数")

  @Valid

  public BigDecimal getActivityNum() {
    return activityNum;
  }

  public void setActivityNum(BigDecimal activityNum) {
    this.activityNum = activityNum;
  }

  public CourseStatisticsVersionVo teamMemberNum(Integer teamMemberNum) {
    this.teamMemberNum = teamMemberNum;
    return this;
  }

  /**
   * 团队成员数
   * @return teamMemberNum
  **/
  @ApiModelProperty(value = "团队成员数")


  public Integer getTeamMemberNum() {
    return teamMemberNum;
  }

  public void setTeamMemberNum(Integer teamMemberNum) {
    this.teamMemberNum = teamMemberNum;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CourseStatisticsVersionVo courseStatisticsVersionVo = (CourseStatisticsVersionVo) o;
    return Objects.equals(this.versionNum, courseStatisticsVersionVo.versionNum) &&
        Objects.equals(this.resourceNum, courseStatisticsVersionVo.resourceNum) &&
        Objects.equals(this.activityNum, courseStatisticsVersionVo.activityNum) &&
        Objects.equals(this.teamMemberNum, courseStatisticsVersionVo.teamMemberNum);
  }

  @Override
  public int hashCode() {
    return Objects.hash(versionNum, resourceNum, activityNum, teamMemberNum);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CourseStatisticsVersionVo {\n");
    
    sb.append("    versionNum: ").append(toIndentedString(versionNum)).append("\n");
    sb.append("    resourceNum: ").append(toIndentedString(resourceNum)).append("\n");
    sb.append("    activityNum: ").append(toIndentedString(activityNum)).append("\n");
    sb.append("    teamMemberNum: ").append(toIndentedString(teamMemberNum)).append("\n");
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

