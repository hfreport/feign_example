package com.example.feign.example.proxy;

/**
 * @author hf
 * date   2021/12/9 11:01
 * description
 */
public class Request {

    // 请求封装

    private String requestBody;

    private String methodName;


    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
