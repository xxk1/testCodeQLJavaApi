package com.lztech.domain.model.projectattribute.enums;

public enum ProjectAttributeKind {
    LABORATORY(0,"实验室"),
    TEST_POSITION(1,"实验位"),
    CURRICULUM(2,"课程")       ;

    private String name;

    private Integer value;

    ProjectAttributeKind(Integer value, String name) {
        this.name = name;
        this.value = value;
    }

    public static ProjectAttributeKind getProjectAttributeKind(Integer value) {
        for (ProjectAttributeKind projectAttributeKind : ProjectAttributeKind.values()) {
            if (projectAttributeKind.getValue() == value) {
                return projectAttributeKind;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int index) {
        this.value = index;
    }
}
