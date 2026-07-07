package com.lztech.site.viewmodel.coursestatistics.sql;

import lombok.Data;

import java.math.BigInteger;

@Data
public class CourseResourceStatisticsVo {

    private BigInteger totalNum;
    private BigInteger teachingContentNum;
    private BigInteger teachingActivityNum;

}
