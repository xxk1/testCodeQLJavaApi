package com.lztech.site.resource.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseTableDetailResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-08-09T09:20:47.695Z")


public class CourseTableDetailResource {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseCode")
    private String courseCode = null;
    @JsonProperty("groupId")
    private String groupId = null;
    @JsonProperty("groupNo")
    private String groupNo = null;

    @JsonProperty("groupName")
    private String groupName = null;

    @JsonProperty("courseType")
    private String courseType = null;

    @JsonProperty("teacherName")
    private String teacherName = null;

    @JsonProperty("teacherNameAndNo")
    private String teacherNameAndNo = null;

    @JsonProperty("schoolYear")
    private String schoolYear = null;

    @JsonProperty("term")
    private String term = null;

    @JsonProperty("courseDate")
    private String courseDate = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("weekNum")
    private String weekNum = null;

    @JsonProperty("week")
    private String week = null;

    @JsonProperty("roomId")
    private String roomId = null;

    @JsonProperty("roomName")
    private String roomName = null;

    @JsonProperty("buildName")
    private String buildName = null;

    @JsonProperty("segments")
    private String segments = null;

    @JsonProperty("segmentStartTime")
    private String segmentStartTime = null;
    @JsonProperty("segmentEndTime")
    private String segmentEndTime = null;

    @JsonProperty("currentSegment")
    private Integer currentSegment = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    @JsonProperty("courseKind")
    private Integer courseKind = null;

    @JsonProperty("courseAttrName")
    private String courseAttrName = null;

    @JsonProperty("studentNum")
    private Object studentNum = null;

    @JsonProperty("hasLive")
    private boolean hasLive;
    @JsonProperty("source")
    private Integer source;
    @JsonProperty("classCompositionName")
    private String classCompositionName;
    @JsonProperty("teacherResourceList")
    @Valid
    private List<TeacherResource> teacherResourceList = null;

    @JsonProperty("studentType")
    private Integer studentType = null;
    @JsonProperty("studentTypeName")
    private String studentTypeName = null;

    @JsonProperty("courseTableId")
    private String courseTableId = null;
    @JsonProperty("sourceDataSourceName")
    private String sourceDataSourceName = null;
    @JsonProperty("sourceDataSource")
    private String sourceDataSource = null;
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseTableId() {
        return courseTableId;
    }

    public void setCourseTableId(String courseTableId) {
        this.courseTableId = courseTableId;
    }
    public String getSourceDataSourceName() {
        return sourceDataSourceName;
    }

    public void setSourceDataSourceName(String sourceDataSourceName) {
        this.sourceDataSourceName = sourceDataSourceName;
    }
    public String getSourceDataSource() {
        return sourceDataSource;
    }

    public void setSourceDataSource(String sourceDataSource) {
        this.sourceDataSource = sourceDataSource;
    }

    public CourseTableDetailResource id(String id) {
        this.id = id;
        return this;
    }

    /**
     * 课表id
     *
     * @return id
     **/
    @ApiModelProperty(value = "课表id")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CourseTableDetailResource courseId(String courseId) {
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

    public CourseTableDetailResource groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    /**
     * 组Id
     *
     * @return groupId
     **/
    @ApiModelProperty(value = "组编号")


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }


    public CourseTableDetailResource groupNo(String groupNo) {
        this.groupNo = groupNo;
        return this;
    }

    /**
     * 组编号
     *
     * @return groupNo
     **/
    @ApiModelProperty(value = "组编号")


    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    public CourseTableDetailResource groupName(String groupName) {
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

    public CourseTableDetailResource courseType(String courseType) {
        this.courseType = courseType;
        return this;
    }

    /**
     * 课程类型
     *
     * @return courseType
     **/
    @ApiModelProperty(value = "课程类型")


    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public CourseTableDetailResource teacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    /**
     * 教师名称
     *
     * @return teacherName
     **/
    @ApiModelProperty(value = "教师名称")


    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public CourseTableDetailResource teacherNameAndNo(String teacherNameAndNo) {
        this.teacherNameAndNo = teacherNameAndNo;
        return this;
    }

    /**
     * 教师姓名和编号（格式：名称 编号）
     *
     * @return teacherNameAndNo
     **/
    @ApiModelProperty(value = "教师姓名和编号（格式：名称 编号）")


    public String getTeacherNameAndNo() {
        return teacherNameAndNo;
    }

    public void setTeacherNameAndNo(String teacherNameAndNo) {
        this.teacherNameAndNo = teacherNameAndNo;
    }

    public CourseTableDetailResource schoolYear(String schoolYear) {
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

    public CourseTableDetailResource term(String term) {
        this.term = term;
        return this;
    }

    /**
     * 学期
     *
     * @return term
     **/
    @ApiModelProperty(value = "学期")


    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public CourseTableDetailResource courseDate(String courseDate) {
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

    public CourseTableDetailResource courseName(String courseName) {
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

    public CourseTableDetailResource weekNum(String weekNum) {
        this.weekNum = weekNum;
        return this;
    }

    /**
     * 星期几
     *
     * @return weekNum
     **/
    @ApiModelProperty(value = "星期几")


    public String getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(String weekNum) {
        this.weekNum = weekNum;
    }

    public CourseTableDetailResource week(String week) {
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

    public CourseTableDetailResource roomId(String roomId) {
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

    public CourseTableDetailResource roomName(String roomName) {
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

    public CourseTableDetailResource buildName(String buildName) {
        this.buildName = buildName;
        return this;
    }

    /**
     * 教学楼
     *
     * @return buildName
     **/
    @ApiModelProperty(value = "教学楼")


    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public CourseTableDetailResource segments(String segments) {
        this.segments = segments;
        return this;
    }

    /**
     * 节次
     *
     * @return segments
     **/
    @ApiModelProperty(value = "节次")


    public String getSegments() {
        return segments;
    }

    public void setSegments(String segments) {
        this.segments = segments;
    }

    public CourseTableDetailResource segmentStartTime(String segmentStartTime) {
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

    public CourseTableDetailResource segmentEndTime(String segmentEndTime) {
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


    public CourseTableDetailResource currentSegment(Integer currentSegment) {
        this.currentSegment = currentSegment;
        return this;
    }

    /**
     * 当前所处节次
     *
     * @return currentSegment
     **/
    @ApiModelProperty(value = "当前所处节次")


    public Integer getCurrentSegment() {
        return currentSegment;
    }

    public void setCurrentSegment(Integer currentSegment) {
        this.currentSegment = currentSegment;
    }

    public CourseTableDetailResource collegeId(String collegeId) {
        this.collegeId = collegeId;
        return this;
    }

    /**
     * 学院名称
     *
     * @return collegeId
     **/
    @ApiModelProperty(value = "学院名称")


    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public CourseTableDetailResource collegeName(String collegeName) {
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

    public CourseTableDetailResource courseKind(Integer courseKind) {
        this.courseKind = courseKind;
        return this;
    }

    /**
     * 课表类型  0：理论课；1：实践课
     *
     * @return courseKind
     **/
    @ApiModelProperty(value = "课表类型  0：理论课；1：实践课")


    public Integer getCourseKind() {
        return courseKind;
    }

    public void setCourseKind(Integer courseKind) {
        this.courseKind = courseKind;
    }

    public CourseTableDetailResource courseAttrName(String courseAttrName) {
        this.courseAttrName = courseAttrName;
        return this;
    }

    /**
     * 课程类别
     *
     * @return courseAttrName
     **/
    @ApiModelProperty(value = "课程类别")


    public String getCourseAttrName() {
        return courseAttrName;
    }

    public void setCourseAttrName(String courseAttrName) {
        this.courseAttrName = courseAttrName;
    }

    public CourseTableDetailResource studentNum(Integer studentNum) {
        this.studentNum = studentNum;
        return this;
    }

    /**
     * 学生人数
     *
     * @return studentNum
     **/
    @ApiModelProperty(value = "学生人数")


    public Object getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(Object studentNum) {
        this.studentNum = studentNum;
    }

    public CourseTableDetailResource hasLive(Boolean hasLive) {
        this.hasLive = hasLive;
        return this;
    }

    /**
     * 是否直播
     *
     * @return hasLive
     **/
    @ApiModelProperty(value = "是否直播")


    public boolean getHasLive() {
        return hasLive;
    }

    public void setHasLive(boolean hasLive) {
        this.hasLive = hasLive;
    }

    public CourseTableDetailResource teacherResourceList(List<TeacherResource> teacherResourceList) {
        this.teacherResourceList = teacherResourceList;
        return this;
    }

    public CourseTableDetailResource addTeacherResourceListItem(TeacherResource teacherResourceListItem) {
        if (this.teacherResourceList == null) {
            this.teacherResourceList = new ArrayList<TeacherResource>();
        }
        this.teacherResourceList.add(teacherResourceListItem);
        return this;
    }

    /**
     * Get teacherResourceList
     *
     * @return teacherResourceList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<TeacherResource> getTeacherResourceList() {
        return teacherResourceList;
    }

    public void setTeacherResourceList(List<TeacherResource> teacherResourceList) {
        this.teacherResourceList = teacherResourceList;
    }

    /**
     * 班级类型
     *
     * @return source
     **/
    @ApiModelProperty(value = "班级类型")
    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    /**
     * 班级组成
     *
     * @return source
     **/
    @ApiModelProperty(value = "班级组成")
    public String getClassCompositionName() {
        return classCompositionName;
    }

    public void setClassCompositionName(String classCompositionName) {
        this.classCompositionName = classCompositionName;
    }

    public CourseTableDetailResource studentType(Integer studentType) {
        this.studentType = studentType;
        return this;
    }

    /**
     * 学生类型
     *
     * @return studentType
     **/
    @ApiModelProperty(value = "学生类型")


    public Integer getStudentType() {
        return studentType;
    }

    public void setStudentType(Integer studentType) {
        this.studentType = studentType;
    }

    public CourseTableDetailResource studentTypeName(String studentTypeName) {
        this.studentTypeName = studentTypeName;
        return this;
    }

    /**
     * 学生类型名称
     *
     * @return studentTypeName
     **/
    @ApiModelProperty(value = "学生类型名称")


    public String getStudentTypeName() {
        return studentTypeName;
    }

    public void setStudentTypeName(String studentTypeName) {
        this.studentTypeName = studentTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseTableDetailResource courseTableDetailResource = (CourseTableDetailResource) o;
        return Objects.equals(this.id, courseTableDetailResource.id) &&
                Objects.equals(this.courseId, courseTableDetailResource.courseId) &&
                Objects.equals(this.groupId, courseTableDetailResource.groupId) &&
                Objects.equals(this.groupNo, courseTableDetailResource.groupNo) &&
                Objects.equals(this.groupName, courseTableDetailResource.groupName) &&
                Objects.equals(this.courseType, courseTableDetailResource.courseType) &&
                Objects.equals(this.teacherName, courseTableDetailResource.teacherName) &&
                Objects.equals(this.teacherNameAndNo, courseTableDetailResource.teacherNameAndNo) &&
                Objects.equals(this.schoolYear, courseTableDetailResource.schoolYear) &&
                Objects.equals(this.term, courseTableDetailResource.term) &&
                Objects.equals(this.courseDate, courseTableDetailResource.courseDate) &&
                Objects.equals(this.courseName, courseTableDetailResource.courseName) &&
                Objects.equals(this.weekNum, courseTableDetailResource.weekNum) &&
                Objects.equals(this.week, courseTableDetailResource.week) &&
                Objects.equals(this.roomId, courseTableDetailResource.roomId) &&
                Objects.equals(this.roomName, courseTableDetailResource.roomName) &&
                Objects.equals(this.buildName, courseTableDetailResource.buildName) &&
                Objects.equals(this.segments, courseTableDetailResource.segments) &&
                Objects.equals(this.segmentStartTime, courseTableDetailResource.segmentStartTime) &&
                Objects.equals(this.segmentEndTime, courseTableDetailResource.segmentEndTime) &&
                Objects.equals(this.currentSegment, courseTableDetailResource.currentSegment) &&
                Objects.equals(this.collegeId, courseTableDetailResource.collegeId) &&
                Objects.equals(this.collegeName, courseTableDetailResource.collegeName) &&
                Objects.equals(this.courseKind, courseTableDetailResource.courseKind) &&
                Objects.equals(this.courseAttrName, courseTableDetailResource.courseAttrName) &&
                Objects.equals(this.studentNum, courseTableDetailResource.studentNum) &&
                Objects.equals(this.hasLive, courseTableDetailResource.hasLive) &&
                Objects.equals(this.teacherResourceList, courseTableDetailResource.teacherResourceList) &&
                Objects.equals(this.studentType, courseTableDetailResource.studentType) &&
                Objects.equals(this.studentTypeName, courseTableDetailResource.studentTypeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseId, groupId, groupNo, groupName, courseType, teacherName, teacherNameAndNo, schoolYear
                , term, courseDate, courseName, weekNum, week, roomId, roomName, buildName, segments, segmentStartTime,
                segmentEndTime, currentSegment, collegeId, collegeName, courseKind, courseAttrName, studentNum, hasLive,
                teacherResourceList, studentType, studentTypeName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTableDetailResource {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
        sb.append("    groupNo: ").append(toIndentedString(groupNo)).append("\n");
        sb.append("    groupName: ").append(toIndentedString(groupName)).append("\n");
        sb.append("    courseType: ").append(toIndentedString(courseType)).append("\n");
        sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
        sb.append("    teacherNameAndNo: ").append(toIndentedString(teacherNameAndNo)).append("\n");
        sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
        sb.append("    term: ").append(toIndentedString(term)).append("\n");
        sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    weekNum: ").append(toIndentedString(weekNum)).append("\n");
        sb.append("    week: ").append(toIndentedString(week)).append("\n");
        sb.append("    roomId: ").append(toIndentedString(roomId)).append("\n");
        sb.append("    roomName: ").append(toIndentedString(roomName)).append("\n");
        sb.append("    buildName: ").append(toIndentedString(buildName)).append("\n");
        sb.append("    segments: ").append(toIndentedString(segments)).append("\n");
        sb.append("    currentSegment: ").append(toIndentedString(currentSegment)).append("\n");
        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
        sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
        sb.append("    courseKind: ").append(toIndentedString(courseKind)).append("\n");
        sb.append("    courseAttrName: ").append(toIndentedString(courseAttrName)).append("\n");
        sb.append("    studentNum: ").append(toIndentedString(studentNum)).append("\n");
        sb.append("    hasLive: ").append(toIndentedString(hasLive)).append("\n");
        sb.append("    teacherResourceList: ").append(toIndentedString(teacherResourceList)).append("\n");
        sb.append("    studentType: ").append(toIndentedString(studentType)).append("\n");
        sb.append("    studentTypeName: ").append(toIndentedString(studentTypeName)).append("\n");
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

