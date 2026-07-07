package com.lztech.site.viewmodel.coursematerial;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lztech.site.viewmodel.coursemanagement.CourseResourceVo;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseMateriaList
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-02-17T03:15:02.960Z")


public class CourseMateriaList {
    @JsonProperty("pageNum")
    private Integer pageNum = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    @JsonProperty("totalCount")
    private Integer totalCount = null;

    @JsonProperty("courseResourceList")
    @Valid
    private List<CourseResourceVo> courseResourceList = null;

    public CourseMateriaList pageNum(Integer pageNum) {
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

    public CourseMateriaList pageSize(Integer pageSize) {
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

    public CourseMateriaList totalCount(Integer totalCount) {
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

    public CourseMateriaList courseResourceList(List<CourseResourceVo> courseResourceList) {
        this.courseResourceList = courseResourceList;
        return this;
    }

    public CourseMateriaList addCourseResourceListItem(CourseResourceVo courseResourceListItem) {
        if (this.courseResourceList == null) {
            this.courseResourceList = new ArrayList<CourseResourceVo>();
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

    public List<CourseResourceVo> getCourseResourceList() {
        return courseResourceList;
    }

    public void setCourseResourceList(List<CourseResourceVo> courseResourceList) {
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
        CourseMateriaList courseMateriaList = (CourseMateriaList) o;
        return Objects.equals(this.pageNum, courseMateriaList.pageNum) &&
                Objects.equals(this.pageSize, courseMateriaList.pageSize) &&
                Objects.equals(this.totalCount, courseMateriaList.totalCount) &&
                Objects.equals(this.courseResourceList, courseMateriaList.courseResourceList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageNum, pageSize, totalCount, courseResourceList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseMateriaList {\n");

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

