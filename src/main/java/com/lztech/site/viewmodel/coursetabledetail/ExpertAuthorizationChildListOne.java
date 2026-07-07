package com.lztech.site.viewmodel.coursetabledetail;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * ExpertAuthorizationChildListOne
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-01-12T02:22:49.339Z")


public class ExpertAuthorizationChildListOne {
    @JsonProperty("courseCode")
    private String courseCode = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("schoolYear")
    private String schoolYear = null;

    @JsonProperty("term")
    private Integer term = null;

    @JsonProperty("nodeId")
    private String nodeId = null;

    @JsonProperty("nodeLevel")
    private Integer nodeLevel = null;

    @JsonProperty("parentId")
    private String parentId = null;

    @JsonProperty("childList")
    @Valid
    private List<ExpertAuthorizationChildListTwo> childList = null;

    public ExpertAuthorizationChildListOne courseCode(String courseCode) {
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

    public ExpertAuthorizationChildListOne courseName(String courseName) {
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

    public ExpertAuthorizationChildListOne schoolYear(String schoolYear) {
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

    public ExpertAuthorizationChildListOne term(Integer term) {
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

    public ExpertAuthorizationChildListOne nodeId(String nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    /**
     * 节点ID（课程编号）
     *
     * @return nodeId
     **/
    @ApiModelProperty(value = "节点ID（课程编号）")


    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public ExpertAuthorizationChildListOne nodeLevel(Integer nodeLevel) {
        this.nodeLevel = nodeLevel;
        return this;
    }

    /**
     * 节点层级
     *
     * @return nodeLevel
     **/
    @ApiModelProperty(value = "节点层级")


    public Integer getNodeLevel() {
        return nodeLevel;
    }

    public void setNodeLevel(Integer nodeLevel) {
        this.nodeLevel = nodeLevel;
    }

    public ExpertAuthorizationChildListOne parentId(String parentId) {
        this.parentId = parentId;
        return this;
    }

    /**
     * 节点父级ID
     *
     * @return parentId
     **/
    @ApiModelProperty(value = "节点父级ID")


    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public ExpertAuthorizationChildListOne childList(List<ExpertAuthorizationChildListTwo> childList) {
        this.childList = childList;
        return this;
    }

    public ExpertAuthorizationChildListOne addChildListItem(ExpertAuthorizationChildListTwo childListItem) {
        if (this.childList == null) {
            this.childList = new ArrayList<ExpertAuthorizationChildListTwo>();
        }
        this.childList.add(childListItem);
        return this;
    }

    /**
     * Get childList
     *
     * @return childList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<ExpertAuthorizationChildListTwo> getChildList() {
        return childList;
    }

    public void setChildList(List<ExpertAuthorizationChildListTwo> childList) {
        this.childList = childList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExpertAuthorizationChildListOne expertAuthorizationChildListOne = (ExpertAuthorizationChildListOne) o;
        return Objects.equals(this.courseCode, expertAuthorizationChildListOne.courseCode) &&
                Objects.equals(this.courseName, expertAuthorizationChildListOne.courseName) &&
                Objects.equals(this.schoolYear, expertAuthorizationChildListOne.schoolYear) &&
                Objects.equals(this.term, expertAuthorizationChildListOne.term) &&
                Objects.equals(this.nodeId, expertAuthorizationChildListOne.nodeId) &&
                Objects.equals(this.nodeLevel, expertAuthorizationChildListOne.nodeLevel) &&
                Objects.equals(this.parentId, expertAuthorizationChildListOne.parentId) &&
                Objects.equals(this.childList, expertAuthorizationChildListOne.childList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseCode, courseName, schoolYear, term, nodeId, nodeLevel, parentId, childList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExpertAuthorizationChildListOne {\n");

        sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
        sb.append("    term: ").append(toIndentedString(term)).append("\n");
        sb.append("    nodeId: ").append(toIndentedString(nodeId)).append("\n");
        sb.append("    nodeLevel: ").append(toIndentedString(nodeLevel)).append("\n");
        sb.append("    parentId: ").append(toIndentedString(parentId)).append("\n");
        sb.append("    childList: ").append(toIndentedString(childList)).append("\n");
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

