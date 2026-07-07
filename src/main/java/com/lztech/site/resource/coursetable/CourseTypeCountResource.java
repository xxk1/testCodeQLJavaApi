package com.lztech.site.resource.coursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseTypeCountResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-18T05:36:46.621Z")

public class CourseTypeCountResource {
    @JsonProperty("theoryCount")
    private Integer theoryCount = null;

    @JsonProperty("sportsCount")
    private Integer sportsCount = null;

    @JsonProperty("englishCount")
    private Integer englishCount = null;

    @JsonProperty("experimentCount")
    private Integer experimentCount = null;

    public CourseTypeCountResource theoryCount(Integer theoryCount) {
        this.theoryCount = theoryCount;
        return this;
    }

    /**
     * 理论课数量
     *
     * @return theoryCount
     **/
    @ApiModelProperty(value = "理论课数量 ")


    public Integer getTheoryCount() {
        return theoryCount;
    }

    public void setTheoryCount(Integer theoryCount) {
        this.theoryCount = theoryCount;
    }

    public CourseTypeCountResource sportsCount(Integer sportsCount) {
        this.sportsCount = sportsCount;
        return this;
    }

    /**
     * 体育课数量
     *
     * @return sportsCount
     **/
    @ApiModelProperty(value = "体育课数量")


    public Integer getSportsCount() {
        return sportsCount;
    }

    public void setSportsCount(Integer sportsCount) {
        this.sportsCount = sportsCount;
    }

    public CourseTypeCountResource englishCount(Integer englishCount) {
        this.englishCount = englishCount;
        return this;
    }

    /**
     * 英语课数量
     *
     * @return englishCount
     **/
    @ApiModelProperty(value = "英语课数量")


    public Integer getEnglishCount() {
        return englishCount;
    }

    public void setEnglishCount(Integer englishCount) {
        this.englishCount = englishCount;
    }

    public CourseTypeCountResource experimentCount(Integer experimentCount) {
        this.experimentCount = experimentCount;
        return this;
    }

    /**
     * 实验课数量
     *
     * @return experimentCount
     **/
    @ApiModelProperty(value = "实验课数量")


    public Integer getExperimentCount() {
        return experimentCount;
    }

    public void setExperimentCount(Integer experimentCount) {
        this.experimentCount = experimentCount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseTypeCountResource courseTypeCountResource = (CourseTypeCountResource) o;
        return Objects.equals(this.theoryCount, courseTypeCountResource.theoryCount) &&
                Objects.equals(this.sportsCount, courseTypeCountResource.sportsCount) &&
                Objects.equals(this.englishCount, courseTypeCountResource.englishCount) &&
                Objects.equals(this.experimentCount, courseTypeCountResource.experimentCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(theoryCount, sportsCount, englishCount, experimentCount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTypeCountResource {\n");

        sb.append("    theoryCount: ").append(toIndentedString(theoryCount)).append("\n");
        sb.append("    sportsCount: ").append(toIndentedString(sportsCount)).append("\n");
        sb.append("    englishCount: ").append(toIndentedString(englishCount)).append("\n");
        sb.append("    experimentCount: ").append(toIndentedString(experimentCount)).append("\n");
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

