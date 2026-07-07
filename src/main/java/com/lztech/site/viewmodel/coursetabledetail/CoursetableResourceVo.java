package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lztech.site.viewmodel.expertinformation.TeacherInfo;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * CoursetableResourceVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2026-01-15T11:43:32.117+08:00")

public class CoursetableResourceVo {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("courseTableDetailId")
    private String courseTableDetailId = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("schoolYear")
    private String schoolYear = null;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @JsonProperty("courseDate")
    private Date courseDate = null;

    @JsonProperty("classHour")
    private String classHour = null;

    @JsonProperty("teacherName")
    private String teacherName = null;

    @JsonProperty("teacherId")
    private String teacherId = null;

    @JsonProperty("teacherNo")
    private String teacherNo = null;

    @JsonProperty("term")
    private String term = null;

    @JsonProperty("className")
    private String className = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    @JsonProperty("segmentList")
    private String segmentList = null;

    @JsonProperty("week")
    private String week = null;

    @JsonProperty("weekNum")
    private String weekNum = null;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @JsonProperty("startDate")
    private Date startDate = null;

    @JsonProperty("isInClass")
    private Boolean isInClass = null;

    @JsonProperty("courseAttrName")
    private String courseAttrName = null;

    @JsonProperty("studentNum")
    private Integer studentNum = null;

    @JsonProperty("roomId")
    private String roomId = null;

    @JsonProperty("roomName")
    private String roomName = null;

    @JsonProperty("majorName")
    private String majorName = null;

    @JsonProperty("teacherInfoList")
    @Valid
    private List<TeacherInfo> teacherInfoList = null;

    @JsonProperty("sourceDataSource")
    private String sourceDataSource = null;

    @JsonProperty("sourceDataSourceName")
    private String sourceDataSourceName = null;

    public CoursetableResourceVo id(String id) {
        this.id = id;
        return this;
    }

    /**
     * 课程ID
     *
     * @return id
     **/
    @ApiModelProperty(value = "课程ID")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CoursetableResourceVo courseTableDetailId(String courseTableDetailId) {
        this.courseTableDetailId = courseTableDetailId;
        return this;
    }

    /**
     * 课表明细ID
     *
     * @return courseTableDetailId
     **/
    @ApiModelProperty(value = "课表明细ID")


    public String getCourseTableDetailId() {
        return courseTableDetailId;
    }

    public void setCourseTableDetailId(String courseTableDetailId) {
        this.courseTableDetailId = courseTableDetailId;
    }

    public CoursetableResourceVo courseName(String courseName) {
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

    public CoursetableResourceVo schoolYear(String schoolYear) {
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

    public CoursetableResourceVo courseDate(Date courseDate) {
        this.courseDate = courseDate;
        return this;
    }

    /**
     * 上课时间
     *
     * @return courseDate
     **/
    @ApiModelProperty(value = "上课时间")


    public Date getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(Date courseDate) {
        this.courseDate = courseDate;
    }

    public CoursetableResourceVo classHour(String classHour) {
        this.classHour = classHour;
        return this;
    }

    /**
     * 第几节次
     *
     * @return classHour
     **/
    @ApiModelProperty(value = "第几节次")


    public String getClassHour() {
        return classHour;
    }

    public void setClassHour(String classHour) {
        this.classHour = classHour;
    }

    public CoursetableResourceVo teacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    /**
     * 上课老师名称
     *
     * @return teacherName
     **/
    @ApiModelProperty(value = "上课老师名称")


    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public CoursetableResourceVo teacherId(String teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    /**
     * 上课老师ID
     *
     * @return teacherId
     **/
    @ApiModelProperty(value = "上课老师ID")


    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public CoursetableResourceVo teacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
        return this;
    }

    /**
     * 上课老师编号
     *
     * @return teacherNo
     **/
    @ApiModelProperty(value = "上课老师编号")


    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    public CoursetableResourceVo term(String term) {
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

    public CoursetableResourceVo className(String className) {
        this.className = className;
        return this;
    }

    /**
     * 上课班级名称
     *
     * @return className
     **/
    @ApiModelProperty(value = "上课班级名称")


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public CoursetableResourceVo collegeId(String collegeId) {
        this.collegeId = collegeId;
        return this;
    }

    /**
     * 学院ID
     *
     * @return collegeId
     **/
    @ApiModelProperty(value = "学院ID")


    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public CoursetableResourceVo collegeName(String collegeName) {
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

    public CoursetableResourceVo segmentList(String segmentList) {
        this.segmentList = segmentList;
        return this;
    }

    /**
     * 节次信息
     *
     * @return segmentList
     **/
    @ApiModelProperty(value = "节次信息")


    public String getSegmentList() {
        return segmentList;
    }

    public void setSegmentList(String segmentList) {
        this.segmentList = segmentList;
    }

    public CoursetableResourceVo week(String week) {
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

    public CoursetableResourceVo weekNum(String weekNum) {
        this.weekNum = weekNum;
        return this;
    }

    /**
     * 周几
     *
     * @return weekNum
     **/
    @ApiModelProperty(value = "周几")


    public String getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(String weekNum) {
        this.weekNum = weekNum;
    }

    public CoursetableResourceVo startDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * 学期开始时间
     *
     * @return startDate
     **/
    @ApiModelProperty(value = "学期开始时间")


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public CoursetableResourceVo isInClass(Boolean isInClass) {
        this.isInClass = isInClass;
        return this;
    }

    /**
     * 是否上课中
     *
     * @return isInClass
     **/
    @ApiModelProperty(value = "是否上课中")


    public Boolean isIsInClass() {
        return isInClass;
    }

    public void setIsInClass(Boolean isInClass) {
        this.isInClass = isInClass;
    }

    public CoursetableResourceVo courseAttrName(String courseAttrName) {
        this.courseAttrName = courseAttrName;
        return this;
    }

    /**
     * 课程属性名称
     *
     * @return courseAttrName
     **/
    @ApiModelProperty(value = "课程属性名称")


    public String getCourseAttrName() {
        return courseAttrName;
    }

    public void setCourseAttrName(String courseAttrName) {
        this.courseAttrName = courseAttrName;
    }

    public CoursetableResourceVo studentNum(Integer studentNum) {
        this.studentNum = studentNum;
        return this;
    }

    /**
     * 上课人数
     *
     * @return studentNum
     **/
    @ApiModelProperty(value = "上课人数")


    public Integer getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(Integer studentNum) {
        this.studentNum = studentNum;
    }

    public CoursetableResourceVo roomId(String roomId) {
        this.roomId = roomId;
        return this;
    }

    /**
     * 教室ID
     *
     * @return roomId
     **/
    @ApiModelProperty(value = "教室ID")


    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public CoursetableResourceVo roomName(String roomName) {
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

    public CoursetableResourceVo majorName(String majorName) {
        this.majorName = majorName;
        return this;
    }

    /**
     * 专业名称
     *
     * @return majorName
     **/
    @ApiModelProperty(value = "专业名称")


    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public CoursetableResourceVo teacherInfoList(List<TeacherInfo> teacherInfoList) {
        this.teacherInfoList = teacherInfoList;
        return this;
    }

    public CoursetableResourceVo addTeacherInfoListItem(TeacherInfo teacherInfoListItem) {
        if (this.teacherInfoList == null) {
            this.teacherInfoList = new ArrayList<TeacherInfo>();
        }
        this.teacherInfoList.add(teacherInfoListItem);
        return this;
    }

    /**
     * 上课老师集合
     *
     * @return teacherInfoList
     **/
    @ApiModelProperty(value = "上课老师集合")

    @Valid

    public List<TeacherInfo> getTeacherInfoList() {
        return teacherInfoList;
    }

    public void setTeacherInfoList(List<TeacherInfo> teacherInfoList) {
        this.teacherInfoList = teacherInfoList;
    }

    public CoursetableResourceVo sourceDataSource(String sourceDataSource) {
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

    public CoursetableResourceVo sourceDataSourceName(String sourceDataSourceName) {
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
        CoursetableResourceVo coursetableResourceVo = (CoursetableResourceVo) o;
        return Objects.equals(this.id, coursetableResourceVo.id) &&
                Objects.equals(this.courseTableDetailId, coursetableResourceVo.courseTableDetailId) &&
                Objects.equals(this.courseName, coursetableResourceVo.courseName) &&
                Objects.equals(this.schoolYear, coursetableResourceVo.schoolYear) &&
                Objects.equals(this.courseDate, coursetableResourceVo.courseDate) &&
                Objects.equals(this.classHour, coursetableResourceVo.classHour) &&
                Objects.equals(this.teacherName, coursetableResourceVo.teacherName) &&
                Objects.equals(this.teacherId, coursetableResourceVo.teacherId) &&
                Objects.equals(this.teacherNo, coursetableResourceVo.teacherNo) &&
                Objects.equals(this.term, coursetableResourceVo.term) &&
                Objects.equals(this.className, coursetableResourceVo.className) &&
                Objects.equals(this.collegeId, coursetableResourceVo.collegeId) &&
                Objects.equals(this.collegeName, coursetableResourceVo.collegeName) &&
                Objects.equals(this.segmentList, coursetableResourceVo.segmentList) &&
                Objects.equals(this.week, coursetableResourceVo.week) &&
                Objects.equals(this.weekNum, coursetableResourceVo.weekNum) &&
                Objects.equals(this.startDate, coursetableResourceVo.startDate) &&
                Objects.equals(this.isInClass, coursetableResourceVo.isInClass) &&
                Objects.equals(this.courseAttrName, coursetableResourceVo.courseAttrName) &&
                Objects.equals(this.studentNum, coursetableResourceVo.studentNum) &&
                Objects.equals(this.roomId, coursetableResourceVo.roomId) &&
                Objects.equals(this.roomName, coursetableResourceVo.roomName) &&
                Objects.equals(this.majorName, coursetableResourceVo.majorName) &&
                Objects.equals(this.teacherInfoList, coursetableResourceVo.teacherInfoList) &&
                Objects.equals(this.sourceDataSource, coursetableResourceVo.sourceDataSource) &&
                Objects.equals(this.sourceDataSourceName, coursetableResourceVo.sourceDataSourceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseTableDetailId, courseName, schoolYear, courseDate, classHour, teacherName, teacherId, teacherNo, term, className, collegeId, collegeName, segmentList, week, weekNum, startDate, isInClass, courseAttrName, studentNum, roomId, roomName, majorName, teacherInfoList, sourceDataSource, sourceDataSourceName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CoursetableResourceVo {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    courseTableDetailId: ").append(toIndentedString(courseTableDetailId)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
        sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
        sb.append("    classHour: ").append(toIndentedString(classHour)).append("\n");
        sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
        sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
        sb.append("    teacherNo: ").append(toIndentedString(teacherNo)).append("\n");
        sb.append("    term: ").append(toIndentedString(term)).append("\n");
        sb.append("    className: ").append(toIndentedString(className)).append("\n");
        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
        sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
        sb.append("    segmentList: ").append(toIndentedString(segmentList)).append("\n");
        sb.append("    week: ").append(toIndentedString(week)).append("\n");
        sb.append("    weekNum: ").append(toIndentedString(weekNum)).append("\n");
        sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
        sb.append("    isInClass: ").append(toIndentedString(isInClass)).append("\n");
        sb.append("    courseAttrName: ").append(toIndentedString(courseAttrName)).append("\n");
        sb.append("    studentNum: ").append(toIndentedString(studentNum)).append("\n");
        sb.append("    roomId: ").append(toIndentedString(roomId)).append("\n");
        sb.append("    roomName: ").append(toIndentedString(roomName)).append("\n");
        sb.append("    majorName: ").append(toIndentedString(majorName)).append("\n");
        sb.append("    teacherInfoList: ").append(toIndentedString(teacherInfoList)).append("\n");
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

