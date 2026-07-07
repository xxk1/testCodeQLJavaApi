package com.lztech.site.viewmodel.wisdomcourse;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.math.BigInteger;
import java.util.Objects;

/**
 * WisdomCourseStatisticsResult
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2026-01-09T09:39:43.365+08:00")
@Data
public class WisdomCourseStatisticsResult   {
  @JsonProperty("taskType")
  private Integer taskType = null;

  @JsonProperty("taskTypeName")
  private String taskTypeName = null;

  @JsonProperty("count")
  private BigInteger count = null;

  public WisdomCourseStatisticsResult taskType(Integer taskType) {
    this.taskType = taskType;
    return this;
  }

  /**
   * 任务类型
   * @return taskType
  **/
  @ApiModelProperty(value = "任务类型")


  public Integer getTaskType() {
    return taskType;
  }

  public void setTaskType(Integer taskType) {
    this.taskType = taskType;
  }

  public WisdomCourseStatisticsResult taskTypeName(String taskTypeName) {
    this.taskTypeName = taskTypeName;
    return this;
  }

  /**
   * 任务类型名称
   * @return taskTypeName
  **/
  @ApiModelProperty(value = "任务类型名称")


  public String getTaskTypeName() {
    return taskTypeName;
  }

  public void setTaskTypeName(String taskTypeName) {
    this.taskTypeName = taskTypeName;
  }

  public WisdomCourseStatisticsResult count(BigInteger count) {
    this.count = count;
    return this;
  }

  /**
   * 统计名称
   * @return count
  **/
  @ApiModelProperty(value = "统计名称")


  public BigInteger getCount() {
    return count;
  }

  public void setCount(BigInteger count) {
    this.count = count;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WisdomCourseStatisticsResult wisdomCourseStatisticsResult = (WisdomCourseStatisticsResult) o;
    return Objects.equals(this.taskType, wisdomCourseStatisticsResult.taskType) &&
        Objects.equals(this.taskTypeName, wisdomCourseStatisticsResult.taskTypeName) &&
        Objects.equals(this.count, wisdomCourseStatisticsResult.count);
  }

  @Override
  public int hashCode() {
    return Objects.hash(taskType, taskTypeName, count);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WisdomCourseStatisticsResult {\n");
    
    sb.append("    taskType: ").append(toIndentedString(taskType)).append("\n");
    sb.append("    taskTypeName: ").append(toIndentedString(taskTypeName)).append("\n");
    sb.append("    count: ").append(toIndentedString(count)).append("\n");
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

