package com.lztech.domain.model.kggentask.enums;

public enum KgGenTaskType {

    COURSE_MATERIAL_KNOWLEDGE_GRAPH_AI_GENERATE_TASK(0, "课程教材生成"),
    COURSE_RECORD_KNOWLEDGE_GRAPH_AI_GENERATE_TASK(1, "课堂实录"),
    COURSEWARE_KNOWLEDGE_GRAPH_AI_GENERATE_TASK(2, "课件生成");
    private Integer index;

    private String name;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    KgGenTaskType(Integer index, String name) {
        this.index = index;
        this.name = name;
    }


}
