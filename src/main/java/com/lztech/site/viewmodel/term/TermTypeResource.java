package com.lztech.site.viewmodel.term;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * TermTypeResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-11-18T06:33:26.663Z")


public class TermTypeResource {
    @JsonProperty("termTypeId")
    private Integer termTypeId = null;

    @JsonProperty("termtypeName")
    private String termtypeName = null;

    public TermTypeResource termTypeId(Integer termTypeId) {
        this.termTypeId = termTypeId;
        return this;
    }

    /**
     * 学期类型id
     *
     * @return termTypeId
     **/
    @ApiModelProperty(value = "学期类型id")


    public Integer getTermTypeId() {
        return termTypeId;
    }

    public void setTermTypeId(Integer termTypeId) {
        this.termTypeId = termTypeId;
    }

    public TermTypeResource termtypeName(String termtypeName) {
        this.termtypeName = termtypeName;
        return this;
    }

    /**
     * 学期类型名称
     *
     * @return termtypeName
     **/
    @ApiModelProperty(value = "学期类型名称")


    public String getTermtypeName() {
        return termtypeName;
    }

    public void setTermtypeName(String termtypeName) {
        this.termtypeName = termtypeName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TermTypeResource termTypeResource = (TermTypeResource) o;
        return Objects.equals(this.termTypeId, termTypeResource.termTypeId) &&
                Objects.equals(this.termtypeName, termTypeResource.termtypeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(termTypeId, termtypeName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TermTypeResource {\n");

        sb.append("    termTypeId: ").append(toIndentedString(termTypeId)).append("\n");
        sb.append("    termtypeName: ").append(toIndentedString(termtypeName)).append("\n");
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

