package com.lztech.site.viewmodel.courseobjective;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseObjectiveSortRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-11-06T15:31:22.319+08:00")

public class CourseObjectiveSortRequest {
    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("operatorId")
    private String operatorId = null;

    @JsonProperty("operatorNo")
    private String operatorNo = null;

    @JsonProperty("operatorName")
    private String operatorName = null;


    @JsonProperty("sortList")
    @Valid
    private List<CourseObjectiveSortRequestSortList> sortList = new ArrayList<CourseObjectiveSortRequestSortList>();

    /**
     * 创建人ID
     *
     * @return operatorId
     **/
    @ApiModelProperty(value = "创建人ID")


    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public CourseObjectiveSortRequest operatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
        return this;
    }

    /**
     * 创建人编号
     *
     * @return operatorNo
     **/
    @ApiModelProperty(value = "创建人编号")


    public String getOperatorNo() {
        return operatorNo;
    }

    public void setOperatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
    }

    public CourseObjectiveSortRequest operatorName(String operatorName) {
        this.operatorName = operatorName;
        return this;
    }

    /**
     * 创建人名称
     *
     * @return operatorName
     **/
    @ApiModelProperty(value = "创建人名称")


    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }



    public CourseObjectiveSortRequest courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    /**
     * 课程ID
     *
     * @return courseId
     **/
    @ApiModelProperty(required = true, value = "课程ID")
    @NotNull


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public CourseObjectiveSortRequest sortList(List<CourseObjectiveSortRequestSortList> sortList) {
        this.sortList = sortList;
        return this;
    }

    public CourseObjectiveSortRequest addSortListItem(CourseObjectiveSortRequestSortList sortListItem) {
        this.sortList.add(sortListItem);
        return this;
    }

    /**
     * 排序信息列表
     *
     * @return sortList
     **/
    @ApiModelProperty(required = true, value = "排序信息列表")
    @NotNull

    @Valid

    public List<CourseObjectiveSortRequestSortList> getSortList() {
        return sortList;
    }

    public void setSortList(List<CourseObjectiveSortRequestSortList> sortList) {
        this.sortList = sortList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseObjectiveSortRequest courseObjectiveSortRequest = (CourseObjectiveSortRequest) o;
        return Objects.equals(this.courseId, courseObjectiveSortRequest.courseId) &&
                Objects.equals(this.sortList, courseObjectiveSortRequest.sortList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, sortList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseObjectiveSortRequest {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    sortList: ").append(toIndentedString(sortList)).append("\n");
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

