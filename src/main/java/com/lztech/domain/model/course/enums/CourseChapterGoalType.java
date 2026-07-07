package com.lztech.domain.model.course.enums;

import org.apache.commons.lang3.ObjectUtils;

public enum CourseChapterGoalType {

    TEACHING_GOAL_SUMMARY(0, "概述", "教学目标", 0, false),
    TEACHING_GOAL_KNOWLEDGE(1, "知识", "教学目标", 1, true),
    TEACHING_GOAL_ABILITY(2, "能力", "教学目标", 2, true),
    TEACHING_GOAL_POLITICAL_GOAL(3, "思政目标", "思政目标", 3, false),
    ASSESSMENT_METHOD_SUMMARY(4, "概述", "考核办法", 4, false);

    private int value;
    private String name;
    private String parentName;
    private int showOrder;
    private boolean isShow;

    CourseChapterGoalType(int value, String name, String parentName, int showOrder, boolean isShow) {
        this.value = value;
        this.name = name;
        this.parentName = parentName;
        this.showOrder = showOrder;
        this.isShow = isShow;
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

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public int getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(int showOrder) {
        this.showOrder = showOrder;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public static CourseChapterGoalType getCourseChapterGoalType(Integer value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (CourseChapterGoalType courseChapterGoalType : CourseChapterGoalType.values()) {
            if (value == courseChapterGoalType.getValue()){
                return courseChapterGoalType;
            }
        }
        return null;
    }
}
