package com.lztech.site.viewmodel.courseexpansion;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseExpansionQuery
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2026-01-29T16:18:20.693+08:00")

public class CourseExpansionQuery {
    @JsonProperty("expansionKey")
    private String expansionKey = null;

    @JsonProperty("courseNameOrCode")
    private String courseNameOrCode = null;

    @JsonProperty("page")
    private Integer page = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    public CourseExpansionQuery expansionKey(String expansionKey) {
        this.expansionKey = expansionKey;
        return this;
    }

    /**
     * 扩展属性key
     *
     * @return expansionKey
     **/
    @ApiModelProperty(value = "扩展属性key")


    public String getExpansionKey() {
        return expansionKey;
    }

    public void setExpansionKey(String expansionKey) {
        this.expansionKey = expansionKey;
    }

    public CourseExpansionQuery courseNameOrCode(String courseNameOrCode) {
        this.courseNameOrCode = courseNameOrCode;
        return this;
    }

    /**
     * 课程名称或者编号
     *
     * @return courseNameOrCode
     **/
    @ApiModelProperty(value = "课程名称或者编号")


    public String getCourseNameOrCode() {
        return courseNameOrCode;
    }

    public void setCourseNameOrCode(String courseNameOrCode) {
        this.courseNameOrCode = courseNameOrCode;
    }

    public CourseExpansionQuery page(Integer page) {
        this.page = page;
        return this;
    }

    /**
     * 页码
     *
     * @return page
     **/
    @ApiModelProperty(value = "页码")


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public CourseExpansionQuery pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * 每页数量
     *
     * @return pageSize
     **/
    @ApiModelProperty(value = "每页数量")


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseExpansionQuery courseExpansionQuery = (CourseExpansionQuery) o;
        return Objects.equals(this.expansionKey, courseExpansionQuery.expansionKey) &&
                Objects.equals(this.courseNameOrCode, courseExpansionQuery.courseNameOrCode) &&
                Objects.equals(this.page, courseExpansionQuery.page) &&
                Objects.equals(this.pageSize, courseExpansionQuery.pageSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expansionKey, courseNameOrCode, page, pageSize);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseExpansionQuery {\n");

        sb.append("    expansionKey: ").append(toIndentedString(expansionKey)).append("\n");
        sb.append("    courseNameOrCode: ").append(toIndentedString(courseNameOrCode)).append("\n");
        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
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

