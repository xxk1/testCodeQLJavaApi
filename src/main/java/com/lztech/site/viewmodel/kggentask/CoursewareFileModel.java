package com.lztech.site.viewmodel.kggentask;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CoursewareFileModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-07-22T10:17:27.444+08:00")

public class CoursewareFileModel {

    @JsonProperty("courseResourceId")
    private String courseResourceId = null;

    @JsonProperty("courseResourceName")
    private String courseResourceName = null;

    @JsonProperty("fileType")
    private String fileType = null;

    public CoursewareFileModel courseResourceId(String courseResourceId) {
        this.courseResourceId = courseResourceId;
        return this;
    }
    /**
     * 课件id
     * @return courseResourceId
     **/
    @ApiModelProperty(value = "课件id", position = 1)
    public String getCourseResourceId() {
        return courseResourceId;
    }

    public void setCourseResourceId(String courseResourceId) {
        this.courseResourceId = courseResourceId;
    }

    public CoursewareFileModel courseResourceName(String courseResourceName) {
        this.courseResourceName = courseResourceName;
        return this;
    }
    /**
     * 课件名称
     * @return courseResourceName
     **/
    @ApiModelProperty(value = "课件名称", position = 2)
    public String getCourseResourceName() {
        return courseResourceName;
    }
    public void setCourseResourceName(String courseResourceName) {
        this.courseResourceName = courseResourceName;
    }

    public CoursewareFileModel fileType(String fileType) {
        this.fileType = fileType;
        return this;
    }
    /**
     * 文件类型
     * @return fileType
     **/
    @ApiModelProperty(value = "文件类型", position = 3)
    public String getFileType() {
        return fileType;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CoursewareFileModel coursewareFileModel = (CoursewareFileModel) o;
        return Objects.equals(this.courseResourceId, coursewareFileModel.courseResourceId) &&
                Objects.equals(this.courseResourceName, coursewareFileModel.courseResourceName) &&
                Objects.equals(this.fileType, coursewareFileModel.fileType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseResourceId, courseResourceName, fileType);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class KgAIGenCoursewareParam {\n");
        sb.append("    courseResourceId: ").append(toIndentedString(courseResourceId)).append("\n");
        sb.append("    courseResourceName: ").append(toIndentedString(courseResourceName)).append("\n");
        sb.append("    fileType: ").append(toIndentedString(fileType)).append("\n");
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
