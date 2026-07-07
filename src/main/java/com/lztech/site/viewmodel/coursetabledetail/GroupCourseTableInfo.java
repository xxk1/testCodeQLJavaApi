package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * GroupCourseTableInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-02-22T05:44:45.129Z")
public class GroupCourseTableInfo {
    @JsonProperty("groupId")
    private String groupId = null;

    @JsonProperty("courseCode")
    private String courseCode = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("schoolYear")
    private String schoolYear = null;

    @JsonProperty("term")
    private Integer term = null;

    @JsonProperty("courseTableDetailList")
    @Valid
    private List<CourseTableDetailResource> courseTableDetailList = null;

    public GroupCourseTableInfo groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    /**
     * 班级编号
     *
     * @return groupNo
     **/
    @ApiModelProperty(value = "班级id")


    public String getGroupId() {
        return groupId;
    }

    public void setGroupNo(String groupId) {
        this.groupId = groupId;
    }

    public GroupCourseTableInfo courseCode(String courseCode) {
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

    public GroupCourseTableInfo courseName(String courseName) {
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

    public GroupCourseTableInfo schoolYear(String schoolYear) {
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

    public GroupCourseTableInfo term(Integer term) {
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

    public GroupCourseTableInfo courseTableDetailList(List<CourseTableDetailResource> courseTableDetailList) {
        this.courseTableDetailList = courseTableDetailList;
        return this;
    }

    public GroupCourseTableInfo addCourseTableDetailListItem(CourseTableDetailResource courseTableDetailListItem) {
        if (this.courseTableDetailList == null) {
            this.courseTableDetailList = new ArrayList<CourseTableDetailResource>();
        }
        this.courseTableDetailList.add(courseTableDetailListItem);
        return this;
    }

    /**
     * Get courseTableDetailList
     *
     * @return courseTableDetailList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<CourseTableDetailResource> getCourseTableDetailList() {
        return courseTableDetailList;
    }

    public void setCourseTableDetailList(List<CourseTableDetailResource> courseTableDetailList) {
        this.courseTableDetailList = courseTableDetailList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GroupCourseTableInfo groupCourseTableInfo = (GroupCourseTableInfo) o;
        return Objects.equals(this.groupId, groupCourseTableInfo.groupId) &&
                Objects.equals(this.courseCode, groupCourseTableInfo.courseCode) &&
                Objects.equals(this.courseName, groupCourseTableInfo.courseName) &&
                Objects.equals(this.schoolYear, groupCourseTableInfo.schoolYear) &&
                Objects.equals(this.term, groupCourseTableInfo.term) &&
                Objects.equals(this.courseTableDetailList, groupCourseTableInfo.courseTableDetailList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, courseCode, courseName, schoolYear, term, courseTableDetailList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GroupCourseTableInfo {\n");

        sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
        sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
        sb.append("    term: ").append(toIndentedString(term)).append("\n");
        sb.append("    courseTableDetailList: ").append(toIndentedString(courseTableDetailList)).append("\n");
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

