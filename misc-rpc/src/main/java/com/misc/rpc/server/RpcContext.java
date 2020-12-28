package com.misc.rpc.server;

import com.misc.rpc.core.RpcRequest;
import com.misc.rpc.core.RpcResponse;

/**
 *
 */
public interface RpcContext {

    void handler(RpcRequest request, RpcResponse response);
}
