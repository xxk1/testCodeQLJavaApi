package com.lztech.domain.model.experimentalgroup;

import com.lztech.domain.model.base.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "tb_experimental_group_member")
public class ExperimentalGroupMember extends BaseModel {

    private String studentId;

    private String studentNo;

    private String studentName;

    private boolean isGroupLeader;

    @ManyToOne
    @JoinColumn(name = "experimental_group_id")
    private ExperimentalGroup experimentalGroup;

}
