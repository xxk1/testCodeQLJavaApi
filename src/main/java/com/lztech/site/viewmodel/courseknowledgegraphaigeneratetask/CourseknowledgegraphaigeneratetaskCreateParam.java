package com.lztech.site.viewmodel.courseknowledgegraphaigeneratetask;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseknowledgegraphaigeneratetaskCreateParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-01-31T03:06:00.478Z")
public class CourseknowledgegraphaigeneratetaskCreateParam {

    @JsonProperty("courseId")
    private String courseId = null;


    @JsonProperty("schoolYear")
    private String schoolYear = null;


    @JsonProperty("term")
    private Integer term = null;


    @JsonProperty("groupId")
    private String groupId = null;

    @JsonProperty("accessUserId")
    String accessUserId = null;

    @JsonProperty("accessUserName")
    String accessUserName = null;

    public CourseknowledgegraphaigeneratetaskCreateParam courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    /**
     * 课程id
     *
     * @return courseId
     **/
    @ApiModelProperty(value = "课程id")


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public CourseknowledgegraphaigeneratetaskCreateParam schoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
        return this;
    }

    /**
     * 学年
     *
     * @return schoolYear
     **/
    @ApiModelProperty(value = "学年")

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public CourseknowledgegraphaigeneratetaskCreateParam term(Integer term) {
        this.term = term;
        return this;
    }

    /**
     * 学期
     *
     * @return term
     **/
    @ApiModelProperty(value = "学期")

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public CourseknowledgegraphaigeneratetaskCreateParam groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    /**
     * 教学班
     *
     * @return groupId
     **/
    @ApiModelProperty(value = "学年")

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public CourseknowledgegraphaigeneratetaskCreateParam accessUserId(String accessUserId) {
        this.accessUserId = accessUserId;
        return this;
    }

    /**
     * 访问人id
     *
     * @return accessUserId
     **/
    @ApiModelProperty(value = "访问人id")

    public String getAccessUserId() {
        return accessUserId;
    }

    public void setAccessUserId(String accessUserId) {
        this.accessUserId = accessUserId;
    }

    public CourseknowledgegraphaigeneratetaskCreateParam accessUserName(String accessUserName) {
        this.accessUserName = accessUserName;
        return this;
    }

    /**
     * 访问人名称
     *
     * @return accessUserName
     **/
    @ApiModelProperty(value = "访问人名称")


    public String getAccessUserName() {
        return accessUserName;
    }

    public void setAccessUserName(String accessUserName) {
        this.accessUserName = accessUserName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseknowledgegraphaigeneratetaskCreateParam courseknowledgegraphaigeneratetaskCreateParam =
                (CourseknowledgegraphaigeneratetaskCreateParam) o;
        return Objects.equals(this.courseId, courseknowledgegraphaigeneratetaskCreateParam.courseId) &&
                Objects.equals(this.schoolYear, courseknowledgegraphaigeneratetaskCreateParam.schoolYear) &&
                Objects.equals(this.term, courseknowledgegraphaigeneratetaskCreateParam.term) &&
                Objects.equals(this.groupId, courseknowledgegraphaigeneratetaskCreateParam.groupId) &&
                Objects.equals(this.accessUserId, courseknowledgegraphaigeneratetaskCreateParam.accessUserId) &&
                Objects.equals(this.accessUserName, courseknowledgegraphaigeneratetaskCreateParam.accessUserName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, schoolYear, term, groupId,accessUserId,accessUserName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseknowledgegraphaigeneratetaskCreateParam {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
        sb.append("    term: ").append(toIndentedString(term)).append("\n");
        sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
        sb.append("    accessUserId: ").append(toIndentedString(accessUserId)).append("\n");
        sb.append("    accessUserName: ").append(toIndentedString(accessUserName)).append("\n");
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
