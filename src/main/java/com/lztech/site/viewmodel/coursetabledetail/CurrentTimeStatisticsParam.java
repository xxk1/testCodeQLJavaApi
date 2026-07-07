package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CurrentTimeStatisticsParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-03-04T08:01:01.136Z")


public class CurrentTimeStatisticsParam {

    @JsonProperty("isDistinguishCourseStudentType")
    private String isDistinguishCourseStudentType = null;
    @JsonProperty("courseStudentTypeIds")
    private String courseStudentTypeIds = null;
    @JsonProperty("courseTableCollegeIdList")
    @Valid
    private List<String> courseTableCollegeIdList = new ArrayList<>();
    @JsonProperty("courseIdList")
    @Valid
    private List<String> courseIdList = new ArrayList<>();

    @JsonProperty("roomIdList")
    @Valid
    private List<String> roomIdList = new ArrayList<>();

    public CurrentTimeStatisticsParam isDistinguishCourseStudentType(String isDistinguishCourseStudentType) {
        this.isDistinguishCourseStudentType = isDistinguishCourseStudentType;
        return this;
    }

    /**
     * isDistinguishCourseStudentType
     *
     * @return 是否需要区分授课学生类型（0或null-不区分，1-区分）
     **/
    @ApiModelProperty(value = "是否需要区分授课学生类型（0或null-不区分，1-区分）")
    public String getIsDistinguishCourseStudentType() {
        return isDistinguishCourseStudentType;
    }

    public void setIsDistinguishCourseStudentType(String isDistinguishCourseStudentType) {
        this.isDistinguishCourseStudentType = isDistinguishCourseStudentType;
    }

    public CurrentTimeStatisticsParam courseStudentTypeIds(String courseStudentTypeIds) {
        this.courseStudentTypeIds = courseStudentTypeIds;
        return this;
    }

    /**
     * courseStudentTypeIds
     *
     * @return 授课学生类型Ids（逗号分隔）
     **/
    @ApiModelProperty(value = "授课学生类型Ids（逗号分隔）")
    public String getCourseStudentTypeIds() {
        return courseStudentTypeIds;
    }

    public void setCourseStudentTypeIds(String courseStudentTypeIds) {
        this.courseStudentTypeIds = courseStudentTypeIds;
    }


    public CurrentTimeStatisticsParam courseTableCollegeIdList(List<String> courseTableCollegeIdList) {
        this.courseTableCollegeIdList = courseTableCollegeIdList;
        return this;
    }

    public CurrentTimeStatisticsParam addCourseTableCollegeIdListItem(String courseTableCollegeId) {
        if (this.courseTableCollegeIdList == null) {
            this.courseTableCollegeIdList = new ArrayList<String>();
        }
        this.courseTableCollegeIdList.add(courseTableCollegeId);
        return this;
    }

    /**
     * courseTableCollegeIdList
     *
     * @return 开课学院id列表
     **/
    @ApiModelProperty(value = "开课学院id列表")
    @Valid
    public List<String> getCourseTableCollegeIdList() {
        return courseTableCollegeIdList;
    }

    public void setCourseTableCollegeIdList(List<String> courseTableCollegeIdList) {
        this.courseTableCollegeIdList = courseTableCollegeIdList;
    }

    public CurrentTimeStatisticsParam courseIdList(List<String> courseIdList) {
        this.courseIdList = courseIdList;
        return this;
    }

    public CurrentTimeStatisticsParam addCourseIdListItem(String courseId) {
        if (this.courseIdList == null) {
            this.courseIdList = new ArrayList<String>();
        }
        this.courseIdList.add(courseId);
        return this;
    }

    /**
     * courseIdList
     *
     * @return 课程id列表
     **/
    @ApiModelProperty(value = "课程id列表")
    @Valid
    public List<String> getCourseIdList() {
        return courseIdList;
    }

    public void setCourseIdList(List<String> courseIdList) {
        this.courseIdList = courseIdList;
    }


    public CurrentTimeStatisticsParam roomIdList(List<String> roomIdList) {
        this.roomIdList = roomIdList;
        return this;
    }

    public CurrentTimeStatisticsParam addRoomIdListItem(String roomId) {
        if (this.roomIdList == null) {
            this.roomIdList = new ArrayList<String>();
        }
        this.roomIdList.add(roomId);
        return this;
    }

    /**
     * roomIdList
     *
     * @return 教室id列表
     **/
    @ApiModelProperty(value = "教室id列表")
    @Valid
    public List<String> getRoomIdList() {
        return roomIdList;
    }

    public void setRoomIdList(List<String> roomIdList) {
        this.roomIdList = roomIdList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CurrentTimeStatisticsParam currentTimeStatisticsParam = (CurrentTimeStatisticsParam) o;
        return Objects.equals(this.isDistinguishCourseStudentType, currentTimeStatisticsParam.isDistinguishCourseStudentType) &&
                Objects.equals(this.courseStudentTypeIds, currentTimeStatisticsParam.courseStudentTypeIds) &&
                Objects.equals(this.courseTableCollegeIdList, currentTimeStatisticsParam.courseTableCollegeIdList) &&
                Objects.equals(this.courseIdList, currentTimeStatisticsParam.courseIdList) &&
                Objects.equals(this.roomIdList, currentTimeStatisticsParam.roomIdList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isDistinguishCourseStudentType, courseStudentTypeIds, courseTableCollegeIdList, courseIdList,roomIdList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CurrentTimeStatisticsParam {\n");
        sb.append("    isDistinguishCourseStudentType: ").append(toIndentedString(isDistinguishCourseStudentType)).append("\n");
        sb.append("    courseStudentTypeIds: ").append(toIndentedString(courseStudentTypeIds)).append("\n");
        sb.append("    courseTableCollegeIdList: ").append(toIndentedString(courseTableCollegeIdList)).append("\n");
        sb.append("    courseIdList: ").append(toIndentedString(courseIdList)).append("\n");
        sb.append("    roomIdList: ").append(toIndentedString(roomIdList)).append("\n");
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
