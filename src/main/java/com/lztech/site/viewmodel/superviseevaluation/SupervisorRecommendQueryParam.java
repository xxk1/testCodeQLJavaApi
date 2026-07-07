package com.lztech.site.viewmodel.superviseevaluation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * SupervisorRecommendQueryParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-04-09T07:35:43.430Z")


public class SupervisorRecommendQueryParam {
    @JsonProperty("supervisorRecommendVoList")
    @Valid
    private List<SupervisorRecommendVo> supervisorRecommendVoList = null;

    public SupervisorRecommendQueryParam supervisorRecommendVoList(List<SupervisorRecommendVo> supervisorRecommendVoList) {
        this.supervisorRecommendVoList = supervisorRecommendVoList;
        return this;
    }

    public SupervisorRecommendQueryParam addSupervisorRecommendVoListItem(SupervisorRecommendVo supervisorRecommendVoListItem) {
        if (this.supervisorRecommendVoList == null) {
            this.supervisorRecommendVoList = new ArrayList<SupervisorRecommendVo>();
        }
        this.supervisorRecommendVoList.add(supervisorRecommendVoListItem);
        return this;
    }

    /**
     * Get supervisorRecommendVoList
     *
     * @return supervisorRecommendVoList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<SupervisorRecommendVo> getSupervisorRecommendVoList() {
        return supervisorRecommendVoList;
    }

    public void setSupervisorRecommendVoList(List<SupervisorRecommendVo> supervisorRecommendVoList) {
        this.supervisorRecommendVoList = supervisorRecommendVoList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SupervisorRecommendQueryParam supervisorRecommendQueryParam = (SupervisorRecommendQueryParam) o;
        return Objects.equals(this.supervisorRecommendVoList, supervisorRecommendQueryParam.supervisorRecommendVoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supervisorRecommendVoList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SupervisorRecommendQueryParam {\n");

        sb.append("    supervisorRecommendVoList: ").append(toIndentedString(supervisorRecommendVoList)).append("\n");
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

