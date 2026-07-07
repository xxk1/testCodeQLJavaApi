package com.lztech.site.viewmodel.studentschedule;

import lombok.Data;

import java.math.BigInteger;

@Data
public class GroupMemberStatisticsSqlVo {

    private String groupId;

    private BigInteger studentCount;
}
