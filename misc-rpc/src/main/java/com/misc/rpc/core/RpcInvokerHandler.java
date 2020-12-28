package com.misc.rpc.core;

import com.misc.core.exception.RpcException;

import java.lang.reflect.Method;

/**
 *
 */
public interface RpcInvokerHandler {

    Object invoke(Class<?> clazz, Method method, Object ... args) throws RpcException;
}
