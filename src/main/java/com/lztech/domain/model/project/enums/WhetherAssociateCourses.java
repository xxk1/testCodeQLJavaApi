package com.lztech.domain.model.project.enums;

import java.util.Arrays;

public enum WhetherAssociateCourses {

    NO("否", 0),
    YES("是", 1);
    public String name;
    private Integer value;

    WhetherAssociateCourses(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public static WhetherAssociateCourses getWhetherAssociateCourses(int value) {
        return Arrays.stream(WhetherAssociateCourses.values())
                .filter(whetherAssociateCourses -> whetherAssociateCourses.getValue().equals(value))
                .findFirst().orElse(null);
    }

    public static WhetherAssociateCourses getWhetherAssociateCourses(String name) {
        return Arrays.stream(WhetherAssociateCourses.values())
                .filter(whetherAssociateCourses -> whetherAssociateCourses.getName().equals(name))
                .findFirst().orElse(null);
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
