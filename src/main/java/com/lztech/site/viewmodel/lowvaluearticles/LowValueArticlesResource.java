package com.lztech.site.viewmodel.lowvaluearticles;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * LowValueArticlesResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-27T07:01:24.425Z")


public class LowValueArticlesResource {
    @JsonProperty("lowValueArticlesId")
    private String lowValueArticlesId = null;

    @JsonProperty("lowValueArticlesNo")
    private String lowValueArticlesNo = null;

    @JsonProperty("lowValueArticlesName")
    private String lowValueArticlesName = null;

    @JsonProperty("lowValueArticlesTypeId")
    private String lowValueArticlesTypeId = null;

    @JsonProperty("lowValueArticlesTypeName")
    private String lowValueArticlesTypeName = null;

    @JsonProperty("specification")
    private String specification = null;

    @JsonProperty("price")
    private Double price = null;

    @JsonProperty("units")
    private String units = null;

    @JsonProperty("employType")
    private String employType = null;

    @JsonProperty("usage")
    private Integer usage = null;

    @JsonProperty("employDate")
    private String employDate = null;

    public LowValueArticlesResource lowValueArticlesId(String lowValueArticlesId) {
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

    public LowValueArticlesResource lowValueArticlesNo(String lowValueArticlesNo) {
        this.lowValueArticlesNo = lowValueArticlesNo;
        return this;
    }

    /**
     * 物品编号
     *
     * @return lowValueArticlesNo
     **/
    @ApiModelProperty(value = "物品编号")


    public String getLowValueArticlesNo() {
        return lowValueArticlesNo;
    }

    public void setLowValueArticlesNo(String lowValueArticlesNo) {
        this.lowValueArticlesNo = lowValueArticlesNo;
    }

    public LowValueArticlesResource lowValueArticlesName(String lowValueArticlesName) {
        this.lowValueArticlesName = lowValueArticlesName;
        return this;
    }

    /**
     * 物品名称
     *
     * @return lowValueArticlesName
     **/
    @ApiModelProperty(value = "物品名称")


    public String getLowValueArticlesName() {
        return lowValueArticlesName;
    }

    public void setLowValueArticlesName(String lowValueArticlesName) {
        this.lowValueArticlesName = lowValueArticlesName;
    }

    public LowValueArticlesResource lowValueArticlesTypeId(String lowValueArticlesTypeId) {
        this.lowValueArticlesTypeId = lowValueArticlesTypeId;
        return this;
    }

    /**
     * 物品分类id
     *
     * @return lowValueArticlesTypeId
     **/
    @ApiModelProperty(value = "物品分类id")


    public String getLowValueArticlesTypeId() {
        return lowValueArticlesTypeId;
    }

    public void setLowValueArticlesTypeId(String lowValueArticlesTypeId) {
        this.lowValueArticlesTypeId = lowValueArticlesTypeId;
    }

    public LowValueArticlesResource lowValueArticlesTypeName(String lowValueArticlesTypeName) {
        this.lowValueArticlesTypeName = lowValueArticlesTypeName;
        return this;
    }

    /**
     * 物品分类名称
     *
     * @return lowValueArticlesTypeName
     **/
    @ApiModelProperty(value = "物品分类名称")


    public String getLowValueArticlesTypeName() {
        return lowValueArticlesTypeName;
    }

    public void setLowValueArticlesTypeName(String lowValueArticlesTypeName) {
        this.lowValueArticlesTypeName = lowValueArticlesTypeName;
    }

    public LowValueArticlesResource specification(String specification) {
        this.specification = specification;
        return this;
    }

    /**
     * 规格
     *
     * @return specification
     **/
    @ApiModelProperty(value = "规格")


    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public LowValueArticlesResource price(Double price) {
        this.price = price;
        return this;
    }

    /**
     * 单位
     *
     * @return price
     **/
    @ApiModelProperty(value = "单位")


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LowValueArticlesResource units(String units) {
        this.units = units;
        return this;
    }

    /**
     * 单位
     *
     * @return units
     **/
    @ApiModelProperty(value = "单位")


    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public LowValueArticlesResource employType(String employType) {
        this.employType = employType;
        return this;
    }

    /**
     * 使用类型
     *
     * @return employType
     **/
    @ApiModelProperty(value = "使用类型")


    public String getEmployType() {
        return employType;
    }

    public void setEmployType(String employType) {
        this.employType = employType;
    }

    public LowValueArticlesResource usage(Integer usage) {
        this.usage = usage;
        return this;
    }

    /**
     * 使用量
     *
     * @return usage
     **/
    @ApiModelProperty(value = "使用量")


    public Integer getUsage() {
        return usage;
    }

    public void setUsage(Integer usage) {
        this.usage = usage;
    }

    public LowValueArticlesResource employDate(String employDate) {
        this.employDate = employDate;
        return this;
    }

    /**
     * 使用时间
     *
     * @return employDate
     **/
    @ApiModelProperty(value = "使用时间")


    public String getEmployDate() {
        return employDate;
    }

    public void setEmployDate(String employDate) {
        this.employDate = employDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LowValueArticlesResource lowValueArticlesResource = (LowValueArticlesResource) o;
        return Objects.equals(this.lowValueArticlesId, lowValueArticlesResource.lowValueArticlesId) &&
                Objects.equals(this.lowValueArticlesNo, lowValueArticlesResource.lowValueArticlesNo) &&
                Objects.equals(this.lowValueArticlesName, lowValueArticlesResource.lowValueArticlesName) &&
                Objects.equals(this.lowValueArticlesTypeId, lowValueArticlesResource.lowValueArticlesTypeId) &&
                Objects.equals(this.lowValueArticlesTypeName, lowValueArticlesResource.lowValueArticlesTypeName) &&
                Objects.equals(this.specification, lowValueArticlesResource.specification) &&
                Objects.equals(this.price, lowValueArticlesResource.price) &&
                Objects.equals(this.units, lowValueArticlesResource.units) &&
                Objects.equals(this.employType, lowValueArticlesResource.employType) &&
                Objects.equals(this.usage, lowValueArticlesResource.usage) &&
                Objects.equals(this.employDate, lowValueArticlesResource.employDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lowValueArticlesId, lowValueArticlesNo, lowValueArticlesName, lowValueArticlesTypeId, lowValueArticlesTypeName,
                specification, price, units, employType, usage, employDate);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class LowValueArticlesResource {\n");

        sb.append("    lowValueArticlesId: ").append(toIndentedString(lowValueArticlesId)).append("\n");
        sb.append("    lowValueArticlesNo: ").append(toIndentedString(lowValueArticlesNo)).append("\n");
        sb.append("    lowValueArticlesName: ").append(toIndentedString(lowValueArticlesName)).append("\n");
        sb.append("    lowValueArticlesTypeId: ").append(toIndentedString(lowValueArticlesTypeId)).append("\n");
        sb.append("    lowValueArticlesTypeName: ").append(toIndentedString(lowValueArticlesTypeName)).append("\n");
        sb.append("    specification: ").append(toIndentedString(specification)).append("\n");
        sb.append("    price: ").append(toIndentedString(price)).append("\n");
        sb.append("    units: ").append(toIndentedString(units)).append("\n");
        sb.append("    employType: ").append(toIndentedString(employType)).append("\n");
        sb.append("    usage: ").append(toIndentedString(usage)).append("\n");
        sb.append("    employDate: ").append(toIndentedString(employDate)).append("\n");
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

