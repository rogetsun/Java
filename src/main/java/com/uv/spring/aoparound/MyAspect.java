package com.uv.spring.aoparound;

import com.uv.spring.aop.annotation.Limit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author uvsun 2018/7/2 下午3:10
 */
public class MyAspect {

    public Object around(ProceedingJoinPoint jp, Limit limit, String name) {

        try {
            System.out.println("********* around2 begin ********");
            System.out.println(limit);
            System.out.println(name);

            Method m = ((MethodSignature) jp.getSignature()).getMethod();
            System.out.println(m.getReturnType());
            System.out.println(m.getReturnType().equals(Void.TYPE));
            Object r = jp.proceed();
            System.out.println("proceed return:" + r);
            System.out.println("********* around2 end ********");
            return r;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }


}
