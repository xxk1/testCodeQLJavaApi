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
 * MaterialParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-05-20T10:45:35.732+08:00")

public class MaterialParam {
    @JsonProperty("versionId")
    private String versionId = null;

    @JsonProperty("userId")
    private String userId = null;

    @JsonProperty("userName")
    private String userName = null;

    @JsonProperty("materialType")
    private Integer materialType = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("courseCategoryId")
    private String courseCategoryId = null;

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

    @JsonProperty("materialContent")
    private String materialContent = null;

    @JsonProperty("teachingMaterialFileVoList")
    @Valid
    private List<TeachingMaterialFileVo> teachingMaterialFileVoList = null;

    public MaterialParam versionId(String versionId) {
        this.versionId = versionId;
        return this;
    }

    /**
     * 版本id
     *
     * @return versionId
     **/
    @ApiModelProperty(value = "版本id")


    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public MaterialParam userId(String userId) {
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

    public MaterialParam userName(String userName) {
        this.userName = userName;
        return this;
    }

    /**
     * 用户名称
     *
     * @return userName
     **/
    @ApiModelProperty(value = "用户名称")


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public MaterialParam materialType(Integer materialType) {
        this.materialType = materialType;
        return this;
    }

    /**
     * 资料模块类型：0 课程简介，1课程大纲，2考核办法
     *
     * @return materialType
     **/
    @ApiModelProperty(value = "资料模块类型：0 课程简介，1课程大纲，2考核办法")


    public Integer getMaterialType() {
        return materialType;
    }

    public void setMaterialType(Integer materialType) {
        this.materialType = materialType;
    }

    public MaterialParam courseName(String courseName) {
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

    public MaterialParam courseCategoryId(String courseCategoryId) {
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

    public MaterialParam teachingUserType(String teachingUserType) {
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

    public MaterialParam classHours(BigDecimal classHours) {
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

    public MaterialParam courseContent(String courseContent) {
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

    public MaterialParam teachingMaterial(String teachingMaterial) {
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

    public MaterialParam referenceMaterials(String referenceMaterials) {
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

    public MaterialParam courseIntroductionList(List<CourseIntroductionResource> courseIntroductionList) {
        this.courseIntroductionList = courseIntroductionList;
        return this;
    }

    public MaterialParam addCourseIntroductionListItem(CourseIntroductionResource courseIntroductionListItem) {
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

    public MaterialParam materialContent(String materialContent) {
        this.materialContent = materialContent;
        return this;
    }

    /**
     * 资料内容
     *
     * @return materialContent
     **/
    @ApiModelProperty(value = "资料内容")


    public String getMaterialContent() {
        return materialContent;
    }

    public void setMaterialContent(String materialContent) {
        this.materialContent = materialContent;
    }

    public MaterialParam teachingMaterialFileVoList(List<TeachingMaterialFileVo> teachingMaterialFileVoList) {
        this.teachingMaterialFileVoList = teachingMaterialFileVoList;
        return this;
    }

    public MaterialParam addTeachingMaterialFileVoListItem(TeachingMaterialFileVo teachingMaterialFileVoListItem) {
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
        MaterialParam materialParam = (MaterialParam) o;
        return Objects.equals(this.versionId, materialParam.versionId) &&
                Objects.equals(this.userId, materialParam.userId) &&
                Objects.equals(this.userName, materialParam.userName) &&
                Objects.equals(this.materialType, materialParam.materialType) &&
                Objects.equals(this.courseName, materialParam.courseName) &&
                Objects.equals(this.courseCategoryId, materialParam.courseCategoryId) &&
                Objects.equals(this.teachingUserType, materialParam.teachingUserType) &&
                Objects.equals(this.classHours, materialParam.classHours) &&
                Objects.equals(this.courseContent, materialParam.courseContent) &&
                Objects.equals(this.teachingMaterial, materialParam.teachingMaterial) &&
                Objects.equals(this.referenceMaterials, materialParam.referenceMaterials) &&
                Objects.equals(this.courseIntroductionList, materialParam.courseIntroductionList) &&
                Objects.equals(this.materialContent, materialParam.materialContent) &&
                Objects.equals(this.teachingMaterialFileVoList, materialParam.teachingMaterialFileVoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(versionId, userId, userName, materialType, courseName, courseCategoryId, teachingUserType,
                classHours, courseContent, teachingMaterial, referenceMaterials, courseIntroductionList, materialContent,
                teachingMaterialFileVoList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class MaterialParam {\n");

        sb.append("    versionId: ").append(toIndentedString(versionId)).append("\n");
        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
        sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
        sb.append("    materialType: ").append(toIndentedString(materialType)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    courseCategoryId: ").append(toIndentedString(courseCategoryId)).append("\n");
        sb.append("    teachingUserType: ").append(toIndentedString(teachingUserType)).append("\n");
        sb.append("    classHours: ").append(toIndentedString(classHours)).append("\n");
        sb.append("    courseContent: ").append(toIndentedString(courseContent)).append("\n");
        sb.append("    teachingMaterial: ").append(toIndentedString(teachingMaterial)).append("\n");
        sb.append("    referenceMaterials: ").append(toIndentedString(referenceMaterials)).append("\n");
        sb.append("    courseIntroductionList: ").append(toIndentedString(courseIntroductionList)).append("\n");
        sb.append("    materialContent: ").append(toIndentedString(materialContent)).append("\n");
        sb.append("    teachingMaterialFileVoList: ").append(toIndentedString(teachingMaterialFileVoList)).append("\n");
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

