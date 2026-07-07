package com.lztech.site.viewmodel.kggentask;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * KgAIGenTeachingMaterialParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-07-22T10:17:27.444+08:00")

public class KgAIGenTeachingMaterialParam {
    @JsonProperty("isReGen")
    private Boolean isReGen = null;

    @JsonProperty("operatorId")
    private String operatorId = null;

    @JsonProperty("operatorName")
    private String operatorName = null;

    @JsonProperty("operatorNo")
    private String operatorNo = null;

    @JsonProperty("teachingMaterialId")
    private String teachingMaterialId = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseName")
    private String courseName;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public KgAIGenTeachingMaterialParam isReGen(Boolean isReGen) {
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

    public KgAIGenTeachingMaterialParam operatorId(String operatorId) {
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

    public KgAIGenTeachingMaterialParam operatorName(String operatorName) {
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

    public KgAIGenTeachingMaterialParam operatorNo(String operatorNo) {
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

    public KgAIGenTeachingMaterialParam teachingMaterialId(String teachingMaterialId) {
        this.teachingMaterialId = teachingMaterialId;
        return this;
    }

    /**
     * 教材id
     *
     * @return teachingMaterialId
     **/
    @ApiModelProperty(value = "教材id")


    public String getTeachingMaterialId() {
        return teachingMaterialId;
    }

    public void setTeachingMaterialId(String teachingMaterialId) {
        this.teachingMaterialId = teachingMaterialId;
    }

    public KgAIGenTeachingMaterialParam courseId(String courseId) {
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


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        KgAIGenTeachingMaterialParam kgAIGenTeachingMaterialParam = (KgAIGenTeachingMaterialParam) o;
        return Objects.equals(this.isReGen, kgAIGenTeachingMaterialParam.isReGen) &&
                Objects.equals(this.operatorId, kgAIGenTeachingMaterialParam.operatorId) &&
                Objects.equals(this.operatorName, kgAIGenTeachingMaterialParam.operatorName) &&
                Objects.equals(this.operatorNo, kgAIGenTeachingMaterialParam.operatorNo) &&
                Objects.equals(this.teachingMaterialId, kgAIGenTeachingMaterialParam.teachingMaterialId) &&
                Objects.equals(this.courseId, kgAIGenTeachingMaterialParam.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isReGen, operatorId, operatorName, operatorNo, teachingMaterialId, courseId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class KgAIGenTeachingMaterialParam {\n");

        sb.append("    isReGen: ").append(toIndentedString(isReGen)).append("\n");
        sb.append("    operatorId: ").append(toIndentedString(operatorId)).append("\n");
        sb.append("    operatorName: ").append(toIndentedString(operatorName)).append("\n");
        sb.append("    operatorNo: ").append(toIndentedString(operatorNo)).append("\n");
        sb.append("    teachingMaterialId: ").append(toIndentedString(teachingMaterialId)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
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

