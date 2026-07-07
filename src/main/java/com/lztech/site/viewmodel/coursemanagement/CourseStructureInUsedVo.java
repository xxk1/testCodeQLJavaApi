package com.lztech.site.viewmodel.coursemanagement;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourseStructureInUsedVo {

    private Integer order;

    private String userId;

    private String userName;

    /**
     * 被使用的功能
     */
    private List<String> inUsedFunctions;
}
