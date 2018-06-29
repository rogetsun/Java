package com.uv.spring.aop.annotation;

import java.lang.annotation.*;

/**
 * @author uvsun 2018/6/29 下午12:04
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ReceiveCmd {
}
