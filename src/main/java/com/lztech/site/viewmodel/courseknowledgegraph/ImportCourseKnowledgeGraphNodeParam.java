package com.lztech.site.viewmodel.courseknowledgegraph;


import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphDomain;
import lombok.Data;

@Data
public class ImportCourseKnowledgeGraphNodeParam {

    private String userId;
    private String userName;
    private CourseKnowledgeGraphDomain courseKnowledgeGraphDomain;
    private CourseKnowledgeGraphNodeTreeModel oldModel;
    private CourseKnowledgeGraphNodeTreeModel firstLevelNode = null, secondLevelNode = null, thirdLevelNode = null, fourthLevelNode = null,
            fifthLevelNode = null, sixthLevelNode = null, seventhLevelNode = null, eighthLevelNode = null, ninthLevelNode = null;
    private Boolean isSave = false;
    private Boolean isUpdate = false;
    private Integer lastLevel = 0;
    private ImportCourseKnowledgeGraphNode importCourseKnowledgeGraphNode = new ImportCourseKnowledgeGraphNode();
    private int repeatNum = 0;

    private CourseKnowledgeGraphNodeTreeModel oldFirstLevelNode = null, oldSecondLevelNode = null, oldThirdLevelNode = null, oldFourthLevelNode =
            null, oldFifthLevelNode = null, oldSixthLevelNode = null, oldSeventhLevelNode = null, oldEighthLevelNode = null, oldNinthLevelNode = null;
}

