package com.uv.spring.aop;

import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;

import java.lang.reflect.Field;

/**
 * @author uvsun 2018/6/29 下午2:13
 */
public class UVAopUtils {
    /**
     * 根据代理对象 获取 原对象
     *
     * @param proxy 代理对象
     * @return
     * @throws Exception
     */
    public static <T> T getTarget(T proxy) throws Exception {
        if (!AopUtils.isAopProxy(proxy)) {
            //不是代理对象
            return proxy;
        }

        if (AopUtils.isJdkDynamicProxy(proxy)) {
            return getJdkDynamicProxyTargetObject(proxy);
        } else { //cglib
            return getCglibProxyTargetObject(proxy);
        }
    }


    private static <T> T getCglibProxyTargetObject(T proxy) throws Exception {
        Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
        h.setAccessible(true);
        Object dynamicAdvisedInterceptor = h.get(proxy);
        Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        return (T) ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();
    }


    private static <T> T getJdkDynamicProxyTargetObject(T proxy) throws Exception {
        Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
        h.setAccessible(true);
        AopProxy aopProxy = (AopProxy) h.get(proxy);
        Field advised = aopProxy.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        return (T) ((AdvisedSupport) advised.get(aopProxy)).getTargetSource().getTarget();
    }
}
