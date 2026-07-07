package com.lztech.domain.model.project.enums;


import java.util.Arrays;

public enum ProjectType {

    THEORY("理论项目",0),
    EXPERIMENT("实验项目",1);

    public String name;
    private Integer value;

    ProjectType(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public static ProjectType getProjectType(int value) {
        return Arrays.stream(ProjectType.values())
                .filter(projectType -> projectType.getValue().equals(value))
                .findFirst().orElse(null);
    }
    public static ProjectType getProjectType(String name) {
        return Arrays.stream(ProjectType.values())
                .filter(projectType -> projectType.getName().equals(name))
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
