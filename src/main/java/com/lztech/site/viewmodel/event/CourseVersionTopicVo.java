package com.lztech.site.viewmodel.event;

import lombok.Data;

/**
 * @author 智教云项目组
 * @version 1.0
 * @date 2022/4/18 20:12
 * @content:
 */
@Data
public class CourseVersionTopicVo {
    private String id;
    private Integer source;
    private String courseId;
    private Integer versionNumber;
    private String filingUserId;
    private String filingUserNo;
    private String filingUserName;
    private Double completeRate;
    private String modifierId;
    private String modifierName;
    private String modifyTime;
}
