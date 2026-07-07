package com.lztech.persistence.repositories.bigdata;

import com.lztech.domain.model.bigdata.BigDataTemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BigDataTempRepository extends JpaRepository<BigDataTemp,String> {
}
