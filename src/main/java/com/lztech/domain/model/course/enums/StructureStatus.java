package com.lztech.domain.model.course.enums;

public enum StructureStatus {
    NORMAL(0, "正常"),
    DELETE(1, "已删除");

    private Integer index;
    private String value;

    StructureStatus(Integer index, String value) {
        this.index = index;
        this.value = value;
    }

    public static StructureStatus getStructureStatus(int index) {
        for (StructureStatus structureStatus : StructureStatus.values()) {
            if (index == structureStatus.getIndex()) {
                return structureStatus;
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
