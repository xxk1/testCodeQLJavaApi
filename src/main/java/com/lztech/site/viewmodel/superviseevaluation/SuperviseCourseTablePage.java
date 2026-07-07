package com.lztech.site.viewmodel.superviseevaluation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * SuperviseCourseTablePage
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T11:13:09.080Z")


public class SuperviseCourseTablePage {
    @JsonProperty("pageNum")
    private Integer pageNum = null;

    @JsonProperty("totalCount")
    private Integer totalCount = null;

    @JsonProperty("courseTableList")
    @Valid
    private List<SuperviseCourseTable> courseTableList = null;

    public SuperviseCourseTablePage pageNum(Integer pageNum) {
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

    public SuperviseCourseTablePage totalCount(Integer totalCount) {
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

    public SuperviseCourseTablePage courseTableList(List<SuperviseCourseTable> courseTableList) {
        this.courseTableList = courseTableList;
        return this;
    }

    public SuperviseCourseTablePage addCourseTableListItem(SuperviseCourseTable courseTableListItem) {
        if (this.courseTableList == null) {
            this.courseTableList = new ArrayList<SuperviseCourseTable>();
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

    public List<SuperviseCourseTable> getCourseTableList() {
        return courseTableList;
    }

    public void setCourseTableList(List<SuperviseCourseTable> courseTableList) {
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
        SuperviseCourseTablePage superviseCourseTablePage = (SuperviseCourseTablePage) o;
        return Objects.equals(this.pageNum, superviseCourseTablePage.pageNum) &&
                Objects.equals(this.totalCount, superviseCourseTablePage.totalCount) &&
                Objects.equals(this.courseTableList, superviseCourseTablePage.courseTableList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageNum, totalCount, courseTableList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SuperviseCourseTablePage {\n");

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

