package com.lztech.domain.model.course.enums;

import lombok.Getter;

@Getter
public enum WisdomCourseTaskCollectStatus {

    NOT_COLLECT(0, "未提取"),

    ALREADY_COLLECT(1, "已提取");

    private final Integer index;
    private final String value;

    WisdomCourseTaskCollectStatus(int index, String value) {
        this.index = index;
        this.value = value;
    }

}
