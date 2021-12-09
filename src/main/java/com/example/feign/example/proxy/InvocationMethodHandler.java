package com.example.feign.example.proxy;

import com.google.gson.Gson;

import java.lang.reflect.Method;

/**
 * @author hf
 * date   2021/12/9 10:57
 * description
 */
public class InvocationMethodHandler implements MethodHandler{

    /**
     * http client
     */
    private Client client;

    /**
     * 客户端配置参数
     */
    private Options options;

    /**
     * 代理方法
     */
    private Method method;

    public InvocationMethodHandler(Client client, Options options, Method method) {
        this.client = client;
        this.options = options;
        this.method = method;
    }

    @Override
    public Object invoke(Object[] args) throws Throwable{

        Request req = new Request();
        if (args != null && args.length > 0) {
            req.setRequestBody(new Gson().toJson(args));
        }

        req.setMethodName(method.getName());

        Response res = client.execute(req, options);

        System.out.println("发起远程请求， 序列化响应结果 ");
        return new Gson().fromJson(res.getBody(), method.getReturnType());
    }

}
