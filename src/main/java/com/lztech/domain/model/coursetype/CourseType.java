package com.lztech.domain.model.coursetype;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;

@Getter
@Setter
@Entity
@Table(name = "tb_course_type")
public class CourseType {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private Integer id;
    /**
     * 课程类型名称
     */
    private String courseTypeName;
    @Max(value = 500)
    private String imagePath;
    private Integer sortName;

}
