package com.uv.spring.parent.get.child.aopproxy;

import com.uv.spring.aop.annotation.Limit;
import com.uv.spring.aop.annotation.LimitScope;
import com.uv.spring.aop.bean.Cmd;
import org.springframework.aop.framework.AopContext;
import org.springframework.aop.support.AopUtils;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author uvsun 2018/7/4 上午8:54
 */
public abstract class Parent {

    @Resource
    private Service service;


    public void receive(Cmd cmd) {
        System.out.println(service);
        Method[] ms = this.getClass().getMethods();
        for (Method m : ms) {
            System.out.println("Parent:" + m);
            Limit limit = m.getAnnotation(Limit.class);
            if (limit != null) {
                System.out.println("Parent: found method:" + m);
                boolean isProxy = false;
                try {
                    isProxy = AopUtils.isAopProxy(AopContext.currentProxy());
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
                System.out.println("Parent: isProxy:" + isProxy);
                if (isProxy) {
                    try {
                        m.invoke(AopContext.currentProxy(), cmd);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        m.invoke(this, cmd);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Limit(limitScope = LimitScope.All)
    public Cmd dealCmdParent(Cmd cmd) {
        System.out.println("Parent.dealCmdParent:" + cmd);
        cmd.setOK(true);
        cmd.setDealer("Parent");
        System.out.println("Parent.dealCmdParent:" + cmd + " \t ok;");
        return cmd;
    }

}
