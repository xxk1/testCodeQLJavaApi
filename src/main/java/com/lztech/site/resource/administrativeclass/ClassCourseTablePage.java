package com.lztech.site.resource.administrativeclass;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ClassCourseTablePage
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-22T01:42:27.475Z")

public class ClassCourseTablePage {
    @JsonProperty("pageNum")
    private Integer pageNum = null;

    @JsonProperty("totalCount")
    private Integer totalCount = null;

    @JsonProperty("courseTableList")
    @Valid
    private List<ClassCourseTable> courseTableList = null;

    public ClassCourseTablePage pageNum(Integer pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    /**
     * 总页数
     *
     * @return pageNum
     **/
    @ApiModelProperty(example = "10", value = "总页数")


    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public ClassCourseTablePage totalCount(Integer totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    /**
     * 总数量
     *
     * @return totalCount
     **/
    @ApiModelProperty(example = "10", value = "总数量")


    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public ClassCourseTablePage courseTableList(List<ClassCourseTable> courseTableList) {
        this.courseTableList = courseTableList;
        return this;
    }

    public ClassCourseTablePage addCourseTableListItem(ClassCourseTable courseTableListItem) {
        if (this.courseTableList == null) {
            this.courseTableList = new ArrayList<ClassCourseTable>();
        }
        this.courseTableList.add(courseTableListItem);
        return this;
    }

    /**
     * Get courseTableList
     *
     * @return courseTableList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<ClassCourseTable> getCourseTableList() {
        return courseTableList;
    }

    public void setCourseTableList(List<ClassCourseTable> courseTableList) {
        this.courseTableList = courseTableList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClassCourseTablePage classCourseTablePage = (ClassCourseTablePage) o;
        return Objects.equals(this.pageNum, classCourseTablePage.pageNum) &&
                Objects.equals(this.totalCount, classCourseTablePage.totalCount) &&
                Objects.equals(this.courseTableList, classCourseTablePage.courseTableList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageNum, totalCount, courseTableList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ClassCourseTablePage {\n");

        sb.append("    pageNum: ").append(toIndentedString(pageNum)).append("\n");
        sb.append("    totalCount: ").append(toIndentedString(totalCount)).append("\n");
        sb.append("    courseTableList: ").append(toIndentedString(courseTableList)).append("\n");
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

