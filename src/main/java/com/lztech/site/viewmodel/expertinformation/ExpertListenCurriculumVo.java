package com.lztech.site.viewmodel.expertinformation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ExpertListenCurriculumVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-04-15T08:04:04.064Z")


public class ExpertListenCurriculumVo {
    @JsonProperty("courseTableId")
    private String courseTableId = null;

    @JsonProperty("roomId")
    private String roomId = null;

    @JsonProperty("courseTableCollegeName")
    private String courseTableCollegeName = null;

    @JsonProperty("courseTableCollegeId")
    private String courseTableCollegeId = null;

    @JsonProperty("majorName")
    private String majorName = null;

    @JsonProperty("teacherInfoList")
    @Valid
    private List<TeacherInfo> teacherInfoList = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseCode")
    private String courseCode = null;

    @JsonProperty("courseAttrName")
    private String courseAttrName = null;

    @JsonProperty("courseDate")
    private String courseDate = null;

    @JsonProperty("segment")
    private String segment = null;
    @JsonProperty("segmentStartTime")
    private String segmentStartTime = null;

    @JsonProperty("segmentEndTime")
    private String segmentEndTime = null;

    @JsonProperty("hasLiveBroadcast")
    private String hasLiveBroadcast = null;

    @JsonProperty("courseTableDetailId")
    private String courseTableDetailId = null;

    public String getCourseTableDetailId() {
        return courseTableDetailId;
    }

    public void setCourseTableDetailId(String courseTableDetailId) {
        this.courseTableDetailId = courseTableDetailId;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public ExpertListenCurriculumVo courseTableId(String courseTableId) {
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

    public ExpertListenCurriculumVo roomId(String roomId) {
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

    public ExpertListenCurriculumVo courseTableCollegeName(String courseTableCollegeName) {
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

    public ExpertListenCurriculumVo courseTableCollegeId(String courseTableCollegeId) {
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

    public ExpertListenCurriculumVo majorName(String majorName) {
        this.majorName = majorName;
        return this;
    }

    /**
     * 专业组成
     *
     * @return majorName
     **/
    @ApiModelProperty(value = "专业组成")


    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public ExpertListenCurriculumVo teacherInfoList(List<TeacherInfo> teacherInfoList) {
        this.teacherInfoList = teacherInfoList;
        return this;
    }

    public ExpertListenCurriculumVo addTeacherInfoListItem(TeacherInfo teacherInfoListItem) {
        if (this.teacherInfoList == null) {
            this.teacherInfoList = new ArrayList<TeacherInfo>();
        }
        this.teacherInfoList.add(teacherInfoListItem);
        return this;
    }

    /**
     * 老师信息集合
     *
     * @return teacherInfoList
     **/
    @ApiModelProperty(value = "老师信息集合")

    @Valid

    public List<TeacherInfo> getTeacherInfoList() {
        return teacherInfoList;
    }

    public void setTeacherInfoList(List<TeacherInfo> teacherInfoList) {
        this.teacherInfoList = teacherInfoList;
    }

    public ExpertListenCurriculumVo courseName(String courseName) {
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

    public ExpertListenCurriculumVo courseId(String courseId) {
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

    public ExpertListenCurriculumVo courseCode(String courseCode) {
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

    public ExpertListenCurriculumVo courseAttrName(String courseAttrName) {
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

    public ExpertListenCurriculumVo courseDate(String courseDate) {
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

    public ExpertListenCurriculumVo segmentStartTime(String segmentStartTime) {
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

    public ExpertListenCurriculumVo segmentEndTime(String segmentEndTime) {
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

    public ExpertListenCurriculumVo hasLiveBroadcast(String hasLiveBroadcast) {
        this.hasLiveBroadcast = hasLiveBroadcast;
        return this;
    }

    /**
     * 是否在直播(0:没有;1:有)
     *
     * @return hasLiveBroadcast
     **/
    @ApiModelProperty(value = "是否在直播(0:没有;1:有)")


    public String getHasLiveBroadcast() {
        return hasLiveBroadcast;
    }

    public void setHasLiveBroadcast(String hasLiveBroadcast) {
        this.hasLiveBroadcast = hasLiveBroadcast;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExpertListenCurriculumVo expertListenCurriculumVo = (ExpertListenCurriculumVo) o;
        return Objects.equals(this.courseTableId, expertListenCurriculumVo.courseTableId) &&
                Objects.equals(this.roomId, expertListenCurriculumVo.roomId) &&
                Objects.equals(this.courseTableCollegeName, expertListenCurriculumVo.courseTableCollegeName) &&
                Objects.equals(this.courseTableCollegeId, expertListenCurriculumVo.courseTableCollegeId) &&
                Objects.equals(this.majorName, expertListenCurriculumVo.majorName) &&
                Objects.equals(this.teacherInfoList, expertListenCurriculumVo.teacherInfoList) &&
                Objects.equals(this.courseName, expertListenCurriculumVo.courseName) &&
                Objects.equals(this.courseId, expertListenCurriculumVo.courseId) &&
                Objects.equals(this.courseCode, expertListenCurriculumVo.courseCode) &&
                Objects.equals(this.courseAttrName, expertListenCurriculumVo.courseAttrName) &&
                Objects.equals(this.courseDate, expertListenCurriculumVo.courseDate) &&
                Objects.equals(this.segmentStartTime, expertListenCurriculumVo.segmentStartTime) &&
                Objects.equals(this.segmentEndTime, expertListenCurriculumVo.segmentEndTime) &&
                Objects.equals(this.hasLiveBroadcast, expertListenCurriculumVo.hasLiveBroadcast);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseTableId, roomId, courseTableCollegeName, courseTableCollegeId, majorName, teacherInfoList, courseName, courseId,
                courseCode, courseAttrName, courseDate, segmentStartTime, segmentEndTime, hasLiveBroadcast);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExpertListenCurriculumVo {\n");

        sb.append("    courseTableId: ").append(toIndentedString(courseTableId)).append("\n");
        sb.append("    roomId: ").append(toIndentedString(roomId)).append("\n");
        sb.append("    courseTableCollegeName: ").append(toIndentedString(courseTableCollegeName)).append("\n");
        sb.append("    courseTableCollegeId: ").append(toIndentedString(courseTableCollegeId)).append("\n");
        sb.append("    majorName: ").append(toIndentedString(majorName)).append("\n");
        sb.append("    teacherInfoList: ").append(toIndentedString(teacherInfoList)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
        sb.append("    courseAttrName: ").append(toIndentedString(courseAttrName)).append("\n");
        sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
        sb.append("    segmentStartTime: ").append(toIndentedString(segmentStartTime)).append("\n");
        sb.append("    segmentEndTime: ").append(toIndentedString(segmentEndTime)).append("\n");
        sb.append("    hasLiveBroadcast: ").append(toIndentedString(hasLiveBroadcast)).append("\n");
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

