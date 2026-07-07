package com.lztech.persistence.repositories.guidancereminderrecord;

import com.lztech.domain.model.guidancereminderrecord.GuidanceReminderRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuidanceReminderRecordRepository extends JpaRepository<GuidanceReminderRecord, String> {
    GuidanceReminderRecord findByUserIdAndFunctionCode(String userId, String functionCode);

}
