package com.lztech.site.viewmodel.coursemanagement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseByRunTermInfoVo {

    private String courseId;

    private String termId;

    private String schoolYear;

    private Integer term;

    private String startDate;

    private String endDate;

    private String description;
}
