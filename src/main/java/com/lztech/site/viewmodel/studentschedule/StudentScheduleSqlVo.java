package com.lztech.site.viewmodel.studentschedule;

import lombok.Data;

import java.math.BigInteger;

@Data
public class StudentScheduleSqlVo {

    private String courseTableDetailId;

    private String courseTableId;

    private String courseDate;

    private String segmentStartTime;

    private String segmentEndTime;

    private BigInteger courseAttr;

    private String nowWeek;

    private String segments;

    private Integer weekNum;

    private Integer courseType;


}
