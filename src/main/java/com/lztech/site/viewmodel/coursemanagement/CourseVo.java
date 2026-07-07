package com.lztech.site.viewmodel.coursemanagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-01-12T08:51:14.736Z")


public class CourseVo {
    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseCode")
    private String courseCode = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("courseTeachingTeamId")
    private String courseTeachingTeamId = null;

    @JsonProperty("teacherType")
    private Integer teacherType = null;

    @JsonProperty("completeRate")
    private String completeRate = null;

    @JsonProperty("courseSource")
    private Integer courseSource = null;

    @JsonProperty("existCourseClass")
    private Boolean existCourseClass = null;

    @JsonProperty("coverInnerIp")
    private String coverInnerIp = null;

    @JsonProperty("coverOuterIp")
    private String coverOuterIp = null;

    @JsonProperty("coverFilepath")
    private String coverFilepath = null;

    public CourseVo courseId(String courseId) {
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

    public CourseVo courseCode(String courseCode) {
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

    public CourseVo courseName(String courseName) {
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

    public CourseVo courseTeachingTeamId(String courseTeachingTeamId) {
        this.courseTeachingTeamId = courseTeachingTeamId;
        return this;
    }

    /**
     * 课程教学团队Id
     *
     * @return courseTeachingTeamId
     **/
    @ApiModelProperty(value = "课程教学团队Id")


    public String getCourseTeachingTeamId() {
        return courseTeachingTeamId;
    }

    public void setCourseTeachingTeamId(String courseTeachingTeamId) {
        this.courseTeachingTeamId = courseTeachingTeamId;
    }

    public CourseVo teacherType(Integer teacherType) {
        this.teacherType = teacherType;
        return this;
    }

    /**
     * 老师类型：0 团队老师；1 课程负责人
     *
     * @return teacherType
     **/
    @ApiModelProperty(value = "老师类型：0 团队老师；1 课程负责人")


    public Integer getTeacherType() {
        return teacherType;
    }

    public void setTeacherType(Integer teacherType) {
        this.teacherType = teacherType;
    }

    public CourseVo completeRate(String completeRate) {
        this.completeRate = completeRate;
        return this;
    }

    /**
     * 资料完成度
     *
     * @return completeRate
     **/
    @ApiModelProperty(value = "资料完成度")


    public String getCompleteRate() {
        return completeRate;
    }

    public void setCompleteRate(String completeRate) {
        this.completeRate = completeRate;
    }

    public CourseVo courseSource(Integer courseSource) {
        this.courseSource = courseSource;
        return this;
    }

    /**
     * 课程来源（0：数据对接；1：课程管理）
     *
     * @return courseSource
     **/
    @ApiModelProperty(value = "课程来源（0：数据对接；1：课程管理）")


    public Integer getCourseSource() {
        return courseSource;
    }

    public void setCourseSource(Integer courseSource) {
        this.courseSource = courseSource;
    }

    public CourseVo existCourseClass(Boolean existCourseClass) {
        this.existCourseClass = existCourseClass;
        return this;
    }

    /**
     * 是否存在课程班级（true：存在；false：不存在）
     *
     * @return existCourseClass
     **/
    @ApiModelProperty(value = "是否存在课程班级（true：存在；false：不存在）")


    public Boolean isExistCourseClass() {
        return existCourseClass;
    }

    public void setExistCourseClass(Boolean existCourseClass) {
        this.existCourseClass = existCourseClass;
    }

    public CourseVo coverInnerIp(String coverInnerIp) {
        this.coverInnerIp = coverInnerIp;
        return this;
    }

    /**
     * 封面内网IP
     *
     * @return coverInnerIp
     **/
    @ApiModelProperty(value = "封面内网IP")


    public String getCoverInnerIp() {
        return coverInnerIp;
    }

    public void setCoverInnerIp(String coverInnerIp) {
        this.coverInnerIp = coverInnerIp;
    }

    public CourseVo coverOuterIp(String coverOuterIp) {
        this.coverOuterIp = coverOuterIp;
        return this;
    }

    /**
     * 封面外网IP
     *
     * @return coverOuterIp
     **/
    @ApiModelProperty(value = "封面外网IP")


    public String getCoverOuterIp() {
        return coverOuterIp;
    }

    public void setCoverOuterIp(String coverOuterIp) {
        this.coverOuterIp = coverOuterIp;
    }

    public CourseVo coverFilepath(String coverFilepath) {
        this.coverFilepath = coverFilepath;
        return this;
    }

    /**
     * 封面文件路径
     *
     * @return coverFilepath
     **/
    @ApiModelProperty(value = "封面文件路径")


    public String getCoverFilepath() {
        return coverFilepath;
    }

    public void setCoverFilepath(String coverFilepath) {
        this.coverFilepath = coverFilepath;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseVo courseVo = (CourseVo) o;
        return Objects.equals(this.courseId, courseVo.courseId) &&
                Objects.equals(this.courseCode, courseVo.courseCode) &&
                Objects.equals(this.courseName, courseVo.courseName) &&
                Objects.equals(this.courseTeachingTeamId, courseVo.courseTeachingTeamId) &&
                Objects.equals(this.teacherType, courseVo.teacherType) &&
                Objects.equals(this.completeRate, courseVo.completeRate) &&
                Objects.equals(this.courseSource, courseVo.courseSource) &&
                Objects.equals(this.existCourseClass, courseVo.existCourseClass) &&
                Objects.equals(this.coverInnerIp, courseVo.coverInnerIp) &&
                Objects.equals(this.coverOuterIp, courseVo.coverOuterIp) &&
                Objects.equals(this.coverFilepath, courseVo.coverFilepath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseCode, courseName, courseTeachingTeamId, teacherType,
                completeRate, courseSource, existCourseClass, coverInnerIp, coverOuterIp, coverFilepath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseVo {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    courseTeachingTeamId: ").append(toIndentedString(courseTeachingTeamId)).append("\n");
        sb.append("    teacherType: ").append(toIndentedString(teacherType)).append("\n");
        sb.append("    completeRate: ").append(toIndentedString(completeRate)).append("\n");
        sb.append("    courseSource: ").append(toIndentedString(courseSource)).append("\n");
        sb.append("    existCourseClass: ").append(toIndentedString(existCourseClass)).append("\n");
        sb.append("    coverInnerIp: ").append(toIndentedString(coverInnerIp)).append("\n");
        sb.append("    coverOuterIp: ").append(toIndentedString(coverOuterIp)).append("\n");
        sb.append("    coverFilepath: ").append(toIndentedString(coverFilepath)).append("\n");
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

