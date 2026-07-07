package com.lztech.site.resource.coursetable.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by Administrator on 2019-06-20.
 */
public enum CourseTableStatus {
    NO_CLASS("未上课", 0),
    IN_CLASS("上课中", 1),
    OUT_CLASS("已下课", 2);

    private String statusName;
    private int index;

    CourseTableStatus(String statusName, int index) {
        this.statusName = statusName;
        this.index = index;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @JsonValue
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public static CourseTableStatus getUserSource(int statusIndex) {
        for (CourseTableStatus status : CourseTableStatus.values()) {
            if (statusIndex == status.getIndex()) {
                return status;
            }
        }
        return null;
    }
}
