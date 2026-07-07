package com.lztech.domain.model.college;

import com.lztech.domain.model.administrativeclassandgroup.AdministrativeClassAndGroup;
import com.lztech.domain.model.college.enums.CourseInfoCollege;
import com.lztech.domain.model.college.enums.CourseTableCollege;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_college")
public class College {
    @Id
    private String id;
    private String collegeName;
    private String collegeCode;
    @Enumerated(EnumType.ORDINAL)
    private CourseInfoCollege courseInfoCollege;
    @Enumerated(EnumType.ORDINAL)
    private CourseTableCollege courseTableCollege;

    private int showOrder;
    /**
     * 行政班和课表班
     */
    @OneToMany(mappedBy = "group")
    private List<AdministrativeClassAndGroup> administrativeClasses = new ArrayList<>();
}
