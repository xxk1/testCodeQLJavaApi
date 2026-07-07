package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * InclassPageVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-01-05T08:03:59.343Z")


public class InclassModelRequest {
    @JsonProperty("page")
    private Integer page = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseIds")
    private String courseIds = null;

    @JsonProperty("collegeIds")
    private String collegeIds = null;

    @JsonProperty("searchParams")
    private String searchParams = null;

    @JsonProperty("sources")
    private String sources = null;

    public InclassModelRequest page(Integer page) {
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

    public InclassModelRequest pageSize(Integer pageSize) {
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

    public InclassModelRequest courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    /**
     * 课程Id
     *
     * @return courseId
     **/
    @ApiModelProperty(value = "课程Id")


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public InclassModelRequest courseIds(String courseIds) {
        this.courseIds = courseIds;
        return this;
    }

    /**
     * 课程Id,多个以“，”分割
     *
     * @return courseIds
     **/
    @ApiModelProperty(value = "课程Id,多个以“，”分割")


    public String getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(String courseIds) {
        this.courseIds = courseIds;
    }

    public InclassModelRequest collegeIds(String collegeIds) {
        this.collegeIds = collegeIds;
        return this;
    }

    /**
     * 学院id,多个以“，”分割
     *
     * @return collegeIds
     **/
    @ApiModelProperty(value = "学院id,多个以“，”分割")


    public String getCollegeIds() {
        return collegeIds;
    }

    public void setCollegeIds(String collegeIds) {
        this.collegeIds = collegeIds;
    }

    public InclassModelRequest searchParams(String searchParams) {
        this.searchParams = searchParams;
        return this;
    }

    /**
     * 查询条件 教室/课程/教师 多个使用空格隔开
     *
     * @return searchParams
     **/
    @ApiModelProperty(value = "查询条件 教室/课程/教师 多个使用空格隔开")


    public String getSearchParams() {
        return searchParams;
    }

    public void setSearchParams(String searchParams) {
        this.searchParams = searchParams;
    }

    public InclassModelRequest sources(String sources) {
        this.sources = sources;
        return this;
    }

    /**
     * 数据来源，多个使用逗号隔开
     *
     * @return sources
     **/
    @ApiModelProperty(value = "数据来源，多个使用逗号隔开")


    public String getSources() {
        return sources;
    }

    public void setSources(String sources) {
        this.sources = sources;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InclassModelRequest inclassPageVo = (InclassModelRequest) o;
        return Objects.equals(this.page, inclassPageVo.page) &&
                Objects.equals(this.pageSize, inclassPageVo.pageSize) &&
                Objects.equals(this.courseId, inclassPageVo.courseId) &&
                Objects.equals(this.courseIds, inclassPageVo.courseIds) &&
                Objects.equals(this.collegeIds, inclassPageVo.collegeIds) &&
                Objects.equals(this.searchParams, inclassPageVo.searchParams) &&
                Objects.equals(this.sources, inclassPageVo.sources)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, pageSize, courseId, courseIds, collegeIds, searchParams,sources);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class InclassPageVo {\n");

        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseIds: ").append(toIndentedString(courseIds)).append("\n");
        sb.append("    collegeIds: ").append(toIndentedString(collegeIds)).append("\n");
        sb.append("    searchParams: ").append(toIndentedString(searchParams)).append("\n");
        sb.append("    sources: ").append(toIndentedString(sources)).append("\n");
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

