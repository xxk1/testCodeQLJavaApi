package com.lztech.site.viewmodel.project;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ProjectInfoResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-11-04T09:22:43.595Z")


public class ProjectInfoResource {
    @JsonProperty("userId")
    private String userId = null;

    @JsonProperty("userName")
    private String userName = null;

    @JsonProperty("projectCode")
    private String projectCode = null;

    @JsonProperty("projectName")
    private String projectName = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("courseCode")
    private String courseCode = null;

    @JsonProperty("projectTypeValue")
    private Integer projectTypeValue = null;

    @JsonProperty("projectCategoryValue")
    private Integer projectCategoryValue = null;

    @JsonProperty("projectGenreValue")
    private Integer projectGenreValue = null;

    @JsonProperty("projectClaimValue")
    private Integer projectClaimValue = null;

    @JsonProperty("classificationId")
    private String classificationId = null;

    @JsonProperty("classificationName")
    private String classificationName = null;

    @JsonProperty("projectDescription")
    private String projectDescription = null;

    @JsonProperty("whetherAssociateCourses")
    private Integer whetherAssociateCourses = null;

    public ProjectInfoResource userId(String userId) {
        this.userId = userId;
        return this;
    }

    /**
     * 用户id
     *
     * @return userId
     **/
    @ApiModelProperty(value = "用户id")


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ProjectInfoResource userName(String userName) {
        this.userName = userName;
        return this;
    }

    /**
     * 用户姓名
     *
     * @return userName
     **/
    @ApiModelProperty(value = "用户姓名")


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ProjectInfoResource projectCode(String projectCode) {
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

    public ProjectInfoResource projectName(String projectName) {
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

    public ProjectInfoResource courseId(String courseId) {
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

    public ProjectInfoResource courseName(String courseName) {
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

    public ProjectInfoResource courseCode(String courseCode) {
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

    public ProjectInfoResource projectTypeValue(Integer projectTypeValue) {
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

    public ProjectInfoResource projectCategoryValue(Integer projectCategoryValue) {
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

    public ProjectInfoResource projectGenreValue(Integer projectGenreValue) {
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

    public ProjectInfoResource projectClaimValue(Integer projectClaimValue) {
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

    public ProjectInfoResource classificationId(String classificationId) {
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

    public ProjectInfoResource classificationName(String classificationName) {
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

    public ProjectInfoResource projectDescription(String projectDescription) {
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

    public ProjectInfoResource whetherAssociateCourses(Integer whetherAssociateCourses) {
        this.whetherAssociateCourses = whetherAssociateCourses;
        return this;
    }

    /**
     * 是否关联课程(0:否，1:是)
     *
     * @return whetherAssociateCourses
     **/
    @ApiModelProperty(value = "是否关联课程(0:否，1:是)")


    public Integer getWhetherAssociateCourses() {
        return whetherAssociateCourses;
    }

    public void setWhetherAssociateCourses(Integer whetherAssociateCourses) {
        this.whetherAssociateCourses = whetherAssociateCourses;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProjectInfoResource projectInfoResource = (ProjectInfoResource) o;
        return Objects.equals(this.userId, projectInfoResource.userId) &&
                Objects.equals(this.userName, projectInfoResource.userName) &&
                Objects.equals(this.projectCode, projectInfoResource.projectCode) &&
                Objects.equals(this.projectName, projectInfoResource.projectName) &&
                Objects.equals(this.courseId, projectInfoResource.courseId) &&
                Objects.equals(this.courseName, projectInfoResource.courseName) &&
                Objects.equals(this.courseCode, projectInfoResource.courseCode) &&
                Objects.equals(this.projectTypeValue, projectInfoResource.projectTypeValue) &&
                Objects.equals(this.projectCategoryValue, projectInfoResource.projectCategoryValue) &&
                Objects.equals(this.projectGenreValue, projectInfoResource.projectGenreValue) &&
                Objects.equals(this.projectClaimValue, projectInfoResource.projectClaimValue) &&
                Objects.equals(this.classificationId, projectInfoResource.classificationId) &&
                Objects.equals(this.classificationName, projectInfoResource.classificationName) &&
                Objects.equals(this.projectDescription, projectInfoResource.projectDescription) &&
                Objects.equals(this.whetherAssociateCourses, projectInfoResource.whetherAssociateCourses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, projectCode, projectName, courseId, courseName, courseCode,
                projectTypeValue, projectCategoryValue, projectGenreValue, projectClaimValue, classificationId,
                classificationName, projectDescription, whetherAssociateCourses);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ProjectInfoResource {\n");

        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
        sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
        sb.append("    projectCode: ").append(toIndentedString(projectCode)).append("\n");
        sb.append("    projectName: ").append(toIndentedString(projectName)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
        sb.append("    projectTypeValue: ").append(toIndentedString(projectTypeValue)).append("\n");
        sb.append("    projectCategoryValue: ").append(toIndentedString(projectCategoryValue)).append("\n");
        sb.append("    projectGenreValue: ").append(toIndentedString(projectGenreValue)).append("\n");
        sb.append("    projectClaimValue: ").append(toIndentedString(projectClaimValue)).append("\n");
        sb.append("    classificationId: ").append(toIndentedString(classificationId)).append("\n");
        sb.append("    classificationName: ").append(toIndentedString(classificationName)).append("\n");
        sb.append("    projectDescription: ").append(toIndentedString(projectDescription)).append("\n");
        sb.append("    whetherAssociateCourses: ").append(toIndentedString(whetherAssociateCourses)).append("\n");
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

