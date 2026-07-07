package com.lztech.site.viewmodel.superviseevaluation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * SupervisionCourseQueryParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-12T04:51:24.220Z")


public class SupervisionCourseQueryParam {
    @JsonProperty("page")
    private Integer page = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("teacherNameOrNo")
    private String teacherNameOrNo = null;

    @JsonProperty("courseNameOrTeacherName")
    private String courseNameOrTeacherName = null;

    @JsonProperty("nowTime")
    private String nowTime = null;

    @JsonProperty("live")
    private Integer live = null;

    @JsonProperty("pagingEnabled")
    private Boolean pagingEnabled = null;

    @JsonProperty("sortName")
    private String sortName = null;

    @JsonProperty("schoolYear")
    private String schoolYear = null;

    @JsonProperty("term")
    private Integer term = null;

    @JsonProperty("courseStudentTypes")
    private String courseStudentTypes;

    @JsonProperty("isDistinguishCourseStudentType")
    private String isDistinguishCourseStudentType = null;

    @JsonProperty("isNeedFilterPoliticalCourse")
    private Boolean isNeedFilterPoliticalCourse = null;

    @JsonProperty("onlyGraduateCourse")
    private Integer onlyGraduateCourse =null;

    @JsonProperty("superviseCollegeType")
    private Integer superviseCollegeType;

    public Integer getSuperviseCollegeType() {
        return superviseCollegeType;
    }

    public void setSuperviseCollegeType(Integer superviseCollegeType) {
        this.superviseCollegeType = superviseCollegeType;
    }

    public Boolean getNeedFilterPoliticalCourse() {
        return isNeedFilterPoliticalCourse;
    }

    public void setNeedFilterPoliticalCourse(Boolean needFilterPoliticalCourse) {
        isNeedFilterPoliticalCourse = needFilterPoliticalCourse;
    }

    public Integer getOnlyGraduateCourse() {
        return onlyGraduateCourse;
    }

    public void setOnlyGraduateCourse(Integer onlyGraduateCourse) {
        this.onlyGraduateCourse = onlyGraduateCourse;
    }

    public String getIsDistinguishCourseStudentType() {
        return isDistinguishCourseStudentType;
    }

    public void setIsDistinguishCourseStudentType(String isDistinguishCourseStudentType) {
        this.isDistinguishCourseStudentType = isDistinguishCourseStudentType;
    }

    public String getCourseStudentTypes() {
        return courseStudentTypes;
    }

    public void setCourseStudentTypes(String courseStudentTypes) {
        this.courseStudentTypes = courseStudentTypes;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public SupervisionCourseQueryParam page(Integer page) {
        this.page = page;
        return this;
    }

    /**
     * 当前页码
     *
     * @return page
     **/
    @ApiModelProperty(value = "当前页码")


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public SupervisionCourseQueryParam pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * 每页记录数
     *
     * @return pageSize
     **/
    @ApiModelProperty(value = "每页记录数")


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public SupervisionCourseQueryParam collegeId(String collegeId) {
        this.collegeId = collegeId;
        return this;
    }

    /**
     * 学院id
     *
     * @return collegeId
     **/
    @ApiModelProperty(value = "学院id")


    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public SupervisionCourseQueryParam courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    /**
     * 课程id
     *
     * @return courseId
     **/
    @ApiModelProperty(value = "课程id")


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public SupervisionCourseQueryParam teacherNameOrNo(String teacherNameOrNo) {
        this.teacherNameOrNo = teacherNameOrNo;
        return this;
    }

    /**
     * 授课教师姓名或工号
     *
     * @return teacherNameOrNo
     **/
    @ApiModelProperty(value = "授课教师姓名或工号")


    public String getTeacherNameOrNo() {
        return teacherNameOrNo;
    }

    public void setTeacherNameOrNo(String teacherNameOrNo) {
        this.teacherNameOrNo = teacherNameOrNo;
    }

    public SupervisionCourseQueryParam courseNameOrTeacherName(String courseNameOrTeacherName) {
        this.courseNameOrTeacherName = courseNameOrTeacherName;
        return this;
    }

    /**
     * 课程/老师姓名
     *
     * @return courseNameOrTeacherName
     **/
    @ApiModelProperty(value = "课程/老师姓名")


    public String getCourseNameOrTeacherName() {
        return courseNameOrTeacherName;
    }

    public void setCourseNameOrTeacherName(String courseNameOrTeacherName) {
        this.courseNameOrTeacherName = courseNameOrTeacherName;
    }

    public SupervisionCourseQueryParam nowTime(String nowTime) {
        this.nowTime = nowTime;
        return this;
    }

    /**
     * 现在的时间（yyyy-MM-hh HH:mm:ss）
     *
     * @return nowTime
     **/
    @ApiModelProperty(value = "现在的时间（yyyy-MM-hh HH:mm:ss）")


    public String getNowTime() {
        return nowTime;
    }

    public void setNowTime(String nowTime) {
        this.nowTime = nowTime;
    }

    public SupervisionCourseQueryParam live(Integer live) {
        this.live = live;
        return this;
    }

    /**
     * 是否直播中（1-直播中，其他不作为查询条件）
     *
     * @return live
     **/
    @ApiModelProperty(value = "是否直播中（1-直播中，其他不作为查询条件）")


    public Integer getLive() {
        return live;
    }

    public void setLive(Integer live) {
        this.live = live;
    }

    public SupervisionCourseQueryParam pagingEnabled(Boolean pagingEnabled) {
        this.pagingEnabled = pagingEnabled;
        return this;
    }

    public Boolean getPagingEnabled() {
        return pagingEnabled;
    }

    public void setPagingEnabled(Boolean pagingEnabled) {
        this.pagingEnabled = pagingEnabled;
    }

    /**
     * 是否需要分页  默认需要
     *
     * @return isNeedPaging
     **/
    @ApiModelProperty(value = "是否需要分页  默认需要")


    public SupervisionCourseQueryParam sortName(String sortName) {
        this.sortName = sortName;
        return this;
    }

    /**
     * 排序字段名称
     *
     * @return sortName
     **/
    @ApiModelProperty(value = "排序字段名称")


    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SupervisionCourseQueryParam supervisionCourseQueryParam = (SupervisionCourseQueryParam) o;
        return Objects.equals(this.page, supervisionCourseQueryParam.page) &&
                Objects.equals(this.pageSize, supervisionCourseQueryParam.pageSize) &&
                Objects.equals(this.collegeId, supervisionCourseQueryParam.collegeId) &&
                Objects.equals(this.courseId, supervisionCourseQueryParam.courseId) &&
                Objects.equals(this.teacherNameOrNo, supervisionCourseQueryParam.teacherNameOrNo) &&
                Objects.equals(this.courseNameOrTeacherName, supervisionCourseQueryParam.courseNameOrTeacherName) &&
                Objects.equals(this.nowTime, supervisionCourseQueryParam.nowTime) &&
                Objects.equals(this.live, supervisionCourseQueryParam.live) &&
                Objects.equals(this.pagingEnabled, supervisionCourseQueryParam.pagingEnabled) &&
                Objects.equals(this.sortName, supervisionCourseQueryParam.sortName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, pageSize, collegeId, courseId,
                teacherNameOrNo, courseNameOrTeacherName, nowTime, live, pagingEnabled, sortName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SupervisionCourseQueryParam {\n");

        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    teacherNameOrNo: ").append(toIndentedString(teacherNameOrNo)).append("\n");
        sb.append("    courseNameOrTeacherName: ").append(toIndentedString(courseNameOrTeacherName)).append("\n");
        sb.append("    nowTime: ").append(toIndentedString(nowTime)).append("\n");
        sb.append("    live: ").append(toIndentedString(live)).append("\n");
        sb.append("    isNeedPaging: ").append(toIndentedString(pagingEnabled)).append("\n");
        sb.append("    sortName: ").append(toIndentedString(sortName)).append("\n");
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

