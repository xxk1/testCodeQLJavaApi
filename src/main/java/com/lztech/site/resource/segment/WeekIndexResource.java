package com.lztech.site.resource.segment;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * WeekIndexResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-17T06:48:44.958Z")

public class WeekIndexResource {
    @JsonProperty("weekIndex")
    @Valid
    private List<Integer> weekIndex = null;

    public WeekIndexResource weekIndex(List<Integer> weekIndex) {
        this.weekIndex = weekIndex;
        return this;
    }

    public WeekIndexResource addWeekIndexItem(Integer weekIndexItem) {
        if (this.weekIndex == null) {
            this.weekIndex = new ArrayList<Integer>();
        }
        this.weekIndex.add(weekIndexItem);
        return this;
    }

    /**
     * Get weekIndex
     *
     * @return weekIndex
     **/
    @ApiModelProperty(value = "")


    public List<Integer> getWeekIndex() {
        return weekIndex;
    }

    public void setWeekIndex(List<Integer> weekIndex) {
        this.weekIndex = weekIndex;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WeekIndexResource weekIndexResource = (WeekIndexResource) o;
        return Objects.equals(this.weekIndex, weekIndexResource.weekIndex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weekIndex);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class WeekIndexResource {\n");

        sb.append("    weekIndex: ").append(toIndentedString(weekIndex)).append("\n");
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

