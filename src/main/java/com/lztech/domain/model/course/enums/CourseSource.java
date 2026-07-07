package com.lztech.domain.model.course.enums;

public enum CourseSource {

    DATA_DOCKING("数据对接", 0),
    COURSE_MANAGEMENT("课程管理", 1);

    private String name;
    private int index;

    CourseSource(String name, int index) {
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

    public static CourseSource getCourseSourceByName(String name) {
        for (CourseSource courseSource : CourseSource.values()) {
            if (name.equals(courseSource.getName())) {
                return courseSource;
            }
        }
        return null;
    }

    public static CourseSource getCourseSourceByIndex(Integer index) {
        for (CourseSource courseSource : CourseSource.values()) {
            if (index.equals(courseSource.getIndex())) {
                return courseSource;
            }
        }
        return null;
    }
}
