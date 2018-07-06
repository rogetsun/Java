package com.uv.spring.parent.get.child.aopproxy.impl;

import com.uv.spring.aop.annotation.Limit;
import com.uv.spring.aop.annotation.LimitScope;
import com.uv.spring.aop.bean.Cmd;
import com.uv.spring.parent.get.child.aopproxy.Parent;
import com.uv.spring.parent.get.child.aopproxy.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author uvsun 2018/7/4 上午8:54
 */
@Component("Me")
public class Me extends Parent {

    @Resource
    private Service service;

    @Limit(limitScope = LimitScope.NetGate)
    public Cmd dealCmdMe(Cmd cmd) {
        System.out.println("Me.dealCmdMe:" + cmd);
        cmd.setOK(true);
        cmd.setDealer("ME");
        System.out.println("Me.dealCmdMe:" + cmd + " \t ok;");
        return cmd;
    }

}
