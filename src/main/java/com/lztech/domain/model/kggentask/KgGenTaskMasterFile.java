package com.lztech.domain.model.kggentask;

import com.lztech.domain.model.base.BaseUpdateModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 知识图谱生成任务文件
 */
@Setter
@Getter
@Table(name = "tb_kg_gen_task_master_file")
@Entity
public class KgGenTaskMasterFile extends BaseUpdateModel {

    /**
     * 主任务id
     */
    private String masterId;

    /**
     * 课程结构id
     */
    private String courseStructureId;

    /**
     * 课程结构名称
     */
    private String courseStructureName;

    /**
     * 课件资源id
     */
    private String courseResourceId;

    /**
     * 课件资源名称
     */
    private String courseResourceName;

    /**
     * 文件类型
     */
    private String fileType;




}
