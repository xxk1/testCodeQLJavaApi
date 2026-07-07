package com.lztech.domain.model.wisdomcourse;

import com.lztech.domain.model.base.BaseModel;
import com.lztech.domain.model.wisdomcourse.enums.HistoryTaskStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "tb_wisdom_course_history_term")
public class WisdomCourseHistoryTerm extends BaseModel {

    private String termId;

    private String schoolYear;

    private Integer term;

    private HistoryTaskStatus historyTaskStatus;

    @ManyToOne
    @JoinColumn(name = "wisdom_course_id")
    private WisdomCourse wisdomCourse;


}
