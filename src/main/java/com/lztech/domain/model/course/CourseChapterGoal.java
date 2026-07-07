package com.lztech.domain.model.course;

import com.lztech.domain.model.base.BaseUpdateModel;
import com.lztech.domain.model.course.enums.CourseChapterGoalType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 课程章节目标
 */
@Entity
@Getter
@Setter
@Table(name = "tb_course_chapter_goal")
public class CourseChapterGoal extends BaseUpdateModel {

    private CourseChapterGoalType chapterGoalType;

    private String content;

    @ManyToOne
    @JoinColumn(name = "course_structure_id")
    private CourseStructure courseStructure;
}
