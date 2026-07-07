package com.lztech.site.viewmodel.coursetabledetail;

import lombok.Data;

import java.math.BigInteger;

@Data
public class LiveCourseSqlVo {

    private String id = null;

    private String courseCode = null;

    private String courseName = null;

    private BigInteger hasLiveBroadcast = null;
}
