package com.lztech.persistence.repositories.administratorclass;

import com.lztech.domain.model.administrativeclass.AdministrativeClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdministratorClassRepository extends JpaRepository<AdministrativeClass, String> {
    List<AdministrativeClass> findByTeacherId(String teacherId);

    List<AdministrativeClass> findByCollegeIdAndClassNameLikeOrderById(String collectorId, String classNameLike);

    List<AdministrativeClass> findByCounsellorId(String counsellorId);
}
