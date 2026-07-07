package com.lztech.site.resource.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseInfoPageVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-04-14T08:20:46.503Z")


public class CourseInfoPageVo {
    @JsonProperty("pageCount")
    private Integer pageCount = null;

    @JsonProperty("total")
    private Integer total = null;

    @JsonProperty("page")
    private Integer page = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    @JsonProperty("courseInfoVos")
    @Valid
    private List<CourseInfoVo> courseInfoVos = null;

    public CourseInfoPageVo pageCount(Integer pageCount) {
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

    public CourseInfoPageVo total(Integer total) {
        this.total = total;
        return this;
    }

    /**
     * 总条数
     *
     * @return total
     **/
    @ApiModelProperty(value = "总条数")


    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public CourseInfoPageVo page(Integer page) {
        this.page = page;
        return this;
    }

    /**
     * 当前页
     *
     * @return page
     **/
    @ApiModelProperty(value = "当前页")


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public CourseInfoPageVo pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * 每页个数
     *
     * @return pageSize
     **/
    @ApiModelProperty(value = "每页个数")


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public CourseInfoPageVo courseInfoVos(List<CourseInfoVo> courseInfoVos) {
        this.courseInfoVos = courseInfoVos;
        return this;
    }

    public CourseInfoPageVo addCourseInfoVosItem(CourseInfoVo courseInfoVosItem) {
        if (this.courseInfoVos == null) {
            this.courseInfoVos = new ArrayList<CourseInfoVo>();
        }
        this.courseInfoVos.add(courseInfoVosItem);
        return this;
    }

    /**
     * Get courseInfoVos
     *
     * @return courseInfoVos
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<CourseInfoVo> getCourseInfoVos() {
        return courseInfoVos;
    }

    public void setCourseInfoVos(List<CourseInfoVo> courseInfoVos) {
        this.courseInfoVos = courseInfoVos;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseInfoPageVo courseInfoPageVo = (CourseInfoPageVo) o;
        return Objects.equals(this.pageCount, courseInfoPageVo.pageCount) &&
                Objects.equals(this.total, courseInfoPageVo.total) &&
                Objects.equals(this.page, courseInfoPageVo.page) &&
                Objects.equals(this.pageSize, courseInfoPageVo.pageSize) &&
                Objects.equals(this.courseInfoVos, courseInfoPageVo.courseInfoVos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageCount, total, page, pageSize, courseInfoVos);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseInfoPageVo {\n");

        sb.append("    pageCount: ").append(toIndentedString(pageCount)).append("\n");
        sb.append("    total: ").append(toIndentedString(total)).append("\n");
        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    courseInfoVos: ").append(toIndentedString(courseInfoVos)).append("\n");
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

