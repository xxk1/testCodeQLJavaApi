package com.lztech.site.viewmodel.segment;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * BuildingVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-19T08:44:16.470Z")


public class BuildingVo {
    @JsonProperty("buildId")
    private String buildId = null;

    @JsonProperty("segmentList")
    @Valid
    private List<SegmentVo> segmentList = null;

    public BuildingVo buildId(String buildId) {
        this.buildId = buildId;
        return this;
    }

    /**
     * 楼栋Id
     *
     * @return buildId
     **/
    @ApiModelProperty(value = "楼栋Id")


    public String getBuildId() {
        return buildId;
    }

    public void setBuildId(String buildId) {
        this.buildId = buildId;
    }

    public BuildingVo segmentList(List<SegmentVo> segmentList) {
        this.segmentList = segmentList;
        return this;
    }

    public BuildingVo addSegmentListItem(SegmentVo segmentListItem) {
        if (this.segmentList == null) {
            this.segmentList = new ArrayList<SegmentVo>();
        }
        this.segmentList.add(segmentListItem);
        return this;
    }

    /**
     * Get segmentList
     *
     * @return segmentList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<SegmentVo> getSegmentList() {
        return segmentList;
    }

    public void setSegmentList(List<SegmentVo> segmentList) {
        this.segmentList = segmentList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BuildingVo buildingVo = (BuildingVo) o;
        return Objects.equals(this.buildId, buildingVo.buildId) &&
                Objects.equals(this.segmentList, buildingVo.segmentList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buildId, segmentList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class BuildingVo {\n");

        sb.append("    buildId: ").append(toIndentedString(buildId)).append("\n");
        sb.append("    segmentList: ").append(toIndentedString(segmentList)).append("\n");
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

