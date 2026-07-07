package com.lztech.domain.model.group;

import com.lztech.domain.model.administrativeclassandgroup.AdministrativeClassAndGroup;
import com.lztech.domain.model.coursetable.CourseTable;
import com.lztech.domain.model.coursetable.enums.Source;
import com.lztech.domain.model.group.enums.GroupAttribute;
import com.lztech.domain.model.group.enums.GroupStatus;
import com.lztech.domain.model.groupmember.GroupMember;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_group")
public class Group {
    @Id
    @Column(name = "id", nullable = false)
    private String id;
    /**
     * 分组名称
     */
    private String groupName;
    /**
     * 班级属性
     */
    @Enumerated(EnumType.ORDINAL)
    private GroupAttribute groupAttribute;
    /**
     * 分组编号
     */
    private String groupNo;
    /**
     * 数据来源
     */
    @Enumerated(EnumType.ORDINAL)
    private Source source;
    /**
     * 数据状态
     */
    @Enumerated(EnumType.ORDINAL)
    private GroupStatus groupStatus;
    /**
     * 父Id
     */
    private String parentId;
    /**
     * 主课表
     */
    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CourseTable> courseTableList = new ArrayList<>();

    /**
     * 组成员
     */
    @OneToMany(mappedBy = "group")
    private List<GroupMember> groupMemberList = new ArrayList<>();
    /**
     * 行政班和课表班
     */
    @OneToMany(mappedBy = "group")
    private List<AdministrativeClassAndGroup> administrativeClasses = new ArrayList<>();

    private String creatorId; // 创建人id
    private String creatorName; //创建人姓名
    private Date createTime; // 创建时间
    private String modifierId; // 更新人id
    private String modifierName; //修改人姓名
    private Date modifyTime;// 更新时间
    private String classNickName; //班级昵称
    private String classCompositionName; //班级名称组成

    private Integer sort;// 授课班级的排序
    private String schoolYear;
    private Integer term;

    private String majorComposition;

    /**
     * 学生学院ids(多个用逗号分隔)
     */
    private String studentCollegeIds;

    /**
     * 学生学院名称s(多个用逗号分隔)
     */
    private String studentCollegeNames;

    /**
     * 学生专业ids(多个用逗号分隔)
     */
    private String studentMajorIds;

    /**
     * 学生专业名称s(多个用逗号分隔)
     */
    private String studentMajorNames;
}
