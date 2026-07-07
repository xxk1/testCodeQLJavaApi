package com.lztech.domain.model.classgroupingscheme.enums;

public enum SchemeType {

    GENERAL_GROUP_SCHEME(0, "普通小组方案"),
    EXPERIMENTAL_GROUP_SCHEME(1, "实验小组方案");

    private int value;
    private String name;

    SchemeType(int value, String name) {
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

    public static SchemeType getSchemeType(int value) {
        for (SchemeType schemeType : SchemeType.values()) {
            if (value == schemeType.getValue()) {
                return schemeType;
            }
        }
        return null;
    }
}
