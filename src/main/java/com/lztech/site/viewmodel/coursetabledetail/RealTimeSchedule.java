package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * RealTimeSchedule
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-03-30T11:08:11.325Z")


public class RealTimeSchedule {
    @JsonProperty("courseTableDetailId")
    private String courseTableDetailId = null;

    @JsonProperty("nowCourseName")
    private String nowCourseName = null;

    @JsonProperty("nowCourseDate")
    private String nowCourseDate = null;

    @JsonProperty("nowCourseWeek")
    private String nowCourseWeek = null;

    @JsonProperty("nowCourseRoomName")
    private String nowCourseRoomName = null;

    @JsonProperty("nowCourseCollegeName")
    private String nowCourseCollegeName = null;

    @JsonProperty("nowCourseCategoryName")
    private String nowCourseCategoryName = null;

    @JsonProperty("nowCourseCategoryId")
    private String nowCourseCategoryId = null;

    @JsonProperty("nowCourseSection")
    private String nowCourseSection = null;

    @JsonProperty("nowCourseWeekly")
    private String nowCourseWeekly = null;

    @JsonProperty("nowCourseSectionTime")
    private String nowCourseSectionTime = null;

    @JsonProperty("nowCourseTeacherName")
    private String nowCourseTeacherName = null;

    @JsonProperty("nowCourseGroupName")
    private String nowCourseGroupName = null;

    @JsonProperty("nowCourseGroupCount")
    private String nowCourseGroupCount = null;

    @JsonProperty("nowProjectName")
    private String nowProjectName = null;

    @JsonProperty("nextCourseName")
    private String nextCourseName = null;

    @JsonProperty("nextCourseTime")
    private String nextCourseTime = null;

    @JsonProperty("nextCourseTeacherName")
    private String nextCourseTeacherName = null;

    @JsonProperty("nextCourseSegment")
    private String nextCourseSegment = null;

    @JsonProperty("nextProjectName")
    private String nextProjectName = null;

    public RealTimeSchedule courseTableDetailId(String courseTableDetailId) {
        this.courseTableDetailId = courseTableDetailId;
        return this;
    }

    /**
     * 当前课表详情id
     *
     * @return courseTableDetailId
     **/
    @ApiModelProperty(value = "当前课表详情id")


    public String getCourseTableDetailId() {
        return courseTableDetailId;
    }

    public void setCourseTableDetailId(String courseTableDetailId) {
        this.courseTableDetailId = courseTableDetailId;
    }

    public RealTimeSchedule nowCourseName(String nowCourseName) {
        this.nowCourseName = nowCourseName;
        return this;
    }

    /**
     * 当前课程名称
     *
     * @return nowCourseName
     **/
    @ApiModelProperty(value = "当前课程名称")


    public String getNowCourseName() {
        return nowCourseName;
    }

    public void setNowCourseName(String nowCourseName) {
        this.nowCourseName = nowCourseName;
    }

    public RealTimeSchedule nowCourseDate(String nowCourseDate) {
        this.nowCourseDate = nowCourseDate;
        return this;
    }

    /**
     * 当前课程日期
     *
     * @return nowCourseDate
     **/
    @ApiModelProperty(value = "当前课程日期")


    public String getNowCourseDate() {
        return nowCourseDate;
    }

    public void setNowCourseDate(String nowCourseDate) {
        this.nowCourseDate = nowCourseDate;
    }

    public RealTimeSchedule nowCourseWeek(String nowCourseWeek) {
        this.nowCourseWeek = nowCourseWeek;
        return this;
    }

    /**
     * 当前课程星期
     *
     * @return nowCourseWeek
     **/
    @ApiModelProperty(value = "当前课程星期")


    public String getNowCourseWeek() {
        return nowCourseWeek;
    }

    public void setNowCourseWeek(String nowCourseWeek) {
        this.nowCourseWeek = nowCourseWeek;
    }

    public RealTimeSchedule nowCourseRoomName(String nowCourseRoomName) {
        this.nowCourseRoomName = nowCourseRoomName;
        return this;
    }

    /**
     * 当前课程教室名称
     *
     * @return nowCourseRoomName
     **/
    @ApiModelProperty(value = "当前课程教室名称")


    public String getNowCourseRoomName() {
        return nowCourseRoomName;
    }

    public void setNowCourseRoomName(String nowCourseRoomName) {
        this.nowCourseRoomName = nowCourseRoomName;
    }

    public RealTimeSchedule nowCourseCollegeName(String nowCourseCollegeName) {
        this.nowCourseCollegeName = nowCourseCollegeName;
        return this;
    }

    /**
     * 当前课程学院名称
     *
     * @return nowCourseCollegeName
     **/
    @ApiModelProperty(value = "当前课程学院名称")


    public String getNowCourseCollegeName() {
        return nowCourseCollegeName;
    }

    public void setNowCourseCollegeName(String nowCourseCollegeName) {
        this.nowCourseCollegeName = nowCourseCollegeName;
    }

    public RealTimeSchedule nowCourseCategoryName(String nowCourseCategoryName) {
        this.nowCourseCategoryName = nowCourseCategoryName;
        return this;
    }

    /**
     * 当前课程类别名称
     *
     * @return nowCourseCategoryName
     **/
    @ApiModelProperty(value = "当前课程类别名称")


    public String getNowCourseCategoryName() {
        return nowCourseCategoryName;
    }

    public void setNowCourseCategoryName(String nowCourseCategoryName) {
        this.nowCourseCategoryName = nowCourseCategoryName;
    }

    public RealTimeSchedule nowCourseCategoryId(String nowCourseCategoryId) {
        this.nowCourseCategoryId = nowCourseCategoryId;
        return this;
    }

    /**
     * 当前课程类别Id
     *
     * @return nowCourseCategoryId
     **/
    @ApiModelProperty(value = "当前课程类别Id")


    public String getNowCourseCategoryId() {
        return nowCourseCategoryId;
    }

    public void setNowCourseCategoryId(String nowCourseCategoryId) {
        this.nowCourseCategoryId = nowCourseCategoryId;
    }

    public RealTimeSchedule nowCourseSection(String nowCourseSection) {
        this.nowCourseSection = nowCourseSection;
        return this;
    }

    /**
     * 当前课程节次，如：1,2
     *
     * @return nowCourseSection
     **/
    @ApiModelProperty(value = "当前课程节次，如：1,2")


    public String getNowCourseSection() {
        return nowCourseSection;
    }

    public void setNowCourseSection(String nowCourseSection) {
        this.nowCourseSection = nowCourseSection;
    }

    public RealTimeSchedule nowCourseWeekly(String nowCourseWeekly) {
        this.nowCourseWeekly = nowCourseWeekly;
        return this;
    }

    /**
     * 周次
     *
     * @return nowCourseWeekly
     **/
    @ApiModelProperty(value = "周次")


    public String getNowCourseWeekly() {
        return nowCourseWeekly;
    }

    public void setNowCourseWeekly(String nowCourseWeekly) {
        this.nowCourseWeekly = nowCourseWeekly;
    }

    public RealTimeSchedule nowCourseSectionTime(String nowCourseSectionTime) {
        this.nowCourseSectionTime = nowCourseSectionTime;
        return this;
    }

    /**
     * 当前课程节次时间，如：08:00-10:00
     *
     * @return nowCourseSectionTime
     **/
    @ApiModelProperty(value = "当前课程节次时间，如：08:00-10:00")


    public String getNowCourseSectionTime() {
        return nowCourseSectionTime;
    }

    public void setNowCourseSectionTime(String nowCourseSectionTime) {
        this.nowCourseSectionTime = nowCourseSectionTime;
    }

    public RealTimeSchedule nowCourseTeacherName(String nowCourseTeacherName) {
        this.nowCourseTeacherName = nowCourseTeacherName;
        return this;
    }

    /**
     * 当前课程老师名称
     *
     * @return nowCourseTeacherName
     **/
    @ApiModelProperty(value = "当前课程老师名称")


    public String getNowCourseTeacherName() {
        return nowCourseTeacherName;
    }

    public void setNowCourseTeacherName(String nowCourseTeacherName) {
        this.nowCourseTeacherName = nowCourseTeacherName;
    }

    public RealTimeSchedule nowCourseGroupName(String nowCourseGroupName) {
        this.nowCourseGroupName = nowCourseGroupName;
        return this;
    }

    /**
     * 当前课程分组名称
     *
     * @return nowCourseGroupName
     **/
    @ApiModelProperty(value = "当前课程分组名称")


    public String getNowCourseGroupName() {
        return nowCourseGroupName;
    }

    public void setNowCourseGroupName(String nowCourseGroupName) {
        this.nowCourseGroupName = nowCourseGroupName;
    }

    public RealTimeSchedule nowCourseGroupCount(String nowCourseGroupCount) {
        this.nowCourseGroupCount = nowCourseGroupCount;
        return this;
    }

    /**
     * 当前课程分组总人数
     *
     * @return nowCourseGroupCount
     **/
    @ApiModelProperty(value = "当前课程分组总人数")


    public String getNowCourseGroupCount() {
        return nowCourseGroupCount;
    }

    public void setNowCourseGroupCount(String nowCourseGroupCount) {
        this.nowCourseGroupCount = nowCourseGroupCount;
    }

    public RealTimeSchedule nowProjectName(String nowProjectName) {
        this.nowProjectName = nowProjectName;
        return this;
    }

    /**
     * 当前课表项目名称，多个使用逗号隔开
     *
     * @return nowProjectName
     **/
    @ApiModelProperty(value = "当前课表项目名称，多个使用逗号隔开")


    public String getNowProjectName() {
        return nowProjectName;
    }

    public void setNowProjectName(String nowProjectName) {
        this.nowProjectName = nowProjectName;
    }

    public RealTimeSchedule nextCourseName(String nextCourseName) {
        this.nextCourseName = nextCourseName;
        return this;
    }

    /**
     * 下一节课程名称
     *
     * @return nextCourseName
     **/
    @ApiModelProperty(value = "下一节课程名称")


    public String getNextCourseName() {
        return nextCourseName;
    }

    public void setNextCourseName(String nextCourseName) {
        this.nextCourseName = nextCourseName;
    }

    public RealTimeSchedule nextCourseTime(String nextCourseTime) {
        this.nextCourseTime = nextCourseTime;
        return this;
    }

    /**
     * 下一节课程节次时间（如：11:45-12:30）
     *
     * @return nextCourseTime
     **/
    @ApiModelProperty(value = "下一节课程节次时间（如：11:45-12:30）")


    public String getNextCourseTime() {
        return nextCourseTime;
    }

    public void setNextCourseTime(String nextCourseTime) {
        this.nextCourseTime = nextCourseTime;
    }

    public RealTimeSchedule nextCourseTeacherName(String nextCourseTeacherName) {
        this.nextCourseTeacherName = nextCourseTeacherName;
        return this;
    }

    /**
     * 下一节课程老师名称
     *
     * @return nextCourseTeacherName
     **/
    @ApiModelProperty(value = "下一节课程老师名称")


    public String getNextCourseTeacherName() {
        return nextCourseTeacherName;
    }

    public void setNextCourseTeacherName(String nextCourseTeacherName) {
        this.nextCourseTeacherName = nextCourseTeacherName;
    }

    public RealTimeSchedule nextCourseSegment(String nextCourseSegment) {
        this.nextCourseSegment = nextCourseSegment;
        return this;
    }

    /**
     * 下节课的节次信息
     *
     * @return nextCourseSegment
     **/
    @ApiModelProperty(value = "下节课的节次信息")


    public String getNextCourseSegment() {
        return nextCourseSegment;
    }

    public void setNextCourseSegment(String nextCourseSegment) {
        this.nextCourseSegment = nextCourseSegment;
    }

    public RealTimeSchedule nextProjectName(String nextProjectName) {
        this.nextProjectName = nextProjectName;
        return this;
    }

    /**
     * 下节课表项目名称，多个使用逗号隔开
     *
     * @return nextProjectName
     **/
    @ApiModelProperty(value = "下节课表项目名称，多个使用逗号隔开")


    public String getNextProjectName() {
        return nextProjectName;
    }

    public void setNextProjectName(String nextProjectName) {
        this.nextProjectName = nextProjectName;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RealTimeSchedule realTimeSchedule = (RealTimeSchedule) o;
        return Objects.equals(this.courseTableDetailId, realTimeSchedule.courseTableDetailId) &&
                Objects.equals(this.nowCourseName, realTimeSchedule.nowCourseName) &&
                Objects.equals(this.nowCourseDate, realTimeSchedule.nowCourseDate) &&
                Objects.equals(this.nowCourseWeek, realTimeSchedule.nowCourseWeek) &&
                Objects.equals(this.nowCourseRoomName, realTimeSchedule.nowCourseRoomName) &&
                Objects.equals(this.nowCourseCollegeName, realTimeSchedule.nowCourseCollegeName) &&
                Objects.equals(this.nowCourseCategoryName, realTimeSchedule.nowCourseCategoryName) &&
                Objects.equals(this.nowCourseCategoryId, realTimeSchedule.nowCourseCategoryId) &&
                Objects.equals(this.nowCourseSection, realTimeSchedule.nowCourseSection) &&
                Objects.equals(this.nowCourseWeekly, realTimeSchedule.nowCourseWeekly) &&
                Objects.equals(this.nowCourseSectionTime, realTimeSchedule.nowCourseSectionTime) &&
                Objects.equals(this.nowCourseTeacherName, realTimeSchedule.nowCourseTeacherName) &&
                Objects.equals(this.nowCourseGroupName, realTimeSchedule.nowCourseGroupName) &&
                Objects.equals(this.nowCourseGroupCount, realTimeSchedule.nowCourseGroupCount) &&
                Objects.equals(this.nowProjectName, realTimeSchedule.nowProjectName) &&
                Objects.equals(this.nextCourseName, realTimeSchedule.nextCourseName) &&
                Objects.equals(this.nextCourseTime, realTimeSchedule.nextCourseTime) &&
                Objects.equals(this.nextCourseTeacherName, realTimeSchedule.nextCourseTeacherName) &&
                Objects.equals(this.nextCourseSegment, realTimeSchedule.nextCourseSegment) &&
                Objects.equals(this.nextProjectName, realTimeSchedule.nextProjectName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseTableDetailId, nowCourseName, nowCourseDate, nowCourseWeek, nowCourseRoomName, nowCourseCollegeName,
                nowCourseCategoryName, nowCourseCategoryId, nowCourseSection, nowCourseWeekly, nowCourseSectionTime, nowCourseTeacherName,
                nowCourseGroupName, nowCourseGroupCount, nowProjectName, nextCourseName, nextCourseTime, nextCourseTeacherName, nextCourseSegment,
                nextProjectName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RealTimeSchedule {\n");

        sb.append("    courseTableDetailId: ").append(toIndentedString(courseTableDetailId)).append("\n");
        sb.append("    nowCourseName: ").append(toIndentedString(nowCourseName)).append("\n");
        sb.append("    nowCourseDate: ").append(toIndentedString(nowCourseDate)).append("\n");
        sb.append("    nowCourseWeek: ").append(toIndentedString(nowCourseWeek)).append("\n");
        sb.append("    nowCourseRoomName: ").append(toIndentedString(nowCourseRoomName)).append("\n");
        sb.append("    nowCourseCollegeName: ").append(toIndentedString(nowCourseCollegeName)).append("\n");
        sb.append("    nowCourseCategoryName: ").append(toIndentedString(nowCourseCategoryName)).append("\n");
        sb.append("    nowCourseCategoryId: ").append(toIndentedString(nowCourseCategoryId)).append("\n");
        sb.append("    nowCourseSection: ").append(toIndentedString(nowCourseSection)).append("\n");
        sb.append("    nowCourseWeekly: ").append(toIndentedString(nowCourseWeekly)).append("\n");
        sb.append("    nowCourseSectionTime: ").append(toIndentedString(nowCourseSectionTime)).append("\n");
        sb.append("    nowCourseTeacherName: ").append(toIndentedString(nowCourseTeacherName)).append("\n");
        sb.append("    nowCourseGroupName: ").append(toIndentedString(nowCourseGroupName)).append("\n");
        sb.append("    nowCourseGroupCount: ").append(toIndentedString(nowCourseGroupCount)).append("\n");
        sb.append("    nowProjectName: ").append(toIndentedString(nowProjectName)).append("\n");
        sb.append("    nextCourseName: ").append(toIndentedString(nextCourseName)).append("\n");
        sb.append("    nextCourseTime: ").append(toIndentedString(nextCourseTime)).append("\n");
        sb.append("    nextCourseTeacherName: ").append(toIndentedString(nextCourseTeacherName)).append("\n");
        sb.append("    nextCourseSegment: ").append(toIndentedString(nextCourseSegment)).append("\n");
        sb.append("    nextProjectName: ").append(toIndentedString(nextProjectName)).append("\n");
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

