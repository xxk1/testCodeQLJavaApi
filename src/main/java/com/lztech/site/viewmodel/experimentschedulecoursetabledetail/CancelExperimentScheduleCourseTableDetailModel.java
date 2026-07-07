package com.lztech.site.viewmodel.experimentschedulecoursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CancelExperimentScheduleCourseTableDetailModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-09-12T12:21:44.947Z")

public class CancelExperimentScheduleCourseTableDetailModel {

    @JsonProperty("cancelStatus")
    private String cancelStatus;

    @JsonProperty("cancelStatusRemark")
    private String cancelStatusRemark;

    public CancelExperimentScheduleCourseTableDetailModel cancelStatus(String cancelStatus) {
        this.cancelStatus = cancelStatus;
        return this;
    }

    /**
     * 取消状态
     *
     * @return cancelStatus
     **/
    @ApiModelProperty(value = "取消状态", position = 1, required = true)

    public String getCancelStatus() {
        return cancelStatus;
    }

    public void setCancelStatus(String cancelStatus) {
        this.cancelStatus = cancelStatus;
    }
    public CancelExperimentScheduleCourseTableDetailModel cancelStatusRemark(String cancelStatusRemark) {
        this.cancelStatusRemark = cancelStatusRemark;
        return this;
    }

    /**
     * 取消状态备注
     *
     * @return cancelStatusRemark
     **/
    @ApiModelProperty(value = "取消状态备注", position = 2, required = true)

    public String getCancelStatusRemark() {
        return cancelStatusRemark;
    }

    public void setCancelStatusRemark(String cancelStatusRemark) {
        this.cancelStatusRemark = cancelStatusRemark;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CancelExperimentScheduleCourseTableDetailModel cancelExperimentScheduleCourseTableDetailModel =
                (CancelExperimentScheduleCourseTableDetailModel) o;
        return Objects.equals(this.cancelStatus, cancelExperimentScheduleCourseTableDetailModel.cancelStatus) &&
                Objects.equals(this.cancelStatusRemark, cancelExperimentScheduleCourseTableDetailModel.cancelStatusRemark) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cancelStatus,cancelStatusRemark);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CancelExperimentScheduleCourseTableDetailModel {\n");
        sb.append("    cancelStatus: ").append(toIndentedString(cancelStatus)).append("\n");
        sb.append("    cancelStatusRemark: ").append(toIndentedString(cancelStatusRemark)).append("\n");
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
