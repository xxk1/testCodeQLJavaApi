package com.lztech.site.resource.group;


import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * GroupSimpleInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-08-14T06:57:14.571Z")

public class GroupSimpleInfo   {

    @JsonProperty("groupId")
    private String groupId = null;

    @JsonProperty("groupNo")
    private String groupNo = null;

    @JsonProperty("groupName")
    private String groupName = null;

    @JsonProperty("teacherNames")
    private String teacherNames = null;

    public GroupSimpleInfo groupId(String groupId) {
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

    public GroupSimpleInfo groupNo(String groupNo) {
        this.groupNo = groupNo;
        return this;
    }

    /**
     * 班级编号
     * @return groupNo
     **/
    @ApiModelProperty(value = "班级编号")


    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    public GroupSimpleInfo groupName(String groupName) {
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




    public GroupSimpleInfo teacherNames(String teacherNames) {
        this.teacherNames = teacherNames;
        return this;
    }

    /**
     * 服务老师
     * @return teacherNames
     **/
    @ApiModelProperty(value = "服务老师")



    public String getTeacherNames() {
        return teacherNames;
    }

    public void setTeacherNames(String teacherNames) {
        this.teacherNames = teacherNames;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GroupSimpleInfo groupSimpleInfo = (GroupSimpleInfo) o;
        return Objects.equals(this.groupId, groupSimpleInfo.groupId) &&
                Objects.equals(this.groupNo, groupSimpleInfo.groupNo) &&
                Objects.equals(this.groupName, groupSimpleInfo.groupName) &&
                Objects.equals(this.teacherNames, groupSimpleInfo.teacherNames) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, groupNo,groupName, teacherNames);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GroupSimpleInfo {\n");
        sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
        sb.append("    groupNo: ").append(toIndentedString(groupNo)).append("\n");
        sb.append("    groupName: ").append(toIndentedString(groupName)).append("\n");
        sb.append("    teacherNames: ").append(toIndentedString(teacherNames)).append("\n");
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
