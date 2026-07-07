package com.lztech.site.viewmodel.studentcoursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * StudentCourseNameFirstSpell
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-09-25T14:53:35.940+08:00")

public class StudentCourseNameFirstSpell {
    @JsonProperty("firstSpell")
    private String firstSpell = null;

    @JsonProperty("courseList")
    @Valid
    private List<StudentCourseAndCourseTableId> courseList = null;

    public StudentCourseNameFirstSpell firstSpell(String firstSpell) {
        this.firstSpell = firstSpell;
        return this;
    }

    /**
     * 首字母
     *
     * @return firstSpell
     **/
    @ApiModelProperty(value = "首字母")


    public String getFirstSpell() {
        return firstSpell;
    }

    public void setFirstSpell(String firstSpell) {
        this.firstSpell = firstSpell;
    }

    public StudentCourseNameFirstSpell courseList(List<StudentCourseAndCourseTableId> courseList) {
        this.courseList = courseList;
        return this;
    }

    public StudentCourseNameFirstSpell addCourseListItem(StudentCourseAndCourseTableId courseListItem) {
        if (this.courseList == null) {
            this.courseList = new ArrayList<StudentCourseAndCourseTableId>();
        }
        this.courseList.add(courseListItem);
        return this;
    }

    /**
     * Get courseList
     *
     * @return courseList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<StudentCourseAndCourseTableId> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<StudentCourseAndCourseTableId> courseList) {
        this.courseList = courseList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StudentCourseNameFirstSpell studentCourseNameFirstSpell = (StudentCourseNameFirstSpell) o;
        return Objects.equals(this.firstSpell, studentCourseNameFirstSpell.firstSpell) &&
                Objects.equals(this.courseList, studentCourseNameFirstSpell.courseList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstSpell, courseList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class StudentCourseNameFirstSpell {\n");

        sb.append("    firstSpell: ").append(toIndentedString(firstSpell)).append("\n");
        sb.append("    courseList: ").append(toIndentedString(courseList)).append("\n");
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

