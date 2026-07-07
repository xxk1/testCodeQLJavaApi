package com.lztech.domain.model.coursetabledetailteacher.enums;

public enum StudentStatus {


    REMOVE("删除", 0),
    NORMAL("正常", 1);

    private String name;
    private int index;


    StudentStatus(String name, int index) {
        this.name = name;
        this.index = index;
    }


    public static StudentStatus getStudentStatus(int sourceIndex) {
        for (StudentStatus status : StudentStatus.values()) {
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
