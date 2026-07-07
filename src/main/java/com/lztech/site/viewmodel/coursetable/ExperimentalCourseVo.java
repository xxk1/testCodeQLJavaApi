package com.lztech.site.viewmodel.coursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ExperimentalCourseVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-06-16T06:45:52.365Z")


public class ExperimentalCourseVo {
    @JsonProperty("courseTableId")
    private String courseTableId = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("className")
    private String className = null;

    @JsonProperty("teacherInfos")
    private String teacherInfos = null;

    @JsonProperty("projectCount")
    private Integer projectCount = null;

    @JsonProperty("period")
    private Integer period = null;

    public ExperimentalCourseVo courseTableId(String courseTableId) {
        this.courseTableId = courseTableId;
        return this;
    }

    /**
     * 开课课表Id
     *
     * @return courseTableId
     **/
    @ApiModelProperty(value = "开课课表Id")


    public String getCourseTableId() {
        return courseTableId;
    }

    public void setCourseTableId(String courseTableId) {
        this.courseTableId = courseTableId;
    }

    public ExperimentalCourseVo courseName(String courseName) {
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

    public ExperimentalCourseVo className(String className) {
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

    public ExperimentalCourseVo teacherInfos(String teacherInfos) {
        this.teacherInfos = teacherInfos;
        return this;
    }

    /**
     * 教师信息;展示格式：teacherName(teacherNo),多个用','拼接
     *
     * @return teacherInfos
     **/
    @ApiModelProperty(value = "教师信息;展示格式：teacherName(teacherNo),多个用','拼接")


    public String getTeacherInfos() {
        return teacherInfos;
    }

    public void setTeacherInfos(String teacherInfos) {
        this.teacherInfos = teacherInfos;
    }

    public ExperimentalCourseVo projectCount(Integer projectCount) {
        this.projectCount = projectCount;
        return this;
    }

    /**
     * 项目数量
     *
     * @return projectCount
     **/
    @ApiModelProperty(value = "项目数量")


    public Integer getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(Integer projectCount) {
        this.projectCount = projectCount;
    }

    public ExperimentalCourseVo period(Integer period) {
        this.period = period;
        return this;
    }

    /**
     * 课程学时（一个小节次为一个学时）
     *
     * @return period
     **/
    @ApiModelProperty(value = "课程学时（一个小节次为一个学时）")


    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExperimentalCourseVo experimentalCourseVo = (ExperimentalCourseVo) o;
        return Objects.equals(this.courseTableId, experimentalCourseVo.courseTableId) &&
                Objects.equals(this.courseName, experimentalCourseVo.courseName) &&
                Objects.equals(this.className, experimentalCourseVo.className) &&
                Objects.equals(this.teacherInfos, experimentalCourseVo.teacherInfos) &&
                Objects.equals(this.projectCount, experimentalCourseVo.projectCount) &&
                Objects.equals(this.period, experimentalCourseVo.period);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseTableId, courseName, className, teacherInfos, projectCount, period);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExperimentalCourseVo {\n");

        sb.append("    courseTableId: ").append(toIndentedString(courseTableId)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    className: ").append(toIndentedString(className)).append("\n");
        sb.append("    teacherInfos: ").append(toIndentedString(teacherInfos)).append("\n");
        sb.append("    projectCount: ").append(toIndentedString(projectCount)).append("\n");
        sb.append("    period: ").append(toIndentedString(period)).append("\n");
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

