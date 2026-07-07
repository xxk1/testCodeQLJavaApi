package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * LessonPreviewParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-09-20T01:48:46.432Z")


public class LessonPreviewParam {
    @JsonProperty("courseTableCustomIdList")
    @Valid
    private List<String> courseTableCustomIdList = null;

    @JsonProperty("schoolYear")
    private String schoolYear = null;

    @JsonProperty("term")
    private Integer term = null;

    @JsonProperty("studentType")
    private String studentType = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("page")
    private Integer page = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    @JsonProperty("courseDate")
    private String courseDate = null;

    public String getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(String courseDate) {
        this.courseDate = courseDate;
    }

    public LessonPreviewParam courseTableCustomIdList(List<String> courseTableCustomIdList) {
        this.courseTableCustomIdList = courseTableCustomIdList;
        return this;
    }

    public LessonPreviewParam addCourseTableCustomIdListItem(String courseTableCustomIdListItem) {
        if (this.courseTableCustomIdList == null) {
            this.courseTableCustomIdList = new ArrayList<String>();
        }
        this.courseTableCustomIdList.add(courseTableCustomIdListItem);
        return this;
    }

    /**
     * 课表自定义id集合（为空则获取全部开课类型为本科的正在直播课表信息）
     *
     * @return courseTableCustomIdList
     **/
    @ApiModelProperty(value = "课表自定义id集合（为空则获取全部开课类型为本科的正在直播课表信息）")


    public List<String> getCourseTableCustomIdList() {
        return courseTableCustomIdList;
    }

    public void setCourseTableCustomIdList(List<String> courseTableCustomIdList) {
        this.courseTableCustomIdList = courseTableCustomIdList;
    }

    public LessonPreviewParam schoolYear(String schoolYear) {
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

    public LessonPreviewParam term(Integer term) {
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

    public LessonPreviewParam studentType(String studentType) {
        this.studentType = studentType;
        return this;
    }

    /**
     * 课程开课类型（为空则获取全部）：0：本科生 1：研究生
     *
     * @return studentType
     **/
    @ApiModelProperty(value = "课程开课类型（为空则获取全部）：0：本科生 1：研究生")


    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    public LessonPreviewParam courseName(String courseName) {
        this.courseName = courseName;
        return this;
    }

    /**
     * 课程名称，模糊查询
     *
     * @return courseName
     **/
    @ApiModelProperty(value = "课程名称，模糊查询")


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LessonPreviewParam page(Integer page) {
        this.page = page;
        return this;
    }

    /**
     * 当前页
     *
     * @return page
     **/
    @ApiModelProperty(value = "当前页")


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public LessonPreviewParam pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * 每页个数
     *
     * @return pageSize
     **/
    @ApiModelProperty(value = "每页个数")


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LessonPreviewParam lessonPreviewParam = (LessonPreviewParam) o;
        return Objects.equals(this.courseTableCustomIdList, lessonPreviewParam.courseTableCustomIdList) &&
                Objects.equals(this.schoolYear, lessonPreviewParam.schoolYear) &&
                Objects.equals(this.term, lessonPreviewParam.term) &&
                Objects.equals(this.studentType, lessonPreviewParam.studentType) &&
                Objects.equals(this.courseName, lessonPreviewParam.courseName) &&
                Objects.equals(this.page, lessonPreviewParam.page) &&
                Objects.equals(this.pageSize, lessonPreviewParam.pageSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseTableCustomIdList, schoolYear, term, studentType, courseName, page, pageSize);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class LessonPreviewParam {\n");

        sb.append("    courseTableCustomIdList: ").append(toIndentedString(courseTableCustomIdList)).append("\n");
        sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
        sb.append("    term: ").append(toIndentedString(term)).append("\n");
        sb.append("    studentType: ").append(toIndentedString(studentType)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
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

