package com.lztech.site.viewmodel.teachingreport;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseResourceStatisticsTeacherVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-12-28T03:34:46.082Z")


public class CourseResourceStatisticsTeacherVo {
    @JsonProperty("teacherId")
    private String teacherId = null;
    @JsonProperty("uploadType")
    private Integer uploadType = null;

    public CourseResourceStatisticsTeacherVo teacherId(String teacherId) {
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

    public CourseResourceStatisticsTeacherVo uploadType(Integer uploadType) {
        this.uploadType = uploadType;
        return this;
    }

    /**
     * 上传状态：0 未上传，已上传
     *
     * @return uploadType
     **/
    @ApiModelProperty(value = "上传状态：0 未上传，已上传")


    public Integer getUploadType() {
        return uploadType;
    }

    public void setUploadType(Integer uploadType) {
        this.uploadType = uploadType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseResourceStatisticsTeacherVo courseResourceStatisticsTeacherVo = (CourseResourceStatisticsTeacherVo) o;
        return Objects.equals(this.teacherId, courseResourceStatisticsTeacherVo.teacherId) &&
                Objects.equals(this.uploadType, courseResourceStatisticsTeacherVo.uploadType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, uploadType);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseResourceStatisticsTeacherVo {\n");
        sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
        sb.append("    uploadType: ").append(toIndentedString(uploadType)).append("\n");
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

