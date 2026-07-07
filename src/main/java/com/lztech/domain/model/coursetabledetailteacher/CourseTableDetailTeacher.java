package com.lztech.domain.model.coursetabledetailteacher;

import com.lztech.domain.model.base.BaseModel;
import com.lztech.domain.model.coursetabledetail.CourseTableDetail;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tb_course_table_detail_teacher")
public class CourseTableDetailTeacher extends BaseModel {
    @Id
    @GenericGenerator(name = "user-uuid", strategy = "uuid")
    @GeneratedValue(generator = "user-uuid")
    @Column(name = "id", nullable = false)
    private String id;
    /**
     * 上课老师Id
     */
    private String teacherId;
    /**
     * 上课老师工号
     */
    private String teacherNo;
    /**
     * 上课老师名称
     */
    private String teacherName;
    /**
     * 上课老师所属学院id
     */
    private String teacherCollegeId;
    /**
     * 上课老师所属学院名称
     */
    private String teacherCollegeName;

    private String teacherTitle;
    /**
     * 课表明细Id
     */
    @ManyToOne
    @JoinColumn(name = "course_table_detail_id")
    private CourseTableDetail courseTableDetail;

    private Integer showOrder = 0;
}
