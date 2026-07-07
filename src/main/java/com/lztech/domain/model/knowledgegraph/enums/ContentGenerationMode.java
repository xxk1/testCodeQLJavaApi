package com.lztech.domain.model.knowledgegraph.enums;

public enum ContentGenerationMode {


    // 手工维护
    MANUAL_MAINTENANCE(0, "手工维护"),
    // 智能生成
    SMART_GENERATION(1, "智能生成");

    private int index;

    private String name;

    ContentGenerationMode(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

}
