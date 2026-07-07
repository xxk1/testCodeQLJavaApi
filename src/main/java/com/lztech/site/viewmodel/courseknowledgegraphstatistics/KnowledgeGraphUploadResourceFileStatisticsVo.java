package com.lztech.site.viewmodel.courseknowledgegraphstatistics;

import lombok.Data;

import java.math.BigInteger;

@Data
public class KnowledgeGraphUploadResourceFileStatisticsVo {
    private String fileType;
    private BigInteger fileCount;
}
