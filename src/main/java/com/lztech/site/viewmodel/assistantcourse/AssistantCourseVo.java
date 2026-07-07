package com.lztech.site.viewmodel.assistantcourse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lztech.site.viewmodel.CourseResource;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * AssistantCourseVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-05-17T05:49:36.623Z")


public class AssistantCourseVo {
    @JsonProperty("teacherId")
    private String teacherId = null;

    @JsonProperty("teacherName")
    private String teacherName = null;

    @JsonProperty("CourseResourceList")
    @Valid
    private List<CourseResource> courseResourceList = null;

    public AssistantCourseVo teacherId(String teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    /**
     * 教师id
     *
     * @return teacherId
     **/
    @ApiModelProperty(value = "教师id")


    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public AssistantCourseVo teacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    /**
     * 教师姓名
     *
     * @return teacherName
     **/
    @ApiModelProperty(value = "教师姓名")


    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public AssistantCourseVo courseResourceList(List<CourseResource> courseResourceList) {
        this.courseResourceList = courseResourceList;
        return this;
    }

    public AssistantCourseVo addCourseResourceListItem(CourseResource courseResourceListItem) {
        if (this.courseResourceList == null) {
            this.courseResourceList = new ArrayList<CourseResource>();
        }
        this.courseResourceList.add(courseResourceListItem);
        return this;
    }

    /**
     * 课程信息
     *
     * @return courseResourceList
     **/
    @ApiModelProperty(value = "课程信息")

    @Valid

    public List<CourseResource> getCourseResourceList() {
        return courseResourceList;
    }

    public void setCourseResourceList(List<CourseResource> courseResourceList) {
        this.courseResourceList = courseResourceList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AssistantCourseVo assistantCourseVo = (AssistantCourseVo) o;
        return Objects.equals(this.teacherId, assistantCourseVo.teacherId) &&
                Objects.equals(this.teacherName, assistantCourseVo.teacherName) &&
                Objects.equals(this.courseResourceList, assistantCourseVo.courseResourceList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, teacherName, courseResourceList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AssistantCourseVo {\n");

        sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
        sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
        sb.append("    courseResourceList: ").append(toIndentedString(courseResourceList)).append("\n");
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

