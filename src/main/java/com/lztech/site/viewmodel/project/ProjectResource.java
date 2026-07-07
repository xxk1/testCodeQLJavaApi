package com.lztech.site.viewmodel.project;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Objects;

/**
 * ProjectResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-11-05T01:57:20.762Z")


public class ProjectResource {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("classificationId")
    private String classificationId = null;

    @JsonProperty("classificationName")
    private String classificationName = null;

    @JsonProperty("projectCode")
    private String projectCode = null;

    @JsonProperty("projectName")
    private String projectName = null;

    @JsonProperty("projectDescription")
    private String projectDescription = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("courseCode")
    private String courseCode = null;

    @JsonProperty("standardDuration")
    private Double standardDuration = null;

    @JsonProperty("projectTypeValue")
    private Integer projectTypeValue = null;

    @JsonProperty("projectTypeName")
    private String projectTypeName = null;

    @JsonProperty("projectClaimValue")
    private Integer projectClaimValue = null;

    @JsonProperty("projectClaimName")
    private String projectClaimName = null;

    @JsonProperty("projectGenreValue")
    private Integer projectGenreValue = null;

    @JsonProperty("projectGenreName")
    private String projectGenreName = null;

    @JsonProperty("projectCategoryValue")
    private Integer projectCategoryValue = null;

    @JsonProperty("projectCategoryName")
    private String projectCategoryName = null;

    @JsonProperty("whetherAssociateCoursesValue")
    private Integer whetherAssociateCoursesValue = null;

    @JsonProperty("whetherAssociateCoursesName")
    private String whetherAssociateCoursesName = null;

    public ProjectResource id(String id) {
        this.id = id;
        return this;
    }

    /**
     * 项目id
     *
     * @return id
     **/
    @ApiModelProperty(value = "项目id")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProjectResource classificationId(String classificationId) {
        this.classificationId = classificationId;
        return this;
    }

    /**
     * 项目分类id
     *
     * @return classificationId
     **/
    @ApiModelProperty(value = "项目分类id")


    public String getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(String classificationId) {
        this.classificationId = classificationId;
    }

    public ProjectResource classificationName(String classificationName) {
        this.classificationName = classificationName;
        return this;
    }

    /**
     * 项目分类名称
     *
     * @return classificationName
     **/
    @ApiModelProperty(value = "项目分类名称")


    public String getClassificationName() {
        return classificationName;
    }

    public void setClassificationName(String classificationName) {
        this.classificationName = classificationName;
    }

    public ProjectResource projectCode(String projectCode) {
        this.projectCode = projectCode;
        return this;
    }

    /**
     * 项目编号
     *
     * @return projectCode
     **/
    @ApiModelProperty(value = "项目编号")


    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public ProjectResource projectName(String projectName) {
        this.projectName = projectName;
        return this;
    }

    /**
     * 项目名称
     *
     * @return projectName
     **/
    @ApiModelProperty(value = "项目名称")


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public ProjectResource projectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
        return this;
    }

    /**
     * 项目内容
     *
     * @return projectDescription
     **/
    @ApiModelProperty(value = "项目内容")


    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public ProjectResource courseId(String courseId) {
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

    public ProjectResource courseName(String courseName) {
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

    public ProjectResource courseCode(String courseCode) {
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

    public ProjectResource standardDuration(Double standardDuration) {
        this.standardDuration = standardDuration;
        return this;
    }

    /**
     * 项目标准时长 生成之后改为Double
     *
     * @return standardDuration
     **/
    @ApiModelProperty(value = "项目标准时长 生成之后改为Double")

    @Valid

    public Double getStandardDuration() {
        return standardDuration;
    }

    public void setStandardDuration(Double standardDuration) {
        this.standardDuration = standardDuration;
    }

    public ProjectResource projectTypeValue(Integer projectTypeValue) {
        this.projectTypeValue = projectTypeValue;
        return this;
    }

    /**
     * 项目种类值
     *
     * @return projectTypeValue
     **/
    @ApiModelProperty(value = "项目种类值")


    public Integer getProjectTypeValue() {
        return projectTypeValue;
    }

    public void setProjectTypeValue(Integer projectTypeValue) {
        this.projectTypeValue = projectTypeValue;
    }

    public ProjectResource projectTypeName(String projectTypeName) {
        this.projectTypeName = projectTypeName;
        return this;
    }

    /**
     * 项目种类名称
     *
     * @return projectTypeName
     **/
    @ApiModelProperty(value = "项目种类名称")


    public String getProjectTypeName() {
        return projectTypeName;
    }

    public void setProjectTypeName(String projectTypeName) {
        this.projectTypeName = projectTypeName;
    }

    public ProjectResource projectClaimValue(Integer projectClaimValue) {
        this.projectClaimValue = projectClaimValue;
        return this;
    }

    /**
     * 项目要求值
     *
     * @return projectClaimValue
     **/
    @ApiModelProperty(value = "项目要求值")


    public Integer getProjectClaimValue() {
        return projectClaimValue;
    }

    public void setProjectClaimValue(Integer projectClaimValue) {
        this.projectClaimValue = projectClaimValue;
    }

    public ProjectResource projectClaimName(String projectClaimName) {
        this.projectClaimName = projectClaimName;
        return this;
    }

    /**
     * 项目要求名称
     *
     * @return projectClaimName
     **/
    @ApiModelProperty(value = "项目要求名称")


    public String getProjectClaimName() {
        return projectClaimName;
    }

    public void setProjectClaimName(String projectClaimName) {
        this.projectClaimName = projectClaimName;
    }

    public ProjectResource projectGenreValue(Integer projectGenreValue) {
        this.projectGenreValue = projectGenreValue;
        return this;
    }

    /**
     * 项目类型值
     *
     * @return projectGenreValue
     **/
    @ApiModelProperty(value = "项目类型值")


    public Integer getProjectGenreValue() {
        return projectGenreValue;
    }

    public void setProjectGenreValue(Integer projectGenreValue) {
        this.projectGenreValue = projectGenreValue;
    }

    public ProjectResource projectGenreName(String projectGenreName) {
        this.projectGenreName = projectGenreName;
        return this;
    }

    /**
     * 项目类型名称
     *
     * @return projectGenreName
     **/
    @ApiModelProperty(value = "项目类型名称")


    public String getProjectGenreName() {
        return projectGenreName;
    }

    public void setProjectGenreName(String projectGenreName) {
        this.projectGenreName = projectGenreName;
    }

    public ProjectResource projectCategoryValue(Integer projectCategoryValue) {
        this.projectCategoryValue = projectCategoryValue;
        return this;
    }

    /**
     * 项目类别值
     *
     * @return projectCategoryValue
     **/
    @ApiModelProperty(value = "项目类别值")


    public Integer getProjectCategoryValue() {
        return projectCategoryValue;
    }

    public void setProjectCategoryValue(Integer projectCategoryValue) {
        this.projectCategoryValue = projectCategoryValue;
    }

    public ProjectResource projectCategoryName(String projectCategoryName) {
        this.projectCategoryName = projectCategoryName;
        return this;
    }

    /**
     * 项目类别名称
     *
     * @return projectCategoryName
     **/
    @ApiModelProperty(value = "项目类别名称")


    public String getProjectCategoryName() {
        return projectCategoryName;
    }

    public void setProjectCategoryName(String projectCategoryName) {
        this.projectCategoryName = projectCategoryName;
    }

    public ProjectResource whetherAssociateCoursesValue(Integer whetherAssociateCoursesValue) {
        this.whetherAssociateCoursesValue = whetherAssociateCoursesValue;
        return this;
    }

    /**
     * 是否关联课程值(0:否，1:是)
     *
     * @return whetherAssociateCoursesValue
     **/
    @ApiModelProperty(value = "是否关联课程值(0:否，1:是)")


    public Integer getWhetherAssociateCoursesValue() {
        return whetherAssociateCoursesValue;
    }

    public void setWhetherAssociateCoursesValue(Integer whetherAssociateCoursesValue) {
        this.whetherAssociateCoursesValue = whetherAssociateCoursesValue;
    }

    public ProjectResource whetherAssociateCoursesName(String whetherAssociateCoursesName) {
        this.whetherAssociateCoursesName = whetherAssociateCoursesName;
        return this;
    }

    /**
     * 是否关联课程名称(0:否，1:是)
     *
     * @return whetherAssociateCoursesName
     **/
    @ApiModelProperty(value = "是否关联课程名称(0:否，1:是)")


    public String getWhetherAssociateCoursesName() {
        return whetherAssociateCoursesName;
    }

    public void setWhetherAssociateCoursesName(String whetherAssociateCoursesName) {
        this.whetherAssociateCoursesName = whetherAssociateCoursesName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProjectResource projectResource = (ProjectResource) o;
        return Objects.equals(this.id, projectResource.id) &&
                Objects.equals(this.classificationId, projectResource.classificationId) &&
                Objects.equals(this.classificationName, projectResource.classificationName) &&
                Objects.equals(this.projectCode, projectResource.projectCode) &&
                Objects.equals(this.projectName, projectResource.projectName) &&
                Objects.equals(this.projectDescription, projectResource.projectDescription) &&
                Objects.equals(this.courseId, projectResource.courseId) &&
                Objects.equals(this.courseName, projectResource.courseName) &&
                Objects.equals(this.courseCode, projectResource.courseCode) &&
                Objects.equals(this.standardDuration, projectResource.standardDuration) &&
                Objects.equals(this.projectTypeValue, projectResource.projectTypeValue) &&
                Objects.equals(this.projectTypeName, projectResource.projectTypeName) &&
                Objects.equals(this.projectClaimValue, projectResource.projectClaimValue) &&
                Objects.equals(this.projectClaimName, projectResource.projectClaimName) &&
                Objects.equals(this.projectGenreValue, projectResource.projectGenreValue) &&
                Objects.equals(this.projectGenreName, projectResource.projectGenreName) &&
                Objects.equals(this.projectCategoryValue, projectResource.projectCategoryValue) &&
                Objects.equals(this.projectCategoryName, projectResource.projectCategoryName) &&
                Objects.equals(this.whetherAssociateCoursesValue, projectResource.whetherAssociateCoursesValue) &&
                Objects.equals(this.whetherAssociateCoursesName, projectResource.whetherAssociateCoursesName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, classificationId, classificationName, projectCode, projectName,
                projectDescription, courseId, courseName, courseCode, standardDuration, projectTypeValue,
                projectTypeName, projectClaimValue, projectClaimName, projectGenreValue, projectGenreName,
                projectCategoryValue, projectCategoryName, whetherAssociateCoursesValue, whetherAssociateCoursesName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ProjectResource {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    classificationId: ").append(toIndentedString(classificationId)).append("\n");
        sb.append("    classificationName: ").append(toIndentedString(classificationName)).append("\n");
        sb.append("    projectCode: ").append(toIndentedString(projectCode)).append("\n");
        sb.append("    projectName: ").append(toIndentedString(projectName)).append("\n");
        sb.append("    projectDescription: ").append(toIndentedString(projectDescription)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
        sb.append("    standardDuration: ").append(toIndentedString(standardDuration)).append("\n");
        sb.append("    projectTypeValue: ").append(toIndentedString(projectTypeValue)).append("\n");
        sb.append("    projectTypeName: ").append(toIndentedString(projectTypeName)).append("\n");
        sb.append("    projectClaimValue: ").append(toIndentedString(projectClaimValue)).append("\n");
        sb.append("    projectClaimName: ").append(toIndentedString(projectClaimName)).append("\n");
        sb.append("    projectGenreValue: ").append(toIndentedString(projectGenreValue)).append("\n");
        sb.append("    projectGenreName: ").append(toIndentedString(projectGenreName)).append("\n");
        sb.append("    projectCategoryValue: ").append(toIndentedString(projectCategoryValue)).append("\n");
        sb.append("    projectCategoryName: ").append(toIndentedString(projectCategoryName)).append("\n");
        sb.append("    whetherAssociateCoursesValue: ").append(toIndentedString(whetherAssociateCoursesValue)).append("\n");
        sb.append("    whetherAssociateCoursesName: ").append(toIndentedString(whetherAssociateCoursesName)).append("\n");
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

