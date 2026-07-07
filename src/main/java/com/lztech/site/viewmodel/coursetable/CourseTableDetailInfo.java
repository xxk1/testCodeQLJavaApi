package com.lztech.site.viewmodel.coursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseTableDetailInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2026-01-14T16:39:43.155+08:00")

public class CourseTableDetailInfo {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("courseDate")
    private String courseDate = null;

    @JsonProperty("week")
    private String week = null;

    @JsonProperty("weekNum")
    private Integer weekNum = null;

    @JsonProperty("segment")
    private String segment = null;

    @JsonProperty("segmentStartTime")
    private String segmentStartTime = null;

    @JsonProperty("segmentEndTime")
    private String segmentEndTime = null;

    @JsonProperty("courseTableDetailSegmentVoList")
    @Valid
    private List<CourseTableDetailSegmentVo> courseTableDetailSegmentVoList = null;

    @JsonProperty("courseTableDetailTeachers")
    @Valid
    private List<CourseTableDetailTeacherInfo> courseTableDetailTeachers = null;

    @JsonProperty("sourceDataSource")
    private String sourceDataSource = null;

    @JsonProperty("sourceDataSourceName")
    private String sourceDataSourceName = null;

    public CourseTableDetailInfo id(String id) {
        this.id = id;
        return this;
    }

    /**
     * 课表明细id
     *
     * @return id
     **/
    @ApiModelProperty(value = "课表明细id")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CourseTableDetailInfo courseDate(String courseDate) {
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

    public CourseTableDetailInfo week(String week) {
        this.week = week;
        return this;
    }

    /**
     * 第几周
     *
     * @return week
     **/
    @ApiModelProperty(value = "第几周")


    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public CourseTableDetailInfo weekNum(Integer weekNum) {
        this.weekNum = weekNum;
        return this;
    }

    /**
     * 周几
     *
     * @return weekNum
     **/
    @ApiModelProperty(value = "周几")


    public Integer getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(Integer weekNum) {
        this.weekNum = weekNum;
    }

    public CourseTableDetailInfo segment(String segment) {
        this.segment = segment;
        return this;
    }

    /**
     * 节次
     *
     * @return segment
     **/
    @ApiModelProperty(value = "节次")


    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public CourseTableDetailInfo segmentStartTime(String segmentStartTime) {
        this.segmentStartTime = segmentStartTime;
        return this;
    }

    /**
     * 节次开始时间
     *
     * @return segmentStartTime
     **/
    @ApiModelProperty(value = "节次开始时间")


    public String getSegmentStartTime() {
        return segmentStartTime;
    }

    public void setSegmentStartTime(String segmentStartTime) {
        this.segmentStartTime = segmentStartTime;
    }

    public CourseTableDetailInfo segmentEndTime(String segmentEndTime) {
        this.segmentEndTime = segmentEndTime;
        return this;
    }

    /**
     * 节次结束时间
     *
     * @return segmentEndTime
     **/
    @ApiModelProperty(value = "节次结束时间")


    public String getSegmentEndTime() {
        return segmentEndTime;
    }

    public void setSegmentEndTime(String segmentEndTime) {
        this.segmentEndTime = segmentEndTime;
    }

    public CourseTableDetailInfo courseTableDetailSegmentVoList(List<CourseTableDetailSegmentVo> courseTableDetailSegmentVoList) {
        this.courseTableDetailSegmentVoList = courseTableDetailSegmentVoList;
        return this;
    }

    public CourseTableDetailInfo addCourseTableDetailSegmentVoListItem(CourseTableDetailSegmentVo courseTableDetailSegmentVoListItem) {
        if (this.courseTableDetailSegmentVoList == null) {
            this.courseTableDetailSegmentVoList = new ArrayList<CourseTableDetailSegmentVo>();
        }
        this.courseTableDetailSegmentVoList.add(courseTableDetailSegmentVoListItem);
        return this;
    }

    /**
     * 课表明细节次列表
     *
     * @return courseTableDetailSegmentVoList
     **/
    @ApiModelProperty(value = "课表明细节次列表")

    @Valid

    public List<CourseTableDetailSegmentVo> getCourseTableDetailSegmentVoList() {
        return courseTableDetailSegmentVoList;
    }

    public void setCourseTableDetailSegmentVoList(List<CourseTableDetailSegmentVo> courseTableDetailSegmentVoList) {
        this.courseTableDetailSegmentVoList = courseTableDetailSegmentVoList;
    }

    public CourseTableDetailInfo courseTableDetailTeachers(List<CourseTableDetailTeacherInfo> courseTableDetailTeachers) {
        this.courseTableDetailTeachers = courseTableDetailTeachers;
        return this;
    }

    public CourseTableDetailInfo addCourseTableDetailTeachersItem(CourseTableDetailTeacherInfo courseTableDetailTeachersItem) {
        if (this.courseTableDetailTeachers == null) {
            this.courseTableDetailTeachers = new ArrayList<CourseTableDetailTeacherInfo>();
        }
        this.courseTableDetailTeachers.add(courseTableDetailTeachersItem);
        return this;
    }

    /**
     * 课表明细老师列表
     *
     * @return courseTableDetailTeachers
     **/
    @ApiModelProperty(value = "课表明细老师列表")

    @Valid

    public List<CourseTableDetailTeacherInfo> getCourseTableDetailTeachers() {
        return courseTableDetailTeachers;
    }

    public void setCourseTableDetailTeachers(List<CourseTableDetailTeacherInfo> courseTableDetailTeachers) {
        this.courseTableDetailTeachers = courseTableDetailTeachers;
    }

    public CourseTableDetailInfo sourceDataSource(String sourceDataSource) {
        this.sourceDataSource = sourceDataSource;
        return this;
    }

    /**
     * 数据来源
     *
     * @return sourceDataSource
     **/
    @ApiModelProperty(value = "数据来源")


    public String getSourceDataSource() {
        return sourceDataSource;
    }

    public void setSourceDataSource(String sourceDataSource) {
        this.sourceDataSource = sourceDataSource;
    }

    public CourseTableDetailInfo sourceDataSourceName(String sourceDataSourceName) {
        this.sourceDataSourceName = sourceDataSourceName;
        return this;
    }

    /**
     * 数据来源名称
     *
     * @return sourceDataSourceName
     **/
    @ApiModelProperty(value = "数据来源名称")


    public String getSourceDataSourceName() {
        return sourceDataSourceName;
    }

    public void setSourceDataSourceName(String sourceDataSourceName) {
        this.sourceDataSourceName = sourceDataSourceName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseTableDetailInfo courseTableDetailInfo = (CourseTableDetailInfo) o;
        return Objects.equals(this.id, courseTableDetailInfo.id) &&
                Objects.equals(this.courseDate, courseTableDetailInfo.courseDate) &&
                Objects.equals(this.week, courseTableDetailInfo.week) &&
                Objects.equals(this.weekNum, courseTableDetailInfo.weekNum) &&
                Objects.equals(this.segment, courseTableDetailInfo.segment) &&
                Objects.equals(this.segmentStartTime, courseTableDetailInfo.segmentStartTime) &&
                Objects.equals(this.segmentEndTime, courseTableDetailInfo.segmentEndTime) &&
                Objects.equals(this.courseTableDetailSegmentVoList, courseTableDetailInfo.courseTableDetailSegmentVoList) &&
                Objects.equals(this.courseTableDetailTeachers, courseTableDetailInfo.courseTableDetailTeachers) &&
                Objects.equals(this.sourceDataSource, courseTableDetailInfo.sourceDataSource) &&
                Objects.equals(this.sourceDataSourceName, courseTableDetailInfo.sourceDataSourceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseDate, week, weekNum, segment, segmentStartTime, segmentEndTime, courseTableDetailSegmentVoList,
                courseTableDetailTeachers, sourceDataSource, sourceDataSourceName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTableDetailInfo {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
        sb.append("    week: ").append(toIndentedString(week)).append("\n");
        sb.append("    weekNum: ").append(toIndentedString(weekNum)).append("\n");
        sb.append("    segment: ").append(toIndentedString(segment)).append("\n");
        sb.append("    segmentStartTime: ").append(toIndentedString(segmentStartTime)).append("\n");
        sb.append("    segmentEndTime: ").append(toIndentedString(segmentEndTime)).append("\n");
        sb.append("    courseTableDetailSegmentVoList: ").append(toIndentedString(courseTableDetailSegmentVoList)).append("\n");
        sb.append("    courseTableDetailTeachers: ").append(toIndentedString(courseTableDetailTeachers)).append("\n");
        sb.append("    sourceDataSource: ").append(toIndentedString(sourceDataSource)).append("\n");
        sb.append("    sourceDataSourceName: ").append(toIndentedString(sourceDataSourceName)).append("\n");
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

