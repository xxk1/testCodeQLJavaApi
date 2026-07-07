package com.lztech.domain.model.knowledgegraph.enums;

public enum LevelType {
    MINOR("次要", 0),
    GENERAL("一般", 1),
    IMPORTANT("重要", 2),
    VERY_IMPORTANT("极其重要", 3);
    private String name;
    private int index;

    LevelType(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public static LevelType getLevelType(int sourceIndex) {
        for (LevelType levelType : LevelType.values()) {
            if (sourceIndex == levelType.getIndex()) {
                return levelType;
            }
        }
        return null;
    }

    public static LevelType getLevelTypeName(String name) {
        for (LevelType levelType : LevelType.values()) {
            if (name.equals(levelType.getName())) {
                return levelType;
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
