package com.lztech.domain.model.coursetable.enums;

public enum StudentType {
    UNDERGRADUATE("本科生", "本科生",0),
    GRADUATE("硕士研究生", "研究生",1),
    DOCTOR_GRADUATE("博士研究生", "研究生",2),
    CONTINUING_EDUCATION("继续教育", "继续教育",3),
    OTHER("其他", "其他",4);


    private String name;

    private String aliasName;

    private Integer index;

    StudentType(String name,String aliasName, Integer index) {
        this.name = name;
        this.aliasName = aliasName;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public static StudentType getStudentType(int studentTypeIndex) {
        for (StudentType studentType : StudentType.values()) {
            if (studentTypeIndex == studentType.getIndex()) {
                return studentType;
            }
        }
        return null;
    }
}
