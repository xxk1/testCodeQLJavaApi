package com.lztech.domain.model.groupmember.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum GroupMemberSource {

    DATA_DOCKING("数据对接", 0),
    MANUALLY_ADD("手动添加", 1),
    SCAN_CLASS_CODE("扫码加班", 2),
    TEACHING_CALENDAR("二次排课", 3);

    private String sourceName;
    private int index;

    GroupMemberSource(String sourceName, int index) {
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

    public static GroupMemberSource getUserSource(int sourceIndex) {
        for (GroupMemberSource source : GroupMemberSource.values()) {
            if (sourceIndex == source.getIndex()) {
                return source;
            }
        }
        return null;
    }
}
