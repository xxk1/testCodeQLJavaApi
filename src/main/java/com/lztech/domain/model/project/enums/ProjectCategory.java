package com.lztech.domain.model.project.enums;


import java.util.Arrays;

public enum ProjectCategory {

    PROFESSIONAL("专业", 0),
    BASIS("基础", 1);

    private String name;
    private Integer value;

    ProjectCategory(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public static ProjectCategory getProjectCategory(int value) {
        return Arrays.stream(ProjectCategory.values())
                .filter(projectCategory -> projectCategory.getValue().equals(value))
                .findFirst().orElse(null);
    }

    public static ProjectCategory getProjectCategory(String name) {
        return Arrays.stream(ProjectCategory.values())
                .filter(projectCategory -> projectCategory.getName().equals(name))
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
