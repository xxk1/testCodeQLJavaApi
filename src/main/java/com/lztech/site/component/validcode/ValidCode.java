package com.lztech.site.component.validcode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 接口的验证秘钥 目前只支持post 或者get形式的验证其他的暂不支持...
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCode {
    String value() default "";

    /**
     * 是否进行验证码的验证 一般用作debugger
     *
     * @return
     */
    boolean notValid() default false;
}
