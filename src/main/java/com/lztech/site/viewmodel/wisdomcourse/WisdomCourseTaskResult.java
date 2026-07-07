package com.lztech.site.viewmodel.wisdomcourse;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * WisdomCourseTaskResult
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-08-13T15:53:10.736+08:00")

public class WisdomCourseTaskResult {
    @JsonProperty("taskId")
    private String taskId = null;

    @JsonProperty("taskType")
    private Integer taskType = null;

    @JsonProperty("taskTypeName")
    private String taskTypeName = null;

    @JsonProperty("taskStatus")
    private Integer taskStatus = null;

    @JsonProperty("taskStatusName")
    private String taskStatusName = null;

    @JsonProperty("resourceName")
    private String resourceName = null;

    @JsonProperty("resourceId")
    private String resourceId = null;

    @JsonProperty("createTime")
    private String createTime = null;

    @JsonProperty("resourceType")
    private Integer resourceType = null;

    @JsonProperty("resourceTypeName")
    private String resourceTypeName = null;

    @JsonProperty("remark")
    private String remark = null;

    public WisdomCourseTaskResult remark(String remark) {
        this.remark = remark;
        return this;
    }

    /**
     * 识别结果原因
     *
     * @return remark
     **/
    @ApiModelProperty(value = "识别结果原因")


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public WisdomCourseTaskResult taskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    /**
     * 任务id
     *
     * @return taskId
     **/
    @ApiModelProperty(value = "任务id")


    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public WisdomCourseTaskResult taskType(Integer taskType) {
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

    public WisdomCourseTaskResult taskTypeName(String taskTypeName) {
        this.taskTypeName = taskTypeName;
        return this;
    }

    /**
     * 任务类型名称
     *
     * @return taskTypeName
     **/
    @ApiModelProperty(value = "任务类型名称")


    public String getTaskTypeName() {
        return taskTypeName;
    }

    public void setTaskTypeName(String taskTypeName) {
        this.taskTypeName = taskTypeName;
    }

    public WisdomCourseTaskResult taskStatus(Integer taskStatus) {
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

    public WisdomCourseTaskResult taskStatusName(String taskStatusName) {
        this.taskStatusName = taskStatusName;
        return this;
    }

    /**
     * 任务状态名称
     *
     * @return taskStatusName
     **/
    @ApiModelProperty(value = "任务状态名称")


    public String getTaskStatusName() {
        return taskStatusName;
    }

    public void setTaskStatusName(String taskStatusName) {
        this.taskStatusName = taskStatusName;
    }

    public WisdomCourseTaskResult resourceName(String resourceName) {
        this.resourceName = resourceName;
        return this;
    }

    /**
     * 资源名称
     *
     * @return resourceName
     **/
    @ApiModelProperty(value = "资源名称")


    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public WisdomCourseTaskResult createTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    /**
     * 创建时间
     *
     * @return createTime
     **/
    @ApiModelProperty(value = "创建时间")


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public WisdomCourseTaskResult resourceType(Integer resourceType) {
        this.resourceType = resourceType;
        return this;
    }

    /**
     * 资源类型 0：教材；1：课件；2：视频；3：题库
     *
     * @return resourceType
     **/
    @ApiModelProperty(value = "资源类型 0：教材；1：课件；2：视频；3：题库")


    public Integer getResourceType() {
        return resourceType;
    }

    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    public WisdomCourseTaskResult resourceTypeName(String resourceTypeName) {
        this.resourceTypeName = resourceTypeName;
        return this;
    }

    /**
     * 资源类型名称
     *
     * @return resourceTypeName
     **/
    @ApiModelProperty(value = "资源类型名称")


    public String getResourceTypeName() {
        return resourceTypeName;
    }

    public void setResourceTypeName(String resourceTypeName) {
        this.resourceTypeName = resourceTypeName;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WisdomCourseTaskResult wisdomCourseTaskResult = (WisdomCourseTaskResult) o;
        return Objects.equals(this.taskId, wisdomCourseTaskResult.taskId) &&
                Objects.equals(this.taskType, wisdomCourseTaskResult.taskType) &&
                Objects.equals(this.taskTypeName, wisdomCourseTaskResult.taskTypeName) &&
                Objects.equals(this.taskStatus, wisdomCourseTaskResult.taskStatus) &&
                Objects.equals(this.taskStatusName, wisdomCourseTaskResult.taskStatusName) &&
                Objects.equals(this.resourceName, wisdomCourseTaskResult.resourceName) &&
                Objects.equals(this.createTime, wisdomCourseTaskResult.createTime) &&
                Objects.equals(this.resourceType, wisdomCourseTaskResult.resourceType) &&
                Objects.equals(this.remark, wisdomCourseTaskResult.remark) &&
                Objects.equals(this.resourceTypeName, wisdomCourseTaskResult.resourceTypeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, taskType, taskTypeName, taskStatus,remark, taskStatusName, resourceName, createTime, resourceType, resourceTypeName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class WisdomCourseTaskResult {\n");

        sb.append("    taskId: ").append(toIndentedString(taskId)).append("\n");
        sb.append("    taskType: ").append(toIndentedString(taskType)).append("\n");
        sb.append("    taskTypeName: ").append(toIndentedString(taskTypeName)).append("\n");
        sb.append("    taskStatus: ").append(toIndentedString(taskStatus)).append("\n");
        sb.append("    taskStatusName: ").append(toIndentedString(taskStatusName)).append("\n");
        sb.append("    resourceName: ").append(toIndentedString(resourceName)).append("\n");
        sb.append("    createTime: ").append(toIndentedString(createTime)).append("\n");
        sb.append("    resourceType: ").append(toIndentedString(resourceType)).append("\n");
        sb.append("    resourceTypeName: ").append(toIndentedString(resourceTypeName)).append("\n");
        sb.append("    remark: ").append(toIndentedString(remark)).append("\n");

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

