package com.lztech.site.viewmodel.group;

import lombok.Data;

import java.math.BigInteger;

@Data
public class GroupMemberSqlVo {

    private String groupId;

    private BigInteger groupMemberCount;
}
