package com.lztech.site.viewmodel.peerreview;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * TeacherPeerReviewGroupResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-12-22T02:31:37.314Z")


public class TeacherPeerReviewGroupResource {
    @JsonProperty("groupId")
    private String groupId = null;

    @JsonProperty("groupNo")
    private String groupNo = null;

    @JsonProperty("groupName")
    private String groupName = null;

    @JsonProperty("groupSort")
    private Integer groupSort = null;

    public TeacherPeerReviewGroupResource groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    /**
     * 组id
     *
     * @return groupId
     **/
    @ApiModelProperty(value = "组id")

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public TeacherPeerReviewGroupResource groupNo(String groupNo) {
        this.groupNo = groupNo;
        return this;
    }

    /**
     * 组编号
     *
     * @return groupNo
     **/
    @ApiModelProperty(value = "组编号")

    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    public TeacherPeerReviewGroupResource groupName(String groupName) {
        this.groupName = groupName;
        return this;
    }
    /**
     * 组名称
     *
     * @return groupName
     **/
    @ApiModelProperty(value = "组名称")

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }


    public TeacherPeerReviewGroupResource groupSort(Integer groupSort) {
        this.groupSort = groupSort;
        return this;
    }
    /**
     * 组排序
     *
     * @return groupSort
     **/
    @ApiModelProperty(value = "组排序")

    public Integer getGroupSort() {
        return groupSort;
    }

    public void setGroupSort(Integer groupSort) {
        this.groupSort = groupSort;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeacherPeerReviewGroupResource teacherPeerReviewGroupResource = (TeacherPeerReviewGroupResource) o;
        return Objects.equals(this.groupId, teacherPeerReviewGroupResource.groupId) &&
                Objects.equals(this.groupNo, teacherPeerReviewGroupResource.groupNo) &&
                Objects.equals(this.groupName, teacherPeerReviewGroupResource.groupName) &&
                Objects.equals(this.groupSort, teacherPeerReviewGroupResource.groupSort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, groupNo, groupName,groupSort);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TeacherPeerReviewGroupResource {\n");
        sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
        sb.append("    groupNo: ").append(toIndentedString(groupNo)).append("\n");
        sb.append("    groupName: ").append(toIndentedString(groupName)).append("\n");
        sb.append("    groupSort: ").append(toIndentedString(groupSort)).append("\n");
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

