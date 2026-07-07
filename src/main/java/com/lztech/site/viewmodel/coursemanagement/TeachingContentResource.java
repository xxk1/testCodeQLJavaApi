package com.lztech.site.viewmodel.coursemanagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * TeachingContentResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-04-07T06:04:05.884Z")


public class TeachingContentResource {
    @JsonProperty("userId")
    private String userId = null;

    @JsonProperty("userName")
    private String userName = null;

    @JsonProperty("source")
    private Integer source = null;

    @JsonProperty("courseStructureId")
    private String courseStructureId = null;

    @JsonProperty("resourceType")
    private Integer resourceType = null;

    @JsonProperty("isPublic")
    private Boolean isPublic = null;

    @JsonProperty("fileList")
    @Valid
    private List<TeachingContentFileResource> fileList = null;

    public TeachingContentResource userId(String userId) {
        this.userId = userId;
        return this;
    }

    /**
     * 用户Id
     *
     * @return userId
     **/
    @ApiModelProperty(value = "用户Id")


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public TeachingContentResource userName(String userName) {
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

    public TeachingContentResource source(Integer source) {
        this.source = source;
        return this;
    }

    /**
     * 来源：0:教师备课，1:教学内容
     *
     * @return source
     **/
    @ApiModelProperty(value = "来源：0:教师备课，1:教学内容")


    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public TeachingContentResource courseStructureId(String courseStructureId) {
        this.courseStructureId = courseStructureId;
        return this;
    }

    /**
     * 课程结构Id
     *
     * @return courseStructureId
     **/
    @ApiModelProperty(value = "课程结构Id")


    public String getCourseStructureId() {
        return courseStructureId;
    }

    public void setCourseStructureId(String courseStructureId) {
        this.courseStructureId = courseStructureId;
    }

    public TeachingContentResource resourceType(Integer resourceType) {
        this.resourceType = resourceType;
        return this;
    }

    /**
     * 资源类型 0：教学课件 1：视频 2:测验 3：图片 5:音频
     *
     * @return resourceType
     **/
    @ApiModelProperty(value = "资源类型 0：教学课件 1：视频 2:测验 3：图片 5:音频")


    public Integer getResourceType() {
        return resourceType;
    }

    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    public TeachingContentResource isPublic(Boolean isPublic) {
        this.isPublic = isPublic;
        return this;
    }

    /**
     * 是否为公共资源 true：公共资源 false：私有资源
     *
     * @return isPublic
     **/
    @ApiModelProperty(value = "是否为公共资源 true：公共资源 false：私有资源")


    public Boolean isIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public TeachingContentResource fileList(List<TeachingContentFileResource> fileList) {
        this.fileList = fileList;
        return this;
    }

    public TeachingContentResource addFileListItem(TeachingContentFileResource fileListItem) {
        if (this.fileList == null) {
            this.fileList = new ArrayList<TeachingContentFileResource>();
        }
        this.fileList.add(fileListItem);
        return this;
    }

    /**
     * Get fileList
     *
     * @return fileList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<TeachingContentFileResource> getFileList() {
        return fileList;
    }

    public void setFileList(List<TeachingContentFileResource> fileList) {
        this.fileList = fileList;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeachingContentResource teachingContentResource = (TeachingContentResource) o;
        return Objects.equals(this.userId, teachingContentResource.userId) &&
                Objects.equals(this.userName, teachingContentResource.userName) &&
                Objects.equals(this.source, teachingContentResource.source) &&
                Objects.equals(this.courseStructureId, teachingContentResource.courseStructureId) &&
                Objects.equals(this.resourceType, teachingContentResource.resourceType) &&
                Objects.equals(this.isPublic, teachingContentResource.isPublic) &&
                Objects.equals(this.fileList, teachingContentResource.fileList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, source, courseStructureId, resourceType, isPublic, fileList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TeachingContentResource {\n");

        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
        sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
        sb.append("    source: ").append(toIndentedString(source)).append("\n");
        sb.append("    courseStructureId: ").append(toIndentedString(courseStructureId)).append("\n");
        sb.append("    resourceType: ").append(toIndentedString(resourceType)).append("\n");
        sb.append("    isPublic: ").append(toIndentedString(isPublic)).append("\n");
        sb.append("    fileList: ").append(toIndentedString(fileList)).append("\n");
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

