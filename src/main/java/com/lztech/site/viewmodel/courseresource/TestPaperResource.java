package com.lztech.site.viewmodel.courseresource;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * TestPaperResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-03-10T07:07:09.455Z")


public class TestPaperResource {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("courseResourceId")
    private String courseResourceId = null;

    @JsonProperty("courseStructureId")
    private String courseStructureId = null;

    public TestPaperResource id(String id) {
        this.id = id;
        return this;
    }

    /**
     * 试卷id
     *
     * @return id
     **/
    @ApiModelProperty(value = "试卷id")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TestPaperResource name(String name) {
        this.name = name;
        return this;
    }

    /**
     * 试卷名称
     *
     * @return name
     **/
    @ApiModelProperty(value = "试卷名称")


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TestPaperResource courseResourceId(String courseResourceId) {
        this.courseResourceId = courseResourceId;
        return this;
    }

    /**
     * 资源id
     *
     * @return courseResourceId
     **/
    @ApiModelProperty(value = "资源id")


    public String getCourseResourceId() {
        return courseResourceId;
    }

    public void setCourseResourceId(String courseResourceId) {
        this.courseResourceId = courseResourceId;
    }

    public TestPaperResource courseStructureId(String courseStructureId) {
        this.courseStructureId = courseStructureId;
        return this;
    }

    /**
     * 课程结构id
     *
     * @return courseStructureId
     **/
    @ApiModelProperty(value = "课程结构id")


    public String getCourseStructureId() {
        return courseStructureId;
    }

    public void setCourseStructureId(String courseStructureId) {
        this.courseStructureId = courseStructureId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TestPaperResource testPaperResource = (TestPaperResource) o;
        return Objects.equals(this.id, testPaperResource.id) &&
                Objects.equals(this.name, testPaperResource.name) &&
                Objects.equals(this.courseResourceId, testPaperResource.courseResourceId) &&
                Objects.equals(this.courseStructureId, testPaperResource.courseStructureId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, courseResourceId, courseStructureId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TestPaperResource {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    courseResourceId: ").append(toIndentedString(courseResourceId)).append("\n");
        sb.append("    courseStructureId: ").append(toIndentedString(courseStructureId)).append("\n");
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

