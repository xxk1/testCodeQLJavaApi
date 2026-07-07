package com.lztech.domain.model.downloadrecord;

import com.lztech.domain.model.base.BaseUpdateModel;
import com.lztech.domain.model.downloadrecord.enums.DowmloadRecordStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "tb_download_record")
public class DownloadRecord extends BaseUpdateModel {

    private String courseId;

    private String courseName;

    private String collegeId;

    private String collegeName;

    private String versionId;

    private Integer versionSerialNumber;

    /**
     * 课程包打包状态
     */
    @Enumerated(value = EnumType.ORDINAL)
    private DowmloadRecordStatus packStatus;
    /**
     * 打包人ID
     */
    private String packUserId;
    /**
     * 下载类型0：课程打包 1：课程素材
     */
    private Integer packType;

    /**
     * 文件大小
     */
    private Long fileSize;

    /**
     * 文件类型
     */
    private String fileType;
    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 文件显示名
     */
    private String fileRealName;

    /**
     * 文件服务器存储的名称
     */
    private String fileSavedName;

    /**
     * 内网ip
     */
    private String innerIp;

    /**
     * 外网IP
     */
    private String outerIp;
}
