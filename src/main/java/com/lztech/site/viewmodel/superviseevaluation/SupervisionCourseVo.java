package com.lztech.site.viewmodel.superviseevaluation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * SupervisionCourseVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-12-12T12:28:44.807Z")


public class SupervisionCourseVo {
    @JsonProperty("classNickName")
    private String classNickName = null;

    @JsonProperty("classCompositionName")
    private String classCompositionName = null;

    @JsonProperty("source")
    private Integer source = null;

    @JsonProperty("studentType")
    private Integer studentType = null;

    @JsonProperty("courseTableId")
    private String courseTableId = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("className")
    private String className = null;

    @JsonProperty("teachers")
    private String teachers = null;

    @JsonProperty("live")
    private Boolean live = null;

    @JsonProperty("liveCourseTableDetailId")
    private String liveCourseTableDetailId = null;
    @JsonProperty("courseTableDetailSegmentStartTime")
    private String courseTableDetailSegmentStartTime = null;

    @JsonProperty("courseTableDetailSegmentEndTime")
    private String courseTableDetailSegmentEndTime = null;

    @JsonProperty("courseAttrName")
    private String courseAttrName = null;

    public String getCourseAttrName() {
        return courseAttrName;
    }

    public void setCourseAttrName(String courseAttrName) {
        this.courseAttrName = courseAttrName;
    }

    public SupervisionCourseVo classNickName(String classNickName) {
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

    public SupervisionCourseVo classCompositionName(String classCompositionName) {
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

    public SupervisionCourseVo source(Integer source) {
        this.source = source;
        return this;
    }

    /**
     * 班级来源（数据对接,0；系统录入,1；外部导入,2；自主开班,3；）
     *
     * @return source
     **/
    @ApiModelProperty(value = "班级来源（数据对接,0；系统录入,1；外部导入,2；自主开班,3；）")


    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public SupervisionCourseVo studentType(Integer studentType) {
        this.studentType = studentType;
        return this;
    }

    /**
     * 开课类型
     *
     * @return studentType
     **/
    @ApiModelProperty(value = "开课类型")


    public Integer getStudentType() {
        return studentType;
    }

    public void setStudentType(Integer studentType) {
        this.studentType = studentType;
    }

    public SupervisionCourseVo courseTableId(String courseTableId) {
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

    public SupervisionCourseVo courseName(String courseName) {
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

    public SupervisionCourseVo className(String className) {
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

    public SupervisionCourseVo teachers(String teachers) {
        this.teachers = teachers;
        return this;
    }

    /**
     * 老师信息（姓名+（工号），多个顿号分割）
     *
     * @return teachers
     **/
    @ApiModelProperty(value = "老师信息（姓名+（工号），多个顿号分割）")


    public String getTeachers() {
        return teachers;
    }

    public void setTeachers(String teachers) {
        this.teachers = teachers;
    }

    public SupervisionCourseVo live(Boolean live) {
        this.live = live;
        return this;
    }

    /**
     * 是否直播中（0-不在直播中，1-直播中）
     *
     * @return live
     **/
    @ApiModelProperty(value = "是否直播中（0-不在直播中，1-直播中）")


    public Boolean isLive() {
        return live;
    }

    public void setLive(Boolean live) {
        this.live = live;
    }

    public SupervisionCourseVo liveCourseTableDetailId(String liveCourseTableDetailId) {
        this.liveCourseTableDetailId = liveCourseTableDetailId;
        return this;
    }

    /**
     * 直播中的课表明细id
     *
     * @return liveCourseTableDetailId
     **/
    @ApiModelProperty(value = "直播中的课表明细id")


    public String getLiveCourseTableDetailId() {
        return liveCourseTableDetailId;
    }

    public void setLiveCourseTableDetailId(String liveCourseTableDetailId) {
        this.liveCourseTableDetailId = liveCourseTableDetailId;
    }

    public SupervisionCourseVo courseTableDetailSegmentStartTime(String courseTableDetailSegmentStartTime) {
        this.courseTableDetailSegmentStartTime = courseTableDetailSegmentStartTime;
        return this;
    }

    /**
     * 课表节次开始时间
     *
     * @return courseTableDetailSegmentStartTime
     **/
    @ApiModelProperty(value = "课表节次开始时间")


    public String getCourseTableDetailSegmentStartTime() {
        return courseTableDetailSegmentStartTime;
    }

    public void setCourseTableDetailSegmentStartTime(String courseTableDetailSegmentStartTime) {
        this.courseTableDetailSegmentStartTime = courseTableDetailSegmentStartTime;
    }

    public SupervisionCourseVo courseTableDetailSegmentEndTime(String courseTableDetailSegmentEndTime) {
        this.courseTableDetailSegmentEndTime = courseTableDetailSegmentEndTime;
        return this;
    }

    /**
     * 课表节次结束时间
     *
     * @return courseTableDetailSegmentEndTime
     **/
    @ApiModelProperty(value = "课表节次结束时间")


    public String getCourseTableDetailSegmentEndTime() {
        return courseTableDetailSegmentEndTime;
    }

    public void setCourseTableDetailSegmentEndTime(String courseTableDetailSegmentEndTime) {
        this.courseTableDetailSegmentEndTime = courseTableDetailSegmentEndTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SupervisionCourseVo supervisionCourseVo = (SupervisionCourseVo) o;
        return Objects.equals(this.classNickName, supervisionCourseVo.classNickName) &&
                Objects.equals(this.classCompositionName, supervisionCourseVo.classCompositionName) &&
                Objects.equals(this.source, supervisionCourseVo.source) &&
                Objects.equals(this.studentType, supervisionCourseVo.studentType) &&
                Objects.equals(this.courseTableId, supervisionCourseVo.courseTableId) &&
                Objects.equals(this.courseName, supervisionCourseVo.courseName) &&
                Objects.equals(this.className, supervisionCourseVo.className) &&
                Objects.equals(this.teachers, supervisionCourseVo.teachers) &&
                Objects.equals(this.live, supervisionCourseVo.live) &&
                Objects.equals(this.liveCourseTableDetailId, supervisionCourseVo.liveCourseTableDetailId) &&
                Objects.equals(this.courseTableDetailSegmentStartTime, supervisionCourseVo.courseTableDetailSegmentStartTime) &&
                Objects.equals(this.courseTableDetailSegmentEndTime, supervisionCourseVo.courseTableDetailSegmentEndTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classNickName, classCompositionName, source, studentType, courseTableId, courseName,
                className, teachers, live, liveCourseTableDetailId, courseTableDetailSegmentStartTime, courseTableDetailSegmentEndTime);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SupervisionCourseVo {\n");

        sb.append("    classNickName: ").append(toIndentedString(classNickName)).append("\n");
        sb.append("    classCompositionName: ").append(toIndentedString(classCompositionName)).append("\n");
        sb.append("    source: ").append(toIndentedString(source)).append("\n");
        sb.append("    studentType: ").append(toIndentedString(studentType)).append("\n");
        sb.append("    courseTableId: ").append(toIndentedString(courseTableId)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    className: ").append(toIndentedString(className)).append("\n");
        sb.append("    teachers: ").append(toIndentedString(teachers)).append("\n");
        sb.append("    live: ").append(toIndentedString(live)).append("\n");
        sb.append("    liveCourseTableDetailId: ").append(toIndentedString(liveCourseTableDetailId)).append("\n");
        sb.append("    courseTableDetailSegmentStartTime: ").append(toIndentedString(courseTableDetailSegmentStartTime)).append("\n");
        sb.append("    courseTableDetailSegmentEndTime: ").append(toIndentedString(courseTableDetailSegmentEndTime)).append("\n");
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

