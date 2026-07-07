package com.lztech.site.viewmodel.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * GroupVoResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-02-24T07:36:42.078Z")


public class GroupVoResource {
    @JsonProperty("schoolYear")
    private String schoolYear = null;

    @JsonProperty("term")
    private Integer term = null;

    @JsonProperty("groupId")
    private String groupId = null;

    @JsonProperty("groupNo")
    private String groupNo = null;

    @JsonProperty("groupName")
    private String groupName = null;

    @JsonProperty("teacherName")
    private String teacherName = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("groupMembersNum")
    private Integer groupMembersNum = null;

    @JsonProperty("source")
    private String source = null;

    @JsonProperty("classCompositionName")
    private String classCompositionName = null;

    @JsonProperty("modifierName")
    private String modifierName = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    @JsonProperty("courseCategoryName")
    private String courseCategoryName = null;

    @JsonProperty("publicStatus")
    private Boolean publicStatus = null;


    @JsonProperty("schoolYearTermNickName")
    private String schoolYearTermNickName = null;

    public GroupVoResource schoolYearTermNickName(String schoolYearTermNickName) {
        this.schoolYearTermNickName = schoolYearTermNickName;
        return this;
    }

    /**
     * 学年学期昵称
     *
     * @return schoolYearTermNickName
     **/
    @ApiModelProperty(value = "学年学期昵称")


    public String getSchoolYearTermNickName() {
        return schoolYearTermNickName;
    }

    public void setSchoolYearTermNickName(String schoolYearTermNickName) {
        this.schoolYearTermNickName = schoolYearTermNickName;
    }

    public GroupVoResource schoolYear(String schoolYear) {
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

    public GroupVoResource term(Integer term) {
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

    public GroupVoResource groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    /**
     * 组id
     *
     * @return groupId
     **/
    @ApiModelProperty(value = "组id")


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public GroupVoResource groupNo(String groupNo) {
        this.groupNo = groupNo;
        return this;
    }

    /**
     * 组编号
     *
     * @return groupNo
     **/
    @ApiModelProperty(value = "组编号")


    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    public GroupVoResource groupName(String groupName) {
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

    public GroupVoResource teacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    /**
     * 教师姓名/工号，多个用“、”隔开
     *
     * @return teacherName
     **/
    @ApiModelProperty(value = "教师姓名/工号，多个用“、”隔开")


    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public GroupVoResource courseName(String courseName) {
        this.courseName = courseName;
        return this;
    }

    /**
     * 课程名称（课程编号）
     *
     * @return courseName
     **/
    @ApiModelProperty(value = "课程名称（课程编号）")


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public GroupVoResource groupMembersNum(Integer groupMembersNum) {
        this.groupMembersNum = groupMembersNum;
        return this;
    }

    /**
     * 组成员数量
     *
     * @return groupMembersNum
     **/
    @ApiModelProperty(value = "组成员数量")


    public Integer getGroupMembersNum() {
        return groupMembersNum;
    }

    public void setGroupMembersNum(Integer groupMembersNum) {
        this.groupMembersNum = groupMembersNum;
    }

    public GroupVoResource source(String source) {
        this.source = source;
        return this;
    }

    /**
     * 数据来源
     *
     * @return source
     **/
    @ApiModelProperty(value = "数据来源")


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public GroupVoResource classCompositionName(String classCompositionName) {
        this.classCompositionName = classCompositionName;
        return this;
    }

    /**
     * 班级组成
     *
     * @return classCompositionName
     **/
    @ApiModelProperty(value = "班级组成")


    public String getClassCompositionName() {
        return classCompositionName;
    }

    public void setClassCompositionName(String classCompositionName) {
        this.classCompositionName = classCompositionName;
    }

    public GroupVoResource modifierName(String modifierName) {
        this.modifierName = modifierName;
        return this;
    }

    /**
     * 修改人
     *
     * @return modifierName
     **/
    @ApiModelProperty(value = "修改人")


    public String getModifierName() {
        return modifierName;
    }

    public void setModifierName(String modifierName) {
        this.modifierName = modifierName;
    }

    public GroupVoResource collegeName(String collegeName) {
        this.collegeName = collegeName;
        return this;
    }

    /**
     * 开课学院名称
     *
     * @return collegeName
     **/
    @ApiModelProperty(value = "开课学院名称")


    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public GroupVoResource courseCategoryName(String courseCategoryName) {
        this.courseCategoryName = courseCategoryName;
        return this;
    }

    /**
     * 课程类别名称
     *
     * @return courseCategoryName
     **/
    @ApiModelProperty(value = "课程类别名称")


    public String getCourseCategoryName() {
        return courseCategoryName;
    }

    public void setCourseCategoryName(String courseCategoryName) {
        this.courseCategoryName = courseCategoryName;
    }

    public GroupVoResource publicStatus(Boolean publicStatus) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GroupVoResource groupVoResource = (GroupVoResource) o;
        return Objects.equals(this.schoolYear, groupVoResource.schoolYear) &&
                Objects.equals(this.term, groupVoResource.term) &&
                Objects.equals(this.schoolYearTermNickName, groupVoResource.schoolYearTermNickName) &&
                Objects.equals(this.groupId, groupVoResource.groupId) &&
                Objects.equals(this.groupNo, groupVoResource.groupNo) &&
                Objects.equals(this.groupName, groupVoResource.groupName) &&
                Objects.equals(this.teacherName, groupVoResource.teacherName) &&
                Objects.equals(this.courseName, groupVoResource.courseName) &&
                Objects.equals(this.groupMembersNum, groupVoResource.groupMembersNum) &&
                Objects.equals(this.source, groupVoResource.source) &&
                Objects.equals(this.classCompositionName, groupVoResource.classCompositionName) &&
                Objects.equals(this.modifierName, groupVoResource.modifierName) &&
                Objects.equals(this.collegeName, groupVoResource.collegeName) &&
                Objects.equals(this.courseCategoryName, groupVoResource.courseCategoryName) &&
                Objects.equals(this.publicStatus, groupVoResource.publicStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schoolYear, term, schoolYearTermNickName, groupId, groupNo, groupName, teacherName, courseName, groupMembersNum,
                source, classCompositionName, modifierName, collegeName, courseCategoryName, publicStatus);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GroupVoResource {\n");

        sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
        sb.append("    term: ").append(toIndentedString(term)).append("\n");
        sb.append("    schoolYearTermNickName: ").append(toIndentedString(schoolYearTermNickName)).append("\n");
        sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
        sb.append("    groupNo: ").append(toIndentedString(groupNo)).append("\n");
        sb.append("    groupName: ").append(toIndentedString(groupName)).append("\n");
        sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    groupMembersNum: ").append(toIndentedString(groupMembersNum)).append("\n");
        sb.append("    source: ").append(toIndentedString(source)).append("\n");
        sb.append("    classCompositionName: ").append(toIndentedString(classCompositionName)).append("\n");
        sb.append("    modifierName: ").append(toIndentedString(modifierName)).append("\n");
        sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
        sb.append("    courseCategoryName: ").append(toIndentedString(courseCategoryName)).append("\n");
        sb.append("    publicStatus: ").append(toIndentedString(publicStatus)).append("\n");
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

