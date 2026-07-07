package com.lztech.site.viewmodel.superviseevaluation.v2;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;

/**
 * SupervisionCourseQueryParamV2
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-08-06T16:42:35.739+08:00")

public class SupervisionCourseQueryParamV2 {
    @JsonProperty("page")
    private Integer page = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("collegeIds")
    private String collegeIds = null;

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
    private String term = null;

    @JsonProperty("isDistinguishCourseStudentType")
    private String isDistinguishCourseStudentType = null;

    @JsonProperty("courseStudentTypes")
    private String courseStudentTypes = null;

    @JsonProperty("isNeedFilterPoliticalCourse")
    private Boolean isNeedFilterPoliticalCourse = null;

    @JsonProperty("superviseCollegeType")
    private Integer superviseCollegeType = null;

    @JsonProperty("onlyGraduateCourse")
    private Integer onlyGraduateCourse = null;

    @JsonProperty("sourceDataSource")
    private String sourceDataSource = null;

    @JsonProperty("evaluationStatusType")
    private Integer evaluationStatusType;

    @JsonProperty("evaluatedDataIdList")
    private List<String> evaluatedDataIdList;

    public List<String> getEvaluatedDataIdList() {
        return evaluatedDataIdList;
    }

    public void setEvaluatedDataIdList(List<String> evaluatedDataIdList) {
        this.evaluatedDataIdList = evaluatedDataIdList;
    }

    public SupervisionCourseQueryParamV2 evaluationStatusType(Integer evaluationStatusType) {
        this.evaluationStatusType = evaluationStatusType;
        return this;
    }

    /**
     * 评价状态类型 0: 课程无人评价；1：教师无人评价 2：我已评价 3：我未评价
     * @return evaluationStatusType
     **/
    @ApiModelProperty(value = "评价状态类型 0: 课程无人评价；1：教师无人评价 2：我已评价 3：我未评价")


    public Integer getEvaluationStatusType() {
        return evaluationStatusType;
    }

    public void setEvaluationStatusType(Integer evaluationStatusType) {
        this.evaluationStatusType = evaluationStatusType;
    }

    public SupervisionCourseQueryParamV2 page(Integer page) {
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

    public SupervisionCourseQueryParamV2 pageSize(Integer pageSize) {
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

    public SupervisionCourseQueryParamV2 collegeId(String collegeId) {
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

    public SupervisionCourseQueryParamV2 collegeIds(String collegeIds) {
        this.collegeIds = collegeIds;
        return this;
    }

    /**
     * 学院ids
     *
     * @return collegeIds
     **/
    @ApiModelProperty(value = "学院ids")


    public String getCollegeIds() {
        return collegeIds;
    }

    public void setCollegeIds(String collegeIds) {
        this.collegeIds = collegeIds;
    }

    public SupervisionCourseQueryParamV2 courseId(String courseId) {
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

    public SupervisionCourseQueryParamV2 teacherNameOrNo(String teacherNameOrNo) {
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

    public SupervisionCourseQueryParamV2 courseNameOrTeacherName(String courseNameOrTeacherName) {
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

    public SupervisionCourseQueryParamV2 nowTime(String nowTime) {
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

    public SupervisionCourseQueryParamV2 live(Integer live) {
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

    public SupervisionCourseQueryParamV2 pagingEnabled(Boolean pagingEnabled) {
        this.pagingEnabled = pagingEnabled;
        return this;
    }

    /**
     * 是否需要分页  默认需要
     *
     * @return pagingEnabled
     **/
    @ApiModelProperty(value = "是否需要分页  默认需要")


    public Boolean isPagingEnabled() {
        return pagingEnabled;
    }

    public void setPagingEnabled(Boolean pagingEnabled) {
        this.pagingEnabled = pagingEnabled;
    }

    public SupervisionCourseQueryParamV2 sortName(String sortName) {
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

    public SupervisionCourseQueryParamV2 schoolYear(String schoolYear) {
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

    public SupervisionCourseQueryParamV2 term(String term) {
        this.term = term;
        return this;
    }

    /**
     * 学期
     *
     * @return term
     **/
    @ApiModelProperty(value = "学期")


    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public SupervisionCourseQueryParamV2 isDistinguishCourseStudentType(String isDistinguishCourseStudentType) {
        this.isDistinguishCourseStudentType = isDistinguishCourseStudentType;
        return this;
    }

    /**
     * 是否需要区分授课学生类型
     *
     * @return isDistinguishCourseStudentType
     **/
    @ApiModelProperty(value = "是否需要区分授课学生类型")


    public String getIsDistinguishCourseStudentType() {
        return isDistinguishCourseStudentType;
    }

    public void setIsDistinguishCourseStudentType(String isDistinguishCourseStudentType) {
        this.isDistinguishCourseStudentType = isDistinguishCourseStudentType;
    }

    public SupervisionCourseQueryParamV2 courseStudentTypes(String courseStudentTypes) {
        this.courseStudentTypes = courseStudentTypes;
        return this;
    }

    /**
     * 授课学生类型，多个使用逗号隔开
     *
     * @return courseStudentTypes
     **/
    @ApiModelProperty(value = "授课学生类型，多个使用逗号隔开")


    public String getCourseStudentTypes() {
        return courseStudentTypes;
    }

    public void setCourseStudentTypes(String courseStudentTypes) {
        this.courseStudentTypes = courseStudentTypes;
    }

    public SupervisionCourseQueryParamV2 isNeedFilterPoliticalCourse(Boolean isNeedFilterPoliticalCourse) {
        this.isNeedFilterPoliticalCourse = isNeedFilterPoliticalCourse;
        return this;
    }

    /**
     * 是否需要过思政课
     *
     * @return isNeedFilterPoliticalCourse
     **/
    @ApiModelProperty(value = "是否需要过思政课")


    public Boolean isIsNeedFilterPoliticalCourse() {
        return isNeedFilterPoliticalCourse;
    }

    public void setIsNeedFilterPoliticalCourse(Boolean isNeedFilterPoliticalCourse) {
        this.isNeedFilterPoliticalCourse = isNeedFilterPoliticalCourse;
    }

    public SupervisionCourseQueryParamV2 superviseCollegeType(Integer superviseCollegeType) {
        this.superviseCollegeType = superviseCollegeType;
        return this;
    }

    /**
     * 督导听课查询学类型 0：授课教师学院；1：开课学院
     *
     * @return superviseCollegeType
     **/
    @ApiModelProperty(value = "督导听课查询学类型 0：授课教师学院；1：开课学院")


    public Integer getSuperviseCollegeType() {
        return superviseCollegeType;
    }

    public void setSuperviseCollegeType(Integer superviseCollegeType) {
        this.superviseCollegeType = superviseCollegeType;
    }

    public SupervisionCourseQueryParamV2 onlyGraduateCourse(Integer onlyGraduateCourse) {
        this.onlyGraduateCourse = onlyGraduateCourse;
        return this;
    }

    /**
     * 是否研究生（1-是，其他不作为查询条件）
     *
     * @return onlyGraduateCourse
     **/
    @ApiModelProperty(value = "是否研究生（1-是，其他不作为查询条件）")


    public Integer getOnlyGraduateCourse() {
        return onlyGraduateCourse;
    }

    public void setOnlyGraduateCourse(Integer onlyGraduateCourse) {
        this.onlyGraduateCourse = onlyGraduateCourse;
    }

    public SupervisionCourseQueryParamV2 sourceDataSource(String sourceDataSource) {
        this.sourceDataSource = sourceDataSource;
        return this;
    }

    /**
     * 数据来源
     *
     * @return sourceDataSource
     **/
    @ApiModelProperty(value = "数据来源")


    public String getSourceDataSource() {
        return sourceDataSource;
    }

    public void setSourceDataSource(String sourceDataSource) {
        this.sourceDataSource = sourceDataSource;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SupervisionCourseQueryParamV2 supervisionCourseQueryParamV2 = (SupervisionCourseQueryParamV2) o;
        return Objects.equals(this.page, supervisionCourseQueryParamV2.page) &&
                Objects.equals(this.pageSize, supervisionCourseQueryParamV2.pageSize) &&
                Objects.equals(this.collegeId, supervisionCourseQueryParamV2.collegeId) &&
                Objects.equals(this.collegeIds, supervisionCourseQueryParamV2.collegeIds) &&
                Objects.equals(this.courseId, supervisionCourseQueryParamV2.courseId) &&
                Objects.equals(this.teacherNameOrNo, supervisionCourseQueryParamV2.teacherNameOrNo) &&
                Objects.equals(this.courseNameOrTeacherName, supervisionCourseQueryParamV2.courseNameOrTeacherName) &&
                Objects.equals(this.nowTime, supervisionCourseQueryParamV2.nowTime) &&
                Objects.equals(this.live, supervisionCourseQueryParamV2.live) &&
                Objects.equals(this.pagingEnabled, supervisionCourseQueryParamV2.pagingEnabled) &&
                Objects.equals(this.sortName, supervisionCourseQueryParamV2.sortName) &&
                Objects.equals(this.schoolYear, supervisionCourseQueryParamV2.schoolYear) &&
                Objects.equals(this.term, supervisionCourseQueryParamV2.term) &&
                Objects.equals(this.isDistinguishCourseStudentType, supervisionCourseQueryParamV2.isDistinguishCourseStudentType) &&
                Objects.equals(this.courseStudentTypes, supervisionCourseQueryParamV2.courseStudentTypes) &&
                Objects.equals(this.isNeedFilterPoliticalCourse, supervisionCourseQueryParamV2.isNeedFilterPoliticalCourse) &&
                Objects.equals(this.superviseCollegeType, supervisionCourseQueryParamV2.superviseCollegeType) &&
                Objects.equals(this.onlyGraduateCourse, supervisionCourseQueryParamV2.onlyGraduateCourse) &&
                Objects.equals(this.sourceDataSource, supervisionCourseQueryParamV2.sourceDataSource);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, pageSize, collegeId, collegeIds, courseId, teacherNameOrNo, courseNameOrTeacherName,
                nowTime, live, pagingEnabled, sortName, schoolYear, term, isDistinguishCourseStudentType, courseStudentTypes,
                isNeedFilterPoliticalCourse, superviseCollegeType, onlyGraduateCourse, sourceDataSource);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SupervisionCourseQueryParamV2 {\n");

        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
        sb.append("    collegeIds: ").append(toIndentedString(collegeIds)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    teacherNameOrNo: ").append(toIndentedString(teacherNameOrNo)).append("\n");
        sb.append("    courseNameOrTeacherName: ").append(toIndentedString(courseNameOrTeacherName)).append("\n");
        sb.append("    nowTime: ").append(toIndentedString(nowTime)).append("\n");
        sb.append("    live: ").append(toIndentedString(live)).append("\n");
        sb.append("    pagingEnabled: ").append(toIndentedString(pagingEnabled)).append("\n");
        sb.append("    sortName: ").append(toIndentedString(sortName)).append("\n");
        sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
        sb.append("    term: ").append(toIndentedString(term)).append("\n");
        sb.append("    isDistinguishCourseStudentType: ").append(toIndentedString(isDistinguishCourseStudentType)).append("\n");
        sb.append("    courseStudentTypes: ").append(toIndentedString(courseStudentTypes)).append("\n");
        sb.append("    isNeedFilterPoliticalCourse: ").append(toIndentedString(isNeedFilterPoliticalCourse)).append("\n");
        sb.append("    superviseCollegeType: ").append(toIndentedString(superviseCollegeType)).append("\n");
        sb.append("    onlyGraduateCourse: ").append(toIndentedString(onlyGraduateCourse)).append("\n");
        sb.append("    sourceDataSource: ").append(toIndentedString(sourceDataSource)).append("\n");
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

