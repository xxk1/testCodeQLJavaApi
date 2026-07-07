package com.lztech.domain.model.knowledgegraph.enums;

public enum TextDataSource {
    PPT_RECOGNITION("ppt识别", 0),
    SPEECH_RECOGNITION("语言转写识别", 1);
    private String name;
    private int index;

    TextDataSource(String name, int index) {
        this.name = name;
        this.index = index;
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

    public static TextDataSource getTextDataSource(int sourceIndex) {
        for (TextDataSource textDataSource : TextDataSource.values()) {
            if (textDataSource.getIndex() == sourceIndex) {
                return textDataSource;
            }
        }
        return null;
    }
}
