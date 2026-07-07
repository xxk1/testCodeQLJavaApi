package com.lztech.site.viewmodel.projectsuite;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ArticlesModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-21T12:43:46.941Z")


public class ArticlesModel {
    @JsonProperty("articlesId")
    private String articlesId = null;

    @JsonProperty("articlesNo")
    private String articlesNo = null;

    @JsonProperty("articlesName")
    private String articlesName = null;

    @JsonProperty("articlesNorms")
    private String articlesNorms = null;

    @JsonProperty("articlesUnits")
    private String articlesUnits = null;

    @JsonProperty("articlesTypeId")
    private String articlesTypeId = null;

    @JsonProperty("articlesTypeNo")
    private String articlesTypeNo = null;

    @JsonProperty("articlesTypeName")
    private String articlesTypeName = null;

    @JsonProperty("price")
    private String price = null;

    @JsonProperty("number")
    private Integer number = null;

    public ArticlesModel articlesId(String articlesId) {
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

    public ArticlesModel articlesNo(String articlesNo) {
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

    public ArticlesModel articlesName(String articlesName) {
        this.articlesName = articlesName;
        return this;
    }

    /**
     * 物品名称
     *
     * @return articlesName
     **/
    @ApiModelProperty(value = "物品名称")


    public String getArticlesName() {
        return articlesName;
    }

    public void setArticlesName(String articlesName) {
        this.articlesName = articlesName;
    }

    public ArticlesModel articlesNorms(String articlesNorms) {
        this.articlesNorms = articlesNorms;
        return this;
    }

    /**
     * 物品规则
     *
     * @return articlesNorms
     **/
    @ApiModelProperty(value = "物品规则")


    public String getArticlesNorms() {
        return articlesNorms;
    }

    public void setArticlesNorms(String articlesNorms) {
        this.articlesNorms = articlesNorms;
    }

    public ArticlesModel articlesUnits(String articlesUnits) {
        this.articlesUnits = articlesUnits;
        return this;
    }

    /**
     * 物品单位
     *
     * @return articlesUnits
     **/
    @ApiModelProperty(value = "物品单位")


    public String getArticlesUnits() {
        return articlesUnits;
    }

    public void setArticlesUnits(String articlesUnits) {
        this.articlesUnits = articlesUnits;
    }

    public ArticlesModel articlesTypeId(String articlesTypeId) {
        this.articlesTypeId = articlesTypeId;
        return this;
    }

    /**
     * 物品分类id
     *
     * @return articlesTypeId
     **/
    @ApiModelProperty(value = "物品分类id")


    public String getArticlesTypeId() {
        return articlesTypeId;
    }

    public void setArticlesTypeId(String articlesTypeId) {
        this.articlesTypeId = articlesTypeId;
    }

    public ArticlesModel articlesTypeNo(String articlesTypeNo) {
        this.articlesTypeNo = articlesTypeNo;
        return this;
    }

    /**
     * 物品分类编号
     *
     * @return articlesTypeNo
     **/
    @ApiModelProperty(value = "物品分类编号")


    public String getArticlesTypeNo() {
        return articlesTypeNo;
    }

    public void setArticlesTypeNo(String articlesTypeNo) {
        this.articlesTypeNo = articlesTypeNo;
    }

    public ArticlesModel articlesTypeName(String articlesTypeName) {
        this.articlesTypeName = articlesTypeName;
        return this;
    }

    /**
     * 物品分类名称
     *
     * @return articlesTypeName
     **/
    @ApiModelProperty(value = "物品分类名称")


    public String getArticlesTypeName() {
        return articlesTypeName;
    }

    public void setArticlesTypeName(String articlesTypeName) {
        this.articlesTypeName = articlesTypeName;
    }

    public ArticlesModel price(String price) {
        this.price = price;
        return this;
    }

    /**
     * 物品价格
     *
     * @return price
     **/
    @ApiModelProperty(value = "物品价格")


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ArticlesModel number(Integer number) {
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
        ArticlesModel articlesModel = (ArticlesModel) o;
        return Objects.equals(this.articlesId, articlesModel.articlesId) &&
                Objects.equals(this.articlesNo, articlesModel.articlesNo) &&
                Objects.equals(this.articlesName, articlesModel.articlesName) &&
                Objects.equals(this.articlesNorms, articlesModel.articlesNorms) &&
                Objects.equals(this.articlesUnits, articlesModel.articlesUnits) &&
                Objects.equals(this.articlesTypeId, articlesModel.articlesTypeId) &&
                Objects.equals(this.articlesTypeNo, articlesModel.articlesTypeNo) &&
                Objects.equals(this.articlesTypeName, articlesModel.articlesTypeName) &&
                Objects.equals(this.price, articlesModel.price) &&
                Objects.equals(this.number, articlesModel.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articlesId, articlesNo, articlesName, articlesNorms,
                articlesUnits, articlesTypeId, articlesTypeNo, articlesTypeName, price, number);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ArticlesModel {\n");

        sb.append("    articlesId: ").append(toIndentedString(articlesId)).append("\n");
        sb.append("    articlesNo: ").append(toIndentedString(articlesNo)).append("\n");
        sb.append("    articlesName: ").append(toIndentedString(articlesName)).append("\n");
        sb.append("    articlesNorms: ").append(toIndentedString(articlesNorms)).append("\n");
        sb.append("    articlesUnits: ").append(toIndentedString(articlesUnits)).append("\n");
        sb.append("    articlesTypeId: ").append(toIndentedString(articlesTypeId)).append("\n");
        sb.append("    articlesTypeNo: ").append(toIndentedString(articlesTypeNo)).append("\n");
        sb.append("    articlesTypeName: ").append(toIndentedString(articlesTypeName)).append("\n");
        sb.append("    price: ").append(toIndentedString(price)).append("\n");
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

