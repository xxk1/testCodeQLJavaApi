package com.lztech.domain.model.administrativeclassandgroup;

import com.lztech.domain.model.administrativeclass.AdministrativeClass;
import com.lztech.domain.model.college.College;
import com.lztech.domain.model.coursetable.enums.Source;
import com.lztech.domain.model.group.Group;
import com.lztech.domain.model.major.Major;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "tb_administrative_class_and_group")
public class AdministrativeClassAndGroup {
    @Id
    private String id;
    /**
     * 行政班ID
     */
    @ManyToOne
    @JoinColumn(name = "administrative_class_id")
    private AdministrativeClass administrativeClass;
    /**
     * 行政班名称
     */
    private String className;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 教师ID
     */
    private String teacherId;
    /**
     * 教师名称
     */
    private String teacherName;
    /**
     * 年级
     */
    private String year;
    /**
     * 学生人数
     */
    private Integer studentsCount;
    /**
     * 专业
     */
    @ManyToOne
    @JoinColumn(name = "major_id")
    private Major major;
    /**
     * 学院
     */
    @ManyToOne
    @JoinColumn(name = "college_id")
    private College college;
    /**
     * 组
     */
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
    /**
     * 数据来源
     */
    @Enumerated(EnumType.ORDINAL)
    private Source source;

}
