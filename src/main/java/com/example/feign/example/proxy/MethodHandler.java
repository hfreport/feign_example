package com.example.feign.example.proxy;

/**
 * @author hf
 * date   2021/12/9 10:54
 * description
 */
public interface MethodHandler {

    Object invoke(Object[] args) throws Throwable;

}
