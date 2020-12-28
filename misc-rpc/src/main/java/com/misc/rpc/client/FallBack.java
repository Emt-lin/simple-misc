package com.misc.rpc.client;

import com.misc.rpc.core.RpcResponse;

/**
 *
 */
public interface FallBack {

    /**
     * 当请求超时的响应都会在这里
     */
    void fallback(RpcResponse response);
}
