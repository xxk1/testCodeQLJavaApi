package com.lztech.site.resource.classresource;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ClassSortResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-10-15T06:59:40.717Z")


public class ClassSortResource {
    @JsonProperty("classSort")
    @Valid
    private List<ClassSort> classSort = null;

    @JsonProperty("validCode")
    private String validCode = null;

    public ClassSortResource classSort(List<ClassSort> classSort) {
        this.classSort = classSort;
        return this;
    }

    public ClassSortResource addClassSortItem(ClassSort classSortItem) {
        if (this.classSort == null) {
            this.classSort = new ArrayList<ClassSort>();
        }
        this.classSort.add(classSortItem);
        return this;
    }

    /**
     * Get classSort
     *
     * @return classSort
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<ClassSort> getClassSort() {
        return classSort;
    }

    public void setClassSort(List<ClassSort> classSort) {
        this.classSort = classSort;
    }

    public ClassSortResource validCode(String validCode) {
        this.validCode = validCode;
        return this;
    }

    /**
     * 验证码（&signKey=123123）
     *
     * @return validCode
     **/
    @ApiModelProperty(value = "验证码（&signKey=123123）")


    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClassSortResource classSortResource = (ClassSortResource) o;
        return Objects.equals(this.classSort, classSortResource.classSort) &&
                Objects.equals(this.validCode, classSortResource.validCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classSort, validCode);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ClassSortResource {\n");

        sb.append("    classSort: ").append(toIndentedString(classSort)).append("\n");
        sb.append("    validCode: ").append(toIndentedString(validCode)).append("\n");
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

