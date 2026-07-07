package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * LiveCourseParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-08-18T11:25:31.751Z")


public class LiveCourseParam {
    @JsonProperty("courseTableCustomIdList")
    @Valid
    private List<String> courseTableCustomIdList = null;

    @JsonProperty("authorizationScopeType")
    private Integer authorizationScopeType = null;

    @JsonProperty("groupIdList")
    @Valid
    private List<String> groupIdList = null;

    @JsonProperty("schoolYear")
    private String schoolYear = null;

    @JsonProperty("term")
    private Integer term = null;

    @JsonProperty("termIds")
    private String termIds = null;

    @JsonProperty("studentType")
    private String studentType = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("page")
    private Integer page = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    public String getTermIds() {
        return termIds;
    }

    public void setTermIds(String termIds) {
        this.termIds = termIds;
    }

    public LiveCourseParam courseTableCustomIdList(List<String> courseTableCustomIdList) {
        this.courseTableCustomIdList = courseTableCustomIdList;
        return this;
    }

    public LiveCourseParam addCourseTableCustomIdListItem(String courseTableCustomIdListItem) {
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

    public LiveCourseParam authorizationScopeType(Integer authorizationScopeType) {
        this.authorizationScopeType = authorizationScopeType;
        return this;
    }

    /**
     * 是否授权全部课程范围,0：授权全部课程范围，1：授权直播课程范围，2：授权点播课程范围
     *
     * @return authorizationScopeType
     **/
    @ApiModelProperty(value = "是否授权全部课程范围,0：授权全部课程范围，1：授权直播课程范围，2：授权点播课程范围")


    public Integer getAuthorizationScopeType() {
        return authorizationScopeType;
    }

    public void setAuthorizationScopeType(Integer authorizationScopeType) {
        this.authorizationScopeType = authorizationScopeType;
    }

    public LiveCourseParam groupIdList(List<String> groupIdList) {
        this.groupIdList = groupIdList;
        return this;
    }

    public LiveCourseParam addGroupIdListItem(String groupIdListItem) {
        if (this.groupIdList == null) {
            this.groupIdList = new ArrayList<String>();
        }
        this.groupIdList.add(groupIdListItem);
        return this;
    }

    /**
     * 班级id集合
     *
     * @return groupIdList
     **/
    @ApiModelProperty(value = "班级id集合")


    public List<String> getGroupIdList() {
        return groupIdList;
    }

    public void setGroupIdList(List<String> groupIdList) {
        this.groupIdList = groupIdList;
    }

    public LiveCourseParam schoolYear(String schoolYear) {
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

    public LiveCourseParam term(Integer term) {
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

    public LiveCourseParam studentType(String studentType) {
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

    public LiveCourseParam courseName(String courseName) {
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

    public LiveCourseParam page(Integer page) {
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

    public LiveCourseParam pageSize(Integer pageSize) {
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
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LiveCourseParam liveCourseParam = (LiveCourseParam) o;
        return Objects.equals(this.courseTableCustomIdList, liveCourseParam.courseTableCustomIdList) &&
                Objects.equals(this.authorizationScopeType, liveCourseParam.authorizationScopeType) &&
                Objects.equals(this.groupIdList, liveCourseParam.groupIdList) &&
                Objects.equals(this.schoolYear, liveCourseParam.schoolYear) &&
                Objects.equals(this.term, liveCourseParam.term) &&
                Objects.equals(this.studentType, liveCourseParam.studentType) &&
                Objects.equals(this.courseName, liveCourseParam.courseName) &&
                Objects.equals(this.page, liveCourseParam.page) &&
                Objects.equals(this.pageSize, liveCourseParam.pageSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseTableCustomIdList, authorizationScopeType, groupIdList, schoolYear, term, studentType, courseName, page, pageSize);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class LiveCourseParam {\n");

        sb.append("    courseTableCustomIdList: ").append(toIndentedString(courseTableCustomIdList)).append("\n");
        sb.append("    authorizationScopeType: ").append(toIndentedString(authorizationScopeType)).append("\n");
        sb.append("    groupIdList: ").append(toIndentedString(groupIdList)).append("\n");
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
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

