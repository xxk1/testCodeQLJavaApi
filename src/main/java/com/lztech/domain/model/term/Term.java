package com.lztech.domain.model.term;

import com.lztech.domain.model.term.enums.TermType;
import com.lztech.domain.model.term.enums.termtypeconvert.TermTypeConvert;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "tbTerm")
public class Term {
    /**
     * id不允许自动生成
     **/
    @Id
    private String id;
    private String schoolYear;
    @Convert(converter = TermTypeConvert.class)
    private TermType term;
    private Date startDate;
    private Date endDate;
    private String description;
    private String remark;
    private String nickName;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Term)) {
            return false;
        }
        Term term1 = (Term) o;
        return Objects.equals(getId(), term1.getId()) &&
                Objects.equals(getSchoolYear(), term1.getSchoolYear()) &&
                getTerm() == term1.getTerm() &&
                Objects.equals(getStartDate(), term1.getStartDate()) &&
                Objects.equals(getEndDate(), term1.getEndDate()) &&
                Objects.equals(getDescription(), term1.getDescription()) &&
                Objects.equals(getRemark(), term1.getRemark());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSchoolYear(), getTerm(), getStartDate(), getEndDate(), getDescription(), getRemark());
    }
}
