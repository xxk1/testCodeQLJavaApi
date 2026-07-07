package com.lztech.site.viewmodel.preparationcoursebag;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
public class TeacherCourseResourceReferenceStatisticSqlVo {

    private String teacherId;

    private String teacherName;

    private BigDecimal individualReference;

    private BigDecimal otherReference;

    private BigInteger useResourceCount;

    private BigInteger resourceTotalCount;

}
