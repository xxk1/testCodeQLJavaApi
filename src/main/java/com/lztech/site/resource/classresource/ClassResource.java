package com.lztech.site.resource.classresource;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ClassResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-11T06:50:00.765Z")

public class ClassResource {
    @JsonProperty("className")
    private String className = null;

    @JsonProperty("classAttribute")
    private Integer classAttribute = null;

    public Integer getClassAttribute() {
        return classAttribute;
    }

    public void setClassAttribute(Integer classAttribute) {
        this.classAttribute = classAttribute;
    }


    public ClassResource className(String className) {
        this.className = className;
        return this;
    }

    /**
     * 上课班级名称
     *
     * @return className
     **/
    @ApiModelProperty(value = "上课班级名称")


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClassResource classResource = (ClassResource) o;
        return Objects.equals(this.className, classResource.className);
    }

    @Override
    public int hashCode() {
        return Objects.hash(className);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ClassResource {\n");

        sb.append("    className: ").append(toIndentedString(className)).append("\n");
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

