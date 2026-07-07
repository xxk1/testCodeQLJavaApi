package com.lztech.site.viewmodel.coursestatistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * CourseBuildStatisticsResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-09-30T02:48:37.861Z")


public class CourseBuildStatisticsResource {
    @JsonProperty("completeRate")
    private BigDecimal completeRate = null;

    @JsonProperty("rateGrowth")
    private BigDecimal rateGrowth = null;

    @JsonProperty("buildCourseNum")
    private Integer buildCourseNum = null;

    @JsonProperty("courseTeamNum")
    private Integer courseTeamNum = null;

    public CourseBuildStatisticsResource completeRate(BigDecimal completeRate) {
        this.completeRate = completeRate;
        return this;
    }

    /**
     * 完成率
     *
     * @return completeRate
     **/
    @ApiModelProperty(value = "完成率")


    public BigDecimal getCompleteRate() {
        return completeRate;
    }

    public void setCompleteRate(BigDecimal completeRate) {
        this.completeRate = completeRate;
    }

    public CourseBuildStatisticsResource rateGrowth(BigDecimal rateGrowth) {
        this.rateGrowth = rateGrowth;
        return this;
    }

    /**
     * 完成率较昨日增长
     *
     * @return rateGrowth
     **/
    @ApiModelProperty(value = "完成率较昨日增长")

    @Valid

    public BigDecimal getRateGrowth() {
        return rateGrowth;
    }

    public void setRateGrowth(BigDecimal rateGrowth) {
        this.rateGrowth = rateGrowth;
    }

    public CourseBuildStatisticsResource buildCourseNum(Integer buildCourseNum) {
        this.buildCourseNum = buildCourseNum;
        return this;
    }

    /**
     * 集体备课课程数
     *
     * @return buildCourseNum
     **/
    @ApiModelProperty(value = "集体备课课程数")


    public Integer getBuildCourseNum() {
        return buildCourseNum;
    }

    public void setBuildCourseNum(Integer buildCourseNum) {
        this.buildCourseNum = buildCourseNum;
    }

    public CourseBuildStatisticsResource courseTeamNum(Integer courseTeamNum) {
        this.courseTeamNum = courseTeamNum;
        return this;
    }

    /**
     * 教学团队人数
     *
     * @return courseTeamTeacherNum
     **/
    @ApiModelProperty(value = "教学团队数")


    public Integer getCourseTeamNum() {
        return courseTeamNum;
    }

    public void setCourseTeamTeacherNum(Integer courseTeamNum) {
        this.courseTeamNum = courseTeamNum;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseBuildStatisticsResource courseBuildStatisticsResource = (CourseBuildStatisticsResource) o;
        return Objects.equals(this.completeRate, courseBuildStatisticsResource.completeRate) &&
                Objects.equals(this.rateGrowth, courseBuildStatisticsResource.rateGrowth) &&
                Objects.equals(this.buildCourseNum, courseBuildStatisticsResource.buildCourseNum) &&
                Objects.equals(this.courseTeamNum, courseBuildStatisticsResource.courseTeamNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(completeRate, rateGrowth, buildCourseNum, courseTeamNum);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseBuildStatisticsResource {\n");

        sb.append("    completeRate: ").append(toIndentedString(completeRate)).append("\n");
        sb.append("    rateGrowth: ").append(toIndentedString(rateGrowth)).append("\n");
        sb.append("    buildCourseNum: ").append(toIndentedString(buildCourseNum)).append("\n");
        sb.append("    courseTeamNum: ").append(toIndentedString(courseTeamNum)).append("\n");
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

