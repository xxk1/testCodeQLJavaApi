package com.lztech.site.until;

public enum LineType {
    HORIZONTAL_LINE(0,"横线"),
    OBLIQUE_LINE(1,"斜线");

    private String name;

    private Integer value;

    LineType(Integer value, String name) {
        this.name = name;
        this.value = value;
    }

    public static LineType getAppointmentStatus(Integer value) {
        for (LineType lineType : LineType.values()) {
            if (lineType.getValue() == value) {
                return lineType;
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

    public int getValue() {
        return value;
    }

    public void setValue(int index) {
        this.value = index;
    }
}
