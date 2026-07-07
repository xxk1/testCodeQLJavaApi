package com.lztech.site.resource.collegeresources;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CollegeResources
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-08-26T02:33:33.556Z")


public class CollegeResources {
    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("collegeCode")
    private String collegeCode = null;

    @JsonProperty("courseInfoCollege")
    private Integer courseInfoCollege = null;

    @JsonProperty("courseTableCollege")
    private Integer courseTableCollege = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    public CollegeResources collegeId(String collegeId) {
        this.collegeId = collegeId;
        return this;
    }

    /**
     * 学院Id
     *
     * @return collegeId
     **/
    @ApiModelProperty(value = "学院Id")


    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public CollegeResources collegeCode(String collegeCode) {
        this.collegeCode = collegeCode;
        return this;
    }

    /**
     * 学院编号
     *
     * @return collegeCode
     **/
    @ApiModelProperty(value = "学院编号")


    public String getCollegeCode() {
        return collegeCode;
    }

    public void setCollegeCode(String collegeCode) {
        this.collegeCode = collegeCode;
    }

    public CollegeResources courseInfoCollege(Integer courseInfoCollege) {
        this.courseInfoCollege = courseInfoCollege;
        return this;
    }

    /**
     * 是否是开课学院，0：是，1：否
     *
     * @return courseInfoCollege
     **/
    @ApiModelProperty(value = "是否是开课学院，0：是，1：否")


    public Integer getCourseInfoCollege() {
        return courseInfoCollege;
    }

    public void setCourseInfoCollege(Integer courseInfoCollege) {
        this.courseInfoCollege = courseInfoCollege;
    }

    public CollegeResources courseTableCollege(Integer courseTableCollege) {
        this.courseTableCollege = courseTableCollege;
        return this;
    }

    /**
     * 是否是开课课表学院，0：是，1：否
     *
     * @return courseTableCollege
     **/
    @ApiModelProperty(value = "是否是开课课表学院，0：是，1：否")


    public Integer getCourseTableCollege() {
        return courseTableCollege;
    }

    public void setCourseTableCollege(Integer courseTableCollege) {
        this.courseTableCollege = courseTableCollege;
    }

    public CollegeResources collegeName(String collegeName) {
        this.collegeName = collegeName;
        return this;
    }

    /**
     * 学院名称
     *
     * @return collegeName
     **/
    @ApiModelProperty(value = "学院名称")


    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CollegeResources collegeResources = (CollegeResources) o;
        return Objects.equals(this.collegeId, collegeResources.collegeId) &&
                Objects.equals(this.collegeCode, collegeResources.collegeCode) &&
                Objects.equals(this.courseInfoCollege, collegeResources.courseInfoCollege) &&
                Objects.equals(this.courseTableCollege, collegeResources.courseTableCollege) &&
                Objects.equals(this.collegeName, collegeResources.collegeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(collegeId, collegeCode, courseInfoCollege, courseTableCollege, collegeName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CollegeResources {\n");

        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
        sb.append("    collegeCode: ").append(toIndentedString(collegeCode)).append("\n");
        sb.append("    courseInfoCollege: ").append(toIndentedString(courseInfoCollege)).append("\n");
        sb.append("    courseTableCollege: ").append(toIndentedString(courseTableCollege)).append("\n");
        sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
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

