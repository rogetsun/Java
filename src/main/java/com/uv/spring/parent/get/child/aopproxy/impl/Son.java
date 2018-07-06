package com.uv.spring.parent.get.child.aopproxy.impl;

import com.uv.spring.aop.annotation.Limit;
import com.uv.spring.aop.annotation.LimitScope;
import com.uv.spring.aop.bean.Cmd;
import org.springframework.stereotype.Component;

/**
 * @author uvsun 2018/7/4 上午8:54
 */
@Component("Son")
public class Son extends Me {


    @Limit(limitScope = LimitScope.NetGate)
    public Cmd dealCmdSon(Cmd cmd) {
        System.out.println("Son.dealCmdSon:" + cmd);
        cmd.setOK(true);
        cmd.setDealer("SON");
        System.out.println("Son.dealCmdSon:" + cmd + " \t ok;");
        return cmd;
    }
}
