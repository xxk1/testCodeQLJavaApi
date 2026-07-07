package com.lztech.site.viewmodel.teachingresearchsection;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * TeachingResearchSectionResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-12-29T03:26:59.577Z")


public class TeachingResearchSectionResource {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("teachingResearchName")
    private String teachingResearchName = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    @JsonProperty("teachingResearchCourseVoList")
    @Valid
    private List<TeachingResearchCourseVo> teachingResearchCourseVoList = null;

    public TeachingResearchSectionResource id(String id) {
        this.id = id;
        return this;
    }

    /**
     * 教研室id
     *
     * @return id
     **/
    @ApiModelProperty(value = "教研室id")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TeachingResearchSectionResource teachingResearchName(String teachingResearchName) {
        this.teachingResearchName = teachingResearchName;
        return this;
    }

    /**
     * 教研室名称
     *
     * @return teachingResearchName
     **/
    @ApiModelProperty(value = "教研室名称")


    public String getTeachingResearchName() {
        return teachingResearchName;
    }

    public void setTeachingResearchName(String teachingResearchName) {
        this.teachingResearchName = teachingResearchName;
    }

    public TeachingResearchSectionResource collegeId(String collegeId) {
        this.collegeId = collegeId;
        return this;
    }

    /**
     * 学院id
     *
     * @return collegeId
     **/
    @ApiModelProperty(value = "学院id")


    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public TeachingResearchSectionResource collegeName(String collegeName) {
        this.collegeName = collegeName;
        return this;
    }

    /**
     * 学院名称
     *
     * @return collegeName
     **/
    @ApiModelProperty(value = "学院名称")


    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public TeachingResearchSectionResource teachingResearchCourseVoList(List<TeachingResearchCourseVo> teachingResearchCourseVoList) {
        this.teachingResearchCourseVoList = teachingResearchCourseVoList;
        return this;
    }

    public TeachingResearchSectionResource addTeachingResearchCourseVoListItem(TeachingResearchCourseVo teachingResearchCourseVoListItem) {
        if (this.teachingResearchCourseVoList == null) {
            this.teachingResearchCourseVoList = new ArrayList<TeachingResearchCourseVo>();
        }
        this.teachingResearchCourseVoList.add(teachingResearchCourseVoListItem);
        return this;
    }

    /**
     * 教研室课程列表
     *
     * @return teachingResearchCourseVoList
     **/
    @ApiModelProperty(value = "教研室课程列表")

    @Valid

    public List<TeachingResearchCourseVo> getTeachingResearchCourseVoList() {
        return teachingResearchCourseVoList;
    }

    public void setTeachingResearchCourseVoList(List<TeachingResearchCourseVo> teachingResearchCourseVoList) {
        this.teachingResearchCourseVoList = teachingResearchCourseVoList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeachingResearchSectionResource teachingResearchSectionResource = (TeachingResearchSectionResource) o;
        return Objects.equals(this.id, teachingResearchSectionResource.id) &&
                Objects.equals(this.teachingResearchName, teachingResearchSectionResource.teachingResearchName) &&
                Objects.equals(this.collegeId, teachingResearchSectionResource.collegeId) &&
                Objects.equals(this.collegeName, teachingResearchSectionResource.collegeName) &&
                Objects.equals(this.teachingResearchCourseVoList, teachingResearchSectionResource.teachingResearchCourseVoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teachingResearchName, collegeId, collegeName, teachingResearchCourseVoList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TeachingResearchSectionResource {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    teachingResearchName: ").append(toIndentedString(teachingResearchName)).append("\n");
        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
        sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
        sb.append("    teachingResearchCourseVoList: ").append(toIndentedString(teachingResearchCourseVoList)).append("\n");
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

