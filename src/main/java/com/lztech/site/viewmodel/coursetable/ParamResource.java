package com.lztech.site.viewmodel.coursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ParamResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-03-20T02:45:07.455Z")


public class ParamResource {
    @JsonProperty("sources")
    private String sources = null;

    @JsonProperty("courseDate")
    private String courseDate = null;

    @JsonProperty("roomIds")
    private String roomIds = null;

    public ParamResource sources(String sources) {
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

    public ParamResource courseDate(String courseDate) {
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

    public ParamResource roomIds(String roomIds) {
        this.roomIds = roomIds;
        return this;
    }

    /**
     * 教室ids,多个用“，”隔开
     *
     * @return roomIds
     **/
    @ApiModelProperty(value = "教室ids,多个用“，”隔开")


    public String getRoomIds() {
        return roomIds;
    }

    public void setRoomIds(String roomIds) {
        this.roomIds = roomIds;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ParamResource paramResource = (ParamResource) o;
        return Objects.equals(this.sources, paramResource.sources) &&
                Objects.equals(this.courseDate, paramResource.courseDate) &&
                Objects.equals(this.roomIds, paramResource.roomIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sources, courseDate, roomIds);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ParamResource {\n");

        sb.append("    sources: ").append(toIndentedString(sources)).append("\n");
        sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
        sb.append("    roomIds: ").append(toIndentedString(roomIds)).append("\n");
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

