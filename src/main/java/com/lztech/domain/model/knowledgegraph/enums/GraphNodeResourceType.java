package com.lztech.domain.model.knowledgegraph.enums;

public enum GraphNodeResourceType {
    FILE(0, "文件");
    private Integer index;
    private String value;

    GraphNodeResourceType(Integer index, String value) {
        this.index = index;
        this.value = value;
    }

    public static GraphNodeResourceType getGraphNodeResourceType(int index) {
        for (GraphNodeResourceType graphNodeResourceType : GraphNodeResourceType.values()) {
            if (index == graphNodeResourceType.getIndex()) {
                return graphNodeResourceType;
            }
        }
        return null;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
