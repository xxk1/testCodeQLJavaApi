package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * WaitAttendClassCourseTableDetailParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-04-02T19:33:25.650+08:00")

public class WaitAttendClassCourseTableDetailParam {


    @JsonProperty("courseDate")
    private String courseDate = null;

    @JsonProperty("roomIdList")
    @Valid
    private List<String> roomIdList = null;

    @JsonProperty("collegeIdList")
    @Valid
    private List<String> collegeIdList = null;


    @JsonProperty("isDistinguishCourseStudentType")
    private String isDistinguishCourseStudentType = null;

    @JsonProperty("courseStudentTypes")
    private String courseStudentTypes = null;

    @JsonProperty("superviseCollegeType")
    private Integer superviseCollegeType = null;

    @JsonProperty("onlyGraduateCourse")
    private Integer onlyGraduateCourse = null;

    public WaitAttendClassCourseTableDetailParam courseDate(String courseDate) {
        this.courseDate = courseDate;
        return this;
    }

    /**
     * 课程日期
     *
     * @return courseDate
     **/
    @ApiModelProperty(value = "课程日期")


    public String getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(String courseDate) {
        this.courseDate = courseDate;
    }


    public WaitAttendClassCourseTableDetailParam roomIdList(List<String> roomIdList) {
        this.roomIdList = roomIdList;
        return this;
    }

    /**
     * 教室列表
     *
     * @return roomIdList
     **/
    @ApiModelProperty(value = "教室列表")


    public List<String> getRoomIdList() {
        return roomIdList;
    }

    public void setRoomIdList(List<String> roomIdList) {
        this.roomIdList = roomIdList;
    }

    public WaitAttendClassCourseTableDetailParam collegeIdList(List<String> collegeIdList) {
        this.collegeIdList = collegeIdList;
        return this;
    }

    /**
     * 学院id列表
     *
     * @return collegeIdList
     **/
    @ApiModelProperty(value = "学院id列表")


    public List<String> getCollegeIdList() {
        return collegeIdList;
    }

    public void setCollegeIdList(List<String> collegeIdList) {
        this.collegeIdList = collegeIdList;
    }



    public WaitAttendClassCourseTableDetailParam isDistinguishCourseStudentType(String isDistinguishCourseStudentType) {
        this.isDistinguishCourseStudentType = isDistinguishCourseStudentType;
        return this;
    }

    /**
     * 是否需要区分授课学生类型
     * @return isDistinguishCourseStudentType
     **/
    @ApiModelProperty(value = "是否需要区分授课学生类型")


    public String getIsDistinguishCourseStudentType() {
        return isDistinguishCourseStudentType;
    }

    public void setIsDistinguishCourseStudentType(String isDistinguishCourseStudentType) {
        this.isDistinguishCourseStudentType = isDistinguishCourseStudentType;
    }

    public WaitAttendClassCourseTableDetailParam courseStudentTypes(String courseStudentTypes) {
        this.courseStudentTypes = courseStudentTypes;
        return this;
    }

    /**
     * 授课学生类型，多个使用逗号隔开
     * @return courseStudentTypes
     **/
    @ApiModelProperty(value = "授课学生类型，多个使用逗号隔开")


    public String getCourseStudentTypes() {
        return courseStudentTypes;
    }

    public void setCourseStudentTypes(String courseStudentTypes) {
        this.courseStudentTypes = courseStudentTypes;
    }


    public WaitAttendClassCourseTableDetailParam superviseCollegeType(Integer superviseCollegeType) {
        this.superviseCollegeType = superviseCollegeType;
        return this;
    }

    /**
     * 督导听课查询学类型 0：授课教师学院；1：开课学院
     * @return superviseCollegeType
     **/
    @ApiModelProperty(value = "督导听课查询学类型 0：授课教师学院；1：开课学院")


    public Integer getSuperviseCollegeType() {
        return superviseCollegeType;
    }

    public void setSuperviseCollegeType(Integer superviseCollegeType) {
        this.superviseCollegeType = superviseCollegeType;
    }

    public WaitAttendClassCourseTableDetailParam onlyGraduateCourse(Integer onlyGraduateCourse) {
        this.onlyGraduateCourse = onlyGraduateCourse;
        return this;
    }

    /**
     * 是否研究生（1-是，其他不作为查询条件）
     * @return onlyGraduateCourse
     **/
    @ApiModelProperty(value = "是否研究生（1-是，其他不作为查询条件）")


    public Integer getOnlyGraduateCourse() {
        return onlyGraduateCourse;
    }

    public void setOnlyGraduateCourse(Integer onlyGraduateCourse) {
        this.onlyGraduateCourse = onlyGraduateCourse;
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof WaitAttendClassCourseTableDetailParam)) {
            return false;
        }
        WaitAttendClassCourseTableDetailParam that = (WaitAttendClassCourseTableDetailParam) o;
        return Objects.equals(courseDate, that.courseDate) &&
                Objects.equals(roomIdList, that.roomIdList) &&
                Objects.equals(collegeIdList, that.collegeIdList) &&
                Objects.equals(isDistinguishCourseStudentType, that.isDistinguishCourseStudentType) &&
                Objects.equals(courseStudentTypes, that.courseStudentTypes) &&
                Objects.equals(superviseCollegeType, that.superviseCollegeType) &&
                Objects.equals(onlyGraduateCourse, that.onlyGraduateCourse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseDate, roomIdList, collegeIdList, isDistinguishCourseStudentType,
                courseStudentTypes, superviseCollegeType, onlyGraduateCourse);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("WaitAttendClassCourseTableDetailParam{");
        sb.append("courseDate='").append(courseDate).append('\'');
        sb.append(", roomIdList=").append(roomIdList);
        sb.append(", collegeIdList=").append(collegeIdList);
        sb.append(", isDistinguishCourseStudentType='").append(isDistinguishCourseStudentType).append('\'');
        sb.append(", courseStudentTypes='").append(courseStudentTypes).append('\'');
        sb.append(", superviseCollegeType=").append(superviseCollegeType);
        sb.append(", onlyGraduateCourse=").append(onlyGraduateCourse);
        sb.append('}');
        return sb.toString();
    }
}
