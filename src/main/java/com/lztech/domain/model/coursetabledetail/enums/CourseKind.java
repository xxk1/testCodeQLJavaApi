package com.lztech.domain.model.coursetabledetail.enums;

public enum CourseKind {

    THEORY_COURSE(0, "理论课"),
    EXPERIMENTAL_COURSE(1, "实验课");

    private Integer index;
    private String value;

    CourseKind(Integer index, String value) {
        this.index = index;
        this.value = value;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
