package com.lztech.site.viewmodel.groupmember;

import lombok.Data;

@Data
public class DeleteGroupMemberInfoVo {
    private Integer source;
    private String studentId;
    private String studentNo;
    private String studentName;
    private String groupId;
    private String groupMemberId;

}
