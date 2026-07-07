package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseTableDetailStatusResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-05-14T11:34:06.326+08:00")

public class CourseTableDetailStatusResource {
    @JsonProperty("courseTableDetailId")
    private String courseTableDetailId = null;

    @JsonProperty("courseTableDetailStatus")
    private Integer courseTableDetailStatus = null;

    @JsonProperty("hasLive")
    private Boolean hasLive = null;

    @JsonProperty("roomId")
    private String roomId = null;

    public CourseTableDetailStatusResource courseTableDetailId(String courseTableDetailId) {
        this.courseTableDetailId = courseTableDetailId;
        return this;
    }

    /**
     * 课表明细id
     *
     * @return courseTableDetailId
     **/
    @ApiModelProperty(value = "课表明细id")


    public String getCourseTableDetailId() {
        return courseTableDetailId;
    }

    public void setCourseTableDetailId(String courseTableDetailId) {
        this.courseTableDetailId = courseTableDetailId;
    }

    public CourseTableDetailStatusResource courseTableDetailStatus(Integer courseTableDetailStatus) {
        this.courseTableDetailStatus = courseTableDetailStatus;
        return this;
    }

    /**
     * 课表明细状态 0:已结束；1:进行中；2:未开始
     *
     * @return courseTableDetailStatus
     **/
    @ApiModelProperty(value = "课表明细状态 0:已结束；1:进行中；2:未开始;3:不存在")


    public Integer getCourseTableDetailStatus() {
        return courseTableDetailStatus;
    }

    public void setCourseTableDetailStatus(Integer courseTableDetailStatus) {
        this.courseTableDetailStatus = courseTableDetailStatus;
    }

    public CourseTableDetailStatusResource hasLive(Boolean hasLive) {
        this.hasLive = hasLive;
        return this;
    }

    /**
     * 是否直播 0:否；1:是
     *
     * @return hasLive
     **/
    @ApiModelProperty(value = "是否直播 0:否；1:是")


    public Boolean isHasLive() {
        return hasLive;
    }

    public void setHasLive(Boolean hasLive) {
        this.hasLive = hasLive;
    }

    public CourseTableDetailStatusResource roomId(String roomId) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseTableDetailStatusResource courseTableDetailStatusResource = (CourseTableDetailStatusResource) o;
        return Objects.equals(this.courseTableDetailId, courseTableDetailStatusResource.courseTableDetailId) &&
                Objects.equals(this.courseTableDetailStatus, courseTableDetailStatusResource.courseTableDetailStatus) &&
                Objects.equals(this.hasLive, courseTableDetailStatusResource.hasLive) &&
                Objects.equals(this.roomId, courseTableDetailStatusResource.roomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseTableDetailId, courseTableDetailStatus, hasLive, roomId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTableDetailStatusResource {\n");

        sb.append("    courseTableDetailId: ").append(toIndentedString(courseTableDetailId)).append("\n");
        sb.append("    courseTableDetailStatus: ").append(toIndentedString(courseTableDetailStatus)).append("\n");
        sb.append("    hasLive: ").append(toIndentedString(hasLive)).append("\n");
        sb.append("    roomId: ").append(toIndentedString(roomId)).append("\n");
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

