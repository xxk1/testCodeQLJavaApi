package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseTableDetailPageRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-09-10T14:34:17.925+08:00")

public class CourseTableDetailPageRequest {
    @JsonProperty("collegeIds")
    private String collegeIds = null;

    @JsonProperty("courseDate")
    private String courseDate = null;

    @JsonProperty("courseIds")
    private String courseIds = null;

    @JsonProperty("roomIdList")
    @Valid
    private List<String> roomIdList = null;

    @JsonProperty("courseStudentTypeIds")
    private String courseStudentTypeIds = null;

    @JsonProperty("isDistinguishCourseStudentType")
    private String isDistinguishCourseStudentType = null;

    @JsonProperty("page")
    private Integer page = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

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

    @JsonProperty("studentType")
    private Integer studentType = null;

    public CourseTableDetailPageRequest collegeIds(String collegeIds) {
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

    public CourseTableDetailPageRequest courseDate(String courseDate) {
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

    public CourseTableDetailPageRequest courseIds(String courseIds) {
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

    public CourseTableDetailPageRequest roomIdList(List<String> roomIdList) {
        this.roomIdList = roomIdList;
        return this;
    }

    public CourseTableDetailPageRequest addRoomIdListItem(String roomIdListItem) {
        if (this.roomIdList == null) {
            this.roomIdList = new ArrayList<String>();
        }
        this.roomIdList.add(roomIdListItem);
        return this;
    }

    /**
     * 上课教室Id集合
     *
     * @return roomIdList
     **/
    @ApiModelProperty(value = "上课教室Id集合")


    public List<String> getRoomIdList() {
        return roomIdList;
    }

    public void setRoomIdList(List<String> roomIdList) {
        this.roomIdList = roomIdList;
    }

    public CourseTableDetailPageRequest courseStudentTypeIds(String courseStudentTypeIds) {
        this.courseStudentTypeIds = courseStudentTypeIds;
        return this;
    }

    /**
     * 开课类型
     *
     * @return courseStudentTypeIds
     **/
    @ApiModelProperty(value = "开课类型")


    public String getCourseStudentTypeIds() {
        return courseStudentTypeIds;
    }

    public void setCourseStudentTypeIds(String courseStudentTypeIds) {
        this.courseStudentTypeIds = courseStudentTypeIds;
    }

    public CourseTableDetailPageRequest isDistinguishCourseStudentType(String isDistinguishCourseStudentType) {
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

    public CourseTableDetailPageRequest page(Integer page) {
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

    public CourseTableDetailPageRequest pageSize(Integer pageSize) {
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

    public CourseTableDetailPageRequest searchCourseName(String searchCourseName) {
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

    public CourseTableDetailPageRequest searchParams(String searchParams) {
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

    public CourseTableDetailPageRequest searchRoomName(String searchRoomName) {
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

    public CourseTableDetailPageRequest searchTeacherName(String searchTeacherName) {
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

    public CourseTableDetailPageRequest segment(String segment) {
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

    public CourseTableDetailPageRequest sourceDataSource(String sourceDataSource) {
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

    public CourseTableDetailPageRequest studentType(Integer studentType) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseTableDetailPageRequest courseTableDetailPageRequest = (CourseTableDetailPageRequest) o;
        return Objects.equals(this.collegeIds, courseTableDetailPageRequest.collegeIds) &&
                Objects.equals(this.courseDate, courseTableDetailPageRequest.courseDate) &&
                Objects.equals(this.courseIds, courseTableDetailPageRequest.courseIds) &&
                Objects.equals(this.roomIdList, courseTableDetailPageRequest.roomIdList) &&
                Objects.equals(this.courseStudentTypeIds, courseTableDetailPageRequest.courseStudentTypeIds) &&
                Objects.equals(this.isDistinguishCourseStudentType, courseTableDetailPageRequest.isDistinguishCourseStudentType) &&
                Objects.equals(this.page, courseTableDetailPageRequest.page) &&
                Objects.equals(this.pageSize, courseTableDetailPageRequest.pageSize) &&
                Objects.equals(this.searchCourseName, courseTableDetailPageRequest.searchCourseName) &&
                Objects.equals(this.searchParams, courseTableDetailPageRequest.searchParams) &&
                Objects.equals(this.searchRoomName, courseTableDetailPageRequest.searchRoomName) &&
                Objects.equals(this.searchTeacherName, courseTableDetailPageRequest.searchTeacherName) &&
                Objects.equals(this.segment, courseTableDetailPageRequest.segment) &&
                Objects.equals(this.sourceDataSource, courseTableDetailPageRequest.sourceDataSource) &&
                Objects.equals(this.studentType, courseTableDetailPageRequest.studentType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(collegeIds, courseDate, courseIds, roomIdList, courseStudentTypeIds,
                isDistinguishCourseStudentType, page, pageSize, searchCourseName, searchParams,
                searchRoomName, searchTeacherName, segment, sourceDataSource, studentType);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTableDetailPageRequest {\n");

        sb.append("    collegeIds: ").append(toIndentedString(collegeIds)).append("\n");
        sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
        sb.append("    courseIds: ").append(toIndentedString(courseIds)).append("\n");
        sb.append("    roomIdList: ").append(toIndentedString(roomIdList)).append("\n");
        sb.append("    courseStudentTypeIds: ").append(toIndentedString(courseStudentTypeIds)).append("\n");
        sb.append("    isDistinguishCourseStudentType: ").append(toIndentedString(isDistinguishCourseStudentType)).append("\n");
        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    searchCourseName: ").append(toIndentedString(searchCourseName)).append("\n");
        sb.append("    searchParams: ").append(toIndentedString(searchParams)).append("\n");
        sb.append("    searchRoomName: ").append(toIndentedString(searchRoomName)).append("\n");
        sb.append("    searchTeacherName: ").append(toIndentedString(searchTeacherName)).append("\n");
        sb.append("    segment: ").append(toIndentedString(segment)).append("\n");
        sb.append("    sourceDataSource: ").append(toIndentedString(sourceDataSource)).append("\n");
        sb.append("    studentType: ").append(toIndentedString(studentType)).append("\n");
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

