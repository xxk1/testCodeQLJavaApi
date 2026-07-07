package com.lztech.site.viewmodel.teachingactivity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseActivityList
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-03-29T07:09:20.693Z")


public class CourseActivityList {
    @JsonProperty("pageNum")
    private Integer pageNum = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    @JsonProperty("totalCount")
    private Integer totalCount = null;

    @JsonProperty("courseResourceList")
    @Valid
    private List<CourseActivityVo> courseResourceList = null;

    public CourseActivityList pageNum(Integer pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    /**
     * 当前页码
     *
     * @return pageNum
     **/
    @ApiModelProperty(value = "当前页码")


    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public CourseActivityList pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * 每页记录数
     *
     * @return pageSize
     **/
    @ApiModelProperty(value = "每页记录数")


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public CourseActivityList totalCount(Integer totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    /**
     * 总条数
     *
     * @return totalCount
     **/
    @ApiModelProperty(value = "总条数")


    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public CourseActivityList courseResourceList(List<CourseActivityVo> courseResourceList) {
        this.courseResourceList = courseResourceList;
        return this;
    }

    public CourseActivityList addCourseResourceListItem(CourseActivityVo courseResourceListItem) {
        if (this.courseResourceList == null) {
            this.courseResourceList = new ArrayList<CourseActivityVo>();
        }
        this.courseResourceList.add(courseResourceListItem);
        return this;
    }

    /**
     * Get courseResourceList
     *
     * @return courseResourceList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<CourseActivityVo> getCourseResourceList() {
        return courseResourceList;
    }

    public void setCourseResourceList(List<CourseActivityVo> courseResourceList) {
        this.courseResourceList = courseResourceList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseActivityList courseActivityList = (CourseActivityList) o;
        return Objects.equals(this.pageNum, courseActivityList.pageNum) &&
                Objects.equals(this.pageSize, courseActivityList.pageSize) &&
                Objects.equals(this.totalCount, courseActivityList.totalCount) &&
                Objects.equals(this.courseResourceList, courseActivityList.courseResourceList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageNum, pageSize, totalCount, courseResourceList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseActivityList {\n");

        sb.append("    pageNum: ").append(toIndentedString(pageNum)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    totalCount: ").append(toIndentedString(totalCount)).append("\n");
        sb.append("    courseResourceList: ").append(toIndentedString(courseResourceList)).append("\n");
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

