package com.lztech.site.viewmodel.teachingcalendarcoursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * TeacherResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-08-20T12:04:45.217Z")

public class TeacherResource {
    @JsonProperty("teacherId")
    private String teacherId = null;

    @JsonProperty("teacherNo")
    private String teacherNo = null;

    @JsonProperty("teacherName")
    private String teacherName = null;

    @JsonProperty("teacherStatus")
    private Boolean teacherStatus = null;

    public TeacherResource teacherId(String teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    /**
     * 授课老师Id
     *
     * @return teacherId
     **/
    @ApiModelProperty(value = "授课老师Id")


    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public TeacherResource teacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
        return this;
    }

    /**
     * 授课老师No
     *
     * @return teacherNo
     **/
    @ApiModelProperty(value = "授课老师No")


    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    public TeacherResource teacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    /**
     * 授课老师名称
     *
     * @return teacherName
     **/
    @ApiModelProperty(value = "授课老师名称")


    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public TeacherResource teacherStatus(Boolean teacherStatus) {
        this.teacherStatus = teacherStatus;
        return this;
    }

    /**
     * 授课老师是否存在（true：存在；false：不存在）
     *
     * @return teacherStatus
     **/
    @ApiModelProperty(value = "授课老师是否存在（true：存在；false：不存在）")


    public Boolean isTeacherStatus() {
        return teacherStatus;
    }

    public void setTeacherStatus(Boolean teacherStatus) {
        this.teacherStatus = teacherStatus;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeacherResource teacherResource = (TeacherResource) o;
        return Objects.equals(this.teacherId, teacherResource.teacherId) &&
                Objects.equals(this.teacherNo, teacherResource.teacherNo) &&
                Objects.equals(this.teacherName, teacherResource.teacherName) &&
                Objects.equals(this.teacherStatus, teacherResource.teacherStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, teacherNo, teacherName, teacherStatus);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TeacherResource {\n");

        sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
        sb.append("    teacherNo: ").append(toIndentedString(teacherNo)).append("\n");
        sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
        sb.append("    teacherStatus: ").append(toIndentedString(teacherStatus)).append("\n");
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

