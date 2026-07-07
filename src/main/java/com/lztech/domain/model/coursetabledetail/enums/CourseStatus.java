package com.lztech.domain.model.coursetabledetail.enums;

public enum CourseStatus {


    HAVE_CLASS("已上课", 0),
    NOT_CLASS("未上课", 1);
    private String courseStatusName;
    private int courseStatusIndex;

    CourseStatus(String courseStatusName, int courseStatusIndex) {
        this.courseStatusName = courseStatusName;
        this.courseStatusIndex = courseStatusIndex;
    }

    public static CourseStatus getCourseStatus(int value) {
        for (CourseStatus courseStatus : CourseStatus.values()) {
            if (value == courseStatus.getCourseStatusIndex()) {
                return courseStatus;
            }
        }
        return null;
    }


    public String getCourseStatusName() {
        return courseStatusName;
    }

    public void setCourseStatusName(String courseStatusName) {
        this.courseStatusName = courseStatusName;
    }

    public int getCourseStatusIndex() {
        return courseStatusIndex;
    }

    public void setCourseStatusIndex(int courseStatusIndex) {
        this.courseStatusIndex = courseStatusIndex;
    }
}
