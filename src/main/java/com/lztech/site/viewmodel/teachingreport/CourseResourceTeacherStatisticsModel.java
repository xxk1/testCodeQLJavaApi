package com.lztech.site.viewmodel.teachingreport;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseResourceTeacherStatisticsModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-01-03T03:12:14.631Z")


public class CourseResourceTeacherStatisticsModel {
    @JsonProperty("teacherId")
    private String teacherId = null;

    @JsonProperty("resourceNum")
    private String resourceNum = null;

    public CourseResourceTeacherStatisticsModel teacherId(String teacherId) {
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

    public CourseResourceTeacherStatisticsModel resourceNum(String resourceNum) {
        this.resourceNum = resourceNum;
        return this;
    }

    /**
     * 资源个数
     *
     * @return resourceNum
     **/
    @ApiModelProperty(value = "资源个数")


    public String getResourceNum() {
        return resourceNum;
    }

    public void setResourceNum(String resourceNum) {
        this.resourceNum = resourceNum;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseResourceTeacherStatisticsModel courseResourceTeacherStatisticsModel = (CourseResourceTeacherStatisticsModel) o;
        return Objects.equals(this.teacherId, courseResourceTeacherStatisticsModel.teacherId) &&
                Objects.equals(this.resourceNum, courseResourceTeacherStatisticsModel.resourceNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, resourceNum);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseResourceTeacherStatisticsModel {\n");

        sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
        sb.append("    resourceNum: ").append(toIndentedString(resourceNum)).append("\n");
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

