package com.lztech.site.viewmodel.coursemanagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseTeachingTeamResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-02-21T07:43:39.378Z")


public class CourseTeachingTeamResource {
    @JsonProperty("campusTeacherNum")
    private Integer campusTeacherNum = null;

    @JsonProperty("offCampusTeacherNum")
    private Integer offCampusTeacherNum = null;

    @JsonProperty("jobTitleStatisticsList")
    @Valid
    private List<JobTitleStatisticsResource> jobTitleStatisticsList = null;

    @JsonProperty("orgStatisticsList")
    @Valid
    private List<OrgStatisticsResource> orgStatisticsList = null;

    @JsonProperty("teacherList")
    @Valid
    private List<TeacherInfoVo> teacherList = null;

    public CourseTeachingTeamResource campusTeacherNum(Integer campusTeacherNum) {
        this.campusTeacherNum = campusTeacherNum;
        return this;
    }

    /**
     * 校内教师数量
     *
     * @return campusTeacherNum
     **/
    @ApiModelProperty(value = "校内教师数量")


    public Integer getCampusTeacherNum() {
        return campusTeacherNum;
    }

    public void setCampusTeacherNum(Integer campusTeacherNum) {
        this.campusTeacherNum = campusTeacherNum;
    }

    public CourseTeachingTeamResource offCampusTeacherNum(Integer offCampusTeacherNum) {
        this.offCampusTeacherNum = offCampusTeacherNum;
        return this;
    }

    /**
     * 校外教师数量
     *
     * @return offCampusTeacherNum
     **/
    @ApiModelProperty(value = "校外教师数量")


    public Integer getOffCampusTeacherNum() {
        return offCampusTeacherNum;
    }

    public void setOffCampusTeacherNum(Integer offCampusTeacherNum) {
        this.offCampusTeacherNum = offCampusTeacherNum;
    }

    public CourseTeachingTeamResource jobTitleStatisticsList(List<JobTitleStatisticsResource> jobTitleStatisticsList) {
        this.jobTitleStatisticsList = jobTitleStatisticsList;
        return this;
    }

    public CourseTeachingTeamResource addJobTitleStatisticsListItem(JobTitleStatisticsResource jobTitleStatisticsListItem) {
        if (this.jobTitleStatisticsList == null) {
            this.jobTitleStatisticsList = new ArrayList<JobTitleStatisticsResource>();
        }
        this.jobTitleStatisticsList.add(jobTitleStatisticsListItem);
        return this;
    }

    /**
     * Get jobTitleStatisticsList
     *
     * @return jobTitleStatisticsList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<JobTitleStatisticsResource> getJobTitleStatisticsList() {
        return jobTitleStatisticsList;
    }

    public void setJobTitleStatisticsList(List<JobTitleStatisticsResource> jobTitleStatisticsList) {
        this.jobTitleStatisticsList = jobTitleStatisticsList;
    }

    public CourseTeachingTeamResource orgStatisticsList(List<OrgStatisticsResource> orgStatisticsList) {
        this.orgStatisticsList = orgStatisticsList;
        return this;
    }

    public CourseTeachingTeamResource addOrgStatisticsListItem(OrgStatisticsResource orgStatisticsListItem) {
        if (this.orgStatisticsList == null) {
            this.orgStatisticsList = new ArrayList<OrgStatisticsResource>();
        }
        this.orgStatisticsList.add(orgStatisticsListItem);
        return this;
    }

    /**
     * Get orgStatisticsList
     *
     * @return orgStatisticsList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<OrgStatisticsResource> getOrgStatisticsList() {
        return orgStatisticsList;
    }

    public void setOrgStatisticsList(List<OrgStatisticsResource> orgStatisticsList) {
        this.orgStatisticsList = orgStatisticsList;
    }

    public CourseTeachingTeamResource teacherList(List<TeacherInfoVo> teacherList) {
        this.teacherList = teacherList;
        return this;
    }

    public CourseTeachingTeamResource addTeacherListItem(TeacherInfoVo teacherListItem) {
        if (this.teacherList == null) {
            this.teacherList = new ArrayList<TeacherInfoVo>();
        }
        this.teacherList.add(teacherListItem);
        return this;
    }

    /**
     * Get teacherList
     *
     * @return teacherList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<TeacherInfoVo> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<TeacherInfoVo> teacherList) {
        this.teacherList = teacherList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseTeachingTeamResource courseTeachingTeamResource = (CourseTeachingTeamResource) o;
        return Objects.equals(this.campusTeacherNum, courseTeachingTeamResource.campusTeacherNum) &&
                Objects.equals(this.offCampusTeacherNum, courseTeachingTeamResource.offCampusTeacherNum) &&
                Objects.equals(this.jobTitleStatisticsList, courseTeachingTeamResource.jobTitleStatisticsList) &&
                Objects.equals(this.orgStatisticsList, courseTeachingTeamResource.orgStatisticsList) &&
                Objects.equals(this.teacherList, courseTeachingTeamResource.teacherList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(campusTeacherNum, offCampusTeacherNum, jobTitleStatisticsList, orgStatisticsList, teacherList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTeachingTeamResource {\n");

        sb.append("    campusTeacherNum: ").append(toIndentedString(campusTeacherNum)).append("\n");
        sb.append("    offCampusTeacherNum: ").append(toIndentedString(offCampusTeacherNum)).append("\n");
        sb.append("    jobTitleStatisticsList: ").append(toIndentedString(jobTitleStatisticsList)).append("\n");
        sb.append("    orgStatisticsList: ").append(toIndentedString(orgStatisticsList)).append("\n");
        sb.append("    teacherList: ").append(toIndentedString(teacherList)).append("\n");
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

