package com.lztech.site.viewmodel.kggentask.enums;

public enum KgAIGenSubTaskOperationType {
    // 0:用户取消 1:用户跳过 2:用户放弃;3:重新生成
    CANCEL(0),
    SKIP(1),
    GIVE_UP(2),
    REGENERATE(3);
    private int value;

    KgAIGenSubTaskOperationType(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

}
