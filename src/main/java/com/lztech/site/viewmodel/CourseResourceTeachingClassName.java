package com.lztech.site.viewmodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseResourceTeachingClassName
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-12-12T07:30:56.662Z")


public class CourseResourceTeachingClassName   {
    @JsonProperty("source")
    private String source = null;

    @JsonProperty("teachingClassId")
    private String teachingClassId = null;

    @JsonProperty("teachingClassName")
    private String teachingClassName = null;

    @JsonProperty("teachingClassNo")
    private String teachingClassNo = null;

    @JsonProperty("teachingClassSort")
    private Integer teachingClassSort = null;

    @JsonProperty("teachingClassMembers")
    private Integer teachingClassMembers = null;

    @JsonProperty("courseTableId")
    private String courseTableId = null;

    @JsonProperty("studentType")
    private Integer studentType = null;

    @JsonProperty("teachingClassTeacherName")
    private String teachingClassTeacherName = null;

    @JsonProperty("classNickName")
    private String classNickName = null;

    @JsonProperty("classCompositionName")
    private String classCompositionName = null;

    @JsonProperty("courseTableCollegeId")
    private String courseTableCollegeId = null;

    @JsonProperty("courseTableCollegeCode")
    private String courseTableCollegeCode = null;

    @JsonProperty("courseTableCollegeName")
    private String courseTableCollegeName = null;

    @JsonProperty("groupAttributeValue")
    private Integer groupAttributeValue = null;

    @JsonProperty("groupAttributeName")
    private String groupAttributeName = null;

    public CourseResourceTeachingClassName source(String source) {
        this.source = source;
        return this;
    }

    /**
     * 班级来源（数据对接,0；系统录入,1；外部导入,2；自主开班,3；）
     * @return source
     **/
    @ApiModelProperty(value = "班级来源（数据对接,0；系统录入,1；外部导入,2；自主开班,3；）")


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public CourseResourceTeachingClassName teachingClassId(String teachingClassId) {
        this.teachingClassId = teachingClassId;
        return this;
    }

    /**
     * 上课班级Id
     * @return teachingClassId
     **/
    @ApiModelProperty(value = "上课班级Id")


    public String getTeachingClassId() {
        return teachingClassId;
    }

    public void setTeachingClassId(String teachingClassId) {
        this.teachingClassId = teachingClassId;
    }

    public CourseResourceTeachingClassName teachingClassName(String teachingClassName) {
        this.teachingClassName = teachingClassName;
        return this;
    }

    /**
     * 上课班级
     * @return teachingClassName
     **/
    @ApiModelProperty(value = "上课班级")


    public String getTeachingClassName() {
        return teachingClassName;
    }

    public void setTeachingClassName(String teachingClassName) {
        this.teachingClassName = teachingClassName;
    }

    public CourseResourceTeachingClassName teachingClassNo(String teachingClassNo) {
        this.teachingClassNo = teachingClassNo;
        return this;
    }
    /**
     * 上课班级编号
     * @return teachingClassNo
     **/
    @ApiModelProperty(value = "上课班级编号")

    public String getTeachingClassNo() {
        return teachingClassNo;
    }
    public void setTeachingClassNo(String teachingClassNo) {
        this.teachingClassNo = teachingClassNo;
    }

    public CourseResourceTeachingClassName teachingClassSort(Integer teachingClassSort) {
        this.teachingClassSort = teachingClassSort;
        return this;
    }
    /**
     * 上课班级排序字段
     * @return teachingClassSort
     **/
    @ApiModelProperty(value = "上课班级排序字段")

    public Integer getTeachingClassSort() {
        return teachingClassSort;
    }
    public void setTeachingClassSort(Integer teachingClassSort) {
        this.teachingClassSort = teachingClassSort;
    }


    public CourseResourceTeachingClassName teachingClassMembers(Integer teachingClassMembers) {
        this.teachingClassMembers = teachingClassMembers;
        return this;
    }

    /**
     * 上课班级人数
     * @return teachingClassMembers
     **/
    @ApiModelProperty(value = "上课班级人数")


    public Integer getTeachingClassMembers() {
        return teachingClassMembers;
    }

    public void setTeachingClassMembers(Integer teachingClassMembers) {
        this.teachingClassMembers = teachingClassMembers;
    }

    public CourseResourceTeachingClassName courseTableId(String courseTableId) {
        this.courseTableId = courseTableId;
        return this;
    }

    /**
     * 主课表id
     * @return courseTableId
     **/
    @ApiModelProperty(value = "主课表id")


    public String getCourseTableId() {
        return courseTableId;
    }

    public void setCourseTableId(String courseTableId) {
        this.courseTableId = courseTableId;
    }

    public CourseResourceTeachingClassName studentType(Integer studentType) {
        this.studentType = studentType;
        return this;
    }

    /**
     * 课程开课类型： 0：本科生 1：研究生
     * @return studentType
     **/
    @ApiModelProperty(value = "课程开课类型： 0：本科生 1：研究生")


    public Integer getStudentType() {
        return studentType;
    }

    public void setStudentType(Integer studentType) {
        this.studentType = studentType;
    }

    public CourseResourceTeachingClassName teachingClassTeacherName(String teachingClassTeacherName) {
        this.teachingClassTeacherName = teachingClassTeacherName;
        return this;
    }

    /**
     * 上课老師
     * @return teachingClassTeacherName
     **/
    @ApiModelProperty(value = "上课老師")


    public String getTeachingClassTeacherName() {
        return teachingClassTeacherName;
    }

    public void setTeachingClassTeacherName(String teachingClassTeacherName) {
        this.teachingClassTeacherName = teachingClassTeacherName;
    }

    public CourseResourceTeachingClassName classNickName(String classNickName) {
        this.classNickName = classNickName;
        return this;
    }

    /**
     * 班级昵称
     * @return classNickName
     **/
    @ApiModelProperty(value = "班级昵称")


    public String getClassNickName() {
        return classNickName;
    }

    public void setClassNickName(String classNickName) {
        this.classNickName = classNickName;
    }

    public CourseResourceTeachingClassName classCompositionName(String classCompositionName) {
        this.classCompositionName = classCompositionName;
        return this;
    }

    /**
     * 班级组成名称
     * @return classCompositionName
     **/
    @ApiModelProperty(value = "班级组成名称")


    public String getClassCompositionName() {
        return classCompositionName;
    }

    public void setClassCompositionName(String classCompositionName) {
        this.classCompositionName = classCompositionName;
    }

    public CourseResourceTeachingClassName courseTableCollegeId(String courseTableCollegeId) {
        this.courseTableCollegeId = courseTableCollegeId;
        return this;
    }

    /**
     * 开课学院id
     * @return courseTableCollegeId
     **/
    @ApiModelProperty(value = "开课学院id")


    public String getCourseTableCollegeId() {
        return courseTableCollegeId;
    }

    public void setCourseTableCollegeId(String courseTableCollegeId) {
        this.courseTableCollegeId = courseTableCollegeId;
    }

    public CourseResourceTeachingClassName courseTableCollegeCode(String courseTableCollegeCode) {
        this.courseTableCollegeCode = courseTableCollegeCode;
        return this;
    }

    /**
     * 开课学院编号
     * @return courseTableCollegeCode
     **/
    @ApiModelProperty(value = "开课学院编号")


    public String getCourseTableCollegeCode() {
        return courseTableCollegeCode;
    }

    public void setCourseTableCollegeCode(String courseTableCollegeCode) {
        this.courseTableCollegeCode = courseTableCollegeCode;
    }

    public CourseResourceTeachingClassName courseTableCollegeName(String courseTableCollegeName) {
        this.courseTableCollegeName = courseTableCollegeName;
        return this;
    }

    /**
     * 开课学院学院名称
     * @return courseTableCollegeName
     **/
    @ApiModelProperty(value = "开课学院学院名称")


    public String getCourseTableCollegeName() {
        return courseTableCollegeName;
    }

    public void setCourseTableCollegeName(String courseTableCollegeName) {
        this.courseTableCollegeName = courseTableCollegeName;
    }

    public CourseResourceTeachingClassName groupAttributeValue(Integer groupAttributeValue) {
        this.groupAttributeValue = groupAttributeValue;
        return this;
    }

    /**
     * 班级属性编号
     * @return groupAttributeValue
     **/
    @ApiModelProperty(value = "班级属性编号")

    public Integer getGroupAttributeValue() {
        return groupAttributeValue;
    }

    public void setGroupAttributeValue(Integer groupAttributeValue) {
        this.groupAttributeValue = groupAttributeValue;
    }
    public CourseResourceTeachingClassName groupAttributeName(String groupAttributeName) {
        this.groupAttributeName = groupAttributeName;
        return this;
    }

    /**
     * 班级属性名称
     * @return groupAttributeName
     **/
    @ApiModelProperty(value = "班级属性名称")


    public String getGroupAttributeName() {
        return groupAttributeName;
    }

    public void setGroupAttributeName(String groupAttributeName) {
        this.groupAttributeName = groupAttributeName;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseResourceTeachingClassName courseResourceTeachingClassName = (CourseResourceTeachingClassName) o;
        return Objects.equals(this.source, courseResourceTeachingClassName.source) &&
                Objects.equals(this.teachingClassId, courseResourceTeachingClassName.teachingClassId) &&
                Objects.equals(this.teachingClassName, courseResourceTeachingClassName.teachingClassName) &&
                Objects.equals(this.teachingClassNo, courseResourceTeachingClassName.teachingClassNo) &&
                Objects.equals(this.teachingClassSort, courseResourceTeachingClassName.teachingClassSort) &&
                Objects.equals(this.teachingClassMembers, courseResourceTeachingClassName.teachingClassMembers) &&
                Objects.equals(this.courseTableId, courseResourceTeachingClassName.courseTableId) &&
                Objects.equals(this.studentType, courseResourceTeachingClassName.studentType) &&
                Objects.equals(this.teachingClassTeacherName, courseResourceTeachingClassName.teachingClassTeacherName) &&
                Objects.equals(this.classNickName, courseResourceTeachingClassName.classNickName) &&
                Objects.equals(this.classCompositionName, courseResourceTeachingClassName.classCompositionName) &&
                Objects.equals(this.courseTableCollegeId, courseResourceTeachingClassName.courseTableCollegeId) &&
                Objects.equals(this.courseTableCollegeCode, courseResourceTeachingClassName.courseTableCollegeCode) &&
                Objects.equals(this.courseTableCollegeName, courseResourceTeachingClassName.courseTableCollegeName) &&
                Objects.equals(this.groupAttributeValue, courseResourceTeachingClassName.groupAttributeValue) &&
                Objects.equals(this.groupAttributeName, courseResourceTeachingClassName.groupAttributeName) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, teachingClassId, teachingClassName, teachingClassNo, teachingClassSort, teachingClassMembers,
                courseTableId, studentType, teachingClassTeacherName, classNickName, classCompositionName,
                courseTableCollegeId,courseTableCollegeCode,courseTableCollegeName,groupAttributeValue,groupAttributeName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseResourceTeachingClassName {\n");

        sb.append("    source: ").append(toIndentedString(source)).append("\n");
        sb.append("    teachingClassId: ").append(toIndentedString(teachingClassId)).append("\n");
        sb.append("    teachingClassName: ").append(toIndentedString(teachingClassName)).append("\n");
        sb.append("    teachingClassNo: ").append(toIndentedString(teachingClassNo)).append("\n");
        sb.append("    teachingClassSort: ").append(toIndentedString(teachingClassSort)).append("\n");
        sb.append("    teachingClassMembers: ").append(toIndentedString(teachingClassMembers)).append("\n");
        sb.append("    courseTableId: ").append(toIndentedString(courseTableId)).append("\n");
        sb.append("    studentType: ").append(toIndentedString(studentType)).append("\n");
        sb.append("    teachingClassTeacherName: ").append(toIndentedString(teachingClassTeacherName)).append("\n");
        sb.append("    classNickName: ").append(toIndentedString(classNickName)).append("\n");
        sb.append("    classCompositionName: ").append(toIndentedString(classCompositionName)).append("\n");
        sb.append("    courseTableCollegeId: ").append(toIndentedString(courseTableCollegeId)).append("\n");
        sb.append("    courseTableCollegeCode: ").append(toIndentedString(courseTableCollegeCode)).append("\n");
        sb.append("    courseTableCollegeName: ").append(toIndentedString(courseTableCollegeName)).append("\n");
        sb.append("    groupAttributeValue: ").append(toIndentedString(groupAttributeValue)).append("\n");
        sb.append("    groupAttributeName: ").append(toIndentedString(groupAttributeName)).append("\n");
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

