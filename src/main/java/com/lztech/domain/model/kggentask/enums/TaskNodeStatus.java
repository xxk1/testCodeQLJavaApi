package com.lztech.domain.model.kggentask.enums;

public enum TaskNodeStatus {
    WAITING(0, "待生成"),
    GENERATING(1, "生成中"),
    SUCCESS(2, "生成成功"),
    FAIL(3, "生成失败"),
    NO_NEED_GENERATION(4,"不需要生成");

    private Integer index;

    private String name;

    public Integer getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    TaskNodeStatus(Integer index, String name) {
        this.index = index;
        this.name = name;
    }

    public static TaskNodeStatus getByIndex(Integer index) {
        for (TaskNodeStatus value : values()) {
            if (value.index.equals(index)) {
                return value;
            }
        }
        return null;
    }

}
