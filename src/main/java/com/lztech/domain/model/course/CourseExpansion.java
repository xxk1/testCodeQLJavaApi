package com.lztech.domain.model.course;

import com.lztech.domain.model.base.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 课程扩展信息表；目前用于思政课听课；维护思政课，expansionKey标识为思政课
 */
@Setter
@Getter
@Entity
@Table(name = "tb_course_expansion")
public class CourseExpansion extends BaseModel {

    /**
     * 课程id
     */
    private String courseId;

    /**
     * 课程扩展key;
     */
    private String expansionKey;

    /**
     * 课程扩展value
     */
    private String expansionValue;
}
