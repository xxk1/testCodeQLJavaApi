package com.lztech.site.viewmodel.projectsuite;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ArticlesVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-21T05:33:12.625Z")


public class ArticlesVo {
    @JsonProperty("articlesId")
    //articles
    private String articlesId = null;

    @JsonProperty("articlesNo")
    private String articlesNo = null;

    @JsonProperty("number")
    private Integer number = null;

    public ArticlesVo articlesId(String articlesId) {
        this.articlesId = articlesId;
        return this;
    }

    /**
     * 物品id
     *
     * @return articlesId
     **/
    @ApiModelProperty(value = "物品id")


    public String getArticlesId() {
        return articlesId;
    }

    public void setArticlesId(String articlesId) {
        this.articlesId = articlesId;
    }

    public ArticlesVo articlesNo(String articlesNo) {
        this.articlesNo = articlesNo;
        return this;
    }

    /**
     * 物品编号
     *
     * @return articlesNo
     **/
    @ApiModelProperty(value = "物品编号")


    public String getArticlesNo() {
        return articlesNo;
    }

    public void setArticlesNo(String articlesNo) {
        this.articlesNo = articlesNo;
    }

    public ArticlesVo number(Integer number) {
        this.number = number;
        return this;
    }

    /**
     * 数量
     *
     * @return number
     **/
    @ApiModelProperty(value = "数量")


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
        ArticlesVo articlesVo = (ArticlesVo) o;
        return Objects.equals(this.articlesId, articlesVo.articlesId) &&
                Objects.equals(this.articlesNo, articlesVo.articlesNo) &&
                Objects.equals(this.number, articlesVo.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articlesId, articlesNo, number);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ArticlesVo {\n");

        sb.append("    articlesId: ").append(toIndentedString(articlesId)).append("\n");
        sb.append("    articlesNo: ").append(toIndentedString(articlesNo)).append("\n");
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

