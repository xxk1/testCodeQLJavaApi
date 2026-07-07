package com.lztech.domain.model.knowledgegraph.enums;

public enum GraphNodeResourceStatus {
    NORMAL(0, "正常"),
    DELETE(1, "已删除");

    private Integer index;
    private String value;

    GraphNodeResourceStatus(Integer index, String value) {
        this.index = index;
        this.value = value;
    }

    public static GraphNodeResourceStatus getGraphNodeResourceStatus(int index) {
        for (GraphNodeResourceStatus graphNodeResourceStatus : GraphNodeResourceStatus.values()) {
            if (index == graphNodeResourceStatus.getIndex()) {
                return graphNodeResourceStatus;
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
