package com.lztech.site.viewmodel.wisdomcourse;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * WisdomCourseTaskPageParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-08-13T15:53:10.736+08:00")

public class WisdomCourseTaskPageParam {
    @JsonProperty("wisdomCourseId")
    private String wisdomCourseId = null;

    @JsonProperty("pageNum")
    private Integer pageNum = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    @JsonProperty("taskType")
    private Integer taskType = null;

    @JsonProperty("taskStatus")
    private Integer taskStatus = null;

    public WisdomCourseTaskPageParam wisdomCourseId(String wisdomCourseId) {
        this.wisdomCourseId = wisdomCourseId;
        return this;
    }

    /**
     * 智慧课程id
     *
     * @return wisdomCourseId
     **/
    @ApiModelProperty(value = "智慧课程id")


    public String getWisdomCourseId() {
        return wisdomCourseId;
    }

    public void setWisdomCourseId(String wisdomCourseId) {
        this.wisdomCourseId = wisdomCourseId;
    }

    public WisdomCourseTaskPageParam pageNum(Integer pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    /**
     * 页码
     *
     * @return pageNum
     **/
    @ApiModelProperty(value = "页码")


    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public WisdomCourseTaskPageParam pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * 页大小
     *
     * @return pageSize
     **/
    @ApiModelProperty(value = "页大小")


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public WisdomCourseTaskPageParam taskType(Integer taskType) {
        this.taskType = taskType;
        return this;
    }

    /**
     * 任务类型 0:文件解析-教材；1：文件解析-课件；2：语音转写；3：课堂总结；4：课堂要点；5：知识图谱；6：课堂导览；7：课堂词云；8：智能题库；9：知识图谱挂载-视频；10：知识图谱挂载-题库
     *
     * @return taskType
     **/
    @ApiModelProperty(value = "任务类型 0:文件解析-教材；1：文件解析-课件；2：语音转写；3：课堂总结；4：课堂要点；5：知识图谱；6：课堂导览；7：课堂词云；8：智能题库；9：知识图谱挂载-视频；10：知识图谱挂载-题库")


    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public WisdomCourseTaskPageParam taskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
        return this;
    }

    /**
     * 任务状态 0：待处理；1：处理中；2：已完成；3：处理失败
     *
     * @return taskStatus
     **/
    @ApiModelProperty(value = "任务状态 0：待处理；1：处理中；2：已完成；3：处理失败")


    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WisdomCourseTaskPageParam wisdomCourseTaskPageParam = (WisdomCourseTaskPageParam) o;
        return Objects.equals(this.wisdomCourseId, wisdomCourseTaskPageParam.wisdomCourseId) &&
                Objects.equals(this.pageNum, wisdomCourseTaskPageParam.pageNum) &&
                Objects.equals(this.pageSize, wisdomCourseTaskPageParam.pageSize) &&
                Objects.equals(this.taskType, wisdomCourseTaskPageParam.taskType) &&
                Objects.equals(this.taskStatus, wisdomCourseTaskPageParam.taskStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wisdomCourseId, pageNum, pageSize, taskType, taskStatus);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class WisdomCourseTaskPageParam {\n");

        sb.append("    wisdomCourseId: ").append(toIndentedString(wisdomCourseId)).append("\n");
        sb.append("    pageNum: ").append(toIndentedString(pageNum)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    taskType: ").append(toIndentedString(taskType)).append("\n");
        sb.append("    taskStatus: ").append(toIndentedString(taskStatus)).append("\n");
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

