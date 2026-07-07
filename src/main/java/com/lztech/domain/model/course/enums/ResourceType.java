package com.lztech.domain.model.course.enums;


public enum ResourceType {

    TEACHING_COURSE_WARE(0, "教学课件"),
    MICRO_VIDEO(1, "视频"),
    CLASS_TEST(2, "测验"),
    IMAGE(3, "图片"),
    THEME(4, "主题"),
    AUDIO(5,"音频");

    private Integer index;
    private String value;

    ResourceType(Integer index, String value) {
        this.index = index;
        this.value = value;
    }

    public static ResourceType getResourceType(int index) {
        for (ResourceType resourceType : ResourceType.values()) {
            if (index == resourceType.getIndex()) {
                return resourceType;
            }
        }
        return null;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
