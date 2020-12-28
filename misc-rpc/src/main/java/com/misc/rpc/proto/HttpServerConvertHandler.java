package com.misc.rpc.proto;

import com.misc.core.exception.ConvertException;
import com.misc.core.netty.NettyConvertHandler;
import com.misc.rpc.core.RpcRequest;
import com.misc.rpc.core.RpcResponse;
import com.misc.rpc.server.RpcServerConfig;
import io.netty.buffer.ByteBufAllocator;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;

/**
 * 序列化
 */
public class HttpServerConvertHandler extends NettyConvertHandler<FullHttpRequest, FullHttpResponse, RpcRequest, RpcResponse> {

    private RpcServerConfig rpcServerConfig;

    public HttpServerConvertHandler(RpcServerConfig rpcServerConfig) {
        this.rpcServerConfig = rpcServerConfig;
    }

    /**
     * 将FullHttpRequest 转换成 RpcRequest
     * @param msg
     * @return
     * @throws ConvertException
     */
    @Override
    protected RpcRequest decode(FullHttpRequest msg) throws ConvertException {
        return rpcServerConfig.convertFullHttpRequestToRpcRequest(msg);
    }


    /**
     * 主要是用于编码，将RPC response 转换成 HTTP response
     */
    @Override
    protected FullHttpResponse encode(ByteBufAllocator allocator, RpcResponse msg) throws ConvertException {
        return rpcServerConfig.convertRpcResponseToFullHttpResponse(msg);
    }
}
