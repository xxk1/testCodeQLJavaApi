package com.lztech.site.viewmodel.lowvaluearticles;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ProjectSuiteLowValueArticlesVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-27T07:01:24.425Z")


public class ProjectSuiteLowValueArticlesVo {
    @JsonProperty("lowValueArticlesId")
    private String lowValueArticlesId = null;

    @JsonProperty("number")
    private Integer number = null;

    public ProjectSuiteLowValueArticlesVo lowValueArticlesId(String lowValueArticlesId) {
        this.lowValueArticlesId = lowValueArticlesId;
        return this;
    }

    /**
     * 物品id
     *
     * @return lowValueArticlesId
     **/
    @ApiModelProperty(value = "物品id")


    public String getLowValueArticlesId() {
        return lowValueArticlesId;
    }

    public void setLowValueArticlesId(String lowValueArticlesId) {
        this.lowValueArticlesId = lowValueArticlesId;
    }

    public ProjectSuiteLowValueArticlesVo number(Integer number) {
        this.number = number;
        return this;
    }

    /**
     * 物品数量
     *
     * @return number
     **/
    @ApiModelProperty(value = "物品数量")


    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProjectSuiteLowValueArticlesVo projectSuiteLowValueArticlesVo = (ProjectSuiteLowValueArticlesVo) o;
        return Objects.equals(this.lowValueArticlesId, projectSuiteLowValueArticlesVo.lowValueArticlesId) &&
                Objects.equals(this.number, projectSuiteLowValueArticlesVo.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lowValueArticlesId, number);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ProjectSuiteLowValueArticlesVo {\n");

        sb.append("    lowValueArticlesId: ").append(toIndentedString(lowValueArticlesId)).append("\n");
        sb.append("    number: ").append(toIndentedString(number)).append("\n");
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

