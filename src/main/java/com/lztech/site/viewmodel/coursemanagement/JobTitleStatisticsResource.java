package com.lztech.site.viewmodel.coursemanagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * JobTitleStatisticsResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-02-21T07:43:39.378Z")


public class JobTitleStatisticsResource {
    @JsonProperty("jobTitle")
    private String jobTitle = null;

    @JsonProperty("teacherNum")
    private Integer teacherNum = null;

    public JobTitleStatisticsResource jobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
        return this;
    }

    /**
     * 职称
     *
     * @return jobTitle
     **/
    @ApiModelProperty(value = "职称")


    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public JobTitleStatisticsResource teacherNum(Integer teacherNum) {
        this.teacherNum = teacherNum;
        return this;
    }

    /**
     * 教师数量
     *
     * @return teacherNum
     **/
    @ApiModelProperty(value = "教师数量")


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
        JobTitleStatisticsResource jobTitleStatisticsResource = (JobTitleStatisticsResource) o;
        return Objects.equals(this.jobTitle, jobTitleStatisticsResource.jobTitle) &&
                Objects.equals(this.teacherNum, jobTitleStatisticsResource.teacherNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobTitle, teacherNum);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class JobTitleStatisticsResource {\n");

        sb.append("    jobTitle: ").append(toIndentedString(jobTitle)).append("\n");
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

