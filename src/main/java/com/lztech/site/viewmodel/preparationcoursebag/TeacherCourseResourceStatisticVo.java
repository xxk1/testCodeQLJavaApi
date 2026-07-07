package com.lztech.site.viewmodel.preparationcoursebag;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.math.BigInteger;
import java.util.Objects;

/**
 * TeacherCourseResourceStatisticVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-11-10T03:27:36.083Z")


public class TeacherCourseResourceStatisticVo {
    @JsonProperty("teacherId")
    private String teacherId = null;

    @JsonProperty("teacherName")
    private String teacherName = null;

    @JsonProperty("resourceTypeId")
    private Integer resourceTypeId = null;

    @JsonProperty("resourceTypeName")
    private String resourceTypeName = null;

    @JsonProperty("resourceCount")
    private BigInteger resourceCount = null;

    public TeacherCourseResourceStatisticVo teacherId(String teacherId) {
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

    public TeacherCourseResourceStatisticVo teacherName(String teacherName) {
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

    public TeacherCourseResourceStatisticVo resourceTypeId(Integer resourceTypeId) {
        this.resourceTypeId = resourceTypeId;
        return this;
    }

    /**
     * 资源id
     *
     * @return resourceTypeId
     **/
    @ApiModelProperty(value = "资源id")


    public Integer getResourceTypeId() {
        return resourceTypeId;
    }

    public void setResourceTypeId(Integer resourceTypeId) {
        this.resourceTypeId = resourceTypeId;
    }

    public TeacherCourseResourceStatisticVo resourceTypeName(String resourceTypeName) {
        this.resourceTypeName = resourceTypeName;
        return this;
    }

    /**
     * 资源名称
     *
     * @return resourceTypeName
     **/
    @ApiModelProperty(value = "资源名称")


    public String getResourceTypeName() {
        return resourceTypeName;
    }

    public void setResourceTypeName(String resourceTypeName) {
        this.resourceTypeName = resourceTypeName;
    }

    public TeacherCourseResourceStatisticVo resourceCount(BigInteger resourceCount) {
        this.resourceCount = resourceCount;
        return this;
    }

    /**
     * 资源个数
     *
     * @return resourceCount
     **/
    @ApiModelProperty(value = "资源个数")


    public BigInteger getResourceCount() {
        return resourceCount;
    }

    public void setResourceCount(BigInteger resourceCount) {
        this.resourceCount = resourceCount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeacherCourseResourceStatisticVo teacherCourseResourceStatisticVo = (TeacherCourseResourceStatisticVo) o;
        return Objects.equals(this.teacherId, teacherCourseResourceStatisticVo.teacherId) &&
                Objects.equals(this.teacherName, teacherCourseResourceStatisticVo.teacherName) &&
                Objects.equals(this.resourceTypeId, teacherCourseResourceStatisticVo.resourceTypeId) &&
                Objects.equals(this.resourceTypeName, teacherCourseResourceStatisticVo.resourceTypeName) &&
                Objects.equals(this.resourceCount, teacherCourseResourceStatisticVo.resourceCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, teacherName, resourceTypeId, resourceTypeName, resourceCount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TeacherCourseResourceStatisticVo {\n");

        sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
        sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
        sb.append("    resourceTypeId: ").append(toIndentedString(resourceTypeId)).append("\n");
        sb.append("    resourceTypeName: ").append(toIndentedString(resourceTypeName)).append("\n");
        sb.append("    resourceCount: ").append(toIndentedString(resourceCount)).append("\n");
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

