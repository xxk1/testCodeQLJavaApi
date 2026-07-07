package com.lztech.site.viewmodel.evaluation;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * EvaluationCourseTableVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-12-14T09:20:24.020Z")


public class EvaluationCourseTableVo   {
    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("collegeCode")
    private String collegeCode = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseCode")
    private String courseCode = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("courseDate")
    private String courseDate = null;

    @JsonProperty("startTime")
    private String startTime = null;

    @JsonProperty("endTime")
    private String endTime = null;

    @JsonProperty("schoolYear")
    private String schoolYear = null;

    @JsonProperty("term")
    private Integer term = null;

    @JsonProperty("courseTypeId")
    private Integer courseTypeId = null;

    @JsonProperty("courseTypeName")
    private String courseTypeName = null;

    @JsonProperty("courseTableCount")
    private Integer courseTableCount = null;

    @JsonProperty("courseTableId")
    private String courseTableId = null;

    @JsonProperty("studentType")
    private Integer studentType = null;

    @JsonProperty("courseTableDetailId")
    private String courseTableDetailId = null;

    @JsonProperty("groupId")
    private String groupId = null;

    @JsonProperty("groupNo")
    private String groupNo = null;

    @JsonProperty("groupName")
    private String groupName = null;

    @JsonProperty("segment")
    private String segment = null;

    @JsonProperty("week")
    private String week = null;

    @JsonProperty("weekNum")
    private Integer weekNum = null;

    @JsonProperty("roomId")
    private String roomId = null;

    @JsonProperty("roomName")
    private String roomName = null;

    @JsonProperty("classNickName")
    private String classNickName = null;

    @JsonProperty("classCompositionName")
    private String classCompositionName = null;

    @JsonProperty("source")
    private Integer source = null;
    public EvaluationCourseTableVo collegeId(String collegeId) {
        this.collegeId = collegeId;
        return this;
    }

    /**
     * 开课学院Id
     * @return collegeId
     **/
    @ApiModelProperty(value = "开课学院Id")


    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public EvaluationCourseTableVo collegeCode(String collegeCode) {
        this.collegeCode = collegeCode;
        return this;
    }

    /**
     * 开课学院编号
     *
     * @return collegeCode
     **/
    @ApiModelProperty(value = "开课学院编号")


    public String getCollegeCode() {
        return collegeCode;
    }

    public void setCollegeCode(String collegeCode) {
        this.collegeCode = collegeCode;
    }

    public EvaluationCourseTableVo collegeName(String collegeName) {
        this.collegeName = collegeName;
        return this;
    }

    /**
     * 开课学院名称
     * @return collegeName
     **/
    @ApiModelProperty(value = "开课学院名称")


    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public EvaluationCourseTableVo courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    /**
     * 课程id
     * @return courseId
     **/
    @ApiModelProperty(value = "课程id")


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public EvaluationCourseTableVo courseCode(String courseCode) {
        this.courseCode = courseCode;
        return this;
    }

    /**
     * 课程编号
     * @return courseCode
     **/
    @ApiModelProperty(value = "课程编号")


    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public EvaluationCourseTableVo courseName(String courseName) {
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

    public EvaluationCourseTableVo courseDate(String courseDate) {
        this.courseDate = courseDate;
        return this;
    }

    /**
     * 课程日期
     * @return courseDate
     **/
    @ApiModelProperty(value = "课程日期")


    public String getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(String courseDate) {
        this.courseDate = courseDate;
    }

    public EvaluationCourseTableVo startTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * 开始时间
     * @return startTime
     **/
    @ApiModelProperty(value = "开始时间")


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public EvaluationCourseTableVo endTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    /**
     * 结束时间
     * @return endTime
     **/
    @ApiModelProperty(value = "结束时间")


    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public EvaluationCourseTableVo schoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
        return this;
    }
    /**
     * 来源
     * @return source
     **/
    @ApiModelProperty(value = "班级来源（数据对接,0；系统录入,1；外部导入,2；自主开班,3；）")


    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }
    /**
     * 班级昵称
     * @return classNickName
     **/
    @ApiModelProperty(value = "班级昵称")


    public String getClassNickName() {
        return classNickName;
    }

    public void setClassNickName(String classNickName) {
        this.classNickName = classNickName;
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

    public EvaluationCourseTableVo term(Integer term) {
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

    public EvaluationCourseTableVo courseTypeId(Integer courseTypeId) {
        this.courseTypeId = courseTypeId;
        return this;
    }

    /**
     * 课程类型Id
     * @return courseTypeId
     **/
    @ApiModelProperty(value = "课程类型Id")


    public Integer getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Integer courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public EvaluationCourseTableVo courseTypeName(String courseTypeName) {
        this.courseTypeName = courseTypeName;
        return this;
    }

    /**
     * 课程类型名称
     * @return courseTypeName
     **/
    @ApiModelProperty(value = "课程类型名称")


    public String getCourseTypeName() {
        return courseTypeName;
    }

    public void setCourseTypeName(String courseTypeName) {
        this.courseTypeName = courseTypeName;
    }

    public EvaluationCourseTableVo courseTableCount(Integer courseTableCount) {
        this.courseTableCount = courseTableCount;
        return this;
    }

    /**
     * 课表次数
     * @return courseTableCount
     **/
    @ApiModelProperty(value = "课表次数")


    public Integer getCourseTableCount() {
        return courseTableCount;
    }

    public void setCourseTableCount(Integer courseTableCount) {
        this.courseTableCount = courseTableCount;
    }

    public EvaluationCourseTableVo courseTableId(String courseTableId) {
        this.courseTableId = courseTableId;
        return this;
    }

    /**
     * 课表id
     * @return courseTableId
     **/
    @ApiModelProperty(value = "课表id")


    public String getCourseTableId() {
        return courseTableId;
    }

    public void setCourseTableId(String courseTableId) {
        this.courseTableId = courseTableId;
    }

    public EvaluationCourseTableVo studentType(Integer studentType) {
        this.studentType = studentType;
        return this;
    }

    /**
     * 课程开课类型 0：本科生 1：研究生
     * @return studentType
     **/
    @ApiModelProperty(value = "课程开课类型 0：本科生 1：研究生")


    public Integer getStudentType() {
        return studentType;
    }

    public void setStudentType(Integer studentType) {
        this.studentType = studentType;
    }

    public EvaluationCourseTableVo courseTableDetailId(String courseTableDetailId) {
        this.courseTableDetailId = courseTableDetailId;
        return this;
    }

    /**
     * 课表明细id
     * @return courseTableDetailId
     **/
    @ApiModelProperty(value = "课表明细id")


    public String getCourseTableDetailId() {
        return courseTableDetailId;
    }

    public void setCourseTableDetailId(String courseTableDetailId) {
        this.courseTableDetailId = courseTableDetailId;
    }

    public EvaluationCourseTableVo groupId(String groupId) {
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
    public EvaluationCourseTableVo groupNo(String groupId) {
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

    public EvaluationCourseTableVo groupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    /**
     * 组名称
     * @return groupName
     **/
    @ApiModelProperty(value = "组名称")


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public EvaluationCourseTableVo segment(String segment) {
        this.segment = segment;
        return this;
    }

    /**
     * 节次
     * @return segment
     **/
    @ApiModelProperty(value = "节次")


    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public EvaluationCourseTableVo week(String week) {
        this.week = week;
        return this;
    }

    /**
     * 周次
     * @return week
     **/
    @ApiModelProperty(value = "周次")


    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public EvaluationCourseTableVo weekNum(Integer weekNum) {
        this.weekNum = weekNum;
        return this;
    }

    /**
     * 周几
     * @return weekNum
     **/
    @ApiModelProperty(value = "周几")


    public Integer getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(Integer weekNum) {
        this.weekNum = weekNum;
    }

    public EvaluationCourseTableVo roomId(String roomId) {
        this.roomId = roomId;
        return this;
    }

    /**
     * 教室Id
     * @return roomId
     **/
    @ApiModelProperty(value = "教室Id")


    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public EvaluationCourseTableVo roomName(String roomName) {
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

    public EvaluationCourseTableVo classNickName(String classNickName) {
        this.classNickName = classNickName;
        return this;
    }

    public EvaluationCourseTableVo classCompositionName(String classCompositionName) {
        this.classCompositionName = classCompositionName;
        return this;
    }

    /**
     * 班级组成名称
     * @return classCompositionName
     **/
    @ApiModelProperty(value = "班级组成名称")


    public String getClassCompositionName() {
        return classCompositionName;
    }

    public void setClassCompositionName(String classCompositionName) {
        this.classCompositionName = classCompositionName;
    }

    public EvaluationCourseTableVo source(Integer source) {
        this.source = source;
        return this;
    }



    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EvaluationCourseTableVo evaluationCourseTableVo = (EvaluationCourseTableVo) o;
        return Objects.equals(this.collegeId, evaluationCourseTableVo.collegeId) &&
                Objects.equals(this.collegeName, evaluationCourseTableVo.collegeName) &&
                Objects.equals(this.courseId, evaluationCourseTableVo.courseId) &&
                Objects.equals(this.courseCode, evaluationCourseTableVo.courseCode) &&
                Objects.equals(this.courseName, evaluationCourseTableVo.courseName) &&
                Objects.equals(this.courseDate, evaluationCourseTableVo.courseDate) &&
                Objects.equals(this.startTime, evaluationCourseTableVo.startTime) &&
                Objects.equals(this.endTime, evaluationCourseTableVo.endTime) &&
                Objects.equals(this.schoolYear, evaluationCourseTableVo.schoolYear) &&
                Objects.equals(this.term, evaluationCourseTableVo.term) &&
                Objects.equals(this.courseTypeId, evaluationCourseTableVo.courseTypeId) &&
                Objects.equals(this.courseTypeName, evaluationCourseTableVo.courseTypeName) &&
                Objects.equals(this.courseTableCount, evaluationCourseTableVo.courseTableCount) &&
                Objects.equals(this.courseTableId, evaluationCourseTableVo.courseTableId) &&
                Objects.equals(this.studentType, evaluationCourseTableVo.studentType) &&
                Objects.equals(this.courseTableDetailId, evaluationCourseTableVo.courseTableDetailId) &&
                Objects.equals(this.groupId, evaluationCourseTableVo.groupId) &&
                Objects.equals(this.groupName, evaluationCourseTableVo.groupName) &&
                Objects.equals(this.segment, evaluationCourseTableVo.segment) &&
                Objects.equals(this.week, evaluationCourseTableVo.week) &&
                Objects.equals(this.weekNum, evaluationCourseTableVo.weekNum) &&
                Objects.equals(this.roomId, evaluationCourseTableVo.roomId) &&
                Objects.equals(this.roomName, evaluationCourseTableVo.roomName) &&
                Objects.equals(this.classNickName, evaluationCourseTableVo.classNickName) &&
                Objects.equals(this.classCompositionName, evaluationCourseTableVo.classCompositionName) &&
                Objects.equals(this.source, evaluationCourseTableVo.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(collegeId, collegeName, courseId, courseCode, courseName, courseDate,
                startTime, endTime, schoolYear, term, courseTypeId, courseTypeName, courseTableCount,
                courseTableId, studentType, courseTableDetailId, groupId, groupName, segment, week, weekNum,
                roomId, roomName, classNickName, classCompositionName, source);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class EvaluationCourseTableVo {\n");

        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
        sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
        sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
        sb.append("    endTime: ").append(toIndentedString(endTime)).append("\n");
        sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
        sb.append("    term: ").append(toIndentedString(term)).append("\n");
        sb.append("    courseTypeId: ").append(toIndentedString(courseTypeId)).append("\n");
        sb.append("    courseTypeName: ").append(toIndentedString(courseTypeName)).append("\n");
        sb.append("    courseTableCount: ").append(toIndentedString(courseTableCount)).append("\n");
        sb.append("    courseTableId: ").append(toIndentedString(courseTableId)).append("\n");
        sb.append("    studentType: ").append(toIndentedString(studentType)).append("\n");
        sb.append("    courseTableDetailId: ").append(toIndentedString(courseTableDetailId)).append("\n");
        sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
        sb.append("    groupName: ").append(toIndentedString(groupName)).append("\n");
        sb.append("    segment: ").append(toIndentedString(segment)).append("\n");
        sb.append("    week: ").append(toIndentedString(week)).append("\n");
        sb.append("    weekNum: ").append(toIndentedString(weekNum)).append("\n");
        sb.append("    roomId: ").append(toIndentedString(roomId)).append("\n");
        sb.append("    roomName: ").append(toIndentedString(roomName)).append("\n");
        sb.append("    classNickName: ").append(toIndentedString(classNickName)).append("\n");
        sb.append("    classCompositionName: ").append(toIndentedString(classCompositionName)).append("\n");
        sb.append("    source: ").append(toIndentedString(source)).append("\n");
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

