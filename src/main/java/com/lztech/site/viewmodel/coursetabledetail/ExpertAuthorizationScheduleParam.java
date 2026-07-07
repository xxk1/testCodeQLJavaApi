package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ExpertAuthorizationScheduleParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-07-21T03:28:24.768Z")


public class ExpertAuthorizationScheduleParam {
    @JsonProperty("courseCodeOrName")
    private String courseCodeOrName = null;

    @JsonProperty("teacherNoOrName")
    private String teacherNoOrName = null;

    @JsonProperty("courseDate")
    private String courseDate = null;

    @JsonProperty("courseTableCustomIds")
    private String courseTableCustomIds = null;

    @JsonProperty("courseTableCollegeId")
    private String courseTableCollegeId = null;

    public String getCourseTableCollegeId() {
        return courseTableCollegeId;
    }

    public void setCourseTableCollegeId(String courseTableCollegeId) {
        this.courseTableCollegeId = courseTableCollegeId;
    }

    public ExpertAuthorizationScheduleParam courseCodeOrName(String courseCodeOrName) {
        this.courseCodeOrName = courseCodeOrName;
        return this;
    }

    /**
     * 课程编号或姓名
     *
     * @return courseCodeOrName
     **/
    @ApiModelProperty(value = "课程编号或姓名")


    public String getCourseCodeOrName() {
        return courseCodeOrName;
    }

    public void setCourseCodeOrName(String courseCodeOrName) {
        this.courseCodeOrName = courseCodeOrName;
    }

    public ExpertAuthorizationScheduleParam teacherNoOrName(String teacherNoOrName) {
        this.teacherNoOrName = teacherNoOrName;
        return this;
    }

    /**
     * 教师工号或姓名
     *
     * @return teacherNoOrName
     **/
    @ApiModelProperty(value = "教师工号或姓名")


    public String getTeacherNoOrName() {
        return teacherNoOrName;
    }

    public void setTeacherNoOrName(String teacherNoOrName) {
        this.teacherNoOrName = teacherNoOrName;
    }

    public ExpertAuthorizationScheduleParam courseDate(String courseDate) {
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

    public ExpertAuthorizationScheduleParam courseTableCustomIds(String courseTableCustomIds) {
        this.courseTableCustomIds = courseTableCustomIds;
        return this;
    }

    /**
     * 课表自定义id集合，多个用“;”隔开
     *
     * @return courseTableCustomIds
     **/
    @ApiModelProperty(value = "课表自定义id集合，多个用“;”隔开")


    public String getCourseTableCustomIds() {
        return courseTableCustomIds;
    }

    public void setCourseTableCustomIds(String courseTableCustomIds) {
        this.courseTableCustomIds = courseTableCustomIds;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExpertAuthorizationScheduleParam expertAuthorizationScheduleParam = (ExpertAuthorizationScheduleParam) o;
        return Objects.equals(this.courseCodeOrName, expertAuthorizationScheduleParam.courseCodeOrName) &&
                Objects.equals(this.teacherNoOrName, expertAuthorizationScheduleParam.teacherNoOrName) &&
                Objects.equals(this.courseDate, expertAuthorizationScheduleParam.courseDate) &&
                Objects.equals(this.courseTableCustomIds, expertAuthorizationScheduleParam.courseTableCustomIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseCodeOrName, teacherNoOrName, courseDate, courseTableCustomIds);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExpertAuthorizationScheduleParam {\n");

        sb.append("    courseCodeOrName: ").append(toIndentedString(courseCodeOrName)).append("\n");
        sb.append("    teacherNoOrName: ").append(toIndentedString(teacherNoOrName)).append("\n");
        sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
        sb.append("    courseTableCustomIds: ").append(toIndentedString(courseTableCustomIds)).append("\n");
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

