package com.lztech.site.viewmodel.coursechaptergoal;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseStructureBaseResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-02-25T09:10:03.832Z")


public class CourseStructureBaseResource {
    @JsonProperty("structureId")
    private String structureId = null;

    @JsonProperty("structureName")
    private String structureName = null;

    @JsonProperty("goalList")
    @Valid
    private List<CourseChapterGoalResource> goalList = null;

    private int structureShowOrder;

    public int getStructureShowOrder() {
        return structureShowOrder;
    }

    public void setStructureShowOrder(int structureShowOrder) {
        this.structureShowOrder = structureShowOrder;
    }

    public CourseStructureBaseResource structureId(String structureId) {
        this.structureId = structureId;
        return this;
    }

    /**
     * 章节id
     *
     * @return structureId
     **/
    @ApiModelProperty(value = "章节id")


    public String getStructureId() {
        return structureId;
    }

    public void setStructureId(String structureId) {
        this.structureId = structureId;
    }

    public CourseStructureBaseResource structureName(String structureName) {
        this.structureName = structureName;
        return this;
    }

    /**
     * 章节名称
     *
     * @return structureName
     **/
    @ApiModelProperty(value = "章节名称")


    public String getStructureName() {
        return structureName;
    }

    public void setStructureName(String structureName) {
        this.structureName = structureName;
    }

    public CourseStructureBaseResource goalList(List<CourseChapterGoalResource> goalList) {
        this.goalList = goalList;
        return this;
    }

    public CourseStructureBaseResource addGoalListItem(CourseChapterGoalResource goalListItem) {
        if (this.goalList == null) {
            this.goalList = new ArrayList<CourseChapterGoalResource>();
        }
        this.goalList.add(goalListItem);
        return this;
    }

    /**
     * 章节目标列表
     *
     * @return goalList
     **/
    @ApiModelProperty(value = "章节目标列表")

    @Valid

    public List<CourseChapterGoalResource> getGoalList() {
        return goalList;
    }

    public void setGoalList(List<CourseChapterGoalResource> goalList) {
        this.goalList = goalList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseStructureBaseResource courseStructureBaseResource = (CourseStructureBaseResource) o;
        return Objects.equals(this.structureId, courseStructureBaseResource.structureId) &&
                Objects.equals(this.structureName, courseStructureBaseResource.structureName) &&
                Objects.equals(this.goalList, courseStructureBaseResource.goalList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(structureId, structureName, goalList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseStructureBaseResource {\n");

        sb.append("    structureId: ").append(toIndentedString(structureId)).append("\n");
        sb.append("    structureName: ").append(toIndentedString(structureName)).append("\n");
        sb.append("    goalList: ").append(toIndentedString(goalList)).append("\n");
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

