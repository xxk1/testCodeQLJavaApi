package com.lztech.domain.model.projectsuite.enums;

public enum ProjectSuiteStatus {
    REMOVE("删除", 0),
    NORMAL("正常", 1);

    private String name;
    private int index;


    ProjectSuiteStatus(String name, int index) {
        this.name = name;
        this.index = index;
    }


    public static ProjectSuiteStatus getProjectSuiteStatus(int sourceIndex) {
        for (ProjectSuiteStatus status : ProjectSuiteStatus.values()) {
            if (sourceIndex == status.getIndex()) {
                return status;
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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
