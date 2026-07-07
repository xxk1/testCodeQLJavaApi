package com.lztech.site.viewmodel.teachingactivity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * TeachingActivityType
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-03-29T11:00:56.778Z")


public class TeachingActivityType {
    @JsonProperty("name")
    private String name = null;

    @JsonProperty("index")
    private Integer index = null;

    public TeachingActivityType name(String name) {
        this.name = name;
        return this;
    }

    /**
     * 类型名称
     *
     * @return name
     **/
    @ApiModelProperty(value = "类型名称")


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TeachingActivityType index(Integer index) {
        this.index = index;
        return this;
    }

    /**
     * 类型索引
     *
     * @return index
     **/
    @ApiModelProperty(value = "类型索引")


    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeachingActivityType teachingActivityType = (TeachingActivityType) o;
        return Objects.equals(this.name, teachingActivityType.name) &&
                Objects.equals(this.index, teachingActivityType.index);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, index);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TeachingActivityType {\n");

        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    index: ").append(toIndentedString(index)).append("\n");
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

