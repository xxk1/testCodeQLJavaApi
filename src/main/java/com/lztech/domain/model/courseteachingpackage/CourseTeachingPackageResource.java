package com.lztech.domain.model.courseteachingpackage;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "tb_course_teaching_package_resource")
public class CourseTeachingPackageResource {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;//序列

    private String userId;
    private String courseId;
    private String resourceId;


    private String courseTeachingPackageId;
}
