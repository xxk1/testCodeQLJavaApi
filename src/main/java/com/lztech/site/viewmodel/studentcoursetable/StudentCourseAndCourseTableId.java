package com.lztech.site.viewmodel.studentcoursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * StudentCourseAndCourseTableId
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-09-25T14:53:35.940+08:00")

public class StudentCourseAndCourseTableId {
    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseCode")
    private String courseCode = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("courseTableIds")
    @Valid
    private List<String> courseTableIds = null;

    @JsonProperty("groupIds")
    @Valid
    private List<String> groupIds = null;

    public StudentCourseAndCourseTableId courseId(String courseId) {
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

    public StudentCourseAndCourseTableId courseCode(String courseCode) {
        this.courseCode = courseCode;
        return this;
    }

    /**
     * 课程code
     *
     * @return courseCode
     **/
    @ApiModelProperty(value = "课程code")


    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public StudentCourseAndCourseTableId courseName(String courseName) {
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

    public StudentCourseAndCourseTableId courseTableIds(List<String> courseTableIds) {
        this.courseTableIds = courseTableIds;
        return this;
    }

    public StudentCourseAndCourseTableId addCourseTableIdsItem(String courseTableIdsItem) {
        if (this.courseTableIds == null) {
            this.courseTableIds = new ArrayList<String>();
        }
        this.courseTableIds.add(courseTableIdsItem);
        return this;
    }

    /**
     * 开课id列表
     *
     * @return courseTableIds
     **/
    @ApiModelProperty(value = "开课id列表")


    public List<String> getCourseTableIds() {
        return courseTableIds;
    }

    public void setCourseTableIds(List<String> courseTableIds) {
        this.courseTableIds = courseTableIds;
    }

    public StudentCourseAndCourseTableId groupIds(List<String> groupIds) {
        this.groupIds = groupIds;
        return this;
    }

    public StudentCourseAndCourseTableId addGroupIdsItem(String groupIdsItem) {
        if (this.groupIds == null) {
            this.groupIds = new ArrayList<String>();
        }
        this.groupIds.add(groupIdsItem);
        return this;
    }

    /**
     * 班级id列表
     *
     * @return groupIds
     **/
    @ApiModelProperty(value = "班级id列表")


    public List<String> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<String> groupIds) {
        this.groupIds = groupIds;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StudentCourseAndCourseTableId studentCourseAndCourseTableId = (StudentCourseAndCourseTableId) o;
        return Objects.equals(this.courseId, studentCourseAndCourseTableId.courseId) &&
                Objects.equals(this.courseCode, studentCourseAndCourseTableId.courseCode) &&
                Objects.equals(this.courseName, studentCourseAndCourseTableId.courseName) &&
                Objects.equals(this.courseTableIds, studentCourseAndCourseTableId.courseTableIds) &&
                Objects.equals(this.groupIds, studentCourseAndCourseTableId.groupIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseCode, courseName, courseTableIds, groupIds);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class StudentCourseAndCourseTableId {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    courseTableIds: ").append(toIndentedString(courseTableIds)).append("\n");
        sb.append("    groupIds: ").append(toIndentedString(groupIds)).append("\n");
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

