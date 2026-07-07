package com.lztech.domain.model.course.enums;

public enum CourseExpansionEnum {

    THEORY_COURSE("theoryCourse", "理论课"),
    EXPERIMENTAL_COURSE("experimentalCourse", "实验课"),
    PHYSICAL_COURSE("physicalCourse", "体育课"),
    BILINGUAL_COURSE("bilingualCourse", "双语课"),
    INTERNSHIP_COURSE("internshipCourse", "见习课"),
    SPECIALIZED_COURSE("specializedCourse", "术科课"),
    BASIC_COURSE("basicCourse", "基础课"),
    POLITICAL_COURSE("politicalCourse", "思政课"),
    UNKNOWN_COURSE("unknownCourse", "未知课程");

    private String key;

    private String value;

    CourseExpansionEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static CourseExpansionEnum getCourseExpansionEnumByKey(String key) {
        for (CourseExpansionEnum courseExpansionEnum : CourseExpansionEnum.values()) {
            if (key.equals(courseExpansionEnum.getKey())) {
                return courseExpansionEnum;
            }
        }
        return CourseExpansionEnum.UNKNOWN_COURSE;
    }
}
