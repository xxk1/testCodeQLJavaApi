package com.lztech.domain.model.course.enums;

public enum CourseContentTypeEnum {

    COURSE_BASE_INFO(0, "基本信息", 0, "课程简介", 10.0),
    COURSE_INTRODUCTION(1, "课程介绍", 1, "课程简介", 10.0),
    COURSE_TEAM_LEADER(2, "课程负责人", 2, "课程团队", 10.0),
    COURSE_TEAM_USER(3, "教学团队", 3, "课程团队", 10.0),
    KNOWLEDGE_STRUCTURE(4, "知识图谱", 4, "知识图谱", 10.0),
    COURSE_STRUCTURE(5, "课程结构", 5, "教学内容", 10.0),
    TEACHING_RESOURCES(6, "教学资源", 5, "教学内容", 10.0),
    PREPARATORY_ACTIVITIES(7, "准备活动", 7, "教学内容", 10.0),
    COURSE_OBJECTIVES(8, "课程目标", 8, "教学内容", 10.0),
    ASSESSMENT_SCHEME(9, "考核方案", 9, "考核方案", 10.0);

    private int value;

    private String name;

    private int showOrder;

    private String parentName;

    private double completionPercent;

    CourseContentTypeEnum(int value, String name, int showOrder, String parentName, double completionPercent) {
        this.value = value;
        this.name = name;
        this.showOrder = showOrder;
        this.parentName = parentName;
        this.completionPercent = completionPercent;
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

    public int getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(int showOrder) {
        this.showOrder = showOrder;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public double getCompletionPercent() {
        return completionPercent;
    }

    public void setCompletionPercent(double completionPercent) {
        this.completionPercent = completionPercent;
    }
}
