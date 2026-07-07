package com.lztech.site.viewmodel.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * GroupBaseInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-01-02T06:23:19.150Z")


public class GroupBaseInfo {
    @JsonProperty("groupId")
    private String groupId = null;

    @JsonProperty("groupName")
    private String groupName = null;

    @JsonProperty("groupNo")
    private String groupNo = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    public GroupBaseInfo groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    /**
     * 班级Id
     *
     * @return groupId
     **/
    @ApiModelProperty(value = "班级Id")


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public GroupBaseInfo groupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    /**
     * 班级名称
     *
     * @return groupName
     **/
    @ApiModelProperty(value = "班级名称")


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public GroupBaseInfo groupNo(String groupNo) {
        this.groupNo = groupNo;
        return this;
    }

    /**
     * 班级编号
     *
     * @return groupNo
     **/
    @ApiModelProperty(value = "班级编号")


    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    public GroupBaseInfo collegeId(String collegeId) {
        this.collegeId = collegeId;
        return this;
    }

    /**
     * 开课学院id
     *
     * @return collegeId
     **/
    @ApiModelProperty(value = "开课学院id")


    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public GroupBaseInfo collegeName(String collegeName) {
        this.collegeName = collegeName;
        return this;
    }

    /**
     * 开课学院名称
     *
     * @return collegeName
     **/
    @ApiModelProperty(value = "开课学院名称")


    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GroupBaseInfo groupBaseInfo = (GroupBaseInfo) o;
        return Objects.equals(this.groupId, groupBaseInfo.groupId) &&
                Objects.equals(this.groupName, groupBaseInfo.groupName) &&
                Objects.equals(this.groupNo, groupBaseInfo.groupNo) &&
                Objects.equals(this.collegeId, groupBaseInfo.collegeId) &&
                Objects.equals(this.collegeName, groupBaseInfo.collegeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, groupName, groupNo, collegeId, collegeName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GroupBaseInfo {\n");

        sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
        sb.append("    groupName: ").append(toIndentedString(groupName)).append("\n");
        sb.append("    groupNo: ").append(toIndentedString(groupNo)).append("\n");
        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
        sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
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

