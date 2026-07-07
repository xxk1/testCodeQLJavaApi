package com.lztech.site.viewmodel.experimentschedulecoursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ExperimentScheduleCourseTableDetailRelevantResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-09-14T06:34:55.092Z")

public class ExperimentScheduleCourseTableDetailRelevantResource {

    @JsonProperty("result")
    private boolean result;

    @JsonProperty("remark")
    private String remark;

    @JsonProperty("experimentScheduleCourseTableDetailBaseVo")
    private ExperimentScheduleCourseTableDetailBaseVo experimentScheduleCourseTableDetailBaseVo;

    public ExperimentScheduleCourseTableDetailRelevantResource result(boolean result) {
        this.result = result;
        return this;
    }

    /**
     * 是否保存成功
     *
     * @return result
     **/
    @ApiModelProperty(value = "是否保存成功", position = 1, required = true)

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
    public ExperimentScheduleCourseTableDetailRelevantResource remark(String remark) {
        this.remark = remark;
        return this;
    }

    /**
     * 备注
     *
     * @return remark
     **/
    @ApiModelProperty(value = "备注", position = 2, required = true)

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public ExperimentScheduleCourseTableDetailRelevantResource experimentScheduleCourseTableDetailBaseVo(
            ExperimentScheduleCourseTableDetailBaseVo experimentScheduleCourseTableDetailBaseVo) {
        this.experimentScheduleCourseTableDetailBaseVo = experimentScheduleCourseTableDetailBaseVo;
        return this;
    }

    /**
     * 实验课表基础数据
     *
     * @return experimentScheduleCourseTableDetailBaseVo
     **/
    @ApiModelProperty(value = "实验课表基础数据", position = 3, required = true)

    public ExperimentScheduleCourseTableDetailBaseVo getExperimentScheduleCourseTableDetailBaseVo() {
        return experimentScheduleCourseTableDetailBaseVo;
    }

    public void setExperimentScheduleCourseTableDetailBaseVo(ExperimentScheduleCourseTableDetailBaseVo experimentScheduleCourseTableDetailBaseVo) {
        this.experimentScheduleCourseTableDetailBaseVo = experimentScheduleCourseTableDetailBaseVo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExperimentScheduleCourseTableDetailRelevantResource experimentScheduleCourseTableDetailRelevantResource =
                (ExperimentScheduleCourseTableDetailRelevantResource) o;
        return Objects.equals(this.result, experimentScheduleCourseTableDetailRelevantResource.result) &&
                Objects.equals(this.remark, experimentScheduleCourseTableDetailRelevantResource.remark) &&
                Objects.equals(this.experimentScheduleCourseTableDetailBaseVo,
                        experimentScheduleCourseTableDetailRelevantResource.experimentScheduleCourseTableDetailBaseVo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, remark, experimentScheduleCourseTableDetailBaseVo);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExperimentScheduleCourseTableDetailRelevantResource {\n");

        sb.append("    result: ").append(toIndentedString(result)).append("\n");
        sb.append("    remark: ").append(toIndentedString(remark)).append("\n");
        sb.append("    experimentScheduleCourseTableDetailBaseVo: ").append(toIndentedString(experimentScheduleCourseTableDetailBaseVo)).append("\n");
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
