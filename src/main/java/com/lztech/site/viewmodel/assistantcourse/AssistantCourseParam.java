
package com.lztech.site.viewmodel.assistantcourse;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * AssistantCourseParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-05-17T09:20:48.065Z")


public class AssistantCourseParam {
    @JsonProperty("courseStudentTypes")
    private String courseStudentTypes = null;

    @JsonProperty("schoolYear")
    private String schoolYear = null;

    @JsonProperty("term")
    private Integer term = null;

    @JsonProperty("teacherAndCourseList")
    @Valid
    private List<TeacherAndCourse> teacherAndCourseList = null;

    public AssistantCourseParam courseStudentTypes(String courseStudentTypes) {
        this.courseStudentTypes = courseStudentTypes;
        return this;
    }

    /**
     * 允许的授课学生类型的开课
     *
     * @return courseStudentTypes
     **/
    @ApiModelProperty(value = "允许的授课学生类型的开课")


    public String getCourseStudentTypes() {
        return courseStudentTypes;
    }

    public void setCourseStudentTypes(String courseStudentTypes) {
        this.courseStudentTypes = courseStudentTypes;
    }

    public AssistantCourseParam schoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
        return this;
    }

    /**
     * 学年
     *
     * @return schoolYear
     **/
    @ApiModelProperty(value = "学年")


    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public AssistantCourseParam term(Integer term) {
        this.term = term;
        return this;
    }

    /**
     * 学期
     *
     * @return term
     **/
    @ApiModelProperty(value = "学期")


    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public AssistantCourseParam teacherAndCourseList(List<TeacherAndCourse> teacherAndCourseList) {
        this.teacherAndCourseList = teacherAndCourseList;
        return this;
    }

    public AssistantCourseParam addTeacherAndCourseListItem(TeacherAndCourse teacherAndCourseListItem) {
        if (this.teacherAndCourseList == null) {
            this.teacherAndCourseList = new ArrayList<TeacherAndCourse>();
        }
        this.teacherAndCourseList.add(teacherAndCourseListItem);
        return this;
    }

    /**
     * 教师和课程信息
     *
     * @return teacherAndCourseList
     **/
    @ApiModelProperty(value = "教师和课程信息")

    @Valid

    public List<TeacherAndCourse> getTeacherAndCourseList() {
        return teacherAndCourseList;
    }

    public void setTeacherAndCourseList(List<TeacherAndCourse> teacherAndCourseList) {
        this.teacherAndCourseList = teacherAndCourseList;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AssistantCourseParam assistantCourseParam = (AssistantCourseParam) o;
        return Objects.equals(this.courseStudentTypes, assistantCourseParam.courseStudentTypes) &&
                Objects.equals(this.schoolYear, assistantCourseParam.schoolYear) &&
                Objects.equals(this.term, assistantCourseParam.term) &&
                Objects.equals(this.teacherAndCourseList, assistantCourseParam.teacherAndCourseList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseStudentTypes, schoolYear, term, teacherAndCourseList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AssistantCourseParam {\n");

        sb.append("    courseStudentTypes: ").append(toIndentedString(courseStudentTypes)).append("\n");
        sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
        sb.append("    term: ").append(toIndentedString(term)).append("\n");
        sb.append("    teacherAndCourseList: ").append(toIndentedString(teacherAndCourseList)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

