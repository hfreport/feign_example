package com.example.feign.example.proxy;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author hf
 * date   2021/12/9 11:35
 * description
 */
public class CustomClientProxyFactory {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Client client;

        private Options options;

        public Client getClient() {
            return client;
        }

        public Builder setClient(Client client) {
            this.client = client;
            return this;
        }

        public Options getOptions() {
            return options;
        }

        public Builder setOptions(Options options) {
            this.options = options;
            return this;
        }

        public <T> T build(Class<T> target) {

            HashMap<Method, MethodHandler> handlerMap = new HashMap<>();

            List<DefaultMethodHandler> defaultHandlers = new ArrayList<>();

            Method[] methods = target.getMethods();
            for (Method method : methods) {
                if (method.getDeclaringClass() == Object.class) {
                    continue;
                }
                if (isDefault(method)) {
                    DefaultMethodHandler defaultMethodHandler = new DefaultMethodHandler(method);
                    defaultHandlers.add(defaultMethodHandler);
                    handlerMap.put(method, defaultMethodHandler);
                } else {
                    handlerMap.put(method, new InvocationMethodHandler(client, options, method));
                }
            }

            // 创建 invocationHandler
            DefaultInvocationHandler invocationHandler = new DefaultInvocationHandler(handlerMap);

            T proxy = (T) Proxy.newProxyInstance(target.getClassLoader(), new Class[]{target}, invocationHandler);

            if (defaultHandlers.size() != 0) {
                for (DefaultMethodHandler methodHandler : defaultHandlers) {
                    methodHandler.bindTo(proxy);
                }
            }
            return proxy;
        }
    }

    public static boolean isDefault(Method method) {
        final int SYNTHETIC = 0x00001000;
        return ((method.getModifiers()
                & (Modifier.ABSTRACT | Modifier.PUBLIC | Modifier.STATIC | SYNTHETIC)) == Modifier.PUBLIC)
                && method.getDeclaringClass().isInterface();
    }

}
