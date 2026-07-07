package com.lztech.site.viewmodel.coursestructuremapping;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseStructureMappingParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-07-01T15:05:53.522+08:00")

public class CourseStructureMappingParam {
    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("versionId")
    private String versionId = null;

    @JsonProperty("operatorId")
    private String operatorId = null;

    @JsonProperty("operatorName")
    private String operatorName = null;

    @JsonProperty("mappingList")
    @Valid
    private List<CourseStructureMappingDetailResource> mappingList = null;

    public CourseStructureMappingParam courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    /**
     * Get courseId
     *
     * @return courseId
     **/
    @ApiModelProperty(value = "")


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public CourseStructureMappingParam versionId(String versionId) {
        this.versionId = versionId;
        return this;
    }

    /**
     * 版本id
     *
     * @return versionId
     **/
    @ApiModelProperty(value = "版本id")


    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public CourseStructureMappingParam operatorId(String operatorId) {
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

    public CourseStructureMappingParam operatorName(String operatorName) {
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

    public CourseStructureMappingParam mappingList(List<CourseStructureMappingDetailResource> mappingList) {
        this.mappingList = mappingList;
        return this;
    }

    public CourseStructureMappingParam addMappingListItem(CourseStructureMappingDetailResource mappingListItem) {
        if (this.mappingList == null) {
            this.mappingList = new ArrayList<CourseStructureMappingDetailResource>();
        }
        this.mappingList.add(mappingListItem);
        return this;
    }

    /**
     * Get mappingList
     *
     * @return mappingList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<CourseStructureMappingDetailResource> getMappingList() {
        return mappingList;
    }

    public void setMappingList(List<CourseStructureMappingDetailResource> mappingList) {
        this.mappingList = mappingList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseStructureMappingParam courseStructureMappingParam = (CourseStructureMappingParam) o;
        return Objects.equals(this.courseId, courseStructureMappingParam.courseId) &&
                Objects.equals(this.versionId, courseStructureMappingParam.versionId) &&
                Objects.equals(this.operatorId, courseStructureMappingParam.operatorId) &&
                Objects.equals(this.operatorName, courseStructureMappingParam.operatorName) &&
                Objects.equals(this.mappingList, courseStructureMappingParam.mappingList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, versionId, operatorId, operatorName, mappingList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseStructureMappingParam {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    versionId: ").append(toIndentedString(versionId)).append("\n");
        sb.append("    operatorId: ").append(toIndentedString(operatorId)).append("\n");
        sb.append("    operatorName: ").append(toIndentedString(operatorName)).append("\n");
        sb.append("    mappingList: ").append(toIndentedString(mappingList)).append("\n");
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

