package com.lztech.domain.model.experimentschedulelcoursetabledetail.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ScheduleSource {

    DATA_DOCKING(0,"数据对接"),
    SYSTEM_ENTRY( 1,"系统录入"),
    EXTERNAL_IMPORT( 2,"外部导入"),
    AUTONOMOUS_CLASS(3,"自主开班"),
    PREPARATION_COURSE( 4,"备课夹新建课程"),
    TEACHING_CALENDAR( 5,"二次排课");
    private int index;
    private String name;


    ScheduleSource( int index,String name) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonValue
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public static ScheduleSource getScheduleSource(int index) {
        for (ScheduleSource scheduleSource : ScheduleSource.values()) {
            if (index == scheduleSource.getIndex()) {
                return scheduleSource;
            }
        }
        return null;
    }
}