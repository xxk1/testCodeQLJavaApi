package com.lztech.site.viewmodel.experimentschedulecoursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ExperimentScheduleCourseTableDetailTeacherVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-09-14T06:34:55.092Z")


public class ExperimentScheduleCourseTableDetailTeacherVo {
    @JsonProperty("teacherId")
    private String teacherId = null;

    @JsonProperty("teacherNo")
    private String teacherNo = null;

    @JsonProperty("teacherName")
    private String teacherName = null;

    @JsonProperty("teacherCollegeId")
    private String teacherCollegeId = null;

    @JsonProperty("teacherCollegeName")
    private String teacherCollegeName = null;

    public ExperimentScheduleCourseTableDetailTeacherVo teacherId(String teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    /**
     * 上课老师Id
     *
     * @return teacherId
     **/
    @ApiModelProperty(value = "上课老师Id", position = 1, required = true)


    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public ExperimentScheduleCourseTableDetailTeacherVo teacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
        return this;
    }

    /**
     * 上课老师工号
     *
     * @return teacherNo
     **/
    @ApiModelProperty(value = "上课老师工号", position = 2, required = true)


    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    public ExperimentScheduleCourseTableDetailTeacherVo teacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    /**
     * 上课老师名称
     *
     * @return teacherName
     **/
    @ApiModelProperty(value = "上课老师名称", position = 3, required = true)


    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public ExperimentScheduleCourseTableDetailTeacherVo teacherCollegeId(String teacherCollegeId) {
        this.teacherCollegeId = teacherCollegeId;
        return this;
    }

    /**
     * 上课老师所属学院id
     *
     * @return teacherCollegeId
     **/
    @ApiModelProperty(value = "上课老师所属学院id", position = 4, required = true)


    public String getTeacherCollegeId() {
        return teacherCollegeId;
    }

    public void setTeacherCollegeId(String teacherCollegeId) {
        this.teacherCollegeId = teacherCollegeId;
    }

    public ExperimentScheduleCourseTableDetailTeacherVo teacherCollegeName(String teacherCollegeName) {
        this.teacherCollegeName = teacherCollegeName;
        return this;
    }

    /**
     * 上课老师所属学院名称
     *
     * @return teacherCollegeName
     **/
    @ApiModelProperty(value = "上课老师所属学院名称", position = 5, required = true)


    public String getTeacherCollegeName() {
        return teacherCollegeName;
    }

    public void setTeacherCollegeName(String teacherCollegeName) {
        this.teacherCollegeName = teacherCollegeName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExperimentScheduleCourseTableDetailTeacherVo experimentScheduleCourseTableDetailTeacherVo =
                (ExperimentScheduleCourseTableDetailTeacherVo) o;
        return Objects.equals(this.teacherId, experimentScheduleCourseTableDetailTeacherVo.teacherId) &&
                Objects.equals(this.teacherNo, experimentScheduleCourseTableDetailTeacherVo.teacherNo) &&
                Objects.equals(this.teacherName, experimentScheduleCourseTableDetailTeacherVo.teacherName) &&
                Objects.equals(this.teacherCollegeId, experimentScheduleCourseTableDetailTeacherVo.teacherCollegeId) &&
                Objects.equals(this.teacherCollegeName, experimentScheduleCourseTableDetailTeacherVo.teacherCollegeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, teacherNo, teacherName, teacherCollegeId, teacherCollegeName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExperimentScheduleCourseTableDetailTeacherVo {\n");

        sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
        sb.append("    teacherNo: ").append(toIndentedString(teacherNo)).append("\n");
        sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
        sb.append("    teacherCollegeId: ").append(toIndentedString(teacherCollegeId)).append("\n");
        sb.append("    teacherCollegeName: ").append(toIndentedString(teacherCollegeName)).append("\n");
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

