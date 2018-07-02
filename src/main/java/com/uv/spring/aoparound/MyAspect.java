package com.uv.spring.aoparound;

import com.uv.spring.aop.annotation.Limit;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author uvsun 2018/7/2 下午3:10
 */
public class MyAspect {

    public Object around(ProceedingJoinPoint jp, Limit limit, String name) {

        try {
            System.out.println("Around begin");
            System.out.println(limit);
            System.out.println(name);
            Object r = jp.proceed();
            System.out.println("proceed return:" + r);
            System.out.println("around end");
            return r;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    public Object around2(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("********* around2 begin ********");
        Object r = jp.proceed();
        System.out.println("********* around2 end ********");
        return r;
    }


}
