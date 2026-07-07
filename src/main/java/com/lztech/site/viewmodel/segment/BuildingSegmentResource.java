package com.lztech.site.viewmodel.segment;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * BuildingSegmentResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-02-01T02:48:42.193Z")


public class BuildingSegmentResource {
    @JsonProperty("buildingId")
    private String buildingId = null;

    @JsonProperty("buildingName")
    private String buildingName = null;

    @JsonProperty("segmentList")
    @Valid
    private List<SegmentMode> segmentList = new ArrayList<>();

    public BuildingSegmentResource buildingId(String buildingId) {
        this.buildingId = buildingId;
        return this;
    }

    /**
     * 楼栋id
     *
     * @return buildingId
     **/
    @ApiModelProperty(value = "楼栋id")


    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public BuildingSegmentResource buildingName(String buildingName) {
        this.buildingName = buildingName;
        return this;
    }

    /**
     * 楼栋名称
     *
     * @return buildingName
     **/
    @ApiModelProperty(value = "楼栋名称")


    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public BuildingSegmentResource segmentList(List<SegmentMode> segmentList) {
        this.segmentList = segmentList;
        return this;
    }

    public BuildingSegmentResource addSegmentListItem(SegmentMode segmentListItem) {
        if (this.segmentList == null) {
            this.segmentList = new ArrayList<SegmentMode>();
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

    public List<SegmentMode> getSegmentList() {
        return segmentList;
    }

    public void setSegmentList(List<SegmentMode> segmentList) {
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
        BuildingSegmentResource buildingSegmentResource = (BuildingSegmentResource) o;
        return Objects.equals(this.buildingId, buildingSegmentResource.buildingId) &&
                Objects.equals(this.buildingName, buildingSegmentResource.buildingName) &&
                Objects.equals(this.segmentList, buildingSegmentResource.segmentList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buildingId, buildingName, segmentList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class BuildingSegmentResource {\n");

        sb.append("    buildingId: ").append(toIndentedString(buildingId)).append("\n");
        sb.append("    buildingName: ").append(toIndentedString(buildingName)).append("\n");
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

