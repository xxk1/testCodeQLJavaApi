package com.lztech.domain.model.term.enums.termtypeconvert;


import com.lztech.domain.model.term.enums.TermType;

import javax.persistence.AttributeConverter;

import static com.lztech.domain.model.term.enums.TermType.getTermType;

public class TermTypeConvert  implements AttributeConverter<TermType,Integer> {
    @Override
    public Integer convertToDatabaseColumn(TermType termType) {
        return termType.getIndex();
    }

    @Override
    public TermType convertToEntityAttribute(Integer index) {
        return getTermType(index);
    }
}
