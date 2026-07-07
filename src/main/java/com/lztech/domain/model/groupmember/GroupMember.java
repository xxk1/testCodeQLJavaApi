package com.lztech.domain.model.groupmember;

import com.lztech.domain.model.base.BaseUpdateModel;
import com.lztech.domain.model.group.Group;
import com.lztech.domain.model.groupmember.enums.GroupMemberSource;
import com.lztech.domain.model.groupmember.enums.GroupMemberStatus;
import com.lztech.domain.model.groupmember.enums.StudentIdentity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tb_group_member")
public class GroupMember extends BaseUpdateModel {
    @Id
    @GenericGenerator(name = "user-uuid", strategy = "uuid")
    @GeneratedValue(generator = "user-uuid")
    @Column(name = "id", nullable = false)
    private String id;
    /**
     * 学生Id
     */
    private String studentId;
    /**
     * 学生学号
     */
    private String studentNo;
    /**
     * 学生姓名
     */
    private String studentName;
    /**
     * openId
     */
    private String openId;

    /**
     * 组
     */
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
    /**
     * 数据状态
     */
    @Enumerated(EnumType.ORDINAL)
    private GroupMemberStatus groupMemberStatus;
    /**
     * 行政班
     */
    private  String administrativeClassName;
    /**
     * 数据来源
     */
    @Enumerated(EnumType.ORDINAL)
    private GroupMemberSource source;

    /**
     * 学生身份
     */
    @Enumerated(EnumType.ORDINAL)
    private StudentIdentity studentIdentity = StudentIdentity.STUDENT;

    /**
     * 上课方式编号（0：不在教室 1：在教室上课）
     */
    private Integer learningMethodCode;

    /**
     * 上课方式名称（0：不在教室上课原因、1：在教室上课原因）
     */
    private String learningMethodName;

    /**
     * 学生学院id
     */
    private String studentCollegeId;

    /**
     * 学生学院名称
     */
    private String studentCollegeName;

    /**
     * 学生专业id
     */
    private String studentMajorId;

    /**
     * 学生专业名称
     */
    private String studentMajorName;
}
