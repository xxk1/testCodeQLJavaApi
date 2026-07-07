package com.lztech.site.viewmodel.preparationcoursebag;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * PreparationCourse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-04-25T07:19:21.746Z")


public class PreparationCourse   {
    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("versionId")
    private String versionId = null;

    @JsonProperty("courseCode")
    private String courseCode = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    @JsonProperty("collegeCode")
    private String collegeCode = null;

    @JsonProperty("courseSource")
    private Integer courseSource = null;

    @JsonProperty("isPrincipal")
    private Integer isPrincipal = null;

    @JsonProperty("isJoin")
    private Integer isJoin = null;

    @JsonProperty("ifHide")
    private Integer ifHide = null;

    @JsonProperty("modifyTime")
    private String modifyTime = null;

    public PreparationCourse courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    /**
     * 课程id
     * @return courseId
     **/
    @ApiModelProperty(value = "课程id")


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public PreparationCourse versionId(String versionId) {
        this.versionId = versionId;
        return this;
    }

    /**
     * 版本id
     * @return versionId
     **/
    @ApiModelProperty(value = "版本id")


    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public PreparationCourse courseCode(String courseCode) {
        this.courseCode = courseCode;
        return this;
    }

    /**
     * 课程编号
     * @return courseCode
     **/
    @ApiModelProperty(value = "课程编号")


    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public PreparationCourse courseName(String courseName) {
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

    public PreparationCourse collegeId(String collegeId) {
        this.collegeId = collegeId;
        return this;
    }

    /**
     * 学院ID
     * @return collegeId
     **/
    @ApiModelProperty(value = "学院ID")


    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public PreparationCourse collegeName(String collegeName) {
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

    public PreparationCourse collegeCode(String collegeCode) {
        this.collegeCode = collegeCode;
        return this;
    }

    /**
     * 学院编号
     * @return collegeCode
     **/
    @ApiModelProperty(value = "学院编号")


    public String getCollegeCode() {
        return collegeCode;
    }

    public void setCollegeCode(String collegeCode) {
        this.collegeCode = collegeCode;
    }

    public PreparationCourse courseSource(Integer courseSource) {
        this.courseSource = courseSource;
        return this;
    }

    /**
     * 来源 0：对接；1：老师新增
     * @return courseSource
     **/
    @ApiModelProperty(value = "来源 0：对接；1：老师新增")


    public Integer getCourseSource() {
        return courseSource;
    }

    public void setCourseSource(Integer courseSource) {
        this.courseSource = courseSource;
    }

    public PreparationCourse isPrincipal(Integer isPrincipal) {
        this.isPrincipal = isPrincipal;
        return this;
    }

    /**
     * 是否负责人 0:不是； 1:是
     * @return isPrincipal
     **/
    @ApiModelProperty(value = "是否负责人 0:不是； 1:是")


    public Integer getIsPrincipal() {
        return isPrincipal;
    }

    public void setIsPrincipal(Integer isPrincipal) {
        this.isPrincipal = isPrincipal;
    }

    public PreparationCourse isJoin(Integer isJoin) {
        this.isJoin = isJoin;
        return this;
    }

    /**
     * 是否加入备课包：0 未加入；1 已加入
     * @return isJoin
     **/
    @ApiModelProperty(value = "是否加入备课包：0 未加入；1 已加入")


    public Integer getIsJoin() {
        return isJoin;
    }

    public void setIsJoin(Integer isJoin) {
        this.isJoin = isJoin;
    }

    public PreparationCourse ifHide(Integer ifHide) {
        this.ifHide = ifHide;
        return this;
    }

    /**
     * 是否隐藏包：0 未隐藏；1 隐藏
     * @return ifHide
     **/
    @ApiModelProperty(value = "是否隐藏包：0 未隐藏；1 隐藏 ")


    public Integer getIfHide() {
        return ifHide;
    }

    public void setIfHide(Integer ifHide) {
        this.ifHide = ifHide;
    }

    public PreparationCourse modifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

    /**
     * 更新时间
     * @return modifyTime
     **/
    @ApiModelProperty(value = "更新时间")


    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PreparationCourse preparationCourse = (PreparationCourse) o;
        return Objects.equals(this.courseId, preparationCourse.courseId) &&
                Objects.equals(this.versionId, preparationCourse.versionId) &&
                Objects.equals(this.courseCode, preparationCourse.courseCode) &&
                Objects.equals(this.courseName, preparationCourse.courseName) &&
                Objects.equals(this.collegeId, preparationCourse.collegeId) &&
                Objects.equals(this.collegeName, preparationCourse.collegeName) &&
                Objects.equals(this.collegeCode, preparationCourse.collegeCode) &&
                Objects.equals(this.courseSource, preparationCourse.courseSource) &&
                Objects.equals(this.isPrincipal, preparationCourse.isPrincipal) &&
                Objects.equals(this.isJoin, preparationCourse.isJoin) &&
                Objects.equals(this.ifHide, preparationCourse.ifHide) &&
                Objects.equals(this.modifyTime, preparationCourse.modifyTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, versionId, courseCode, courseName, collegeId, collegeName, collegeCode,
                courseSource, isPrincipal, isJoin, ifHide, modifyTime);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PreparationCourse {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    versionId: ").append(toIndentedString(versionId)).append("\n");
        sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
        sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
        sb.append("    collegeCode: ").append(toIndentedString(collegeCode)).append("\n");
        sb.append("    courseSource: ").append(toIndentedString(courseSource)).append("\n");
        sb.append("    isPrincipal: ").append(toIndentedString(isPrincipal)).append("\n");
        sb.append("    isJoin: ").append(toIndentedString(isJoin)).append("\n");
        sb.append("    ifHide: ").append(toIndentedString(ifHide)).append("\n");
        sb.append("    modifyTime: ").append(toIndentedString(modifyTime)).append("\n");
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

