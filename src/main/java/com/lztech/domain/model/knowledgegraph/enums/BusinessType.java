package com.lztech.domain.model.knowledgegraph.enums;

public enum BusinessType {
    CREATE_COURSE_KNOWLEDGE_GRAPH_NODE(0, "新增知识点"),
    DELETE_COURSE_KNOWLEDGE_GRAPH_NODE(1, "删除知识点"),
    UPDATE_COURSE_KNOWLEDGE_GRAPH_NODE_NAME(2, "修改知识点名称"),
    UPDATE_COURSE_KNOWLEDGE_GRAPH_NODE_CONTENT(3, "修改知识点内容"),
    CREATE_COURSE_KNOWLEDGE_GRAPH_NODE_LEVEL_TYPE(4, "新增知识点重要层级"),
    UPDATE_COURSE_KNOWLEDGE_GRAPH_NODE_LEVEL_TYPE(5, "修改知识点重要层级"),
    DELETE_COURSE_KNOWLEDGE_GRAPH_NODE_LEVEL_TYPE(6, "删除知识点重要层级"),
    CREATE_COURSE_KNOWLEDGE_GRAPH_NODE_RELATIONSHIP(7, "新增知识点关系"),
    DELETE_COURSE_KNOWLEDGE_GRAPH_NODE_RELATIONSHIP(8, "删除知识点关系"),
    CREATE_COURSE_KNOWLEDGE_GRAPH_NODE_LEARNING_RESOURCE(9, "新增学习资源"),
    DELETE_COURSE_KNOWLEDGE_GRAPH_NODE_LEARNING_RESOURCE(10, "删除学习资源"),
    IMPORT_COURSE_KNOWLEDGE_GRAPH_NODE(11, "导入知识点"),
    CREATE_COURSE_KNOWLEDGE_GRAPH_NODE_QUESTION(12, "新增关联题目"),
    DELETE_COURSE_KNOWLEDGE_GRAPH_NODE_QUESTION(13, "删除关联题目");
    private int index;
    private String name;


    BusinessType(int index, String name) {
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
}
