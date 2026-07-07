package com.lztech.site.viewmodel.coursemanagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * StructureParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-01-11T07:22:25.465Z")


public class StructureParam {
    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("versionId")
    private String versionId = null;

    @JsonProperty("parentId")
    private String parentId = null;

    @JsonProperty("userId")
    private String userId = null;

    @JsonProperty("userName")
    private String userName = null;

    @JsonProperty("structureName")
    private String structureName = null;

    public StructureParam versionId(String versionId) {
        this.versionId = versionId;
        return this;
    }

    /**
     * 版本id
     *
     * @return courseId
     **/
    @ApiModelProperty(value = "版本id")


    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    /**
     * 课程id
     * @return courseId
     **/
    @ApiModelProperty(value = "课程id")


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public StructureParam parentId(String parentId) {
        this.parentId = parentId;
        return this;
    }

    /**
     * 父节点id
     *
     * @return parentId
     **/
    @ApiModelProperty(value = "父节点id")


    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public StructureParam userId(String userId) {
        this.userId = userId;
        return this;
    }

    /**
     * 用户id
     *
     * @return userId
     **/
    @ApiModelProperty(value = "用户id")


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public StructureParam userName(String userName) {
        this.userName = userName;
        return this;
    }

    /**
     * 用户名称
     *
     * @return userName
     **/
    @ApiModelProperty(value = "用户名称")


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public StructureParam structureName(String structureName) {
        this.structureName = structureName;
        return this;
    }

    /**
     * 课程结构名称
     *
     * @return structureName
     **/
    @ApiModelProperty(value = "课程结构名称")


    public String getStructureName() {
        return structureName;
    }

    public void setStructureName(String structureName) {
        this.structureName = structureName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StructureParam structureParam = (StructureParam) o;
        return Objects.equals(this.courseId, structureParam.courseId) &&
                Objects.equals(this.parentId, structureParam.parentId) &&
                Objects.equals(this.userId, structureParam.userId) &&
                Objects.equals(this.userName, structureParam.userName) &&
                Objects.equals(this.structureName, structureParam.structureName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, parentId, userId, userName, structureName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class StructureParam {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    parentId: ").append(toIndentedString(parentId)).append("\n");
        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
        sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
        sb.append("    structureName: ").append(toIndentedString(structureName)).append("\n");
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

