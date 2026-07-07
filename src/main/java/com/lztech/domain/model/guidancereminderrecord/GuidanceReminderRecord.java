package com.lztech.domain.model.guidancereminderrecord;

import com.lztech.domain.model.base.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "tb_guidance_reminder_record")
public class GuidanceReminderRecord extends BaseModel {
    private String userId;
    private String userNo;
    private String userName;
    /**
     * 功能编码
     */
    private String functionCode;
    /**
     * 功能描述
     */
    private String functionDescription;
    /**
     * 提醒确认状态
     */
    private Boolean reminderConfirmation;

}
