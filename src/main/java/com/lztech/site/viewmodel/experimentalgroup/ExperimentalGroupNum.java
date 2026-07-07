package com.lztech.site.viewmodel.experimentalgroup;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ExperimentalGroupNum
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-01-20T05:45:55.533Z")


public class ExperimentalGroupNum {
    @JsonProperty("experimentalGroupNum")
    private Integer experimentalGroupNum = null;

    public ExperimentalGroupNum experimentalGroupNum(Integer experimentalGroupNum) {
        this.experimentalGroupNum = experimentalGroupNum;
        return this;
    }

    /**
     * 实验小组数量
     *
     * @return experimentalGroupNum
     **/
    @ApiModelProperty(value = "实验小组数量")


    public Integer getExperimentalGroupNum() {
        return experimentalGroupNum;
    }

    public void setExperimentalGroupNum(Integer experimentalGroupNum) {
        this.experimentalGroupNum = experimentalGroupNum;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExperimentalGroupNum experimentalGroupNum = (ExperimentalGroupNum) o;
        return Objects.equals(this.experimentalGroupNum, experimentalGroupNum.experimentalGroupNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(experimentalGroupNum);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExperimentalGroupNum {\n");

        sb.append("    experimentalGroupNum: ").append(toIndentedString(experimentalGroupNum)).append("\n");
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

