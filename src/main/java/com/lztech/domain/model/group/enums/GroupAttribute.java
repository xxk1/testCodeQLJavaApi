package com.lztech.domain.model.group.enums;

import cn.hutool.core.util.StrUtil;

import java.util.Objects;

public enum GroupAttribute {

    THEORY(0, "理论班"),
    PRACTICE(1, "实践班"),
    THEORY_AND_PRACTICE(2, "理实混合班");

    private int value;

    private String name;

    GroupAttribute(int value, String name) {
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

    public static GroupAttribute getGroupAttribute(Integer sourceIndex) {
        if (Objects.isNull(sourceIndex)){
            return GroupAttribute.THEORY;
        }
        for (GroupAttribute groupAttribute : GroupAttribute.values()) {
            if (sourceIndex == groupAttribute.getValue()) {
                return groupAttribute;
            }
        }
        return GroupAttribute.THEORY;
    }

    public static GroupAttribute getGroupAttributeName(String name) {
        if (StrUtil.isBlank(name)){
            return GroupAttribute.THEORY;
        }
        for (GroupAttribute groupAttribute : GroupAttribute.values()) {
            if (name.equals(groupAttribute.getName())) {
                return groupAttribute;
            }
        }
        return GroupAttribute.THEORY;
    }
}
