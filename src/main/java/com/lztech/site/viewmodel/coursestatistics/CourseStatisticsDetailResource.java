package com.lztech.site.viewmodel.coursestatistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseStatisticsDetailResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-09-27T02:57:40.200Z")


public class CourseStatisticsDetailResource {
    @JsonProperty("typeName")
    private String typeName = null;

    @JsonProperty("typeNum")
    private Integer typeNum = null;

    public CourseStatisticsDetailResource typeName(String typeName) {
        this.typeName = typeName;
        return this;
    }

    /**
     * 类型名称
     *
     * @return typeName
     **/
    @ApiModelProperty(value = "类型名称")


    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public CourseStatisticsDetailResource typeNum(Integer typeNum) {
        this.typeNum = typeNum;
        return this;
    }

    /**
     * 类型数量
     *
     * @return typeNum
     **/
    @ApiModelProperty(value = "类型数量")


    public Integer getTypeNum() {
        return typeNum;
    }

    public void setTypeNum(Integer typeNum) {
        this.typeNum = typeNum;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseStatisticsDetailResource courseStatisticsDetailResource = (CourseStatisticsDetailResource) o;
        return Objects.equals(this.typeName, courseStatisticsDetailResource.typeName) &&
                Objects.equals(this.typeNum, courseStatisticsDetailResource.typeNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeName, typeNum);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseStatisticsDetailResource {\n");

        sb.append("    typeName: ").append(toIndentedString(typeName)).append("\n");
        sb.append("    typeNum: ").append(toIndentedString(typeNum)).append("\n");
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

