package com.lztech.site.viewmodel.coursetheme;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseThemeResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-04-07T03:01:30.819Z")


public class CourseThemeResource {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("source")
    private Integer source = null;

    @JsonProperty("operatorId")
    private String operatorId = null;

    @JsonProperty("operatorName")
    private String operatorName = null;

    @JsonProperty("whetherPublic")
    private Boolean whetherPublic = null;

    @JsonProperty("courseStructureId")
    private String courseStructureId = null;

    @JsonProperty("themeName")
    private String themeName = null;

    @JsonProperty("themeDetailList")
    @Valid
    private List<CourseThemeDetailResource> themeDetailList = null;

    public CourseThemeResource id(String id) {
        this.id = id;
        return this;
    }

    /**
     * 主题id，获取详情以及修改主题时使用
     *
     * @return id
     **/
    @ApiModelProperty(value = "主题id，获取详情以及修改主题时使用")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CourseThemeResource source(Integer source) {
        this.source = source;
        return this;
    }

    /**
     * 来源：0:教师备课，1:教学内容
     *
     * @return source
     **/
    @ApiModelProperty(value = "来源：0:教师备课，1:教学内容")


    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public CourseThemeResource operatorId(String operatorId) {
        this.operatorId = operatorId;
        return this;
    }

    /**
     * 操作人id
     *
     * @return operatorId
     **/
    @ApiModelProperty(value = "操作人id")


    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public CourseThemeResource operatorName(String operatorName) {
        this.operatorName = operatorName;
        return this;
    }

    /**
     * 操作人姓名
     *
     * @return operatorName
     **/
    @ApiModelProperty(value = "操作人姓名")


    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public CourseThemeResource whetherPublic(Boolean whetherPublic) {
        this.whetherPublic = whetherPublic;
        return this;
    }

    /**
     * 是否公开
     *
     * @return whetherPublic
     **/
    @ApiModelProperty(value = "是否公开")


    public Boolean isWhetherPublic() {
        return whetherPublic;
    }

    public void setWhetherPublic(Boolean whetherPublic) {
        this.whetherPublic = whetherPublic;
    }

    public CourseThemeResource courseStructureId(String courseStructureId) {
        this.courseStructureId = courseStructureId;
        return this;
    }

    /**
     * 章节id
     *
     * @return courseStructureId
     **/
    @ApiModelProperty(value = "章节id")


    public String getCourseStructureId() {
        return courseStructureId;
    }

    public void setCourseStructureId(String courseStructureId) {
        this.courseStructureId = courseStructureId;
    }

    public CourseThemeResource themeName(String themeName) {
        this.themeName = themeName;
        return this;
    }

    /**
     * 主题名称
     *
     * @return themeName
     **/
    @ApiModelProperty(value = "主题名称")


    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public CourseThemeResource themeDetailList(List<CourseThemeDetailResource> themeDetailList) {
        this.themeDetailList = themeDetailList;
        return this;
    }

    public CourseThemeResource addThemeDetailListItem(CourseThemeDetailResource themeDetailListItem) {
        if (this.themeDetailList == null) {
            this.themeDetailList = new ArrayList<CourseThemeDetailResource>();
        }
        this.themeDetailList.add(themeDetailListItem);
        return this;
    }

    /**
     * Get themeDetailList
     *
     * @return themeDetailList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<CourseThemeDetailResource> getThemeDetailList() {
        return themeDetailList;
    }

    public void setThemeDetailList(List<CourseThemeDetailResource> themeDetailList) {
        this.themeDetailList = themeDetailList;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseThemeResource courseThemeResource = (CourseThemeResource) o;
        return Objects.equals(this.id, courseThemeResource.id) &&
                Objects.equals(this.source, courseThemeResource.source) &&
                Objects.equals(this.operatorId, courseThemeResource.operatorId) &&
                Objects.equals(this.operatorName, courseThemeResource.operatorName) &&
                Objects.equals(this.whetherPublic, courseThemeResource.whetherPublic) &&
                Objects.equals(this.courseStructureId, courseThemeResource.courseStructureId) &&
                Objects.equals(this.themeName, courseThemeResource.themeName) &&
                Objects.equals(this.themeDetailList, courseThemeResource.themeDetailList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, source, operatorId, operatorName, whetherPublic, courseStructureId, themeName, themeDetailList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseThemeResource {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    source: ").append(toIndentedString(source)).append("\n");
        sb.append("    operatorId: ").append(toIndentedString(operatorId)).append("\n");
        sb.append("    operatorName: ").append(toIndentedString(operatorName)).append("\n");
        sb.append("    whetherPublic: ").append(toIndentedString(whetherPublic)).append("\n");
        sb.append("    courseStructureId: ").append(toIndentedString(courseStructureId)).append("\n");
        sb.append("    themeName: ").append(toIndentedString(themeName)).append("\n");
        sb.append("    themeDetailList: ").append(toIndentedString(themeDetailList)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

