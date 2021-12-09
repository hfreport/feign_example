package com.example.feign.example.proxy;

/**
 * @author hf
 * date   2021/12/9 10:58
 * description
 */
public interface Client {


    Response execute(Request request, Options options);


    class DefaultClient implements Client {

        @Override
        public Response execute(Request request, Options options) {

            System.out.println(" 发起远程请求，请求方法名 " + request.getMethodName());
            System.out.println("发起远程请求， 请求体 " + request.getRequestBody());
            System.out.println("发起远程请求， 请求客户端配置参数 " + options.getConnectTimeOuts());

            Response response = new Response();

            return response;
        }
    }

}
