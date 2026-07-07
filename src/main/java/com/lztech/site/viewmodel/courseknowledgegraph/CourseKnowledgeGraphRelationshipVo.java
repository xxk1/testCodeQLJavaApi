package com.lztech.site.viewmodel.courseknowledgegraph;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class CourseKnowledgeGraphRelationshipVo implements Serializable {

    /**
     * 父节点id
     */
    private String startNodeId;
    /**
     * 子节点id
     */
    private String endNodeId;
    /**
     * 子节点名称
     */
    private String endNodeName;


}
