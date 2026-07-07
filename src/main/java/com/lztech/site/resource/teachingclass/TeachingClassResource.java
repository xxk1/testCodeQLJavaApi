package com.lztech.site.resource.teachingclass;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * TeachingClassResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-18T07:43:32.409Z")

public class TeachingClassResource {
    @JsonProperty("teachingClassId")
    private String teachingClassId = null;

    @JsonProperty("teachingClassName")
    private String teachingClassName = null;

    @JsonProperty("teachingClassCode")
    private String teachingClassCode = null;

    @JsonProperty("teachingClassSort")
    private Integer teachingClassSort = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    @JsonProperty("courseTableCollegeId")
    private String courseTableCollegeId = null;

    @JsonProperty("courseTableCollegeName")
    private String courseTableCollegeName = null;

    @JsonProperty("schoolYear")
    private String schoolYear = null;

    @JsonProperty("description")
    private String description = null;

    @JsonProperty("term")
    private Integer term = null;

    @JsonProperty("teachingClassType")
    private Integer teachingClassType = null;

    @JsonProperty("teachingClassMembers")
    private Integer teachingClassMembers = null;

    @JsonProperty("classNickName")
    private String classNickName = null;

    @JsonProperty("classCompositionName")
    private String classCompositionName = null;

    @JsonProperty("classAttribute")
    private Integer classAttribute = null;
    @JsonProperty("schoolYearTermNickName")
    private String schoolYearTermNickName = null;

    public TeachingClassResource schoolYearTermNickName(String schoolYearTermNickName) {
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

    public Integer getClassAttribute() {
        return classAttribute;
    }

    public void setClassAttribute(Integer classAttribute) {
        this.classAttribute = classAttribute;
    }

    public TeachingClassResource teachingClassId(String teachingClassId) {
        this.teachingClassId = teachingClassId;
        return this;
    }

    /**
     * 班级Id
     * @return teachingClassId
     **/
    @ApiModelProperty(value = "班级Id")


    public String getTeachingClassId() {
        return teachingClassId;
    }

    public void setTeachingClassId(String teachingClassId) {
        this.teachingClassId = teachingClassId;
    }

    public TeachingClassResource teachingClassName(String teachingClassName) {
        this.teachingClassName = teachingClassName;
        return this;
    }

    /**
     * 班级名称
     * @return teachingClassName
     **/
    @ApiModelProperty(value = "班级名称")


    public String getTeachingClassName() {
        return teachingClassName;
    }

    public void setTeachingClassName(String teachingClassName) {
        this.teachingClassName = teachingClassName;
    }

    public TeachingClassResource teachingClassCode(String teachingClassCode) {
        this.teachingClassCode = teachingClassCode;
        return this;
    }
    /**
     * 班级编号
     * @return teachingClassCode
     **/
    @ApiModelProperty(value = "班级编号")

    public String getTeachingClassCode() {
        return teachingClassCode;
    }

    public void setTeachingClassCode(String teachingClassCode) {
        this.teachingClassCode = teachingClassCode;
    }


    public TeachingClassResource teachingClassCode(Integer teachingClassSort) {
        this.teachingClassSort = teachingClassSort;
        return this;
    }
    /**
     * 班级排序
     * @return teachingClassSort
     **/
    @ApiModelProperty(value = "班级排序")

    public Integer getTeachingClassSort() {
        return teachingClassSort;
    }

    public void setTeachingClassSort(Integer teachingClassSort) {
        this.teachingClassSort = teachingClassSort;
    }


    public TeachingClassResource courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    /**
     * 课程Id
     * @return courseId
     **/
    @ApiModelProperty(value = "课程Id")


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public TeachingClassResource courseName(String courseName) {
        this.courseName = courseName;
        return this;
    }

    /**
     * 课程名称
     * @return courseName
     **/
    @ApiModelProperty(value = "课程名称")


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public TeachingClassResource collegeId(String collegeId) {
        this.collegeId = collegeId;
        return this;
    }

    /**
     * 学院Id
     * @return collegeId
     **/
    @ApiModelProperty(value = "学院Id")


    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public TeachingClassResource collegeName(String collegeName) {
        this.collegeName = collegeName;
        return this;
    }

    /**
     * 学院名称
     * @return collegeName
     **/
    @ApiModelProperty(value = "学院名称")


    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }


    public TeachingClassResource courseTableCollegeId(String courseTableCollegeId) {
        this.courseTableCollegeId = courseTableCollegeId;
        return this;
    }

    /**
     * 开课所属学院Id
     * @return courseTableCollegeId
     **/
    @ApiModelProperty(value = "开课所属学院Id")


    public String getCourseTableCollegeId() {
        return courseTableCollegeId;
    }

    public void setCourseTableCollegeId(String courseTableCollegeId) {
        this.courseTableCollegeId = courseTableCollegeId;
    }

    public TeachingClassResource courseTableCollegeName(String courseTableCollegeName) {
        this.courseTableCollegeName = courseTableCollegeName;
        return this;
    }

    /**
     * 开课所属学院名称
     * @return courseTableCollegeName
     **/
    @ApiModelProperty(value = "开课所属学院名称")


    public String getCourseTableCollegeName() {
        return courseTableCollegeName;
    }

    public void setCourseTableCollegeName(String courseTableCollegeName) {
        this.courseTableCollegeName = courseTableCollegeName;
    }



    public TeachingClassResource schoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
        return this;
    }

    /**
     * 学年
     * @return schoolYear
     **/
    @ApiModelProperty(value = "学年")


    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public TeachingClassResource description(String description) {
        this.description = description;
        return this;
    }

    /**
     * 第几学期
     * @return description
     **/
    @ApiModelProperty(value = "第几学期")


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TeachingClassResource term(Integer term) {
        this.term = term;
        return this;
    }

    /**
     * 学期
     * @return term
     **/
    @ApiModelProperty(value = "学期")


    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public TeachingClassResource teachingClassType(Integer teachingClassType) {
        this.teachingClassType = teachingClassType;
        return this;
    }

    /**
     * 班级类型 0:课表班 3:自由班
     * @return teachingClassType
     **/
    @ApiModelProperty(value = "班级类型 0:课表班 3:自由班")


    public Integer getTeachingClassType() {
        return teachingClassType;
    }

    public void setTeachingClassType(Integer teachingClassType) {
        this.teachingClassType = teachingClassType;
    }

    public TeachingClassResource teachingClassMembers(Integer teachingClassMembers) {
        this.teachingClassMembers = teachingClassMembers;
        return this;
    }

    /**
     * 班级人数
     * @return teachingClassMembers
     **/
    @ApiModelProperty(value = "班级人数")


    public Integer getTeachingClassMembers() {
        return teachingClassMembers;
    }

    public void setTeachingClassMembers(Integer teachingClassMembers) {
        this.teachingClassMembers = teachingClassMembers;
    }

    public TeachingClassResource classNickName(String classNickName) {
        this.classNickName = classNickName;
        return this;
    }

    /**
     * 班级昵称
     * @return classNickName
     **/
    @ApiModelProperty(value = "班级昵称")


    public String getClassNickName() {
        return classNickName;
    }

    public void setClassNickName(String classNickName) {
        this.classNickName = classNickName;
    }

    public TeachingClassResource classCompositionName(String classCompositionName) {
        this.classCompositionName = classCompositionName;
        return this;
    }

    /**
     * 班级组成名称
     * @return classCompositionName
     **/
    @ApiModelProperty(value = "班级组成名称")


    public String getClassCompositionName() {
        return classCompositionName;
    }

    public void setClassCompositionName(String classCompositionName) {
        this.classCompositionName = classCompositionName;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeachingClassResource teachingClassResource = (TeachingClassResource) o;
        return Objects.equals(this.teachingClassId, teachingClassResource.teachingClassId) &&
                Objects.equals(this.teachingClassName, teachingClassResource.teachingClassName) &&
                Objects.equals(this.teachingClassCode, teachingClassResource.teachingClassCode) &&
                Objects.equals(this.teachingClassSort, teachingClassResource.teachingClassSort) &&
                Objects.equals(this.courseId, teachingClassResource.courseId) &&
                Objects.equals(this.courseName, teachingClassResource.courseName) &&
                Objects.equals(this.collegeId, teachingClassResource.collegeId) &&
                Objects.equals(this.collegeName, teachingClassResource.collegeName) &&
                Objects.equals(this.courseTableCollegeId, teachingClassResource.courseTableCollegeId) &&
                Objects.equals(this.courseTableCollegeName, teachingClassResource.courseTableCollegeName) &&
                Objects.equals(this.schoolYear, teachingClassResource.schoolYear) &&
                Objects.equals(this.description, teachingClassResource.description) &&
                Objects.equals(this.term, teachingClassResource.term) &&
                Objects.equals(this.schoolYearTermNickName, teachingClassResource.schoolYearTermNickName) &&
                Objects.equals(this.teachingClassType, teachingClassResource.teachingClassType) &&
                Objects.equals(this.teachingClassMembers, teachingClassResource.teachingClassMembers) &&
                Objects.equals(this.classNickName, teachingClassResource.classNickName) &&
                Objects.equals(this.classCompositionName, teachingClassResource.classCompositionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teachingClassId, teachingClassName, teachingClassCode,teachingClassSort, courseId, courseName, schoolYearTermNickName,
                courseTableCollegeId, courseTableCollegeName,collegeId, collegeName, schoolYear, description, term,teachingClassType,
                teachingClassMembers, classNickName, classCompositionName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TeachingClassResource {\n");

        sb.append("    teachingClassId: ").append(toIndentedString(teachingClassId)).append("\n");
        sb.append("    teachingClassName: ").append(toIndentedString(teachingClassName)).append("\n");
        sb.append("    teachingClassCode: ").append(toIndentedString(teachingClassCode)).append("\n");
        sb.append("    teachingClassSort: ").append(toIndentedString(teachingClassSort)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
        sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
        sb.append("    courseTableCollegeId: ").append(toIndentedString(courseTableCollegeId)).append("\n");
        sb.append("    courseTableCollegeName: ").append(toIndentedString(courseTableCollegeName)).append("\n");
        sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    term: ").append(toIndentedString(term)).append("\n");
        sb.append("    schoolYearTermNickName: ").append(toIndentedString(schoolYearTermNickName)).append("\n");
        sb.append("    teachingClassType: ").append(toIndentedString(teachingClassType)).append("\n");
        sb.append("    teachingClassMembers: ").append(toIndentedString(teachingClassMembers)).append("\n");
        sb.append("    classNickName: ").append(toIndentedString(classNickName)).append("\n");
        sb.append("    classCompositionName: ").append(toIndentedString(classCompositionName)).append("\n");
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

