package com.lztech.domain.model.coursecategory;

import com.lztech.domain.model.coursetable.CourseTable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_course_category")

public class CourseCategory {
    @Id
    private Integer id;
    /**
     * 课程类别名称
     */
    private String courseCategoryName;
    private Integer sortName;

    /**
     * 组成员
     */
    @OneToMany(mappedBy = "courseCategory")
    private List<CourseTable> courseTableList = new ArrayList<>();
}
