package com.lztech.domain.model.groupmember.enums;

public enum StudentIdentity {

    STUDENT("学生", 0),
    ASSISTANT("助教", 1);

    private String name;
    private int index;


    StudentIdentity(String name, int index) {
        this.name = name;
        this.index = index;
    }


    public static StudentIdentity getStudentIdentity(int sourceIndex) {
        for (StudentIdentity status : StudentIdentity.values()) {
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
