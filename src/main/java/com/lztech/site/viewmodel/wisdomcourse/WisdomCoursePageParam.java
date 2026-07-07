package com.lztech.site.viewmodel.wisdomcourse;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * WisdomCoursePageParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-08-13T15:53:10.736+08:00")

public class WisdomCoursePageParam {
    @JsonProperty("courseNoOrName")
    private String courseNoOrName = null;

    @JsonProperty("pageNum")
    private Integer pageNum = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    @JsonProperty("teacherNoOrName")
    private String teacherNoOrName = null;

    public WisdomCoursePageParam courseNoOrName(String courseNoOrName) {
        this.courseNoOrName = courseNoOrName;
        return this;
    }

    /**
     * 课程编号或名称
     *
     * @return courseNoOrName
     **/
    @ApiModelProperty(value = "课程编号或名称")


    public String getCourseNoOrName() {
        return courseNoOrName;
    }

    public void setCourseNoOrName(String courseNoOrName) {
        this.courseNoOrName = courseNoOrName;
    }

    public WisdomCoursePageParam pageNum(Integer pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    /**
     * 页码
     *
     * @return pageNum
     **/
    @ApiModelProperty(value = "页码")


    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public WisdomCoursePageParam pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * 页大小
     *
     * @return pageSize
     **/
    @ApiModelProperty(value = "页大小")


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public WisdomCoursePageParam teacherNoOrName(String teacherNoOrName) {
        this.teacherNoOrName = teacherNoOrName;
        return this;
    }

    /**
     * 教师编号或名称
     *
     * @return teacherNoOrName
     **/
    @ApiModelProperty(value = "教师编号或名称")


    public String getTeacherNoOrName() {
        return teacherNoOrName;
    }

    public void setTeacherNoOrName(String teacherNoOrName) {
        this.teacherNoOrName = teacherNoOrName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WisdomCoursePageParam wisdomCoursePageParam = (WisdomCoursePageParam) o;
        return Objects.equals(this.courseNoOrName, wisdomCoursePageParam.courseNoOrName) &&
                Objects.equals(this.pageNum, wisdomCoursePageParam.pageNum) &&
                Objects.equals(this.pageSize, wisdomCoursePageParam.pageSize) &&
                Objects.equals(this.teacherNoOrName, wisdomCoursePageParam.teacherNoOrName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseNoOrName, pageNum, pageSize, teacherNoOrName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class WisdomCoursePageParam {\n");

        sb.append("    courseNoOrName: ").append(toIndentedString(courseNoOrName)).append("\n");
        sb.append("    pageNum: ").append(toIndentedString(pageNum)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    teacherNoOrName: ").append(toIndentedString(teacherNoOrName)).append("\n");
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

