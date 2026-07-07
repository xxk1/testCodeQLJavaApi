package com.lztech.site.viewmodel.kggentask;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * KgAIGenTeachingMaterialNodeResourceItem
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-07-24T15:17:22.858+08:00")

public class KgAIGenTeachingMaterialNodeResourceItem {
    @JsonProperty("kgNodeId")
    private String kgNodeId = null;

    @JsonProperty("kgNodeName")
    private String kgNodeName = null;

    @JsonProperty("level")
    private Integer level = null;

    @JsonProperty("levelName")
    private String levelName = null;

    @JsonProperty("kgNodeImportantTag")
    private Integer kgNodeImportantTag = null;

    @JsonProperty("kgNodeImportantTagName")
    private String kgNodeImportantTagName = null;


    @JsonProperty("kgNodeContent")
    private String kgNodeContent = null;

    @JsonProperty("showOrder")
    private Integer showOrder;

    @JsonProperty("childList")
    @Valid
    private List<KgAIGenTeachingMaterialNodeResourceItem> childList = null;

    public Integer getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getKgNodeImportantTagName() {
        return kgNodeImportantTagName;
    }

    public void setKgNodeImportantTagName(String kgNodeImportantTagName) {
        this.kgNodeImportantTagName = kgNodeImportantTagName;
    }

    public KgAIGenTeachingMaterialNodeResourceItem kgNodeId(String kgNodeId) {
        this.kgNodeId = kgNodeId;
        return this;
    }

    /**
     * 节点id
     *
     * @return kgNodeId
     **/
    @ApiModelProperty(value = "节点id")


    public String getKgNodeId() {
        return kgNodeId;
    }

    public void setKgNodeId(String kgNodeId) {
        this.kgNodeId = kgNodeId;
    }

    public KgAIGenTeachingMaterialNodeResourceItem kgNodeName(String kgNodeName) {
        this.kgNodeName = kgNodeName;
        return this;
    }

    /**
     * 节点名称
     *
     * @return kgNodeName
     **/
    @ApiModelProperty(value = "节点名称")


    public String getKgNodeName() {
        return kgNodeName;
    }

    public void setKgNodeName(String kgNodeName) {
        this.kgNodeName = kgNodeName;
    }

    public KgAIGenTeachingMaterialNodeResourceItem level(Integer level) {
        this.level = level;
        return this;
    }

    /**
     * 节点层级
     *
     * @return level
     **/
    @ApiModelProperty(value = "节点层级")


    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public KgAIGenTeachingMaterialNodeResourceItem kgNodeImportantTag(Integer kgNodeImportantTag) {
        this.kgNodeImportantTag = kgNodeImportantTag;
        return this;
    }

    /**
     * 节点重要标签 0：次要；1：一般；2：重要;3：极其重要
     *
     * @return kgNodeImportantTag
     **/
    @ApiModelProperty(value = "节点重要标签 0：次要；1：一般；2：重要;3：极其重要")


    public Integer getKgNodeImportantTag() {
        return kgNodeImportantTag;
    }

    public void setKgNodeImportantTag(Integer kgNodeImportantTag) {
        this.kgNodeImportantTag = kgNodeImportantTag;
    }

    public KgAIGenTeachingMaterialNodeResourceItem kgNodeContent(String kgNodeContent) {
        this.kgNodeContent = kgNodeContent;
        return this;
    }

    /**
     * 节点内容
     *
     * @return kgNodeContent
     **/
    @ApiModelProperty(value = "节点内容")


    public String getKgNodeContent() {
        return kgNodeContent;
    }

    public void setKgNodeContent(String kgNodeContent) {
        this.kgNodeContent = kgNodeContent;
    }

    public KgAIGenTeachingMaterialNodeResourceItem childList(List<KgAIGenTeachingMaterialNodeResourceItem> childList) {
        this.childList = childList;
        return this;
    }

    public KgAIGenTeachingMaterialNodeResourceItem addChildListItem(KgAIGenTeachingMaterialNodeResourceItem childListItem) {
        if (this.childList == null) {
            this.childList = new ArrayList<KgAIGenTeachingMaterialNodeResourceItem>();
        }
        this.childList.add(childListItem);
        return this;
    }

    /**
     * Get childList
     *
     * @return childList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<KgAIGenTeachingMaterialNodeResourceItem> getChildList() {
        return childList;
    }

    public void setChildList(List<KgAIGenTeachingMaterialNodeResourceItem> childList) {
        this.childList = childList;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        KgAIGenTeachingMaterialNodeResourceItem kgAIGenTeachingMaterialNodeResourceItem = (KgAIGenTeachingMaterialNodeResourceItem) o;
        return Objects.equals(this.kgNodeId, kgAIGenTeachingMaterialNodeResourceItem.kgNodeId) &&
                Objects.equals(this.kgNodeName, kgAIGenTeachingMaterialNodeResourceItem.kgNodeName) &&
                Objects.equals(this.level, kgAIGenTeachingMaterialNodeResourceItem.level) &&
                Objects.equals(this.kgNodeImportantTag, kgAIGenTeachingMaterialNodeResourceItem.kgNodeImportantTag) &&
                Objects.equals(this.kgNodeContent, kgAIGenTeachingMaterialNodeResourceItem.kgNodeContent) &&
                Objects.equals(this.childList, kgAIGenTeachingMaterialNodeResourceItem.childList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kgNodeId, kgNodeName, level, kgNodeImportantTag, kgNodeContent, childList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class KgAIGenTeachingMaterialNodeResourceItem {\n");

        sb.append("    kgNodeId: ").append(toIndentedString(kgNodeId)).append("\n");
        sb.append("    kgNodeName: ").append(toIndentedString(kgNodeName)).append("\n");
        sb.append("    level: ").append(toIndentedString(level)).append("\n");
        sb.append("    kgNodeImportantTag: ").append(toIndentedString(kgNodeImportantTag)).append("\n");
        sb.append("    kgNodeContent: ").append(toIndentedString(kgNodeContent)).append("\n");
        sb.append("    childList: ").append(toIndentedString(childList)).append("\n");
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

