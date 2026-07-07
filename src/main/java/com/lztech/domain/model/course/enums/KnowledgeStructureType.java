package com.lztech.domain.model.course.enums;

import java.util.Objects;

public enum KnowledgeStructureType {
    CHAPTER(0, "章"),
    SECTION(1, "节"),
    KNOWLEDGE_POINT(2, "知识点");

    private int value;

    private String name;

    KnowledgeStructureType(int value, String name) {
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

    public static KnowledgeStructureType getKnowledgeStructureType(Integer value) {
        if (Objects.isNull(value)) {
            return null;
        }
        for (KnowledgeStructureType knowledgeStructureType : KnowledgeStructureType.values()) {
            if (value.equals(knowledgeStructureType.getValue())) {
                return knowledgeStructureType;
            }
        }
        return null;
    }
}
