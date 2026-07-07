package com.lztech.domain.model.wisdomcourse.enums;

public enum HistoryTaskStatus {

    NOT_COLLECTED(0, "未开始"),

    COLLECTING(1, "采集中"),

    PROCESSING(2, "生成中"),

    FINISHED(3, "已完成");

    private Integer code;

    private String message;

    HistoryTaskStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }
}
