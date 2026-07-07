package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * LessonPreviewPage
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-09-18T11:32:24.607Z")


public class LessonPreviewPage {
    @JsonProperty("page")
    private Integer page = null;

    @JsonProperty("pageCount")
    private Integer pageCount = null;

    @JsonProperty("totalCount")
    private Integer totalCount = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    @JsonProperty("allCourseTable")
    private Boolean allCourseTable = null;

    @JsonProperty("authorizationScopeType")
    private Integer authorizationScopeType = null;

    @JsonProperty("LessonPreviewList")
    @Valid
    private List<LessonPreview> lessonPreviewList = null;

    public LessonPreviewPage page(Integer page) {
        this.page = page;
        return this;
    }

    /**
     * 当前页码
     *
     * @return page
     **/
    @ApiModelProperty(value = "当前页码")


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public LessonPreviewPage pageCount(Integer pageCount) {
        this.pageCount = pageCount;
        return this;
    }

    /**
     * 总页数
     *
     * @return pageCount
     **/
    @ApiModelProperty(value = "总页数")


    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public LessonPreviewPage totalCount(Integer totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    /**
     * 总条数
     *
     * @return totalCount
     **/
    @ApiModelProperty(value = "总条数")


    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public LessonPreviewPage pageSize(Integer pageSize) {
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

    public LessonPreviewPage allCourseTable(Boolean allCourseTable) {
        this.allCourseTable = allCourseTable;
        return this;
    }

    /**
     * 是否授权全部课表巡课 true：授权全部课表巡课，false：未授权全部课表巡课
     *
     * @return allCourseTable
     **/
    @ApiModelProperty(value = "是否授权全部课表巡课 true：授权全部课表巡课，false：未授权全部课表巡课")


    public Boolean isAllCourseTable() {
        return allCourseTable;
    }

    public void setAllCourseTable(Boolean allCourseTable) {
        this.allCourseTable = allCourseTable;
    }

    public LessonPreviewPage authorizationScopeType(Integer authorizationScopeType) {
        this.authorizationScopeType = authorizationScopeType;
        return this;
    }

    /**
     * 是否授权全部课程范围,0：授权全部课程范围，1：授权直播课程范围，2：授权点播课程范围
     *
     * @return authorizationScopeType
     **/
    @ApiModelProperty(value = "是否授权全部课程范围,0：授权全部课程范围，1：授权直播课程范围，2：授权点播课程范围")


    public Integer getAuthorizationScopeType() {
        return authorizationScopeType;
    }

    public void setAuthorizationScopeType(Integer authorizationScopeType) {
        this.authorizationScopeType = authorizationScopeType;
    }

    public LessonPreviewPage lessonPreviewList(List<LessonPreview> lessonPreviewList) {
        this.lessonPreviewList = lessonPreviewList;
        return this;
    }

    public LessonPreviewPage addLessonPreviewListItem(LessonPreview lessonPreviewListItem) {
        if (this.lessonPreviewList == null) {
            this.lessonPreviewList = new ArrayList<LessonPreview>();
        }
        this.lessonPreviewList.add(lessonPreviewListItem);
        return this;
    }

    /**
     * 正在直播的课表信息集合
     *
     * @return lessonPreviewList
     **/
    @ApiModelProperty(value = "正在直播的课表信息集合")

    @Valid

    public List<LessonPreview> getLessonPreviewList() {
        return lessonPreviewList;
    }

    public void setLessonPreviewList(List<LessonPreview> lessonPreviewList) {
        this.lessonPreviewList = lessonPreviewList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LessonPreviewPage lessonPreviewPage = (LessonPreviewPage) o;
        return Objects.equals(this.page, lessonPreviewPage.page) &&
                Objects.equals(this.pageCount, lessonPreviewPage.pageCount) &&
                Objects.equals(this.totalCount, lessonPreviewPage.totalCount) &&
                Objects.equals(this.pageSize, lessonPreviewPage.pageSize) &&
                Objects.equals(this.allCourseTable, lessonPreviewPage.allCourseTable) &&
                Objects.equals(this.authorizationScopeType, lessonPreviewPage.authorizationScopeType) &&
                Objects.equals(this.lessonPreviewList, lessonPreviewPage.lessonPreviewList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, pageCount, totalCount, pageSize, allCourseTable, authorizationScopeType, lessonPreviewList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class LessonPreviewPage {\n");

        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    pageCount: ").append(toIndentedString(pageCount)).append("\n");
        sb.append("    totalCount: ").append(toIndentedString(totalCount)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    allCourseTable: ").append(toIndentedString(allCourseTable)).append("\n");
        sb.append("    authorizationScopeType: ").append(toIndentedString(authorizationScopeType)).append("\n");
        sb.append("    lessonPreviewList: ").append(toIndentedString(lessonPreviewList)).append("\n");
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

