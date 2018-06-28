package com.uv.spring.bean.propertyOverride;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author uvsun 2017/10/21 下午3:39
 */
@Component("component")
@Scope("prototype")
public class UvComponent {

    @Resource(name = "bean1")
    private UvBean uvBean;


    public UvBean getBean() {
        return this.uvBean;
    }

}
