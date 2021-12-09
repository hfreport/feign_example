package com.example.feign.example;

import com.example.feign.example.custom.RemoteRequestService;
import com.example.feign.example.proxy.Client;
import com.example.feign.example.proxy.CustomClientProxyFactory;
import com.example.feign.example.proxy.Options;

/**
 * @author hf
 * date   2021/12/9 11:49
 * description
 */
public class MainTest {

    public static void main(String[] args) {

        Options options = new Options();
        options.setConnectTimeOuts(60000);

        RemoteRequestService remoteRequestService = CustomClientProxyFactory
                .builder().setClient(new Client.DefaultClient())
                .setOptions(options)
                .build(RemoteRequestService.class);

        remoteRequestService.pushData(" push data");
        remoteRequestService.sayHello(" say hello ");
        String s = remoteRequestService.defaultMethod(" default param ");
        System.out.println(s);

    }

}
