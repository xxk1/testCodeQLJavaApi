package com.lztech.domain.model.assistant;

import com.lztech.domain.model.base.BaseModel;
import com.lztech.domain.model.coursetable.CourseTable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tb_assistant")
public class Assistant extends BaseModel {
    @Id
    @GenericGenerator(name = "user-uuid", strategy = "uuid")
    @GeneratedValue(generator = "user-uuid")
    @Column(name = "id", nullable = false)
    private String id;
    /**
     * 助教Id
     */
    private String assistantId;
    /**
     * 助教姓名
     */
    private String assistantName;
    /**
     * 学号/工号
     */
    private String userNo;
    /**
     * 主课表
     */
    @ManyToOne
    @JoinColumn(name = "tb_course_table")
    private CourseTable courseTable;

}
