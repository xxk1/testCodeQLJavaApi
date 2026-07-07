package com.lztech.site.viewmodel.coursetabledetail.enums;

public enum CourseTableDetailStatus {
    // 课表明细状态 0:已结束；1:进行中；2:未开始;3:不存在
    ENDED(0, "已结束"),
    ONGOING(1, "进行中"),
    NOT_STARTED(2, "未开始"),
    NOT_EXIST(3, "不存在");

    private final Integer value;

    private final String description;

    CourseTableDetailStatus(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public Integer getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
