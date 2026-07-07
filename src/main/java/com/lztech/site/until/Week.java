package com.lztech.site.until;


public enum Week {
    SUNDAY("星期日", 0), MONDAY("星期一", 1), TUESDAY("星期二", 2), WEDNESDAY("星期三", 3),
    THURSDAY("星期四", 4), FRIDAY("星期五", 5), SATURDAY("星期六", 6);

    private String weekName;
    private int index;

    Week(String weekName, int index) {
        this.weekName = weekName;
        this.index = index;
    }


    public static String getEntranceGuardStatusName(int index) {
        for (Week week : Week.values()) {
            if (week.getIndex() == index) {
                return week.getWeekName();
            }
        }
        return null;
    }

    public static Week getWeekByIndex(int index) {
        for (Week week : Week.values()) {
            if (week.getIndex() == index) {
                return week;
            }
        }
        return null;
    }

    public static Integer getEntranceGuardStatusName(String weekName) {
        for (Week week : Week.values()) {
            if (week.getWeekName().equals(weekName)) {
                return week.getIndex();
            }
        }
        return null;
    }

    public String getWeekName() {
        return weekName;
    }

    public void setWeekName(String weekName) {
        this.weekName = weekName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
