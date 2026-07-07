package com.lztech.site.viewmodel.coursetabledetailteacher;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * AutonomousClassTeacher
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-25T03:18:16.145Z")
public class AutonomousClassTeacher {
    @JsonProperty("teacherId")
    private String teacherId = null;

    @JsonProperty("teacherNo")
    private String teacherNo = null;

    @JsonProperty("teacherName")
    private String teacherName = null;

    @JsonProperty("teacherCollegeName")
    private String teacherCollegeName = null;

    @JsonProperty("teacherCollegeId")
    private String teacherCollegeId = null;

    public AutonomousClassTeacher teacherId(String teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    /**
     * 老师Id
     *
     * @return teacherId
     **/
    @ApiModelProperty(value = "老师Id")


    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public AutonomousClassTeacher teacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
        return this;
    }

    /**
     * 老师编号
     *
     * @return teacherNo
     **/
    @ApiModelProperty(value = "老师编号")


    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    public AutonomousClassTeacher teacherName(String teacherName) {
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

    public AutonomousClassTeacher teacherCollegeName(String teacherCollegeName) {
        this.teacherCollegeName = teacherCollegeName;
        return this;
    }

    /**
     * 老师所属学院名称
     *
     * @return teacherCollegeName
     **/
    @ApiModelProperty(value = "老师所属学院名称")


    public String getTeacherCollegeName() {
        return teacherCollegeName;
    }

    public void setTeacherCollegeName(String teacherCollegeName) {
        this.teacherCollegeName = teacherCollegeName;
    }

    public AutonomousClassTeacher teacherCollegeId(String teacherCollegeId) {
        this.teacherCollegeId = teacherCollegeId;
        return this;
    }

    /**
     * 老师所属学院Id
     *
     * @return teacherCollegeId
     **/
    @ApiModelProperty(value = "老师所属学院Id")


    public String getTeacherCollegeId() {
        return teacherCollegeId;
    }

    public void setTeacherCollegeId(String teacherCollegeId) {
        this.teacherCollegeId = teacherCollegeId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AutonomousClassTeacher autonomousClassTeacher = (AutonomousClassTeacher) o;
        return Objects.equals(this.teacherId, autonomousClassTeacher.teacherId) &&
                Objects.equals(this.teacherNo, autonomousClassTeacher.teacherNo) &&
                Objects.equals(this.teacherName, autonomousClassTeacher.teacherName) &&
                Objects.equals(this.teacherCollegeName, autonomousClassTeacher.teacherCollegeName) &&
                Objects.equals(this.teacherCollegeId, autonomousClassTeacher.teacherCollegeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, teacherNo, teacherName, teacherCollegeName, teacherCollegeId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AutonomousClassTeacher {\n");

        sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
        sb.append("    teacherNo: ").append(toIndentedString(teacherNo)).append("\n");
        sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
        sb.append("    teacherCollegeName: ").append(toIndentedString(teacherCollegeName)).append("\n");
        sb.append("    teacherCollegeId: ").append(toIndentedString(teacherCollegeId)).append("\n");
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

