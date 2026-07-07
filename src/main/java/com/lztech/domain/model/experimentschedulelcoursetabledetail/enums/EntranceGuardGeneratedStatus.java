package com.lztech.domain.model.experimentschedulelcoursetabledetail.enums;


import com.fasterxml.jackson.annotation.JsonValue;

public enum EntranceGuardGeneratedStatus {
    NOT_GENERATED(0,"没有生成"),
    FAILED(1, "生成失败"),
    SUCCESS(2, "生成成功"),
    NO_SMART_DEVICE(3, "生成失败，没有门禁设备");

    private int index;
    private String name;

    EntranceGuardGeneratedStatus(int index, String name) {
        this.index = index;
        this.name = name;
    }

    @JsonValue
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static EntranceGuardGeneratedStatus getEntranceGuardGeneratedStatus(int value) {
        for (EntranceGuardGeneratedStatus entranceGuardGeneratedStatus : EntranceGuardGeneratedStatus.values()) {
            if (value == entranceGuardGeneratedStatus.getIndex()) {
                return entranceGuardGeneratedStatus;
            }
        }
        return null;
    }
}
