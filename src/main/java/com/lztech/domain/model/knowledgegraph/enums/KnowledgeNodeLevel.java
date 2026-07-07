package com.lztech.domain.model.knowledgegraph.enums;

public enum KnowledgeNodeLevel {
    ZERO_LEVEL("课程", 0),
    ONE_LEVEL("一级知识点", 1),
    TWO_LEVEL("二级知识点", 2),
    THREE_LEVEL("三级知识点", 3),
    FOUR_LEVEL("四级知识点", 4),
    FIVE_LEVEL("五级知识点", 5),
    SIX_LEVEL("六级知识点", 6),
    SEVEN_LEVEL("七级知识点", 7),
    EIGHT_LEVEL("八级知识点", 8),
    NINE_LEVEL("九级知识点", 9),
    TEN_LEVEL("十级知识点", 10);

    private String name;
    private int index;

    KnowledgeNodeLevel(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public static KnowledgeNodeLevel getKnowledgeNodeLevel(int sourceIndex) {
        for (KnowledgeNodeLevel knowledgeNodeLevel : KnowledgeNodeLevel.values()) {
            if (sourceIndex == knowledgeNodeLevel.getIndex()) {
                return knowledgeNodeLevel;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
