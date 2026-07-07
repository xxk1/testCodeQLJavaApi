package com.lztech.site.service.groupmember.annotation;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelCheckClassCodeIsExist {
    String message() default "班级编号不存在!";
}
