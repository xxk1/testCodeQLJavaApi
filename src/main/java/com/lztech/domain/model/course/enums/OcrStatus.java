package com.lztech.domain.model.course.enums;

public enum OcrStatus {

    UNRECOGNIZED(0, "未识别"),
    DISCERNING(1, "识别中"),
    SEND_SUCCESS(2, "识别成功"),
    SEND_FAIL(3, "识别失败");

    private int value;

    private String name;

    OcrStatus(int value, String name) {
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

    public static OcrStatus getOcrStatus(int index) {
        for (OcrStatus ocrStatus : OcrStatus.values()) {
            if (index == ocrStatus.getValue()) {
                return ocrStatus;
            }
        }
        return null;
    }

}
