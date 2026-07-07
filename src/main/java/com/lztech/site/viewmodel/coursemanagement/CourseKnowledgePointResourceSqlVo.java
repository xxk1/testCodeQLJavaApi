package com.lztech.site.viewmodel.coursemanagement;

import lombok.Data;

@Data
public class CourseKnowledgePointResourceSqlVo {

    private String pointId;

    private String pointParentId;

    private String pointContent;

    private String courseId;

    private String versionId;

    private String resourceId;

    private Integer resourceType;

    private Integer resourceContentNum;

    private Integer resourceReferenceNum;

    private Integer resourceOtherReferenceNum;

    private String resourceName;

    private String resourceDetailId;

    private boolean isPublic;

    private String modifierId;

    private String modifierName;

    private String modifierTime;

    private int pointShowOrder;

    private int sectionShowOrder;

    private int chapterShowOrder;

}
