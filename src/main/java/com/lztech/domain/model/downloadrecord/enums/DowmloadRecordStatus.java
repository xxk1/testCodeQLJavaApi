package com.lztech.domain.model.downloadrecord.enums;

public enum DowmloadRecordStatus {
    PACK_ING(0,"打包中"),
    PACK_END(1,"打结结束"),
    PACK_FAIL(2,"打包失败"),
    UP_ING(3, "上传中"),
    UP_END(4, "上传结束"),
    UP_FAIL(5, "上传失败"),
    GENERATE_ING(6,"生成中"),
    GENERATE_END(7,"生成结束"),
    GENERATE_FAIL(8,"生成失败");

;

    private int value;

    private String name;

    DowmloadRecordStatus(int value, String name) {
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
}
