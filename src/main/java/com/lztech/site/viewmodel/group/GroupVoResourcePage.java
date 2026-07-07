package com.lztech.site.viewmodel.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * GroupVoResourcePage
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-10-21T06:57:40.079Z")


public class GroupVoResourcePage {
    @JsonProperty("total")
    private Integer total = null;

    @JsonProperty("page")
    private Integer page = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    @JsonProperty("GroupVoResourceList")
    @Valid
    private List<GroupVoResource> groupVoResourceList = null;

    public GroupVoResourcePage total(Integer total) {
        this.total = total;
        return this;
    }

    /**
     * 总个数
     *
     * @return total
     **/
    @ApiModelProperty(value = "总个数")


    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public GroupVoResourcePage page(Integer page) {
        this.page = page;
        return this;
    }

    /**
     * 当前页
     *
     * @return page
     **/
    @ApiModelProperty(value = "当前页")


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public GroupVoResourcePage pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * 每页个数
     *
     * @return pageSize
     **/
    @ApiModelProperty(value = "每页个数")


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public GroupVoResourcePage groupVoResourceList(List<GroupVoResource> groupVoResourceList) {
        this.groupVoResourceList = groupVoResourceList;
        return this;
    }

    public GroupVoResourcePage addGroupVoResourceListItem(GroupVoResource groupVoResourceListItem) {
        if (this.groupVoResourceList == null) {
            this.groupVoResourceList = new ArrayList<GroupVoResource>();
        }
        this.groupVoResourceList.add(groupVoResourceListItem);
        return this;
    }

    /**
     * 当前页列表数据
     *
     * @return groupVoResourceList
     **/
    @ApiModelProperty(value = "当前页列表数据")

    @Valid

    public List<GroupVoResource> getGroupVoResourceList() {
        return groupVoResourceList;
    }

    public void setGroupVoResourceList(List<GroupVoResource> groupVoResourceList) {
        this.groupVoResourceList = groupVoResourceList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GroupVoResourcePage groupVoResourcePage = (GroupVoResourcePage) o;
        return Objects.equals(this.total, groupVoResourcePage.total) &&
                Objects.equals(this.page, groupVoResourcePage.page) &&
                Objects.equals(this.pageSize, groupVoResourcePage.pageSize) &&
                Objects.equals(this.groupVoResourceList, groupVoResourcePage.groupVoResourceList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, page, pageSize, groupVoResourceList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GroupVoResourcePage {\n");

        sb.append("    total: ").append(toIndentedString(total)).append("\n");
        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    groupVoResourceList: ").append(toIndentedString(groupVoResourceList)).append("\n");
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

