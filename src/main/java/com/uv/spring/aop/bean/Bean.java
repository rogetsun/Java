package com.uv.spring.aop.bean;

import com.uv.spring.aop.annotation.Limit;
import com.uv.spring.aop.annotation.LimitScope;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author uvsun 2018/6/28 下午2:17
 */
@Component("Bean")
@Scope(scopeName = "prototype")
public class Bean {

    @Limit(limitScope = LimitScope.NetGate)
    public void receiveCmd(Cmd cmd) {
        System.out.println("Bean.receiveCmd:" + cmd);

    }

}
