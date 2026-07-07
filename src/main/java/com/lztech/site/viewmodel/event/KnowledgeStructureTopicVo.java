package com.lztech.site.viewmodel.event;

import lombok.Data;

/**
 * @author 智教云项目组
 * @version 1.0
 * @date 2022/4/18 17:55
 * @content:
 */
@Data
public class KnowledgeStructureTopicVo {
    private String id;
    private Integer source;
    /*知识结构类型:知识点类型:2*/
    private Integer knowledgeStructureType;
    private String parentId;
    private String parentName;
    private String content;
    private String courseId;
    private String courseCode;
    private String courseName;
    private String courseVersionId;
    private String modifierId;
    private String modifierName;
    private String modifyTime;
    private String knowledgeGraphDomainId;


}
