package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseIdAndCollegeIdQuery
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-12-06T06:42:31.097Z")


public class CourseIdAndCollegeIdQuery {
    @JsonProperty("studentType")
    private Integer studentType = null;

    @JsonProperty("courseIdList")
    @Valid
    private List<String> courseIdList = null;

    @JsonProperty("collegeIdList")
    @Valid
    private List<String> collegeIdList = null;

    @JsonProperty("isDistinguishCourseStudentType")
    private String isDistinguishCourseStudentType = null;

    @JsonProperty("courseStudentTypeIds")
    private String courseStudentTypeIds = null;

    public String getIsDistinguishCourseStudentType() {
        return isDistinguishCourseStudentType;
    }

    public void setIsDistinguishCourseStudentType(String isDistinguishCourseStudentType) {
        this.isDistinguishCourseStudentType = isDistinguishCourseStudentType;
    }

    public String getCourseStudentTypeIds() {
        return courseStudentTypeIds;
    }

    public void setCourseStudentTypeIds(String courseStudentTypeIds) {
        this.courseStudentTypeIds = courseStudentTypeIds;
    }

    public CourseIdAndCollegeIdQuery studentType(Integer studentType) {
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

    public CourseIdAndCollegeIdQuery courseIdList(List<String> courseIdList) {
        this.courseIdList = courseIdList;
        return this;
    }

    public CourseIdAndCollegeIdQuery addCourseIdListItem(String courseIdListItem) {
        if (this.courseIdList == null) {
            this.courseIdList = new ArrayList<String>();
        }
        this.courseIdList.add(courseIdListItem);
        return this;
    }

    /**
     * 课程id集合
     *
     * @return courseIdList
     **/
    @ApiModelProperty(value = "课程id集合")


    public List<String> getCourseIdList() {
        return courseIdList;
    }

    public void setCourseIdList(List<String> courseIdList) {
        this.courseIdList = courseIdList;
    }

    public CourseIdAndCollegeIdQuery collegeIdList(List<String> collegeIdList) {
        this.collegeIdList = collegeIdList;
        return this;
    }

    public CourseIdAndCollegeIdQuery addCollegeIdListItem(String collegeIdListItem) {
        if (this.collegeIdList == null) {
            this.collegeIdList = new ArrayList<String>();
        }
        this.collegeIdList.add(collegeIdListItem);
        return this;
    }

    /**
     * 学院id集合
     *
     * @return collegeIdList
     **/
    @ApiModelProperty(value = "学院id集合")


    public List<String> getCollegeIdList() {
        return collegeIdList;
    }

    public void setCollegeIdList(List<String> collegeIdList) {
        this.collegeIdList = collegeIdList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseIdAndCollegeIdQuery courseIdAndCollegeIdQuery = (CourseIdAndCollegeIdQuery) o;
        return Objects.equals(this.studentType, courseIdAndCollegeIdQuery.studentType) &&
                Objects.equals(this.courseIdList, courseIdAndCollegeIdQuery.courseIdList) &&
                Objects.equals(this.collegeIdList, courseIdAndCollegeIdQuery.collegeIdList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentType, courseIdList, collegeIdList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseIdAndCollegeIdQuery {\n");

        sb.append("    studentType: ").append(toIndentedString(studentType)).append("\n");
        sb.append("    courseIdList: ").append(toIndentedString(courseIdList)).append("\n");
        sb.append("    collegeIdList: ").append(toIndentedString(collegeIdList)).append("\n");
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

