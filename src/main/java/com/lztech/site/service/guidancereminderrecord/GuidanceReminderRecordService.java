package com.lztech.site.service.guidancereminderrecord;

import com.lztech.domain.model.guidancereminderrecord.GuidanceReminderRecord;
import com.lztech.persistence.repositories.guidancereminderrecord.GuidanceReminderRecordRepository;
import com.lztech.site.viewmodel.guidancereminderrecord.GuidanceReminderRecordResource;
import com.lztech.site.viewmodel.guidancereminderrecord.GuidanceReminderRecordVo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class GuidanceReminderRecordService {
    @Autowired
    private GuidanceReminderRecordRepository guidanceReminderRecordRepository;

    public void addGuidanceReminderRecord(GuidanceReminderRecordResource guidanceReminderRecordResource) {
        GuidanceReminderRecord guidanceReminderRecord = guidanceReminderRecordRepository.findByUserIdAndFunctionCode(
                guidanceReminderRecordResource.getUserId(), guidanceReminderRecordResource.getFunctionCode());
        if (ObjectUtils.isEmpty(guidanceReminderRecord)) {
            guidanceReminderRecord = new GuidanceReminderRecord();
            Date now = new Date();
            guidanceReminderRecord.setCreateTime(now);
            guidanceReminderRecord.setCreatorId(guidanceReminderRecordResource.getUserId());
            guidanceReminderRecord.setCreatorName(guidanceReminderRecordResource.getUserName());
            guidanceReminderRecord.setUserId(guidanceReminderRecordResource.getUserId());
            guidanceReminderRecord.setUserNo(guidanceReminderRecordResource.getUserNo());
            guidanceReminderRecord.setUserName(guidanceReminderRecordResource.getUserName());
            guidanceReminderRecord.setFunctionCode(guidanceReminderRecordResource.getFunctionCode());
            guidanceReminderRecord.setFunctionDescription(guidanceReminderRecordResource.getFunctionDescription());
            guidanceReminderRecord.setReminderConfirmation(true);
            guidanceReminderRecordRepository.saveAndFlush(guidanceReminderRecord);
        }
    }

    public GuidanceReminderRecordVo getGuidanceReminderRecord(String userId, String functionCode) {
        GuidanceReminderRecord guidanceReminderRecord = guidanceReminderRecordRepository.findByUserIdAndFunctionCode(userId, functionCode);
        if (ObjectUtils.isNotEmpty(guidanceReminderRecord)) {
            GuidanceReminderRecordVo guidanceReminderRecordVo = new GuidanceReminderRecordVo();
            guidanceReminderRecordVo.setUserId(userId);
            guidanceReminderRecordVo.setReminderConfirmation(guidanceReminderRecord.getReminderConfirmation());
            return guidanceReminderRecordVo;
        } else {
            return null;
        }
    }
}
