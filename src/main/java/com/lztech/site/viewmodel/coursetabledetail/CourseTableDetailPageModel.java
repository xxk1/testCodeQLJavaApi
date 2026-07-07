package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseTableDetailPageModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-03-21T12:18:57.276Z")


public class CourseTableDetailPageModel {
    @JsonProperty("pageCount")
    private Integer pageCount = null;

    @JsonProperty("total")
    private Integer total = null;

    @JsonProperty("page")
    private Integer page = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    @JsonProperty("courseTableDetailList")
    @Valid
    private List<CourseTableDetailModel> courseTableDetailModelList = null;

    public CourseTableDetailPageModel pageCount(Integer pageCount) {
        this.pageCount = pageCount;
        return this;
    }

    /**
     * 总页数
     *
     * @return pageCount
     **/
    @ApiModelProperty(value = "总页数")


    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public CourseTableDetailPageModel total(Integer total) {
        this.total = total;
        return this;
    }

    /**
     * 总条数
     *
     * @return total
     **/
    @ApiModelProperty(example = "10", value = "总条数")


    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public CourseTableDetailPageModel page(Integer page) {
        this.page = page;
        return this;
    }

    /**
     * 当前页
     *
     * @return page
     **/
    @ApiModelProperty(example = "10", value = "当前页")


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public CourseTableDetailPageModel pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * 每页个数
     *
     * @return pageSize
     **/
    @ApiModelProperty(example = "10", value = "每页个数")


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public CourseTableDetailPageModel courseTableDetailModelList(List<CourseTableDetailModel> courseTableDetailModelList) {
        this.courseTableDetailModelList = courseTableDetailModelList;
        return this;
    }

    public CourseTableDetailPageModel addCourseTableDetailModelListItem(CourseTableDetailModel courseTableDetailModel) {
        if (this.courseTableDetailModelList == null) {
            this.courseTableDetailModelList = new ArrayList<CourseTableDetailModel>();
        }
        this.courseTableDetailModelList.add(courseTableDetailModel);
        return this;
    }

    /**
     * Get courseTableDetailModelList
     *
     * @return courseTableDetailModelList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<CourseTableDetailModel> getCourseTableDetailModelList() {
        return courseTableDetailModelList;
    }

    public void setCourseTableDetailModelList(List<CourseTableDetailModel> courseTableDetailModelList) {
        this.courseTableDetailModelList = courseTableDetailModelList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseTableDetailPageModel courseTableDetailPageModel = (CourseTableDetailPageModel) o;
        return Objects.equals(this.pageCount, courseTableDetailPageModel.pageCount) &&
                Objects.equals(this.total, courseTableDetailPageModel.total) &&
                Objects.equals(this.page, courseTableDetailPageModel.page) &&
                Objects.equals(this.pageSize, courseTableDetailPageModel.pageSize) &&
                Objects.equals(this.courseTableDetailModelList, courseTableDetailPageModel.courseTableDetailModelList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageCount, total, page, pageSize, courseTableDetailModelList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTableDetailPageModel {\n");

        sb.append("    pageCount: ").append(toIndentedString(pageCount)).append("\n");
        sb.append("    total: ").append(toIndentedString(total)).append("\n");
        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    courseTableDetailList: ").append(toIndentedString(courseTableDetailModelList)).append("\n");
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

