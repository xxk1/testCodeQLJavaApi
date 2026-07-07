package com.lztech.persistence.repositories.kggentask;

import com.lztech.domain.model.kggentask.KgGenTaskMasterFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KgGenTaskMasterFileRepository extends JpaRepository<KgGenTaskMasterFile, String> {
}
