package com.niuzhuang.richbuy.common.mvc;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: huimeng.li
 * @Description:
 * @Date: 2020/4/18 17:24
 */
@Component
public class ResponseBodyWrapFactoryBean implements InitializingBean {
    @Autowired
    private RequestMappingHandlerAdapter adapter;

    @Override
    public void afterPropertiesSet() throws Exception {
        List<HandlerMethodReturnValueHandler> returnValueHandlers = adapter.getReturnValueHandlers();
        adapter.setReturnValueHandlers(decorateHandlers(returnValueHandlers));
    }

    private List<HandlerMethodReturnValueHandler> decorateHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        List<HandlerMethodReturnValueHandler> newHanlders = new ArrayList<>();
        for (HandlerMethodReturnValueHandler handler : handlers) {
            if (handler instanceof RequestResponseBodyMethodProcessor) {
                //用自己的ResponseBody包装类替换掉框架的，达到返回Result的效果
                ResponseBodyWrapHandler decorator = new ResponseBodyWrapHandler(handler);
                newHanlders.add(decorator);
            } else {
                newHanlders.add(handler);
            }
        }
        return newHanlders;
    }
}
