package com.uv.spring.parent.get.child.aopproxy;

import com.uv.spring.aop.annotation.Limit;
import com.uv.spring.aop.bean.Cmd;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author uvsun 2018/7/4 上午9:04
 */
public class MyAspect {

    private Object around(ProceedingJoinPoint jp, Limit limit, Cmd cmd) {
        Object ret = null;
        System.out.println("MyAspect: around begin;");
        System.out.println("MyAspect: limit:" + limit + ", Cmd:" + cmd);
        try {
            ret = jp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("MyAspect: around end;");
        return ret;
    }

}
