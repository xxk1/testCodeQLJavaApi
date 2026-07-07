package com.lztech.domain.model.term.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

public enum TermType {

    TERM_ONE("第一学期", 1),
    TERM_TWO("第二学期", 2),
    TERM_THREE("第三学期", 3),
    TERM_FOUR("第四学期", 4),
    TERM_FIVE("第五学期", 5),
    TERM_SIX("第六学期", 6),
    TERM_SEVEN("第七学期", 7),
    TERM_EIGHT("第八学期", 8),
    TERM_NINE("第九学期", 9),
    TERM_TEN("第十学期", 10);

    private String name;
    private Integer index;

    TermType(String name, Integer index) {
        this.name = name;
        this.index = index;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonValue
    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public static TermType getTermType(Integer index) {
        for (TermType e : TermType.values()) {
            if (Objects.equals(e.getIndex(), index)) {
                return e;
            }
        }
        return null;
    }


}