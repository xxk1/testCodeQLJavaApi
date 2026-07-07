package com.lztech.domain.model.wisdomcourse.enums;

public enum WisdomCourseTaskResourceType {

    TEACHING_MATERIAL(0, "教材"),

    COURSEWARE(1, "课件"),

    VIDEO(2, "视频"),

    QUESTION_BANK(3, "题库");

    private Integer code;

    private String message;

    WisdomCourseTaskResourceType(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static WisdomCourseTaskResourceType getByCode(Integer code) {
        for (WisdomCourseTaskResourceType value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }


}
