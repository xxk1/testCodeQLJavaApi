package com.lztech.site.viewmodel.teachingreport;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * TeachingReportCourseResourceStatisticsVol
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-12-28T03:34:46.082Z")


public class TeachingReportCourseResourceStatisticsVo {
    @JsonProperty("courseResourceNum")
    private Integer courseResourceNum = 0;

    @JsonProperty("joinTeacherNumber")
    private Integer joinTeacherNumber = 0;
    @JsonProperty("teacherTotalNumber")
    private Integer teacherTotalNumber = 0;
    @JsonProperty("resourceReferenceNum")
    private Integer resourceReferenceNum = 0;
    @JsonProperty("teachingCourseWareNum")
    private Integer teachingCourseWareNum = 0;
    @JsonProperty("microVideoNum")
    private Integer microVideoNum = 0;
    @JsonProperty("imageNum")
    private Integer imageNum = 0;
    @JsonProperty("classTestNum")
    private Integer classTestNum = 0;
    @JsonProperty("knowledgePointNum")
    private Integer knowledgePointNum = 0;
    @JsonProperty("themeNum")
    private Integer themeNum = 0;

    @JsonProperty("teacherList")
    @Valid
    private List<CourseResourceStatisticsTeacherVo> teacherList = new ArrayList<>();

    public TeachingReportCourseResourceStatisticsVo courseResourceNum(Integer courseResourceNum) {
        this.courseResourceNum = courseResourceNum;
        return this;
    }

    /**
     * 课程资源个数
     *
     * @return courseResourceNum
     **/
    @ApiModelProperty(value = "课程资源个数")

    public Integer getCourseResourceNum() {
        return courseResourceNum;
    }

    public void setCourseResourceNum(Integer courseResourceNum) {
        this.courseResourceNum = courseResourceNum;
    }

    public TeachingReportCourseResourceStatisticsVo joinTeacherNumber(Integer joinTeacherNumber) {
        this.joinTeacherNumber = joinTeacherNumber;
        return this;
    }

    /**
     * 参与老师个数
     *
     * @return joinTeacherNumber
     **/
    @ApiModelProperty(value = "参与老师个数")
    public Integer getJoinTeacherNumber() {
        return joinTeacherNumber;
    }

    public void setJoinTeacherNumber(Integer joinTeacherNumber) {
        this.joinTeacherNumber = joinTeacherNumber;
    }

    public TeachingReportCourseResourceStatisticsVo teacherTotalNumber(Integer teacherTotalNumber) {
        this.teacherTotalNumber = teacherTotalNumber;
        return this;
    }

    /**
     * 老师总个数
     *
     * @return teacherTotalNumber
     **/
    @ApiModelProperty(value = "老师总个数")
    public Integer getTeacherTotalNumber() {
        return teacherTotalNumber;
    }

    public void setTeacherTotalNumber(Integer teacherTotalNumber) {
        this.teacherTotalNumber = teacherTotalNumber;
    }

    public TeachingReportCourseResourceStatisticsVo resourceReferenceNum(Integer resourceReferenceNum) {
        this.resourceReferenceNum = resourceReferenceNum;
        return this;
    }

    /**
     * 资源引用个数
     *
     * @return resourceReferenceNum
     **/
    @ApiModelProperty(value = "资源引用个数")
    public Integer getResourceReferenceNum() {
        return resourceReferenceNum;
    }

    public void setResourceReferenceNum(Integer resourceReferenceNum) {
        this.resourceReferenceNum = resourceReferenceNum;
    }

    public TeachingReportCourseResourceStatisticsVo teachingCourseWareNum(Integer teachingCourseWareNum) {
        this.teachingCourseWareNum = teachingCourseWareNum;
        return this;
    }

    /**
     * 教学课件个数
     *
     * @return teachingCourseWareNum
     **/
    @ApiModelProperty(value = "教学课件个数")
    public Integer getTeachingCourseWareNum() {
        return teachingCourseWareNum;
    }

    public void setTeachingCourseWareNum(Integer teachingCourseWareNum) {
        this.teachingCourseWareNum = teachingCourseWareNum;
    }

    public TeachingReportCourseResourceStatisticsVo microVideoNum(Integer microVideoNum) {
        this.microVideoNum = microVideoNum;
        return this;
    }

    /**
     * 视频个数
     *
     * @return microVideoNum
     **/
    @ApiModelProperty(value = "视频个数")
    public Integer getMicroVideoNum() {
        return microVideoNum;
    }

    public void setMicroVideoNum(Integer microVideoNum) {
        this.microVideoNum = microVideoNum;
    }

    public TeachingReportCourseResourceStatisticsVo imageNum(Integer imageNum) {
        this.imageNum = imageNum;
        return this;
    }

    /**
     * 图片个数
     *
     * @return imageNum
     **/
    @ApiModelProperty(value = "图片个数")
    public Integer getImageNum() {
        return imageNum;
    }

    public void setImageNum(Integer imageNum) {
        this.imageNum = imageNum;
    }

    public TeachingReportCourseResourceStatisticsVo classTestNum(Integer classTestNum) {
        this.classTestNum = classTestNum;
        return this;
    }

    /**
     * 测验个数
     *
     * @return classTestNum
     **/
    @ApiModelProperty(value = "测验个数")
    public Integer getClassTestNum() {
        return classTestNum;
    }

    public void setClassTestNum(Integer classTestNum) {
        this.classTestNum = classTestNum;
    }

    public TeachingReportCourseResourceStatisticsVo knowledgePointNum(Integer knowledgePointNum) {
        this.knowledgePointNum = knowledgePointNum;
        return this;
    }

    /**
     * 知识点个数
     *
     * @return knowledgePointNum
     **/
    @ApiModelProperty(value = "知识点个数")
    public Integer getKnowledgePointNum() {
        return knowledgePointNum;
    }

    public void setKnowledgePointNum(Integer knowledgePointNum) {
        this.knowledgePointNum = knowledgePointNum;
    }

    public TeachingReportCourseResourceStatisticsVo themeNum(Integer themeNum) {
        this.themeNum = themeNum;
        return this;
    }

    /**
     * 主题个数
     *
     * @return themeNum
     **/
    @ApiModelProperty(value = "主题个数")
    public Integer getThemeNum() {
        return themeNum;
    }

    public void setThemeNum(Integer themeNum) {
        this.themeNum = themeNum;
    }

    public TeachingReportCourseResourceStatisticsVo teacherList(List<CourseResourceStatisticsTeacherVo> courseResourceStatisticsTeacherVoList) {
        this.teacherList = teacherList;
        return this;
    }

    /**
     * 课程资源统计教师列表
     *
     * @return teacherList
     **/
    @ApiModelProperty(value = "课程资源统计教师列表")
    public List<CourseResourceStatisticsTeacherVo> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<CourseResourceStatisticsTeacherVo> teacherList) {
        this.teacherList = teacherList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeachingReportCourseResourceStatisticsVo teachingReportCourseResourceStatisticsVo = (TeachingReportCourseResourceStatisticsVo) o;
        return Objects.equals(this.courseResourceNum, teachingReportCourseResourceStatisticsVo.courseResourceNum) &&
                Objects.equals(this.joinTeacherNumber, teachingReportCourseResourceStatisticsVo.joinTeacherNumber) &&
                Objects.equals(this.teacherTotalNumber, teachingReportCourseResourceStatisticsVo.teacherTotalNumber) &&
                Objects.equals(this.resourceReferenceNum, teachingReportCourseResourceStatisticsVo.resourceReferenceNum) &&
                Objects.equals(this.teachingCourseWareNum, teachingReportCourseResourceStatisticsVo.teachingCourseWareNum) &&
                Objects.equals(this.microVideoNum, teachingReportCourseResourceStatisticsVo.microVideoNum) &&
                Objects.equals(this.imageNum, teachingReportCourseResourceStatisticsVo.imageNum) &&
                Objects.equals(this.classTestNum, teachingReportCourseResourceStatisticsVo.classTestNum) &&
                Objects.equals(this.knowledgePointNum, teachingReportCourseResourceStatisticsVo.knowledgePointNum) &&
                Objects.equals(this.themeNum, teachingReportCourseResourceStatisticsVo.themeNum) &&
                Objects.equals(this.teacherList, teachingReportCourseResourceStatisticsVo.teacherList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseResourceNum, joinTeacherNumber, teacherTotalNumber, resourceReferenceNum,
                teachingCourseWareNum, microVideoNum, imageNum, classTestNum, knowledgePointNum, themeNum, teacherList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TeachingReportCourseResourceStatisticsVo {\n");
        sb.append("    courseResourceNum: ").append(toIndentedString(courseResourceNum)).append("\n");
        sb.append("    joinTeacherNumber: ").append(toIndentedString(joinTeacherNumber)).append("\n");
        sb.append("    teacherTotalNumber: ").append(toIndentedString(teacherTotalNumber)).append("\n");
        sb.append("    resourceReferenceNum: ").append(toIndentedString(resourceReferenceNum)).append("\n");
        sb.append("    teachingCourseWareNum: ").append(toIndentedString(teachingCourseWareNum)).append("\n");
        sb.append("    microVideoNum: ").append(toIndentedString(microVideoNum)).append("\n");
        sb.append("    imageNum: ").append(toIndentedString(imageNum)).append("\n");
        sb.append("    classTestNum: ").append(toIndentedString(classTestNum)).append("\n");
        sb.append("    knowledgePointNum: ").append(toIndentedString(knowledgePointNum)).append("\n");
        sb.append("    themeNum: ").append(toIndentedString(themeNum)).append("\n");
        sb.append("    teacherList: ").append(toIndentedString(teacherList)).append("\n");
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

