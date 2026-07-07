package com.lztech.site.viewmodel.coursestatistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseResourceStatisticsResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-09-28T02:29:38.903Z")


public class CourseResourceStatisticsResource {
    @JsonProperty("totalNum")
    private Integer totalNum = null;

    @JsonProperty("teachingContentNum")
    private Integer teachingContentNum = null;

    @JsonProperty("teachingActivityNum")
    private Integer teachingActivityNum = null;

    public CourseResourceStatisticsResource totalNum(Integer totalNum) {
        this.totalNum = totalNum;
        return this;
    }

    /**
     * 总数
     *
     * @return totalNum
     **/
    @ApiModelProperty(value = "总数")


    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public CourseResourceStatisticsResource teachingContentNum(Integer teachingContentNum) {
        this.teachingContentNum = teachingContentNum;
        return this;
    }

    /**
     * 教学内容数
     *
     * @return teachingContentNum
     **/
    @ApiModelProperty(value = "教学内容数")


    public Integer getTeachingContentNum() {
        return teachingContentNum;
    }

    public void setTeachingContentNum(Integer teachingContentNum) {
        this.teachingContentNum = teachingContentNum;
    }

    public CourseResourceStatisticsResource teachingActivityNum(Integer teachingActivityNum) {
        this.teachingActivityNum = teachingActivityNum;
        return this;
    }

    /**
     * 教学活动数
     *
     * @return teachingActivityNum
     **/
    @ApiModelProperty(value = "教学活动数")


    public Integer getTeachingActivityNum() {
        return teachingActivityNum;
    }

    public void setTeachingActivityNum(Integer teachingActivityNum) {
        this.teachingActivityNum = teachingActivityNum;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseResourceStatisticsResource courseResourceStatisticsResource = (CourseResourceStatisticsResource) o;
        return Objects.equals(this.totalNum, courseResourceStatisticsResource.totalNum) &&
                Objects.equals(this.teachingContentNum, courseResourceStatisticsResource.teachingContentNum) &&
                Objects.equals(this.teachingActivityNum, courseResourceStatisticsResource.teachingActivityNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalNum, teachingContentNum, teachingActivityNum);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseResourceStatisticsResource {\n");

        sb.append("    totalNum: ").append(toIndentedString(totalNum)).append("\n");
        sb.append("    teachingContentNum: ").append(toIndentedString(teachingContentNum)).append("\n");
        sb.append("    teachingActivityNum: ").append(toIndentedString(teachingActivityNum)).append("\n");
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

