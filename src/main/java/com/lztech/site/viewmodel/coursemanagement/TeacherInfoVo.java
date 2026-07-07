package com.lztech.site.viewmodel.coursemanagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * TeacherInfoVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2026-01-06T16:43:58.781+08:00")

public class TeacherInfoVo {
    @JsonProperty("teacherId")
    private String teacherId = null;

    @JsonProperty("teachingTeamId")
    private String teachingTeamId = null;

    @JsonProperty("teacherNo")
    private String teacherNo = null;

    @JsonProperty("teacherName")
    private String teacherName = null;

    @JsonProperty("isLeader")
    private Boolean isLeader = null;

    @JsonProperty("avatarPath")
    private String avatarPath = null;

    @JsonProperty("avatarInnerUrl")
    private String avatarInnerUrl = null;

    @JsonProperty("avatarOuterUrl")
    private String avatarOuterUrl = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    @JsonProperty("dataSource")
    private Integer dataSource = null;

    @JsonProperty("schoolName")
    private String schoolName = null;

    @JsonProperty("jobTitle")
    private String jobTitle = null;

    @JsonProperty("teacherProfile")
    private String teacherProfile = null;

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

    public TeacherInfoVo teacherId(String teacherId) {
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

    public TeacherInfoVo teachingTeamId(String teachingTeamId) {
        this.teachingTeamId = teachingTeamId;
        return this;
    }

    /**
     * 老师教师团队id
     *
     * @return teachingTeamId
     **/
    @ApiModelProperty(value = "老师教师团队id")


    public String getTeachingTeamId() {
        return teachingTeamId;
    }

    public void setTeachingTeamId(String teachingTeamId) {
        this.teachingTeamId = teachingTeamId;
    }

    public TeacherInfoVo teacherNo(String teacherNo) {
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

    public TeacherInfoVo teacherName(String teacherName) {
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

    public TeacherInfoVo isLeader(Boolean isLeader) {
        this.isLeader = isLeader;
        return this;
    }

    /**
     * 是否为课程负责人
     *
     * @return isLeader
     **/
    @ApiModelProperty(value = "是否为课程负责人")


    public Boolean isIsLeader() {
        return isLeader;
    }

    public void setIsLeader(Boolean isLeader) {
        this.isLeader = isLeader;
    }

    public TeacherInfoVo avatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
        return this;
    }

    /**
     * 头像路径
     *
     * @return avatarPath
     **/
    @ApiModelProperty(value = "头像路径")


    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public TeacherInfoVo avatarInnerUrl(String avatarInnerUrl) {
        this.avatarInnerUrl = avatarInnerUrl;
        return this;
    }

    /**
     * 头像内网地址
     *
     * @return avatarInnerUrl
     **/
    @ApiModelProperty(value = "头像内网地址")


    public String getAvatarInnerUrl() {
        return avatarInnerUrl;
    }

    public void setAvatarInnerUrl(String avatarInnerUrl) {
        this.avatarInnerUrl = avatarInnerUrl;
    }

    public TeacherInfoVo avatarOuterUrl(String avatarOuterUrl) {
        this.avatarOuterUrl = avatarOuterUrl;
        return this;
    }

    /**
     * 头像外网地址
     *
     * @return avatarOuterUrl
     **/
    @ApiModelProperty(value = "头像外网地址")


    public String getAvatarOuterUrl() {
        return avatarOuterUrl;
    }

    public void setAvatarOuterUrl(String avatarOuterUrl) {
        this.avatarOuterUrl = avatarOuterUrl;
    }

    public TeacherInfoVo collegeId(String collegeId) {
        this.collegeId = collegeId;
        return this;
    }

    /**
     * 学院id
     *
     * @return collegeId
     **/
    @ApiModelProperty(value = "学院id")


    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public TeacherInfoVo collegeName(String collegeName) {
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

    public TeacherInfoVo dataSource(Integer dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    /**
     * 数据来源 0：校内；1：校外
     *
     * @return dataSource
     **/
    @ApiModelProperty(value = "数据来源 0：校内；1：校外")


    public Integer getDataSource() {
        return dataSource;
    }

    public void setDataSource(Integer dataSource) {
        this.dataSource = dataSource;
    }

    public TeacherInfoVo schoolName(String schoolName) {
        this.schoolName = schoolName;
        return this;
    }

    /**
     * 学校名称
     *
     * @return schoolName
     **/
    @ApiModelProperty(value = "学校名称")


    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public TeacherInfoVo jobTitle(String jobTitle) {
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

    public TeacherInfoVo teacherProfile(String teacherProfile) {
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

    public TeacherInfoVo photoFileDisplayName(String photoFileDisplayName) {
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

    public TeacherInfoVo photoFileRealName(String photoFileRealName) {
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

    public TeacherInfoVo photoFileType(String photoFileType) {
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

    public TeacherInfoVo photoInnerUrl(String photoInnerUrl) {
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

    public TeacherInfoVo photoOuterUrl(String photoOuterUrl) {
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

    public TeacherInfoVo photoFilePath(String photoFilePath) {
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

    public TeacherInfoVo photoFileSize(String photoFileSize) {
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
        TeacherInfoVo teacherInfoVo = (TeacherInfoVo) o;
        return Objects.equals(this.teacherId, teacherInfoVo.teacherId) &&
                Objects.equals(this.teachingTeamId, teacherInfoVo.teachingTeamId) &&
                Objects.equals(this.teacherNo, teacherInfoVo.teacherNo) &&
                Objects.equals(this.teacherName, teacherInfoVo.teacherName) &&
                Objects.equals(this.isLeader, teacherInfoVo.isLeader) &&
                Objects.equals(this.avatarPath, teacherInfoVo.avatarPath) &&
                Objects.equals(this.avatarInnerUrl, teacherInfoVo.avatarInnerUrl) &&
                Objects.equals(this.avatarOuterUrl, teacherInfoVo.avatarOuterUrl) &&
                Objects.equals(this.collegeId, teacherInfoVo.collegeId) &&
                Objects.equals(this.collegeName, teacherInfoVo.collegeName) &&
                Objects.equals(this.dataSource, teacherInfoVo.dataSource) &&
                Objects.equals(this.schoolName, teacherInfoVo.schoolName) &&
                Objects.equals(this.jobTitle, teacherInfoVo.jobTitle) &&
                Objects.equals(this.teacherProfile, teacherInfoVo.teacherProfile) &&
                Objects.equals(this.photoFileDisplayName, teacherInfoVo.photoFileDisplayName) &&
                Objects.equals(this.photoFileRealName, teacherInfoVo.photoFileRealName) &&
                Objects.equals(this.photoFileType, teacherInfoVo.photoFileType) &&
                Objects.equals(this.photoInnerUrl, teacherInfoVo.photoInnerUrl) &&
                Objects.equals(this.photoOuterUrl, teacherInfoVo.photoOuterUrl) &&
                Objects.equals(this.photoFilePath, teacherInfoVo.photoFilePath) &&
                Objects.equals(this.photoFileSize, teacherInfoVo.photoFileSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, teachingTeamId, teacherNo, teacherName, isLeader, avatarPath, avatarInnerUrl,
                avatarOuterUrl, collegeId, collegeName, dataSource, schoolName, jobTitle, teacherProfile,
                photoFileDisplayName, photoFileRealName, photoFileType, photoInnerUrl, photoOuterUrl,
                photoFilePath, photoFileSize);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TeacherInfoVo {\n");

        sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
        sb.append("    teachingTeamId: ").append(toIndentedString(teachingTeamId)).append("\n");
        sb.append("    teacherNo: ").append(toIndentedString(teacherNo)).append("\n");
        sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
        sb.append("    isLeader: ").append(toIndentedString(isLeader)).append("\n");
        sb.append("    avatarPath: ").append(toIndentedString(avatarPath)).append("\n");
        sb.append("    avatarInnerUrl: ").append(toIndentedString(avatarInnerUrl)).append("\n");
        sb.append("    avatarOuterUrl: ").append(toIndentedString(avatarOuterUrl)).append("\n");
        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
        sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
        sb.append("    dataSource: ").append(toIndentedString(dataSource)).append("\n");
        sb.append("    schoolName: ").append(toIndentedString(schoolName)).append("\n");
        sb.append("    jobTitle: ").append(toIndentedString(jobTitle)).append("\n");
        sb.append("    teacherProfile: ").append(toIndentedString(teacherProfile)).append("\n");
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

