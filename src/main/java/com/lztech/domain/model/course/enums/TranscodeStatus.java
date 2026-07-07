package com.lztech.domain.model.course.enums;

public enum TranscodeStatus {
    NOT_TRANSCODE(0, "不转码"),
    TRANS_CODING(1, "转码中"),
    TRANSCODE_SUCCESS(2, "转码成功"),
    TRANSCODE_FAIL(3, "转码失败");

    private Integer value;
    private String name;

    TranscodeStatus(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public static TranscodeStatus getResourceStatus(int value) {
        for (TranscodeStatus resourceStatus : TranscodeStatus.values()) {
            if (value == resourceStatus.getValue()) {
                return resourceStatus;
            }
        }
        return null;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
