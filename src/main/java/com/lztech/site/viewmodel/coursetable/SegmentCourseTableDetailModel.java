package com.lztech.site.viewmodel.coursetable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SegmentCourseTableDetailModel {
    private String id;
    private String courseDate;
    private String courseName;
    private String courseTableId;
    /**
     * 第几周
     */
    private String week;
    /**
     * 周几
     */
    private Integer weekNum;

    private Integer courseTypeId;

    private String courseTypeName;

    private String source;

    private Integer courseKind;

    private String segmentStartTime;

    private String segmentEndTime;
}
