package com.lztech.site.viewmodel.coursemanagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseTeachingTeamVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2026-01-06T13:55:16.286+08:00")

public class CourseTeachingTeamVo {
    @JsonProperty("operatorId")
    private String operatorId = null;

    @JsonProperty("operatorName")
    private String operatorName = null;

    @JsonProperty("teachingTeamId")
    private String teachingTeamId = null;

    @JsonProperty("teacherId")
    private String teacherId = null;

    @JsonProperty("teacherNo")
    private String teacherNo = null;

    @JsonProperty("teacherName")
    private String teacherName = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("dataSource")
    private Integer dataSource = null;

    @JsonProperty("teacherProfile")
    private String teacherProfile = null;

    @JsonProperty("jobTitle")
    private String jobTitle = null;

    @JsonProperty("schoolName")
    private String schoolName = null;

    @JsonProperty("versionId")
    private String versionId = null;

    @JsonProperty("photoFileDisplayName")
    private String photoFileDisplayName = null;

    @JsonProperty("photoFileRealName")
    private String photoFileRealName = null;

    @JsonProperty("photoFileType")
    private String photoFileType = null;

    @JsonProperty("photoInnerUrl")
    private String photoInnerUrl = null;

    @JsonProperty("photoOuterUrl")
    private String photoOuterUrl = null;

    @JsonProperty("photoFilePath")
    private String photoFilePath = null;

    @JsonProperty("photoFileSize")
    private String photoFileSize = null;

    public CourseTeachingTeamVo operatorId(String operatorId) {
        this.operatorId = operatorId;
        return this;
    }

    /**
     * 操作人id
     *
     * @return operatorId
     **/
    @ApiModelProperty(value = "操作人id")


    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public CourseTeachingTeamVo operatorName(String operatorName) {
        this.operatorName = operatorName;
        return this;
    }

    /**
     * 操作人姓名
     *
     * @return operatorName
     **/
    @ApiModelProperty(value = "操作人姓名")


    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public CourseTeachingTeamVo teachingTeamId(String teachingTeamId) {
        this.teachingTeamId = teachingTeamId;
        return this;
    }

    /**
     * 教学团队id，没有新增；有更新
     *
     * @return teachingTeamId
     **/
    @ApiModelProperty(value = "教学团队id，没有新增；有更新")


    public String getTeachingTeamId() {
        return teachingTeamId;
    }

    public void setTeachingTeamId(String teachingTeamId) {
        this.teachingTeamId = teachingTeamId;
    }

    public CourseTeachingTeamVo teacherId(String teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    /**
     * 老师id
     *
     * @return teacherId
     **/
    @ApiModelProperty(value = "老师id")


    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public CourseTeachingTeamVo teacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
        return this;
    }

    /**
     * 老师工号
     *
     * @return teacherNo
     **/
    @ApiModelProperty(value = "老师工号")


    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    public CourseTeachingTeamVo teacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    /**
     * 老师名称
     *
     * @return teacherName
     **/
    @ApiModelProperty(value = "老师名称")


    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public CourseTeachingTeamVo courseId(String courseId) {
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

    public CourseTeachingTeamVo dataSource(Integer dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    /**
     * 教师数据来源 0：校内；1：校外
     *
     * @return dataSource
     **/
    @ApiModelProperty(value = "教师数据来源 0：校内；1：校外")


    public Integer getDataSource() {
        return dataSource;
    }

    public void setDataSource(Integer dataSource) {
        this.dataSource = dataSource;
    }

    public CourseTeachingTeamVo teacherProfile(String teacherProfile) {
        this.teacherProfile = teacherProfile;
        return this;
    }

    /**
     * 教师简介
     *
     * @return teacherProfile
     **/
    @ApiModelProperty(value = "教师简介")


    public String getTeacherProfile() {
        return teacherProfile;
    }

    public void setTeacherProfile(String teacherProfile) {
        this.teacherProfile = teacherProfile;
    }

    public CourseTeachingTeamVo jobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
        return this;
    }

    /**
     * 职称
     *
     * @return jobTitle
     **/
    @ApiModelProperty(value = "职称")


    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public CourseTeachingTeamVo schoolName(String schoolName) {
        this.schoolName = schoolName;
        return this;
    }

    /**
     * 校外用户所属学校
     *
     * @return schoolName
     **/
    @ApiModelProperty(value = "校外用户所属学校")


    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public CourseTeachingTeamVo versionId(String versionId) {
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

    public CourseTeachingTeamVo photoFileDisplayName(String photoFileDisplayName) {
        this.photoFileDisplayName = photoFileDisplayName;
        return this;
    }

    /**
     * 教师照片文件显示名称
     *
     * @return photoFileDisplayName
     **/
    @ApiModelProperty(value = "教师照片文件显示名称")


    public String getPhotoFileDisplayName() {
        return photoFileDisplayName;
    }

    public void setPhotoFileDisplayName(String photoFileDisplayName) {
        this.photoFileDisplayName = photoFileDisplayName;
    }

    public CourseTeachingTeamVo photoFileRealName(String photoFileRealName) {
        this.photoFileRealName = photoFileRealName;
        return this;
    }

    /**
     * 教师照片文件存储名称
     *
     * @return photoFileRealName
     **/
    @ApiModelProperty(value = "教师照片文件存储名称")


    public String getPhotoFileRealName() {
        return photoFileRealName;
    }

    public void setPhotoFileRealName(String photoFileRealName) {
        this.photoFileRealName = photoFileRealName;
    }

    public CourseTeachingTeamVo photoFileType(String photoFileType) {
        this.photoFileType = photoFileType;
        return this;
    }

    /**
     * 教师照片文件类型（即文件后缀）
     *
     * @return photoFileType
     **/
    @ApiModelProperty(value = "教师照片文件类型（即文件后缀）")


    public String getPhotoFileType() {
        return photoFileType;
    }

    public void setPhotoFileType(String photoFileType) {
        this.photoFileType = photoFileType;
    }

    public CourseTeachingTeamVo photoInnerUrl(String photoInnerUrl) {
        this.photoInnerUrl = photoInnerUrl;
        return this;
    }

    /**
     * 教师照片内网地址
     *
     * @return photoInnerUrl
     **/
    @ApiModelProperty(value = "教师照片内网地址")


    public String getPhotoInnerUrl() {
        return photoInnerUrl;
    }

    public void setPhotoInnerUrl(String photoInnerUrl) {
        this.photoInnerUrl = photoInnerUrl;
    }

    public CourseTeachingTeamVo photoOuterUrl(String photoOuterUrl) {
        this.photoOuterUrl = photoOuterUrl;
        return this;
    }

    /**
     * 教师照片外网地址
     *
     * @return photoOuterUrl
     **/
    @ApiModelProperty(value = "教师照片外网地址")


    public String getPhotoOuterUrl() {
        return photoOuterUrl;
    }

    public void setPhotoOuterUrl(String photoOuterUrl) {
        this.photoOuterUrl = photoOuterUrl;
    }

    public CourseTeachingTeamVo photoFilePath(String photoFilePath) {
        this.photoFilePath = photoFilePath;
        return this;
    }

    /**
     * 教师照片文件路径
     *
     * @return photoFilePath
     **/
    @ApiModelProperty(value = "教师照片文件路径")


    public String getPhotoFilePath() {
        return photoFilePath;
    }

    public void setPhotoFilePath(String photoFilePath) {
        this.photoFilePath = photoFilePath;
    }

    public CourseTeachingTeamVo photoFileSize(String photoFileSize) {
        this.photoFileSize = photoFileSize;
        return this;
    }

    /**
     * 教师照片文件大小
     *
     * @return photoFileSize
     **/
    @ApiModelProperty(value = "教师照片文件大小")


    public String getPhotoFileSize() {
        return photoFileSize;
    }

    public void setPhotoFileSize(String photoFileSize) {
        this.photoFileSize = photoFileSize;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseTeachingTeamVo courseTeachingTeamVo = (CourseTeachingTeamVo) o;
        return Objects.equals(this.operatorId, courseTeachingTeamVo.operatorId) &&
                Objects.equals(this.operatorName, courseTeachingTeamVo.operatorName) &&
                Objects.equals(this.teachingTeamId, courseTeachingTeamVo.teachingTeamId) &&
                Objects.equals(this.teacherId, courseTeachingTeamVo.teacherId) &&
                Objects.equals(this.teacherNo, courseTeachingTeamVo.teacherNo) &&
                Objects.equals(this.teacherName, courseTeachingTeamVo.teacherName) &&
                Objects.equals(this.courseId, courseTeachingTeamVo.courseId) &&
                Objects.equals(this.dataSource, courseTeachingTeamVo.dataSource) &&
                Objects.equals(this.teacherProfile, courseTeachingTeamVo.teacherProfile) &&
                Objects.equals(this.jobTitle, courseTeachingTeamVo.jobTitle) &&
                Objects.equals(this.schoolName, courseTeachingTeamVo.schoolName) &&
                Objects.equals(this.versionId, courseTeachingTeamVo.versionId) &&
                Objects.equals(this.photoFileDisplayName, courseTeachingTeamVo.photoFileDisplayName) &&
                Objects.equals(this.photoFileRealName, courseTeachingTeamVo.photoFileRealName) &&
                Objects.equals(this.photoFileType, courseTeachingTeamVo.photoFileType) &&
                Objects.equals(this.photoInnerUrl, courseTeachingTeamVo.photoInnerUrl) &&
                Objects.equals(this.photoOuterUrl, courseTeachingTeamVo.photoOuterUrl) &&
                Objects.equals(this.photoFilePath, courseTeachingTeamVo.photoFilePath) &&
                Objects.equals(this.photoFileSize, courseTeachingTeamVo.photoFileSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operatorId, operatorName, teachingTeamId, teacherId, teacherNo, teacherName, courseId,
                dataSource, teacherProfile, jobTitle, schoolName, versionId, photoFileDisplayName, photoFileRealName,
                photoFileType, photoInnerUrl, photoOuterUrl, photoFilePath, photoFileSize);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTeachingTeamVo {\n");

        sb.append("    operatorId: ").append(toIndentedString(operatorId)).append("\n");
        sb.append("    operatorName: ").append(toIndentedString(operatorName)).append("\n");
        sb.append("    teachingTeamId: ").append(toIndentedString(teachingTeamId)).append("\n");
        sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
        sb.append("    teacherNo: ").append(toIndentedString(teacherNo)).append("\n");
        sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    dataSource: ").append(toIndentedString(dataSource)).append("\n");
        sb.append("    teacherProfile: ").append(toIndentedString(teacherProfile)).append("\n");
        sb.append("    jobTitle: ").append(toIndentedString(jobTitle)).append("\n");
        sb.append("    schoolName: ").append(toIndentedString(schoolName)).append("\n");
        sb.append("    versionId: ").append(toIndentedString(versionId)).append("\n");
        sb.append("    photoFileDisplayName: ").append(toIndentedString(photoFileDisplayName)).append("\n");
        sb.append("    photoFileRealName: ").append(toIndentedString(photoFileRealName)).append("\n");
        sb.append("    photoFileType: ").append(toIndentedString(photoFileType)).append("\n");
        sb.append("    photoInnerUrl: ").append(toIndentedString(photoInnerUrl)).append("\n");
        sb.append("    photoOuterUrl: ").append(toIndentedString(photoOuterUrl)).append("\n");
        sb.append("    photoFilePath: ").append(toIndentedString(photoFilePath)).append("\n");
        sb.append("    photoFileSize: ").append(toIndentedString(photoFileSize)).append("\n");
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

