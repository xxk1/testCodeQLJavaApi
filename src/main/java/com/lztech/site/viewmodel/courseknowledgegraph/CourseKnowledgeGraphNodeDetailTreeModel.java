package com.lztech.site.viewmodel.courseknowledgegraph;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CourseKnowledgeGraphNodeDetailTreeModel {

    private String id = null;

    private String knowledgeNodeName = null;

    private Integer knowledgeNodeLevelIndex = null;

    private String knowledgeNodeLevelName = null;

    private Integer levelType;

    private Integer sort = null;

    private String contentDetail;

    private String courseKnowledgeGraphId  = null;

    private List<CourseKnowledgeGraphNodeDetailTreeModel> subsetCourseKnowledgeGraphNodeTreeList = new ArrayList<>();

    public CourseKnowledgeGraphNodeDetailTreeModel addSubsetCourseKnowledgeGraphNodeTreeListItem(
            CourseKnowledgeGraphNodeDetailTreeModel subsetCourseKnowledgeGraphNodeTreeListItem) {
        if (this.subsetCourseKnowledgeGraphNodeTreeList == null) {
            this.subsetCourseKnowledgeGraphNodeTreeList = new ArrayList<CourseKnowledgeGraphNodeDetailTreeModel>();
        }
        this.subsetCourseKnowledgeGraphNodeTreeList.add(subsetCourseKnowledgeGraphNodeTreeListItem);
        return this;
    }

}
