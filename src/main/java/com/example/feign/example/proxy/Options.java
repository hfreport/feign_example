package com.example.feign.example.proxy;

/**
 * @author hf
 * date   2021/12/9 11:13
 * description
 */
public class Options {

    // 请求客户端配置, 超时时间、响应时间

    private int connectTimeOuts;

    public int getConnectTimeOuts() {
        return connectTimeOuts;
    }

    public void setConnectTimeOuts(int connectTimeOuts) {
        this.connectTimeOuts = connectTimeOuts;
    }
}
