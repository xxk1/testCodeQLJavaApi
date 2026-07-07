package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseTableDetailAdministrativeClassPageRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-09-10T14:34:17.925+08:00")

public class CourseTableDetailAdministrativeClassPageRequest {

    @JsonProperty("page")
    private Integer page = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    @JsonProperty("administrativeClassIds")
    private String administrativeClassIds = null;

    @JsonProperty("courseDate")
    private String courseDate = null;

    @JsonProperty("searchCourseName")
    private String searchCourseName = null;

    @JsonProperty("searchParams")
    private String searchParams = null;

    @JsonProperty("searchRoomName")
    private String searchRoomName = null;

    @JsonProperty("searchTeacherName")
    private String searchTeacherName = null;

    @JsonProperty("segment")
    private String segment = null;

    @JsonProperty("sourceDataSource")
    private String sourceDataSource = null;

    public CourseTableDetailAdministrativeClassPageRequest page(Integer page) {
        this.page = page;
        return this;
    }

    /**
     * 当前页数
     *
     * @return page
     **/
    @ApiModelProperty(value = "当前页数")

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public CourseTableDetailAdministrativeClassPageRequest pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * 每页条数
     *
     * @return pageSize
     **/
    @ApiModelProperty(value = "每页条数")

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public CourseTableDetailAdministrativeClassPageRequest administrativeClassIds(String administrativeClassIds) {
        this.administrativeClassIds = administrativeClassIds;
        return this;
    }

    /**
     * 行政班id,多个以“，”分割
     *
     * @return administrativeClassIds
     **/
    @ApiModelProperty(value = "行政班id,多个以“，”分割")


    public String getAdministrativeClassIds() {
        return administrativeClassIds;
    }

    public void setAdministrativeClassIds(String administrativeClassIds) {
        this.administrativeClassIds = administrativeClassIds;
    }

    public CourseTableDetailAdministrativeClassPageRequest courseDate(String courseDate) {
        this.courseDate = courseDate;
        return this;
    }

    /**
     * 上课日期
     *
     * @return courseDate
     **/
    @ApiModelProperty(value = "上课日期")

    public String getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(String courseDate) {
        this.courseDate = courseDate;
    }

    public CourseTableDetailAdministrativeClassPageRequest searchCourseName(String searchCourseName) {
        this.searchCourseName = searchCourseName;
        return this;
    }

    /**
     * 高级搜索-课程名称模糊查询条件
     *
     * @return searchCourseName
     **/
    @ApiModelProperty(value = "高级搜索-课程名称模糊查询条件")


    public String getSearchCourseName() {
        return searchCourseName;
    }

    public void setSearchCourseName(String searchCourseName) {
        this.searchCourseName = searchCourseName;
    }

    public CourseTableDetailAdministrativeClassPageRequest searchParams(String searchParams) {
        this.searchParams = searchParams;
        return this;
    }

    /**
     * 查询条件 老师名称或者课程名称
     *
     * @return searchParams
     **/
    @ApiModelProperty(value = "查询条件 老师名称或者课程名称")

    public String getSearchParams() {
        return searchParams;
    }

    public void setSearchParams(String searchParams) {
        this.searchParams = searchParams;
    }

    public CourseTableDetailAdministrativeClassPageRequest searchRoomName(String searchRoomName) {
        this.searchRoomName = searchRoomName;
        return this;
    }

    /**
     * 高级搜索-教室名称模糊查询条件
     *
     * @return searchRoomName
     **/
    @ApiModelProperty(value = "高级搜索-教室名称模糊查询条件")

    public String getSearchRoomName() {
        return searchRoomName;
    }

    public void setSearchRoomName(String searchRoomName) {
        this.searchRoomName = searchRoomName;
    }

    public CourseTableDetailAdministrativeClassPageRequest searchTeacherName(String searchTeacherName) {
        this.searchTeacherName = searchTeacherName;
        return this;
    }

    /**
     * 高级搜索-教师姓名模糊查询条件
     *
     * @return searchTeacherName
     **/
    @ApiModelProperty(value = "高级搜索-教师姓名模糊查询条件")

    public String getSearchTeacherName() {
        return searchTeacherName;
    }

    public void setSearchTeacherName(String searchTeacherName) {
        this.searchTeacherName = searchTeacherName;
    }

    public CourseTableDetailAdministrativeClassPageRequest segment(String segment) {
        this.segment = segment;
        return this;
    }

    /**
     * 节次
     *
     * @return segment
     **/
    @ApiModelProperty(value = "节次")

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public CourseTableDetailAdministrativeClassPageRequest sourceDataSource(String sourceDataSource) {
        this.sourceDataSource = sourceDataSource;
        return this;
    }

    /**
     * 数据来源
     *
     * @return sourceDataSource
     **/
    @ApiModelProperty(value = "数据来源")

    public String getSourceDataSource() {
        return sourceDataSource;
    }

    public void setSourceDataSource(String sourceDataSource) {
        this.sourceDataSource = sourceDataSource;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseTableDetailAdministrativeClassPageRequest request =
                (CourseTableDetailAdministrativeClassPageRequest) o;
        return Objects.equals(this.page, request.page) &&
                Objects.equals(this.pageSize, request.pageSize) &&
                Objects.equals(this.administrativeClassIds, request.pageSize) &&
                Objects.equals(this.courseDate, request.courseDate) &&
                Objects.equals(this.searchParams, request.searchParams) &&
                Objects.equals(this.searchCourseName, request.searchCourseName) &&
                Objects.equals(this.searchRoomName, request.searchRoomName) &&
                Objects.equals(this.searchTeacherName, request.searchTeacherName) &&
                Objects.equals(this.segment, request.segment) &&
                Objects.equals(this.sourceDataSource, request.sourceDataSource);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, pageSize, courseDate, administrativeClassIds, searchParams, searchCourseName,
                searchRoomName, searchTeacherName, segment, sourceDataSource);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTableDetailAdministrativeClassPageRequest {\n");
        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
        sb.append("    administrativeClassIds: ").append(toIndentedString(administrativeClassIds)).append("\n");
        sb.append("    searchParams: ").append(toIndentedString(searchParams)).append("\n");
        sb.append("    searchCourseName: ").append(toIndentedString(searchCourseName)).append("\n");
        sb.append("    searchRoomName: ").append(toIndentedString(searchRoomName)).append("\n");
        sb.append("    searchTeacherName: ").append(toIndentedString(searchTeacherName)).append("\n");
        sb.append("    segment: ").append(toIndentedString(segment)).append("\n");
        sb.append("    sourceDataSource: ").append(toIndentedString(sourceDataSource)).append("\n");
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
