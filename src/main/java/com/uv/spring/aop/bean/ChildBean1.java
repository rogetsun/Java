package com.uv.spring.aop.bean;

import com.uv.spring.aop.annotation.Limit;
import com.uv.spring.aop.annotation.LimitScope;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author uvsun 2018/6/28 下午2:22
 */
@Component("ChildBean1")
@Scope(scopeName = "prototype")
public class ChildBean1 extends Bean {
    @Override
    @Limit(limitScope = LimitScope.All)
    public void receiveCmd(Cmd cmd) {
        System.out.println("ChildBean1.receiveCmd:" + cmd);
    }
}
