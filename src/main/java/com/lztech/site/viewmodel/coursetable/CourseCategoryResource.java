package com.lztech.site.viewmodel.coursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseCategoryResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-01-27T02:28:10.357Z")


public class CourseCategoryResource {
    @JsonProperty("courseCategoryId")
    private Integer courseCategoryId = null;

    @JsonProperty("courseCategoryName")
    private String courseCategoryName = null;

    @JsonProperty("sortName")
    private Integer sortName = null;

    public CourseCategoryResource courseCategoryId(Integer courseCategoryId) {
        this.courseCategoryId = courseCategoryId;
        return this;
    }

    /**
     * 课程类型id
     *
     * @return courseCategoryId
     **/
    @ApiModelProperty(value = "课程类型id")


    public Integer getCourseCategoryId() {
        return courseCategoryId;
    }

    public void setCourseCategoryId(Integer courseCategoryId) {
        this.courseCategoryId = courseCategoryId;
    }

    public CourseCategoryResource courseCategoryName(String courseCategoryName) {
        this.courseCategoryName = courseCategoryName;
        return this;
    }

    /**
     * 课程类型名称
     *
     * @return courseCategoryName
     **/
    @ApiModelProperty(value = "课程类型名称")


    public String getCourseCategoryName() {
        return courseCategoryName;
    }

    public void setCourseCategoryName(String courseCategoryName) {
        this.courseCategoryName = courseCategoryName;
    }

    public CourseCategoryResource sortName(Integer sortName) {
        this.sortName = sortName;
        return this;
    }

    /**
     * 顺序
     *
     * @return sortName
     **/
    @ApiModelProperty(value = "顺序")


    public Integer getSortName() {
        return sortName;
    }

    public void setSortName(Integer sortName) {
        this.sortName = sortName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseCategoryResource courseCategoryResource = (CourseCategoryResource) o;
        return Objects.equals(this.courseCategoryId, courseCategoryResource.courseCategoryId) &&
                Objects.equals(this.courseCategoryName, courseCategoryResource.courseCategoryName) &&
                Objects.equals(this.sortName, courseCategoryResource.sortName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseCategoryId, courseCategoryName, sortName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseCategoryResource {\n");

        sb.append("    courseCategoryId: ").append(toIndentedString(courseCategoryId)).append("\n");
        sb.append("    courseCategoryName: ").append(toIndentedString(courseCategoryName)).append("\n");
        sb.append("    sortName: ").append(toIndentedString(sortName)).append("\n");
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

