package com.lztech.site.constants;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ResultStatus {
    SUCCESS("成功", 1), ERROR("失败", 0);
    private String name;
    private Integer index;

    ResultStatus(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @JsonValue
    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
