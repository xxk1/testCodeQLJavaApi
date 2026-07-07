package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseGroupSuperviseVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-12-09T05:41:44.621Z")


public class CourseGroupSuperviseVo {
    @JsonProperty("groupId")
    private String groupId = null;

    @JsonProperty("groupName")
    private String groupName = null;

    @JsonProperty("groupNo")
    private String groupNo = null;

    @JsonProperty("courseTableId")
    private String courseTableId = null;

    @JsonProperty("studentType")
    private String studentType = null;

    public CourseGroupSuperviseVo groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    /**
     * 上课班级ID
     *
     * @return groupId
     **/
    @ApiModelProperty(value = "上课班级ID")


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public CourseGroupSuperviseVo groupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    /**
     * 上课班级名称
     *
     * @return groupName
     **/
    @ApiModelProperty(value = "上课班级名称")


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    public CourseGroupSuperviseVo courseTableId(String courseTableId) {
        this.courseTableId = courseTableId;
        return this;
    }

    /**
     * 课表Id
     *
     * @return courseTableId
     **/
    @ApiModelProperty(value = "课表Id")


    public String getCourseTableId() {
        return courseTableId;
    }

    public void setCourseTableId(String courseTableId) {
        this.courseTableId = courseTableId;
    }

    public CourseGroupSuperviseVo studentType(String studentType) {
        this.studentType = studentType;
        return this;
    }

    /**
     * 课程开课类型：0：本科生 1：研究生
     *
     * @return studentType
     **/
    @ApiModelProperty(value = "课程开课类型：0：本科生 1：研究生")


    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
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
        CourseGroupSuperviseVo courseGroupSuperviseVo = (CourseGroupSuperviseVo) o;
        return Objects.equals(this.groupId, courseGroupSuperviseVo.groupId) &&
                Objects.equals(this.courseTableId, courseGroupSuperviseVo.courseTableId) &&
                Objects.equals(this.groupName, courseGroupSuperviseVo.groupName);
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

    @Override
    public int hashCode() {
        return Objects.hash(groupId, groupName, courseTableId, studentType);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseGroupSuperviseVo {\n");

        sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
        sb.append("    groupName: ").append(toIndentedString(groupName)).append("\n");
        sb.append("    courseTableId: ").append(toIndentedString(courseTableId)).append("\n");
        sb.append("    studentType: ").append(toIndentedString(studentType)).append("\n");
        sb.append("}");
        return sb.toString();
    }


}

