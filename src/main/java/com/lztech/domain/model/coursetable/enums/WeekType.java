package com.lztech.domain.model.coursetable.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum WeekType {

    ONE_WEEK("单周", 0),
    BIWEEKLY("双周", 1),
    ONE_OR_TWO_WEEKS("单双周", 2);
    private String weekTypeName;
    private int index;

    WeekType(String weekTypeName, int index) {
        this.weekTypeName = weekTypeName;
        this.index = index;
    }

    public String getWeekTypeName() {
        return weekTypeName;
    }

    public void setWeekTypeName(String weekTypeName) {
        this.weekTypeName = weekTypeName;
    }

    @JsonValue
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public static WeekType getWeekTypeSource(int sourceIndex) {
        for (WeekType source : WeekType.values()) {
            if (sourceIndex == source.getIndex()) {
                return source;
            }
        }
        return null;
    }

    public static WeekType getWeekTypeByName(String weekTypeName) {
        for (WeekType source : WeekType.values()) {
            if (weekTypeName.equals(source.getWeekTypeName())) {
                return source;
            }
        }
        return null;
    }
}