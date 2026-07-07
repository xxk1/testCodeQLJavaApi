package com.lztech.site.viewmodel.firstcoursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.math.BigInteger;
import java.util.Objects;

/**
 * ClassFirstCourseTableInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-08-26T09:37:56.824Z")

public class ClassFirstCourseTableInfo {
    @JsonProperty("groupId")
    private String groupId = null;

    @JsonProperty("groupName")
    private String groupName = null;

    @JsonProperty("groupMember")
    private BigInteger groupMember = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("courseCode")
    private String courseCode = null;

    @JsonProperty("courseTableId")
    private String courseTableId = null;

    @JsonProperty("courseTableDetailId")
    private String courseTableDetailId = null;

    @JsonProperty("courseDate")
    private String courseDate = null;

    @JsonProperty("segments")
    private String segments = null;

    @JsonProperty("segmentStartTime")
    private String segmentStartTime = null;

    @JsonProperty("segmentEndTime")
    private String segmentEndTime = null;

    @JsonProperty("roomId")
    private String roomId = null;

    @JsonProperty("roomName")
    private String roomName = null;

    @JsonProperty("roomCode")
    private String roomCode = null;

    @JsonProperty("teacherIds")
    private String teacherIds = null;

    @JsonProperty("teacherNos")
    private String teacherNos = null;

    @JsonProperty("teacherNames")
    private String teacherNames = null;

    @JsonProperty("teacherCollegeIds")
    private String teacherCollegeIds = null;

    @JsonProperty("teacherCollegeNames")
    private String teacherCollegeNames = null;

    @JsonProperty("courseTableCollegeId")
    private String courseTableCollegeId = null;

    @JsonProperty("courseTableCollegeName")
    private String courseTableCollegeName = null;

    @JsonProperty("schoolYear")
    private String schoolYear = null;

    @JsonProperty("term")
    private Integer term = null;

    public ClassFirstCourseTableInfo groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    /**
     * 组id
     * @return groupId
     **/
    @ApiModelProperty(value = "组id")


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public ClassFirstCourseTableInfo groupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    /**
     * 组名
     * @return groupName
     **/
    @ApiModelProperty(value = "组名")


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public ClassFirstCourseTableInfo groupMember(BigInteger groupMember) {
        this.groupMember = groupMember;
        return this;
    }

    /**
     * 班级人数
     * @return groupMember
     **/
    @ApiModelProperty(value = "班级人数")


    public BigInteger getGroupMember() {
        return groupMember;
    }

    public void setGroupMember(BigInteger groupMember) {
        this.groupMember = groupMember;
    }

    public ClassFirstCourseTableInfo courseName(String courseName) {
        this.courseName = courseName;
        return this;
    }

    /**
     * 课程名称
     * @return courseName
     **/
    @ApiModelProperty(value = "课程名称")


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public ClassFirstCourseTableInfo courseCode(String courseCode) {
        this.courseCode = courseCode;
        return this;
    }

    /**
     * 课程编码
     * @return courseCode
     **/
    @ApiModelProperty(value = "课程编码")


    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public ClassFirstCourseTableInfo courseTableId(String courseTableId) {
        this.courseTableId = courseTableId;
        return this;
    }

    /**
     * 开课id
     * @return courseTableId
     **/
    @ApiModelProperty(value = "开课id")


    public String getCourseTableId() {
        return courseTableId;
    }

    public void setCourseTableId(String courseTableId) {
        this.courseTableId = courseTableId;
    }

    public ClassFirstCourseTableInfo courseTableDetailId(String courseTableDetailId) {
        this.courseTableDetailId = courseTableDetailId;
        return this;
    }

    /**
     * 第一堂课课表id
     * @return courseTableDetailId
     **/
    @ApiModelProperty(value = "第一堂课课表id")


    public String getCourseTableDetailId() {
        return courseTableDetailId;
    }

    public void setCourseTableDetailId(String courseTableDetailId) {
        this.courseTableDetailId = courseTableDetailId;
    }

    public ClassFirstCourseTableInfo courseDate(String courseDate) {
        this.courseDate = courseDate;
        return this;
    }

    /**
     * 上课日期
     * @return courseDate
     **/
    @ApiModelProperty(value = "上课日期")


    public String getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(String courseDate) {
        this.courseDate = courseDate;
    }

    public ClassFirstCourseTableInfo segments(String segments) {
        this.segments = segments;
        return this;
    }

    /**
     * 节次信息
     * @return segments
     **/
    @ApiModelProperty(value = "节次信息")


    public String getSegments() {
        return segments;
    }

    public void setSegments(String segments) {
        this.segments = segments;
    }

    public ClassFirstCourseTableInfo segmentStartTime(String segmentStartTime) {
        this.segmentStartTime = segmentStartTime;
        return this;
    }

    /**
     * 节次开始时间
     * @return segmentStartTime
     **/
    @ApiModelProperty(value = "节次开始时间")


    public String getSegmentStartTime() {
        return segmentStartTime;
    }

    public void setSegmentStartTime(String segmentStartTime) {
        this.segmentStartTime = segmentStartTime;
    }

    public ClassFirstCourseTableInfo segmentEndTime(String segmentEndTime) {
        this.segmentEndTime = segmentEndTime;
        return this;
    }

    /**
     * 节次结束时间
     * @return segmentEndTime
     **/
    @ApiModelProperty(value = "节次结束时间")


    public String getSegmentEndTime() {
        return segmentEndTime;
    }

    public void setSegmentEndTime(String segmentEndTime) {
        this.segmentEndTime = segmentEndTime;
    }

    public ClassFirstCourseTableInfo roomId(String roomId) {
        this.roomId = roomId;
        return this;
    }

    /**
     * 教室id
     * @return roomId
     **/
    @ApiModelProperty(value = "教室id")


    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public ClassFirstCourseTableInfo roomName(String roomName) {
        this.roomName = roomName;
        return this;
    }

    /**
     * 教室名称
     * @return roomName
     **/
    @ApiModelProperty(value = "教室名称")


    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public ClassFirstCourseTableInfo roomCode(String roomCode) {
        this.roomCode = roomCode;
        return this;
    }

    /**
     * 教室code
     * @return roomCode
     **/
    @ApiModelProperty(value = "教室code ")


    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public ClassFirstCourseTableInfo teacherIds(String teacherIds) {
        this.teacherIds = teacherIds;
        return this;
    }

    /**
     * 上课教师ids，多个使用”,“隔开
     * @return teacherIds
     **/
    @ApiModelProperty(value = "上课教师ids，多个使用”,“隔开")


    public String getTeacherIds() {
        return teacherIds;
    }

    public void setTeacherIds(String teacherIds) {
        this.teacherIds = teacherIds;
    }

    public ClassFirstCourseTableInfo teacherNos(String teacherNos) {
        this.teacherNos = teacherNos;
        return this;
    }

    /**
     * 上课教师工号，多个使用”,“隔开
     * @return teacherNos
     **/
    @ApiModelProperty(value = "上课教师工号，多个使用”,“隔开")


    public String getTeacherNos() {
        return teacherNos;
    }

    public void setTeacherNos(String teacherNos) {
        this.teacherNos = teacherNos;
    }

    public ClassFirstCourseTableInfo teacherNames(String teacherNames) {
        this.teacherNames = teacherNames;
        return this;
    }

    /**
     * 上课教师姓名，多个使用”,“隔开
     * @return teacherNames
     **/
    @ApiModelProperty(value = "上课教师姓名，多个使用”,“隔开")


    public String getTeacherNames() {
        return teacherNames;
    }

    public void setTeacherNames(String teacherNames) {
        this.teacherNames = teacherNames;
    }

    public ClassFirstCourseTableInfo teacherCollegeIds(String teacherCollegeIds) {
        this.teacherCollegeIds = teacherCollegeIds;
        return this;
    }

    /**
     * 上课教师学院ids，多个使用”,“隔开
     * @return teacherCollegeIds
     **/
    @ApiModelProperty(value = "上课教师学院ids，多个使用”,“隔开")


    public String getTeacherCollegeIds() {
        return teacherCollegeIds;
    }

    public void setTeacherCollegeIds(String teacherCollegeIds) {
        this.teacherCollegeIds = teacherCollegeIds;
    }

    public ClassFirstCourseTableInfo teacherCollegeNames(String teacherCollegeNames) {
        this.teacherCollegeNames = teacherCollegeNames;
        return this;
    }

    /**
     * 上课教师学院名称，多个使用”,“隔开
     * @return teacherCollegeNames
     **/
    @ApiModelProperty(value = "上课教师学院名称，多个使用”,“隔开")


    public String getTeacherCollegeNames() {
        return teacherCollegeNames;
    }

    public void setTeacherCollegeNames(String teacherCollegeNames) {
        this.teacherCollegeNames = teacherCollegeNames;
    }

    public ClassFirstCourseTableInfo courseTableCollegeId(String courseTableCollegeId) {
        this.courseTableCollegeId = courseTableCollegeId;
        return this;
    }

    /**
     * 开课学院id
     * @return courseTableCollegeId
     **/
    @ApiModelProperty(value = "开课学院id")


    public String getCourseTableCollegeId() {
        return courseTableCollegeId;
    }

    public void setCourseTableCollegeId(String courseTableCollegeId) {
        this.courseTableCollegeId = courseTableCollegeId;
    }

    public ClassFirstCourseTableInfo courseTableCollegeName(String courseTableCollegeName) {
        this.courseTableCollegeName = courseTableCollegeName;
        return this;
    }

    /**
     * 开课学院名称
     * @return courseTableCollegeName
     **/
    @ApiModelProperty(value = "开课学院名称")


    public String getCourseTableCollegeName() {
        return courseTableCollegeName;
    }

    public void setCourseTableCollegeName(String courseTableCollegeName) {
        this.courseTableCollegeName = courseTableCollegeName;
    }

    public ClassFirstCourseTableInfo schoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
        return this;
    }

    /**
     * 学年
     * @return schoolYear
     **/
    @ApiModelProperty(value = "学年")


    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public ClassFirstCourseTableInfo term(Integer term) {
        this.term = term;
        return this;
    }

    /**
     * 学期
     * @return term
     **/
    @ApiModelProperty(value = "学期")


    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClassFirstCourseTableInfo classFirstCourseTableInfo = (ClassFirstCourseTableInfo) o;
        return Objects.equals(this.groupId, classFirstCourseTableInfo.groupId) &&
                Objects.equals(this.groupName, classFirstCourseTableInfo.groupName) &&
                Objects.equals(this.groupMember, classFirstCourseTableInfo.groupMember) &&
                Objects.equals(this.courseName, classFirstCourseTableInfo.courseName) &&
                Objects.equals(this.courseCode, classFirstCourseTableInfo.courseCode) &&
                Objects.equals(this.courseTableId, classFirstCourseTableInfo.courseTableId) &&
                Objects.equals(this.courseTableDetailId, classFirstCourseTableInfo.courseTableDetailId) &&
                Objects.equals(this.courseDate, classFirstCourseTableInfo.courseDate) &&
                Objects.equals(this.segments, classFirstCourseTableInfo.segments) &&
                Objects.equals(this.segmentStartTime, classFirstCourseTableInfo.segmentStartTime) &&
                Objects.equals(this.segmentEndTime, classFirstCourseTableInfo.segmentEndTime) &&
                Objects.equals(this.roomId, classFirstCourseTableInfo.roomId) &&
                Objects.equals(this.roomName, classFirstCourseTableInfo.roomName) &&
                Objects.equals(this.roomCode, classFirstCourseTableInfo.roomCode) &&
                Objects.equals(this.teacherIds, classFirstCourseTableInfo.teacherIds) &&
                Objects.equals(this.teacherNos, classFirstCourseTableInfo.teacherNos) &&
                Objects.equals(this.teacherNames, classFirstCourseTableInfo.teacherNames) &&
                Objects.equals(this.teacherCollegeIds, classFirstCourseTableInfo.teacherCollegeIds) &&
                Objects.equals(this.teacherCollegeNames, classFirstCourseTableInfo.teacherCollegeNames) &&
                Objects.equals(this.courseTableCollegeId, classFirstCourseTableInfo.courseTableCollegeId) &&
                Objects.equals(this.courseTableCollegeName, classFirstCourseTableInfo.courseTableCollegeName) &&
                Objects.equals(this.schoolYear, classFirstCourseTableInfo.schoolYear) &&
                Objects.equals(this.term, classFirstCourseTableInfo.term);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, groupName, groupMember, courseName, courseCode,
                courseTableId, courseTableDetailId, courseDate, segments,
                segmentStartTime, segmentEndTime, roomId, roomName, roomCode,
                teacherIds, teacherNos, teacherNames, teacherCollegeIds,
                teacherCollegeNames, courseTableCollegeId, courseTableCollegeName, schoolYear, term);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ClassFirstCourseTableInfo {\n");

        sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
        sb.append("    groupName: ").append(toIndentedString(groupName)).append("\n");
        sb.append("    groupMember: ").append(toIndentedString(groupMember)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
        sb.append("    courseTableId: ").append(toIndentedString(courseTableId)).append("\n");
        sb.append("    courseTableDetailId: ").append(toIndentedString(courseTableDetailId)).append("\n");
        sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
        sb.append("    segments: ").append(toIndentedString(segments)).append("\n");
        sb.append("    segmentStartTime: ").append(toIndentedString(segmentStartTime)).append("\n");
        sb.append("    segmentEndTime: ").append(toIndentedString(segmentEndTime)).append("\n");
        sb.append("    roomId: ").append(toIndentedString(roomId)).append("\n");
        sb.append("    roomName: ").append(toIndentedString(roomName)).append("\n");
        sb.append("    roomCode: ").append(toIndentedString(roomCode)).append("\n");
        sb.append("    teacherIds: ").append(toIndentedString(teacherIds)).append("\n");
        sb.append("    teacherNos: ").append(toIndentedString(teacherNos)).append("\n");
        sb.append("    teacherNames: ").append(toIndentedString(teacherNames)).append("\n");
        sb.append("    teacherCollegeIds: ").append(toIndentedString(teacherCollegeIds)).append("\n");
        sb.append("    teacherCollegeNames: ").append(toIndentedString(teacherCollegeNames)).append("\n");
        sb.append("    courseTableCollegeId: ").append(toIndentedString(courseTableCollegeId)).append("\n");
        sb.append("    courseTableCollegeName: ").append(toIndentedString(courseTableCollegeName)).append("\n");
        sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
        sb.append("    term: ").append(toIndentedString(term)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

