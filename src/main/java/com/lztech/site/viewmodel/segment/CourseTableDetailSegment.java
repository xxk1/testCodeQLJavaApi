package com.lztech.site.viewmodel.segment;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseTableDetailSegment
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-08-05T10:38:06.026+08:00")

public class CourseTableDetailSegment {
    @JsonProperty("roomId")
    private String roomId = null;

    @JsonProperty("roomName")
    private String roomName = null;

    @JsonProperty("schoolYear")
    private String schoolYear = null;

    @JsonProperty("term")
    private Integer term = null;

    @JsonProperty("groupId")
    private String groupId = null;

    @JsonProperty("groupName")
    private String groupName = null;

    @JsonProperty("week")
    private String week = null;

    @JsonProperty("weekNum")
    private Integer weekNum = null;

    @JsonProperty("segment")
    private Integer segment = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseCode")
    private String courseCode = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("courseTableId")
    private String courseTableId = null;

    @JsonProperty("courseTableDetailId")
    private String courseTableDetailId = null;

    @JsonProperty("teacherIds")
    private String teacherIds = null;

    @JsonProperty("teacherNames")
    private String teacherNames = null;

    @JsonProperty("teacherNos")
    private String teacherNos = null;

    @JsonProperty("studentCount")
    private Integer studentCount = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    @JsonProperty("studentType")
    private Integer studentType = null;

    @JsonProperty("sourceDataSource")
    private String sourceDataSource = null;

    @JsonProperty("sourceDataSourceName")
    private String sourceDataSourceName = null;

    public CourseTableDetailSegment roomId(String roomId) {
        this.roomId = roomId;
        return this;
    }

    /**
     * 教室id
     *
     * @return roomId
     **/
    @ApiModelProperty(value = "教室id")


    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public CourseTableDetailSegment roomName(String roomName) {
        this.roomName = roomName;
        return this;
    }

    /**
     * 教室名称
     *
     * @return roomName
     **/
    @ApiModelProperty(value = "教室名称")


    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public CourseTableDetailSegment schoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
        return this;
    }

    /**
     * 学年
     *
     * @return schoolYear
     **/
    @ApiModelProperty(value = "学年")


    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public CourseTableDetailSegment term(Integer term) {
        this.term = term;
        return this;
    }

    /**
     * 学期
     *
     * @return term
     **/
    @ApiModelProperty(value = "学期")


    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public CourseTableDetailSegment groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    /**
     * 组Id
     *
     * @return groupId
     **/
    @ApiModelProperty(value = "组Id")


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public CourseTableDetailSegment groupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    /**
     * 组名称
     *
     * @return groupName
     **/
    @ApiModelProperty(value = "组名称")


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public CourseTableDetailSegment week(String week) {
        this.week = week;
        return this;
    }

    /**
     * 周次
     *
     * @return week
     **/
    @ApiModelProperty(value = "周次")


    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public CourseTableDetailSegment weekNum(Integer weekNum) {
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

    public CourseTableDetailSegment segment(Integer segment) {
        this.segment = segment;
        return this;
    }

    /**
     * 节次
     *
     * @return segment
     **/
    @ApiModelProperty(value = "节次")


    public Integer getSegment() {
        return segment;
    }

    public void setSegment(Integer segment) {
        this.segment = segment;
    }

    public CourseTableDetailSegment courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    /**
     * 课程Id
     *
     * @return courseId
     **/
    @ApiModelProperty(value = "课程Id")


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public CourseTableDetailSegment courseCode(String courseCode) {
        this.courseCode = courseCode;
        return this;
    }

    /**
     * 课程编码
     *
     * @return courseCode
     **/
    @ApiModelProperty(value = "课程编码")


    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public CourseTableDetailSegment courseName(String courseName) {
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

    public CourseTableDetailSegment courseTableId(String courseTableId) {
        this.courseTableId = courseTableId;
        return this;
    }

    /**
     * 课表Id
     *
     * @return courseTableId
     **/
    @ApiModelProperty(value = "课表Id")


    public String getCourseTableId() {
        return courseTableId;
    }

    public void setCourseTableId(String courseTableId) {
        this.courseTableId = courseTableId;
    }

    public CourseTableDetailSegment courseTableDetailId(String courseTableDetailId) {
        this.courseTableDetailId = courseTableDetailId;
        return this;
    }

    /**
     * 课表明细Id
     *
     * @return courseTableDetailId
     **/
    @ApiModelProperty(value = "课表明细Id")


    public String getCourseTableDetailId() {
        return courseTableDetailId;
    }

    public void setCourseTableDetailId(String courseTableDetailId) {
        this.courseTableDetailId = courseTableDetailId;
    }

    public CourseTableDetailSegment teacherIds(String teacherIds) {
        this.teacherIds = teacherIds;
        return this;
    }

    /**
     * 课表明细老师id，多个时,分割
     *
     * @return teacherIds
     **/
    @ApiModelProperty(value = "课表明细老师id，多个时,分割")


    public String getTeacherIds() {
        return teacherIds;
    }

    public void setTeacherIds(String teacherIds) {
        this.teacherIds = teacherIds;
    }

    public CourseTableDetailSegment teacherNames(String teacherNames) {
        this.teacherNames = teacherNames;
        return this;
    }

    /**
     * 课表明细老师姓名，多个时,分割
     *
     * @return teacherNames
     **/
    @ApiModelProperty(value = "课表明细老师姓名，多个时,分割")


    public String getTeacherNames() {
        return teacherNames;
    }

    public void setTeacherNames(String teacherNames) {
        this.teacherNames = teacherNames;
    }

    public CourseTableDetailSegment teacherNos(String teacherNos) {
        this.teacherNos = teacherNos;
        return this;
    }

    /**
     * 课表明细老师工号，多个时,分割
     *
     * @return teacherNos
     **/
    @ApiModelProperty(value = "课表明细老师工号，多个时,分割")


    public String getTeacherNos() {
        return teacherNos;
    }

    public void setTeacherNos(String teacherNos) {
        this.teacherNos = teacherNos;
    }

    public CourseTableDetailSegment studentCount(Integer studentCount) {
        this.studentCount = studentCount;
        return this;
    }

    /**
     * 课表学生数
     *
     * @return studentCount
     **/
    @ApiModelProperty(value = "课表学生数")


    public Integer getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }

    public CourseTableDetailSegment collegeId(String collegeId) {
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

    public CourseTableDetailSegment collegeName(String collegeName) {
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

    public CourseTableDetailSegment studentType(Integer studentType) {
        this.studentType = studentType;
        return this;
    }

    /**
     * 开课类型
     *
     * @return studentType
     **/
    @ApiModelProperty(value = "开课类型")


    public Integer getStudentType() {
        return studentType;
    }

    public void setStudentType(Integer studentType) {
        this.studentType = studentType;
    }

    public CourseTableDetailSegment sourceDataSource(String sourceDataSource) {
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

    public CourseTableDetailSegment sourceDataSourceName(String sourceDataSourceName) {
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
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseTableDetailSegment courseTableDetailSegment = (CourseTableDetailSegment) o;
        return Objects.equals(this.roomId, courseTableDetailSegment.roomId) &&
                Objects.equals(this.roomName, courseTableDetailSegment.roomName) &&
                Objects.equals(this.schoolYear, courseTableDetailSegment.schoolYear) &&
                Objects.equals(this.term, courseTableDetailSegment.term) &&
                Objects.equals(this.groupId, courseTableDetailSegment.groupId) &&
                Objects.equals(this.groupName, courseTableDetailSegment.groupName) &&
                Objects.equals(this.week, courseTableDetailSegment.week) &&
                Objects.equals(this.weekNum, courseTableDetailSegment.weekNum) &&
                Objects.equals(this.segment, courseTableDetailSegment.segment) &&
                Objects.equals(this.courseId, courseTableDetailSegment.courseId) &&
                Objects.equals(this.courseCode, courseTableDetailSegment.courseCode) &&
                Objects.equals(this.courseName, courseTableDetailSegment.courseName) &&
                Objects.equals(this.courseTableId, courseTableDetailSegment.courseTableId) &&
                Objects.equals(this.courseTableDetailId, courseTableDetailSegment.courseTableDetailId) &&
                Objects.equals(this.teacherIds, courseTableDetailSegment.teacherIds) &&
                Objects.equals(this.teacherNames, courseTableDetailSegment.teacherNames) &&
                Objects.equals(this.teacherNos, courseTableDetailSegment.teacherNos) &&
                Objects.equals(this.studentCount, courseTableDetailSegment.studentCount) &&
                Objects.equals(this.collegeId, courseTableDetailSegment.collegeId) &&
                Objects.equals(this.collegeName, courseTableDetailSegment.collegeName) &&
                Objects.equals(this.studentType, courseTableDetailSegment.studentType) &&
                Objects.equals(this.sourceDataSource, courseTableDetailSegment.sourceDataSource) &&
                Objects.equals(this.sourceDataSourceName, courseTableDetailSegment.sourceDataSourceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, roomName, schoolYear, term, groupId, groupName, week, weekNum, segment,courseId, courseCode,
                courseName, courseTableId, courseTableDetailId, teacherIds, teacherNames, teacherNos, studentCount, collegeId,
                collegeName, studentType, sourceDataSource, sourceDataSourceName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTableDetailSegment {\n");

        sb.append("    roomId: ").append(toIndentedString(roomId)).append("\n");
        sb.append("    roomName: ").append(toIndentedString(roomName)).append("\n");
        sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
        sb.append("    term: ").append(toIndentedString(term)).append("\n");
        sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
        sb.append("    groupName: ").append(toIndentedString(groupName)).append("\n");
        sb.append("    week: ").append(toIndentedString(week)).append("\n");
        sb.append("    weekNum: ").append(toIndentedString(weekNum)).append("\n");
        sb.append("    segment: ").append(toIndentedString(segment)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    courseTableId: ").append(toIndentedString(courseTableId)).append("\n");
        sb.append("    courseTableDetailId: ").append(toIndentedString(courseTableDetailId)).append("\n");
        sb.append("    teacherIds: ").append(toIndentedString(teacherIds)).append("\n");
        sb.append("    teacherNames: ").append(toIndentedString(teacherNames)).append("\n");
        sb.append("    teacherNos: ").append(toIndentedString(teacherNos)).append("\n");
        sb.append("    studentCount: ").append(toIndentedString(studentCount)).append("\n");
        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
        sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
        sb.append("    studentType: ").append(toIndentedString(studentType)).append("\n");
        sb.append("    sourceDataSource: ").append(toIndentedString(sourceDataSource)).append("\n");
        sb.append("    sourceDataSourceName: ").append(toIndentedString(sourceDataSourceName)).append("\n");
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

