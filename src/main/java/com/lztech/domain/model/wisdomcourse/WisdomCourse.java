package com.lztech.domain.model.wisdomcourse;

import com.lztech.domain.model.base.BaseUpdateModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "tb_wisdom_course")
public class WisdomCourse extends BaseUpdateModel {

    private String courseId;

    private String courseName;

    private String creatorNo;

    private String modifyNo;

    /**
     * 使用状态(true:正使用；false:已删除)
     */
    private boolean useState;

    @OneToMany(mappedBy = "wisdomCourse")
    private List<WisdomCourseHistoryTerm> historyTermList = new ArrayList<>();

    @OneToMany(mappedBy = "wisdomCourse")
    private List<WisdomCourseTask> taskList = new ArrayList<>();

}
