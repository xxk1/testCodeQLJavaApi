package com.lztech.domain.model.group.enums;

public enum GroupStatus {

    NORMAL("正常", 0),
    REMOVE("删除", 1);

    private String name;
    private int index;


    GroupStatus(String name, int index) {
        this.name = name;
        this.index = index;
    }


    public static GroupStatus getGroupStatus(int sourceIndex) {
        for (GroupStatus status : GroupStatus.values()) {
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
