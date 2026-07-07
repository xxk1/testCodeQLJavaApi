package com.lztech.site.viewmodel.teachingactivity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ActivityTypeStatisticVO
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-03-29T01:51:01.290Z")


public class ActivityTypeStatisticVO {
    @JsonProperty("value")
    private Integer value = null;

    @JsonProperty("name")
    private String name = null;

    public ActivityTypeStatisticVO value(Integer value) {
        this.value = value;
        return this;
    }

    /**
     * 活动数量
     *
     * @return value
     **/
    @ApiModelProperty(value = "活动数量")


    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public ActivityTypeStatisticVO name(String name) {
        this.name = name;
        return this;
    }

    /**
     * 活动类型
     *
     * @return name
     **/
    @ApiModelProperty(value = "活动类型")


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ActivityTypeStatisticVO activityTypeStatisticVO = (ActivityTypeStatisticVO) o;
        return Objects.equals(this.value, activityTypeStatisticVO.value) &&
                Objects.equals(this.name, activityTypeStatisticVO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ActivityTypeStatisticVO {\n");

        sb.append("    value: ").append(toIndentedString(value)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

