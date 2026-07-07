package com.lztech.domain.model.knowledgegraphaigeneratetask.enums;

public enum GraphAiGenerateStatus {
    WAITING(0, "待生成"),
    GENERATING(1, "生成中"),
    SUCCESS(2, "生成成功"),
    FAIL(3, "生成失败");

    private int index;
    private String name;

    GraphAiGenerateStatus(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static GraphAiGenerateStatus getGraphAiGenerateStatus(int index) {
        for (GraphAiGenerateStatus graphAiGenerateStatus : GraphAiGenerateStatus.values()) {
            if (index == graphAiGenerateStatus.getIndex()) {
                return graphAiGenerateStatus;
            }
        }
        return null;
    }
}
