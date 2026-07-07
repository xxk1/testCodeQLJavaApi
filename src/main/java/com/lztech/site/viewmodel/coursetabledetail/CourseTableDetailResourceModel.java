package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseTableDetailResourceModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-02-22T05:44:45.129Z")

public class CourseTableDetailResourceModel {

    @JsonProperty("courseTableDetailId")
    private String courseTableDetailId = null;

    @JsonProperty("schoolYear")
    private String schoolYear = null;

    @JsonProperty("term")
    private Integer term = null;

    @JsonProperty("courseDate")
    private String courseDate = null;

    @JsonProperty("segmentStartTime")
    private String segmentStartTime = null;

    @JsonProperty("segmentEndTime")
    private String segmentEndTime = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseCode")
    private String courseCode = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("groupId")
    private String groupId = null;

    @JsonProperty("groupName")
    private String groupName = null;

    @JsonProperty("groupStudentCount")
    private Integer groupStudentCount = null;

    @JsonProperty("roomIds")
    private String roomIds = null;

    @JsonProperty("roomNames")
    private String roomNames = null;

    @JsonProperty("segments")
    private String segments = null;

    @JsonProperty("teacherIds")
    private String teacherIds = null;

    @JsonProperty("teacherNos")
    private String teacherNos = null;

    @JsonProperty("teacherNames")
    private String teacherNames = null;

    @JsonProperty("courseTableCollegeId")
    private String courseTableCollegeId = null;

    @JsonProperty("courseTableCollegeName")
    private String courseTableCollegeName = null;


    public CourseTableDetailResourceModel groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    /**
     * 教学班id
     *
     * @return groupId
     **/
    @ApiModelProperty(value = "教学班id")

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public CourseTableDetailResourceModel groupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    /**
     * 教学班名称
     *
     * @return groupName
     **/
    @ApiModelProperty(value = "教学班名称")


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public CourseTableDetailResourceModel groupStudentCount(Integer groupStudentCount) {
        this.groupStudentCount = groupStudentCount;
        return this;
    }

    /**
     * 教学班学生人数
     *
     * @return groupStudentCount
     **/
    @ApiModelProperty(value = "教学班学生人数")


    public Integer getGroupStudentCount() {
        return groupStudentCount;
    }

    public void setGroupStudentCount(Integer groupStudentCount) {
        this.groupStudentCount = groupStudentCount;
    }


    public CourseTableDetailResourceModel roomNames(String roomNames) {
        this.roomNames = roomNames;
        return this;
    }

    /**
     * 教室名称(多个,拼接)
     *
     * @return roomNames
     **/
    @ApiModelProperty(value = "教室名称(多个,拼接)")


    public String getRoomNames() {
        return roomNames;
    }

    public void setRoomNames(String roomNames) {
        this.roomNames = roomNames;
    }

    public CourseTableDetailResourceModel courseTableDetailId(String courseTableDetailId) {
        this.courseTableDetailId = courseTableDetailId;
        return this;
    }

    /**
     * 课表id
     *
     * @return courseTableDetailId
     **/
    @ApiModelProperty(value = "课表id")


    public String getCourseTableDetailId() {
        return courseTableDetailId;
    }

    public void setCourseTableDetailId(String courseTableDetailId) {
        this.courseTableDetailId = courseTableDetailId;
    }


    public CourseTableDetailResourceModel segments(String segments) {
        this.segments = segments;
        return this;
    }

    /**
     * 节次(多个,拼接)
     *
     * @return segments
     **/
    @ApiModelProperty(value = "节次(多个,拼接)")


    public String getSegments() {
        return segments;
    }

    public void setSegments(String segments) {
        this.segments = segments;
    }


    public CourseTableDetailResourceModel courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    /**
     * 课程id
     *
     * @return courseId
     **/
    @ApiModelProperty(value = "课程id")


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }


    public CourseTableDetailResourceModel courseCode(String courseCode) {
        this.courseCode = courseCode;
        return this;
    }

    /**
     * 课程编号
     *
     * @return courseCode
     **/
    @ApiModelProperty(value = "课程编号")


    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public CourseTableDetailResourceModel courseName(String courseName) {
        this.courseName = courseName;
        return this;
    }

    /**
     * 课程名称
     *
     * @return courseName
     **/
    @ApiModelProperty(value = "课程名称")


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public CourseTableDetailResourceModel courseDate(String courseDate) {
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


    public CourseTableDetailResourceModel roomIds(String roomIds) {
        this.roomIds = roomIds;
        return this;
    }

    /**
     * 教室id(多个,拼接)
     *
     * @return roomIds
     **/
    @ApiModelProperty(value = "教室id(多个,拼接)")


    public String getRoomIds() {
        return roomIds;
    }

    public void setRoomIds(String roomIds) {
        this.roomIds = roomIds;
    }

    public CourseTableDetailResourceModel segmentStartTime(String segmentStartTime) {
        this.segmentStartTime = segmentStartTime;
        return this;
    }

    /**
     * 课表开始时间
     *
     * @return segmentStartTime
     **/
    @ApiModelProperty(value = "课表开始时间")


    public String getSegmentStartTime() {
        return segmentStartTime;
    }

    public void setSegmentStartTime(String segmentStartTime) {
        this.segmentStartTime = segmentStartTime;
    }

    public CourseTableDetailResourceModel segmentEndTime(String segmentEndTime) {
        this.segmentEndTime = segmentEndTime;
        return this;
    }

    /**
     * 课表结束时间
     *
     * @return segmentEndTime
     **/
    @ApiModelProperty(value = "课表结束时间")


    public String getSegmentEndTime() {
        return segmentEndTime;
    }

    public void setSegmentEndTime(String segmentEndTime) {
        this.segmentEndTime = segmentEndTime;
    }

    public CourseTableDetailResourceModel schoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
        return this;
    }

    /**
     * 学年
     *
     * @return schoolYear
     **/
    @ApiModelProperty(value = "课表结束时间")


    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public CourseTableDetailResourceModel term(Integer term) {
        this.term = term;
        return this;
    }

    /**
     * 学年
     *
     * @return term
     **/
    @ApiModelProperty(value = "学年")

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public CourseTableDetailResourceModel teacherIds(String teacherIds) {
        this.teacherIds = teacherIds;
        return this;
    }

    /**
     * 老师id(多个,拼接)
     *
     * @return teacherIds
     **/
    @ApiModelProperty(value = "老师id(多个,拼接)")

    public String getTeacherIds() {
        return teacherIds;
    }

    public void setTeacherIds(String teacherIds) {
        this.teacherIds = teacherIds;
    }

    public CourseTableDetailResourceModel teacherNos(String teacherNos) {
        this.teacherNos = teacherNos;
        return this;
    }

    /**
     * 老师编号(多个,拼接)
     *
     * @return teacherNos
     **/
    @ApiModelProperty(value = "老师编号(多个,拼接)")

    public String getTeacherNos() {
        return teacherNos;
    }

    public void setTeacherNos(String teacherNos) {
        this.teacherNos = teacherNos;
    }


    public CourseTableDetailResourceModel teacherNames(String teacherNames) {
        this.teacherNames = teacherNames;
        return this;
    }

    /**
     * 老师名称(多个,拼接)
     *
     * @return teacherNames
     **/
    @ApiModelProperty(value = "老师名称(多个,拼接)")


    public String getTeacherNames() {
        return teacherNames;
    }

    public void setTeacherNames(String teacherNames) {
        this.teacherNames = teacherNames;
    }


    public CourseTableDetailResourceModel courseTableCollegeId(String courseTableCollegeId) {
        this.courseTableCollegeId = courseTableCollegeId;
        return this;
    }

    /**
     * 开课学院id
     *
     * @return courseTableCollegeId
     **/
    @ApiModelProperty(value = "开课学院id")


    public String getCourseTableCollegeId() {
        return courseTableCollegeId;
    }

    public void setCourseTableCollegeId(String courseTableCollegeId) {
        this.courseTableCollegeId = courseTableCollegeId;
    }

    public CourseTableDetailResourceModel courseTableCollegeName(String courseTableCollegeName) {
        this.courseTableCollegeName = courseTableCollegeName;
        return this;
    }

    /**
     * 开课学院名称
     *
     * @return courseTableCollegeName
     **/
    @ApiModelProperty(value = "开课学院名称")


    public String getCourseTableCollegeName() {
        return courseTableCollegeName;
    }

    public void setCourseTableCollegeName(String courseTableCollegeName) {
        this.courseTableCollegeName = courseTableCollegeName;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CourseTableDetailResourceModel)) {
            return false;
        }
        CourseTableDetailResourceModel that = (CourseTableDetailResourceModel) o;
        return Objects.equals(courseTableDetailId, that.courseTableDetailId) &&
                Objects.equals(schoolYear, that.schoolYear) &&
                Objects.equals(term, that.term) &&
                Objects.equals(courseDate, that.courseDate) &&
                Objects.equals(segmentStartTime, that.segmentStartTime) &&
                Objects.equals(segmentEndTime, that.segmentEndTime) &&
                Objects.equals(courseId, that.courseId) &&
                Objects.equals(courseCode, that.courseCode) &&
                Objects.equals(courseName, that.courseName) &&
                Objects.equals(groupId, that.groupId) &&
                Objects.equals(groupName, that.groupName) &&
                Objects.equals(groupStudentCount, that.groupStudentCount) &&
                Objects.equals(roomIds, that.roomIds) &&
                Objects.equals(roomNames, that.roomNames) &&
                Objects.equals(segments, that.segments) &&
                Objects.equals(teacherIds, that.teacherIds) &&
                Objects.equals(teacherNos, that.teacherNos) &&
                Objects.equals(teacherNames, that.teacherNames) &&
                Objects.equals(courseTableCollegeId, that.courseTableCollegeId) &&
                Objects.equals(courseTableCollegeName, that.courseTableCollegeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseTableDetailId, schoolYear, term, courseDate, segmentStartTime, segmentEndTime,
                courseId, courseCode, courseName, groupId, groupName, groupStudentCount, roomIds, roomNames,
                segments, teacherIds, teacherNos, teacherNames,courseTableCollegeId,courseTableCollegeName);
    }
}
