package com.lztech.domain.model.course.enums;

public enum FileContentStatus {
    DELETE(0, "已删除"),
    NORMAL(1, "正常");

    private int value;

    private String name;

    FileContentStatus(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static FileContentStatus getFileContentStatus(int value) {
        for (FileContentStatus fileContentStatus : FileContentStatus.values()) {
            if (value == fileContentStatus.getValue()) {
                return fileContentStatus;
            }
        }
        return null;
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

}
