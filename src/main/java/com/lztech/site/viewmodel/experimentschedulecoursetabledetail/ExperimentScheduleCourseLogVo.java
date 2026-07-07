package com.lztech.site.viewmodel.experimentschedulecoursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ExperimentScheduleCourseLogVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-07-22T09:39:25.243+08:00")

public class ExperimentScheduleCourseLogVo {
    @JsonProperty("experimentScheduleId")
    private String experimentScheduleId = null;

    @JsonProperty("operationDescription")
    private String operationDescription = null;

    @JsonProperty("creatorName")
    private String creatorName = null;

    @JsonProperty("createTime")
    private String createTime = null;

    public ExperimentScheduleCourseLogVo experimentScheduleId(String experimentScheduleId) {
        this.experimentScheduleId = experimentScheduleId;
        return this;
    }

    /**
     * 实验排课待排课表id
     *
     * @return experimentScheduleId
     **/
    @ApiModelProperty(value = "实验排课待排课表id")


    public String getExperimentScheduleId() {
        return experimentScheduleId;
    }

    public void setExperimentScheduleId(String experimentScheduleId) {
        this.experimentScheduleId = experimentScheduleId;
    }

    public ExperimentScheduleCourseLogVo operationDescription(String operationDescription) {
        this.operationDescription = operationDescription;
        return this;
    }

    /**
     * 操作描述
     *
     * @return operationDescription
     **/
    @ApiModelProperty(value = "操作描述")


    public String getOperationDescription() {
        return operationDescription;
    }

    public void setOperationDescription(String operationDescription) {
        this.operationDescription = operationDescription;
    }

    public ExperimentScheduleCourseLogVo creatorName(String creatorName) {
        this.creatorName = creatorName;
        return this;
    }

    /**
     * 操作人姓名
     *
     * @return creatorName
     **/
    @ApiModelProperty(value = "操作人姓名")


    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public ExperimentScheduleCourseLogVo createTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    /**
     * 操作时间（yyyy-MM-dd HH:mm:ss）
     *
     * @return createTime
     **/
    @ApiModelProperty(value = "操作时间（yyyy-MM-dd HH:mm:ss）")


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExperimentScheduleCourseLogVo experimentScheduleCourseLogVo = (ExperimentScheduleCourseLogVo) o;
        return Objects.equals(this.experimentScheduleId, experimentScheduleCourseLogVo.experimentScheduleId) &&
                Objects.equals(this.operationDescription, experimentScheduleCourseLogVo.operationDescription) &&
                Objects.equals(this.creatorName, experimentScheduleCourseLogVo.creatorName) &&
                Objects.equals(this.createTime, experimentScheduleCourseLogVo.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(experimentScheduleId, operationDescription, creatorName, createTime);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExperimentScheduleCourseLogVo {\n");

        sb.append("    experimentScheduleId: ").append(toIndentedString(experimentScheduleId)).append("\n");
        sb.append("    operationDescription: ").append(toIndentedString(operationDescription)).append("\n");
        sb.append("    creatorName: ").append(toIndentedString(creatorName)).append("\n");
        sb.append("    createTime: ").append(toIndentedString(createTime)).append("\n");
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

