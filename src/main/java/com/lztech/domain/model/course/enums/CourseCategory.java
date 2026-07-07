package com.lztech.domain.model.course.enums;

/**
 * 课程类别
 */
public enum CourseCategory {

    COMPULSORY("必修", 0),
    ELECTIVE("选修", 1),
    PUBLIC_ELECTIVE("公选", 2),
    SELECTING("指选", 3),
    INTERNATIONAL("国际课", 4),
    DOUBLE_DEGREE("双学位", 5),
    OTHER("其他", 6);

    private String name;
    private int index;

    CourseCategory(String name, int index) {
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

    public static CourseCategory getCourseTypeByName(String name) {
        for (CourseCategory courseCategory : CourseCategory.values()) {
            if (name.equals(courseCategory.getName())) {
                return courseCategory;
            }
        }
        return OTHER;
    }

}
