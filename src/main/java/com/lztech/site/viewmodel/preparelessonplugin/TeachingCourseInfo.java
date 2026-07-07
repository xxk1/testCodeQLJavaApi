package com.lztech.site.viewmodel.preparelessonplugin;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TeachingCourseInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-04-12T11:52:44.035Z")

public class TeachingCourseInfo {
    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseCode")
    private String courseCode = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("versionId")
    private String versionId = null;

    @JsonProperty("courseTeachingTeamId")
    private String courseTeachingTeamId = null;

    @JsonProperty("courseStructureInfoList")
    @Valid
    private List<CourseStructureInfo> courseStructureInfoList = null;

    public TeachingCourseInfo versionId(String versionId) {
        this.versionId = versionId;
        return this;
    }

    /**
     * 版本id
     * @return versionId
     **/
    @ApiModelProperty(value = "课程Id")

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public TeachingCourseInfo courseId(String courseId) {
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

    public TeachingCourseInfo courseCode(String courseCode) {
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

    public TeachingCourseInfo courseName(String courseName) {
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

    public TeachingCourseInfo courseTeachingTeamId(String courseTeachingTeamId) {
        this.courseTeachingTeamId = courseTeachingTeamId;
        return this;
    }

    /**
     * 课程教学团队Id
     * @return courseTeachingTeamId
     **/
    @ApiModelProperty(value = "课程教学团队Id")


    public String getCourseTeachingTeamId() {
        return courseTeachingTeamId;
    }

    public void setCourseTeachingTeamId(String courseTeachingTeamId) {
        this.courseTeachingTeamId = courseTeachingTeamId;
    }

    public TeachingCourseInfo courseStructureInfoList(List<CourseStructureInfo> courseStructureInfoList) {
        this.courseStructureInfoList = courseStructureInfoList;
        return this;
    }

    public TeachingCourseInfo addCourseStructureInfoListItem(CourseStructureInfo courseStructureInfoListItem) {
        if (this.courseStructureInfoList == null) {
            this.courseStructureInfoList = new ArrayList<CourseStructureInfo>();
        }
        this.courseStructureInfoList.add(courseStructureInfoListItem);
        return this;
    }

    /**
     * Get courseStructureInfoList
     * @return courseStructureInfoList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<CourseStructureInfo> getCourseStructureInfoList() {
        return courseStructureInfoList;
    }

    public void setCourseStructureInfoList(List<CourseStructureInfo> courseStructureInfoList) {
        this.courseStructureInfoList = courseStructureInfoList;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeachingCourseInfo teachingCourseInfo = (TeachingCourseInfo) o;
        return Objects.equals(this.courseId, teachingCourseInfo.courseId) &&
                Objects.equals(this.courseCode, teachingCourseInfo.courseCode) &&
                Objects.equals(this.courseName, teachingCourseInfo.courseName) &&
                Objects.equals(this.courseTeachingTeamId, teachingCourseInfo.courseTeachingTeamId) &&
                Objects.equals(this.courseStructureInfoList, teachingCourseInfo.courseStructureInfoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseCode, courseName, courseTeachingTeamId, courseStructureInfoList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TeachingCourseInfo {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    courseTeachingTeamId: ").append(toIndentedString(courseTeachingTeamId)).append("\n");
        sb.append("    courseStructureInfoList: ").append(toIndentedString(courseStructureInfoList)).append("\n");
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