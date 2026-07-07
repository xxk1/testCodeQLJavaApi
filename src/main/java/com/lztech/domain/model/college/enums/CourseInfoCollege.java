package com.lztech.domain.model.college.enums;


public enum CourseInfoCollege {
    FALSE("否", 0), TRUE("是", 1);

    private String name;
    private int index;

    CourseInfoCollege(String name, int index) {
        this.name = name;
        this.index = index;
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
