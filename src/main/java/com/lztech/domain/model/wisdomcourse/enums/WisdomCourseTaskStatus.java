package com.lztech.domain.model.wisdomcourse.enums;

public enum WisdomCourseTaskStatus {

    WAITING(0, "待处理"),
    GENERATING(1, "处理中"),
    SUCCESS(2, "已完成"),
    FAIL(3, "失败"),
    NO_NEED_GENERATION(4,"无需处理");

    private final Integer code;

    private final String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static WisdomCourseTaskStatus getByCode(Integer code) {
        for (WisdomCourseTaskStatus value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }

    WisdomCourseTaskStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
