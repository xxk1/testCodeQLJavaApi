package com.lztech.site.viewmodel.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * GroupUserStatisticsResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-12-09T01:49:15.018Z")


public class GroupUserStatisticsResource {
    @JsonProperty("studentCount")
    private Integer studentCount = null;

    @JsonProperty("teacherCount")
    private Integer teacherCount = null;

    public GroupUserStatisticsResource studentCount(Integer studentCount) {
        this.studentCount = studentCount;
        return this;
    }

    /**
     * 学生总数
     *
     * @return studentCount
     **/
    @ApiModelProperty(value = "学生总数")


    public Integer getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }

    public GroupUserStatisticsResource teacherCount(Integer teacherCount) {
        this.teacherCount = teacherCount;
        return this;
    }

    /**
     * 老师总数
     *
     * @return teacherCount
     **/
    @ApiModelProperty(value = "老师总数")


    public Integer getTeacherCount() {
        return teacherCount;
    }

    public void setTeacherCount(Integer teacherCount) {
        this.teacherCount = teacherCount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GroupUserStatisticsResource groupUserStatisticsResource = (GroupUserStatisticsResource) o;
        return Objects.equals(this.studentCount, groupUserStatisticsResource.studentCount) &&
                Objects.equals(this.teacherCount, groupUserStatisticsResource.teacherCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentCount, teacherCount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GroupUserStatisticsResource {\n");

        sb.append("    studentCount: ").append(toIndentedString(studentCount)).append("\n");
        sb.append("    teacherCount: ").append(toIndentedString(teacherCount)).append("\n");
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

