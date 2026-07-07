package com.lztech.site.viewmodel.event;

import lombok.Data;

/**
 * @author 智教云项目组
 * @version 1.0
 * @date 2022/2/14 13:31
 * @content:
 */
@Data
public class GroupMemberEvent {
    private String id;
    /**
     * 组编号
     */
    private String groupId;
    /**
     * 学生ID
     */
    private String studentId;
    /**
     * 事件来源来源，默认0
     */
    private int source;
    /**
     * 学生编号
     */
    private String studentNo;
    /**
     * 学生名字
     */
    private String studentName;
    /**
     * 时间
     */
    private String modifyTime;
}
