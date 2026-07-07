package com.lztech.site.viewmodel.courseexpansion;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListeningTypeConfigResource {

    private Integer showOrder;

    private String listeningTypeCode;

    private String listeningTypeName;

    private String courseType;

    private String courseTypeName;

    private Boolean isRelatedCourseTable;

    private String relatedScheduleTypes;

    private Boolean isWebPageShow;
}

