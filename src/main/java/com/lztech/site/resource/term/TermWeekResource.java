package com.lztech.site.resource.term;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * TermWeekResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-16T12:04:49.250Z")

public class TermWeekResource {
    @JsonProperty("nameList")
    @Valid
    private List<List<String>> nameList = null;

    @JsonProperty("indexList")
    @Valid
    private List<List<Integer>> indexList = null;

    public TermWeekResource nameList(List<List<String>> nameList) {
        this.nameList = nameList;
        return this;
    }


    public TermWeekResource addNameListItem(List<String> nameListItem) {
        if (this.nameList == null) {
            this.nameList = new ArrayList<List<String>>();
        }
        this.nameList.add(nameListItem);
        return this;
    }

    /**
     * Get nameList
     *
     * @return nameList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<List<String>> getNameList() {
        return nameList;
    }

    public void setNameList(List<List<String>> nameList) {
        this.nameList = nameList;
    }

    public TermWeekResource indexList(List<List<Integer>> indexList) {
        this.indexList = indexList;
        return this;
    }

    public TermWeekResource addIndexListItem(List<Integer> indexListItem) {
        if (this.indexList == null) {
            this.indexList = new ArrayList<List<Integer>>();
        }
        this.indexList.add(indexListItem);
        return this;
    }

    /**
     * Get indexList
     *
     * @return indexList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<List<Integer>> getIndexList() {
        return indexList;
    }

    public void setIndexList(List<List<Integer>> indexList) {
        this.indexList = indexList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TermWeekResource termWeekResource = (TermWeekResource) o;
        return Objects.equals(this.nameList, termWeekResource.nameList) &&
                Objects.equals(this.indexList, termWeekResource.indexList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameList, indexList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TermWeekResource {\n");

        sb.append("    nameList: ").append(toIndentedString(nameList)).append("\n");
        sb.append("    indexList: ").append(toIndentedString(indexList)).append("\n");
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

