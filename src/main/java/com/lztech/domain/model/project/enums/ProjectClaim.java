package com.lztech.domain.model.project.enums;


import java.util.Arrays;

public enum ProjectClaim {
    OBLIGATORY("必修", 0),
    ELECTIVE("选修", 1);

    private String name;
    private Integer value;

    ProjectClaim(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public static ProjectClaim getProjectClaim(int value) {
        return Arrays.stream(ProjectClaim.values())
                .filter(projectClaim -> projectClaim.getValue().equals(value))
                .findFirst().orElse(null);
    }

    public static ProjectClaim getProjectClaim(String name) {
        return Arrays.stream(ProjectClaim.values())
                .filter(projectClaim -> projectClaim.getName().equals(name))
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
