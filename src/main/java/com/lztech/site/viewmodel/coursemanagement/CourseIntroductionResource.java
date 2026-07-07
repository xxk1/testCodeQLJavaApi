package com.lztech.site.viewmodel.coursemanagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseIntroductionResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-02-17T05:54:03.354Z")


public class CourseIntroductionResource {
    @JsonProperty("classificationName")
    private String classificationName = null;

    @JsonProperty("classificationContent")
    private String classificationContent = null;

    public CourseIntroductionResource classificationName(String classificationName) {
        this.classificationName = classificationName;
        return this;
    }

    /**
     * 分类名称
     *
     * @return classificationName
     **/
    @ApiModelProperty(value = "分类名称")


    public String getClassificationName() {
        return classificationName;
    }

    public void setClassificationName(String classificationName) {
        this.classificationName = classificationName;
    }

    public CourseIntroductionResource classificationContent(String classificationContent) {
        this.classificationContent = classificationContent;
        return this;
    }

    /**
     * 分类内容
     *
     * @return classificationContent
     **/
    @ApiModelProperty(value = "分类内容")


    public String getClassificationContent() {
        return classificationContent;
    }

    public void setClassificationContent(String classificationContent) {
        this.classificationContent = classificationContent;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseIntroductionResource courseIntroductionResource = (CourseIntroductionResource) o;
        return Objects.equals(this.classificationName, courseIntroductionResource.classificationName) &&
                Objects.equals(this.classificationContent, courseIntroductionResource.classificationContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classificationName, classificationContent);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseIntroductionResource {\n");

        sb.append("    classificationName: ").append(toIndentedString(classificationName)).append("\n");
        sb.append("    classificationContent: ").append(toIndentedString(classificationContent)).append("\n");
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

