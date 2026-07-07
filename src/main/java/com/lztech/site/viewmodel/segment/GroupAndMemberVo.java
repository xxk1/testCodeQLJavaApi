package com.lztech.site.viewmodel.segment;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class GroupAndMemberVo {
    private String groupId;
    private String groupName;
    private BigInteger studentNum;
}
