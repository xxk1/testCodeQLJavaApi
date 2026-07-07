package com.lztech.site.viewmodel.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseImageInfoVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-04-09T06:58:32.214Z")


public class CourseImageInfoVo {
    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseCode")
    private String courseCode = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("innerIp")
    private String innerIp = null;

    @JsonProperty("outerIp")
    private String outerIp = null;

    @JsonProperty("filePath")
    private String filePath = null;

    public CourseImageInfoVo courseId(String courseId) {
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

    public CourseImageInfoVo courseCode(String courseCode) {
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


    public CourseImageInfoVo courseName(String courseName) {
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

    public CourseImageInfoVo innerIp(String innerIp) {
        this.innerIp = innerIp;
        return this;
    }

    /**
     * 内网ip
     *
     * @return innerIp
     **/
    @ApiModelProperty(value = "内网ip")


    public String getInnerIp() {
        return innerIp;
    }

    public void setInnerIp(String innerIp) {
        this.innerIp = innerIp;
    }

    public CourseImageInfoVo outerIp(String outerIp) {
        this.outerIp = outerIp;
        return this;
    }

    /**
     * 外网ip
     *
     * @return outerIp
     **/
    @ApiModelProperty(value = "外网ip")


    public String getOuterIp() {
        return outerIp;
    }

    public void setOuterIp(String outerIp) {
        this.outerIp = outerIp;
    }


    public CourseImageInfoVo filePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    /**
     * 文件路径
     *
     * @return filePath
     **/
    @ApiModelProperty(value = "文件路径")


    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseImageInfoVo courseImageInfoVo = (CourseImageInfoVo) o;
        return Objects.equals(this.courseId, courseImageInfoVo.courseId) &&
                Objects.equals(this.courseCode, courseImageInfoVo.courseCode) &&
                Objects.equals(this.courseName, courseImageInfoVo.courseName) &&
                Objects.equals(this.innerIp, courseImageInfoVo.innerIp) &&
                Objects.equals(this.outerIp, courseImageInfoVo.outerIp) &&
                Objects.equals(this.filePath, courseImageInfoVo.filePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseCode,  courseName,innerIp, outerIp,filePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseImageInfoVo {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    innerIp: ").append(toIndentedString(innerIp)).append("\n");
        sb.append("    outerIp: ").append(toIndentedString(outerIp)).append("\n");
        sb.append("    filePath: ").append(toIndentedString(filePath)).append("\n");
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

