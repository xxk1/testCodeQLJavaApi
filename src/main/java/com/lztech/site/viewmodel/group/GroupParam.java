package com.lztech.site.viewmodel.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * GroupParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-02-25T09:36:08.807Z")


public class GroupParam {
    @JsonProperty("page")
    private Integer page = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    @JsonProperty("schoolYear")
    private String schoolYear = null;

    @JsonProperty("term")
    private Integer term = null;

    @JsonProperty("groupName")
    private String groupName = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("source")
    private Integer source = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("courseNameOrCode")
    private String courseNameOrCode = null;

    @JsonProperty("teacherNameOrCode")
    private String teacherNameOrCode = null;

    @JsonProperty("courseCategoryId")
    private String courseCategoryId = null;

    @JsonProperty("publicStatus")
    private Boolean publicStatus = null;

    @JsonProperty("groupIds")
    @Valid
    private List<String> groupIds = null;

    public GroupParam page(Integer page) {
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

    public GroupParam pageSize(Integer pageSize) {
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

    public GroupParam schoolYear(String schoolYear) {
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

    public GroupParam term(Integer term) {
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

    public GroupParam groupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    /**
     * 组名
     *
     * @return groupName
     **/
    @ApiModelProperty(value = "组名")


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public GroupParam courseName(String courseName) {
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

    public GroupParam source(Integer source) {
        this.source = source;
        return this;
    }

    /**
     * 数据来源 0：数据对接，1：手动添加
     *
     * @return source
     **/
    @ApiModelProperty(value = "数据来源 0：数据对接，1：手动添加")


    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public GroupParam collegeId(String collegeId) {
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

    public GroupParam courseNameOrCode(String courseNameOrCode) {
        this.courseNameOrCode = courseNameOrCode;
        return this;
    }

    /**
     * 课程名称或编号
     *
     * @return courseNameOrCode
     **/
    @ApiModelProperty(value = "课程名称或编号")


    public String getCourseNameOrCode() {
        return courseNameOrCode;
    }

    public void setCourseNameOrCode(String courseNameOrCode) {
        this.courseNameOrCode = courseNameOrCode;
    }

    public GroupParam teacherNameOrCode(String teacherNameOrCode) {
        this.teacherNameOrCode = teacherNameOrCode;
        return this;
    }

    /**
     * 教师姓名或编号
     *
     * @return teacherNameOrCode
     **/
    @ApiModelProperty(value = "教师姓名或编号")


    public String getTeacherNameOrCode() {
        return teacherNameOrCode;
    }

    public void setTeacherNameOrCode(String teacherNameOrCode) {
        this.teacherNameOrCode = teacherNameOrCode;
    }

    public GroupParam courseCategoryId(String courseCategoryId) {
        this.courseCategoryId = courseCategoryId;
        return this;
    }

    /**
     * 课程类别类型id
     *
     * @return courseCategoryId
     **/
    @ApiModelProperty(value = "课程类别类型id")


    public String getCourseCategoryId() {
        return courseCategoryId;
    }

    public void setCourseCategoryId(String courseCategoryId) {
        this.courseCategoryId = courseCategoryId;
    }

    public GroupParam publicStatus(Boolean publicStatus) {
        this.publicStatus = publicStatus;
        return this;
    }

    /**
     * 公开状态（true：公开、false：不公开）
     *
     * @return publicStatus
     **/
    @ApiModelProperty(value = "公开状态（true：公开、false：不公开）")


    public Boolean isPublicStatus() {
        return publicStatus;
    }

    public void setPublicStatus(Boolean publicStatus) {
        this.publicStatus = publicStatus;
    }

    public GroupParam groupIds(List<String> groupIds) {
        this.groupIds = groupIds;
        return this;
    }

    public GroupParam addGroupIdsItem(String groupIdsItem) {
        if (this.groupIds == null) {
            this.groupIds = new ArrayList<String>();
        }
        this.groupIds.add(groupIdsItem);
        return this;
    }

    /**
     * 教学班id
     *
     * @return groupIds
     **/
    @ApiModelProperty(value = "教学班id")


    public List<String> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<String> groupIds) {
        this.groupIds = groupIds;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GroupParam groupParam = (GroupParam) o;
        return Objects.equals(this.page, groupParam.page) &&
                Objects.equals(this.pageSize, groupParam.pageSize) &&
                Objects.equals(this.schoolYear, groupParam.schoolYear) &&
                Objects.equals(this.term, groupParam.term) &&
                Objects.equals(this.groupName, groupParam.groupName) &&
                Objects.equals(this.courseName, groupParam.courseName) &&
                Objects.equals(this.source, groupParam.source) &&
                Objects.equals(this.collegeId, groupParam.collegeId) &&
                Objects.equals(this.courseNameOrCode, groupParam.courseNameOrCode) &&
                Objects.equals(this.teacherNameOrCode, groupParam.teacherNameOrCode) &&
                Objects.equals(this.courseCategoryId, groupParam.courseCategoryId) &&
                Objects.equals(this.publicStatus, groupParam.publicStatus) &&
                Objects.equals(this.groupIds, groupParam.groupIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, pageSize, schoolYear, term, groupName, courseName, source,
                collegeId, courseNameOrCode, teacherNameOrCode, courseCategoryId, publicStatus, groupIds);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GroupParam {\n");

        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
        sb.append("    term: ").append(toIndentedString(term)).append("\n");
        sb.append("    groupName: ").append(toIndentedString(groupName)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    source: ").append(toIndentedString(source)).append("\n");
        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
        sb.append("    courseNameOrCode: ").append(toIndentedString(courseNameOrCode)).append("\n");
        sb.append("    teacherNameOrCode: ").append(toIndentedString(teacherNameOrCode)).append("\n");
        sb.append("    courseCategoryId: ").append(toIndentedString(courseCategoryId)).append("\n");
        sb.append("    publicStatus: ").append(toIndentedString(publicStatus)).append("\n");
        sb.append("    groupIds: ").append(toIndentedString(groupIds)).append("\n");
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

