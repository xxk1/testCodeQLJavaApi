package com.lztech.persistence.repositories.terms;

import com.lztech.domain.model.term.Term;
import com.lztech.domain.model.term.enums.TermType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TermRepository extends JpaRepository<Term, String>, JpaSpecificationExecutor<Term> {

    @Query(nativeQuery = true, value = "SELECT * from tb_term WHERE start_date <= :nowStartTime AND end_date >= :nowEndTime")
    Term findByStartDateLessThanEqualAndEndDateGreaterThanEqual(@Param("nowStartTime") Date nowStartTime,
                                                                @Param("nowEndTime") Date nowEndTime);

    Term findTop1ByEndDateLessThanOrderByStartDateDesc(Date nowDate);

    Term findTop1ByStartDateGreaterThanOrderByStartDateAsc(Date nowDate);

    List<Term> findTop5ByEndDateLessThanOrderByStartDateDesc(Date nowDate);

    Term findBySchoolYearAndTerm(String schoolYear, TermType termType);

    List<Term> findAllByOrderByStartDateDesc();

    List<Term> findAllBySchoolYearAndTerm(String schoolYear, TermType termType);

    List<Term> findTop4ByStartDateLessThanEqualOrderByStartDateDesc(Date nowDate);

    List<Term> findTop2ByEndDateLessThanOrderByStartDateDesc(Date nowDate);

    List<Term> findByStartDateLessThan(Date nowDate);
    List<Term> findByStartDateGreaterThanEqualAndStartDateLessThanEqual(Date nowDate,Date nowStartTime);

    @Query(nativeQuery = true, value = "select * from tb_term where school_year=?1 and term=?2 limit 1")
    Term findBySchoolYearAndTerm(String schoolYear, Integer term);

    @Query(nativeQuery = true, value = "select * from tb_term where start_date <=:courseDate order by start_date desc  limit 1")
    Term getTermByDate(@Param("courseDate") Date courseDate);
}
