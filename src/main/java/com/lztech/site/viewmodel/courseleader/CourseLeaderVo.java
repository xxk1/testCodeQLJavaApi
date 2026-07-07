package com.lztech.site.viewmodel.courseleader;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseLeaderVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-03-04T01:44:21.745Z")


public class CourseLeaderVo {
    @JsonProperty("managementPort")
    private Boolean managementPort = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("versionId")
    private String versionId = null;

    @JsonProperty("courseLeaderId")
    private String courseLeaderId = null;

    @JsonProperty("courseLeaderName")
    private String courseLeaderName = null;

    @JsonProperty("actionType")
    private Integer actionType = null;

    @JsonProperty("teacherId")
    private String teacherId = null;

    @JsonProperty("teacherNo")
    private String teacherNo = null;

    @JsonProperty("teacherName")
    private String teacherName = null;

    public CourseLeaderVo managementPort(Boolean managementPort) {
        this.managementPort = managementPort;
        return this;
    }

    /**
     * 是否为管理端修改调用
     *
     * @return managementPort
     **/
    @ApiModelProperty(value = "是否为管理端修改调用")


    public Boolean isManagementPort() {
        return managementPort;
    }

    public void setManagementPort(Boolean managementPort) {
        this.managementPort = managementPort;
    }


    public CourseLeaderVo versionId(String versionId) {
        this.versionId = versionId;
        return this;
    }


    /**
     * versionId
     *
     * @return versionId
     **/
    @ApiModelProperty(value = "版本Id")

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }


    public CourseLeaderVo courseId(String courseId) {
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

    public CourseLeaderVo courseLeaderId(String courseLeaderId) {
        this.courseLeaderId = courseLeaderId;
        return this;
    }

    /**
     * 课程负责人Id
     *
     * @return courseLeaderId
     **/
    @ApiModelProperty(value = "课程负责人Id")


    public String getCourseLeaderId() {
        return courseLeaderId;
    }

    public void setCourseLeaderId(String courseLeaderId) {
        this.courseLeaderId = courseLeaderId;
    }

    public CourseLeaderVo courseLeaderName(String courseLeaderName) {
        this.courseLeaderName = courseLeaderName;
        return this;
    }

    /**
     * 课程负责人姓名
     *
     * @return courseLeaderName
     **/
    @ApiModelProperty(value = "课程负责人姓名")


    public String getCourseLeaderName() {
        return courseLeaderName;
    }

    public void setCourseLeaderName(String courseLeaderName) {
        this.courseLeaderName = courseLeaderName;
    }

    public CourseLeaderVo actionType(Integer actionType) {
        this.actionType = actionType;
        return this;
    }

    /**
     * 0:转让并离开团队 1：转让
     *
     * @return actionType
     **/
    @ApiModelProperty(value = "0:转让并离开团队 1：转让")


    public Integer getActionType() {
        return actionType;
    }

    public void setActionType(Integer actionType) {
        this.actionType = actionType;
    }

    public CourseLeaderVo teacherId(String teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    /**
     * 老师Id
     *
     * @return teacherId
     **/
    @ApiModelProperty(value = "老师Id")


    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public CourseLeaderVo teacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
        return this;
    }

    /**
     * 老师工号
     *
     * @return teacherNo
     **/
    @ApiModelProperty(value = "老师工号")


    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    public CourseLeaderVo teacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    /**
     * 老师姓名
     *
     * @return teacherName
     **/
    @ApiModelProperty(value = "老师姓名")


    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseLeaderVo courseLeaderVo = (CourseLeaderVo) o;
        return Objects.equals(this.managementPort, courseLeaderVo.managementPort) &&
                Objects.equals(this.courseId, courseLeaderVo.courseId) &&
                Objects.equals(this.courseLeaderId, courseLeaderVo.courseLeaderId) &&
                Objects.equals(this.courseLeaderName, courseLeaderVo.courseLeaderName) &&
                Objects.equals(this.actionType, courseLeaderVo.actionType) &&
                Objects.equals(this.teacherId, courseLeaderVo.teacherId) &&
                Objects.equals(this.teacherNo, courseLeaderVo.teacherNo) &&
                Objects.equals(this.teacherName, courseLeaderVo.teacherName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(managementPort, courseId, courseLeaderId, courseLeaderName, actionType, teacherId, teacherNo, teacherName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseLeaderVo {\n");

        sb.append("    managementPort: ").append(toIndentedString(managementPort)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseLeaderId: ").append(toIndentedString(courseLeaderId)).append("\n");
        sb.append("    courseLeaderName: ").append(toIndentedString(courseLeaderName)).append("\n");
        sb.append("    actionType: ").append(toIndentedString(actionType)).append("\n");
        sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
        sb.append("    teacherNo: ").append(toIndentedString(teacherNo)).append("\n");
        sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
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

