package com.lztech.domain.model.project.enums;


import java.util.Arrays;

public enum ProjectGenre {

    INTEGRATEDDESIGN("综合设计型", 0),
    VERIFICATIONDRIVEN("验证型", 1),
    DESIGNRESEARCH("设计研究型", 2),
    BASIS("基础型", 3),
    SKILL_TRAINING_CATEGORY("技能实训类", 4),
    OTHER("其它", 5);

    private String name;
    private Integer value;

    ProjectGenre(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public static ProjectGenre getProjectGenre(int value) {
        return Arrays.stream(ProjectGenre.values())
                .filter(projectGenre -> projectGenre.getValue().equals(value))
                .findFirst().orElse(null);
    }

    public static ProjectGenre getProjectGenre(String name) {
        return Arrays.stream(ProjectGenre.values())
                .filter(projectGenre -> projectGenre.getName().equals(name))
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
