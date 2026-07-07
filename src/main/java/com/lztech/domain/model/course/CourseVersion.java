package com.lztech.domain.model.course;

import com.lztech.domain.model.base.BaseUpdateModel;
import com.lztech.domain.model.course.enums.CourseVersionStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * 课程版本表
 */
@Entity
@Getter
@Setter
@Table(name = "tb_course_version")
public class CourseVersion extends BaseUpdateModel {

    /**
     * 版本序号
     */
    private Integer versionSerialNumber;

    /**
     * 版本状态
     */
    @Enumerated(value = EnumType.ORDINAL)
    private CourseVersionStatus courseVersionStatus;

    /**
     * 归档时间
     */
    private Date filingTime;

    /**
     * 归档人id
     */
    private String filingUserId;
    /**
     * 归档人工号
     */
    private String filingUserNo;
    /**
     * 归档人姓名
     */
    private String filingUserName;

    /**
     * 版本备注
     */
    private String remark;

    /**
     * 版本完成度
     */
    private Double versionCompletion;



    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

}
