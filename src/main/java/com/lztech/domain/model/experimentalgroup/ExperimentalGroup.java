package com.lztech.domain.model.experimentalgroup;

import com.lztech.domain.model.base.BaseUpdateModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_experimental_group")
public class ExperimentalGroup extends BaseUpdateModel {

    /**
     * 组名
     */
    private String groupName;

    /**
     * 负责教师id
     */
    private String responsibleTeacherId;

    /**
     * 负责教师工号
     */
    private String responsibleTeacherNo;

    /**
     * 负责教师名称
     */
    private String responsibleTeacherName;

    /**
     * 组长id
     */
    private String groupLeaderId;

    /**
     * 组长姓名
     */
    private String groupLeaderName;

    /**
     * 组长工号
     */
    private String groupLeaderNo;

    /**
     * 班级id
     */
    private String groupId;

    /**
     * 小组人数
     */
    private Integer memberNum;

    /**
     * 使用状态(true:正使用；false:已删除)
     */
    private boolean useState;

    @OneToMany(mappedBy = "experimentalGroup", cascade = CascadeType.ALL)
    private List<ExperimentalGroupMember> groupMemberList = new ArrayList<>();

}
