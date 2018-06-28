package com.uv.spring.aop.annotation;

import java.lang.annotation.*;

/**
 * @author uvsun 2018/6/28 下午2:18
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Limit {
    LimitScope limitScope() default LimitScope.All;
}
