package com.example.feign.example.proxy;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author hf
 * date   2021/12/9 13:12
 * description
 */
public class DefaultMethodHandler implements MethodHandler{

    private MethodHandle unboundHandle;

    private MethodHandle handle;


    public DefaultMethodHandler(Method defaultMethod) {
        try {
            Class<?> declaringClass = defaultMethod.getDeclaringClass();
            Field field = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
            field.setAccessible(true);
            MethodHandles.Lookup lookup = (MethodHandles.Lookup) field.get(null);
            this.unboundHandle = lookup.unreflectSpecial(defaultMethod, declaringClass);
        } catch (NoSuchFieldException ex) {
            throw new IllegalStateException(ex);
        } catch (IllegalAccessException ex) {
            throw new IllegalStateException(ex);
        }
    }

    public void bindTo(Object proxy) {
        if (handle != null) {
            throw new IllegalStateException(
                    "Attempted to rebind a default method handler that was already bound");
        }
        handle = unboundHandle.bindTo(proxy);
    }

    @Override
    public Object invoke(Object[] args) throws Throwable{

        if (handle == null) {
            throw new IllegalStateException(
                    "Default method handler invoked before proxy has been bound.");
        }
        return handle.invokeWithArguments(args);
    }
}
