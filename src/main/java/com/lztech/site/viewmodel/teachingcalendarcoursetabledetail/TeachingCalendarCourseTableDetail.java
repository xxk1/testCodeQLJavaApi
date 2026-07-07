package com.lztech.site.viewmodel.teachingcalendarcoursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * TeachingCalendarCourseTableDetail
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-08-20T12:04:45.217Z")


public class TeachingCalendarCourseTableDetail {
    @JsonProperty("groupId")
    private String groupId = null;

    @JsonProperty("week")
    private String week = null;

    @JsonProperty("segments")
    private String segments = null;

    @JsonProperty("courseDate")
    private String courseDate = null;

    @JsonProperty("sectionMinStartTime")
    private String sectionMinStartTime = null;

    @JsonProperty("sectionMaxEndTime")
    private String sectionMaxEndTime = null;

    @JsonProperty("projectList")
    @Valid
    private List<ProjectResource> projectList = new ArrayList<>();

    @JsonProperty("labList")
    @Valid
    private List<LabResource> labList = new ArrayList<>();

    @JsonProperty("teacherList")
    @Valid
    private List<TeacherResource> teacherList = new ArrayList<>();


    public TeachingCalendarCourseTableDetail groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    /**
     * 大组id
     *
     * @return groupId
     **/
    @ApiModelProperty(value = "大组id")


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public TeachingCalendarCourseTableDetail week(String week) {
        this.week = week;
        return this;
    }

    /**
     * 周
     *
     * @return week
     **/
    @ApiModelProperty(value = "周")


    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public TeachingCalendarCourseTableDetail segments(String segments) {
        this.segments = segments;
        return this;
    }

    /**
     * 节次信息，多个使用逗号隔开
     *
     * @return segments
     **/
    @ApiModelProperty(value = "节次信息，多个使用逗号隔开")


    public String getSegments() {
        return segments;
    }

    public void setSegments(String segments) {
        this.segments = segments;
    }

    public TeachingCalendarCourseTableDetail courseDate(String courseDate) {
        this.courseDate = courseDate;
        return this;
    }

    /**
     * 上课日期
     *
     * @return courseDate
     **/
    @ApiModelProperty(value = "上课日期")


    public String getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(String courseDate) {
        this.courseDate = courseDate;
    }

    public TeachingCalendarCourseTableDetail sectionMinStartTime(String sectionMinStartTime) {
        this.sectionMinStartTime = sectionMinStartTime;
        return this;
    }

    /**
     * 最小节次开始时间 格式：HH:mm
     *
     * @return sectionMinStartTime
     **/
    @ApiModelProperty(value = "最小节次开始时间 格式：HH:mm")


    public String getSectionMinStartTime() {
        return sectionMinStartTime;
    }

    public void setSectionMinStartTime(String sectionMinStartTime) {
        this.sectionMinStartTime = sectionMinStartTime;
    }

    public TeachingCalendarCourseTableDetail sectionMaxEndTime(String sectionMaxEndTime) {
        this.sectionMaxEndTime = sectionMaxEndTime;
        return this;
    }

    /**
     * 最大节次结束时间 格式：HH:mm
     *
     * @return sectionMaxEndTime
     **/
    @ApiModelProperty(value = "最大节次结束时间 格式：HH:mm")


    public String getSectionMaxEndTime() {
        return sectionMaxEndTime;
    }

    public void setSectionMaxEndTime(String sectionMaxEndTime) {
        this.sectionMaxEndTime = sectionMaxEndTime;
    }

    public TeachingCalendarCourseTableDetail projectList(List<ProjectResource> projectList) {
        this.projectList = projectList;
        return this;
    }

    public TeachingCalendarCourseTableDetail addProjectListItem(ProjectResource projectListItem) {
        if (this.projectList == null) {
            this.projectList = new ArrayList<ProjectResource>();
        }
        this.projectList.add(projectListItem);
        return this;
    }

    /**
     * Get projectList
     *
     * @return projectList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<ProjectResource> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<ProjectResource> projectList) {
        this.projectList = projectList;
    }

    public TeachingCalendarCourseTableDetail labList(List<LabResource> labList) {
        this.labList = labList;
        return this;
    }

    public TeachingCalendarCourseTableDetail addLabListItem(LabResource labListItem) {
        if (this.labList == null) {
            this.labList = new ArrayList<LabResource>();
        }
        this.labList.add(labListItem);
        return this;
    }

    /**
     * Get labList
     *
     * @return labList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<LabResource> getLabList() {
        return labList;
    }

    public void setLabList(List<LabResource> labList) {
        this.labList = labList;
    }

    public TeachingCalendarCourseTableDetail teacherList(List<TeacherResource> teacherList) {
        this.teacherList = teacherList;
        return this;
    }

    public TeachingCalendarCourseTableDetail addTeacherListItem(TeacherResource teacherListItem) {
        if (this.teacherList == null) {
            this.teacherList = new ArrayList<TeacherResource>();
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

    public List<TeacherResource> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<TeacherResource> teacherList) {
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
        TeachingCalendarCourseTableDetail teachingCalendarCourseTableDetail = (TeachingCalendarCourseTableDetail) o;
        return Objects.equals(this.groupId, teachingCalendarCourseTableDetail.groupId) &&
                Objects.equals(this.week, teachingCalendarCourseTableDetail.week) &&
                Objects.equals(this.segments, teachingCalendarCourseTableDetail.segments) &&
                Objects.equals(this.courseDate, teachingCalendarCourseTableDetail.courseDate) &&
                Objects.equals(this.sectionMinStartTime, teachingCalendarCourseTableDetail.sectionMinStartTime) &&
                Objects.equals(this.sectionMaxEndTime, teachingCalendarCourseTableDetail.sectionMaxEndTime) &&
                Objects.equals(this.projectList, teachingCalendarCourseTableDetail.projectList) &&
                Objects.equals(this.labList, teachingCalendarCourseTableDetail.labList) &&
                Objects.equals(this.teacherList, teachingCalendarCourseTableDetail.teacherList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, week, segments, courseDate, sectionMinStartTime,
                sectionMaxEndTime, projectList, labList, teacherList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TeachingCalendarCourseTableDetail {\n");

        sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
        sb.append("    week: ").append(toIndentedString(week)).append("\n");
        sb.append("    segments: ").append(toIndentedString(segments)).append("\n");
        sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
        sb.append("    sectionMinStartTime: ").append(toIndentedString(sectionMinStartTime)).append("\n");
        sb.append("    sectionMaxEndTime: ").append(toIndentedString(sectionMaxEndTime)).append("\n");
        sb.append("    projectList: ").append(toIndentedString(projectList)).append("\n");
        sb.append("    labList: ").append(toIndentedString(labList)).append("\n");
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

