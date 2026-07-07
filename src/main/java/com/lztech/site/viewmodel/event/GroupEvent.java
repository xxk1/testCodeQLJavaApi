package com.lztech.site.viewmodel.event;

import lombok.Data;

/**
 * @author 智教云项目组
 * @version 1.0
 * @date 2022/2/12 15:06
 * @content:
 */
@Data
public class GroupEvent {
    private String id;
    /**
     * 组编号
     */
    private String groupNo;
    /**
     * 组名称
     */
    private String groupName;
    /**
     * 事件来源来源，默认0
     */
    private int source;
    /**
     * 教学班来源
     */
    private int groupSource;
    /**
     * 班级昵称
     */
    private String classNickName;
    /**
     * 班级名称组成
     */
    private String classCompositionName;
    /**
     * 时间
     */
    private String modifyTime;

}
