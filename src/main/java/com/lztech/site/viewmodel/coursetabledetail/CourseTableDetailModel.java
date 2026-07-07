package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lztech.site.resource.coursetabledetail.TeacherResource;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseTableDetailModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-03-21T12:18:57.276Z")


public class CourseTableDetailModel {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("courseId")
    private String courseId = null;

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
    private Integer studentNum = null;

    @JsonProperty("sourceId")
    private String sourceId = null;

    @JsonProperty("sourceName")
    private String sourceName = null;

    @JsonProperty("hasLive")
    private Boolean hasLive = null;

    @JsonProperty("teacherResourceList")
    @Valid
    private List<TeacherResource> teacherResourceList = null;

    public CourseTableDetailModel id(String id) {
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

    public CourseTableDetailModel courseId(String courseId) {
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

    public CourseTableDetailModel groupNo(String groupNo) {
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

    public CourseTableDetailModel groupName(String groupName) {
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

    public CourseTableDetailModel courseType(String courseType) {
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

    public CourseTableDetailModel teacherName(String teacherName) {
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

    public CourseTableDetailModel teacherNameAndNo(String teacherNameAndNo) {
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

    public CourseTableDetailModel schoolYear(String schoolYear) {
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

    public CourseTableDetailModel term(String term) {
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

    public CourseTableDetailModel courseDate(String courseDate) {
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

    public CourseTableDetailModel courseName(String courseName) {
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

    public CourseTableDetailModel weekNum(String weekNum) {
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

    public CourseTableDetailModel week(String week) {
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

    public CourseTableDetailModel roomId(String roomId) {
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

    public CourseTableDetailModel roomName(String roomName) {
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

    public CourseTableDetailModel buildName(String buildName) {
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

    public CourseTableDetailModel segments(String segments) {
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

    public CourseTableDetailModel currentSegment(Integer currentSegment) {
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

    public CourseTableDetailModel collegeId(String collegeId) {
        this.collegeId = collegeId;
        return this;
    }

    /**
     * 学院Id
     *
     * @return collegeId
     **/
    @ApiModelProperty(value = "学院Id")


    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public CourseTableDetailModel collegeName(String collegeName) {
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

    public CourseTableDetailModel courseKind(Integer courseKind) {
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

    public CourseTableDetailModel courseAttrName(String courseAttrName) {
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

    public CourseTableDetailModel studentNum(Integer studentNum) {
        this.studentNum = studentNum;
        return this;
    }

    /**
     * 学生人数
     *
     * @return studentNum
     **/
    @ApiModelProperty(value = "学生人数")


    public Integer getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(Integer studentNum) {
        this.studentNum = studentNum;
    }

    public CourseTableDetailModel sourceId(String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    /**
     * 数据来源id
     *
     * @return sourceId
     **/
    @ApiModelProperty(value = "数据来源id")


    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public CourseTableDetailModel sourceName(String sourceName) {
        this.sourceName = sourceName;
        return this;
    }

    /**
     * 数据来源名称
     *
     * @return sourceName
     **/
    @ApiModelProperty(value = "数据来源名称")


    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public CourseTableDetailModel hasLive(Boolean hasLive) {
        this.hasLive = hasLive;
        return this;
    }

    /**
     * 是否直播
     *
     * @return hasLive
     **/
    @ApiModelProperty(value = "是否直播")


    public Boolean isHasLive() {
        return hasLive;
    }

    public void setHasLive(Boolean hasLive) {
        this.hasLive = hasLive;
    }

    public CourseTableDetailModel teacherResourceList(List<TeacherResource> teacherResourceList) {
        this.teacherResourceList = teacherResourceList;
        return this;
    }

    public CourseTableDetailModel addTeacherResourceListItem(TeacherResource teacherResourceListItem) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseTableDetailModel courseTableDetailModel = (CourseTableDetailModel) o;
        return Objects.equals(this.id, courseTableDetailModel.id) &&
                Objects.equals(this.courseId, courseTableDetailModel.courseId) &&
                Objects.equals(this.groupNo, courseTableDetailModel.groupNo) &&
                Objects.equals(this.groupName, courseTableDetailModel.groupName) &&
                Objects.equals(this.courseType, courseTableDetailModel.courseType) &&
                Objects.equals(this.teacherName, courseTableDetailModel.teacherName) &&
                Objects.equals(this.teacherNameAndNo, courseTableDetailModel.teacherNameAndNo) &&
                Objects.equals(this.schoolYear, courseTableDetailModel.schoolYear) &&
                Objects.equals(this.term, courseTableDetailModel.term) &&
                Objects.equals(this.courseDate, courseTableDetailModel.courseDate) &&
                Objects.equals(this.courseName, courseTableDetailModel.courseName) &&
                Objects.equals(this.weekNum, courseTableDetailModel.weekNum) &&
                Objects.equals(this.week, courseTableDetailModel.week) &&
                Objects.equals(this.roomId, courseTableDetailModel.roomId) &&
                Objects.equals(this.roomName, courseTableDetailModel.roomName) &&
                Objects.equals(this.buildName, courseTableDetailModel.buildName) &&
                Objects.equals(this.segments, courseTableDetailModel.segments) &&
                Objects.equals(this.currentSegment, courseTableDetailModel.currentSegment) &&
                Objects.equals(this.collegeId, courseTableDetailModel.collegeId) &&
                Objects.equals(this.collegeName, courseTableDetailModel.collegeName) &&
                Objects.equals(this.courseKind, courseTableDetailModel.courseKind) &&
                Objects.equals(this.courseAttrName, courseTableDetailModel.courseAttrName) &&
                Objects.equals(this.studentNum, courseTableDetailModel.studentNum) &&
                Objects.equals(this.sourceId, courseTableDetailModel.sourceId) &&
                Objects.equals(this.sourceName, courseTableDetailModel.sourceName) &&
                Objects.equals(this.hasLive, courseTableDetailModel.hasLive) &&
                Objects.equals(this.teacherResourceList, courseTableDetailModel.teacherResourceList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseId, groupNo, groupName, courseType, teacherName,
                teacherNameAndNo, schoolYear, term, courseDate, courseName, weekNum, week,
                roomId, roomName, buildName, segments, currentSegment, collegeId, collegeName,
                courseKind, courseAttrName, studentNum, sourceId, sourceName, hasLive, teacherResourceList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTableDetailModel {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
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
        sb.append("    sourceId: ").append(toIndentedString(sourceId)).append("\n");
        sb.append("    sourceName: ").append(toIndentedString(sourceName)).append("\n");
        sb.append("    hasLive: ").append(toIndentedString(hasLive)).append("\n");
        sb.append("    teacherResourceList: ").append(toIndentedString(teacherResourceList)).append("\n");
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

