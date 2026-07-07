package com.lztech.domain.model.wisdomcourse;

import com.lztech.domain.model.base.BaseUpdateModel;
import com.lztech.domain.model.wisdomcourse.enums.WisdomCourseTaskResourceType;
import com.lztech.domain.model.wisdomcourse.enums.WisdomCourseTaskStatus;
import com.lztech.domain.model.wisdomcourse.enums.WisdomCourseTaskType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "tb_wisdom_course_task")
public class WisdomCourseTask extends BaseUpdateModel {

    private String resourceId;

    private String resourceName;

    private String schoolYear;

    private Integer term;

    private WisdomCourseTaskType taskType;

    private WisdomCourseTaskResourceType resourceType;

    private WisdomCourseTaskStatus taskStatus;

    @ManyToOne
    @JoinColumn(name = "wisdom_course_id")
    private WisdomCourse wisdomCourse;

    private String remark;


}
