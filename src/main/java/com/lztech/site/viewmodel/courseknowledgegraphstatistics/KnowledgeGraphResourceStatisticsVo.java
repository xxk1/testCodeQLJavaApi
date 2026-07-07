package com.lztech.site.viewmodel.courseknowledgegraphstatistics;

import lombok.Data;

import java.math.BigInteger;

@Data
public class KnowledgeGraphResourceStatisticsVo {
    private Integer resourceType;
    private BigInteger resourceCount;
}
