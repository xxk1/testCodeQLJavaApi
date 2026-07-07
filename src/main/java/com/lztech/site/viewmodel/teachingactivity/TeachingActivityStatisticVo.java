package com.lztech.site.viewmodel.teachingactivity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * TeachingActivityStatisticVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-03-28T08:23:44.244Z")


public class TeachingActivityStatisticVo {
    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("courseCode")
    private String courseCode = null;

    @JsonProperty("courseCollege")
    private String courseCollege = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("allCount")
    private BigDecimal allCount = null;

    @JsonProperty("testCount")
    private BigDecimal testCount = null;

    @JsonProperty("themeCount")
    private BigDecimal themeCount = null;
    
    public TeachingActivityStatisticVo courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    /**
     * 课程ID
     *
     * @return courseId
     **/
    @ApiModelProperty(value = "课程ID")


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public TeachingActivityStatisticVo courseName(String courseName) {
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

    public TeachingActivityStatisticVo courseCode(String courseCode) {
        this.courseCode = courseCode;
        return this;
    }

    /**
     * 课程编号
     *
     * @return courseCode
     **/
    @ApiModelProperty(value = "课程编号")


    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public TeachingActivityStatisticVo courseCollege(String courseCollege) {
        this.courseCollege = courseCollege;
        return this;
    }

    /**
     * 所属学院
     *
     * @return courseCollege
     **/
    @ApiModelProperty(value = "所属学院")


    public String getCourseCollege() {
        return courseCollege;
    }

    public void setCourseCollege(String courseCollege) {
        this.courseCollege = courseCollege;
    }

    public TeachingActivityStatisticVo collegeId(String collegeId) {
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

    public TeachingActivityStatisticVo allCount(BigDecimal allCount) {
        this.allCount = allCount;
        return this;
    }

    /**
     * 活动总数
     *
     * @return allCount
     **/
    @ApiModelProperty(value = "活动总数")


    public BigDecimal getAllCount() {
        return allCount;
    }

    public void setAllCount(BigDecimal allCount) {
        this.allCount = allCount;
    }

    public TeachingActivityStatisticVo testCount(BigDecimal testCount) {
        this.testCount = testCount;
        return this;
    }

    /**
     * 考核测验
     *
     * @return testCount
     **/
    @ApiModelProperty(value = "考核测验")


    public BigDecimal getTestCount() {
        return testCount;
    }

    public void setTestCount(BigDecimal testCount) {
        this.testCount = testCount;
    }

    public TeachingActivityStatisticVo themeCount(BigDecimal themeCount) {
        this.themeCount = themeCount;
        return this;
    }

    /**
     * 讨论主题
     *
     * @return themeCount
     **/
    @ApiModelProperty(value = "讨论主题")


    public BigDecimal getThemeCount() {
        return themeCount;
    }

    public void setThemeCount(BigDecimal themeCount) {
        this.themeCount = themeCount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeachingActivityStatisticVo teachingActivityStatisticVo = (TeachingActivityStatisticVo) o;
        return Objects.equals(this.courseId, teachingActivityStatisticVo.courseId) &&
                Objects.equals(this.courseName, teachingActivityStatisticVo.courseName) &&
                Objects.equals(this.courseCode, teachingActivityStatisticVo.courseCode) &&
                Objects.equals(this.courseCollege, teachingActivityStatisticVo.courseCollege) &&
                Objects.equals(this.collegeId, teachingActivityStatisticVo.collegeId) &&
                Objects.equals(this.allCount, teachingActivityStatisticVo.allCount) &&
                Objects.equals(this.testCount, teachingActivityStatisticVo.testCount) &&
                Objects.equals(this.themeCount, teachingActivityStatisticVo.themeCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseName, courseCode, courseCollege, collegeId, allCount, testCount, themeCount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TeachingActivityStatisticVo {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
        sb.append("    courseCollege: ").append(toIndentedString(courseCollege)).append("\n");
        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
        sb.append("    allCount: ").append(toIndentedString(allCount)).append("\n");
        sb.append("    testCount: ").append(toIndentedString(testCount)).append("\n");
        sb.append("    themeCount: ").append(toIndentedString(themeCount)).append("\n");
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

