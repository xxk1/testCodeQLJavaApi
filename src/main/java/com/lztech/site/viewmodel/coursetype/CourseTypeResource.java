package com.lztech.site.viewmodel.coursetype;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseTypeResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-21T05:45:39.862Z")

public class CourseTypeResource {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("courseTypeName")
    private String courseTypeName = null;

    @JsonProperty("imagePath")
    private String imagePath = null;

    @JsonProperty("sort")
    private Integer sort = null;

    public CourseTypeResource id(String id) {
        this.id = id;
        return this;
    }

    /**
     * 课程分类id
     *
     * @return id
     **/
    @ApiModelProperty(value = "课程分类id")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CourseTypeResource courseTypeName(String courseTypeName) {
        this.courseTypeName = courseTypeName;
        return this;
    }

    /**
     * 课程分类名称
     *
     * @return courseTypeName
     **/
    @ApiModelProperty(value = "课程分类名称")


    public String getCourseTypeName() {
        return courseTypeName;
    }

    public void setCourseTypeName(String courseTypeName) {
        this.courseTypeName = courseTypeName;
    }

    public CourseTypeResource imagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }

    /**
     * 缩略图路径
     *
     * @return imagePath
     **/
    @ApiModelProperty(value = "缩略图路径")


    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public CourseTypeResource sort(Integer sort) {
        this.sort = sort;
        return this;
    }

    /**
     * 排序
     *
     * @return sort
     **/
    @ApiModelProperty(value = "排序")


    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseTypeResource courseTypeResource = (CourseTypeResource) o;
        return Objects.equals(this.id, courseTypeResource.id) &&
                Objects.equals(this.courseTypeName, courseTypeResource.courseTypeName) &&
                Objects.equals(this.imagePath, courseTypeResource.imagePath) &&
                Objects.equals(this.sort, courseTypeResource.sort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseTypeName, imagePath, sort);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTypeResource {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    courseTypeName: ").append(toIndentedString(courseTypeName)).append("\n");
        sb.append("    imagePath: ").append(toIndentedString(imagePath)).append("\n");
        sb.append("    sort: ").append(toIndentedString(sort)).append("\n");
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

