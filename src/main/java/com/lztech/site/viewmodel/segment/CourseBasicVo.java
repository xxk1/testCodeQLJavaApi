package com.lztech.site.viewmodel.segment;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseBasicVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-02-01T02:48:42.193Z")


public class CourseBasicVo {
    @JsonProperty("courseId")
    private String courseId = null;
    @JsonProperty("courseCode")
    private String courseCode = null;
    @JsonProperty("courseName")
    private String courseName = null;
    @JsonProperty("courseCategoryId")
    private Integer courseCategoryId = null;
    @JsonProperty("courseCategoryName")
    private String courseCategoryName = null;
    @JsonProperty("teacherNames")
    private String teacherNames = null;
    @JsonProperty("groupId")
    private String groupId = null;
    @JsonProperty("groupNo")
    private String groupNo = null;
    @JsonProperty("groupName")
    private String groupName = null;
    @JsonProperty("collegeId")
    private String collegeId = null;
    @JsonProperty("collegeName")
    private String collegeName = null;
    @JsonProperty("groupMemberCounts")
    private Integer groupMemberCounts = null;

    public CourseBasicVo courseId(String courseId) {
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

    public CourseBasicVo courseCode(String courseCode) {
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

    public CourseBasicVo courseName(String courseName) {
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

    public CourseBasicVo courseCategoryId(Integer courseCategoryId) {
        this.courseCategoryId = courseCategoryId;
        return this;
    }

    /**
     * 课程类型id
     *
     * @return courseCategoryId
     **/
    @ApiModelProperty(value = "课程类型id")
    public Integer getCourseCategoryId() {
        return courseCategoryId;
    }

    public void setCourseCategoryId(Integer courseCategoryId) {
        this.courseCategoryId = courseCategoryId;
    }
    public CourseBasicVo courseCategoryName(String courseCategoryName) {
        this.courseCategoryName = courseCategoryName;
        return this;
    }

    /**
     * 课程类型名称
     *
     * @return courseCategoryName
     **/
    @ApiModelProperty(value = "课程类型名称")
    public String getCourseCategoryName() {
        return courseCategoryName;
    }

    public void setCourseCategoryName(String courseCategoryName) {
        this.courseCategoryName = courseCategoryName;
    }
    public CourseBasicVo teacherNames(String teacherNames) {
        this.teacherNames = teacherNames;
        return this;
    }

    /**
     * 老师名称(多个逗号拼接)
     *
     * @return teacherNames
     **/
    @ApiModelProperty(value = "老师名称(多个逗号拼接)")
    public String getTeacherNames() {
        return teacherNames;
    }

    public void setTeacherNames(String teacherNames) {
        this.teacherNames = teacherNames;
    }
    public CourseBasicVo groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    /**
     * 教学班id
     *
     * @return groupId
     **/
    @ApiModelProperty(value = "教学班id")
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    public CourseBasicVo groupNo(String groupNo) {
        this.groupNo = groupNo;
        return this;
    }

    /**
     * 教学班编号
     *
     * @return groupNo
     **/
    @ApiModelProperty(value = "教学班编号")

    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }
    public CourseBasicVo groupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    /**
     * 教学班名称
     *
     * @return groupName
     **/
    @ApiModelProperty(value = "教学班名称")

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public CourseBasicVo groupMemberCounts(Integer groupMemberCounts) {
        this.groupMemberCounts = groupMemberCounts;
        return this;
    }

    /**
     * 教学班学生总数
     *
     * @return groupMemberCounts
     **/
    @ApiModelProperty(value = "教学班学生总数")
    public Integer getGroupMemberCounts() {
        return groupMemberCounts;
    }

    public void setGroupMemberCounts(Integer groupMemberCounts) {
        this.groupMemberCounts = groupMemberCounts;
    }
    public CourseBasicVo collegeId(String collegeId) {
        this.collegeId = collegeId;
        return this;
    }

    /**
     * 开课学院id
     *
     * @return collegeId
     **/
    @ApiModelProperty(value = "开课学院id")
    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }
    public CourseBasicVo collegeName(String collegeName) {
        this.collegeName = collegeName;
        return this;
    }

    /**
     * 开课学院名称
     *
     * @return collegeName
     **/
    @ApiModelProperty(value = "开课学院名称")
    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseBasicVo that = (CourseBasicVo) o;
        return Objects.equals(courseId, that.courseId) &&
                Objects.equals(courseCode, that.courseCode) &&
                Objects.equals(courseName, that.courseName) &&
                Objects.equals(courseCategoryId, that.courseCategoryId) &&
                Objects.equals(courseCategoryName, that.courseCategoryName) &&
                Objects.equals(teacherNames, that.teacherNames) &&
                Objects.equals(groupId, that.groupId) &&
                Objects.equals(groupNo, that.groupNo) &&
                Objects.equals(groupName, that.groupName) &&
                Objects.equals(collegeId, that.collegeId) &&
                Objects.equals(collegeName, that.collegeName) &&
                Objects.equals(groupMemberCounts, that.groupMemberCounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseCode, courseName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseBasicVo {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    courseCategoryId: ").append(toIndentedString(courseCategoryId)).append("\n");
        sb.append("    courseCategoryName: ").append(toIndentedString(courseCategoryName)).append("\n");
        sb.append("    teacherNames: ").append(toIndentedString(teacherNames)).append("\n");
        sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
        sb.append("    groupNo: ").append(toIndentedString(groupNo)).append("\n");
        sb.append("    groupName: ").append(toIndentedString(groupName)).append("\n");
        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
        sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
        sb.append("    groupMemberCounts: ").append(toIndentedString(groupMemberCounts)).append("\n");
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

