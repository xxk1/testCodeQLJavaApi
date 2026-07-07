package com.lztech.domain.model.projectsuite.enums;

public enum ProjectSuiteType {

     EQUIPMENT("设备仪器", 0),
     CHEAP("低值易耗", 1);

    private String name;
    private int index;


    ProjectSuiteType(String name, int index) {
        this.name = name;
        this.index = index;
    }


    public static ProjectSuiteType getProjectSuiteType(int sourceIndex) {
        for (ProjectSuiteType status : ProjectSuiteType.values()) {
            if (sourceIndex == status.getIndex()) {
                return status;
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
