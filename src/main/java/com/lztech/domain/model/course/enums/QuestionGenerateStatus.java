package com.lztech.domain.model.course.enums;

public enum QuestionGenerateStatus {

    UNRECOGNIZED(0, "未识别"),
    DISCERNING(1, "识别中"),
    SEND_SUCCESS(2, "识别成功"),
    SEND_FAIL(3, "识别失败"),
    NO_NEED_GENERATION(4,"不需要生成");
    private int value;

    private String name;

    QuestionGenerateStatus(int value, String name) {
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

    public static QuestionGenerateStatus getQuestionGenerateStatus(int index) {
        for (QuestionGenerateStatus questionGenerateStatus : QuestionGenerateStatus.values()) {
            if (index == questionGenerateStatus.getValue()) {
                return questionGenerateStatus;
            }
        }
        return null;
    }
}
