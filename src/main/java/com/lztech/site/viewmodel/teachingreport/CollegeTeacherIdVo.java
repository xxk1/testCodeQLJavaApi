package com.lztech.site.viewmodel.teachingreport;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class CollegeTeacherIdVo {
    private String collegeId;
    private String collegeName;
    private List<TeacherIdVo> teacherIds;
}

