package com.lztech.site.viewmodel.preparationcoursebag;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * TeacherCourseResourceReferenceStatisticVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-11-13T08:00:01.318Z")


public class TeacherCourseResourceReferenceStatisticVo {
    @JsonProperty("teacherId")
    private String teacherId = null;

    @JsonProperty("teacherName")
    private String teacherName = null;

    @JsonProperty("individualReference")
    private Integer individualReference = null;

    @JsonProperty("otherReference")
    private Integer otherReference = null;

    @JsonProperty("useResourceCount")
    private Integer useResourceCount = null;

    @JsonProperty("resourceTotalCount")
    private Integer resourceTotalCount = null;

    public TeacherCourseResourceReferenceStatisticVo teacherId(String teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    /**
     * 老师id
     *
     * @return teacherId
     **/
    @ApiModelProperty(value = "老师id")


    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public TeacherCourseResourceReferenceStatisticVo teacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    /**
     * 老师名称
     *
     * @return teacherName
     **/
    @ApiModelProperty(value = "老师名称")


    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public TeacherCourseResourceReferenceStatisticVo individualReference(Integer referenceTypeId) {
        this.individualReference = individualReference;
        return this;
    }

    /**
     * 个人引用个数
     *
     * @return individualReference
     **/
    @ApiModelProperty(value = "个人引用个数")


    public Integer getIndividualReference() {
        return individualReference;
    }

    public void setIndividualReference(Integer individualReference) {
        this.individualReference = individualReference;
    }

    public TeacherCourseResourceReferenceStatisticVo otherReference(Integer otherReference) {
        this.otherReference = otherReference;
        return this;
    }

    /**
     * 他人引用个数
     *
     * @return otherReference
     **/
    @ApiModelProperty(value = "他人引用个数")


    public Integer getOtherReference() {
        return otherReference;
    }

    public void setOtherReference(Integer otherReference) {
        this.otherReference = otherReference;
    }

    public TeacherCourseResourceReferenceStatisticVo useResourceCount(Integer useResourceCount) {
        this.useResourceCount = useResourceCount;
        return this;
    }

    /**
     * 使用资源个数
     *
     * @return useResourceCount
     **/
    @ApiModelProperty(value = "使用资源个数")


    public Integer getUseResourceCount() {
        return useResourceCount;
    }

    public void setUseResourceCount(Integer useResourceCount) {
        this.useResourceCount = useResourceCount;
    }

    public TeacherCourseResourceReferenceStatisticVo resourceTotalCount(Integer resourceTotalCount) {
        this.resourceTotalCount = resourceTotalCount;
        return this;
    }

    /**
     * 资源总个数
     *
     * @return resourceTotalCount
     **/
    @ApiModelProperty(value = "资源总个数")


    public Integer getResourceTotalCount() {
        return resourceTotalCount;
    }

    public void setResourceTotalCount(Integer resourceTotalCount) {
        this.resourceTotalCount = resourceTotalCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeacherCourseResourceReferenceStatisticVo teacherCourseResourceReferenceStatisticVo = (TeacherCourseResourceReferenceStatisticVo) o;
        return Objects.equals(this.teacherId, teacherCourseResourceReferenceStatisticVo.teacherId) &&
                Objects.equals(this.teacherName, teacherCourseResourceReferenceStatisticVo.teacherName) &&
                Objects.equals(this.individualReference, teacherCourseResourceReferenceStatisticVo.individualReference) &&
                Objects.equals(this.otherReference, teacherCourseResourceReferenceStatisticVo.otherReference) &&
                Objects.equals(this.useResourceCount,teacherCourseResourceReferenceStatisticVo.useResourceCount) &&
                Objects.equals(this.resourceTotalCount,teacherCourseResourceReferenceStatisticVo.resourceTotalCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, teacherName, individualReference, otherReference,useResourceCount,resourceTotalCount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TeacherCourseResourceReferenceStatisticVo {\n");

        sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
        sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
        sb.append("    individualReference: ").append(toIndentedString(individualReference)).append("\n");
        sb.append("    otherReference: ").append(toIndentedString(otherReference)).append("\n");
        sb.append("    useResourceCount: ").append(toIndentedString(useResourceCount)).append("\n");
        sb.append("    resourceTotalCount: ").append(toIndentedString(resourceTotalCount)).append("\n");
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

