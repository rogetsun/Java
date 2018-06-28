package com.uv.spring.aop.aspect;

import com.uv.spring.aop.annotation.Limit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author uvsun 2018/6/28 下午3:15
 */
public class MyAspect {
    private void before(JoinPoint jp) {

        System.out.println(jp);

        //连接点方法即为被切面植入的目标方法
        System.out.println("获取连接点方法运行时的入参列表:" + Arrays.toString(jp.getArgs()));
        System.out.println("获取连接点的方法签名对象:" + jp.getSignature());
        System.out.println("获取连接点所在的目标对象:" + jp.getTarget());
        System.out.println("获取代理对象本身:" + jp.getThis());


        MethodSignature signature = (MethodSignature) jp.getSignature();
        System.out.println("获取连接点方法:" + signature.getMethod());
        Method m = signature.getMethod();
        //连接点方法上的注解
        Limit l = m.getAnnotation(Limit.class);
        System.out.println(l);

    }

    private void after(JoinPoint joinPoint) {

        System.out.println(joinPoint);
        System.out.println(this);
    }

}
