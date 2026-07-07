package com.lztech.site.viewmodel.coursemanagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseMaterialResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-05-20T10:45:35.732+08:00")

public class CourseMaterialResource {
    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("courseCategoryId")
    private String courseCategoryId = null;

    @JsonProperty("courseCategoryName")
    private String courseCategoryName = null;

    @JsonProperty("teachingUserType")
    private String teachingUserType = null;

    @JsonProperty("classHours")
    private BigDecimal classHours = null;

    @JsonProperty("courseContent")
    private String courseContent = null;

    @JsonProperty("teachingMaterial")
    private String teachingMaterial = null;

    @JsonProperty("referenceMaterials")
    private String referenceMaterials = null;

    @JsonProperty("courseIntroductionList")
    @Valid
    private List<CourseIntroductionResource> courseIntroductionList = null;

    @JsonProperty("assessmentMethod")
    private String assessmentMethod = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    @JsonProperty("teachingMaterialFileVoList")
    @Valid
    private List<TeachingMaterialFileVo> teachingMaterialFileVoList = null;

    @JsonProperty("coverInnerIp")
    private String coverInnerIp = null;

    @JsonProperty("coverOuterIp")
    private String coverOuterIp = null;

    @JsonProperty("coverFilepath")
    private String coverFilepath = null;


    public CourseMaterialResource coverInnerIp(String coverInnerIp) {
        this.coverInnerIp = coverInnerIp;
        return this;
    }

    /**
     * 封面内网IP
     *
     * @return coverInnerIp
     **/
    @ApiModelProperty(value = "封面内网IP")
    public String getCoverInnerIp() {
        return coverInnerIp;
    }

    public void setCoverInnerIp(String coverInnerIp) {
        this.coverInnerIp = coverInnerIp;
    }

    public CourseMaterialResource coverOuterIp(String coverOuterIp) {
        this.coverOuterIp = coverOuterIp;
        return this;
    }

    /**
     * 封面外网IP
     *
     * @return coverOuterIp
     **/
    @ApiModelProperty(value = "封面外网IP")
    public String getCoverOuterIp() {
        return coverOuterIp;
    }

    public void setCoverOuterIp(String coverOuterIp) {
        this.coverOuterIp = coverOuterIp;
    }

    public CourseMaterialResource coverFilepath(String coverFilepath) {
        this.coverFilepath = coverFilepath;
        return this;
    }

    /**
     * 封面文件路径
     *
     * @return coverFilepath
     **/
    @ApiModelProperty(value = "封面文件路径")
    public String getCoverFilepath() {
        return coverFilepath;
    }

    public void setCoverFilepath(String coverFilepath) {
        this.coverFilepath = coverFilepath;
    }



    public CourseMaterialResource courseName(String courseName) {
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

    public CourseMaterialResource courseCategoryId(String courseCategoryId) {
        this.courseCategoryId = courseCategoryId;
        return this;
    }

    /**
     * 课程类别id
     *
     * @return courseCategoryId
     **/
    @ApiModelProperty(value = "课程类别id")


    public String getCourseCategoryId() {
        return courseCategoryId;
    }

    public void setCourseCategoryId(String courseCategoryId) {
        this.courseCategoryId = courseCategoryId;
    }

    public CourseMaterialResource courseCategoryName(String courseCategoryName) {
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

    public CourseMaterialResource teachingUserType(String teachingUserType) {
        this.teachingUserType = teachingUserType;
        return this;
    }

    /**
     * 授课对象
     *
     * @return teachingUserType
     **/
    @ApiModelProperty(value = "授课对象")


    public String getTeachingUserType() {
        return teachingUserType;
    }

    public void setTeachingUserType(String teachingUserType) {
        this.teachingUserType = teachingUserType;
    }

    public CourseMaterialResource classHours(BigDecimal classHours) {
        this.classHours = classHours;
        return this;
    }

    /**
     * 学时
     *
     * @return classHours
     **/
    @ApiModelProperty(value = "学时")

    @Valid

    public BigDecimal getClassHours() {
        return classHours;
    }

    public void setClassHours(BigDecimal classHours) {
        this.classHours = classHours;
    }

    public CourseMaterialResource courseContent(String courseContent) {
        this.courseContent = courseContent;
        return this;
    }

    /**
     * 课程内容
     *
     * @return courseContent
     **/
    @ApiModelProperty(value = "课程内容")


    public String getCourseContent() {
        return courseContent;
    }

    public void setCourseContent(String courseContent) {
        this.courseContent = courseContent;
    }

    public CourseMaterialResource teachingMaterial(String teachingMaterial) {
        this.teachingMaterial = teachingMaterial;
        return this;
    }

    /**
     * 教材
     *
     * @return teachingMaterial
     **/
    @ApiModelProperty(value = "教材")


    public String getTeachingMaterial() {
        return teachingMaterial;
    }

    public void setTeachingMaterial(String teachingMaterial) {
        this.teachingMaterial = teachingMaterial;
    }

    public CourseMaterialResource referenceMaterials(String referenceMaterials) {
        this.referenceMaterials = referenceMaterials;
        return this;
    }

    /**
     * 参考教材
     *
     * @return referenceMaterials
     **/
    @ApiModelProperty(value = "参考教材")


    public String getReferenceMaterials() {
        return referenceMaterials;
    }

    public void setReferenceMaterials(String referenceMaterials) {
        this.referenceMaterials = referenceMaterials;
    }

    public CourseMaterialResource courseIntroductionList(List<CourseIntroductionResource> courseIntroductionList) {
        this.courseIntroductionList = courseIntroductionList;
        return this;
    }

    public CourseMaterialResource addCourseIntroductionListItem(CourseIntroductionResource courseIntroductionListItem) {
        if (this.courseIntroductionList == null) {
            this.courseIntroductionList = new ArrayList<CourseIntroductionResource>();
        }
        this.courseIntroductionList.add(courseIntroductionListItem);
        return this;
    }

    /**
     * 资源集合
     *
     * @return courseIntroductionList
     **/
    @ApiModelProperty(value = "资源集合")

    @Valid

    public List<CourseIntroductionResource> getCourseIntroductionList() {
        return courseIntroductionList;
    }

    public void setCourseIntroductionList(List<CourseIntroductionResource> courseIntroductionList) {
        this.courseIntroductionList = courseIntroductionList;
    }

    public CourseMaterialResource assessmentMethod(String assessmentMethod) {
        this.assessmentMethod = assessmentMethod;
        return this;
    }

    /**
     * 考核办法
     *
     * @return assessmentMethod
     **/
    @ApiModelProperty(value = "考核办法")


    public String getAssessmentMethod() {
        return assessmentMethod;
    }

    public void setAssessmentMethod(String assessmentMethod) {
        this.assessmentMethod = assessmentMethod;
    }

    public CourseMaterialResource collegeId(String collegeId) {
        this.collegeId = collegeId;
        return this;
    }

    /**
     * 学院ID
     *
     * @return collegeId
     **/
    @ApiModelProperty(value = "学院ID")


    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public CourseMaterialResource collegeName(String collegeName) {
        this.collegeName = collegeName;
        return this;
    }

    /**
     * 学院名称
     *
     * @return collegeName
     **/
    @ApiModelProperty(value = "学院名称")


    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public CourseMaterialResource teachingMaterialFileVoList(List<TeachingMaterialFileVo> teachingMaterialFileVoList) {
        this.teachingMaterialFileVoList = teachingMaterialFileVoList;
        return this;
    }

    public CourseMaterialResource addTeachingMaterialFileVoListItem(TeachingMaterialFileVo teachingMaterialFileVoListItem) {
        if (this.teachingMaterialFileVoList == null) {
            this.teachingMaterialFileVoList = new ArrayList<TeachingMaterialFileVo>();
        }
        this.teachingMaterialFileVoList.add(teachingMaterialFileVoListItem);
        return this;
    }

    /**
     * 教材附件集合
     *
     * @return teachingMaterialFileVoList
     **/
    @ApiModelProperty(value = "教材附件集合")

    @Valid

    public List<TeachingMaterialFileVo> getTeachingMaterialFileVoList() {
        return teachingMaterialFileVoList;
    }

    public void setTeachingMaterialFileVoList(List<TeachingMaterialFileVo> teachingMaterialFileVoList) {
        this.teachingMaterialFileVoList = teachingMaterialFileVoList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseMaterialResource courseMaterialResource = (CourseMaterialResource) o;
        return Objects.equals(this.courseName, courseMaterialResource.courseName) &&
                Objects.equals(this.courseCategoryId, courseMaterialResource.courseCategoryId) &&
                Objects.equals(this.courseCategoryName, courseMaterialResource.courseCategoryName) &&
                Objects.equals(this.teachingUserType, courseMaterialResource.teachingUserType) &&
                Objects.equals(this.classHours, courseMaterialResource.classHours) &&
                Objects.equals(this.courseContent, courseMaterialResource.courseContent) &&
                Objects.equals(this.teachingMaterial, courseMaterialResource.teachingMaterial) &&
                Objects.equals(this.referenceMaterials, courseMaterialResource.referenceMaterials) &&
                Objects.equals(this.courseIntroductionList, courseMaterialResource.courseIntroductionList) &&
                Objects.equals(this.assessmentMethod, courseMaterialResource.assessmentMethod) &&
                Objects.equals(this.collegeId, courseMaterialResource.collegeId) &&
                Objects.equals(this.collegeName, courseMaterialResource.collegeName) &&
                Objects.equals(this.teachingMaterialFileVoList, courseMaterialResource.teachingMaterialFileVoList) &&
                Objects.equals(this.coverInnerIp, courseMaterialResource.coverInnerIp) &&
                Objects.equals(this.coverOuterIp, courseMaterialResource.coverOuterIp) &&
                Objects.equals(this.coverFilepath, courseMaterialResource.coverFilepath) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName, courseCategoryId, courseCategoryName, teachingUserType, classHours,
                courseContent, teachingMaterial, referenceMaterials, courseIntroductionList, assessmentMethod,
                collegeId, collegeName, teachingMaterialFileVoList,coverInnerIp,coverOuterIp,coverFilepath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseMaterialResource {\n");

        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    courseCategoryId: ").append(toIndentedString(courseCategoryId)).append("\n");
        sb.append("    courseCategoryName: ").append(toIndentedString(courseCategoryName)).append("\n");
        sb.append("    teachingUserType: ").append(toIndentedString(teachingUserType)).append("\n");
        sb.append("    classHours: ").append(toIndentedString(classHours)).append("\n");
        sb.append("    courseContent: ").append(toIndentedString(courseContent)).append("\n");
        sb.append("    teachingMaterial: ").append(toIndentedString(teachingMaterial)).append("\n");
        sb.append("    referenceMaterials: ").append(toIndentedString(referenceMaterials)).append("\n");
        sb.append("    courseIntroductionList: ").append(toIndentedString(courseIntroductionList)).append("\n");
        sb.append("    assessmentMethod: ").append(toIndentedString(assessmentMethod)).append("\n");
        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
        sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
        sb.append("    teachingMaterialFileVoList: ").append(toIndentedString(teachingMaterialFileVoList)).append("\n");
        sb.append("    coverInnerIp: ").append(toIndentedString(coverInnerIp)).append("\n");
        sb.append("    coverOuterIp: ").append(toIndentedString(coverOuterIp)).append("\n");
        sb.append("    coverFilepath: ").append(toIndentedString(coverFilepath)).append("\n");
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

