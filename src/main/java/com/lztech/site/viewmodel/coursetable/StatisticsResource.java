package com.lztech.site.viewmodel.coursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * StatisticsResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-12-02T06:39:41.502Z")


public class StatisticsResource {
    @JsonProperty("statisticsName")
    private String statisticsName = null;

    @JsonProperty("statisticsOrder")
    private Integer statisticsOrder = null;

    @JsonProperty("statisticsNumber")
    private Integer statisticsNumber = null;

    public StatisticsResource statisticsName(String statisticsName) {
        this.statisticsName = statisticsName;
        return this;
    }

    /**
     * 统计名称
     *
     * @return statisticsName
     **/
    @ApiModelProperty(value = "统计名称")


    public String getStatisticsName() {
        return statisticsName;
    }

    public void setStatisticsName(String statisticsName) {
        this.statisticsName = statisticsName;
    }

    public StatisticsResource statisticsOrder(Integer statisticsOrder) {
        this.statisticsOrder = statisticsOrder;
        return this;
    }

    /**
     * 统计顺序
     *
     * @return statisticsOrder
     **/
    @ApiModelProperty(value = "统计顺序")


    public Integer getStatisticsOrder() {
        return statisticsOrder;
    }

    public void setStatisticsOrder(Integer statisticsOrder) {
        this.statisticsOrder = statisticsOrder;
    }

    public StatisticsResource statisticsNumber(Integer statisticsNumber) {
        this.statisticsNumber = statisticsNumber;
        return this;
    }

    /**
     * 数量
     *
     * @return statisticsNumber
     **/
    @ApiModelProperty(value = "数量")


    public Integer getStatisticsNumber() {
        return statisticsNumber;
    }

    public void setStatisticsNumber(Integer statisticsNumber) {
        this.statisticsNumber = statisticsNumber;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StatisticsResource statisticsResource = (StatisticsResource) o;
        return Objects.equals(this.statisticsName, statisticsResource.statisticsName) &&
                Objects.equals(this.statisticsOrder, statisticsResource.statisticsOrder) &&
                Objects.equals(this.statisticsNumber, statisticsResource.statisticsNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statisticsName, statisticsOrder, statisticsNumber);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class StatisticsResource {\n");

        sb.append("    statisticsName: ").append(toIndentedString(statisticsName)).append("\n");
        sb.append("    statisticsOrder: ").append(toIndentedString(statisticsOrder)).append("\n");
        sb.append("    statisticsNumber: ").append(toIndentedString(statisticsNumber)).append("\n");
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

