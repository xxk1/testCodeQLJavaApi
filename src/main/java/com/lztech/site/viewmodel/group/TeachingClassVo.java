package com.lztech.site.viewmodel.group;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * TeachingClassVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-12-12T07:56:36.829Z")


public class TeachingClassVo {
    @JsonProperty("source")
    private String source = null;

    @JsonProperty("teachingClassId")
    private String teachingClassId = null;

    @JsonProperty("teachingClassName")
    private String teachingClassName = null;

    @JsonProperty("teachingClassNo")
    private String teachingClassNo = null;

    @JsonProperty("courseTableId")
    private String courseTableId = null;

    @JsonProperty("studentType")
    private Integer studentType = null;

    @JsonProperty("teachingClassMembers")
    private Integer teachingClassMembers = null;

    public Integer getTeachingClassMembers() {
        return teachingClassMembers;
    }

    public void setTeachingClassMembers(Integer teachingClassMembers) {
        this.teachingClassMembers = teachingClassMembers;
    }

    public TeachingClassVo source(String source) {
        this.source = source;
        return this;
    }

    /**
     * 班级来源（数据对接,0；系统录入,1；外部导入,2；自主开班,3；）
     *
     * @return source
     **/
    @ApiModelProperty(value = "班级来源（数据对接,0；系统录入,1；外部导入,2；自主开班,3；）")


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public TeachingClassVo teachingClassId(String teachingClassId) {
        this.teachingClassId = teachingClassId;
        return this;
    }

    /**
     * 上课班级Id
     *
     * @return teachingClassId
     **/
    @ApiModelProperty(value = "上课班级Id")


    public String getTeachingClassId() {
        return teachingClassId;
    }

    public void setTeachingClassId(String teachingClassId) {
        this.teachingClassId = teachingClassId;
    }

    public TeachingClassVo teachingClassName(String teachingClassName) {
        this.teachingClassName = teachingClassName;
        return this;
    }

    /**
     * 上课班级
     *
     * @return teachingClassName
     **/
    @ApiModelProperty(value = "上课班级")


    public String getTeachingClassName() {
        return teachingClassName;
    }

    public void setTeachingClassName(String teachingClassName) {
        this.teachingClassName = teachingClassName;
    }

    public TeachingClassVo teachingClassNo(String teachingClassNo) {
        this.teachingClassNo = teachingClassNo;
        return this;
    }

    /**
     * 上课班级编号
     *
     * @return teachingClassNo
     **/
    @ApiModelProperty(value = "上课班级编号")


    public String getTeachingClassNo() {
        return teachingClassNo;
    }

    public void setTeachingClassNo(String teachingClassNo) {
        this.teachingClassNo = teachingClassNo;
    }

    public TeachingClassVo courseTableId(String courseTableId) {
        this.courseTableId = courseTableId;
        return this;
    }

    /**
     * 课表ID
     *
     * @return courseTableId
     **/
    @ApiModelProperty(value = "课表ID")


    public String getCourseTableId() {
        return courseTableId;
    }

    public void setCourseTableId(String courseTableId) {
        this.courseTableId = courseTableId;
    }

    public TeachingClassVo studentType(Integer studentType) {
        this.studentType = studentType;
        return this;
    }

    /**
     * 开课类型
     *
     * @return studentType
     **/
    @ApiModelProperty(value = "开课类型")


    public Integer getStudentType() {
        return studentType;
    }

    public void setStudentType(Integer studentType) {
        this.studentType = studentType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeachingClassVo teachingClassVo = (TeachingClassVo) o;
        return Objects.equals(this.source, teachingClassVo.source) &&
                Objects.equals(this.teachingClassId, teachingClassVo.teachingClassId) &&
                Objects.equals(this.teachingClassName, teachingClassVo.teachingClassName) &&
                Objects.equals(this.teachingClassNo, teachingClassVo.teachingClassNo) &&
                Objects.equals(this.courseTableId, teachingClassVo.courseTableId) &&
                Objects.equals(this.studentType, teachingClassVo.studentType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, teachingClassId, teachingClassName, teachingClassNo, courseTableId, studentType);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TeachingClassVo {\n");

        sb.append("    source: ").append(toIndentedString(source)).append("\n");
        sb.append("    teachingClassId: ").append(toIndentedString(teachingClassId)).append("\n");
        sb.append("    teachingClassName: ").append(toIndentedString(teachingClassName)).append("\n");
        sb.append("    teachingClassNo: ").append(toIndentedString(teachingClassNo)).append("\n");
        sb.append("    courseTableId: ").append(toIndentedString(courseTableId)).append("\n");
        sb.append("    studentType: ").append(toIndentedString(studentType)).append("\n");
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

