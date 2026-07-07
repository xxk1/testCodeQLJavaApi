package com.lztech.site.viewmodel.event;

import lombok.Data;

@Data
public class KnowledgeGraphDomainTopicVo {

    private String id;

    private String courseId;

    private String courseCode;

    private String courseName;

    private Integer showOrder;

    private String versionLabel;

    private Boolean status;
}
