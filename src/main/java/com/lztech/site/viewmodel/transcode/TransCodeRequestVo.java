package com.lztech.site.viewmodel.transcode;

import lombok.Data;

@Data
public class TransCodeRequestVo {
    /**
     * 资源id
     */
    private String videoResourceId;

    /**
     * 资源类型
     */
    private String resourceType;

    /**
     * 视频内网全地址
     */
    private String innerIp;

    /**
     * 文件名
     */
    private String fileName;
}
