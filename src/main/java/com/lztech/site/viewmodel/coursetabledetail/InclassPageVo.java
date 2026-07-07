package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * InclassPageVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-12-07T06:16:52.376Z")


public class InclassPageVo {
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

    @JsonProperty("studentType")
    private Integer studentType = null;

    @JsonProperty("isDistinguishCourseStudentType")
    private String isDistinguishCourseStudentType = null;

    @JsonProperty("courseStudentTypeIds")
    private String courseStudentTypeIds = null;

    public InclassPageVo page(Integer page) {
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

    public InclassPageVo pageSize(Integer pageSize) {
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

    public InclassPageVo courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    /**
     * 课程ID
     *
     * @return courseId
     **/
    @ApiModelProperty(value = "课程ID")


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public InclassPageVo courseIds(String courseIds) {
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

    public InclassPageVo collegeIds(String collegeIds) {
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

    public InclassPageVo searchParams(String searchParams) {
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

    public InclassPageVo studentType(Integer studentType) {
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

    public InclassPageVo isDistinguishCourseStudentType(String isDistinguishCourseStudentType) {
        this.isDistinguishCourseStudentType = isDistinguishCourseStudentType;
        return this;
    }

    /**
     * 是否需要区分授课学生类型
     *
     * @return isDistinguishCourseStudentType
     **/
    @ApiModelProperty(value = "是否需要区分授课学生类型")


    public String getIsDistinguishCourseStudentType() {
        return isDistinguishCourseStudentType;
    }

    public void setIsDistinguishCourseStudentType(String isDistinguishCourseStudentType) {
        this.isDistinguishCourseStudentType = isDistinguishCourseStudentType;
    }

    public InclassPageVo courseStudentTypeIds(String courseStudentTypeIds) {
        this.courseStudentTypeIds = courseStudentTypeIds;
        return this;
    }

    /**
     * 授课学生类型IDs
     *
     * @return courseStudentTypeIds
     **/
    @ApiModelProperty(value = "授课学生类型IDs")


    public String getCourseStudentTypeIds() {
        return courseStudentTypeIds;
    }

    public void setCourseStudentTypeIds(String courseStudentTypeIds) {
        this.courseStudentTypeIds = courseStudentTypeIds;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InclassPageVo inclassPageVo = (InclassPageVo) o;
        return Objects.equals(this.page, inclassPageVo.page) && Objects.equals(this.pageSize, inclassPageVo.pageSize) &&
                Objects.equals(this.courseId, inclassPageVo.courseId) && Objects.equals(this.courseIds, inclassPageVo.courseIds) &&
                Objects.equals(this.collegeIds, inclassPageVo.collegeIds) && Objects.equals(this.searchParams, inclassPageVo.searchParams) &&
                Objects.equals(this.studentType, inclassPageVo.studentType) &&
                Objects.equals(this.isDistinguishCourseStudentType, inclassPageVo.isDistinguishCourseStudentType) &&
                Objects.equals(this.courseStudentTypeIds, inclassPageVo.courseStudentTypeIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, pageSize, courseId, courseIds, collegeIds, searchParams, studentType, isDistinguishCourseStudentType,
                courseStudentTypeIds);
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
        sb.append("    studentType: ").append(toIndentedString(studentType)).append("\n");
        sb.append("    isDistinguishCourseStudentType: ").append(toIndentedString(isDistinguishCourseStudentType)).append("\n");
        sb.append("    courseStudentTypeIds: ").append(toIndentedString(courseStudentTypeIds)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

