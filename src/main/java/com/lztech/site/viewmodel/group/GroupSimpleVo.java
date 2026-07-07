package com.lztech.site.viewmodel.group;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * GroupSimpleVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-01-18T07:53:50.584Z")

public class GroupSimpleVo   {
    @JsonProperty("groupId")
    private String groupId = null;

    @JsonProperty("groupName")
    private String groupName = null;

    @JsonProperty("source")
    private Integer source = null;

    @JsonProperty("classNickName")
    private String classNickName = null;

    @JsonProperty("classCompositionName")
    private String classCompositionName = null;

    @JsonProperty("courseCategoryCode")
    private String courseCategoryCode = null;

    @JsonProperty("courseCategoryName")
    private String courseCategoryName = null;

    public GroupSimpleVo groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    /**
     * 班级Id
     * @return groupId
     **/
    @ApiModelProperty(value = "班级Id")


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public GroupSimpleVo groupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    /**
     * 班级名称
     * @return groupName
     **/
    @ApiModelProperty(value = "班级名称")


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public GroupSimpleVo source(Integer source) {
        this.source = source;
        return this;
    }

    /**
     * 班级类型
     * @return source
     **/
    @ApiModelProperty(value = "班级类型")


    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }
    /**
     * 班级昵称
     * @return source
     **/
    @ApiModelProperty(value = "班级昵称")
    public String getClassNickName() {
        return classNickName;
    }

    public void setClassNickName(String classNickName) {
        this.classNickName = classNickName;
    }
    /**
     * 班级组成
     * @return source
     **/
    @ApiModelProperty(value = "班级组成")
    public String getClassCompositionName() {
        return classCompositionName;
    }

    public void setClassCompositionName(String classCompositionName) {
        this.classCompositionName = classCompositionName;
    }
    public GroupSimpleVo courseCategoryCode(String courseCategoryCode) {
        this.courseCategoryCode = courseCategoryCode;
        return this;
    }
    /**
     * 课程类型编号
     * @return courseCategoryCode
     **/
    @ApiModelProperty(value = "课程类型编号")
    public String getCourseCategoryCode() {
        return courseCategoryCode;
    }

    public void setCourseCategoryCode(String courseCategoryCode) {
        this.courseCategoryCode = courseCategoryCode;
    }
    public GroupSimpleVo courseCategoryName(String courseCategoryName) {
        this.courseCategoryName = courseCategoryName;
        return this;
    }
    /**
     * 课程类型名称
     * @return courseCategoryName
     **/
    @ApiModelProperty(value = "课程类型名称")
    public String getCourseCategoryName() {
        return courseCategoryName;
    }

    public void setCourseCategoryName(String courseCategoryName) {
        this.courseCategoryName = courseCategoryName;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GroupSimpleVo groupSimpleVo = (GroupSimpleVo) o;
        return Objects.equals(this.groupId, groupSimpleVo.groupId) &&
                Objects.equals(this.groupName, groupSimpleVo.groupName) &&
                Objects.equals(this.source, groupSimpleVo.source) &&
                Objects.equals(this.classNickName, groupSimpleVo.classNickName) &&
                Objects.equals(this.classCompositionName, groupSimpleVo.classCompositionName) &&
                Objects.equals(this.courseCategoryCode,groupSimpleVo.courseCategoryCode) &&
                Objects.equals(this.courseCategoryName,groupSimpleVo.courseCategoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, groupName, source,classNickName,classCompositionName,courseCategoryCode,classCompositionName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GroupSimpleVo {\n");

        sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
        sb.append("    groupName: ").append(toIndentedString(groupName)).append("\n");
        sb.append("    source: ").append(toIndentedString(source)).append("\n");
        sb.append("    classNickName: ").append(toIndentedString(classNickName)).append("\n");
        sb.append("    classCompositionName: ").append(toIndentedString(classCompositionName)).append("\n");
        sb.append("    courseCategoryCode: ").append(toIndentedString(courseCategoryCode)).append("\n");
        sb.append("    courseCategoryName: ").append(toIndentedString(courseCategoryName)).append("\n");
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
