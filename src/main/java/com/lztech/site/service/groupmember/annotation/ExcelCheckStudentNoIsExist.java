package com.lztech.site.service.groupmember.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelCheckStudentNoIsExist {
    String message() default "学生学号!";
}
