package com.example.feign.example.custom;

/**
 * @author hf
 * date   2021/12/9 10:48
 * description
 */
public interface RemoteRequestService {


    void sayHello(String param);


    void pushData(String param);


    default String defaultMethod(String param) {
        return " default " + param;
    }

}
