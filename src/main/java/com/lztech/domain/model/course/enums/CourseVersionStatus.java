package com.lztech.domain.model.course.enums;

public enum CourseVersionStatus {

    ARCHIVED(0, "已归档"),
    IN_USE(1, "使用中"),
    DELETE(2,"删除");

    private int value;

    private String name;

    CourseVersionStatus(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
