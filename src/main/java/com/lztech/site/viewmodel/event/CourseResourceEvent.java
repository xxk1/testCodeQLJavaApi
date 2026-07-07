package com.lztech.site.viewmodel.event;

import lombok.Data;

/**
 * @author admin
 */
@Data
public class CourseResourceEvent {
    private String id;
    private Integer source;
    private Boolean isPublic;
    private Integer resourceType;
    private String resourceDetailId;
    private String resourceName;
    private Long resourceSize;
    private Integer resourceContentNum;
    private String courseStructureId;
    private String courseStructureName;
    private String courseId;
    private String courseCode;
    private String courseName;
    private String courseVersionId;
    private String modifyTime;
    private String modifierId;
    private String modifierName;

}
