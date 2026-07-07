package com.lztech.domain.model.course.enums;

public enum TeacherType {

    TEACHER("老师", 0),
    COURSE_LEADER("课程负责人", 1);

    private String name;
    private int index;

    TeacherType(String name, int index) {
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

    public static TeacherType getTeacherTypeByName(String name) {
        for (TeacherType teacherType : TeacherType.values()) {
            if (name.equals(teacherType.getName())) {
                return teacherType;
            }
        }
        return null;
    }
}
