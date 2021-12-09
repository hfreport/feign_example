package com.example.feign.example.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author hf
 * date   2021/12/9 10:53
 * description
 */
public class DefaultInvocationHandler implements InvocationHandler {

    /**
     * 代理方法
     */
    private Map<Method, MethodHandler> methodHandlerMap;

    public DefaultInvocationHandler(Map<Method, MethodHandler> methodHandlerMap) {
        this.methodHandlerMap = methodHandlerMap;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        return methodHandlerMap.get(method).invoke(args);
    }
}
