package com.lztech.domain.model.major;

import com.lztech.domain.model.administrativeclassandgroup.AdministrativeClassAndGroup;
import com.lztech.domain.model.college.College;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "tb_major")
public class Major {

    @Id
    private String id;
    /**
     * 学院
     */
    @ManyToOne
    @JoinColumn(name = "college_id")
    private College college;
    /**
     * 专业名称
     */
    private String majorName;
    /**
     * 年级
     */
    private String year;

    /**
     * 行政班和课表班
     */
    @OneToMany(mappedBy = "major")
    private List<AdministrativeClassAndGroup> administrativeClasses = new ArrayList<>();
}
