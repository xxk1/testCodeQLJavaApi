package com.lztech.domain.model.coursetable.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Source {

    DATA_DOCKING("数据对接", 0),
    SYSTEM_ENTRY("系统录入", 1),
    EXTERNAL_IMPORT("外部导入", 2),
    AUTONOMOUS_CLASS("自主开班", 3),
    PREPARATION_COURSE("备课夹新建课程", 4),
    TEACHING_CALENDAR("二次排课", 5);

    private String sourceName;
    private int index;

    Source(String sourceName, int index) {
        this.sourceName = sourceName;
        this.index = index;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    @JsonValue
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public static Source getUserSource(int sourceIndex) {
        for (Source source : Source.values()) {
            if (sourceIndex == source.getIndex()) {
                return source;
            }
        }
        return null;
    }
}