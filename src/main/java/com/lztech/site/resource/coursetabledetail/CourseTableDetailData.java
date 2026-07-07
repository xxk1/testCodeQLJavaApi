package com.lztech.site.resource.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseTableDetailData
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-12-15T09:54:30.989Z")


public class CourseTableDetailData {
    @JsonProperty("studentType")
    private Integer studentType = null;

    @JsonProperty("page")
    private Integer page = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    @JsonProperty("startTime")
    private String startTime = null;

    @JsonProperty("searchName")
    private String searchName = null;

    @JsonProperty("endTime")
    private String endTime = null;

    @JsonProperty("type")
    private String type = null;

    public CourseTableDetailData studentType(Integer studentType) {
        this.studentType = studentType;
        return this;
    }

    /**
     * 开课类型
     *
     * @return studentType
     **/
    @ApiModelProperty(value = "开课类型")


    public Integer getStudentType() {
        return studentType;
    }

    public void setStudentType(Integer studentType) {
        this.studentType = studentType;
    }

    public CourseTableDetailData page(Integer page) {
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

    public CourseTableDetailData pageSize(Integer pageSize) {
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

    public CourseTableDetailData startTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * 开始时间
     *
     * @return startTime
     **/
    @ApiModelProperty(value = "开始时间")


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public CourseTableDetailData searchName(String searchName) {
        this.searchName = searchName;
        return this;
    }

    /**
     * 教师名称/课程名称/上课教室
     *
     * @return searchName
     **/
    @ApiModelProperty(value = "教师名称/课程名称/上课教室")


    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public CourseTableDetailData endTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    /**
     * 结束时间
     *
     * @return endTime
     **/
    @ApiModelProperty(value = "结束时间")


    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public CourseTableDetailData type(String type) {
        this.type = type;
        return this;
    }

    /**
     * 类别 0:理论课 1:实验课
     *
     * @return type
     **/
    @ApiModelProperty(value = "类别 0:理论课 1:实验课")


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseTableDetailData courseTableDetailData = (CourseTableDetailData) o;
        return Objects.equals(this.studentType, courseTableDetailData.studentType) &&
                Objects.equals(this.page, courseTableDetailData.page) &&
                Objects.equals(this.pageSize, courseTableDetailData.pageSize) &&
                Objects.equals(this.startTime, courseTableDetailData.startTime) &&
                Objects.equals(this.searchName, courseTableDetailData.searchName) &&
                Objects.equals(this.endTime, courseTableDetailData.endTime) &&
                Objects.equals(this.type, courseTableDetailData.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentType, page, pageSize, startTime, searchName, endTime, type);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTableDetailData {\n");

        sb.append("    studentType: ").append(toIndentedString(studentType)).append("\n");
        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
        sb.append("    searchName: ").append(toIndentedString(searchName)).append("\n");
        sb.append("    endTime: ").append(toIndentedString(endTime)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

