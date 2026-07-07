package com.lztech.site.viewmodel.superviseevaluation.v2;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * SupervisionCourseTableAndDetailVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2026-01-16T09:49:54.168+08:00")

public class SupervisionCourseTableAndDetailVo {
    @JsonProperty("courseTableId")
    private String courseTableId = null;

    @JsonProperty("courseTableDetailId")
    private String courseTableDetailId = null;

    @JsonProperty("groupId")
    private String groupId = null;

    @JsonProperty("className")
    private String className = null;

    @JsonProperty("classNickName")
    private String classNickName = null;

    @JsonProperty("classCompositionName")
    private String classCompositionName = null;

    @JsonProperty("courseAttrName")
    private String courseAttrName = null;

    @JsonProperty("segment")
    private String segment = null;

    @JsonProperty("studentCount")
    private String studentCount = null;

    @JsonProperty("courseDate")
    private String courseDate = null;

    @JsonProperty("segmentStartTime")
    private String segmentStartTime = null;

    @JsonProperty("segmentEndTime")
    private String segmentEndTime = null;

    @JsonProperty("teachers")
    @Valid
    private List<SupervisionCourseTableDetailTeacherVo> teachers = null;

    @JsonProperty("rooms")
    @Valid
    private List<SupervisionCourseTableDetailRoomVo> rooms = null;

    @JsonProperty("sourceDataSource")
    private String sourceDataSource = null;

    @JsonProperty("sourceDataSourceName")
    private String sourceDataSourceName = null;

    public SupervisionCourseTableAndDetailVo courseTableId(String courseTableId) {
        this.courseTableId = courseTableId;
        return this;
    }

    /**
     * 开课id
     *
     * @return courseTableId
     **/
    @ApiModelProperty(value = "开课id")


    public String getCourseTableId() {
        return courseTableId;
    }

    public void setCourseTableId(String courseTableId) {
        this.courseTableId = courseTableId;
    }

    public SupervisionCourseTableAndDetailVo courseTableDetailId(String courseTableDetailId) {
        this.courseTableDetailId = courseTableDetailId;
        return this;
    }

    /**
     * 课表明细id
     *
     * @return courseTableDetailId
     **/
    @ApiModelProperty(value = "课表明细id")


    public String getCourseTableDetailId() {
        return courseTableDetailId;
    }

    public void setCourseTableDetailId(String courseTableDetailId) {
        this.courseTableDetailId = courseTableDetailId;
    }

    public SupervisionCourseTableAndDetailVo groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    /**
     * 班级id
     *
     * @return groupId
     **/
    @ApiModelProperty(value = "班级id")


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public SupervisionCourseTableAndDetailVo className(String className) {
        this.className = className;
        return this;
    }

    /**
     * 班级名称
     *
     * @return className
     **/
    @ApiModelProperty(value = "班级名称")


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public SupervisionCourseTableAndDetailVo classNickName(String classNickName) {
        this.classNickName = classNickName;
        return this;
    }

    /**
     * 班级昵称
     *
     * @return classNickName
     **/
    @ApiModelProperty(value = "班级昵称")


    public String getClassNickName() {
        return classNickName;
    }

    public void setClassNickName(String classNickName) {
        this.classNickName = classNickName;
    }

    public SupervisionCourseTableAndDetailVo classCompositionName(String classCompositionName) {
        this.classCompositionName = classCompositionName;
        return this;
    }

    /**
     * 班级组成
     *
     * @return classCompositionName
     **/
    @ApiModelProperty(value = "班级组成")


    public String getClassCompositionName() {
        return classCompositionName;
    }

    public void setClassCompositionName(String classCompositionName) {
        this.classCompositionName = classCompositionName;
    }

    public SupervisionCourseTableAndDetailVo courseAttrName(String courseAttrName) {
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

    public SupervisionCourseTableAndDetailVo segment(String segment) {
        this.segment = segment;
        return this;
    }

    /**
     * 节次信息
     *
     * @return segment
     **/
    @ApiModelProperty(value = "节次信息")


    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public SupervisionCourseTableAndDetailVo studentCount(String studentCount) {
        this.studentCount = studentCount;
        return this;
    }

    /**
     * 学生人数
     *
     * @return studentCount
     **/
    @ApiModelProperty(value = "学生人数")


    public String getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(String studentCount) {
        this.studentCount = studentCount;
    }

    public SupervisionCourseTableAndDetailVo courseDate(String courseDate) {
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

    public SupervisionCourseTableAndDetailVo segmentStartTime(String segmentStartTime) {
        this.segmentStartTime = segmentStartTime;
        return this;
    }

    /**
     * 大节次开始时间
     *
     * @return segmentStartTime
     **/
    @ApiModelProperty(value = "大节次开始时间")


    public String getSegmentStartTime() {
        return segmentStartTime;
    }

    public void setSegmentStartTime(String segmentStartTime) {
        this.segmentStartTime = segmentStartTime;
    }

    public SupervisionCourseTableAndDetailVo segmentEndTime(String segmentEndTime) {
        this.segmentEndTime = segmentEndTime;
        return this;
    }

    /**
     * 大节次结束时间
     *
     * @return segmentEndTime
     **/
    @ApiModelProperty(value = "大节次结束时间")


    public String getSegmentEndTime() {
        return segmentEndTime;
    }

    public void setSegmentEndTime(String segmentEndTime) {
        this.segmentEndTime = segmentEndTime;
    }

    public SupervisionCourseTableAndDetailVo teachers(List<SupervisionCourseTableDetailTeacherVo> teachers) {
        this.teachers = teachers;
        return this;
    }

    public SupervisionCourseTableAndDetailVo addTeachersItem(SupervisionCourseTableDetailTeacherVo teachersItem) {
        if (this.teachers == null) {
            this.teachers = new ArrayList<SupervisionCourseTableDetailTeacherVo>();
        }
        this.teachers.add(teachersItem);
        return this;
    }

    /**
     * 老师信息列表
     *
     * @return teachers
     **/
    @ApiModelProperty(value = "老师信息列表")

    @Valid

    public List<SupervisionCourseTableDetailTeacherVo> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<SupervisionCourseTableDetailTeacherVo> teachers) {
        this.teachers = teachers;
    }

    public SupervisionCourseTableAndDetailVo rooms(List<SupervisionCourseTableDetailRoomVo> rooms) {
        this.rooms = rooms;
        return this;
    }

    public SupervisionCourseTableAndDetailVo addRoomsItem(SupervisionCourseTableDetailRoomVo roomsItem) {
        if (this.rooms == null) {
            this.rooms = new ArrayList<SupervisionCourseTableDetailRoomVo>();
        }
        this.rooms.add(roomsItem);
        return this;
    }

    /**
     * 教室信息列表
     *
     * @return rooms
     **/
    @ApiModelProperty(value = "教室信息列表")

    @Valid

    public List<SupervisionCourseTableDetailRoomVo> getRooms() {
        return rooms;
    }

    public void setRooms(List<SupervisionCourseTableDetailRoomVo> rooms) {
        this.rooms = rooms;
    }

    public SupervisionCourseTableAndDetailVo sourceDataSource(String sourceDataSource) {
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

    public SupervisionCourseTableAndDetailVo sourceDataSourceName(String sourceDataSourceName) {
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
        SupervisionCourseTableAndDetailVo supervisionCourseTableAndDetailVo = (SupervisionCourseTableAndDetailVo) o;
        return Objects.equals(this.courseTableId, supervisionCourseTableAndDetailVo.courseTableId) &&
                Objects.equals(this.courseTableDetailId, supervisionCourseTableAndDetailVo.courseTableDetailId) &&
                Objects.equals(this.groupId, supervisionCourseTableAndDetailVo.groupId) &&
                Objects.equals(this.className, supervisionCourseTableAndDetailVo.className) &&
                Objects.equals(this.classNickName, supervisionCourseTableAndDetailVo.classNickName) &&
                Objects.equals(this.classCompositionName, supervisionCourseTableAndDetailVo.classCompositionName) &&
                Objects.equals(this.courseAttrName, supervisionCourseTableAndDetailVo.courseAttrName) &&
                Objects.equals(this.segment, supervisionCourseTableAndDetailVo.segment) &&
                Objects.equals(this.studentCount, supervisionCourseTableAndDetailVo.studentCount) &&
                Objects.equals(this.courseDate, supervisionCourseTableAndDetailVo.courseDate) &&
                Objects.equals(this.segmentStartTime, supervisionCourseTableAndDetailVo.segmentStartTime) &&
                Objects.equals(this.segmentEndTime, supervisionCourseTableAndDetailVo.segmentEndTime) &&
                Objects.equals(this.teachers, supervisionCourseTableAndDetailVo.teachers) &&
                Objects.equals(this.rooms, supervisionCourseTableAndDetailVo.rooms) &&
                Objects.equals(this.sourceDataSource, supervisionCourseTableAndDetailVo.sourceDataSource) &&
                Objects.equals(this.sourceDataSourceName, supervisionCourseTableAndDetailVo.sourceDataSourceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseTableId, courseTableDetailId, groupId, className, classNickName, classCompositionName,
                courseAttrName, segment, studentCount, courseDate, segmentStartTime, segmentEndTime, teachers, rooms,
                sourceDataSource, sourceDataSourceName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SupervisionCourseTableAndDetailVo {\n");

        sb.append("    courseTableId: ").append(toIndentedString(courseTableId)).append("\n");
        sb.append("    courseTableDetailId: ").append(toIndentedString(courseTableDetailId)).append("\n");
        sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
        sb.append("    className: ").append(toIndentedString(className)).append("\n");
        sb.append("    classNickName: ").append(toIndentedString(classNickName)).append("\n");
        sb.append("    classCompositionName: ").append(toIndentedString(classCompositionName)).append("\n");
        sb.append("    courseAttrName: ").append(toIndentedString(courseAttrName)).append("\n");
        sb.append("    segment: ").append(toIndentedString(segment)).append("\n");
        sb.append("    studentCount: ").append(toIndentedString(studentCount)).append("\n");
        sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
        sb.append("    segmentStartTime: ").append(toIndentedString(segmentStartTime)).append("\n");
        sb.append("    segmentEndTime: ").append(toIndentedString(segmentEndTime)).append("\n");
        sb.append("    teachers: ").append(toIndentedString(teachers)).append("\n");
        sb.append("    rooms: ").append(toIndentedString(rooms)).append("\n");
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

