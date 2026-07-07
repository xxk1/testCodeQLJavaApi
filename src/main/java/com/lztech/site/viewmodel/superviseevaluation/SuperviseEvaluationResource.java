package com.lztech.site.viewmodel.superviseevaluation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * SuperviseEvaluationResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T11:25:45.180Z")


public class SuperviseEvaluationResource {

    @JsonProperty("courseDate")
    private String courseDate = null;

    @JsonProperty("roomId")
    private String roomId = null;

    @JsonProperty("searchStr")
    private String searchStr = null;

    @JsonProperty("page")
    private Integer page = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    @JsonProperty("courseTableIds")
    private String courseTableIds = null;

    @JsonProperty("collegeIds")
    private String collegeIds = null;

    @JsonProperty("courseStudentTypes")
    private String courseStudentTypes = null;

    @JsonProperty("isDistinguishCourseStudentType")
    private String isDistinguishCourseStudentType = null;

    @JsonProperty("superviseCollegeType")
    private Integer superviseCollegeType;

    @JsonProperty("isNeedFilterPoliticalCourse")
    private Boolean isNeedFilterPoliticalCourse = null;


    @JsonProperty("relatedScheduleTypes")
    private String relatedScheduleTypes = null;

    public String getRelatedScheduleTypes() {
        return relatedScheduleTypes;
    }

    public void setRelatedScheduleTypes(String relatedScheduleTypes) {
        this.relatedScheduleTypes = relatedScheduleTypes;
    }

    public SuperviseEvaluationResource isNeedFilterPoliticalCourse(Boolean isNeedFilterPoliticalCourse) {
        this.isNeedFilterPoliticalCourse = isNeedFilterPoliticalCourse;
        return this;
    }

    /**
     * 是否需要过思政课
     *
     * @return isNeedFilterPoliticalCourse
     **/
    @ApiModelProperty(value = "是否需要过思政课")


    public Boolean isIsNeedFilterPoliticalCourse() {
        return isNeedFilterPoliticalCourse;
    }

    public void setIsNeedFilterPoliticalCourse(Boolean isNeedFilterPoliticalCourse) {
        this.isNeedFilterPoliticalCourse = isNeedFilterPoliticalCourse;
    }

    public String getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(String courseDate) {
        this.courseDate = courseDate;
    }

    public Integer getSuperviseCollegeType() {
        return superviseCollegeType;
    }

    public void setSuperviseCollegeType(Integer superviseCollegeType) {
        this.superviseCollegeType = superviseCollegeType;
    }

    public String getIsDistinguishCourseStudentType() {
        return isDistinguishCourseStudentType;
    }

    public void setIsDistinguishCourseStudentType(String isDistinguishCourseStudentType) {
        this.isDistinguishCourseStudentType = isDistinguishCourseStudentType;
    }

    public String getCourseStudentTypes() {
        return courseStudentTypes;
    }

    public void setCourseStudentTypes(String courseStudentTypes) {
        this.courseStudentTypes = courseStudentTypes;
    }

    public SuperviseEvaluationResource roomId(String roomId) {
        this.roomId = roomId;
        return this;
    }

    /**
     * 教室id
     *
     * @return roomId
     **/
    @ApiModelProperty(value = "教室id")


    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public SuperviseEvaluationResource searchStr(String searchStr) {
        this.searchStr = searchStr;
        return this;
    }

    /**
     * 老师或课程名
     *
     * @return searchStr
     **/
    @ApiModelProperty(value = "老师或课程名")


    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }

    public SuperviseEvaluationResource page(Integer page) {
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

    public SuperviseEvaluationResource pageSize(Integer pageSize) {
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

    public SuperviseEvaluationResource courseTableIds(String courseTableIds) {
        this.courseTableIds = courseTableIds;
        return this;
    }

    /**
     * 课表ids
     *
     * @return courseTableIds
     **/
    @ApiModelProperty(value = "课表ids")


    public String getCourseTableIds() {
        return courseTableIds;
    }

    public void setCourseTableIds(String courseTableIds) {
        this.courseTableIds = courseTableIds;
    }

    public SuperviseEvaluationResource collegeIds(String collegeIds) {
        this.collegeIds = collegeIds;
        return this;
    }

    /**
     * 学院ids
     *
     * @return collegeIds
     **/
    @ApiModelProperty(value = "学院ids")


    public String getCollegeIds() {
        return collegeIds;
    }

    public void setCollegeIds(String collegeIds) {
        this.collegeIds = collegeIds;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SuperviseEvaluationResource superviseEvaluationResource = (SuperviseEvaluationResource) o;
        return Objects.equals(this.roomId, superviseEvaluationResource.roomId) &&
                Objects.equals(this.searchStr, superviseEvaluationResource.searchStr) &&
                Objects.equals(this.page, superviseEvaluationResource.page) &&
                Objects.equals(this.pageSize, superviseEvaluationResource.pageSize) &&
                Objects.equals(this.courseTableIds, superviseEvaluationResource.courseTableIds) &&
                Objects.equals(this.collegeIds, superviseEvaluationResource.collegeIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, searchStr, page, pageSize, courseTableIds, collegeIds);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SuperviseEvaluationResource {\n");

        sb.append("    roomId: ").append(toIndentedString(roomId)).append("\n");
        sb.append("    searchStr: ").append(toIndentedString(searchStr)).append("\n");
        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    courseTableIds: ").append(toIndentedString(courseTableIds)).append("\n");
        sb.append("    collegeIds: ").append(toIndentedString(collegeIds)).append("\n");
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

