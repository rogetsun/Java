package com.uv.spring.aoparound;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author uvsun 2018/7/2 下午3:10
 */
public class MyAspect {

    public Object around(ProceedingJoinPoint jp) {

        try {
            System.out.println("Around begin");
            Object r = jp.proceed();
            System.out.println("proceed return:" + r);
            System.out.println("around end");
            return r;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

}
