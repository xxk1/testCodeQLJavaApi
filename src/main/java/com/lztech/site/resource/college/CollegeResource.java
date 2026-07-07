package com.lztech.site.resource.college;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * CollegeResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-07T02:41:59.747Z")

public class CollegeResource {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    @JsonProperty("collegeCode")
    private String collegeCode = null;

    @JsonProperty("showOrder")
    private Integer showOrder = null;

    public Integer getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
    }

    public CollegeResource id(String id) {
        this.id = id;
        return this;
    }

    /**
     * 学院id
     *
     * @return id
     **/
    @ApiModelProperty(value = "学院id")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CollegeResource collegeName(String collegeName) {
        this.collegeName = collegeName;
        return this;
    }

    /**
     * 学院名称
     *
     * @return collegeName
     **/
    @ApiModelProperty(value = "学院名称")


    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public CollegeResource collegeCode(String collegeCode) {
        this.collegeCode = collegeCode;
        return this;
    }

    /**
     * 学院编号
     *
     * @return collegeCode
     **/
    @ApiModelProperty(value = "学院编号")


    public String getCollegeCode() {
        return collegeCode;
    }

    public void setCollegeCode(String collegeCode) {
        this.collegeCode = collegeCode;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CollegeResource collegeResource = (CollegeResource) o;
        return Objects.equals(this.id, collegeResource.id) &&
                Objects.equals(this.collegeName, collegeResource.collegeName) &&
                Objects.equals(this.collegeCode, collegeResource.collegeCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, collegeName, collegeCode);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CollegeResource {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
        sb.append("    collegeCode: ").append(toIndentedString(collegeCode)).append("\n");
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

