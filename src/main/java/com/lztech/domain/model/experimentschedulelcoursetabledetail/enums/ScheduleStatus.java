package com.lztech.domain.model.experimentschedulelcoursetabledetail.enums;

public enum ScheduleStatus {
    WAIT_SCHEDULE("待排课", 0),
    CANCEL_SCHEDULE("已取消", 1),
    EXPIRE_SCHEDULE("已过期", 2),
    ALREADY_SCHEDULE("已排课", 3),
    EDUCATIONAL_ADMINISTRATION_DELETE("教务删除", 4);


    private String name;
    private Integer index;

    ScheduleStatus(String name, Integer index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }


    public static ScheduleStatus getScheduleStatus(int scheduleStatusIndex) {
        for (ScheduleStatus scheduleStatus : ScheduleStatus.values()) {
            if (scheduleStatusIndex == scheduleStatus.getIndex()) {
                return scheduleStatus;
            }
        }
        return null;
    }
}
