package com.lztech.site.viewmodel.coursechaptergoal;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseChapterGoalVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-02-26T02:15:05.872Z")


public class CourseChapterGoalVo {
    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("versionId")
    private String versionId = null;

    @JsonProperty("structureId")
    private String structureId = null;

    @JsonProperty("userId")
    private String userId = null;

    @JsonProperty("userName")
    private String userName = null;

    @JsonProperty("goalList")
    @Valid
    private List<CourseChapterGoalResource> goalList = null;

    public CourseChapterGoalVo courseId(String courseId) {
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

    public CourseChapterGoalVo versionId(String versionId) {
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

    public CourseChapterGoalVo structureId(String structureId) {
        this.structureId = structureId;
        return this;
    }

    /**
     * 章节id
     *
     * @return structureId
     **/
    @ApiModelProperty(value = "章节id")


    public String getStructureId() {
        return structureId;
    }

    public void setStructureId(String structureId) {
        this.structureId = structureId;
    }

    public CourseChapterGoalVo userId(String userId) {
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

    public CourseChapterGoalVo userName(String userName) {
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

    public CourseChapterGoalVo goalList(List<CourseChapterGoalResource> goalList) {
        this.goalList = goalList;
        return this;
    }

    public CourseChapterGoalVo addGoalListItem(CourseChapterGoalResource goalListItem) {
        if (this.goalList == null) {
            this.goalList = new ArrayList<CourseChapterGoalResource>();
        }
        this.goalList.add(goalListItem);
        return this;
    }

    /**
     * 目标列表
     *
     * @return goalList
     **/
    @ApiModelProperty(value = "目标列表")

    @Valid

    public List<CourseChapterGoalResource> getGoalList() {
        return goalList;
    }

    public void setGoalList(List<CourseChapterGoalResource> goalList) {
        this.goalList = goalList;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseChapterGoalVo courseChapterGoalVo = (CourseChapterGoalVo) o;
        return Objects.equals(this.courseId, courseChapterGoalVo.courseId) &&
                Objects.equals(this.versionId, courseChapterGoalVo.versionId) &&
                Objects.equals(this.structureId, courseChapterGoalVo.structureId) &&
                Objects.equals(this.userId, courseChapterGoalVo.userId) &&
                Objects.equals(this.userName, courseChapterGoalVo.userName) &&
                Objects.equals(this.goalList, courseChapterGoalVo.goalList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, versionId, structureId, userId, userName, goalList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseChapterGoalVo {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    versionId: ").append(toIndentedString(versionId)).append("\n");
        sb.append("    structureId: ").append(toIndentedString(structureId)).append("\n");
        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
        sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
        sb.append("    goalList: ").append(toIndentedString(goalList)).append("\n");
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

