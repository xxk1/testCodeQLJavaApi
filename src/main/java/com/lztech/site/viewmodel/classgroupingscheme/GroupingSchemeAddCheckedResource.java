package com.lztech.site.viewmodel.classgroupingscheme;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * GroupingSchemeAddCheckedResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-02-14T05:51:39.513Z")


public class GroupingSchemeAddCheckedResource {
    @JsonProperty("checkedStatus")
    private Integer checkedStatus = null;

    public GroupingSchemeAddCheckedResource checkedStatus(Integer checkedStatus) {
        this.checkedStatus = checkedStatus;
        return this;
    }

    /**
     * 0:允许添加实验小组；1：允许添加普通小组；2：都不允许添加
     *
     * @return checkedStatus
     **/
    @ApiModelProperty(value = "0:允许添加实验小组；1：允许添加普通小组；2：都不允许添加")


    public Integer getCheckedStatus() {
        return checkedStatus;
    }

    public void setCheckedStatus(Integer checkedStatus) {
        this.checkedStatus = checkedStatus;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GroupingSchemeAddCheckedResource groupingSchemeAddCheckedResource = (GroupingSchemeAddCheckedResource) o;
        return Objects.equals(this.checkedStatus, groupingSchemeAddCheckedResource.checkedStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(checkedStatus);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GroupingSchemeAddCheckedResource {\n");

        sb.append("    checkedStatus: ").append(toIndentedString(checkedStatus)).append("\n");
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

