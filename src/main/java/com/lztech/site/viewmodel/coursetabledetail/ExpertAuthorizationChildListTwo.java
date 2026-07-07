package com.lztech.site.viewmodel.coursetabledetail;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * ExpertAuthorizationChildListTwo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-01-12T02:22:49.339Z")


public class ExpertAuthorizationChildListTwo {
    @JsonProperty("teacherNameAndNo")
    private String teacherNameAndNo = null;

    @JsonProperty("courseDate")
    private String courseDate = null;

    @JsonProperty("segments")
    private String segments = null;

    @JsonProperty("groupId")
    private String groupId = null;

    @JsonProperty("roomId")
    private String roomId = null;

    @JsonProperty("courseTableId")
    private String courseTableId = null;

    @JsonProperty("courseTableDetailId")
    private String courseTableDetailId = null;

    @JsonProperty("nodeId")
    private String nodeId = null;

    @JsonProperty("nodeLevel")
    private Integer nodeLevel = null;

    @JsonProperty("parentId")
    private String parentId = null;

    @JsonProperty("childList")
    @Valid
    private List<ExpertAuthorizationChildListThree> childList = null;

    public String getCourseTableDetailId() {
        return courseTableDetailId;
    }

    public void setCourseTableDetailId(String courseTableDetailId) {
        this.courseTableDetailId = courseTableDetailId;
    }

    public ExpertAuthorizationChildListTwo teacherNameAndNo(String teacherNameAndNo) {
        this.teacherNameAndNo = teacherNameAndNo;
        return this;
    }

    /**
     * 教师姓名和编号（格式：名称 编号）
     *
     * @return teacherNameAndNo
     **/
    @ApiModelProperty(value = "教师姓名和编号（格式：名称 编号）")


    public String getTeacherNameAndNo() {
        return teacherNameAndNo;
    }

    public void setTeacherNameAndNo(String teacherNameAndNo) {
        this.teacherNameAndNo = teacherNameAndNo;
    }

    public ExpertAuthorizationChildListTwo courseDate(String courseDate) {
        this.courseDate = courseDate;
        return this;
    }

    /**
     * 上课日期
     *
     * @return courseDate
     **/
    @ApiModelProperty(value = "上课日期")


    public String getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(String courseDate) {
        this.courseDate = courseDate;
    }

    public ExpertAuthorizationChildListTwo segments(String segments) {
        this.segments = segments;
        return this;
    }

    /**
     * 节次
     *
     * @return segments
     **/
    @ApiModelProperty(value = "节次")


    public String getSegments() {
        return segments;
    }

    public void setSegments(String segments) {
        this.segments = segments;
    }

    public ExpertAuthorizationChildListTwo groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    /**
     * 班级id
     *
     * @return groupId
     **/
    @ApiModelProperty(value = "班级id")


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public ExpertAuthorizationChildListTwo roomId(String roomId) {
        this.roomId = roomId;
        return this;
    }

    /**
     * 教室id
     *
     * @return roomId
     **/
    @ApiModelProperty(value = "教室id")


    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public ExpertAuthorizationChildListTwo courseTableId(String courseTableId) {
        this.courseTableId = courseTableId;
        return this;
    }

    /**
     * 开课Id
     *
     * @return courseTableId
     **/
    @ApiModelProperty(value = "开课Id")


    public String getCourseTableId() {
        return courseTableId;
    }

    public void setCourseTableId(String courseTableId) {
        this.courseTableId = courseTableId;
    }

    public ExpertAuthorizationChildListTwo nodeId(String nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    /**
     * 节点ID（课表唯一联合字段拼接(course_table_id+course_date,segment)）
     *
     * @return nodeId
     **/
    @ApiModelProperty(value = "节点ID（课表唯一联合字段拼接(course_table_id+course_date,segment)）")


    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public ExpertAuthorizationChildListTwo nodeLevel(Integer nodeLevel) {
        this.nodeLevel = nodeLevel;
        return this;
    }

    /**
     * 节点层级
     *
     * @return nodeLevel
     **/
    @ApiModelProperty(value = "节点层级")


    public Integer getNodeLevel() {
        return nodeLevel;
    }

    public void setNodeLevel(Integer nodeLevel) {
        this.nodeLevel = nodeLevel;
    }

    public ExpertAuthorizationChildListTwo parentId(String parentId) {
        this.parentId = parentId;
        return this;
    }

    /**
     * 节点父级ID
     *
     * @return parentId
     **/
    @ApiModelProperty(value = "节点父级ID")


    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public ExpertAuthorizationChildListTwo childList(List<ExpertAuthorizationChildListThree> childList) {
        this.childList = childList;
        return this;
    }

    public ExpertAuthorizationChildListTwo addChildListItem(ExpertAuthorizationChildListThree childListItem) {
        if (this.childList == null) {
            this.childList = new ArrayList<ExpertAuthorizationChildListThree>();
        }
        this.childList.add(childListItem);
        return this;
    }

    /**
     * Get childList
     *
     * @return childList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<ExpertAuthorizationChildListThree> getChildList() {
        return childList;
    }

    public void setChildList(List<ExpertAuthorizationChildListThree> childList) {
        this.childList = childList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExpertAuthorizationChildListTwo expertAuthorizationChildListTwo = (ExpertAuthorizationChildListTwo) o;
        return Objects.equals(this.teacherNameAndNo, expertAuthorizationChildListTwo.teacherNameAndNo) &&
                Objects.equals(this.courseDate, expertAuthorizationChildListTwo.courseDate) &&
                Objects.equals(this.segments, expertAuthorizationChildListTwo.segments) &&
                Objects.equals(this.groupId, expertAuthorizationChildListTwo.groupId) &&
                Objects.equals(this.roomId, expertAuthorizationChildListTwo.roomId) &&
                Objects.equals(this.courseTableId, expertAuthorizationChildListTwo.courseTableId) &&
                Objects.equals(this.nodeId, expertAuthorizationChildListTwo.nodeId) &&
                Objects.equals(this.nodeLevel, expertAuthorizationChildListTwo.nodeLevel) &&
                Objects.equals(this.parentId, expertAuthorizationChildListTwo.parentId) &&
                Objects.equals(this.childList, expertAuthorizationChildListTwo.childList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherNameAndNo, courseDate, segments, groupId, roomId, courseTableId, nodeId, nodeLevel, parentId, childList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExpertAuthorizationChildListTwo {\n");

        sb.append("    teacherNameAndNo: ").append(toIndentedString(teacherNameAndNo)).append("\n");
        sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
        sb.append("    segments: ").append(toIndentedString(segments)).append("\n");
        sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
        sb.append("    roomId: ").append(toIndentedString(roomId)).append("\n");
        sb.append("    courseTableId: ").append(toIndentedString(courseTableId)).append("\n");
        sb.append("    nodeId: ").append(toIndentedString(nodeId)).append("\n");
        sb.append("    nodeLevel: ").append(toIndentedString(nodeLevel)).append("\n");
        sb.append("    parentId: ").append(toIndentedString(parentId)).append("\n");
        sb.append("    childList: ").append(toIndentedString(childList)).append("\n");
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

