package com.lztech.domain.model.course.enums;

public enum ResourceStatus {
    NORMAL(0, "正常"),
    DELETE(1, "已删除");

    private Integer index;
    private String value;

    ResourceStatus(Integer index, String value) {
        this.index = index;
        this.value = value;
    }

    public static ResourceStatus getResourceStatus(int index) {
        for (ResourceStatus resourceStatus : ResourceStatus.values()) {
            if (index == resourceStatus.getIndex()) {
                return resourceStatus;
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
