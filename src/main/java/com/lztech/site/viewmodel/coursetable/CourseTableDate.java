package com.lztech.site.viewmodel.coursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseTableDate
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-01-29T08:32:35.006Z")


public class CourseTableDate {
    @JsonProperty("courseTableId")
    private String courseTableId = null;

    @JsonProperty("startTime")
    private String startTime = null;

    @JsonProperty("endTime")
    private String endTime = null;

    @JsonProperty("teacherId")
    private String teacherId = null;

    @JsonProperty("teacherName")
    private String teacherName = null;

    @JsonProperty("courseDate")
    private String courseDate = null;

    @JsonProperty("weekNum")
    private Integer weekNum = null;

    @JsonProperty("roomId")
    private String roomId = null;

    @JsonProperty("roomName")
    private String roomName = null;

    @JsonProperty("status")
    private Integer status = null;

    @JsonProperty("segmentList")
    @Valid
    private List<CourseTableDateSegmentList> segmentList = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("teachingClassName")
    private String teachingClassName = null;

    @JsonProperty("teachingClassId")
    private String teachingClassId = null;

    @JsonProperty("courseAttr")
    private Integer courseAttr = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    @JsonProperty("segment")
    private String segment = null;

    @JsonProperty("nowWeek")
    private String nowWeek = null;

    @JsonProperty("studentCount")
    private String studentCount = null;

    @JsonProperty("courseType")
    private Integer courseType = null;

    @JsonProperty("courseTypeName")
    private String courseTypeName = null;

    @JsonProperty("weekType")
    private String weekType = null;

    @JsonProperty("className")
    private String className = null;

    @JsonProperty("courseCategory")
    private Integer courseCategory = null;

    @JsonProperty("courseCategoryName")
    private String courseCategoryName = null;

    @JsonProperty("projectNames")
    private String projectNames = null;

    public String getProjectNames() {
        return projectNames;
    }

    public void setProjectNames(String projectNames) {
        this.projectNames = projectNames;
    }

    public CourseTableDate courseTableId(String courseTableId) {
        this.courseTableId = courseTableId;
        return this;
    }

    /**
     * 课表id
     *
     * @return courseTableId
     **/
    @ApiModelProperty(value = "课表id")


    public String getCourseTableId() {
        return courseTableId;
    }

    public void setCourseTableId(String courseTableId) {
        this.courseTableId = courseTableId;
    }

    public CourseTableDate startTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * 课表开始时间 yyyy-MM-dd HH:mm:ss
     *
     * @return startTime
     **/
    @ApiModelProperty(value = "课表开始时间 yyyy-MM-dd HH:mm:ss")


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public CourseTableDate endTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    /**
     * 课表结束时间 yyyy-MM-dd HH:mm:ss
     *
     * @return endTime
     **/
    @ApiModelProperty(value = "课表结束时间 yyyy-MM-dd HH:mm:ss")


    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public CourseTableDate teacherId(String teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    /**
     * 上课老师id,多个老师使用“,” 隔开
     *
     * @return teacherId
     **/
    @ApiModelProperty(value = "上课老师id,多个老师使用“,” 隔开")


    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public CourseTableDate teacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    /**
     * 上课老师名称,多个老师使用“,” 隔开
     *
     * @return teacherName
     **/
    @ApiModelProperty(value = "上课老师名称,多个老师使用“,” 隔开")


    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public CourseTableDate courseDate(String courseDate) {
        this.courseDate = courseDate;
        return this;
    }

    /**
     * 上课时间
     *
     * @return courseDate
     **/
    @ApiModelProperty(value = "上课时间")


    public String getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(String courseDate) {
        this.courseDate = courseDate;
    }

    public CourseTableDate weekNum(Integer weekNum) {
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

    public CourseTableDate roomId(String roomId) {
        this.roomId = roomId;
        return this;
    }

    /**
     * 上课教室id
     *
     * @return roomId
     **/
    @ApiModelProperty(value = "上课教室id")


    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public CourseTableDate roomName(String roomName) {
        this.roomName = roomName;
        return this;
    }

    /**
     * 上课教室名称
     *
     * @return roomName
     **/
    @ApiModelProperty(value = "上课教室名称")


    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public CourseTableDate status(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * 课表状态:0-未上课,1-上课中，2-已下课
     *
     * @return status
     **/
    @ApiModelProperty(value = "课表状态:0-未上课,1-上课中，2-已下课")


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public CourseTableDate segmentList(List<CourseTableDateSegmentList> segmentList) {
        this.segmentList = segmentList;
        return this;
    }

    public CourseTableDate addSegmentListItem(CourseTableDateSegmentList segmentListItem) {
        if (this.segmentList == null) {
            this.segmentList = new ArrayList<CourseTableDateSegmentList>();
        }
        this.segmentList.add(segmentListItem);
        return this;
    }

    /**
     * 节次列表
     *
     * @return segmentList
     **/
    @ApiModelProperty(value = "节次列表")

    @Valid

    public List<CourseTableDateSegmentList> getSegmentList() {
        return segmentList;
    }

    public void setSegmentList(List<CourseTableDateSegmentList> segmentList) {
        this.segmentList = segmentList;
    }

    public CourseTableDate courseName(String courseName) {
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

    public CourseTableDate courseId(String courseId) {
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

    public CourseTableDate teachingClassName(String teachingClassName) {
        this.teachingClassName = teachingClassName;
        return this;
    }

    /**
     * 上课班级
     *
     * @return teachingClassName
     **/
    @ApiModelProperty(value = "上课班级")


    public String getTeachingClassName() {
        return teachingClassName;
    }

    public void setTeachingClassName(String teachingClassName) {
        this.teachingClassName = teachingClassName;
    }

    public CourseTableDate teachingClassId(String teachingClassId) {
        this.teachingClassId = teachingClassId;
        return this;
    }

    /**
     * 班级id
     *
     * @return teachingClassId
     **/
    @ApiModelProperty(value = "班级id")


    public String getTeachingClassId() {
        return teachingClassId;
    }

    public void setTeachingClassId(String teachingClassId) {
        this.teachingClassId = teachingClassId;
    }

    public CourseTableDate courseAttr(Integer courseAttr) {
        this.courseAttr = courseAttr;
        return this;
    }

    /**
     * 0:课标班 1:辅导班
     *
     * @return courseAttr
     **/
    @ApiModelProperty(value = "0:课标班 1:辅导班")


    public Integer getCourseAttr() {
        return courseAttr;
    }

    public void setCourseAttr(Integer courseAttr) {
        this.courseAttr = courseAttr;
    }

    public CourseTableDate collegeId(String collegeId) {
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

    public CourseTableDate collegeName(String collegeName) {
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

    public CourseTableDate segment(String segment) {
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

    public CourseTableDate nowWeek(String nowWeek) {
        this.nowWeek = nowWeek;
        return this;
    }

    /**
     * 当前周次
     *
     * @return nowWeek
     **/
    @ApiModelProperty(value = "当前周次")


    public String getNowWeek() {
        return nowWeek;
    }

    public void setNowWeek(String nowWeek) {
        this.nowWeek = nowWeek;
    }

    public CourseTableDate studentCount(String studentCount) {
        this.studentCount = studentCount;
        return this;
    }

    /**
     * 选课人数
     *
     * @return studentCount
     **/
    @ApiModelProperty(value = "选课人数")


    public String getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(String studentCount) {
        this.studentCount = studentCount;
    }

    public CourseTableDate courseType(Integer courseType) {
        this.courseType = courseType;
        return this;
    }

    /**
     * 0:必修 1:选修 2: 公选 3:指选 4:国际课 5:双学位 6:其他
     *
     * @return courseType
     **/
    @ApiModelProperty(value = "0:必修 1:选修 2: 公选 3:指选 4:国际课 5:双学位 6:其他")


    public Integer getCourseType() {
        return courseType;
    }

    public void setCourseType(Integer courseType) {
        this.courseType = courseType;
    }

    public CourseTableDate courseTypeName(String courseTypeName) {
        this.courseTypeName = courseTypeName;
        return this;
    }

    /**
     * 课程类型名称
     *
     * @return courseTypeName
     **/
    @ApiModelProperty(value = "课程类型名称")


    public String getCourseTypeName() {
        return courseTypeName;
    }

    public void setCourseTypeName(String courseTypeName) {
        this.courseTypeName = courseTypeName;
    }

    public CourseTableDate weekType(String weekType) {
        this.weekType = weekType;
        return this;
    }

    /**
     * 单双周
     *
     * @return weekType
     **/
    @ApiModelProperty(value = "单双周")


    public String getWeekType() {
        return weekType;
    }

    public void setWeekType(String weekType) {
        this.weekType = weekType;
    }

    public CourseTableDate className(String className) {
        this.className = className;
        return this;
    }

    /**
     * 上课班级
     *
     * @return className
     **/
    @ApiModelProperty(value = "上课班级")


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public CourseTableDate courseCategory(Integer courseCategory) {
        this.courseCategory = courseCategory;
        return this;
    }

    /**
     * 课程种类id
     *
     * @return courseCategory
     **/
    @ApiModelProperty(value = "课程种类id")


    public Integer getCourseCategory() {
        return courseCategory;
    }

    public void setCourseCategory(Integer courseCategory) {
        this.courseCategory = courseCategory;
    }

    public CourseTableDate courseCategoryName(String courseCategoryName) {
        this.courseCategoryName = courseCategoryName;
        return this;
    }

    /**
     * 课程种类名称
     *
     * @return courseCategoryName
     **/
    @ApiModelProperty(value = "课程种类名称")


    public String getCourseCategoryName() {
        return courseCategoryName;
    }

    public void setCourseCategoryName(String courseCategoryName) {
        this.courseCategoryName = courseCategoryName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseTableDate courseTableDate = (CourseTableDate) o;
        return Objects.equals(this.courseTableId, courseTableDate.courseTableId) &&
                Objects.equals(this.startTime, courseTableDate.startTime) &&
                Objects.equals(this.endTime, courseTableDate.endTime) &&
                Objects.equals(this.teacherId, courseTableDate.teacherId) &&
                Objects.equals(this.teacherName, courseTableDate.teacherName) &&
                Objects.equals(this.courseDate, courseTableDate.courseDate) &&
                Objects.equals(this.weekNum, courseTableDate.weekNum) &&
                Objects.equals(this.roomId, courseTableDate.roomId) &&
                Objects.equals(this.roomName, courseTableDate.roomName) &&
                Objects.equals(this.status, courseTableDate.status) &&
                Objects.equals(this.segmentList, courseTableDate.segmentList) &&
                Objects.equals(this.courseName, courseTableDate.courseName) &&
                Objects.equals(this.courseId, courseTableDate.courseId) &&
                Objects.equals(this.teachingClassName, courseTableDate.teachingClassName) &&
                Objects.equals(this.teachingClassId, courseTableDate.teachingClassId) &&
                Objects.equals(this.courseAttr, courseTableDate.courseAttr) &&
                Objects.equals(this.collegeId, courseTableDate.collegeId) &&
                Objects.equals(this.collegeName, courseTableDate.collegeName) &&
                Objects.equals(this.segment, courseTableDate.segment) &&
                Objects.equals(this.nowWeek, courseTableDate.nowWeek) &&
                Objects.equals(this.studentCount, courseTableDate.studentCount) &&
                Objects.equals(this.courseType, courseTableDate.courseType) &&
                Objects.equals(this.courseTypeName, courseTableDate.courseTypeName) &&
                Objects.equals(this.weekType, courseTableDate.weekType) &&
                Objects.equals(this.className, courseTableDate.className) &&
                Objects.equals(this.courseCategory, courseTableDate.courseCategory) &&
                Objects.equals(this.courseCategoryName, courseTableDate.courseCategoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseTableId, startTime, endTime, teacherId, teacherName, courseDate, weekNum, roomId, roomName,
                status, segmentList, courseName, courseId, teachingClassName,
                teachingClassId, courseAttr, collegeId, collegeName, segment, nowWeek, studentCount,
                courseType, courseTypeName, weekType, className, courseCategory, courseCategoryName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTableDate {\n");

        sb.append("    courseTableId: ").append(toIndentedString(courseTableId)).append("\n");
        sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
        sb.append("    endTime: ").append(toIndentedString(endTime)).append("\n");
        sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
        sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
        sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
        sb.append("    weekNum: ").append(toIndentedString(weekNum)).append("\n");
        sb.append("    roomId: ").append(toIndentedString(roomId)).append("\n");
        sb.append("    roomName: ").append(toIndentedString(roomName)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    segmentList: ").append(toIndentedString(segmentList)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    teachingClassName: ").append(toIndentedString(teachingClassName)).append("\n");
        sb.append("    teachingClassId: ").append(toIndentedString(teachingClassId)).append("\n");
        sb.append("    courseAttr: ").append(toIndentedString(courseAttr)).append("\n");
        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
        sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
        sb.append("    segment: ").append(toIndentedString(segment)).append("\n");
        sb.append("    nowWeek: ").append(toIndentedString(nowWeek)).append("\n");
        sb.append("    studentCount: ").append(toIndentedString(studentCount)).append("\n");
        sb.append("    courseType: ").append(toIndentedString(courseType)).append("\n");
        sb.append("    courseTypeName: ").append(toIndentedString(courseTypeName)).append("\n");
        sb.append("    weekType: ").append(toIndentedString(weekType)).append("\n");
        sb.append("    className: ").append(toIndentedString(className)).append("\n");
        sb.append("    courseCategory: ").append(toIndentedString(courseCategory)).append("\n");
        sb.append("    courseCategoryName: ").append(toIndentedString(courseCategoryName)).append("\n");
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

