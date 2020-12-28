package com.misc.rpc.core;

import com.misc.core.exception.ProxyException;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.*;

/**
 * 代理类
 */
public class RpcProxy<T> implements InvocationHandler {

    /**
     * 代理接口
     */
    private final RpcInvokerHandler invokerHandler;

    /**
     * 代理接口类型
     */
    private final Class<T> inter;

    private RpcProxy(RpcInvokerHandler invokerHandler, Class<T> inter) {
        this.invokerHandler = invokerHandler;
        this.inter = inter;
    }

    @SuppressWarnings("all")
    public static <T> T newInstance(Class<T> inter, RpcInvokerHandler invokerHandler) throws ProxyException {
        if (!inter.isInterface()) {
            throw new ProxyException(String.format("The %s not be a interface", inter));
        }
        if (!inter.isInterface()) return null;

        Class<?> proxyClass = Proxy.getProxyClass(Thread.currentThread().getContextClassLoader(), inter);
        RpcProxy<T> rpcProxy= new RpcProxy<>(invokerHandler, inter);

        try {
            // 返回对象
            return (T) proxyClass.getConstructor(InvocationHandler.class).newInstance(rpcProxy);
        }catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new ProxyException(e);
        }

    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        }

        if(method.isDefault()) {
            return invokeDefaultMethod(proxy, method, args);
        }

        return invokerHandler.invoke(inter, method, args);
    }

    /**
     * 引用自 mybatis的MapperProxy
     */
    private Object invokeDefaultMethod(Object proxy, Method method, Object[] args) throws Throwable{
        final Constructor<MethodHandles.Lookup> constructor = MethodHandles.Lookup.class.
                getDeclaredConstructor(Class.class, int.class);
        if(!constructor.isAccessible()) {
            constructor.setAccessible(true);
        }
        final Class<?> declaringClass = method.getDeclaringClass();
        return constructor.newInstance(declaringClass, MethodHandles.Lookup.PRIVATE |
                MethodHandles.Lookup.PROTECTED | MethodHandles.Lookup.PACKAGE | MethodHandles.Lookup.PUBLIC)
                .unreflectSpecial(method, declaringClass).bindTo(proxy).invokeWithArguments(args);
    }

    /**
     * 修改后的toString方法
     */
    @Override
    public String toString() {
        return inter.getName() + "@" + Integer.toHexString(this.hashCode());
    }
}
