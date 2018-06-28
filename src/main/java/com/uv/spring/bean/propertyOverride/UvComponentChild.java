package com.uv.spring.bean.propertyOverride;

import com.uv.spring.bean.propertyOverride.UvBean;
import com.uv.spring.bean.propertyOverride.UvComponent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author uvsun 2017/10/21 下午3:41
 */
@Component("componentChild")
@Scope("prototype")
public class UvComponentChild extends UvComponent {
    @Resource(name = "bean2")
    private UvBean uvBean;


}
