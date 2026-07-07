package com.lztech.site.viewmodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * TeacherCourseTableSimpleResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-12-05T13:01:32.454Z")


public class TeacherCourseTableSimpleResource {
    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("groupName")
    private String groupName = null;

    @JsonProperty("groupNo")
    private String groupNo = null;

    @JsonProperty("groupMemberNum")
    private Integer groupMemberNum = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseCode")
    private String courseCode = null;

    @JsonProperty("courseTableId")
    private String courseTableId = null;

    @JsonProperty("courseTableDetailId")
    private String courseTableDetailId = null;

    @JsonProperty("groupId")
    private String groupId = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    @JsonProperty("collegeCode")
    private String collegeCode = null;

    @JsonProperty("courseCollegeId")
    private String courseCollegeId = null;

    @JsonProperty("courseCollegeName")
    private String courseCollegeName = null;

    @JsonProperty("courseCollegeCode")
    private String courseCollegeCode = null;

    @JsonProperty("roomIds")
    private String roomIds = null;

    @JsonProperty("studentType")
    private Integer studentType = null;

    @JsonProperty("segmentStartTime")
    private String segmentStartTime;

    @JsonProperty("segmentEndTime")
    private String segmentEndTime;

    public String getSegmentStartTime() {
        return segmentStartTime;
    }

    public void setSegmentStartTime(String segmentStartTime) {
        this.segmentStartTime = segmentStartTime;
    }

    public String getSegmentEndTime() {
        return segmentEndTime;
    }

    public void setSegmentEndTime(String segmentEndTime) {
        this.segmentEndTime = segmentEndTime;
    }

    public TeacherCourseTableSimpleResource courseName(String courseName) {
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

    public TeacherCourseTableSimpleResource groupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    /**
     * 班级名称
     *
     * @return groupName
     **/
    @ApiModelProperty(value = "班级名称")


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public TeacherCourseTableSimpleResource groupNo(String groupNo) {
        this.groupNo = groupNo;
        return this;
    }

    /**
     * 班级编号
     *
     * @return groupNo
     **/
    @ApiModelProperty(value = "班级编号")


    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    public TeacherCourseTableSimpleResource groupMemberNum(Integer groupMemberNum) {
        this.groupMemberNum = groupMemberNum;
        return this;
    }

    /**
     * 班级人数
     *
     * @return groupMemberNum
     **/
    @ApiModelProperty(value = "班级人数")


    public Integer getGroupMemberNum() {
        return groupMemberNum;
    }

    public void setGroupMemberNum(Integer groupMemberNum) {
        this.groupMemberNum = groupMemberNum;
    }

    public TeacherCourseTableSimpleResource courseId(String courseId) {
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

    public TeacherCourseTableSimpleResource courseCode(String courseCode) {
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

    public TeacherCourseTableSimpleResource courseTableId(String courseTableId) {
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

    public TeacherCourseTableSimpleResource courseTableDetailId(String courseTableDetailId) {
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

    public TeacherCourseTableSimpleResource groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    /**
     * 组id
     *
     * @return groupId
     **/
    @ApiModelProperty(value = "组id")


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public TeacherCourseTableSimpleResource collegeId(String collegeId) {
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

    public TeacherCourseTableSimpleResource collegeName(String collegeName) {
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

    public TeacherCourseTableSimpleResource collegeCode(String collegeCode) {
        this.collegeCode = collegeCode;
        return this;
    }

    /**
     * 学院编号
     *
     * @return collegeCode
     **/
    @ApiModelProperty(value = "学院编号")


    public String getCollegeCode() {
        return collegeCode;
    }

    public void setCollegeCode(String collegeCode) {
        this.collegeCode = collegeCode;
    }

    public TeacherCourseTableSimpleResource courseCollegeId(String courseCollegeId) {
        this.courseCollegeId = courseCollegeId;
        return this;
    }

    /**
     * 课程学院id
     *
     * @return courseCollegeId
     **/
    @ApiModelProperty(value = "课程学院id")


    public String getCourseCollegeId() {
        return courseCollegeId;
    }

    public void setCourseCollegeId(String courseCollegeId) {
        this.courseCollegeId = courseCollegeId;
    }

    public TeacherCourseTableSimpleResource courseCollegeName(String courseCollegeName) {
        this.courseCollegeName = courseCollegeName;
        return this;
    }

    /**
     * 课程学院名称
     *
     * @return courseCollegeName
     **/
    @ApiModelProperty(value = "课程学院名称")


    public String getCourseCollegeName() {
        return courseCollegeName;
    }

    public void setCourseCollegeName(String courseCollegeName) {
        this.courseCollegeName = courseCollegeName;
    }

    public TeacherCourseTableSimpleResource courseCollegeCode(String courseCollegeCode) {
        this.courseCollegeCode = courseCollegeCode;
        return this;
    }

    /**
     * 课程学院编号
     *
     * @return courseCollegeCode
     **/
    @ApiModelProperty(value = "课程学院编号")


    public String getCourseCollegeCode() {
        return courseCollegeCode;
    }

    public void setCourseCollegeCode(String courseCollegeCode) {
        this.courseCollegeCode = courseCollegeCode;
    }

    public TeacherCourseTableSimpleResource roomIds(String roomIds) {
        this.roomIds = roomIds;
        return this;
    }

    /**
     * 上课教室，多个使用逗号隔开
     *
     * @return roomIds
     **/
    @ApiModelProperty(value = "上课教室，多个使用逗号隔开")


    public String getRoomIds() {
        return roomIds;
    }

    public void setRoomIds(String roomIds) {
        this.roomIds = roomIds;
    }

    public TeacherCourseTableSimpleResource studentType(Integer studentType) {
        this.studentType = studentType;
        return this;
    }

    /**
     * 课程开课类型
     *
     * @return studentType
     **/
    @ApiModelProperty(value = "课程开课类型")


    public Integer getStudentType() {
        return studentType;
    }

    public void setStudentType(Integer studentType) {
        this.studentType = studentType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeacherCourseTableSimpleResource teacherCourseTableSimpleResource = (TeacherCourseTableSimpleResource) o;
        return Objects.equals(this.courseName, teacherCourseTableSimpleResource.courseName) &&
                Objects.equals(this.groupName, teacherCourseTableSimpleResource.groupName) &&
                Objects.equals(this.groupNo, teacherCourseTableSimpleResource.groupNo) &&
                Objects.equals(this.groupMemberNum, teacherCourseTableSimpleResource.groupMemberNum) &&
                Objects.equals(this.courseId, teacherCourseTableSimpleResource.courseId) &&
                Objects.equals(this.courseCode, teacherCourseTableSimpleResource.courseCode) &&
                Objects.equals(this.courseTableId, teacherCourseTableSimpleResource.courseTableId) &&
                Objects.equals(this.courseTableDetailId, teacherCourseTableSimpleResource.courseTableDetailId) &&
                Objects.equals(this.groupId, teacherCourseTableSimpleResource.groupId) &&
                Objects.equals(this.collegeId, teacherCourseTableSimpleResource.collegeId) &&
                Objects.equals(this.collegeName, teacherCourseTableSimpleResource.collegeName) &&
                Objects.equals(this.collegeCode, teacherCourseTableSimpleResource.collegeCode) &&
                Objects.equals(this.courseCollegeId, teacherCourseTableSimpleResource.courseCollegeId) &&
                Objects.equals(this.courseCollegeName, teacherCourseTableSimpleResource.courseCollegeName) &&
                Objects.equals(this.courseCollegeCode, teacherCourseTableSimpleResource.courseCollegeCode) &&
                Objects.equals(this.roomIds, teacherCourseTableSimpleResource.roomIds) &&
                Objects.equals(this.studentType, teacherCourseTableSimpleResource.studentType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName, groupName, groupNo, groupMemberNum, courseId, courseCode, courseTableId,
                courseTableDetailId, groupId, collegeId, collegeName, collegeCode, courseCollegeId,
                courseCollegeName, courseCollegeCode, roomIds, studentType);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TeacherCourseTableSimpleResource {\n");

        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    groupName: ").append(toIndentedString(groupName)).append("\n");
        sb.append("    groupNo: ").append(toIndentedString(groupNo)).append("\n");
        sb.append("    groupMemberNum: ").append(toIndentedString(groupMemberNum)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
        sb.append("    courseTableId: ").append(toIndentedString(courseTableId)).append("\n");
        sb.append("    courseTableDetailId: ").append(toIndentedString(courseTableDetailId)).append("\n");
        sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
        sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
        sb.append("    collegeCode: ").append(toIndentedString(collegeCode)).append("\n");
        sb.append("    courseCollegeId: ").append(toIndentedString(courseCollegeId)).append("\n");
        sb.append("    courseCollegeName: ").append(toIndentedString(courseCollegeName)).append("\n");
        sb.append("    courseCollegeCode: ").append(toIndentedString(courseCollegeCode)).append("\n");
        sb.append("    roomIds: ").append(toIndentedString(roomIds)).append("\n");
        sb.append("    studentType: ").append(toIndentedString(studentType)).append("\n");
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

