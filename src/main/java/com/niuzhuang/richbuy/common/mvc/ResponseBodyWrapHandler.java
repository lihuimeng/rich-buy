package com.niuzhuang.richbuy.common.mvc;

import com.niuzhuang.richbuy.common.dto.ActionResult;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @Author: huimeng.li
 * @Description:
 * @Date: 2020/4/18 17:24
 */
public class ResponseBodyWrapHandler implements HandlerMethodReturnValueHandler {

    private final HandlerMethodReturnValueHandler delegate;

    public ResponseBodyWrapHandler(HandlerMethodReturnValueHandler delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return delegate.supportsReturnType(returnType);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        //增加注解忽略自动包装
//        if (returnType.getMethod().isAnnotationPresent(NotAutoWrap.class)) {
//            delegate.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
//            return;
//        }
//        if (returnType.getMethod().getDeclaringClass().isAnnotationPresent(NotAutoWrap.class)) {
//            delegate.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
//            return;
//        }
        if (returnValue == null) {
            delegate.handleReturnValue(new ActionResult<>(), returnType, mavContainer, webRequest);
        } else {
            if (returnValue instanceof ActionResult) {
                delegate.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
            } else {
                delegate.handleReturnValue(new ActionResult<>(returnValue), returnType, mavContainer, webRequest);
            }
        }
    }
}
