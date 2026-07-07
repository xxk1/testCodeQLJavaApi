package com.lztech.domain.model.projectcard.enums;


public enum ProjectCardType {
    REMOVE("删除", 0),
    NORMAL("正常", 1);

    private String name;
    private int index;


    ProjectCardType(String name, int index) {
        this.name = name;
        this.index = index;
    }


    public static ProjectCardType getProjectCardType(int sourceIndex) {
        for (ProjectCardType status : ProjectCardType.values()) {
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
