package com.lztech.site.viewmodel.kggentask;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * KgAIGenCourseVideoParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-07-22T10:17:27.444+08:00")

public class KgAIGenCourseVideoParam {

    @JsonProperty("isReGen")
    private Boolean isReGen = null;

    @JsonProperty("operatorId")
    private String operatorId = null;

    @JsonProperty("operatorNo")
    private String operatorNo = null;

    @JsonProperty("operatorName")
    private String operatorName = null;

    @JsonProperty("schoolYear")
    private String schoolYear = null;

    @JsonProperty("term")
    private Integer term = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseName")
    private String courseName;

    @JsonProperty("groupId")
    private String groupId = null;
    public KgAIGenCourseVideoParam courseName(String courseName) {
        this.courseName = courseName;
        return this;
    }

    /**
     * 课程名称
     *
     * @return courseName
     **/
    @ApiModelProperty(value = "课程名称")

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public KgAIGenCourseVideoParam isReGen(Boolean isReGen) {
        this.isReGen = isReGen;
        return this;
    }

    /**
     * 是否重新生成
     *
     * @return isReGen
     **/
    @ApiModelProperty(value = "是否重新生成")


    public Boolean isIsReGen() {
        return isReGen;
    }

    public void setIsReGen(Boolean isReGen) {
        this.isReGen = isReGen;
    }

    public KgAIGenCourseVideoParam operatorId(String operatorId) {
        this.operatorId = operatorId;
        return this;
    }

    /**
     * 操作人id
     *
     * @return operatorId
     **/
    @ApiModelProperty(value = "操作人id")


    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }


    public KgAIGenCourseVideoParam operatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
        return this;
    }

    /**
     * 操作人编号
     *
     * @return operatorNo
     **/
    @ApiModelProperty(value = "操作人编号")


    public String getOperatorNo() {
        return operatorNo;
    }

    public void setOperatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
    }

    public KgAIGenCourseVideoParam operatorName(String operatorName) {
        this.operatorName = operatorName;
        return this;
    }

    /**
     * 操作人姓名
     *
     * @return operatorName
     **/
    @ApiModelProperty(value = "操作人姓名")


    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }


    public KgAIGenCourseVideoParam schoolYear(String schoolYear) {
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
    public KgAIGenCourseVideoParam term(Integer term) {
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

    public KgAIGenCourseVideoParam courseId(String courseId) {
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

    public KgAIGenCourseVideoParam groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    /**
     * 教学班id
     *
     * @return groupId
     **/
    @ApiModelProperty(value = "教学班id")

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        KgAIGenCourseVideoParam kgAIGenCourseVideoParam = (KgAIGenCourseVideoParam) o;
        return Objects.equals(this.isReGen, kgAIGenCourseVideoParam.isReGen) &&
                Objects.equals(this.operatorId, kgAIGenCourseVideoParam.operatorId) &&
                Objects.equals(this.operatorNo, kgAIGenCourseVideoParam.operatorNo) &&
                Objects.equals(this.operatorName, kgAIGenCourseVideoParam.operatorName) &&
                Objects.equals(this.schoolYear, kgAIGenCourseVideoParam.schoolYear) &&
                Objects.equals(this.term, kgAIGenCourseVideoParam.term) &&
                Objects.equals(this.groupId, kgAIGenCourseVideoParam.groupId) &&
                Objects.equals(this.courseId, kgAIGenCourseVideoParam.courseId) &&
                Objects.equals(this.courseName, kgAIGenCourseVideoParam.courseName) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isReGen, operatorId, operatorNo, operatorName, schoolYear,term,groupId, courseId,courseName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class KgAIGenCourseVideoParam {\n");
        sb.append("    isReGen: ").append(toIndentedString(isReGen)).append("\n");
        sb.append("    operatorId: ").append(toIndentedString(operatorId)).append("\n");
        sb.append("    operatorNo: ").append(toIndentedString(operatorNo)).append("\n");
        sb.append("    operatorName: ").append(toIndentedString(operatorName)).append("\n");
        sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
        sb.append("    term: ").append(toIndentedString(term)).append("\n");
        sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}
