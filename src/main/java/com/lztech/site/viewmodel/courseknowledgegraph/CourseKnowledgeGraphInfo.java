package com.lztech.site.viewmodel.courseknowledgegraph;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseKnowledgeGraphInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-03-04T11:17:57.580Z")


public class CourseKnowledgeGraphInfo {
    @JsonProperty("creatorId")
    private String creatorId = null;

    @JsonProperty("creatorName")
    private String creatorName = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("schoolYear")
    private String schoolYear = null;

    @JsonProperty("term")
    private Integer term = null;

    @JsonProperty("courseKnowledgeGraphClassVoList")
    @Valid
    private List<ClassVo> courseKnowledgeGraphClassVoList = null;

    public CourseKnowledgeGraphInfo creatorId(String creatorId) {
        this.creatorId = creatorId;
        return this;
    }

    /**
     * 操作人Id
     *
     * @return creatorId
     **/
    @ApiModelProperty(value = "操作人Id")


    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public CourseKnowledgeGraphInfo creatorName(String creatorName) {
        this.creatorName = creatorName;
        return this;
    }

    /**
     * 操作人姓名
     *
     * @return creatorName
     **/
    @ApiModelProperty(value = "操作人姓名")


    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public CourseKnowledgeGraphInfo courseId(String courseId) {
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

    public CourseKnowledgeGraphInfo courseName(String courseName) {
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

    public CourseKnowledgeGraphInfo schoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
        return this;
    }

    /**
     * 开课学年
     *
     * @return schoolYear
     **/
    @ApiModelProperty(value = "开课学年")


    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public CourseKnowledgeGraphInfo term(Integer term) {
        this.term = term;
        return this;
    }

    /**
     * 开课学期
     *
     * @return term
     **/
    @ApiModelProperty(value = "开课学期")


    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public CourseKnowledgeGraphInfo courseKnowledgeGraphClassVoList(List<ClassVo> courseKnowledgeGraphClassVoList) {
        this.courseKnowledgeGraphClassVoList = courseKnowledgeGraphClassVoList;
        return this;
    }

    public CourseKnowledgeGraphInfo addCourseKnowledgeGraphClassVoListItem(ClassVo courseKnowledgeGraphClassVoListItem) {
        if (this.courseKnowledgeGraphClassVoList == null) {
            this.courseKnowledgeGraphClassVoList = new ArrayList<ClassVo>();
        }
        this.courseKnowledgeGraphClassVoList.add(courseKnowledgeGraphClassVoListItem);
        return this;
    }

    /**
     * Get courseKnowledgeGraphClassVoList
     *
     * @return courseKnowledgeGraphClassVoList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<ClassVo> getCourseKnowledgeGraphClassVoList() {
        return courseKnowledgeGraphClassVoList;
    }

    public void setCourseKnowledgeGraphClassVoList(List<ClassVo> courseKnowledgeGraphClassVoList) {
        this.courseKnowledgeGraphClassVoList = courseKnowledgeGraphClassVoList;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseKnowledgeGraphInfo courseKnowledgeGraphInfo = (CourseKnowledgeGraphInfo) o;
        return Objects.equals(this.creatorId, courseKnowledgeGraphInfo.creatorId) &&
                Objects.equals(this.creatorName, courseKnowledgeGraphInfo.creatorName) &&
                Objects.equals(this.courseId, courseKnowledgeGraphInfo.courseId) &&
                Objects.equals(this.courseName, courseKnowledgeGraphInfo.courseName) &&
                Objects.equals(this.schoolYear, courseKnowledgeGraphInfo.schoolYear) &&
                Objects.equals(this.term, courseKnowledgeGraphInfo.term) &&
                Objects.equals(this.courseKnowledgeGraphClassVoList, courseKnowledgeGraphInfo.courseKnowledgeGraphClassVoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creatorId, creatorName, courseId, courseName, schoolYear, term, courseKnowledgeGraphClassVoList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseKnowledgeGraphInfo {\n");

        sb.append("    creatorId: ").append(toIndentedString(creatorId)).append("\n");
        sb.append("    creatorName: ").append(toIndentedString(creatorName)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
        sb.append("    term: ").append(toIndentedString(term)).append("\n");
        sb.append("    courseKnowledgeGraphClassVoList: ").append(toIndentedString(courseKnowledgeGraphClassVoList)).append("\n");
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

