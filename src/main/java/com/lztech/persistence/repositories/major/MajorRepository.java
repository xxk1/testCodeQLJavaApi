package com.lztech.persistence.repositories.major;

import com.lztech.domain.model.major.Major;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface MajorRepository extends JpaRepository<Major, String> {

    Page<Major> findByMajorNameLikeOrderByIdAsc(@Param("majorName") String majorName, Pageable pageable);

    Page<Major> findByCollegeCollegeNameIsLikeOrderByIdAsc(@Param("collegeName") String collegeName, Pageable pageable);

    @Query(nativeQuery = true, value = "select * from tb_major LEFT JOIN tb_college  on tb_college.id=tb_major.college_id where" +
            " tb_major.major_name like :queryMajorName and tb_college.college_name like :queryCollegeName  " +
            "order by tb_major.id ASC")
    Page<Major> findByCollegeNameMajorNameOrderBy(@Param("queryMajorName") String queryMajorName,
                                                  @Param("queryCollegeName") String queryCollegeName, Pageable pageable);

}
