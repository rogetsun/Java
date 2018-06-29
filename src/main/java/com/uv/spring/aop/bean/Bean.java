package com.uv.spring.aop.bean;

import com.uv.spring.aop.annotation.Limit;
import com.uv.spring.aop.annotation.LimitScope;
import com.uv.spring.aop.annotation.ReceiveCmd;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author uvsun 2018/6/28 下午2:17
 */
@Component("Bean")
@Scope(scopeName = "prototype")
public class Bean implements ApplicationContextAware {
    private ApplicationContext ac;

    @Limit(limitScope = LimitScope.NetGate)
    @ReceiveCmd
    public void dealCmd(Cmd cmd) {
        System.out.println("******dealCmd******");
        System.out.println(this);
        System.out.println(this.hashCode());
        System.out.println("Bean.dealCmd:" + cmd);
        System.out.println("******dealCmd******");
    }

    public void receiveCmd(Cmd cmd) throws Throwable {
        System.out.println("******receiveCmd******");
        System.out.println(this.hashCode());
        Method m = this.getClass().getMethod("dealCmd", Cmd.class);
        System.out.println("orginMethod:" + m);
        System.out.println("orginMethod:" + m.hashCode());
        Bean proxy = (Bean) AopContext.currentProxy();
        System.out.println(proxy.hashCode());
        Method m2 = proxy.getClass().getMethod("dealCmd", Cmd.class);
        System.out.println("proxyMethod:" + m2);
        System.out.println("proxyMethod:" + m2.hashCode());
        System.out.println("-----------------------");
        //原始方法被执行
        m.invoke(this, cmd);
        System.out.println("-----------------------");

        //切片代理方法被执行
        m.invoke(proxy, cmd);
        System.out.println("-----------------------");
        //切片代理方法被执行
        m2.invoke(proxy, cmd);
        System.out.println("******receiveCmd******");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ac = applicationContext;
    }
}
