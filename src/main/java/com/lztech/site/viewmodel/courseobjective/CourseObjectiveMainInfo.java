package com.lztech.site.viewmodel.courseobjective;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseObjectiveMainInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-11-06T16:10:03.853+08:00")

public class CourseObjectiveMainInfo {
    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("objectiveCount")
    private Integer objectiveCount = null;

    @JsonProperty("totalRelatedKnowledgePointNum")
    private Integer totalRelatedKnowledgePointNum = null;

    @JsonProperty("objectiveList")
    @Valid
    private List<CourseObjectiveResponse> objectiveList = null;

    public CourseObjectiveMainInfo courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    /**
     * 课程ID
     *
     * @return courseId
     **/
    @ApiModelProperty(value = "课程ID")


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public CourseObjectiveMainInfo objectiveCount(Integer objectiveCount) {
        this.objectiveCount = objectiveCount;
        return this;
    }

    /**
     * 课程目标数量
     *
     * @return objectiveCount
     **/
    @ApiModelProperty(value = "课程目标数量")


    public Integer getObjectiveCount() {
        return objectiveCount;
    }

    public void setObjectiveCount(Integer objectiveCount) {
        this.objectiveCount = objectiveCount;
    }

    public CourseObjectiveMainInfo totalRelatedKnowledgePointNum(Integer totalRelatedKnowledgePointNum) {
        this.totalRelatedKnowledgePointNum = totalRelatedKnowledgePointNum;
        return this;
    }

    /**
     * 关联知识点数量
     *
     * @return totalRelatedKnowledgePointNum
     **/
    @ApiModelProperty(value = "关联知识点数量")


    public Integer getTotalRelatedKnowledgePointNum() {
        return totalRelatedKnowledgePointNum;
    }

    public void setTotalRelatedKnowledgePointNum(Integer totalRelatedKnowledgePointNum) {
        this.totalRelatedKnowledgePointNum = totalRelatedKnowledgePointNum;
    }

    public CourseObjectiveMainInfo objectiveList(List<CourseObjectiveResponse> objectiveList) {
        this.objectiveList = objectiveList;
        return this;
    }

    public CourseObjectiveMainInfo addObjectiveListItem(CourseObjectiveResponse objectiveListItem) {
        if (this.objectiveList == null) {
            this.objectiveList = new ArrayList<CourseObjectiveResponse>();
        }
        this.objectiveList.add(objectiveListItem);
        return this;
    }

    /**
     * Get objectiveList
     *
     * @return objectiveList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<CourseObjectiveResponse> getObjectiveList() {
        return objectiveList;
    }

    public void setObjectiveList(List<CourseObjectiveResponse> objectiveList) {
        this.objectiveList = objectiveList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseObjectiveMainInfo courseObjectiveMainInfo = (CourseObjectiveMainInfo) o;
        return Objects.equals(this.courseId, courseObjectiveMainInfo.courseId) &&
                Objects.equals(this.objectiveCount, courseObjectiveMainInfo.objectiveCount) &&
                Objects.equals(this.totalRelatedKnowledgePointNum, courseObjectiveMainInfo.totalRelatedKnowledgePointNum) &&
                Objects.equals(this.objectiveList, courseObjectiveMainInfo.objectiveList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, objectiveCount, totalRelatedKnowledgePointNum, objectiveList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseObjectiveMainInfo {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    objectiveCount: ").append(toIndentedString(objectiveCount)).append("\n");
        sb.append("    totalRelatedKnowledgePointNum: ").append(toIndentedString(totalRelatedKnowledgePointNum)).append("\n");
        sb.append("    objectiveList: ").append(toIndentedString(objectiveList)).append("\n");
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

