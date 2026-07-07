package com.lztech.site.viewmodel.superviseevaluation.enums;

public enum EvaluationStatusType {
    // 0: 课程无人评价；1：教师无人评价 2：我已评价 3：我未评价
    COURSE_NO_EVALUATION(0, "课程无人评价"),
    TEACHER_NO_EVALUATION(1, "教师无人评价"),
    I_HAVE_EVALUATED(2, "我已评价"),
    I_HAVE_NOT_EVALUATED(3, "我未评价");
    private Integer code;
    private String message;

    EvaluationStatusType(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
