package com.lztech.domain.model.course.enums;

public enum ResourceSourceType {

    USER_ADDED(0, "新增"),

    USER_COPIED(1, "复制");

    private Integer value;

    private String name;

    ResourceSourceType(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
