package com.lztech.domain.model.course;

import com.lztech.domain.model.base.BaseUpdateModel;
import com.lztech.domain.model.course.enums.CourseContentTypeEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 课程建设完成度
 */
@Entity
@Getter
@Setter
@Table(name = "tb_course_completion")
public class CourseCompletion extends BaseUpdateModel {

    /**
     * 课程内容分类
     */
    @Enumerated(value = EnumType.ORDINAL)
    private CourseContentTypeEnum courseContentEnum;

    /**
     * 是否完成
     */
    private boolean isCompleted = true;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "course_version_id")
    private CourseVersion courseVersion;

}
