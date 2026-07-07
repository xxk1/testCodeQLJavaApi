package com.lztech.site.viewmodel.teachingcenterstatistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * TeachingCenterJobTitleStatistics
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-06-16T02:29:30.517Z")

public class TeachingCenterJobTitleStatistics {
    @JsonProperty("jobTitleName")
    private String jobTitleName = null;

    @JsonProperty("teacherNum")
    private Integer teacherNum = null;

    public TeachingCenterJobTitleStatistics jobTitleName(String jobTitleName) {
        this.jobTitleName = jobTitleName;
        return this;
    }

    /**
     * 职称名称
     *
     * @return jobTitleName
     **/
    @ApiModelProperty(value = "职称名称")


    public String getJobTitleName() {
        return jobTitleName;
    }

    public void setJobTitleName(String jobTitleName) {
        this.jobTitleName = jobTitleName;
    }

    public TeachingCenterJobTitleStatistics teacherNum(Integer teacherNum) {
        this.teacherNum = teacherNum;
        return this;
    }

    /**
     * 老师数量
     *
     * @return teacherNum
     **/
    @ApiModelProperty(value = "老师数量")


    public Integer getTeacherNum() {
        return teacherNum;
    }

    public void setTeacherNum(Integer teacherNum) {
        this.teacherNum = teacherNum;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeachingCenterJobTitleStatistics teachingCenterJobTitleStatistics = (TeachingCenterJobTitleStatistics) o;
        return Objects.equals(this.jobTitleName, teachingCenterJobTitleStatistics.jobTitleName) &&
                Objects.equals(this.teacherNum, teachingCenterJobTitleStatistics.teacherNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobTitleName, teacherNum);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TeachingCenterJobTitleStatistics {\n");

        sb.append("    jobTitleName: ").append(toIndentedString(jobTitleName)).append("\n");
        sb.append("    teacherNum: ").append(toIndentedString(teacherNum)).append("\n");
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

