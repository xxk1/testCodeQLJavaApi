package com.lztech.site.viewmodel.courseobjective;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseObjectiveSortRequestSortList
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-11-06T15:31:22.319+08:00")

public class CourseObjectiveSortRequestSortList {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("showOrder")
    private Integer showOrder = null;

    public CourseObjectiveSortRequestSortList id(String id) {
        this.id = id;
        return this;
    }

    /**
     * 课程目标ID
     *
     * @return id
     **/
    @ApiModelProperty(value = "课程目标ID")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CourseObjectiveSortRequestSortList showOrder(Integer showOrder) {
        this.showOrder = showOrder;
        return this;
    }

    /**
     * 新的显示顺序
     *
     * @return showOrder
     **/
    @ApiModelProperty(value = "新的显示顺序")


    public Integer getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseObjectiveSortRequestSortList courseObjectiveSortRequestSortList = (CourseObjectiveSortRequestSortList) o;
        return Objects.equals(this.id, courseObjectiveSortRequestSortList.id) &&
                Objects.equals(this.showOrder, courseObjectiveSortRequestSortList.showOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, showOrder);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseObjectiveSortRequestSortList {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    showOrder: ").append(toIndentedString(showOrder)).append("\n");
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

