package com.lztech.domain.model.course.enums;

public enum TeacherDataSource {

    CAMPUS_USER(0, "校内用户"), OFF_CAMPUS_USER(1, "校外用户");

    private int value;

    private String name;

    TeacherDataSource(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public static TeacherDataSource getTeacherDataSource(Integer index) {
        if (index == null) {
            return null;
        }
        for (TeacherDataSource teacherDataSource : TeacherDataSource.values()) {
            if (index.equals(teacherDataSource.getValue())) {
                return teacherDataSource;
            }
        }
        return null;
    }
}
