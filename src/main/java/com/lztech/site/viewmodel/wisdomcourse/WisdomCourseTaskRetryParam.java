package com.lztech.site.viewmodel.wisdomcourse;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * WisdomCourseTaskRetryParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-08-13T15:53:10.736+08:00")

public class WisdomCourseTaskRetryParam {
    @JsonProperty("taskIds")
    private String taskIds = null;

    @JsonProperty("operatorId")
    private String operatorId = null;

    @JsonProperty("operatorName")
    private String operatorName = null;

    @JsonProperty("operatorNo")
    private String operatorNo = null;

    public WisdomCourseTaskRetryParam taskIds(String taskIds) {
        this.taskIds = taskIds;
        return this;
    }

    /**
     * 任务id列表，多个用逗号分隔
     *
     * @return taskIds
     **/
    @ApiModelProperty(value = "任务id列表，多个用逗号分隔")


    public String getTaskIds() {
        return taskIds;
    }

    public void setTaskIds(String taskIds) {
        this.taskIds = taskIds;
    }

    public WisdomCourseTaskRetryParam operatorId(String operatorId) {
        this.operatorId = operatorId;
        return this;
    }

    /**
     * 操作人id
     *
     * @return operatorId
     **/
    @ApiModelProperty(value = "操作人id")


    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public WisdomCourseTaskRetryParam operatorName(String operatorName) {
        this.operatorName = operatorName;
        return this;
    }

    /**
     * 操作人名称
     *
     * @return operatorName
     **/
    @ApiModelProperty(value = "操作人名称")


    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public WisdomCourseTaskRetryParam operatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
        return this;
    }

    /**
     * 操作人编号
     *
     * @return operatorNo
     **/
    @ApiModelProperty(value = "操作人编号")


    public String getOperatorNo() {
        return operatorNo;
    }

    public void setOperatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WisdomCourseTaskRetryParam wisdomCourseTaskRetryParam = (WisdomCourseTaskRetryParam) o;
        return Objects.equals(this.taskIds, wisdomCourseTaskRetryParam.taskIds) &&
                Objects.equals(this.operatorId, wisdomCourseTaskRetryParam.operatorId) &&
                Objects.equals(this.operatorName, wisdomCourseTaskRetryParam.operatorName) &&
                Objects.equals(this.operatorNo, wisdomCourseTaskRetryParam.operatorNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskIds, operatorId, operatorName, operatorNo);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class WisdomCourseTaskRetryParam {\n");

        sb.append("    taskIds: ").append(toIndentedString(taskIds)).append("\n");
        sb.append("    operatorId: ").append(toIndentedString(operatorId)).append("\n");
        sb.append("    operatorName: ").append(toIndentedString(operatorName)).append("\n");
        sb.append("    operatorNo: ").append(toIndentedString(operatorNo)).append("\n");
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

