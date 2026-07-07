package com.lztech.domain.model.course.enums;

import java.util.Objects;

public enum SearchEngineStatus {

    NOT_EXECUTE(0, "未推送"),
    GENERATING(1, "执行中"),
    EXECUTE_SUCCESS(2, "执行任务成功"),
    EXECUTE_FAILED(3,"执行任务失败"),
    DELETED(4,"已删除"),
    NO_NEED_EXECUTE(5,"不需要执行");


    private int value;

    private String name;

    SearchEngineStatus(int value, String name) {
        this.value = value;
        this.name = name;
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

    public static SearchEngineStatus getSearchEngineStatus(Integer value) {
        if (Objects.isNull(value)) {
            return null;
        }
        for (SearchEngineStatus searchEngineStatus : SearchEngineStatus.values()) {
            if (value.equals(searchEngineStatus.getValue())) {
                return searchEngineStatus;
            }
        }
        return null;
    }
}
