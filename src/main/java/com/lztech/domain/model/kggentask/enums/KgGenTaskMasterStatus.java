package com.lztech.domain.model.kggentask.enums;

public enum KgGenTaskMasterStatus {


    WAITING(0, "待生成"),
    GENERATING(1, "生成中"),
    SUCCESS(2, "生成成功"),
    FAIL(3, "生成失败"),
    PARTIAL_SUCCESS(4, "部分成功"),
    CANCEL(5, "取消");


    private Integer index;

    private String name;

    KgGenTaskMasterStatus(Integer index, String name) {
        this.index = index;
        this.name = name;
    }

    public Integer getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }


}
