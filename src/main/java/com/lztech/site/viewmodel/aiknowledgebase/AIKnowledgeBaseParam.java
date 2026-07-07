package com.lztech.site.viewmodel.aiknowledgebase;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * AIKnowledgeBaseParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-08-02T08:01:01.136Z")

public class AIKnowledgeBaseParam {

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("userId")
    private String userId = null;
    @JsonProperty("userType")
    private Integer userType = null;
    @JsonProperty("page")
    private Integer page = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    @JsonProperty("searchStr")
    private String searchStr = null;

    @JsonProperty("startDate")
    private String startDate = null;

    @JsonProperty("endDate")
    private String endDate = null;

    @JsonProperty("sourceType")
    private Integer sourceType = null;

    @JsonProperty("aiKnowledgeBaseType")
    private Integer aiKnowledgeBaseType = null;


    @JsonProperty("sortName")
    private String sortName = null;


    @JsonProperty("sortRule")
    private String sortRule = null;

    public AIKnowledgeBaseParam courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    /**
     * 课程id
     *
     * @return courseId
     **/
    @ApiModelProperty(value = "课程id")



    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public AIKnowledgeBaseParam userId(String userId) {
        this.userId = userId;
        return this;
    }

    /**
     * 用户id
     *
     * @return userId
     **/
    @ApiModelProperty(value = "userId")



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public AIKnowledgeBaseParam userType(Integer userType) {
        this.userType = userType;
        return this;
    }

    /**
     * 用户类型（0：老师，1：学生）
     *
     * @return userType
     **/
    @ApiModelProperty(value = "userType")


    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public AIKnowledgeBaseParam page(Integer page) {
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

    public AIKnowledgeBaseParam pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * 每页数量
     *
     * @return pageSize
     **/
    @ApiModelProperty(value = "每页数量")


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    public AIKnowledgeBaseParam searchStr(String searchStr) {
        this.searchStr = searchStr;
        return this;
    }

    /**
     * 查询条件
     *
     * @return searchStr
     **/
    @ApiModelProperty(value = "查询条件")

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }
    public AIKnowledgeBaseParam startDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * 开始日期
     *
     * @return startDate
     **/
    @ApiModelProperty(value = "开始日期")

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public AIKnowledgeBaseParam endDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * 结束日期
     *
     * @return endDate
     **/
    @ApiModelProperty(value = "结束日期")

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public AIKnowledgeBaseParam sourceType(Integer sourceType) {
        this.sourceType = sourceType;
        return this;
    }

    /**
     * 课程来源类型 (null:全部来源;0:个人;1:公共)
     *
     * @return sourceType
     **/
    @ApiModelProperty(value = "课程来源类型 (null:全部来源;0:个人;1:公共)")

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }
    public AIKnowledgeBaseParam aiKnowledgeBaseType(Integer aiKnowledgeBaseType) {
        this.aiKnowledgeBaseType = aiKnowledgeBaseType;
        return this;
    }

    /**
     * AI知识库类型编号(null:全部; 0:课程教材;1:课件资源;2:课堂实录)
     *
     * @return aiKnowledgeBaseType
     **/
    @ApiModelProperty(value = "AI知识库类型编号(null:全部; 0:课程教材;1:课件资源;2:课堂实录)")

    public Integer getAiKnowledgeBaseType() {
        return aiKnowledgeBaseType;
    }

    public void setAiKnowledgeBaseType(Integer aiKnowledgeBaseType) {
        this.aiKnowledgeBaseType = aiKnowledgeBaseType;
    }
    public AIKnowledgeBaseParam sortName(String sortName) {
        this.sortName = sortName;
        return this;
    }

    /**
     * 排序字段，现在有【createTime,resourceName】，多个,拼接
     *
     * @return sortName
     **/
    @ApiModelProperty(value = "排序字段，现在有【createTime,resourceName】，多个,拼接")

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }
    public AIKnowledgeBaseParam sortRule(String sortRule) {
        this.sortRule = sortRule;
        return this;
    }

    /**
     * 排序规则 asc升序，desc降序
     *
     * @return sortRule
     **/
    @ApiModelProperty(value = "排序规则 asc升序，desc降序")

    public String getSortRule() {
        return sortRule;
    }

    public void setSortRule(String sortRule) {
        this.sortRule = sortRule;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AIKnowledgeBaseParam)) {
            return false;
        }
        AIKnowledgeBaseParam that = (AIKnowledgeBaseParam) o;
        return Objects.equals(page, that.page) &&
                Objects.equals(pageSize, that.pageSize) &&
                Objects.equals(userType, that.userType) &&
                Objects.equals(searchStr, that.searchStr) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(sourceType, that.sourceType) &&
                Objects.equals(aiKnowledgeBaseType, that.aiKnowledgeBaseType) &&
                Objects.equals(sortName, that.sortName) &&
                Objects.equals(sortRule, that.sortRule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, pageSize, searchStr, startDate, endDate,userType,
                sourceType, aiKnowledgeBaseType, sortName, sortRule);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AIKnowledgeBaseParam{");
        sb.append("page=").append(page);
        sb.append(", userType=").append(userType);
        sb.append(", pageSize=").append(pageSize);
        sb.append(", searchStr='").append(searchStr).append('\'');
        sb.append(", startDate='").append(startDate).append('\'');
        sb.append(", endDate='").append(endDate).append('\'');
        sb.append(", sourceType=").append(sourceType);
        sb.append(", aiKnowledgeBaseType=").append(aiKnowledgeBaseType);
        sb.append(", sortName='").append(sortName).append('\'');
        sb.append(", sortRule='").append(sortRule).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
