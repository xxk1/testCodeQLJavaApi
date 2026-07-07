package com.lztech.site.viewmodel.preparelessonplugin.enums;

public enum ResourceIsSameName {
    NOT_SAME("没重名", 0), SAME("重名", 1);

    private String name;
    private int index;

    ResourceIsSameName(String name, int index) {
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
}
